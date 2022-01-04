package com.example.materialtest

import androidx.annotation.LayoutRes
import com.chad.library.adapter.base.module.LoadMoreModule

abstract class BaseLoadMoreListAdapter<T>(@LayoutRes private val layoutResId: Int, data: MutableList<T>? = null) :
    BaseListAdapter<T>(layoutResId, data), LoadMoreModule