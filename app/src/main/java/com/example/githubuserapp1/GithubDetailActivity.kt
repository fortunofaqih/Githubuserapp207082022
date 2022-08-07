package com.example.githubuserapp1


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.Intents.Insert.DATA
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.githubuserapp1.R.id.*
import com.example.githubuserapp1.databinding.ActivityDetailGithubBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class GithubDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGithubBinding


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailGithubBinding.inflate(layoutInflater)
        setContentView(binding.root)

            val data = intent.getParcelableExtra<Github>(EXTRA_GITHUB) as Github


           binding.imgItemPhoto.setImageResource(data.avatars)
           binding.tvItemUsername.text = data.username
           binding.tvItemName.text = data.name
           binding.tvLocation.text = data.location
           binding.tvRepository.text = data.repository
           binding.tvCompany.text = data.company

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        //supportActionBar?.elevation = 0f


    }

    companion object {
        const val EXTRA_GITHUB = "extra_github"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.following,
            R.string.followers
        )
    }


}