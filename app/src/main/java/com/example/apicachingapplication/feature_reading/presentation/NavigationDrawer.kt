package com.example.apicachingapplication.feature_reading.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Drawer palette: a warm brown a few shades lighter than the main
// background so it stands out, with a hint of gold, and cream text.
val DrawerBrown = Color(0xFF2B1E14)      // drawer background
val DrawerBrownGold = Color(0xFF3A2A17)  // slightly golden brown for the header
val DrawerGold = Color(0xFFD4AF37)       // icons, heading, accent lines
val DrawerCream = Color(0xFFF5EBDD)      // all text

@Preview
@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                // Gentle gradient from golden brown down into the drawer brown
                .background(Brush.verticalGradient(listOf(DrawerBrownGold, DrawerBrown)))
                .padding(horizontal = 24.dp, vertical = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "The Noble Quran",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DrawerCream
                )
            )
        }
        // Thin gold line separating the header from the menu
        HorizontalDivider(color = DrawerGold.copy(alpha = 0.7f), thickness = 1.dp)
    }
}

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    onMenuItemClicked: (MenuItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(DrawerBrown)
            .padding(vertical = 8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onMenuItemClicked(item) }
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.title,
                    tint = DrawerGold
                )
                Spacer(Modifier.width(16.dp))
                Text(
                    text = item.title,
                    style = TextStyle(fontSize = 17.sp, lineHeight = 24.sp),
                    color = DrawerCream
                )
            }
            // Faint gold separator between items (not after the last one)
            if (index < items.lastIndex) {
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 24.dp),
                    color = DrawerGold.copy(alpha = 0.18f),
                    thickness = 1.dp
                )
            }
        }
        item { Spacer(Modifier.height(8.dp)) }
    }
}