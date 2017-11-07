package com.sctech.falcon_e_book;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainFalconEbookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Books>> {

    private String mSubject = "mystery";
    private static final String USGS_REQUEST_URL =
            //        "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=20";
            "https://www.googleapis.com/books/v1/volumes?q=";


    public static final String LOG_TAG = MainFalconEbookActivity.class.getName();
    private BooksAdapter mAdapter;
    private TextView mEmptyView;
    private Loader<List<Books>> mLoader = null;
    /** Database helper that will provide us access to the database */

    private static final int BOOKLIST_LOADER_ID = 1;

    @Override
    public Loader<List<Books>> onCreateLoader(int i, Bundle bundle) {
        String strQuerySubject = USGS_REQUEST_URL + mSubject+"&maxResults=20";
        Log.v(LOG_TAG, "Loader created: " + i);
        TextView CurrentSubjectView = (TextView) findViewById(R.id.bookSubject);
        mAdapter.clear();
        CurrentSubjectView.setText("BookSubject:" + mSubject + ", searing ...");
        return (mLoader =  new BookLoader(this, strQuerySubject));
    }



    @Override
    public void onLoadFinished(Loader<List<Books>> loader, List<Books> booksList) {

        // Clear the adapter of previous earthquake data
        if (mAdapter != null) {
            mAdapter.clear();
        }

        TextView CurrentSubjectView = (TextView) findViewById(R.id.bookSubject);
        CurrentSubjectView.setText("BookSubject: " + mSubject + ", Enter new subject and click 'searching' on menu");

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (booksList != null && !booksList.isEmpty()) {
            Log.v(LOG_TAG, "Loader completed, adding all reports");
            mAdapter.addAll(booksList);
            //updateUi(earthquakes);
        }
        else {
            mEmptyView.setText(R.string.no_report);
            Log.v(LOG_TAG, "report empty view");

        }

    }

    @Override
    public void onLoaderReset(Loader<List<Books>> loader) {
        // Loader reset, so we can clear out our existing data.
        Log.v(LOG_TAG, "Loader reset, clear mAdapter object");
        mAdapter.clear();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_falcon_ebook);

        ListView bookListView = (ListView) findViewById(R.id.listBooks);
        mEmptyView = (TextView) findViewById(R.id.emptyView);
        bookListView.setEmptyView(mEmptyView);

         // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new BooksAdapter(this, new ArrayList<Books>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        bookListView.setAdapter(mAdapter);

       bookListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current earthquake that was clicked on
                Books currentBook = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri booksUri = Uri.parse(currentBook.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, booksUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager MgrConn = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = MgrConn.getActiveNetworkInfo();
        boolean isConnected = ((activeNetwork != null) && activeNetwork.isConnectedOrConnecting());
        if ( isConnected == false) {
             mEmptyView.setText(R.string.no_internet);

            // notify user you are off-line

        }
        else {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(BOOKLIST_LOADER_ID, null, this);
        }

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainFalconEbookActivity.this, AddPetActivity.class);
                startActivity(intent);




            }
        });


    }




    public void getBookSubjec(MenuItem item)
    {
        EditText edit = (EditText)findViewById(R.id.book_subject_input);
        mSubject = edit.getText().toString();
        LoaderManager loaderManager = getLoaderManager();

         //Use restart loader to do new query search
        loaderManager.restartLoader(BOOKLIST_LOADER_ID, null, this);
 }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_falcon_ebook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_seaching) {
            getBookSubjec(item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
