package com.company.xpertech.xpertech;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Skylar Gail on 7/28/2018.
 */

public class ListViewItemAdapter extends BaseAdapter{
    Context ctx;
    LayoutInflater inflater;
    private List<TroubleshootItem> troubleshootItemList = null;
    private ArrayList<TroubleshootItem> troubleshootItemList1;

    public ListViewItemAdapter(Context ctx, List<TroubleshootItem> troubleshootItemList){
        this.ctx = ctx;
        inflater =  (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.troubleshootItemList1 = new ArrayList<TroubleshootItem>();
        this.troubleshootItemList1.addAll(troubleshootItemList);
    }

    public class ViewHolder{
        TextView item;
    }

    @Override
    public int getCount() {
        return troubleshootItemList1.size();
    }

    @Override
    public Object getItem(int position) {
        return troubleshootItemList1.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        View view = convertView;

        if(view == null){
            holder  = new ViewHolder();
            view = inflater.inflate(R.layout.activity_troubleshoot_item, parent, false);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        // locate the item in troubleshoot list
        holder.item  =  (TextView) view.findViewById(R.id.troubleshoot_item);
        view.setTag(holder);

        //set results to list view
        holder.item.setText(troubleshootItemList1.get(position).getItem());

        return view;
    }



}
