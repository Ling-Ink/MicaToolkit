package com.moling.micatoolkit.presentation.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import com.moling.micatoolkit.presentation.activities.functionActs.IconButton


@Composable
fun ConfirmButton(
    onClick: () -> Unit
) {
    IconButton(
        imageVector = Icons.Outlined.Done,
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(top = 15.dp),
        onClick = onClick
    )
}