package jzyu.github.sample.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;

import jzyu.github.photogallery.PhotoGalleryActivity;


public class MainActivity extends Activity {

    private static final String[] sUrls = {
            "http://7xjbzg.com2.z0.glb.qiniucdn.com/upload/weplant-upload-1443785262155.jpeg?imageView2/2/w/64",
            "http://7xjbzg.com2.z0.glb.qiniucdn.com/upload/648808903810879488.png?imageView2/2/w/400"
            /*"http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Morning%20Mist.jpg",
            "http://7xjbzg.com2.z0.glb.qiniucdn.com/upload/QQ%E6%88%AA%E5%9B%BE20150906145109.jpg?imageView2/2/w/60",
            "http://7xjbzg.com2.z0.glb.qiniucdn.com/upload/upload_b506af0904d4abe645459f63780d19e0.jpg?imageView2/2/w/60",
            "http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Ferns%20and%20Aspens%20Rocky%20Mountain%20National%20Park%20Colorado.jpg",*/
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

        final ImageView iv1 = (ImageView) findViewById(R.id.iv1);
        final ImageView iv2 = (ImageView) findViewById(R.id.iv2);

        Glide.with(this).load(sUrls[0]).into(iv1);
        Glide.with(this).load(sUrls[1]).into(iv2);

        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewOnClick(iv1, 0);
            }
        });

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewOnClick(iv2, 1);
            }
        });
    }

    private void imageViewOnClick(ImageView iv, int urlIndex) {
        startActivity(PhotoGalleryActivity.newIntent(MainActivity.this, sUrls, urlIndex));
    }
}
