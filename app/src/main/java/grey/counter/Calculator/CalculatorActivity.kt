package grey.counter.Calculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import grey.counter.R

class CalculatorActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)
        if(savedInstanceState==null) {
            val f = CalculatorFragment()

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.calc_fragment_container,f)
                .commit()
        }
    }
}