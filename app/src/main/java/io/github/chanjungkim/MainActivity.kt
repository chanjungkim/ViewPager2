package io.github.chanjungkim

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    val mArray by lazy {
        resources.getStringArray(R.array.my_colors)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mViewPager2 = findViewById<ViewPager2>(R.id.vp)
        val mAdapter = MyAdapter(mArray.toList())
        mViewPager2.adapter = mAdapter
        mViewPager2.clipToPadding = false
        mViewPager2.clipChildren = false
        mViewPager2.offscreenPageLimit = 3
        mViewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        mViewPager2.getChildAt(0).overScrollMode = ViewPager2.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(80))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - Math.abs(position)
            page.scaleX = 0.85f + r * 0.15f
        }

        mViewPager2.setPageTransformer(compositePageTransformer)

        mViewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mAdapter.focus(position)
                Log.d("cnt", "${mAdapter.getItemCount()}")
                Log.d("cnt", "left: ${mAdapter.itemCount - position}")
                if(mAdapter.itemCount - position == 2){
                    mAdapter.addMore(mArray.toList())
                    Log.d("cnt", "after add: ${mAdapter.getItemCount()}")
                }
            }
        })
    }
}