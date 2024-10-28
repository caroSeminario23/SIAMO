package com.example.siamo

import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.siamo.data.RegistroBitacora
import com.example.siamo.data.local.RegistrosBitacora
import com.example.siamo.ui.theme.SIAMOTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Bitacora(
    modifier: Modifier = Modifier
) {
    Scaffold (
        topBar = { BitacoraTopAppBar() },
        containerColor = MaterialTheme.colorScheme.background
    ) {
        paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            item {
                Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            }
            val registrosBitacora = RegistrosBitacora.registrosBitacora
            items(registrosBitacora.size) { index ->
                val registro = registrosBitacora[index]
                RegistroBitactoraItem(
                    registroBitacora = registro,
                    modifier = Modifier
                        .padding(
                            start = dimensionResource(id = R.dimen.padding_medium),
                            top = dimensionResource(id = R.dimen.padding_small),
                            end = dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_small)
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BitacoraTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
                Text(
                    text = stringResource(id = R.string.topbar_bitacora),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondaryContainer
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.height(dimensionResource(id = R.dimen.topbar_height))
    )
}

@Composable
fun RegistroBitactoraItem(
    registroBitacora: RegistroBitacora,
    modifier: Modifier = Modifier
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val color by animateColorAsState(
        targetValue = if (expanded) MaterialTheme.colorScheme.primaryContainer
        else MaterialTheme.colorScheme.secondaryContainer,
        label = "colorFondo"
    )
    Card (modifier = modifier) {
        Column (
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
                .background(color = color)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_small))
            ) {
                BitacoraDescripcion(
                    descripcion = registroBitacora.problema.descripcionID,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(0.2f)
                )
                Spacer(modifier = Modifier.weight(0.0001f))
                BitacoraBoton(
                    expanded = expanded,
                    onClick = { expanded = !expanded },
                    modifier = Modifier.wrapContentWidth()
                )
            }
            if (expanded) {
                BitacoraMasInformacion(
                    detalleProblema = registroBitacora.problema.detalleID,
                    detalleSolucion = registroBitacora.solucion.descripcionID,
                    modifier = Modifier.padding(
                        start = dimensionResource(id = R.dimen.padding_medium),
                        //top = dimensionResource(id = R.dimen.padding_small),
                        end = dimensionResource(id = R.dimen.padding_medium),
                        bottom = dimensionResource(id = R.dimen.padding_medium)
                    )
                )
            }
        }
    }
}

@Composable
fun BitacoraDescripcion(
    @StringRes descripcion: Int,
    modifier: Modifier = Modifier
        .padding(dimensionResource(id = R.dimen.padding_small))
) {
    Box(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small))
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = stringResource(id = descripcion),
            style = MaterialTheme.typography.displaySmall
                .copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun BitacoraBoton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess
            else Icons.Filled.ExpandMore,
            contentDescription = if (expanded) "Contraer" else "Expandir",
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun BitacoraMasInformacion(
    @StringRes detalleProblema: Int,
    @StringRes detalleSolucion: Int,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier) {
        Text(
            text = stringResource(id = detalleProblema),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Text(
            text = "Solución:",
            style = MaterialTheme.typography.bodyLarge
                .copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.padding_medium),
                bottom = dimensionResource(id = R.dimen.padding_small)
            )
        )
        Text(
            text = stringResource(id = detalleSolucion),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BitacoraLightThemePreview() {
    SIAMOTheme(darkTheme = false) {
        Bitacora()
    }
}

@Preview(showBackground = true)
@Composable
fun BitacoraDarkThemePreview() {
    SIAMOTheme(darkTheme = true) {
        Bitacora()
    }
}