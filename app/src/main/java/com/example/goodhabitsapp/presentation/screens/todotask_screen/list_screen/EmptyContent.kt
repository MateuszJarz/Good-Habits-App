package com.example.goodhabitsapp.presentation.screens.todotask_screen.list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.goodhabitsapp.R
import com.example.goodhabitsapp.ui.theme.MediumGray
import com.example.goodhabitsapp.ui.theme.SIZE_SAD_ICON

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(SIZE_SAD_ICON),
            painter = painterResource(id = R.drawable.ic_sad_face),
            contentDescription = "Sad Face Icon",
            tint = MediumGray
        )
        Text(
            text = "No Task Found.",
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}

@Preview
@Composable
fun EmptyContentPreview() {
    EmptyContent()
}