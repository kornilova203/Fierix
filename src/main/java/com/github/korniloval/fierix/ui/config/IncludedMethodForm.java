package com.github.korniloval.fierix.ui.config;

import javax.swing.*;
import java.awt.*;

public class IncludedMethodForm {
    public ExcludedMethodForm excludedMethodForm;
    public JCheckBox saveReturnValueCheckBox;
    private JPanel mainPanel;

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 0));
        excludedMethodForm = new ExcludedMethodForm();
        mainPanel.add(excludedMethodForm.$$$getRootComponent$$$(), BorderLayout.CENTER);
        saveReturnValueCheckBox = new JCheckBox();
        saveReturnValueCheckBox.setText("Save return value");
        mainPanel.add(saveReturnValueCheckBox, BorderLayout.SOUTH);
    }

    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
