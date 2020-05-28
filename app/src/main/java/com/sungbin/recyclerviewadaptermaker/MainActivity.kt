package com.sungbin.recyclerviewadaptermaker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sungbin.recyclerviewadaptermaker.library.AdapterMaker
import com.sungbin.recyclerviewadaptermaker.library.swipe.SwipeController
import com.sungbin.recyclerviewadaptermaker.library.swipe.SwipeControllerActions
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AdapterMaker()
            .setAdapter(
                rv,
                SwipeController(object : SwipeControllerActions() {
                    override fun onRightClicked(position: Int) {
                        super.onLeftClicked(position)
                        Toast.makeText(applicationContext, "Clicked Right Button.", Toast.LENGTH_LONG).show()
                    }

                    override fun onLeftClicked(position: Int) {
                        super.onRightClicked(position)
                        Toast.makeText(applicationContext, "Clicked Left Button.", Toast.LENGTH_LONG).show()
                    }
                }, 300f, 40f, Color.BLUE, Color.RED, "LEFT", "RIGHT"),
                AdapterMaker.Option(
                    AdapterMaker.Divider(
                        LinearLayout.HORIZONTAL
                    ),
                    AdapterMaker.Padding(
                        16, 16, 16, 16
                    )
                ),
                arrayListOf("H", "E", "L", "L", "O"),
                R.layout.test_layout
            )
            .setAdapterMakeListener { item, view, position ->
                val tv = view as TextView
                tv.text = item[position].toString()
                tv.setOnClickListener {
                    Toast.makeText(applicationContext, "${tv.text} Clicked", Toast.LENGTH_LONG).show()
                }
            }
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
