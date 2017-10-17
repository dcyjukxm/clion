// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger;

import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.ContextFreeResolveUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.xdebugger.XDebuggerUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrMemberValue;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.intellij.openapi.application.AccessToken;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrPhysicalValue;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.OCLanguage;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.evaluation.CidrDebuggerTypesHelper;

public class OCDebuggerTypesHelper extends CidrDebuggerTypesHelper
{
    public OCDebuggerTypesHelper(final CidrDebugProcess cidrDebugProcess) {
        super(cidrDebugProcess);
    }
    
    @Override
    public PsiElement resolveToDeclaration(final XSourcePosition xSourcePosition, final LLValue llValue) {
        final PsiElement contextElement = this.getContextElement(xSourcePosition);
        try {
            if (contextElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCReference referenceFromText = this.createReferenceFromText(llValue, contextElement);
        try {
            if (referenceFromText == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final PsiElement resolve = referenceFromText.resolve();
        try {
            if (resolve == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (resolve.getContainingFile() == ((PsiElement)referenceFromText).getContainingFile()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return resolve;
    }
    
    @Nullable
    protected OCReference createReferenceFromText(@NotNull final LLValue llValue, @NotNull final PsiElement psiElement) {
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "createReferenceFromText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "createReferenceFromText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement.getLanguage() == OCLanguage.getInstance()) {
                return OCElementFactory.referenceElementFromText(llValue.getName(), psiElement, false);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @Nullable
    @Override
    public Boolean isImplicitContextVariable(@NotNull final XSourcePosition xSourcePosition, @NotNull final LLValue llValue) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "position", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "isImplicitContextVariable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (llValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "var", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "isImplicitContextVariable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final PsiElement contextElement = this.getContextElement(xSourcePosition);
        try {
            if (contextElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (OCThisSelfSuperSymbol.tryResolveThisSelfSuper(llValue.getName(), contextElement, new OCResolveContext(contextElement)) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return false;
    }
    
    @Nullable
    protected OCType resolveType(final CidrPhysicalValue cidrPhysicalValue, final Class... array) {
        final XSourcePosition sourcePosition = cidrPhysicalValue.getSourcePosition();
        try {
            if (sourcePosition == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return this.a(sourcePosition, cidrPhysicalValue.getVar().getType(), PsiManager.getInstance(this.myProcess.getProject()).findFile(sourcePosition.getFile()), array);
    }
    
    @Nullable
    public OCType resolveTypeInRA(final CidrPhysicalValue cidrPhysicalValue, final Class... array) {
        return (OCType)ApplicationManager.getApplication().runReadAction((Computable)(() -> {
            try {
                if (this.myProcess.getProject().isDisposed()) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return this.resolveType(cidrPhysicalValue, array);
        }));
    }
    
    @Nullable
    private OCType a(@NotNull final XSourcePosition xSourcePosition, @NotNull final String s, final PsiFile psiFile, final Class... array) {
        try {
            if (xSourcePosition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pos", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "doResolveType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "doResolveType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return a(s, psiFile, this.getContextElement(xSourcePosition), array);
    }
    
    @Nullable
    private static OCType a(@NotNull final String s, final PsiFile psiFile, final PsiElement psiElement, final Class... array) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "doResolveType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final OCTypeElement typeElementFromTextOrNull = OCElementFactory.typeElementFromTextOrNull(s, psiElement, false);
        try {
            if (typeElementFromTextOrNull == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCType resolve = typeElementFromTextOrNull.getType().resolve(psiFile);
        if (resolve instanceof OCReferenceType) {
            final List<OCSymbol> resolveToSymbols = ((OCReferenceType)resolve).getReference(psiFile).resolveToSymbols(psiFile);
            final Iterator<OCSymbol> iterator = (Iterator<OCSymbol>)resolveToSymbols.iterator();
            while (iterator.hasNext()) {
                final OCSymbol definitionSymbol = iterator.next().getDefinitionSymbol();
                Label_0183: {
                    try {
                        if (definitionSymbol == null) {
                            continue;
                        }
                        final OCSymbol ocSymbol = definitionSymbol;
                        final OCType ocType = ocSymbol.getResolvedType();
                        final Class[] array2 = array;
                        final boolean b = a(ocType, array2);
                        if (b) {
                            break Label_0183;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                    try {
                        final OCSymbol ocSymbol = definitionSymbol;
                        final OCType ocType = ocSymbol.getResolvedType();
                        final Class[] array2 = array;
                        final boolean b = a(ocType, array2);
                        if (b) {
                            return definitionSymbol.getResolvedType();
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                }
            }
            final Iterator<OCSymbol> iterator2 = (Iterator<OCSymbol>)resolveToSymbols.iterator();
            while (iterator2.hasNext()) {
                final OCType resolvedType = iterator2.next().getResolvedType();
                try {
                    if (resolvedType == null) {
                        continue;
                    }
                    final OCType ocType2 = resolvedType;
                    final Class[] array3 = array;
                    final boolean b2 = a(ocType2, array3);
                    if (b2) {
                        return resolvedType;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                try {
                    final OCType ocType2 = resolvedType;
                    final Class[] array3 = array;
                    final boolean b2 = a(ocType2, array3);
                    if (b2) {
                        return resolvedType;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
            }
        }
        else {
            try {
                if (a(resolve, array)) {
                    return resolve;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
        }
        return null;
    }
    
    private static boolean a(final OCType ocType, final Class[] array) {
        for (final Class clazz : array) {
            try {
                if (clazz.isInstance(ocType)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
        }
        return false;
    }
    
    @Override
    public boolean isCPPThisPsi(final CidrPhysicalValue cidrPhysicalValue) {
        try {
            if (!cidrPhysicalValue.getVar().getName().equals("this")) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Project project = this.myProcess.getProject();
        final XSourcePosition sourcePosition = cidrPhysicalValue.getSourcePosition();
        try {
            if (sourcePosition == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final AccessToken acquireReadActionLock = ApplicationManager.getApplication().acquireReadActionLock();
        boolean cpp = false;
        try {
            final PsiFile file = PsiManager.getInstance(project).findFile(sourcePosition.getFile());
            if (file instanceof OCFile) {
                cpp = ((OCFile)file).isCpp();
            }
        }
        finally {
            acquireReadActionLock.finish();
        }
        return cpp;
    }
    
    @Override
    public boolean isCStringType(final CidrPhysicalValue cidrPhysicalValue) {
        final OCType resolveTypeInRA = this.resolveTypeInRA(cidrPhysicalValue, OCPointerType.class);
        Label_0033: {
            try {
                if (resolveTypeInRA == null) {
                    return false;
                }
                final OCType ocType = resolveTypeInRA;
                final boolean b = ocType.isCString();
                if (b) {
                    break Label_0033;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final OCType ocType = resolveTypeInRA;
                final boolean b = ocType.isCString();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Override
    public XSourcePosition computeSourcePosition(final CidrMemberValue cidrMemberValue) {
        final OCType resolveType = this.resolveType(cidrMemberValue.getParent(), OCPointerType.class, OCStructType.class, OCReferenceType.class, OCObjectType.class);
        try {
            if (resolveType == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCType terminalType = resolveType.getTerminalType();
        if (terminalType instanceof OCObjectType) {
            final OCInstanceVariableSymbol ocInstanceVariableSymbol = ((OCObjectType)terminalType).findMember(cidrMemberValue.getVar().getName(), OCInstanceVariableSymbol.class);
            try {
                if (ocInstanceVariableSymbol != null) {
                    return XDebuggerUtil.getInstance().createPositionByOffset(ocInstanceVariableSymbol.getContainingFile(), ocInstanceVariableSymbol.getOffset());
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        else if (terminalType instanceof OCStructType) {
            final OCDeclaratorSymbol field = ((OCStructType)terminalType).findField(cidrMemberValue.getVar().getName());
            try {
                if (field != null) {
                    return XDebuggerUtil.getInstance().createPositionByOffset(field.getContainingFile(), field.getOffset());
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return null;
    }
    
    @Nullable
    @Override
    public PsiElement getContextElement(@Nullable final XSourcePosition xSourcePosition) {
        return getContextElement(xSourcePosition, this.myProcess.getProject());
    }
    
    @Nullable
    @Override
    public XSourcePosition resolveProperty(@NotNull final CidrMemberValue cidrMemberValue, @Nullable final String s) {
        try {
            if (cidrMemberValue == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/OCDebuggerTypesHelper", "resolveProperty"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCMemberSymbol resolveToProperty = ContextFreeResolveUtil.resolveToProperty(s, cidrMemberValue.getName(), this.myProcess.getProject());
        try {
            if (resolveToProperty == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return XDebuggerUtil.getInstance().createPositionByOffset(resolveToProperty.getContainingFile(), resolveToProperty.getOffset());
    }
    
    public static PsiElement getContextElement(final XSourcePosition xSourcePosition, final Project project) {
        Object o = CidrDebuggerTypesHelper.getDefaultContextElement(xSourcePosition, project);
        try {
            if (o == null || !(((PsiElement)o).getParent() instanceof OCForStatement)) {
                return (PsiElement)o;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        o = ((OCForStatement)((PsiElement)o).getParent()).getInitializer();
        return (PsiElement)o;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
