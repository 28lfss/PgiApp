package com.lfss.pgiapp.listoccurrences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lfss.pgiapp.R

class ListOccurrencesFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ) : View? {
        return inflater.inflate(R.layout.fragment_list_occurrences,container, false)
    }
}