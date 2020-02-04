package grey.counter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import grey.counter.source.Category
import grey.counter.source.CategoryLocalDataSource

class CategoryListViewModel(
//    private val taskDataSource: CategoryDataSource
): ViewModel() {

    private var _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>>
        get() =  _categoryList

    private val _openCategoryEvent = MutableLiveData<Int>()
    val openCategoryEvent: LiveData<Int>
        get() = _openCategoryEvent

    private var _newCategoryEvent = MutableLiveData<Boolean>()
    val newCategoryEvent:  LiveData<Boolean>
        get() = _newCategoryEvent

    private var _openDayListEvent = MutableLiveData<Int>()
    val openDayListEvent: LiveData<Int>
        get() =  _openDayListEvent

    val isEmpty: LiveData<Boolean> = Transformations.map(_categoryList) {
       it.isEmpty()
    }


    init{
       refreshCategoryList()
//        _categoryList.value = CategoryLocalDataSource.getAllCategories()
    }


    fun refreshCategoryList() {
        Log.d("RV","vieModel refreshList")
        _categoryList.value = CategoryLocalDataSource.getAllCategories()
    }

    fun openCategory(id: Int){
        _openCategoryEvent.value = id
    }
    fun createCategory(){
        _newCategoryEvent.value = true
        Log.d("RV","new cat")
    }
//    fun resultHandler(){
//        _newCategoryEvent.value =false
//    }
    fun openDayList(){
        _openDayListEvent.value = 1
    }
}