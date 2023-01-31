package com.example.mvvmKotlinJetpackCompose.ui.dashboard

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.activity.viewModels
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mvvmKotlinJetpackCompose.R
import com.example.mvvmKotlinJetpackCompose.data.network.DataError
import com.example.mvvmKotlinJetpackCompose.data.network.Success
import com.example.mvvmKotlinJetpackCompose.data.network.model.DashboardResponse
import com.example.mvvmKotlinJetpackCompose.data.others.MenuItem
import com.example.mvvmKotlinJetpackCompose.ui.base.BaseComponentActivity
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.compose.BottomNavData
import com.example.mvvmKotlinJetpackCompose.ui.dashboard.compose.TopBar
import com.example.mvvmKotlinJetpackCompose.ui.login.LoginActivity
import com.example.mvvmKotlinJetpackCompose.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseComponentActivity<DashboardViewModel>() {

    override val viewModel: DashboardViewModel by viewModels()



    @ExperimentalFoundationApi
    @Composable
    override fun ProvideCompose() {

        val dashboardData = remember {
            mutableStateOf(DashboardResponse.Data())
        }


        val userId = remember { mutableStateOf("") }

        observe(viewModel.userIdData) {
            when (it) {
                is Success -> {
                    userId.value = it.data!!
                }
                else -> {}
            }
        }

        observe(viewModel.dashboardData) {
            when (it) {
                is DataError -> {

                }
                is Success -> {
                    it.data.let {
                        dashboardData.value = it!!.data
                    }
                }
            }
        }

        observe(viewModel.logoutData) {
            when (it.getContentIfNotHandled()) {
                is DataError -> println()
                is Success -> {
                    startActivity<LoginActivity>()
                    finish()
                }
                else -> {}
            }

        }

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        SetUpDashboardCompose(dashboardData.value,
            userId.value,
            currentRoute,
            {
                navController.navigate(it) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route) {
                            saveState = true
                        }
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }

            }) {

            NavHost(navController = navController,
                startDestination = BottomNavData.Home.route) {

                composable(route = BottomNavData.Home.route) {
                    DashboardContent(viewModel.getMenuData()) {

                    }

                }

                  composable(route = BottomNavData.History.route) {
                   Text(text = "History",
                   modifier = Modifier.fillMaxWidth(),
                   textAlign = TextAlign.Center)
                }

                  composable(route = BottomNavData.Other.route) {
                      Text(text = "Other",
                          modifier = Modifier.fillMaxWidth(),
                          textAlign = TextAlign.Center)

                }



            }
        }


    }

    @ExperimentalFoundationApi
    @Composable
    private fun SetUpDashboardCompose(
        dashboardData: DashboardResponse.Data,
        userId: String,
        currentRoute: String?,
        onItemClick: (String)->Unit={},
        SetupNavHost: @Composable () -> Unit,
    ) {
        val scaffoldState =
            rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val scope = rememberCoroutineScope()

        Scaffold(scaffoldState = scaffoldState,
            topBar = {
                TopBar(scope,
                    scaffoldState,
                    dashboardData.liqrToINR)
            },
            drawerShape = RoundedCornerShape(0.dp),
            drawerContent = {
                DrawerCompose(scope, scaffoldState,
                    dashboardData.balanceLiqr.toString(),
                    userId) { viewModel.logout() }
            },
            bottomBar = {
                BottomNavigation(
                    backgroundColor = MaterialTheme.colors.onSecondary,
                    contentColor = MaterialTheme.colors.onSecondary
                ) {

                    val items=BottomNavData.values()
                    items.forEach {
                        BottomNavigationItem(
                            icon = {
                                Icon(painterResource(it.iconId ) ,
                                    contentDescription =it.title)
                            },
                            label = { Text(text = it.title) },
                            selectedContentColor = MaterialTheme.colors.secondary,
                            unselectedContentColor = MaterialTheme.colors.primary
                            ,
                            alwaysShowLabel = true,
                            selected = currentRoute==it.route,
                            onClick = {
                                onItemClick(it.route)

                            }
                        )
                    }

                }


            }) {

            SetupNavHost()

        }
    }


    @ExperimentalFoundationApi
    @Preview(showBackground = true,
        uiMode = UI_MODE_NIGHT_NO)
    @Composable
    override fun ProvideComposeLightPreview() {

        //jetpack compose compiler sometimes shows different colors
        SetUpDashboardCompose(DashboardResponse.Data(), "userId",
            "") {

            DashboardContent(listOf(
                MenuItem(R.drawable.ic_prepaid, "Prepaid"),
                MenuItem(R.drawable.ic_postpaid, "PostPaid"),
                MenuItem(R.drawable.ic_dtf, "Dth"),
                MenuItem(R.drawable.ic_datacard, "DataCard"),
                MenuItem(R.drawable.ic_gas, "Gas"),
                MenuItem(R.drawable.ic_water, "Water"),
                MenuItem(R.drawable.ic_electricity, "Electricity"),
                MenuItem(R.drawable.ic_billpayment, "Bill Payment"),
                MenuItem(R.drawable.ic_fund_recieve, "Fun receive"),
                MenuItem(R.drawable.ic_bus, "bus"),
                MenuItem(R.drawable.ic_flight, "flight"),
                MenuItem(R.drawable.ic_hotel_booking, "Hotel booking"),
            ))
        }
    }


}

