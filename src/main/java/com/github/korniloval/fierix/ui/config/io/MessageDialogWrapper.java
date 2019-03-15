package com.github.korniloval.fierix.ui.config.io;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MessageDialogWrapper extends DialogWrapper {
    protected MessageDialogWrapper(boolean canBeParent) {
        super(canBeParent);
        setTitle("Import Failed");
        setModal(false);
        init();
        setOKButtonText("OK");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return null;
    }
}
