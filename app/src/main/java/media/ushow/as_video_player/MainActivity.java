package media.ushow.as_video_player;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    private Button forward_video_player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        forward_video_player = (Button) findViewById(R.id.forward_video_player);
        forward_video_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangbaPlayerActivity.class);
                startActivity(intent);
            }
        });

        requestPermission();
    }

    private void requestPermission() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {
                PermissionManager.sharedInstance().requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA,
                                android.Manifest.permission.RECORD_AUDIO},
                        PermissionManager.RequestCodeCAMERA, new PermissionManager.Listener() {
                            @Override
                            public void onGranted(int requestCode) {

                            }

                            @Override
                            public void onDenied(int requestCode) {

                            }

                            @Override
                            public void onAlwaysDenied(int requestCode, List<String> permissions) {

                            }
                        });
            }
        }, 20);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
