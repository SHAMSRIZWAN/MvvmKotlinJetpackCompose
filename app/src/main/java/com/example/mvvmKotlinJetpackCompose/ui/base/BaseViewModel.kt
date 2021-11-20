package com.example.mvvmKotlinJetpackCompose.ui.base

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider

open class BaseViewModel<R : BaseRepository>(
    private val repository: R,
    private val appDispatcher: DispatcherProvider,
) : ViewModel() {

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val showDialogLoadingPrivate= MutableLiveData(false)

    val showErrorDialog = MutableLiveData<Resource<String>>()

    private val onErrorDialogDissmissPrivate = MutableLiveData<Resource<Boolean>>()
    val onErrorDialogDismiss: LiveData<Resource<Boolean>> get() = onErrorDialogDissmissPrivate

    fun isLoading(): Boolean {
        return showDialogLoadingPrivate.value!!
    }


    fun showLoading() {
        if (!showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.value = true
        }

    }

    fun hideLoading() {
        if (showDialogLoadingPrivate.value!!) {
            showDialogLoadingPrivate.value = false
        }
    }

    fun getRepo(): R {
        return repository
    }

    fun getAppDispatcher(): DispatcherProvider {
        return appDispatcher
    }

    fun showMessageDialog(dataError: DataError<String>) {
        showErrorDialog.value = dataError
    }

    fun onErrorDialogDismiss(value: Success<Boolean>) {
        onErrorDialogDissmissPrivate.value = value
    }
}