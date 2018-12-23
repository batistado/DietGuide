package com.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model.bean.ProductInfo;

public class QueryProcessor {
	private int defaultSize = 10;
	private List<ProductInfo> finalProductList;
	private int budgetNow;
	int iteration = 0;

	public List<Map<String, List<ProductInfo>>>  processQuery(List<String> categories, List<String> allergies, double budget) {
		List<Map<String, List<ProductInfo>>> final1 = new ArrayList<Map<String, List<ProductInfo>>>();
		Map<String, List<ProductInfo>> m;
		int n = categories.size();
		if (n == 0) {
			categories = ProductRanking.getInstance().getAllCategories();
			n = categories.size();
		}

		int numberToPick = defaultSize / n;

		List<ProductInfo> productList;
		int j = 5;
		iteration = 0;
		while (j > 0) {
			finalProductList = new ArrayList<ProductInfo>();
			m = new HashMap<String,List<ProductInfo>>();
			for (String category : categories) {
				productList = ProductRanking.getInstance().getList(category);
				for (int i = iteration; i < numberToPick && i < productList.size(); i++) {
					ProductInfo info = productList.get(i);
					float price = info.getPrice();
					if (price + budgetNow < budget) {
						budgetNow += price;
					}
					finalProductList.add(info);
				}
			}
			if(finalProductList.size()>0) {
				m.put("productsList", finalProductList);
				final1.add(m);				
			}
			j--;
			iteration+= numberToPick-1;
			numberToPick += (defaultSize / n);
		}		
		return final1;
	}
}