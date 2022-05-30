package me.gabeg.sicksends.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.*

@Composable
fun SsHomeScreen(innerPadding : PaddingValues)
{

	//Column(modifier = Modifier.padding(innerPadding))
	ConstraintLayout(
		modifier = Modifier
			.padding(vertical = 24.dp, horizontal = 16.dp))
	{
		val (gradeIcon, gradeBody, gradeLine, feelIcon) = createRefs()

		var showGradeBody by remember { mutableStateOf(true) }
		var gradeSubtitle by remember { mutableStateOf("") }

		SsIconBubble(ref = gradeIcon)
		{
			Icon(Icons.Default.CloudDone,
				modifier = Modifier
					.align(Alignment.Center)
					.size(24.dp),
				contentDescription = "Yo")
		}

		Column(
			modifier = Modifier
				.constrainAs(gradeBody) {
					top.linkTo(gradeIcon.top)
					start.linkTo(gradeIcon.end)
				}
				.padding(top = 0.dp, bottom = 16.dp, start = 8.dp, end = 8.dp))
		{

			Text("Grade",
				fontWeight = FontWeight.Bold)

			Text("V-scale",
				fontSize = MaterialTheme.typography.caption.fontSize,
				fontWeight = FontWeight.Light)

			AnimatedVisibility(visible = showGradeBody) {

				Button(onClick = { /*TODO*/ })
				{
					Text("Dummy")
				}
			}

			Button(onClick = { showGradeBody = !showGradeBody })
			{
				Text("Done")
			}

		}

		SsVerticalLine(
			ref = gradeLine,
			topRef = gradeIcon,
			bottomRef = gradeBody)

		SsIconBubble(
			ref = feelIcon,
			anchor = gradeLine.bottom)
		{
			Icon(Icons.Default.Face,
				modifier = Modifier
					.align(Alignment.Center)
					.size(24.dp),
				contentDescription = "Yo")
		}

	}

}

/**
 * Create an icon bubble that appears next to each question.
 */
@Composable
fun ConstraintLayoutScope.SsIconBubble(
	ref : ConstrainedLayoutReference,
	anchor : ConstraintLayoutBaseScope.HorizontalAnchor? = null,
	content : @Composable BoxScope.() -> Unit = {})
{

	Box(
		modifier = Modifier
			.constrainAs(ref) {
				top.linkTo(anchor ?: parent.top)
				start.linkTo(parent.start)
			}
			.padding(horizontal = 16.dp)
			.size(48.dp)
			.background(color = Color.White, shape = CircleShape)
			.border(width = 3.dp, color = Color.Green, shape = CircleShape))
	{
		content()
	}
}

/**
 * Create a vertical line that connects two icon bubbles.
 */
@Composable
fun ConstraintLayoutScope.SsVerticalLine(
	ref : ConstrainedLayoutReference,
	topRef : ConstrainedLayoutReference,
	bottomRef : ConstrainedLayoutReference)
{
	Divider(
		modifier = Modifier
			.constrainAs(ref) {
				centerHorizontallyTo(topRef)

				top.linkTo(topRef.bottom)
				bottom.linkTo(bottomRef.bottom)

				height = Dimension.fillToConstraints
			}
			.width(3.dp),
		color = Color.Green)
}