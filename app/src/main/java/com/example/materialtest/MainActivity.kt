package com.example.materialtest

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val toolBar by lazy { findViewById<Toolbar>(R.id.toolBar) }
    private val drawerLayout by lazy { findViewById<DrawerLayout>(R.id.drawerLayout) }
    private val navView by lazy { findViewById<NavigationView>(R.id.navView) }
    private val mFab by lazy { findViewById<FloatingActionButton>(R.id.fab) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    private val swipeRefresh by lazy { findViewById<SwipeRefreshLayout>(R.id.swipeRefresh) }

    //初始化数据源
    private val fruits = listOf(
        Fruit("Apple", R.drawable.apple),
        Fruit("Banana", R.drawable.banana),
        Fruit("Orange", R.drawable.orange),
        Fruit("Watermelon", R.drawable.watermelon),
        Fruit("Pear", R.drawable.pear),
        Fruit("Grape", R.drawable.grape),
        Fruit("Pineapple", R.drawable.pineapple),
        Fruit("Strawberry", R.drawable.strawberry),
        Fruit("Cherry", R.drawable.cherry),
        Fruit("Mango", R.drawable.mango)
    )
    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //状态栏沉浸
        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_main)
        //使用toolBar并使其外观与功能和actionBar一致
        setSupportActionBar(toolBar)
        supportActionBar?.let {
            //启用导航按钮，默认Home按钮 左上角返回箭头，可用来返回上一个页面
            it.setDisplayHomeAsUpEnabled(true)
            //修改导航按钮图标
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        navView.setCheckedItem(R.id.navCall)
        navView.setNavigationItemSelectedListener {
            "${it.title}".showToast()
            drawerLayout.closeDrawers()
            true
        }
        mFab.setOnClickListener {
            it.showSnackbar("Data deleted", "Undo") {
                "Data restored".showToast()
            }
        }
        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = layoutManager
        //val adapter = FruitAdapter(this, fruitList)
        val adapter = FruitsAdapter(R.layout.fruit_item, fruitList)
        adapter.setOnItemClickListener { _, _, position ->
            val user = User()
            user.name = "Tom"
            user.age = 20
            val person = Person("Amy", 25)
            val fruit = fruitList[position]
            Intent(this, FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
                putExtra("user_data", user)
                putExtra("person_data", person)
                startActivity(this)
            }
        }
        recyclerView.adapter = adapter
        swipeRefresh.setColorSchemeResources(android.R.color.holo_orange_light)
        swipeRefresh.setOnRefreshListener {
            refreshFruits(adapter)
        }
        if (isDarkTheme(this)) {
            "当前是夜间主题".showToast()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
//            R.id.backup -> "You clicked Backup".showToast()
//            R.id.delete -> "You clicked Delete".showToast()
//            R.id.settings -> "You clicked Settings".showToast()
            //Home按钮id永远为android.R.id.home 打开侧滑栏
//            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
//        return true
    }*/

    /**
     * 随机挑选五十个水果添加进集合
     */
    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (fruits.indices).random()
            fruitList.add(fruits[index])
        }
    }

    /**
     * 刷新操作
     * @param adapter 要刷新的适配器
     */
    private fun refreshFruits(adapter: FruitsAdapter) {
        thread {
            Thread.sleep(1000)
            runOnUiThread {
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    /**
     * 判断当前是否是夜间主题
     *
     * @param context 上下文对象
     * @return 是否
     */
    private fun isDarkTheme(context: Context): Boolean {
        val flag = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        return flag == Configuration.UI_MODE_NIGHT_YES
    }

    private fun printFruits() {
        val fruitList = mutableListOf("Apple", "Banana", "Orange", "Pear", "Grape", "WaterMelon")
        for (fruit in fruitList) {
            println(fruit)
        }
    }
}