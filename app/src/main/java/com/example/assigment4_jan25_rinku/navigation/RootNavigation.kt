package com.example.assigment4_jan25_rinku.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assigment4_jan25_rinku.MainActivity
import com.example.assigment4_jan25_rinku.ViewModelFactory
import com.example.assigment4_jan25_rinku.ViewModelStudentFactory
import com.example.assigment4_jan25_rinku.database.CollegeDatabase
import com.example.assigment4_jan25_rinku.repositry.CollegeRepository
import com.example.assigment4_jan25_rinku.repositry.StudentRepository
import com.example.assigment4_jan25_rinku.screen.AddNewStudentUI
import com.example.assigment4_jan25_rinku.screen.CollegeListScreen
import com.example.assigment4_jan25_rinku.screen.CollegeWebScreen
import com.example.assigment4_jan25_rinku.screen.FavCollegeListScreen
import com.example.assigment4_jan25_rinku.screen.StudentListScreen
import com.example.assigment4_jan25_rinku.services.CollegeServices
import com.example.assigment4_jan25_rinku.viewmodel.CollegeViewModel
import com.example.assigment4_jan25_rinku.viewmodel.StudentViewModel
import kotlin.jvm.java

object Route {
    const val LISTCOLLEGE = "List"
    const val FAVISTCOLLEGE = "FAV"
    const val DETAIL = "DETAIL"
    const val WEBSIT = "WEB"
    const val STUDENTLIST = "STUDENTLIST"
    const val ADDSTUDENT = "ADDSTUDENT"
}

sealed class NavRoute(val route: String) {
    object ListScreen : NavRoute(Route.LISTCOLLEGE)
    object FavListScreen : NavRoute(Route.FAVISTCOLLEGE)
    object DetailScreen : NavRoute(Route.DETAIL)
    object WebScreen : NavRoute(Route.WEBSIT)
    object StudentScreen : NavRoute(Route.STUDENTLIST)
    object AddStudentScreen : NavRoute(Route.ADDSTUDENT)
}

@Composable
fun RootNavigation(activity: MainActivity) {
    val collegeDatabase = CollegeDatabase.getDatabase(activity)
    val dao = collegeDatabase.collegeDao()
    val studentDao = collegeDatabase.studentDao()
    val collegeServices = CollegeServices()
    val collegeRepository = CollegeRepository(dao, collegeServices)
    val viewModelFactory = ViewModelFactory(collegeRepository)
    val studentRepository = StudentRepository(studentDao)
    val viewModelStudentFactory = ViewModelStudentFactory(studentRepository)
    val collegeViewModel =
        ViewModelProvider(activity, viewModelFactory)[CollegeViewModel::class.java]
    val studentViewModel =
        ViewModelProvider(activity, viewModelStudentFactory)[StudentViewModel::class.java]
    val remNavController = rememberNavController()

    NavHost(navController = remNavController, startDestination = NavRoute.ListScreen.route) {
        composable(NavRoute.ListScreen.route) {
            CollegeListScreen(collegeViewModel, remNavController)
        }
        composable(NavRoute.FavListScreen.route) {
            FavCollegeListScreen(collegeViewModel, remNavController)
        }
        composable(
            "${NavRoute.WebScreen.route}/{url}", arguments = listOf(
                navArgument("url") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            CollegeWebScreen(collegeViewModel, url)
        }

        composable(
            "${NavRoute.StudentScreen.route}/{id}/{name}", arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val collegeId = backStackEntry.arguments?.getString("id")
            val name = backStackEntry.arguments?.getString("name")
            StudentListScreen(studentViewModel, collegeId, name, remNavController)
        }

        composable(
            "${NavRoute.AddStudentScreen.route}/{id}", arguments = listOf(
                navArgument("id") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val collegeId = backStackEntry.arguments?.getString("id")
            AddNewStudentUI(remNavController, collegeId, studentViewModel)
        }

    }
}
