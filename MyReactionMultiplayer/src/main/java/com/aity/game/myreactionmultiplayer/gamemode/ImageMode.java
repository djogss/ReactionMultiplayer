package com.aity.game.myreactionmultiplayer.gamemode;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.aity.game.myreactionmultiplayer.R;

import java.util.Random;

public class ImageMode extends Fragment implements GameMode {

    private final int OBJECT_HEIGHT = 75;
    private final int OBJECT_WIDTH  = 75;

    private ImageView imageView;
    public ImageMode() {
        imageView = (ImageView) getActivity().findViewById(R.id.rndImageView);
        imageView.setOnClickListener(imgOnClickListener);
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void showObject() {

            Random rnd = new Random();
            int randomLeft = rnd.nextInt(300);
            int randomTop = rnd.nextInt(400);
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(randomLeft, randomTop, 0, 0);
            Log.d("CustomLog","width " + lp.width + " height " + lp.height + " top margin " + lp.topMargin + " bottom margin " + lp.bottomMargin);
            imageView.setLayoutParams(lp);
            imageView.getLayoutParams().height = OBJECT_HEIGHT;
            imageView.getLayoutParams().width  = OBJECT_HEIGHT;
            imageView.setVisibility(View.VISIBLE);
    }


    /**
     * Makes object invisible and notifies running thread that he can show other object at any time.
     */
    @Override
    public void hideObject() {
        Log.d("CustomLog", "Hide image method");
        imageView.setVisibility(View.INVISIBLE);
    }

    /**
     * Object is hidden once it's clicked.
     * List view gets populated with the reaction times.
     */
    private View.OnClickListener imgOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            hideObject();
//            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ReactionGame.this,android.R.layout.simple_list_item_1, reactionTimes.getRunningTimes());
//            arrayAdapter.notifyDataSetChanged();
//            listView.setAdapter(arrayAdapter);
        }
    };
}
