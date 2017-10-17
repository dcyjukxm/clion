// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.OCBundle;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.generate.OCCppDefinitionsUtil;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.generate.OCGenerateUtil;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.generate.OCCaretLocation;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;

public class OCGenerateDefinitionsHandler extends OCCCppGenerateHandlerBase<OCMembersContainer, OCFunctionSymbol, OCGenerateDefinitionsContext>
{
    @NotNull
    @Override
    protected List<OCGenerateUtil.Replacement> getReplacements(@NotNull final OCCaretLocation ocCaretLocation, @NotNull final OCGenerateDefinitionsContext ocGenerateDefinitionsContext, @NotNull final List<OCFunctionSymbol> list) {
        try {
            if (ocCaretLocation == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "location", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getReplacements"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocGenerateDefinitionsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getReplacements"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getReplacements"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        List<OCGenerateUtil.Replacement> generateDefinitionReplacements;
        try {
            generateDefinitionReplacements = OCCppDefinitionsUtil.getGenerateDefinitionReplacements(ocCaretLocation, ocGenerateDefinitionsContext.getParent(), list, ContainerUtil.map((Collection)list, OCFunctionSymbol::locateFunctionDefinition), ((OCCCppGenerateHandlerBase<P, M, OCGenerateDefinitionsContext>)this).getInlinePolicy(ocGenerateDefinitionsContext));
            if (generateDefinitionReplacements == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getReplacements"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return generateDefinitionReplacements;
    }
    
    @Override
    protected boolean allowNamespacesAndTopLevel() {
        return true;
    }
    
    @Override
    protected boolean allowUnions() {
        return true;
    }
    
    @Nullable
    @Override
    protected OCGenerateDefinitionsContext evaluateActionContext(final OCMembersContainer ocMembersContainer, final PsiElement psiElement) {
        return new OCGenerateDefinitionsContext(ocMembersContainer, psiElement);
    }
    
    @NotNull
    @Override
    public String getActionName() {
        String message;
        try {
            message = OCBundle.message("generate.definitions.action.name", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getActionName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return message;
    }
    
    @Override
    protected String getActionTitle() {
        return OCBundle.message("generate.definitions.action.title", new Object[0]);
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return OCBundle.message("generate.definitions.action.memberChooserTitle", new Object[0]);
    }
    
    @Override
    protected String getNoMembersMessage(@NotNull final OCGenerateDefinitionsContext ocGenerateDefinitionsContext) {
        try {
            if (ocGenerateDefinitionsContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getNoMembersMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCBundle.message("generate.definitions.action.noMembers", ocGenerateDefinitionsContext.getParentNameUppercase());
    }
    
    @NotNull
    @Override
    protected String getNoParentsMessage() {
        String message;
        try {
            message = OCBundle.message("generate.definitions.action.noParent", new Object[0]);
            if (message == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getNoParentsMessage"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return message;
    }
    
    @NotNull
    @Override
    protected String getParentChooserTitle() {
        String s;
        try {
            s = "Choose Class/Namespace";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getParentChooserTitle"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @Nullable
    @NonNls
    @Override
    protected String getHelpID() {
        return "generate.definitions.dialog";
    }
    
    @NotNull
    @Override
    protected String getFeatureId() {
        String s;
        try {
            s = "generate.definitions";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateDefinitionsHandler", "getFeatureId"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
