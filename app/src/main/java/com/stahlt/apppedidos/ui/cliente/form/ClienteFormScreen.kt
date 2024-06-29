package com.stahlt.apppedidos.ui.cliente.form

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stahlt.apppedidos.R
import com.stahlt.apppedidos.ui.theme.AppPedidosTheme

@Composable
fun ClienteFormScreen(
    modifier: Modifier = Modifier ,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            FormTopAppBar(
                isNewCliente = false ,
                onBackPressed = onBackPressed ,
                onSavePressed = {}
            )
        }
    ) { innerPadding->
        FormContent(modifier = modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FormTopAppBar(
    modifier: Modifier = Modifier ,
    isNewCliente: Boolean ,
    onBackPressed: () -> Unit ,
    onSavePressed: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer ,
            titleContentColor = MaterialTheme.colorScheme.primary
        ) ,
        title = {
            Text(
                if (isNewCliente) stringResource(R.string.novo_cliente)
                else stringResource(R.string.editar_cliente)
            )
        } ,
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
                    contentDescription = stringResource(
                        R.string.voltar
                    )
                )
            }
        } ,
        actions = {
            IconButton(onClick = onSavePressed) {
                Icon(
                    imageVector = Icons.Filled.Check ,
                    contentDescription = stringResource(R.string.salvar)
                )
            }
        } ,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun FormTopAppBarPreview(modifier: Modifier = Modifier) {
    AppPedidosTheme {
        FormTopAppBar(
            isNewCliente = true ,
            onBackPressed = {} ,
            onSavePressed = {}
        )
    }
}

@Composable
private fun FormContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 16.dp)
            .verticalScroll(
                rememberScrollState()
            )
    ) {
        ClienteId(id = 1)
        FormTextField(
            label = stringResource(id = R.string.nome) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardCapitalization = KeyboardCapitalization.Words
        )
        FormTextField(
            label = stringResource(id = R.string.cpf) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardType = KeyboardType.Number
        )
        FormTextField(
            label = stringResource(id = R.string.telefone) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardType = KeyboardType.Number
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        FormTitle(text = stringResource(R.string.endereco))
        FormTextField(
            label = stringResource(id = R.string.cep) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardType = KeyboardType.Number
        )
        FormTextField(
            label = stringResource(id = R.string.logradouro) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardCapitalization = KeyboardCapitalization.Words
        )
        FormTextField(
            label = stringResource(id = R.string.numero) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardType = KeyboardType.Number
        )
        FormTextField(
            label = stringResource(id = R.string.bairro) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardCapitalization = KeyboardCapitalization.Words
        )
        FormTextField(
            label = stringResource(id = R.string.cidade) ,
            value = "",
            onValueChanged = {},
            errorMessageCode = null,
            keyboardCapitalization = KeyboardCapitalization.Words,
            keyboardImeAction = ImeAction.Done
        )
    }
}

@Composable
private fun ClienteId(
    modifier: Modifier = Modifier ,
    id: Int
) {
    if (id > 0) {
       FormTitle(text = "${stringResource(R.string.codigo)} - $id")
    } else {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FormTitle(text = "${stringResource(R.string.codigo)} = ")
            Text(
                text = "a definir",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                )
            )
        }
    }
}

@Composable
private fun FormTitle(
    modifier: Modifier = Modifier ,
    text: String
) {
    Text(
        modifier = modifier.padding(start = 16.dp) ,
        text = text ,
        style = MaterialTheme.typography.titleLarge ,
        color = MaterialTheme.colorScheme.primary
    )
}

@Composable
private fun FormTextField(
    modifier: Modifier = Modifier ,
    label: String ,
    value: String ,
    onValueChanged: (String) -> Unit ,
    enabled: Boolean = true ,
    @StringRes
    errorMessageCode: Int? = null,
    keyboardCapitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    keyboardImeAction: ImeAction = ImeAction.Next,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    OutlinedTextField(
        value = value ,
        onValueChange = onValueChanged,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp ,horizontal = 16.dp),
        label = { Text(label)},
        maxLines = 1,
        enabled = enabled,
        isError = errorMessageCode != null,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            capitalization = keyboardCapitalization,
            imeAction = keyboardImeAction,
            keyboardType = keyboardType
        ),
        visualTransformation = visualTransformation
    )
    errorMessageCode?.let {
        Text(
            text = stringResource(id = errorMessageCode) ,
            color = MaterialTheme.colorScheme.error ,
            style = MaterialTheme.typography.labelSmall ,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FormContentPreview(modifier: Modifier = Modifier) {
    AppPedidosTheme {
        FormContent()
    }
}