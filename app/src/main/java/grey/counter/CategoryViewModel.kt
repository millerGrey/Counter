package grey.counter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import grey.counter.source.Category
import grey.counter.source.CategoryLocalDataSource

class CategoryViewModel: ViewModel() {


    private var _category: MutableLiveData<Category> = MutableLiveData()
    val category : LiveData<Category>
        get() = _category

    val name: MutableLiveData<String> = MutableLiveData()
//    val name : LiveData<String>
//        get() = _name


    val coast: MutableLiveData<String> = MutableLiveData()


    fun start(id: Int){
        if(id!=0) {
            updateCategory(id)
        }else{

            return
        }
    }

    private fun updateCategory(id: Int){
        _category.value=CategoryLocalDataSource.getCategory(id)
        name.value = category.value?.name
        coast.value = category.value?.coast.toString()
    }

    fun addNewCategory(cat: Category){
        CategoryLocalDataSource.addCategory(cat)
    }
    fun editCategory(cat: Category){

    }
    fun saveCategory(){

        if(category.value!=null) {
            category.value?.name = name.value.toString()
            category.value?.coast = Integer.parseInt(coast.value.toString())
            CategoryLocalDataSource.editCategory(category.value!!)
        }else{
            val id = CategoryLocalDataSource.getAllCategories().size
            _category.value = Category(id)
            category.value?.name = name.value?:""
            category.value?.coast = Integer.parseInt(coast.value?:"0")
            CategoryLocalDataSource.addCategory(category.value!!)
        }
        updateCategory(category.value!!.id!!)
    }

}