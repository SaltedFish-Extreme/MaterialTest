package com.example.materialtest

import android.widget.Toast

/**
 * Created by 咸鱼至尊 on 2021/11/4
 * des: toast封装工具类
 */
/**
 * String类扩展toast函数
 *
 * @param context 上下文对象
 * @param duration 显示时长 默认短时间
 */
fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}

/**
 * Int类扩展toast函数
 *
 * @param context 上下文对象
 * @param duration 显示时长 默认短时间
 */
fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(MyApplication.context, this, duration).show()
}