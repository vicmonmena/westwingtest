package com.westwing.vicmonmena.androidtest.ui.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.westwing.vicmonmena.androidtest.R;
import com.westwing.vicmonmena.androidtest.ui.CustomSectionFragment;

/**
 * @author Vicente Montaño Mena
 * Customized fragment pager adapter.
 */
public class CustomFragmentAdapter extends FragmentPagerAdapter {

	/**
	 * Number of fragment items.
	 */
	static final int NUM_ITEMS = 2;
	
	/**
	 * It allows load text from strings resources.
	 */
	private Context ctx;
	
	/**
	 * Create an CustomFragmentAdapter instance.
	 * @param fm - fragment manager.
	 * @param ctx - allows load text from strings resources.
	 */
	public CustomFragmentAdapter(FragmentManager fm, Context ctx) {
		super(fm);
		this.ctx = ctx;
	}

	@Override
	public Fragment getItem(int i) {
		Fragment fragment = new CustomSectionFragment();
    	Bundle args = new Bundle();
        switch (i) {
            case 0:
                args.putInt(CustomSectionFragment.LAYOUT_RESOURCE_ID,
                		R.layout.fragment_section_image);
                
                break;
            case 1:
            	args.putInt(CustomSectionFragment.LAYOUT_RESOURCE_ID,
            			R.layout.fragment_section_button);
                break;
            default:
            	break;
        }
        fragment.setArguments(args);
        return fragment;
	}

	@Override
	public int getCount() {
		return NUM_ITEMS;
	}
	
	 @Override
     public CharSequence getPageTitle(int position) {
         return ctx.getString(R.string.base_page_title) + " " + (position + 1);
     }

}