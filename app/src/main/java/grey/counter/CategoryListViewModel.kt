package grey.counter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import grey.counter.source.Category
import grey.counter.source.local.LocalDataSource
import grey.counter.source.Note
import grey.counter.source.Repository
import java.util.*

class CategoryListViewModel(
//    private val taskDataSource: CategoryDataSource
): ViewModel() {

    private val repo = Repository()
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
        _res.value = LocalDataSource.getNote(Date()).res
    }


    fun refreshCategoryList() {
        Log.d("RV","vieModel refreshList")
        _categoryList.value = LocalDataSource.getAllCategories()
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

    fun onPressPositive(pos: Int){
        var c = LocalDataSource.getCategory(pos + 1).coast
        _res.value = _res.value?.plus(c)

        Log.d("RV","res = ${_res.value}")
    }
    fun onPressNegative(pos: Int){
        var c = LocalDataSource.getCategory(pos + 1).coast
        _res.value = _res.value?.minus(c)

        Log.d("RV","res = ${_res.value}")
    }

    fun onPressSave(){

        var note = Note()
        _res.value?.let{note.res = it}
        note.date = Date()
        LocalDataSource.editNote(note)
        Log.d("RV","save res = ${_res.value}")
    }

}