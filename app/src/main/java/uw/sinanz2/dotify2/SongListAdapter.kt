package uw.sinanz2.dotify2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import uw.sinanz2.dotify2.databinding.ActivitySongListBinding
import uw.sinanz2.dotify2.databinding.ItemSongBinding

class SongListAdapter(private var listOfSong: List<Song>):RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {
    var onSongClickListener: (song: Song) -> Unit = {_ -> }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listOfSong.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song: Song = listOfSong[position]
        with(holder.binding) {
//            songName.text = root.context.getString(R.string.welcome_prefix, song.title, song.artist)
            songPic.setImageResource(song.smallImageID)
            songName.text = song.title
            songArtist.text = song.artist
            root.setOnClickListener {
                onSongClickListener(song)
            }
        }

    }
    fun updateSong(newListOfSong: List<Song>) {

        this.listOfSong = newListOfSong
        notifyDataSetChanged()
    }

    class SongViewHolder(val binding: ItemSongBinding):RecyclerView.ViewHolder(binding.root)

}