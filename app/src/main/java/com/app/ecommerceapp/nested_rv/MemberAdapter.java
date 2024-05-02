package com.app.ecommerceapp.nested_rv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.ecommerceapp.R;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder>{

    List<Member> listOfMember;
    Context context;

    public MemberAdapter(List<Member> listOfMember, Context context) {
        this.listOfMember = listOfMember;
        this.context = context;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.member_item, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        holder.memberName.setText(listOfMember.get(position).getName());
        holder.slugName.setText(listOfMember.get(position).getSlug());
    }

    @Override
    public int getItemCount() {
        return listOfMember.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder {

        TextView memberName, slugName;
        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            memberName = itemView.findViewById(R.id.member_name);
            slugName = itemView.findViewById(R.id.member_slug);
        }
    }
}
