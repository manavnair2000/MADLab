package com.example.madexperiments.ui.experiment_6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExperimentSixViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExperimentSixViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Experiment Six Multithreading.....");
    }

    public LiveData<String> getText() {
        return mText;
    }
}