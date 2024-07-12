package com.genxsol.home.presentation.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.genxsol.core.components.CoilImageComponent
import com.genxsol.home.domain.model.ProductItem
import com.genxsol.home.presentation.uievent.HomeUIEvent

@Composable
fun SlidableSection(
    productItems: List<ProductItem>,
    sectionTitle: String,
    onProductClick: (HomeUIEvent) -> Unit
) {
    Column {
        SectionTitle(title = sectionTitle)
        LazyRow {
            items(items = productItems, key = { product ->
               product.productId
            }) { product ->
                HorizontalCard(
                    product.productImage,
                    product.text,
                    product.subText,
                    onClick = { onProductClick(HomeUIEvent.OnProductClicked) })
            }
        }
    }
}

@Composable
fun HorizontalCard(
    imageUri: String,
    title: String?,
    subTitle: String?,
    onClick: () -> Unit
) {
    val cardModifier = remember {
        Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .fillMaxWidth()
            .height(200.dp)
            .clickable(onClick = onClick)
    }
    Card(
        modifier = cardModifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            CoilImageComponent(
                imageUri,
                contentDescription = "Slidable Image",
                modifier = Modifier
                    .size(100.dp)
                    .padding(8.dp)
                    .align(Alignment.CenterHorizontally)
            )

            title?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
            subTitle?.let {
                Text(
                    text = it,
                    color = Color.Gray,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.align(Alignment.Start)
                )
            }
        }
    }
}