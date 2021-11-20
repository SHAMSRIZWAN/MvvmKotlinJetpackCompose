package com.example.mvvmKotlinJetpackCompose.ui.dashboard.compose

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.MenuItemCompose
import com.example.mvvmKotlinJetpackCompose.ui.theme.LiquorCoinTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    openComingSoonActivity: (String) -> Unit,
    liqrCoin: Double,
    openActivity: (String) -> Unit,
) {
    Image(modifier = Modifier
        .fillMaxWidth()
        .height(dimensionResource(R.dimen.dp_145)),
        painter = painterResource(R.drawable.bg_toolbar),
        contentScale = ContentScale.FillBounds,
        contentDescription = "")

    TopAppBar(title = {

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(end = dimensionResource(id = R.dimen.dp_15)),
            textAlign = TextAlign.End,
            text = "1 Liqrcoin = "+liqrCoin+" INR",
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.subtitle2)

    },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { scaffoldState.drawerState.open() }
            }) {
                Icon(Icons.Default.Menu, "",
                    tint = MaterialTheme.colors.onSecondary)
            }
        },
        backgroundColor = Color.Transparent,
        elevation = 0.dp
    )

    Column {
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dp_75)))
        Row {
            val color = MaterialTheme.colors.onSecondary;

            MenuItemCompose(
                Modifier.weight(1f), R.drawable.ic_qr_code,
                stringResource(R.string.scan_and_pay),
                {
                    openActivity(it)


                }, color
            )

            MenuItemCompose(Modifier.weight(1f), R.drawable.ic_upi,
                stringResource(R.string.upi),
                {
                    openComingSoonActivity(it)
                }, color)



            MenuItemCompose(Modifier.weight(1f), R.drawable.ic_passbook,
                stringResource(R.string.passbook),
                { openComingSoonActivity(it) }, color)

            MenuItemCompose(Modifier.weight(1f), R.drawable.ic_deposit,
                stringResource(R.string.deposit),
                { openComingSoonActivity(it) }, color)


        }

    }

}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ProvideComposeLightPreview() {
    LiquorCoinTheme {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()
        TopBar(scope, scaffoldState, {}, 20.0,{},)

    }
}
