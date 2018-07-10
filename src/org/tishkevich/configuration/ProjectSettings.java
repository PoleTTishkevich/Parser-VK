package org.tishkevich.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProjectSettings {
	private String accessToken;
	private List<Integer> userArray;
	private int postsCount;
	private Properties prop;
	private int minutes;
	private List<String> nameArray;
	private List<String> publicNames;

	public List<String> getPublicNames() {
		return publicNames;
	}

	public List<String> getNameArray() {
		return nameArray;
	}

	private static final ProjectSettings ps = new ProjectSettings();

	public static ProjectSettings getInstance() {

		return ps;
	}

	private ProjectSettings() {
		userArray = new ArrayList<>();
		prop = new Properties();
		nameArray = new ArrayList<>();
		publicNames = new ArrayList<>();
		try {
			prop.load(new FileInputStream(new File("config/config.properties")));

			this.accessToken = prop.getProperty("accessToken");
			System.out.println(this.accessToken);
			for (String str : prop.getProperty("userArray").split(";")) {
				userArray.add(Integer.valueOf(str));
			}
			for (String str : prop.getProperty("namesArray").split(";")) {
				nameArray.add(str);
			}
			for (String str : prop.getProperty("publicNames").split(";")) {
				publicNames.add(str);
			}
			this.postsCount = Integer.valueOf(prop.getProperty("postsCount"));
			this.minutes = Integer.valueOf(prop.getProperty("minutes"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAccessToken() {
		return accessToken;
	}

	public List<Integer> getUserArray() {
		return userArray;
	}

	public int getPostsCount() {
		return postsCount;
	}

	public int getMinutes() {
		return minutes;
	}

}
