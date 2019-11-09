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

//    private val categoryListViewModel  by lazy{ ViewModelProviders.of(this).get(CategoryListViewModel::class.java) }

    private lateinit var viewBinding:FragmentCategoryListBinding

    override fun onResume() {
        super.onResume()
        viewBinding.viewModel?.refreshCat()
        Log.d("RV","onResume")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewBinding = FragmentCategoryListBinding.inflate(inflater, container, false).apply {
            viewModel = (activity as MainActivity).getVM()
        }

        val adapter = RecyclerAdapter(::openCategory, viewBinding.viewModel)

        viewBinding.myRecycler.adapter = adapter
        viewBinding.myRecycler.layoutManager = LinearLayoutManager(activity)
        viewBinding.viewModel?.getListCategory()?.observe(this, Observer {
            adapter.refresh(it)
        })

        viewBinding.fab.setOnClickListener {
            viewBinding.viewModel?.newCategory()
        }
        return viewBinding.root
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class RecyclerAdapter(
        val listener: (Int) -> Unit,
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
            Log.d("RV","onBindVH")
            val userActionListener = object : ItemClickListener {
                override fun onClick() {
//                    listener(position)
                    categoryListViewModel?.openCategory(position+1)
                }
            }
            with(itemBinding) {
                category = list[position]
                listener = userActionListener
                executePendingBindings()
            }
        }


        override fun getItemCount(): Int {
//            Log.d("RV","getItemCount ${list.size}")
            return list.size
        }

        fun refresh(list: List<Category>){
//            Log.d("RV","refresh ${list.size}")
            this.list = list

            notifyDataSetChanged()
        }
    }

    fun openCategory(pos: Int){
        val intent = Intent(activity, CategoryActivity::class.java)
        startActivity(intent)
    }

}