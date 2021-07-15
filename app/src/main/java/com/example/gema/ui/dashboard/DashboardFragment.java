package com.example.gema.ui.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
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
import com.example.gema.adapter.GetLokasiAdapter;
import com.example.gema.helper.Endpoint;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.GetLokasiModel;

import org.json.JSONObject;

import java.util.List;

public class DashboardFragment extends Fragment implements IGetLokasiRespon {

    ListView lv;
    GetLokasiController getLokasiController;
    GetLokasiAdapter getLokasiAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv=(ListView)getActivity().findViewById(R.id.lv_getlokasi);
        getLokasiAdapter=new GetLokasiAdapter(getActivity(),null);
        getLokasiController=new GetLokasiController(getActivity(), this);
        getLokasiController.getLokasi();
    }

    @Override
    public void onGetLokasiSukses(BaseRespon<List<GetLokasiModel>> models) {
        getLokasiAdapter.setGetLokasiModels(models.getPayload());
        lv.setAdapter(getLokasiAdapter);
        getLokasiAdapter.notifyDataSetChanged();

        //jika sukses, hit endpoint lagi sesudah 2,5 detik
        //jadi proses ini dilakukan berulang-ulang hanya dari kode dibawah
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                //tunggu 2,5 detik
                //baru hit endpoint lagi
                getLokasiController.getLokasi();

            }
        }, 2500);
    }

    @Override
    public void onGetLokasiGagal(ANError anError) {
//        Toast.makeText(getActivity(), anError.getMessage(), Toast.LENGTH_SHORT).show();
        Log.d("get lokasi error", anError.getMessage());

        //jika gagal, hit endpoint lagi sesudah 5 detik
        //jadi proses ini dilakukan berulang-ulang hanya dari kode dibawah
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                //tunggu 2,5 detik
                //baru hit endpoint lagi
                getLokasiController.getLokasi();

            }
        }, 5000);
    }
}