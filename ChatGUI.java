import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;


/*
 * The Client with its GUI
 */
public class ChatGUI extends javax.swing.JFrame {

	private HashMap<String, ChatClient> activeUsers = new HashMap<String, ChatClient>();
	private int defaultPort;
	private String defaultHost;
	
	private int requiredUsernameLength = 3; // Usernames must be at least this long
	private String thisUser; // Saved username after a user is connected
	
	private boolean thisUserConnected = false; // Is there a user connected in this GUI?
	
	private static final long serialVersionUID = 1L;
	// the Client object
	private ChatClient client;
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JTextArea activeMessagesArea;
    private javax.swing.JScrollPane activeMessagesPane;
    private javax.swing.JTextArea activeUsersArea;
    private javax.swing.JLabel activeUsersLabel;
    private javax.swing.JScrollPane activeUsersPane;
    private javax.swing.JButton connectButton;
    private javax.swing.JButton disconnectButton;
    private javax.swing.JButton sendButton;
    private javax.swing.JTextArea userMessageArea;
    private javax.swing.JScrollPane userMessagePane;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables


	// Constructor connection receiving a socket number
	ChatGUI() {

		initComponents();

	}

	
	
	
	// called by the Client to append text in the TextArea 
	void append(String str) {
		activeMessagesArea.append(str + "\n");		
	}
		
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        connectButton = new javax.swing.JButton();
        disconnectButton = new javax.swing.JButton();
        activeUsersLabel = new javax.swing.JLabel();
        activeUsersPane = new javax.swing.JScrollPane();
        activeUsersArea = new javax.swing.JTextArea();
        activeMessagesPane = new javax.swing.JScrollPane();
        activeMessagesArea = new javax.swing.JTextArea();
        userMessagePane = new javax.swing.JScrollPane();
        userMessageArea = new javax.swing.JTextArea();
        sendButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        usernameLabel.setText("Username");

        usernameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameFieldActionPerformed(evt);
            }
        });

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        disconnectButton.setText("Disconnect");
        disconnectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disconnectButtonActionPerformed(evt);
            }
        });

        activeUsersLabel.setText("Active Users");

        activeUsersArea.setEditable(false);
        activeUsersArea.setColumns(15);
        activeUsersArea.setRows(5);
        activeUsersArea.setAutoscrolls(false);
        activeUsersPane.setViewportView(activeUsersArea);

        activeMessagesArea.setEditable(false);
        activeMessagesArea.setColumns(20);
        activeMessagesArea.setLineWrap(true);
        activeMessagesArea.setRows(5);
        activeMessagesPane.setViewportView(activeMessagesArea);

        userMessageArea.setColumns(20);
        userMessageArea.setRows(5);
        userMessagePane.setViewportView(userMessageArea);

        sendButton.setText("Send");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(activeUsersPane, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(activeUsersLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(activeMessagesPane, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userMessagePane, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usernameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connectButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(disconnectButton)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(activeUsersLabel)
                    .addComponent(usernameLabel)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectButton)
                    .addComponent(disconnectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(activeMessagesPane, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(userMessagePane, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                            .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(activeUsersPane, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
	
	
    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
    	client.sendMessage(userMessageArea.getText());
    	userMessageArea.setText("");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        
        // Make sure user isn't already connected
        if ( !thisUserConnected ) {
        
            // Check for username length > requiredUsernameLength
            if ( usernameField.getText().length() >= requiredUsernameLength ) {
            	String username = usernameField.getText();
            	int port = 5554;
            	String server = "localhost";
                // Create client
               client = new ChatClient(server, port, username, this);
               try {
   				if(!client.start()) 
   					return;
   			} catch (IOException e1) {
   				// TODO Auto-generated catch block
   				e1.printStackTrace();
   			}
               
                // Check for successful client run
                if (client.running) {
                    
                        // Username length > required length, so add user to activeUsers list
                        activeUsers.put( usernameField.getText(), client );

                        // Save username and connection
                        thisUser = usernameField.getText();
                        thisUserConnected = true;

                        // Update activeUsers GUI list
                        updateUserList();

                        // Notify users and add to activeUsersArea
                        //blastMessage();
                        append(thisUser + " connected.");
                }
            }
            else {
                if ( usernameField.getText().length() == 0 ) {
                    // Username length requirement isn't met: No username entered
                    append("You must enter a username...");
                }
                else {
                    // Username length requirement isn't met: Username too short
                    append("Username must be at least " + requiredUsernameLength + " characters.");
                }
            }
        }
        else {
                // User already connected
                append("You are already connected...");
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed

        if( thisUserConnected ) {
            
            // Discconect user
            activeUsers.get(thisUser).stop();
            thisUserConnected = false;
            
            // Remove user from activeUsers list
            activeUsers.remove(thisUser);

            
            // Provide user feedback
            append(thisUser + " has left the chat.");
        }
        // Else --> do nothing
    }//GEN-LAST:event_disconnectButtonActionPerformed
    
    
    private void updateUserList() {
    	/*
    	if(activeUsers.get(name) != null){
    		activeUsers.remove(thisUser);
    	}
    	else{
    		activeUsers.put( usernameField.getText(), client );
    	}
    	for(int i = 0; i < activeUsers.size(); i++)
    		activeUsersArea.setText(activeUsers.get(i));
    		*/
    }
	
	// to start the whole thing the server
	public static void main(String[] args) {
		//new ChatServer(2343);
		new ChatGUI().setVisible(true);
	}

}





