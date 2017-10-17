// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.generate.actions.OCObjCActionContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolOffsetUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.util.OCDeclarationKind;
import java.util.Iterator;
import com.jetbrains.cidr.lang.quickfixes.OCImportSymbolFix;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.refactoring.util.OCElementsMover;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.util.CommonProcessors;
import com.intellij.openapi.project.Project;
import java.util.Collection;
import java.util.Collections;
import java.util.Arrays;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import javax.swing.JComponent;
import java.util.Map;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.settings.OCBooleanOption;
import com.jetbrains.cidr.lang.generate.actions.OCGenerateMethodActionContext;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;

public abstract class OCGenerateMethodHandler extends OCObjCClassTextActionHandlerBase<OCInstanceVariableSymbol, OCGenerateMethodActionContext>
{
    private static final OCBooleanOption DECLARE_IN_INTERFACE;
    
    protected void loadOptions(final PsiFile psiFile, final Editor editor, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext, @Nullable final OCCodeStyleSettings ocCodeStyleSettings, @NotNull final List<Pair<OCOption, Object>> list) {
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "loadOptions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        OCBooleanOption declare_IN_INTERFACE = null;
        boolean b = false;
        Label_0123: {
            Label_0122: {
                try {
                    declare_IN_INTERFACE = OCGenerateMethodHandler.DECLARE_IN_INTERFACE;
                    if (ocCodeStyleSettings != null) {
                        if (!ocCodeStyleSettings.DECLARE_GENERATED_METHODS) {
                            break Label_0122;
                        }
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                b = true;
                break Label_0123;
            }
            b = false;
        }
        list.add((Pair<OCOption, Object>)new Pair((Object)declare_IN_INTERFACE, (Object)b));
        super.loadOptions(psiFile, editor, (C)ocGenerateMethodActionContext, ocCodeStyleSettings, list);
    }
    
    @Override
    protected void saveOptions(final PsiFile psiFile, @NotNull final OCCodeStyleSettings ocCodeStyleSettings, final Map<OCOption, Object> map) {
        try {
            if (ocCodeStyleSettings == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "settings", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "saveOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        ocCodeStyleSettings.DECLARE_GENERATED_METHODS = OCClassActionHandlerBase.getOption(map, (OCOption<Boolean, JComponent>)OCGenerateMethodHandler.DECLARE_IN_INTERFACE);
        super.saveOptions(psiFile, ocCodeStyleSettings, map);
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "Select Members";
    }
    
    @Nullable
    @Override
    protected OCGenerateMethodActionContext evaluateActionContext(final OCClassSymbol ocClassSymbol, final PsiElement psiElement) {
        try {
            if (!(ocClassSymbol.locateDefinition() instanceof OCClassDeclaration)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCObjectType resolvedType = ocClassSymbol.getResolvedType(true);
        return new OCGenerateMethodActionContext(ocClassSymbol, ContainerUtil.map((Object[])this.getMethodNames(), s -> this.a(s, resolvedType, ocClassSymbol.getProject())), resolvedType, psiElement) {
            @Override
            public List<OCClassSymbol> getSymbolsToModify() {
                if (((OCClassActionHandlerBase<P, M, C>)OCGenerateMethodHandler.this).getOption((C)this, (OCOption<Boolean, JComponent>)OCGenerateMethodHandler.DECLARE_IN_INTERFACE)) {
                    return Arrays.asList(this.myInterfaceSymbol, this.myImplementationSymbol);
                }
                return (List<OCClassSymbol>)Collections.singletonList(this.myImplementationSymbol);
            }
            
            @Override
            protected boolean showSymbol(final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
                return OCGenerateMethodHandler.this.showSymbolInChooser(ocInstanceVariableSymbol, this);
            }
        };
    }
    
    @NotNull
    protected Collection<OCInstanceVariableSymbol> getSelectedCandidates(@NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final List<OCInstanceVariableSymbol> list) {
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "getSelectedCandidates"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        return list;
    }
    
    protected boolean showSymbolInChooser(final OCInstanceVariableSymbol ocInstanceVariableSymbol, final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        return true;
    }
    
    @Nullable
    protected String getParentProtocol() {
        return null;
    }
    
    private OCMethodSymbol a(final String s, final OCObjectType ocObjectType, final Project project) {
        final CommonProcessors.FindFirstProcessor<OCMethodSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCMethodSymbol>() {
            protected boolean accept(final OCMethodSymbol ocMethodSymbol) {
                return !ocMethodSymbol.isStatic();
            }
        };
        if (this.getParentProtocol() != null) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor2 = new CommonProcessors.FindFirstProcessor();
            OCGlobalProjectSymbolsCache.processTopLevelSymbols(project, (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)findFirstProcessor2, OCProtocolSymbol.class), this.getParentProtocol());
            final OCProtocolSymbol ocProtocolSymbol = (OCProtocolSymbol)findFirstProcessor2.getFoundValue();
            try {
                if (ocProtocolSymbol != null) {
                    ocProtocolSymbol.processMembers(s, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor);
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
        else {
            try {
                if (ocObjectType != null) {
                    ocObjectType.processMembers(s, OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return (OCMethodSymbol)findFirstProcessor.getFoundValue();
    }
    
    protected abstract String[] getMethodNames();
    
    protected boolean checkContext(@NotNull final Project project, @Nullable final Editor editor, @Nullable final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "checkContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (!super.checkContext(project, editor, (C)ocGenerateMethodActionContext)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        final OCMethodSymbol existingMethod = this.findExistingMethod(ocGenerateMethodActionContext);
        Object locateDefinition = null;
        Label_0092: {
            try {
                ocGenerateMethodActionContext.setDeleteExisting(true);
                if (existingMethod != null) {
                    locateDefinition = ((OCSymbol<__Null>)existingMethod).locateDefinition();
                    break Label_0092;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            locateDefinition = null;
        }
        if (locateDefinition != null) {
            final String string = existingMethod.getNameWithKindUppercase() + " is already defined.\nDo you wish to replace the existing method?";
            int showYesNoCancelDialog = 0;
            Label_0157: {
                try {
                    if (ApplicationManager.getApplication().isUnitTestMode()) {
                        showYesNoCancelDialog = 0;
                        break Label_0157;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
                showYesNoCancelDialog = Messages.showYesNoCancelDialog(string, this.getActionTitle(), Messages.getQuestionIcon());
            }
            final int n = showYesNoCancelDialog;
            try {
                if (n == 0) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            try {
                if (n == 1) {
                    ocGenerateMethodActionContext.setDeleteExisting(false);
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw c(ex6);
            }
            return false;
        }
        return true;
    }
    
    protected OCMethodSymbol findExistingMethod(final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        for (final String s : this.getMethodNames()) {
            try {
                if (!ocGenerateMethodActionContext.getImplementationSymbol().processMembersInAllCategories(s, (Class<? extends OCMemberSymbol>)OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, true)) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
        return (OCMethodSymbol)findFirstProcessor.getFoundValue();
    }
    
    protected boolean isExistingMethod(final String s) {
        for (final String s2 : this.getMethodNames()) {
            try {
                if (s.equals(s2)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
        }
        return false;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, @NotNull final PsiElement psiElement, final int n, @Nullable final PsiElement psiElement2, @NotNull final List<OCInstanceVariableSymbol> list, @NotNull final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        try {
            if (ocGenerateMethodActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/generate/handlers/OCGenerateMethodHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        Label_0287: {
            try {
                if (this.getParentProtocol() == null || !(psiElement instanceof OCImplementation)) {
                    break Label_0287;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            final OCClassDeclarationBase ocClassDeclarationBase = ocGenerateMethodActionContext.getInterfaceSymbol().locateDefinition();
            final OCProtocolSymbol ocProtocolSymbol = ((OCSymbolWithParent<T, OCProtocolSymbol>)ocGenerateMethodActionContext.getBaseMethod()).getParent();
            Label_0257: {
                try {
                    if (!(ocClassDeclarationBase instanceof OCClassDeclaration)) {
                        break Label_0287;
                    }
                    final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                    final OCObjectType ocObjectType = ocGenerateMethodActionContext2.getType();
                    final OCProtocolSymbol ocProtocolSymbol2 = ocProtocolSymbol;
                    final boolean b = ocObjectType.implementsProtocol(ocProtocolSymbol2);
                    if (!b) {
                        break Label_0257;
                    }
                    break Label_0287;
                }
                catch (IllegalArgumentException ex6) {
                    throw c(ex6);
                }
                try {
                    final OCGenerateMethodActionContext ocGenerateMethodActionContext2 = ocGenerateMethodActionContext;
                    final OCObjectType ocObjectType = ocGenerateMethodActionContext2.getType();
                    final OCProtocolSymbol ocProtocolSymbol2 = ocProtocolSymbol;
                    final boolean b = ocObjectType.implementsProtocol(ocProtocolSymbol2);
                    if (!b) {
                        new OCElementsMover().addBaseProtocol((OCClassDeclaration)ocClassDeclarationBase, ocProtocolSymbol.getName());
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw c(ex7);
                }
            }
        }
        if (ocGenerateMethodActionContext.isDeleteExisting()) {
            for (final OCMethod ocMethod : ((OCClassDeclaration)psiElement).getMethods()) {
                try {
                    if (!this.isExistingMethod(ocMethod.getSelector())) {
                        continue;
                    }
                    OCChangeUtil.delete((PsiElement)ocMethod);
                }
                catch (IllegalArgumentException ex8) {
                    throw c(ex8);
                }
            }
        }
        if (psiElement instanceof OCImplementation) {
            for (final OCInstanceVariableSymbol ocInstanceVariableSymbol : list) {
                try {
                    if (((OCSymbolWithParent<T, OCClassSymbol>)ocInstanceVariableSymbol).getParent().isSameCategory(ocGenerateMethodActionContext.getInterfaceSymbol())) {
                        continue;
                    }
                    new OCImportSymbolFix(psiElement, ((OCSymbolWithParent<T, OCClassSymbol>)ocInstanceVariableSymbol).getParent(), false).fixFirstItem(project, psiElement.getContainingFile());
                }
                catch (IllegalArgumentException ex9) {
                    throw c(ex9);
                }
            }
        }
        super.performAction(project, psiElement, n, psiElement2, list, ocGenerateMethodActionContext);
    }
    
    @Override
    protected int getInsertPosition(final PsiElement psiElement, final int n, final PsiElement psiElement2, final List<OCInstanceVariableSymbol> list, final OCGenerateMethodActionContext ocGenerateMethodActionContext) {
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
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final int n2 = n;
                final PsiElement psiElement3 = psiElement;
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement3;
                final boolean b = true;
                final int n3 = ocClassDeclaration.getMethodsStartOffset(b);
                if (n2 < n3) {
                    return OCDeclarationKind.InstanceMethod.getChildrenEndOffset(psiElement);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return ((OCClassDeclaration)psiElement).getMethodsInsertPosition(true, psiElement2, n);
    }
    
    protected static boolean processStructFields(final OCType ocType, final Processor<OCDeclaratorSymbol> processor) {
        try {
            if (!(ocType instanceof OCStructType) || ocType.isScalar()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        final OCStructSymbol symbol = ((OCStructType)ocType).getSymbol();
        if (symbol.isPOD()) {
            final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
            symbol.processFields((Processor<OCDeclaratorSymbol>)collectProcessor);
            final ArrayList list = new ArrayList<OCDeclaratorSymbol>(collectProcessor.getResults());
            Collections.sort((List<E>)list, (ocDeclaratorSymbol, ocDeclaratorSymbol2) -> OCSymbolOffsetUtil.compare(ocDeclaratorSymbol.getComplexOffset(), ocDeclaratorSymbol2.getComplexOffset()));
            final Iterator<OCDeclaratorSymbol> iterator = list.iterator();
            while (iterator.hasNext()) {
                processor.process((Object)iterator.next());
            }
            return true;
        }
        return false;
    }
    
    static {
        DECLARE_IN_INTERFACE = new OCBooleanOption("Declare in interface");
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
