package com.gpcgldh.collegeapp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class loginactivity extends AppCompatActivity {

    Button submitbuttton;
    EditText usernameedittext,passwordedittext;
    ArrayList<NameValuePair> list;
    String username,password;
    AlertDialog.Builder builder;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("S.R.S GPCG LUDHIANA");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        submitbuttton=(Button)findViewById(R.id.button_login);
        usernameedittext=(EditText)findViewById(R.id.editText_username);
        passwordedittext=(EditText)findViewById(R.id.editText2_password);
        builder=new AlertDialog.Builder(this);
        builder.setTitle("Username or Password is incorrect!");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        submitbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=usernameedittext.getText().toString();
                password=passwordedittext.getText().toString();
                GetdataClass getdataClass=new GetdataClass();
                getdataClass.execute(username,password);
            }
        });
    }
    void showdialog()
    {
        dialog=new ProgressDialog(this);
        dialog.setMessage("LOADING...");
        dialog.show();

    }
    public class GetdataClass extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            list=new ArrayList<>();
            list.add(new BasicNameValuePair("sendusername",params[0]));
            list.add(new BasicNameValuePair("sendpassword",params[1]));
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/logintable.php");
            try
            {
                httpPost.setEntity(new UrlEncodedFormEntity(list));
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                InputStream is;
                is=httpEntity.getContent();
                InputStreamReader inputStreamReader=new InputStreamReader(is);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line;
                StringBuilder sb=new StringBuilder();
                while((line=bufferedReader.readLine())!=null)
                {
                    sb.append(line);
                }
                String Data=sb.toString();
                JSONObject jsonObject=new JSONObject(Data);
                String result=jsonObject.getString("response");
                if(result.equalsIgnoreCase("ok"))
                {
                    return true;
                }
            }
            catch(Exception e)
            {
              Log.d("Log_Exception",e.toString());
            }
            return false;
        }

        @Override
        protected void onPreExecute() {
            showdialog();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            dialog.dismiss();
            if(aBoolean)
            {
                finish();
                Intent i=new Intent(loginactivity.this,AfterLoginActivity.class);
                startActivity(i);
            }
            else
            {
               // Toast.makeText(loginactivity.this,"Username or Password is incorrect!",Toast.LENGTH_SHORT).show();
                AlertDialog dialog=builder.create();
                dialog.show();
            }
            super.onPostExecute(aBoolean);
        }
    }
}
