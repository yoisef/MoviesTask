package com.soft.task.utils

import androidx.recyclerview.widget.DiffUtil

class UserDiffCallback(private val mOldDayList: List<com.soft.task.domain.models.Movie>, private val mNewDayList: List<com.soft.task.domain.models.Movie>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldDayList.size
    }

    override fun getNewListSize(): Int {
        return mNewDayList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldDayList[oldItemPosition].id === mNewDayList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldone = mOldDayList[oldItemPosition]
        val newone = mNewDayList[newItemPosition]
        return oldone == newone
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        // Implement method if you're going to use ItemAnimator
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}