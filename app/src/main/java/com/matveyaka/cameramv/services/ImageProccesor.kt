package com.matveyaka.cameramv.services

import android.annotation.SuppressLint
import android.media.Image
import android.util.Size
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions
import com.matveyaka.cameramv.scanActivity.DrawView
import java.lang.Integer.max
import java.lang.Math.min

class ImageProccesor(
    private val view: DrawView
): ImageAnalysis.Analyzer {
    private val options = AccuratePoseDetectorOptions
        .Builder()
        .setDetectorMode(AccuratePoseDetectorOptions.STREAM_MODE)
        .build()
    private val detector = PoseDetection.getClient(options)
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val frameImage = image.image
        if (frameImage != null)
        {
            val imageForDetector = InputImage.fromMediaImage(frameImage, image.imageInfo.rotationDegrees)
            val detectTask = detector.process(imageForDetector)
            detectTask
                        .addOnSuccessListener {
                            val size = Size(
                                min(image.width,image.height),
                                max(image.width,image.height)
                            )
                            view.setPose(it, size)
                            image.close()
            }
                        .addOnFailureListener{
                            image.close()
                }
        }

    }
}