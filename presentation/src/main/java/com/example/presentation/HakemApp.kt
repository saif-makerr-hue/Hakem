package com.example.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.presentation.navigator.NavHostPatient

@Composable
fun HakemApp() {
    Scaffold { innerPadding->
        val navController = rememberNavController()
        NavHostPatient(
            modifier = Modifier.padding(innerPadding),
            navController = navController
        )

    }
}
