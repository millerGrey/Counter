package grey.counter.MainScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TabHost
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import grey.counter.Category.CategoryActivity
import grey.counter.CategoryListViewModel
import grey.counter.R
import grey.counter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var listViewModel: CategoryListViewModel
    private lateinit var binding: ActivityMainBinding

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
            if(it==true){
                addNewCategory()
            }
        })
        listViewModel.openCategoryEvent.observe(this, Observer {
            openCategory(it)
        })
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel=listViewModel
        binding.tabs.setupWithViewPager(binding.viewPager)
        binding.lifecycleOwner =this
        binding.pageNum=binding.viewPager.currentItem
        binding.viewPager.adapter=sectionsPagerAdapter
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                binding.pageNum = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                return
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                return
            }
        })
    }

    fun addNewCategory() {
        val intent = Intent(this, CategoryActivity::class.java)
        Log.d("RV","newCat")
        listViewModel.resultHandler()
        startActivity(intent)
    }
    fun openCategory(id: Int) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("id", id)
        Log.d("RV","OpenCat putExtra ${id}")
        listViewModel.resultHandler()
        startActivity(intent)
    }
}