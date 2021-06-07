package com.moringaschool.kenyatravelguide;

import android.content.Context;
import android.widget.ArrayAdapter;

public class TouristFacilitiesArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mTouristFacilitiesName;
    private String[] mTouristFacilitiesKind;

    public TouristFacilitiesArrayAdapter(Context mContext, int resource, String[] mtouristFacilitiesName, String[] mtouristFacilitiesKind) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mTouristFacilitiesName = mtouristFacilitiesName;
        this.mTouristFacilitiesKind = mtouristFacilitiesKind;
    }
    @Override
    public Object getItem(int position) {
        String name = mTouristFacilitiesName[position];
        String kind = mTouristFacilitiesKind[position];
        return String.format("%s \nCategory : %s", name, kind);
    }

    @Override
    public int getCount() {
        return mTouristFacilitiesName.length;
    }
}
