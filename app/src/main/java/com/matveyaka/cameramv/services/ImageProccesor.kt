package com.matveyaka.cameramv.services

import android.annotation.SuppressLint
import android.media.Image
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetectorOptionsBase
import com.google.mlkit.vision.pose.PoseLandmark
import com.google.mlkit.vision.pose.accurate.AccuratePoseDetectorOptions

class ImageProccesor: ImageAnalysis.Analyzer {
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
                            it.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
                            image.close()
            }
                        .addOnFailureListener{
                            image.close()
                }
        }

    }
}