package io.github.ilyadreamix.quadrilaterals.home

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import io.github.ilyadreamix.quadrilaterals.shape.QuadrilateralShape
import io.github.ilyadreamix.quadrilaterals.shape.QuadrilateralShapeSides
import io.github.ilyadreamix.quadrilaterals.shape.drawQuadrilateralPath
import io.github.ilyadreamix.quadrilaterals.theme.ApplicationBlue
import io.github.ilyadreamix.quadrilaterals.theme.ApplicationOrange

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HomeScreen() {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenSafePadding = WindowInsets.safeContent.asPaddingValues()

    val side = 54.dp
    val spacing = 12.dp
    val screenPadding = 16.dp
    val cornerRadius = 16.dp

    val firstQuadrilateralSides = QuadrilateralShapeSides(
        bottomEnd = side to 0.dp
    )
    val secondQuadrilateralSides = QuadrilateralShapeSides(
        topStart = side to 0.dp
    )

    val firstQuadrilateralWidth = (
        configuration.screenWidthDp.dp / 1.6f
            - screenPadding
            - screenSafePadding.calculateLeftPadding(LayoutDirection.Ltr)
            - screenSafePadding.calculateRightPadding(LayoutDirection.Ltr)
    )

    val secondQuadrilateralWidth = (
        configuration.screenWidthDp.dp - (configuration.screenWidthDp.dp / 1.6f)
            - screenPadding
            - screenSafePadding.calculateLeftPadding(LayoutDirection.Ltr)
            - screenSafePadding.calculateRightPadding(LayoutDirection.Ltr)
    )

    val firstQuadrilateralPressed = remember { mutableStateOf(false) }
    val firstQuadrilateralScale = animateFloatAsState(
        targetValue = if (firstQuadrilateralPressed.value) 0.95f else 1f,
        label = "firstQuadrilateralScale"
    )

    val secondQuadrilateralPressed = remember { mutableStateOf(false) }
    val secondQuadrilateralScale = animateFloatAsState(
        targetValue = if (secondQuadrilateralPressed.value) 0.95f else 1f,
        label = "secondQuadrilateralScale"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .safeContentPadding()
            .padding(screenPadding)
    ) {
        Box(
            modifier = Modifier.scale(firstQuadrilateralScale.value)
                .height(300.dp)
                .width(firstQuadrilateralWidth + side / 2 - spacing / 2)
                .clip(QuadrilateralShape(firstQuadrilateralSides))
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val path = drawQuadrilateralPath(
                            size = size,
                            sides = firstQuadrilateralSides,
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
                        MotionEvent.ACTION_UP -> firstQuadrilateralPressed.value = false
                        MotionEvent.ACTION_DOWN -> firstQuadrilateralPressed.value = true
                        MotionEvent.ACTION_CANCEL -> firstQuadrilateralPressed.value = false
                    }
                    true
                }
        )
        Box(
            modifier = Modifier
                .height(300.dp)
                .width(secondQuadrilateralWidth + side / 2 - spacing / 2)
                .offset(x = firstQuadrilateralWidth - side / 2 + spacing / 2)
                .scale(secondQuadrilateralScale.value)
                .clip(QuadrilateralShape(secondQuadrilateralSides))
                .drawBehind {
                    drawIntoCanvas { canvas ->
                        val path = drawQuadrilateralPath(
                            size = size,
                            sides = secondQuadrilateralSides,
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
                        MotionEvent.ACTION_UP -> secondQuadrilateralPressed.value = false
                        MotionEvent.ACTION_DOWN -> secondQuadrilateralPressed.value = true
                        MotionEvent.ACTION_CANCEL -> secondQuadrilateralPressed.value = false
                    }
                    true
                }
        )
    }
}
