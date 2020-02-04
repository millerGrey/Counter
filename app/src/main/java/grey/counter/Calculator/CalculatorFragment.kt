package grey.counter.Calculator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import grey.counter.CategoryListViewModel
import grey.counter.CategoryViewModel
import grey.counter.MainScreen.CategoryListAdapter
import grey.counter.R
import grey.counter.databinding.FragmentCalculatorBinding
import grey.counter.databinding.FragmentCategoryBinding
import java.util.zip.Inflater

class CalculatorFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV","Calc frag onCreateView")


        val vm = ViewModelProviders.of(requireActivity()).get(CategoryListViewModel::class.java)
        val binding = FragmentCalculatorBinding.inflate(inflater,container,false)
        val adapter = CategoryListAdapter(R.layout.item_calculator, vm)

        binding.apply{
            viewModel = vm
            calcRecycler.adapter = adapter
            calcRecycler.layoutManager = LinearLayoutManager(requireActivity())
            lifecycleOwner = requireActivity()
        }


        return binding.root
    }
}