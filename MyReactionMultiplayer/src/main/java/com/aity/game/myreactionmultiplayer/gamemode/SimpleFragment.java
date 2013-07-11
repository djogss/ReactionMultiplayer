package com.aity.game.myreactionmultiplayer.gamemode;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aity.game.myreactionmultiplayer.R;

public class SimpleFragment extends Fragment implements GameMode {

    private ImageView imageView;
    public SimpleFragment() {

//        imageView = (ImageView) getActivity().findViewById(R.id.rndImageView);
//        imageView.setOnClickListener(imgOnClickListener);
//        imageView.setVisibility(View.GONE);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.game_action_activity, container, false);
        ImageView imageView = (ImageView)v.findViewById(R.id.rndImageView);
        v.setVisibility(View.GONE);
        return v;
    }
    @Override
    public void showObject() {

    }

    @Override
    public void hideObject() {

    }
}
