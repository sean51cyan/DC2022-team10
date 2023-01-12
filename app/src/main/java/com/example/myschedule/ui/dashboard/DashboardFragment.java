package com.example.myschedule.ui.dashboard;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myschedule.R;
import com.example.myschedule.TestOpenHelper;
import com.example.myschedule.databinding.FragmentDashboardBinding;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;


public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private TestOpenHelper helper;
    private Spinner spinner;
    private RatingBar ratingBarDiff;
    private RatingBar ratingBarEasy;
    private RatingBar ratingBarGet;
    private RatingBar ratingBarPro;
    private RatingBar ratingBarMood;
    private SQLiteDatabase db;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        spinner = root.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (root.getContext(),
                        R.array.title_array,
                        android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            //　アイテムが選択された時
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
            }

            //　アイテムが選択されなかった
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        ratingBarDiff = root.findViewById(R.id.ratingbarDiff);
        ratingBarEasy = root.findViewById(R.id.ratingbarEasy);
        ratingBarGet = root.findViewById(R.id.ratingbarGet);
        ratingBarPro = root.findViewById(R.id.ratingbarPro);
        ratingBarMood = root.findViewById(R.id.ratingbarMood);

        //View textView = root.findViewById(R.id.text_view);

        Button insertButton = root.findViewById(R.id.button_insert);
        insertButton.setOnClickListener(v -> {

            if (helper == null) {
                helper = new TestOpenHelper(root.getContext());
            }

            if (db == null) {
                db = helper.getWritableDatabase();
            }

            String title = (String) spinner.getSelectedItem();

            int diff = (int) ratingBarDiff.getRating();
            int easy = (int) ratingBarEasy.getRating();
            int get = (int) ratingBarGet.getRating();
            int pro = (int) ratingBarPro.getRating();
            int mood = (int) ratingBarMood.getRating();

            // 価格は整数を想定
            insertData(db, title, diff, easy, get, pro, mood);
        });

        return root;
    }

    private void insertData(SQLiteDatabase db, String title, int diff, int easy, int get, int pro, int mood) {

        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("diff", diff);
        values.put("easy", easy);
        values.put("get", get);
        values.put("pro", pro);
        values.put("mood", mood);

        db.insert("comment", null, values);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}