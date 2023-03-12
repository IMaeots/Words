package com.imaeots.wordcounter.entities.shell;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.io.File;

public class DragAndDropFileFrame extends JFrame {
    public boolean isReady = false;
    public StringBuilder data = new StringBuilder();

    public DragAndDropFileFrame() {
        super("Words - Text file statistics");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("src/resources/drag.png");
        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(200,200, Image.SCALE_SMOOTH));
        JLabel label = new JLabel(scaledIcon);

        // add Jlabel to JFrame
        add(label);

        getContentPane().add(label, BorderLayout.CENTER);

        // Drop target
        new DropTarget(label, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                try {
                    // Accept
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    // Get
                    Transferable transferable = event.getTransferable();
                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        @SuppressWarnings("unchecked") File file = ((java.util.List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor)).get(0);
                        // Open and add file data
                        java.nio.file.Files.lines(file.toPath()).forEach(line -> data.append(line + "\n"));
                    }

                    // Mark the drop as complete
                    event.dropComplete(true);
                    isReady = true;
                    
                } catch (Exception e) {
                    // Something went wrong
                    e.printStackTrace();
                    event.rejectDrop();
                }
                
            }
        });
        setVisible(true);
    }
    public StringBuilder getData() {
        return data;
    }
}

