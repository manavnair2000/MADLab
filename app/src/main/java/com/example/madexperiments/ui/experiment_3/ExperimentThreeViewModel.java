package com.example.madexperiments.ui.experiment_3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExperimentThreeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExperimentThreeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Basic Graphical Primitives");
    }

    public LiveData<String> getText() {
        return mText;
    }
}