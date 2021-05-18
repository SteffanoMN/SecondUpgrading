package com.example.secondupgrading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ListViewHolder> {

    private ArrayList<TeamModel> dataList;
    private OnItemClickListener mListener;
    private Context mContext;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public TeamAdapter(Context mContext, ArrayList<TeamModel> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rv_list, parent, false);
        return new ListViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.tv_title.setText(dataList.get(position).getTeamName());
        holder.tv_desc.setText(dataList.get(position).getTeamDesc());
        Picasso.get()
                .load(dataList.get(position).getTeamImage())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher_round)
                .into(holder.img_list);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title, tv_desc;
        private ImageView img_list;
        private RelativeLayout relativeLayout;

        public ListViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title_list);
            tv_desc = itemView.findViewById(R.id.tv_desc_list);
            img_list = itemView.findViewById(R.id.img_list);
            relativeLayout = itemView.findViewById(R.id.rv_layout_list);

            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}