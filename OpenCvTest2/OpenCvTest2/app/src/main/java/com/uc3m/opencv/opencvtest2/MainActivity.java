package com.uc3m.opencv.opencvtest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
            Log.i(TAG, "OpenCv Loaded Succesfully");

        }else{
            Log.i(TAG, "OpenCv not loaded");

        }
    }



    //*******************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Declaración y asignacion de botones-etiquetas
        Button Capturar = (Button)findViewById(R.id.tecCaptur);
        Button About = (Button)findViewById(R.id.tecAbout);


        /**
         * Metodo que es llamado al pulsar la tecla bacerca. Ejecuta la activity Acercade
         */
        Capturar.setOnClickListener(new View.OnClickListener(){


                                       @Override
                                       public void onClick(View v)
                                       {
                                           startActivity(new Intent(MainActivity.this, ActCapturar.class));



                                       }

                                   }


        );
        /**
         * Metodo que es llamado al pulsar la tecla bacerca. Ejecuta la activity Acercade
         */
        About.setOnClickListener(new View.OnClickListener(){


                                        @Override
                                        public void onClick(View v)
                                        {
                                            startActivity(new Intent(MainActivity.this, ActAbout.class));



                                        }

                                    }


        );













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
