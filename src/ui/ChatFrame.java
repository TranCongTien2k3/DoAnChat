package ui;

import Client.*;
import Object.Message;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.awt.SystemColor;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ChatFrame extends javax.swing.JFrame implements ActionListener {
	public javax.swing.JButton btConnect, btLogin, btSignUp, btSend, btFileDown, btSendFile, btChoose, btShow;

	private javax.swing.JLabel lbHost, lbHostPort, lbPass, lbUser, lbMess, lbFile, lbHis;

	public JList List;
	public javax.swing.JPasswordField txtPass;
	private javax.swing.JScrollPane SrcollPaneText;
	private javax.swing.JScrollPane ScrollPaneList;
	private javax.swing.JSeparator Separator1;
	public javax.swing.JTextArea textArea;

	public javax.swing.JTextField txtHost, txtHostPost, txtUser, txtMess, txtFile, txtHis;

	public SocketClient client;
	public int port;
	public String serverAddr, username, password;
	public Thread clientThread;
	public DefaultListModel model;
	public File file;
	public String historyFile = "D:\\History.xml";
	public HistoryFrame historyFrame;
	public History hist;

	public ChatFrame() {
		getContentPane().setBackground(SystemColor.activeCaption);
		initComponents();
		this.setTitle("Messenger");
		model.addElement("All");
		List.setSelectedIndex(0);

		txtHis.setEditable(false);

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				try {
					client.send(new Message("message", username, ".bye", "SERVER"));
					clientThread.stop();
				} catch (Exception ex) {
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}
		});

		hist = new History(historyFile);
	}

	public boolean isWin32() {
		return System.getProperty("os.name").startsWith("Windows");
	}

	private void initComponents() {

		lbHost = new javax.swing.JLabel();
		txtHost = new javax.swing.JTextField();
		lbHostPort = new javax.swing.JLabel();
		txtHostPost = new javax.swing.JTextField();
		btConnect = new javax.swing.JButton();
		txtUser = new javax.swing.JTextField();
		lbPass = new javax.swing.JLabel();
		lbUser = new javax.swing.JLabel();
		btSignUp = new javax.swing.JButton();
		txtPass = new javax.swing.JPasswordField();
		Separator1 = new javax.swing.JSeparator();
		SrcollPaneText = new javax.swing.JScrollPane();
		textArea = new javax.swing.JTextArea();
		ScrollPaneList = new javax.swing.JScrollPane();
		List = new javax.swing.JList();
		lbMess = new javax.swing.JLabel();
		txtMess = new javax.swing.JTextField();
		btSend = new javax.swing.JButton();
		btLogin = new javax.swing.JButton();
		txtFile = new javax.swing.JTextField();
		btFileDown = new javax.swing.JButton();
		btSendFile = new javax.swing.JButton();
		lbFile = new javax.swing.JLabel();
		lbHis = new javax.swing.JLabel();
		txtHis = new javax.swing.JTextField();
		btChoose = new javax.swing.JButton();
		btShow = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		lbHost.setText("Host Address : ");

		txtHost.setText("localhost");

		lbHostPort.setText("Host Port : ");

		txtHostPost.setText("13000");

		btConnect.setText("Connect");
		btConnect.addActionListener(this);

		txtUser.setText("Tien");
		txtUser.setEnabled(false);

		lbPass.setText("Password :");

		lbUser.setText("Username :");

		btSignUp.setText("SignUp");
		btSignUp.setEnabled(false);
		btSignUp.addActionListener(this);

		txtPass.setText("password");
		txtPass.setEnabled(false);

		textArea.setColumns(20);
		textArea.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
		textArea.setRows(5);
		SrcollPaneText.setViewportView(textArea);

		List.setModel((model = new DefaultListModel()));
		ScrollPaneList.setViewportView(List);

		lbMess.setText("Message : ");

		btSend.setText("Send Message ");
		btSend.setEnabled(false);
		btSend.addActionListener(this);

		btLogin.setText("Login");
		btLogin.setEnabled(false);
		btLogin.addActionListener(this);

		btFileDown.setText("...");
		btFileDown.setEnabled(false);
		btFileDown.addActionListener(this);

		btSendFile.setText("Send");
		btSendFile.setEnabled(false);
		btSendFile.addActionListener(this);

		lbFile.setText("File :");

		lbHis.setText("History File :");

		btChoose.setText("...");
		btChoose.setEnabled(false);
		btChoose.addActionListener(this);

		btShow.setText("Show");
		btShow.setEnabled(false);
		btShow.addActionListener(this);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup()
				.addGap(36)
				.addGroup(layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(Separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGroup(layout.createSequentialGroup().addGap(8).addGroup(layout
								.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(lbUser, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addComponent(lbHis, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(
										lbHost, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout
										.createSequentialGroup()
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtUser, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
												.addComponent(txtHost, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
										.addGap(18)
										.addGroup(layout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lbHostPort, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(layout.createSequentialGroup().addGap(1).addComponent(lbPass,
														GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addComponent(txtHostPost, GroupLayout.DEFAULT_SIZE, 126,
														Short.MAX_VALUE)
												.addComponent(txtPass, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
										.addComponent(txtHis, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(btConnect, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(layout.createSequentialGroup()
												.addGroup(layout.createParallelGroup(Alignment.TRAILING)
														.addComponent(btLogin, GroupLayout.PREFERRED_SIZE, 70,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btChoose, GroupLayout.PREFERRED_SIZE, 70,
																GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
														.addComponent(btShow, GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btSignUp, GroupLayout.DEFAULT_SIZE, 81,
																Short.MAX_VALUE)))))
						.addGroup(layout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(SrcollPaneText, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(ScrollPaneList,
										GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(12)
								.addComponent(lbFile, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtFile, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE).addGap(18)
								.addComponent(btFileDown, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btSendFile, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup()
								.addComponent(lbMess, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtMess, GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE).addGap(18)
								.addComponent(btSend, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(Alignment.LEADING)
						.addGroup(
								layout.createSequentialGroup().addContainerGap()
										.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbHost)
												.addComponent(txtHost, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lbHostPort)
												.addComponent(txtHostPost, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btConnect))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE)
												.addComponent(txtUser, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lbPass).addComponent(lbUser).addComponent(btSignUp)
												.addComponent(txtPass, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btLogin))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lbHis)
												.addComponent(txtHis, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btChoose).addComponent(btShow))
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(Separator1, GroupLayout.PREFERRED_SIZE, 10,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addGroup(layout.createParallelGroup(Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(ScrollPaneList, GroupLayout.DEFAULT_SIZE, 264,
																Short.MAX_VALUE)
														.addGap(4))
												.addComponent(SrcollPaneText, GroupLayout.DEFAULT_SIZE, 268,
														Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(btSend)
												.addComponent(lbMess)
												.addComponent(txtMess, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(30)
										.addGroup(layout.createParallelGroup(Alignment.BASELINE, false)
												.addComponent(btSendFile, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btFileDown, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lbFile).addComponent(txtFile, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		getContentPane().setLayout(layout);

		pack();
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			System.out.println("Look & Feel exception");
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ChatFrame().setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btChoose) {
			JFileChooser jf = new JFileChooser();
			jf.showDialog(this, "Select File");

			if (!jf.getSelectedFile().getPath().isEmpty()) {
				historyFile = jf.getSelectedFile().getPath();
				if (this.isWin32()) {
					historyFile = historyFile.replace("/", "\\");
				}
				txtHis.setText(historyFile);
				txtHis.setEditable(false);
				btChoose.setEnabled(false);
				btShow.setEnabled(true);
				hist = new History(historyFile);

				historyFrame = new HistoryFrame(hist);
				historyFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				historyFrame.setVisible(false);
			}
		} else if (e.getSource() == btConnect) {
			serverAddr = txtHost.getText();
			port = Integer.parseInt(txtHostPost.getText());

			if (!serverAddr.isEmpty() && !txtHostPost.getText().isEmpty()) {
				try {
					client = new SocketClient(this);
					clientThread = new Thread(client);
					clientThread.start();
					client.send(new Message("test", "testUser", "testContent", "SERVER"));
				} catch (Exception ex) {
					textArea.append("[Application > Me] : Server not found\n");
				}
			}
		} else if (e.getSource() == btFileDown) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.showDialog(this, "Select File");
			file = fileChooser.getSelectedFile();

			if (file != null) {
				if (!file.getName().isEmpty()) {
					btSendFile.setEnabled(true);
					String str;

					if (txtFile.getText().length() > 30) {
						String t = file.getPath();
						str = t.substring(0, 20) + " [...] " + t.substring(t.length() - 20, t.length());
					} else {
						str = file.getPath();
					}
					txtFile.setText(str);

				}
			}
		} else if (e.getSource() == btLogin) {
			username = txtUser.getText();
			password = txtPass.getText();

			if (!username.isEmpty() && !password.isEmpty()) {
				client.send(new Message("login", username, password, "SERVER"));
			}
		} else if (e.getSource() == btShow) {
			historyFrame.setLocation(this.getLocation());
			historyFrame.setVisible(true);
		} else if (e.getSource() == btSend) {
			String msg = txtMess.getText();
			String target = List.getSelectedValue().toString();

			if (!msg.isEmpty() && !target.isEmpty()) {
				txtMess.setText("");
				client.send(new Message("message", username, msg, target));
			}
		} else if (e.getSource() == btSignUp) {
			username = txtUser.getText();
			password = txtPass.getText();

			if (!username.isEmpty() && !password.isEmpty()) {
				client.send(new Message("signup", username, password, "SERVER"));
			}
		} else if (e.getSource() == btSendFile) {
			long size = file.length();
			if (size < 120 * 1024 * 1024) {
				client.send(new Message("upload_req", username, file.getName(), List.getSelectedValue().toString()));
			} else {
				textArea.append("[Application > Me] : File is size too large\n");
			}
		}

	}

}
