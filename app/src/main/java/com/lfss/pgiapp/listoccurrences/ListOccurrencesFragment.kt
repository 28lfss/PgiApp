package com.lfss.pgiapp.listoccurrences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfss.pgiapp.databinding.FragmentListOccurrencesBinding

class ListOccurrencesFragment : Fragment() {

    private val viewModel: ListOccurrencesViewModel by viewModels()

    private var _binding: FragmentListOccurrencesBinding? = null
    private val binding get() = _binding!!

    data class Occurrence(val area: String, val time: String)

    private val occurrenceList = listOf(
        Occurrence("AREA 1", "11111111111111"),
        Occurrence("AREA 2", "22222222222222"),
        Occurrence("AREA 3", "33333333333333"),
        Occurrence("AREA 4", "44444444444444"),
        Occurrence("AREA 5", "55555555555555"),
        Occurrence("AREA 6", "66666666666666"),
        Occurrence("AREA 1", "11111111111111"),
        Occurrence("AREA 2", "22222222222222"),
        Occurrence("AREA 3", "33333333333333"),
        Occurrence("AREA 4", "44444444444444"),
        Occurrence("AREA 5", "55555555555555"),
        Occurrence("AREA 6", "66666666666666"),
        Occurrence("AREA 1", "11111111111111"),
        Occurrence("AREA 2", "22222222222222"),
        Occurrence("AREA 3", "33333333333333"),
        Occurrence("AREA 4", "44444444444444"),
        Occurrence("AREA 5", "55555555555555"),
        Occurrence("AREA 6", "66666666666666"),
        Occurrence("AREA 1", "11111111111111"),
        Occurrence("AREA 2", "22222222222222"),
        Occurrence("AREA 3", "33333333333333"),
        Occurrence("AREA 4", "44444444444444"),
        Occurrence("AREA 5", "55555555555555"),
        Occurrence("AREA 6", "66666666666666"),

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

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = ListOccurrencesAdapter(occurrenceList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}