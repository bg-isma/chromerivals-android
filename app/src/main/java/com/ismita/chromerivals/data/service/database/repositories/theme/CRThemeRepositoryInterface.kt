package com.ismita.chromerivals.data.service.database.repositories.theme

import com.ismita.chromerivals.data.model.theme.ThemeDB

interface CRThemeRepositoryInterface {
    suspend fun getTheme(): ThemeDB
    suspend fun insertTheme(theme: ThemeDB)
    suspend fun updateTheme(theme: ThemeDB)
}