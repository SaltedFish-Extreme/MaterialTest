package com.example.materialtest

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * Created by 咸鱼至尊 on 2021/11/2
 */
class FruitAdapter(private val context: Context, private val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruitImage)
        val fruitName: TextView = view.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.fruit_item, parent, false)
        val user = User()
        user.name = "Tom"
        user.age = 20
        val person = Person("Amy", 25)
        ViewHolder(view).apply {
            itemView.setOnClickListener {
                val fruit = fruitList[bindingAdapterPosition]
                Intent(context, FruitActivity::class.java).apply {
                    putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                    putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
                    putExtra("user_data", user)
                    putExtra("person_data", person)
                    context.startActivity(this)
                }
            }
            return this
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage)
    }

    override fun getItemCount() = fruitList.size
}