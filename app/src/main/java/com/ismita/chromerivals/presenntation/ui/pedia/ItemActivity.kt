package com.ismita.chromerivals.presenntation.ui.pedia

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.ismita.chromerivals.R
import com.ismita.chromerivals.databinding.ActivityItemBinding
import com.ismita.chromerivals.databinding.ItemSubListCardBinding
import com.ismita.chromerivals.utils.Constants.ERROR_IMAGE_URL
import com.ismita.chromerivals.utils.Constants.ITEM_IMG_URL
import com.ismita.chromerivals.presenntation.adapters.ItemSubMenusCardAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import android.content.Intent
import java.io.ByteArrayOutputStream
import androidx.core.content.FileProvider
import com.ismita.chromerivals.data.model.pedia.Item
import com.ismita.chromerivals.data.model.pedia.ItemCode
import com.ismita.chromerivals.data.model.pedia.Monster
import com.ismita.chromerivals.data.model.pedia.MonsterCode
import com.ismita.chromerivals.data.model.pedia.typesEnum.PediaElementType
import com.ismita.chromerivals.utils.ChipUtils.getCustomMaterialChip
import com.ismita.chromerivals.utils.extensions.StringExtension.deleteStrangeCharacters


@AndroidEntryPoint
class ItemActivity : AppCompatActivity() {

    private val itemViewModel: ItemViewModel by viewModels()
    private lateinit var binding: ActivityItemBinding

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("ELEMENT_ID")
        intent.getSerializableExtra("ELEMENT_TYPE")?.let { pediaElementType ->
            when (pediaElementType as PediaElementType) {
                PediaElementType.MONSTER -> {
                    itemViewModel.getElementInfo(Monster(monsterCode = MonsterCode(idString = id)))
                }
                PediaElementType.ITEM -> {
                    itemViewModel.getElementInfo(Item(itemCode = ItemCode(idString = id)))
                }
            }
        }

        binding.itemBackButton.setOnClickListener { this.finish() }

        itemViewModel.pediaElement.observe(this , { pediaElement ->

            if (pediaElement != null) createPediaElementExpandableList()

            when (pediaElement) {
                is Monster -> {
                    binding.textView.text = pediaElement.name?.deleteStrangeCharacters() ?: ""
                    Glide.with(this).load(ERROR_IMAGE_URL).into(binding.itemImage)
                }
                is Item -> {
                    binding.textView.text = pediaElement.name?.deleteStrangeCharacters() ?: ""

                    if (pediaElement.itemInfo?.size != null && pediaElement.itemInfo.isNotEmpty()) {
                        val itemInfoTexts = pediaElement.itemInfo.filter { info -> !isCapacitability(info) && !info.contains("Name:") }
                        val itemCapabilities = pediaElement.itemInfo.filter { info -> isCapacitability(info) }

                        binding.textView1.text = getItemInfoDescription(itemInfoTexts)
                        createCapacitabilitiesChips(itemCapabilities)
                    }

                    Glide.with(this).load("${ITEM_IMG_URL}${pediaElement.iconId}.png")
                        .error(Glide.with(this).load(ERROR_IMAGE_URL))
                        .into(binding.itemImage)
                }
            }
        })

        binding.itemShareButton.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            val uri = FileProvider.getUriForFile(this, "com.ismita.chromerivals.provider", createFileToShare())
            this.grantUriPermission("com.instagram.android", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);

            share.setDataAndType(uri,  "image/*")
            share.putExtra(Intent.EXTRA_STREAM, uri)
            startActivity(Intent.createChooser(share, "Share to"))
        }
    }

    private fun createFileToShare(): File {
        val screenShot = Bitmap.createBitmap(binding.root.measuredWidth, binding.root.measuredHeight, Bitmap.Config.ARGB_8888)
        val byteArrayOutputStream = ByteArrayOutputStream()
        val file = File(cacheDir, binding.textView.text.toString())
        val fileOutputStream = FileOutputStream(file)

        binding.root.draw(Canvas(screenShot))
        screenShot.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        fileOutputStream.write(byteArrayOutputStream.toByteArray())

        fileOutputStream.flush()
        fileOutputStream.close()

        return file
    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun createCapacitabilitiesChips(itemCapabilities: List<String>) {
        for (capacitability  in itemCapabilities) {
            val chip = getCustomMaterialChip(
                context = this,
                text = capacitability.split(":")[0],
                bgColor = R.color.white,
                strokeColor = R.color.app_primary,
                textAppearance = R.style.element_chip_not_selected,
                strokeWidth = 1f,
                minHeight = 88f
            )
            binding.itemCapacitabilities.addView(chip)
        }
    }

    private fun getItemInfoDescription(itemInfoTexts: List<String>): String {
        return itemInfoTexts.reduce { pevInfo, info -> "$pevInfo \n$info" }.toString()
    }

    private fun isCapacitability(info: String): Boolean {
        val isCapacitability = info.contains("Enabled") || info.contains("Yes")
        val isEmpty = info.split(":")[1].replace("\\s".toRegex(), "").isEmpty()
        return isCapacitability && !isEmpty
    }


    private fun inflateExpandableList(title: String, adapter: ItemSubMenusCardAdapter, view: View) {
        val binding = ItemSubListCardBinding.bind(view)

        binding.itemSubMenuTitle.text = title
        binding.itemSubMenuList.layoutManager = LinearLayoutManager(this)
        binding.itemSubMenuList.adapter = adapter
        binding.itemSubMenuList.visibility = View.GONE

        binding.itemSubMenuCardTop.setOnClickListener {
            binding.itemSubMenuList.visibility = itemSubMenuListVisibility(binding.itemSubMenuList.visibility)
            binding.itemSubMenuBtn.rotation = itemSubMenuListRotation(binding.itemSubMenuBtn.rotation)
        }
    }

    private fun itemSubMenuListVisibility(visibility: Int): Int {
        return if (visibility == View.VISIBLE) View.GONE
        else View.VISIBLE
    }

    private fun itemSubMenuListRotation(rotation: Float): Float {
        return if (rotation == 0f) 45f
        else 0f
    }

    private fun createPediaElementExpandableList() {
        val pediaElement = itemViewModel.pediaElement.value
        when (pediaElement) {
            is Monster -> {
                val drop = pediaElement.drop
                val dropList = this.binding.dropList.root
                if (drop != null) {
                    dropList.visibility = View.VISIBLE
                    inflateExpandableList("Drops (${drop.size})", ItemSubMenusCardAdapter(drop), dropList)
                }
            }
            is Item -> {
                val usedFor = pediaElement.usedFor
                val usedForList = this.binding.usedForList.root
                if (usedFor != null) {
                    usedForList.visibility = View.VISIBLE
                    inflateExpandableList("Used For (${usedFor.size})", ItemSubMenusCardAdapter(usedFor), usedForList)
                }
                val drops = pediaElement.drops
                val dropFromList = this.binding.dropFromList.root
                if (drops != null) {
                    dropFromList.visibility = View.VISIBLE
                    inflateExpandableList("Drops  (${drops.size})", ItemSubMenusCardAdapter(drops), dropFromList)
                }
                val mixingElements = pediaElement.mixingElements
                val mixFromList = this.binding.mixFromList.root
                if (mixingElements != null) {
                    mixFromList.visibility = View.VISIBLE
                    inflateExpandableList("Mix (${mixingElements.size})", ItemSubMenusCardAdapter(pediaElement.mixingElements), mixFromList)
                }
            }
            else -> {}
        }
    }

}