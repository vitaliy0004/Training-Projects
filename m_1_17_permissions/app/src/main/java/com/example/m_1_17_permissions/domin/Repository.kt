package com.example.m_1_17_permissions.domin


import com.example.m_1_17_permissions.data.Fail
import com.example.m_1_17_permissions.data.FailDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(private val fail: FailDao) {
    fun getFailDao(): Flow<List<Fail>> {
        return fail.getFail()
    }

    suspend fun addFail(addFail: Fail) {
        return fail.addFail(addFail)
    }

    suspend fun deleteFail(deleteFail: List<Fail>) {
        return fail.deleteFail(deleteFail)
    }

    suspend fun update(nowFail: Fail) {
        return fail.update(nowFail)
    }
}