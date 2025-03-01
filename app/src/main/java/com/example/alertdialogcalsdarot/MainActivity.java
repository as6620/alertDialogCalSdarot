package com.example.alertdialogcalsdarot;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] strLV = new String[20];
    double[] fltLV= new double[20];
    TextView tVX1, tVd, tVn, tVSn;
    Button getData, btnHeshbonit, btnHandasit;
    EditText eTFirst, eTHefreshOrMahpil;
    AlertDialog.Builder abd;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        
    }

    private void initView() {
        tVX1 = (TextView) findViewById(R.id.tVX1);
        tVd = (TextView) findViewById(R.id.tVd);
        tVn = (TextView) findViewById(R.id.tVn);
        tVSn = (TextView) findViewById(R.id.tVSn);
        listView = (ListView) findViewById(R.id.listView);
        getData = (Button) findViewById(R.id.getData);

        btnHeshbonit = (Button) findViewById(R.id.btnHeshbonit);
        btnHandasit = (Button) findViewById(R.id.btnHandasit);
        eTFirst = (EditText) findViewById(R.id.eTFirst);
        eTHefreshOrMahpil = (EditText) findViewById(R.id.eTHefreshOrMahpil);
    }
}