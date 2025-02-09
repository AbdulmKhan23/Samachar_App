package com.khan.samacharapp.presentation.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.khan.samacharapp.R
import com.khan.samacharapp.domain.model.Article
import com.khan.samacharapp.presentation.bookmark.BookMarkScreen
import com.khan.samacharapp.presentation.bookmark.BookMarkViewModel
import com.khan.samacharapp.presentation.details.DetailsEvent
import com.khan.samacharapp.presentation.details.DetailsScreen
import com.khan.samacharapp.presentation.details.DetailsViewModel
import com.khan.samacharapp.presentation.home.HomViewModel
import com.khan.samacharapp.presentation.home.HomeScreen
import com.khan.samacharapp.presentation.navgraph.Route
import com.khan.samacharapp.presentation.news_navigator.components.BottomNavigationItem
import com.khan.samacharapp.presentation.news_navigator.components.NewsBottomNavigation
import com.khan.samacharapp.presentation.search.SearchScreen
import com.khan.samacharapp.presentation.search.SearchViewModel


@Composable
fun NewsNavigator() {

    val bottomNavigationItem =  remember{
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark")
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableStateOf(0)
    }

    selectedItem = when(backStackState?.destination?.route){
        Route.HomeScreen.Route ->0
        Route.SearchScreen.Route ->1
        Route.BookMarkScreen.Route ->2
        else ->0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route in listOf(
            Route.HomeScreen.Route,
            Route.SearchScreen.Route,
            Route.BookMarkScreen.Route
        )
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if(isBottomBarVisible){
            NewsBottomNavigation(
                items = bottomNavigationItem,
                selected =selectedItem,
                onItemClick ={index ->
                    when(index){
                        0 ->navigateToTab(navController,Route.HomeScreen.Route)
                        1 ->navigateToTab(navController,Route.SearchScreen.Route)
                        2 ->navigateToTab(navController,Route.BookMarkScreen.Route)
                    }

                }
            )
        }
        }
    ){
        val bottomPadding = it.calculateBottomPadding()
        NavHost(modifier = Modifier.padding(bottom = bottomPadding),
            navController = navController,
            startDestination = Route.HomeScreen.Route)
        {
           composable(route = Route.HomeScreen.Route){
               val viewModel: HomViewModel = hiltViewModel()
               val articles = viewModel.news.collectAsLazyPagingItems()
               HomeScreen(articles = articles, navigateToSearch = {
                   navigateToTab(navController,Route.SearchScreen.Route)
               }, navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController,
                        article = article
                    )
               })

           }
            composable(route = Route.SearchScreen.Route){
                val viewModel:SearchViewModel = hiltViewModel()
                val state = viewModel.state.value
                SearchScreen(state = state, event = viewModel::onEvent, navigateToDetails =
                {
                    navigateToDetails(
                        navController = navController,
                        article =it
                    )
                })
            }
            composable(route =Route.DetailsScreen.Route){
                val viewModel:DetailsViewModel = hiltViewModel()
                if(viewModel.sideEffect != null){
                    Toast.makeText(LocalContext.current,viewModel.sideEffect,Toast.LENGTH_LONG).show()
                    viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                    ?.let {article ->
                        DetailsScreen(article = article, event = viewModel::onEvent,
                            navigateUp = {
                                navController.navigateUp()
                            })

                }
            }
            composable(route = Route.BookMarkScreen.Route){
                val viewModel:BookMarkViewModel = hiltViewModel()
                val state = viewModel.state.value
                BookMarkScreen(state = state, navigateToDetails = {article ->
                    navigateToDetails(navController = navController, article = article)
                }
                )
            }
        }
    }
}


private fun navigateToTab(navController: NavController,route:String){
    navController.navigate(route){
        navController.graph.startDestinationRoute?.let {
            popUpTo(it){saveState = true}
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article:Article){
      navController.currentBackStackEntry?.savedStateHandle?.set("article",article)
    navController.navigate(
        route = Route.DetailsScreen.Route
    )
}



