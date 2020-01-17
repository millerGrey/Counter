package grey.counter.Calculator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import grey.counter.R
import grey.counter.databinding.FragmentCategoryBinding
import java.util.zip.Inflater

class CalculatorFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("RV","frag onCreateView")

        var v = inflater.inflate(R.layout.fragment_calculator, container, false)

        return v
    }
}