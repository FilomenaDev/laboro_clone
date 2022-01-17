package com.filomenadeveloper.laboro_new_version.ui.drawer.venda

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.filomenadeveloper.laboro_new_version.R

class VendaFragment : Fragment() {

    private lateinit var vendaViewModel: VendaViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vendaViewModel =
            ViewModelProvider(this).get(VendaViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_venda, container, false)
        val toolbar: Toolbar = root.findViewById(R.id.toolbar_venda)

        var mRecycler = root.findViewById(R.id.recycle_venda) as RecyclerView
        val layoutManager = GridLayoutManager(context,3)
        mRecycler.layoutManager = layoutManager
        val mVenda = ArrayList<venda>()
        mVenda.add(venda(R.drawable.ic_baseline_add_box_24))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))
        mVenda.add(venda(R.color.cinza))

        val adapter = AdapterVenda(mVenda)
        mRecycler.adapter = adapter
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_venda,menu)
        val searchItem = menu.findItem(R.id.action_search)
        super.onCreateOptionsMenu(menu, inflater)
    }
}