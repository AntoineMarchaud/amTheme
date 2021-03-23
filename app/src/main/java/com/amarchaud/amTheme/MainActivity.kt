package com.amarchaud.amTheme

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.amarchaud.amTheme.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.teamFragment, R.id.marketFragment, R.id.filterFragment
            )
        )

        // nav host
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.my_first_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        with(binding) {
            setSupportActionBar(toolbar)
            toolbar.setupWithNavController(navController, appBarConfiguration)
            bottomNav.setupWithNavController(navController)
        }

        // remove bottom nav bar (home + back)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { // android 11
            window.insetsController?.let {
                // Default behavior is that if navigation bar is hidden, the system will "steal" touches
                // and show it again upon user's touch. We just want the user to be able to show the
                // navigation bar by swipe, touches are handled by custom code -> change system bar behavior.
                // Alternative to deprecated SYSTEM_UI_FLAG_IMMERSIVE.
                it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                // Finally, hide the nav bars, alternative to View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                it.hide(WindowInsets.Type.navigationBars())
            }
        } else {
            // Enables regular immersive mode.
            // For "lean back" mode : remove first parameter : one click, show statusbar
            // SYSTEM_UI_FLAG_IMMERSIVE : plus d'actionBar, swipe pour retrouver statusBar + navBar
            // SYSTEM_UI_FLAG_IMMERSIVE_STICKY : idem, mais statusBar + navBar disparait tout seul après quelques secondes
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            // Set the content to appear under the system bars so that the
                            // content doesn't resize when the system bars hide and show.
                            //or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            // Hide the nav bar and status bar
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_FULLSCREEN
                    )
        }
    }

    fun changeToolbarTitle(title: String) {
        binding.toolbarTitle.text = title
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.my_first_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}