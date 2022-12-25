package com.soft.task.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Genre (
    val id: Int,
    val name: String
) : java.io.Serializable