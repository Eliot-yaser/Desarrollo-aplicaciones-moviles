package com.example.mpazpro1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.mpazpro1.ui.navigation.AppNavigation
import com.example.mpazpro1.ui.theme.MpazPro1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MpazPro1Theme {
                AppNavigation()
            }
        }
    }
}