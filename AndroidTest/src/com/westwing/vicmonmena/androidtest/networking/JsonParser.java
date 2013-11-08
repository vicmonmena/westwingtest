package com.westwing.vicmonmena.androidtest.networking;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.westwing.vicmonmena.androidtest.dto.ImageDTO;
import com.westwing.vicmonmena.androidtest.dto.ProductDTO;

/**
 * @author Vicente Montaño Mena
 * Json parser operations.
 */
public class JsonParser {

	/**
	 * TAG for log messages.
	 */
	private static final String TAG = JsonParser.class.getSimpleName();
	
	/**
	 * Parse Json products to ProductDTOs.
	 * @param json
	 * @return ArrayList of products inside json String argument, or null
	 * if an exception happend.
	 */
	public static ArrayList<ProductDTO> parseJsonToProducts(String json) {
		ArrayList<ProductDTO> products = null;

		try {
			JSONObject jsonObject = new JSONObject(json);
			String metadata = jsonObject.getString("metadata");
			JSONObject jsonMetadata = new JSONObject(metadata);
			JSONArray jsonGroupArray = jsonMetadata.getJSONArray("results");
		
			if ( jsonGroupArray != null ){
				
				products = new ArrayList<ProductDTO>();
				
				// Get Products from Json products
		        for (int i = 0; i < jsonGroupArray.length(); i++) {
		
		            JSONObject jsonProduct = jsonGroupArray.getJSONObject(i);
		            
		            ProductDTO product = new ProductDTO();
		            product.setId(jsonProduct.getString("id"));

		            JSONArray jsonImageArray = jsonProduct.getJSONArray("images");
		            ArrayList<ImageDTO> producImages = new ArrayList<ImageDTO>();
		            
		            // Get Images from Json images
			        for (int j = 0; j < jsonImageArray.length(); j++) {
			        	ImageDTO image = parseJsonToImage(jsonImageArray.getJSONObject(j));
			        	if (image != null) {
			        		producImages.add(image);
			        	}
			        }
			        
			        Log.d(TAG, products.size() + " images found of '" + product.getId() + "' product");
			        
			        product.setImages(producImages);
		        	products.add(product);
		        }
		        
		        Log.d(TAG, products.size() + " products found");
			}
		} catch (JSONException e) {
			Log.e(TAG, "An exception was caught parsing products");
		}
		return products;
	}

	/**
	 * Parse Json image to ImageDTO.
	 * @param jsonObject
	 * @return ImageDTO or null if an exception happens
	 */
	private static ImageDTO parseJsonToImage(JSONObject jsonObject) {
		ImageDTO image = null;
		try {
	        image = new ImageDTO();
	        image.setPath(jsonObject.getString("path"));
	        image.setFormat(jsonObject.getString("format"));
	        image.setWidth(jsonObject.getInt("width"));
	        image.setHeight(jsonObject.getInt("height"));
		} catch (JSONException e) {
			Log.e(TAG, "An exception was caught parsing image");
		}
		return image;
	}
}
