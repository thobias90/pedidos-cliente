package com.stahlt.apppedidos.ui.cliente.list

import androidx.compose.runtime.mutableStateOf
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
    val uiState = mutableStateOf(ClientesListUIState())

    init {
        load()
    }

    fun load() {
        uiState.value = uiState.value.copy(
            loading = true,
            hasError = false
        )
        viewModelScope.launch {
            delay(2000)
            val hasErrorLoading = Random.nextBoolean()
            uiState.value = if(hasErrorLoading) {
                uiState.value.copy(
                    hasError = true,
                    loading = false
                )
            } else {
                uiState.value.copy(
                    hasError = false,
                    loading = false,
                    clientes = clientesFake
                )
            }
        }
    }
}