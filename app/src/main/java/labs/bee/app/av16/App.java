package labs.bee.app.av16;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by arysuryawan on 11/29/16.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }
}
