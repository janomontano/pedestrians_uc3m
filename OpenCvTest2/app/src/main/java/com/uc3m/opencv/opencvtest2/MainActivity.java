package com.uc3m.opencv.opencvtest2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.opencv.android.OpenCVLoader;



/**
 * La clase MainActivity alberga los metodos necesarios para la ejecucion
 * de operaciones matematicas basicas de la calculadora.
 * Esta clase será una Activity de Android
 * @author Félix Sánhcez Sánchez.
 * @version 1.0-19/12/2015
 */



public class MainActivity extends AppCompatActivity {

    //*******************************************
    private static String TAG = "MainActivity";

    static
    {
        if(OpenCVLoader.initDebug()){
            Log.i(TAG, "OpenCv Loades Succesfully");

        }else{
            Log.i(TAG, "OpenCv not loades");

        }
    }



    //*******************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
/*
        //Declaración y asignacion de botones-etiquetas
        Button blimp = (Button)findViewById(R.id.teclimpia);
        Button bsigno = (Button)findViewById(R.id.tecsigno);
        Button bigual = (Button)findViewById(R.id.tecigual);
        Button bporcen = (Button)findViewById(R.id.tecporcen);
        Button bacerca = (Button)findViewById(R.id.tecAcerca);
        Button bacientifica = (Button)findViewById(R.id.aCientifica);


        /**
         * Metodo que es llamado al pulsar la tecla bacerca. Ejecuta la activity Acercade
         */

        /*
        bacerca.setOnClickListener(new View.OnClickListener(){


                                       @Override
                                       public void onClick(View v)
                                       {
                                           startActivity(new Intent(MainActivity.this, Acercade.class));



                                       }

                                   }


        );

        */







    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
