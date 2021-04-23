package uw.sinanz2.dotify2

import android.app.Person
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import uw.sinanz2.dotify2.databinding.ActivityPlayerBinding
import java.util.*
import kotlin.random.Random

//
//private const val SONG_NAME_KEY = "song_name"
//private const val SONG_ARTIST_KEY = "song_artist"
//private const val SONG_IMAGE_KEY = "album_photo"
private const val SONG_KEY = "song"

fun navigateToDetail(context: Context, song: Song){
    val intent = Intent(context, PlayerActivity::class.java)

    val bundle = Bundle().apply{
//        putString(SONG_NAME_KEY, song.title)
//        putString(SONG_ARTIST_KEY, song.artist)
//        putInt(SONG_IMAGE_KEY, song.largeImageID)
        putParcelable(SONG_KEY, song)
    }
    intent.putExtras(bundle)
    context.startActivity(intent)
}

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayerBinding
    private val randomNumber = Random.nextInt(1, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayerBinding.inflate(layoutInflater).apply { setContentView(root) }

        val initPlayNum = randomNumber
        var currentNum = initPlayNum

        with(binding) {
            numPlays.text = "${initPlayNum.toString()} plays"

            playBtn.setOnClickListener {
                currentNum++
                numPlays.text = "${currentNum.toString()} plays"
            }

//            changeNameBtn.setOnClickListener{
//                userName.visibility = View.GONE
//                userNameEdit.visibility= View.VISIBLE
//                changeNameBtn.visibility = View.GONE
//                applyBtn.visibility= View.VISIBLE
//                applyBtn.setOnClickListener {
//                    if (userNameEdit.text.isEmpty()){
//                        Toast.makeText(this, "You did not enter a user name.", Toast.LENGTH_SHORT).show()
//                    } else {
//                        userName.text = userNameEdit.text
//                        userName.visibility = View.VISIBLE
//                        applyBtn.visibility = View.GONE
//                        changeNameBtn.visibility = View.VISIBLE
//                        userNameEdit.visibility = View.GONE
//                    }
//                }

            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)

            if (song != null) {
                songImage.setImageResource(song.largeImageID)
                songArtist.text = song.artist
                songTitle.text = song.title
            }

            previousBtn.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to previous track", Toast.LENGTH_SHORT).show()
            }
            nextBtn.setOnClickListener {
                Toast.makeText(this@PlayerActivity, "Skipping to next track", Toast.LENGTH_SHORT).show()
            }

            songImage.setOnClickListener {
//                val songName: String?= intent.extras?.getString(SONG_NAME_KEY)
//                val songArtist: String? = intent.extras?.getString(SONG_ARTIST_KEY)
                navigateToListActivity(this@PlayerActivity)
            }

        }
    }
}



