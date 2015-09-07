package jzyu.github.photogallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;

import uk.co.senab.photoview.PhotoView;


public class PhotoGalleryActivity extends Activity implements View.OnClickListener{
    private static final String EXTRA_URLS = "extra_urls";
    private static final String EXTRA_POSITION = "extra_position";

    private ViewPager mViewPager;
    private String[] mUrls;
    private ImageUrlResizer mResizer = new QiniuResizer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photogallery);

        ViewTarget.setTagId(R.id.glide_request);

        mUrls = getIntent().getStringArrayExtra(EXTRA_URLS);

        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.setCurrentItem(getIntent().getIntExtra(EXTRA_POSITION, 0));

        findViewById(R.id.button_original).setOnClickListener(this);
        findViewById(R.id.button_save).setOnClickListener(this);
    }

    private void initFunctionalView(int position) {
        ((TextView)findViewById(R.id.tv_cursor)).setText((position + 1) + " / " + mUrls.length);

        // display button - original
        String url = mUrls[position];
        if (url.equals(mResizer.getUrlForOriginal(url))) {
            findViewById(R.id.button_original).setVisibility(View.INVISIBLE);
        } else {
            findViewById(R.id.button_original).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        int pos = mViewPager.getCurrentItem();

        if (v.getId() == R.id.button_original) {
            PhotoView photoView = (PhotoView) mViewPager.findViewWithTag(mUrls[pos]);
            String newUrl = mResizer.getUrlForOriginal(mUrls[pos]);

            Glide.with(this)
                        .load(newUrl)
                        .into(photoView);

            mUrls[pos] = newUrl;
            v.setVisibility(View.INVISIBLE);
        } else if (v.getId() == R.id.button_save) {
            Toast.makeText(this, "save", Toast.LENGTH_SHORT).show();
        }
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mUrls.length;
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            PhotoView photoView = new PhotoView(container.getContext());
            String url = mResizer.getUrlForScreen(mUrls[position]);
            container.setBackgroundColor(Color.BLACK);

            Glide.with(PhotoGalleryActivity.this)
                    .load(url)
                    .into(photoView);
            container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

            photoView.setTag(mUrls[position]);
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

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
            initFunctionalView(position);
        }
    }

    public static Intent newIntent(Context context, String[] urls, int position) {
        Intent intent = new Intent(context, PhotoGalleryActivity.class);
        intent.putExtra(EXTRA_URLS, urls);
        intent.putExtra(EXTRA_POSITION, position);
        return intent;
    }
}
