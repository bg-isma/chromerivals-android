package com.ismita.chromerivals.data.model.pedia

data class Monster(
    val monsterCode: MonsterCode? = null,
    val level: Int? = null,
    val name: String? = null,
    val hp: Long? = null,
    val speed: Long? = null,
    val range: Long? = null,
    val recoveryValue: Long? = null,
    val recoveryTime: Long? = null,
    val experience: Long? = null,
    val tier: Int? = null,
    val drop: List<DropItem>? = null
) : PediaElement()

data class MonsterCode(
    val id: Long? = null,
    val idString: String? = null
)

data class DropItem(
    val referenceItem: Item? = null,
    val minimumCount: Int? = null,
    val maximumCount: Int? = null,
    val dropProbability: Double? = null
): ExpandableListItem()