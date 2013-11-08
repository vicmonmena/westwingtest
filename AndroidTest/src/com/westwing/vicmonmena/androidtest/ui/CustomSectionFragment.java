package com.westwing.vicmonmena.androidtest.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Vicente Montaño Mena
 * Custom section fragment class.
 */
public class CustomSectionFragment extends Fragment {

	/**
     * Argument key name.
     */
    public static final String LAYOUT_RESOURCE_ID = "layout_resource_id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	Bundle args = getArguments();
    	View rootView = inflater.inflate(args.getInt(LAYOUT_RESOURCE_ID), 
    		container, false);
        return rootView;
    }
}
