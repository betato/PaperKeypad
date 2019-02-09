package org.betato.paperkeypad;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cameraBridgeViewBase;
    BaseLoaderCallback baseLoaderCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        cameraBridgeViewBase = (JavaCameraView) findViewById(R.id.cameraView);
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeViewBase.setCvCameraViewListener(this);

        // Camera listener callback
        baseLoaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                        cameraBridgeViewBase.enableView();
                        break;
                    default:
                        super.onManagerConnected(status);
                        break;
                }
            }
        };
    }

    @Override
    public void onCameraViewStarted(int width, int height) {

    }

    @Override
    public void onCameraViewStopped() {

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat gray = inputFrame.gray();
        MatOfPoint paperOutline = findPaper(gray);
        Mat colourImage = inputFrame.rgba();

        if (paperOutline != null) {
            ArrayList<MatOfPoint> outlineArray = new ArrayList<>();
            outlineArray.add(paperOutline);
            Imgproc.drawContours(colourImage, outlineArray, 0, new Scalar(255, 0, 0), 5);
        }

        return colourImage;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (cameraBridgeViewBase != null) {
            cameraBridgeViewBase.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()) {
            baseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraBridgeViewBase != null) {
            cameraBridgeViewBase.disableView();
        }
    }

    private MatOfPoint findPaper(Mat input) {
        Mat canny = new Mat();
        Imgproc.blur(input, canny, new Size(3, 3));
        Imgproc.Canny(canny, canny, 60, 180, 3);
        Imgproc.dilate(canny, canny, new Mat());

        ArrayList<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(canny, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        ArrayList<MatOfPoint> outlines = new ArrayList<>();
        int minContourPixels = (int)canny.size().area() / 8;

        for(MatOfPoint contour : contours) {
            MatOfPoint outline = approxContour(contour);
            if(outline.size().height == 4 && Imgproc.contourArea(outline) > minContourPixels && Imgproc.isContourConvex(outline)){
                outlines.add(outline);
            }
        }

        if (outlines.isEmpty()) {
            return null;
        } else {
            MatOfPoint largestOutline = outlines.get(0);
            for(int i = 1; i < outlines.size(); i++) {
                if (Imgproc.contourArea(outlines.get(i)) > Imgproc.contourArea(largestOutline)) {
                    largestOutline = outlines.get(i);
                }
            }
            return largestOutline;
        }
    }

    private MatOfPoint approxContour(MatOfPoint contour) {
        MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
        MatOfPoint2f approx = new MatOfPoint2f();
        Imgproc.approxPolyDP(contour2f, approx, Imgproc.arcLength(contour2f,true)*0.06, true);
        return new MatOfPoint(approx.toArray());
    }
}
