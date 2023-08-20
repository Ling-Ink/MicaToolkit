package com.moling.micatoolkit.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.ScalingLazyListItemScope
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import com.moling.micatoolkit.presentation.model.FunctionItem

@Composable
fun FunctionList(
    headerId: Int,
    navController: NavHostController,
    functionsList: List<FunctionItem>,
    headerChip:@Composable (ScalingLazyListItemScope.() -> Unit)? = null,
) {
    ScalingLazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        item {
            ListHeader {
                Text(
                    text = stringResource(id = headerId),
                    color = Color.White
                )
            }
        }
        if (headerChip != null) {
            item (
                content = headerChip
            )
        }
        items(functionsList) {
            FuncChip(
                onClick = {
                    if (!it.onClickRoute.isNullOrEmpty()) {
                        navController.navigate(it.onClickRoute) {
                            if (!it.onClickPopUpTo.isNullOrEmpty()) {
                                popUpTo(it.onClickPopUpTo)
                            }
                        }
                    }
                },
                onLongClick = {
                    if (!it.onLongClickRoute.isNullOrEmpty()) {
                        navController.navigate(it.onLongClickRoute) {
                            if (!it.onLongClickPopUpTo.isNullOrEmpty()) {
                                popUpTo(it.onLongClickPopUpTo)
                            }
                        }
                    }
                },
                colors = ChipDefaults.secondaryChipColors(),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                        .fillParentMaxHeight()) {
                        Icon(imageVector = it.icon, contentDescription = "")
                    }
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                        .fillParentMaxHeight()
                        .padding(start = 10.dp)) {
                        Text(
                            text = stringResource(id = it.titleId),
                            color = Color.White,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        if (it.subTitleId != 0) {
                            Text(
                                text = stringResource(id = it.subTitleId),
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    }
                }
            )
        }
    }
}