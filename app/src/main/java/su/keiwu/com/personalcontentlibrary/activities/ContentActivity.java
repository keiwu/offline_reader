package su.keiwu.com.personalcontentlibrary.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import su.keiwu.com.personalcontentlibrary.R;

public class ContentActivity extends AppCompatActivity {
    public static String CONTENT = "content";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        TextView contentTv = (TextView) findViewById(R.id.content);
        WebView webView = (WebView) findViewById(R.id.webView);
        String content = getIntent().getExtras().getString(CONTENT);
        //contentTv.setText(content);
        content = "<html><head></head><body>" + content + "</body></html>";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadData(content, "text/html", "utf-8");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_content, menu);
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
}
