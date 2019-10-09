package com.example.loginonlyonce.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginonlyonce.Model.ModelClass;
import com.example.loginonlyonce.R;
import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {

    private ArrayList<ModelClass> dataList;

    public InboxAdapter(ArrayList<ModelClass> dataList) {
      this.dataList = dataList;
      this.dataList = dataList;
    }

    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.data_placeholder_layout, parent, false);
        return new InboxViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder holder, int position) {

        holder.txttitle.setText(dataList.get(position).getTxttitle());
        holder.txtisi.setText(dataList.get(position).getTxtisi());
        holder.txttype.setText(dataList.get(position).getTxttype());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class InboxViewHolder extends RecyclerView.ViewHolder{
        private TextView txttitle, txtisi, txttype;

        InboxViewHolder(View itemView) {
            super(itemView);

            txttitle = itemView.findViewById(R.id.txttitle);
            txtisi = itemView.findViewById(R.id.txtisi);
            txttype = itemView.findViewById(R.id.txttype);

        }
    }

}
