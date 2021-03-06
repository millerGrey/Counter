package grey.counter.Category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import grey.counter.CategoryViewModel
import grey.counter.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private val categoryViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(
            CategoryViewModel::class.java
        )
    }
    private lateinit var categoryBinding: FragmentCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RV", "frag onCreate")
        val id = arguments?.getInt(ARG_ID)
        if (id != null && id >= 0) {
            categoryViewModel.start(id)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("RV", "frag onResume")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV", "frag onCreateView")
        categoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)

        //TODO dimens for fabs on SDK<21
        with(categoryBinding) {
            viewModel = categoryViewModel
            lifecycleOwner = requireActivity()
            fabDone.setOnClickListener {
                viewModel?.saveCategory()
                requireActivity().finish()
            }
            fabDelete.setOnClickListener {
                viewModel?.deleteCategory()
                requireActivity().finish()
            }
        }
        return categoryBinding.root
    }


    fun newInstance(id: Int): CategoryFragment = CategoryFragment().apply {
        arguments = Bundle().apply {
            putInt(ARG_ID, id)
        }
        Log.d("RV", "newInstance ${id}")
    }

    companion object {

        val ARG_ID = "arg_id"

    }


}