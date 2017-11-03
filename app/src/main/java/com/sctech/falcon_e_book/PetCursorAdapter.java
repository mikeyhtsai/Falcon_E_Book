package com.sctech.falcon_e_book;

/**
 * Created by miketsai on 11/3/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/* this is how getView being implemented in cursor adapter
/**
     * @see android.widget.ListAdapter#getView(int, View, ViewGroup)
     */
/*
public View getView(int position, View convertView, ViewGroup parent) {
        if (!mDataValid) {
        throw new IllegalStateException("this should only be called when the cursor is valid");
        }
        if (!mCursor.moveToPosition(position)) {
        throw new IllegalStateException("couldn't move cursor to position " + position);
        }
        View v;
        if (convertView == null) {
        v = newView(mContext, mCursor, parent);
        } else {
        v = convertView;
        }
        bindView(v, mContext, mCursor);
        return v;
        }
 */
/**
 * {@link PetCursorAdapter} is an adapter for a list or grid view
 * that uses a {@link Cursor} of pet data as its data source. This adapter knows
 * how to create list items for each row of pet data in the {@link Cursor}.
 */
public class PetCursorAdapter extends CursorAdapter {

    /**
     * Constructs a new {@link PetCursorAdapter}.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     */
    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    /**
     * Makes a new blank list item view. No data is set (or bound) to the views yet.
     *
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already
     *                moved to the correct position.
     * @param parent  The parent to which the new view is attached to
     * @return the newly created list item view.
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View listItemView = null;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(
                    R.layout.list_item_pets, parent, false);
        }
        return listItemView;
    }

    /**
     * This method binds the pet data (in the current row pointed to by cursor) to the given
     * list item layout. For example, the name for the current pet can be set on the name TextView
     * in the list item layout.
     *
     * @param view    Existing view, returned earlier by newView() method
     * @param context app context
     * @param cursor  The cursor from which to get the data. The cursor is already moved to the
     *                correct row.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = view;


        int idColumnIndex = cursor.getColumnIndex(PetContract.PetEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_NAME);
        int breedColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_BREED);
        int genderColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_GENDER);
        int weightColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUMN_PET_WEIGHT);

        TextView nameView = (TextView) listItemView.findViewById(R.id.name);
        nameView.setText(cursor.getString(nameColumnIndex));

        int currentID = cursor.getInt(idColumnIndex);
        String currentName = cursor.getString(nameColumnIndex);
        String currentBreed = cursor.getString(breedColumnIndex);
        int currentGender = cursor.getInt(genderColumnIndex);
        int currentWeight = cursor.getInt(weightColumnIndex);

        TextView summaryView = (TextView) listItemView.findViewById(R.id.summary);
        summaryView.setText(currentID + " - " +
                currentName + " - " +
                currentBreed + " - " +
                currentGender + " - " +
                currentWeight);

    }
}
