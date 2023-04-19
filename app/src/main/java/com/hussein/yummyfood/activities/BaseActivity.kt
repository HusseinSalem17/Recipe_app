package com.hussein.yummyfood.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.hussein.yummyfood.R
import com.hussein.yummyfood.adapters.FragmentAdapter
import com.hussein.yummyfood.fragments.FavoritesFragment
import com.hussein.yummyfood.fragments.HomeFragment
import com.hussein.yummyfood.fragments.RecipesFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_base.*
import android.view.WindowManager

import kotlinx.android.synthetic.main.fragment_home.*


@AndroidEntryPoint
class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)


        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        bottomBar.addTab(bottomBar.newTab().setIcon(R.drawable.ic_home_orange))
        bottomBar.addTab(bottomBar.newTab().setIcon(R.drawable.ic_favorites_grey))
        bottomBar.addTab(bottomBar.newTab().setIcon(R.drawable.ic_recipes_grey))
        val fragmentList = listOf(HomeFragment(), FavoritesFragment(), RecipesFragment())
        val fragmentManager = supportFragmentManager
        val viewPager2Adapter = FragmentAdapter(fragmentList, fragmentManager, lifecycle)
        viewPager.adapter = viewPager2Adapter
        setListeners()

    }

    private fun setListeners() {
        bottomBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.currentItem = tab!!.position
                val tabIconColor: Int =
                    ContextCompat.getColor(this@BaseActivity, R.color.colorNavSelected)
                tab.icon?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabIconColor: Int =
                    ContextCompat.getColor(this@BaseActivity, R.color.colorNavUnSelected)
                tab?.icon?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val tabIconColor: Int =
                    ContextCompat.getColor(this@BaseActivity, R.color.colorNavSelected)
                tab?.icon?.setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN)
            }
        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                bottomBar.selectTab(bottomBar.getTabAt(position))
            }
        })

        search_Btn.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }

    }


}