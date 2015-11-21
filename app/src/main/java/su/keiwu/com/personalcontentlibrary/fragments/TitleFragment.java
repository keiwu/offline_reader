package su.keiwu.com.personalcontentlibrary.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;

import java.util.ArrayList;

import su.keiwu.com.personalcontentlibrary.R;
import su.keiwu.com.personalcontentlibrary.activities.AddArticleActivity;
import su.keiwu.com.personalcontentlibrary.activities.MainActivity;
import su.keiwu.com.personalcontentlibrary.adapter.ArticleAdapter;
import su.keiwu.com.personalcontentlibrary.model.Article;
import su.keiwu.com.personalcontentlibrary.utils.DBHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TitleFragment.OnTitleFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TitleFragment extends Fragment implements ArticleAdapter.OnDualPanListener, View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnTitleFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    Button addBtn;
    ArrayList<Article> articles = new ArrayList<Article>();
    DBHelper dbHelper;
    public static ArticleAdapter articleAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TitleFragment newInstance(String param1, String param2) {
        TitleFragment fragment = new TitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public TitleFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title_fragment, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTitleFragmentInteraction(null);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        addBtn = (Button) view.findViewById(R.id.add);
        addBtn.setOnClickListener(this);
//        addBtn.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), AddArticleActivity.class);
//                startActivityForResult(i, MainActivity.ADD_ARTICLE);
//            }
//        });
        Log.d("onViewCreated", "onViewCreated");
        initData();
        initListView();
    }

    public void initListView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        articleAdapter = new ArticleAdapter(getActivity(), articles, this);
        recyclerView.setAdapter(articleAdapter);

    }

    public void initData(){
        dbHelper = DBHelper.getInstance(getActivity());
        Cursor c = dbHelper.query();
        Log.d("initData", c.getCount() + "");

        if (c.getCount() <= 0){
            recyclerView.setVisibility(View.GONE);
            addBtn.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            addBtn.setVisibility(View.GONE);
        }

        while (c.moveToNext()){
            String id = c.getString(0);
            String title = c.getString(1);
            String image_path = c.getString(2);
            String content= c.getString(3);

            Log.d("initData", id + " " + title + " " + image_path + " " + content);
            Article article = new Article(title, image_path, content);

            articles.add(article);

        }


    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnTitleFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDualPanListener(String content) {
        mListener.onTitleFragmentInteraction(content);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(getActivity(), AddArticleActivity.class);
        getActivity().startActivityForResult(i, MainActivity.ADD_ARTICLE);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnTitleFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onTitleFragmentInteraction(String content);
    }

}
