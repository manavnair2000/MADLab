package com.example.madexperiments.ui.experiment_6;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.madexperiments.LoadImageTask;
import com.example.madexperiments.R;

public class ExperimentSix extends Fragment {

    private ExperimentSixViewModel experimentSixViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        experimentSixViewModel =
                ViewModelProviders.of(this).get(ExperimentSixViewModel.class);
        View root = inflater.inflate(R.layout.experiment_6, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        experimentSixViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
