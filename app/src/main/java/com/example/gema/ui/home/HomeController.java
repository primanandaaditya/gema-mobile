package com.example.gema.ui.home;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.gema.R;
import com.example.gema.adapter.HomeAdapter;
import com.example.gema.helper.Endpoint;
import com.example.gema.helper.InternetChecker;
import com.example.gema.helper.Konstanta;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.HomeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class HomeController implements IHomeRequest {

    Context context;
    IHomeRespon iHomeRespon;
    MediaPlayer mediaPlayer;
    Vibrator vibrator;


    public HomeController(Context context, IHomeRespon iHomeRespon) {
        this.context = context;
        this.iHomeRespon = iHomeRespon;

        //init media player untuk membunyikan alarm
        mediaPlayer = MediaPlayer.create(context, R.raw.alarm);

        //init vibrator
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
    }

    @Override
    public void get() {


        AndroidNetworking.get(Endpoint.BASE_URL + Endpoint.DATA_WBP)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("respon json", response.toString());


                        Gson gson = new Gson();

                        //tangkap respon JSON dalam class BaseRespon
                        BaseRespon<List<HomeModel>> listBaseRespon= gson.fromJson(response.toString(), new TypeToken<BaseRespon<List<HomeModel>>>(){}.getType());


                        //cari yang nilai aksesnya =3
                        Stream<HomeModel> k = listBaseRespon.getPayload().stream().filter(x -> x.getAkses().equals(Konstanta.AKSES_MERAH));


                        //jika jumlahnya lebih besar/sama dengan 1
                        //nyalakan alarm
                        if (k.count() >= 1){

                            //mainkan sound alarm
                            mediaPlayer.start();

                            //getarkan HP
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(Konstanta.LAMA_GETAR, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(Konstanta.LAMA_GETAR);
                            }
                        }

                        //hilangkan jika aksesnya =1, karena hijau tidak ditempilkan
                        listBaseRespon.getPayload().removeIf(x -> x.getAkses().equals(Konstanta.AKSES_HIJAU));

                        //urutkan supaya yang aksesnya 3 berada di list paling atas
                        Collections.sort(listBaseRespon.getPayload(),HomeModel.modelComparator);

                        iHomeRespon.onSukses(listBaseRespon);

                    }
                    @Override
                    public void onError(ANError error) {

                        iHomeRespon.onGagal(error);
                    }
                });

    }

    @Override
    public void stopAlarm() {

        if (mediaPlayer != null){
            mediaPlayer.stop();
        }

        if (vibrator != null){
            vibrator.cancel();
        }

    }
}
