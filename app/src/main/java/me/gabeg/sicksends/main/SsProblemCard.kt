package me.gabeg.sicksends.main

//import androidx.compose.foundation.layout.*
//import androidx.compose.material.Divider
//import androidx.compose.material.Icon
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Text
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import me.gabeg.sicksends.shared.howDidItFeelScaleToString

// Problem card
//Card(
//modifier = Modifier
//.fillMaxWidth()
//.padding(horizontal = 16.dp, vertical = 8.dp),
//elevation = 10.dp)
//{
//
//	// Container for all the content in the card
//	Row(
//		modifier = Modifier
//			.fillMaxWidth()
//			.height(IntrinsicSize.Min)
//			.padding(16.dp))
//	{
//
//		// Show the grade and how the climb felt
//		Column(
//			modifier = Modifier
//				.fillMaxHeight(),
//			horizontalAlignment = Alignment.CenterHorizontally,
//			verticalArrangement = Arrangement.Center)
//		{
//
//			// Grade
//			Text("${it.grade}",
//				textAlign = TextAlign.Center,
//				fontSize = MaterialTheme.typography.h4.fontSize,
//				fontWeight = FontWeight.Bold)
//
//			// How the climb felt, if specified
//			if (it.howDidItFeelScale > 0 && it.howDidItFeelScale != 3)
//			{
//				Text(dataStore.howDidItFeelScaleToString(it.howDidItFeelScale),
//					fontSize = MaterialTheme.typography.caption.fontSize)
//			}
//		}
//
//		//Text("Timestamp    : ${it.timestamp}")
//
//		Divider(
//			modifier = Modifier
//				.fillMaxHeight()
//				.padding(horizontal = 16.dp)
//				.width(1.dp),
//			color = Color.Blue)
//
//		// TODO: Place = Location icon
//		// TODO: Visibility = Eye icon
//		//Column()
//		//	{
//
//		//		Text("Num Attempt : ${it.numAttempt}")
//		//	}
//
//		//Spacer(modifier = Modifier.weight(1f))
//
//		Box(modifier = Modifier.fillMaxHeight())
//		{
//			Column(
//				modifier = Modifier
//					.align(Alignment.BottomStart)
//					.width(IntrinsicSize.Max))
//			{
//
//				// Name of the climb
//				if (!it.name.isNullOrEmpty())
//				{
//					Text("${it.name}",
//						modifier = Modifier.fillMaxWidth(),
//						textAlign = TextAlign.Start)
//				}
//
//				// Location of the climb
//				//if (!it.locationName.isNullOrEmpty())
//				if (it.name == "Yellow")
//				{
//					Text("${it.locationName}",
//						modifier = Modifier.fillMaxWidth(),
//						textAlign = TextAlign.Start)
//				}
//			}
//		}
//
//		Spacer(modifier = Modifier.weight(1f))
//
//		// Container for all icons
//		Box(
//			modifier = Modifier
//				.fillMaxHeight())
//		{
//
//			// Align the icons in a row
//			Row(modifier = Modifier.align(Alignment.TopEnd))
//			{
//				// Project
//				if (it.isProject)
//				{
//					Icon(Icons.Default.Construction,
//						modifier = Modifier
//							.size(18.dp),
//						contentDescription = "Project")
//				}
//
//				// Outdoor (Icon=Forest not found)
//				if (it.isOutdoor)
//				{
//					Icon(Icons.Default.Park,
//						modifier = Modifier
//							.size(18.dp),
//						contentDescription = "Outdoor")
//				}
//
//				// Flash
//				if (it.isFlash)
//				{
//					Icon(Icons.Default.Bolt,
//						modifier = Modifier
//							.size(18.dp),
//						contentDescription = "Flash")
//				}
//			}
//
//		}
//
//		//Text("Hold Type    : ${it.holdType}")
//		//Text("Route Feature: ${it.routeFeatureType}")
//		//Text("Technique    : ${it.climbingTechniqueType}")
//		//Text("Note         : ${it.note}")
//	}
//}



//// Problem card
//Card(
//modifier = Modifier
////.fillMaxWidth()
//.padding(horizontal = 16.dp, vertical = 8.dp)
//.width(IntrinsicSize.Min),
////.width(width),
//elevation = 10.dp)
//{
//
//	// Container for all the content in the card
//	Column(
//		modifier = Modifier
//			//.fillMaxWidth()
//			//.height(IntrinsicSize.Min)
//			.padding(16.dp),
//		horizontalAlignment = Alignment.CenterHorizontally,
//		verticalArrangement = Arrangement.Center)
//	{
//
//		// Grade
//		Text("${it.grade}",
//			textAlign = TextAlign.Center,
//			fontSize = MaterialTheme.typography.h4.fontSize,
//			fontWeight = FontWeight.Bold)
//
//		// How the climb felt, if specified
//		if (it.howDidItFeelScale > 0 && it.howDidItFeelScale != 3)
//		{
//			Text(howDidItFeelScaleToString(it.howDidItFeelScale),
//				fontSize = MaterialTheme.typography.caption.fontSize)
//		}
//
//		//Text("Timestamp    : ${it.timestamp}")
//
//		Divider(
//			modifier = Modifier
//				//.fillMaxHeight()
//				.fillMaxWidth()
//				.padding(vertical = 8.dp)
//				.height(1.dp),
//			//.width(1.dp),
//			color = Color.Blue)
//
//		// TODO: Place = Location icon
//		// TODO: Visibility = Eye icon
//		//Text("Num Attempt : ${it.numAttempt}")
//		//Spacer(modifier = Modifier.weight(1f))
//
//		// Name of the climb
//		if (!it.name.isNullOrEmpty())
//		{
//			Text("${it.name}",
//				modifier = Modifier.fillMaxWidth(),
//				textAlign = TextAlign.Start)
//		}
//
//		// Location of the climb
//		//if (!it.locationName.isNullOrEmpty())
//		if (it.name == "Yellow")
//		{
//			Text("${it.locationName}",
//				modifier = Modifier.fillMaxWidth(),
//				textAlign = TextAlign.Start)
//		}
//
//		//Spacer(modifier = Modifier.weight(1f))
//
//		// Container for all icons
//		Row()
//		{
//			// Project
//			if (it.isProject)
//			{
//				Icon(Icons.Default.Construction,
//					modifier = Modifier
//						.size(18.dp),
//					contentDescription = "Project")
//			}
//
//			// Outdoor (Icon=Forest not found)
//			if (it.isOutdoor)
//			{
//				Icon(Icons.Default.Park,
//					modifier = Modifier
//						.size(18.dp),
//					contentDescription = "Outdoor")
//			}
//
//			// Flash
//			if (it.isFlash)
//			{
//				Icon(Icons.Default.Bolt,
//					modifier = Modifier
//						.size(18.dp),
//					contentDescription = "Flash")
//			}
//		}
//
//		//Text("Hold Type    : ${it.holdType}")
//		//Text("Route Feature: ${it.routeFeatureType}")
//		//Text("Technique    : ${it.climbingTechniqueType}")
//		//Text("Note         : ${it.note}")
//	}
//}
