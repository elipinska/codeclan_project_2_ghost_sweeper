package com.e20.ewa.ghostsweeper;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ewa.ghostsweeper.R;

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

    // create a new TextView using single_tile layout for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.single_tile, parent, false);
        }

        Field field = getItem(position);
        TextView tile = convertView.findViewById(R.id.singleTileTextView);
        convertView.setTag(field);
        String buttonText = getItem(position).getTextForButton();
        tile.setText(buttonText);
        if (field.getFieldType() == FieldType.HINT) {
            HintField hintField = (HintField) field;
            tile.setTextColor(ContextCompat.getColor(mContext, HintTextColor.getHintColor(hintField.getBombCount())));
        }


        if (field.isUncovered()) {
            tile.setBackgroundColor(ContextCompat.getColor(mContext, R.color.uncoveredTiles));
        } else {
            tile.setBackgroundColor(ContextCompat.getColor(mContext, R.color.coveredTiles));
        }

        if (field.getFieldType() == FieldType.GHOST && field.isUncovered()) {
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

}
