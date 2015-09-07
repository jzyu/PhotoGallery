package jzyu.github.AndroidTest;

import android.net.Uri;
import android.test.AndroidTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class UnitTest extends AndroidTestCase {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void test_Uri() throws Exception {
        final String URL = "http://7xjbzg.com2.z0.glb.qiniucdn.com/upload/QQShot20150907101715.png?imageView2/2/w/400";
        Uri uri = Uri.parse(URL);
        System.out.println(uri.getHost());
        assertEquals(URL, uri.getHost());
    }
}