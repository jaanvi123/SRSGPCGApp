package com.gpcgldh.collegeapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ChangePasswordActivity extends AppCompatActivity {

   EditText uname,pword,npword;
    Button button;
    String aname,ap,anp;
    ArrayList<NameValuePair> arrayList;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder,builders,buildern;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_change);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("Change Password");
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        toolbar.setTitleTextColor(Color.WHITE);
        uname = (EditText) findViewById(R.id.editText_username);
        pword = (EditText) findViewById(R.id.editText2_password);
        npword = (EditText) findViewById(R.id.editText2_newpassword);
        button = (Button) findViewById(R.id.button_submit);
        builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Fill All The Fields!");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builders=new AlertDialog.Builder(this);
        builders.setMessage("Data Updated");
        builders.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        buildern=new AlertDialog.Builder(this);
        buildern.setMessage("No Network Connection");
        buildern.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uname.length()==0 && pword.length()==0&&npword.length()==0)
                {

                    AlertDialog dialog=builder.create();
                    dialog.show();

                }
                else {
                    aname=uname.getText().toString();
                    ap=pword.getText().toString();
                    anp=npword.getText().toString();
                    SendDataClass sendDataClass=new SendDataClass();
                    sendDataClass.execute(aname,ap,anp);

                }
            }
        });

    }

       void show()
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("LOADING...");
        progressDialog.show();
    }
    public class SendDataClass extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {

            arrayList=new ArrayList<>();
            arrayList.add(new BasicNameValuePair("sendname",params[0]));
            arrayList.add(new BasicNameValuePair("sendpword",params[1]));
            arrayList.add(new BasicNameValuePair("newpword",params[2]));
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/updatepword.php");
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(arrayList));
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                InputStream is;
                is=httpEntity.getContent();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);
                String line;
                StringBuilder sb=new StringBuilder();
                while ((line=br.readLine())!=null)
                {
                    sb.append(line);
                }
                String Data=sb.toString();
                JSONObject jsonObject=new JSONObject(Data);
                String response=jsonObject.getString("response");
                if(response.equalsIgnoreCase("ok"))
                {
                    return true;
                }
            }
            catch (Exception e)
            {
                Log.d("Exception",e.toString());
            }
            return false;
        }

        @Override
        protected void onPreExecute() {

            show();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            progressDialog.dismiss();
            if(aBoolean)
            {

                //Toast.makeText(ChangePasswordActivity.this,"Updated",Toast.LENGTH_SHORT).show();
                AlertDialog dialog=builders.create();
                dialog.show();
            }
            else
            {
               // Toast.makeText(ChangePasswordActivity.this,"Check username , password and internet connection",Toast.LENGTH_LONG).show();
               AlertDialog dialog=buildern.create();
                dialog.show();
            }
            super.onPostExecute(aBoolean);
        }
        }
    }
