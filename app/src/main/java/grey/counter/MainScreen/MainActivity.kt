package grey.counter.MainScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import grey.counter.Category.CategoryActivity
import grey.counter.CategoryListViewModel
import grey.counter.R


class MainActivity : AppCompatActivity() {

    private lateinit var listViewModel: CategoryListViewModel


    override fun onPause() {
        super.onPause()
        Log.d("RV"," activity onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RV"," activity onResume")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RV"," activity onCreate")
        listViewModel = ViewModelProviders.of(this).get(CategoryListViewModel::class.java)
        listViewModel.newCategoryEvent.observe(this, Observer{
            if(it!=null){
                addNewCategory()
            }
        })
        listViewModel.openCategoryEvent.observe(this, Observer {
            openCategory(it)
        })
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
    }

    fun addNewCategory() {
        val intent = Intent(this, CategoryActivity::class.java)
        Log.d("RV","newCat")
        startActivity(intent)
    }
    fun openCategory(id: Int) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("id", id)
        Log.d("RV","OpenCat putExtra ${id}")
        startActivity(intent)
    }
}