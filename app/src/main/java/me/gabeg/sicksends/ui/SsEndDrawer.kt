package me.gabeg.sicksends.ui

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

class SsDrawerState()
{

	var state : MutableState<DrawerValue>

	init
	{
		state = mutableStateOf(DrawerValue.Closed)
	}

	constructor(value : DrawerValue) : this()
	{
		state.value = value
	}

	fun close()
	{
		state.value = DrawerValue.Closed
	}

	fun isClosed() : Boolean
	{
		return state.value == DrawerValue.Closed
	}

	fun isOpen() : Boolean
	{
		return state.value == DrawerValue.Open
	}

	fun open()
	{
		state.value = DrawerValue.Open
	}

	fun toggle()
	{
		if (isOpen())
		{
			close()
		}
		else
		{
			open()
		}
	}

}

// TODO: Grayed out color add as argument
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SsEndDrawer(
	scaffoldPadding : PaddingValues,
	modifier : Modifier = Modifier,
	zIndex : Float = 1000f,
	state : SsDrawerState = SsDrawerState(DrawerValue.Open),
	backgroundColor : Color = Color.Green,
	occlusionColor: Color = Color(0xB0888888),
	content : @Composable () -> Unit = {})
{

	Log.i("EndDrawerYo", "Animating this sheiza : $state")

	AnimatedVisibility(
		modifier = Modifier.zIndex(zIndex),
		visible = state.isOpen(),
		//enter = EnterTransition.None,
		//exit = ExitTransition.None)
		enter = fadeIn(animationSpec = tween(200)),
		exit = fadeOut())
	//enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
	//exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth }))
	{

		Row(
			modifier = Modifier
				.fillMaxSize()
				.padding(scaffoldPadding)
				.zIndex(zIndex))
		{

			Column(
				modifier = Modifier
					.fillMaxHeight()
					.background(occlusionColor)
					.weight(1f)
					.clickable {
						state.close()
					})
			{
			}

			Column(
				modifier = Modifier
					.fillMaxHeight()
					.background(backgroundColor)
					.animateEnterExit(
						enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
						exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })))
			{

				Column(
					modifier = modifier)
				{
					content()
				}
			}

		}

	}

}