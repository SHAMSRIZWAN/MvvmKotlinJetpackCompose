package com.example.mvvmKotlinJetpackCompose.ui.base


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.ui.theme.LiquorCoinTheme
import com.example.mvvmKotlinJetpackCompose.ui.theme.White

abstract class BaseComponentActivity<VM : BaseViewModel<*>> : ComponentActivity() {

    abstract val viewModel: VM

    //override in child class if you don't want to use global loading state
    open val wantToShowCustomLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiquorCoinTheme {
                ProvideCompose()
                SetUpLoadingDialog()
                SetUpErrorDialog()
            }
        }


    }

    @Composable
    private fun SetUpLoadingDialog() {
        if (!wantToShowCustomLoading) {
            val loadingValue = viewModel.showDialogLoadingPrivate.observeAsState()

            if (loadingValue.value == true) {
                ShowLoading()
            }
        }

    }

    @Composable
    private fun SetUpErrorDialog() {
        var dialogState = false
        var errorDescription = ""
        val vmLoadinState = viewModel.showMessageDialog.observeAsState()
        when (vmLoadinState.value) {
            is DataError -> {
                errorDescription = (vmLoadinState.value as DataError<String>).errorDescription
                dialogState = true
            }

            is Success -> {
                dialogState = false
            }
        }
        if (dialogState) {
            ShowErrorDialog(errorDescription)

        }


    }


    @Composable
    protected open fun ShowLoading() {

        Dialog(
            onDismissRequest = { viewModel.hideLoading() },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator()
            }
        }
    }


    @Composable
    private fun ShowErrorDialog(errorDescription: String) {

        AlertDialog(
            onDismissRequest = {
            },

            title = {
                Text(stringResource(R.string.error), style = MaterialTheme.typography.h4)
            },
            text = {
                Text(errorDescription, fontSize = 16.sp)
            },
            confirmButton = {
            },

            dismissButton = {
                TextButton(
                    onClick = {
                        viewModel.hideMessageDialog(Success(""))
                    }) {
                    Text(
                        "Ok",
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.onSecondary
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = Color.White
        )

    }


    @Composable
    abstract fun ProvideCompose()

    @Composable
    abstract fun ProvideComposeLightPreview()


    inline fun <reified T : ComponentActivity> Context.startActivity(block: Intent.() -> Unit = {}) {

        startActivity(Intent(this, T::class.java).apply(block))
    }


}