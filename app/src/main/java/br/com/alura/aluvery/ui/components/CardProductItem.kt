package br.com.alura.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.R
import br.com.alura.aluvery.extensions.toBrazilianCurrency
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.theme.Indigo400Light
import coil3.compose.AsyncImage
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp,
    isExpanded: Boolean = false
) {
    var expanded by rememberSaveable {
        mutableStateOf(isExpanded)
    }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(150.dp)
            .clickable {
                expanded = !expanded
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Indigo400Light)
                    .padding(16.dp)
            ) {
                Text(
                    color = Color.Black,
                    text = product.name
                )
                Text(
                    color = Color.Black,
                    text = product.price.toBrazilianCurrency()
                )
            }
            if (expanded){
                product.description?.let {
                    Text(
                        text = product.description,
                        Modifier
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardProductItemPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("9.99")
                ),
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemWithDescriptionPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("9.99"),
                    description = LoremIpsum(10).values.first()
                ),
            )
        }
    }
}

@Preview
@Composable
private fun CardProductItemWithDescriptionExpandedPreview() {
    AluveryTheme {
        Surface {
            CardProductItem(
                product = Product(
                    name = "teste",
                    price = BigDecimal("9.99"),
                    description = LoremIpsum(50).values.first()
                ),
                isExpanded = true
            )
        }
    }
}