package com.transcendence.universe.abp.base.act;


import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.transcendence.universe.R;
import com.transcendence.universe.abp.main.act.TitleBarActivity;

/**
 * Created by Joephone on 2018/12/20 17:08
 * E-Mail Address：joephonechen@gmail.com
 */

public class SoundPlayActivity extends TitleBarActivity implements View.OnClickListener {

    // 4个按钮和一个TextView
    private Button btnSoundStart, btnSoundStop, btnMediaStart, btnMediaStop;
    private TextView tvMsg;
    private MediaPlayer mPlayer;
    private SoundPool mSound;
    private HashMap<Integer, Integer> soundPoolMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitSounds();

        setContentView(R.layout.sound_play);

        btnSoundStart = (Button) findViewById(R.id.button1);
        btnSoundStop = (Button) findViewById(R.id.button2);
        btnMediaStart = (Button) findViewById(R.id.button3);
        btnMediaStop = (Button) findViewById(R.id.button4);

        tvMsg = (TextView) findViewById(R.id.tvMsg);

        btnSoundStart.setOnClickListener(this);
        btnSoundStop.setOnClickListener(this);
        btnMediaStart.setOnClickListener(this);
        btnMediaStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSoundStart) {
            this.PlaySound(1, 0);
            tvMsg.setText("SoundPool播放");
        } else if (v == btnSoundStop) {
            mSound.pause(1);
            tvMsg.setText("SoundPool暂停");
        }else if(v==btnMediaStart){
            if(!mPlayer.isPlaying())
                mPlayer.start();
            tvMsg.setText("MediaPlayer播放");
        }else if(v==btnMediaStop){
            if(mPlayer.isPlaying())
                mPlayer.pause();
            tvMsg.setText("MediaPlayer暂停");
        }
    }

    /**
     * 初始化声音
     */
    private void InitSounds() {
        // 设置播放音效
        mPlayer = MediaPlayer.create(this, R.raw.beep);
        // 第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        mSound = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(1, mSound.load(this, R.raw.beep, 1));
        //可以在后面继续put音效文件
    }

    /**
     * soundPool播放
     *
     * @param sound
     *            播放第一个
     * @param loop
     *            是否循环
     */
    private void PlaySound(int sound, int loop) {
        AudioManager mgr = (AudioManager) this
                .getSystemService(Context.AUDIO_SERVICE);
        // 获取系统声音的当前音量
        float currentVolume = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
        // 获取系统声音的最大音量
        float maxVolume = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // 获取当前音量的百分比
        float volume = currentVolume / maxVolume;

        // 第一个参数是声效ID,第二个是左声道音量，第三个是右声道音量，第四个是流的优先级，最低为0，第五个是是否循环播放，第六个播放速度(1.0 =正常播放,范围0.5 - 2.0)
        mSound.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
    }
}
