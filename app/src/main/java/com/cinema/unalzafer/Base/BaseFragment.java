package com.cinema.unalzafer.Base;

import android.os.Bundle;

import com.cinema.unalzafer.Helper.RetrofitHelper;
import com.cinema.unalzafer.Listener.ResponsesBody;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment implements ResponsesBody {

    public RetrofitHelper retrofitHelper;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitHelper = new RetrofitHelper(getActivity(), this, "");
    }
}
