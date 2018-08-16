package com.company.xpertech.xpertech;

import android.content.Context;
import android.content.Intent;
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

public class ListViewAdapter extends BaseAdapter{
    Context ctx;
    LayoutInflater inflater;
    private List<Troubleshoot> troubleshootList = null;
    private ArrayList<Troubleshoot> troubleshootList1;

    public ListViewAdapter(Context ctx, List<Troubleshoot> troubleshootList){
        this.ctx = ctx;
        inflater =  (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.troubleshootList1 = new ArrayList<Troubleshoot>();
        this.troubleshootList1.addAll(troubleshootList);
    }

    public class ViewHolder{
        TextView title;
    }

    @Override
    public int getCount() {
        return troubleshootList1.size();
    }

    @Override
    public Object getItem(int position) {
        return troubleshootList1.get(position);
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
            view = inflater.inflate(R.layout.activity_troubleshoot_title, parent, false);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        // locate the title in troubleshoot list
        holder.title  =  (TextView) view.findViewById(R.id.troubleshoot_title);
        view.setTag(holder);

        //set results to list view
        Troubleshoot tb  =  new Troubleshoot();
        holder.title.setText(troubleshootList1.get(position).getTitle());
        tb.setPosition(troubleshootList1.indexOf(troubleshootList1.get(position).getTitle()));

        //listen for listview item click
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,  TroubleshootItemActivity.class);
                intent.putExtra("title", (troubleshootList1.get(position).getTitle()));
                intent.putExtra("index", (troubleshootList1.indexOf(position)));
                ctx.startActivity(intent);
            }
        });

        return view;
    }



}
