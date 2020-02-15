package com.example.madexperiments.ui.experiment_4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.madexperiments.R;

public class ExperimentFour extends Fragment {

    private ExperimentFourViewModel experimentFourViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        experimentFourViewModel =
                ViewModelProviders.of(this).get(ExperimentFourViewModel.class);
        View root = inflater.inflate(R.layout.experiment_4, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        experimentFourViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button addme = root.findViewById(R.id.addMe);
        Button deleteme = root.findViewById(R.id.deleteMe);
        Button showme = root.findViewById(R.id.showMe);
        Button modifyme = root.findViewById(R.id.modifyMe);
        Button showall = root.findViewById(R.id.showMeall);
        final EditText rollno = root.findViewById(R.id.editRollno);
        final EditText name = root.findViewById(R.id.editName);
        final EditText marks = root.findViewById(R.id.editMarks);
        addme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"record inserted",Toast.LENGTH_LONG).show();
                DatabaseHelper db = new DatabaseHelper(getActivity());
                db.addStudent(rollno.getText().toString(),name.getText().toString(),marks.getText().toString());
            }
        });
        deleteme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatabaseHelper db = new DatabaseHelper(getActivity());
                db.deleteStudent(rollno.getText().toString());
            }
        });
        modifyme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatabaseHelper db = new DatabaseHelper(getActivity());
                db.modifyStudent(rollno.getText().toString(),name.getText().toString(),marks.getText().toString());
            }
        });
        showme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatabaseHelper db = new DatabaseHelper(getActivity());
                db.viewStudent(rollno.getText().toString());
            }
        });
        showall.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatabaseHelper db = new DatabaseHelper(getActivity());
                db.viewAll();
            }
        });
        return root;
    }
}