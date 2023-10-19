package com.example.ruuttest.presentation.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.ruuttest.R
import com.example.ruuttest.databinding.ActivityMainBinding
import com.example.ruuttest.presentation.bases.BaseActivity
import com.example.ruuttest.presentation.views.fragments.BarChartFragment
import com.example.ruuttest.presentation.views.fragments.NewsFragment
import com.example.ruuttest.presentation.views.fragments.PieChartFragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

/**
 * Tell me if you don't know how to implement Dependency Injection (DI).
 */
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var fragmentManager: FragmentManager
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navigationView: NavigationView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navigationView = findViewById(R.id.navigation_drawer)
        drawerLayout = findViewById(R.id.drawer_layout)

        configToggle()
        configBottomNavigation()
        configNavigationView()
        onBackPress()

        fragmentManager = supportFragmentManager
        openFragment(NewsFragment())
    }

    private fun onBackPress() {
        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    } else {
                        finish()
                    }

                }
            })
    }

    private fun configNavigationView() {
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    // handle click
                    if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                        binding.drawerLayout.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_profile -> {
                    // handle click
                    goStartActivity(ProfileActivity::class.java)
                    true
                }
                R.id.nav_logout -> {
                    // handle click
                    firebaseAuth = FirebaseAuth.getInstance()
                    firebaseAuth.signOut()
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun configBottomNavigation() {
        binding.bottomNavigation.background = null

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_news -> openFragment(NewsFragment())
                R.id.bottom_bar -> openFragment(BarChartFragment())
                R.id.bottom_pie -> openFragment(PieChartFragment())
            }
            true
        }
    }

    private fun configToggle() {
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun goStartActivity(cls: Class<*>?) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    private fun openFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}