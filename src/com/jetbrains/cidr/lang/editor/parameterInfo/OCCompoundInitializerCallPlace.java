// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.parameterInfo;

import com.jetbrains.cidr.lang.types.OCFunctionType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.List;
import com.jetbrains.cidr.lang.editor.OCFunctionParameterInfoHandler;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;

public class OCCompoundInitializerCallPlace extends OCFunctionCallPlace<OCCompoundInitializer>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCCompoundInitializerCallPlace(final OCCompoundInitializer ocCompoundInitializer) {
        super((PsiElement)ocCompoundInitializer);
    }
    
    @Override
    public void collectCallOptions(@NotNull final Collection<OCFunctionCallOption> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/parameterInfo/OCCompoundInitializerCallPlace", "collectCallOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCCompoundInitializer ocCompoundInitializer = this.getElement();
        final PsiElement parent = ocCompoundInitializer.getParent();
        Object o = null;
        if (parent instanceof OCArgumentList) {
            o = new OCArgumentListCallPlace((OCArgumentList)parent);
        }
        else if (parent instanceof OCCompoundInitializer) {
            o = new OCCompoundInitializerCallPlace((OCCompoundInitializer)parent);
        }
        try {
            if (o != null) {
                a((OCFunctionCallPlace<? extends PsiElement>)o, ocCompoundInitializer, collection);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCType inferType = ocCompoundInitializer.inferType();
        if (inferType != null) {
            OCFunctionCallPlace.collectInitializationOptions(inferType.resolve(ocCompoundInitializer.getContainingFile()), ocCompoundInitializer, collection);
        }
    }
    
    private static void a(@NotNull final OCFunctionCallPlace<? extends PsiElement> ocFunctionCallPlace, @NotNull final OCCompoundInitializer ocCompoundInitializer, @NotNull final Collection<OCFunctionCallOption> collection) {
        try {
            if (ocFunctionCallPlace == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "place", "com/jetbrains/cidr/lang/editor/parameterInfo/OCCompoundInitializerCallPlace", "collectUsingFromParentOption"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocCompoundInitializer == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "com/jetbrains/cidr/lang/editor/parameterInfo/OCCompoundInitializerCallPlace", "collectUsingFromParentOption"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/parameterInfo/OCCompoundInitializerCallPlace", "collectUsingFromParentOption"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final List argumentExpressions = ocFunctionCallPlace.getArgumentExpressions();
        final int index = argumentExpressions.indexOf(ocCompoundInitializer);
        Label_0164: {
            try {
                if (OCCompoundInitializerCallPlace.$assertionsDisabled) {
                    break Label_0164;
                }
                final int n = index;
                if (n < 0) {
                    break Label_0164;
                }
                break Label_0164;
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            try {
                final int n = index;
                if (n < 0) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        final ArrayList<OCFunctionCallOption> list = new ArrayList<OCFunctionCallOption>();
        ocFunctionCallPlace.collectCallOptions(list);
        final Iterator<OCFunctionCallOption> iterator = list.iterator();
        while (iterator.hasNext()) {
            final OCFunctionType type = iterator.next().getParameterInfo().getType();
            final List<OCType> parameterTypes = type.getParameterTypes();
            Label_0262: {
                try {
                    if (parameterTypes.size() <= index) {
                        continue;
                    }
                    final int n2 = index;
                    final int n3 = 1;
                    if (n2 >= n3) {
                        break Label_0262;
                    }
                    break Label_0262;
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                try {
                    final int n2 = index;
                    final int n3 = 1;
                    if (n2 >= n3) {
                        if (!OCFunctionParameterInfoHandler.isApplicableBeforeIndex(type, argumentExpressions, index - 1)) {
                            continue;
                        }
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
            OCFunctionCallPlace.collectInitializationOptions(parameterTypes.get(index), ocCompoundInitializer, collection);
        }
    }
    
    @NotNull
    @Override
    public List<OCExpression> getArgumentExpressions() {
        List<OCExpression> initializerExpressions;
        try {
            initializerExpressions = this.getElement().getInitializerExpressions();
            if (initializerExpressions == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/parameterInfo/OCCompoundInitializerCallPlace", "getArgumentExpressions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return initializerExpressions;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCCompoundInitializerCallPlace.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
