package com.github.korniloval.fierix.ui.config;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;

public class ShowProfilerDialogAction extends AnAction implements DumbAware {
    @Override
    public void actionPerformed(AnActionEvent e) {
        if (e.getProject() == null) {
            return;
        }
        new ChangeConfigurationDialog(e.getProject()).show();
    }
}
