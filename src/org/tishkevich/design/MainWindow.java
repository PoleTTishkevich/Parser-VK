package org.tishkevich.design;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.tishkevich.configuration.ProjectSettings;

public class MainWindow extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TrayIcon trayIcon;
	SystemTray tray;
	PopupMenu popup;
	Image image;
	Calendar date;
	

	/**
	 * Creates new form НовыйApplication
	 */
	public MainWindow() {
		setTitle(
				"\u041D\u043E\u0432\u043E\u0441\u0442\u0438 \u043E\u0434\u043D\u043E\u0439 \u0441\u0442\u0440\u043E\u043A\u043E\u0439");
		setResizable(false);
		try {
			System.out.println("setting look and feel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Unable to set LookAndFeel");
		}
		if (SystemTray.isSupported()) {
			System.out.println("system tray supported");
			tray = SystemTray.getSystemTray();

			image = Toolkit.getDefaultToolkit().getImage("config/hide.png");
			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting....");
					System.exit(0);
				}
			};
			popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem = new MenuItem("Open");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			popup.add(defaultItem);
			trayIcon = new TrayIcon(image, "Parser", popup);
			trayIcon.setImageAutoSize(true);
			tray.remove(trayIcon);
		} else {
			System.out.println("system tray not supported");
		}
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == ICONIFIED) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to tray");
					}
				}
				if (e.getNewState() == 7) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to system tray");
					}
				}
				if (e.getNewState() == MAXIMIZED_BOTH) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
				if (e.getNewState() == NORMAL) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
			}
		});
		setIconImage(image);
		//setMinimumSize(new Dimension(1000, 800));
		//setPreferredSize(new Dimension(1000, 800));
		setBounds(700, 50, 800, 650);
		initComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public MainWindow(Calendar date) {
		this.date=date;
		setTitle(
				"\u041D\u043E\u0432\u043E\u0441\u0442\u0438 \u043E\u0434\u043D\u043E\u0439 \u0441\u0442\u0440\u043E\u043A\u043E\u0439");
		setResizable(false);
		try {
			System.out.println("setting look and feel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Unable to set LookAndFeel");
		}
		if (SystemTray.isSupported()) {
			System.out.println("system tray supported");
			tray = SystemTray.getSystemTray();

			image = Toolkit.getDefaultToolkit().getImage("config/hide.png");
			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Exiting....");
					System.exit(0);
				}
			};
			popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem = new MenuItem("Open");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			popup.add(defaultItem);
			trayIcon = new TrayIcon(image, "Parser", popup);
			trayIcon.setImageAutoSize(true);
			tray.remove(trayIcon);
		} else {
			System.out.println("system tray not supported");
		}
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == ICONIFIED) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to tray");
					}
				}
				if (e.getNewState() == 7) {
					try {
						tray.add(trayIcon);
						setVisible(false);
						System.out.println("added to SystemTray");
					} catch (AWTException ex) {
						System.out.println("unable to add to system tray");
					}
				}
				if (e.getNewState() == MAXIMIZED_BOTH) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
				if (e.getNewState() == NORMAL) {
					tray.remove(trayIcon);
					setVisible(true);
					System.out.println("Tray icon removed");
				}
			}
		});
		setIconImage(image);

		setBounds(700, 50, 800, 650);
		//setMinimumSize(new Dimension(1000, 800));
		//setPreferredSize(new Dimension(1000, 800));
		initDateComponents();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		menuBar = new javax.swing.JMenuBar();
		menuBar.setToolTipText(
				"\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C \u0434\u0430\u043D\u043D\u044B\u0435/\u043E\u0442\u043A\u0440\u044B\u0442\u044C \u0438\u0441\u0442\u043E\u0440\u0438\u044E");
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		refreshMenuItem = new javax.swing.JMenuItem();

		fileMenu.setMnemonic('f');
		fileMenu.setText("Функции");

		openMenuItem.setMnemonic('o');
		openMenuItem.setText("Открыть");
		fileMenu.add(openMenuItem);

		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogPanel(MainWindow.this).setVisible(true);

			}
		});

		refreshMenuItem.setMnemonic('r');
		refreshMenuItem.setText("Обновить");
		refreshMenuItem.setVisible(true);
		refreshMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainWindow().setVisible(true);

			}
		});
		fileMenu.add(refreshMenuItem);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 575, 590);
		getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		panel_3.add(tabbedPane);

		ProjectSettings ps = ProjectSettings.getInstance();
		List<String> tmp = ps.getNameArray();
		Border solid = BorderFactory.createRaisedBevelBorder();
		for (int i = 0; i < tmp.size(); i++) {

			MyJPanel panel = new MyJPanel(i);
			panel.setBorder(solid);
			JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setMinimumSize(new Dimension(160, 200));
            scrollPane.setPreferredSize(new Dimension(160, 200));


			tabbedPane.addTab(tmp.get(i), null, scrollPane, null);

		}

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(400, 600,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(400, 600,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	private void initDateComponents() {

		menuBar = new javax.swing.JMenuBar();
		menuBar.setToolTipText(
				"\u041E\u0431\u043D\u043E\u0432\u0438\u0442\u044C \u0434\u0430\u043D\u043D\u044B\u0435/\u043E\u0442\u043A\u0440\u044B\u0442\u044C \u0438\u0441\u0442\u043E\u0440\u0438\u044E");
		fileMenu = new javax.swing.JMenu();
		openMenuItem = new javax.swing.JMenuItem();
		refreshMenuItem = new javax.swing.JMenuItem();

		fileMenu.setMnemonic('f');
		fileMenu.setText("Функции");

		openMenuItem.setMnemonic('o');
		openMenuItem.setText("Открыть");
		fileMenu.add(openMenuItem);

		openMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new DialogPanel(MainWindow.this).setVisible(true);

			}
		});

		refreshMenuItem.setMnemonic('r');
		refreshMenuItem.setText("Обновить");
		refreshMenuItem.setVisible(false);
		refreshMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new MainWindow().setVisible(true);

			}
		});
		fileMenu.add(refreshMenuItem);

		menuBar.add(fileMenu);

		setJMenuBar(menuBar);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 575, 590);

		getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(0, 1, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		panel_3.add(tabbedPane);

		ProjectSettings ps = ProjectSettings.getInstance();
		List<String> tmp = ps.getNameArray();
		Border solid = BorderFactory.createRaisedBevelBorder();
		for (int i = 0; i < tmp.size(); i++) {

			MyJPanel panel = new MyJPanel(i, this.date);
			panel.setBorder(solid);
			JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
					JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setMinimumSize(new Dimension(160, 200));
			scrollPane.setPreferredSize(new Dimension(160, 200));

			tabbedPane.addTab(tmp.get(i), null, scrollPane, null);

		}

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(400, 600,
				Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(400, 600,
				Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		MainWindow mw = new MainWindow();
		mw.tray.remove(mw.trayIcon);
		mw.setVisible(true);
		while (true) {
			try {

				Thread.sleep(1800000);
				mw.tray.remove(mw.trayIcon);
				mw = new MainWindow();
				mw.setVisible(true);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Variables declaration - do not modify

	private javax.swing.JMenu fileMenu;

	private javax.swing.JMenuBar menuBar;
	private javax.swing.JMenuItem openMenuItem;

	private javax.swing.JMenuItem refreshMenuItem;
	// End of variables declaration

}
