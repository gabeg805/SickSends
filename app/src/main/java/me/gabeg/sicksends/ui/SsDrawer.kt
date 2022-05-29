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

/**
 * State of a navigation drawer.
 */
data class SsDrawerState(
	val info : MutableState<DrawerValue> = mutableStateOf(DrawerValue.Closed))
{

	/**
	 * Close the navigation drawer.
	 */
	fun close()
	{
		info.value = DrawerValue.Closed
	}

	/**
	 * Check if the navigation drawer is closed.
	 *
	 * @return True if the drawer is closed, and False otherwise.
	 */
	fun isClosed() : Boolean
	{
		return info.value == DrawerValue.Closed
	}

	/**
	 * Check if the navigation drawer is open.
	 *
	 * @return True if the drawer is open, and False otherwise.
	 */
	fun isOpen() : Boolean
	{
		return info.value == DrawerValue.Open
	}

	/**
	 * Open the navigation drawer.
	 */
	fun open()
	{
		info.value = DrawerValue.Open
	}

	/**
	 * Toggle the open/closed state of the navigation drawer.
	 */
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
	state : SsDrawerState = SsDrawerState(),
	backgroundColor : Color = Color.Green,
	occlusionColor: Color = Color(0xB0888888),
	content : @Composable () -> Unit = {})
{

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

		/**
		 * Row consisting of two elements:
		 *
		 * 1. The shadowed element that covers the screen behind the drawer.
		 * 2. The drawer element.
		 */
		Row(
			modifier = Modifier
				.fillMaxSize()
				.padding(scaffoldPadding)
				.zIndex(zIndex))
		{

			/**
			 * Shadowed element.
			 */
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

			/**
			 * Drawer element.
			 */
			Column(
				modifier = Modifier
					.fillMaxHeight()
					.background(backgroundColor)
					.animateEnterExit(
						enter = slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }),
						exit = slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth })))
			{

				/**
				 * Extra column so that users can set inner padding and other
				 * attributes of the drawer.
				 */
				Column(
					modifier = modifier)
				{
					content()
				}

			}

		}

	}

}

// TODO: Grayed out color add as argument
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SsTopDrawer(
	scaffoldPadding : PaddingValues,
	modifier : Modifier = Modifier,
	zIndex : Float = 1000f,
	state : SsDrawerState = SsDrawerState(),
	backgroundColor : Color = Color.Green,
	occlusionColor: Color = Color(0xB0888888),
	content : @Composable () -> Unit = {})
{

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

		/**
		 * Column consisting of two elements:
		 *
		 * 1. The drawer element.
		 * 2. The shadowed element that covers the screen behind the drawer.
		 */
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(scaffoldPadding)
				.zIndex(zIndex))
		{

			/**
			 * Drawer element.
			 */
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.background(backgroundColor)
					.animateEnterExit(
						enter = slideInVertically(initialOffsetY= { fullWidth -> -fullWidth }),
						exit = slideOutVertically(targetOffsetY = { fullWidth -> -fullWidth })))
			{

				/**
				 * Extra column so that users can set inner padding and other
				 * attributes of the drawer.
				 */
				Column(
					modifier = modifier)
				{
					content()
				}
			}

			/**
			 * Shadowed element.
			 */
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.background(occlusionColor)
					.weight(1f)
					.clickable {
						state.close()
					})
			{
			}

		}

	}

}
