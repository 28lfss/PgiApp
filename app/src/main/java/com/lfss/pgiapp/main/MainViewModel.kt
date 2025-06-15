package com.lfss.pgiapp.main

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfss.pgiapp.EventRepository
import com.lfss.pgiapp.model.OccurrenceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel() {
    val eventRepository: EventRepository = EventRepository()

    private val _occurrenceState = MutableStateFlow<OccurrenceModel?>(null)
    val occurrenceState: StateFlow<OccurrenceModel?> = _occurrenceState

    private fun bitmapToMultipart(bitmap: Bitmap): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream)  // Adjust compression as needed
        val byteArray = stream.toByteArray()

        val requestBody = byteArray.toRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("imageFile", "image.jpg", requestBody)
    }

    fun createOccurrence(imageBitmap: Bitmap, area: String, description: String, registrantId: Long?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (registrantId == 0L || registrantId == null) {
                    Log.e("MainViewModel", "Session token is not available")
                    throw IllegalArgumentException()
                }
                val newOccurrence = OccurrenceModel(
                    null,
                    area,
                    description,
                    null,
                    null,
                    registrantId
                )

                val response =
                    eventRepository.createOccurrence(newOccurrence, bitmapToMultipart(imageBitmap))

                Log.e("TEST", response.body().toString())

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        _occurrenceState.value = body
                    }
                } else {
                    _occurrenceState.value = null
                }
            } catch (e: Exception) {
                _occurrenceState.value = null
            }
        }
    }

    fun getUserOccurrencesList(userUid: String): List<OccurrenceModel> {
        return eventRepository.occurrencesListByUid(userUid)
    }
}