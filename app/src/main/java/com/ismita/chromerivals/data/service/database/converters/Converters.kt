package com.ismita.chromerivals.data.service.database.converters

import androidx.room.TypeConverter
import com.ismita.chromerivals.data.model.pedia.ItemCode
import com.ismita.chromerivals.data.model.pedia.MonsterCode

class Converters {


    @TypeConverter
    fun stringToItemCode(itemCodeString: String): ItemCode {
        val stringList = itemCodeString.split(" ")
        return ItemCode(stringList[0].toLong(), stringList[1])
    }

    @TypeConverter
    fun itemCodeToString(itemCode: ItemCode?): String {
        return if (itemCode != null) {
            itemCode.id.toString() + " " + itemCode.idString
        } else ""
    }

    @TypeConverter
    fun monsterCodeToString(monsterCode: MonsterCode?): String {
        return if (monsterCode != null) {
            monsterCode.id.toString() + " " + monsterCode.idString
        } else ""
    }

    @TypeConverter
    fun stringToMonsterCode(monsterCodeString: String): MonsterCode {
        val stringList = monsterCodeString.split(" ")
        return MonsterCode(stringList[0].toLong(), stringList[1])
    }

    @TypeConverter
    fun stringToMaps(mapsString: String): List<Int> {
        return mapsString.split(":").map { map -> map.toInt() }
    }

    @TypeConverter
    fun mapsToString(maps: List<Int>): String {
        return maps.map { map -> map.toString() }.joinToString(":")
    }


    /*
    @TypeConverter
    fun stringToBigInteger(iconIdString: String): BigInteger {
        return iconIdString.toBigInteger()
    }

    @TypeConverter
    fun bigIntegerToString(iconId: BigInteger): String {
        return iconId.toString()
    }
    */

}