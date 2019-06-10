package com.example.parsers2.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import com.example.parsers2.ItemModel
import com.example.parsers2.R
import com.example.parsers2.adapters.AdapterItem
import com.example.parsers2.fragments.ContentFragment
import com.example.parsers2.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    lateinit var drawerListView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.content_frame, ContentFragment())
            .commit()

        drawerLayout = findViewById(R.id.drawer_layout)
        drawerListView = findViewById(R.id.left_drawer)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val arrayItems = createMenuItem()

        val adapter = AdapterItem(this, R.layout.item_row, arrayItems)

        drawerListView.adapter = adapter
        drawerListView.onItemClickListener = ItemClickListener()

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun createMenuItem(): Array<ItemModel> {
        return arrayOf(
            ItemModel(android.R.drawable.ic_dialog_email, "Главное меню"),
            ItemModel(android.R.drawable.ic_dialog_info, "Настройки")
        )
    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    inner class ItemClickListener : AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            var fragment: Fragment? = null

            when (position) {
                0 -> fragment = ContentFragment()
                1 -> fragment = SettingsFragment()

                else -> {
                }
            }

            if (fragment != null) {

                supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.content_frame, fragment)
                    .commit()

                drawerListView.setItemChecked(position, true)
                drawerListView.setSelection(position)
                val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }
}
