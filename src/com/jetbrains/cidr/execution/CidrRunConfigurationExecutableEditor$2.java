// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import com.intellij.ide.NativeIconProvider;
import com.intellij.ui.SimpleColoredComponent;
import javax.swing.JList;
import com.intellij.openapi.options.ConfigurationException;
import com.jetbrains.cidr.lang.OCLog;
import javax.swing.JComboBox;
import org.jetbrains.annotations.Nullable;
import java.awt.Component;
import javax.swing.JLabel;
import com.intellij.util.ui.GridBag;
import javax.swing.JPanel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.util.Collections;
import java.util.Collection;
import java.util.ArrayList;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ListCellRenderer;
import com.jetbrains.cidr.CidrBundle;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.project.Project;
import java.io.File;
import java.util.Comparator;

class CidrRunConfigurationExecutableEditor$2 implements Comparator<Object> {
    @Override
    public int compare(final Object o, final Object o2) {
        return this.a(o) - this.a(o2);
    }
    
    private int a(final Object o) {
        if (o == null) {
            return 0;
        }
        if (o == CidrRunConfigurationExecutableEditor.this.SELECT_EXTERNAL_EXECUTABLE) {
            return 10;
        }
        final Class<?> class1 = o.getClass();
        if (CidrBuildTarget.class.isAssignableFrom(class1)) {
            return 1;
        }
        if (CidrRunConfigurationSettingsEditor.InvalidItem.class.isAssignableFrom(class1)) {
            return 2;
        }
        if (File.class.isAssignableFrom(class1)) {
            return 3;
        }
        return 4;
    }
}