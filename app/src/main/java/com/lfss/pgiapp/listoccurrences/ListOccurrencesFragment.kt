package com.lfss.pgiapp.listoccurrences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfss.pgiapp.databinding.FragmentListOccurrencesBinding
import com.lfss.pgiapp.model.OccurrenceModel

class ListOccurrencesFragment : Fragment() {

    private val viewModel: ListOccurrencesViewModel by viewModels()

    private var _binding: FragmentListOccurrencesBinding? = null
    private val binding get() = _binding!!

    private val occurrenceList = listOf(
        OccurrenceModel(null, "AREA 1", "DESCRIPTION ", null, "30/03/2025"),
        OccurrenceModel(null, "AREA 2", "DESCRIPTION ", null, "01/04/2025"),
        OccurrenceModel(null, "AREA 3", "DESCRIPTION ", null, "05/04/2025"),
        OccurrenceModel(null, "AREA 4", "DESCRIPTION ", null, "09/04/2025"),
        OccurrenceModel(null, "AREA 5", "DESCRIPTION ", null, "13/04/2025"),
        OccurrenceModel(null, "AREA 6", "DESCRIPTION ", null, "22/04/2025")
        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ): View? {
        _binding = FragmentListOccurrencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ListOccurrencesAdapter(occurrenceList) {
            Toast.makeText(
                requireContext(), "ITEM CLICK TEST",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}