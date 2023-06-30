package io.github.ilyadreamix.quadrilaterals.shape

import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
data class QuadrilateralShapeSides(
    val topStart: Pair<Dp, Dp> = 0.dp to 0.dp,
    val topEnd: Pair<Dp, Dp> = 0.dp to 0.dp,
    val bottomStart: Pair<Dp, Dp> = 0.dp to 0.dp,
    val bottomEnd: Pair<Dp, Dp> = 0.dp to 0.dp
)
