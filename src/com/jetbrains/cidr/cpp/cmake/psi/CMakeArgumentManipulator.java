// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.psi;

import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFilePathPartsRenameHolder;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeLiteralUtils;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Pattern;
import com.intellij.util.NotNullFunction;
import com.intellij.psi.AbstractElementManipulator;

public class CMakeArgumentManipulator extends AbstractElementManipulator<CMakeArgument>
{
    public static final NotNullFunction<String, String> ESCAPER;
    private static final Pattern LINE_CONTINUATION;
    
    @Nullable
    @Contract("null->null;!null->!null")
    public static String getCMakeLiteralFromValue(@Nullable String wrapWithDoubleQuote) {
        try {
            if (wrapWithDoubleQuote == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        wrapWithDoubleQuote = (String)CMakeArgumentManipulator.ESCAPER.fun((Object)wrapWithDoubleQuote);
        if (CMakeLiteralUtils.shouldBeInsideQuotes(wrapWithDoubleQuote)) {
            wrapWithDoubleQuote = StringUtil.wrapWithDoubleQuote(wrapWithDoubleQuote);
        }
        return wrapWithDoubleQuote;
    }
    
    @Nullable
    @Contract("null->null;!null->!null")
    public static String getValueFromCMakeLiteralNoEval(@Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return StringUtil.unquoteString(StringUtil.unescapeStringCharacters(CMakeArgumentManipulator.LINE_CONTINUATION.matcher(s).replaceAll("")), '\"');
    }
    
    public CMakeArgument handleContentChange(@NotNull final CMakeArgument cMakeArgument, @NotNull final TextRange textRange, String newContent) throws IncorrectOperationException {
        try {
            if (cMakeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentManipulator", "handleContentChange"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final CMakeFilePathPartsRenameHolder invoke = new CMakeFilePathPartsRenameHolder().invoke((PsiElement)cMakeArgument, textRange, newContent);
        final String oldStart = invoke.getOldStart();
        newContent = invoke.getNewContent();
        final String oldEnd = invoke.getOldEnd();
        String s = null;
        Label_0296: {
            if (cMakeArgument.isBracketArgument()) {
                s = oldStart + newContent + oldEnd;
            }
            else if (cMakeArgument.isQuotedArgument()) {
                s = oldStart + (String)CMakeArgumentManipulator.ESCAPER.fun((Object)newContent) + oldEnd;
                if (!s.startsWith("\"")) {
                    s = StringUtil.wrapWithDoubleQuote(s);
                }
            }
            else {
                s = oldStart + (String)CMakeArgumentManipulator.ESCAPER.fun((Object)newContent) + oldEnd;
                try {
                    if (s.startsWith("\"") || !CMakeLiteralUtils.shouldBeInsideQuotes(s)) {
                        break Label_0296;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                s = StringUtil.wrapWithDoubleQuote(s);
            }
        }
        cMakeArgument.replace((PsiElement)CMakeElementFactory.createArgumentRaw(cMakeArgument.getProject(), s));
        return cMakeArgument;
    }
    
    @NotNull
    public TextRange getRangeInElement(@NotNull final CMakeArgument cMakeArgument) {
        try {
            if (cMakeArgument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentManipulator", "getRangeInElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        TextRange contentsRange = null;
        Label_0113: {
            TextRange textRange3 = null;
            Label_0078: {
                try {
                    if (!cMakeArgument.isQuotedArgument()) {
                        break Label_0113;
                    }
                    final CMakeArgument cMakeArgument2 = cMakeArgument;
                    final TextRange textRange = cMakeArgument2.getContentsRange();
                    final int n = -1;
                    final TextRange textRange2 = textRange.shiftRight(n);
                    final int n2 = -1;
                    textRange3 = textRange2.grown(n2);
                    if (textRange3 == null) {
                        break Label_0078;
                    }
                    return textRange3;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final CMakeArgument cMakeArgument2 = cMakeArgument;
                    final TextRange textRange = cMakeArgument2.getContentsRange();
                    final int n = -1;
                    final TextRange textRange2 = textRange.shiftRight(n);
                    final int n2 = -1;
                    textRange3 = textRange2.grown(n2);
                    if (textRange3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentManipulator", "getRangeInElement"));
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return textRange3;
            try {
                contentsRange = cMakeArgument.getContentsRange();
                if (contentsRange == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/psi/CMakeArgumentManipulator", "getRangeInElement"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return contentsRange;
    }
    
    static {
        ESCAPER = StringUtil.escaper(true, "$;\"");
        LINE_CONTINUATION = Pattern.compile("[\\\\]([\\\\][\\\\])*([\n]|([\r][\n]))");
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
