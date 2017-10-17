// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.ui;

import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import javax.swing.Icon;
import com.intellij.codeInsight.completion.PrefixMatcher;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.intellij.ui.TextFieldWithAutoCompletionListProvider;

public class OCTextFieldCompletionProvider<SUPPLEMENTED> extends TextFieldWithAutoCompletionListProvider<SUPPLEMENTED>
{
    protected final OCFieldAdapter<SUPPLEMENTED> myAdapter;
    
    public OCTextFieldCompletionProvider(@NotNull final OCFieldAdapter<SUPPLEMENTED> myAdapter) {
        if (myAdapter == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "adapter", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "<init>"));
        }
        super(null);
        this.myAdapter = myAdapter;
    }
    
    @NotNull
    @Override
    public PrefixMatcher createPrefixMatcher(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "createPrefixMatcher"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        PrefixMatcher prefixMatcher;
        try {
            prefixMatcher = this.myAdapter.createPrefixMatcher(s);
            if (prefixMatcher == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "createPrefixMatcher"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return prefixMatcher;
    }
    
    @Override
    protected Icon getIcon(@NotNull final SUPPLEMENTED supplemented) {
        try {
            if (supplemented == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "getIcon"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myAdapter.getIcon(supplemented);
    }
    
    @NotNull
    @Override
    protected String getLookupString(@NotNull final SUPPLEMENTED supplemented) {
        try {
            if (supplemented == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "getLookupString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String readableName;
        try {
            readableName = this.myAdapter.getReadableName(supplemented);
            if (readableName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "getLookupString"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return readableName;
    }
    
    @Override
    protected String getTailText(@NotNull final SUPPLEMENTED supplemented) {
        try {
            if (supplemented == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "getTailText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Override
    protected String getTypeText(@NotNull final SUPPLEMENTED supplemented) {
        try {
            if (supplemented == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "getTypeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.myAdapter.getTypeText(supplemented);
    }
    
    @Override
    public int compare(final SUPPLEMENTED supplemented, final SUPPLEMENTED supplemented2) {
        return StringUtil.compare(this.myAdapter.getName(supplemented), this.myAdapter.getName(supplemented2), false);
    }
    
    @NotNull
    public Collection<SUPPLEMENTED> collectValuesFromProject(@NotNull final Project project, @Nullable final Condition<SUPPLEMENTED> condition) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "collectValuesFromProject"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Collection<SUPPLEMENTED> collectValuesFromProject;
        try {
            collectValuesFromProject = this.myAdapter.collectValuesFromProject(project, condition);
            if (collectValuesFromProject == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/ui/OCTextFieldCompletionProvider", "collectValuesFromProject"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return collectValuesFromProject;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
