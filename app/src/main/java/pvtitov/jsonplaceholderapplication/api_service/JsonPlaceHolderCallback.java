package pvtitov.jsonplaceholderapplication.api_service;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Павел on 08.10.2017.
 */

public class JsonPlaceHolderCallback<T> implements retrofit2.Callback<T> {
    private TextView mTextView;

    public JsonPlaceHolderCallback(TextView textView){
        mTextView = textView;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        T body = response.body();
        if (body instanceof CommonResponse) {
            CommonResponse commonResponseBody = (CommonResponse) body;
            mTextView.setText(commonResponseBody.getData());
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }
}