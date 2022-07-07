package Server;

import java.awt.Color;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

public class ServerFrame extends javax.swing.JFrame {
	private javax.swing.JButton btStart;
	private javax.swing.JButton btBrowse;
	private javax.swing.JLabel lbDatabase;
	private javax.swing.JScrollPane ScrollPane;
	public javax.swing.JTextArea textArea;
	private javax.swing.JTextField txtFile;
	public SocketServer server;
	public Thread serverThread;
	public String filePath = "D:\\Data.xml";
	public JFileChooser fileChooser;

	public ServerFrame() {
		initComponents();
		txtFile.setEditable(false);
		txtFile.setBackground(Color.WHITE);

		fileChooser = new JFileChooser();
		textArea.setEditable(false);
	}

	public boolean isWin32() {
		return System.getProperty("os.name").startsWith("Windows");
	}

	private void initComponents() {

		btStart = new javax.swing.JButton();
		ScrollPane = new javax.swing.JScrollPane();
		textArea = new javax.swing.JTextArea();
		lbDatabase = new javax.swing.JLabel();
		txtFile = new javax.swing.JTextField();
		btBrowse = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Server");

		btStart.setText("Start Server");
		btStart.setEnabled(false);
		btStart.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		textArea.setColumns(20);
		textArea.setFont(new java.awt.Font("Consolas", 0, 12)); 
		textArea.setRows(5);
		ScrollPane.setViewportView(textArea);

		lbDatabase.setText("Database File : ");

		btBrowse.setText("Browse...");
		btBrowse.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(ScrollPane)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
								.addComponent(lbDatabase)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(txtFile, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 91,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(btStart)))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(txtFile, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lbDatabase).addComponent(btBrowse).addComponent(btStart))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(ScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
						.addContainerGap()));

		pack();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		server = new SocketServer(this);
		btStart.setEnabled(false);
		btBrowse.setEnabled(false);
	}

	public void RetryStart(int port) {
		if (server != null) {
			server.stop();
		}
		server = new SocketServer(this, port);
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		fileChooser.showDialog(this, "Select");
		File file = fileChooser.getSelectedFile();

		if (file != null) {
			filePath = file.getPath();
			if (this.isWin32()) {
				filePath = filePath.replace("\\", "/");
			}
			txtFile.setText(filePath);
			btStart.setEnabled(true);
		}
	}

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			System.out.println("Look & Feel Exception");
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerFrame().setVisible(true);
			}
		});
	}



	
}
