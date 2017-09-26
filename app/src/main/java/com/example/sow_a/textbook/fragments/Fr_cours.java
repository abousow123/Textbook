package com.example.sow_a.textbook.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;


import com.example.sow_a.textbook.activites.AjoutCour;
import com.example.sow_a.textbook.R;
import com.example.sow_a.textbook.dao.DAO;
import com.example.sow_a.textbook.metier.Cour;
import com.example.sow_a.textbook.metier.ExpandableListAdapte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fr_cours extends Fragment {

    TabHost tabHost;

    Button butAjoutMatiere,butAjoutUE, butAjoutCour  ;


    ArrayList<Cour> cours ;


    ArrayList<String> ab;
    ArrayList<String> ab1;

    DAO dao ;
    ListView listViewtab1,listViewtab3 ;

    ExpandableListAdapte listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    FragmentTransaction fragmentTransaction ;
    private FragmentManager fragmentManager;


    public Fr_cours() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dao = new DAO(getContext()) ;

        cours = dao.allCour() ;

        

        fragmentManager = getFragmentManager() ;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.cours, container, false);

       // rootView.setOnKeyListener(pressed);





        expListView = (ExpandableListView)rootView.findViewById(R.id.listExpandable);


        butAjoutCour = (Button)rootView.findViewById(R.id.butAjoutCour) ;












        butAjoutCour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



              startActivity(new Intent(Fr_cours.super.getActivity(),AjoutCour.class));

                Toast.makeText(getContext(),"cxcsefd",Toast.LENGTH_LONG).show();
            }
        });

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapte(Fr_cours.super.getContext(),listDataHeader,listDataChild) ;

        // setting list adapter
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){

            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                final Cour cour = cours.get(i) ;

                AlertDialog.Builder buider = new AlertDialog.Builder(getContext()) ;

                buider.setMessage(cour.toString());

                AlertDialog dialog = buider.create();
                dialog.show();

                return false;
            }
        });








         String a= "no" ;







        Toast.makeText(getContext(),a,Toast.LENGTH_LONG).show();

        return rootView;
    }









   /* class MyListAdapter2 extends ArrayAdapter<String> {



        public   MyListAdapter2() {

            super(Accueil_Fragment.super.getContext(),R.layout.listview_cour_suite,ab1);

        }

        public void onClick(View view) {

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater  lis = getActivity().getLayoutInflater() ;
            View row = lis.inflate(R.layout.listview_cour_suite, parent, false);
            TextView label = (TextView) row.findViewById(R.id.te1);
            //triTabChaine(sg);
            label.setText(ab1.get(position) );

           ImageView icon = (ImageView) row.findViewById(R.id.icon);

            if (etudiants.get(position).getSexe()
                    .equalsIgnoreCase("Femme"))
                icon.setImageResource(R.drawable.f1);
            if (etudiants.get(position).getSexe()
                    .equalsIgnoreCase("Homme"))
                icon.setImageResource(R.drawable.h1);

            return (row);
        }
    }*/


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
      /*  listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");*/


      //  dao = new DAO() ;

        List<String> li = new ArrayList<String>();

        for (int i = 0;i<dao.allCour().size();i++){
            listDataHeader.add(dao.allCour().get(i).getDate());


        }
        int k = 0 ;
        while (k<=dao.allCour().size()-1){
            List<String> l = new ArrayList<String>();



            for (int i = 0;i<dao.allCour().size();i++){
                l.add(dao.allCour().get(i).getMatiere()) ;

            }



            listDataChild.put(listDataHeader.get(k), l);

            k++ ;

        }






        // Adding child data
    /*    List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");*/

      /*  listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
        listDataChild.put(listDataHeader.get(3),li) ;*/
    }








}
