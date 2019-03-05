package com.octaviano.opencv;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AnalisisIMG {

    public Bitmap segmentar(Bitmap bmp){
        Mat newimg = new Mat();
        Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, newimg);
        Mat des = new Mat(newimg.rows(), newimg.cols(), newimg.type());
        Mat norm = new Mat();

        Imgproc.cvtColor(newimg, des, Imgproc.COLOR_BGR2HSV);
        List<Mat> hsv = new ArrayList<Mat>();
        Core.split(des, hsv);
        Mat v = hsv.get(2); //gets the grey scale version

        //Imgcodecs.imwrite("/Users/Lisa-Maria/Documents/CapturedImages/B&Wpic.jpg", v); //only writes mats

        CLAHE clahe = Imgproc.createCLAHE(2.0, new Size(8,8) ); //2.0, new Size(8,8)
        clahe.apply(v,v);
//    Imgproc.GaussianBlur(v, v, new Size(9,9), 3); //adds left pupil boundary and random circle on 'a'
        //   Imgproc.GaussianBlur(v, v, new Size(9,9), 13); //adds right outer iris boundary and random circle on 'a'
        Imgproc.GaussianBlur(v, v, new Size(9,9), 7);  //adds left outer iris boundary and random circle on left by hair
        //  Imgproc.GaussianBlur(v, v, new Size(7,7), 15);
        Core.addWeighted(v, 1.5, v, -0.5, 0, v);



        if (v != null) {

            Mat circles = new Mat();
            Imgproc.HoughCircles( v, circles, Imgproc.CV_HOUGH_GRADIENT, 2, v.rows(), 100, 20, 20, 200 );

            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

            System.out.println("circles.cols() " + circles.cols());
            if(circles.cols() > 0) {
                System.out.println("1");
                for (int x = 0; x < circles.cols(); x++) {
                    System.out.println("2");
                    double vCircle[] = circles.get(0, x);


                    if(vCircle == null) {
                        break;
                    }

                    Point pt = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
                    int radius = (int) Math.round(vCircle[2]);

                    //draw the found circle




                    Imgproc.circle(v, pt, radius, new Scalar(255,0,0),2); //newimg
                    //Imgproc.circle(des, pt, radius/3, new Scalar(225,0,0),2); //pupil
                    Imgcodecs.imwrite("/Users/.../Houghpic.jpg", v); //newimg

                    //draw the mask: white circle on black background
//                  Mat mask = new Mat( new Size( des.cols(), des.rows() ), CvType.CV_8UC1 );
//                  Imgproc.circle(mask, pt, radius, new Scalar(255,0,0),2);

//                  des.copyTo(des,mask);
//                  Imgcodecs.imwrite("/Users/..../mask.jpg", des); //newimg

                    Imgproc.logPolar(des, norm, pt, radius, Imgproc.WARP_FILL_OUTLIERS);


                }
            }
        }

        Utils.matToBitmap(v, bmp);
        return bmp;
    }


    public Bitmap buscarCirculos(Bitmap bmp) {
        Mat src = new Mat();
        Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, src);

        Mat src_gray = new Mat();


        Imgproc.cvtColor(src, src_gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(src_gray, src_gray, new Size(9, 9), 2, 2);
        Imgproc.Canny(src_gray, src_gray, 32, 2);



        //Imgproc.morphologyEx(src_gray,src_gray,4,Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(11, 11)));
        //Imgproc.threshold(src_gray,src_gray,127,255,Imgproc.THRESH_OTSU);
        //Imgproc.GaussianBlur(src_gray, src_gray, new Size(9, 9), 2, 2);
        /*
        Imgproc.equalizeHist(src_gray,src_gray);
        final Size kernelSize = new Size(11, 11);
        final Point anchor = new Point(1, 1);
        final int iterations = 1;
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE, kernelSize);
        Imgproc.erode(src_gray, src_gray, kernel, anchor, iterations);
        Imgproc.dilate(src_gray, src_gray, kernel, anchor, iterations);
        Imgproc.Canny(src_gray, src_gray, 32, 2);*/



        Mat circles = new Mat();

        Imgproc.HoughCircles( src_gray, circles, Imgproc.CV_HOUGH_GRADIENT, 2, src_gray.height()/4 );

        Log.w("circles", circles.cols() + "");

        /// Draw the circles detected
        for (int i = 0; i < circles.cols(); i++) {
            double vCircle[] = circles.get(0, i);
            Point center = new Point(Math.round(vCircle[0]), Math.round(vCircle[1]));
            int radius = (int) Math.round(vCircle[2]);

            Imgproc.circle(src, center, 3, new Scalar(0, 255, 0), -1, 8, 0);
            // circle outline
            Imgproc.circle(src, center, radius, new Scalar(0, 0, 255), 3, 8, 0);
        }

        Utils.matToBitmap(src, bmp);
        return bmp;
    }

    public Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;

    }
}
