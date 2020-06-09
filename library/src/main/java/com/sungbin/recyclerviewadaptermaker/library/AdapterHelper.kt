package com.sungbin.recyclerviewadaptermaker.library

import android.graphics.Canvas
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sungbin.recyclerviewadaptermaker.library.dto.Divider
import com.sungbin.recyclerviewadaptermaker.library.dto.Option
import com.sungbin.recyclerviewadaptermaker.library.dto.Padding
import com.sungbin.recyclerviewadaptermaker.library.swipe.SwipeController
import kotlin.properties.Delegates


/**
 * Created by SungBin on 2020-05-28.
 */

object AdapterHelper {

    private lateinit var item: ArrayList<*>
    private lateinit var listener: OnViewBindListener
    private lateinit var recyclerView: RecyclerView
    private var option: Option? = null
    private var swipeController: SwipeController? = null
    private var layoutRes by Delegates.notNull<Int>()

    interface OnViewBindListener {
        fun onViewBindListener(item: ArrayList<*>, view: View, position: Int)
    }

    fun addViewBindListener(listener: OnViewBindListener): AdapterHelper {
        this.listener = listener
        return this
    }

    fun addViewBindListener(listener: (item: ArrayList<*>, view: View, position: Int) -> Unit): AdapterHelper {
        this.listener = object : OnViewBindListener {
            override fun onViewBindListener(item: ArrayList<*>, view: View, position: Int) {
                listener(item, view, position)
            }
        }
        return this
    }

    fun with(recyclerView: RecyclerView): AdapterHelper {
        this.recyclerView = recyclerView
        return this
    }

    fun bindLayout(layoutRes: Int): AdapterHelper {
        this.layoutRes = layoutRes
        return this
    }

    fun addSwipeListener(swipeController: SwipeController): AdapterHelper {
        this.swipeController = swipeController
        return this
    }

    fun addOption(option: Option): AdapterHelper {
        this.option = option
        return this
    }

    fun create(item: ArrayList<*>){
        this.item = item
        setAdapter()
    }

    private fun setAdapter(){
        val adapter = Adapter()
        if(swipeController != null) {
            val itemTouchHelper = ItemTouchHelper(swipeController!!)
            itemTouchHelper.attachToRecyclerView(recyclerView)
            recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
                    swipeController!!.onDraw(c)
                }
            })
        }
        if(option != null) recyclerView.setItemOption(option!!.divier, option!!.padding)
        recyclerView.adapter = adapter
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
                    if (parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount) {
                        outRect.set(padding.left, padding.top, padding.right, padding.bottom)
                    }
                }
            })
        }
    }

    class Adapter : RecyclerView.Adapter<Adapter.ViewHolder>() {

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
            listener.onViewBindListener(item, viewholder.view, position)
        }

        override fun getItemCount(): Int = item.size
        override fun getItemId(position: Int): Long = position.toLong()
        override fun getItemViewType(position: Int): Int = position
        fun getItem(position: Int): Any = item[position]
        fun getAllItem(): ArrayList<*> = item
    }

}