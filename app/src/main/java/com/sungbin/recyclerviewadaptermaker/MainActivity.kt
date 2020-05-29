package com.sungbin.recyclerviewadaptermaker

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungbin.recyclerviewadaptermaker.library.AdapterHelper
import com.sungbin.recyclerviewadaptermaker.library.dto.Option
import com.sungbin.recyclerviewadaptermaker.library.dto.Padding
import com.sungbin.recyclerviewadaptermaker.library.swipe.SwipeController
import com.sungbin.recyclerviewadaptermaker.library.swipe.SwipeControllerActions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AdapterHelper
            .with(rv)
            .bindLayout(R.layout.test_layout)
            .addViewBindListener { item, view, position ->
                val tv = view as TextView
                tv.text = item[position].toString()
                tv.setOnClickListener { toast("${tv.text} Clicked.") }
            }
            .addOption(Option(null, Padding(16, 16, 16, 16)))
            .addSwipeListener(SwipeController(object : SwipeControllerActions() {
                override fun onLeftClicked(items: ArrayList<Any>, position: Int) {
                    super.onLeftClicked(items, position)
                    toast("${items[position]} Left Clicked.")
                }
                override fun onRightClicked(items: ArrayList<Any>, position: Int) {
                    super.onRightClicked(items, position)
                    toast("${items[position]} Right Clicked.")
                }
            }, 300f, 40f, Color.BLUE, Color.RED, "Left", "Right"))
            .create(arrayListOf("H", "E", "L", "L", "O"))
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun toast(string: String){
        Toast.makeText(
            applicationContext,
            string,
            Toast.LENGTH_LONG
        ).show()
    }
}
