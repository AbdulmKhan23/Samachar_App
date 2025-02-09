package com.khan.samacharapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khan.samacharapp.domain.useCase.app_entry.AppEntryUsesCases
import com.khan.samacharapp.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUsesCases: AppEntryUsesCases
):ViewModel(){

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.Route)
        private set

    init {
        appEntryUsesCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen)
            {
                startDestination = Route.NewsNavigation.Route
            }else{
                startDestination = Route.AppStartNavigation.Route

            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }
}