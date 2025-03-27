package com.lfss.pgiapp.createoccurrence

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lfss.pgiapp.databinding.FragmentCreateOccurrenceBinding
import kotlin.getValue

class CreateOccurrenceFragment : Fragment() {
    private val viewModel: CreateOccurrenceViewModel by viewModels()

    private var _binding: FragmentCreateOccurrenceBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraPermissionRequestLauncher: ActivityResultLauncher<String>
    private lateinit var getCameraImageBitMap: ActivityResultLauncher<Void?>
    private lateinit var getGalleryImageBitMap: ActivityResultLauncher<PickVisualMediaRequest>

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
                getCameraImageBitMap.launch(null)
            } else {
                Toast.makeText(
                    requireContext(), "Camera not available",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        getCameraImageBitMap = registerForActivityResult(
            ActivityResultContracts.TakePicturePreview()
        ) { image: Bitmap? ->
            image?.let {
                binding.occurrenceImage.setImageBitmap(image)
            }
        }

        getGalleryImageBitMap = registerForActivityResult(PickVisualMedia()) { uri ->
            if (uri != null) {
                uri.let {
                    binding.occurrenceImage.setImageURI(uri)
                }
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

        binding.cameraImage.setOnClickListener {
            cameraPermissionRequestLauncher.launch(Manifest.permission.CAMERA)
        }

        binding.galleryImage.setOnClickListener {
            getGalleryImageBitMap.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
