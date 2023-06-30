package io.github.ilyadreamix.quadrilaterals.shape

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class QuadrilateralShape(
    private val sides: QuadrilateralShapeSides = QuadrilateralShapeSides()
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        path = drawQuadrilateralPath(
            size = size,
            sides = sides,
            density = density
        )
    )
}
