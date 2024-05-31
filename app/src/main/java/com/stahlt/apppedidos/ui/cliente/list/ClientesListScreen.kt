package com.stahlt.apppedidos.ui.cliente.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stahlt.apppedidos.R
import com.stahlt.apppedidos.data.cliente.Cliente
import com.stahlt.apppedidos.data.cliente.Endereco
import com.stahlt.apppedidos.ui.theme.AppPedidosTheme

@Composable
fun ClientesListScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            ClientesTopBar()
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.adicionar)
                )
            }
        }
    ) { paddingValues ->
        LoadingClientes(modifier = Modifier.padding(paddingValues))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ClientesTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        title = {Text(stringResource(R.string.clientes))},
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = stringResource(R.string.atualizar)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ClientesTopBarPreview() {
    AppPedidosTheme {
        ClientesTopBar()
    }
}

@Composable
private fun LoadingClientes(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            modifier = Modifier.size(60.dp)
        )
        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.carregando_clientes),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true, heightDp = 300)
@Composable
private fun LoadingClientesPreview(modifier: Modifier = Modifier) {
    AppPedidosTheme {
        LoadingClientes()
    }
}

@Composable
private fun ErrorLoadingClientes(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.CloudOff,
            contentDescription = stringResource(R.string.erro_ao_carregar),
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(80.dp)
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
            text = stringResource(R.string.nao_foi_poss_vel_carregar_os_clientes),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
            text = stringResource(R.string.aguarde_um_momento_e_tente_novamente),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
        ElevatedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(stringResource(R.string.tentar_novamente))
        }
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
private fun ErrorLoadingClientesPreview(modifier: Modifier = Modifier) {
    AppPedidosTheme {
        ErrorLoadingClientes()
    }
}

@Composable
private fun ClientesList(
    modifier: Modifier = Modifier,
    clientes: List<Cliente> = listOf<Cliente>()
) {
    if (clientes.isEmpty()) {
        EmptyList(modifier = modifier)
    } else {
        // Exibe lista de clientes
    }
}

@Composable
private fun EmptyList(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nenhum cliente encontrado",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Adicione clientes pressionando o \"+\"",
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview(showBackground = true, heightDp = 400)
@Composable
fun EmptyListPreview(modifier: Modifier = Modifier) {
    AppPedidosTheme {
        EmptyList()
    }
}

@Composable
private fun FilledList(
    modifier: Modifier = Modifier,
    clientes: List<Cliente>
) {
    Column(
        modifier = modifier.padding(vertical = 4.dp)
    ) {
        clientes.forEachIndexed { index, cliente ->
            ListItem(
                modifier = modifier.padding(8.dp),
                headlineContent = {
                    Text(
                        text = "${cliente.id} - ${cliente.nome}",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                supportingContent = {
                    Text(
                        text = cliente.endereco.descricao,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Selecionar",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            )
            if (index < clientes.lastIndex) {
                HorizontalDivider()
            }
        }
    }
}
@Preview(showBackground = true, heightDp = 400)
@Composable
fun FilledListPreview(modifier: Modifier = Modifier) {
    AppPedidosTheme {
        FilledList(clientes = listOf(
            Cliente(
                id = 1,
                nome = "Joao",
                cpf = "1234",
                telefone = "1234",
                endereco = Endereco(
                    logradouro = "Rua tal",
                    numero = 13,
                    cidade = "Joinville"
                )
            ),
            Cliente(
                id = 2,
                nome = "Jose",
                cpf = "4123",
                telefone = "4123",
                endereco = Endereco(
                    logradouro = "Ituporanga",
                    numero = 12,
                    cidade = "Pato Branco"
                )
            )
        ))
    }
}