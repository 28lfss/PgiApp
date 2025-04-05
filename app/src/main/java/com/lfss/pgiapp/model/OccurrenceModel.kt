package com.lfss.pgiapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OccurrenceModel(
    val id: Int,
    //val imageUri: Uri,
    val area: String,
    val description: String,
    val timestamp: String
) : Parcelable