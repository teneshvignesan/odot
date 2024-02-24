package com.teneshvignesan.odot.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.teneshvignesan.odot.design.OdotTheme
import com.teneshvignesan.odot.navigation.Screen
import com.teneshvignesan.odot.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OdotTheme {
                val navHostController = rememberNavController()
                SetupNavGraph(
                    startDestination = Screen.HomeScreen.route,
                    navHostController = navHostController
                )
            }
        }
    }
}