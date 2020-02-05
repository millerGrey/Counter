package grey.counter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grey.counter.source.Category
import grey.counter.source.local.LocalDataSource

class CategoryViewModel: ViewModel() {


    private var _category: MutableLiveData<Category> = MutableLiveData()
    val category : LiveData<Category>
        get() = _category

    val name: MutableLiveData<String> = MutableLiveData()
//    val name : LiveData<String>
//        get() = _name


    val coast: MutableLiveData<String> = MutableLiveData()


    fun start(id: Int){
        if(id>=0) {
            showCategory(id)
        }else{

            return
        }
    }

    private fun showCategory(id: Int){
        _category.value= LocalDataSource.getCategory(id)
        name.value = category.value?.name
        coast.value = category.value?.coast.toString()
    }

    fun saveCategory(){

        if(category.value!=null) {
            category.value?.name = name.value.toString()
            category.value?.coast = Integer.parseInt(coast.value!!)
            Log.d("RV","edit ${category.value?.id}")
            LocalDataSource.editCategory(category.value!!)
        }else{
            val id = LocalDataSource.getAllCategories().size
            _category.value = Category(id)
            category.value?.name = name.value?:""
            category.value?.coast = Integer.parseInt(coast.value?:"0")
            Log.d("RV","add ${category.value?.id}")
            LocalDataSource.addCategory(category.value!!)
        }
        showCategory(category.value!!.id!!)
    }
    fun deleteCategory(){

        LocalDataSource.deleteCategory(category.value!!)
    }

}