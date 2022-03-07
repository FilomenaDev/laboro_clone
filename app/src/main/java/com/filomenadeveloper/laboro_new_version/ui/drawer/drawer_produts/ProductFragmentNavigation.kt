package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.category.FragmentoCategory
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.FragmentProdut
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.stockofproduts.StockProdutFragment
import com.google.android.material.tabs.TabLayout

class ProductFragmentNavigation : Fragment() {

    @SuppressLint("UseRequireInsteadOfGet", "ResourceAsColor")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.activity_main, container, false)
        val sectionsPagerAdapter = SectionsPagerAdapter(fragmentManager!!,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val viewPager: ViewPager = root.findViewById(R.id.view_pager)
        sectionsPagerAdapter.AddFragment(FragmentProdut(),"Itens")
        sectionsPagerAdapter.AddFragment(StockProdutFragment(),"Estoque")
        sectionsPagerAdapter.AddFragment(FragmentoCategory(),"Categoria")
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = root.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val tab =tabs.getTabAt(0)
        val badge = tab?.orCreateBadge
        badge!!.number =122


        return root
    }
}