package com.ismita.chromerivals.models.pedia

data class Item(
    val itemCode: ItemCode? = null,
    val name: String? = null,
    val iconId: Long? = null,
    val level: Int? = null,
    val kind: Int? = null,
    val gear: Int? = null,
    var drops: List<DropFromItem>? = null,
    val usedFor: List<UsedForItem>? = null,
    val mixingElements: List<MixingElementsItem>? = null,
    val itemInfo: List<String>? = null,
    var count: Int? = null
): PediaElement()

data class ItemCode(
    val id: Long? = null,
    val idString: String? = null
)

data class MixingElementsItem (
    val cost: Int? = null,
    val chance: Int? = null,
    val elements: List<MixingElementsListItem>? = null
): ExpandableListItem()

data class UsedForItem(
    val itemCode: ItemCode? = null,
    val name: String? = null,
    val iconId: Long? = null,
    val level: Int? = null,
    val kind: Int? = null,
    val gear: Int? = null
): ExpandableListItem()

data class DropFromItem(
    val referenceMonster: Monster? = null,
    val minimumCount: Int? = null,
    val maximumCount: Int? = null,
    val dropProbability: Double? = null
): ExpandableListItem()

data class MixingElementsListItem (
    val item: Item? = null,
    val count: Int? = null
)