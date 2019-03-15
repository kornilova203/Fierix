package com.github.korniloval.fierix.ui.config.decorator.actions;

import com.github.korniloval.fierix.ui.config.ConfigCheckboxTree;
import com.github.korniloval.fierix.ui.config.tree.nodes.ConfigCheckedTreeNode;
import com.github.korniloval.fierix.ui.config.tree.nodes.MethodTreeNode;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.util.PlatformIcons;

public class CopyAction extends AnAction {
    private ConfigCheckboxTree tree;

    public CopyAction(ConfigCheckboxTree tree) {
        super("Copy pattern", "Copy pattern", PlatformIcons.COPY_ICON);
        this.tree = tree;
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        ConfigCheckedTreeNode node = tree.getSelectedNode();
        if (node instanceof MethodTreeNode) {
            tree.duplicate(((MethodTreeNode) node));
        }
    }

    @Override
    public void update(AnActionEvent e) {
        ConfigCheckedTreeNode node = tree.getSelectedNode();
        e.getPresentation().setEnabled(node instanceof MethodTreeNode);
    }
}
