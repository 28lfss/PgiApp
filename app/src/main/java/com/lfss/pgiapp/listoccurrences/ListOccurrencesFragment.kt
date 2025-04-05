package com.lfss.pgiapp.listoccurrences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfss.pgiapp.R
import com.lfss.pgiapp.databinding.FragmentListOccurrencesBinding
import com.lfss.pgiapp.model.OccurrenceModel
import com.lfss.pgiapp.viewoccurrence.ViewOccurrenceFragment

class ListOccurrencesFragment : Fragment() {

    private val viewModel: ListOccurrencesViewModel by viewModels()

    private var _binding: FragmentListOccurrencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ): View? {
        _binding = FragmentListOccurrencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter =
            ListOccurrencesAdapter(viewModel.getUserOccurrencesList("user")) { occurrenceData ->
                val bundle = Bundle().apply {
                    putParcelable("occurrence", occurrenceData)
                }
                parentFragmentManager.setFragmentResult("request_key", bundle)

                parentFragmentManager.beginTransaction().replace(
                    R.id.menu_frame_layout,
                    ViewOccurrenceFragment()
                ).addToBackStack(null).commit()
            }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}