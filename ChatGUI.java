
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nic
 */
public class ChatGUI extends javax.swing.JFrame {
    
    private HashMap<String, ChatClient> activeUsers = new HashMap<String, ChatClient>();
    
    private int portNumber;
    private String hostName;
    
    private int requiredUsernameLength = 3; // Usernames must be at least this long
    private String thisUser; // Saved username after a user is connected
    private boolean thisUserConnected = false; // Is there a user connected in this GUI?
    private ChatClient client;
    public ChatGUI(String host, int port) {
        
        hostName = host;
        portNumber = port;
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        // TODO add your handling code here:
    	client.handle("Test");
    }//GEN-LAST:event_sendButtonActionPerformed

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        
        // Make sure user isn't already connected
        if ( !thisUserConnected ) {
        
            // Check for username length > requiredUsernameLength
            if ( usernameField.getText().length() >= requiredUsernameLength ) {

                // Create client
               client = new ChatClient(hostName, portNumber);

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
                        addSystemMessage(thisUser + " connected.");
                }
            }
            else {
                if ( usernameField.getText().length() == 0 ) {
                    // Username length requirement isn't met: No username entered
                    addSystemMessage("You must enter a username...");
                }
                else {
                    // Username length requirement isn't met: Username too short
                    addSystemMessage("Username must be at least " + requiredUsernameLength + " characters.");
                }
            }
        }
        else {
                // User already connected
                addSystemMessage("You are already connected...");
        }
    }//GEN-LAST:event_connectButtonActionPerformed

    private void disconnectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disconnectButtonActionPerformed

        if( thisUserConnected ) {
            
            // Discconect user
            activeUsers.get(thisUser).stop();
            thisUserConnected = false;
            
            // Remove user from activeUsers list
            activeUsers.remove(thisUser);
            
            // Update activeUsers GUI list
            updateUserList();
            
            // Provide user feedback
            addSystemMessage(thisUser + " has left the chat.");
        }
        // Else --> do nothing
    }//GEN-LAST:event_disconnectButtonActionPerformed
    
    private void blastMessage() {
        // Send update to all users
    }
    
    static void addSystemMessage(String message) {
        activeMessagesArea.append(message + "\n");
    }
    
    private void updateUserList() {
        
        // Clear list
        activeUsersArea.setText("");
        
        // Repopulate list
        for( String username : activeUsers.keySet() ) {
          activeUsersArea.append(username + "\n"); 
        }
    }
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChatGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new ChatGUI(portNumber).setVisible(true);
//            }
//        });
//    }

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
}
