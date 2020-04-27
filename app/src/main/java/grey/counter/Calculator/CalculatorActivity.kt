package grey.counter.Calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import grey.counter.R

class CalculatorActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val date = intent.getStringExtra("date")
        Log.d("RV", "CalculatorActivity created with date = $date")
        setContentView(R.layout.activity_calculator)
        if(savedInstanceState==null) {
            val f = CalculatorFragment().newInstance(date)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.calc_fragment_container,f)
                .commit()
        }
    }
}