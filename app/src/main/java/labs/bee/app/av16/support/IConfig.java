package labs.bee.app.av16.support;

import android.os.Environment;

/**
 * Created by arysuryawan on 11/27/16.
 */

public interface IConfig {
    String VIDEO_URL = "http://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4";
    //    String VIDEO_URL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

}
