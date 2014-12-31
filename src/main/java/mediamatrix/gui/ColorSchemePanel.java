package mediamatrix.gui;

import mediamatrix.munsell.ColorHistogram;
import mediamatrix.munsell.ColorImpressionDataStore;
import mediamatrix.munsell.ColorImpressionKnowledge;
import mediamatrix.munsell.HSVColor;
import mediamatrix.mvc.ColorListCellRenderer;
import mediamatrix.utils.FileUtils;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import mediamatrix.io.SuffixFilenameFilter;

public class ColorSchemePanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultComboBoxModel<String> csModel;
    private ColorHistogram histogram;
    private ColorImpressionKnowledge createdCI;
    private JFileChooser fileChooser;

    public ColorSchemePanel() {
        initComponents();
        fileChooser = FileUtils.createJFileChooserWithImagePreview();
        aScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        aScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        colorList.setCellRenderer(new ColorListCellRenderer());
        createdCI = null;
        updateCI();
        try {
            updateColorList(ColorImpressionDataStore.getColorImpressionKnowledge(csModel.getSelectedItem().toString()));
        } catch (Exception ex) {
            ErrorUtils.showDialog(ex, aScrollPane);
        }
    }

    private void updateCI() {
        csModel = new DefaultComboBoxModel<String>();
        try {
            String[] ciList = ColorImpressionDataStore.getColorImpressionKnowledgeList();
            for (String ciName : ciList) {
                csModel.addElement(ciName);
            }
        } catch (Exception ex) {
            ErrorUtils.showDialog(ex, this);
        }
        csComboBox.setModel(csModel);
    }

    private void updateColorList(ColorImpressionKnowledge ci) {
        final HSVColor[] colors = ci.getColors();
        final DefaultListModel<Color> colorListModel = new DefaultListModel<Color>();
        for (int i = 0; i < colors.length; i++) {
            colorListModel.addElement(colors[i]);
        }
        colorList.setModel(colorListModel);

        final String[] words = ci.getWords();
        csList.setCellRenderer(new ImpressionWordListCellRenderer(ci, 14f));
        csList.setModel(new AbstractListModel<String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public int getSize() {
                return words.length;
            }

            @Override
            public String getElementAt(int index) {
                return words[index];
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        csDialog = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        csNameTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        fileButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        colorRibbonLabel = new javax.swing.JLabel();
        toolPanel = new javax.swing.JPanel();
        aToolBar = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        saveAsButton = new javax.swing.JButton();
        inspectButton = new javax.swing.JButton();
        importButton = new javax.swing.JButton();
        csComboBox = new javax.swing.JComboBox<String>();
        aSplitPane = new javax.swing.JSplitPane();
        aScrollPane = new javax.swing.JScrollPane();
        csList = new javax.swing.JList<String>();
        colorListScrollPane = new javax.swing.JScrollPane();
        colorList = new javax.swing.JList<Color>();

        csDialog.setTitle("Color Scheme Generator");

        jLabel1.setText("Scheme Name");

        csNameTextField.setText("scheme1");

        jLabel2.setText("Color Scheme");

        fileButton.setText("File");
        fileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileButtonActionPerformed(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        colorRibbonLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout csDialogLayout = new javax.swing.GroupLayout(csDialog.getContentPane());
        csDialog.getContentPane().setLayout(csDialogLayout);
        csDialogLayout.setHorizontalGroup(
            csDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(csDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(csDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(csDialogLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(csNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                    .addGroup(csDialogLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(colorRibbonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fileButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, csDialogLayout.createSequentialGroup()
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)))
                .addContainerGap())
        );
        csDialogLayout.setVerticalGroup(
            csDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(csDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(csDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(csNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(csDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(csDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(fileButton))
                    .addComponent(colorRibbonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(csDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton))
                .addContainerGap())
        );

        setLayout(new java.awt.BorderLayout());

        toolPanel.setLayout(new java.awt.BorderLayout());

        aToolBar.setRollover(true);

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediamatrix/resources/Add16.png"))); // NOI18N
        addButton.setText("Add");
        addButton.setFocusable(false);
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        aToolBar.add(addButton);

        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediamatrix/resources/Delete16.png"))); // NOI18N
        removeButton.setText("Remove");
        removeButton.setFocusable(false);
        removeButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        aToolBar.add(removeButton);

        saveAsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediamatrix/resources/Filesave.png"))); // NOI18N
        saveAsButton.setText("Save As");
        saveAsButton.setFocusable(false);
        saveAsButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        saveAsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsButtonActionPerformed(evt);
            }
        });
        aToolBar.add(saveAsButton);

        inspectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediamatrix/resources/See.png"))); // NOI18N
        inspectButton.setText("Inspect");
        inspectButton.setFocusable(false);
        inspectButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        inspectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inspectButtonActionPerformed(evt);
            }
        });
        aToolBar.add(inspectButton);

        importButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mediamatrix/resources/Import.gif"))); // NOI18N
        importButton.setText("Import CSV");
        importButton.setFocusable(false);
        importButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });
        aToolBar.add(importButton);

        csComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                csComboBoxActionPerformed(evt);
            }
        });
        aToolBar.add(csComboBox);

        toolPanel.add(aToolBar, java.awt.BorderLayout.NORTH);

        add(toolPanel, java.awt.BorderLayout.PAGE_START);

        aSplitPane.setDividerLocation(100);
        aSplitPane.setContinuousLayout(true);

        aScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        aScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        csList.setFixedCellHeight(60);
        csList.setFixedCellWidth(200);
        csList.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        csList.setVisibleRowCount(0);
        aScrollPane.setViewportView(csList);

        aSplitPane.setRightComponent(aScrollPane);

        colorListScrollPane.setViewportView(colorList);

        aSplitPane.setLeftComponent(colorListScrollPane);

        add(aSplitPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void csComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_csComboBoxActionPerformed
        createdCI = null;
        try {
            updateColorList(ColorImpressionDataStore.getColorImpressionKnowledge(csModel.getSelectedItem().toString()));
        } catch (Exception ex) {
            ErrorUtils.showDialog(ex, this);
        }
    }//GEN-LAST:event_csComboBoxActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        csDialog.setSize(600, 200);
        csDialog.setLocationRelativeTo(null);
        csDialog.setVisible(true);
    }//GEN-LAST:event_addButtonActionPerformed

    private void fileButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fileButtonActionPerformed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (fileChooser.getSelectedFile() != null) {
                try {
                    final ColorImpressionKnowledge ci = ColorImpressionDataStore.getColorImpressionKnowledge(csModel.getSelectedItem().toString());
                    histogram = ci.generateHistogram(ImageIO.read(fileChooser.getSelectedFile()));
                    colorRibbonLabel.setIcon(new ImageIcon(histogram.createHistogramImage()));
                } catch (IOException ex) {
                    ErrorUtils.showDialog(ex, this);
                }
            }
        }
    }//GEN-LAST:event_fileButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        final String word = csNameTextField.getText().trim();
        if (histogram != null) {
            if (createdCI == null) {
                try {
                    createdCI = ColorImpressionDataStore.getColorImpressionKnowledge(csModel.getSelectedItem().toString()).duplicate();
                } catch (Exception ex) {
                    ErrorUtils.showDialog(ex, this);
                }
            }
            createdCI.add(word, histogram);
            updateColorList(createdCI);
            csDialog.setVisible(false);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        csDialog.setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void saveAsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsButtonActionPerformed
        if (createdCI != null) {
            String ans = JOptionPane.showInputDialog(SwingUtilities.getWindowAncestor(this), "Please input color-impression name.");
            if (ans != null) {
                ans = ans.trim();
                try {
                    ColorImpressionDataStore.registerColorImpressionKnowledge(ans, createdCI);
                    updateCI();
                } catch (IOException ex) {
                    Logger.getLogger(ColorSchemePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_saveAsButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if (createdCI == null) {
            try {
                createdCI = ColorImpressionDataStore.getColorImpressionKnowledge(csModel.getSelectedItem().toString()).duplicate();
            } catch (Exception ex) {
                ErrorUtils.showDialog(ex, this);
            }
        }
        createdCI.remove(csList.getSelectedValue().toString());
        updateColorList(createdCI);
    }//GEN-LAST:event_removeButtonActionPerformed

    private void inspectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inspectButtonActionPerformed
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile() != null) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            final SwingWorker<BufferedImage, Object> worker = new SwingWorker<BufferedImage, Object>() {

                @Override
                protected BufferedImage doInBackground() throws Exception {
                    return ImageIO.read(fileChooser.getSelectedFile());
                }

                @Override
                protected void done() {
                    try {
                        final BufferedImage image = get();
                        ColorImpressionKnowledge ci = ColorImpressionDataStore.getColorImpressionKnowledge(csModel.getSelectedItem().toString());
                        if (createdCI != null) {
                            ci = createdCI;
                        }
                        DialogUtils.showDialog(fileChooser.getSelectedFile().getAbsolutePath(), new MunsellImagePanel(image, ci), ColorSchemePanel.this);
                    } catch (Exception ex) {
                        ErrorUtils.showDialog(ex, csList);
                    }
                    ColorSchemePanel.this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            };
            worker.execute();
        }
    }//GEN-LAST:event_inspectButtonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        final JFileChooser fc = new JFileChooser(new File("."));
        fc.setMultiSelectionEnabled(false);
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new SuffixFilenameFilter(new String[]{".csv", ".CSV"}));
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            final File file = fc.getSelectedFile();
            final ColorImpressionKnowledge ci = new ColorImpressionKnowledge();
            try {
                ci.load(new BufferedInputStream(new FileInputStream(file)), "UTF-8");
                final String str = file.getName();
                ColorImpressionDataStore.registerColorImpressionKnowledge(str.substring(0, str.lastIndexOf('.')), ci);
                JOptionPane.showMessageDialog(this, "Successfully installed.", "ColorImpression CSV Installation", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ErrorUtils.showDialog(ex, this);
            }
        }
    }//GEN-LAST:event_importButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane aScrollPane;
    private javax.swing.JSplitPane aSplitPane;
    private javax.swing.JToolBar aToolBar;
    private javax.swing.JButton addButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JList<Color> colorList;
    private javax.swing.JScrollPane colorListScrollPane;
    private javax.swing.JLabel colorRibbonLabel;
    private javax.swing.JComboBox<String> csComboBox;
    private javax.swing.JDialog csDialog;
    private javax.swing.JList<String> csList;
    private javax.swing.JTextField csNameTextField;
    private javax.swing.JButton fileButton;
    private javax.swing.JButton importButton;
    private javax.swing.JButton inspectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton okButton;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton saveAsButton;
    private javax.swing.JPanel toolPanel;
    // End of variables declaration//GEN-END:variables

    private class ImpressionWordListCellRenderer extends JLabel implements ListCellRenderer<String> {

        private static final long serialVersionUID = 1L;
        private final ColorImpressionKnowledge ci;

        public ImpressionWordListCellRenderer(ColorImpressionKnowledge ci, float fontSize) {
            setOpaque(true);
            this.ci = ci;
            setFont(getFont().deriveFont(Font.PLAIN, fontSize));
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(ci.toPrintName(value.toString()));
            setIcon(new ImageIcon(ci.getHistogramImage(value.toString())));
            setVerticalAlignment(TOP);
            setHorizontalAlignment(CENTER);
            setVerticalTextPosition(BOTTOM);
            setHorizontalTextPosition(CENTER);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            return this;
        }
    }
}