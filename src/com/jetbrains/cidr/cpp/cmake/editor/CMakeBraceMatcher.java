// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.editor;

import com.jetbrains.cidr.cpp.cmake.psi.CMakeTokenTypes;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;

public class CMakeBraceMatcher implements PairedBraceMatcher
{
    private static final BracePair[] PAIRS;
    
    @NotNull
    public BracePair[] getPairs() {
        BracePair[] pairs;
        try {
            pairs = CMakeBraceMatcher.PAIRS;
            if (pairs == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/editor/CMakeBraceMatcher", "getPairs"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return pairs;
    }
    
    public boolean isPairedBracesAllowedBeforeType(@NotNull final IElementType elementType, @Nullable final IElementType elementType2) {
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "lbraceType", "com/jetbrains/cidr/cpp/cmake/editor/CMakeBraceMatcher", "isPairedBracesAllowedBeforeType"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return true;
    }
    
    public int getCodeConstructStart(@NotNull final PsiFile psiFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/editor/CMakeBraceMatcher", "getCodeConstructStart"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return n;
    }
    
    static {
        PAIRS = new BracePair[] { new BracePair(CMakeTokenTypes.LPAR, CMakeTokenTypes.RPAR, false) };
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
