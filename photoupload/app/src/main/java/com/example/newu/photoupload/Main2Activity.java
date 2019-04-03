package com.example.newu.photoupload;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class Main2Activity extends AppCompatActivity implements demoInterface {
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    //private CollectionReference photoref=db.collection("Uploads");
    private List<Upload> mUploads;
    private StorageReference mStorageRef1;
    //TextView txtvw;
    String gString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mUploads = new ArrayList<>();
       // txtvw=(TextView)findViewById(R.id.txt);
        //mStorageRef1=FirebaseStorage.getInstance().getReference("uploads/1553715288216.jpg");
        final String base="gs://photoupload-aebca.appspot.com/";



        /*photoref.addSnapshotListener(this, new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e!=null)
                {
                    return;
                }
                String data="";
                for (QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                {
                    Upload newupload=documentSnapshot.toObject(Upload.class);
                    mUploads.add(newupload);



                }
                mAdapter = new ImageAdapter(Main2Activity.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);
    }
});*/
        db.collection("Uploads")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Map<String,Object> note=new HashMap<String,String>()
                                //note=document.getData();
                                String url=document.getString("Image Url");
                                String name=document.getString("Name");
                                //txtvw.setText(url);
                                onSuccessfetch(url,name);


                            }
                            //mAdapter = new ImageAdapter(Main3Activity.this, mUploads);

                            //mRecyclerView.setAdapter(mAdapter);

                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



    }
    @Override
    public void onSuccessfetch(final String Url, final String name)

    {
        mStorageRef1=FirebaseStorage.getInstance().getReference(Url);
        mStorageRef1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
        {
            @Override
            public void onSuccess(Uri uri) {
                Log.v("TAG",uri.toString());
                //txtvw.setText(uri.toString());
                createList(name,uri.toString());

                // Got the download URL for 'users/me/profile.png'
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.v("TAG2",exception.getMessage());
            }
        });
    }
    void createList(String name,String uri)
    {
        Upload upload=new Upload(name,uri);
        mUploads.add(upload);
        mAdapter = new ImageAdapter(Main2Activity.this, mUploads);

        mRecyclerView.setAdapter(mAdapter);

    }
}
