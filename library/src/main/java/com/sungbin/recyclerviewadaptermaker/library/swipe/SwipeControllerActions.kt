package com.sungbin.recyclerviewadaptermaker.library.swipe

abstract class SwipeControllerActions {
    open fun onLeftClicked(position: Int) {}
    open fun onRightClicked(position: Int) {}
}