![banner](https://raw.githubusercontent.com/sungbin5304/RecyclerViewAdapterMaker/master/banner.png)

<p align="center">
  <a href="https://github.com/sungbin5304/RecyclerViewAdapterMaker/blob/master/LICENSE"><img alt="License" src="https://img.shields.io/badge/License-Apache2-blue"/></a>
  <a href="https://jitpack.io/#sungbin5304/RecyclerViewAdapterMaker"><img alt="Download" src="https://jitpack.io/v/sungbin5304/SimpleCodeEditor.svg"/></a>
  <a href="https://github.com/sungbin5304/RecyclerViewAdapterMaker"><img alt="Title" src="https://img.shields.io/badge/Maker-RecyclerViewAdapter-ff69b4"/></a>
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
  implementation 'com.github.sungbin5304:RecyclerViewAdapterMaker:{version}'
}
```

# Usage
```Kotlin
AdapterMaker()
    .setAdapter(
        view: RecyclerView,
        swipeController: SwipeController(
          object : SwipeControllerActions(),
          buttonWidth: Float = 300f,
          buttonRadius: Float = 40f,
          leftButtonColor: Int? = null,
          rightButtonColor: Int? = null,
          leftButtonText: String? = null,
          rightButtonText: String? = null
        ),
        option: AdapterMaker.Option(
            AdapterMaker.Divider(
                orientation: Int = LinearLayout.VERTICAL
            )?,
            AdapterMaker.Padding(
                left: Int = 0,
                top: Int = 0,
                right: Int = 0,
                bottom: Int = 0
            )?
        )?,
        tem: ArrayList<Any>,
        layoutRes: Int
    )
    .setAdapterMakeListener { item, view, position ->
        //todo
    }
```
## Example
> `rv` is `RecyclerView`
``` Kotlin
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
```
## Preview (when clicked `L` and swipe left)

<img alt="preview" src="https://raw.githubusercontent.com/sungbin5304/RecyclerViewAdapterMaker/master/Screenshot_1590685364.png" width="33%"/>


# License
[Apache License 2.0](https://github.com/sungbin5304/RecyclerViewAdapterMaker/blob/master/LICENSE)

# Happy Coding :)
