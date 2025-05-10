package com.lfss.pgiapp.model;

import android.os.Parcelable;
import kotlinx.parcelize.Parcelize;

@Parcelize
data class UserModel(
    val userId : Long,
    val username : String,
    val email : String,
    val sessionToken : String,
    val accessLevel : String
) : Parcelable
