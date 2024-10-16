package com.getyourguide.interview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun RatingBar(
  modifier: Modifier = Modifier,
  rating: Float,
  rounding: Boolean = true,
) {
  var currentRating by remember(rating) { mutableFloatStateOf(rating) }
  val roundedRating = buildRoundedRating(currentRating, rounding)
  Row(
    modifier = modifier
      .height(16.dp)
      .width(IntrinsicSize.Min)
      .wrapContentWidth(),
    horizontalArrangement = Arrangement.spacedBy(1.dp),
  ) {
    for (step in 1..5) {
      val stepRating = when {
        roundedRating > step -> 1f
        step.rem(roundedRating) < 1 -> roundedRating - (step - 1f)
        else -> 0f
      }
      RatingStar(
        modifier = Modifier
          .semantics {
            testTag = "RatingStar:$step"
          },
        rating = stepRating,
      )
    }
  }
}

@Composable
private fun RatingStar(
  modifier: Modifier = Modifier,
  rating: Float,
) {
  val starDrawable = R.drawable.ic_star_fill
  Box(
    modifier = modifier
      .fillMaxHeight()
      .aspectRatio(1f),
  ) {
    Icon(
      modifier = Modifier
        .fillMaxSize()
        .drawWithContent {
          clipRect(left = size.width * rating) {
            this@drawWithContent.drawContent()
          }
        },
      painter = painterResource(id = starDrawable),
      tint = MaterialTheme.colorScheme.surfaceSecondary,
      contentDescription = null,
    )
    Icon(
      modifier = Modifier
        .fillMaxSize()
        .starClip(rating),
      painter = painterResource(id = starDrawable),
      tint = Colors.BASKING_YELLOW,
      contentDescription = null,
    )
  }
}

private fun Modifier.starClip(rating: Float): Modifier {
  return this.drawWithContent {
    clipRect(right = size.width * rating) {
      this@drawWithContent.drawContent()
    }
  }
}


private fun buildRoundedRating(rating: Float, rounding: Boolean) =
  if (rounding) {
    (rating * 2).roundToInt() / 2f
  } else {
    rating
  }
