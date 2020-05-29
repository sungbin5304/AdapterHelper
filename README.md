![banner](https://raw.githubusercontent.com/sungbin5304/AdapterHelper/master/banner%20(1).png)

<p align="center">
  <a href="https://github.com/sungbin5304/AdapterHelper/blob/master/LICENSE"><img alt="License" src="https://img.shields.io/badge/License-Apache2-blue"/></a>
  <a href="https://jitpack.io/#sungbin5304/AdapterHelper"><img alt="Download" src="https://jitpack.io/v/sungbin5304/AdapterHelper.svg"/></a>
  <a href="https://github.com/sungbin5304/RecyclerViewAdapterMaker"><img alt="Title" src="https://img.shields.io/badge/Helper-RecyclerViewAdapter-ff69b4"/></a>
  <a href="https://github.com/sungbin5304"><img alt="Highlight" src="https://img.shields.io/badge/GitHub-SungBin-yellow"/></a>
</p>

-----

# Download
```Gradle
repositories {
  mavenCentral()
  google()
  maven { 
    url 'https://jitpack.io' 
  }
}

dependencies {
  implementation 'com.github.sungbin5304:AdapterHelper:{version}'
}
```

# Usage
## Default
``` kotlin
AdapterHelper
    .with(view: RecyclerView)
    .bindLayout(layoutRes: Int)
    .create(item: ArrayList<Any>)
```

## Binding your view
``` kotlin
...
.addViewBindListener { item, view, position ->
  //Bind your view. 
}
.create(item: ArrayList<Any>)
```

## Add some options
``` kotlin
...
.addOption(Option(val divier: Divider?, val padding: Padding?)) 
.addSwipeListener(SwipeController(object : SwipeControllerActions() {
  override fun onLeftClicked(items: ArrayList<Any>, position: Int) {
    super.onLeftClicked(items, position)
      //code your action.
  }
  override fun onRightClicked(items: ArrayList<Any>, position: Int) {
    super.onRightClicked(items, position)
      //code your action.
  }
}, buttonWidth: Float = 300f, buttonRadius: Float = 40f, leftButtonColor: Int? = null, rightButtonColor: Int? = null, leftButtonText: String? = null, rightButtonText: String? = null))
.create(item: ArrayList<Any>)
```

## Example

<img alt="preview" src="https://raw.githubusercontent.com/sungbin5304/AdapterHelper/master/preview.png" align="right" width="30%"/>

``` Kotlin
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
```

# License
[Apache License 2.0](https://github.com/sungbin5304/RecyclerViewAdapterMaker/blob/master/LICENSE)

# Happy Coding :)
