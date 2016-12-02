package unima.bmvidatarun.truckoo.view.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import unima.bmvidatarun.R;

/**
 * Created by Marko on 02.12.16.
 */

public class TimeActivity extends AppCompatActivity {
    @BindView(R.id.circle_progress_bar1) ProgressBar progressBar1;
    @BindView(R.id.circle_progress_bar2) ProgressBar progressBar2;
    @BindView(R.id.circle_progress_bar3) ProgressBar progressBar3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        ButterKnife.bind(this);
        progressBar1.setProgress(81);
        progressBar2.setProgress(94);
        progressBar3.setProgress(90);

    }

}
