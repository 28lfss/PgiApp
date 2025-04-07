package com.lfss.pgiapp.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OccurrenceModel(
    val id: Int?,
    val userUid: String,
    val imageBitmap: Bitmap?, //TODO: remove nullable condition after connecting repository to back-end
    val area: String,
    val description: String,
    val timestamp: Long
) : Parcelable