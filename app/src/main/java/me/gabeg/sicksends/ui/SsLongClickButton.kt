package me.gabeg.sicksends

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

/**
 * Button that has a long click listener so that long click events can be
 * captured.
 *
 * Found at:
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:compose/material/material/src/commonMain/kotlin/androidx/compose/material/Button.kt
 */
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun SsLongClickButton(
    onClick: () -> Unit,
    onLongClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    elevation: ButtonElevation? = ButtonDefaults.elevation(),
    shape: Shape = MaterialTheme.shapes.small,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {

    val contentColor by colors.contentColor(enabled)

    Surface(
        //onClick = onClick,
        modifier = modifier
            .combinedClickable(
                enabled = enabled,
				indication = null,
                interactionSource = interactionSource,
				role = Role.Button,
                onClick = onClick,
                onLongClick = onLongClick
            ),
        //enabled = enabled,
        shape = shape,
        color = colors.backgroundColor(enabled).value,
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
        elevation = elevation?.elevation(enabled, interactionSource)?.value ?: 0.dp)
        //interactionSource = interactionSource)
    {

        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha)
        {

            ProvideTextStyle(value = MaterialTheme.typography.button)
            {

                Row(
                    Modifier
                        .defaultMinSize(
                            minWidth = ButtonDefaults.MinWidth,
                            minHeight = ButtonDefaults.MinHeight)
                        .padding(contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = content)

            }

        }

    }

}




@Composable
@NonRestartableComposable
fun SsLongClickOutlinedButton(
	onClick: () -> Unit,
	onLongClick: (() -> Unit)? = null,
	modifier: Modifier = Modifier,
	enabled: Boolean = true,
	interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
	elevation: ButtonElevation? = null,
	shape: Shape = MaterialTheme.shapes.small,
	border: BorderStroke? = ButtonDefaults.outlinedBorder,
	colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
	contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
	content: @Composable RowScope.() -> Unit) =
		SsLongClickButton(
			onClick = onClick,
			onLongClick = onLongClick,
			modifier = modifier,
			enabled = enabled,
			interactionSource = interactionSource,
			elevation = elevation,
			shape = shape,
			border = border,
			colors = colors,
			contentPadding = contentPadding,
			content = content)

