package com.mega.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shadow. on 2016/9/22 0022.
 */

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private LayoutInflater inflater;
    private List<String> mDatas;
    private Context mContext;

    public SimpleAdapter(List<String> mDatas, Context context) {
        this.mDatas = mDatas;
        mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.content_main, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.getTv().setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
class MyViewHolder extends RecyclerView.ViewHolder{
    private TextView tv;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.id_tv);
    }

    public TextView getTv() {
        return tv;
    }
}

