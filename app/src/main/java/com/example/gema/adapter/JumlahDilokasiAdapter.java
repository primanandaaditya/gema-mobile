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
import com.example.gema.model.JumlahDilokasiModel;

import java.util.List;

public class JumlahDilokasiAdapter extends BaseAdapter {

    Context context;
    List<JumlahDilokasiModel> jumlahDilokasiModels;
    LayoutInflater inflater;
    JumlahDilokasiModel jumlahDilokasiModel;

    public JumlahDilokasiAdapter(Context context, List<JumlahDilokasiModel> jumlahDilokasiModels) {
        this.context = context;
        this.jumlahDilokasiModels = jumlahDilokasiModels;
    }

    @Override
    public int getCount() {
        if (jumlahDilokasiModels == null){
            return 0;
        }else{
            return jumlahDilokasiModels.size();
        }
    }

    @Override
    public JumlahDilokasiModel getItem(int position) {
        return jumlahDilokasiModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_jumlah, null);

        TextView tv_dmac = (TextView) convertView.findViewById(R.id.tv_dmac);
        TextView tv_nama = (TextView) convertView.findViewById(R.id.tv_nama);
        TextView tv_registrasiwbp = (TextView) convertView.findViewById(R.id.tv_registrasiwbp);
        ImageView id = (ImageView)convertView.findViewById(R.id.iv);
        LinearLayout ll = (LinearLayout)convertView.findViewById(R.id.ll);

        jumlahDilokasiModel = jumlahDilokasiModels.get(position);

        tv_dmac.setText( "DMAC: " + jumlahDilokasiModel.getDmac());
        tv_nama.setText("Nama: " + jumlahDilokasiModel.getNama());
        tv_registrasiwbp.setText("Registrasi WBP: " + jumlahDilokasiModel.getRegistrasi_WBP());


        return convertView;
    }

    void fontHitam(TextView textView){
        textView.setTextColor(context.getResources().getColor(R.color.black));
    }

    public List<JumlahDilokasiModel> getJumlahDilokasiModels() {
        return jumlahDilokasiModels;
    }

    public void setJumlahDilokasiModels(List<JumlahDilokasiModel> jumlahDilokasiModels) {
        this.jumlahDilokasiModels = jumlahDilokasiModels;
    }
}
