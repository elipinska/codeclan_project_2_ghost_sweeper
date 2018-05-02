package com.example.ewa.ghostsweeper;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.os.Vibrator;
import android.widget.TextView;
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


        if (convertView == null) {
            // if the view isn't recycled, create a new button
            convertView = LayoutInflater.from(mContext).inflate(R.layout.single_tile, parent, false);
//            button = new Button(mContext);
//            button.setLayoutParams(new ViewGroup.LayoutParams(90, 90));
//            button.setPadding(0,0, 0, 0);
//            button.setTextSize(24);
        }

        Field field = getItem(position);
        TextView tile = convertView.findViewById(R.id.singleTileTextView);
        convertView.setTag(field);
//        tile.setOnClickListener(onClickListener);
//        tile.setOnLongClickListener(onLongClickListener);
        String buttonText = getItem(position).getTextForButton();
        tile.setText(buttonText);



        if (field.isUncovered()) {
            tile.setBackgroundColor(ContextCompat.getColor(mContext, R.color.uncoveredTiles));
        } else {
            tile.setBackgroundColor(ContextCompat.getColor(mContext, R.color.coveredTiles));
        }

        if (field.getFieldType() == FieldType.BOMB && field.isUncovered()) {
            GhostField bomb = (GhostField) field;
            tile.setBackgroundResource(R.drawable.ghost);
            if (bomb.isActivatedByPlayer()) {
                tile.setBackgroundResource(R.drawable.ghost_angry);
            }
        }

        if (field.getIsLongPressed()) {
            tile.setBackgroundResource(R.drawable.trap);
        }

        return convertView;
    }


//    View.OnClickListener onClickListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Field field = (Field) view.getTag();
//
//            if (!field.getIsLongPressed()) {
//                  game.uncoverFieldAndNeighbours(field);
//
//                  GameStatusType gameStatus = game.checkIfGameWonOrLost();
//
//                  if (gameStatus == GameStatusType.LOST) {
//                      Toast.makeText(mContext, R.string.lost_message, Toast.LENGTH_LONG).show();
//                  } else if (gameStatus == GameStatusType.WON) {
//                      Toast.makeText(mContext, R.string.won_message, Toast.LENGTH_LONG).show();
//                  }
//
//                  notifyDataSetChanged();
//
//            }
//
//
//        }
//    };
//
//    View.OnLongClickListener onLongClickListener = new View.OnLongClickListener() {
//        @Override
//        public boolean onLongClick(View view) {
//            Vibrator vibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE);
//            Button button = (Button) view;
//            Field field = (Field) view.getTag();
//
//            if (!field.isUncovered()){
//                if (field.getIsLongPressed()) {
//                    button.setBackgroundColor(ContextCompat.getColor(mContext, R.color.coveredTiles));
//                    button.setText("");
//                    game.addToTrapsLeft();
//                    field.toggleLongPressed();
//                    vibrator.vibrate(25);
//                } else if (game.getTrapsLeft() > 0){
//                    button.setBackgroundResource(R.drawable.trap);
//                    game.subtractFromTrapsLeft();
//                    field.toggleLongPressed();
//                    vibrator.vibrate(25);
//                }
//
//             }
//
//            return true;
//        }
//    };


}
