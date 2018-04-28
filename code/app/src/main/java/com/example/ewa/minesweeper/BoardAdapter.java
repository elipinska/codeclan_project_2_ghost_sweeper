package com.example.ewa.minesweeper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.os.Vibrator;

public class BoardAdapter extends BaseAdapter {
    private Context mContext;

    public BoardAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new Button for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            button = new Button(mContext);
            button.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
            button.setPadding(0,0, 0, 0);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = (Button)v;
                    if (!(b.getText() == "Long")) {
                        ((Button) v).setText("Clicked");
                    }
                }
            });
            button.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
                    Button b = (Button)v;
                    if (b.getText() == "Long") {
                        b.setText("");
                        vibrator.vibrate(25);
                    } else {
                        b.setText("Long");
                        vibrator.vibrate(25);
                    }
                    return true;
                }
            });
        } else {
            button = (Button) convertView;
        }

        button.setBackgroundResource(mThumbIds[position]);
        return button;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
            R.drawable.tile, R.drawable.tile,
    };
}
