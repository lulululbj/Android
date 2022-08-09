package luyao.android.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import luyao.android.R
import luyao.android.model.Menu

/**
 * Description:
 * Author: luyao
 * Date: 2022/8/5 16:41
 */
class MenuAdapter : ListAdapter<Menu, MenuAdapter.MenuViewHolder>(MenuDiffCallback()) {

    private var itemClickListener: ((Menu) -> Unit)? = null

    fun setItemClickListener(itemClickListener: ((Menu) -> Unit)?) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val button = holder.itemView as AppCompatButton
        val item = getItem(position)
        button.text = item.name
        button.setOnClickListener {
            itemClickListener?.invoke(item)
        }
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    class MenuDiffCallback : DiffUtil.ItemCallback<Menu>() {
        override fun areItemsTheSame(oldItem: Menu, newItem: Menu) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Menu, newItem: Menu) = oldItem.name == newItem.name

    }
}