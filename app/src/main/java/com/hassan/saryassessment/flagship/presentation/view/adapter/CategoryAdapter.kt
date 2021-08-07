package com.hassan.saryassessment.flagship.presentation.view.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
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
    private val dataType: DataType
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
                //To make screen fit two items at a time
                val parentParams = ViewGroup.LayoutParams(parent.measuredWidth / 2, R.dimen._80sdp.getPixelValue(parent.context))
                view.layoutParams = parentParams
                val margin = R.dimen._10sdp.getPixelValue(parent.context)
                val params = RelativeLayout.LayoutParams(parent.measuredWidth / 2 - margin, RelativeLayout.LayoutParams.MATCH_PARENT)
                params.marginEnd = margin
                params.setMargins(margin, 0,0,0)
                view.cardViewBanner.layoutParams = params
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
        override fun bind(categoryModel: CategoryUIModel) {
            Glide.with(itemView.context)
                .load(categoryModel.data.image)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                    ): Boolean {
                        if (categoryModel.bgColor == null)
                            setBackgroundFromDrawable(resource, categoryModel)
                        return false
                    }
                })
                .into(itemView.ivBannerCategoryImg)
            if (categoryModel.bgColor != null){
                categoryModel.bgColor?.let {
                    itemView.cardViewBanner.setCardBackgroundColor(it)
                }
            }
            if (categoryModel.data.name != null) {
                itemView.tvBannerCategoryText.visibility = View.VISIBLE
                itemView.tvBannerCategoryText.text = categoryModel.data.name
            } else {
                itemView.tvBannerCategoryText.visibility = View.GONE
            }
        }

        private fun setBackgroundFromDrawable(drawable: Drawable?, categoryModel: CategoryUIModel) {
            drawable?.drawableToBitmap()?.let { bitmap ->
                Palette.from(bitmap).generate {
                    val color = it?.getVibrantColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.white
                        )
                    )
                    color?.let {
                        categoryModel.bgColor = color
                        itemView.cardViewBanner.setCardBackgroundColor(color)
                    }
                }
            }
        }
    }

    abstract class BaseCategoryViewHolder(view: View) : RecyclerView.ViewHolder(view){
        abstract fun bind(model: CategoryUIModel)
    }
}