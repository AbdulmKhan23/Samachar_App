package com.khan.samacharapp.presentation.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.khan.samacharapp.presentation.onBoarding.Dimesions.IndicatorSize
import com.khan.samacharapp.ui.theme.BlueGray

@Composable
fun PageIndicator(
    modifier: Modifier=Modifier,
    pageSize:Int,
    selectedPage:Int,
    selectedPageColor: Color = MaterialTheme.colorScheme.primary,
    unselectedPageColor:Color= BlueGray
) {
    Row(modifier=Modifier, horizontalArrangement = Arrangement.SpaceBetween) {
        repeat(pageSize) { page ->
            Box(
                modifier = Modifier
                    .size(IndicatorSize)
                    .clip(CircleShape)
                    .background(color = if (page == selectedPage) selectedPageColor else unselectedPageColor)
            )
        }
    }
}