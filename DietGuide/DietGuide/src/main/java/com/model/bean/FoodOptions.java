package com.model.bean;

import java.util.List;

public class FoodOptions {

	private List<String> foodAllergies;
	private List<String> foodCategories;
	private int calories;
	private List<Integer> budgetRange;
	private String foodType;
	
	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	public List<Integer> getBudgetRange() {
		return budgetRange;
	}

	public void setBudgetRange(List<Integer> budgetRange) {
		this.budgetRange = budgetRange;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public List<String> getFoodAllergies() {
		return foodAllergies;
	}

	public void setFoodAllergies(List<String> foodAllergies) {
		this.foodAllergies = foodAllergies;
	}

	public List<String> getFoodCategories() {
		return foodCategories;
	}

	public void setFoodCategories(List<String> foodCategories) {
		this.foodCategories = foodCategories;
	}
}
