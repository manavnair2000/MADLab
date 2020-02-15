package com.example.madexperiments.ui.experiment_4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExperimentFourViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExperimentFourViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Database Experiment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}