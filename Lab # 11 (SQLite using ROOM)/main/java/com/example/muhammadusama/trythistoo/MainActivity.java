package com.example.muhammadusama.trythistoo;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    FloatingActionButton floatingActionButton;
    ListView listView;
    List<StudentInfo> taskList;
    ProgressDialog progressDialog;
    ArrayList<String>Arrayid= new ArrayList<>();
    ArrayAdapter<String> adapter;

    ArrayList<String> names;
    String i, n, d;
    //Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // TextView textView= findViewById(R.id.checkifwork);

        super.onCreate(savedInstanceState);
        getTasks();
        setContentView(R.layout.activity_main);
        progressDialog= new ProgressDialog(this);
        floatingActionButton= findViewById(R.id.floatingActionButton3);
        listView =findViewById(R.id.list);

//        AsyncTaskExample asyncTaskExample= new AsyncTaskExample(textView,progressDialog);
//        asyncTaskExample.execute();
        registerForContextMenu(listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                n= taskList.get(position).getS_Name();
                i= taskList.get(position).getS_ID();
                d= taskList.get(position).getS_Department();

                Toast.makeText(MainActivity.this," postion "+position,Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this,"Student id is "+Arrayid.get(position)+" name "+n+"dep "+d+"id "+i,Toast.LENGTH_SHORT).show();
              //  Toast.makeText(MainActivity.this,"Student name is "+name.get(position),Toast.LENGTH_SHORT).show();
              //  Toast.makeText(MainActivity.this, , Toast.LENGTH_SHORT).show();





            }
        });



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
     //   Toast.makeText(this,""+v.getId(),Toast.LENGTH_SHORT).show();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item){
        final Dialog dialog= new Dialog(this);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = info.position;

        Toast.makeText(MainActivity.this,"index is "+index,Toast.LENGTH_SHORT).show();
        View view = info.targetView;
        if(item.getItemId()==R.id.delete_student){
          //  Toast.makeText(getApplicationContext(),"Student will delete",Toast.LENGTH_LONG).show();
            deleteTasks(index);

        }
        else if(item.getItemId()==R.id.update_student){
            Toast.makeText(getApplicationContext(),"Student will update",Toast.LENGTH_LONG).show();

            dialog.setContentView(R.layout.activity_update_student);
            dialog.setTitle("Update Name");
            final EditText editText= dialog.findViewById(R.id.student_name);


          Button  updateButton= dialog.findViewById(R.id.update_button);
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateTasks(editText.getText().toString(),Arrayid.get(index),index);
                    dialog.dismiss();

                }
            });
            dialog.show();

        }else{
            return false;
        }
        return true;
    }


    public void onClickFloatingBtn(View view){

       Intent intent = new Intent(MainActivity.this,StudentEntry.class);
    //   StudentEntry studentEntry = new StudentEntry(context);
       startActivity(intent);
       finish();
     //  startActivity(intent);
    }
    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<StudentInfo>> {
            @Override
            protected List<StudentInfo> doInBackground(Void... voids) {
                taskList= AppDatabase.database(getApplicationContext()).getQuery_Listener().getAllStudent();
                return taskList;
            }
            @Override
            protected void onPostExecute(List<StudentInfo> tasks) {
                super.onPostExecute(tasks);
                names = new ArrayList();
                for(StudentInfo t:tasks){
                    names.add(t.getS_Name());
                    Arrayid.add(t.getS_ID());
                }
                adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, names);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }

    private void deleteTasks(final int argu) {
        class GetTasks extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                AppDatabase.database(getApplicationContext()).getQuery_Listener().removeStudentWithId(Arrayid.get(argu));
//                taskList= AppDatabase.database(getApplicationContext()).getQuery_Listener().getAllStudent();
                names.remove(argu);

                return "Removed Successfully";
            }
            @Override
            protected void onPostExecute(String tasks) {
                super.onPostExecute(tasks);

//                ArrayList<String> names = new ArrayList();
//                for(StudentInfo t:tasks){
//                    names.add(t.getS_Name());
//                    Arrayid.add(t.getS_ID());
//                }
//                adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, names);
//                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,tasks,Toast.LENGTH_SHORT).show();
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }

    private void updateTasks(final String argu,final String id,final int index) {
        class GetTasks extends AsyncTask<Void, Void, String> {
            @Override
            protected String doInBackground(Void... voids) {
                AppDatabase.database(getApplicationContext()).getQuery_Listener().updateStudentWithId(argu,id);
//                taskList= AppDatabase.database(getApplicationContext()).getQuery_Listener().getAllStudent();
                names.set(index,argu);

                return "Updated Successfully";
            }
            @Override
            protected void onPostExecute(String tasks) {
                super.onPostExecute(tasks);

//                ArrayList<String> names = new ArrayList();
//                for(StudentInfo t:tasks){
//                    names.add(t.getS_Name());
//                    Arrayid.add(t.getS_ID());
//                }
//                adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1, android.R.id.text1, names);
//                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,tasks,Toast.LENGTH_SHORT).show();
            }
        }
        GetTasks gt = new GetTasks();
        gt.execute();
    }

}