// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.ui;

import java.awt.Component;
import javax.swing.AbstractButton;
import com.intellij.ui.components.JBRadioButton;
import javax.swing.ButtonGroup;
import org.jetbrains.annotations.NotNull;
import javax.swing.JPanel;

public class CMakeProjectUiUtils
{
    public static void addLanguageSelectButton(@NotNull final JPanel panel, @NotNull final ButtonGroup buttonGroup, @NotNull final String s, @NotNull final String s2) {
        try {
            if (panel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mainPanel", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (buttonGroup == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buttonGroup", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageName", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageVersion", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        addLanguageSelectButton(panel, buttonGroup, s, s2, false);
    }
    
    public static void addLanguageSelectButton(@NotNull final JPanel panel, @NotNull final ButtonGroup buttonGroup, @NotNull final String s, @NotNull final String actionCommand, final boolean b) {
        try {
            if (panel == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mainPanel", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (buttonGroup == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buttonGroup", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageName", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (actionCommand == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageVersion", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/settings/ui/CMakeProjectUiUtils", "addLanguageSelectButton"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final JBRadioButton jbRadioButton = new JBRadioButton(s + actionCommand, true);
        try {
            ((AbstractButton)jbRadioButton).setActionCommand(actionCommand);
            buttonGroup.add((AbstractButton)jbRadioButton);
            if (b) {
                ((AbstractButton)jbRadioButton).setSelected(true);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        panel.add((Component)jbRadioButton);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
