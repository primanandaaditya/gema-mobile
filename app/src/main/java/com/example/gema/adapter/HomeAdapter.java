package com.example.gema.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gema.R;
import com.example.gema.model.HomeModel;

import java.util.List;

public class HomeAdapter  extends BaseAdapter {

    Context context;
    List<HomeModel> homeModels;
    LayoutInflater inflater;
    HomeModel homeModel;

    public HomeAdapter(Context context, List<HomeModel> homeModels) {
        this.context = context;
        this.homeModels = homeModels;
    }

    @Override
    public int getCount() {
        return homeModels.size();
    }

    @Override
    public HomeModel getItem(int position) {
        return homeModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_home, null);

        TextView tv_dmac = (TextView) convertView.findViewById(R.id.tv_dmac);
        TextView tv_nama = (TextView) convertView.findViewById(R.id.tv_nama);
        TextView tv_registrasiwbp = (TextView) convertView.findViewById(R.id.tv_registrasiwbp);
        ImageView id = (ImageView)convertView.findViewById(R.id.iv);
        LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.ll);

        homeModel = homeModels.get(position);

        switch (homeModel.getAkses()) {
            case "1":
                ll.setBackgroundColor(context.getResources().getColor(R.color.hijau));
                break;
            case "2":
                ll.setBackgroundColor(context.getResources().getColor(R.color.kuning));
                fontHitam(tv_dmac);
                fontHitam(tv_registrasiwbp);
                fontHitam(tv_nama);
                break;
            case "3":
                ll.setBackgroundColor(context.getResources().getColor(R.color.merah));
                break;

        }


        tv_dmac.setText( "DMAC: " + homeModel.getDmac());
        tv_nama.setText("Nama: " + homeModel.getNama());
        tv_registrasiwbp.setText("Registrasi WBP: " + homeModel.getRegistrasi_WBP());


        return convertView;
    }

    void fontHitam(TextView textView){
        textView.setTextColor(context.getResources().getColor(R.color.black));
    }
}
