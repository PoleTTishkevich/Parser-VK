package org.tishkevich.design;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;

import org.jfree.chart.renderer.category.LineAndShapeRenderer;

import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import org.tishkevich.post.Post;

public class DiagramFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Post post;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */

	public DiagramFrame(Post post) {
		this.post = post;
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		JPanel panel = new ChartPanel(createChart(createDataset1()));
		this.add(panel);
		setVisible(false);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

	}

	private JFreeChart createChart(CategoryDataset dataset) {
		final JFreeChart chart = ChartFactory.createLineChart("Динамика активности за день", // chart
																									// title
				null, // domain axis label
				"Количество", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
		);

		chart.setBackgroundPaint(Color.white);

		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);

		LineAndShapeRenderer renderer;
		renderer = (LineAndShapeRenderer) plot.getRenderer();

		renderer.setSeriesStroke(0, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
				new float[] { 10.0f, 6.0f }, 0.0f));
		renderer.setSeriesStroke(1, new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
				new float[] { 6.0f, 6.0f }, 0.0f));
		return chart;
	}

	/*
	 * void add(RegularTimePeriod period, double value)
	 */
	public CategoryDataset createDataset1() {
		DefaultCategoryDataset dataset;

		final String series1 = "Лайки";
		final String series2 = "Комментарии";
		List<Calendar> cal = post.getDateReg();
		List<String> likes = post.getLikes();
		List<String> comments = post.getComments();
		dataset = new DefaultCategoryDataset();
		for (int i = 0; i < cal.size(); i++) {
			SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm");
			String formatted = formatDate.format(cal.get(i).getTime());
			dataset.addValue(Double.valueOf(likes.get(i)), series1, formatted);
			dataset.addValue(Double.valueOf(comments.get(i)), series2, formatted);

		}

		return dataset;
	}

}
