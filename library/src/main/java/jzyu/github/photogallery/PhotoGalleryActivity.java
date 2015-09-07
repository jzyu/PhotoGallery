package jzyu.github.photogallery;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;


public class PhotoGalleryActivity extends Activity {
    private static final String[] sUrls = {
            "http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Parinacota%20Lauca%20National%20Park%20Andes%20Mountains%20Chile.jpg",
            "http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Ferns%20and%20Aspens%20Rocky%20Mountain%20National%20Park%20Colorado.jpg",
            "http://www.wallcoo.com/nature/2008_Landscape_1680_Desktop_02/wallpapers/1600x1200/Morning%20Mist.jpg",
    };

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photogallery);

        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new SamplePagerAdapter());
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return sUrls.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            Glide.with(PhotoGalleryActivity.this)
                    .load(sUrls[position])
                    .placeholder(R.drawable.pic_listitem_null)
                    .error(R.drawable.pic_listitem_null)
                    .into(photoView);
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
