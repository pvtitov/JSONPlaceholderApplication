package pvtitov.jsonplaceholderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

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

        TextView postTextView = (TextView) findViewById(R.id.post);
        TextView commentTextView = (TextView) findViewById(R.id.comment);
        TextView userTextView = (TextView) findViewById(R.id.user);
        TextView todoTextView = (TextView) findViewById(R.id.todo);
        ImageView photoImageView = (ImageView) findViewById(R.id.photo);


        JsonPlaceHolderCallback<PostsModel> postCallback = new JsonPlaceHolderCallback<>(postTextView);
        mJsonPlaceHolderApi.getPost(randomIntFromOneTo(100)).enqueue(postCallback);

        JsonPlaceHolderCallback<CommentsModel> commentCallback = new JsonPlaceHolderCallback<>(commentTextView);
        mJsonPlaceHolderApi.getComment(randomIntFromOneTo(500)).enqueue(commentCallback);

        JsonPlaceHolderCallback<UsersModel> userCallback = new JsonPlaceHolderCallback<>(userTextView);
        mJsonPlaceHolderApi.getUser(randomIntFromOneTo(5)).enqueue(userCallback);

        JsonPlaceHolderCallback<PhotosModel> photoCallback = new JsonPlaceHolderCallback<>(this, photoImageView);
        mJsonPlaceHolderApi.getPhoto(randomIntFromOneTo(5000)).enqueue(photoCallback);

        JsonPlaceHolderCallback<TodosModel> todoCallback = new JsonPlaceHolderCallback<>(todoTextView);
        mJsonPlaceHolderApi.getTodo(randomIntFromOneTo(200)).enqueue(todoCallback);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private int randomIntFromOneTo(int max) {
        double randomNumber = Math.random()*(max-1)+1;
        return (int)randomNumber;
    }
}
