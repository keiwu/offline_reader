package su.keiwu.com.personalcontentlibrary.activities;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

import su.keiwu.com.personalcontentlibrary.R;
import su.keiwu.com.personalcontentlibrary.utils.DBHelper;
import su.keiwu.com.personalcontentlibrary.utils.Utils;

public class AddArticleActivity extends AppCompatActivity {
    EditText urlEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        urlEt = (EditText) findViewById(R.id.add_article_et);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_article, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }

    public void addArticle(View v){
        final String url = urlEt.getText().toString();
        new Thread(){
            @Override
            public void run() {
                getContents(url);
            }
        }.start();


    }

    public void getContents(String url){
        String path = "https://www.readability.com/api/content/v1/parser?url=" + url + "&token=dc77258d03194b59b092fa5826f6fd4f9208d69e";

        HttpClient hc = new DefaultHttpClient();
        HttpGet hg = new HttpGet(path);

        try{
            HttpResponse hr = hc.execute(hg);
            StatusLine sl = hr.getStatusLine();
            if(sl.getStatusCode() == 200){
                HttpEntity he = hr.getEntity();
                InputStream is = he.getContent();
                String content = Utils.getContentFromStream(is);
                Log.d("fromServer", "content" + content);
                processData(content);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void processData(String input){
        try {
            JSONObject reader = new JSONObject(input);
            String excerpt = reader.getString("excerpt");
            String image_url = reader.getString("lead_image_url");
            String title = reader.getString("title");
            String content = reader.getString("content");

            Log.d("processing", excerpt + " " + image_url + " " + title + " " + content);


            ContentValues cv = new ContentValues();
            cv.put(DBHelper.TITLE, title);
            cv.put(DBHelper.IMAGE_NAME, image_url);
            cv.put(DBHelper.CONTENT, content);

            DBHelper dbHelper = DBHelper.getInstance(this);
            dbHelper.insertArticle(cv);
            setResult(MainActivity.ADD_ARTICLE_SUCCESS);
            finish();

        } catch (JSONException je){
            je.printStackTrace();
        }

    }



}


