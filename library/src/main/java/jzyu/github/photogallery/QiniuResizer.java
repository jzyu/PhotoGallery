package jzyu.github.photogallery;

import android.net.Uri;

public class QiniuResizer implements ImageUrlResizer {

    private static final String QINIU_HOST = "qiniu";
    private static final String QINIU_RESIZE_QUERY = "imageView2/2/w/";

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
        String query = QINIU_RESIZE_QUERY + WIDTH_FOR_SCREEN;

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

    private int getWidthParam(String url) {
        if (! isResizeable(url))
            return -1;

        Uri uri = Uri.parse(url);
        int start = uri.getQuery().lastIndexOf('/');
        String width = uri.getQuery().substring(start);

        return Integer.parseInt(width);
    }

    @Override
    public SizeType getSizeType(String url) {
        int width = getWidthParam(url);

        if (width < 0 ) {
            return SizeType.ORIGINAL;
        } else if (width < WIDTH_FOR_SCREEN) {
            return SizeType.SMALL;
        } else {
            return SizeType.BIG;
        }
    }
}
