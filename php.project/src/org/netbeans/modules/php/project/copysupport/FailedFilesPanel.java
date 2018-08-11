/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.php.project.copysupport;

import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.awt.Mnemonics;
import org.openide.util.NbBundle;

/**
 * UI for failed files.
 */
@org.netbeans.api.annotations.common.SuppressWarnings("SE_BAD_FIELD_STORE")
public final class FailedFilesPanel extends JPanel {

    private static final long serialVersionUID = 146876432354L;

    private final ListModel<String> failedFilesListModel;


    private FailedFilesPanel(boolean local, List<String> failedFiles) {
        failedFilesListModel = new FailedFilesListModel(failedFiles);
        initComponents();
        init(local);
    }

    public static void local(String projectName, List<String> failedFiles) {
        show(true, projectName, failedFiles);
    }

    public static void remote(String projectName, List<String> failedFiles) {
        show(false, projectName, failedFiles);
    }

    private static void show(boolean local, String projectName, List<String> failedFiles) {
        FailedFilesPanel panel = new FailedFilesPanel(local, failedFiles);
        DialogDescriptor descriptor = new DialogDescriptor(
                panel,
                projectName,
                true,
                new Object[] {DialogDescriptor.OK_OPTION},
                DialogDescriptor.OK_OPTION,
                DialogDescriptor.DEFAULT_ALIGN,
                null,
                null);
        DialogDisplayer.getDefault().notify(descriptor);
    }

    @NbBundle.Messages({
        "FailedFilesPanel.title.local=Copy Support failed for:",
        "FailedFilesPanel.title.remote=Upload Files On Save failed for:"
    })
    private void init(boolean local) {
        /// title
        String title;
        if (local) {
            title = Bundle.FailedFilesPanel_title_local();
        } else {
            title = Bundle.FailedFilesPanel_title_remote();
        }
        titleLabel.setText(title);
        // files
        failedFilesList.setModel(failedFilesListModel);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleLabel = new JLabel();
        failedFilesScrollPane = new JScrollPane();
        failedFilesList = new JList<String>();

        Mnemonics.setLocalizedText(titleLabel, "TITLE"); // NOI18N

        failedFilesList.setEnabled(false);
        failedFilesScrollPane.setViewportView(failedFilesList);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(failedFilesScrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(titleLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(ComponentPlacement.RELATED)
                .addComponent(failedFilesScrollPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JList<String> failedFilesList;
    private JScrollPane failedFilesScrollPane;
    private JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    //~ Inner classes

    private static final class FailedFilesListModel implements ListModel<String> {

        private final List<String> failedFiles;


        public FailedFilesListModel(List<String> failedFiles) {
            this.failedFiles = new ArrayList<>(failedFiles);
        }

        @Override
        public int getSize() {
            return failedFiles.size();
        }

        @Override
        public String getElementAt(int index) {
            return failedFiles.get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            // noop
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
            // noop
        }

    }

}