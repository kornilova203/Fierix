package com.github.korniloval.fierix.ui.config.decorator.actions;

import com.github.korniloval.fierix.ui.config.ConfigCheckboxTree;
import com.github.korniloval.fierix.ui.config.tree.nodes.ConfigCheckedTreeNode;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.AnActionButtonRunnable;
import com.intellij.util.ui.tree.TreeUtil;

public class RemoveNodeActionButton implements AnActionButtonRunnable {
    private ConfigCheckboxTree tree;

    public RemoveNodeActionButton(ConfigCheckboxTree tree) {
        this.tree = tree;
    }

    @Override
    public void run(AnActionButton anActionButton) {
        ConfigCheckedTreeNode selectedNode = tree.getSelectedNode();
        if (selectedNode != null) {
            selectedNode.removeItselfFromConfigsSet();
            tree.removeNode(selectedNode);
            TreeUtil.expandAll(tree);
        }
    }
}
