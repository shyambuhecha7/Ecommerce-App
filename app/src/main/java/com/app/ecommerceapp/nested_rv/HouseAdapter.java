package com.app.ecommerceapp.nested_rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ecommerceapp.R;

import java.util.List;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.HouseViewHolder> {

    boolean isVisible = false;
    List<House> listOfHouse;
    Context context;

    public HouseAdapter(List<House> listOfHouse, Context context) {
        this.listOfHouse = listOfHouse;
        this.context = context;
    }

    @NonNull
    @Override
    public HouseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.house_item, parent, false);
        return new HouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseViewHolder holder, int position) {
        holder.houseSlug.setText(listOfHouse.get(position).getSlug());
        holder.houseName.setText(listOfHouse.get(position).getName());

        MemberAdapter memberAdapter = new MemberAdapter(listOfHouse.get(position).getMembers(), context);
        holder.memberRecyclerview.setAdapter(memberAdapter);
        holder.memberRecyclerview.setLayoutManager(new LinearLayoutManager(context));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isVisible) {
                    holder.memberRecyclerview.setVisibility(View.VISIBLE);
                    isVisible = false;
                }else {
                    holder.memberRecyclerview.setVisibility(View.GONE);
                    isVisible = true;

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfHouse.size();
    }

    class HouseViewHolder extends RecyclerView.ViewHolder {
        TextView houseSlug, houseName;
        RecyclerView memberRecyclerview;
        public HouseViewHolder(@NonNull View itemView) {
            super(itemView);
            houseSlug = itemView.findViewById(R.id.house_slug);
            houseName = itemView.findViewById(R.id.house_name);
            memberRecyclerview = itemView.findViewById(R.id.members_recycler_view);

        }
    }
}
