package com.peepers.peeper;

import android.content.Context;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by m_yg on 2017-05-19.
 */

public class ListAdapter extends BaseAdapter {

    ArrayList<ListModel> items;
    LayoutInflater inflater;
    Context context;

    public ListAdapter(Context context, ArrayList<ListModel> items){
        this.context = context;
        this.items = items;
        this.inflater = LayoutInflater.from(context);
    }

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
        if(convertView == null){
            convertView = inflater.inflate(R.layout.custom_item, parent, false);
        }
        TextView tv1 = (TextView) convertView.findViewById(R.id.item_title);
        TextView tv2 = (TextView) convertView.findViewById(R.id.item_description);
        TextView tv3 = (TextView) convertView.findViewById(R.id.item_link);
        TextView tv4 = (TextView) convertView.findViewById(R.id.item_bloggername);

        tv1.setText(items.get(position).getTitle());
        tv2.setText(items.get(position).getDescription());
        tv3.setText(items.get(position).getLink());
        tv4.setText(items.get(position).getBloggername());

        return convertView;
    }
}
