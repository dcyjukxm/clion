// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.symbols.objc.OCGenericParameterSymbol;
import com.jetbrains.cidr.lang.psi.OCGenericParameter;
import com.jetbrains.cidr.lang.psi.OCGenericParametersList;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCTypeParameterTypeSymbol;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCTemplateParameterList;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.intellij.psi.PsiElement;

public class OCTypeParameterSymbolResolver
{
    public static boolean processTemplateSymbols(final String s, final PsiElement psiElement, final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/resolve/OCTypeParameterSymbolResolver", "processTemplateSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType(psiElement, false, new Class[] { OCDeclaration.class, OCCppUsingStatement.class });
        Label_0101: {
            try {
                if (ocElement == null) {
                    return true;
                }
                final OCElement ocElement2 = ocElement;
                final OCFile ocFile = ocElement2.getContainingOCFile();
                final boolean b = ocFile.isCpp();
                if (!b) {
                    return true;
                }
                break Label_0101;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCElement ocElement2 = ocElement;
                final OCFile ocFile = ocElement2.getContainingOCFile();
                final boolean b = ocFile.isCpp();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        while (ocElement != null) {
            final OCTemplateParameterList[] array = (OCTemplateParameterList[])PsiTreeUtil.getChildrenOfType((PsiElement)ocElement, (Class)OCTemplateParameterList.class);
            if (array != null) {
                for (final OCTemplateParameterList list : array) {
                    final Iterator<OCParameterDeclaration> iterator = list.getParameterDeclarations().iterator();
                    while (iterator.hasNext()) {
                        final OCDeclarator ocDeclarator = iterator.next().getDeclarators().get(0);
                        Label_0228: {
                            try {
                                if (s == null) {
                                    break Label_0228;
                                }
                                final String s2 = s;
                                final OCDeclarator ocDeclarator2 = ocDeclarator;
                                final String s3 = ocDeclarator2.getName();
                                final boolean b2 = s2.equals(s3);
                                if (b2) {
                                    break Label_0228;
                                }
                                continue;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final String s2 = s;
                                final OCDeclarator ocDeclarator2 = ocDeclarator;
                                final String s3 = ocDeclarator2.getName();
                                final boolean b2 = s2.equals(s3);
                                if (b2) {
                                    return processor.process(ocResolveContext.getSubstitution().substitute((Object)ocDeclarator.getLocalSymbol(), ocResolveContext));
                                }
                                continue;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                    }
                    for (final OCTypeParameterDeclaration ocTypeParameterDeclaration : list.getTypeParameterDeclarations()) {
                        try {
                            if (s != null) {
                                if (!s.equals(ocTypeParameterDeclaration.getName())) {
                                    continue;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                        final OCTypeParameterTypeSymbol ocTypeParameterTypeSymbol = ocTypeParameterDeclaration.getLocalSymbol();
                        try {
                            if (ocTypeParameterTypeSymbol != null) {
                                return processor.process((Object)ocTypeParameterTypeSymbol);
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex7) {
                            throw a(ex7);
                        }
                    }
                }
            }
            ocElement = (OCElement)PsiTreeUtil.getContextOfType((PsiElement)ocElement, true, new Class[] { OCDeclaration.class });
        }
        return true;
    }
    
    static boolean processGenericSymbols(final String s, final PsiElement psiElement, final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/resolve/OCTypeParameterSymbolResolver", "processGenericSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType(psiElement, false, new Class[] { OCClassDeclaration.class }); ocElement != null; ocElement = (OCElement)PsiTreeUtil.getContextOfType((PsiElement)ocElement, true, new Class[] { OCClassDeclaration.class })) {
            final OCGenericParametersList[] array = (OCGenericParametersList[])PsiTreeUtil.getChildrenOfType((PsiElement)ocElement, (Class)OCGenericParametersList.class);
            if (array != null) {
                final OCGenericParametersList[] array2 = array;
                for (int length = array2.length, i = 0; i < length; ++i) {
                    for (final OCGenericParameter ocGenericParameter : array2[i].getParameters()) {
                        try {
                            if (s != null) {
                                if (!s.equals(ocGenericParameter.getName())) {
                                    continue;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        final OCGenericParameterSymbol localSymbol = ocGenericParameter.getLocalSymbol();
                        try {
                            if (localSymbol != null) {
                                return processor.process((Object)localSymbol);
                            }
                            continue;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                }
            }
        }
        return true;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
