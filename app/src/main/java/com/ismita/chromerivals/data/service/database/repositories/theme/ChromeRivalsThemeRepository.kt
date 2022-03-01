package com.ismita.chromerivals.data.service.database.repositories.theme

import com.ismita.chromerivals.data.model.theme.ThemeDB
import com.ismita.chromerivals.data.service.database.ChromeRivalsThemeRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChromeRivalsThemeRepository @Inject constructor(
    private val themeRoom: ChromeRivalsThemeRoom
) {

    suspend fun getTheme(): ThemeDB? {
        return withContext(Dispatchers.IO) {
            themeRoom.getTheme()
        }
    }

    suspend fun insertTheme(theme: ThemeDB): Long {
        return withContext(Dispatchers.IO) {
            themeRoom.insert(theme)
        }
    }

    suspend fun updateTheme(theme: ThemeDB) {
        return withContext(Dispatchers.IO) {
            themeRoom.update(theme)
        }
    }

}