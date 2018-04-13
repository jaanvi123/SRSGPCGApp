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
import android.widget.ImageView;
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

public class FeedbackDisplayActivity extends AppCompatActivity {

    ListView feedbacklistview;
    ArrayList<String> arrayListname;
    ArrayList<String> arrayListid;
    ArrayList<String> arrayListmessage;
    ProgressDialog progressDialog;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_display);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_feedbackdisplay);
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

        builder=new AlertDialog.Builder(this);
        builder.setMessage("No Network Connection");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        feedbacklistview=(ListView)findViewById(R.id.feedbacklistview);
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
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/feedbackselect.php");
            try
            {
                HttpResponse httpResponse=httpClient.execute(httpPost);
                HttpEntity httpEntity=httpResponse.getEntity();
                InputStream is;
                is=httpEntity.getContent();
                InputStreamReader inputStream=new InputStreamReader(is);
                BufferedReader bufferedReader=new BufferedReader(inputStream);
                String line;
                StringBuilder stringBuilder=new StringBuilder();
                while((line=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(line);
                }
                String Data=stringBuilder.toString();
                JSONObject jsonObject=new JSONObject(Data);
                Log.d("Log_data",Data);
                JSONArray jsonArrayname=jsonObject.getJSONArray("name");
                JSONArray jsonArrayid=jsonObject.getJSONArray("id");
                JSONArray jsonArraymessage=jsonObject.getJSONArray("message");
                arrayListname=new ArrayList<>();
                arrayListid=new ArrayList<>();
                arrayListmessage=new ArrayList<>();
                for(int i=0;i<jsonArrayid.length();i++)
                {
                    arrayListname.add(jsonArrayname.getString(i));
                    arrayListid.add(jsonArrayid.getString(i));
                    arrayListmessage.add(jsonArraymessage.getString(i));
                }
                return true;
            }
            catch (Exception e)
            {
                Log.d("Log_Exception",e.toString());
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
                feedbacklistview.setAdapter(customAdapter);
            }
            else
            {
               // Toast.makeText(FeedbackDisplayActivity.this,"Error OnPostExecution",Toast.LENGTH_SHORT).show();
                AlertDialog dialog=builder.create();
                dialog.show();
            }
            super.onPostExecute(aBoolean);
        }
    }


    public class CustomAdapter extends BaseAdapter
    {

        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        @Override
        public int getCount() {
            return arrayListname.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayListname.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView=layoutInflater.inflate(R.layout.feedback_customadapter,parent,false);
            }
            TextView name=(TextView)convertView.findViewById(R.id.textView39_feedbackname_ca);
            name.setText(arrayListname.get(position));
            TextView id=(TextView)convertView.findViewById(R.id.textView40_feedbackid_ca);
            id.setText(arrayListid.get(position));
            TextView message=(TextView)convertView.findViewById(R.id.textView41_feedbackmessage_ca);
            message.setText(arrayListmessage.get(position));
            return convertView;
        }
    }
}
