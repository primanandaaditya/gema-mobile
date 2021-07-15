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
import com.example.gema.model.GetLokasiModel;
import com.example.gema.model.HomeModel;

import java.util.List;

public class GetLokasiAdapter extends BaseAdapter {

    Context context;
    List<GetLokasiModel> getLokasiModels;
    LayoutInflater inflater;
    GetLokasiModel getLokasiModel;

    public GetLokasiAdapter(Context context, List<GetLokasiModel> getLokasiModels) {
        this.context = context;
        this.getLokasiModels = getLokasiModels;
    }

    @Override
    public int getCount() {
        if (getLokasiModels == null){
            return 0;
        }else{
            return getLokasiModels.size();
        }
    }

    @Override
    public GetLokasiModel getItem(int position) {
        return getLokasiModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_getlokasi, null);

        TextView tv_kodelokasi = (TextView) convertView.findViewById(R.id.tv_kodelokasi);
        TextView tv_namalokasi = (TextView) convertView.findViewById(R.id.tv_namalokasi);
        TextView tv_jumlah = (TextView) convertView.findViewById(R.id.tv_jumlah);
        LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.ll);

        getLokasiModel = getLokasiModels.get(position);

        tv_kodelokasi.setText( "Kode lokasi: " + getLokasiModel.getKode_Lokasi());
        tv_namalokasi.setText("Nama lokasi: " + getLokasiModel.getNama_Lokasi());
        tv_jumlah.setText(getLokasiModel.getJumlah());
        return convertView;
    }

    void fontHitam(TextView textView){
        textView.setTextColor(context.getResources().getColor(R.color.black));
    }

    public List<GetLokasiModel> getGetLokasiModels() {
        return getLokasiModels;
    }

    public void setGetLokasiModels(List<GetLokasiModel> getLokasiModels) {
        this.getLokasiModels = getLokasiModels;
    }

}
