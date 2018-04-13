package com.gpcgldh.collegeapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Training extends AppCompatActivity {

    //ListView traininglist;
    //ArrayList<String> name;
    //ArrayList<String> phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_department);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Training & Placement");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.mainarrow);
//        traininglist=(ListView)findViewById(R.id.listView_training);
//        name=new ArrayList<>();
//        name.add("Kulwinder Singh(TPO)");
//        name.add("Devinder Kumar(MOP DEPTT.)");
//        name.add("Pankaj Kumar(FD DEPTT.)");
//        name.add("Lakhbir Singh(IT DEPTT.)");
//        name.add("Yashpal Gupta(ECE DEPTT.)");
//        name.add("Rajkumar(GMT DEPTT.)");
//        phone=new ArrayList<>();
//        phone.add("9814709893");
//        phone.add("9465616090");
//        phone.add("9417751919");
//        phone.add("8146550321");
//        phone.add("9463383303");
//        phone.add("9855758800");
//        CustomAdapter customAdapter=new CustomAdapter();
//        traininglist.setAdapter(customAdapter);
//    }
//    class CustomAdapter extends BaseAdapter
//    {
//        LayoutInflater layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
//        @Override
//        public int getCount() {
//            return name.size();
//        }
//
//        @Override
//        public Object getItem(int position) {
//            return name.get(position);
//        }
//
//        @Override
//        public long getItemId(int position) {
//            return position;
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if(convertView==null)
//            {
//                convertView=layoutInflater.inflate(R.layout.trainingnavigation,parent,false);
//            }
//            TextView Name=(TextView)convertView.findViewById(R.id.textView38_namenavigation);
//            TextView Phone=(TextView)convertView.findViewById(R.id.textView37_phonenavigation);
//            Name.setText(name.get(position));
//            Phone.setText(phone.get(position));
//            return convertView;
//        }
    }
}
