/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vilsonudp;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author Vinnie
 */
public class FrameVilsonUDP extends javax.swing.JFrame {

    /**
     * Creates new form FrameClienteUDP
     */
    public FrameVilsonUDP() {
        initComponents();
        setLocationRelativeTo(this);
        desativaCampos();
        jlImagemPrincipal.requestFocus();
        recebeMensagem();
    }

    //Funcao que desativa campos que o usuario nao deve interagir
    public void desativaCampos() {
        jtRobson2.setVisible(false);
        jtRobson3.setVisible(false);
        jtVilson2.setVisible(false);
        jtVilson3.setVisible(false);
        jtRobson1.setEditable(false);
        jtRobson2.setEditable(false);
        jtRobson3.setEditable(false);
        jtVilson1.setEditable(false);
        jtVilson2.setEditable(false);
        jtVilson3.setEditable(false);
        jlDigitando.setVisible(false);
    }

    //Funcao que ativa campos apos o envio de mensagens (Vilson)
    public void ativaCamposVilson() {
        if (jtVilson2.getText().length() > 0) {
            jtVilson2.setVisible(true);
        }
        if (jtVilson3.getText().length() > 0) {
            jtVilson3.setVisible(true);
        }
    }

    //Funcao que ativa campos apos o envio de mensagens (Robson)
    public void ativaCamposRobson() {
        if (jtRobson2.getText().length() > 0) {
            jtRobson2.setVisible(true);
        }
        if (jtRobson3.getText().length() > 0) {
            jtRobson3.setVisible(true);
        }
    }

    //funcao que tira um texto de um jTextArea e joga para o outro (Robson)
    public void mudaCamposRobson() {
        jtRobson3.setText(jtRobson2.getText());
        jtRobson2.setText(jtRobson1.getText());
        jtRobson1.setText("");
    }

    //funcao que tira um texto de um jTextArea e joga para o outro (Robson)
    public void mudaCamposVilson() {
        jtVilson3.setText(jtVilson2.getText());
        jtVilson2.setText(jtVilson1.getText());
        jtVilson1.setText("");
    }

    //Funcao mais importante, a que recebe os dados atraves de uma thread que
    // fica rodando e esperando a chegada de novos pacotes com dados.
    public void enviaMensagem(String sendMsg) {
        try {
            DatagramSocket clientSock = new DatagramSocket();
            InetAddress srvIP = InetAddress.getLocalHost();
            int srvPort = 25500;

            /* 65535 - 8 bytes UDP header - 20 bytes IP header */
            byte[] sendData = new byte[65507];

            System.out.print("\nDigite uma mensagem: ");
            sendData = sendMsg.getBytes();

            DatagramPacket sendPkt = new DatagramPacket(sendData, sendMsg.length(),
                    srvIP, srvPort);
            sendMsg = "";
            System.out.print("\nSending message...");
            clientSock.send(sendPkt);
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void recebeMensagem() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    DatagramSocket srvSock = new DatagramSocket(25000);
                    byte[] rcvdData = new byte[65507];

                    while (true) {
                        DatagramPacket rcvdPkt = new DatagramPacket(rcvdData, rcvdData.length);
                        System.out.print("\nWaiting message...");
                        srvSock.receive(rcvdPkt);

                        InetAddress srcIPAddr = rcvdPkt.getAddress();
                        int srcPort = rcvdPkt.getPort();
                        rcvdData = rcvdPkt.getData();
                        String msg = new String(rcvdPkt.getData(), "UTF-8");

                        if (msg.substring(0, 2).equals("lz")) {
                            System.out.println(msg);
                            msg = "";
                            jlDigitando.setVisible(true);
                            
                        } else if (msg.substring(0, 2).equals("2z")) {
                            jlDigitando.setVisible(false);
                            
                        } else {
                            System.out.print("\nMessage received...");
                            System.out.print("\n\tSource IP address: " + srcIPAddr);
                            System.out.print("\n\tSource port: " + srcPort);
                            System.out.print("\n\tSource payload length: " + rcvdPkt.getLength());
                            System.out.print("\n\tPayload: " + msg);
                            mudaCamposRobson();
                            ativaCamposRobson();
                            jtRobson1.setText("");
                            jlDigitando.setVisible(false);
                            jtRobson1.setText(msg.substring(0, rcvdPkt.getLength()));
                        }
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        ).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jlImagemPrincipal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jpanelifscbook = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jtMensagemVilson = new javax.swing.JTextField();
        jbEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtRobson1 = new javax.swing.JTextArea();
        jlImagemPrincipal1 = new javax.swing.JLabel();
        jlImagemPrincipal2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtRobson2 = new javax.swing.JTextArea();
        jlImagemPrincipal3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtRobson3 = new javax.swing.JTextArea();
        jlImagemVilson = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtVilson1 = new javax.swing.JTextArea();
        jlImagemVilson1 = new javax.swing.JLabel();
        jlImagemVilson2 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtVilson2 = new javax.swing.JTextArea();
        jlImagemVilson3 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtVilson3 = new javax.swing.JTextArea();
        jlImagemVilson4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jlDigitando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("IFSCBOOK Vilson");
        setBackground(new java.awt.Color(255, 255, 255));

        jlImagemPrincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/0 - Copia.jpg"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Robson Costa");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ifsbook.png"))); // NOI18N

        javax.swing.GroupLayout jpanelifscbookLayout = new javax.swing.GroupLayout(jpanelifscbook);
        jpanelifscbook.setLayout(jpanelifscbookLayout);
        jpanelifscbookLayout.setHorizontalGroup(
            jpanelifscbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpanelifscbookLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 941, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jpanelifscbookLayout.setVerticalGroup(
            jpanelifscbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jtMensagemVilson.setText("Digite seu texto aqui...");
        jtMensagemVilson.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jtMensagemVilsonFocusGained(evt);
            }
        });
        jtMensagemVilson.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtMensagemVilsonKeyPressed(evt);
            }
        });

        jbEnviar.setText("Enviar");
        jbEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarActionPerformed(evt);
            }
        });

        jtRobson1.setColumns(20);
        jtRobson1.setRows(5);
        jScrollPane1.setViewportView(jtRobson1);

        jlImagemPrincipal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/0 - Copia.jpg"))); // NOI18N

        jlImagemPrincipal2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/0 - Copia.jpg"))); // NOI18N

        jtRobson2.setColumns(20);
        jtRobson2.setRows(5);
        jScrollPane2.setViewportView(jtRobson2);

        jlImagemPrincipal3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/0 - Copia.jpg"))); // NOI18N

        jtRobson3.setColumns(20);
        jtRobson3.setRows(5);
        jScrollPane3.setViewportView(jtRobson3);

        jlImagemVilson.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vilsonheck.png"))); // NOI18N

        jtVilson1.setColumns(20);
        jtVilson1.setRows(5);
        jScrollPane5.setViewportView(jtVilson1);

        jlImagemVilson1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vilsonheck.png"))); // NOI18N

        jlImagemVilson2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vilsonheck.png"))); // NOI18N

        jtVilson2.setColumns(20);
        jtVilson2.setRows(5);
        jScrollPane6.setViewportView(jtVilson2);

        jlImagemVilson3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vilsonheck.png"))); // NOI18N

        jtVilson3.setColumns(20);
        jtVilson3.setRows(5);
        jScrollPane7.setViewportView(jtVilson3);

        jlImagemVilson4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/vilsonheck.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 255));
        jLabel3.setText("Vilson Heck Junior");

        jlDigitando.setText("Robson está digitando...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlImagemPrincipal2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlImagemPrincipal3)
                            .addComponent(jlImagemPrincipal1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jlDigitando)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jlImagemPrincipal)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jlImagemVilson1)
                            .addComponent(jlImagemVilson)
                            .addComponent(jlImagemVilson2)
                            .addComponent(jlImagemVilson4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                            .addComponent(jScrollPane6)
                            .addComponent(jScrollPane5)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jtMensagemVilson)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jbEnviar)))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlImagemVilson3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(271, 271, 271))))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanelifscbook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpanelifscbook, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlImagemPrincipal1)
                            .addComponent(jlImagemVilson3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlImagemPrincipal3)
                                .addGap(24, 24, 24))
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jlImagemVilson4)
                        .addGap(67, 67, 67)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlImagemVilson2)
                                .addGap(16, 16, 16)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jtMensagemVilson, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jbEnviar))
                                .addGap(15, 15, 15))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jlImagemVilson)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jlImagemVilson1)
                                .addContainerGap())))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jlImagemPrincipal2)
                                .addGap(24, 24, 24)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlImagemPrincipal)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(jlDigitando)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jtMensagemVilsonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jtMensagemVilsonFocusGained
        if (jtMensagemVilson.getText().equals("Digite seu texto aqui...")) {
            jtMensagemVilson.setText("");
        }
    }//GEN-LAST:event_jtMensagemVilsonFocusGained

    private void jbEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarActionPerformed
        enviaMensagem(jtMensagemVilson.getText());
        mudaCamposVilson();
        ativaCamposVilson();
        jtVilson1.setText(jtMensagemVilson.getText());
        jtMensagemVilson.setText("");
    }//GEN-LAST:event_jbEnviarActionPerformed
    //Evento que dispara o "Digitando..." para a pessoa do outro lado.
    private void jtMensagemVilsonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtMensagemVilsonKeyPressed
        if (jtMensagemVilson.getText().length() > 0) {
            enviaMensagem("lz");
        }else{
            enviaMensagem("2z");
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER
                && jtMensagemVilson.getText().length() > 0) {
            jbEnviar.doClick();
        }
    }//GEN-LAST:event_jtMensagemVilsonKeyPressed

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
            java.util.logging.Logger.getLogger(FrameVilsonUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameVilsonUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameVilsonUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameVilsonUDP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrameVilsonUDP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JButton jbEnviar;
    private javax.swing.JLabel jlDigitando;
    private javax.swing.JLabel jlImagemPrincipal;
    private javax.swing.JLabel jlImagemPrincipal1;
    private javax.swing.JLabel jlImagemPrincipal2;
    private javax.swing.JLabel jlImagemPrincipal3;
    private javax.swing.JLabel jlImagemVilson;
    private javax.swing.JLabel jlImagemVilson1;
    private javax.swing.JLabel jlImagemVilson2;
    private javax.swing.JLabel jlImagemVilson3;
    private javax.swing.JLabel jlImagemVilson4;
    private javax.swing.JPanel jpanelifscbook;
    private javax.swing.JTextField jtMensagemVilson;
    private javax.swing.JTextArea jtRobson1;
    private javax.swing.JTextArea jtRobson2;
    private javax.swing.JTextArea jtRobson3;
    private javax.swing.JTextArea jtVilson1;
    private javax.swing.JTextArea jtVilson2;
    private javax.swing.JTextArea jtVilson3;
    // End of variables declaration//GEN-END:variables
}
