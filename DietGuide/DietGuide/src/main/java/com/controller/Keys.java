package com.controller;

public class Keys {

	public static final String FOOD_ALLERGY = "allergies";
	public static final String FOOD_CATEGORY = "categories";
	public static final String BASE_URL = "https://api.wegmans.io/";

	public static final String WENGMEN_API_KEY = "&Subscription-Key=6ce1a4c3915e46b8bb28dbcd762b561c";
	public static final String API_VERSION = "?api-version=2018-10-18";

	public static final String PRODUCT_URL = BASE_URL + "products/%s" + API_VERSION + WENGMEN_API_KEY;
	public static final String PRODUCT_PRICE_URL = BASE_URL + "products/%s/prices" + API_VERSION + WENGMEN_API_KEY;
	public static final String CATEGORY_URL = BASE_URL + "products/categories/%s" + API_VERSION + WENGMEN_API_KEY;

	public static final String CHOLESTROL = "Cholesterol";
	public static final String FIBER = "Fiber";
	public static final String PROTEIN = "Protein";
	public static final String SAT_FAT = "Saturated Fat";
	public static final String TOTAL_FAT = "Total Fat";

	public static final double CHOLESTROL_SCORE = - 0.2;
	public static final double FIBER_SCORE = 0.2;
	public static final double PROTEIN_SCORE = 0.4;
	public static final double SAT_FAT_SCORE = -0.1;
	public static final double TOTAL_FAT_SCORE = -0.1;
	

}
