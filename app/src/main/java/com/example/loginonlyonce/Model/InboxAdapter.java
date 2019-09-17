package com.example.loginonlyonce.Model;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.loginonlyonce.MainActivity;
import com.example.loginonlyonce.R;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {


    private ArrayList<ModelClass> dataList;

    public InboxAdapter(MainActivity mainActivity, ArrayList<ModelClass> dataList) {
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public InboxViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.activity_adapter_inbox, viewGroup, false);
        return new InboxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InboxViewHolder inboxViewHolder, int i) {
        inboxViewHolder.txttitle.setText(dataList.get(i).getTxttitle());
        inboxViewHolder.txtisi.setText(dataList.get(i).getTxtisi());
        inboxViewHolder.txtwaktu.setText(dataList.get(i).getTxtwaktu());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class InboxViewHolder extends RecyclerView.ViewHolder{
        private TextView txttitle, txtisi,txtwaktu;

        public InboxViewHolder(View itemView) {
            super(itemView);
            txttitle = (TextView) itemView.findViewById(R.id.txttitle);
            txtisi = (TextView) itemView.findViewById(R.id.txtisi);
            txtwaktu = (TextView) itemView.findViewById(R.id.txtwaktu);
            //onclick
        }
    }

}
