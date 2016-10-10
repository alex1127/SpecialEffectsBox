package com.ly.specialeffectsbox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ly.specialeffectsbox.R;
import com.ly.specialeffectsbox.model.Item;

import java.util.List;


public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private List<Item> mList;

    public MyRecyclerAdapter(List<Item> mList) {
        this.mList = mList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Item item = mList.get(position); // Object Item
        holder.setName(item.getName()); // Name
        holder.setDescription(item.getDescription()); // Description
        holder.setImage(item.getIdImage()); // Image
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        } else {
            return mList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivUser;
        TextView tvName, tvDescription;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivUser = (ImageView) itemView.findViewById(R.id.ivUser);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            tvDescription = (TextView) itemView.findViewById(R.id.tvDescription);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(ivUser.getContext(), "Item click" + view.getTag(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setName(String name) {
            tvName.setText(name);
        }

        public void setDescription(String description) {
            tvDescription.setText(description);
        }

        public void setImage(int idImage) {

        }

    }

}
