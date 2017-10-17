// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.editor;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.cpp.cmake.completion.CMakeCompletionUtils;
import javax.swing.Icon;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFileLocationUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgument;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeArgumentImplMixin;
import com.intellij.navigation.ItemPresentation;

public class CMakeArgumentPresentation implements ItemPresentation
{
    private CMakeArgumentImplMixin myMixin;
    
    public CMakeArgumentPresentation(final CMakeArgumentImplMixin myMixin) {
        this.myMixin = myMixin;
    }
    
    @Nullable
    public String getPresentableText() {
        if (this.myMixin.isCommandDefinitionName()) {
            final StringBuilder sb = new StringBuilder(this.myMixin.getLiteralNotNull().getText());
            sb.append('(');
            boolean b = false;
            for (CMakeArgument cMakeArgument = this.myMixin.getNextArgument(); cMakeArgument != null; cMakeArgument = cMakeArgument.getNextArgument()) {
                sb.append(cMakeArgument.getText()).append(' ');
                b = true;
            }
            if (b) {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(')');
            return sb.toString();
        }
        return this.myMixin.getText();
    }
    
    @Nullable
    public String getLocationString() {
        return CMakeFileLocationUtil.getLocationInFile((PsiElement)this.myMixin, false);
    }
    
    @Nullable
    public Icon getIcon(final boolean b) {
        return CMakeCompletionUtils.getRoutineIcon(this.myMixin.isFunctionName());
    }
}
