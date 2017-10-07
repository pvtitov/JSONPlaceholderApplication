package pvtitov.jsonplaceholderapplication.api_service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Павел on 07.10.2017.
 */

public interface JSONPlaceHolderApi {
    @GET("/posts/{number}")
    Call<PostsModel> getPost(@Path("number") int number);

    @GET("/comments/{number}")
    Call<CommentsModel> getComment(@Path("number") int number);

    @GET("/users/{number}")
    Call<UsersModel> getUser(@Path("number") int number);

    @GET("/photos/{number}")
    Call<PhotosModel> getPhoto(@Path("number") int number);

    @GET("/todos/{number}")
    Call<TodosModel> getTodo(@Path("number") int number);
}
