package com.westwing.vicmonmena.androidtest.dto;

/**
 * @author Vicente Montaño Mena.
 *
 */
public class ImageDTO {

	private String path;
	private String format;
	private int width;
	private int height;
	
	/**
	 * Create an empty ImageDTO instance.
	 */
	public ImageDTO() {
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
