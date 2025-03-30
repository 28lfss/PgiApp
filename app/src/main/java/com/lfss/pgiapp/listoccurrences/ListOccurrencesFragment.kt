package com.lfss.pgiapp.listoccurrences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lfss.pgiapp.databinding.FragmentListOccurrencesBinding

class ListOccurrencesFragment: Fragment() {

    private val viewModel: ListOccurrencesViewModel by viewModels()

    private var _binding: FragmentListOccurrencesBinding? = null
    private val binding get() = _binding!!

    data class Occurrence(val area: String, val description: String)

    private val occurrenceList = listOf(
        Occurrence("AREA 1", "DESCRIPTION 1"),
        Occurrence("AREA 2", "DESCRIPTION 2"),
        Occurrence("AREA 3", "DESCRIPTION 3"),
        Occurrence("AREA 1", "DESCRIPTION 1"),
        Occurrence("AREA 2", "DESCRIPTION 2"),
        Occurrence("AREA 3", "DESCRIPTION 3")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ) : View? {
        _binding = FragmentListOccurrencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ListOccurrencesAdapter(occurrenceList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}