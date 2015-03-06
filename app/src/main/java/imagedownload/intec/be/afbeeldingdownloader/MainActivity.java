package imagedownload.intec.be.afbeeldingdownloader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startDownloading(View view) {
        if (view.getId() == R.id.button) {
            Intent intent = new Intent(this, MyService.class);
            startService(intent);
        }
    }

    public void stopDownloading(View view) {
        if (view.getId() == R.id.button2) {
            stopService(new Intent(this, MyService.class));
        }
    }
}
