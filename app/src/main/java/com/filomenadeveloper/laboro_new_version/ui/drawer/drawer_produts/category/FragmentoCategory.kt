package com.filomenadeveloper.laboro_new_version.ui.drawer.drawer_produts.category

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.filomenadeveloper.laboro_new_version.R
import com.filomenadeveloper.laboro_new_version.Status
import com.filomenadeveloper.laboro_new_version.ui.adapters.AdapterCategory
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentoCategory : Fragment() {

    private lateinit var root: View
    private lateinit var ButtonAddCategory: AppCompatImageButton
    private val categoryoOfProdutView : ViewModelCategory by viewModel()

    private lateinit var adapterCategories: AdapterCategory
    private lateinit var recyclerviewCategory: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_category, container, false)

        ButtonAddCategory = root.findViewById(R.id.image_button_add_category)
        recyclerviewCategory = root.findViewById(R.id.recycle_category)

        recyclerviewCategory.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        ButtonAddCategory.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    ActivityAddCategory ::class.java
                )
            )
        }

        ListOfCategories()
        return root
    }

    private fun ListOfCategories(){
        categoryoOfProdutView.getCategories().observe(viewLifecycleOwner,{
            it.let { resource ->
                when(resource.status) {
                    Status.LOADING ->{}
                        Status.SUCCESS -> {
                        resource.data?.let { response ->
                            adapterCategories = AdapterCategory(response)
                            recyclerviewCategory.adapter = adapterCategories

                        }
                    }
                }
            }
        })
    }

}