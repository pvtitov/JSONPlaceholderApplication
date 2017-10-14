package pvtitov.jsonplaceholderapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Павел on 15.10.2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<String> mListOfItems;

    public UserAdapter(List<String> listOfItems) {
        mListOfItems = listOfItems;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(textView);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mListOfItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mListOfItems.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;

        public ViewHolder(TextView textView) {
            super(textView);
            mTextView = textView;
        }
    }
}
