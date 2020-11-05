package com.example.toytrader;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executor;

public class FirebaseHelper {

    private final static String TAG = "FIREBASE_HELPER";
    private static FirebaseHelper fireStoreHelper = null;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user;
    private FirebaseListener fbl;

    private FirebaseHelper() {
    }

    public static FirebaseHelper getInstance() {
        if (fireStoreHelper == null) {
            fireStoreHelper = new FirebaseHelper();
        }
        return fireStoreHelper;
    }

    public FirebaseAuth getFirebaseAuth() {
        return mAuth;
    }

    public void signupWith(String email, String password, final Map data, final FirebaseListener fbl) {
        this.fbl = fbl;
        final Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            user = mAuth.getCurrentUser();
                            setupUserDataAfterSignup(user.getUid(), data, fbl);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            user = null;
                            fbl.getFBData(null);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void cleanUpForLogout() {
        mAuth.signOut();
    }

    private void setupUserDataAfterSignup(String id, Map userData, final FirebaseListener fbl) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userRef = db.collection("users");
        userRef.document(id).set(userData).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                fbl.getFBData(user);
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void signinWith(String email, String password, final FirebaseListener fbl) {
        this.fbl = fbl;
        final Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signin:success");
                            user = mAuth.getCurrentUser();
                            fbl.getFBData(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signin:failure", task.getException());
                            user = null;
                            fbl.getFBData(null);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void uploadToyWithData(Map toyDetails, @Nullable final FirebaseListener fbl) {
        this.fbl = fbl;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference toysRef = db.collection("toys");
        toysRef.add(toyDetails).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void getAllToys() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference toysRef = db.collection("toys");
        toysRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
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

    public void getDetailsForCurrentUser(final FirebaseListener fbl) {
        this.fbl = fbl;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userRef = db.collection("users");
        userRef.document(mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot d = (DocumentSnapshot)task.getResult();
                    Map m = d.getData();
                    fbl.getFBData(m);
                } else {
                    fbl.getFBData(null);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                fbl.getFBData(null);
            }
        });
    }

    public void updateDetailsForCurrentUser(Map userData, final FirebaseListener fbl) {
        this.fbl = fbl;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userRef = db.collection("users");
        userRef.document(mAuth.getCurrentUser().getUid()).update(userData).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    fbl.updateFBResult(true);
                } else {
                    fbl.updateFBResult(false);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                fbl.updateFBResult(false);
            }
        });
    }
}


