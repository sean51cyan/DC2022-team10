package com.example.myschedule.ui.home;

import static android.graphics.Color.*;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myschedule.ClassEditDialogFragment;
import com.example.myschedule.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TableLayout mTableLayout;
    public static TextView[][] class_views;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mTableLayout = binding.timetable;
        class_views = new TextView[7][6];
        createTable(homeViewModel);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                homeViewModel.getClassName(i, j).observe(getViewLifecycleOwner(), class_views[i][j]::setText);
            }
        }
        return root;
    }

    public void createTable(HomeViewModel homeViewModel) {
        int leftRowMargin = 0;
        int topRowMargin = 0;
        int rightRowMargin = 0;
        int bottomRowMargin = 0;
        int textSize = 20, smallTextSize = 15;

        mTableLayout.removeAllViews();

        for (int i = -1; i <= 6; i++) {
            final TextView tv = new TextView(getContext());
            tv.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setPadding(5, 15, 0, 15);
            tv.setWidth(100);
            //一番左の奴
            if (i == -1) {
                tv.setText("時間");
                tv.setBackgroundColor(parseColor("#219ddd"));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
            } else {
                tv.setHeight(200);
                tv.setText(String.valueOf(i + 1));
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, 40);
            }
            //
            final TableRow tr = new TableRow(getContext());
            tr.setId(i + 1);
            TableLayout.LayoutParams trParams = new
                    TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT);
            trParams.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
            tr.setPadding(0, 0, 0, 0);
            tr.setLayoutParams(trParams);
            tr.addView(tv);
            if (i != -1) {
                tr.setMinimumHeight(200);
            }
            for (int j = 0; j < 6; j++) {
                final TextView tv1 = new TextView(getContext());
                tv1.setLayoutParams(new
                        TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
                tv1.setGravity(Gravity.CENTER);
                tv1.setPadding(5, 15, 0, 15);
                tv1.setWidth(156);
                String[] day_name = {"月", "火", "水", "木", "金", "土"};
                if (i == -1) {
                    tv1.setText(day_name[j]);
                    //a260bf
                    tv1.setBackgroundColor(Color.parseColor("#a260bf"));
                    tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallTextSize);
                } else {
                    tv1.setHeight(200);
                    tv1.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                    class_views[i][j] = tv1;
                    final int ii = i;
                    final int jj = j;
                    tv1.setOnClickListener(view -> {
                        ClassEditDialogFragment dialogFragment =
                                new ClassEditDialogFragment(ii, jj, homeViewModel);
                        dialogFragment.show(getParentFragmentManager(), "edit_dialog");
                    });
                }
                tr.addView(tv1);
            }
            mTableLayout.addView(tr, trParams);
            // 罫線を追加
            if (i > -1) {
                final TableRow trSep = new TableRow(getContext());
                TableLayout.LayoutParams trParamsSep = new
                        TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT);
                trParamsSep.setMargins(leftRowMargin, topRowMargin, rightRowMargin, bottomRowMargin);
                trSep.setLayoutParams(trParamsSep);
                TextView tvSep = new TextView(getContext());
                TableRow.LayoutParams tvSepLay = new
                        TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT);
                tvSepLay.span = 7;
                tvSep.setLayoutParams(tvSepLay);
                tvSep.setBackgroundColor(Color.parseColor("#d9d9d9"));
                tvSep.setHeight(2);
                trSep.addView(tvSep);
                mTableLayout.addView(trSep, trParamsSep);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}