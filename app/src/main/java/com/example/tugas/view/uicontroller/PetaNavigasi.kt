package com.example.tugas.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tugas.room.Parfum
import com.example.tugas.view.HalamanHome
import com.example.tugas.view.HalamanTambah
import com.example.tugas.view.HalamanEdit
import com.example.tugas.view.HalamanListParfum
import com.example.tugas.view.HalamanLogin
import com.example.tugas.view.HalamanRegister
import com.example.tugas.view.route.DestinasiDashboard
import com.example.tugas.view.route.DestinasiTambah
import com.example.tugas.view.route.DestinasiEdit
import com.example.tugas.view.route.DestinasiListParfum
import com.example.tugas.view.route.DestinasiLogin
import com.example.tugas.view.route.DestinasiRegister

@Composable
fun ParfumApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    HostNavigasi(navController = navController, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HostNavigasi(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    NavHost(
        navController = navController,
        startDestination = DestinasiLogin.route,
        modifier = modifier
    ){
        composable(DestinasiLogin.route) {
            HalamanLogin(
                navigateToHome = {
                    navController.navigate(DestinasiDashboard.route) {
                        popUpTo(DestinasiLogin.route) { inclusive = true }
                    }
                },
                navigateToRegister = { navController.navigate(DestinasiRegister.route) }
            )
        }

        composable(DestinasiRegister.route) {
            HalamanRegister(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(DestinasiTambah.route){
            HalamanTambah(
                navigateBack = { navController.popBackStack() }
            )
        }

        composable(DestinasiDashboard.route){
            HalamanHome(
                onNavigateToKatalog = { navController.navigate(DestinasiListParfum.route) },
                onNavigateToTambah = { navController.navigate(DestinasiTambah.route) },
                onLogout = {
                    navController.navigate(DestinasiLogin.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }

        composable(DestinasiListParfum.route){
            HalamanListParfum(
                navigateBack = { navController.popBackStack() },
                navigateToTambah = { navController.navigate(DestinasiTambah.route) },
                navigateToEdit = { parfum ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("parfum", parfum)
                    navController.navigate(DestinasiEdit.route)
                }
            )
        }

        composable(DestinasiEdit.route){
            val parfum =
                navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Parfum>("parfum")

            if (parfum != null) {
                HalamanEdit(
                    parfum = parfum,
                    navigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
