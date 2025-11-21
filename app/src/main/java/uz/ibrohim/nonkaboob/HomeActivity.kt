package uz.ibrohim.nonkaboob

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import uz.ibrohim.nonkaboob.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDrawerLayout()

        val close: ImageView = binding.navView.getHeaderView(0).findViewById(R.id.header_img)

        binding.apply {

            close.setOnClickListener {
                drawerLayout.closeDrawer(GravityCompat.START)
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment -> {
                        adminText.text = "Bosh sahifa"
                    }

                    R.id.productsFragment -> {
                        adminText.text = "Mahsulolar"
                    }

                    R.id.warehouseFragment -> {
                        adminText.text = "Ombor"
                    }

                    R.id.statisticsFragment -> {
                        adminText.text = "Statistika"
                    }

                    R.id.settingFragment -> {
                        adminText.text = "Sozlamalar"
                    }
                }
            }
        }
    }

    private fun setupDrawerLayout() {
        binding.navView.setupWithNavController(navController)
        binding.homeTool.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        // Navigation menu click handler
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            NavigationUI.onNavDestinationSelected(menuItem, navController)
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    @Deprecated("Deprecated in Java")
    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}