package grey.counter.MainScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import grey.counter.CategoryListViewModel


import grey.counter.databinding.FragmentCategoryListBinding


class CategoryListFragment: Fragment() {

    private lateinit var categoryListViewModel : CategoryListViewModel

    private lateinit var viewBinding:FragmentCategoryListBinding

   override fun onResume() {
        super.onResume()
        Log.d("RV","listfrag onResume")
       Log.d("RV","resume getList1 ${categoryListViewModel.categoryList.value}")
        categoryListViewModel.refreshCat()
       Log.d("RV","resume getList2 ${categoryListViewModel.categoryList.value}")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV","listfrag onCreateView")
        viewBinding = FragmentCategoryListBinding.inflate(inflater, container, false)
        categoryListViewModel = ViewModelProviders.of(requireActivity()).get(CategoryListViewModel::class.java)

        val adapter = CategoryListAdapter(categoryListViewModel)

        viewBinding.apply{
            viewModel = categoryListViewModel
            myRecycler.adapter = adapter
            myRecycler.layoutManager = LinearLayoutManager(requireActivity())
        }

        categoryListViewModel.categoryList.observe(this, Observer {
            Log.d("RV","observe getList $it")
            adapter.refreshList(it)
        })
        return viewBinding.root
    }
}