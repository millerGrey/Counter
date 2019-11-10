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
        viewBinding = FragmentCategoryListBinding.inflate(inflater, container, false).apply {
//            viewModel = (activity as MainActivity).getVM()
        }
        categoryListViewModel = (activity as MainActivity).getVM()
        val adapter = RecyclerAdapter(categoryListViewModel)

        viewBinding.myRecycler.adapter = adapter
        viewBinding.myRecycler.layoutManager = LinearLayoutManager(activity)
        categoryListViewModel?.getListCategory()?.observe(this, Observer {
            Log.d("RV","getList ${it.toString()}")
            adapter.refresh(it)
        })

        viewBinding.fab.setOnClickListener {
            categoryListViewModel?.newCategory()
        }
        return viewBinding.root
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class RecyclerAdapter(
        val categoryListViewModel: CategoryListViewModel?
    ) : RecyclerView.Adapter<ViewHolder>() {
        private var list: List<Category> = ArrayList()
        private lateinit var itemBinding: ItemCategoryBinding
        init {
            Log.d("RV", "init Adapter ${list.size}")
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            Log.d("RV","onCreateVH")
            itemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category,
                parent,
                false
            )
            return ViewHolder(itemBinding.root)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val userActionListener = object : ItemClickListener {
                override fun onClick() {
//                    listener(position)
                    Log.d("RV","+press ${position}")
                    categoryListViewModel?.openCategory(position)
                }
            }
            Log.d("RV","onBindVH ${position} ${itemBinding.itemName.text.toString()}")
            with(itemBinding) {
                category = list[position]
                listener = userActionListener
                executePendingBindings()
            }
            Log.d("RV","onBindVH ${position} ${itemBinding.itemName.text.toString()}")
            Log.d("RV","onBindVH ${itemBinding.category}")
        }


        override fun getItemCount(): Int {
            return list.size
        }

        fun refresh(lst: List<Category>){
            Log.d("RV","oldList ${list.toString()}")
            Log.d("RV","newList ${lst.toString()}")
            list = lst
            notifyDataSetChanged()
        }
    }
}