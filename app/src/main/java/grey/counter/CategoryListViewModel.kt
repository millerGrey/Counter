package grey.counter

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import grey.counter.Category.CategoryActivity
import grey.counter.source.Category
import grey.counter.source.CategoryLocalDataSource

class CategoryListViewModel(
//    private val taskDataSource: CategoryDataSource
): ViewModel() {

    private val _openCategoryEvent = MutableLiveData<Int>()
    val openCategoryEvent: LiveData<Int>
        get() = _openCategoryEvent

    private var _newCategoryEvent = MutableLiveData<Int>()
    val newCategoryEvent:  LiveData<Int>
        get() = _newCategoryEvent



    var categoryList: MutableLiveData<List<Category>> = MutableLiveData()

    init{
       refreshCat()
    }
    fun getListCategory() = categoryList



    fun refreshCat() {
        categoryList.value = CategoryLocalDataSource.getAllCategories()
    }

    fun openCategory(id: Int){
        _openCategoryEvent.value = id
    }
    fun newCategory(){
        _newCategoryEvent.value = (categoryList.value?.size)?.plus(1)
        Log.d("RV","new cat")
    }
}