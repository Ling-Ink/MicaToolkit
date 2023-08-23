package com.moling.micatoolkit.presentation.widgets.functionList

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

@Composable
fun FunctionList(
    headerId: Int,
    navController: NavHostController? = null,
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
                onClick = { it.onClick.invoke(navController) },
                onLongClick = { it.onLongClick.invoke(navController) },
                colors = ChipDefaults.secondaryChipColors(backgroundColor = it.color?: MaterialTheme.colors.surface),
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                        .fillParentMaxHeight()) {
                        Icon(imageVector = it.icon, contentDescription = "")
                    }
                    Column(verticalArrangement = Arrangement.Center, modifier = Modifier
                        .fillParentMaxHeight()
                        .padding(start = 10.dp)) {
                        if (!it.title.isNullOrEmpty()) {
                            Text(
                                text = it.title,
                                color = Color.White,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        } else if (it.titleId != null) {
                            Text(
                                text = stringResource(id = it.titleId),
                                color = Color.White,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                        }
                        if (!it.subTitle.isNullOrEmpty()) {
                            Text(
                                text = it.subTitle,
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 10.sp,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        } else if (it.subTitleId != null) {
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