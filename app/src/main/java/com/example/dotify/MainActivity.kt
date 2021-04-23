package com.example.dotify

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.dotify.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val randomNumber = Random.nextInt(1, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
        setContentView(R.layout.activity_main)

        //set initial play number
        val initPlayNum = randomNumber
        var currentNum = initPlayNum

        var numPlay =  findViewById<TextView>(R.id.numPlays)

        numPlay.text = "${initPlayNum.toString()} plays"

        // click play button and increase the number of play
        var playBtn = findViewById<ImageButton>(R.id.playBtn)
        playBtn.setOnClickListener {
            currentNum++
            numPlay.text = "${currentNum.toString()} plays"
        }

        //edit the use name
        var userName = findViewById<TextView>(R.id.userName)
        var userNameEdit = findViewById<EditText>(R.id.userNameEdit)
        var changeUserNameBtn = findViewById<Button>(R.id.changeNameBtn)
        var applyBtn = findViewById<Button>(R.id.applyBtn)
        changeUserNameBtn.setOnClickListener{
            userName.visibility = View.GONE
            userNameEdit.visibility=View.VISIBLE
            changeUserNameBtn.visibility = View.GONE
            applyBtn.visibility=View.VISIBLE
            applyBtn.setOnClickListener {
                if (userNameEdit.text.isEmpty()){
                    Toast.makeText(this, "You did not enter a user name.", Toast.LENGTH_SHORT).show()
                } else {
                    userName.text = userNameEdit.text
                    userName.visibility = View.VISIBLE
                    applyBtn.visibility = View.GONE
                    changeUserNameBtn.visibility = View.VISIBLE
                    userNameEdit.visibility = View.GONE
                }
            }
        }


        // long press song image, change the color of number of plays
        var songImage = findViewById<ImageView>(R.id.songImage)
        songImage.setOnLongClickListener {
            numPlay.setTextColor(getColor(R.color.purple_200))
            true
        }


//        binding.previousBtn.setOnClickListener {
//            currentNum++
//            binding.numPlays.text="${currentNum.toString()} plays"
//        }

//        // make toast text for previous button
//        binding.previousBtn.setOnClickListener{
//            playPrevious()
//        }
//
//        // make toast text for next button
//        binding.nextBtn.setOnClickListener{
//            playNext()
//        }
    }

    fun playPrevious(view: View) {
        Toast.makeText(this,"Skipping to previous track", Toast.LENGTH_SHORT).show()
    }

    fun playNext(view: View) {
        Toast.makeText(this,"Skipping to next track", Toast.LENGTH_SHORT).show()
    }
}