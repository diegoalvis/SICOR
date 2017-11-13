package com.diegoalvis.android.sicor

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter



/**
 * Created by diegoalvis on 13/11/2017.
 */

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val mFragmentList = ArrayList<Fragment>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList.get(position)
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
    }

}