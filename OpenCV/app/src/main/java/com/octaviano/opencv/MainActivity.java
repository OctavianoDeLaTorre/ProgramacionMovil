package com.octaviano.opencv;

;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2{

    private CameraBridgeViewBase cameraView = null;
    private static boolean initOpenCV = false;

    static { initOpenCV = OpenCVLoader.initDebug(); }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cameraView = findViewById(R.id.cameraview);
        cameraView.setVisibility(SurfaceView.VISIBLE);
        cameraView.setCvCameraViewListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (initOpenCV) { cameraView.enableView(); }
    }

    @Override
    public void onPause() {
        super.onPause();

        // Release the camera.
        if (cameraView != null) {
            cameraView.disableView();
            cameraView = null;
        }
    }

    //Sobreescritos de CameraBridgeViewBase.CvCameraViewListener2
    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat src = inputFrame.rgba() ;
        Mat src_gray = inputFrame.gray();

        Imgproc.cvtColor( src, src_gray, Imgproc.COLOR_BGR2GRAY );
        Imgproc.GaussianBlur( src_gray, src_gray, new Size(9, 9), 2, 2 );

        Mat circles = new Mat();

        Imgproc.HoughCircles( src_gray, circles, Imgproc.CV_HOUGH_GRADIENT, 1, src_gray.height()/8, 200, 100, 0, 0 );

        Log.w("circles", circles.cols()+"");

        /// Draw the circles detected
        for( int i = 0; i < circles.cols(); i++ )
        {
            double vCircle[]=circles.get(0,i);
            Point center=new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
            int radius = (int)Math.round(vCircle[2]);

            Imgproc.circle( src, center, 3, new Scalar(0,255,0), -1, 8, 0 );
            // circle outline
            Imgproc.circle( src, center, radius, new Scalar(0,0,255), 3, 8, 0 );
        }
        return src;
    }
}
