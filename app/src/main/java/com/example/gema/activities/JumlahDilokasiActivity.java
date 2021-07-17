package com.example.gema.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.error.ANError;
import com.example.gema.R;
import com.example.gema.helper.Konstanta;
import com.example.gema.model.BaseRespon;
import com.example.gema.model.JumlahDilokasiModel;
import com.example.gema.ui.jumlahdilokasi.IJumlahDilokasiRespon;
import com.example.gema.ui.jumlahdilokasi.JumlahDilokasiController;

import java.util.List;

public class JumlahDilokasiActivity extends AppCompatActivity implements IJumlahDilokasiRespon {

    TextView tv;
    String kode_lokasi, jumlah_orang;
    JumlahDilokasiController jumlahDilokasiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumlah_dilokasi);

        tv=(TextView)findViewById(R.id.tv);
        Intent intent = getIntent();
        kode_lokasi = intent.getStringExtra(Konstanta.KODE_LOKASI);
        jumlah_orang = intent.getStringExtra(Konstanta.JUMLAH_ORANG);

        tv.setText("Jumlah orang di lokasi : " + kode_lokasi + " adalah " + jumlah_orang + " orang");
        jumlahDilokasiController = new JumlahDilokasiController(JumlahDilokasiActivity.this, this);
        jumlahDilokasiController.getJumlahDilokasi(kode_lokasi);
    }

    @Override
    public void onJumlahSukses(BaseRespon<List<JumlahDilokasiModel>> respon) {
        Toast.makeText(JumlahDilokasiActivity.this, String.valueOf(respon.getPayload().size()), Toast.LENGTH_SHORT).show();
        tv.setText(tv.getText() + respon.toString());
    }

    @Override
    public void onJumlahGagal(ANError anError) {
        Log.d("error jumlah", anError.getMessage());
    }
}