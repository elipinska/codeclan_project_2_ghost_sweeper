package com.example.ewa.minesweeper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.os.Vibrator;

public class BoardAdapter extends BaseAdapter {
    private Context mContext;
    private Board board;


    public BoardAdapter(Context context, Board board) {
        mContext = context;
        this.board = board;
    }

    public int getCount() {
        return board.getSimpleFieldsArray().size();
    }

    public Field getItem(int position) {
        return board.getSimpleFieldsArray().get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new Button for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {

        Button button;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            button = new Button(mContext);
            button.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
            button.setPadding(0,0, 0, 0);
            button.setTag(position);
            button.setOnClickListener(onClickListener);
            button.setOnLongClickListener(onLongClickListener);
        } else {
            button = (Button) convertView;
        }

        button.setBackgroundResource(R.drawable.tile);
        return button;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button)view;
            String hint = "Bomb";
            int position = (int) view.getTag();


            IUncoverable correspondingField = (IUncoverable) board.getFieldAtIndex(position);
            if (!(correspondingField.isBomb()))
                hint = ((HintField)correspondingField).getBombCount().toString();
            if (!(b.getText() == "Long")) {
                ((Button) view).setText(hint);
            }
        }
    };

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
            Button b = (Button)view;
            if (b.getText() == "Long") {
                b.setText("");
                vibrator.vibrate(25);
            } else {
                b.setText("Long");
                vibrator.vibrate(25);
            }
            return true;
        }
    };


}
