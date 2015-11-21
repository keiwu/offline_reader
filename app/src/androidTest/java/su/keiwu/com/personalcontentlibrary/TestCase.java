package su.keiwu.com.personalcontentlibrary;

import android.test.AndroidTestCase;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import su.keiwu.com.personalcontentlibrary.utils.Utils;

/**
 * Created by kei on 11/20/15.
 */
public class TestCase extends AndroidTestCase {

    public void testFromString(){
        String testString = "junit test";
        InputStream stream = new ByteArrayInputStream(testString.getBytes(Charset.forName("UTF-8")));
        String resultString = Utils.getContentFromStream(stream);
        assertEquals(testString, resultString);
    }

    public void testValidUrl(){
        String path = "http://www.google.com";
        HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(path);
        String content = null;

        try{
            HttpResponse hr = hc.execute(hg);
            StatusLine sl = hr.getStatusLine();
            if(sl.getStatusCode() == 200){
                HttpEntity he = hr.getEntity();
                InputStream is = he.getContent();
                content = Utils.getContentFromStream(is);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        assertNotNull(content);

    }

    public void testEmptyUrl(){
        String path = "";
        HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(path);
        String content = null;

        try{
            HttpResponse hr = hc.execute(hg);
            StatusLine sl = hr.getStatusLine();
            if(sl.getStatusCode() == 200){
                HttpEntity he = hr.getEntity();
                InputStream is = he.getContent();
                content = Utils.getContentFromStream(is);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        assertNull(content);

    }

    public void testInvalidUrl(){
        String path = "moc.elgoog.www//:ptth";
        HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(path);
        String content = null;

        try{
            HttpResponse hr = hc.execute(hg);
            StatusLine sl = hr.getStatusLine();
            if(sl.getStatusCode() == 200){
                HttpEntity he = hr.getEntity();
                InputStream is = he.getContent();
                content = Utils.getContentFromStream(is);

            }
        } catch (Exception e){
            e.printStackTrace();
        }
        assertNull(content);

    }

}
