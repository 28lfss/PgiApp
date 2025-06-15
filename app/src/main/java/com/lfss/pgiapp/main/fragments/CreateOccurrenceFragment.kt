package com.lfss.pgiapp.main.fragments

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.lfss.pgiapp.databinding.FragmentCreateOccurrenceBinding
import com.lfss.pgiapp.main.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CreateOccurrenceFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentCreateOccurrenceBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraPermissionRequestLauncher: ActivityResultLauncher<String>
    private lateinit var getCameraImage: ActivityResultLauncher<Void?>
    private lateinit var getGalleryImage: ActivityResultLauncher<PickVisualMediaRequest>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cameraPermissionRequestLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(
                    requireContext(), "Camera available",
                    Toast.LENGTH_SHORT
                ).show()
                getCameraImage.launch(null)
            } else {
                Toast.makeText(
                    requireContext(), "Camera not available",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        getCameraImage = registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) { imageBitmap: Bitmap? ->
            imageBitmap?.let {
                binding.occurrenceImage.setImageBitmap(imageBitmap)
            }
        }

        getGalleryImage =
            registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
                if (uri != null) {
                    val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
                    val imageBitmap = ImageDecoder.decodeBitmap(source)
                    binding.occurrenceImage.setImageBitmap(imageBitmap)
                } else {
                    Log.d("PhotoPicker", "No media selected")
                }
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

        binding.cameraImageButton.setOnClickListener {
            cameraPermissionRequestLauncher.launch(Manifest.permission.CAMERA)
        }

        binding.galleryImageButton.setOnClickListener {
            getGalleryImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.createOccurrenceButton.setOnClickListener {
            if (binding.occurrenceImage.drawable != null) { //TODO: Check if text inputs aren't empty

                viewModel.createOccurrence(
                    binding.occurrenceImage.drawable.toBitmap(),
                    binding.occurrenceAreaInput.text.toString(),
                    binding.occurrenceDescriptionInput.text.toString(),
                    activity?.getSharedPreferences("sessionPreference", Context.MODE_PRIVATE)
                        ?.getLong("sessionToken", 0L)
                )
            }
        }

        lifecycleScope.launch {
            viewModel.occurrenceState.collectLatest { occurrence ->
                if (occurrence != null) {
                    Toast.makeText(
                        this@CreateOccurrenceFragment.activity,
                        "Occurrence created!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@CreateOccurrenceFragment.activity,
                        "Error creating occurrence",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}