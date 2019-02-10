package org.betato.paperkeypad;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;

public class ImageTransform {

    private MatOfPoint paperOutline;

    public MatOfPoint getPaperOutline() {
        return paperOutline;
    }

    public MatOfPoint findPaper(Mat input) {
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
            paperOutline = outlines.get(0);
            for(int i = 1; i < outlines.size(); i++) {
                if (Imgproc.contourArea(outlines.get(i)) > Imgproc.contourArea(paperOutline)) {
                    paperOutline = outlines.get(i);
                }
            }
            return paperOutline;
        }
    }

    private MatOfPoint approxContour(MatOfPoint contour) {
        MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
        MatOfPoint2f approx = new MatOfPoint2f();
        Imgproc.approxPolyDP(contour2f, approx, Imgproc.arcLength(contour2f,true)*0.06, true);
        return new MatOfPoint(approx.toArray());
    }

    public void transformToOutline(Mat image) {
        Point[] sortedTransform = paperOutline.toArray();

        int first = 0;
        for (int i = 0; i < sortedTransform.length; i++) {
            if (sortedTransform[i].x + sortedTransform[i].y < sortedTransform[first].x + sortedTransform[first].y) {
                first = i;
            }
        }

        Mat srcTransform = new MatOfPoint2f(sortedTransform);
        Mat destTransform = new MatOfPoint2f(
                new Point(0,0), new Point(image.width() - 1,0),
                new Point(image.width() - 1,image.height() - 1), new Point(0,image.height() - 1));

        Mat finalTransform = Imgproc.getPerspectiveTransform(srcTransform, destTransform);
        Imgproc.warpPerspective(image, image, finalTransform, image.size());
    }
}
