package pvtitov.jsonplaceholderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pvtitov.jsonplaceholderapplication.api_service.CommentsModel;
import pvtitov.jsonplaceholderapplication.api_service.JSONPlaceHolderApi;
import pvtitov.jsonplaceholderapplication.api_service.JsonPlaceHolderCallback;
import pvtitov.jsonplaceholderapplication.api_service.PhotosModel;
import pvtitov.jsonplaceholderapplication.api_service.PostsModel;
import pvtitov.jsonplaceholderapplication.api_service.TodosModel;
import pvtitov.jsonplaceholderapplication.api_service.UsersModel;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_contacts:
                    Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                    startActivity(intent);
                    return true;
            }
            return false;
        }

    };

    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    JSONPlaceHolderApi mJsonPlaceHolderApi = mRetrofit.create(JSONPlaceHolderApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Карточка с постами
        // Номер поста вводит пользаватель
        TextView postTextView = (TextView) findViewById(R.id.post);
        EditText postRequest = (EditText) findViewById(R.id.post_request);
        ImageButton postRequestButton = (ImageButton) findViewById(R.id.post_request_button);
            PostRequestListener postRequestListener = new PostRequestListener(postTextView, postRequest);
            postRequestButton.setOnClickListener(postRequestListener);


        // Карточка с комментариями
        TextView commentTextView = (TextView) findViewById(R.id.comment);
        EditText commentRequest = (EditText) findViewById(R.id.comment_request);
        ImageButton commentRequestButton = (ImageButton) findViewById(R.id.comment_request_button);
            CommentRequestListener commentRequestListener = new CommentRequestListener(commentTextView, commentRequest);
            commentRequestButton.setOnClickListener(commentRequestListener);


        // Карточка со списком пользователей
        RecyclerView usersListView = (RecyclerView) findViewById(R.id.users);
        usersListView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        usersListView.setLayoutManager(layoutManager);

        List<String> usersList = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            JsonPlaceHolderCallback<UsersModel> userCallback = new JsonPlaceHolderCallback<>(this, usersListView, usersList);
            mJsonPlaceHolderApi.getUser(i+1).enqueue(userCallback);
        }


        // Карточка с картинкой
        ImageView photoImageView = (ImageView) findViewById(R.id.photo);
        JsonPlaceHolderCallback<PhotosModel> photoCallback = new JsonPlaceHolderCallback<>(this, photoImageView);
        mJsonPlaceHolderApi.getPhoto(3).enqueue(photoCallback);


        // Карточка с задачами
        TextView todoTextView = (TextView) findViewById(R.id.todo);
        JsonPlaceHolderCallback<TodosModel> todoCallback = new JsonPlaceHolderCallback<>(todoTextView);
        mJsonPlaceHolderApi.getTodo(randomIntFromOneTo(200)).enqueue(todoCallback);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



    private int randomIntFromOneTo(int max) {
        double randomNumber = Math.random()*(max-1)+1;
        return (int)randomNumber;
    }



    private class PostRequestListener implements View.OnClickListener {

        TextView mTextView;
        EditText mEditText;

        public PostRequestListener(TextView textView, EditText editText) {
            mTextView = textView;
            mEditText = editText;
        }

        @Override
        public void onClick(View view) {
            int postNumber = 0;

            try {
                postNumber = Integer.parseInt(mEditText.getText().toString());
            } catch (NumberFormatException e) {
                mTextView.setText(e.getLocalizedMessage());
            }

            if ((postNumber >= 1) && (postNumber <= 100)) {
                JsonPlaceHolderCallback<PostsModel> postCallback = new JsonPlaceHolderCallback<>(mTextView);
                mJsonPlaceHolderApi.getPost(postNumber).enqueue(postCallback);
            }
        }
    }

    private class CommentRequestListener implements View.OnClickListener {

        TextView mTextView;
        EditText mEditText;

        public CommentRequestListener(TextView textView, EditText editText) {
            mTextView = textView;
            mEditText = editText;
        }

        @Override
        public void onClick(View view) {
            int postNumber = 0;

            try {
                postNumber = Integer.parseInt(mEditText.getText().toString());
            } catch (NumberFormatException e) {
                mTextView.setText(e.getLocalizedMessage());
            }

            if ((postNumber >= 1) && (postNumber <= 500)) {
                JsonPlaceHolderCallback<CommentsModel> commentCallback = new JsonPlaceHolderCallback<>(mTextView);
                mJsonPlaceHolderApi.getComment(postNumber).enqueue(commentCallback);
            }
        }
    }
}
