package com.example.alertdialogcalsdarot;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    boolean handasitOrHeshbonit;
    ListView listView;
    String[] strLV = new String[20];
    float[] fltLV= new float[20];
    TextView tVX1, tVd, tVn, tVSn;
    Button getData, btnHeshbonit, btnHandasit;
    EditText eTFirst, eTHefreshOrMahpil;
    LinearLayout dialog_layout;
    AlertDialog.Builder adb;
    ArrayAdapter<String> adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        adp = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, strLV);

        tVX1 = (TextView) findViewById(R.id.tVX1);
        tVd = (TextView) findViewById(R.id.tVd);
        tVn = (TextView) findViewById(R.id.tVn);
        tVSn = (TextView) findViewById(R.id.tVSn);
        listView = (ListView) findViewById(R.id.listView);
        getData = (Button) findViewById(R.id.getData);
    }

    public void goData(View view) {
        dialog_layout = (LinearLayout) getLayoutInflater().inflate(R.layout.my_dialog, null);
        btnHeshbonit = (Button) dialog_layout.findViewById(R.id.btnHeshbonit);
        btnHandasit = (Button) dialog_layout.findViewById(R.id.btnHandasit);
        eTFirst = (EditText) dialog_layout.findViewById(R.id.eTFirst);
        eTHefreshOrMahpil = (EditText) dialog_layout.findViewById(R.id.eTHefreshOrMahpil);

        adb = new AlertDialog.Builder(this);
        adb.setView(dialog_layout);
        adb.setTitle("Data ALERT!!");
        adb.setMessage("enter data pls:");
        adb.setNeutralButton("Reset", clicked);
        adb.setPositiveButton("Finish", clicked);
        adb.setNegativeButton("Cancel", clicked);
        adb.setCancelable(false);
        AlertDialog ad = adb.create();
        ad.show();
    }

    public void goHeshbonit(View view) {
        eTFirst.setHint("רשום את המספר הראשון בסדרה החשבונית");
        eTHefreshOrMahpil.setHint("רשום את ההפרש בסדרה החשבונית");
        eTFirst.setVisibility(View.VISIBLE);
        eTHefreshOrMahpil.setVisibility(View.VISIBLE);
        handasitOrHeshbonit = true;
    }

    public void goHandasit(View view) {
        eTFirst.setHint("רשום את המספר הראשון בסדרה ההנדסית");
        eTHefreshOrMahpil.setHint("רשום את המכפיל בסדרה ההנדסית");
        eTFirst.setVisibility(View.VISIBLE);
        eTHefreshOrMahpil.setVisibility(View.VISIBLE);
        handasitOrHeshbonit = false;
    }

    DialogInterface.OnClickListener clicked = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                String firstStr = eTFirst.getText().toString();
                String hefreshOrMahpilStr = eTHefreshOrMahpil.getText().toString();
                if (firstStr.isEmpty() | firstStr.equals("-") | firstStr.equals("-.") | firstStr.equals("+") | firstStr.equals("+.") | hefreshOrMahpilStr.isEmpty() | hefreshOrMahpilStr.equals("-") | hefreshOrMahpilStr.equals("-.") | hefreshOrMahpilStr.equals("+") | hefreshOrMahpilStr.equals("+."))
                    Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show();
                else {
                    float x1 = Float.parseFloat(firstStr);
                    float d = Float.parseFloat(hefreshOrMahpilStr);

                    DecimalFormat df = new DecimalFormat("#.###"); // פורמט לשלוש ספרות אחרי הנקודה

                    for (int i = 0; i < 20; i++) {
                        if (handasitOrHeshbonit) {
                            fltLV[i] = x1 + i * d; // חשבונית
                        } else {
                            fltLV[i] = (float) (x1 * Math.pow(d, i)); // הנדסית
                        }
                        strLV[i] = df.format(fltLV[i]);
                    }


                    listView.setOnItemClickListener(MainActivity.this);
                    listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                    listView.setAdapter(adp);

                    //נתונים קבועים
                    tVX1.setText("X1 = " + df.format(x1));
                    tVd.setText("d = " + df.format(d));

                    listView.setVisibility(View.VISIBLE);
                    tVX1.setVisibility(View.VISIBLE);
                    tVd.setVisibility(View.VISIBLE);
                }
            } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                dialog.cancel();
            } else if (which == DialogInterface.BUTTON_NEUTRAL) {
                listView.setVisibility(View.INVISIBLE);
                tVX1.setVisibility(View.INVISIBLE);
                tVd.setVisibility(View.INVISIBLE);
                tVn.setVisibility(View.INVISIBLE);
                tVSn.setVisibility(View.INVISIBLE);
            }
        }
    };



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int n = position + 1;
        float Sn = 0;

        for (int i = 0; i < n; i++) {
            Sn += fltLV[i];
        }

        DecimalFormat df = new DecimalFormat("#.###");
        tVn.setText("n = " + n);
        tVSn.setText("Sn = " + df.format(Sn));

        tVn.setVisibility(View.VISIBLE);
        tVSn.setVisibility(View.VISIBLE);
    }
}