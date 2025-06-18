package com.lfss.pgiapp.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.lfss.pgiapp.databinding.FragmentViewOccurrenceBinding
import com.lfss.pgiapp.main.MainViewModel
import com.lfss.pgiapp.model.OccurrenceModel
import kotlin.getValue

class ViewOccurrenceFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentViewOccurrenceBinding? = null
    private val binding get() = _binding!!

    private var occurrenceData: OccurrenceModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requireActivity().supportFragmentManager.setFragmentResultListener(
            "request_key",
            this
        ) { key, bundle ->
            occurrenceData = bundle.getParcelable<OccurrenceModel>("occurrence")

            binding.occurrenceDate.text = viewModel.timestampToDateTime(occurrenceData?.timeCreated)
            binding.viewOccurrenceArea.text = occurrenceData?.area
            binding.viewOccurrenceDescription.text = occurrenceData?.description
            Log.e("IMAGE PATH", occurrenceData?.imagePath.toString())
            Glide.with(this)
                .load("https://pgi-backend.onrender.com/api/v1/file-storage?filename=${occurrenceData?.imagePath}")
                .into(binding.image)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ): View? {
        _binding = FragmentViewOccurrenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}