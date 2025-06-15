package com.lfss.pgiapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OccurrenceModel(
    val occurrenceId: Long?,
    val area: String,
    val description: String,
    val imagePath: String?,
    val timeCreated: Long?,
    val registrantId: Long
) : Parcelable