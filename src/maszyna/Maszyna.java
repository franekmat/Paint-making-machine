package maszyna;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

public class Maszyna extends javax.swing.JFrame {
    
    Fasada fasada = new Fasada();
    
    private ObservableList<Farba> farby = 
            ObservableCollections.observableList(new ArrayList<Farba>());
    private  ObservableList<Pigment> pigmenty = 
            ObservableCollections.observableList(new ArrayList<Pigment>());
    private Farba farba;
    private Pigment pigment;
    private Boolean trwaMieszanie = false;
    
    public ObservableList<Farba> getFarby () {
        return farby;
    }
    
    public ObservableList<Pigment> getPigmenty () {
        return pigmenty;
    }
    
    public Farba getFarba() {
        return farba;
    }
    
    public Pigment getPigment() {
        return pigment;
    }
    
    public void setFarby (ObservableList<Farba> farby) {
        ObservableList<Farba> oldFarby = 
                ObservableCollections.observableList(
                        new ArrayList<Farba>(this.farby));
        this.farby = farby;
        firePropertyChange("farby", oldFarby, farby);
    }
    
    public void setPigmenty (ObservableList<Pigment> pigmenty) {
        ObservableList<Pigment> oldPigmenty = 
                ObservableCollections.observableList(
                        new ArrayList<Pigment>(this.pigmenty));
        this.pigmenty = pigmenty;
        firePropertyChange("pigmenty", oldPigmenty, pigmenty);
    }
    
    public void setFarba(Farba farba) {
        sprawdzMieszanie();
        Farba oldFarba = this.farba;
        this.farba = farba;
        firePropertyChange("farba", oldFarba, farba);
    }
    
    public void setPigment(Pigment pigment) {
        Pigment oldPigment = this.pigment;
        this.pigment = pigment;
        firePropertyChange("pigment", oldPigment, pigment);
    }
    
    private void sprawdzMieszanie () {
        if (trwaMieszanie) {
            System.out.println("\n***** Koniec mieszania *****");
            jButtonUzyjPigmentu.setEnabled(false);
            trwaMieszanie = false;
        }
    }
    
    private void czytajWejscie (File plik) {
        try {
                List<String> l = Files.readAllLines(plik.toPath(),
                        Charset.defaultCharset());
                setFarby(fasada.czytajFarby(new File(l.get(0))));
                setPigmenty(fasada.czytajPigmenty(new File(l.get(1))));
                if (farby == null || pigmenty == null) {
                    this.dispatchEvent(new WindowEvent(this, 
                            WindowEvent.WINDOW_CLOSING));
                }
            } catch (IOException e1) {
            System.err.println("Error while reading file.");
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Nie można odczytać pliku " 
                    + plik);
            this.dispatchEvent(new WindowEvent(this, 
                    WindowEvent.WINDOW_CLOSING));
        }
    }


    public Maszyna() {
        initComponents();
        czytajWejscie(new File(System.getProperty("user.dir") 
                + "/maszyna.conf"));
        this.setTitle("Maszyna");
        jButtonUzyjPigmentu.setEnabled(false);
        jTextFarba.setEditable(false);
        jTextPigment.setEditable(false);
    }    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        jListFarby = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListPigmenty = new javax.swing.JList<>();
        jLabelFarby = new javax.swing.JLabel();
        jLabelPigmenty = new javax.swing.JLabel();
        jButtonDodajFarbe = new javax.swing.JButton();
        jButtonMieszaj = new javax.swing.JButton();
        jButtonDodajPigment = new javax.swing.JButton();
        jButtonUzyjPigmentu = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFarba = new javax.swing.JTextField();
        jTextFarbaToksycznosc = new javax.swing.JTextField();
        jTextFarbaJakosc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextPigment = new javax.swing.JTextField();
        jTextPigmentToksycznosc = new javax.swing.JTextField();
        jTextPigmentJakosc = new javax.swing.JTextField();
        jTextPigmentKolorPoczatkowy = new javax.swing.JTextField();
        jTextPigmentKolorKoncowy = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jListFarby.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${farby}");
        org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jListFarby);
        jListBinding.setDetailBinding(org.jdesktop.beansbinding.ELProperty.create("${nazwa}"));
        bindingGroup.addBinding(jListBinding);
        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${farba}"), jListFarby, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane1.setViewportView(jListFarby);

        jListPigmenty.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = {  };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${pigmenty}");
        jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jListPigmenty);
        jListBinding.setDetailBinding(org.jdesktop.beansbinding.ELProperty.create("${nazwa}"));
        bindingGroup.addBinding(jListBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${pigment}"), jListPigmenty, org.jdesktop.beansbinding.BeanProperty.create("selectedElement"));
        bindingGroup.addBinding(binding);

        jScrollPane2.setViewportView(jListPigmenty);

        jLabelFarby.setText("Dostępne farby:");

        jLabelPigmenty.setText("Dostępne pigmenty:");

        jButtonDodajFarbe.setText("Dodaj farbę");
        jButtonDodajFarbe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajFarbeActionPerformed(evt);
            }
        });

        jButtonMieszaj.setText("Mieszaj");
        jButtonMieszaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMieszajActionPerformed(evt);
            }
        });

        jButtonDodajPigment.setText("Dodaj pigment");
        jButtonDodajPigment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDodajPigmentActionPerformed(evt);
            }
        });

        jButtonUzyjPigmentu.setText("Użyj pigmentu");
        jButtonUzyjPigmentu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUzyjPigmentuActionPerformed(evt);
            }
        });

        jLabel3.setText("Wybrana farba:");

        jLabel5.setText("Poziom toksyczności:");

        jLabel6.setText("Jakość:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${farba.nazwa}"), jTextFarba, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue("");
        binding.setSourceUnreadableValue("");
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${farba.toksycznosc}"), jTextFarbaToksycznosc, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        jTextFarbaToksycznosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFarbaToksycznoscActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${farba.jakosc}"), jTextFarbaJakosc, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        jTextFarbaJakosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFarbaJakoscActionPerformed(evt);
            }
        });

        jLabel7.setText("Wybrany pigment:");

        jLabel8.setText("Współczynnik toksyczności:");

        jLabel9.setText("Wpływ na jakość farby:");

        jLabel2.setText("Kolor początkowy:");

        jLabel4.setText("Kolor końcowy:");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${pigment.nazwa}"), jTextPigment, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${pigment.zmianaToksycznosci}"), jTextPigmentToksycznosc, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        jTextPigmentToksycznosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPigmentToksycznoscActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${pigment.zmianaJakosci}"), jTextPigmentJakosc, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        jTextPigmentJakosc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPigmentJakoscActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${pigment.kolorPoczatkowy}"), jTextPigmentKolorPoczatkowy, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        jTextPigmentKolorPoczatkowy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPigmentKolorPoczatkowyActionPerformed(evt);
            }
        });

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("${pigment.kolorKoncowy}"), jTextPigmentKolorKoncowy, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        binding.setSourceNullValue(null);
        binding.setSourceUnreadableValue(null);
        bindingGroup.addBinding(binding);

        jTextPigmentKolorKoncowy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextPigmentKolorKoncowyActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 3, 12)); // NOI18N
        jLabel1.setText("Aby zatwierdzić wprowadzone w formularzu zmiany potwierdź je każdorazowo enterem.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonDodajFarbe)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonMieszaj))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelFarby)
                                .addGap(15, 15, 15))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3))
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFarba)
                            .addComponent(jTextFarbaToksycznosc)
                            .addComponent(jTextFarbaJakosc))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPigmentToksycznosc, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextPigment)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel2))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextPigmentKolorPoczatkowy)
                                    .addComponent(jTextPigmentKolorKoncowy)
                                    .addComponent(jTextPigmentJakosc)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel4)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelPigmenty)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonDodajPigment)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonUzyjPigmentu)))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(113, 113, 113))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFarby)
                    .addComponent(jLabelPigmenty))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonDodajFarbe)
                    .addComponent(jButtonMieszaj)
                    .addComponent(jButtonUzyjPigmentu)
                    .addComponent(jButtonDodajPigment))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFarba, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextPigment, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel7)))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFarbaToksycznosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextPigmentToksycznosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFarbaJakosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jTextPigmentJakosc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextPigmentKolorPoczatkowy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextPigmentKolorKoncowy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jButtonDodajFarbeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajFarbeActionPerformed
        farby.add(new Farba());
    }//GEN-LAST:event_jButtonDodajFarbeActionPerformed

    private void jButtonMieszajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMieszajActionPerformed
        if (getFarba() != null) {
            getFarba().mieszaj();
            jButtonUzyjPigmentu.setEnabled(true);
            trwaMieszanie = true;
        }
    }//GEN-LAST:event_jButtonMieszajActionPerformed

    private void jButtonDodajPigmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDodajPigmentActionPerformed
        pigmenty.add(new Pigment());
    }//GEN-LAST:event_jButtonDodajPigmentActionPerformed

    private void jButtonUzyjPigmentuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUzyjPigmentuActionPerformed
        Farba tmp = getPigment().uzyjPigmentu(getFarba());
        setFarba(tmp);
        farby.set(jListFarby.getSelectedIndex(), tmp);
        jListFarby.clearSelection();
        jButtonUzyjPigmentu.setEnabled(false);
    }//GEN-LAST:event_jButtonUzyjPigmentuActionPerformed

    private void jTextFarbaToksycznoscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFarbaToksycznoscActionPerformed
        sprawdzMieszanie();
    }//GEN-LAST:event_jTextFarbaToksycznoscActionPerformed

    private void jTextFarbaJakoscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFarbaJakoscActionPerformed
        sprawdzMieszanie();
    }//GEN-LAST:event_jTextFarbaJakoscActionPerformed

    private void jTextPigmentToksycznoscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPigmentToksycznoscActionPerformed
        sprawdzMieszanie();
    }//GEN-LAST:event_jTextPigmentToksycznoscActionPerformed

    private void jTextPigmentJakoscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPigmentJakoscActionPerformed
        sprawdzMieszanie();
    }//GEN-LAST:event_jTextPigmentJakoscActionPerformed

    private void jTextPigmentKolorPoczatkowyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPigmentKolorPoczatkowyActionPerformed
        sprawdzMieszanie();
    }//GEN-LAST:event_jTextPigmentKolorPoczatkowyActionPerformed

    private void jTextPigmentKolorKoncowyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextPigmentKolorKoncowyActionPerformed
        sprawdzMieszanie();
    }//GEN-LAST:event_jTextPigmentKolorKoncowyActionPerformed

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
            java.util.logging.Logger.getLogger(Maszyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Maszyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Maszyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Maszyna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Maszyna().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDodajFarbe;
    private javax.swing.JButton jButtonDodajPigment;
    private javax.swing.JButton jButtonMieszaj;
    private javax.swing.JButton jButtonUzyjPigmentu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelFarby;
    private javax.swing.JLabel jLabelPigmenty;
    private static javax.swing.JList<String> jListFarby;
    private static javax.swing.JList<String> jListPigmenty;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFarba;
    private javax.swing.JTextField jTextFarbaJakosc;
    private javax.swing.JTextField jTextFarbaToksycznosc;
    private javax.swing.JTextField jTextPigment;
    private javax.swing.JTextField jTextPigmentJakosc;
    private javax.swing.JTextField jTextPigmentKolorKoncowy;
    private javax.swing.JTextField jTextPigmentKolorPoczatkowy;
    private javax.swing.JTextField jTextPigmentToksycznosc;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
