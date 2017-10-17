// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class DisableClangTidyGroupIntentionAction extends DisableClangTidyIntentionAction
{
    @NotNull
    private final String myGroupName;
    
    public DisableClangTidyGroupIntentionAction(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checkName", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyGroupIntentionAction", "<init>"));
        }
        final String groupNameForCheck = ClangTidyUtil.getGroupNameForCheck(s);
        String myGroupName = null;
        Label_0064: {
            try {
                if (groupNameForCheck != null) {
                    myGroupName = groupNameForCheck;
                    break Label_0064;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            myGroupName = "";
        }
        this.myGroupName = myGroupName;
    }
    
    @Override
    public boolean isAvailable(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyGroupIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyGroupIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0115: {
            try {
                if (!super.isAvailable(project, editor, psiElement)) {
                    return false;
                }
                final DisableClangTidyGroupIntentionAction disableClangTidyGroupIntentionAction = this;
                final String s = disableClangTidyGroupIntentionAction.myGroupName;
                final boolean b = StringUtil.isNotEmpty(s);
                if (b) {
                    break Label_0115;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final DisableClangTidyGroupIntentionAction disableClangTidyGroupIntentionAction = this;
                final String s = disableClangTidyGroupIntentionAction.myGroupName;
                final boolean b = StringUtil.isNotEmpty(s);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    protected String modifyConfiguration(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configuration", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyGroupIntentionAction", "modifyConfiguration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String disableGroup;
        try {
            disableGroup = ClangTidyUtil.disableGroup(s, this.myGroupName);
            if (disableGroup == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyGroupIntentionAction", "modifyConfiguration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return disableGroup;
    }
    
    @NotNull
    @Override
    public String getText() {
        String string;
        try {
            string = "Disable all " + StringUtil.wrapWithDoubleQuote(this.myGroupName + "-*") + " checks";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/DisableClangTidyGroupIntentionAction", "getText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
