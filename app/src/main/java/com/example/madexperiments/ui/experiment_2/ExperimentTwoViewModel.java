package com.example.madexperiments.ui.experiment_2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExperimentTwoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ExperimentTwoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(" Enter Student Data ");
    }

    public LiveData<String> getText() {
        return mText;
    }
}