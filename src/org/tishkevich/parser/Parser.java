package org.tishkevich.parser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.tishkevich.configuration.ProjectSettings;
import org.tishkevich.post.Post;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {
	private URL url;
	private int groupId;
	private String publicName;
	
	private DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

	public Parser(ProjectSettings ps, int num) {
		try {
			this.url = new URL(
					"https://api.vk.com/method/wall.get.xml?owner_id=" + ps.getUserArray().get(num) + "&count="
							+ ps.getPostsCount() + "&filter=all&extended=0&v=5.73&access_token=" + ps.getAccessToken());
			System.out.println(this.url);
			System.out.println("Access " + num + " done");
			groupId=ps.getUserArray().get(num);
			publicName=ps.getPublicNames().get(num);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTagValue(String tag, Element element) { // метод который по
																// названию тэга
																// выбирает
																// первый
		// подэлемент
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes(); // выбираем
																						// все
																						// подэлементы
																						// тэга
		if (nodeList.getLength() == 0 || ((Node) nodeList.item(0)).getNodeValue().equals(null)) {
			return " ";
		} else {
			return ((Node) nodeList.item(0)).getNodeValue();
		}

	}

	public Map<Integer, Post> updateMap(Map<Integer, Post> oldMap) {

		try {
			if (oldMap==null){
				oldMap=new TreeMap<>();
			}
			Document document = factory.newDocumentBuilder().parse(new BufferedInputStream(url.openStream()));
			Calendar dateReg = Calendar.getInstance();
			document.getDocumentElement().normalize();
			int tmpId = 0, tmpReposts = 0;
			String tmpDate = Calendar.getInstance().toString(), tmpText = "", tmpLikes = "", tmpComments = "";
			boolean isRepost = false;

			NodeList nodeList = document.getElementsByTagName("items").item(0).getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeName().equals("post")) {
					NodeList nod = nodeList.item(i).getChildNodes();
					for (int j = 0; j < nod.getLength(); j++) {
						String tmp = nod.item(j).getNodeName();

						switch (tmp) {
						case "id":
							tmpId = Integer.parseInt(nod.item(j).getFirstChild().getNodeValue());
							break;
						case "date":
							tmpDate = nod.item(j).getFirstChild().getNodeValue();
							break;
						case "text":
							//System.out.println(nod.item(j).hasChildNodes());
							if (nod.item(j).hasChildNodes()) {
								tmpText = nod.item(j).getFirstChild().getNodeValue();
							} else {
								tmpText = "Это фотография/репост";
							}
							break;
						case "post":
							isRepost = true;
							break;
						case "likes":
							Element element = (Element) nod.item(j);
							tmpLikes = getTagValue("count", element);
							break;
						case "comments":
							element = (Element) nod.item(j);
							tmpComments = getTagValue("count", element);
							break;
						case "reposts":
							element = (Element) nod.item(j);
							tmpReposts = Integer.parseInt(getTagValue("count", element));
							break;
						default:
							break;
						}
						if (j == nod.getLength() - 1) {
							if (oldMap.containsKey(tmpId)) {
								Post t = oldMap.get(tmpId);
								t.setDateReg(dateReg);
								t.setLikes(tmpLikes);
								t.setComments(tmpComments);
								t.setReposts(tmpReposts);
								t.setGroupId(groupId);
								t.setPublicName(publicName);

							} else {
								Post tPost = new Post();
								tPost.setDate(tmpDate);
								tPost.setId(tmpId);
								tPost.setDateReg(dateReg);
								tPost.isRepost = isRepost;
								tPost.setText(tmpText);
								tPost.setLikes(tmpLikes);
								tPost.setComments(tmpComments);
								tPost.setReposts(tmpReposts);
								tPost.setGroupId(groupId);
								tPost.setPublicName(publicName);
								if (tPost.isNeeded()) oldMap.put(tPost.getId(), tPost);
							}

						}
					}
				}
			}

		} catch (SAXException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return oldMap;

	}

	
}