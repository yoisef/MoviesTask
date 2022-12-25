package com.soft.task.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class Genre (
    val id: Int?=null,
    val name: String?=null
) : java.io.Serializable