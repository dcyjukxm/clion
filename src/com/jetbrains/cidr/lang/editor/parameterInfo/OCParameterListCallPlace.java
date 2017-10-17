// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.parameterInfo;

import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCParameterList;

public class OCParameterListCallPlace extends OCFunctionCallPlace<OCParameterList>
{
    public OCParameterListCallPlace(final OCParameterList list) {
        super((PsiElement)list);
    }
    
    @Override
    public void collectCallOptions(@NotNull final Collection<OCFunctionCallOption> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/parameterInfo/OCParameterListCallPlace", "collectCallOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCParameterList list = this.getElement();
        try {
            if (!list.getParameterDeclarations().isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final PsiElement parent = list.getParent().getParent();
        try {
            if (!(parent instanceof OCCallable)) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        OCFunctionCallPlace.collectInitializationOptions(((OCCallable)parent).getReturnType().resolve(list.getContainingFile()), list, collection);
    }
    
    @NotNull
    @Override
    public List<OCExpression> getArgumentExpressions() {
        List<OCExpression> emptyList;
        try {
            emptyList = Collections.emptyList();
            if (emptyList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/parameterInfo/OCParameterListCallPlace", "getArgumentExpressions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return emptyList;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
