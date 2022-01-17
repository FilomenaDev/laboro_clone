package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter





class SectionsPagerAdapter(fm: FragmentManager, behavior: Int) :
    FragmentPagerAdapter(fm, behavior) {

    val fragmentArrayList = ArrayList<Fragment>()
    val framentTitle = ArrayList<String>()

    override fun getCount(): Int {
        return fragmentArrayList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentArrayList.get(position)
    }

    fun AddFragment(fragment: Fragment?, title: String?) {
        fragmentArrayList.add(fragment!!)
        framentTitle.add(title!!)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return framentTitle.get(position)
    }

}