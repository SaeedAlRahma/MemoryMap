package compsci290.duke.edu.memorymap.startup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import compsci290.duke.edu.memorymap.map.MapsActivity;
import compsci290.duke.edu.memorymap.memory.MemoryListActivity;
import compsci290.duke.edu.memorymap.R;
import compsci290.duke.edu.memorymap.map.EditableMapsActivity;
import compsci290.duke.edu.memorymap.memory.PublicMemoryListActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mStatusTextView;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        mStatusTextView = (TextView) findViewById(R.id.status);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }


    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }


    private void updateUI(FirebaseUser user) {
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt, user.getEmail()/*, user.isEmailVerified()*/));


            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);

        } else {
            Intent intent = new Intent(this, SignInActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.sign_out_button) {
            signOut();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public void onClickPersonalMapBtn(View v)
    {
        Intent intent = new Intent(this, EditableMapsActivity.class);
        startActivity(intent);
    }

    public void onClickPersonalListBtn(View v) {
        Intent intent = new Intent(this, MemoryListActivity.class);
        startActivity(intent);
    }

    public void onClickPublicMapBtn(View v)
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onClickPublicListBtn(View v) {
        Intent intent = new Intent(this, PublicMemoryListActivity.class);
        startActivity(intent);
    }

}