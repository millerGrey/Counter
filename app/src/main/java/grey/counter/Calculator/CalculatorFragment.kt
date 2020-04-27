package grey.counter.Calculator

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
import grey.counter.MainScreen.CategoryListAdapter
import grey.counter.NoteViewModel
import grey.counter.R
import grey.counter.databinding.FragmentCalculatorBinding

class CalculatorFragment: Fragment() {

    private val noteVM  by lazy{ViewModelProviders.of(requireActivity()).get(NoteViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RV","CalculatorFragment onCreate")
        val date =arguments?.getString(ARG_DATE)
        date?.let{
            noteVM.start(date)
        }
        noteVM.onSaveEvent.observe(this, Observer{
            if(it==true){
                requireActivity().finish()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV","CalculatorFragment onCreateView")


        val listVM = ViewModelProviders.of(requireActivity()).get(CategoryListViewModel::class.java)

        val binding = FragmentCalculatorBinding.inflate(inflater,container,false)
        val adapter = CategoryListAdapter(R.layout.item_calculator, listVM, noteVM)
        //TODO settings for textView width
        binding.apply{
            listViewModel = listVM
            noteViewModel = noteVM
            calcRecycler.adapter = adapter
            calcRecycler.layoutManager = LinearLayoutManager(requireActivity())
            lifecycleOwner = requireActivity()
        }


        return binding.root
    }

    fun newInstance(date: String): CalculatorFragment = CalculatorFragment().apply{
        arguments = Bundle().apply{
            putString(ARG_DATE,date)
        }
        Log.d("RV","CalculatorFragment newInstance with $date")
    }
    companion object {

        val ARG_DATE = "arg_date"

    }
}