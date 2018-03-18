package com.example.letsseatinmetro.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.letsseatinmetro.R;

/**
 * Created by 이승헌 on 2018-03-18.
 */

public class ExtreamFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.extream_tab, container, false);
    }
}
