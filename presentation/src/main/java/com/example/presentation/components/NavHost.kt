package com.example.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.presentation.screens.HomePatientScreen
import com.example.presentation.screens.UpdatePatientScreen
import kotlinx.serialization.Serializable

@Composable
fun NavHostPatient(navController: NavHostController, modifier: Modifier = Modifier) {
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

@Serializable
object Home

@Serializable
data class UpdatePatient(val id: String)