package grey.counter.MainScreen

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import grey.counter.CategoryListViewModel
import grey.counter.R
import grey.counter.source.Category

object ListBindingAdapter {

    @BindingAdapter("app:items")
    @JvmStatic fun setItems(listView: RecyclerView, items: List<Category>) {
        with(listView.adapter as CategoryListAdapter) {
            Log.d("RV","Refresh")
            refreshList(items)
        }
    }
}

object IconBindingAdapter {

    @BindingAdapter("app:icon","app:action")
    @JvmStatic fun setItems(fab: FloatingActionButton, pageNum: Int, vm: CategoryListViewModel) {
        when(pageNum){
            0->{
                fab.setImageResource(R.drawable.ic_add_black_24dp)
                fab.setOnClickListener{
                            vm.newCategory()
                }
            }
            1-> {
                fab.setImageResource(R.drawable.ic_edit_black_24dp)
                fab.setOnClickListener {
                    vm.refreshCat()
                }
            }
        }
    }
}

