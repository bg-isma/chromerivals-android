package com.ismita.chromerivals.views.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.ItemSubListCardRowBinding
import com.ismita.chromerivals.models.pedia.*
import com.ismita.chromerivals.utils.extensions.StringExtension.deleteStrangeCharacters

class ItemSubMenusCardAdapter(private var subMenusCardItems: List<ExpandableListItem>) : RecyclerView.Adapter<ItemSubMenusCardHolder>() {
    override fun getItemCount(): Int {
        return subMenusCardItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSubMenusCardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sub_list_card_row, parent, false)
        return ItemSubMenusCardHolder(view)
    }

    override fun onBindViewHolder(holder: ItemSubMenusCardHolder, position: Int) {
        return holder.bind(subMenusCardItems[position])
    }
}

// Initialization
class ItemSubMenusCardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    @SuppressLint("SetTextI18n")
    fun bind(item: ExpandableListItem) = with(itemView) {
        val binding = ItemSubListCardRowBinding.bind(itemView)
        f(item, binding, context)
    }

    private fun loadImage(iconId: Long, index: Int, context: Context, binding: ItemSubListCardRowBinding) {
        Glide.with(context).asBitmap().load("https://download.chromerivals.net/resources/items_images/standard/${iconId}.png")
            .error(Glide.with(context).asBitmap().load("https://chromerivals.net/assets/images/icons/item_x.png"))
            .into(getImageViewToBinding(binding, index))
    }

    private fun getImageViewToBinding(binding: ItemSubListCardRowBinding, index: Int): ImageView {
        when (index) {
            1 -> return binding.object1Img
            2 -> return binding.object2Img
            3 -> return binding.object3Img
            4 -> return binding.object4Img
            5 -> return binding.object5Img
            6 -> return binding.dropObjectImg
        }
        return binding.object1Img
    }

    private fun f (item: ExpandableListItem, binding: ItemSubListCardRowBinding, context: Context) {
        when (item) {
            is DropItem -> {
                binding.usedForRow.visibility = View.GONE
                binding.dropFromRow.visibility = View.GONE
                binding.obtainedFromRow.visibility = View.GONE

                binding.dropItemDropProb.text = item.dropProbability.toString()
                binding.dropItemLvl.text = item.referenceItem?.level.toString()
                binding.dropItemName.text = item.referenceItem?.name.toString().deleteStrangeCharacters()
                item.referenceItem?.iconId?.let { loadImage(it, 6, context, binding) }
            }
            is MixingElementsItem -> {
                binding.dropsRow.visibility = View.GONE
                binding.dropFromRow.visibility = View.GONE
                binding.usedForRow.visibility = View.GONE

                item.elements.let { elements ->
                    if (elements != null) {
                        for (index in 0..4) {
                            if (index < elements.size) {
                                elements[index].item?.iconId?.let { iconId -> loadImage(iconId, index + 1, context, binding) }
                            }
                        }
                    }
                }
            }
            is DropFromItem -> {
                binding.usedForRow.visibility = View.GONE
                binding.dropsRow.visibility = View.GONE
                binding.obtainedFromRow.visibility = View.GONE

                binding.monsterName.text = item.referenceMonster?.name.toString().deleteStrangeCharacters()
                binding.monsterLevel.text = item.referenceMonster?.level.toString()
                binding.dropfromSucessPercent.text = item.dropProbability.toString() + "%"
            }
            is UsedForItem -> {
                binding.dropsRow.visibility = View.GONE
                binding.dropFromRow.visibility = View.GONE
                binding.obtainedFromRow.visibility = View.GONE

                binding.objectName.text = item.name?.deleteStrangeCharacters() ?: ""
                binding.agearImg.setImageResource(R.drawable.ic_agear)
                binding.bgearImg.setImageResource(R.drawable.ic_bgear)
                binding.mgearImg.setImageResource(R.drawable.ic_mgear)
                binding.igearImg.setImageResource(R.drawable.ic_igear)
            }
        }
    }

}