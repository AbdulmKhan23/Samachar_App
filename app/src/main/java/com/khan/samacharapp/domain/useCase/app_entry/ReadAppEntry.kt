package com.khan.samacharapp.domain.useCase.app_entry

import com.khan.samacharapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class readAppEntry (
    private val localUserManager: LocalUserManager
){
    operator fun invoke():Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}