package com.example.ewa.ghostsweeper;

import android.content.Context;
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
    private Game game;


    public BoardAdapter(Context context, Game game) {
        mContext = context;
        this.game = game;
    }

    public int getCount() {
        return game.getBoard().getSimpleFieldsArray().size();
    }

    public Field getItem(int position) {
        return game.getBoard().getFieldAtIndex(position);
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
            GhostField bomb = (GhostField) field;
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


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Field field = (Field) view.getTag();

            if (!field.getIsLongPressed()) {
                  game.uncoverFieldAndNeighbours(field);

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
