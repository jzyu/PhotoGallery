package jzyu.github.photogallery;

public interface ImageUrlResizer {
    //返回适合屏幕显示的图片url
    String getUrlForScreen(String url);

    //返回原图url
    String getUrlForOriginal(String url);
}
