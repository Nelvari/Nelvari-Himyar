package com.example.loginonlyonce.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginonlyonce.Model.ModelClass;
import com.example.loginonlyonce.R;
import com.example.loginonlyonce.Ui.InboxActivity;
import com.example.loginonlyonce.Ui.MainActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class InboxAdapter extends RecyclerView.Adapter<InboxAdapter.InboxViewHolder> {


    private ArrayList<ModelClass> dataList;
    Boolean showShimmer = true;
    int SHIMMER_ITEM_NUMBER = 5;


    public InboxAdapter(InboxActivity mainActivity, ArrayList<ModelClass> dataList) {
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

        if(showShimmer){
            inboxViewHolder.shimmerFrameLayout.startShimmer();
        }else {
            inboxViewHolder.shimmerFrameLayout.stopShimmer();
            inboxViewHolder.shimmerFrameLayout.setShimmer(null);

            inboxViewHolder.txttitle.setBackground(null);
            inboxViewHolder.txttitle.setText(dataList.get(i).getTxttitle());

            inboxViewHolder.txtisi.setBackground(null);
            inboxViewHolder.txtisi.setText(dataList.get(i).getTxtisi());

            inboxViewHolder.txttype.setBackground(null);
            inboxViewHolder.txttype.setText(dataList.get(i).getTxttype());
        }

    }

    @Override
    public int getItemCount() {
        return showShimmer?SHIMMER_ITEM_NUMBER : dataList.size();
    }

    public static class InboxViewHolder extends RecyclerView.ViewHolder{
        private TextView txttitle, txtisi,txttype;
        private ShimmerFrameLayout shimmerFrameLayout;

        @SuppressLint("WrongViewCast")
        public InboxViewHolder(View itemView) {
            super(itemView);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer_view_container);
            txttitle = (TextView) itemView.findViewById(R.id.txttitle);
            txtisi = (TextView) itemView.findViewById(R.id.txtisi);
            txttype = (TextView) itemView.findViewById(R.id.txttype);
            //onclick
        }
    }


}
