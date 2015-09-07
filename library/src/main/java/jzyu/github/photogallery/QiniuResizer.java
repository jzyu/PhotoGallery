package jzyu.github.photogallery;

import android.net.Uri;

public class QiniuResizer implements ImageUrlResizer {

    public static final String QINIU_HOST = "qiniu";

    private static boolean isResizeable(String url) {
        Uri uri = Uri.parse(url);
        return uri.getHost().contains(QINIU_HOST);
    }

    //重设width参数
    @Override
    public String getUrlForScreen(String url) {
        if (! isResizeable(url))
            return url;

        Uri uri = Uri.parse(url);
        String query = "imageView2/2/w/" + 200;

        return uri.getScheme()
                + "://" + uri.getHost()
                + uri.getPath()
                + "?" + query;
    }

    //清除query
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
