package com.example.myschedule.ui.ranking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.myschedule.R;
import com.example.myschedule.TestOpenHelper;
import com.example.myschedule.databinding.FragmentRankingBinding;

public class RankingFragment extends Fragment {

    private FragmentRankingBinding binding;
    private TestOpenHelper helper;
    private TextView textView;
    private SQLiteDatabase db;
    private View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRankingBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        textView = binding.textRanking;

        Button readButton = root.findViewById(R.id.button_read);
        readButton.setOnClickListener(v -> readData());

        return root;
    }

    private void readData() {
        if (helper == null) {
            helper = new TestOpenHelper(root.getContext());
        }

        if (db == null) {
            db = helper.getReadableDatabase();
        }
        Log.d("debug", "**********Cursor");

        String[] col = new String[]{
                "title",
                "(diff + easy + get + pro + mood)/5 AS avg"
        };

        Cursor cursor = db.query(
                "comment",
                col,
                null,
                null,
                "title",
                null,
                "avg desc"
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(i+1);
            sbuilder.append("位 ");
            sbuilder.append(cursor.getString(0));
            sbuilder.append("\t: ");
            sbuilder.append(cursor.getString(1));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        Log.d("debug", "**********" + sbuilder);
        textView.setText(sbuilder.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
