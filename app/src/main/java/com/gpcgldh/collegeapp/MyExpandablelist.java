package com.gpcgldh.collegeapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by hp on 13-Nov-16.
 */
public class MyExpandablelist  extends BaseExpandableListAdapter {
    Context context;
    List<Integer> years;
    Map<Integer, AchievementsData> myStudentdata;
    public MyExpandablelist(Context context,List<Integer> years,Map<Integer, AchievementsData> myStudentdata)
    {
       // LayoutInflater layoutInflater=(LayoutInflater)getS
        this.context=context;
        this.years=years;
        this.myStudentdata=myStudentdata;
    }

    @Override
    public int getGroupCount() {
        return years.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return years.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return myStudentdata.get(years.get(groupPosition));
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        int year= Integer.parseInt(getGroup(groupPosition).toString());
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.parentlist,null);
        }

        TextView txtparent=(TextView)convertView.findViewById(R.id.parentlist_courses);
        txtparent.setText(String.valueOf(year));

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        AchievementsData data=(AchievementsData) getChild(groupPosition,childPosition);
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.childlist,null);
        }
        TextView txtchild=(TextView)convertView.findViewById(R.id.childlist_year);
        TextView txt=(TextView)convertView.findViewById(R.id.childlist_fname);
        txtchild.setText(data.getName());
        txt.setText(data.getFname());
        Log.d("sname",data.getName()+"fname"+data.getFname());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
