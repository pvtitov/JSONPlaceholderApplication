package pvtitov.jsonplaceholderapplication.api_service;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Павел on 08.10.2017.
 */

public class JsonPlaceHolderCallback<T> implements retrofit2.Callback<T> {
    private TextView mTextView;
    private ImageView mImageView;
    private Context mContext;
    private String mString;

    public JsonPlaceHolderCallback(TextView textView){
        mTextView = textView;
    }

    public JsonPlaceHolderCallback(List<String> strings, int i){
        mString = strings.get(i);
    }

    public JsonPlaceHolderCallback(Context context, ImageView imageView){
        mImageView = imageView;
        mContext = context;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (body instanceof StringFromResponse) {
            StringFromResponse responseBody = (StringFromResponse) body;
            mTextView.setText(responseBody.getData());
            mString = responseBody.getData();
        }
        if (body instanceof ImageFromResponse) {
            ImageFromResponse responseBody = (ImageFromResponse) body;
            Picasso.with(mContext).load(responseBody.getUrl()).into(mImageView);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }
}