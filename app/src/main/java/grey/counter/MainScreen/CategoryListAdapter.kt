package grey.counter.MainScreen

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import grey.counter.Category.ItemClickListener
import grey.counter.CategoryListViewModel
import grey.counter.databinding.ItemCategoryBinding
import grey.counter.source.Category

class CategoryListAdapter(
    private val categoryListViewModel: CategoryListViewModel?
    ) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
    private var list: List<Category> = emptyList()
    private lateinit var itemBinding: ItemCategoryBinding
    init {
        Log.d("RV", "init Adapter ${list.size}")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("RV","onCreateVH")
        itemBinding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d("RV","onBindVH ${position} ${itemBinding.itemName.text}")
        holder.bind(list[position], categoryListViewModel!!)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun refreshList(lst: List<Category>){
        Log.d("RV","oldList $list")
        Log.d("RV","newList $lst")
        list = lst
        notifyDataSetChanged()
    }


    class ViewHolder(private var binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: Category, viewModel: CategoryListViewModel) {
            val userActionListener = object : ItemClickListener {
                override fun onClick() {
                    Log.d("RV", "+press ${layoutPosition}")
                    viewModel?.openCategory(layoutPosition)
                }
            }
            with(binding) {
                category = list
                listener = userActionListener
                executePendingBindings()
            }
        }
    }
}
