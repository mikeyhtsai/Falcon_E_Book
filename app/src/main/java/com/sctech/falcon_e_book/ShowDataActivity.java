package com.sctech.falcon_e_book;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sctech.falcon_e_book.PetContract.PetEntry;

/**
 * Created by miketsai on 11/3/2017.
 */

public class ShowDataActivity  extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private PetDbHelper mDbHelper;
    private static final int PET_LOADER_ID = 2;
    private CursorLoader mLoader = null;
    private PetCursorAdapter mAdapter = null;
    public static final String LOG_TAG = ShowDataActivity.class.getName();

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                PetContract.PetEntry._ID,
                PetContract.PetEntry.COLUMN_PET_NAME,
                PetContract.PetEntry.COLUMN_PET_BREED,
                PetContract.PetEntry.COLUMN_PET_GENDER,
                PetContract.PetEntry.COLUMN_PET_WEIGHT };

        Log.v(LOG_TAG, "Pet Loader created: " + i);

        return  new CursorLoader(this,
                PetContract.PetEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
     mAdapter.swapCursor(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaydata);

        Intent intent = getIntent();

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new PetDbHelper(this);

        ListView petListView = (ListView) findViewById(R.id.listPets);
        View emptyView = findViewById(R.id.empty_view);

        petListView.setEmptyView(emptyView);
        mAdapter = new PetCursorAdapter(this, null);
        petListView.setAdapter(mAdapter);

        getLoaderManager().initLoader(PET_LOADER_ID, null, this);

        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Cursor cursor = db.
                Intent intent = new Intent(ShowDataActivity.this, AddPetActivity.class);
                Uri currentPetUri = ContentUris.withAppendedId(PetEntry.CONTENT_URI, id);

                intent.setData(currentPetUri);
                startActivity(intent);

            }
        });
    }

}
