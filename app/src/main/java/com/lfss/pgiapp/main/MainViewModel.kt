package com.lfss.pgiapp.main

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _listLiveData = MutableLiveData<List<OccurrenceModel>>(emptyList())
    val listLiveData: LiveData<List<OccurrenceModel>> = _listLiveData

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

    fun getUserOccurrencesList(userId: Long?) {
        viewModelScope.launch {
            try {
                if (userId == 0L || userId == null) {
                    Log.e("OCCURRENCES LIST", "Session token is not available")
                    throw IllegalArgumentException()
                }
                _listLiveData.value = eventRepository.occurrencesListByUid(userId)


            } catch (e: Exception) {
                Log.e("OCCURRENCES LIST", e.toString())
            }
        }
    }
}