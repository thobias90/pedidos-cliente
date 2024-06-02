package com.stahlt.apppedidos.ui.cliente.list

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stahlt.apppedidos.data.cliente.Cliente
import com.stahlt.apppedidos.data.cliente.network.ApiClientes
import kotlinx.coroutines.launch

data class ClientesListUIState(
    val loading: Boolean = false,
    val hasError: Boolean = false,
    val clientes: List<Cliente> = listOf()
) {
    val success get(): Boolean = !loading && !hasError
}
class ClientesListViewModel: ViewModel() {
    private val tag: String = "ClientesListViewModel"
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
            uiState = try {
                val clientes = ApiClientes.retrofitService.findAll()
                uiState.copy(
                    loading = false,
                    clientes = clientes
                )
            } catch (ex: Exception) {
                Log.d(tag, "Erro ao carregar lista de clientes", ex)
                uiState.copy(
                    hasError = true,
                    loading = false
                )
            }
        }
    }
}