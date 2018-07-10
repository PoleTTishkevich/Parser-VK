package org.tishkevich.design;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

public class DialogPanel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JTextField textField = new JTextField();
	MainWindow mw;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public DialogPanel(MainWindow mw) {
		this.mw = mw;
		setTitle("\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0434\u0430\u0442\u0443");
		setResizable(false);
		setBounds(100, 100, 393, 70);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			textField.setToolTipText("\u0414\u0430\u0442\u0430");
			textField.setText("dd.mm.yyyy");
			buttonPane.add(textField);
			textField.setColumns(30);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String[] arr = DialogPanel.this.textField.getText().split("\\D");
						try {
							Calendar date = Calendar.getInstance();
							date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(arr[0]));
							date.set(Calendar.MONTH, Integer.parseInt(arr[1]));
							date.set(Calendar.YEAR, Integer.parseInt(arr[2]));
							mw.setVisible(false);
							Calendar tmpDate = Calendar.getInstance();
							if (date.get(Calendar.DAY_OF_MONTH) == tmpDate.get(Calendar.DAY_OF_MONTH)
									&& date.get(Calendar.MONTH) == (tmpDate.get(Calendar.MONTH)+1)
									&& date.get(Calendar.YEAR) == tmpDate.get(Calendar.YEAR)) {
								(new MainWindow()).setVisible(true);
							} else {
								(new MainWindow(date)).setVisible(true);
							}

							setVisible(false);

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(DialogPanel.this,
									"Дата введена неправильно, необходимо ввести ДД.ММ.ГГГГ");
						}
					}
				});
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);

					}
				});
				buttonPane.add(cancelButton);
			}
		}
	}
}
