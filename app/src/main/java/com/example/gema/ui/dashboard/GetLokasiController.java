package com.example.gema.ui.dashboard;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.gema.helper.Endpoint;
import com.example.gema.model.GetLokasiModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.gema.model.*;

import org.json.JSONObject;

import java.util.List;

public class GetLokasiController implements IGetLokasiRequest {

    Context context;
    IGetLokasiRespon iGetLokasiRespon;


    public GetLokasiController(Context context, IGetLokasiRespon iGetLokasiRespon) {
        this.context = context;
        this.iGetLokasiRespon = iGetLokasiRespon;
    }

    @Override
    public void getLokasi() {
        AndroidNetworking.get(Endpoint.BASE_URL + Endpoint.GET_LOKASI)

                .setTag("get lokasi")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Gson gson = new Gson();

                        Log.d("get lokasi", response.toString());

                        //tangkap respon JSON dalam class BaseRespon
                        BaseRespon<List<GetLokasiModel>> listBaseRespon= gson.fromJson(response.toString(), new TypeToken<BaseRespon<List<GetLokasiModel>>>(){}.getType());
                        iGetLokasiRespon.onGetLokasiSukses(listBaseRespon);
                    }
                    @Override
                    public void onError(ANError error) {
                        iGetLokasiRespon.onGetLokasiGagal(error);
                    }
                });

    }
}
