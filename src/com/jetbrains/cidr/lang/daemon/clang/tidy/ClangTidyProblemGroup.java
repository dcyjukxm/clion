// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang.tidy;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInspection.SuppressIntentionAction;
import com.intellij.codeInspection.SuppressableProblemGroup;

public class ClangTidyProblemGroup implements SuppressableProblemGroup
{
    @NotNull
    private final SuppressIntentionAction[] mySuppressIntentionActions;
    
    public ClangTidyProblemGroup(@NotNull final String s) {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "checkName", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyProblemGroup", "<init>"));
        }
        this.mySuppressIntentionActions = new SuppressIntentionAction[] { new DisableClangTidyCheckIntentionAction(s), new DisableClangTidyGroupIntentionAction(s), new ClangTidySuppressIntentionAction() };
    }
    
    @NotNull
    public SuppressIntentionAction[] getSuppressActions(@Nullable final PsiElement psiElement) {
        SuppressIntentionAction[] mySuppressIntentionActions;
        try {
            mySuppressIntentionActions = this.mySuppressIntentionActions;
            if (mySuppressIntentionActions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/tidy/ClangTidyProblemGroup", "getSuppressActions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySuppressIntentionActions;
    }
    
    @Nullable
    public String getProblemName() {
        return "ClangTidyInspection";
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
