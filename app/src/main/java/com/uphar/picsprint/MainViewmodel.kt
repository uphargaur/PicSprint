package com.uphar.picsprint

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhalakXCorp.vent.bussinesss.domain.Utils.state.DataState
import com.uphar.bussiness.domain.data.ImageResponse
import com.uphar.bussiness.repo.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val imageRepository : ImageRepository
) : ViewModel()
{
    private val _allMessagesDataState =
        MutableStateFlow<DataState<ImageResponse>>(DataState.Loading(false))
    val allMessagesDataState: StateFlow<DataState<ImageResponse>> get() = _allMessagesDataState


    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private fun setStateEvent(subscriptionEvent: UserSubscriptionEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (subscriptionEvent) {
                is UserSubscriptionEvent.GetToken -> {
                    imageRepository.gettoken().onEach {
                        when (it) {
                            is DataState.Error -> _allMessagesDataState.emit(it)
                            is DataState.Loading -> _loading.emit(it.loading)
                            is DataState.Success -> _allMessagesDataState.emit(it)
                        }
                    }.launchIn(viewModelScope)

                }
            }
        }
    }


    fun getToken() {
        setStateEvent(UserSubscriptionEvent.GetToken)
    }


    sealed class UserSubscriptionEvent {
        object  GetToken : UserSubscriptionEvent()
    }

}