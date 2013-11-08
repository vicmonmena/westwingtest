package com.westwing.vicmonmena.androidtest.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.TitlePageIndicator;
import com.viewpagerindicator.TitlePageIndicator.IndicatorStyle;
import com.westwing.vicmonmena.androidtest.Controller;
import com.westwing.vicmonmena.androidtest.R;
import com.westwing.vicmonmena.androidtest.ui.adapter.CustomFragmentAdapter;

/**
 * @author Vicente Montaño Mena
 * Main activity which create and manage user interface.
 */
public class MainActivity extends FragmentActivity {

	/**
	 * TAG for logs.
	 */
	private final String TAG = MainActivity.class.getSimpleName();
	
	/**
	 * Page manager.
	 */
	ViewPager pager;
	
	/**
	 * Tabs manager.
	 */
	TitlePageIndicator indicator;
	
    /**
     * Fragment adapter customized to this application. 
     */
    CustomFragmentAdapter fragmentAdapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                showSimpleDialog(MainActivity.this,getString(R.string.about_title),
                	getString(R.string.about_message));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
	/**
	 * Create and show a simple dialog with neutral button.
	 * @param context
	 * @param title
	 * @param message
	 */
	private void showSimpleDialog(Context context, String title, String message) {
	
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder
			.setTitle(title)
			.setMessage(message)
			.setCancelable(false)
			.setNeutralButton(getString(R.string.dialog_button_title),new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			})
			.create()
			.show();
	}
	
	/**
	 * Initialize view components.
	 */
	private void initView() {
		
		fragmentAdapter = new CustomFragmentAdapter(getSupportFragmentManager(), MainActivity.this); 

		pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(fragmentAdapter);
        
		indicator = (TitlePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        indicator.setFooterIndicatorStyle(IndicatorStyle.Triangle);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setPageCounter(getString(R.string.base_page_title) + " " + (position + 1));
                Log.i(TAG,"Current position: " + position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        setPageCounter(getString(R.string.base_page_title) + " " + (pager.getCurrentItem() + 1));
        new LoadImageTask().execute();
	}
	
	/**
	 * Set the text of the page counter.
	 * @param text - the text to indicate the current pager selected.
	 */
	public void setPageCounter(String text) {
		((TextView)findViewById(R.id.vp_counter)).setText(text);
	}
	
	/**
	 * Catch the event onclick when view is clicked.
	 * @param view - view clicked.
	 */
	public void onButtonClick(View view) {
		
		if (view.getId() == R.id.button1) {
			TextView helloTextView = (TextView)findViewById(R.id.hello);
			
			if(helloTextView.getVisibility() == TextView.INVISIBLE) {
				helloTextView.setVisibility(TextView.VISIBLE);
			} else {
				helloTextView.setVisibility(TextView.INVISIBLE);
			}
		}
	}
	
	/**
	 * Load an image in background.
	 */
	private class LoadImageTask extends AsyncTask<Void, Void, Drawable> {
		
        @Override
        protected Drawable doInBackground(Void... params) {
        	
        	Drawable image = null;
        	
            try {
            	image = Controller.getInstance().loadRandomImageFromURL(
            		getString(R.string.product_uri));
			} catch (Exception e) {
				Log.e(TAG, "An exception was caught loading the image", e);
			}
            return image;
        }

        @Override
        protected void onPostExecute(Drawable result) {
        	
        	if(result != null) {
				((ImageView)findViewById(R.id.image1)).setImageDrawable(result);
        	} else {
        		((ImageView)findViewById(R.id.image1)).setImageResource(R.drawable.image);
        		Toast.makeText(MainActivity.this, "Image not found. If persist, please contact with developers.",
        			Toast.LENGTH_SHORT).show();
        	}
        	((ImageView)findViewById(R.id.image1)).setVisibility(ImageView.VISIBLE);
        	((ProgressBar)findViewById(R.id.imageProgressBar)).setVisibility(
        		ProgressBar.GONE);
	        super.onPostExecute(result);
        }		
    }
}