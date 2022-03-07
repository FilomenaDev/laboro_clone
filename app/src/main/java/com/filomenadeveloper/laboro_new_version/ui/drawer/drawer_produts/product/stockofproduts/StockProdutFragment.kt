package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.product.stockofproduts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.filomenadeveloper.laboro_new_version.R

/**
 * A placeholder fragment containing a simple view.
 */
class StockProdutFragment : Fragment() {

    private lateinit var root: View



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_manage_stock, container, false)

        return root
    }

}