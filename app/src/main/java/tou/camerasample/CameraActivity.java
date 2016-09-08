package tou.camerasample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Tou on 9/7/2016.
 */
public class CameraActivity extends Activity {

    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        backButton = (Button)findViewById(R.id.btn_camera_back);
        backButton.setOnClickListener(btnBackButton);
    }

    private View.OnClickListener btnBackButton = new  View.OnClickListener(){
        public void onClick(View v) {
            finish();
        }

    };
}
