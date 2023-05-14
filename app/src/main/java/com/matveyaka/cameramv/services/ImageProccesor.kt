package com.matveyaka.cameramv.services

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy

class ImageProccesor: ImageAnalysis.Analyzer {
    override fun analyze(image: ImageProxy) {
        println("абаб")


        image.close()
    }
}