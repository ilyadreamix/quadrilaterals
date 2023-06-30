package io.github.ilyadreamix.quadrilaterals.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowOutward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.ilyadreamix.quadrilaterals.shape.QuadrilateralShapeSides

@Composable
internal fun HomeScreenContent() {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenSafePadding = WindowInsets.safeContent.asPaddingValues()

    val side = 54.dp
    val spacing = 12.dp
    val screenPadding = 16.dp
    val cornerRadius = 16.dp
    val quadrilateralHeight = 300.dp

    val firstQuadrilateralSides = QuadrilateralShapeSides(bottomEnd = side to 0.dp)
    val secondQuadrilateralSides = QuadrilateralShapeSides(topStart = side to 0.dp)

    val firstQuadrilateralRelativeWidth = configuration.screenWidthDp.dp / 1.6f

    val firstQuadrilateralWidth = (
        firstQuadrilateralRelativeWidth
            - screenPadding
            - screenSafePadding.calculateLeftPadding(LayoutDirection.Ltr)
            - screenSafePadding.calculateRightPadding(LayoutDirection.Ltr)
    )
    val secondQuadrilateralWidth = (
        (configuration.screenWidthDp.dp - firstQuadrilateralRelativeWidth)
            - screenPadding
            - screenSafePadding.calculateLeftPadding(LayoutDirection.Ltr)
            - screenSafePadding.calculateRightPadding(LayoutDirection.Ltr)
    )

    HomeScreenFirstQuadrilateral(
        width = firstQuadrilateralWidth,
        side = side,
        sides = firstQuadrilateralSides,
        density = density,
        spacing = spacing,
        cornerRadius = cornerRadius
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "144 GB")
            Icon(
                imageVector = Icons.Rounded.ArrowOutward,
                contentDescription = null
            )
        }

        Column(modifier = Modifier.align(Alignment.CenterStart)) {
            Text(
                text = (
                    "photos * video * games" + "\n"
                        + "documents * PDF files" + "\n"
                        + "icons * shots"
                )
            )
            Box(modifier = Modifier.height(16.dp))
        }

        Text(
            text = "Google Drive",
            modifier = Modifier.align(Alignment.BottomStart),
            style = MaterialTheme.typography.displayMedium
        )
    }

    HomeScreenSecondQuadrilateral(
        sides = secondQuadrilateralSides,
        side = side,
        spacing = spacing,
        width = secondQuadrilateralWidth,
        height = quadrilateralHeight,
        firstWidth = firstQuadrilateralWidth,
        cornerRadius = cornerRadius,
        density = density
    ) {
        Icon(
            imageVector = Icons.Rounded.ArrowOutward,
            contentDescription = null,
            modifier = Modifier.align(Alignment.TopEnd)
        )

        Column(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .fillMaxWidth()
        ) {
            Text(
                text = "music",
                modifier = Modifier.padding(start = side / 1.75f)
            )
            Text(
                text = "podcasts",
                modifier = Modifier.padding(start = side / 2f)
            )
            Text(
                text = "clownade",
                modifier = Modifier.padding(start = side / 2.3f)
            )
            Box(modifier = Modifier.height(16.dp))
        }

        Text(
            text = "ICloud",
            modifier = Modifier.align(Alignment.BottomCenter),
            fontSize = 42.sp
        )
    }
}
