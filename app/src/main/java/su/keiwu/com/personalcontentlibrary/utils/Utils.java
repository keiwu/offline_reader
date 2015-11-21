package su.keiwu.com.personalcontentlibrary.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by kei on 11/19/15.
 */
public class Utils {
    public static String getContentFromStream(InputStream is){
        byte[] b = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            String content = new String(bos.toByteArray());
            bos.close();
            return content;
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public static int sum(int a, int b){
        return a+b;
    }
}
