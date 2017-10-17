// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.parameterInfo;

import java.util.List;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCArgumentList;

public class OCArgumentListCallPlace extends OCFunctionCallPlace<OCArgumentList>
{
    public OCArgumentListCallPlace(final OCArgumentList list) {
        super((PsiElement)list);
    }
    
    @Override
    public void collectCallOptions(@NotNull final Collection<OCFunctionCallOption> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/parameterInfo/OCArgumentListCallPlace", "collectCallOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCArgumentList list = this.getElement();
        final PsiElement context = list.getContext();
        final PsiFile containingFile = list.getContainingFile();
        if (context instanceof OCCallExpression) {
            final OCExpression functionReferenceExpression = ((OCCallExpression)context).getFunctionReferenceExpression();
            if (functionReferenceExpression instanceof OCReferenceExpression) {
                final OCReferenceElement referenceElement = ((OCReferenceExpression)functionReferenceExpression).getReferenceElement();
                if (referenceElement != null) {
                    for (final OCSymbol ocSymbol : referenceElement.resolveToOverloadsSymbols()) {
                        try {
                            if (ocSymbol instanceof OCStructSymbol) {
                                OCFunctionCallPlace.collectConstructorCallOptions(ocSymbol.getType(), (PsiElement)list, collection);
                                continue;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        OCFunctionCallPlace.collectCallOptions(collection, (PsiElement)list, (Collection<OCSymbol>)Collections.singleton(ocSymbol), null);
                    }
                }
            }
            else if (functionReferenceExpression instanceof OCQualifiedExpression) {
                final OCQualifiedExpression ocQualifiedExpression = (OCQualifiedExpression)functionReferenceExpression;
                OCFunctionCallPlace.collectCallOptions(collection, (PsiElement)list, ocQualifiedExpression.resolveToOverloadsSymbols(), ocQualifiedExpression.getQualifier().getResolvedType().getTerminalType());
            }
            else {
                OCFunctionCallPlace.collectCallOptions(collection, (PsiElement)list, (Collection<OCSymbol>)Collections.singletonList(OCGetSymbolVisitor.getSymbol(functionReferenceExpression)), null);
            }
        }
        else if (context instanceof OCDeclarator) {
            OCFunctionCallPlace.collectInitializationOptions(((OCDeclarator)context).getType().resolve(containingFile), list, collection);
        }
        else if (context instanceof OCCppNewExpression) {
            OCFunctionCallPlace.collectInitializationOptions(((OCCppNewExpression)context).getConstructingType().resolve(containingFile), list, collection);
        }
        else if (context instanceof OCConstructorFieldInitializer) {
            final OCReferenceElement referenceElement2 = ((OCConstructorFieldInitializer)context).getReferenceElement();
            if (referenceElement2 != null) {
                final OCSymbol resolveToSymbol = referenceElement2.resolveToSymbol();
                OCType ocType = null;
                Label_0447: {
                    Label_0424: {
                        Label_0394: {
                            try {
                                if (resolveToSymbol == null) {
                                    return;
                                }
                                final OCSymbol ocSymbol2 = resolveToSymbol;
                                final boolean b = ocSymbol2 instanceof OCFunctionSymbol;
                                if (b) {
                                    break Label_0394;
                                }
                                break Label_0424;
                            }
                            catch (IllegalArgumentException ex3) {
                                throw b(ex3);
                            }
                            try {
                                final OCSymbol ocSymbol2 = resolveToSymbol;
                                final boolean b = ocSymbol2 instanceof OCFunctionSymbol;
                                if (!b) {
                                    break Label_0424;
                                }
                                if (!((OCFunctionSymbol)resolveToSymbol).isCppConstructor()) {
                                    break Label_0424;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw b(ex4);
                            }
                        }
                        ocType = resolveToSymbol.getEffectiveType();
                        break Label_0447;
                    }
                    if (resolveToSymbol instanceof OCDeclaratorSymbol) {
                        ocType = resolveToSymbol.getType();
                    }
                    else {
                        ocType = null;
                    }
                }
                if (ocType != null) {
                    OCFunctionCallPlace.collectInitializationOptions(ocType.resolve(new OCResolveContext(context)), list, collection);
                }
            }
        }
    }
    
    @NotNull
    @Override
    public List<OCExpression> getArgumentExpressions() {
        List<OCExpression> arguments;
        try {
            arguments = this.getElement().getArguments();
            if (arguments == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/parameterInfo/OCArgumentListCallPlace", "getArgumentExpressions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return arguments;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
