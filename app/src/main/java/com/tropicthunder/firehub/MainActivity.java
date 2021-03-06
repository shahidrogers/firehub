package com.tropicthunder.firehub;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static List<PostDetails> postsList;
    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#81c784"));
        pDialog.setTitleText("Looking for classes near you..");
        pDialog.setCancelable(false);
        pDialog.show();

        SessionManager sessionManager = new SessionManager(this);

        Button btnTeach = (Button)findViewById(R.id.btn_Teach);
        Button btnMyClasses = (Button) findViewById(R.id.btn_myClasses);
        final TextView postCount = (TextView)findViewById(R.id.txt_postCount);
        final TextView txtLocation = (TextView)findViewById(R.id.txt_Location);

        txtLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] items = {"Bukit Bintang", "Ampang", "Cheras"};

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Select location");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        // Do something with the selection
                        txtLocation.setText(items[item]);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        btnTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TeachActivity.class));

            }
        });

        btnMyClasses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MyClassesActivity.class));
            }
        });

        postsList = new ArrayList<PostDetails>();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_classesList);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (and pass questions list data to adapter)
        mAdapter = new PostListAdapter(this.postsList, getApplicationContext());
        adapter  = mAdapter;
        mRecyclerView.setAdapter(mAdapter);

        final Firebase ref = new Firebase("https://firehub-ahkl.firebaseio.com/data/posts");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals("count")){
                    Count count = dataSnapshot.getValue(Count.class);
                    postCount.setText(String.valueOf(count.getNumber()));
                    pDialog.hide();
                }
                else{
                    PostDetails post = dataSnapshot.getValue(PostDetails.class);
                    post.setKey(dataSnapshot.getKey());
                    postsList.add(post);
                    mAdapter.notifyDataSetChanged();
                    pDialog.hide();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot.getKey().equals("count")){
                    Count count = dataSnapshot.getValue(Count.class);
                    postCount.setText(String.valueOf(count.getNumber()));
                }
                else{
                    PostDetails post = dataSnapshot.getValue(PostDetails.class);
                    post.setKey(dataSnapshot.getKey());

                    for (int i=0; i<postsList.size(); i++){
                        if (postsList.get(i).getKey().equals(post.getKey())){
                            postsList.set(i, post);
                        }
                    }

                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        Button btnProfile = (Button) findViewById(R.id.btn_MyProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
            }
        });

    }




}
