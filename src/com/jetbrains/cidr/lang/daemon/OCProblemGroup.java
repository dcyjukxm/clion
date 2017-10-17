// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.psi.PsiElement;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.quickfixes.OCSuppressClangDiagnosticIntentionAction;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.codeInspection.SuppressIntentionAction;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.codeInspection.SuppressableProblemGroup;

public class OCProblemGroup implements SuppressableProblemGroup
{
    @Nullable
    private final String myProblemName;
    @Nullable
    private OCSuppressionGroup mySuppressGroup;
    private List<SuppressIntentionAction> mySuppressActions;
    
    public OCProblemGroup(@Nullable final String myProblemName) {
        this.myProblemName = myProblemName;
    }
    
    public OCProblemGroup(@Nullable final String myProblemName, @Nullable final OCSuppressionGroup mySuppressGroup, @Nullable final OCFile ocFile, @NotNull final TextRange textRange) {
        if (textRange == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/daemon/OCProblemGroup", "<init>"));
        }
        this.myProblemName = myProblemName;
        this.mySuppressGroup = mySuppressGroup;
        this.mySuppressActions = new ArrayList<SuppressIntentionAction>(Arrays.asList(new OCSuppressClangDiagnosticIntentionAction.ForStatement((PsiFile)ocFile, textRange, mySuppressGroup), new OCSuppressClangDiagnosticIntentionAction.ForCallable((PsiFile)ocFile, textRange, mySuppressGroup), new OCSuppressClangDiagnosticIntentionAction.ForFile(ocFile, textRange, mySuppressGroup)));
    }
    
    @Nullable
    public String getProblemName() {
        return this.myProblemName;
    }
    
    @Nullable
    public String getSuppressOption() {
        try {
            if (this.mySuppressGroup == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.mySuppressGroup.suppressionOption;
    }
    
    @NotNull
    public SuppressIntentionAction[] getSuppressActions(@Nullable final PsiElement psiElement) {
        SuppressIntentionAction[] array4 = null;
        Label_0077: {
            SuppressIntentionAction[] array3 = null;
            Label_0042: {
                try {
                    if (this.mySuppressActions == null) {
                        break Label_0077;
                    }
                    final OCProblemGroup ocProblemGroup = this;
                    final List<SuppressIntentionAction> list = ocProblemGroup.mySuppressActions;
                    final OCProblemGroup ocProblemGroup2 = this;
                    final List<SuppressIntentionAction> list2 = ocProblemGroup2.mySuppressActions;
                    final int n = list2.size();
                    final SuppressIntentionAction[] array = new SuppressIntentionAction[n];
                    final SuppressIntentionAction[] array2 = list.toArray(array);
                    array3 = array2;
                    if (array3 == null) {
                        break Label_0042;
                    }
                    return array3;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCProblemGroup ocProblemGroup = this;
                    final List<SuppressIntentionAction> list = ocProblemGroup.mySuppressActions;
                    final OCProblemGroup ocProblemGroup2 = this;
                    final List<SuppressIntentionAction> list2 = ocProblemGroup2.mySuppressActions;
                    final int n = list2.size();
                    final SuppressIntentionAction[] array = new SuppressIntentionAction[n];
                    final SuppressIntentionAction[] array2 = list.toArray(array);
                    array3 = array2;
                    if (array3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCProblemGroup", "getSuppressActions"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return array3;
            try {
                array4 = new SuppressIntentionAction[0];
                if (array4 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCProblemGroup", "getSuppressActions"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return array4;
    }
    
    public void addSuppressAction(@NotNull final SuppressIntentionAction suppressIntentionAction) {
        try {
            if (suppressIntentionAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/daemon/OCProblemGroup", "addSuppressAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.mySuppressActions != null) {
                this.mySuppressActions.add(suppressIntentionAction);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.mySuppressActions = new ArrayList<SuppressIntentionAction>(Arrays.asList(suppressIntentionAction));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
