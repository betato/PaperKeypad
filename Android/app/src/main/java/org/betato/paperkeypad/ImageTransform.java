package org.betato.paperkeypad;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.imgproc.Imgproc;

public class ImageTransform {
    private MatOfPoint approxContour(MatOfPoint contour) {
        MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
        MatOfPoint2f approx = new MatOfPoint2f();
        Imgproc.approxPolyDP(contour2f, approx, Imgproc.arcLength(contour2f,true)*0.06, true);
        return new MatOfPoint(approx.toArray());
    }

    private void transform(Mat image, MatOfPoint transform) {
        Point[] sortedTransform = transform.toArray();

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
