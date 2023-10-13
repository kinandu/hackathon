package com.hack.easy2invest;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;  
import android.view.ViewGroup;  
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;  
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.hack.easy2invest.model.Items;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{
    private Items[] listdata;
  
   // RecyclerView recyclerView;  
    public MyListAdapter(Items[] listdata) {
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
        final Items myListData = listdata[position];
        holder.tv1.setText(listdata[position].getName());
        holder.tv2.setText(listdata[position].getCurrency());
        holder.tv3.setText(listdata[position].getStockExchange());
        holder.tv4.setText(listdata[position].getSymbol());

        String code = listdata[position].getSymbol().replace("-","") ;
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override  
            public void onClick(View view) {

                Intent myIntent = new Intent(view.getContext(), WebViewActivity.class);


                   myIntent.putExtra("code", code);

                view.getContext().startActivity(myIntent);

                // Toast.makeText(view.getContext(),"click on item: "+myListData.getExchangeShortName(),Toast.LENGTH_LONG).show();
            }  
        });  
    }  
  
  
    @Override  
    public int getItemCount() {  
        return 1;
    }  
  
    public static class ViewHolder extends RecyclerView.ViewHolder {  
        public TextView tv1;
        public TextView tv2;
        public TextView tv3;
        public TextView tv4;
        public LinearLayout relativeLayout;
        public ViewHolder(View itemView) {  
            super(itemView);  
            this.tv4 = (TextView) itemView.findViewById(R.id.tv_value4);
            this.tv1 = (TextView) itemView.findViewById(R.id.tv_value1);
            this.tv2 = (TextView) itemView.findViewById(R.id.tv_value2);
            this.tv3 = (TextView) itemView.findViewById(R.id.tv_value3);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.linerLayout);
        }  
    }  
}  
