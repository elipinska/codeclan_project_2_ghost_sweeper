package com.example.ewa.minesweeper;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.os.Vibrator;
import android.widget.Toast;

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
        return board.getFieldAtIndex(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new Button for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {

        Button button;
        if (convertView == null) {
            // if the view isn't recycled, create a new button
            button = new Button(mContext);
            button.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
            button.setPadding(0,0, 0, 0);
            button.setTextSize(24);
        } else {
            //otherwise reuse previously created view
            button = (Button) convertView;
        }

        Field field = getItem(position);
        button.setTag(field);
        button.setOnClickListener(onClickListener);
        button.setOnLongClickListener(onLongClickListener);
        String buttonText = getItem(position).getTextForButton();
        button.setText(buttonText);



        if (field.isUncovered()) {
            button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.uncoveredTiles));
        } else {
            button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.coveredTiles));
        }

        if (field.getFieldType() == FieldType.BOMB && field.isUncovered()) {
            BombField bomb = (BombField) field;
            button.setBackgroundResource(R.drawable.ghost);
            if (bomb.isActivatedByPlayer()) {
                button.setBackgroundResource(R.drawable.ghost_angry);
            }
        }

        if (field.getIsLongPressed()) {
            button.setBackgroundResource(R.drawable.trap);
        }

        return button;
    }

    public void uncoverFieldAndNeighbours(Field field) {

        if (!field.getIsLongPressed()) {

            if (field.getFieldType() == FieldType.BOMB) {
                if (!field.isUncovered()) {
                    ((BombField) field).activate();
                    Toast.makeText(mContext, R.string.lost_message,
                            Toast.LENGTH_LONG).show();
                }
                board.uncoverAll();
            }

            field.markAsUncovered();



            if (field.getFieldType() == FieldType.EMPTY) {
                ArrayList<Field> neighbours = board.getAllNeighboursForField(field);
                for (Field neighbour : neighbours) {
                    if (neighbour.getFieldType() != FieldType.BOMB && !neighbour.isUncovered()) {
                        uncoverFieldAndNeighbours(neighbour); //recursion
                    }
                }

            }
        }
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Field field = (Field) view.getTag();

            if (!field.getIsLongPressed()) {
                  uncoverFieldAndNeighbours(field);

                ((Button) view).setText(field.getTextForButton());
                notifyDataSetChanged();

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
                if (field.getIsLongPressed()) {
                    button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.coveredTiles));
                    button.setText("");
                } else {
                    button.setBackgroundResource(R.drawable.trap);
                }
                field.toggleLongPressed();
                vibrator.vibrate(25);
             }

            return true;
        }
    };


}
