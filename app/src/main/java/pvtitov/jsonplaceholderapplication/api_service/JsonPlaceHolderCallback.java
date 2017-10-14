package pvtitov.jsonplaceholderapplication.api_service;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pvtitov.jsonplaceholderapplication.UserAdapter;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Павел on 08.10.2017.
 */

public class JsonPlaceHolderCallback<T> implements retrofit2.Callback<T> {

    private static final String TEXTVIEW_CONSTRUCTOR = "textview";
    private static final String IMAGEVIEW_CONSTRUCTOR = "imageview";
    private static final String LIST_CONSTRUCTOR = "list";

    private TextView mTextView;
    private ImageView mImageView;
    private Context mContext;
    private RecyclerView mListView;
        private List<String> mList = new ArrayList<>();
    private String constructorFlag;

    public JsonPlaceHolderCallback(TextView textView){
        constructorFlag = TEXTVIEW_CONSTRUCTOR;
        mTextView = textView;
    }

    public JsonPlaceHolderCallback(Context context, RecyclerView listView, List<String> list){
        constructorFlag = LIST_CONSTRUCTOR;
        mListView = listView;
        mList = list;
        mContext = context;
    }

    public JsonPlaceHolderCallback(Context context, ImageView imageView){
        constructorFlag = IMAGEVIEW_CONSTRUCTOR;
        mImageView = imageView;
        mContext = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        NeededData<String> responseBody = (NeededData<String>) body;
        switch (constructorFlag) {
            case TEXTVIEW_CONSTRUCTOR:
                mTextView.setText(responseBody.getData());
                return;
            case IMAGEVIEW_CONSTRUCTOR:
                Picasso.with(mContext).load(responseBody.getData()).into(mImageView);
                return;
            case LIST_CONSTRUCTOR:
                mList.add(responseBody.getData());
                RecyclerView.Adapter adapter = new UserAdapter(mList);
                mListView.setAdapter(adapter);
                return;
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }
}