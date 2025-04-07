package com.lfss.pgiapp.createoccurrence

import android.Manifest
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
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.lfss.pgiapp.databinding.FragmentCreateOccurrenceBinding
import com.lfss.pgiapp.model.OccurrenceModel
import java.time.LocalDateTime
import kotlin.getValue

class CreateOccurrenceFragment : Fragment() {
    private val viewModel: CreateOccurrenceViewModel by viewModels()

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
            registerForActivityResult(PickVisualMedia()) { uri ->
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
            getGalleryImage.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))
        }

        binding.createOccurrenceButton.setOnClickListener {
            if (binding.occurrenceImage.drawable != null) { //TODO: Check if text inputs aren't empty
                var createdOccurrence: OccurrenceModel = OccurrenceModel(
                    null, // ID will be appointed when added to the Database
                    "USER UID",
                    binding.occurrenceImage.drawable.toBitmap(),
                    binding.occurrenceAreaInput.text.toString(),
                    binding.occurrenceDescriptionInput.text.toString(),
                    System.currentTimeMillis()
                )
                viewModel.createOccurrence(createdOccurrence)
                Toast.makeText(
                    requireContext(), "Occurrence Created",
                    Toast.LENGTH_SHORT
                ).show()
            }
            Toast.makeText(
                requireContext(), "OCCURRENCE INCOMPLETE!!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}