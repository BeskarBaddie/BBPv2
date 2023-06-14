package com.honours.bbpv2

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button


import androidx.appcompat.app.AppCompatActivity


class DashBoard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


//creates variables to access all the buttons on the screen
        val videoButton: Button = findViewById(R.id.videoScreenButton)
        val publicArticlesButton: Button = findViewById(R.id.publicArticlesButton)

       //navigates to desired activity
        videoButton.setOnClickListener {
            // Your code to handle the button click event
            val intent = Intent(this, Videos::class.java)
            startActivity(intent)
        }

        publicArticlesButton.setOnClickListener {
            // Your code to handle the button click event
            val intent = Intent(this, PublicArticles::class.java)
            startActivity(intent)
        }



    }




}