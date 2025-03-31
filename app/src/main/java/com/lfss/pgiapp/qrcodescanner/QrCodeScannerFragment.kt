package com.lfss.pgiapp.qrcodescanner

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.journeyapps.barcodescanner.ScanContract
import com.lfss.pgiapp.databinding.FragmentQrCodeScannerBinding
import com.journeyapps.barcodescanner.ScanOptions
import androidx.core.net.toUri

class QrCodeScannerFragment: Fragment() {
    private var _binding: FragmentQrCodeScannerBinding? = null
    private val binding get() = _binding!!

    private lateinit var qrCodeScannerLauncher: ActivityResultLauncher<ScanOptions>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        qrCodeScannerLauncher = registerForActivityResult(ScanContract()) { result ->
            if (result.contents != null) {
                Log.d("QR CODE", "Scanned: ${result.contents}")
                Toast.makeText(
                    requireContext(),
                    "QR Code: ${result.contents}",
                    Toast.LENGTH_SHORT
                ).show()

                //TODO: Validate if user has a browser before calling this intent
                openWebPage(result.contents)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ) : View? {
        _binding = FragmentQrCodeScannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.qrCodeScanner.setOnClickListener {
            startQRScanner()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun startQRScanner() {
        val options = ScanOptions().apply {
            setDesiredBarcodeFormats(ScanOptions.QR_CODE)
            setPrompt("Scan a QR code")
            setBeepEnabled(false)
        }
        qrCodeScannerLauncher.launch(options)
    }

    private fun openWebPage(url: String) {
        val webpage: Uri = url.toUri()
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }
}