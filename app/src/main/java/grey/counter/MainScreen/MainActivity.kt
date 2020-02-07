package grey.counter.MainScreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import grey.counter.Calculator.CalculatorActivity
import grey.counter.Category.CategoryActivity
import grey.counter.CategoryListViewModel
import grey.counter.R
import grey.counter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var categoryListVM: CategoryListViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onPause() {
        super.onPause()
        Log.d("RV"," MainActivity onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d("RV"," MainActivity onResume")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RV"," MainActivity onCreate")
        categoryListVM = ViewModelProviders.of(this).get(CategoryListViewModel::class.java)
        categoryListVM.newCategoryEvent.observe(this, Observer{
            if(it==true){
                addNewCategory()
            }
        })
        categoryListVM.openCategoryEvent.observe(this, Observer {
            openCategory(it)
        })
        categoryListVM.openDayListEvent.observe(this, Observer {
            openDayList(it)
        })
        val sectionsPagerAdapter =
            SectionsPagerAdapter(this, supportFragmentManager)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        with(binding){
            viewModel = categoryListVM
            tabs.setupWithViewPager(viewPager)
            pageNum = viewPager.currentItem
            viewPager.adapter = sectionsPagerAdapter
        }

        binding.lifecycleOwner = this
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
        startActivity(intent)
    }
    fun openCategory(id: Int) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("id", id)
        Log.d("RV","OpenCat putExtra ${id}")
        startActivity(intent)
    }
    fun openDayList(date: String){
        val intent = Intent(this,CalculatorActivity::class.java).putExtra("date", date)
        startActivity(intent)
    }


}