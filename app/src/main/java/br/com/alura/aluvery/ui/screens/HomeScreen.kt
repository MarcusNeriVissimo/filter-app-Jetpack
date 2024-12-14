package br.com.alura.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import br.com.alura.aluvery.sampledata.sampleCandies
import br.com.alura.aluvery.sampledata.sampleDrinks
import br.com.alura.aluvery.sampledata.sampleProducts
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.components.SectionProducts
import br.com.alura.aluvery.ui.theme.AluveryTheme

class HomeScreenUiState(
    val section: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {

    fun isShowSection(): Boolean {
        return searchText.isBlank()
    }

}

@Composable
fun HomeScreen(products: List<Product>) {
    val sections = mapOf(
        "Todos produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )

    var text by remember {
        mutableStateOf("")
    }

    fun constainInNameOrDescription() = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) ||
                product.description?.contains(
                    text,
                    ignoreCase = true
                ) ?: false
    }

    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(constainInNameOrDescription()) + products.filter(constainInNameOrDescription())
        } else emptyList()
    }

    val state = remember(products, text) {
        HomeScreenUiState(
            section = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
){
    Column {
        val sections = state.section
        val text = state.searchText
        val searchedProducts = state.searchedProducts
        SearchTextField(
            searchText = text,
            onSearchChanged = state.onSearchChange,
            Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if(state.isShowSection())
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        SectionProducts(
                            title = title,
                            productsList = products
                        )
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
            HomeScreen(HomeScreenUiState(sampleSections))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchPreview(){
    AluveryTheme {
        Surface {
            HomeScreen(HomeScreenUiState(searchText = "chocolate", section = sampleSections))
        }
    }
}