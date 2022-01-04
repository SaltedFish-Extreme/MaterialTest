package com.example.materialtest

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by 咸鱼至尊 on 2021/11/10
 *
 * des: Parcelable分解对象
 */
@Parcelize
class Person(var name: String, var age: Int) : Parcelable