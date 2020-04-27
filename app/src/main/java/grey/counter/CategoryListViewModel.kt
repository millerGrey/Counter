package grey.counter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import grey.counter.source.Category
import grey.counter.source.Repository
import grey.counter.source.local.LocalDataSource

class CategoryListViewModel(
//    private val taskDataSource: CategoryDataSource
) : ViewModel() {

    private val repo = Repository()
    private var _categoryList = MutableLiveData<List<Category>>()
    val categoryList: LiveData<List<Category>>
        get() = _categoryList

    private val _openCategoryEvent = MutableLiveData<Int>()
    val openCategoryEvent: LiveData<Int>
        get() = _openCategoryEvent

    private var _newCategoryEvent = MutableLiveData<Boolean>()
    val newCategoryEvent: LiveData<Boolean>
        get() = _newCategoryEvent

    private var _openDayListEvent = MutableLiveData<String>()
    val openDayListEvent: LiveData<String>
        get() = _openDayListEvent

    val isEmpty: LiveData<Boolean> = Transformations.map(_categoryList) {
        it.isEmpty()
    }

    private var _date = MutableLiveData<String>(" - ")
    val date: LiveData<String>
        get() = _date

    init {
        Log.d("RV", "categoryListVieModel init")
        refreshCategoryList()
    }


    fun refreshCategoryList() {
        _categoryList.value = LocalDataSource.getAllCategories()
        Log.d("RV", "viewModel refreshList ${categoryList.value}")
    }

    fun openCategory(id: Int) {
        _openCategoryEvent.value = id
    }

    fun createCategory() {
        _newCategoryEvent.value = true
        Log.d("RV", "new cat")
    }

    fun openDayList() {
        _openDayListEvent.value = _date.value
    }

    fun setDate(date: String) {
        _date.value = date
        Log.d("RV", "date = ${_date.value}")
    }

}