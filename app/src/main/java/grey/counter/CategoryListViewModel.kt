package grey.counter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import grey.counter.source.Category
import grey.counter.source.CategoryLocalDataSource
import grey.counter.source.Note
import java.util.*

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

    private var _res = MutableLiveData<Int>(0)
    val res: LiveData<Int>
        get() = _res

    init{
       refreshCategoryList()
        _res.value = CategoryLocalDataSource.getNote(Date()).res
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
    fun openDayList(){
        _openDayListEvent.value = 1
    }

    fun onPressPositive(id: Int){
        var c = CategoryLocalDataSource.getCategory(id).coast
        _res.value = _res.value?.plus(c)

        Log.d("RV","res = ${_res.value}")
    }
    fun onPressNegative(id: Int){
        var c = CategoryLocalDataSource.getCategory(id).coast
        _res.value = _res.value?.minus(c)

        Log.d("RV","res = ${_res.value}")
    }

    fun onPressSave(){

        var note = Note()
        _res.value?.let{note.res = it}
        note.date = Date()
        CategoryLocalDataSource.editNote(note)
        Log.d("RV","save res = ${_res.value}")
    }

}