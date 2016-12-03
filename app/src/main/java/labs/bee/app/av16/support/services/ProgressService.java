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
import labs.bee.app.av16.support.tools.Mux;

/**
 * Created by arysuryawan on 12/1/16.
 */

public class ProgressService extends AsyncTask<String, String, String> {
    ProgressDialog progressDialog;
    Context context;
    String audioSavePathInDevice;
    String audioPath;

    public ProgressService(String audioSavePathInDevice, String audioPath, Context context) {
        this.audioSavePathInDevice = audioSavePathInDevice;
        this.audioPath = audioPath;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, null, "PLease wait...", true);
        progressDialog.setCancelable(true);
    }

    @Override
    protected String doInBackground(String... params) {
        downloadFile(IConfig.VIDEO_URL, "dodol.mp4", audioSavePathInDevice, audioPath);
        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        progressDialog.dismiss();
    }


    private void downloadFile(String fileURL, String fileName, String audioFilePath, String audioPath) {
        try {
            String rootDir = Environment.getExternalStorageDirectory()
                    + File.separator + context.getResources().getString(R.string.app_name) + File.separator;

            String videoPath = rootDir + "Video" + File.separator;
            File rootFile = new File(videoPath);
            rootFile.mkdir();
            URL url = new URL(fileURL);
            HttpURLConnection c = (HttpURLConnection) url.openConnection();
            c.setRequestMethod("GET");
            c.setDoOutput(true);
            c.connect();

            File file = new File(videoPath,fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream f = new FileOutputStream(file);
            InputStream in = c.getInputStream();
            byte[] buffer = new byte[1024];
            int len1 = 0;
            while ((len1 = in.read(buffer)) > 0) {
                f.write(buffer, 0, len1);
            }
            f.close();
            in.close();
            c.disconnect();


            String outputFilePath = videoPath + "output.mp4";

            Mux.doMux(videoPath+"dodol.mp4", audioFilePath, outputFilePath);
        } catch (Exception e) {
            Log.d("Error....", e.toString());
        }

    }
}