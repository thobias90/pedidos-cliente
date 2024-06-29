package com.stahlt.apppedidos.ui.cliente.details

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stahlt.apppedidos.data.cliente.Cliente
import com.stahlt.apppedidos.data.cliente.network.ApiClientes
import kotlinx.coroutines.launch

data class ClienteDetailsUiState(
    val isLoading: Boolean = false,
    val hasErrorLoading: Boolean = false,
    val cliente: Cliente = Cliente(),
    val isDeleting: Boolean = false,
    val hasErrorDeleting: Boolean = false,
    val clienteDeleted: Boolean = false,
    val showConfirmationDialog: Boolean = false
) {
    val isSuccessLoading get(): Boolean = !isLoading && !hasErrorLoading
}
class ClienteDetailsViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    private val tag: String = "ClienteDetailsViewModel"
    private val clienteId: Int = savedStateHandle.get<Int>("id")!!

    var uiState: ClienteDetailsUiState by mutableStateOf(ClienteDetailsUiState())

    init {
        loadCliente()
    }

    fun loadCliente() {
        uiState = uiState.copy(
            isLoading = true,
            hasErrorLoading = false
        )
        viewModelScope.launch {
            uiState = try {
                uiState.copy(
                    isLoading = false,
                    cliente = ApiClientes.retrofitService.findById(clienteId)
                )
            } catch (ex: Exception) {
                Log.d(tag, "Erro ao carregadr dados do cliente com id ${clienteId}", ex)
                uiState.copy(
                    isLoading = false,
                    hasErrorLoading = true
                )
            }
        }
    }

    fun showConfirmationDialog() {
        uiState = uiState.copy(
            showConfirmationDialog = true
        )
    }

    fun dismissConfirmationDialog() {
        uiState = uiState.copy(
            showConfirmationDialog = false
        )
    }

    fun delete() {
        uiState = uiState.copy(
            isDeleting = true,
            hasErrorDeleting = false,
            showConfirmationDialog = false
        )
        viewModelScope.launch {
            uiState = try {
                ApiClientes.retrofitService.delete(clienteId)
                uiState.copy(
                    isDeleting = false,
                    clienteDeleted = true
                )
            } catch(ex: Exception) {
                Log.d(tag, "Erro ao deletar cliente ${clienteId}", ex)
                uiState.copy(
                    isDeleting = false,
                    hasErrorDeleting = true
                )
            }
        }
    }
}