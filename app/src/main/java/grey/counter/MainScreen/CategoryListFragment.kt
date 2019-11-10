package grey.counter.MainScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grey.counter.source.Category
import grey.counter.Category.CategoryActivity
import grey.counter.Category.ItemClickListener
import grey.counter.CategoryListViewModel


import grey.counter.R
import grey.counter.databinding.FragmentCategoryListBinding
import grey.counter.databinding.ItemCategoryBinding
import androidx.databinding.BindingAdapter
import grey.counter.CategoryListBindings
import grey.counter.RecyclerAdapter
import java.util.ArrayList


class CategoryListFragment: Fragment() {

    private lateinit var categoryListViewModel : CategoryListViewModel

    private lateinit var viewBinding:FragmentCategoryListBinding

    override fun onResume() {
        super.onResume()
        Log.d("RV","listfrag onResume")
        categoryListViewModel?.refreshCat()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV","listfrag onCreateView")

        val listBinding = FragmentCategoryListBinding.inflate(inflater, container, false)
        categoryListViewModel = (activity as MainActivity).getVM()
        listBinding.vm = categoryListViewModel
        listBinding.myRecycler.adapter = RecyclerAdapter(listBinding.vm)
        listBinding.myRecycler.layoutManager = LinearLayoutManager(activity)

        listBinding.fab.setOnClickListener(){
            categoryListViewModel?.newCategory()
        }


        return listBinding.root
    }

}