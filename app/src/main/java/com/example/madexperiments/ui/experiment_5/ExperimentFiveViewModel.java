package com.example.madexperiments.ui.experiment_5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExperimentFiveViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExperimentFiveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is experiment_5 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}