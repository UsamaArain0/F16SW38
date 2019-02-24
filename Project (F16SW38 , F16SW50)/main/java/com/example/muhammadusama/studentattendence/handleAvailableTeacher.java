package com.example.muhammadusama.studentattendence;

import android.app.Activity;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class handleAvailableTeacher extends Fragment {
    TextView teacher_NAME,teacher_ID;
    ListView list_of_teachers;
    ArrayList<TeacherAttribute>arrayList= new ArrayList<>();
    String mLoL;
    FirebaseDatabase firebaseDB;
  //  ArrayAdapter <TeacherAttribute> arrayAdapter;
    DatabaseReference databaseReference;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.teacher_detail_layout,container,false);
          //  teacher_NAME= view.findViewById(R.id.t_name);
            firebaseDB= FirebaseDatabase.getInstance();
            databaseReference= FirebaseDatabase.getInstance().getReference("Teachers");
          //  teacher_ID= view.findViewById(R.id.t_id);
            list_of_teachers= view.findViewById(R.id.teacher_list);

//            FirebaseListAdapter<String> firebaseListAdapter= new FirebaseListAdapter<String>(getActivity(),String.class,android.R.layout.simple_list_item_1,databaseReference) {
//                @Override
//                protected void populateView(View v, String model, int position) {
//                    TextView textView= v.findViewById(android.R.id.text1);
//                    textView.setText(model);
//                }

//                @Override
//                protected void populateView(View v, String model, int position) {
//                   TextView tv= v.findViewById(android.R.id.text1);
//                   tv.setText(model);
//                }
       //     };
           //  list_of_teachers.setAdapter(firebaseListAdapter);
//        databaseReference.addListenerForSingleValueEvent(
//
//                new ValueEventListener() {
//
//                  @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//
//                        //Get map of users in datasnapshot
//                        for(DataSnapshot childData: dataSnapshot.getChildren()) {
//                           // Toast.makeText(getContext(), childData.getKey(), Toast.LENGTH_SHORT).show();
//
//                           // myStaticVariable=childData.getKey();
//                            arrayList.add(childData.getKey());
//                           // emailOfSingleUser(childData);
//
//
//
//                            Toast.makeText(getContext(), (Integer) childData.getValue(),Toast.LENGTH_SHORT).show();
//                     //  collectPhoneNumbers((Map<String,Object>) childData.getValue());
//
//                          }
////
////
////                        Toast.makeText(getContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();
////                     DataSnapshot ii= (DataSnapshot) dataSnapshot.getChildren();
////                      Toast.makeText(getContext(), ii.getValue().toString(), Toast.LENGTH_SHORT).show();
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });
//        final TeacherListAdapter t_adapter = new TeacherListAdapter(getContext(),R.layout.list_adapter_for_teacher,arrayList);
//        arrayList.add(new TeacherAttribute("usama","usamaarain056@gmial.com","f16sw38"));
//        list_of_teachers.setAdapter(t_adapter);

        FirebaseListAdapter<TeacherAttribute> firebaseListAdapter = new FirebaseListAdapter<TeacherAttribute>( getActivity()
                ,TeacherAttribute.class,R.layout.list_adapter_for_teacher,databaseReference) {
            @Override
            protected void populateView(View v, TeacherAttribute model, int position) {
                TextView tt= v.findViewById(R.id.teacher_name_lable);
                TextView ttt= v.findViewById(R.id.teacher_id_lable);
                tt.setText(model.getName());
                ttt.setText(model.getEmail());
            }
        };
        list_of_teachers.setAdapter(firebaseListAdapter);

        return  view;
    }
    private void collectPhoneNumbers(Map<String,Object> users) {

        ArrayList<String> teacher_name = new ArrayList<>();
        ArrayList<String> teacher_email = new ArrayList<>();


        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get phone field and append to list
//            phoneNumbers.add((String) singleUser.get("Teacher Email"));
            teacher_name.add((String) singleUser.get("Teacher Name"));
            teacher_email.add((String)singleUser.get("Teacher Email"));
            // Toast.makeText(getContext(), singleUser.get("Teacher Name").toString(), Toast.LENGTH_SHORT).show();

        }

       Toast.makeText(getContext(), "this teacher name "+teacher_name.toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(), "teacher email "+teacher_email.toString(), Toast.LENGTH_SHORT).show();

    }

    public void emailOfSingleUser(DataSnapshot snapshot){
        DatabaseReference myQWDB = FirebaseDatabase.getInstance().getReferenceFromUrl("https://attendence-ce242.firebaseio.com/Teachers/").child(snapshot.getKey());
        myQWDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Map<String,String> myDAta= dataSnapshot.getValue(Map.class);
               String aaa= myDAta.get("Teacher Email");
             //  String bbb= myDAta.get("Teacher Name");
                Toast.makeText(getContext(), aaa, Toast.LENGTH_SHORT).show();
               // Toast.makeText(getContext(), bbb, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}
