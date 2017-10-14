package pvtitov.jsonplaceholderapplication;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Павел on 07.10.2017.
 */

public class ContactsActivity extends AppCompatActivity {

    TextView mName;
    TextView mEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        mName = (TextView) findViewById(R.id.my_name);
        mEmail = (TextView) findViewById(R.id.my_email);
    }
}
