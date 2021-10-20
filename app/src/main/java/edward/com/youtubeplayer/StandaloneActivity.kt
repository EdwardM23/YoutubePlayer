package edward.com.youtubeplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*
import java.lang.IllegalArgumentException

class StandaloneActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnPlayVideo.setOnClickListener(this)
        btnPlaylist.setOnClickListener(this)

//        btnPlayVideo.setOnClickListener(object : View.OnClickListener{
//            override fun onClick(v: View?) {
//            TODO("not implemented")
//            }
//        })

//        btnPlayVideo.setOnClickListener(View.OnClickListener { v ->
//            TODO("not implemented")
//        })

//        val listener = View.OnClickListener { v ->
//            TODO("not implemented")
//        }
//        btnPlayVideo.setOnClickListener(listener)
//        btnPlaylist.setOnClickListener(listener)

    }

    override fun onClick(v: View?) {
        val intent = when (v?.id) {
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                    this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_VIDEO_ID, 0, true, true)
            R.id.btnPlaylist -> YouTubeStandalonePlayer.createPlaylistIntent(
                    this, getString(R.string.GOOGLE_API_KEY), YOUTUBE_PLAYLIST_ID, 10, 0, true, true)
            else -> throw IllegalArgumentException("Undefined button clicked")
        }
        startActivity(intent)
    }

//    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, p1: YouTubePlayer?, p2: Boolean) {
//        if(!p2) {
//            p1?.loadVideo(YOUTUBE_VIDEO_ID)
//        } else{
//            p1?.play()
//        }
//    }
//
//    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
//        TODO("Not yet implemented")
//    }


}