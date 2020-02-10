package com.example.madexperiments.ui.experiment_1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExperimentOneViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExperimentOneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Hello World! This is MAD Lab");

    }

    public LiveData<String> getText() {
        return mText;
    }
}