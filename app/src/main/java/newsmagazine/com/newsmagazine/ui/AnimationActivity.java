package newsmagazine.com.newsmagazine.ui;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import newsmagazine.com.newsmagazine.R;

public class AnimationActivity extends AppCompatActivity {


    @BindView(R.id.txt_touch)
    TextView txtTouch;
    @BindView(R.id.btn_begin)
    Button btnBegin;
    @BindView(R.id.ll_first)
    LinearLayout llFirst;
    @BindView(R.id.txt_time)
    TextView txtTime;
    @BindView(R.id.video_view)
    VideoView videoView;
    @BindView(R.id.ll_second)
    FrameLayout llSecond;
    @BindView(R.id.rl_main)
    RelativeLayout rlMain;
    // total video videoDuration
    private long videoDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Remove status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_animation);
        ButterKnife.bind(this);

        init_view();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void init_view() {
        // Fade animation
        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .repeat(0)
                .playOn(btnBegin);
        btnBegin.setVisibility(View.GONE);

        txtTouch.setText("The Special Magazine of Today is:");

        txtTouch.setTextColor(getResources().getColor(R.color.colorBlack));
        YoYo.with(Techniques.SlideInUp)
                .duration(2000)
                .repeat(0)
                .playOn(txtTouch);

        YoYo.with(Techniques.FadeOut)
                .duration(500)
                .repeat(0)
                .playOn(txtTouch);

        // After animation start video play
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                llFirst.setVisibility(View.GONE);
                llSecond.setVisibility(View.VISIBLE);

                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                );

                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) videoView.getLayoutParams();
                params.width = metrics.widthPixels;
                params.height = metrics.heightPixels;
                params.leftMargin = 0;
                params.bottomMargin = 0;
                params.topMargin = 0;
                params.rightMargin = 0;
                videoView.setLayoutParams(params);

              /*  DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                videoView.setLayoutParams(new FrameLayout.LayoutParams(metrics.widthPixels, metrics.heightPixels));*/

                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.cartoon_video);
                videoView.setVideoURI(uri);
                videoView.start();

                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        videoDuration = videoView.getDuration();
                        setCountDown(videoDuration);
                    }
                });

                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        Toast.makeText(AnimationActivity.this, "Video End", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(AnimationActivity.this, FormActivity.class));
                    }
                });
            }
        }, 3000);
    }

    @OnClick(R.id.btn_begin)
    public void onClick() {

    }

    //set count down timer in top right corner
    private void setCountDown(long duration) {
        CountDownTimer countdown = new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                txtTime.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                txtTime.setText("00");
            }
        };
        countdown.start();
    }
}
