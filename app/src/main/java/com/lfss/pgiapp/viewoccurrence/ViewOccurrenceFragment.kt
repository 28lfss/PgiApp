package com.lfss.pgiapp.viewoccurrence

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lfss.pgiapp.databinding.FragmentViewOccurrenceBinding
import com.lfss.pgiapp.model.OccurrenceModel

class ViewOccurrenceFragment : Fragment() {

    private var _binding: FragmentViewOccurrenceBinding? = null
    private val binding get() = _binding!!

    private var occurrenceData: OccurrenceModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ): View? {
        _binding = FragmentViewOccurrenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().supportFragmentManager.setFragmentResultListener(
            "request_key",
            this
        ) { key, bundle ->
            occurrenceData = bundle.getParcelable<OccurrenceModel>("occurrence")

            binding.viewOccurrenceArea.text = occurrenceData?.area
            binding.viewOccurrenceDescription.text = occurrenceData?.description
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}