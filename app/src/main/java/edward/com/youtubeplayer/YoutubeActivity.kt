package edward.com.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

const val YOUTUBE_VIDEO_ID = "f5_wn8mexmM"
const val YOUTUBE_PLAYLIST_ID = "PL9K3xwFkFqWGEVTB3QNRQ1wgxF6Y-DMOX"

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener   {
    private val TAG = "YoutubeActivity";
    private val DIALOG_REQUEST_CODE = 1
    val playerView by lazy { YouTubePlayerView(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)
//        val layout = findViewById<ConstraintLayout>(R.id.activity_youtube)

        val layout = layoutInflater.inflate(R.layout.activity_youtube, null) as ConstraintLayout
        setContentView(layout)

//        val button1 = Button(this)
//        button1.layoutParams = ConstraintLayout.LayoutParams(600, 180)
//        button1.text = "Button added"
//        layout.addView(button1)

//        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)

    }

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?, wasRestored: Boolean) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
        Toast.makeText(this, "Initialized YouTube Player Successfully", Toast.LENGTH_SHORT)

        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
        youTubePlayer?.setPlaybackEventListener(playbackEventListener)

        if(!wasRestored){
//            youTubePlayer?.cueVideo(YOUTUBE_VIDEO_ID)
            youTubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        }else{
            youTubePlayer?.play()
        }
    }

    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
                                         youTubeInitializationResult: YouTubeInitializationResult?) {
//        val REQUEST_CODE = 0

        if(youTubeInitializationResult?.isUserRecoverableError == true){
            youTubeInitializationResult.getErrorDialog(this, DIALOG_REQUEST_CODE).show()
        } else{
            val errorMessage = "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG)
        }
    }

    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener{
        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity, "Good, Video is playing ok", Toast.LENGTH_SHORT)
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity, "Video has paused", Toast.LENGTH_SHORT)
        }

        override fun onStopped() {
        }

        override fun onBuffering(p0: Boolean) {
        }

        override fun onSeekTo(p0: Int) {
        }
    }

    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener{
        override fun onLoading() {
        }

        override fun onLoaded(p0: String?) {
        }

        override fun onAdStarted() {
            Toast.makeText(this@YoutubeActivity, "Click skip ad now", Toast.LENGTH_SHORT)
        }

        override fun onVideoStarted() {
            Toast.makeText(this@YoutubeActivity, "Video has started", Toast.LENGTH_SHORT)
        }

        override fun onVideoEnded() {
            Toast.makeText(this@YoutubeActivity, "Video has ended", Toast.LENGTH_SHORT)

        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult called with response code $resultCode for request $requestCode")
        if (requestCode == DIALOG_REQUEST_CODE){
            Log.d(TAG, intent?.toString()!!)
            Log.d(TAG, intent?.extras.toString())
            playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
        }
    }
}