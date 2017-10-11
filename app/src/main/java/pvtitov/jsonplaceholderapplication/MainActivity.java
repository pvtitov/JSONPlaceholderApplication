package pvtitov.jsonplaceholderapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        TextView postTextView = (TextView) findViewById(R.id.post);
            final EditText postRequest = (EditText) findViewById(R.id.post_request);
            ImageButton postRequestButton = (ImageButton) findViewById(R.id.post_request_button);
        TextView commentTextView = (TextView) findViewById(R.id.comment);
        ListView usersListView = (ListView) findViewById(R.id.users);
            List<String> usersList = new ArrayList<>();
        TextView todoTextView = (TextView) findViewById(R.id.todo);
        ImageView photoImageView = (ImageView) findViewById(R.id.photo);


        postRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, postRequest.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        JsonPlaceHolderCallback<PostsModel> postCallback = new JsonPlaceHolderCallback<>(postTextView);
        mJsonPlaceHolderApi.getPost(randomIntFromOneTo(100)).enqueue(postCallback);

        JsonPlaceHolderCallback<CommentsModel> commentCallback = new JsonPlaceHolderCallback<>(commentTextView);
        mJsonPlaceHolderApi.getComment(randomIntFromOneTo(500)).enqueue(commentCallback);

        /*for (int i = 0; i < 5; i++){
            usersList.add(i,"");
            JsonPlaceHolderCallback<UsersModel> userCallback = new JsonPlaceHolderCallback<>(usersList, i);
            mJsonPlaceHolderApi.getUser(i+1).enqueue(userCallback);
        }


        Поместить в Callback.onResponse

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, usersList);
        usersListView.setAdapter(adapter);*/

        JsonPlaceHolderCallback<PhotosModel> photoCallback = new JsonPlaceHolderCallback<>(this, photoImageView);
        mJsonPlaceHolderApi.getPhoto(3).enqueue(photoCallback);

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
