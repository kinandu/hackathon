package com.hack.easy2invest;

import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;  
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private MyListData[] listdata;  
  
   // RecyclerView recyclerView;  
    public MyListAdapter(MyListData[] listdata) {  
        this.listdata = listdata;  
    }  
    @Override  
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {  
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());  
        View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);  
        ViewHolder viewHolder = new ViewHolder(listItem);  
        return viewHolder;  
    }  
  
    @Override  
    public void onBindViewHolder(ViewHolder holder, int position) {  
        final MyListData myListData = listdata[position];  
        holder.tv1.setText(listdata[position].getValue1());
        holder.tv2.setText(listdata[position].getValue2());
        holder.tv3.setText(listdata[position].getValue3());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override  
            public void onClick(View view) {  
                Toast.makeText(view.getContext(),"click on item: "+myListData.getValue1(),Toast.LENGTH_LONG).show();
            }  
        });  
    }  
  
  
    @Override  
    public int getItemCount() {  
        return listdata.length;  
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {  
            super(itemView);  
            this.tv1 = (TextView) itemView.findViewById(R.id.tv_value1);
            this.tv2 = (TextView) itemView.findViewById(R.id.tv_value2);
            this.tv3 = (TextView) itemView.findViewById(R.id.tv_value3);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.linerLayout);
        }  
    }  
}  
