package grey.counter.Category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import grey.counter.CategoryViewModel
import grey.counter.R
import grey.counter.databinding.FragmentCategoryBinding
import grey.counter.source.Category
import kotlinx.android.synthetic.main.fragment_category.view.*

class CategoryFragment: Fragment() {

    private val categoryViewModel  by lazy{ ViewModelProviders.of(this).get(CategoryViewModel::class.java) }
    private lateinit var categoryBinding: FragmentCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RV","frag onCreate")

        if(arguments?.getInt(ARG_ID)!=null) {
            categoryViewModel?.start(arguments?.getInt(ARG_ID)!!)
        }else{
            categoryViewModel?.start(0)
        }


    }

    override fun onResume() {
        super.onResume()
        Log.d("RV","frag onResume")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV","frag onCreateView")
        categoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        categoryBinding.viewModel = categoryViewModel

        categoryBinding.fabDone.setOnClickListener{

            categoryBinding.viewModel?.saveCategory()
        }
        return categoryBinding.root
    }


    fun newInstance(id: Int):CategoryFragment=CategoryFragment().apply{
        arguments = Bundle().apply{
            putInt(ARG_ID,id)
        }
        Log.d("RV","newInstance ${id}")
    }
companion object {

    val ARG_ID = "arg_id"

}


}