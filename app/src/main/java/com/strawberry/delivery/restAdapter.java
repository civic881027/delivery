package com.strawberry.delivery;

import android.content.Context;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class restAdapter extends RecyclerView.Adapter<restAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Poster> mData;
    private OnItemClickListener mOnItemClickListener;
    public restAdapter(Context context,ArrayList<Poster> data){
        this.mContext=context;
        this.mData=data;
    }
    //初始化Holder
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
        ViewHolder holder=new ViewHolder(view);

        holder.ivPosterThumbnail=(ImageView)view.findViewById(R.id.ivResraurant);
        holder.tvPosterName=(TextView)view.findViewById(R.id.tvItemName);
        holder.tvTime=(TextView)view.findViewById(R.id.tvTime);
        return holder;
    }
    public String getRestName(int position){
        Poster poster=mData.get(position);
        return poster.posterName;
    }
    //將資料綁入View中的元件
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Poster poster=mData.get(position);
        holder.tvPosterName.setText(poster.posterName);
        holder.tvTime.setText(poster.time);
        //使用第三方套件Glide直接抓取圖片至ImageView
        Glide.with(mContext).load(poster.posterThumbnailUrl).into(holder.ivPosterThumbnail);

        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivPosterThumbnail;
        public TextView tvPosterName;
        public TextView tvTime;


        public ViewHolder(View itemView){
            super(itemView);
        }

    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener=listener;
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
