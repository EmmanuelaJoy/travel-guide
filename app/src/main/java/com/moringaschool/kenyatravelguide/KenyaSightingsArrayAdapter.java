package com.moringaschool.kenyatravelguide;

import android.content.Context;
import android.widget.ArrayAdapter;

public class KenyaSightingsArrayAdapter extends ArrayAdapter{
    private Context mContext;
    private String[] mSightingName;
    private String[] mSightingKind;

    public KenyaSightingsArrayAdapter(Context mContext, int resource, String[] sightingName, String[] sightingKind) {
        super(mContext, resource);
        this.mContext = mContext;
        this.mSightingName = sightingName;
        this.mSightingKind = sightingKind;
    }
    @Override
    public Object getItem(int position) {
        String name = mSightingName[position];
        String kind = mSightingKind[position];
        return String.format("%s \nCategory : %s", name, kind);
    }

    @Override
    public int getCount() {
        return mSightingName.length;
    }
}
