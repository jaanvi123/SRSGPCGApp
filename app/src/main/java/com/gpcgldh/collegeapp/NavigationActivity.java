package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.text.Layout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NavigationActivity extends AppCompatActivity {

    GridView gridview;
    //0ImageView adapterimageview;
    ArrayList<Integer> list_images;
    ArrayList<String> list_labels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("S.R.S GPCG LUDHIANA");
        toolbar.setTitleTextColor(Color.WHITE);
       // setTitleColor("#ffffff");
        gridview=(GridView)findViewById(R.id.gridView_navigation);
//        ArrayList<String> image=new ArrayList<>();
        list_images = new ArrayList<>();
        list_images.add(R.drawable.homeus);
        list_images.add(R.drawable.courses);
        list_images.add(R.drawable.cstructure);
        list_images.add(R.drawable.achievements);
        list_images.add(R.drawable.training);
        list_images.add(R.drawable.contact);
        list_images.add(R.drawable.camera);
        list_images.add(R.drawable.location);
        //list_images.add(R.drawable.aa);
       // image.add(adapterimageview);
        list_labels=new ArrayList<>();
        list_labels.add("About Us");
        list_labels.add("Our Courses");
        list_labels.add("Infrastructure");
        list_labels.add("Achievements");
        list_labels.add("Training and Placement");
        list_labels.add("Contact Us");
        list_labels.add("Gallery");
        list_labels.add("Location");
        //list_labels.add("About App");
//        ArrayAdapter<String> listadapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        CustomAdapter customAdapter=new CustomAdapter();
        gridview.setAdapter(customAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent aboutus=new Intent(NavigationActivity.this,Aboutus.class);
                    startActivity(aboutus);
                }
                else if(position==1)
                {
                    Intent courses=new Intent(NavigationActivity.this,Department.class);
                    startActivity(courses);

                }
                else if(position==2)
                {
                    Intent achievements=new Intent(NavigationActivity.this,activityinfrastructure.class);
                    startActivity(achievements);
                }

                else if(position==3)
                {
                    Intent achievements=new Intent(NavigationActivity.this,Achievements.class);
                    startActivity(achievements);

                }
                else if(position==5)
                {
                    Intent achievements=new Intent(NavigationActivity.this,contact.class);
                    startActivity(achievements);

                }
                else if(position==6)
                {
                    Intent achievements=new Intent(NavigationActivity.this,galaryactivity.class);
                    startActivity(achievements);
//                    Uri uri = Uri.parse("http://www.gpcgldh.ac.in");
//                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//                    startActivity(intent);

                }
                else if(position==7)
                {
                    Intent achievements=new Intent(NavigationActivity.this,CollegeLocationActivity.class);
                    startActivity(achievements);

                }


                else if(position==4)
                {
                    Intent achievements=new Intent(NavigationActivity.this,Training.class);
                    startActivity(achievements);

                }



            }
        });
    }

    class CustomAdapter extends BaseAdapter
    {

        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        @Override
        public int getCount() {
            return list_labels.size();
        }

        @Override
        public Object getItem(int position) {
            return list_labels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView=layoutInflater.inflate(R.layout.gridviewadapter,parent,false);
            }
            ImageView image=(ImageView)convertView.findViewById(R.id.imageView_gridviewadapter);
            TextView text=(TextView)convertView.findViewById(R.id.textView_gridviewadapter);
            image.setImageResource(list_images.get(position));
            text.setText(list_labels.get(position));

            return convertView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.action_about_app){
          Intent i=new Intent(NavigationActivity.this,Aboutappactivity.class);
            startActivity(i);
        }
        else if(id==R.id.action_admin)
        {
            Intent i=new Intent(NavigationActivity.this,loginactivity.class);
            startActivity(i);
        }
        else if(id==R.id.action_feedback)
        {
            Intent i=new Intent(NavigationActivity.this,feedbackactivity.class);
            startActivity(i);
        }
        else if(id==R.id.action_notification)
        {
            Intent i=new Intent(NavigationActivity.this,NotificationDisplayActivity.class);
            startActivity(i);
        }

        else if(id==R.id.action_share)
        {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
           // sendIntent.putExtra(Intent.EXTRA_TEXT, text_share);
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if(id==R.id.action_staff)
        {
            Intent i=new Intent(NavigationActivity.this,FacultyActivity.class);
            startActivity(i);
        }


        return super.onOptionsItemSelected(item);
    }
}
