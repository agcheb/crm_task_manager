package com.gb.students.crm_task_manager.contacts.profile.tab_fragments.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gb.students.crm_task_manager.R;

public class FragmentTabMoreInfo extends Fragment {


    public static FragmentTabMoreInfo newInstance(Bundle bundle) {
        FragmentTabMoreInfo currentFragment = new FragmentTabMoreInfo();
        Bundle args = new Bundle();
        args.putBundle("gettedArgs", bundle);
        currentFragment.setArguments(args);
        return currentFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_more_info, container, false);
        return view;
    }
}