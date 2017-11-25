package kivaaz.com.userapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestsActivity extends AppCompatActivity {

    private static final String TAG = ".RequestsActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    List<RequestList> reqList;
    RequestAdapter adapter;

    RecyclerView req_recycle;

    Button addBtn, clearBtn;
    EditText nameEt, descEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        mAuth = FirebaseAuth.getInstance();
        AuthChecker();

        final DatabaseReference myRef = database.getReference("Requests");

        req_recycle = (RecyclerView) findViewById(R.id.request_recycle);
        addBtn = (Button) findViewById(R.id.add_btn);
        clearBtn = (Button) findViewById(R.id.clear_btn);
        nameEt = (EditText) findViewById(R.id.req_nameET);
        descEt = (EditText) findViewById(R.id.req_descET);

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearFields(nameEt,descEt);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestList request = new RequestList(nameEt.getText().toString(), descEt.getText().toString(), mAuth.getCurrentUser().getEmail(),false,"None");

                String uploadID = request.getReqName().replace(" ","")+ "_" + request.getReqEmail().replace(".","");
                myRef.child(uploadID).setValue(request).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        ClearFields(nameEt,descEt);
                        Toast.makeText(getBaseContext(),"Request Added",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getBaseContext(),"Request Failed to  Add: " + e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                reqList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    RequestList req = snapshot.getValue(RequestList.class);
                    if(req.getReqEmail().equals(mAuth.getCurrentUser().getEmail())){
                        reqList.add(req);
                    }
                }
                SetContents(reqList,req_recycle);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }
    private void AuthChecker() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    startActivity(new Intent(getBaseContext(),MainActivity.class));
                }
            }
        };
    }

    private void ClearFields(EditText nameEt, EditText descEt) {
        nameEt.setText("");
        descEt.setText("");
    }

    private void SetContents(List<RequestList> reqList, RecyclerView req_recycle) {
        if(!reqList.isEmpty()){
            adapter = new RequestAdapter(reqList, getBaseContext(), new OnItemClick() {
                @Override
                public void OnClick(String Data) {

                }
            });
            req_recycle.setAdapter(adapter);
            req_recycle.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        }
    }
}
