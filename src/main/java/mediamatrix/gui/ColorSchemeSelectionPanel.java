package mediamatrix.gui;

import mediamatrix.munsell.ColorImpressionKnowledge;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ColorSchemeSelectionPanel extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private List<JCheckBox> checkBoxList;
    private ColorImpressionKnowledge ci;

    public ColorSchemeSelectionPanel() {
        initComponents();
    }

    public void setColorScheme(ColorImpressionKnowledge ci) {
        this.ci = ci;
        final String[] words = ci.getWords();
        mainPanel.setVisible(false);
        mainPanel.removeAll();
        checkBoxList = new ArrayList<JCheckBox>();
        mainPanel.setLayout(new java.awt.GridLayout(words.length, 2));
        for (int i = 0; i < words.length; i++) {
            final String colorSchemeName = words[i];
            final JPanel panel = new JPanel(true);
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            final JLabel label = new JLabel();
            final BufferedImage image = ci.getHistogramImage(colorSchemeName);
            label.setIcon(new ImageIcon(image));
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.LEFT);
            label.setBorder(BorderFactory.createEtchedBorder());
            label.setSize(image.getWidth() + 3, image.getHeight() + 3);
            JCheckBox checkBox = new JCheckBox(ci.toPrintName(colorSchemeName));
            checkBox.setSelected(true);
            checkBox.setFont(label.getFont().deriveFont(Font.PLAIN, 12));
            panel.add(label);
            panel.add(checkBox);
            checkBoxList.add(checkBox);
            mainPanel.add(panel);
            checkBox.addActionListener(checkBoxListener);
        }
        mainPanel.setVisible(true);
        updateSelection();
    }

    public List<String> getSelectedWords() {
        final String[] words = ci.getWords();
        final List<String> result = new ArrayList<String>();
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i).isSelected()) {
                result.add(words[i]);
            }
        }
        return result;
    }

    private void updateSelection() {
        final String[] words = ci.getWords();
        final List<String> result = new ArrayList<String>();
        for (int i = 0; i < checkBoxList.size(); i++) {
            if (checkBoxList.get(i).isSelected()) {
                result.add(words[i]);
            }
        }
        final PropertyChangeListener[] listeners = getPropertyChangeListeners("colorschema");
        for (int i = 0; i < listeners.length; i++) {
            listeners[i].propertyChange(new PropertyChangeEvent(this, "colorschema", null, result));
        }
    }
    private final ActionListener checkBoxListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateSelection();
        }
    };

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainToolBar = new javax.swing.JToolBar();
        selectButton = new javax.swing.JButton();
        unselectButton = new javax.swing.JButton();
        mainScrollPane = new javax.swing.JScrollPane();
        mainPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        mainToolBar.setRollover(true);

        selectButton.setText("Select all");
        selectButton.setFocusable(false);
        selectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        selectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        selectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(selectButton);

        unselectButton.setText("Unselect all");
        unselectButton.setFocusable(false);
        unselectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        unselectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        unselectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unselectButtonActionPerformed(evt);
            }
        });
        mainToolBar.add(unselectButton);

        add(mainToolBar, java.awt.BorderLayout.PAGE_START);

        mainScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        mainScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.Y_AXIS));
        mainScrollPane.setViewportView(mainPanel);

        add(mainScrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void selectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectButtonActionPerformed
        for (int i = 0; i < checkBoxList.size(); i++) {
            checkBoxList.get(i).setSelected(true);
        }
        updateSelection();
    }//GEN-LAST:event_selectButtonActionPerformed

    private void unselectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unselectButtonActionPerformed
        for (int i = 0; i < checkBoxList.size(); i++) {
            checkBoxList.get(i).setSelected(false);
        }
        updateSelection();
    }//GEN-LAST:event_unselectButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    private javax.swing.JScrollPane mainScrollPane;
    private javax.swing.JToolBar mainToolBar;
    private javax.swing.JButton selectButton;
    private javax.swing.JButton unselectButton;
    // End of variables declaration//GEN-END:variables
}