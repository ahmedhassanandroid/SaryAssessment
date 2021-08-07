package com.hassan.saryassessment.flagship.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hassan.saryassessment.R
import com.hassan.saryassessment.core.presentation.utils.getDpValue
import com.hassan.saryassessment.flagship.domain.model.BannerModel
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_banner.view.*

class BannersAdapter(
    private val items: ArrayList<BannerModel>,
    private val onItemClick: (model: BannerModel) -> Unit
): SliderViewAdapter<BannersAdapter.BannerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup): BannerHolder {
        return BannerHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
        )
    }

    override fun getCount(): Int = items.size

    override fun onBindViewHolder(holder: BannerHolder, position: Int) {
        holder.bind(items[position])
    }

    fun addItems(items: List<BannerModel>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }
    inner class BannerHolder(view: View) : ViewHolder(view){
        fun bind(model: BannerModel){
            val radius = 24f.getDpValue(itemView.context)
            Glide.with(itemView.context)
                .load(model.image)
                .fitCenter()
                .apply(RequestOptions().transform(RoundedCorners(radius.toInt())))
                .into(itemView.ivBannerImage)

            itemView.setOnClickListener {
                onItemClick(model)
            }
        }

    }
}