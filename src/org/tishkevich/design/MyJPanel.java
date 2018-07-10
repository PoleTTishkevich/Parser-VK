package org.tishkevich.design;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.tishkevich.configuration.ProjectSettings;
import org.tishkevich.files.FileWorker;
import org.tishkevich.parser.Parser;
import org.tishkevich.post.Post;

public class MyJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectSettings ps;
	Map<Integer, Post> curMap;

	/**
	 * Create the panel.
	 */
	public MyJPanel(Integer num) {
		ps = ProjectSettings.getInstance();
		curMap = new TreeMap<>();
		Parser p = new Parser(ps, num);
		Post[] postArray = new Post[0];
		try {
			FileWorker fw = new FileWorker(ps.getUserArray().get(num));
			curMap = p.updateMap(fw.getData());
			fw.save(curMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		if (curMap.keySet().size() == 0) {
			JLabel label = new JLabel("<HTML> Актуальных новостей нет</HTML>");
			this.add(label);
			label.setVerticalAlignment(SwingConstants.TOP);
			label.setBounds(200, 11 + i, 437, 37);

		} else {

			int size = curMap.keySet().size();

			postArray = new Post[size];
			int j = 0;
			for (Integer tmp : curMap.keySet()) {
				postArray[j++] = curMap.get(tmp);
			}

			for (int a = 0; a < size; a++) {
				for (int b = 0; b < size; b++) {
					List<String> listA = postArray[a].getLikes();
					List<String> listB = postArray[b].getLikes();
					int tmpA = Integer.parseInt(listA.get(listA.size() - 1));
					int tmpB = Integer.parseInt(listB.get(listB.size() - 1));
					if (tmpA > tmpB) {
						Post tmpPost = postArray[a];
						postArray[a] = postArray[b];
						postArray[b] = tmpPost;
					}

				}
			}

		}
		for (Post tmp : postArray) {
			new NewsPanel(this, tmp, i);

			i += 42;

		}
		setPreferredSize(new Dimension(570, i + 10));
		setLayout(new FlowLayout());

	}

	public MyJPanel(Integer num, Calendar date) {
		ps = ProjectSettings.getInstance();
		curMap = new TreeMap<>();
		Post[] postArray = new Post[0];
		try {
			FileWorker fw = new FileWorker(ps.getUserArray().get(num));
			curMap = fw.getDataByDate(date);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int i = 0;
		if (curMap.keySet().size() == 0) {
			JLabel label = new JLabel("<HTML> Актуальных новостей нет</HTML>");
			this.add(label);
			label.setVerticalAlignment(SwingConstants.TOP);
			label.setBounds(200, 11 + i, 437, 37);
		} else {
			int size = curMap.keySet().size();
			postArray = new Post[size];
			int j = 0;
			for (Integer tmp : curMap.keySet()) {
				postArray[j++] = curMap.get(tmp);
			}

			for (int a = 0; a < size; a++) {
				for (int b = 0; b < size; b++) {
					List<String> listA = postArray[a].getLikes();
					List<String> listB = postArray[b].getLikes();
					int tmpA = Integer.parseInt(listA.get(listA.size() - 1));
					int tmpB = Integer.parseInt(listB.get(listB.size() - 1));
					if (tmpA > tmpB) {
						Post tmpPost = postArray[a];
						postArray[a] = postArray[b];
						postArray[b] = tmpPost;
					}
				}
			}

		}
		for (Post tmp : postArray) {
			new NewsPanel(this, tmp, i);

			i += 42;

		}

		setPreferredSize(new Dimension(570, i + 10));
		// setMaximumSize(new Dimension(570,1000));
		setLayout(new FlowLayout());

	}

}
