package com.model.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.common.utils.CommonUtils;
import com.controller.Keys;
import com.google.gson.internal.LinkedTreeMap;

@SuppressWarnings("rawtypes")
public class ProductInfo {

	private String productName;
	private String imageUrl;
	private String sku;
	private float price;
	private List ingredients;
	private List nutrients;
	private double nutritionIndex;
	private boolean isCornFree;
	private boolean isLactoovoVegetarian;
	private boolean isFairtrade;

	public void loadOtherData() {
		getImageUrl();
		getPrice();
		getProductName();
		calculateScore();
	}

	private void calculateScore() {
		double totalVal = 0.0;
		for (Object object : nutrients) {
			Collection a = (((LinkedTreeMap<String, String>) object).values());
			int i = 0;
			String key = null;
			double val = 0;
			for (Object object2 : a) {
				if (i == 0) {
					key = (String) object2;
				} else if (i == 1) {
					val = Integer.parseInt(((String) object2).split(" ")[0]);
				} else {
					break;
				}
				i += 1;
			}
			if (key.contains(Keys.CHOLESTROL)) {
				totalVal += (val * Keys.CHOLESTROL_SCORE);
			}
			else if (key.contains(Keys.FIBER)) {
				totalVal += (val * Keys.FIBER_SCORE);
			}
			else if (key.contains(Keys.PROTEIN)) {
				totalVal += (val * Keys.PROTEIN_SCORE);
			}
			else if (key.contains(Keys.SAT_FAT)) {
				totalVal += (val * Keys.SAT_FAT_SCORE);
			}
			else if (key.contains(Keys.TOTAL_FAT)) {
				totalVal += (val * Keys.TOTAL_FAT_SCORE);
			}
		}
		nutritionIndex = Math.round(totalVal);
	}

	public List getIngredients() {
		return ingredients;
	}

	public void setIngredients(List ingredients) {
		this.ingredients = ingredients;
	}

	public List getNutrients() {
		return nutrients;
	}

	public void setNutrients(ArrayList nutrients) {
		this.nutrients = nutrients;
	}

	private List tradeIdentifiers;
	private LinkedTreeMap descriptions;

	public List getTradeIdentifiers() {
		return tradeIdentifiers;
	}

	public void setTradeIdentifiers(List tradeIdentifiers) {
		this.tradeIdentifiers = tradeIdentifiers;
	}

	public String getImageUrl() {
		if (imageUrl == null) {
			LinkedTreeMap imageUrlMap = (LinkedTreeMap) tradeIdentifiers.get(0);
			imageUrl = (String) ((List) imageUrlMap.get("images")).get(0);
		}
		return imageUrl;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public Object getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(LinkedTreeMap descriptions) {
		this.descriptions = descriptions;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public float getPrice() {
		if (price == 0) {
			price = getPrice(sku);
		}
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProductName() {
		if (productName == null) {
			productName = (String) (descriptions.get("consumer"));
		}
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getNutritionIndex() {
		return nutritionIndex;
	}

	public void setNutritionIndex(double nutritionIndex) {
		this.nutritionIndex = nutritionIndex;
	}

	public boolean isCornFree() {
		return isCornFree;
	}

	public void setCornFree(boolean isCornFree) {
		this.isCornFree = isCornFree;
	}

	public boolean isLactoovoVegetarian() {
		return isLactoovoVegetarian;
	}

	public void setLactoovoVegetarian(boolean isLactoovoVegetarian) {
		this.isLactoovoVegetarian = isLactoovoVegetarian;
	}

	public boolean isFairtrade() {
		return isFairtrade;
	}

	public void setFairtrade(boolean isFairtrade) {
		this.isFairtrade = isFairtrade;
	}

	public float getPrice(String sku) {
		String p = CommonUtils.fetchJsonDataWithoutCompression(String.format(Keys.PRODUCT_PRICE_URL, sku));
		String[] array = p.split(":");
		String op = array[4].split(",")[0];
		return Float.parseFloat(op);
	}
}