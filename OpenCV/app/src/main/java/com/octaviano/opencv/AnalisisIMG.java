package com.octaviano.opencv;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.Log;

public class AnalisisIMG {

    public Bitmap buscarCirculos(Bitmap bmp) {
        Mat src = new Mat();
        Bitmap bmp32 = bmp.copy(Bitmap.Config.ARGB_8888, true);
        Utils.bitmapToMat(bmp32, src);

        Mat src_gray = new Mat();

        Imgproc.cvtColor(src, src_gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(src_gray,src_gray);
        Imgproc.GaussianBlur(src_gray, src_gray, new Size(9, 9), 2, 2);


        Mat circles = new Mat();

        Imgproc.HoughCircles( src_gray, circles, Imgproc.CV_HOUGH_GRADIENT, 1, src_gray.height()/6, 200, 100, 0, 0 );

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
