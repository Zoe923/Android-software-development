package com.domker.study.androidstudy;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.domker.study.androidstudy.player.VideoPlayerIJK;
import com.domker.study.androidstudy.player.VideoPlayerListener;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * 使用开源IjkPlayer播放视频
 */
public class IJKPlayerActivity extends AppCompatActivity {
    private VideoPlayerIJK ijkPlayer;
    private SeekBar seekBar;
    private TextView time;
    private Handler handler = new Handler();
    int min,sec;
    private boolean seekUsing = false;
    private int seekPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ijkplayer);
        setTitle("ijkPlayer");

        ijkPlayer = findViewById(R.id.ijkPlayer);
        seekBar = findViewById(R.id.sb_play);
        time = findViewById(R.id.tv_time);
        initSeekBar();

        //加载native库
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");
        } catch (Exception e) {
            this.finish();
        }
        ijkPlayer.setListener(new VideoPlayerListener());
        //ijkPlayer.setVideoResource(R.raw.bytedance);
        ijkPlayer.setVideoPath(getVideoPath());

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (!seekUsing) {
                        float rate = (float) ijkPlayer.getCurrentPosition() / (float) ijkPlayer.getDuration();
                        seekBar.setProgress((int) (rate * seekBar.getMax()));
                    }
                }
            }
        }).start();

        findViewById(R.id.buttonPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ijkPlayer.start();
            }
        });

        findViewById(R.id.buttonPause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ijkPlayer.pause();
            }
        });

        findViewById(R.id.buttonSeek).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ijkPlayer.seekTo(20 * 1000);
            }
        });
        handler.post(runnable);
    }

    private Runnable runnable = new Runnable() {
        public void run() {
            if (ijkPlayer.isPlaying()) {
                seekBar.setProgress((int)(((float)ijkPlayer.getCurrentPosition()/ijkPlayer.getDuration())*100));
                min = (int)ijkPlayer.getCurrentPosition()/60000;
                sec = (int)(ijkPlayer.getCurrentPosition() - min*60000)/1000;
                time.setText(" "+min+" : "+sec);
            }
            handler.postDelayed(runnable, 500);
        }
    };


    private String getVideoPath() {
        return "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
//        return "android.resource://" + this.getPackageName() + "/" + resId;
    }

    private void initSeekBar() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekPosition = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekUsing = true;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float percent = (float) seekPosition / (float) seekBar.getMax();
                ijkPlayer.seekTo((long) (ijkPlayer.getDuration() * percent));
                seekUsing = false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ijkPlayer.isPlaying()) {
            ijkPlayer.stop();
        }

        IjkMediaPlayer.native_profileEnd();
    }
}
