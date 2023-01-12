package com.example.myschedule.ui.notifications;

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
import androidx.lifecycle.ViewModelProvider;

import com.example.myschedule.R;
import com.example.myschedule.TestOpenHelper;
import com.example.myschedule.databinding.FragmentNotificationsBinding;



public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private TestOpenHelper helper;
    private TextView textView;
    private SQLiteDatabase db;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        root = binding.getRoot();

        textView = binding.textNotifications;

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

        Cursor cursor = db.query(
                "comment",
                new String[]{"title", "diff", "easy", "get", "pro", "mood"},
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
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