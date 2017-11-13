package com.diegoalvis.android.sicor

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import com.diegoalvis.android.sicor.R.id.viewPager




class MainActivity : AppCompatActivity() {

    lateinit var prevMenuItem: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager(viewPager)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.navigation_home -> viewPager.currentItem = 0
                R.id.navigation_dashboard -> viewPager.currentItem = 1
                R.id.navigation_notifications -> viewPager.currentItem = 2
            }
            false
        }

        prevMenuItem = bottomNavigation.getMenu().getItem(0)


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                prevMenuItem?.setChecked(false)
                bottomNavigation.getMenu().getItem(0).setChecked(false)

                bottomNavigation.getMenu().getItem(position).setChecked(true)
                prevMenuItem = bottomNavigation.getMenu().getItem(position)
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        var homeFragment = HomeFragment()
        var newsFragment = NewsFragment()

        adapter.addFragment(newsFragment)
        adapter.addFragment(homeFragment)

        viewPager.adapter = adapter
    }


}
