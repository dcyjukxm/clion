// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp;

import com.intellij.ide.util.projectWizard.ModuleBuilder;
import org.jetbrains.jps.model.java.JavaSourceRootType;
import org.jetbrains.jps.model.module.JpsModuleSourceRootType;
import com.intellij.icons.AllIcons;
import javax.swing.Icon;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.module.ModuleTypeManager;
import org.jetbrains.annotations.NonNls;
import com.intellij.ide.util.projectWizard.EmptyModuleBuilder;
import com.intellij.openapi.module.ModuleType;

public class CPPModuleType extends ModuleType<EmptyModuleBuilder>
{
    @NonNls
    public static final String CPP_MODULE = "CPP_MODULE";
    
    @NotNull
    public static ModuleType getInstance() {
        ModuleType byID;
        try {
            byID = ModuleTypeManager.getInstance().findByID("CPP_MODULE");
            if (byID == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPModuleType", "getInstance"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return byID;
    }
    
    public CPPModuleType() {
        super("CPP_MODULE");
    }
    
    @NotNull
    public EmptyModuleBuilder createModuleBuilder() {
        EmptyModuleBuilder emptyModuleBuilder;
        try {
            emptyModuleBuilder = new EmptyModuleBuilder() {
                public ModuleType getModuleType() {
                    return ModuleTypeManager.getInstance().findByID("CPP_MODULE");
                }
            };
            if (emptyModuleBuilder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPModuleType", "createModuleBuilder"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return emptyModuleBuilder;
    }
    
    @NotNull
    public String getName() {
        String s;
        try {
            s = "C++ Module";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPModuleType", "getName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getDescription() {
        String string;
        try {
            string = "Provides facilities to edit " + OCLanguage.getInstance().getDisplayName() + " files";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/CPPModuleType", "getDescription"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return string;
    }
    
    public Icon getNodeIcon(final boolean b) {
        return AllIcons.Modules.SourceFolder;
    }
    
    public boolean isSupportedRootType(final JpsModuleSourceRootType jpsModuleSourceRootType) {
        try {
            if (jpsModuleSourceRootType != JavaSourceRootType.TEST_SOURCE) {
                return true;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
