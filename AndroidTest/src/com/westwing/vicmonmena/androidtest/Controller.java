package com.westwing.vicmonmena.androidtest;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import android.graphics.drawable.Drawable;

import com.westwing.vicmonmena.androidtest.dto.ProductDTO;
import com.westwing.vicmonmena.androidtest.networking.CustomHttpConnection;
import com.westwing.vicmonmena.androidtest.networking.JsonParser;

/**
 * @author Vicente Montaño Mena.
 * (MVC pattern design).
 */
public class Controller {
	
	private static Controller controller;
	
	/**
	 * (Singleton pattern design).
	 */
	private Controller() {}
	
	/**
	 * @return get the only Controller instance.
	 */
	public static Controller getInstance() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	/**
	 * Get an ArrayList of ProductDTOs from ur.
	 * @param url
	 * @return
	 */
	public ArrayList<ProductDTO> readProductsFromURI(String url) {
		return JsonParser.parseJsonToProducts(CustomHttpConnection.getProducts(url));
	}
	
	/**
	 * Get an image drawable from url.
	 * @param url
	 * @return
	 */
	public Drawable getImageFromURL(String url) {
		Drawable image = null;
		InputStream is = CustomHttpConnection.getImage(url);
		
		if (is != null) {
			image = Drawable.createFromStream(is, "image");
		}
		return image;
	}

	/**
	 * This method loads a random image from ramdon product extrated from url.
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public Drawable loadRandomImageFromURL(String url) throws Exception {
		ArrayList<ProductDTO> products = readProductsFromURI(url);
		int randomProductPosition = new Random().nextInt(products.size());
		ProductDTO product = products.get(randomProductPosition);
		int randomImagePosition = new Random().nextInt(product.getImages().size());
		String imagePath = product.getImages().get(randomImagePosition).getPath();
		return getImageFromURL(imagePath);
	}
}
