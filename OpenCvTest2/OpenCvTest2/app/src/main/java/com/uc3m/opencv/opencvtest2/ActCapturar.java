package com.uc3m.opencv.opencvtest2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;

import java.util.List;
import java.util.Vector;


//librerias opencv


public class ActCapturar extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    //Declaracion de VAriables


    private static String TAG = "MainActivity";
    private HOGDescriptor hog;
    private BaseLoaderCallback mLoaderCallBack = new  BaseLoaderCallback(this){
        @Override
        public void onManagerConnected(int status){
            switch(status)
            {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "=====================>OpenCV OK");
                  mOpenCvCameraView.enableView();
                    break;

                }
                default:
                {
                    super.onManagerConnected(status);
                }
            }
        }

    };
    private JavaCameraView mOpenCvCameraView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_capturar);
        mOpenCvCameraView = (JavaCameraView) findViewById(R.id.ActCapturarCameraView);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);


    }

    public void onResume(){
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, mLoaderCallBack);
    }

    public void onDestroy()
    {
        super.onDestroy();
        if(mOpenCvCameraView != null){
            mOpenCvCameraView.disableView();
        }
    }



    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        Mat frame = inputFrame.rgba();

        //initialize the HOG descriptor/person detector
        HOGDescriptor hog = new HOGDescriptor();
        hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
        int fdc=2;
        double fdci=0.5;


        MatOfRect foundLocations = new MatOfRect();
        MatOfDouble foundWeights = new MatOfDouble();

        Mat tempMat = new Mat(frame.rows(), frame.cols(), CvType.CV_8UC1); //CvType.CV_8UC3
        Mat dst = new Mat(frame.rows(), frame.cols(), CvType.CV_8UC1);


        /*Imgproc.cvtColor(source mat, destination mat1, Imgproc.COLOR_RGB2GRAY);
        El método cvtColor () toma tres parámetros que son la matriz de imagen de origen, la matriz de imagen de destino, y el tipo de conversión de color.
         */
        Imgproc.cvtColor(frame, tempMat, Imgproc.COLOR_RGBA2GRAY); //Imgproc.COLOR_RGBA2RGB

        Imgproc.resize(tempMat, tempMat, new Size(),fdci, fdci, Imgproc.INTER_AREA);



        /*HOGDescriptor :: detectMultiScale
        • testImage: La imagen en la que queremos que detecte.
        • found: Límites de lo objetos detectados.
        • hitThreshold: Umbral para la distancia entre las características y el plano de clasi-
            ficación de la SVM.
        • winStride: El paso de la ventana.Debe ser un múltiplo de blockStride.
        • padding: Parámetro Mock para mantener la compatibilidad de interfaz de la CPU
        (0,0).
        • scale: Coeficiente de la ventana de detección de aumento.
        • group_threshold: Coeficiente para regular el umbral de similitud. Cuando se detecta,
        algunos objetos pueden ser cubiertos por muchos rectángulos. 0 significa no
        realizar el agrupamiento.

         */
        hog.detectMultiScale(tempMat, foundLocations, foundWeights, 0.5, new Size(8,8),
                new Size(32, 32), 1.05, 2, false);
        

        //(tempMat, foundLocations, foundWeights, 1.4, new Size(8,8),
        //new Size(0, 0), 1.04, 2, false);

        Vector<Rect> foundLocationsFilteredList = new Vector<Rect>();
        filterRects(foundLocations.toList(), foundLocationsFilteredList);

        for (Rect foundLocation : foundLocationsFilteredList) {
            foundLocation.x=foundLocation.x*fdc;
            foundLocation.y=foundLocation.y*fdc;
            foundLocation.height=foundLocation.height*fdc;
            foundLocation.width=foundLocation.width*fdc;

            Imgproc.rectangle(frame, foundLocation.tl(), foundLocation.br(), new Scalar(0, 255, 0), 3);
        }

        tempMat.release();
        return frame;


    }
    private final void filterRects(List<Rect> candidates, List<Rect> objects) {
        for (int i = 0; i < candidates.size(); ++i) {
            Rect r = candidates.get(i);

            int j;
            for (j = 0; j < candidates.size(); ++j) {
                if (j != i && r.equals(candidates.get(j)))
                    break;
            }

            if (j == candidates.size())
                objects.add(r);
        }
    }
}
