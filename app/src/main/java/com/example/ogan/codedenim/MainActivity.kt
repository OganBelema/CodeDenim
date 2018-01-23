package com.example.ogan.codedenim

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.example.ogan.codedenim.courses.MyCourses
import com.example.ogan.codedenim.fragments.HomeFragment
import com.example.ogan.codedenim.sessionManagement.UserSessionManager






class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentTransaction: FragmentTransaction
    private lateinit var fm: FragmentManager

    // User Session Manager Class
    private var session: UserSessionManager? = null
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Session class instance
        session = UserSessionManager(applicationContext)

        // get user data from session
        val user = UserSessionManager.getUserDetails()

        // get email
        try {
            email = user[UserSessionManager.KEY_EMAIL]
        } catch (e: Exception){
            e.printStackTrace()
        }


        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        val headerView = navigationView.getHeaderView(0)
        val textView = headerView.findViewById<TextView>(R.id.drawNav_txt)
        textView.text = email

        // Check user login
        // If User is not logged in , This will redirect user to LoginActivity.
        if (session!!.checkLogin())
            finish()


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        fm = supportFragmentManager
        fragmentTransaction = fm.beginTransaction()
        //fragmentTransaction.replace(R.id.content, new TabFragment()).commit();
        fragmentTransaction.replace(R.id.content, HomeFragment()).commit()


        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        val toggle = object : ActionBarDrawerToggle(
                this, drawer, toolbar,  R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val container = findViewById<View>(R.id.main_container)
                container.translationX = slideOffset * drawerView.width
            }
        }
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        val logOut = findViewById<TextView>(R.id.log_out_tv)
        logOut.setOnClickListener {
            // Clear the User session data
            // and redirect user to LoginActivity
            if (session != null) {
                session?.logoutUser()
                finish()
            }
        }


            navigationView.setNavigationItemSelectedListener(this)
        }

        override fun onBackPressed() {
            val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START)
            } else {
                //prevent logged in user from returning to login page
                val intent = Intent(Intent.ACTION_MAIN)
                intent.addCategory(Intent.CATEGORY_HOME)
                startActivity(intent)
            }
        }

        /*@Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }*/

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            val id = item.itemId


            return if (id == R.id.action_search) {
                true
            } else super.onOptionsItemSelected(item)

        }


        override fun onNavigationItemSelected(item: MenuItem): Boolean {
            // Handle navigation view item clicks here.
            val id = item.itemId
            val intent: Intent

            when (id) {
                R.id.nav_myAccount -> {
                    intent = Intent(applicationContext, MyAccount::class.java)
                    startActivity(intent)
                }
                R.id.nav_myCourses -> {
                    intent = Intent(applicationContext, MyCourses::class.java)
                    startActivity(intent)
                }
                /*R.id.nav_courses -> {
                    intent = Intent(applicationContext, CategoriesActivity::class.java)
                    startActivity(intent)
                }*/
//            R.id.nav_logout -> {
//                // Clear the User session data
//                // and redirect user to LoginActivity
//                if (session != null) {
//                    session?.logoutUser()
//                    finish()
//                }
//            }
            }

            val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
            drawer.closeDrawer(GravityCompat.START)
            return true
        }
    }
