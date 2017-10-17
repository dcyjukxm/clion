// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation.doxygen.api;

import java.util.Collections;
import com.intellij.psi.PsiComment;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class DoxygenFacade
{
    public static boolean isDoxygenSupported() {
        try {
            if (((Doxygen[])Doxygen.EP_NAME.getExtensions()).length > 0) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    @Nullable
    public static DoxygenCommentGroup getCommentGroup(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade", "getCommentGroup"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Doxygen[] array = (Doxygen[])Doxygen.EP_NAME.getExtensions();
        try {
            if (array.length > 0) {
                return array[0].getCommentGroup(psiElement);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    public static List<PsiComment> getCommentScope(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade", "getCommentScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Doxygen[] array = (Doxygen[])Doxygen.EP_NAME.getExtensions();
        List<PsiComment> list = null;
        Label_0076: {
            try {
                if (array.length > 0) {
                    final List<PsiComment> list2;
                    list = (list2 = array[0].getCommentScope(psiElement));
                    break Label_0076;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            List<PsiComment> list2;
            list = (list2 = Collections.emptyList());
            try {
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade", "getCommentScope"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return list;
    }
    
    public static boolean isDoxygenComment(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade", "isDoxygenComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (isDoxygenSupported()) {
                return ((Doxygen[])Doxygen.EP_NAME.getExtensions())[0].isDoxygenComment(psiComment);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
