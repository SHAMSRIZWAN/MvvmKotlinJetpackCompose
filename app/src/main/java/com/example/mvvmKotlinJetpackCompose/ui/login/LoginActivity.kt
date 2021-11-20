package com.example.mvvmKotlinJetpackCompose.ui.login


import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Resource
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseComponentActivity
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.DashboardActivity
import com.example.mvvmKotlinJetpackCompose.ui.theme.LiquorCoinTheme
import com.example.mvvmKotlinJetpackCompose.ui.theme.Shapes
import com.example.mvvmKotlinJetpackCompose.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseComponentActivity<LoginViewModel>() {

    override val viewModel: LoginViewModel by viewModels()

//    @Inject
//    lateinit var registrationComponent: RegistrationComponent
//    val toDoRepository = EntryPoints
//        .get(registrationComponent, RegistrationComponentEntryPoint::class.java)
//        .getRegistrationRepo()

    companion object {
        fun getActivity(context: Context): Intent {
            return Intent(context, LoginActivity::class.java)
        }

    }

    @Composable
    override fun ProvideCompose() {

        observe(viewModel.loginResponse) {
            when (it) {
                is DataError -> {
                }
                is Success -> {
                    startDashboardAcitivty()
                    finish()
                }


            }
        }

        LoginCompose() {
            TopImageAndText()
            var txtAccountNo by remember { mutableStateOf("") }

            TextFieldUserName(txtAccountNo) { txtAccountNo = it }
            var txtPass by remember { mutableStateOf("") }

            TextFieldPassword(txtPass) {
                txtPass = it
            }

            RegistrationButton {
                viewModel.onSignInBtnClick(txtAccountNo.trim(),
                    txtPass.trim())
            }
            ForgotPassWordAndRegistration()


        }

    }

    @Composable
    private fun RegistrationButton(onClick: () -> Unit) {

        Button(modifier = Modifier.width(dimensionResource(R.dimen.dp_150)),
            colors = ButtonDefaults
                .buttonColors(MaterialTheme.colors.secondary),
            onClick = { onClick() }) {

            Text(text = stringResource(id = R.string.sign_in).uppercase())
        }

        Spacer(modifier = Modifier
            .height(dimensionResource(R.dimen.dp_30)))


    }

    @Composable
    private fun ForgotPassWordAndRegistration() {

        val annotatedLinkString=getAnnotedString()
        Text(text = annotatedLinkString,
            color = MaterialTheme.colors.onSecondary)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp_100)))


    }

    @Composable
    private fun getAnnotedString(): AnnotatedString {

        return buildAnnotatedString {

            val str = stringResource(R.string.hyper_link)
            val startIndex = str.indexOf("Registration")
            val endIndex = startIndex + 12
            append(str)
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    fontSize = 18.sp,
                    textDecoration = TextDecoration.Underline
                ), start = startIndex, end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = "https://github.com",
                start = startIndex,
                end = endIndex
            )

        }
    }

    @Composable
    private fun TextFieldPassword(txtPass: String, setPass: (String) -> Unit) {


        TextField(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(shape = Shapes.medium,
                color = MaterialTheme.colors.primaryVariant),
            maxLines = 1,
            singleLine = true,
            value = txtPass,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { setPass(it) },
            placeholder = {
                Text(text = stringResource(R.string.password),
                    color = MaterialTheme.colors.onSecondary)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onSecondary,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onSecondary
            ),
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.ic_baseline_lock_24),
                    contentDescription = "", tint = MaterialTheme.colors.onSecondary)
            })

        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.dp_40)))


    }

    @Composable
    private fun TopImageAndText() {
        Image(modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.dp_100))
            .size(dimensionResource(id = R.dimen.dp_100)),
            painter = painterResource(id = R.drawable.ic_app),
            contentDescription = "")

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp_10)))

        Text(modifier = Modifier.width(IntrinsicSize.Max),
            textAlign = TextAlign.Center,
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.button,
            color = MaterialTheme.colors.onSecondary)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dp_100)))


    }

    @Composable
    private fun TextFieldUserName(txtAccountNo: String, setAcc: (String) -> Unit) {


        TextField(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(shape = Shapes.medium,
                color = MaterialTheme.colors.primaryVariant),
            maxLines = 1,
            singleLine = true,
            value = txtAccountNo,
            onValueChange = { setAcc(it) },
            placeholder = {
                Text(text = stringResource(R.string.acount_no),
                    color = MaterialTheme.colors.onSecondary)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onSecondary,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.onSecondary

            ),
            leadingIcon = {
                Icon(painter = painterResource(R.drawable.ic_baseline_person_24),
                    contentDescription = "", tint = MaterialTheme.colors.onSecondary)
            })
        Spacer(modifier = Modifier
            .height(dimensionResource(id = R.dimen.dp_20)))


    }

    fun startDashboardAcitivty() {
        startActivity(Intent(this, DashboardActivity::class.java))
    }

    @Composable
    fun LoginCompose(childrenCompose: @Composable () -> Unit) {

        Box {
            Image(modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.bg_login),
                contentScale = ContentScale.FillBounds,
                contentDescription = "")


            Column(
                Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.dp_20)),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                childrenCompose()
            }


        }


    }

    @Preview(
        showBackground = true,
        uiMode = Configuration.UI_MODE_NIGHT_NO
    )
    @Composable
    override fun ProvideComposeLightPreview() {
        LiquorCoinTheme {
            LoginCompose {

                TopImageAndText()
                TextFieldUserName("") { }
                TextFieldPassword("") {}
                RegistrationButton {}
                ForgotPassWordAndRegistration()


            }

        }
    }


}