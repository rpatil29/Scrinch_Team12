/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.scrinch.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.scrinch.model.Member;
import org.scrinch.model.ScrinchEnvToolkit;

/**
 *
 * @author christian
 */
public abstract class WindowUtilities {

    public static void performBackgroundAction(final Runnable action, Window parent) {
        performBackgroundAction(action, null, parent);
    }

    public static void performBackgroundAction(final Runnable action, final String waitingMessage, Window parent) {
        if (waitingMessage != null) {
            final WaitWindow waitWindow = new WaitWindow(parent, waitingMessage);
            waitWindow.setVisible(true);
            Thread t = new Thread(new Runnable() {

                public void run() {
                    try {
                        action.run();
                    } catch (Error e) {
                        ScrinchEnvToolkit.logger.log(Level.SEVERE, "could not perform background action", e);
                        ScrinchEnvToolkit.exit(-4);
                    } finally {
                        waitWindow.setVisible(false);
                        waitWindow.dispose();
                    }
                }
            });
            t.setPriority(Thread.MIN_PRIORITY);
            t.start();
        } else {
            EventQueue.invokeLater(action);
        }
    }

    public static void centerFrame(Window window, Window reference) {
        Dimension windowSize = window.getSize();
        int minX = 0;
        int minY = 0;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension availableSize = null;
        if (reference == null) {
            availableSize = screenSize;
        } else {
            availableSize = reference.getSize();
            minX = reference.getLocation().x;
            minY = reference.getLocation().y;
        }
        int xposition = minX + (int) (availableSize.getWidth() - windowSize.getWidth()) / 2;
        if (xposition < minX) {
            xposition = minX;
        }
        if ((xposition + windowSize.getWidth()) > screenSize.getWidth()) {
            xposition = (int) (screenSize.getWidth() - windowSize.getWidth());
        }
        if (xposition < 0) {
            xposition = 0;
        }
        int yposition = minY + (int) (availableSize.getHeight() - windowSize.getHeight()) / 2;
        if (yposition < minY) {
            yposition = minY;
        }
        if ((yposition + windowSize.getHeight()) > screenSize.getHeight()) {
            yposition = (int) (screenSize.getHeight() - windowSize.getHeight());
        }
        if (yposition < 0) {
            yposition = 0;
        }
        window.setLocation(xposition, yposition);
    }

    public static void removeAllComponentsRecursivly(Container container) {
        Component[] components = container.getComponents();
        container.removeAll();
        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof Container) {
                removeAllComponentsRecursivly((Container) components[i]);
            }
        }
    }

    public static boolean showOKCancelModalDialog(Window parent, JPanel content, String title) {
        final boolean r[] = new boolean[1];
        final JDialog dialog = new JDialog(parent, title, JDialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel okCancelPanel = new JPanel();
        JButton okButton = new JButton();
        okButton.setText("OK");
        okButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                r[0] = true;
                dialog.dispose();
            }
        });
        okCancelPanel.add(okButton);
        JButton cancelButton = new JButton();
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        cancelButton.setText("CANCEL");
        okCancelPanel.add(cancelButton);
        dialog.getContentPane().add(content, BorderLayout.CENTER);
        dialog.getContentPane().add(okCancelPanel, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setResizable(false);
        WindowUtilities.centerFrame(dialog, parent);
        dialog.setVisible(true);
        return r[0];
    }

    public static void showCloseOnlyModalDialog(Window parent, JPanel content, String title) {
        final JDialog dialog = new JDialog(parent, title, JDialog.ModalityType.APPLICATION_MODAL);
        dialog.getContentPane().setLayout(new BorderLayout(0, 0));
        JPanel closePanel = new JPanel();
        dialog.getContentPane().add(content, BorderLayout.CENTER);
        dialog.pack();
        dialog.setResizable(false);
        WindowUtilities.centerFrame(dialog, parent);
        dialog.setVisible(true);
    }

    public static JPanel createMembersSelectionPanel(Collection<Member> availableMembers, final Collection<Member> selectedMembersHolder, final Collection<Member> currentlySelectedMembers) {
        JPanel membersPanel = new JPanel();
        membersPanel.setLayout(new GridBagLayout());
        Insets insets = new Insets(5, 2, 5, 2);
        int row = 0;
        for (final Member member : availableMembers) {
            final JCheckBox checkbox = new JCheckBox();
            if( currentlySelectedMembers!=null && currentlySelectedMembers.contains(member) ){
                checkbox.setSelected(true);
                selectedMembersHolder.add(member);
            }
            final Runnable updateSelectedMembers = new Runnable() {

                public void run() {
                    if (checkbox.isSelected()) {
                        selectedMembersHolder.add(member);
                    } else {
                        selectedMembersHolder.remove(member);
                    }
                }
            };
            checkbox.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    updateSelectedMembers.run();
                }
            });
            GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = row;
            constraints.insets = insets;
            membersPanel.add(checkbox, constraints);

            MouseListener listener = new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    checkbox.setSelected(!checkbox.isSelected());
                    updateSelectedMembers.run();
                }
            };
            JLabel id = new JLabel(member.getNickname());
            id.addMouseListener(listener);
            constraints = new GridBagConstraints();
            constraints.gridx = 1;
            constraints.gridy = row;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.insets = insets;
            membersPanel.add(id, constraints);

            JLabel separator = new JLabel(" :");
            separator.setPreferredSize(new Dimension(30, 14));
            constraints = new GridBagConstraints();
            constraints.gridx = 2;
            constraints.gridy = row;
            constraints.insets = insets;
            membersPanel.add(separator, constraints);

            JLabel fullName = new JLabel(member.getFullName());
            fullName.addMouseListener(listener);
            constraints = new GridBagConstraints();
            constraints.gridx = 3;
            constraints.gridy = row;
            constraints.fill = GridBagConstraints.BOTH;
            constraints.weightx = 1.0;
            constraints.insets = insets;
            membersPanel.add(fullName, constraints);

            row++;
        }
        return membersPanel;
    }
}
