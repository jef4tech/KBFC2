package com.jeftech.kbfc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<DataModel> dataModelArrayList;
    Context c;
//    ClickListener clickListener;

    public RvAdapter(Context ctx, ArrayList<DataModel> dataModelArrayList){
//        clickListener = (ClickListener) ctx;
        c = ctx;
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public RvAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rv_one, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RvAdapter.MyViewHolder holder, final int position) {

        holder.name.setText(dataModelArrayList.get(position).getName());
//        holder.rooms.setText(dataModelArrayList.get(position).getRooms());
//        holder.phone.setText(dataModelArrayList.get(position).getPhone());
//        holder.place.setText(dataModelArrayList.get(position).getPlace());
        Picasso.get().load(dataModelArrayList.get(position).getImgURL()).into(holder.iv);

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel p = dataModelArrayList.get(position);
                String name = p.getName();
                String rooms = p.getRooms();
                String phone = p.getPhone();
                String place = p.getPlace();
                String imgURL = p.getImgURL();

                Intent i=new Intent(c, HostelViewActivity.class);
                i.putExtra("name", name);
                i.putExtra("rooms", rooms);
                i.putExtra("phone", phone);
                i.putExtra("place", place);
                i.putExtra("url", imgURL);
                c.startActivity(i);
            }
        });

//        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickListener.onClick(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name
//                rooms, phone, place
                ;
        ImageView iv;
        LinearLayout rootLayout;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
//            rooms = itemView.findViewById(R.id.rooms);
//            phone = itemView.findViewById(R.id.phone);
//            place = itemView.findViewById(R.id.place);
            iv = itemView.findViewById(R.id.iv);
            rootLayout = itemView.findViewById(R.id.rootLayout);
        }

    }

//    public interface ClickListener
//    {
//        void onClick(int position);
//    }

}
