package com.moling.micatoolkit.presentation.widgets.functionList

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ChipBorder
import androidx.wear.compose.material.ChipColors
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.LocalContentAlpha
import androidx.wear.compose.material.LocalContentColor
import androidx.wear.compose.material.LocalTextStyle
import androidx.wear.compose.material.MaterialTheme

@Composable
fun FuncChip(
    label: @Composable RowScope.() -> Unit,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
    secondaryLabel: (@Composable RowScope.() -> Unit)? = null,
    icon: (@Composable BoxScope.() -> Unit)? = null,
    colors: ChipColors = ChipDefaults.primaryChipColors(),
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    shape: Shape = MaterialTheme.shapes.small,
    border: ChipBorder = ChipDefaults.chipBorder()
) {
    FuncChip(
        onClick = onClick,
        onLongClick = onLongClick,
        colors = colors,
        border = border,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        shape = shape,
        contentPadding = contentPadding
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            // Fill the container height but not its width as chips have fixed size height but we
            // want them to be able to fit their content
            modifier = Modifier.fillMaxHeight()
        ) {
            if (icon != null) {
                CompositionLocalProvider(
                    LocalContentColor provides colors.iconColor(enabled).value,
                    LocalContentAlpha provides
                            colors.iconColor(enabled = enabled).value.alpha,
                ) {
                    Box(
                        modifier = Modifier.wrapContentSize(align = Alignment.Center),
                        content = icon
                    )
                }
                Spacer(modifier = Modifier.size(FuncChipDefaults.IconSpacing))
            }
            Column {
                Row {
                    CompositionLocalProvider(
                        LocalContentColor provides colors.contentColor(enabled).value,
                        LocalTextStyle provides MaterialTheme.typography.button,
                        LocalContentAlpha provides
                                colors.contentColor(enabled = enabled).value.alpha,
                    ) {
                        label()
                    }
                }
                if (secondaryLabel != null) {
                    Row {
                        CompositionLocalProvider(
                            LocalContentColor provides colors.secondaryContentColor(enabled).value,
                            LocalTextStyle provides MaterialTheme.typography.caption2,
                            LocalContentAlpha provides
                                    colors.secondaryContentColor(enabled = enabled).value.alpha,
                        ) {
                            secondaryLabel()
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FuncChip(
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    colors: ChipColors,
    border: ChipBorder,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    shape: Shape = MaterialTheme.shapes.small,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    role: Role? = Role.Button,
    content: @Composable RowScope.() -> Unit,
) {
    CompositionLocalProvider(
        LocalContentColor provides colors.contentColor(enabled = enabled).value,
        LocalTextStyle provides MaterialTheme.typography.button,
        LocalContentAlpha provides colors.contentColor(enabled = enabled).value.alpha,
    ) {
        val borderStroke = border.borderStroke(enabled).value
        Row(
            modifier = modifier
                .height(FuncChipDefaults.Height)
                .then(
                    if (borderStroke != null) Modifier.border(
                        border = borderStroke,
                        shape = shape
                    ) else Modifier
                )
                .clip(shape = shape)
                .width(intrinsicSize = IntrinsicSize.Max)
                .paint(
                    painter = colors.background(enabled = enabled).value,
                    contentScale = ContentScale.Crop
                )
                .combinedClickable(
                    enabled = enabled,
                    onClick = onClick,
                    onLongClick = onLongClick,
                    role = role,
                    indication = rememberRipple(),
                    interactionSource = interactionSource,
                )
                .padding(contentPadding),
            content = content
        )
    }
}

object FuncChipDefaults {
    internal val Height = 52.dp

    internal val IconSpacing = 6.dp
}