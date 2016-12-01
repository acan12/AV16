package labs.bee.app.av16.support.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import labs.bee.app.av16.AudioVideoRecordingActivity;
import labs.bee.app.av16.R;
import labs.bee.app.av16.support.IConfig;

/**
 * Created by arysuryawan on 12/1/16.
 */

public class ProgressService extends AsyncTask<String, String, String> {
    ProgressDialog progressDialog;
    Context context;
    String audioSavePathInDevice;

    public ProgressService(String audioSavePathInDevice, Context context) {
        this.audioSavePathInDevice = audioSavePathInDevice;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, null, "PLease wait...", true);
        progressDialog.setCancelable(true);
    }

    @Override
    protected String doInBackground(String... params) {
        downloadFile(IConfig.VIDEO_URL, "Sample.mp4", audioSavePathInDevice);
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
    }


    private void downloadFile(String fileURL, String fileName, String audioFilePath) {
        try {
            String rootDir = Environment.getExternalStorageDirectory()
                    + File.separator + context.getResources().getString(R.string.app_name) + File.separator ;
            File rootFile = new File(rootDir+ "Video");
            rootFile.mkdir();
            URL url = new URL(fileURL);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();
            FileOutputStream f = new FileOutputStream(new File(rootFile, fileName));
            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();

            AudioVideoRecordingActivity.setupMuxAV(rootFile+fileName, audioFilePath);
        } catch (IOException e) {
            Log.d("Error....", e.toString());
        }

    }
}