package com.stahlt.apppedidos.ui.cliente.list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stahlt.apppedidos.data.cliente.Cliente
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ClientesListViewModel: ViewModel() {
    val loading = mutableStateOf(false)
    val hasError = mutableStateOf(false)
    val clientes = mutableStateOf(listOf<Cliente>())

    init {
        load()
    }

    fun load() {
        loading.value = true
        hasError.value = false
        viewModelScope.launch {
            delay(2000)
            val hasErrorLoading = Random.nextBoolean()
            if (hasErrorLoading) {
                hasError.value = true
            } else {
                clientes.value = clientesFake
            }
            loading.value = false
        }
    }
}