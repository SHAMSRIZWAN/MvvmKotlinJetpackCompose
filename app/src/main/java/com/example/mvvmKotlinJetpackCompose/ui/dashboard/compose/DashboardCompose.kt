package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.others.MenuItem
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.compose.TopBar
import com.example.mvvmKotlinJetpackCompose.ui.theme.LiquorCoinTheme
import kotlinx.coroutines.CoroutineScope



@ExperimentalFoundationApi
@Composable
fun DashboardContent(menuItems: List<MenuItem>, openActivity: (String) -> Unit={}) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Menu(menuItems, openActivity)
        Spacer(modifier = Modifier
            .height(dimensionResource(R.dimen.dp_80)))
        Image(painter = painterResource(R.drawable.jetpack_logo),
            modifier = Modifier
                .width(dimensionResource(id = R.dimen.dp_145))
                .height(dimensionResource(id = R.dimen.dp_145)),
            contentDescription = "")

        Spacer(modifier = Modifier
            .height(dimensionResource(R.dimen.dp_100)))
    }

}

@ExperimentalFoundationApi
@Composable
private fun Menu(menuItems: List<MenuItem>, openActivity: (String) -> Unit) {


    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
    ) {
        items(menuItems) { item ->
            MenuItemCompose(Modifier
                .padding(top = dimensionResource(R.dimen.dp_40)),
                iconId = item.icon,
                title = item.text,
                onClick = {
                    openActivity(it)

                }, color = MaterialTheme.colors.primary)

        }
    }
}

@Composable
fun MenuItemCompose(
    modifier: Modifier = Modifier, iconId: Int,
    title: String,
    onClick: (String) -> Unit,
    color: Color,
) {
    Column(modifier

        .clickable { onClick(title) },
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(modifier = Modifier
            .width(dimensionResource(R.dimen.dp_40))
            .height(dimensionResource(R.dimen.dp_40)),
            painter = painterResource(iconId),
            contentDescription = "")
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dp_5)))
        Text(
            text = title.uppercase(),
            color = color,
            style = MaterialTheme.typography.caption,
        )

    }

}

