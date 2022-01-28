package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.SectionsPagerAdapter
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.addProducts.ActivityAddProduts
import com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.stockofproduts.StockProdutFragment
import com.google.android.material.tabs.TabLayout

class NavigationManegerProducts : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_maneger_products)
        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager,FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
        val viewPager: ViewPager = findViewById(R.id.view_pager_manege_product)

        sectionsPagerAdapter.AddFragment(ActivityAddProduts(),"Produto")
        sectionsPagerAdapter.AddFragment(StockProdutFragment(),"Stocks")
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs_manage_product)
        tabs.setupWithViewPager(viewPager)
    }

}
