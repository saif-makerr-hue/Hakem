package com.example.presentation.navigator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.screens.HomePatientScreen
import com.example.presentation.screens.UpdatePatientScreen

@Composable
fun NavHostPatient(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Home,
        modifier = modifier

    ) {
        composable<Home> {
            HomePatientScreen(
                onClickToAdd = { navController.navigate(UpdatePatient("")) },
                onClickToUpdate = { navController.navigate(UpdatePatient(it)) },
            )
        }
        composable<UpdatePatient> {
            UpdatePatientScreen(onClickHome = { navController.popBackStack() })
        }
    }
}