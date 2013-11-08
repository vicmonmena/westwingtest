package com.westwing.vicmonmena.androidtest.dto;

import java.util.ArrayList;

/**
 * @author Vicente Montaño Mena.
 *
 */
public class ProductDTO {

	/**
	 * Product identifier.
	 */
	private String id;
	
	/**
	 * Product images. 
	 */
	private ArrayList<ImageDTO> images;
	
	/**
	 * Create an empty ProductDTO instance.
	 */
	public ProductDTO() {
		
	}
	
	/**
	 * Set id attribute.
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * @return id attribute.
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Set product images.
	 * @param images
	 */
	public void setImages(ArrayList<ImageDTO> images) {
		this.images = images;
	}
	
	/**
	 * @return get produc images.
	 */
	public ArrayList<ImageDTO> getImages() {
		return images;
	}
}
