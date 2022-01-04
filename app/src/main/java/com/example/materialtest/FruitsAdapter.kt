package com.example.materialtest

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * Created by 咸鱼至尊 on 2021/11/27
 */
class FruitsAdapter(layoutResId: Int, data: MutableList<Fruit>) : BaseListAdapter<Fruit>(layoutResId, data) {
    override fun convert(holder: BaseViewHolder, item: Fruit) {
        holder.setText(R.id.fruitName, item.name)
        Glide.with(context).load(item.imageId).into(holder.getView(R.id.fruitImage))
    }
}