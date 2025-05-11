package com.lfss.pgiapp.model;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;

@Parcelize
data class UserModel(
    val userId : Long,
    val username : String,
    val email : String,
    val sessionToken : String?, //TODO: remove nullable
    val accessLevel : String? //TODO: remove nullable
) : Parcelable
