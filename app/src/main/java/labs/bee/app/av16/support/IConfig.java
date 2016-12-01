package labs.bee.app.av16.support;

import android.app.Application;
import android.os.Environment;

import labs.bee.app.av16.App;
import labs.bee.app.av16.R;

/**
 * Created by arysuryawan on 11/27/16.
 */

public interface IConfig {

    final String VIDEO_URL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    final String VIDEO_ROOT_PATH = "/AVR/Video/";
    final String AUDIO_ROOT_PATH = "/AVR/Audio/";
    final String SDCARD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

}
