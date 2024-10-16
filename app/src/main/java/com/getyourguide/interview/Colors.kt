package com.getyourguide.interview

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal object Colors {
  internal val GREY_100 = Color(0xFFEBEEF1)
  internal val BASKING_YELLOW = Color(0xFFFFD938)
  internal val BLUE_900 = Color(0xFF1A2B49)
  internal val WHITE = Color(0xFFFFFFFF)
  internal val GREY_800 = Color(0xFF31343D)
  internal val GUIDING_RED = Color(0xFFFE5533)

}


val ColorScheme.labelPrimary: Color
  @Composable
  get() = if (!isSystemInDarkTheme()) Colors.BLUE_900 else Colors.WHITE

val ColorScheme.surfaceSecondary: Color
  @Composable
  get() = if (!isSystemInDarkTheme()) Colors.GREY_100 else Colors.GREY_800
