package com.moling.micatoolkit.presentation.activities.functionActs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.moling.micatoolkit.R
import com.moling.micatoolkit.presentation.activities.MainActivity.Companion.global
import com.moling.micatoolkit.presentation.theme.MicaToolkitTheme

@Composable
fun InfoAct() {
    MicaToolkitTheme {
        ScalingLazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            item { ListHeader { Text(text = stringResource(id = R.string.app_about), color = Color.White) } }
            item { InfoChip(title = stringResource(id = R.string.app_name), subTitle = global.getString("versionName")) }
            item { InfoChip(title = stringResource(id = R.string.app_project), subTitle = stringResource(id = R.string.app_project_url)) }
            item { InfoChip(title = stringResource(id = R.string.app_website), subTitle = stringResource(id = R.string.app_website_url)) }
        }
    }
}

@Composable
fun InfoChip(
    title: String,
    subTitle: String
) {
    Chip(
        onClick = { /* IGNORE */ },
        colors = ChipDefaults.primaryChipColors(backgroundColor = MaterialTheme.colors.surface),
        modifier = Modifier.fillMaxWidth(),
        label = {
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp)) {
                Text(
                    text = title,
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = subTitle,
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    )
}