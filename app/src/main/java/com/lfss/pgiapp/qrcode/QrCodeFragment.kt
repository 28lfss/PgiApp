package com.lfss.pgiapp.qrcode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lfss.pgiapp.R

class QrCodeFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_qr_code,container, false)
    }
}