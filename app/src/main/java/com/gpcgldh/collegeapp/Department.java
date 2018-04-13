package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Department extends AppCompatActivity {
    GridView coursesgv;
    ArrayList<String> courseslist;
    ArrayList<Integer> images;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        coursesgv=(GridView)findViewById(R.id.gridView_courses);
        setTitle("Our Courses");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        courseslist=new ArrayList<>();
        courseslist.add("Science");
        courseslist.add("Comp Engg");
        courseslist.add("F.D");
        courseslist.add("G.M.T");
        courseslist.add("IT");
        courseslist.add("M.O.P");
        courseslist.add("Workshop");
        courseslist.add("C.D.T.P");
        courseslist.add("E.C.E");
        images=new ArrayList<>();
        images.add(R.drawable.appliedtry);
        images.add(R.drawable.computerengineeringtry);
        images.add(R.drawable.fdtry);
        images.add(R.drawable.gmttry);
        images.add(R.drawable.ittry);
        images.add(R.drawable.moptry);
        images.add(R.drawable.workshoptry);
        images.add(R.drawable.community_try);
        images.add(R.drawable.ecetry);

      CustomAdapter ca=new CustomAdapter();
        coursesgv.setAdapter(ca);
        coursesgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent i = new Intent(Department.this, AppliedScience.class);
                    startActivity(i);
                } else if (position == 1) {
                    Intent i = new Intent(Department.this, CS.class);
                    startActivity(i);
                } else if (position == 2) {
                    Intent i = new Intent(Department.this, FD.class);
                    startActivity(i);
                } else if (position == 3) {
                    Intent i = new Intent(Department.this, GMT.class);
                    startActivity(i);
                } else if (position == 4) {
                    Intent i = new Intent(Department.this, IT.class);
                    startActivity(i);
                } else if (position == 5) {
                    Intent i = new Intent(Department.this, MOP.class);
                    startActivity(i);
                } else if (position == 6) {
                    Intent i = new Intent(Department.this, workshop.class);
                    startActivity(i);
                } else if (position == 7) {
                    Intent i = new Intent(Department.this, CDTP.class);
                    startActivity(i);
                }
                else if(position==8)
                {
                    Intent i=new Intent(Department.this,ECE.class);
                    startActivity(i);
                }

            }
        });
    }
    class CustomAdapter extends BaseAdapter
    {
        LayoutInflater inflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        @Override
        public int getCount() {
            return courseslist.size();
        }

        @Override
        public Object getItem(int position) {
            return courseslist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
                convertView=inflater.inflate(R.layout.commonadapter,parent,false);
            }
            TextView about=(TextView)convertView.findViewById(R.id.textView71_commonadap);
            about.setText(courseslist.get(position));
//            ImageView image=(ImageView)convertView.findViewById(R.id.departmentimage);
//            image.setImageResource(images.get(position));
            LinearLayout image=(LinearLayout) convertView.findViewById(R.id.departmentimage);
            image.setBackgroundResource(images.get(position));
            return convertView;
        }
    }
}
