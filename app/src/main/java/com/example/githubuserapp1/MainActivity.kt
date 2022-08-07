package com.example.githubuserapp1

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Intents.Insert.DATA
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {



    private lateinit var rvGithub: RecyclerView
    private val list = ArrayList<Github>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvGithub = findViewById(R.id.rv_github)
        rvGithub.setHasFixedSize(true)
        list.addAll(listGithub)
        showRecyclerList()

    }
    private val listGithub: ArrayList<Github>
        @SuppressLint("Recycle")
        get() {
            val dataUserName = resources.getStringArray(R.array.username)
            val dataName = resources.getStringArray(R.array.name)
            val dataLocation = resources.getStringArray(R.array.location)
            val dataRepository = resources.getStringArray(R.array.repository)
            val dataCompany = resources.getStringArray(R.array.company)
            val datafollowers = resources.getStringArray(R.array.followers)
            val datafollowing = resources.getStringArray(R.array.following)
            val dataPhoto = resources.obtainTypedArray(R.array.avatars)

            val listGithub = ArrayList<Github>()
            for (i in dataUserName.indices) {
                val github = Github(dataUserName[i],dataName[i], dataLocation[i], dataRepository[i], dataCompany[i], datafollowers[i], datafollowing[i],dataPhoto.getResourceId(i,-1))
                listGithub.add(github)

            }
            return listGithub
        }
    private fun showRecyclerList() {

        if (applicationContext.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvGithub.layoutManager = GridLayoutManager(this, 2)
        } else {
            rvGithub.layoutManager = LinearLayoutManager(this)
        }

        val listGithubAdapter = ListGithubAdapter(list)
        rvGithub.adapter = listGithubAdapter

        listGithubAdapter.setOnItemClickCallback(object : ListGithubAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Github) {
                val intentToDetail = Intent(this@MainActivity, GithubDetailActivity::class.java)
                intentToDetail.putExtra(GithubDetailActivity.EXTRA_GITHUB,data)
                startActivity(intentToDetail)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            /*
            Gunakan method ini ketika search selesai atau OK
             */
            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.clearFocus()
                return true
            }

            /*
            Gunakan method ini untuk merespon tiap perubahan huruf pada searchView
             */
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu1 -> {
                supportFragmentManager.beginTransaction()
                Toast.makeText(this,"Menu Utama Github User",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menu2 -> {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                return true
            }
            else -> return true
        }
    }
}
