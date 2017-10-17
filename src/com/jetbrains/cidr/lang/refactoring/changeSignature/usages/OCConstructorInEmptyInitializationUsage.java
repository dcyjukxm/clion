// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.changeSignature.usages;

import com.intellij.openapi.util.Comparing;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureUsageProcessor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeInfo;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.search.constructors.OCBaseConstructorReference;
import com.jetbrains.cidr.lang.psi.OCElement;

public class OCConstructorInEmptyInitializationUsage extends OCUsageInfo<OCElement>
{
    private OCBaseConstructorReference myReference;
    
    public OCConstructorInEmptyInitializationUsage(@NotNull final OCBaseConstructorReference myReference) {
        if (myReference == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCConstructorInEmptyInitializationUsage", "<init>"));
        }
        super((PsiReference)myReference);
        this.myReference = myReference;
    }
    
    static OCConstructorInitializationList getConstructorInitializationList(final OCFunctionDefinition ocFunctionDefinition) {
        OCConstructorInitializationList list = ocFunctionDefinition.getConstructorInitializationList();
        if (list == null) {
            list = ocFunctionDefinition.setConstructorInitializationList(OCElementFactory.constructorInitializationList(ocFunctionDefinition));
        }
        return list;
    }
    
    @Override
    public boolean processUsage(@NotNull final OCChangeInfo ocChangeInfo, @NotNull final OCElement ocElement, @NotNull final Project project) {
        try {
            if (ocChangeInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeInfo", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCConstructorInEmptyInitializationUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCConstructorInEmptyInitializationUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/refactoring/changeSignature/usages/OCConstructorInEmptyInitializationUsage", "processUsage"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        if (ocChangeInfo.isParameterSetOrOrderChanged()) {
            final OCConstructorFieldInitializer constructorFieldInitializerFromText = OCElementFactory.constructorFieldInitializerFromText(this.myReference.getField().getSymbol().getName() + "()", (PsiElement)ocElement);
            try {
                OCChangeSignatureUsageProcessor.changeConstructorOrMethodCall(constructorFieldInitializerFromText, ocChangeInfo);
                if (ocElement instanceof OCFunctionDefinition) {
                    OCChangeUtil.add((PsiElement)getConstructorInitializationList((OCFunctionDefinition)ocElement), constructorFieldInitializerFromText);
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            if (ocElement instanceof OCStruct) {
                final List<OCFunctionDeclaration> constructors = ((OCStruct)ocElement).getConstructors();
                if (constructors.size() == 0) {
                    final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)OCElementFactory.constructorFromText(((OCStruct)ocElement).getName() + "() {}", (PsiElement)ocElement);
                    OCChangeUtil.add((PsiElement)ocElement, ocFunctionDefinition);
                    OCChangeUtil.add((PsiElement)getConstructorInitializationList(ocFunctionDefinition), constructorFieldInitializerFromText);
                }
                else {
                    OCChangeUtil.add((PsiElement)getConstructorInitializationList((OCFunctionDefinition)constructors.get(0)), constructorFieldInitializerFromText);
                }
            }
        }
        return true;
    }
    
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final OCConstructorInEmptyInitializationUsage ocConstructorInEmptyInitializationUsage = this;
                final Class<? extends OCConstructorInEmptyInitializationUsage> clazz = ocConstructorInEmptyInitializationUsage.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCConstructorInEmptyInitializationUsage ocConstructorInEmptyInitializationUsage = this;
                final Class<? extends OCConstructorInEmptyInitializationUsage> clazz = ocConstructorInEmptyInitializationUsage.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                if (!super.equals(o)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return Comparing.equal(this.myReference.getField().getSymbol(), ((OCConstructorInEmptyInitializationUsage)o).myReference.getField().getSymbol());
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
