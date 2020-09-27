package com.GinoAmaury.TVMazeApp.View.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.GinoAmaury.TVMazeApp.Model.Object.Search;
import com.GinoAmaury.TVMazeApp.R;
import com.google.android.material.chip.Chip;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShowInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowInfoFragment extends Fragment {

    private Search show;
    private static final String SHOWARG = "show";

    @BindView(R.id.titleShow)
    TextView titleShow;
    @BindView(R.id.chip)
    Chip chip;

    public ShowInfoFragment() {
    }

    public static ShowInfoFragment newInstance(Search show) {
        ShowInfoFragment fragment = new ShowInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(SHOWARG, show);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            show = (Search) getArguments().getSerializable(SHOWARG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_info, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(show != null){
            titleShow.setText(show.getName());
            chip.setText(show.getGenres().toString());
        }
    }
}