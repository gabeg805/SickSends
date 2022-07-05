package me.gabeg.sicksends.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.gabeg.sicksends.R

/*
 * Welcome the user to the app. This is the very first page that the user will
 * see.
 */
@Composable
fun WelcomePage(onClick : () -> Unit = {})
{

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(32.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Top)
	{

		// Main app image
		Image(
			modifier = Modifier
				.fillMaxWidth(0.5f)
				.fillMaxHeight(0.5f),
			painter = painterResource(id = R.mipmap.app),
			contentDescription = "Welcome page")

		// Title of app
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 16.dp),
			text = stringResource(id = R.string.app_name),
			fontSize = MaterialTheme.typography.h4.fontSize,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center)

		// Description of app
		Text(
			modifier = Modifier
				.fillMaxWidth(),
			text = "Log all your climbs and track your progress as you send it.",
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center)

		// Empty space to arrange button at the bottom of the page
		Spacer(modifier = Modifier.weight(1f))

		// Get started button
		Button(
			colors = ButtonDefaults.buttonColors(
				backgroundColor = Color.Magenta,
				contentColor = Color.White),
			shape = RoundedCornerShape(32.dp),
			onClick = onClick)
		{
			Text("GET STARTED",
				modifier = Modifier
					.padding(vertical = 4.dp, horizontal = 16.dp))
		}

	}
}

/**
 * Preview the page.
 */
@Composable
@Preview(showBackground = true)
fun WelcomeOnBoardingScreenPreview() {
	Column(modifier = Modifier.fillMaxSize()) {
		WelcomePage()
	}
}
