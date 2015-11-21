package su.keiwu.com.personalcontentlibrary.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImage;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

import su.keiwu.com.personalcontentlibrary.R;
import su.keiwu.com.personalcontentlibrary.activities.ContentActivity;
import su.keiwu.com.personalcontentlibrary.activities.MainActivity;
import su.keiwu.com.personalcontentlibrary.model.Article;

/**
 * Created by kei on 11/20/15.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ListHolder>{
    ArrayList<Article> articles = new ArrayList<Article>();
    Context context;
    OnDualPanListener listener;


    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ListHolder(view);
    }

    public ArticleAdapter(Context context, ArrayList<Article> articles, OnDualPanListener listener){
        this.articles = articles;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(ListHolder listHolder, int position) {
        listHolder.setData(position);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    class ListHolder extends RecyclerView.ViewHolder{
//        ImageView image;
        TextView title;
        SmartImageView siv;

        public ListHolder(View itemView){
            super(itemView);
//            image = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            siv = (SmartImageView) itemView.findViewById(R.id.siv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    Log.d("RecyclerView", "onClick：" + getPosition());
                    Context context = v.getContext();
                    if (MainActivity.isDualPane){
                        Log.d("dualPan", "true");
                        String content = articles.get(position).getContent();
                        listener.onDualPanListener(content);
                    } else {
                        Intent i = new Intent(context, ContentActivity.class);
                        i.putExtra(ContentActivity.CONTENT, articles.get(position).getContent());
                        context.startActivity(i);
                    }
                }
            });
        }

        public void setData(int position){
//            image.setImageResource(R.drawable.article);
            title.setText(articles.get(position).getTitle());
            String imageUrl = articles.get(position).getImage_path();

            if (imageUrl != null)
                siv.setImageUrl(imageUrl);


        }


    }

    public interface OnDualPanListener{
        public void onDualPanListener(String content);
    }
}
