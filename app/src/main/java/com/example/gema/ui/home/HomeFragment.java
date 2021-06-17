package com.example.gema.ui.home;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.gema.R;
import com.example.gema.adapter.HomeAdapter;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.HomeModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class HomeFragment extends Fragment {

    ListView listView;
    MediaPlayer mediaPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.alarm);

        listView=(ListView)getActivity().findViewById(R.id.lv);

        AndroidNetworking.get("http://10.0.3.2:8083/fiktif.json")

                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("respon json", response.toString());
                        Gson gson = new Gson();
                        BaseRespon<List<HomeModel>> listBaseRespon= gson.fromJson(response.toString(), new TypeToken<BaseRespon<List<HomeModel>>>(){}.getType());


                        //cari yang nilai aksesnya =3
                        Stream<HomeModel> k = listBaseRespon.getPayload().stream().filter(x -> x.getAkses().equals("3"));


                        //jika jumlahnya lebih besar/sama dengan 1
                        //nyalakan alarm
                        if (k.count() >= 1){

                            //mainkan sound alarm
                            mediaPlayer.start();

                            //getarkan HP
                            Vibrator vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                vibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(2000);
                            }
                        }

                        //hilangkan jika aksesnya =1, karena hijau tidak ditempilkan
                        listBaseRespon.getPayload().removeIf(x -> x.getAkses().equals("1"));

                        //urutkan supaya yang aksesnya 3 berada di list paling atas
                        Collections.sort(listBaseRespon.getPayload(),HomeModel.modelComparator);


                        //tampilkan data di listview
                        HomeAdapter homeAdapter = new HomeAdapter(getActivity(), listBaseRespon.getPayload());
                        listView.setAdapter(homeAdapter);

                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    @Override
    public void onDetach() {
        super.onDetach();

        mediaPlayer.stop();
    }

    @Override
    public void onStop() {
        super.onStop();

        mediaPlayer.stop();
    }

    List<HomeModel> filterList(List<HomeModel> list){
        List<HomeModel> result = new ArrayList<HomeModel>() ;
        for(HomeModel h: result){
            if (h.getAkses().equals("1")){

            }else{
                result.add(h);
            }
        }
        return result;
    }

    List<HomeModel> getData(){
        List<HomeModel> hasil = new ArrayList<HomeModel>();
        HomeModel homeModel;

        homeModel=new HomeModel();
        homeModel.setAkses("2");
        homeModel.setDMAC("3987348989");
        homeModel.setNama("Ani");
        homeModel.setRegistrasiWBP("001");
        hasil.add(homeModel);

        homeModel=new HomeModel();
        homeModel.setAkses("3");
        homeModel.setDMAC("3984515687");
        homeModel.setNama("Budi");
        homeModel.setRegistrasiWBP("002");
        hasil.add(homeModel);

        homeModel=new HomeModel();
        homeModel.setAkses("1");
        homeModel.setDMAC("3694512354");
        homeModel.setNama("Candra");
        homeModel.setRegistrasiWBP("003");
        hasil.add(homeModel);

        homeModel=new HomeModel();
        homeModel.setAkses("1");
        homeModel.setDMAC("3754561587");
        homeModel.setNama("Dedi");
        homeModel.setRegistrasiWBP("004");
        hasil.add(homeModel);

        homeModel=new HomeModel();
        homeModel.setAkses("1");
        homeModel.setDMAC("3312549785");
        homeModel.setNama("Eni");
        homeModel.setRegistrasiWBP("005");
        hasil.add(homeModel);


        //cari yang nilai aksesnya =3
        Stream<HomeModel> k = hasil.stream().filter(x -> x.getAkses()=="3");

        //jika jumlahnya lebih besar/sama dengan 1
        //nyalakan alarm
        if (k.count() >= 1){

            //mainkan sound alarm
            mediaPlayer.start();

            //getarkan HP
            Vibrator vibrator = (Vibrator) getActivity().getSystemService(getActivity().VIBRATOR_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                vibrator.vibrate(2000);
            }
        }

        //hilangkan jika aksesnya =1, karena hijau tidak ditempilkan
        hasil.removeIf(x -> x.getAkses()=="1");

        //urutkan supaya yang aksesnya 3 berada di list paling atas
        Collections.sort(hasil,HomeModel.modelComparator);

        return hasil;
    }


}