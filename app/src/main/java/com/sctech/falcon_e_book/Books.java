package com.sctech.falcon_e_book;

/**
 * Created by miketsai on 10/31/2017.
 */

public class Books {
    private String mMaturity;
    private String mAuthors;
    private String mTitle;
    /** Website URL of the earthquake */
    private String mUrl;
    private String mDate;

    public Books(String maturity, String authors, String title, String url, String date)
    {
        mMaturity = maturity;
        mAuthors = authors;
        mTitle = title;
        mUrl = url;
        mDate = date;

    }

    public String getMaturity(){return mMaturity;}
    public String getAuthor(){return mAuthors;}
    public String getTitle(){return mTitle;}
    public String getUrl(){return mUrl;}
    public String getDate(){return mDate;}

}
