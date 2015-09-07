package jzyu.github.photogallery;

import android.net.Uri;

public class QiniuResizer implements ImageUrlResizer {

    private static final String QINIU_HOST = "qiniu";
    private static final String QINIU_RESIZE_QUERY = "imageView2/2";

    private static final int WIDTH_FOR_SCREEN = 600;

    //来自七牛且带有resize query的图片url，才支持resize，否则直接显示原图
    private static boolean isResizeable(String url) {
        Uri uri = Uri.parse(url);
        return uri.getHost().contains(QINIU_HOST)
                && uri.getQuery() != null
                && uri.getQuery().contains(QINIU_RESIZE_QUERY);
    }

    //得到适合屏幕显示的url：重设query的width参数
    @Override
    public String getUrlForScreen(String url) {
        if (! isResizeable(url))
            return url;

        Uri uri = Uri.parse(url);
        String query = "imageView2/2/w/" + WIDTH_FOR_SCREEN;

        return uri.getScheme()
                + "://" + uri.getHost()
                + uri.getPath()
                + "?" + query;
    }

    //得到原图url：清除query
    @Override
    public String getUrlForOriginal(String url) {
        if (! isResizeable(url))
            return url;

        Uri uri = Uri.parse(url);
        return uri.getScheme()
                + "://" + uri.getHost()
                + uri.getPath();
    }
}
