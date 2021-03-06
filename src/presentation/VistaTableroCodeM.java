package presentation;

import utils.KeyListenerPers;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import utils.Pair;

/**
 *
 * @author Martínez Martínez, Daniel
 */
public class VistaTableroCodeM extends javax.swing.JFrame {

    CtrlPresentacion CP;
    int num;
    int ran;
    int dif;
    int turno;
    int state;
    boolean introducirJugada = true;
    Icon iconoVacio;
    Icon iconoVacioK;
    ArrayList<Integer> cods;
    JLabel[][] codePegs;
    JLabel[][] keyPegs;
    JFrame solucion;
    
    /**
     * Creates new form VistaTablero
     */
    public VistaTableroCodeM() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            try {
                UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName());
            } catch(Exception ex){
                
            }
        }
        initComponents();
        
        setLocationRelativeTo(null);
        setTitle("MasterMind");
        
        ImageIcon imgicon = new ImageIcon("src/resources/iconomastermind.png");
        iconoVacio = new javax.swing.ImageIcon(getClass().getResource("/resources/0.png"));
        iconoVacioK = new javax.swing.ImageIcon(getClass().getResource("/resources/0K.png"));
        state = 1;
        cods = new ArrayList<Integer>();
        this.setIconImage(imgicon.getImage());
        
        setListeners();
    }
    
    /**
     *
     * @param CP la capa de presentación
     */
    public void setCP(CtrlPresentacion CP){
        this.CP = CP;
    }
    
    private void setListeners(){
        KeyListenerPers kl2 = new KeyListenerPers(jButton4, KeyEvent.VK_ESCAPE);
        jPanel1.setFocusable(true);
        jPanel1.addKeyListener(kl2);
        jPanel2.setFocusable(true);
        jPanel2.addKeyListener(kl2);
        jPanel4.setFocusable(true);
        jPanel4.addKeyListener(kl2);
        jButton1.addKeyListener(kl2);
        jButton2.addKeyListener(kl2);
        jButton3.addKeyListener(kl2);
        jButton4.addKeyListener(kl2);
        jButton5.addKeyListener(kl2);
        jButton6.addKeyListener(kl2);
        jButton7.addKeyListener(kl2);
        jButton8.addKeyListener(kl2);
        
        
        KeyListenerPers kl = new KeyListenerPers(jButton2, KeyEvent.VK_BACK_SPACE);
        jPanel1.addKeyListener(kl);
        jPanel2.addKeyListener(kl);
        jPanel4.addKeyListener(kl);
        jButton1.addKeyListener(kl);
        jButton2.addKeyListener(kl);
        jButton3.addKeyListener(kl);
        jButton4.addKeyListener(kl);
        jButton5.addKeyListener(kl);
        jButton6.addKeyListener(kl);
        jButton7.addKeyListener(kl);
        jButton8.addKeyListener(kl);
    }
    
    /**
     *
     * @param n el número de fichas de la partida
     * @param r el rango de colores de la partida
     * @param d la dificultad de la partida
     * @param t el turno porque el que va la partida
     * @param cargado si la partida se está cargando es cierto, si es una nueva partida es falso
     */
    public void setParams(int n, int r, int d, int t, boolean cargado){
        this.num = n;
        this.ran = r;
        this.dif = d;  
        this.turno = t;
        labelsMethod();
        if (cargado) {
            cargarTableroB(CP.getJugadasCodeB());
            cargarTableroM(CP.getJugadasCodeM());
        }
        else primeraJugada();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/2K.png"))); // NOI18N
        jButton6.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/1K.png"))); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(35, 35));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(239, 207, 167));

        jPanel2.setBackground(new java.awt.Color(239, 207, 167));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        jLabel1.setBackground(new java.awt.Color(239, 207, 167));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Introduce tu jugada");

        jPanel4.setBackground(new java.awt.Color(239, 207, 167));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jButton1.setText("Confirmar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Borrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Guardar partida");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Salir del juego");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Ayuda");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton8.setText("Ver solución");
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jButton1)
                .addGap(56, 56, 56)
                .addComponent(jButton5)
                .addGap(59, 59, 59)
                .addComponent(jButton2)
                .addGap(0, 72, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton8))
                .addGap(0, 11, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (state <= num && introducirJugada) {
         
            cods.add(2);
            keyPegs[turno-1][state-1].setIcon(jButton6.getIcon());
            ++state;
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void finishGame(boolean victory) {
        
        if (!victory) {
            JOptionPane.showMessageDialog(null, "Game over :(");
            jButton4ActionPerformed(null);
        }
        else {
            
            JOptionPane.showMessageDialog(null, "¡La IA ha acertado la combinación!");
            jButton4ActionPerformed(null);
            
        }
    }
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if (state <= num && introducirJugada) {
         
            cods.add(1);
            keyPegs[turno-1][state-1].setIcon(jButton7.getIcon());
            ++state;
            
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        solucion.setVisible(true);

    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JOptionPane.showMessageDialog(null, "Tienes que introducir un conjunto de fichas"
            + " que sean la pista adecuada.");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        VistaMenuPartida Vmp = new VistaMenuPartida();
        Vmp.setCP(CP);
        solucion.setVisible(false);
        Vmp.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Pair<Boolean,String> p = CP.saveGame();
        if (p.getLeft()){
            JOptionPane.showMessageDialog(null, "Has guardado la partida.");
        }
        else {
            JOptionPane.showMessageDialog(null, "Ha habido un error guardando la partida.","Error",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (state != 1) {
            --state;
            keyPegs[turno-1][state-1].setIcon(iconoVacioK);
            cods.remove(cods.size()-1);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        jButton6.setEnabled(false);
        jButton7.setEnabled(false);

        rellenarCods();

        if (CP.validarJugadaCodeM(cods)){
            setCursor(Cursor.WAIT_CURSOR);

            ArrayList<Integer> a = new ArrayList<Integer>();

            boolean victory = true;
            if (cods.contains(1) || cods.contains(0)) victory = false;
            if (victory) finishGame(true);
            else a = CP.jugadaCodeB(cods);

            turno++;

            if (a != null) setJugadaCodeB(a);

            state = 1;
            cods = new ArrayList<Integer>();
            if (turno > dif) finishGame(false);
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);

            setCursor(Cursor.getDefaultCursor());
        }
        else {
            JOptionPane.showMessageDialog(null, "La pista que has introducido no es correcta.","Pista incorrecta",JOptionPane.WARNING_MESSAGE);
            jButton6.setEnabled(true);
            jButton7.setEnabled(true);
            resetFila();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(VistaTableroCodeM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaTableroCodeM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaTableroCodeM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaTableroCodeM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaTableroCodeM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void labelsMethod() {
    
        codePegs = new JLabel[dif][num];
        int x = 10, y = 8, auxX = 10;
        
        if (num < 9) x = (9 - num) * 35 + 10;
               
        for(int row = 0; row < dif; row++) {

            for(int col = 0; col < num; col++) {

                codePegs[row][col] = new JLabel();
                codePegs[row][col].setOpaque(false);
                codePegs[row][col].setBounds(x, y, 35, 35);
                codePegs[row][col].setIcon(iconoVacio);
                jPanel2.add(codePegs[row][col]);
                
                x += 35;
            }
            auxX = x;
            x = 10;
            if (num < 9) x = (9 - num) * 35 + 10;
            y += 35;
        }
                
        if (ran >= 1) jPanel4.add(jButton6);
        if (ran >= 2) jPanel4.add(jButton7);
        
        x = auxX;
        y = 8;
        
        keyPegs = new JLabel[dif][num];
        
        for(int row = 0; row < dif; row++) {

            int col = 0;
            
            for(; col < num / 2; col++) {

                keyPegs[row][col] = new JLabel();
                keyPegs[row][col].setOpaque(false);
                keyPegs[row][col].setBounds(x, y, 18, 18);
                keyPegs[row][col].setIcon(iconoVacioK);
                jPanel2.add(keyPegs[row][col]);
                
                x += 17;
            }
            
            x = auxX;
            y += 17;
            
            for(; col < num; col++) {

                keyPegs[row][col] = new JLabel();
                keyPegs[row][col].setOpaque(false);
                keyPegs[row][col].setBounds(x, y, 18, 18);
                keyPegs[row][col].setIcon(iconoVacioK);
                jPanel2.add(keyPegs[row][col]);
                
                x += 17;
            }
            x = auxX;
            y += 18;
        }
        
        JLabel fondo = new JLabel();
        fondo.setOpaque(false);
        fondo.setBounds(0, 0, 424, 440);
        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/tablero.png")));
        jPanel2.add(fondo);
    }
    
    private void primeraJugada() {
        jButton6.setEnabled(false);
        jButton7.setEnabled(false);
        jLabel1.setText("Esperando la jugada del Codebreaker...");

        ArrayList<Integer> a = CP.jugadaCodeB(cods);

        if (a != null) setJugadaCodeB(a);

        jButton6.setEnabled(true);
        jButton7.setEnabled(true);
        jLabel1.setText("Introduce tu jugada");
    }
    
    private void setJugadaCodeB(ArrayList<Integer> a) {
        
        for (int i = 0; i < a.size(); ++i){
            codePegs[turno-1][i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/"+Integer.toString(a.get(i))+".png")));
        }
    }
    
    private void rellenarCods() {
        
        while(cods.size() < num){
            cods.add(0);
        }
    }
    
    /**
     *
     * @param a el patrón a adivinar de la partida
     */
    public void PopUpSolucion(ArrayList<Integer> a){
        
        solucion = new JFrame();
        solucion.setLocationRelativeTo(null);
        solucion.setSize(400,115);
        solucion.setTitle("Solución de la partida " +CP.getId());
        
        ImageIcon imgicon = new ImageIcon("src/resources/iconomastermind.png");
        
        solucion.setIconImage(imgicon.getImage());
        
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        
        solucion.add(p);
        
        for (int i = 0; i < a.size(); ++i) {
            JLabel l = new JLabel();
            l.setText("");
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/"+Integer.toString(a.get(i))+".png")));
            p.add(l);
        }
    }
    
    private void cargarTableroB(ArrayList<ArrayList<Integer>> log) {
        
        for (int i = 0; i < log.size(); ++i){
            
            for (int j = 0; j < log.get(i).size(); ++j){
                
                codePegs[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/"+Integer.toString(log.get(i).get(j))+".png")));
            }
        }
    }
    
    private void cargarTableroM(ArrayList<ArrayList<Integer>> log) {
        
        for (int i = 0; i < log.size(); ++i){
            
            for (int j = 0; j < log.get(i).size(); ++j){
                
                keyPegs[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/"+Integer.toString(log.get(i).get(j))+"K.png")));
            }
        }
    }
    
    private void resetFila() {
        
        state = 1;
        cods = new ArrayList<Integer>();
        for (int i = 0; i < num; ++i){
            keyPegs[turno-1][i].setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/0K.png")));
        }
    }
}