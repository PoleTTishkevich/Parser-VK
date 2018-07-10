package org.tishkevich.post;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
	private int repostId;
	private ArrayList<Calendar> dateReg;
	private Calendar date;
	private int id;
	private ArrayList<String> likes;
	private ArrayList<String> comments;
	public boolean isRepost = false;
	private String publicName;

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	private int reposts;
	private String text = "";

	public ArrayList<Calendar> getDateReg() {
		return dateReg;
	}

	public Calendar getDate() {
		return date;
	}

	public Post() {
		dateReg = new ArrayList<>();
		date = Calendar.getInstance();
		likes = new ArrayList<>();
		comments = new ArrayList<>();
		repostId = 0;
	}

	public int getRepostId() {
		return repostId;
	}

	public void setRepostId(int repostId) {
		this.repostId = repostId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setDate(String date) {
		this.date.setTimeInMillis(Long.valueOf(date + "000"));
	}

	public void setDateReg(String date) {
		Calendar tmp = Calendar.getInstance();
		tmp.setTimeInMillis(Long.valueOf(date + "000"));
		this.dateReg.add(tmp);
	}

	public void setDateReg(Calendar date) {
		this.dateReg.add(date);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<String> getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes.add(likes);
	}

	public ArrayList<String> getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments.add(comments);
	}

	public int getReposts() {
		return reposts;
	}

	public void setReposts(int reposts) {
		this.reposts = reposts;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isNeeded() {
		return (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == (date.get(Calendar.DAY_OF_MONTH))
				&& Calendar.getInstance().get(Calendar.MONTH) == (date.get(Calendar.MONTH)));
	}

	public String printSimpleText() {
		if (this.text.trim().length() == 0) {
			this.text = " \u0424\u043e\u0442\u043e/\u0440\u0435\u043f\u043e\u0441\u0442;";
		} else if (this.isRepost) {
			this.text = " \u0420\u0435\u043f\u043e\u0441\u0442 \u043d\u043e\u0432\u043e\u0441\u0442\u0438:" + this.text;
		}
		if (this.text.length() > 56) {
			return " " + this.text.substring(0, 56) + "...<BR>"
					+ "  \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043b\u0430\u0439\u043a\u043e\u0432: "
					+ this.likes.get(this.likes.size() - 1)
					+ ", \u043a\u043e\u043c\u043c\u0435\u043d\u0442\u0430\u0440\u0438\u0435\u0432: "
					+ this.comments.get(this.comments.size() - 1)
					+ ", \u0440\u0435\u043f\u043e\u0441\u0442\u043e\u0432: " + this.getReposts();
		}
		return " " + this.text
				+ "<BR>  \u043a\u043e\u043b\u0438\u0447\u0435\u0441\u0442\u0432\u043e \u043b\u0430\u0439\u043a\u043e\u0432: "
				+ this.likes.get(this.likes.size() - 1)
				+ ", \u043a\u043e\u043c\u043c\u0435\u043d\u0442\u0430\u0440\u0438\u0435\u0432: "
				+ this.comments.get(this.comments.size() - 1) + ", \u0440\u0435\u043f\u043e\u0441\u0442\u043e\u0432: "
				+ this.getReposts();
	}

	public String printDate() {

		SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");
		String formatted = formatDate.format(date.getTime());
		return formatted;
	}

	public void printInfo() {
		if (getId() != 0) {
			System.out.println("Information: id=" + getId() + ", date=" + printDate() + ", text=" + printSimpleText());
			System.out.println("reposts=" + getReposts() + " " + isRepost);
		}

	}

}
