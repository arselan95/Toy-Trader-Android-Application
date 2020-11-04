package com.example.toytrader;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.Map;


public class FirebaseHelper {
    private final static String TAG = "FIREBASE_HELPER";
    private static FirebaseHelper fireStoreHelper = null;

    private FirebaseHelper() {
    }

    public static FirebaseHelper getInstance() {
        if (fireStoreHelper == null) {
            fireStoreHelper = new FirebaseHelper();
        }
        return fireStoreHelper;
    }


    public void getAllToys() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference citiesRef = db.collection("toys");
        citiesRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> json = document.getData();
                        for (Map.Entry<String, Object> entry : json.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            Log.d(TAG, key + " => " + value);
                        }
                        Log.d(TAG, document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
