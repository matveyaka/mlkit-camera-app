package com.matveyaka.cameramv.firstFragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.matveyaka.cameramv.R
import com.matveyaka.cameramv.scanActivity.ScanActivity

class StartScanFragment: Fragment() {
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
        {isGranted ->
            if (isGranted == true)
            {
                startScan()
            }
            else
            {
                //ДЗ: сделать чтобы кнопка исчезала
            }
        }
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.start_scan_fragment, container, false )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.buttonScan)
        button.setOnClickListener {
            val permission = ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            )
            if ( permission == PackageManager.PERMISSION_GRANTED)
            {
                startScan()
            }
            else
            {
                permissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun startScan(){
        val intent = Intent (requireContext(), ScanActivity::class.java)
        startActivity(intent)
    }
}