// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.ide.fileTemplates.FileTemplate;
import java.util.Properties;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.util.OCDeclarationKind;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import java.util.List;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;

public class OCGenerateSharedInstanceHandler extends OCObjCClassTextActionHandlerBase<OCMethodSymbol, OCObjCActionContext<OCMethodSymbol>>
{
    private static final String SHARED_INSTANCE_HEADER_TEMPLATE_NAME = "OC Shared Instance Header.h";
    private static final String SHARED_INSTANCE_IMPLEMENTATION_TEMPLATE_NAME = "OC Shared Instance Implementation.m";
    private static final String CLASS_TYPE_TEMPLATE_PROPERTY = "CLASS_TYPE";
    
    @Override
    protected String getActionTitle() {
        return "Generate Shared Instance Method";
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "";
    }
    
    @NotNull
    @Override
    protected OCObjCActionContext<OCMethodSymbol> evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        OCObjCActionContext<OCMethodSymbol> ocObjCActionContext;
        try {
            ocObjCActionContext = new OCObjCActionContext<OCMethodSymbol>(ocClassSymbol, psiElement, ocClassSymbol.getResolvedType(true)) {
                @Override
                protected Class<OCMethodSymbol> getMemberSymbolClass() {
                    return OCMethodSymbol.class;
                }
            };
            if (ocObjCActionContext == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateSharedInstanceHandler", "evaluateActionContext"));
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return ocObjCActionContext;
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<OCMethodSymbol> collection) {
        return false;
    }
    
    @Override
    protected boolean allowEmptySelection(final OCObjCActionContext<OCMethodSymbol> ocObjCActionContext) {
        return true;
    }
    
    @Override
    protected int getInsertPosition(final PsiElement psiElement, final int n, final PsiElement psiElement2, final List<OCMethodSymbol> list, final OCObjCActionContext<OCMethodSymbol> ocObjCActionContext) {
        Label_0025: {
            try {
                if (psiElement2 == null) {
                    break Label_0025;
                }
                final int n2 = n;
                final PsiElement psiElement3 = psiElement;
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement3;
                final boolean b = true;
                final int n3 = ocClassDeclaration.getMethodsStartOffset(b);
                if (n2 < n3) {
                    break Label_0025;
                }
                return ((OCClassDeclaration)psiElement).getMethodsInsertPosition(true, psiElement2, n);
            }
            catch (RuntimeException ex) {
                throw b(ex);
            }
            try {
                final int n2 = n;
                final PsiElement psiElement3 = psiElement;
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement3;
                final boolean b = true;
                final int n3 = ocClassDeclaration.getMethodsStartOffset(b);
                if (n2 < n3) {
                    return OCDeclarationKind.StaticMethod.getChildrenEndOffset(psiElement);
                }
            }
            catch (RuntimeException ex2) {
                throw b(ex2);
            }
        }
        return ((OCClassDeclaration)psiElement).getMethodsInsertPosition(true, psiElement2, n);
    }
    
    @NotNull
    @Override
    protected String getInsertText(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, @NotNull final List<OCMethodSymbol> list, @NotNull final OCObjCActionContext<OCMethodSymbol> ocObjCActionContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateSharedInstanceHandler", "getInsertText"));
            }
        }
        catch (Exception ex) {
            throw b(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateSharedInstanceHandler", "getInsertText"));
            }
        }
        catch (Exception ex2) {
            throw b(ex2);
        }
        try {
            if (ocObjCActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateSharedInstanceHandler", "getInsertText"));
            }
        }
        catch (Exception ex3) {
            throw b(ex3);
        }
        final FileTemplateManager instance = FileTemplateManager.getInstance(psiElement.getProject());
        String s;
        if (psiElement instanceof OCInterface) {
            s = "OC Shared Instance Header.h";
        }
        else {
            if (!(psiElement instanceof OCImplementation)) {
                String s2;
                try {
                    s2 = "";
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateSharedInstanceHandler", "getInsertText"));
                    }
                }
                catch (Exception ex4) {
                    throw b(ex4);
                }
                return s2;
            }
            s = "OC Shared Instance Implementation.m";
        }
        final FileTemplate codeTemplate = instance.getCodeTemplate(s);
        if (codeTemplate != null) {
            final Properties properties = new Properties(FileTemplateManager.getInstance(psiElement.getProject()).getDefaultProperties());
            properties.setProperty("CLASS_TYPE", ocObjCActionContext.getType().getBestNameInContext(psiElement));
            try {
                final String text = codeTemplate.getText(properties);
                if (text == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateSharedInstanceHandler", "getInsertText"));
                }
                return text;
            }
            catch (Exception ex5) {
                throw new RuntimeException(String.format("Unable to load template for file template '%s'!", instance.internalTemplateToSubject(s)), ex5);
            }
        }
        String s3;
        try {
            s3 = "";
            if (s3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateSharedInstanceHandler", "getInsertText"));
            }
        }
        catch (Exception ex6) {
            throw b(ex6);
        }
        return s3;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
