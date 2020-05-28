package com.sungbin.recyclerviewadaptermaker.library

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sungbin.recyclerviewadaptermaker.library.swipe.SwipeController


/**
 * Created by SungBin on 2020-05-28.
 */

open class AdapterMaker {

    data class Option(val divier: Divider?, val padding: Padding?)
    data class Divider(val orientation: Int = LinearLayout.VERTICAL)
    data class Padding(val left: Int = 0,
                       val top: Int = 0,
                       val right: Int = 0,
                       val bottom: Int = 0)

    fun setAdapter(recyclerView: RecyclerView,
                   swipeController: SwipeController?,
                   option: Option?,
                   item: ArrayList<Any>,
                   layoutRes: Int): Adapter{
        val adapter = Adapter(item, layoutRes)
        if(swipeController != null) {
            val itemTouchHelper = ItemTouchHelper(swipeController)
            itemTouchHelper.attachToRecyclerView(recyclerView)
            recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    swipeController.onDraw(c)
                }
            })
        }
        recyclerView.adapter = adapter
        if(option != null) recyclerView.setItemOption(option.divier, option.padding)
        return adapter
    }

    private fun RecyclerView.setItemOption(divier: Divider?, padding: Padding? = null){
        if(divier != null) {
            this.addItemDecoration(DividerItemDecoration(this.context, divier.orientation))
        }
        if(padding != null) {
            this.addItemDecoration(object : RecyclerView.ItemDecoration() { //아이템 간격
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    if (parent.getChildAdapterPosition(view) !=
                        parent.adapter!!.itemCount) {
                        outRect.set(padding.left, padding.top, padding.right, padding.bottom)
                    }
                }
            })
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Adapter (private val item: ArrayList<Any>,
                   private val layoutRes: Int)
        : RecyclerView.Adapter<Adapter.ViewHolder>() {

        interface OnAdapterMakeListener {
            fun onBindViewHolder(item: ArrayList<Any>, view: View, position: Int)
        }

        private lateinit var listener: OnAdapterMakeListener
        fun setAdapterMakeListener(listener: OnAdapterMakeListener): Adapter {
            this.listener = listener
            return this
        }
        fun setAdapterMakeListener(listener: (item: ArrayList<Any>, view: View, position: Int) -> Unit): Adapter {
            this.listener = object : OnAdapterMakeListener {
                override fun onBindViewHolder(item: ArrayList<Any>, view: View, position: Int) {
                    listener(item, view, position)
                }
            }
            return this
        }

        inner class ViewHolder(viewholder: View) : RecyclerView.ViewHolder(viewholder) {
            val view = viewholder
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater
                .from(viewGroup.context)
                .inflate(
                    layoutRes,
                    viewGroup,
                    false
                )
            return ViewHolder(view)
        }

        override fun onBindViewHolder(@NonNull viewholder: ViewHolder, position: Int) {
            listener.onBindViewHolder(item, viewholder.view, position)
        }

        override fun getItemCount(): Int {
            return item.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        fun getItem(position: Int): Any {
            return item[position]
        }

    }

}