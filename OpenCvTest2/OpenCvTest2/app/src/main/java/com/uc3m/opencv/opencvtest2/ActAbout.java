package com.uc3m.opencv.opencvtest2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * La clase Acercade muestra los datos del
 * autor del programa
 * @author Félix Sánhcez Sánchez.
 * @version 1.0-19/12/2015
 */
public class ActAbout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_about);

        Button bvolver = (Button)findViewById(R.id.tecAtras);
        bvolver.setOnClickListener(new View.OnClickListener(){

                                       @Override
                                       public void onClick(View v)
                                       {
                                           //Al pulsar el boton volver, finaliza la Activity
                                           finish();



                                       }

                                   }


        );

    }



}
