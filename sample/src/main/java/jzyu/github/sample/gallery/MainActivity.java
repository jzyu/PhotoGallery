package jzyu.github.sample.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import jzyu.github.photogallery.PhotoGalleryActivity;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, PhotoGalleryActivity.class));
            }
        });
    }
}
