package com.example.myschedule.ui.ranking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RankingViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public RankingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ranking fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

