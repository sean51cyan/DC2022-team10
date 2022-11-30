package com.example.myschedule.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private final ArrayList<MutableLiveData<String>> class_names;

    public HomeViewModel() {

        class_names = new ArrayList<>();
        for(int i = 0 ; i < 7 ; i ++){
            for(int j = 0 ; j < 6; j++){
                MutableLiveData<String> inner = new MutableLiveData<>();
                inner.setValue("未登録");
                class_names.add(inner);
            }
        }
    }

    public LiveData<String> getClassName(int i, int j){return class_names.get(i*6+j);}
    public void setClassName(int i, int j, String new_name){
        class_names.get(i*6+j).setValue(new_name);
    }
}