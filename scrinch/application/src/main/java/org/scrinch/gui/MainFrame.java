/*
Scrinch is a stand-alone Swing application that helps managing your
projects based on Agile principles.
Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluckiger,
Christian Lebaudy, Jean-Marc Borer
Scrinch is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.
Scrinch is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.scrinch.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.scrinch.Main;
import org.scrinch.gui.reporting.GlobalReportPanel;
import org.scrinch.gui.reporting.VelocityHistoryPanel;
import org.scrinch.gui.reporting.WorkloadGraphPanel;
import org.scrinch.model.ProjectItemSetFactory;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.ScrinchException;
import org.scrinch.model.UndoManager;
import org.scrinch.ostools.OSXAdapter;

public class MainFrame extends javax.swing.JFrame {

    private static final ImageIcon WINDOW_ICON = new ImageIcon(MainFrame.class.getResource("/org/scrinch/gui/icon32x32.png"));
    private static final String UNSAVED_DATA_TXT = "New unsaved scrinch";
    private File currentFile;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    private transient Thread updateMemoryThread = null;

    private void addMacHandlers() {
        if( OSXAdapter.MAC_OS_X ){
            try {
                OSXAdapter.setQuitHandler(this, getClass().getDeclaredMethod("exit", (Class[]) null));
                OSXAdapter.setAboutHandler(this, getClass().getDeclaredMethod("about", (Class[]) null));
            } catch (NoSuchMethodException nsme) {
                ScrinchEnvToolkit.logger.log(Level.SEVERE, "Error while loading the OSXAdapter", nsme);
            }
        }
    }

    private void forceLaf() {
        try {
            UIManager.put("ComboBox.disabledForeground", Color.BLACK);
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//            UIManager.setLookAndFeel("net.java.plaf.windows.WindowsLookAndFeel");
//            SwingUtilities.updateComponentTreeUI(this);
        } catch (Throwable t) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "Force LAF could not be done", t);
        }
    }
    public final ScrinchEnvToolkit toolkit;

    public MainFrame(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        toolkit.addListener(new ScrinchEnvToolkit.Listener() {

            public void preferencesChanged() {
                MainFrame.this.preferencesChanged();
            }

            public void dataLoaded() {
            }

            public void dataChanged() {
            }
        });
        init();
        preferencesChanged();
    }

    public final void preferencesChanged() {
        this.oldProjectsVisibleCheckBox.setSelected(toolkit.isOldProjectsAndSprintsVisible());
    }

    private void initOldProjectsVisibleCheckbox() {
        this.oldProjectsVisibleCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.toggleOldProjectsVisible();
            }
        });
    }

    private void init() {
        forceLaf();
        
        initComponents();
        centerPanel.setSelectedComponent(sprintsMainPanel);
        this.setIconImage(WINDOW_ICON.getImage());
        addMacHandlers();
        Dimension size = decreaseScreenSize();
        setPreferredSize(size);
        //setPreferredSize(new Dimension(800,800));
        ScrinchGuiToolkit.centerFrame(this);
        UndoKeyboardFocusManager manager = new UndoKeyboardFocusManager();
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(manager);

        initOldProjectsVisibleCheckbox();

        this.updateMemoryThread = new Thread() {

            @Override
            public void run() {
                String windowTitle = getTitle();
                while (true) {
                    try {
                        long maxMem = Runtime.getRuntime().maxMemory();
                        long usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                        int percent = (int) (usedMem * 100 / maxMem);
                        String memory = "        (memory " + usedMem / 1000 + "/" + maxMem / 1000 + "Kb -> " + percent + "% used)";
                        setTitle(windowTitle + memory);
                        System.gc();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        ScrinchEnvToolkit.logger.log(Level.SEVERE, "Failed to create main frame", e);
                    } catch (Throwable e) {
                        ScrinchEnvToolkit.logger.log(Level.SEVERE, "Failed to create main frame", e);
                        ScrinchEnvToolkit.exit(-3);
                    }
                }
            }
        };
        this.lbStatus.setText(UNSAVED_DATA_TXT);
        this.updateMemoryThread.start();
    }

    private Dimension decreaseScreenSize() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        return new Dimension((int) (dim.getWidth() * 0.9),
                (int) (dim.getHeight() * 0.9));
    }

    public void open() throws ScrinchException {
        JFileChooser fileChooser = getScrinchFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                currentPath = file.getParentFile();
                open(file);
            }
        }
    }
    private static File currentPath = null;

    private static JFileChooser getScrinchFileChooser() {
        File path = currentPath;
        if (path == null) {
            path = new File(".");
        }
        JFileChooser fileChooser = new JFileChooser(path);
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("SCRINCH file", "scrinch");
        fileChooser.setFileFilter(filter);
        return fileChooser;
    }

    private void cleanEnv() {
        ScrinchEnvToolkit.logger.info("WorkTypes will be removed");
        toolkit.getWorkTypeFactory().disposeAll();
        ScrinchEnvToolkit.logger.info("OriginTypes will be removed");
        toolkit.getOriginTypeFactory().disposeAll();
        ScrinchEnvToolkit.logger.info("Members will be removed");
        toolkit.getMemberFactory().disposeAll();
        ScrinchEnvToolkit.logger.info("Items will be removed");
        toolkit.getItemFactory().disposeAll();
        ScrinchEnvToolkit.logger.info("ProjectItemSet will be removed");
        ProjectItemSetFactory.getInstance().disposeAll();
        ScrinchEnvToolkit.logger.info("Project will be removed");
        toolkit.getProjectFactory().disposeAll();
        ScrinchEnvToolkit.logger.info("Sprints will be removed");
        toolkit.getSprintFactory().disposeAll();
        ScrinchEnvToolkit.logger.info("Targets will be removed");
        toolkit.getTargetFactory().disposeAll();
    }

    public void open(File fileToOpen) throws ScrinchException {
        try {

            cleanEnv();

            this.currentFile = fileToOpen;
            if (fileToOpen != null && fileToOpen.exists()) {
                Main.setUserProperty(Main.LAST_USED_FILE, fileToOpen.getPath());
                ScrinchEnvToolkit.logger.info("Loading data");
                toolkit.open(fileToOpen);
                this.lbStatus.setText("Opened file is " + fileToOpen.getPath() + " at " + sdf.format(new Date()));
                if(toolkit.getSprintFactory().findTopicalSprints().isEmpty()){
                    toolkit.setOldProjectsAndSprintsVisible(true);
                }
            }
        } catch (ScrinchException se) {
            throw se;
        } catch (Throwable t) {
            throw new ScrinchException("Unable to open scrinch env", t);
        }
    }

    private void saveAs() throws ScrinchException {
        try {
            JFileChooser fileChooser = getScrinchFileChooser();
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                currentPath = file.getParentFile();
                if (!file.exists() || file.canWrite()) {
                    String name = file.getName();
                    if (!name.endsWith(".scrinch")) {
                        name = name + ".scrinch";
                    }
                    this.currentFile = new File(file.getParentFile(), name).getCanonicalFile();
                    toolkit.save(this.currentFile);
                    Main.setUserProperty(Main.LAST_USED_FILE, file.getPath());
                    this.lbStatus.setText("Saved as " + this.currentFile.getPath() + " at " + sdf.format(new Date()));
                } else {
                    throw new ScrinchException("File is read-only!");
                }
            }
        } catch (ScrinchException se) {
            throw se;
        } catch (Throwable t) {
            throw new ScrinchException("Unable to save scrinch env", t);
        }
    }

    private void save() throws ScrinchException {
        if (this.currentFile != null) {
            try {
                if (this.currentFile.canWrite()) {
                    toolkit.save(this.currentFile);
                    this.lbStatus.setText("Saved " + this.currentFile.getPath() + " at " + sdf.format(new Date()));
                } else {
                    throw new ScrinchException("File is read-only!");
                }
            } catch (ScrinchException se) {
                throw se;
            } catch (Throwable t) {
                throw new ScrinchException("Unable to save scrinch env", t);
            }
        } else {
            saveAs();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        centerPanel = new javax.swing.JTabbedPane();
        projectsMainPanel = new ProjectsMainPanel(toolkit);
        sprintsMainPanel = new SprintsMainPanel(toolkit);
        searchMainPanel = new SearchMainPanel(toolkit);
        membersMainPanel = new MembersMainPanel(toolkit);
        targetsMainPanel = new TargetsMainPanel(toolkit);
        toolbar = new javax.swing.JPanel();
        mainToolbar = new javax.swing.JPanel();
        btNew = new javax.swing.JButton();
        btOpen = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        btSaveAs = new javax.swing.JButton();
        statsToolbar = new javax.swing.JPanel();
        btReport = new javax.swing.JButton();
        btVelocityHistory = new javax.swing.JButton();
        btWorkLoadByCriterion = new javax.swing.JButton();
        statusBar = new javax.swing.JPanel();
        lbStatus = new javax.swing.JLabel();
        mainMenuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newMenuItem = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveasMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        preferencesItem = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        exitMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        oldProjectsVisibleCheckBox = new javax.swing.JCheckBoxMenuItem();
        help = new javax.swing.JMenu();
        about = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("SCRINCH : simply Agile");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                scrinchWindowClosing(evt);
            }
        });

        centerPanel.setOpaque(true);
        centerPanel.addTab(" Projects ", new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/33-cabinet.png")), projectsMainPanel); // NOI18N
        centerPanel.addTab(" Sprints ", new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/63-runner.png")), sprintsMainPanel); // NOI18N
        centerPanel.addTab(" Search ", new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/06-magnify.png")), searchMainPanel); // NOI18N
        centerPanel.addTab(" Members ", new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/112-group.png")), membersMainPanel); // NOI18N
        centerPanel.addTab(" Targets ", new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/13-target.png")), targetsMainPanel); // NOI18N

        getContentPane().add(centerPanel, java.awt.BorderLayout.CENTER);

        toolbar.setMinimumSize(new java.awt.Dimension(100, 36));
        toolbar.setPreferredSize(new java.awt.Dimension(100, 40));
        toolbar.setLayout(new java.awt.BorderLayout());

        btNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/document-new.png"))); // NOI18N
        btNew.setToolTipText("New");
        btNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNew.setPreferredSize(new java.awt.Dimension(32, 32));
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        mainToolbar.add(btNew);

        btOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/document-open.png"))); // NOI18N
        btOpen.setToolTipText("Open");
        btOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btOpen.setPreferredSize(new java.awt.Dimension(32, 32));
        btOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenActionPerformed(evt);
            }
        });
        mainToolbar.add(btOpen);

        btSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/document-save.png"))); // NOI18N
        btSave.setToolTipText("Save");
        btSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSave.setPreferredSize(new java.awt.Dimension(32, 32));
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        mainToolbar.add(btSave);

        btSaveAs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/document-save-as.png"))); // NOI18N
        btSaveAs.setToolTipText("Save as...");
        btSaveAs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSaveAs.setPreferredSize(new java.awt.Dimension(32, 32));
        btSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveAsActionPerformed(evt);
            }
        });
        mainToolbar.add(btSaveAs);

        toolbar.add(mainToolbar, java.awt.BorderLayout.WEST);

        statsToolbar.setLayout(new java.awt.GridBagLayout());

        btReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/81-dashboard.png"))); // NOI18N
        btReport.setText("Global report");
        btReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReportActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        statsToolbar.add(btReport, gridBagConstraints);

        btVelocityHistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/16-line-chart.png"))); // NOI18N
        btVelocityHistory.setText("Velocity history");
        btVelocityHistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btVelocityHistoryActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        statsToolbar.add(btVelocityHistory, gridBagConstraints);

        btWorkLoadByCriterion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/17-bar-chart.png"))); // NOI18N
        btWorkLoadByCriterion.setText("Workload distribution");
        btWorkLoadByCriterion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btWorkLoadByCriterionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        statsToolbar.add(btWorkLoadByCriterion, gridBagConstraints);

        toolbar.add(statsToolbar, java.awt.BorderLayout.EAST);

        getContentPane().add(toolbar, java.awt.BorderLayout.NORTH);

        statusBar.setPreferredSize(new java.awt.Dimension(100, 24));
        statusBar.setLayout(new java.awt.BorderLayout());

        lbStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbStatus.setText("...");
        statusBar.add(lbStatus, java.awt.BorderLayout.CENTER);

        getContentPane().add(statusBar, java.awt.BorderLayout.SOUTH);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        newMenuItem.setMnemonic('n');
        newMenuItem.setText("New...");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newMenuItem);

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("Open...");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        saveasMenuItem.setText("Save as...");
        saveasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveasMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveasMenuItem);
        fileMenu.add(jSeparator1);

        preferencesItem.setMnemonic('n');
        preferencesItem.setText("Preferences");
        preferencesItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preferencesItemActionPerformed(evt);
            }
        });
        fileMenu.add(preferencesItem);
        fileMenu.add(jSeparator2);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        mainMenuBar.add(fileMenu);

        viewMenu.setText("View");

        oldProjectsVisibleCheckBox.setText("See old projects/sprints");
        viewMenu.add(oldProjectsVisibleCheckBox);

        mainMenuBar.add(viewMenu);

        help.setMnemonic('H');
        help.setText("Help");

        about.setText("About...");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        help.add(about);

        mainMenuBar.add(help);

        setJMenuBar(mainMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        about();
    }//GEN-LAST:event_aboutActionPerformed

    public void about() {
        new AboutBox(MainFrame.this, "Running Scrinch version : " + Main.VERSION, "About Scrinch").setVisible(true);
    }

    private void btWorkLoadByCriterionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btWorkLoadByCriterionActionPerformed
        JPanel panel = new WorkloadGraphPanel(null, toolkit);
        WindowUtilities.showCloseOnlyModalDialog(this, panel, "Workload by criterion graph");
    }//GEN-LAST:event_btWorkLoadByCriterionActionPerformed

    private void btVelocityHistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btVelocityHistoryActionPerformed
        JPanel panel = new VelocityHistoryPanel(toolkit);
        WindowUtilities.showCloseOnlyModalDialog(this, panel, "Velocity history");
    }//GEN-LAST:event_btVelocityHistoryActionPerformed

    private class UndoKeyboardFocusManager implements KeyEventDispatcher {

        public boolean dispatchKeyEvent(KeyEvent event) {
            if (event.getID() == KeyEvent.KEY_PRESSED
                    && event.getKeyCode() == KeyEvent.VK_Z
                    && event.isControlDown()) {
                UndoManager.getInstance().undoLastEvent();
                return true;
            }
            return false;
        }
    }

    private void toggleOldProjectsVisible() {
        boolean isOldSelected = oldProjectsVisibleCheckBox.getModel().isSelected();
        try {
            toolkit.setOldProjectsAndSprintsVisible(isOldSelected);
            toolkit.preferencesChanged();
        } catch (Error e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "could not switch to old projects mode", e);
            ScrinchEnvToolkit.exit(-1);
        }
    }

    private void scrinchWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_scrinchWindowClosing
        exit();
    }//GEN-LAST:event_scrinchWindowClosing

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        exit();
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void btReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReportActionPerformed
        showReport();
    }//GEN-LAST:event_btReportActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        try {
            save();
        } catch (Throwable t) {
            handleThrowable(t);
        }
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        try {
            save();
        } catch (Throwable t) {
            handleThrowable(t);
        }
    }//GEN-LAST:event_btSaveActionPerformed

    private void btOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenActionPerformed
        openFile();
    }//GEN-LAST:event_btOpenActionPerformed

    private void btSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveAsActionPerformed
        try {
            saveAs();
        } catch (Throwable t) {
            handleThrowable(t);
        }
    }//GEN-LAST:event_btSaveAsActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        openFile();
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveasMenuItemActionPerformed
        try {
            saveAs();
        } catch (Throwable t) {
            handleThrowable(t);
        }
    }//GEN-LAST:event_saveasMenuItemActionPerformed

    private boolean checkSaveNeededAndContinue() {
        try {
            if (toolkit.isSomethingToSave()
                    && toolkit.isFileSaveNeeded()) {
                int optionChoosen = JOptionPane.showConfirmDialog(this,
                        "Do you want to save?",
                        currentFile != null ? currentFile.getName() : "[NO_FILE_NAME]",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (optionChoosen == JOptionPane.CANCEL_OPTION) {
                    return false;
                } else if (optionChoosen == JOptionPane.OK_OPTION) {
                    if (this.currentFile != null) {
                        save();
                    } else {
                        saveAs();
                    }
                }
            }
        } catch (Throwable t) {
            handleThrowable(t);
        }
        return true;
    }

    public boolean exit() {
        try {
            boolean continueAfterSave = checkSaveNeededAndContinue();
            if (continueAfterSave) {
                ScrinchEnvToolkit.exit(0);
            }
        } catch (Throwable e) {
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "exiting failed", e);
        }
        return false;
    }

    private void openFile() {
        try {
            boolean continueAfterSave = checkSaveNeededAndContinue();
            if (continueAfterSave) {
                open();
            }
        } catch (Throwable t) {
            handleThrowable(t);
        }
    }

    private void newEnv() {
        try {
            this.currentFile = null;
            boolean continueAfterSave = checkSaveNeededAndContinue();
            if (continueAfterSave) {
                cleanEnv();
                this.lbStatus.setText(UNSAVED_DATA_TXT);
            }
        } catch (Throwable t) {
            handleThrowable(t);
        }
    }

    private void handleThrowable(Throwable t) {
        t.printStackTrace();
        JOptionPane.showMessageDialog(this, t.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
    }

    private void showReport() {
        JPanel panel = new GlobalReportPanel(toolkit);
        WindowUtilities.showCloseOnlyModalDialog(this, panel, "Global report graph");
    }

    private void showPreferencesDialog() {
        PreferencesPanel dialog = new PreferencesPanel(toolkit);
        WindowUtilities.showCloseOnlyModalDialog(this, dialog, "Preferences");
    }

private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
    newEnv();
}//GEN-LAST:event_newMenuItemActionPerformed

private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
    newEnv();
}//GEN-LAST:event_btNewActionPerformed

private void preferencesItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preferencesItemActionPerformed
    showPreferencesDialog();
}//GEN-LAST:event_preferencesItemActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem about;
    private javax.swing.JButton btNew;
    private javax.swing.JButton btOpen;
    private javax.swing.JButton btReport;
    private javax.swing.JButton btSave;
    private javax.swing.JButton btSaveAs;
    private javax.swing.JButton btVelocityHistory;
    private javax.swing.JButton btWorkLoadByCriterion;
    private javax.swing.JTabbedPane centerPanel;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu help;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbStatus;
    private javax.swing.JMenuBar mainMenuBar;
    private javax.swing.JPanel mainToolbar;
    private org.scrinch.gui.MembersMainPanel membersMainPanel;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JCheckBoxMenuItem oldProjectsVisibleCheckBox;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem preferencesItem;
    private org.scrinch.gui.ProjectsMainPanel projectsMainPanel;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem saveasMenuItem;
    private org.scrinch.gui.SearchMainPanel searchMainPanel;
    private org.scrinch.gui.SprintsMainPanel sprintsMainPanel;
    private javax.swing.JPanel statsToolbar;
    private javax.swing.JPanel statusBar;
    private org.scrinch.gui.TargetsMainPanel targetsMainPanel;
    private javax.swing.JPanel toolbar;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables
}
