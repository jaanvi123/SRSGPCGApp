package com.gpcgldh.collegeapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

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
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NotificationService extends Service {

    ScheduledExecutorService executorService;
    Runnable runnable;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    int id = 0;
    ArrayList<String> notifarray;
    ArrayList<Integer> not_id;
    public NotificationService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        executorService = new ScheduledThreadPoolExecutor(5);
        preferences = getSharedPreferences("my_pref",MODE_PRIVATE);

            id = preferences.getInt("not_id",0);

        runnable = new Runnable() {
            @Override
            public void run() {
                new NotificationTask().execute();
            }
        };
        executorService.scheduleAtFixedRate(runnable,1,20, TimeUnit.MINUTES);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class NotificationTask extends AsyncTask<String,Integer,Integer>{

        String message;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            id = preferences.getInt("not_id",0);
        }

        @Override
        protected Integer doInBackground(String... params) {
            ArrayList<NameValuePair> list = new ArrayList<>();
            list.add(new BasicNameValuePair("nid",String.valueOf(id)));
            HttpClient httpClient=new DefaultHttpClient();
            HttpPost httpPost=new HttpPost("http://112.196.5.135:8081/collegeapp/notification.php");
            try
            {
                httpPost.setEntity(new UrlEncodedFormEntity(list));
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
                Log.d("log_tag",Data);
                JSONObject jsonObject=new JSONObject(Data);
                String respsonse=jsonObject.getString("response");
                JSONArray notification=jsonObject.getJSONArray("message");
                JSONArray jsonArrayDate=jsonObject.getJSONArray("date");
                JSONArray jsonArrayid=jsonObject.getJSONArray(("id"));
                notifarray=new ArrayList<>();
                not_id=new ArrayList<>();
                if(respsonse.equalsIgnoreCase("ok")) {
                    int gr_id=id;
                    for (int i = 0; i < notification.length(); i++) {
                        message = notification.getString(i);
                        notifarray.add(notification.getString(i));
                        not_id.add(jsonArrayid.getInt(i));
                        if(not_id.get(i)>gr_id){
                            gr_id = not_id.get(i);
                        }
                    }
                    editor = preferences.edit();
                    editor.putInt("not_id",gr_id);
                    editor.apply();
                    return notification.length();
                }
                else{
                    return 0;
                }


            }
            catch (Exception e)
            {
                Log.d("Exception",e.toString());
            }
            return -1;



        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            if(i>0){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                if(i==1) {
                    builder.setContentTitle("New Notification from college");
                    builder.setContentText(message);
                }
                else{
                    builder.setContentTitle("New Notifications");
                    builder.setContentText("You have new notifications from college");
                }
                builder.setSmallIcon(R.drawable.logo);
                Intent in = new Intent(getApplicationContext(),NotificationDisplayActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),1,in,PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(pendingIntent);
                builder.setAutoCancel(true);
                long[] vib = {200,200,200,200};
                builder.setVibrate(vib);
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(uri);

                NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Notification notification = builder.build();
                manager.notify(1,notification);
            }
        }
    }
}
