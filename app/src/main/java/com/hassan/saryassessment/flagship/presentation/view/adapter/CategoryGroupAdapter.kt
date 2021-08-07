package com.hassan.saryassessment.flagship.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hassan.saryassessment.R
import com.hassan.saryassessment.core.presentation.utils.SnapToBlock
import com.hassan.saryassessment.flagship.domain.model.CategoryGroupModel
import com.hassan.saryassessment.flagship.domain.model.DataType
import com.hassan.saryassessment.flagship.presentation.uimodel.CategoryUIModel
import kotlinx.android.synthetic.main.group_grid_categories.view.*
import kotlinx.android.synthetic.main.group_slider_categories.view.*


class CategoryGroupAdapter(
    private val items: ArrayList<CategoryGroupModel>
) : RecyclerView.Adapter<CategoryGroupAdapter.BaseGroupViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseGroupViewHolder {
        return when (viewType){
            DataType.SMART.ordinal ->{
                GridGroupViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.group_grid_categories, parent, false)
                        .apply {
                            setBackgroundColor(ContextCompat.getColor(parent.context, R.color.colorGrayFA))
                        }
                )
            }
            DataType.BANNER.ordinal ->{
                SliderGroupViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.group_slider_categories, parent, false)
                )
            }
            else ->{
                GridGroupViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.group_grid_categories, parent, false)
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
        return items[position].dataType.ordinal
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
            itemView.rvGridCategories.adapter = CategoryAdapter(model.data.map { CategoryUIModel(it) }, model.dataType)
            (itemView.rvGridCategories.layoutManager as GridLayoutManager).spanCount = model.rowCount
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
            itemView.categoriesSlider.adapter = CategoryAdapter(model.data.map { CategoryUIModel(it) }, model.dataType)
        }
    }
    abstract class BaseGroupViewHolder(view: View) : RecyclerView.ViewHolder(view){
        abstract fun bind(model: CategoryGroupModel)
    }
}