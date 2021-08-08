package com.hassan.saryassessment.flagship.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hassan.saryassessment.R
import com.hassan.saryassessment.core.presentation.utils.SnapToBlock
import com.hassan.saryassessment.flagship.domain.model.CategoryGroupModel
import com.hassan.saryassessment.flagship.domain.model.UIType
import kotlinx.android.synthetic.main.group_grid_categories.view.*
import kotlinx.android.synthetic.main.group_linear_categories.view.*
import kotlinx.android.synthetic.main.group_slider_categories.view.*


class CategoryGroupAdapter(
    private val items: ArrayList<CategoryGroupModel>
) : RecyclerView.Adapter<CategoryGroupAdapter.BaseGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseGroupViewHolder {
        return when (viewType){
            UIType.GRID.ordinal ->{
                GridGroupViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.group_grid_categories, parent, false)
                )
            }
            UIType.SLIDER.ordinal ->{
                SliderGroupViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.group_slider_categories, parent, false)
                )
            }
            else ->{
                LinearGroupViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.group_linear_categories, parent, false)
                )
            }
        }
    }

    fun addItems(items: List<CategoryGroupModel>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseGroupViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].uiType.ordinal
    }

    class GridGroupViewHolder(view: View): BaseGroupViewHolder(view){
        override fun bind(model: CategoryGroupModel) {
            if (model.showTitle == true){
                itemView.tvGridGroupTitle.visibility = View.VISIBLE
                itemView.tvGridGroupTitle.text = model.title
            } else {
                itemView.tvGridGroupTitle.visibility = View.GONE
            }

            //setup inner recycler
            itemView.rvGridCategories.adapter = CategoryAdapter(model.data, model.dataType)
            (itemView.rvGridCategories.layoutManager as GridLayoutManager).spanCount = model.rowCount
        }
    }

    class LinearGroupViewHolder(view: View): BaseGroupViewHolder(view){
        override fun bind(model: CategoryGroupModel) {
            if (model.showTitle == true){
                itemView.tvLinearGroupTitle.visibility = View.VISIBLE
                itemView.tvLinearGroupTitle.text = model.title
            } else {
                itemView.tvLinearGroupTitle.visibility = View.GONE
            }

            //setup inner recycler
            itemView.rvLinearCategories.adapter = CategoryAdapter(model.data, model.dataType)
        }
    }

    class SliderGroupViewHolder(view: View): BaseGroupViewHolder(view){
        override fun bind(model: CategoryGroupModel) {
            if (model.showTitle == true){
                itemView.tvSliderGroupTitle.visibility = View.VISIBLE
                itemView.tvSliderGroupTitle.text = model.title
            } else {
                itemView.tvSliderGroupTitle.visibility = View.GONE
            }

            //setup inner slider
            val snapHelper = SnapToBlock(1)
            snapHelper.attachToRecyclerView(itemView.categoriesSlider)
            itemView.categoriesSlider.adapter = CategoryAdapter(model.data, model.dataType, model.rowCount)
        }
    }
    abstract class BaseGroupViewHolder(view: View) : RecyclerView.ViewHolder(view){
        abstract fun bind(model: CategoryGroupModel)
    }
}