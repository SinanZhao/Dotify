package uw.sinanz2.dotify2

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.SongDataProvider
import uw.sinanz2.dotify2.databinding.ActivitySongListBinding


fun navigateToListActivity(context: Context) = with(context) {
    val intent = Intent(this, SongListActivity::class.java)
    startActivity(intent)
}

class SongListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        title = "All Songs"
//        val allSong = SongDataProvider.getAllSongs()
//
//        val artistNameList = mutableListOf("")

//        val song = listOf<Song>(
//            Song("1", "Love Story", "Taylor Swift", 1, 2, 2),
//            Song("1", "Love Story", "Taylor Swift", 1, 2, 2)
//            )
//        for (i in allSong.indices) {
//            artistNameList.add()
//
//        }
//        val artistName = firstSong.artist
//        val songName = firstSong.title

        with(binding) {
            val songs = SongDataProvider.getAllSongs()

            val adapter = SongListAdapter(songs)
            allSong.adapter = adapter

            adapter.onSongClickListener = {song ->
                btnRefresh.visibility = View.VISIBLE
                btnRefresh.setOnClickListener {
                    adapter.updateSong(songs.toMutableList().shuffled())
//                    navigateToDetail(this@SongListActivity, song)
                }
                miniPlayer.visibility = View.VISIBLE
                miniPlayer.setOnClickListener {
                    navigateToDetail(this@SongListActivity, song)
                }
                miniPlayer.text = root.context.getString(R.string.brief_info, song.title, song.artist)
//                Toast.makeText(this@SongListActivity, song.artist, Toast.LENGTH_SHORT).show()
//                navigateToDetail(this@SongListActivity, song)
            }


            btnRefresh.setOnClickListener {
                adapter.updateSong(songs.toMutableList().shuffled())
            }
//            miniPlayer.text = root.context.getString(R.string.brief_info, song.title, song.artist)

        }

    }

}