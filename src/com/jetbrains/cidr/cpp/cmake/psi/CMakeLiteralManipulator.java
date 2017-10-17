// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeLiteralUtils;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFilePathPartsRenameHolder;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.AbstractElementManipulator;

public class CMakeLiteralManipulator extends AbstractElementManipulator<CMakeLiteral>
{
    public CMakeLiteral handleContentChange(@NotNull final CMakeLiteral cMakeLiteral, @NotNull final TextRange textRange, String newContent) throws IncorrectOperationException {
        try {
            if (cMakeLiteral == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/cpp/cmake/psi/CMakeLiteralManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final CMakeFilePathPartsRenameHolder invoke = new CMakeFilePathPartsRenameHolder().invoke((PsiElement)cMakeLiteral, textRange, newContent);
        final String oldStart = invoke.getOldStart();
        newContent = invoke.getNewContent();
        final String oldEnd = invoke.getOldEnd();
        final CMakeArgument cMakeArgument = (CMakeArgument)cMakeLiteral.getParent();
        if (cMakeArgument.isBracketArgument()) {
            cMakeLiteral.replace(CMakeElementFactory.createArgumentRaw(cMakeLiteral.getProject(), "[[" + oldStart + newContent + oldEnd + "]]").getLiteralNotNull());
        }
        else {
            final String string = oldStart + (String)CMakeArgumentManipulator.ESCAPER.fun((Object)newContent) + oldEnd;
            if (CMakeLiteralUtils.shouldBeInsideQuotes(string)) {
                final CMakeArgument argumentRaw = CMakeElementFactory.createArgumentRaw(cMakeLiteral.getProject(), StringUtil.wrapWithDoubleQuote(string));
                Label_0302: {
                    try {
                        if (cMakeArgument.isQuotedArgument()) {
                            break Label_0302;
                        }
                        final CMakeArgument cMakeArgument2 = cMakeArgument;
                        final boolean b = cMakeArgument2.isBracketArgument();
                        if (!b) {
                            break Label_0302;
                        }
                        break Label_0302;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final CMakeArgument cMakeArgument2 = cMakeArgument;
                        final boolean b = cMakeArgument2.isBracketArgument();
                        if (!b) {
                            cMakeLiteral.getParent().addBefore(argumentRaw.getFirstChild(), (PsiElement)cMakeLiteral);
                            cMakeLiteral.getParent().addAfter(argumentRaw.getLastChild(), (PsiElement)cMakeLiteral);
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                cMakeLiteral.replace(argumentRaw.getLiteralNotNull());
            }
            else {
                cMakeLiteral.replace(CMakeElementFactory.createArgumentRaw(cMakeLiteral.getProject(), string).getLiteralNotNull());
            }
        }
        return cMakeLiteral;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
