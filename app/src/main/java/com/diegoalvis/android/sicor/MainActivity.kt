package com.diegoalvis.android.sicor

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var prevMenuItem: MenuItem

    var homeFragment = HomeFragment()
    var newsFragment = NewsFragment()
    var notificationFragment = NotificationsFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewPager(viewPager)

        Log.d("Alvis", "Refreshed token: " + FirebaseInstanceId.getInstance().token)
        FirebaseMessaging.getInstance().subscribeToTopic("all")

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.navigation_home -> viewPager.currentItem = 0
                R.id.navigation_dashboard -> viewPager.currentItem = 1
                R.id.navigation_notifications -> {
                    viewPager.currentItem = 2
                    notificationFragment.setData()
                }
            }
            false
        }

        prevMenuItem = bottomNavigation.getMenu().getItem(0)


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                prevMenuItem.setChecked(false)
                bottomNavigation.getMenu().getItem(0).setChecked(false)

                bottomNavigation.getMenu().getItem(position).setChecked(true)
                prevMenuItem = bottomNavigation.getMenu().getItem(position)

                if (position == 2)
                    notificationFragment.setData()

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })


    }


    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(homeFragment)
        adapter.addFragment(newsFragment)
        adapter.addFragment(notificationFragment)

        viewPager.adapter = adapter
    }


}
