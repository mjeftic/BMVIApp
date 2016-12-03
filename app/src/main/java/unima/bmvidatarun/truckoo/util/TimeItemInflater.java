package unima.bmvidatarun.truckoo.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import unima.bmvidatarun.R;

/**
 * Created by Mukizen on 03.12.2016.
 */

public class TimeItemInflater {

    public static void setupItem(final View view, int position) {
        final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.circle_progress_bar);
        progressBar.setProgress(85);
    }
}
