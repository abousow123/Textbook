package com.example.sow_a.textbook.activites;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sow_a.textbook.R;
import com.example.sow_a.textbook.dao.DAO;
import com.example.sow_a.textbook.metier.Matiere;

import java.util.ArrayList;

public class AjoutMatiere extends AppCompatActivity {


    ArrayList<Matiere> matiereArrayList ;



    Spinner spinnerProf,spinnerUE ;
    Button add ;
    EditText editText1,editText3 ;
    DAO dao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_matiere);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        editText1 = (EditText)findViewById(R.id.editText) ;


        editText3 = (EditText)findViewById(R.id.editText3) ;
        add = (Button)findViewById(R.id.butAddMatiere) ;
        spinnerProf = (Spinner)findViewById(R.id.spinner2) ;
        spinnerUE = (Spinner)findViewById(R.id.spinner3) ;

        dao = new DAO(getApplicationContext()) ;

        ArrayList<String> ms = new ArrayList<>();
        ArrayList<String> ueName = new ArrayList<>();
        for (int i = 0;i<dao.allProf().size();i++){
            ms.add(dao.allProf().get(i).getPrenom() +" "+dao.allProf().get(i).getNom());
        }

        for (int i = 0;i<dao.allUE().size();i++){
            ueName.add(dao.allUE().get(i).getNomUE());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ms) ;
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ueName) ;

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerProf.setAdapter(dataAdapter);
        spinnerUE.setAdapter(dataAdapter1);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues contentValues = new ContentValues() ;

                String m = editText1.getText().toString() ;
                String p = spinnerProf.getSelectedItem().toString();
                String u = spinnerUE.getSelectedItem().toString();
                String b = editText3.getText().toString() ;
                int v = Integer.parseInt(b) ;

                if (m.equalsIgnoreCase("") || p.equalsIgnoreCase("")
                        || editText3.getText().toString().equalsIgnoreCase("") ) {

                    Toast.makeText(
                            getApplicationContext(),
                            "Veuillez entrer tout les informations s'il vous plait !!!",
                            Toast.LENGTH_LONG).show();

                }
                else {

                    contentValues.put("nomMatiere",m);
                    contentValues.put("professeur",p);
                    contentValues.put("volumeHoraire",v);
                    contentValues.put("Ue",u);

                    dao.addMatiere(contentValues);

                    Toast.makeText(getApplicationContext(),
                            "succés",
                            Toast.LENGTH_LONG).show();



                }
            }
        });






    }

}
