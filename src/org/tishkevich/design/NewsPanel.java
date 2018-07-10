package org.tishkevich.design;

import java.awt.Button;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;



import org.tishkevich.post.Post;

public class NewsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Post post;


	/**
	 * Create the panel.
	 */
	public NewsPanel(JPanel panel, Post post, int i) {
		setPreferredSize(new Dimension(950,790));
		setMaximumSize(new Dimension(950,790));
		this.post = post;
		Post t = new Post();
		if (!t.equals(post)) {
		JButton label = new JButton("<HTML> " + this.post.printSimpleText()+"</HTML>");
		label.setBackground(UIManager.getColor("Button.background"));
		Border solidBorder = BorderFactory.createRaisedBevelBorder(); 
		label.setBorder(solidBorder);
		label.setFocusable(false);
		label.setHorizontalAlignment (SwingConstants.LEFT);
		label.setMargin(new Insets(1, 1, 1, 1));
		label.setHorizontalTextPosition(AbstractButton.LEFT);
		label.setPreferredSize(new Dimension(440, 37));
        label.setBounds(0, 11 + i, 508, 37);
		label.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					URL url =new URL("https://vk.com/"+
							post.getPublicName() + "?w=wall" + post.getGroupId() + "_" + post.getId()); 
					openWebpage(url);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				};
				
			
		});
		panel.add(label);
		Button button = new Button("\u0418\u0441\u0442\u043e\u0440\u0438\u044f");
        button.setBackground(UIManager.getColor("Button.background"));
       // label.setLabelFor(button);
        button.setBounds(510, 11 + i, 45, 37);
        button.setPreferredSize(new Dimension(90, 37));
        DiagramFrame frame = new DiagramFrame(post);
        button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				
				};
				
			
		});
        panel.add(button);
        this.setLayout(null);
    }

		
		}
	
	
	public boolean openWebpage(URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    return false;
	}

	public boolean openWebpage(URL url) {
	    try {
	        return openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
