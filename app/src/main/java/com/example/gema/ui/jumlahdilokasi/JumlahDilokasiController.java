package com.example.gema.ui.jumlahdilokasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.gema.helper.Endpoint;
import com.example.gema.helper.Konstanta;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.GetLokasiModel;
import com.example.gema.model.JumlahDilokasiModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class JumlahDilokasiController implements IJumlahDilokasiRequest{

    Context context;
    IJumlahDilokasiRespon respon;
    ProgressDialog progressDialog;

    public JumlahDilokasiController(Context context, IJumlahDilokasiRespon respon) {
        this.context = context;
        this.respon = respon;
    }

    @Override
    public void getJumlahDilokasi(String kode_lokasi) {

        progressDialog=new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(Konstanta.NOW_LOADING);
        progressDialog.show();

        AndroidNetworking.get(Endpoint.BASE_URL + Endpoint.JUMLAH_DILOKASI + "{kode_lokasi}")
                .addPathParameter("kode_lokasi", kode_lokasi)
                .setTag("jumlah dilokasi")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();
                        Log.d("jumlah lokasi", response.toString());
                        //tangkap respon JSON dalam class BaseRespon
                        BaseRespon<List<JumlahDilokasiModel>> listBaseRespon= gson.fromJson(response.toString(), new TypeToken<BaseRespon<List<JumlahDilokasiModel>>>(){}.getType());
                        respon.onJumlahSukses(listBaseRespon);

                        progressDialog.dismiss();
                    }
                    @Override
                    public void onError(ANError error) {

                        respon.onJumlahGagal(error);
                        progressDialog.dismiss();
                    }
                });
    }
}
