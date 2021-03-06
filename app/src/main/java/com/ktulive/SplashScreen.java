package com.ktulive;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ktulive.actvities.MainActivity;
import com.ktulive.db.SubjectDetails;
import com.ktulive.extra.CusUtils;
import com.ktulive.models.SubForSaving;
import com.ktulive.models.Subjects;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.ktulive.db.SubjectDetails.MODULE_1;
import static com.ktulive.db.SubjectDetails.MODULE_2;
import static com.ktulive.db.SubjectDetails.MODULE_3;
import static com.ktulive.db.SubjectDetails.MODULE_4;
import static com.ktulive.db.SubjectDetails.MODULE_5;
import static com.ktulive.db.SubjectDetails.MODULE_6;
import static com.ktulive.db.SubjectDetails.REFERENCE;
import static com.ktulive.db.SubjectDetails.SUBJECT_CODE;
import static com.ktulive.db.SubjectDetails.TEXT_BOOK;


public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;
    private SignInButton googleButton;
    private static final int RC_SIGN_IN = 1;
    private GoogleApiClient mGoogleApiClient;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mauthListener;

    ImageView vtlo;

    boolean isDBUptoDate ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharedPreferences =  getSharedPreferences("ktulive",Context.MODE_PRIVATE);
       final SharedPreferences.Editor  e = sharedPreferences.edit();
        isDBUptoDate = sharedPreferences.getBoolean("isDBUptoDate",false);



//        LOGIN SECTION
        mAuth = FirebaseAuth.getInstance();
        mauthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() !=null){
                    googleButton.setVisibility(View.GONE);
                    Toast.makeText(SplashScreen.this, "Welcome " + firebaseAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(SplashScreen.this, "Welcome " + firebaseAuth.getCurrentUser().getPhotoUrl(), Toast.LENGTH_SHORT).show();

                    movetoMainScreen();
                }
            }
        };
        googleButton = (SignInButton)findViewById(R.id.googleButton);
        TextView textView = (TextView) googleButton.getChildAt(0);
        textView.setText("Sign In To Continue");



        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(SplashScreen.this, "ERROR", Toast.LENGTH_SHORT).show();

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });


        final SubjectDetails subjectDetails = new SubjectDetails(this);

        final String modules[] = {MODULE_1,MODULE_2,MODULE_3,MODULE_4,MODULE_5,MODULE_6};
                DatabaseReference databaseReference = CusUtils.getDatabase().getReference().child("btech").child("syllbus");
                if(!isDBUptoDate) {
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {


                            final ProgressDialog pd = new ProgressDialog(SplashScreen.this);
                            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            pd.setMessage("Please wait while  we download Details");
                            pd.setCancelable(true);
                            pd.setIndeterminate(true);
                            pd.show();

                            Iterable<DataSnapshot> dataSnapshotIterator = dataSnapshot.getChildren();
                            for (DataSnapshot dataSnapshot1 : dataSnapshotIterator) {
//                            Log.e("KEY",dataSnapshot1.getKey());
//                            Log.e("INNERSNAP",dataSnapshot1.toString());
                                SubForSaving subForSaving = dataSnapshot1.getValue(SubForSaving.class);
//                            Log.e("SAVED",subForSaving.reference);

                                ContentValues contentValues = new ContentValues();

                                try {
                                    contentValues.put(SUBJECT_CODE, dataSnapshot1.getKey());
                                    contentValues.put(TEXT_BOOK, subForSaving.text_book);
                                    contentValues.put(REFERENCE, subForSaving.reference);

                                    contentValues.put(MODULE_1,subForSaving.modules.get(0).description);
                                    contentValues.put(MODULE_2,subForSaving.modules.get(0).description);
                                    contentValues.put(MODULE_3,subForSaving.modules.get(0).description);
                                    contentValues.put(MODULE_4,subForSaving.modules.get(0).description);
                                    contentValues.put(MODULE_5,subForSaving.modules.get(0).description);
                                    contentValues.put(MODULE_6,subForSaving.modules.get(0).description);
                                    subjectDetails.insertSubjects(contentValues);
                                    Log.e("CONT",contentValues.toString());


                                }
                                catch (Exception e1){
                                    subjectDetails.insertSubjects(contentValues);
                                    Log.e("CONT",contentValues.toString());

                                }


                            }
                            pd.dismiss();


                            e.putBoolean("isDBUptoDate", true);
                            e.apply();


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
//



    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            Log.e("RES",result.getStatus().toString());
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {

                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.e("GOOGLE_LOGIN", "firebaseAuthWithGoogle:" + acct.getId());

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e("GOOGLE_LOGIN", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            googleButton.setVisibility(View.INVISIBLE);
                            Toast.makeText(SplashScreen.this, "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
                            movetoMainScreen();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("GOOGLE_LOGIN", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SplashScreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mauthListener);
    }


    private void movetoMainScreen(){
        if(isNetworkAvailable()){
            new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);

                    // close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);

        }
        else {
            Toast.makeText(this, "Please Connect To Internet", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)  getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
