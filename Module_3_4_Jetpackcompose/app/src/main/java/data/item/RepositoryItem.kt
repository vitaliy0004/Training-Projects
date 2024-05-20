package data.item

import data.Retrofit

class RepositoryItem {
    suspend fun item(page: String): List<Episode> {
        return Retrofit().itemApi.getNewTask(page)
    }
}