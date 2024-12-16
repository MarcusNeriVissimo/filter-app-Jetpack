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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.alura.aluvery.sampledata.sampleSections
import br.com.alura.aluvery.ui.components.CardProductItem
import br.com.alura.aluvery.ui.components.SearchTextField
import br.com.alura.aluvery.ui.components.SectionProducts
import br.com.alura.aluvery.ui.states.HomeScreenUiState
import br.com.alura.aluvery.ui.theme.AluveryTheme
import br.com.alura.aluvery.ui.viewmodels.HomeScreenViewModel

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
) {
    val state = viewModel.uiState
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