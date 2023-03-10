package com.imaeots.wordcounter.entities.shell;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class DragAndDropFileFrame extends JFrame {
    private JTextArea textArea;
    public boolean isReady = false;
    public String filePath;
    public String data;

    public DragAndDropFileFrame() {
        super("Words");

        textArea = new JTextArea();
        getContentPane().add(textArea, BorderLayout.CENTER);

        // Drop target
        new DropTarget(textArea, new DropTargetAdapter() {
            @Override
            public void drop(DropTargetDropEvent event) {
                try {
                    // Accept
                    event.acceptDrop(DnDConstants.ACTION_COPY);

                    // Get
                    Transferable transferable = event.getTransferable();
                    if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                        File file = ((java.util.List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor)).get(0);

                        // Open and add file data
                        java.nio.file.Files.lines(file.toPath()).forEach(line -> textArea.append(line + "\n"));
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

    }
}
