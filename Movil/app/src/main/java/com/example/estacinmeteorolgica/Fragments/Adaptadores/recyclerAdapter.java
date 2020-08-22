package com.example.estacinmeteorolgica.Fragments.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estacinmeteorolgica.R;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolderData> {

    private ArrayList<String> permissionslist;

    public recyclerAdapter(ArrayList<String> permissionslist){
        this.permissionslist = permissionslist;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclercomponent, null, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {

        holder.PERMISSION.setText(permissionslist.get(position));
        holder.STATUS.setText("No Activo");
    }

    @Override
    public int getItemCount() {
        return permissionslist.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView PERMISSION, STATUS;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);

            PERMISSION = (TextView) itemView.findViewById(R.id.permission);
            STATUS = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
