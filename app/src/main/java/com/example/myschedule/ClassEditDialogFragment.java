package com.example.myschedule;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myschedule.ui.home.HomeViewModel;

import java.util.Locale;

public class ClassEditDialogFragment extends DialogFragment {

    int i,j;
    HomeViewModel homeViewModel;
    public ClassEditDialogFragment(int i, int j, HomeViewModel homeViewModel) {
        this.i = i;
        this.j = j;
        this.homeViewModel = homeViewModel;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] day_name = {"月","火","水","木","金","土"};
        String[] choices = {"未選択","数学","理科","英語","国語","社会"};
        int initial_select = 0;
        for(int i = 0; i < 6; i++){
            if(choices[i].equals(homeViewModel.getClassName(this.i,this.j).getValue())){
                initial_select = i;
            }
        }
        final String time_name = String.format(Locale.JAPAN,"%s%d",day_name[j],i+1);

        builder.setTitle(time_name)
                .setPositiveButton("登録", (dialogInterface, id) -> {
                })
                .setSingleChoiceItems(choices,initial_select, (dialogInterface, which) -> {
                    homeViewModel.setClassName(i,j,choices[which]);
                });
        return builder.create();
    }
}
