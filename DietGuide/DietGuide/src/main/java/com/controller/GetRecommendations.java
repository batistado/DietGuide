package com.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.monitor.FileAlterationListener;

import com.google.gson.Gson;
import com.model.FileChangeListener;
import com.model.QueryProcessor;
import com.model.bean.FoodOptions;
import com.model.bean.ProductInfo;

public class GetRecommendations extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();
		FileChangeListener.getInstance();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		String line = null;
		StringBuilder builder = new StringBuilder();
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		Gson gson = new Gson();
		FoodOptions option = gson.fromJson(builder.toString(), FoodOptions.class);

		PrintWriter writer = response.getWriter();
		response.setContentType("json");
		Gson gson2 = new Gson();
		String output = gson2.toJson(new QueryProcessor().processQuery(option.getFoodCategories(), option.getFoodAllergies(),
				option.getBudgetRange().get(1)));
		writer.append(output);
		writer.flush();
	}
}
