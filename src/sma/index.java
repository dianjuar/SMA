/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sma;

import Componentes.NXT.Dispositivos;
import Componentes.NXT.Robot;
import Networking.ConexionACO;
import Networking.ConexionVisionArtificial;
import Networking.base.Puertos;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author diego_juliao
 */
public class index extends javax.swing.JFrame 
{    
    private final ConexionVisionArtificial conect_VA;
    private ConexionACO conect_ACO;
    
    public static Robot romer,greta,fryda;
    public static Robot[] robots;
    private int robotsConected;
    
    public static boolean DEBUG = false;

    /**
     * Creates new form index
     */
    public index() 
    {
        initComponents();
        
        JButton buttonsStartRobots[] = { jButton_EmpFryda, jButton_EmpGreta, jButton_EmpRomer };
        
        conect_VA = new ConexionVisionArtificial( jLabel_connect_VA );

        fryda = new Robot( Dispositivos.Frida, 1, conect_VA, jLabel_dirFryda );
        romer = new Robot( Dispositivos.Romer, 2, conect_VA, jLabel_dirRomer );
        greta = new Robot( Dispositivos.Greta, 3, conect_VA, jLabel_dirGreta );
        
        robots = new Robot[3];
        robots[0] = fryda;
        robots[1] = romer;
        robots[2] = greta;
        
        robotsConected = 0;
        
        if(DEBUG)
        {
            jButton_EmpFryda.setEnabled(true);
            jButton_EmpRomer.setEnabled(true);
            jButton_EmpGreta.setEnabled(true);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Titulo = new javax.swing.JLabel();
        Panel_Agentes3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel_connect_frida = new javax.swing.JLabel();
        jButton_conectarFrida = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_connect_romer = new javax.swing.JLabel();
        jButton_conectarRomer = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel_connect_greta = new javax.swing.JLabel();
        jButton_conectarGreta = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel13 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel_ACO_estado = new javax.swing.JLabel();
        jButton_ACO_Connect = new javax.swing.JButton();
        jTextfield_ACO_host = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel14 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel_connect_VA = new javax.swing.JLabel();
        Panel_Agentes4 = new javax.swing.JPanel();
        Panel_Agentes5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton_EmpFryda = new javax.swing.JButton();
        jButton_ControlFryda = new javax.swing.JButton();
        jLabel_dirFryda = new javax.swing.JLabel();
        Panel_Agentes7 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton_EmpRomer = new javax.swing.JButton();
        jButton_ControlRomer = new javax.swing.JButton();
        jLabel_dirRomer = new javax.swing.JLabel();
        Panel_Agentes6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton_EmpGreta = new javax.swing.JButton();
        jButton_ControlGreta = new javax.swing.JButton();
        jLabel_dirGreta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Titulo.setFont(new java.awt.Font("Droid Sans Mono", 1, 36)); // NOI18N
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("Agentes Físicos");
        Titulo.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        Panel_Agentes3.setBackground(new java.awt.Color(197, 197, 197));
        Panel_Agentes3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tareas Pendientes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Andale Mono", 0, 14), new java.awt.Color(1, 1, 1))); // NOI18N
        Panel_Agentes3.setForeground(new java.awt.Color(1, 1, 1));
        Panel_Agentes3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_Agentes3.setPreferredSize(new java.awt.Dimension(666, 666));
        Panel_Agentes3.setLayout(new javax.swing.BoxLayout(Panel_Agentes3, javax.swing.BoxLayout.Y_AXIS));

        jLabel5.setText("Conectar Frida(1)");

        jLabel_connect_frida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/sincargar25x25.png"))); // NOI18N

        jButton_conectarFrida.setText("Go");
        jButton_conectarFrida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_conectarFridaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(jButton_conectarFrida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_connect_frida)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel_connect_frida)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_conectarFrida))))
        );

        Panel_Agentes3.add(jPanel6);
        Panel_Agentes3.add(jSeparator1);

        jLabel1.setText("Conectar Romer(2)");

        jLabel_connect_romer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/sincargar25x25.png"))); // NOI18N

        jButton_conectarRomer.setText("Go");
        jButton_conectarRomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_conectarRomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addComponent(jButton_conectarRomer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_connect_romer)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel_connect_romer)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_conectarRomer))))
        );

        Panel_Agentes3.add(jPanel5);
        Panel_Agentes3.add(jSeparator6);

        jLabel7.setText("Conectar Greta(3)");

        jLabel_connect_greta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/sincargar25x25.png"))); // NOI18N

        jButton_conectarGreta.setText("Go");
        jButton_conectarGreta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_conectarGretaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(jButton_conectarGreta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_connect_greta)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel_connect_greta)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_conectarGreta))))
        );

        Panel_Agentes3.add(jPanel7);
        Panel_Agentes3.add(jSeparator5);

        jLabel19.setText("Conectar A. Transito");

        jLabel_ACO_estado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/sincargar25x25.png"))); // NOI18N

        jButton_ACO_Connect.setText("Go");
        jButton_ACO_Connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ACO_ConnectActionPerformed(evt);
            }
        });

        jTextfield_ACO_host.setText("127.0.0.1");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextfield_ACO_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_ACO_Connect)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_ACO_estado)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_ACO_estado)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_ACO_Connect)
                        .addComponent(jTextfield_ACO_host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        Panel_Agentes3.add(jPanel13);
        Panel_Agentes3.add(jSeparator2);

        jLabel21.setText("Conectar Vision Artificial");

        jLabel_connect_VA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/connecting25x25.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(jLabel_connect_VA)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_connect_VA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Panel_Agentes3.add(jPanel14);

        Panel_Agentes4.setBackground(new java.awt.Color(197, 197, 197));
        Panel_Agentes4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agentes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Andale Mono", 0, 14), new java.awt.Color(1, 1, 1))); // NOI18N
        Panel_Agentes4.setForeground(new java.awt.Color(1, 1, 1));
        Panel_Agentes4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_Agentes4.setPreferredSize(new java.awt.Dimension(666, 666));
        Panel_Agentes4.setLayout(new javax.swing.BoxLayout(Panel_Agentes4, javax.swing.BoxLayout.PAGE_AXIS));

        Panel_Agentes5.setBackground(new java.awt.Color(197, 197, 197));
        Panel_Agentes5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fryda(1)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Andale Mono", 0, 14), new java.awt.Color(1, 1, 1))); // NOI18N
        Panel_Agentes5.setForeground(new java.awt.Color(1, 1, 1));
        Panel_Agentes5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_Agentes5.setPreferredSize(new java.awt.Dimension(666, 666));
        Panel_Agentes5.setLayout(new javax.swing.BoxLayout(Panel_Agentes5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jButton_EmpFryda.setBackground(new java.awt.Color(72, 151, 1));
        jButton_EmpFryda.setForeground(new java.awt.Color(255, 255, 255));
        jButton_EmpFryda.setText("Empezar");
        jButton_EmpFryda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EmpFrydaActionPerformed(evt);
            }
        });
        jPanel1.add(jButton_EmpFryda);

        jButton_ControlFryda.setBackground(new java.awt.Color(118, 118, 118));
        jButton_ControlFryda.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ControlFryda.setText("Simple Contol");
        jButton_ControlFryda.setEnabled(false);
        jButton_ControlFryda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_ControlFrydaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton_ControlFrydaKeyReleased(evt);
            }
        });
        jPanel1.add(jButton_ControlFryda);

        Panel_Agentes5.add(jPanel1);

        jLabel_dirFryda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/Direcciones/0.png"))); // NOI18N
        jLabel_dirFryda.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLabel_dirFryda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dirFrydaMouseClicked(evt);
            }
        });
        Panel_Agentes5.add(jLabel_dirFryda);

        Panel_Agentes4.add(Panel_Agentes5);

        Panel_Agentes7.setBackground(new java.awt.Color(197, 197, 197));
        Panel_Agentes7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Romer(2)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Andale Mono", 0, 14), new java.awt.Color(1, 1, 1))); // NOI18N
        Panel_Agentes7.setForeground(new java.awt.Color(1, 1, 1));
        Panel_Agentes7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_Agentes7.setPreferredSize(new java.awt.Dimension(666, 666));
        Panel_Agentes7.setLayout(new javax.swing.BoxLayout(Panel_Agentes7, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jButton_EmpRomer.setBackground(new java.awt.Color(72, 151, 1));
        jButton_EmpRomer.setForeground(new java.awt.Color(255, 255, 255));
        jButton_EmpRomer.setText("Empezar");
        jButton_EmpRomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EmpRomerActionPerformed(evt);
            }
        });
        jPanel3.add(jButton_EmpRomer);

        jButton_ControlRomer.setBackground(new java.awt.Color(118, 118, 118));
        jButton_ControlRomer.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ControlRomer.setText("Simple Contol");
        jButton_ControlRomer.setEnabled(false);
        jButton_ControlRomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_ControlRomerKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton_ControlRomerKeyReleased(evt);
            }
        });
        jPanel3.add(jButton_ControlRomer);

        Panel_Agentes7.add(jPanel3);

        jLabel_dirRomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/Direcciones/0.png"))); // NOI18N
        jLabel_dirRomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dirRomerMouseClicked(evt);
            }
        });
        Panel_Agentes7.add(jLabel_dirRomer);

        Panel_Agentes4.add(Panel_Agentes7);

        Panel_Agentes6.setBackground(new java.awt.Color(197, 197, 197));
        Panel_Agentes6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Greta(3)", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Andale Mono", 0, 14), new java.awt.Color(1, 1, 1))); // NOI18N
        Panel_Agentes6.setForeground(new java.awt.Color(1, 1, 1));
        Panel_Agentes6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Panel_Agentes6.setPreferredSize(new java.awt.Dimension(666, 666));
        Panel_Agentes6.setLayout(new javax.swing.BoxLayout(Panel_Agentes6, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setLayout(new java.awt.GridLayout(2, 1, 0, 5));

        jButton_EmpGreta.setBackground(new java.awt.Color(72, 151, 1));
        jButton_EmpGreta.setForeground(new java.awt.Color(255, 255, 255));
        jButton_EmpGreta.setText("Empezar");
        jButton_EmpGreta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_EmpGretaActionPerformed(evt);
            }
        });
        jPanel2.add(jButton_EmpGreta);

        jButton_ControlGreta.setBackground(new java.awt.Color(118, 118, 118));
        jButton_ControlGreta.setForeground(new java.awt.Color(255, 255, 255));
        jButton_ControlGreta.setText("Simple Contol");
        jButton_ControlGreta.setEnabled(false);
        jButton_ControlGreta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton_ControlGretaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jButton_ControlGretaKeyReleased(evt);
            }
        });
        jPanel2.add(jButton_ControlGreta);

        Panel_Agentes6.add(jPanel2);

        jLabel_dirGreta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Media/Img/Direcciones/0.png"))); // NOI18N
        jLabel_dirGreta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel_dirGretaMouseClicked(evt);
            }
        });
        Panel_Agentes6.add(jLabel_dirGreta);

        Panel_Agentes4.add(Panel_Agentes6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Panel_Agentes4, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(15, 15, 15)
                .addComponent(Panel_Agentes3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Panel_Agentes3, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Panel_Agentes4, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_conectarRomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_conectarRomerActionPerformed
        gestorConectorRobots(romer,jLabel_connect_romer,jButton_conectarRomer, jButton_ControlRomer);
    }//GEN-LAST:event_jButton_conectarRomerActionPerformed

    private void jButton_conectarFridaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_conectarFridaActionPerformed
        gestorConectorRobots(fryda,jLabel_connect_frida,jButton_conectarFrida, jButton_ControlFryda);
    }//GEN-LAST:event_jButton_conectarFridaActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        romer.desconectar();
        greta.desconectar();
        fryda.desconectar();
    }//GEN-LAST:event_formWindowClosing

    private void jButton_conectarGretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_conectarGretaActionPerformed
        gestorConectorRobots(greta,jLabel_connect_greta,jButton_conectarGreta, jButton_ControlGreta);       
    }//GEN-LAST:event_jButton_conectarGretaActionPerformed

    private void jButton_ACO_ConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ACO_ConnectActionPerformed
        
        Tools.GestionLabels.CambiarLabel_esperando25x25( jLabel_ACO_estado );
        
        if( conect_ACO == null || !conect_ACO.isConnected() )
        {
            conect_ACO = new ConexionACO(jTextfield_ACO_host.getText(), Puertos.ACO, new Robot[]{ fryda, romer, greta } );
            conect_ACO.connectTo(jLabel_ACO_estado, jButton_ACO_Connect, jTextfield_ACO_host);
            
            for (Robot robot : robots)
                robot.setConect_ACO(conect_ACO);            
        }
    }//GEN-LAST:event_jButton_ACO_ConnectActionPerformed

    private void jLabel_dirGretaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dirGretaMouseClicked
        
        if( jLabel_dirGreta.isEnabled())
            cambiarFlecha();
        
    }//GEN-LAST:event_jLabel_dirGretaMouseClicked

    private void jButton_ControlGretaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_ControlGretaKeyPressed
        manejarRobot(greta, evt);
    }//GEN-LAST:event_jButton_ControlGretaKeyPressed

    private void jButton_ControlRomerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_ControlRomerKeyPressed
        manejarRobot(romer, evt);
    }//GEN-LAST:event_jButton_ControlRomerKeyPressed

    private void manejarRobot(Robot r, java.awt.event.KeyEvent evt)
    {
        if(r.isConnected())
        {
            int key = evt.getKeyCode();
            
            switch (key)
            {
                case KeyEvent.VK_W: //adelante
                    r.adelante();
                break;

                case KeyEvent.VK_S: //atras
                    r.atras();
                break;

                case KeyEvent.VK_A: //izq
                    r.izq();
                break;

                case KeyEvent.VK_D: //der
                    r.der();
                break;
            }
        }
    }
    
    private void jButton_ControlGretaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_ControlGretaKeyReleased
        if( greta.isConnected() )
            greta.parar();
    }//GEN-LAST:event_jButton_ControlGretaKeyReleased

    private void jButton_ControlRomerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_ControlRomerKeyReleased
        if( romer.isConnected() )
            romer.parar();
    }//GEN-LAST:event_jButton_ControlRomerKeyReleased

    private void jButton_EmpGretaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EmpGretaActionPerformed
        robotStart(greta);
    }//GEN-LAST:event_jButton_EmpGretaActionPerformed

    private void jButton_EmpRomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EmpRomerActionPerformed
        robotStart(romer);
    }//GEN-LAST:event_jButton_EmpRomerActionPerformed

    private void jLabel_dirRomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dirRomerMouseClicked
         if( jLabel_dirRomer.isEnabled())
            cambiarFlecha();
    }//GEN-LAST:event_jLabel_dirRomerMouseClicked

    private void jLabel_dirFrydaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel_dirFrydaMouseClicked

        if( jLabel_dirFryda.isEnabled())
        cambiarFlecha();

    }//GEN-LAST:event_jLabel_dirFrydaMouseClicked

    private void jButton_ControlFrydaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_ControlFrydaKeyReleased
        if( fryda.isConnected() )
        fryda.parar();
    }//GEN-LAST:event_jButton_ControlFrydaKeyReleased

    private void jButton_ControlFrydaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton_ControlFrydaKeyPressed
        manejarRobot(fryda, evt);
    }//GEN-LAST:event_jButton_ControlFrydaKeyPressed

    private void jButton_EmpFrydaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_EmpFrydaActionPerformed

        robotStart(fryda);
    }//GEN-LAST:event_jButton_EmpFrydaActionPerformed

    private void robotStart( Robot r )
    {
        if( r.isConnected() )
        {
            if( !r.isAlive() )
                r.start();
            else
                r.continuarHilo();
        }
    }
    
    private void cambiarFlecha()
    {
        fryda.setSiguiente_horientacion();
        romer.setSiguiente_horientacion();
        greta.setSiguiente_horientacion();
    }
    
    private void gestorConectorRobots( Robot r, JLabel l, JButton b_conectBlueThoot, JButton b_SimpleControl )
    {   
        Tools.GestionLabels.CambiarLabel_esperando25x25(l);
        r.conectar();
        
        if(r.isConnected())
        {
            Tools.GestionLabels.CambiarLabel_correcto25x25(l);
            
            b_conectBlueThoot.setEnabled(false);
            b_SimpleControl.setEnabled(true);
            
            robotsConected++;
        }
        else
            Tools.GestionLabels.CambiarLabel_incorrecto25x25(l);
    }
    
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
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(index.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new index().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel_Agentes3;
    private javax.swing.JPanel Panel_Agentes4;
    private javax.swing.JPanel Panel_Agentes5;
    private javax.swing.JPanel Panel_Agentes6;
    private javax.swing.JPanel Panel_Agentes7;
    private javax.swing.JLabel Titulo;
    private javax.swing.JButton jButton_ACO_Connect;
    private javax.swing.JButton jButton_ControlFryda;
    private javax.swing.JButton jButton_ControlGreta;
    private javax.swing.JButton jButton_ControlRomer;
    private javax.swing.JButton jButton_EmpFryda;
    private javax.swing.JButton jButton_EmpGreta;
    private javax.swing.JButton jButton_EmpRomer;
    private javax.swing.JButton jButton_conectarFrida;
    private javax.swing.JButton jButton_conectarGreta;
    private javax.swing.JButton jButton_conectarRomer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel_ACO_estado;
    private javax.swing.JLabel jLabel_connect_VA;
    private javax.swing.JLabel jLabel_connect_frida;
    private javax.swing.JLabel jLabel_connect_greta;
    private javax.swing.JLabel jLabel_connect_romer;
    private javax.swing.JLabel jLabel_dirFryda;
    private javax.swing.JLabel jLabel_dirGreta;
    private javax.swing.JLabel jLabel_dirRomer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextfield_ACO_host;
    // End of variables declaration//GEN-END:variables
}
