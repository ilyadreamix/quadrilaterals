package io.github.ilyadreamix.quadrilaterals.home

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.ilyadreamix.quadrilaterals.shape.QuadrilateralShape
import io.github.ilyadreamix.quadrilaterals.shape.QuadrilateralShapeSides
import io.github.ilyadreamix.quadrilaterals.shape.drawQuadrilateralPath
import io.github.ilyadreamix.quadrilaterals.theme.ApplicationBlue
import io.github.ilyadreamix.quadrilaterals.theme.ApplicationOrange

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun HomeScreenFirstQuadrilateral(
    width: Dp,
    side: Dp,
    sides: QuadrilateralShapeSides,
    density: Density,
    spacing: Dp,
    cornerRadius: Dp,
    contentPadding: PaddingValues = PaddingValues(cornerRadius / 2f),
    content: @Composable BoxScope.() -> Unit = {}
) {
    val isPressed = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(
        targetValue = if (isPressed.value) 0.95f else 1f,
        label = "firstQuadrilateralScale"
    )

    Box(
        modifier = Modifier
            .scale(scale.value)
            .height(300.dp)
            .width(width + (side / 2) - (spacing / 2))
            .clip(QuadrilateralShape(sides))
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val path = drawQuadrilateralPath(
                        size = size,
                        sides = sides,
                        density = density
                    )
                    canvas.drawOutline(
                        outline = Outline.Generic(path),
                        paint = Paint().apply {
                            val cornerRadiusPx = cornerRadius.toPx()
                            color = ApplicationOrange
                            pathEffect = PathEffect.cornerPathEffect(cornerRadiusPx)
                        }
                    )
                }
            }
            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_UP -> isPressed.value = false
                    MotionEvent.ACTION_DOWN -> isPressed.value = true
                    MotionEvent.ACTION_CANCEL -> isPressed.value = false
                }
                true
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            content = content
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun HomeScreenSecondQuadrilateral(
    sides: QuadrilateralShapeSides,
    side: Dp,
    spacing: Dp,
    width: Dp,
    height: Dp,
    firstWidth: Dp,
    cornerRadius: Dp,
    density: Density,
    contentPadding: PaddingValues = PaddingValues(cornerRadius / 2f),
    content: @Composable BoxScope.() -> Unit = {}
) {
    val isPressed = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(
        targetValue = if (isPressed.value) 0.95f else 1f,
        label = "secondQuadrilateralScale"
    )

    Box(
        modifier = Modifier
            .height(height)
            .width(width + (side / 2) - (spacing / 2))
            .offset(x = firstWidth - (side / 2) + (spacing / 2))
            .scale(scale.value)
            .clip(QuadrilateralShape(sides))
            .drawBehind {
                drawIntoCanvas { canvas ->
                    val path = drawQuadrilateralPath(
                        size = size,
                        sides = sides,
                        density = density
                    )
                    canvas.drawOutline(
                        outline = Outline.Generic(path),
                        paint = Paint().apply {
                            val cornerRadiusPx = cornerRadius.toPx()
                            color = ApplicationBlue
                            pathEffect = PathEffect.cornerPathEffect(cornerRadiusPx)
                        }
                    )
                }
            }
            .pointerInteropFilter { event ->
                when (event.action) {
                    MotionEvent.ACTION_UP -> isPressed.value = false
                    MotionEvent.ACTION_DOWN -> isPressed.value = true
                    MotionEvent.ACTION_CANCEL -> isPressed.value = false
                }
                true
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding),
            content = content
        )
    }
}
