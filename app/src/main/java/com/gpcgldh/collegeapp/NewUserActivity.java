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
import android.widget.TextView;
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

public class NewUserActivity extends AppCompatActivity {

    EditText username,password;
    Button button;
    String uname,pword;
    ProgressDialog progressDialog;
    ArrayList<NameValuePair> arrayList;
    AlertDialog.Builder builder,buildern,builername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_newuser);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("Create Account");
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        toolbar.setTitleTextColor(Color.WHITE);
        username=(EditText) findViewById(R.id.editText_username);
        password=(EditText)findViewById(R.id.editText2_password);
        button=(Button)findViewById(R.id.button_submit);

        builder=new AlertDialog.Builder(this);
        builder.setMessage("Data Submitted");
        builername=new AlertDialog.Builder(this);
        builername.setMessage("Please enter valid username and password like jaanvijuneja");
        builername.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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

              uname= username.getText().toString();
                pword=password.getText().toString();
                if(uname.length()!=0 && pword.length()!=0) {
                    if((uname.matches("^[A-Za-z]+$"))&&(pword.matches("^[a-zA-Z0-9]+$")) ) {

                        SubmitDataClass submitDataClass = new SubmitDataClass();
                        submitDataClass.execute(uname,pword);

                    }
                    else
                    {
                      AlertDialog dialog=builername.create();
                        dialog.show();
                    }

                }
                else
                {

                    AlertDialog dialog=builername.create();
                    dialog.show();
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
    public class SubmitDataClass extends AsyncTask<String,Integer,Boolean>
    {
        @Override
        protected Boolean doInBackground(String... params) {
            arrayList=new ArrayList<>();
            arrayList.add(new BasicNameValuePair("sendname",params[0]));
            arrayList.add(new BasicNameValuePair("sendpword",params[1]));
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/newuser.php");
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
                Log.d("Data_New_User",Data);
                JSONObject jsonObject=new JSONObject(Data);
                String response=jsonObject.getString("response");
                Log.d("Response_New_User",response);
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

              AlertDialog dialog=builder.create();
                dialog.show();
            }
            else
            {
                AlertDialog dialog=buildern.create();
                dialog.show();
            }
            super.onPostExecute(aBoolean);
        }
    }
}
