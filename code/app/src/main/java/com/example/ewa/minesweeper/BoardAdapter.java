package com.example.ewa.minesweeper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.os.Vibrator;

import java.util.ArrayList;

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
            Field field = getItem(position);
            button.setTag(field);
            button.setOnClickListener(onClickListener);
            button.setOnLongClickListener(onLongClickListener);
        } else {
            button = (Button) convertView;
            button.setText("");
        }

        String buttonText = getItem(position).getTextForButton();
        button.setText(buttonText);

        button.setBackgroundResource(R.drawable.tile);
        return button;
    }

    public void uncoverFieldAndNeighbours(Field field) {

        field.markAsUncovered();

        if (field.getFieldType() == FieldType.EMPTY) {
            ArrayList<Field> neighbours = board.getAllNeighboursForField(field);
            for (Field neighbour:neighbours) {
                if (neighbour.getFieldType() == FieldType.EMPTY && !neighbour.isUncovered()) {
                    uncoverFieldAndNeighbours(neighbour);
                }
            }

        }



    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button)view;
            Field field = (Field) view.getTag();

//            field.markAsUncovered();
            uncoverFieldAndNeighbours(field);

            if (button.getText() != "Long") {
                ((Button) view).setText(field.getTextForButton());
            }


        }
    };

    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
            Button button = (Button) view;
            Field field = (Field) view.getTag();

            if (!field.isUncovered()){
                if (button.getText() == "Long") {
                    button.setText("");
                    vibrator.vibrate(25);
                } else {
                    button.setText("Long");
                    vibrator.vibrate(25);
                }
             }
            return true;
        }
    };


}
