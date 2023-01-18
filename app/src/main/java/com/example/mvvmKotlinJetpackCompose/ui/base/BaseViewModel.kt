package com.example.mvvmKotlinJetpackCompose.ui.base

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.Companion.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.util.SOMETHING_WENT_WRONG
import com.example.mvvmKotlinJetpackCompose.util.coroutines.DispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel<R : BaseRepository>(
    private val repository: R,
    private val appDispatcher: DispatcherProvider,
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val showDialogLoadingPrivate = MutableLiveData(false)

    val showMessageDialog = MutableLiveData<Resource<String>>()

    private val onErrorDialogDismissPrivate = MutableLiveData<Resource<Boolean>>()
    val onErrorDialogDismiss: LiveData<Resource<Boolean>> get() = onErrorDialogDismissPrivate

    protected val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        hideLoading()
        showMessageDialog(DataError(SOMETHING_WENT_WRONG))

    }


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
        showMessageDialog.value = dataError
    }

    fun showPostMessageDialog(dataError: DataError<String>) {
        showMessageDialog.value = dataError
    }

    fun hideMessageDialog(success: Success<String>) {
        showMessageDialog.value = success

    }



}

