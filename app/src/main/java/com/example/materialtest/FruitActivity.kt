package com.example.materialtest

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FruitActivity : AppCompatActivity() {
    private val fab: FloatingActionButton by lazy { findViewById(R.id.fab) }
    private val fruitContentText: TextView by lazy { findViewById(R.id.fruitContentText) }
    private val collapsingToolbar: CollapsingToolbarLayout by lazy { findViewById(R.id.collapsingToolbar) }
    private val toolbar: Toolbar by lazy { findViewById(R.id.toolbar) }
    private val fruitImageView: ImageView by lazy { findViewById(R.id.fruitImageView) }

    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)
        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        val user = intent.getSerializableExtra("user_data") as User
        LogUtil.d("FruitActivity", "User's name is ${user.name}, age is ${user.age}")
        val person = intent.getParcelableExtra<Person>("person_data") as Person
        LogUtil.d("FruitActivity", "person's name is ${person.name}, age is ${person.age}")
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsingToolbar.title = fruitName
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.AppBarExpanded);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);
        Glide.with(this).load(fruitImageId).into(fruitImageView)
        fruitContentText.text = generateFruitContent(fruitName)
        fab.setOnClickListener {
            it.showSnackbar("Data deleted", "Undo") {
                "Data restored".showToast()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * 将水果名循环拼接500次
     * @param fruitName 水果名
     */
    private fun generateFruitContent(fruitName: String) = fruitName.repeat(500)
}