package com.ismita.chromerivals.presenntation.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.SearchCardBinding
import com.ismita.chromerivals.data.model.pedia.Item
import com.ismita.chromerivals.data.model.pedia.Monster
import com.ismita.chromerivals.data.model.pedia.PediaElement
import com.ismita.chromerivals.data.model.pedia.typesEnum.PediaElementType
import com.ismita.chromerivals.utils.Constants.ERROR_IMAGE_URL
import com.ismita.chromerivals.utils.Constants.ITEM_IMG_URL
import com.ismita.chromerivals.utils.extensions.StringExtension.deleteStrangeCharacters
import com.ismita.chromerivals.presenntation.ui.pedia.ItemActivity

class SearchAdapter() : RecyclerView.Adapter<SearchViewHolder>() {

    private var pediaElements = emptyList<PediaElement>()

    fun setData(newPediaElements: List<PediaElement>) {
        pediaElements = newPediaElements
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return pediaElements.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        return holder.bind(pediaElements[position])
    }
}

// Initialization
class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(pediaElement: PediaElement) = with(itemView) {

        val binding = SearchCardBinding.bind(itemView)
        when (pediaElement) {
            is Monster -> {
                Glide.with(context).asBitmap().load(ERROR_IMAGE_URL).into(binding.itemImage)
                this.setOnClickListener {
                    openPediaElement(pediaElement.monsterCode?.idString.toString(), PediaElementType.MONSTER)
                }
                binding.itemName.text = pediaElement.name?.deleteStrangeCharacters() ?: ""
            }
            is Item -> {
                Glide.with(context).asBitmap().load("${ITEM_IMG_URL}${pediaElement.iconId}.png")
                    .error(Glide.with(context).asBitmap().load(ERROR_IMAGE_URL))
                    .into(binding.itemImage)
                this.setOnClickListener {
                    openPediaElement(pediaElement.itemCode?.idString.toString(), PediaElementType.ITEM)
                }
                binding.itemName.text = pediaElement.name?.deleteStrangeCharacters() ?: ""
            }
        }

    }

    private fun openPediaElement(id: String, type: PediaElementType) {
        val intent = Intent(itemView.context, ItemActivity::class.java)
        intent.putExtra("ELEMENT_TYPE", type)
        intent.putExtra("ELEMENT_ID", id)
        itemView.context.startActivity(intent)
    }
}