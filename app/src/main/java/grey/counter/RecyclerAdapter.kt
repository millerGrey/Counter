package grey.counter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import grey.counter.Category.ItemClickListener
import grey.counter.databinding.ItemCategoryBinding
import grey.counter.source.Category

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

class RecyclerAdapter(
    var categoryListViewModel: CategoryListViewModel?
) : RecyclerView.Adapter<ViewHolder>() {
    private var list = emptyList<Category>().toMutableList()
    private lateinit var itemBinding: ItemCategoryBinding
    init {
        Log.d("RV", "init Adapter ${list.size}")
        list = categoryListViewModel?.getListCategory()?.value as MutableList<Category>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("RV","onCreateVH")
        itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_category, parent, false)
        return ViewHolder(itemBinding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userActionListener = object : ItemClickListener {
            override fun onClick() {
//                    listener(position)
                Log.d("RV","+press ${position}")
                categoryListViewModel?.openCategory(position)
            }
        }
        Log.d("RV","onBindVH ${position} ${itemBinding.itemName.text.toString()}")
        with(itemBinding) {
            category = list[position]
            listener = userActionListener
            executePendingBindings()
        }
        Log.d("RV","onBindVH ${position} ${itemBinding.itemName.text.toString()}")
        Log.d("RV","onBindVH ${itemBinding.category}")
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun refresh(lst: List<Category>){
        Log.d("RV","oldList ${list.toString()}")
        Log.d("RV","newList ${lst.toString()}")
//        list=lst.toMutableList()
        notifyDataSetChanged()
    }
}