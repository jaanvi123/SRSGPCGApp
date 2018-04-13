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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class feedbackactivity extends AppCompatActivity {

    Button submitfbbutton;
    EditText name,id,message;
    String sendname,sendid,sendmessage;
    ArrayList<NameValuePair> list_fb_data;
    AlertDialog.Builder builder,builders,buildern,buildere,builderun,builderum;
    ProgressDialog progressdialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackactivity);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Feedback");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        submitfbbutton=(Button)findViewById(R.id.button2_submit_fb);
        name=(EditText)findViewById(R.id.editText4_namefb);
        id=(EditText)findViewById(R.id.editText5_idfb);
        message=(EditText)findViewById(R.id.editText6_messagefb);
        builder=new AlertDialog.Builder(this);
        builder.setTitle("Please Fill All The Fields!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builders=new AlertDialog.Builder(this);
        builders.setMessage("Thank You For Your Feedback!");
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
        buildere=new AlertDialog.Builder(this);

        buildere.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builderun=new AlertDialog.Builder(this);

        builderun.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builderum=new AlertDialog.Builder(this);

        builderum.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        submitfbbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendname = name.getText().toString();
                sendid = id.getText().toString();
                sendmessage = message.getText().toString();
                Pattern p=Pattern.compile("^[\\w}+-_\\.+]*[\\w}+-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
                Matcher m=p.matcher(sendid);
                if(name.length()==0 && id.length()==0&&message.length()==0) {
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
                else if(!m.find())
                {
                    buildere.setMessage("Please Enter Valid E-mail Address!");
                    AlertDialog dialog=buildere.create();
                    dialog.show();
                }
                else if(!sendname.matches("^[A-Z,a-z]+$"))
                {
                    builderun.setMessage("Please Enter Valid Username!");
                    AlertDialog dialog=builderun.create();
                    dialog.show();

                }
                else if(sendmessage.length()>300)
                {
                    builderum.setMessage("Message length should not be more than 300!");
                    AlertDialog dialog=builderum.create();
                    dialog.show();

                }
                else {
                    FeedbackSubmitClass feedbackSubmitClass = new FeedbackSubmitClass();
                    feedbackSubmitClass.execute(sendname, sendid, sendmessage);
                }
            }
        });

    }
    void show()
    {
       progressdialog=new ProgressDialog(this);
        progressdialog.setMessage("LOADING...");
        progressdialog.show();
    }
    public class FeedbackSubmitClass extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {

            list_fb_data=new ArrayList<>();
            list_fb_data.add(new BasicNameValuePair("sendname",params[0]));
            list_fb_data.add(new BasicNameValuePair("sendid",params[1]));
            list_fb_data.add(new BasicNameValuePair("sendmessage",params[2]));
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/feedbacksfb.php");

            try
            {
                httpPost.setEntity(new UrlEncodedFormEntity(list_fb_data));
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity entity=httpResponse.getEntity();
                InputStream responseget;
                responseget=entity.getContent();
                InputStreamReader inputStreamReader=new InputStreamReader(responseget);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String getline;
                StringBuilder sb=new StringBuilder();
                while((getline=bufferedReader.readLine())!=null)
                {
                    sb.append(getline);
                }
                String data=sb.toString();
                Log.d("log_response",data);
                JSONObject json=new JSONObject(data);
                String r = json.getString("response");
                if(r.equalsIgnoreCase("inserted")) {
                    return true;
                }
            }
            catch(Exception e)
            {
                Log.d("log_exception",e.toString());
            }
            return false;
        }

        @Override
        protected void onPreExecute() {

            show();
        }

        protected void onPostExecute(Boolean aBoolean) {
            progressdialog.dismiss();
            if(aBoolean){
                AlertDialog dialog=builders.create();
                dialog.show();
                name.setText("");
                id.setText("");
                message.setText("");
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
