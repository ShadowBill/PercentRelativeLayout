package com.mega.recyclerviewdemo.com.mega.recyclerviewdemo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mega.recyclerviewdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shadow. on 2016/9/22 0022.
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private List<String> mDatas;
    private Context mContext;
    private List<Integer> mHeight;

    public interface OnItemClickListener{
        void onItemClick(View view, int pos);
        void onItemLongClick(View view, int pos);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public StaggeredAdapter(List<String> mDatas, Context context) {
        this.mDatas = mDatas;
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        mHeight = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.content_main, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final StaggeredAdapter.MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mHeight.get(position);
        holder.itemView.setLayoutParams(lp);
        holder.getTv().setText(mDatas.get(position));
        onclick(holder);
    }

    private void onclick(final MyViewHolder holder) {
        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,layoutPosition);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int layoutPosition = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView,layoutPosition);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
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

    public void addData(int position) {
        mDatas.add(position, "inSert One");

        notifyItemInserted(position);
    }



    public void deleteData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);

    }

}


