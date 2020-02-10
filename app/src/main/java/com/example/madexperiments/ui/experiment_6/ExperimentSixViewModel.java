package com.example.madexperiments.ui.experiment_6;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExperimentSixViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExperimentSixViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is experiment_6 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}