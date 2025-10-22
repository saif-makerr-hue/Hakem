package com.example.presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun Space(space: Dp,modifier: Modifier = Modifier){
    Spacer(modifier = modifier.height(space))
}

@Composable
fun SpaceWidth(space: Dp,modifier: Modifier = Modifier){
    Spacer(modifier = modifier.width(space))
}
