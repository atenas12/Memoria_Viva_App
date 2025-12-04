package com.aplicativomemoriaviva.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aplicativomemoriaviva.data.database.AppDatabase
import com.aplicativomemoriaviva.data.repository.AppRepository
import com.aplicativomemoriaviva.ui.screens.login.LoginScreen
import com.aplicativomemoriaviva.ui.screens.menu.MenuScreen
import com.aplicativomemoriaviva.ui.screens.idoso.IdosoListScreen
import com.aplicativomemoriaviva.ui.screens.idoso.IdosoFormScreen
import com.aplicativomemoriaviva.ui.screens.idoso.IdosoDetailScreen
import com.aplicativomemoriaviva.ui.screens.medicacao.MedicacaoListScreen
import com.aplicativomemoriaviva.ui.screens.medicacao.MedicacaoFormScreen
import com.aplicativomemoriaviva.ui.screens.observacao.ObservacaoListScreen
import com.aplicativomemoriaviva.ui.screens.observacao.ObservacaoFormScreen
import com.aplicativomemoriaviva.viewmodel.IdosoViewModel
import com.aplicativomemoriaviva.viewmodel.MainViewModel
import com.aplicativomemoriaviva.viewmodel.ViewModelFactory

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    // Inicialização da base de dados e repositório
    val context = navController.context
    val db = AppDatabase.getInstance(context)
    val repo = AppRepository(
        db.usuarioDao(),
        db.idosoDao(),
        db.observacaoDao(),
        db.medicacaoDao(),
        db.alertaDao()
    )

    // ViewModels usando nossa própria Factory
    val mainViewModel: MainViewModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as android.app.Application, repo)
    )

    val idosoViewModel: IdosoViewModel = viewModel(
        factory = ViewModelFactory(context.applicationContext as android.app.Application, repo)
    )

    NavHost(
        navController = navController,
        startDestination = "login",
        modifier = modifier
    ) {

        // ============================
        // LOGIN
        // ============================
        composable("login") {
            LoginScreen(
                mainViewModel = mainViewModel,
                onLoginSuccess = {
                    navController.navigate("menu") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
        }

        // ============================
        // MENU PRINCIPAL
        // ============================
        composable("menu") {
            MenuScreen(
                onOpenIdosos = { navController.navigate("idosos") },
                onOpenMedicacoes = { navController.navigate("medicacoes") },
                onOpenObservacoes = { navController.navigate("observacoes") }
            )
        }

        // ============================
        // IDOSOS
        // ============================
        composable("idosos") {
            IdosoListScreen(
                viewModel = idosoViewModel,
                onAdd = { navController.navigate("idosoForm") },
                onSelect = { id -> navController.navigate("idosoDetail/$id") }
            )
        }

        composable("idosoForm") {
            IdosoFormScreen(
                viewModel = idosoViewModel,
                onSave = { navController.popBackStack() }
            )
        }

        composable(
            "idosoDetail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            IdosoDetailScreen(
                id = id,
                viewModel = idosoViewModel,
                onBack = { navController.popBackStack() },
                onOpenMedicacoes = { navController.navigate("medicacoes/$id") },
                onOpenObservacoes = { navController.navigate("observacoes/$id") }
            )
        }

        // ============================
        // MEDICAÇÕES
        // ============================
        composable("medicacoes") {
            MedicacaoListScreen(
                viewModel = idosoViewModel,
                idIdoso = null,
                onAdd = { navController.navigate("medicacaoForm") }
            )
        }

        composable(
            "medicacoes/{idosoId}",
            arguments = listOf(navArgument("idosoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val idosoId = backStackEntry.arguments?.getInt("idosoId")
            MedicacaoListScreen(
                viewModel = idosoViewModel,
                idIdoso = idosoId,
                onAdd = { navController.navigate("medicacaoForm/$idosoId") }
            )
        }

        composable("medicacaoForm") {
            MedicacaoFormScreen(
                viewModel = idosoViewModel,
                idIdoso = null,
                onSave = { navController.popBackStack() }
            )
        }

        composable(
            "medicacaoForm/{idIdoso}",
            arguments = listOf(navArgument("idIdoso") { type = NavType.IntType })
        ) { backStackEntry ->
            val idIdoso = backStackEntry.arguments?.getInt("idIdoso")
            MedicacaoFormScreen(
                viewModel = idosoViewModel,
                idIdoso = idIdoso,
                onSave = { navController.popBackStack() }
            )
        }

        // ============================
        // OBSERVAÇÕES
        // ============================
        composable("observacoes") {
            ObservacaoListScreen(
                viewModel = idosoViewModel,
                idIdoso = null,
                onAdd = { navController.navigate("observacaoForm") }
            )
        }

        composable(
            "observacoes/{idosoId}",
            arguments = listOf(navArgument("idosoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val idosoId = backStackEntry.arguments?.getInt("idosoId")
            ObservacaoListScreen(
                viewModel = idosoViewModel,
                idIdoso = idosoId,
                onAdd = { navController.navigate("observacaoForm/$idosoId") }
            )
        }

        composable("observacaoForm") {
            ObservacaoFormScreen(
                viewModel = mainViewModel,
                idIdoso = null,
                onSave = { navController.popBackStack() }
            )
        }

        composable(
            "observacaoForm/{idIdoso}",
            arguments = listOf(navArgument("idIdoso") { type = NavType.IntType })
        ) { backStackEntry ->
            val idIdoso = backStackEntry.arguments?.getInt("idIdoso")
            ObservacaoFormScreen(
                viewModel = mainViewModel,
                idIdoso = idIdoso,
                onSave = { navController.popBackStack() }
            )
        }
    }
}
