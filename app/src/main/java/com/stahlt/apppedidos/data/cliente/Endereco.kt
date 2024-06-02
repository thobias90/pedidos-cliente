package com.stahlt.apppedidos.data.cliente

import kotlinx.serialization.Serializable

@Serializable
data class Endereco(
    val cep: String = "",
    val logradouro: String = "",
    val numero: Int = 0,
    val complemento: String = "",
    val bairro: String = "",
    val cidade: String = ""
) {
    val descricao get(): String =  "$logradouro, $numero, $cidade"
}