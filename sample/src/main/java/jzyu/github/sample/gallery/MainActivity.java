package jzyu.github.sample.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.request.target.ViewTarget;

import jzyu.github.photogallery.PhotoGalleryActivity;


public class MainActivity extends Activity {

    private static final String[] sUrls = {
            /*
            "http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Parinacota%20Lauca%20National%20Park%20Andes%20Mountains%20Chile.jpg",
            */

            "http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Morning%20Mist.jpg",
            "http://7xjbzg.com2.z0.glb.qiniucdn.com/upload/QQ%E6%88%AA%E5%9B%BE20150906145109.jpg?imageView2/2/w/60",
            "http://7xjbzg.com2.z0.glb.qiniucdn.com/upload/upload_b506af0904d4abe645459f63780d19e0.jpg?imageView2/2/w/60",
            "http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Ferns%20and%20Aspens%20Rocky%20Mountain%20National%20Park%20Colorado.jpg",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(PhotoGalleryActivity.newIntent(MainActivity.this, sUrls, 1));
            }
        });
    }
}
