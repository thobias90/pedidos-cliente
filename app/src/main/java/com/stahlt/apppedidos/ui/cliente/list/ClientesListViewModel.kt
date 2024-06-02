package com.stahlt.apppedidos.ui.cliente.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stahlt.apppedidos.data.cliente.Cliente
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

data class ClientesListUIState(
    val loading: Boolean = false,
    val hasError: Boolean = false,
    val clientes: List<Cliente> = listOf()
) {
    val success get(): Boolean = !loading && !hasError
}
class ClientesListViewModel: ViewModel() {
    var uiState by mutableStateOf(ClientesListUIState())

    init {
        load()
    }

    fun load() {
        uiState = uiState.copy(
            loading = true,
            hasError = false
        )
        viewModelScope.launch {
            delay(2000)
            val hasErrorLoading = Random.nextBoolean()
            uiState = if(hasErrorLoading) {
                uiState.copy(
                    hasError = true,
                    loading = false
                )
            } else {
                uiState.copy(
                    hasError = false,
                    loading = false,
                    clientes = clientesFake
                )
            }
        }
    }
}