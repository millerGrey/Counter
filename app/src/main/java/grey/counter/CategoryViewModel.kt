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
    val coast: MutableLiveData<String> = MutableLiveData()


    fun start(id: Int){
        if(id >= 0) {
            showCategory(id)
        }else{

            return
        }
    }

    private fun showCategory(id: Int){
        _category.value = LocalDataSource.getCategory(id)
        name.value = category.value?.name
        coast.value = category.value?.coast.toString()
    }

    fun saveCategory(){
        _category.value?.let {
            it.name = name.value.toString()
            it.coast = Integer.parseInt(coast.value!!)
            Log.d("RV","edit ${it.id}")
            LocalDataSource.editCategory(it)
        }?:let{
            val categoryes = LocalDataSource.getAllCategories()
//            val id = categoryes[categoryes.size - 1].id + 1
            val cat = Category()
            cat.name = name.value?:""
            cat.coast = Integer.parseInt(coast.value?:"0")
            _category.value = cat
            Log.d("RV","add ${category.value?.id}")
            LocalDataSource.addCategory(category.value!!)
        }
        showCategory(category.value!!.id)
    }
    fun deleteCategory(){
        LocalDataSource.deleteCategory(category.value!!)
    }

}