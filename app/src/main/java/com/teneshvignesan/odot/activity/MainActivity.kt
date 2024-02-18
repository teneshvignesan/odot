package com.teneshvignesan.odot.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.teneshvignesan.odot.design.OdotTheme
import com.teneshvignesan.odot.presentation.home.HomeScreen
import com.teneshvignesan.odot.presentation.task.TaskScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OdotTheme {
                Navigator(screen = HomeScreen()) { navigator ->
                    SlideTransition(navigator)
                }
            }
        }
    }
}