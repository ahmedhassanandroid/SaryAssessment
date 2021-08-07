package com.hassan.saryassessment.flagship.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.hassan.saryassessment.R
import com.hassan.saryassessment.core.presentation.utils.drawableToBitmap
import com.hassan.saryassessment.core.presentation.utils.getPixelValue
import com.hassan.saryassessment.flagship.domain.model.DataType
import com.hassan.saryassessment.flagship.presentation.uimodel.CategoryUIModel
import kotlinx.android.synthetic.main.item_banner_category.view.*
import kotlinx.android.synthetic.main.item_group_category.view.*
import kotlinx.android.synthetic.main.item_smart_category.view.*


class CategoryAdapter(
    private val items: List<CategoryUIModel>,
    private val dataType: DataType,
    private val rowCount: Int = 0
): RecyclerView.Adapter<CategoryAdapter.BaseCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseCategoryViewHolder {
        return when (dataType){
            DataType.SMART ->
                SmartCategoryViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_smart_category, parent, false)
                )
            DataType.GROUP ->
                GroupCategoryViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_group_category, parent, false)
                )
            DataType.BANNER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_banner_category, parent, false)
                if(rowCount != 0){
                    //To make screen fit two items at a time
                    val parentParams = ViewGroup.LayoutParams(parent.measuredWidth / rowCount, R.dimen._80sdp.getPixelValue(parent.context))
                    view.layoutParams = parentParams
                    val margin = R.dimen._10sdp.getPixelValue(parent.context)
                    val params = RelativeLayout.LayoutParams(parent.measuredWidth / rowCount - margin, RelativeLayout.LayoutParams.MATCH_PARENT)
                    params.marginEnd = margin
                    params.setMargins(margin, 0,0,0)
                    view.cardViewBanner.layoutParams = params
                }
                BannerCategoryViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseCategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class SmartCategoryViewHolder(view: View) : BaseCategoryViewHolder(view){
        override fun bind(model: CategoryUIModel) {
            Glide.with(itemView.context)
                .load(model.data.image)
                .into(itemView.ivSmartCategoryImg)

            itemView.tvSmartCategoryImg.text = model.data.name
        }

    }

    class GroupCategoryViewHolder(view: View) : BaseCategoryViewHolder(view){
        override fun bind(model: CategoryUIModel) {
            Glide.with(itemView.context)
                .load(model.data.image)
                .into(itemView.ivGroupCategoryImg)
        }

    }

    class BannerCategoryViewHolder(view: View) : BaseCategoryViewHolder(view) {
        override fun bind(model: CategoryUIModel) {
            Glide.with(itemView.context)
                .load(model.data.image)
                .into(itemView.ivBannerCategoryImg)
        }
    }

    abstract class BaseCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        abstract fun bind(model: CategoryUIModel)
    }
}