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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class AdminNotActivity extends AppCompatActivity {

    ListView listView;

    ArrayList<String> notifarray;
    ArrayList<String> datearray;
    ArrayList<Integer> notifid;
    ProgressDialog progressDialog;
    Integer notid;
    ArrayList<NameValuePair> arrayid;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_not);
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
        builder.setMessage("Do you want to delete this notification?");
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                DeleteNotif deleteNotif=new DeleteNotif();
                deleteNotif.execute(String.valueOf(notid));
            }
        });


listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        notid=notifid.get(position);
        AlertDialog dialog=builder.create();
        dialog.show();
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
                JSONArray nid=jsonObject.getJSONArray("id");
                notifarray=new ArrayList<>();
                datearray=new ArrayList<>();
                notifid=new ArrayList<>();
                for(int i=0;i<notification.length();i++)
                {

                    notifarray.add(notification.getString(i));
                    datearray.add(date.getString(i));
                    notifid.add(nid.getInt(i));

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
                CustomAdapter customAdapter=new CustomAdapter();
                listView.setAdapter(customAdapter);
            }
            else
            {
                Toast.makeText(AdminNotActivity.this,"No Network Connection",Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(aBoolean);
        }
    }
    public class DeleteNotif extends AsyncTask<String,Integer,Boolean>
    {

        @Override
        protected Boolean doInBackground(String... params) {
            arrayid=new ArrayList<>();
            arrayid.add(new BasicNameValuePair("id",params[0]));
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/delnotification.php");
            try
            {
                httpPost.setEntity(new UrlEncodedFormEntity(arrayid));
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
                Log.d("Log_data",Data);
                JSONObject jsonObject=new JSONObject(Data);
                String response=jsonObject.getString("response");
                Log.d("Log_response",response);
                if(response.equalsIgnoreCase("ok")) {
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
                Toast.makeText(AdminNotActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                GetDataClass getDataClass=new GetDataClass();
                getDataClass.execute();

            }
            else
            {
                Toast.makeText(AdminNotActivity.this,"No Network Connection",Toast.LENGTH_LONG).show();
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
