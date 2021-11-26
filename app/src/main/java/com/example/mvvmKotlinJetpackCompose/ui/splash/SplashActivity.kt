package com.example.mvvmKotlinJetpackCompose.ui.splash

import android.content.res.Configuration
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseComponentActivity
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.DashboardActivity
import com.example.mvvmKotlinJetpackCompose.ui.login.LoginActivity
import com.example.mvvmKotlinJetpackCompose.ui.theme.LiquorCoinTheme
import com.example.mvvmKotlinJetpackCompose.util.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@AndroidEntryPoint
class SplashActivity : BaseComponentActivity<SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModels()

    override val wantToShowCustomLoading=true

    @Composable
    override fun ProvideCompose() {
        viewModel.decideActivity()

        SplashCompose {
                ImageAndAppName {
                    val loadingValue = viewModel.isLoading()

                    observeEvent(viewModel.singleEventOpenActivity) {
                        when (val result = it.getContentIfNotHandled()) {
                            is Success -> {
                                if (result.data == 1) {
                                    startActivity<DashboardActivity>()
                                } else {
                                    startActivity<LoginActivity>()

                                }

                            }

                        }
                    }
                    if (loadingValue) {

                        CircularProgressIndicator()
                    }

                }

        }
    }

    @Composable
    private fun SplashCompose(ChildrenCompose: @Composable () -> Unit) {

        Box(Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter) {
            ChildrenCompose()

        }
    }


    @Composable
    private fun ImageAndAppName(showLoading: @Composable () -> Unit) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(R.drawable.jetpack_logo),
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.dp_100))
                    .width(dimensionResource(id = R.dimen.dp_120))
                    .height(dimensionResource(id = R.dimen.dp_120)),
                contentDescription = "")

            Text(modifier = Modifier.width(IntrinsicSize.Max),
                text = stringResource(id = R.string.app_name),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.secondary,
                style = MaterialTheme.typography.h6)

            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dp_20)))
            showLoading()

        }
    }


    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        LiquorCoinTheme {

            SplashCompose {
                ImageAndAppName {
                    CircularProgressIndicator()

                }
            }
        }
    }
}

