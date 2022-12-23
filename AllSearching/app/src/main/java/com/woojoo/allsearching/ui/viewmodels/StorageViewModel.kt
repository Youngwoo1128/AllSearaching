package com.woojoo.allsearching.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woojoo.allsearching.domain.entites.DeleteResult
import com.woojoo.allsearching.utils.SingleLiveEvent
import com.woojoo.allsearching.domain.entites.Researching
import com.woojoo.allsearching.domain.usecases.GetDeleteResearchingItem
import com.woojoo.allsearching.domain.usecases.GetAllResearchingUseCase
import com.woojoo.allsearching.domain.usecases.NotifyQueueUseCase
import com.woojoo.allsearching.domain.usecases.NotifyResearchingUseCase
import com.woojoo.allsearching.utils.LoadStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val getAllResearchingUseCase: GetAllResearchingUseCase,
    private val getDeleteResearchingItem: GetDeleteResearchingItem,
    private val notifyResearchingUseCase: NotifyResearchingUseCase,
    private val notifyQueueUseCase: NotifyQueueUseCase
) : ViewModel() {

    val localResearching: LiveData<List<Researching>>
        get() = _localResearching
    private val _localResearching = MutableLiveData<List<Researching>>()

    val deletedItem: LiveData<DeleteResult>
        get() = _deletedItem
    private val _deletedItem = SingleLiveEvent<DeleteResult>()

    val loadStatus: LiveData<LoadStatus>
        get() = _loadStatus
    private val _loadStatus = MutableLiveData<LoadStatus>()

    val notifyResearching: LiveData<Researching>
        get() = _notifyResearching
    private val _notifyResearching = MutableLiveData<Researching>()

    fun getLocalResearchingList() {
        viewModelScope.launch(Dispatchers.IO) {
            _localResearching.postValue(getAllResearchingUseCase())
        }
    }

    fun deleteResearchingItem(item: Researching) {
        viewModelScope.launch(Dispatchers.IO) {
            getDeleteResearchingItem(item).collectLatest {
                _deletedItem.postValue(it)
            }
        }
    }

    fun notifyAddItem() {
        viewModelScope.launch(Dispatchers.IO) {
            notifyResearchingUseCase().collectIndexed { index, value ->
                Log.d("allSearching Project", "notifyValue : $value")
                _notifyResearching.postValue(value)
            }
        }
    }

    fun isNotifyQueueEmpty(): Boolean {
        Log.d("ywsong Tag", "$notifyQueueUseCase")
        return notifyQueueUseCase()
    }

    fun setLoadStatusLoading() {
        _loadStatus.value = LoadStatus.isLoading
    }

    fun setLoadStatusFinish() {
        _loadStatus.value = LoadStatus.loadFinish
    }
}