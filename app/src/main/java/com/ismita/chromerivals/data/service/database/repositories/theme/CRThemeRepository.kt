package com.ismita.chromerivals.data.service.database.repositories.theme

import com.ismita.chromerivals.data.model.theme.ThemeDB
import com.ismita.chromerivals.data.service.database.interfaces.ChromeRivalsThemeRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CRThemeRepository @Inject constructor(
    private val themeRoom: ChromeRivalsThemeRoom
): CRThemeRepositoryInterface {

    override suspend fun getTheme(): ThemeDB {
        return withContext(Dispatchers.IO) {
            themeRoom.getTheme()
        }
    }

    override suspend fun insertTheme(theme: ThemeDB) {
        return withContext(Dispatchers.IO) {
            themeRoom.insert(theme)
        }
    }

    override suspend fun updateTheme(theme: ThemeDB) {
        return withContext(Dispatchers.IO) {
            themeRoom.update(theme)
        }
    }

}