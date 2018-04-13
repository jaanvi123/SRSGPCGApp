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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class NotificationDisplayActivity extends AppCompatActivity {

   ListView listView;

    ArrayList<String> notifarray;
    ArrayList<String> datearray;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder,buildera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_display);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_notif);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Notification");

        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        listView=(ListView)findViewById(R.id.listview_notification);

        builder=new AlertDialog.Builder(this);
        builder.setMessage("No Network Connection");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        buildera=new AlertDialog.Builder(this);
        buildera.setMessage("No Notification!");
        buildera.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        GetDataClass getDataClass=new GetDataClass();
        getDataClass.execute();
    }
    void show()
    {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("LOADING...");
        progressDialog.show();
    }
    public class GetDataClass extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/shownotif.php");
            try
            {
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                InputStream is;
                is=httpEntity.getContent();
                InputStreamReader isr=new InputStreamReader(is);
                BufferedReader br=new BufferedReader(isr);
                String line;
                StringBuilder sb=new StringBuilder();
                while((line=br.readLine())!=null)
                {
                    sb.append(line);
                }
                String Data=sb.toString();
                JSONObject jsonObject=new JSONObject(Data);
                JSONArray notification=jsonObject.getJSONArray("notification");
                JSONArray date=jsonObject.getJSONArray("date");
                notifarray=new ArrayList<>();
                datearray=new ArrayList<>();
                for(int i=0;i<notification.length();i++)
                {

                    notifarray.add(notification.getString(i));
                    datearray.add(date.getString(i));
                }
                return true;

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
                if(notifarray.size()==0)
                {
                    AlertDialog dialog=buildera.create();
                    dialog.show();
                    dialog.setCanceledOnTouchOutside(false);
                }
                else {
                    CustomAdapter customAdapter = new CustomAdapter();
                    listView.setAdapter(customAdapter);
                }
            }
            else
            {
                //Toast.makeText(NotificationDisplayActivity.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
                AlertDialog dialog=builder.create();
               dialog.show();
                dialog.setCanceledOnTouchOutside(false);

            }
            super.onPostExecute(aBoolean);
        }
    }
    public class CustomAdapter extends BaseAdapter {

        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        @Override
        public int getCount() {
            return notifarray.size();
        }

        @Override
        public Object getItem(int position) {
            return notifarray.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.notificationfile, parent, false);
            }
            TextView name = (TextView) convertView.findViewById(R.id.text_notif);
            name.setText(notifarray.get(position) + "\n" + datearray.get(position));
            return convertView;
        }
    }
    }
