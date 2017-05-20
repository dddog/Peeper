package com.peepers.peeper;

import android.content.Context;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by m_yg on 2017-05-19.
 */

public class ListAdapter extends BaseAdapter {

    ArrayList<ListModel> items;
    LayoutInflater inflater;
    Context context;

    TextView title;
    TextView description;
    TextView link;
    TextView bloggername;

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_item, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.tvDesc = (TextView) convertView.findViewById(R.id.item_description);
            viewHolder.tvLink = (TextView) convertView.findViewById(R.id.item_link);
            viewHolder.tvBlog = (TextView) convertView.findViewById(R.id.item_bloggername);
        }
        else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListModel listModel = items.get(position);
        viewHolder.tvTitle.setText(listModel.getTitle());
        viewHolder.tvDesc.setText(listModel.getDescription());
        viewHolder.tvLink.setText(listModel.getLink());
        viewHolder.tvBlog.setText(listModel.getBloggername());
        return convertView;
    }
    class  ViewHolder{
        TextView tvTitle;
        TextView tvDesc;
        TextView tvLink;
        TextView tvBlog;

    }

}
