package grey.counter.Category

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import grey.counter.R

class CategoryActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val id = intent.getIntExtra("id",-1)
        Log.d("RV","getExtra ${id}")
        if(savedInstanceState==null) {
            val f = CategoryFragment().newInstance(id)

            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,f)
                .commit()
        }

    }


}

