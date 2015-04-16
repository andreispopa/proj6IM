package proj6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;

public class MainGUI extends javax.swing.JDialog implements Runnable {
    private Socket sock;
    private String friendList[];
    private String friends;
    //this is to store who this user is to help with cedartalk 
    private String myUsername;
    /**
     * Creates new form MainGUI
     * @param parent
     * @param modal
     * @param sock
     * @param friends
     * @param user
     */
    public MainGUI(java.awt.Frame parent, boolean modal, Socket sock, String friends, String user) {
        super(parent, modal);
        initComponents();
        this.sock = sock;
        this.friends = friends;
        this.myUsername = user;
        initFL(); 
        //this.sock = sock;  System.out.println("here");
    }
    
    private void initFL() 
    {
        initializeFL();
        createFriendList();
        updateFriends();
    }
    
    private void initializeFL () 
    {
        int size = 0; 
        StringTokenizer st = new StringTokenizer(friends, " ");
        while (st.hasMoreElements()) {System.out.println((String)st.nextElement());
            size++;
        }
       friendList = new String[size];
    }
    
    private void createFriendList() {
        int i = 0;
        
        StringTokenizer st = new StringTokenizer(friends, " ");
        
        while (st.hasMoreElements()) {
            friendList[i] = (String)st.nextElement();
            i++;
        }
    }
    
    public void updateFriends () {
        for (String s : friendList) {
            String text = onlineFriendsTxtArea.getText() + "- " + s + "\n";
            onlineFriendsTxtArea.setText(text);
        }
    }
    
    public MainGUI(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jScrollPane1 = new javax.swing.JScrollPane();
        onlineFriendsTxtArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        LOGOFF = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        onlineFriendsTxtArea.setEditable(false);
        onlineFriendsTxtArea.setColumns(20);
        onlineFriendsTxtArea.setRows(5);
        jScrollPane1.setViewportView(onlineFriendsTxtArea);

        jLabel1.setText("Online Friends");

        LOGOFF.setText("LOGOFF");
        LOGOFF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGOFFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorLabel)
                    .addComponent(LOGOFF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(errorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LOGOFF))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LOGOFFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGOFFActionPerformed
        PrintWriter pout = null;
            try {
                //      int port = 4220;
                //  String host = "127.0.0.1";
                BufferedReader in = null;
                Socket socket = this.sock;
                errorLabel.setText("");
                if(socket == null){
                    System.out.println("why is this socket null?");
                } else { 
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    pout = new PrintWriter(socket.getOutputStream(), true);
                    String message = "2 " + this.myUsername;
                    pout.println(message);
                    // feedback messages
                    System.out.println("String sent to the server. (logging off)");
                    System.out.println("Waiting for the server to respond...");
                    String logoffSuccess = "";

                    logoffSuccess = in.readLine();

                    if (logoffSuccess.equals("LOGGEDOFF")) {
                        System.out.println("Message received from server: " + logoffSuccess);
                        this.sock.close();
                        System.exit(0);
                        //do we need to close this socket?
                        //sock.close();
                    } else {
                        System.out.println("logoff failed");
                    }
                }
            } catch (IOException ioe) {
                System.err.println(ioe);
            }
    }//GEN-LAST:event_LOGOFFActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                MainGUI dialog = new MainGUI(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LOGOFF;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea onlineFriendsTxtArea;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        
    }
}
