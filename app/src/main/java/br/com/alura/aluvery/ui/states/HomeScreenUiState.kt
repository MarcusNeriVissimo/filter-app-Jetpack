package br.com.alura.aluvery.ui.states

import br.com.alura.aluvery.model.Product

data class HomeScreenUiState(
    val section: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {

    fun isShowSection(): Boolean {
        return searchText.isBlank()
    }

}