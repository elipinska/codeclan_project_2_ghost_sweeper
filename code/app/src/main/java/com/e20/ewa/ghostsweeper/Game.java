package com.e20.ewa.ghostsweeper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Game implements Parcelable {

    private Board board;
    private int uncoveredFieldsCount;
    private GameStatusType gameStatus;
    private int mData;
    private int trapsLeft;

    public Game(int rowNo, int ghostCount) {
        this.board = new Board(rowNo,ghostCount);
        this.uncoveredFieldsCount = 0;
        this.gameStatus = GameStatusType.IN_PROGRESS;
        this.trapsLeft = board.getGhostCount();
    }

    public Board getBoard() {
        return board;
    }


    public int getTrapsLeft() {
        return trapsLeft;
    }

    public void addToTrapsLeft() {
        trapsLeft++;
    }

    public void subtractFromTrapsLeft() {
        trapsLeft--;
    }

    private void addToUncoveredFieldsCount() {
        uncoveredFieldsCount++;
    }

    public GameStatusType checkIfGameWonOrLost() {
        int allFieldsCount = board.getSimpleFieldsArray().size();
        int ghostCount = board.getGhostPositions().size();

        if (uncoveredFieldsCount >= allFieldsCount) {
            gameStatus = GameStatusType.LOST;
        } else if (uncoveredFieldsCount == allFieldsCount - ghostCount && trapsLeft == 0) {
            gameStatus = GameStatusType.WON;
        }
        return gameStatus;
    }

    private void uncoverAll() {
        for(Field field: board.getSimpleFieldsArray()) {
            if (!field.isUncovered()) {
                field.markAsUncovered();
                addToUncoveredFieldsCount();
            }
        }


    }


    public void uncoverFieldAndNeighbours(Field field) {

        if (!field.getIsLongPressed()) {

            if (field.getFieldType() == FieldType.GHOST) {
                if (!field.isUncovered()) {
                    ((GhostField) field).activate();
                }
                uncoverAll();
            } else {

                field.markAsUncovered();
                addToUncoveredFieldsCount();


                if (field.getFieldType() == FieldType.EMPTY) {
                    ArrayList<Field> neighbours = board.getAllNeighboursForField(field);
                    for (Field neighbour : neighbours) {
                        if (neighbour.getFieldType() != FieldType.GHOST && !neighbour.isUncovered()) {
                            uncoverFieldAndNeighbours(neighbour); //recursion
                        }
                    }

                }
            }
        }
    }



//    Methods required by the Parcelable interface which makes it possible to save an instance of Game in SavedInstanceState


        public int describeContents() {
            return 0;
        }

//        Saves object in a Parcel
        public void writeToParcel(Parcel out, int flags) {
            out.writeInt(mData);
        }

        public static final Parcelable.Creator<Game> CREATOR
                = new Parcelable.Creator<Game>() {
            public Game createFromParcel(Parcel in) {
                return new Game(in);
            }

            public Game[] newArray(int size) {
                return new Game[size];
            }
        };

//        Recreates object from parcel
        private Game(Parcel in) {
            mData = in.readInt();
        }

}
