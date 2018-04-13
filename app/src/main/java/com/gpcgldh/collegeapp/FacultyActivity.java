package com.gpcgldh.collegeapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FacultyActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_faculty);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setTitle("Our Faculty");
        toolbar.setNavigationIcon(R.drawable.mainarrow);
        toolbar.setTitleTextColor(Color.WHITE);
        listView=(ListView)findViewById(R.id.listView_staff);
        textView=(TextView)findViewById(R.id.textView_staff);
        arrayList=new ArrayList();
        arrayList.add("Applied Science");
        arrayList.add("Computer Engineering");
        arrayList.add("Electronics & Comm. Engg.");
        arrayList.add("Fashion Designing");
        arrayList.add("Garment Manufacturing Technology");
        arrayList.add("Information Technology");
        arrayList.add("Modern Office Practice");
        arrayList.add("Workshop");
        arrayList.add("Office Staff");
        arrayList.add("Community Development Through Polytechnics");
        StaffAdapter staffAdapter=new StaffAdapter();
        listView.setAdapter(staffAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=app_sc");
                    startActivity(i);
                }
                else  if(position==1)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=cse");
                    startActivity(i);
                }
                else  if(position==2)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=ece");
                    startActivity(i);
                }
                else  if(position==3)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=fd");
                    startActivity(i);
                }
                else  if(position==4)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=gmt");
                    startActivity(i);
                }
                else  if(position==5)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=it");
                    startActivity(i);
                }
                else  if(position==6)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=mot");
                    startActivity(i);
                }
                else  if(position==7)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=wksp");
                    startActivity(i);
                }
                else  if(position==8)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=ofc");
                    startActivity(i);
                }
                else  if(position==9)
                {
                    Intent i=new Intent(FacultyActivity.this,StaffActivity.class);
                    i.putExtra("url","http://www.gpcgldh.ac.in/faculty_department.php?d=cdtp");
                    startActivity(i);
                }

            }
        });
    }
    public class StaffAdapter extends BaseAdapter
    {
        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null)
            {
             convertView=layoutInflater.inflate(R.layout.stafffile,parent,false);
            }
            TextView staff=(TextView)convertView.findViewById(R.id.textView_staff);
            staff.setText(arrayList.get(position));
            return convertView;
        }
    }
}
