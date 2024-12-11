package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.model.Product
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.components.SectionProducts
import br.com.alura.aluvery.ui.theme.AluveryTheme

@Composable
fun HomeScreen(sections: Map<String, List<Product>>, searchText: String = ""){
    Column {
        var text by remember { mutableStateOf(searchText) }
        SearchTextField(searchText = text, onSearchChanged = {
            text = it
        })
        val searchedProducts = remember(text) {
            sampleProducts.filter { product ->
                product.name.contains(text, ignoreCase = true) ||
                        product.description?.contains(text, ignoreCase = true) ?: false
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if(text.isBlank()){
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        SectionProducts(
                            title = title,
                            productsList = products
                        )
                    }

                }
            }else {
                items(searchedProducts) { p ->
                    CardProductItem(product = p, Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchPreview(){
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections, "cho")
        }
    }
}