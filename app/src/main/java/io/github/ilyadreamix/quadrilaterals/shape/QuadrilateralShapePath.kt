package io.github.ilyadreamix.quadrilaterals.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

fun drawQuadrilateralPath(
    size: Size,
    sides: QuadrilateralShapeSides,
    density: Density
): Path {
    val (topStartX, topStartY) = sides.topStart.toPx(density)
    val (topEndX, topEndY) = sides.topEnd.toPx(density)
    val (bottomStartX, bottomStartY) = sides.bottomStart.toPx(density)
    val (bottomEndX, bottomEndY) = sides.bottomEnd.toPx(density)

    return Path().apply {
        moveTo(topStartX, topStartY)
        lineTo(size.width - topEndX, topEndY)
        lineTo(size.width - bottomEndX, size.height - bottomEndY)
        lineTo(bottomStartX, size.height - bottomStartY)
        lineTo(topStartX, topStartY)
        close()
    }
}

internal fun Pair<Dp, Dp>.toPx(density: Density) =
    with(density) { first.toPx() to second.toPx() }
