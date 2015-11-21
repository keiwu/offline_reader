package su.keiwu.com.personalcontentlibrary.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.auth.api.Auth;

import su.keiwu.com.personalcontentlibrary.R;
import su.keiwu.com.personalcontentlibrary.fragments.DetailFragment;
import su.keiwu.com.personalcontentlibrary.fragments.TitleFragment;

public class MainActivity extends AppCompatActivity implements TitleFragment.OnTitleFragmentInteractionListener, DetailFragment.OnDetailFragmentInteractionListener{
    public static final int ADD_ARTICLE = 1;
    public static final int ADD_ARTICLE_SUCCESS = 2;
    FrameLayout detailFl;
    FrameLayout titleFl;
    public static boolean isDualPane = false;
    DetailFragment df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void initView(){
        detailFl = (FrameLayout) findViewById(R.id.detail_container);

        if (detailFl != null) {
            Log.d("mainActivity", "large screen layout");
            isDualPane=true;

            df = DetailFragment.newInstance("", "");
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.detail_container, df);
            ft.commit();
        }


        titleFl = (FrameLayout) findViewById(R.id.title_container);

        if (titleFl != null){
            Log.d("mainActivity", "small screen layout bbbbb");
            TitleFragment tf = TitleFragment.newInstance("", "");
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.title_container, tf);
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_article) {
            Intent i = new Intent(this, AddArticleActivity.class);
            startActivityForResult(i, ADD_ARTICLE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTitleFragmentInteraction(String content) {
        content = "<html><head></head><body>" + content + "</body></html>";
        Log.d("onTitleFragen", "onTitleFragentInteraction" + content);
        df.setContent(content);
    }

    @Override
    public void onDetailFragmentInteraction(Uri uri) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == ADD_ARTICLE_SUCCESS) {
            Log.d("addArticle", "success");
            //Refresh recycler view after article is added
            initView();

        }


    }
}
