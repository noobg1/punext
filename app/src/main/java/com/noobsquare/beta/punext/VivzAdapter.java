package com.noobsquare.beta.punext;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jeevan on 12/20/2015.
 */
public class VivzAdapter extends RecyclerView.Adapter<VivzAdapter.MyViewHolder> {

    private  LayoutInflater inflater;
   // private ClickListener clickListener; //method 2 var
    List<Information> data = Collections.emptyList();
    public VivzAdapter(Context context,List<Information> data){

        inflater=LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = inflater.inflate(R.layout.custom_row, parent,false);
        Log.d("VIVZ", "onCreateViewHolder called");
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
        Information current = data.get(position);
        Log.d("VIVZ","onBindHolder called"+position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
        /*holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("VIVZ","click"+position);
                Toast.makeText(v.getContext(),"Item Clickedat position "+position,Toast.LENGTH_SHORT).show();

            }
        });*/
    }
//method 2 util function
   /* public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;

    }*/

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           // Toast.makeText(v.getContext(),"Item Clicked at "+getAdapterPosition(),Toast.LENGTH_SHORT).show();
          //  delete(getAdapterPosition());
           // v.getContext().startActivity(new Intent(v.getContext(), Subactivity.class)); //not a good method to start activity i.e by adapter
            //method 2
           /* if(clickListener!=null){
                clickListener.itermClicked(v,getAdapterPosition());
            }*/
        }
    }
    //implementing activity launcher for navbar ietms method 2
   /* public interface ClickListener{
        public void itermClicked(View view,int position);

    }*/
}
