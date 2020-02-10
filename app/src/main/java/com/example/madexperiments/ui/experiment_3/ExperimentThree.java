package com.example.madexperiments.ui.experiment_3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.madexperiments.R;

public class ExperimentThree extends Fragment {

    private ExperimentThreeViewModel experimentThreeViewModel;

    public class SampleCanvas extends View{

        Paint paint = new Paint();

        public SampleCanvas(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);
            canvas.drawRect(200,250,400,350,paint);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(300,525,100,paint);
            paint.setColor(Color.RED);
            canvas.drawLine(200,200,400,200,paint);
        }


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        experimentThreeViewModel =
                ViewModelProviders.of(this).get(ExperimentThreeViewModel.class);


        View root = inflater.inflate(R.layout.experiment_3, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        ConstraintLayout constraintLayout = root.findViewById(R.id.exp3_layout);
        constraintLayout.addView(new SampleCanvas(getActivity()));
        experimentThreeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }



}