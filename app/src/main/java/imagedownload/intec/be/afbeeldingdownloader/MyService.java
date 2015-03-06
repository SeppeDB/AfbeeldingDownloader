package imagedownload.intec.be.afbeeldingdownloader;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Starting", Toast.LENGTH_LONG).show();

        try {
            URL url1 = new URL("http://img3.wikia.nocookie.net/__cb20111221042919/finalfantasy/images/thumb/2/2b/FF_1_PSP.jpg/250px-FF_1_PSP.jpg");
            URL url2 = new URL("http://img4.wikia.nocookie.net/__cb20070325151105/finalfantasy/images/thumb/f/f0/FFII_PSP_Logo.jpg/250px-FFII_PSP_Logo.jpg");
            URL url3 = new URL("http://img2.wikia.nocookie.net/__cb20100805044558/finalfantasy/images/thumb/9/9a/Ff3-DSlogo.png/242px-Ff3-DSlogo.png");
            URL url4 = new URL("http://img1.wikia.nocookie.net/__cb20110410161527/finalfantasy/images/thumb/c/c2/FFIVnds_logo.jpg/240px-FFIVnds_logo.jpg");
            URL url5 = new URL("http://img3.wikia.nocookie.net/__cb20060221072345/finalfantasy/images/thumb/5/52/Ff5-logo.jpg/242px-Ff5-logo.jpg");
            URL url6 = new URL("http://img3.wikia.nocookie.net/__cb20120805195050/finalfantasy/images/thumb/4/49/Ff6-logo.jpg/250px-Ff6-logo.jpg");
            URL url7 = new URL("http://img4.wikia.nocookie.net/__cb20100805045741/finalfantasy/images/thumb/e/e2/FF7logo.jpg/250px-FF7logo.jpg");
            URL url8 = new URL("http://img1.wikia.nocookie.net/__cb20100805044600/finalfantasy/images/thumb/3/37/Ff8_logo.png/250px-Ff8_logo.png");
            URL url9 = new URL("http://img3.wikia.nocookie.net/__cb20100721024726/finalfantasy/images/thumb/8/8e/FfixLogo.jpg/250px-FfixLogo.jpg");
            URL url10 = new URL("http://img2.wikia.nocookie.net/__cb20100721021039/finalfantasy/images/thumb/d/d7/Ff10_logo.jpeg/250px-Ff10_logo.jpeg");
            URL url11 = new URL("http://img3.wikia.nocookie.net/__cb20120614002218/finalfantasy/images/thumb/4/48/Ff11_logo.png/300px-Ff11_logo.png");
            URL url12 = new URL("http://img3.wikia.nocookie.net/__cb20100831212715/finalfantasy/images/thumb/b/b4/Ff12_logo.jpeg/250px-Ff12_logo.jpeg");
            URL url13 = new URL("http://img1.wikia.nocookie.net/__cb20120614005423/finalfantasy/images/thumb/9/94/Final_Fantasy_XIII_Logo.jpg/250px-Final_Fantasy_XIII_Logo.jpg");
            URL url14 = new URL("http://img4.wikia.nocookie.net/__cb20130126083247/finalfantasy/images/thumb/c/cf/ARR_FFXIV_Logo.png/250px-ARR_FFXIV_Logo.png");

            new downloadTask().execute(url1, url2, url3, url4, url5, url6, url7, url8, url9, url10, url11, url12, url13, url14);
        } catch (MalformedURLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();
    }

    private long downloadPictures(URL url, String fileName) {
        long count = 0;
        try {
            File file = new File(String.valueOf(Environment.getExternalStorageDirectory()) + File.separator + fileName);
            file.createNewFile();

            InputStream input = url.openStream();
            OutputStream output = new FileOutputStream(file);

            int line = 0;
            while ((line = input.read()) != -1) {
                output.write(line);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }


    private class downloadTask extends AsyncTask<URL, Integer, Long> {
        @Override
        protected Long doInBackground(URL... params) {

            int count = params.length;
            long totalBytesDownloaded = 0;

            for (int i = 0; i < count; i++) {
                totalBytesDownloaded += downloadPictures(params[i], "Afbeelding " +  (i+1));
                double procent = ((i+1) / (double)count)*100;

                publishProgress((int)procent);
                Log.v("DOWNLOADED", "Bytes: " + totalBytesDownloaded);
            }

            return totalBytesDownloaded;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getBaseContext(), values[0] + "% downloaded", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(), "Aantal bytes downloaded: " + result, Toast.LENGTH_LONG).show();
        }
    }
}



