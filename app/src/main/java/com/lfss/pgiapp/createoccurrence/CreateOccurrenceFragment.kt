package com.lfss.pgiapp.createoccurrence

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lfss.pgiapp.databinding.FragmentCreateOccurrenceBinding
import kotlin.getValue

class CreateOccurrenceFragment : Fragment() {
    private var _binding: FragmentCreateOccurrenceBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateOccurrenceViewModel by viewModels()

    private val getImageBitMap = registerForActivityResult(
        ActivityResultContracts.TakePicturePreview()
    ) { image: Bitmap? ->
        image?.let {
            binding.occurrenceImage.setImageBitmap(image)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstaceState: Bundle?
    ): View? {
        _binding = FragmentCreateOccurrenceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cameraImage.setOnClickListener {
            getImageBitMap.launch(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
