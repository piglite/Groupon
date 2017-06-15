package com.tarena.groupon.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tarena.groupon.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by pjy on 2017/6/15.
 */

public class FragmentC extends BaseFragment{
    @BindView(R.id.tv_fragment_skip)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c,container,false);
        ButterKnife.bind(this,view);
        skip(textView);
        return view;
    }
}
