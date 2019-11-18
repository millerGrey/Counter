package grey.counter

import android.util.Log
import android.view.animation.Transformation
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import grey.counter.source.Category
import grey.counter.source.CategoryLocalDataSource

class CategoryListViewModel(
//    private val taskDataSource: CategoryDataSource
): ViewModel() {

    private val _openCategoryEvent = MutableLiveData<Int>()
    val openCategoryEvent: LiveData<Int>
        get() = _openCategoryEvent

    private var _newCategoryEvent = MutableLiveData<Boolean>()
    val newCategoryEvent:  LiveData<Boolean>
        get() = _newCategoryEvent

    private var _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>>
        get() =  _categoryList

    val empty: LiveData<Boolean> = Transformations.map(_categoryList) {
       it.isEmpty()
    }


    init{
       refreshCat()
//        _categoryList.value = CategoryLocalDataSource.getAllCategories()
    }




    fun refreshCat() {
        Log.d("RV","vieModel refreshList")
        _categoryList.value = CategoryLocalDataSource.getAllCategories()
    }

    fun openCategory(id: Int){
        _openCategoryEvent.value = id
    }
    fun newCategory(){
        _newCategoryEvent.value = true
        Log.d("RV","new cat")
    }
    fun resultHandler(){
        _newCategoryEvent.value =false
    }
}