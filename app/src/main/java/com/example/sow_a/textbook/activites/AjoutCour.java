package com.example.sow_a.textbook.activites;

import android.content.ContentValues;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sow_a.textbook.R;
import com.example.sow_a.textbook.dao.DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AjoutCour extends AppCompatActivity {

    TimePicker deb,fin ;
    Spinner spinner ;
    EditText description ;
    Button button ;

    DAO dao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_cour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinner = (Spinner)findViewById(R.id.spinner) ;
        deb = (TimePicker)findViewById(R.id.timePicker4) ;
        fin = (TimePicker)findViewById(R.id.timePicker2) ;
        description = (EditText)findViewById(R.id.editText5) ;
        button = (Button)findViewById(R.id.buttonCour) ;




        deb.setIs24HourView(true);
        fin.setIs24HourView(true);

        dao = new DAO(getApplicationContext()) ;
        ArrayList<String> ms = new ArrayList<>();
        for (int i = 0;i<dao.allMatiere().size();i++){
            ms.add(dao.allMatiere().get(i).getNom_matiere());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ms) ;

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                String des = description.getText().toString() ;

                int  debut = deb.getHour() ;
                int  debut1 = deb.getMinute() ;
                String dT = debut+"h"+debut1 ;

                int f = fin.getHour() ;
                int f1 = fin.getMinute();
                String ft = f + "h" + f1 ;

                String sp = spinner.getSelectedItem().toString() ;
                String d = getDateTime().toString() ;

                ContentValues contentValues = new ContentValues() ;

                contentValues.put("matiere",sp);
                contentValues.put("date",d);
                contentValues.put("heuredeb",dT);
                contentValues.put("heurefin",ft);
                contentValues.put("description",des);

                dao.addCour(contentValues);

                Toast.makeText(getApplicationContext(),"succes",Toast.LENGTH_LONG).show();



            }
        });


    }


    public static Date getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        return date;
    }



}
