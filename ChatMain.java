/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nic
 */
public class ChatMain {
    
    public static void main( String[] args ) {
        new ChatServer(2345);
        new ChatGUI("localhost", 2345).setVisible(true);
    }
}
