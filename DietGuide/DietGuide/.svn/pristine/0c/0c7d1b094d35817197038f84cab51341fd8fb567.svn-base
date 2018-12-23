package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;

import com.description.GetCategories;

public class FileChangeListener implements GetCategories {
	
	private String dirPath = "src/main/resources";
	private Path foodPreferencePath = Paths.get(dirPath);
	private Map<String, ArrayList<String>> foodCategories = new HashMap<String, ArrayList<String>>();
	private Map<String, ArrayList<String>> foodAllergies = new HashMap<String, ArrayList<String>>();
	private ArrayList<String> categories = new ArrayList<String>();
	
    URL url = this.getClass().getClassLoader().getResource("/data");
	static FileChangeListener listener;
	
	private FileChangeListener() {
		init();
	}
	
	public static FileChangeListener getInstance() {
		if(listener == null) {
			synchronized (FileChangeListener.class) {
				if(listener ==  null) {
					listener = new FileChangeListener();
				}
			}			
		}
		return listener;
	}

	

	public ArrayList<String> getCategories(String foodPreference) {
		return foodCategories.get(foodPreference);
	}

	public ArrayList<String> getAllergies(String foodPreference) {
		return foodAllergies.get(foodPreference);
	}

	private void init() {
		File dir = null;
		try {
			dir = new File(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		for (File file : dir.listFiles()) {
			updateArray(file);
		}
	}

	private void updateArray(File file) {
		ArrayList<String> foodArray = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine())!= null) {
					foodArray.add(line);	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(reader);
		}
		switch (file.getName() + ".txt") {
		case FileConstants.NON_VEG_ALLERGIES:
			foodAllergies.put(FileConstants.NON_VEG, foodArray);
			break;
		case FileConstants.VEG_ALLERGIES:
			foodAllergies.put(FileConstants.VEG, foodArray);
			break;
		case FileConstants.VEGAN_ALLERGIES:
			foodAllergies.put(FileConstants.VEGAN, foodArray);
			break;

		case FileConstants.NON_VEG_CATEGORIES:
			foodCategories.put(FileConstants.NON_VEG, foodArray);
			break;
		case FileConstants.VEG_CATEGORIES:
			foodCategories.put(FileConstants.VEG, foodArray);
			break;
		case FileConstants.VEGAN_CATEGORIES:
			foodCategories.put(FileConstants.VEGAN, foodArray);
			break;
		case FileConstants.CATEGORIES_IDS:
			System.out.println("Our case");
			System.out.println(foodArray);
			categories = new ArrayList<>(foodArray);
			break;
		}
	}

	private void fileChangeListener() {
		FileChangeListener listner = new FileChangeListener();
		try {
			WatchService services = FileSystems.getDefault().newWatchService();
			listner.foodPreferencePath.register(services, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY);
			WatchKey key;
			while ((key = services.take()) != null) {
				for (WatchEvent<?> event : key.pollEvents()) {
					System.out.println("File changed");
					System.out.println(event.context());
				}
				key.reset();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}
}