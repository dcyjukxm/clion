// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.meta.PsiWritableMetaData;
import com.intellij.psi.meta.PsiMetaData;
import com.intellij.util.ArrayUtil;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.types.visitors.OCArgumentDepLookupAccumulator;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCMacroParameterList;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCMacroParameter;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.List;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.CommonProcessors;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.impl.source.resolve.ResolveCache;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.intellij.util.IncorrectOperationException;
import com.intellij.psi.PsiReference;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.meta.PsiMetaOwner;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;

public class OCReferenceElementImpl extends OCElementBase implements OCNamespaceQualifierOwner, OCReferenceElement, OCExpectedTypeUtil.Expectable, PsiMetaOwner
{
    private static final Condition<OCSymbol> IN_HEADER_FILE;
    
    public OCReferenceElementImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "<init>"));
        }
        super(astNode);
    }
    
    @NotNull
    public PsiReference getReference() {
        try {
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "getReference"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (PsiReference)this;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitReferenceElement(this);
    }
    
    public OCReferenceElement getElement() {
        return this;
    }
    
    @Override
    public PsiElement getNameIdentifier() {
        return this.findNameStartTokenInCall();
    }
    
    @NotNull
    @Override
    public PsiElement setNameOfIdentifier(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "setNameOfIdentifier"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
            if (this == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "setNameOfIdentifier"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return (PsiElement)this;
    }
    
    @NotNull
    @Override
    public String getName() {
        String canonicalText;
        try {
            canonicalText = this.getCanonicalText();
            if (canonicalText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return canonicalText;
    }
    
    @Override
    public OCCppNamespaceQualifier getNamespaceQualifier() {
        return this.findChildByType(OCElementTypes.CPP_NAMESPACE_QUALIFIER);
    }
    
    public OCTypeArgumentList getTemplateArgumentList() {
        return this.findChildByType(OCElementTypes.TYPE_ARGUMENT_LIST);
    }
    
    public TextRange getRangeInElement() {
        return this.getRangeInCallElement();
    }
    
    public PsiElement resolve() {
        final OCSymbol resolveToSymbol = this.resolveToSymbol();
        Label_0028: {
            try {
                if (resolveToSymbol == null) {
                    break Label_0028;
                }
                final OCSymbol ocSymbol = resolveToSymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.BUILTIN_SYMBOL;
                if (ocSymbolKind == ocSymbolKind2) {
                    break Label_0028;
                }
                break Label_0028;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCSymbol ocSymbol = resolveToSymbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.BUILTIN_SYMBOL;
                if (ocSymbolKind == ocSymbolKind2) {
                    return null;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        final PsiElement locateDefinition = resolveToSymbol.locateDefinition();
        try {
            if (locateDefinition != null) {
                return locateDefinition;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return OCSymbolHolderVirtualPsiElement.create(resolveToSymbol);
    }
    
    @Override
    public OCSymbol resolveToSymbol() {
        return this.resolveToSymbol(new OCResolveContext((PsiElement)this));
    }
    
    @Nullable
    @Override
    public OCSymbol resolveToSymbol(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final Pair pair = ResolveCache.getInstance(this.getProject()).resolveWithCaching(this, (ResolveCache.AbstractResolver<OCReferenceElementImpl, Pair>)new MyResolver(ocResolveContext), false, false);
        Label_0093: {
            try {
                if (pair == null) {
                    return null;
                }
                final Pair pair2 = pair;
                final Object o = pair2.second;
                final Boolean b = (Boolean)o;
                final boolean b2 = b;
                if (b2) {
                    break Label_0093;
                }
                return null;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final Pair pair2 = pair;
                final Object o = pair2.second;
                final Boolean b = (Boolean)o;
                final boolean b2 = b;
                if (b2) {
                    return (OCSymbol)pair.first;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Override
    public OCSymbol resolveToSymbolIgnoringSymbolContext() {
        final Pair pair = ResolveCache.getInstance(this.getProject()).resolveWithCaching(this, (ResolveCache.AbstractResolver<OCReferenceElementImpl, Pair>)new MyResolver(new OCResolveContext((PsiElement)this)), false, false);
        try {
            if (pair != null) {
                return (OCSymbol)pair.first;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @Nullable
    @Override
    public OCSymbol resolveToSymbol(@Nullable final OCSymbolGroupContext ocSymbolGroupContext) {
        return this.resolveToSymbol(ocSymbolGroupContext, new OCResolveContext((PsiElement)this), false, false, false);
    }
    
    @Override
    public boolean isCppThis() {
        final OCSymbol resolveToSymbol = this.resolveToSymbol();
        Label_0032: {
            try {
                if (!(resolveToSymbol instanceof OCThisSelfSuperSymbol)) {
                    return false;
                }
                final OCSymbol ocSymbol = resolveToSymbol;
                final OCThisSelfSuperSymbol ocThisSelfSuperSymbol = (OCThisSelfSuperSymbol)ocSymbol;
                final OCThisSelfSuperSymbol.Kind kind = ocThisSelfSuperSymbol.getSelfSuperThisKind();
                final OCThisSelfSuperSymbol.Kind kind2 = OCThisSelfSuperSymbol.Kind.THIS;
                if (kind == kind2) {
                    break Label_0032;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCSymbol ocSymbol = resolveToSymbol;
                final OCThisSelfSuperSymbol ocThisSelfSuperSymbol = (OCThisSelfSuperSymbol)ocSymbol;
                final OCThisSelfSuperSymbol.Kind kind = ocThisSelfSuperSymbol.getSelfSuperThisKind();
                final OCThisSelfSuperSymbol.Kind kind2 = OCThisSelfSuperSymbol.Kind.THIS;
                if (kind == kind2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @NotNull
    @Override
    public Collection<OCSymbol> resolveToOverloadsSymbols() {
        Collection<OCSymbol> a;
        try {
            a = this.a(this.getSymbolContext(), false, true, new OCResolveContext((PsiElement)this));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToOverloadsSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    @Override
    public Collection<OCSymbol> resolveToOverloadsSymbols(@Nullable final OCSymbolGroupContext ocSymbolGroupContext, final boolean b) {
        Collection<OCSymbol> a;
        try {
            a = this.a(ocSymbolGroupContext, b, true, new OCResolveContext((PsiElement)this));
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToOverloadsSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    private Collection<OCSymbol> a(@Nullable final OCSymbolGroupContext ocSymbolGroupContext, final boolean b, final boolean b2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToOverloadsSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement context = this.getContext();
        final Collection<OCSymbol> processMacros = this.processMacros(ocSymbolGroupContext);
        Label_0111: {
            Collection<OCSymbol> collection = null;
            Label_0076: {
                try {
                    if (processMacros == null) {
                        break Label_0111;
                    }
                    collection = processMacros;
                    if (collection == null) {
                        break Label_0076;
                    }
                    return collection;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    collection = processMacros;
                    if (collection == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToOverloadsSymbols"));
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return collection;
        }
        final Collection<OCSymbol> processSelfSuper = this.processSelfSuper(ocResolveContext);
        Label_0172: {
            Collection<OCSymbol> collection2 = null;
            Label_0137: {
                try {
                    if (processSelfSuper == null) {
                        break Label_0172;
                    }
                    collection2 = processSelfSuper;
                    if (collection2 == null) {
                        break Label_0137;
                    }
                    return collection2;
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                try {
                    collection2 = processSelfSuper;
                    if (collection2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToOverloadsSymbols"));
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            return collection2;
        }
        final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor = new CommonProcessors.CollectProcessor<OCSymbol>() {
            public boolean process(final OCSymbol ocSymbol) {
                return (ocSymbolGroupContext != null && !ocSymbolGroupContext.isSuitableSymbol(ocSymbol)) || (ocSymbol instanceof OCClassSymbol && ((OCClassSymbol)ocSymbol).getCategoryName() != null) || super.process((Object)ocSymbol);
            }
        };
        final OCFile containingOCFile = this.getContainingOCFile();
        OCSymbolReference.SymbolFilter none = null;
        Label_0209: {
            try {
                if (ocSymbolGroupContext == null) {
                    none = OCSymbolReference.SymbolFilter.NONE;
                    break Label_0209;
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
            none = (ocSymbol -> ocSymbolGroupContext.isSuitableSymbol(ocSymbol));
        }
        OCSymbolReference.SymbolFilter only_NAMESPACE = none;
        Label_0244: {
            try {
                if (!(context instanceof OCCppUsingStatement) || !((OCCppUsingStatement)context).isNamespaceUsing()) {
                    break Label_0244;
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
            only_NAMESPACE = OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE;
        }
        OCSymbolReference ocSymbolReference = OCSymbolReference.getLocalReference(this, only_NAMESPACE);
        Object resolveFilteringProcessor = collectProcessor;
        if (!this.a(ocSymbolReference)) {
            ocSymbolReference = OCSymbolReferenceResolver.getGlobalReferenceFromLocal(ocSymbolReference);
            resolveFilteringProcessor = OCSymbolReferenceResolver.createResolveFilteringProcessor((Processor<OCSymbol>)collectProcessor, ocResolveContext);
        }
        if (b) {
            final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor((com.intellij.util.Processor<? super Object>)resolveFilteringProcessor, (com.intellij.openapi.util.Condition<Object>[])new Condition[] { OCReferenceElementImpl.IN_HEADER_FILE });
            final OCResolveContext ocResolveContext2 = new OCResolveContext((PsiElement)containingOCFile);
            ocResolveContext2.setProcessNonImported(true);
            ContainerUtil.process((List)ocResolveContext2.resolveToSymbols(ocSymbolReference), (Processor)orderedProcessor);
            orderedProcessor.finish();
        }
        else {
            ocSymbolReference.processPossibleSymbols((Processor<OCSymbol>)resolveFilteringProcessor, b2, ocResolveContext);
        }
        Collection results = null;
        Label_0427: {
            Collection<OCSymbol> collection3 = null;
            Label_0392: {
                try {
                    if (collectProcessor.getResults().size() != 0) {
                        break Label_0427;
                    }
                    final OCReferenceElementImpl ocReferenceElementImpl = this;
                    final OCSymbolGroupContext ocSymbolGroupContext2 = ocSymbolGroupContext;
                    final boolean b3 = b;
                    final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor2 = collectProcessor;
                    final OCResolveContext ocResolveContext3 = ocResolveContext;
                    final OCFile ocFile = containingOCFile;
                    collection3 = ocReferenceElementImpl.processOtherSources(ocSymbolGroupContext2, b3, collectProcessor2, ocResolveContext3, ocFile);
                    if (collection3 == null) {
                        break Label_0392;
                    }
                    return collection3;
                }
                catch (IncorrectOperationException ex8) {
                    throw a(ex8);
                }
                try {
                    final OCReferenceElementImpl ocReferenceElementImpl = this;
                    final OCSymbolGroupContext ocSymbolGroupContext2 = ocSymbolGroupContext;
                    final boolean b3 = b;
                    final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor2 = collectProcessor;
                    final OCResolveContext ocResolveContext3 = ocResolveContext;
                    final OCFile ocFile = containingOCFile;
                    collection3 = ocReferenceElementImpl.processOtherSources(ocSymbolGroupContext2, b3, collectProcessor2, ocResolveContext3, ocFile);
                    if (collection3 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToOverloadsSymbols"));
                    }
                }
                catch (IncorrectOperationException ex9) {
                    throw a(ex9);
                }
            }
            return collection3;
            try {
                results = collectProcessor.getResults();
                if (results == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToOverloadsSymbols"));
                }
            }
            catch (IncorrectOperationException ex10) {
                throw a(ex10);
            }
        }
        return (Collection<OCSymbol>)results;
    }
    
    @NotNull
    public Collection<OCSymbol> resolveTemplateDeclarations() {
        Collection<OCSymbol> resolveTemplateDeclarations;
        try {
            resolveTemplateDeclarations = OCResolveUtil.resolveTemplateDeclarations(this);
            if (resolveTemplateDeclarations == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveTemplateDeclarations"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return resolveTemplateDeclarations;
    }
    
    private boolean a(final OCSymbolReference ocSymbolReference) {
        final OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCBlockStatement.class, OCDeclarationImpl.class, OCCallable.class, OCCppUsingStatement.class, OCClassDeclaration.class });
        Label_0067: {
            try {
                if (ocElement instanceof OCCppUsingStatement) {
                    return true;
                }
                final OCElement ocElement2 = ocElement;
                final Class<OCClassDeclaration> clazz = OCClassDeclaration.class;
                final boolean b = false;
                final PsiElement psiElement = PsiTreeUtil.getParentOfType((PsiElement)ocElement2, (Class)clazz, b);
                if (psiElement != null) {
                    return true;
                }
                break Label_0067;
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCElement ocElement2 = ocElement;
                final Class<OCClassDeclaration> clazz = OCClassDeclaration.class;
                final boolean b = false;
                final PsiElement psiElement = PsiTreeUtil.getParentOfType((PsiElement)ocElement2, (Class)clazz, b);
                if (psiElement != null) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        OCQualifiedName ocQualifiedName;
        for (ocQualifiedName = ocSymbolReference.getQualifiedName(); ocQualifiedName.getQualifier() != null; ocQualifiedName = ocQualifiedName.getQualifier()) {}
        final String name = ocQualifiedName.getName();
        try {
            if (ocElement == null || name == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        OCFile containingOCFile = this.getContainingOCFile();
        while (true) {
            Label_0143: {
                try {
                    if (containingOCFile == null) {
                        break;
                    }
                    final OCFile ocFile = containingOCFile;
                    final String s = name;
                    final boolean b2 = OCFileSymbols.canBeLocalSymbol(ocFile, s);
                    if (b2) {
                        return true;
                    }
                    break Label_0143;
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCFile ocFile = containingOCFile;
                    final String s = name;
                    final boolean b2 = OCFileSymbols.canBeLocalSymbol(ocFile, s);
                    if (b2) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            final PsiElement context = containingOCFile.getContext();
            OCFile ocFile2 = null;
            Label_0175: {
                try {
                    if (context != null) {
                        ocFile2 = (OCFile)context.getContainingFile();
                        break Label_0175;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
                ocFile2 = null;
            }
            containingOCFile = ocFile2;
        }
        return false;
    }
    
    @Nullable
    protected Collection<OCSymbol> processMacros(final OCSymbolGroupContext ocSymbolGroupContext) {
        if (ocSymbolGroupContext == OCSymbolGroupContext.MACRO_OR_MACRO_PARAMETER_CONTEXT) {
            final OCDefineDirective ocDefineDirective = (OCDefineDirective)PsiTreeUtil.getContextOfType((PsiElement)this, new Class[] { OCDefineDirective.class });
            if (ocDefineDirective != null) {
                final OCMacroParameterList macroParameters = ocDefineDirective.getMacroParameters();
                if (macroParameters != null) {
                    for (final OCMacroParameter ocMacroParameter : macroParameters.getParameters()) {
                        try {
                            if (this.getText().equals(ocMacroParameter.getText())) {
                                return (Collection<OCSymbol>)Collections.singletonList((Object)((OCSymbolDeclarator<T>)ocMacroParameter).getSymbol());
                            }
                            continue;
                        }
                        catch (IncorrectOperationException ex) {
                            throw a(ex);
                        }
                    }
                }
            }
        }
        return null;
    }
    
    @Nullable
    protected Collection<OCSymbol> processSelfSuper(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "processSelfSuper"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement context = this.getContext();
        final String canonicalText = this.getCanonicalText();
        if (context instanceof OCReferenceExpression) {
            final OCSymbol tryResolveThisSelfSuper = OCThisSelfSuperSymbol.tryResolveThisSelfSuper(canonicalText, context, ocResolveContext);
            try {
                if (tryResolveThisSelfSuper != null) {
                    return (Collection<OCSymbol>)Collections.singletonList(tryResolveThisSelfSuper);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        else {
            Label_0113: {
                try {
                    if (!(context instanceof OCTypeElement)) {
                        return null;
                    }
                    final PsiElement psiElement = context;
                    final PsiElement psiElement2 = psiElement.getParent();
                    final boolean b = psiElement2 instanceof OCMethod;
                    if (b) {
                        break Label_0113;
                    }
                    return null;
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiElement psiElement = context;
                    final PsiElement psiElement2 = psiElement.getParent();
                    final boolean b = psiElement2 instanceof OCMethod;
                    if (!b) {
                        return null;
                    }
                    if (!"instancetype".equals(canonicalText)) {
                        return null;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType(context, new Class[] { OCClassDeclaration.class });
            try {
                if (ocClassDeclaration != null) {
                    return (Collection<OCSymbol>)ContainerUtil.createMaybeSingletonList((Object)ocClassDeclaration.getSymbol());
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        return null;
    }
    
    protected Collection<OCSymbol> processOtherSources(final OCSymbolGroupContext ocSymbolGroupContext, final boolean b, final CommonProcessors.CollectProcessor<OCSymbol> collectProcessor, @NotNull final OCResolveContext ocResolveContext, final OCFile ocFile) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "processOtherSources"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0069: {
            try {
                if (!ocFile.isCpp()) {
                    break Label_0069;
                }
                final OCSymbolGroupContext ocSymbolGroupContext2 = ocSymbolGroupContext;
                final OCSymbolGroupContext ocSymbolGroupContext3 = OCSymbolGroupContext.PROTOCOL_CONTEXT;
                if (ocSymbolGroupContext2 == ocSymbolGroupContext3) {
                    break Label_0069;
                }
                break Label_0069;
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbolGroupContext ocSymbolGroupContext2 = ocSymbolGroupContext;
                final OCSymbolGroupContext ocSymbolGroupContext3 = OCSymbolGroupContext.PROTOCOL_CONTEXT;
                if (ocSymbolGroupContext2 == ocSymbolGroupContext3) {
                    return this.a(OCSymbolGroupContext.typeContext(ocFile.getKind()), b, true, new OCResolveContext((PsiElement)this));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        final OCSymbolReference.LocalReference localReference = OCSymbolReference.getLocalReference(this, OCSymbolReference.SymbolFilter.NONE);
        Label_0132: {
            try {
                if (ocSymbolGroupContext != OCSymbolGroupContext.MACRO_OR_MACRO_PARAMETER_CONTEXT) {
                    break Label_0132;
                }
                final OCSymbolReference.LocalReference localReference2 = localReference;
                final OCQualifiedName ocQualifiedName = localReference2.getQualifiedName();
                final OCQualifiedName ocQualifiedName2 = ocQualifiedName.getQualifier();
                if (ocQualifiedName2 == null) {
                    break Label_0132;
                }
                break Label_0132;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final OCSymbolReference.LocalReference localReference2 = localReference;
                final OCQualifiedName ocQualifiedName = localReference2.getQualifiedName();
                final OCQualifiedName ocQualifiedName2 = ocQualifiedName.getQualifier();
                if (ocQualifiedName2 == null) {
                    OCGlobalProjectSymbolsCache.processTopLevelSymbols(this.getProject(), (Processor<OCSymbol>)collectProcessor, localReference.getQualifiedName().getName());
                    return (Collection<OCSymbol>)collectProcessor.getResults();
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        if (ocFile.isInLibraries()) {
            final boolean setNonImportedFlag = OCResolveContext.setNonImportedFlag(ocResolveContext, true);
            ContainerUtil.process((List)ocResolveContext.resolveToSymbols(localReference), (Processor)collectProcessor);
            OCResolveContext.setNonImportedFlag(ocResolveContext, setNonImportedFlag);
        }
        return (Collection<OCSymbol>)collectProcessor.getResults();
    }
    
    protected Collection<OCSymbol> addConstructors(final Collection<OCSymbol> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //     4: astore_2       
        //     5: aload_2        
        //     6: ifnull          47
        //     9: aload_2        
        //    10: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //    15: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    18: ifne            61
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    27: athrow         
        //    28: aload_2        
        //    29: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //    34: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //    37: ifne            61
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    46: athrow         
        //    47: aload_2        
        //    48: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //    51: ifeq            144
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    60: athrow         
        //    61: new             Lcom/intellij/util/CommonProcessors$CollectProcessor;
        //    64: dup            
        //    65: new             Ljava/util/ArrayList;
        //    68: dup            
        //    69: aload_1        
        //    70: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
        //    73: invokespecial   com/intellij/util/CommonProcessors$CollectProcessor.<init>:(Ljava/util/Collection;)V
        //    76: astore_3       
        //    77: aload_1        
        //    78: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //    83: astore          4
        //    85: aload           4
        //    87: invokeinterface java/util/Iterator.hasNext:()Z
        //    92: ifeq            139
        //    95: aload           4
        //    97: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   102: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   105: astore          5
        //   107: aload           5
        //   109: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   112: ifeq            136
        //   115: aload           5
        //   117: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   120: astore          6
        //   122: aload           6
        //   124: aload           5
        //   126: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   131: aload_3        
        //   132: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.processMembers:(Ljava/lang/String;Lcom/intellij/util/Processor;)Z
        //   135: pop            
        //   136: goto            85
        //   139: aload_3        
        //   140: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //   143: areturn        
        //   144: aload_1        
        //   145: areturn        
        //    Signature:
        //  (Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;)Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  5      21     24     28     Lcom/intellij/util/IncorrectOperationException;
        //  9      40     43     47     Lcom/intellij/util/IncorrectOperationException;
        //  28     54     57     61     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Nullable
    @Override
    public OCSymbol resolveToSymbol(@Nullable final OCSymbolGroupContext ocSymbolGroupContext, @NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ProgressManager.checkCanceled();
        final Collection<OCSymbol> a = this.a(ocSymbolGroupContext, false, b2, ocResolveContext);
        boolean b5 = false;
        Label_0099: {
            Label_0090: {
                try {
                    if (this.getContainingOCFile().isCpp()) {
                        break Label_0090;
                    }
                    final Collection<OCSymbol> collection = a;
                    final Condition condition = ocSymbol -> ocSymbol.hasAttribute("overloadable");
                    final boolean b4 = ContainerUtil.exists((Iterable)collection, condition);
                    if (b4) {
                        break Label_0090;
                    }
                    break Label_0090;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final Collection<OCSymbol> collection = a;
                    final Condition condition = ocSymbol -> ocSymbol.hasAttribute("overloadable");
                    final boolean b4 = ContainerUtil.exists((Iterable)collection, condition);
                    if (b4) {
                        b5 = true;
                        break Label_0099;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            b5 = false;
        }
        final boolean b6 = b5;
        try {
            if (b6) {
                return this.a(a, ocResolveContext, b, b2, b3);
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return findPredeclaration(a);
    }
    
    @Nullable
    private OCSymbol a(Collection<OCSymbol> doArgDepLookup, @NotNull final OCResolveContext ocResolveContext, final boolean b, final boolean b2, final boolean b3) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "resolveOverloads"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Object o = this.getContext();
        if (o instanceof OCExpression) {
            o = OCParenthesesUtils.topmostParenthesized((OCExpression)o);
        }
        if (o != null) {
            List<OCExpression> list = null;
            OCArgumentsList<?> argumentList = null;
            OCStructType ocStructType = null;
            Label_0499: {
                Label_0466: {
                    if (((PsiElement)o).getParent() instanceof OCCallExpression) {
                        list = ((OCCallExpression)((PsiElement)o).getParent()).getArguments();
                    }
                    else if (((PsiElement)o).getParent() instanceof OCCppNewExpression) {
                        list = ((OCCppNewExpression)((PsiElement)o).getParent()).getInitializers();
                    }
                    else if (o instanceof OCConstructorFieldInitializer) {
                        list = ((OCConstructorFieldInitializer)o).getInitializers();
                    }
                    else {
                        Label_0374: {
                            Label_0294: {
                                Label_0261: {
                                    try {
                                        if (!(((PsiElement)o).getParent() instanceof OCDeclarator) || ((OCDeclarator)((PsiElement)o).getParent()).getInitializer() != o) {
                                            break Label_0261;
                                        }
                                    }
                                    catch (IncorrectOperationException ex2) {
                                        throw a(ex2);
                                    }
                                    final OCType terminalType = ((OCDeclarator)((PsiElement)o).getParent()).getResolvedType(ocResolveContext).getTerminalType();
                                    if (terminalType instanceof OCFunctionType) {
                                        argumentList = new OCArgumentsList<Object>(((OCFunctionType)terminalType).getParameterTypes(), null);
                                    }
                                    break Label_0466;
                                    try {
                                        if (((PsiElement)o).getParent() instanceof OCArgumentList) {
                                            break Label_0294;
                                        }
                                        final PsiElement psiElement = (PsiElement)o;
                                        final PsiElement psiElement2 = psiElement.getParent();
                                        final boolean b4 = psiElement2 instanceof OCCompoundInitializer;
                                        if (b4) {
                                            break Label_0294;
                                        }
                                        break Label_0374;
                                    }
                                    catch (IncorrectOperationException ex3) {
                                        throw a(ex3);
                                    }
                                }
                                try {
                                    final PsiElement psiElement = (PsiElement)o;
                                    final PsiElement psiElement2 = psiElement.getParent();
                                    final boolean b4 = psiElement2 instanceof OCCompoundInitializer;
                                    if (!b4) {
                                        break Label_0374;
                                    }
                                    if (!(((PsiElement)o).getParent().getParent() instanceof OCDeclarator)) {
                                        break Label_0374;
                                    }
                                }
                                catch (IncorrectOperationException ex4) {
                                    throw a(ex4);
                                }
                            }
                            final OCType terminalType2 = ((OCDeclarator)((PsiElement)o).getParent().getParent()).getResolvedType(ocResolveContext).getTerminalType();
                            if (terminalType2 instanceof OCFunctionType) {
                                argumentList = new OCArgumentsList<Object>(((OCFunctionType)terminalType2).getParameterTypes(), null);
                            }
                            break Label_0466;
                            try {
                                if (!(((PsiElement)o).getParent() instanceof OCAssignmentExpression) || ((OCAssignmentExpression)((PsiElement)o).getParent()).getSourceExpression() != o) {
                                    break Label_0466;
                                }
                            }
                            catch (IncorrectOperationException ex5) {
                                throw a(ex5);
                            }
                        }
                        final OCType terminalType3 = ((OCAssignmentExpression)((PsiElement)o).getParent()).getReceiverExpression().getResolvedType(ocResolveContext).getTerminalType();
                        if (terminalType3 instanceof OCFunctionType) {
                            argumentList = new OCArgumentsList<Object>(((OCFunctionType)terminalType3).getParameterTypes(), null);
                        }
                    }
                    try {
                        if (argumentList != null) {
                            break Label_0499;
                        }
                        if (list == null) {
                            return findPredeclaration(doArgDepLookup);
                        }
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                }
                argumentList = OCArgumentsList.getArgumentList(list, ocResolveContext);
            }
            final List all = ContainerUtil.findAll((Collection)doArgDepLookup, (Class)OCStructSymbol.class);
            if (!all.isEmpty()) {
                ocStructType = new OCStructType(all);
            }
            final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getContextOfType((PsiElement)this, false, new Class[] { OCFunctionDefinition.class });
            OCFunctionSymbol symbol = null;
            Label_0567: {
                try {
                    if (ocFunctionDefinition != null) {
                        symbol = ocFunctionDefinition.getSymbol();
                        break Label_0567;
                    }
                }
                catch (IncorrectOperationException ex7) {
                    throw a(ex7);
                }
                symbol = null;
            }
            final OCFunctionSymbol ocFunctionSymbol = symbol;
            CVQualifiers cvQualifiers = null;
            Label_0590: {
                try {
                    if (ocFunctionSymbol != null) {
                        cvQualifiers = ocFunctionSymbol.getType().getCVQualifiers();
                        break Label_0590;
                    }
                }
                catch (IncorrectOperationException ex8) {
                    throw a(ex8);
                }
                cvQualifiers = null;
            }
            final CVQualifiers cvQualifiers2 = cvQualifiers;
            final OCQualifiedName qualifiedName = OCSymbolReferenceResolver.getQualifiedName(this);
            OCType cloneWithCVQualifiers = null;
            final OCCppNamespaceQualifier namespaceQualifier = this.getNamespaceQualifier();
            if (namespaceQualifier != null) {
                final List all2 = ContainerUtil.findAll((Collection)namespaceQualifier.resolveToSymbols(), (Class)OCStructSymbol.class);
                if (!all2.isEmpty()) {
                    cloneWithCVQualifiers = new OCStructType(all2);
                }
            }
            Label_0702: {
                try {
                    if (cloneWithCVQualifiers != null || ocFunctionSymbol == null) {
                        break Label_0702;
                    }
                }
                catch (IncorrectOperationException ex9) {
                    throw a(ex9);
                }
                final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ocFunctionSymbol.getResolvedOwner(ocResolveContext, false);
                if (resolvedOwner instanceof OCStructSymbol) {
                    cloneWithCVQualifiers = resolvedOwner.getType().cloneWithCVQualifiers(cvQualifiers2, resolvedOwner.getProject());
                }
            }
            if (this.getContext().getParent() instanceof OCCallExpression) {
                doArgDepLookup = OCArgumentDepLookupAccumulator.doArgDepLookup(doArgDepLookup, argumentList.getTypes(), list, qualifiedName, ocResolveContext);
            }
            final Collection<OCSymbol> addConstructors = this.addConstructors(doArgDepLookup);
            try {
                if (ocStructType != null) {
                    return OCResolveOverloadsUtil.resolveConstructorOverloads(ocStructType, addConstructors, argumentList, cloneWithCVQualifiers, b, b2, b3, true, false, ocResolveContext);
                }
            }
            catch (IncorrectOperationException ex10) {
                throw a(ex10);
            }
            return OCResolveOverloadsUtil.resolveOverloads(addConstructors, argumentList, cloneWithCVQualifiers, null, null, b, b2, b3, true, false, ocResolveContext);
        }
        return null;
    }
    
    @Nullable
    public static OCSymbol findPredeclaration(@NotNull final Collection<OCSymbol> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "symbols"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findPredeclaration"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface java/util/Collection.size:()I
        //    50: iconst_1       
        //    51: if_icmpeq       73
        //    54: aload_0        
        //    55: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbol.NON_FANTOM_SYMBOL_CONDITION:Lcom/intellij/openapi/util/Condition;
        //    58: invokestatic    com/intellij/util/containers/ContainerUtil.filter:(Ljava/util/Collection;Lcom/intellij/openapi/util/Condition;)Ljava/util/List;
        //    61: astore_1       
        //    62: aload_1        
        //    63: invokeinterface java/util/List.size:()I
        //    68: ifeq            73
        //    71: aload_1        
        //    72: astore_0       
        //    73: aconst_null    
        //    74: astore_1       
        //    75: aconst_null    
        //    76: astore_2       
        //    77: aconst_null    
        //    78: astore_3       
        //    79: aload_0        
        //    80: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //    85: astore          4
        //    87: aload           4
        //    89: invokeinterface java/util/Iterator.hasNext:()Z
        //    94: ifeq            285
        //    97: aload           4
        //    99: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   104: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   107: astore          5
        //   109: aload           5
        //   111: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   116: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   119: if_acmpne       128
        //   122: aload           5
        //   124: astore_3       
        //   125: goto            282
        //   128: aload           5
        //   130: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   133: ifne            151
        //   136: aload           5
        //   138: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   141: ifeq            174
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   150: athrow         
        //   151: aload           5
        //   153: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //   158: ifeq            174
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   167: athrow         
        //   168: aload           5
        //   170: astore_1       
        //   171: goto            282
        //   174: aload_2        
        //   175: ifnull          279
        //   178: aload_2        
        //   179: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   184: aload           5
        //   186: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   191: if_icmpge       240
        //   194: goto            201
        //   197: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   200: athrow         
        //   201: aload_2        
        //   202: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   207: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   210: if_acmpne       240
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   219: athrow         
        //   220: aload           5
        //   222: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   227: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   230: if_acmpeq       279
        //   233: goto            240
        //   236: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   239: athrow         
        //   240: aload_2        
        //   241: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   246: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //   249: ifeq            282
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   258: athrow         
        //   259: aload           5
        //   261: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   266: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   269: if_acmpne       282
        //   272: goto            279
        //   275: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   278: athrow         
        //   279: aload           5
        //   281: astore_2       
        //   282: goto            87
        //   285: aload_2        
        //   286: ifnull          297
        //   289: aload_2        
        //   290: goto            298
        //   293: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   296: athrow         
        //   297: aload_1        
        //   298: astore          4
        //   300: aload_3        
        //   301: ifnull          344
        //   304: aload           4
        //   306: ifnull          336
        //   309: goto            316
        //   312: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   315: athrow         
        //   316: aload           4
        //   318: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   323: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isLocal:()Z
        //   326: ifne            344
        //   329: goto            336
        //   332: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   335: athrow         
        //   336: aload_3        
        //   337: goto            346
        //   340: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   343: athrow         
        //   344: aload           4
        //   346: areturn        
        //    Signature:
        //  (Ljava/util/Collection<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  128    144    147    151    Lcom/intellij/util/IncorrectOperationException;
        //  136    161    164    168    Lcom/intellij/util/IncorrectOperationException;
        //  174    194    197    201    Lcom/intellij/util/IncorrectOperationException;
        //  178    213    216    220    Lcom/intellij/util/IncorrectOperationException;
        //  201    233    236    240    Lcom/intellij/util/IncorrectOperationException;
        //  220    252    255    259    Lcom/intellij/util/IncorrectOperationException;
        //  240    272    275    279    Lcom/intellij/util/IncorrectOperationException;
        //  285    293    293    297    Lcom/intellij/util/IncorrectOperationException;
        //  300    309    312    316    Lcom/intellij/util/IncorrectOperationException;
        //  304    329    332    336    Lcom/intellij/util/IncorrectOperationException;
        //  316    340    340    344    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0201:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public OCType getExpectedType() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getContext:()Lcom/intellij/psi/PsiElement;
        //     4: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //     7: astore_1       
        //     8: aconst_null    
        //     9: astore_2       
        //    10: aload_1        
        //    11: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    14: ifeq            65
        //    17: aload_1        
        //    18: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getContext:()Lcom/intellij/psi/PsiElement;
        //    23: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    26: ifeq            53
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    35: athrow         
        //    36: aload_1        
        //    37: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    42: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    45: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCQualifiedExpressionImpl.getCallExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCCallExpression;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    48: areturn        
        //    49: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    52: athrow         
        //    53: aload_1        
        //    54: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    57: iconst_1       
        //    58: invokestatic    com/jetbrains/cidr/lang/util/OCExpectedTypeUtil.getExpectedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Z)Lcom/jetbrains/cidr/lang/types/OCType;
        //    61: astore_2       
        //    62: goto            167
        //    65: aload_1        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    69: ifeq            167
        //    72: aload_1        
        //    73: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    76: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getInstanceVariableRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    81: astore_3       
        //    82: aload_1        
        //    83: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getPropertyRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    91: astore          4
        //    93: aconst_null    
        //    94: astore          5
        //    96: aload_0        
        //    97: aload_3        
        //    98: if_acmpne       125
        //   101: aload           4
        //   103: ifnull          125
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   112: athrow         
        //   113: aload           4
        //   115: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   120: astore          5
        //   122: goto            150
        //   125: aload_0        
        //   126: aload           4
        //   128: if_acmpne       150
        //   131: aload_3        
        //   132: ifnull          150
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   141: athrow         
        //   142: aload_3        
        //   143: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   148: astore          5
        //   150: aload           5
        //   152: ifnull          167
        //   155: aload           5
        //   157: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   162: areturn        
        //   163: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   166: athrow         
        //   167: aload_2        
        //   168: ifnull          185
        //   171: aload_2        
        //   172: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   175: if_acmpne       227
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   184: athrow         
        //   185: aload_0        
        //   186: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   189: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //   194: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //   199: ifeq            223
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   208: athrow         
        //   209: aload_0        
        //   210: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getProject:()Lcom/intellij/openapi/project/Project;
        //   213: invokestatic    com/jetbrains/cidr/lang/types/OCIdType.pointerToID:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   216: goto            226
        //   219: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   222: athrow         
        //   223: getstatic       com/jetbrains/cidr/lang/types/OCIntType.INT:Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   226: astore_2       
        //   227: aload_2        
        //   228: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  10     29     32     36     Lcom/intellij/util/IncorrectOperationException;
        //  17     49     49     53     Lcom/intellij/util/IncorrectOperationException;
        //  96     106    109    113    Lcom/intellij/util/IncorrectOperationException;
        //  125    135    138    142    Lcom/intellij/util/IncorrectOperationException;
        //  150    163    163    167    Lcom/intellij/util/IncorrectOperationException;
        //  167    178    181    185    Lcom/intellij/util/IncorrectOperationException;
        //  171    202    205    209    Lcom/intellij/util/IncorrectOperationException;
        //  185    219    219    223    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0185:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public OCSymbolGroupContext getSymbolContext() {
        return this.a(new OCResolveContext((PsiElement)this));
    }
    
    private OCSymbolGroupContext a(@NotNull final OCResolveContext p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "context"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getSymbolContext"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getContext:()Lcom/intellij/psi/PsiElement;
        //    48: astore_2       
        //    49: aload_0        
        //    50: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    53: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //    58: astore_3       
        //    59: aload_3        
        //    60: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isCpp:()Z
        //    65: istore          4
        //    67: aload_3        
        //    68: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //    73: istore          5
        //    75: aload_0        
        //    76: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCMacroReferenceElementImpl;
        //    79: ifeq            90
        //    82: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.MACRO_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //    85: areturn        
        //    86: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    89: athrow         
        //    90: aload_2        
        //    91: ifnonnull       100
        //    94: aconst_null    
        //    95: areturn        
        //    96: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    99: athrow         
        //   100: aload_2        
        //   101: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSuperClassRef;
        //   104: ifeq            115
        //   107: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.CLASS_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   110: areturn        
        //   111: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   114: athrow         
        //   115: aload_2        
        //   116: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocolList;
        //   119: ifeq            130
        //   122: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.PROTOCOL_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   125: areturn        
        //   126: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   129: athrow         
        //   130: aload_2        
        //   131: instanceof      Lcom/jetbrains/cidr/lang/psi/OCGotoStatement;
        //   134: ifeq            145
        //   137: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.LABEL_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   140: areturn        
        //   141: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   144: athrow         
        //   145: aload_2        
        //   146: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   149: ifeq            172
        //   152: iload           5
        //   154: ifeq            172
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   163: athrow         
        //   164: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.PROTOCOL_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   167: areturn        
        //   168: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   171: athrow         
        //   172: aload_2        
        //   173: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //   176: ifeq            228
        //   179: aload_2        
        //   180: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //   183: invokeinterface com/jetbrains/cidr/lang/psi/OCCppUsingStatement.isNamespaceUsing:()Z
        //   188: ifeq            228
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   197: athrow         
        //   198: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   201: dup            
        //   202: ldc             "namespace"
        //   204: iconst_2       
        //   205: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   208: dup            
        //   209: iconst_0       
        //   210: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   213: aastore        
        //   214: dup            
        //   215: iconst_1       
        //   216: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   219: aastore        
        //   220: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   223: areturn        
        //   224: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   227: athrow         
        //   228: aload_2        
        //   229: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   232: ifeq            286
        //   235: aload_2        
        //   236: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   239: invokeinterface com/jetbrains/cidr/lang/psi/OCStructLike.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   244: astore          6
        //   246: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   249: dup            
        //   250: aload           6
        //   252: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   255: iconst_4       
        //   256: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   259: dup            
        //   260: iconst_0       
        //   261: aload           6
        //   263: aastore        
        //   264: dup            
        //   265: iconst_1       
        //   266: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   269: aastore        
        //   270: dup            
        //   271: iconst_2       
        //   272: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   275: aastore        
        //   276: dup            
        //   277: iconst_3       
        //   278: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BUILTIN_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   281: aastore        
        //   282: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   285: areturn        
        //   286: aload_0        
        //   287: astore          6
        //   289: aconst_null    
        //   290: astore          7
        //   292: aload           6
        //   294: ifnull          414
        //   297: aload           6
        //   299: bipush          6
        //   301: anewarray       Ljava/lang/Class;
        //   304: dup            
        //   305: iconst_0       
        //   306: ldc             Lcom/jetbrains/cidr/lang/psi/OCParameterList;.class
        //   308: aastore        
        //   309: dup            
        //   310: iconst_1       
        //   311: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   313: aastore        
        //   314: dup            
        //   315: iconst_2       
        //   316: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //   318: aastore        
        //   319: dup            
        //   320: iconst_3       
        //   321: ldc             Lcom/jetbrains/cidr/lang/psi/OCDirective;.class
        //   323: aastore        
        //   324: dup            
        //   325: iconst_4       
        //   326: ldc             Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;.class
        //   328: aastore        
        //   329: dup            
        //   330: iconst_5       
        //   331: ldc             Lcom/jetbrains/cidr/lang/psi/OCStatementExpression;.class
        //   333: aastore        
        //   334: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   337: astore          7
        //   339: aload           7
        //   341: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   344: ifeq            364
        //   347: aload           7
        //   349: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   352: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   357: goto            365
        //   360: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   363: athrow         
        //   364: aconst_null    
        //   365: astore          8
        //   367: aload           8
        //   369: ifnull          386
        //   372: aload           8
        //   374: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   379: goto            387
        //   382: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   385: athrow         
        //   386: aconst_null    
        //   387: astore          9
        //   389: aload           9
        //   391: aload           6
        //   393: iconst_1       
        //   394: invokestatic    com/intellij/psi/util/PsiTreeUtil.isAncestor:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //   397: ifne            407
        //   400: goto            414
        //   403: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   406: athrow         
        //   407: aload           7
        //   409: astore          6
        //   411: goto            292
        //   414: aload           7
        //   416: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //   419: ifeq            430
        //   422: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.MACRO_OR_MACRO_PARAMETER_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   425: areturn        
        //   426: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   429: athrow         
        //   430: aload           7
        //   432: ldc             Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;.class
        //   434: iconst_0       
        //   435: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //   438: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   441: astore          8
        //   443: aload           8
        //   445: ifnull          462
        //   448: aload           8
        //   450: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   455: goto            463
        //   458: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   461: athrow         
        //   462: aconst_null    
        //   463: astore          9
        //   465: aload           9
        //   467: ifnull          484
        //   470: aload           9
        //   472: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   477: goto            485
        //   480: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   483: athrow         
        //   484: aconst_null    
        //   485: astore          10
        //   487: aload           9
        //   489: ifnull          506
        //   492: aload           9
        //   494: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getImplementation:()Lcom/jetbrains/cidr/lang/symbols/objc/OCImplementationSymbol;
        //   499: goto            507
        //   502: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   505: athrow         
        //   506: aconst_null    
        //   507: astore          11
        //   509: aload           10
        //   511: astore          12
        //   513: aload           9
        //   515: ifnull          572
        //   518: invokestatic    com/jetbrains/cidr/lang/workspace/compiler/OCCompilerFeatures.supportsIvarsInImplementation:()Z
        //   521: ifeq            572
        //   524: goto            531
        //   527: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   530: athrow         
        //   531: aload_0        
        //   532: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getProject:()Lcom/intellij/openapi/project/Project;
        //   535: invokestatic    com/intellij/psi/codeStyle/CodeStyleSettingsManager.getSettings:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/codeStyle/CodeStyleSettings;
        //   538: ldc             Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;.class
        //   540: invokevirtual   com/intellij/psi/codeStyle/CodeStyleSettings.getCustomSettings:(Ljava/lang/Class;)Lcom/intellij/psi/codeStyle/CustomCodeStyleSettings;
        //   543: checkcast       Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings;
        //   546: astore          13
        //   548: aload           13
        //   550: ifnull          568
        //   553: aload           13
        //   555: getfield        com/jetbrains/cidr/lang/settings/OCCodeStyleSettings.PUT_IVARS_TO_IMPLEMENTATION:Z
        //   558: ifeq            572
        //   561: goto            568
        //   564: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   567: athrow         
        //   568: aload           11
        //   570: astore          12
        //   572: aload_2        
        //   573: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   576: ifeq            624
        //   579: aload_2        
        //   580: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   583: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getPropertyRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   588: aload_0        
        //   589: if_acmpne       624
        //   592: goto            599
        //   595: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   598: athrow         
        //   599: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   602: dup            
        //   603: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   606: dup            
        //   607: aload_0        
        //   608: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   611: aload           10
        //   613: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   616: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   619: areturn        
        //   620: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   623: athrow         
        //   624: aload_2        
        //   625: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   628: ifeq            676
        //   631: aload_2        
        //   632: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   635: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getInstanceVariableRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   640: aload_0        
        //   641: if_acmpne       676
        //   644: goto            651
        //   647: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   650: athrow         
        //   651: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   654: dup            
        //   655: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   658: dup            
        //   659: aload_0        
        //   660: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   663: aload           12
        //   665: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   668: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   671: areturn        
        //   672: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   675: athrow         
        //   676: aload_2        
        //   677: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   680: ifeq            710
        //   683: aload_2        
        //   684: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   689: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocolExpression;
        //   692: ifeq            710
        //   695: goto            702
        //   698: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   701: athrow         
        //   702: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.PROTOCOL_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   705: areturn        
        //   706: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   709: athrow         
        //   710: aload_2        
        //   711: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompatibilityAlias;
        //   714: ifeq            738
        //   717: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.CLASS_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   720: iconst_1       
        //   721: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   724: dup            
        //   725: iconst_0       
        //   726: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   729: aastore        
        //   730: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.union:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   733: areturn        
        //   734: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   737: athrow         
        //   738: aload_0        
        //   739: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   742: astore          13
        //   744: aload           13
        //   746: aload_1        
        //   747: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getAppropriateToAppendSymbol:(Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   750: astore          14
        //   752: aload           14
        //   754: ifnull          810
        //   757: aload           14
        //   759: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   764: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.canBeNamespace:()Z
        //   767: ifeq            810
        //   770: goto            777
        //   773: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   776: athrow         
        //   777: new             Ljava/lang/StringBuilder;
        //   780: dup            
        //   781: invokespecial   java/lang/StringBuilder.<init>:()V
        //   784: aload           14
        //   786: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   791: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.getNameLowercase:()Ljava/lang/String;
        //   794: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   797: ldc             " member"
        //   799: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   802: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   805: astore          15
        //   807: goto            828
        //   810: aload_2        
        //   811: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //   814: ifeq            824
        //   817: ldc             "member"
        //   819: astore          15
        //   821: goto            828
        //   824: ldc             "variable"
        //   826: astore          15
        //   828: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //   831: dup            
        //   832: aload           15
        //   834: iconst_0       
        //   835: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   838: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //   841: astore          16
        //   843: aload           14
        //   845: ifnull          1007
        //   848: aload           14
        //   850: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   855: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   858: if_acmpne       1007
        //   861: goto            868
        //   864: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   867: athrow         
        //   868: aload           16
        //   870: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   873: dup            
        //   874: aload_0        
        //   875: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   878: aload           14
        //   880: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   883: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   886: aload           16
        //   888: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   891: dup            
        //   892: aload_0        
        //   893: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   896: aload           14
        //   898: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   901: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   904: aload           16
        //   906: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   909: dup            
        //   910: aload_0        
        //   911: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   914: aload           14
        //   916: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   919: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   922: aload           16
        //   924: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   927: dup            
        //   928: aload_0        
        //   929: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   932: aload           14
        //   934: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   937: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //   940: aload           13
        //   942: ifnonnull       1612
        //   945: goto            952
        //   948: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   951: athrow         
        //   952: aload_2        
        //   953: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   956: ifeq            1612
        //   959: goto            966
        //   962: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   965: athrow         
        //   966: aload_2        
        //   967: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   970: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isLValue:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   973: ifne            1612
        //   976: goto            983
        //   979: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   982: athrow         
        //   983: aload           16
        //   985: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //   988: dup            
        //   989: aload_0        
        //   990: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   993: aconst_null    
        //   994: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   997: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1000: goto            1612
        //  1003: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1006: athrow         
        //  1007: aload_0        
        //  1008: iconst_0       
        //  1009: iconst_1       
        //  1010: anewarray       Ljava/lang/Class;
        //  1013: dup            
        //  1014: iconst_0       
        //  1015: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //  1017: aastore        
        //  1018: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;Z[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  1021: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //  1024: astore          17
        //  1026: aload           17
        //  1028: ifnull          1045
        //  1031: aload           17
        //  1033: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //  1038: goto            1046
        //  1041: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1044: athrow         
        //  1045: aconst_null    
        //  1046: astore          18
        //  1048: aload           18
        //  1050: ifnull          1076
        //  1053: aload           18
        //  1055: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.resolveIsStatic:()Z
        //  1058: ifeq            1076
        //  1061: goto            1068
        //  1064: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1067: athrow         
        //  1068: iconst_1       
        //  1069: goto            1077
        //  1072: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1075: athrow         
        //  1076: iconst_0       
        //  1077: istore          19
        //  1079: aload           14
        //  1081: ifnonnull       1378
        //  1084: aload           16
        //  1086: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1089: dup            
        //  1090: aload_0        
        //  1091: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1094: aconst_null    
        //  1095: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1098: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1101: aload           16
        //  1103: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1106: dup            
        //  1107: aload_0        
        //  1108: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.GLOBAL_VARIABLE_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1111: aconst_null    
        //  1112: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1115: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1118: aload           13
        //  1120: ifnonnull       1185
        //  1123: goto            1130
        //  1126: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1129: athrow         
        //  1130: aload_2        
        //  1131: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1134: ifeq            1185
        //  1137: goto            1144
        //  1140: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1143: athrow         
        //  1144: aload_2        
        //  1145: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //  1148: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isLValue:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //  1151: ifne            1185
        //  1154: goto            1161
        //  1157: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1160: athrow         
        //  1161: aload           16
        //  1163: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1166: dup            
        //  1167: aload_0        
        //  1168: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1171: aconst_null    
        //  1172: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1175: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1178: goto            1185
        //  1181: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1184: athrow         
        //  1185: aload           18
        //  1187: ifnull          1202
        //  1190: aload           18
        //  1192: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1195: goto            1203
        //  1198: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1201: athrow         
        //  1202: aconst_null    
        //  1203: astore          20
        //  1205: aload           16
        //  1207: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1210: dup            
        //  1211: aload_0        
        //  1212: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1215: iload           19
        //  1217: aload           20
        //  1219: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1222: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1225: aload           16
        //  1227: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1230: dup            
        //  1231: aload_0        
        //  1232: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1235: iload           19
        //  1237: aload           20
        //  1239: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1242: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1245: aload           16
        //  1247: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1250: dup            
        //  1251: aload_0        
        //  1252: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1255: iload           19
        //  1257: aload           20
        //  1259: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1262: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1265: iload           19
        //  1267: ifne            1315
        //  1270: aload           16
        //  1272: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1275: dup            
        //  1276: aload_0        
        //  1277: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1280: iconst_0       
        //  1281: aload           20
        //  1283: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1286: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1289: aload           16
        //  1291: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1294: dup            
        //  1295: aload_0        
        //  1296: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1299: iconst_0       
        //  1300: aload           20
        //  1302: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1305: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1308: goto            1315
        //  1311: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1314: athrow         
        //  1315: iload           5
        //  1317: ifeq            1375
        //  1320: aload_2        
        //  1321: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //  1324: ifeq            1375
        //  1327: goto            1334
        //  1330: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1333: athrow         
        //  1334: aload_2        
        //  1335: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1340: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;
        //  1343: ifeq            1375
        //  1346: goto            1353
        //  1349: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1352: athrow         
        //  1353: aload           16
        //  1355: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1358: dup            
        //  1359: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1362: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  1365: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1368: goto            1375
        //  1371: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1374: athrow         
        //  1375: goto            1612
        //  1378: aload           14
        //  1380: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1385: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isType:()Z
        //  1388: ifeq            1612
        //  1391: iload           19
        //  1393: ifne            1502
        //  1396: goto            1403
        //  1399: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1402: athrow         
        //  1403: aload           18
        //  1405: ifnull          1502
        //  1408: goto            1415
        //  1411: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1414: athrow         
        //  1415: aload           18
        //  1417: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1420: astore          20
        //  1422: aload           14
        //  1424: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1427: ifeq            1499
        //  1430: aload           20
        //  1432: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1435: ifeq            1499
        //  1438: goto            1445
        //  1441: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1444: athrow         
        //  1445: aload_1        
        //  1446: ifnonnull       1468
        //  1449: goto            1456
        //  1452: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1455: athrow         
        //  1456: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //  1459: dup            
        //  1460: aload_0        
        //  1461: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //  1464: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //  1467: astore_1       
        //  1468: aload           14
        //  1470: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1473: aload           20
        //  1475: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  1478: aload_1        
        //  1479: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.isSubstructOf:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //  1482: ifne            1493
        //  1485: iconst_1       
        //  1486: goto            1494
        //  1489: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1492: athrow         
        //  1493: iconst_0       
        //  1494: istore          19
        //  1496: goto            1502
        //  1499: iconst_1       
        //  1500: istore          19
        //  1502: aload           16
        //  1504: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1507: dup            
        //  1508: aload_0        
        //  1509: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1512: iload           19
        //  1514: aload           14
        //  1516: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1519: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1522: aload           16
        //  1524: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1527: dup            
        //  1528: aload_0        
        //  1529: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1532: iload           19
        //  1534: aload           14
        //  1536: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1539: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1542: aload           16
        //  1544: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1547: dup            
        //  1548: aload_0        
        //  1549: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1552: iload           19
        //  1554: aload           14
        //  1556: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1559: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1562: iload           19
        //  1564: ifne            1612
        //  1567: aload           16
        //  1569: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1572: dup            
        //  1573: aload_0        
        //  1574: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1577: iconst_0       
        //  1578: aload           14
        //  1580: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1583: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1586: aload           16
        //  1588: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext;
        //  1591: dup            
        //  1592: aload_0        
        //  1593: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1596: iconst_0       
        //  1597: aload           14
        //  1599: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext$StructMemberContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;ZLcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1602: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1605: goto            1612
        //  1608: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1611: athrow         
        //  1612: aload           16
        //  1614: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1617: dup            
        //  1618: aload_0        
        //  1619: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.BUILTIN_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1622: aload           14
        //  1624: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1627: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1630: iload           4
        //  1632: ifeq            1678
        //  1635: aload           16
        //  1637: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1640: dup            
        //  1641: aload_0        
        //  1642: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1645: aload           14
        //  1647: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1650: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1653: aload           16
        //  1655: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1658: dup            
        //  1659: aload_0        
        //  1660: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1663: aload           14
        //  1665: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1668: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1671: goto            1678
        //  1674: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1677: athrow         
        //  1678: aload           13
        //  1680: ifnonnull       1886
        //  1683: aload           7
        //  1685: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //  1688: ifne            1713
        //  1691: goto            1698
        //  1694: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1697: athrow         
        //  1698: aload           7
        //  1700: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementExpression;
        //  1703: ifeq            1754
        //  1706: goto            1713
        //  1709: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1712: athrow         
        //  1713: aload           16
        //  1715: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1718: dup            
        //  1719: aload_0        
        //  1720: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.LOCAL_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1723: aconst_null    
        //  1724: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1727: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1730: aload           16
        //  1732: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1735: dup            
        //  1736: aload_0        
        //  1737: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CATCH_EXCEPTION_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1740: aconst_null    
        //  1741: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1744: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1747: goto            1754
        //  1750: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1753: athrow         
        //  1754: aload           9
        //  1756: ifnull          1784
        //  1759: aload           16
        //  1761: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1764: dup            
        //  1765: aload_0        
        //  1766: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1769: aload           12
        //  1771: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1774: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1777: goto            1784
        //  1780: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1783: athrow         
        //  1784: aload           7
        //  1786: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //  1789: ifne            1837
        //  1792: aload           7
        //  1794: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //  1797: ifne            1837
        //  1800: goto            1807
        //  1803: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1806: athrow         
        //  1807: aload           7
        //  1809: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //  1812: ifne            1837
        //  1815: goto            1822
        //  1818: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1821: athrow         
        //  1822: aload           7
        //  1824: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatementExpression;
        //  1827: ifeq            1886
        //  1830: goto            1837
        //  1833: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1836: athrow         
        //  1837: aload           7
        //  1839: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //  1841: iconst_0       
        //  1842: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //  1845: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //  1848: astore          17
        //  1850: aload           16
        //  1852: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  1855: dup            
        //  1856: aload_0        
        //  1857: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1860: aload           17
        //  1862: ifnull          1879
        //  1865: aload           17
        //  1867: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //  1872: goto            1880
        //  1875: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1878: athrow         
        //  1879: aconst_null    
        //  1880: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  1883: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  1886: aload           14
        //  1888: astore          17
        //  1890: aload_2        
        //  1891: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  1894: ifeq            2010
        //  1897: aload_2        
        //  1898: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  1903: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCaseStatement;
        //  1906: ifeq            2010
        //  1909: goto            1916
        //  1912: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1915: athrow         
        //  1916: aload_2        
        //  1917: ldc             Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;.class
        //  1919: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  1922: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;
        //  1925: astore          18
        //  1927: aload           18
        //  1929: ifnull          1946
        //  1932: aload           18
        //  1934: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCDeclarationOrExpression;
        //  1939: goto            1947
        //  1942: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1945: athrow         
        //  1946: aconst_null    
        //  1947: astore          19
        //  1949: aload           19
        //  1951: ifnull          1968
        //  1954: aload           19
        //  1956: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarationOrExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //  1961: goto            1969
        //  1964: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1967: athrow         
        //  1968: aconst_null    
        //  1969: astore          20
        //  1971: aload           20
        //  1973: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1976: ifeq            2010
        //  1979: aload           20
        //  1981: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  1984: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1987: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1990: if_acmpne       2010
        //  1993: goto            2000
        //  1996: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  1999: athrow         
        //  2000: aload           20
        //  2002: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //  2005: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //  2008: astore          17
        //  2010: aload           16
        //  2012: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //  2015: dup            
        //  2016: aload_0        
        //  2017: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2020: aload           17
        //  2022: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //  2025: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //  2028: aload_2        
        //  2029: iconst_2       
        //  2030: anewarray       Ljava/lang/Class;
        //  2033: dup            
        //  2034: iconst_0       
        //  2035: ldc             Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;.class
        //  2037: aastore        
        //  2038: dup            
        //  2039: iconst_1       
        //  2040: ldc             Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;.class
        //  2042: aastore        
        //  2043: invokestatic    com/intellij/psi/util/PsiTreeUtil.skipParentsOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  2046: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSizeofExpression;
        //  2049: ifne            2085
        //  2052: aload_2        
        //  2053: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  2056: ifeq            2215
        //  2059: goto            2066
        //  2062: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2065: athrow         
        //  2066: aload_2        
        //  2067: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  2070: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.isParameterOfBuiltInTrait:()Z
        //  2075: ifeq            2215
        //  2078: goto            2085
        //  2081: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2084: athrow         
        //  2085: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2088: dup            
        //  2089: ldc             "variable or type"
        //  2091: iconst_1       
        //  2092: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2095: dup            
        //  2096: iconst_0       
        //  2097: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2100: aastore        
        //  2101: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2104: astore          18
        //  2106: iload           5
        //  2108: ifeq            2142
        //  2111: aload           18
        //  2113: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2116: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2119: aload           18
        //  2121: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.IMPLEMENTATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2124: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2127: aload           18
        //  2129: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.COMPATIBILITY_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2132: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2135: goto            2142
        //  2138: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2141: athrow         
        //  2142: iload           4
        //  2144: ifeq            2202
        //  2147: aload           18
        //  2149: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2152: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2155: aload           18
        //  2157: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2160: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2163: aload           18
        //  2165: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2168: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2171: aload           18
        //  2173: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2176: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2179: aload           18
        //  2181: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2184: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2187: aload           18
        //  2189: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.USING_SYMBOL_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2192: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2195: goto            2202
        //  2198: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2201: athrow         
        //  2202: aload           18
        //  2204: aload           16
        //  2206: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
        //  2209: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
        //  2212: aload           18
        //  2214: areturn        
        //  2215: aload_2        
        //  2216: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  2219: ifeq            2377
        //  2222: aload_2        
        //  2223: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //  2228: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //  2231: ifne            2287
        //  2234: goto            2241
        //  2237: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2240: athrow         
        //  2241: aload_2        
        //  2242: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //  2247: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //  2250: ifeq            2377
        //  2253: goto            2260
        //  2256: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2259: athrow         
        //  2260: aload_2        
        //  2261: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //  2266: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //  2269: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifyingTokenKind:()Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2274: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DOT:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //  2277: if_acmpne       2377
        //  2280: goto            2287
        //  2283: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2286: athrow         
        //  2287: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2290: dup            
        //  2291: ldc             "variable or class"
        //  2293: iconst_1       
        //  2294: anewarray       Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2297: dup            
        //  2298: iconst_0       
        //  2299: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2302: aastore        
        //  2303: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Ljava/lang/String;[Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2306: astore          18
        //  2308: iload           5
        //  2310: ifeq            2344
        //  2313: aload           18
        //  2315: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2318: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2321: aload           18
        //  2323: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.IMPLEMENTATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2326: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2329: aload           18
        //  2331: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.COMPATIBILITY_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2334: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2337: goto            2344
        //  2340: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2343: athrow         
        //  2344: iload           4
        //  2346: ifeq            2364
        //  2349: aload           18
        //  2351: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.USING_SYMBOL_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2354: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2357: goto            2364
        //  2360: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2363: athrow         
        //  2364: aload           18
        //  2366: aload           16
        //  2368: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
        //  2371: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
        //  2374: aload           18
        //  2376: areturn        
        //  2377: aload_2        
        //  2378: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //  2381: ifeq            2408
        //  2384: aload_2        
        //  2385: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  2390: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  2395: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarationStatement;
        //  2398: ifne            2441
        //  2401: goto            2408
        //  2404: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2407: athrow         
        //  2408: aload_2        
        //  2409: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  2412: ifeq            2476
        //  2415: goto            2422
        //  2418: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2421: athrow         
        //  2422: aload_2        
        //  2423: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //  2428: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //  2431: ifeq            2476
        //  2434: goto            2441
        //  2437: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2440: athrow         
        //  2441: aload_2        
        //  2442: invokeinterface com/intellij/psi/PsiElement.isPhysical:()Z
        //  2447: ifne            2476
        //  2450: goto            2457
        //  2453: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2456: athrow         
        //  2457: aload           16
        //  2459: aload_3        
        //  2460: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.typeContext:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2463: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
        //  2466: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
        //  2469: aload           16
        //  2471: areturn        
        //  2472: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2475: athrow         
        //  2476: aload_2        
        //  2477: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //  2480: ifeq            2615
        //  2483: iload           4
        //  2485: ifeq            2612
        //  2488: goto            2495
        //  2491: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2494: athrow         
        //  2495: aload_2        
        //  2496: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //  2501: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //  2504: ifne            2533
        //  2507: goto            2514
        //  2510: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2513: athrow         
        //  2514: aload_2        
        //  2515: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //  2520: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;
        //  2523: ifeq            2612
        //  2526: goto            2533
        //  2529: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2532: athrow         
        //  2533: aload           16
        //  2535: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2538: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2541: aload           16
        //  2543: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.USING_SYMBOL_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2546: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2549: aload           16
        //  2551: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2554: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2557: aload           16
        //  2559: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2562: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2565: aload           16
        //  2567: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2570: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2573: aload           16
        //  2575: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2578: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2581: aload           16
        //  2583: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2586: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2589: aload           16
        //  2591: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2594: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2597: aload           16
        //  2599: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CPP_CONSTRUCTOR_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  2602: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContext:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)V
        //  2605: goto            2612
        //  2608: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2611: athrow         
        //  2612: aload           16
        //  2614: areturn        
        //  2615: aload_2        
        //  2616: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //  2619: ifne            2636
        //  2622: aload_2        
        //  2623: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppBaseClause;
        //  2626: ifeq            2865
        //  2629: goto            2636
        //  2632: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2635: athrow         
        //  2636: aload_2        
        //  2637: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //  2642: astore          18
        //  2644: aload           18
        //  2646: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //  2649: ifeq            2660
        //  2652: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.CONSTRUCTOR_CONTEXT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2655: areturn        
        //  2656: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2659: athrow         
        //  2660: aload           18
        //  2662: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //  2665: ifeq            2712
        //  2668: aload           18
        //  2670: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //  2673: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //  2678: invokeinterface java/util/List.size:()I
        //  2683: ifne            2712
        //  2686: goto            2693
        //  2689: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2692: athrow         
        //  2693: aload           16
        //  2695: aload_3        
        //  2696: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.typeContext:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2699: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
        //  2702: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
        //  2705: aload           16
        //  2707: areturn        
        //  2708: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2711: athrow         
        //  2712: aload           18
        //  2714: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateArgumentList;
        //  2717: ifeq            2739
        //  2720: aload           16
        //  2722: aload_3        
        //  2723: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.typeContext:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2726: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
        //  2729: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
        //  2732: aload           16
        //  2734: areturn        
        //  2735: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2738: athrow         
        //  2739: aload           18
        //  2741: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;
        //  2744: ifeq            2860
        //  2747: aload           18
        //  2749: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //  2752: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //  2757: invokeinterface java/util/List.size:()I
        //  2762: iconst_1       
        //  2763: if_icmpne       2860
        //  2766: goto            2773
        //  2769: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2772: athrow         
        //  2773: aload           18
        //  2775: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //  2778: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getDeclarators:()Ljava/util/List;
        //  2783: iconst_0       
        //  2784: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //  2789: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //  2792: astore          19
        //  2794: aload           19
        //  2796: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.isEmpty:()Z
        //  2801: ifeq            2860
        //  2804: aload           18
        //  2806: iconst_1       
        //  2807: anewarray       Ljava/lang/Class;
        //  2810: dup            
        //  2811: iconst_0       
        //  2812: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;.class
        //  2814: aastore        
        //  2815: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //  2818: astore          20
        //  2820: aload           20
        //  2822: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //  2825: ifeq            2860
        //  2828: aload           20
        //  2830: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //  2835: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //  2840: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_KR_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //  2843: if_acmpne       2860
        //  2846: goto            2853
        //  2849: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2852: athrow         
        //  2853: aload           16
        //  2855: areturn        
        //  2856: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2859: athrow         
        //  2860: aload_3        
        //  2861: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.typeContext:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2864: areturn        
        //  2865: aload_2        
        //  2866: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppUsingStatement;
        //  2869: ifeq            2891
        //  2872: aload           16
        //  2874: aload_3        
        //  2875: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.typeContext:(Lcom/jetbrains/cidr/lang/OCLanguageKind;)Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //  2878: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.getSymbolContexts:()Ljava/util/List;
        //  2881: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.addSymbolContexts:(Ljava/util/List;)V
        //  2884: aload           16
        //  2886: areturn        
        //  2887: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //  2890: athrow         
        //  2891: aconst_null    
        //  2892: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  75     86     86     90     Lcom/intellij/util/IncorrectOperationException;
        //  90     96     96     100    Lcom/intellij/util/IncorrectOperationException;
        //  100    111    111    115    Lcom/intellij/util/IncorrectOperationException;
        //  115    126    126    130    Lcom/intellij/util/IncorrectOperationException;
        //  130    141    141    145    Lcom/intellij/util/IncorrectOperationException;
        //  145    157    160    164    Lcom/intellij/util/IncorrectOperationException;
        //  152    168    168    172    Lcom/intellij/util/IncorrectOperationException;
        //  172    191    194    198    Lcom/intellij/util/IncorrectOperationException;
        //  179    224    224    228    Lcom/intellij/util/IncorrectOperationException;
        //  339    360    360    364    Lcom/intellij/util/IncorrectOperationException;
        //  367    382    382    386    Lcom/intellij/util/IncorrectOperationException;
        //  389    403    403    407    Lcom/intellij/util/IncorrectOperationException;
        //  414    426    426    430    Lcom/intellij/util/IncorrectOperationException;
        //  443    458    458    462    Lcom/intellij/util/IncorrectOperationException;
        //  465    480    480    484    Lcom/intellij/util/IncorrectOperationException;
        //  487    502    502    506    Lcom/intellij/util/IncorrectOperationException;
        //  513    524    527    531    Lcom/intellij/util/IncorrectOperationException;
        //  548    561    564    568    Lcom/intellij/util/IncorrectOperationException;
        //  572    592    595    599    Lcom/intellij/util/IncorrectOperationException;
        //  579    620    620    624    Lcom/intellij/util/IncorrectOperationException;
        //  624    644    647    651    Lcom/intellij/util/IncorrectOperationException;
        //  631    672    672    676    Lcom/intellij/util/IncorrectOperationException;
        //  676    695    698    702    Lcom/intellij/util/IncorrectOperationException;
        //  683    706    706    710    Lcom/intellij/util/IncorrectOperationException;
        //  710    734    734    738    Lcom/intellij/util/IncorrectOperationException;
        //  752    770    773    777    Lcom/intellij/util/IncorrectOperationException;
        //  843    861    864    868    Lcom/intellij/util/IncorrectOperationException;
        //  848    945    948    952    Lcom/intellij/util/IncorrectOperationException;
        //  868    959    962    966    Lcom/intellij/util/IncorrectOperationException;
        //  952    976    979    983    Lcom/intellij/util/IncorrectOperationException;
        //  966    1003   1003   1007   Lcom/intellij/util/IncorrectOperationException;
        //  1026   1041   1041   1045   Lcom/intellij/util/IncorrectOperationException;
        //  1048   1061   1064   1068   Lcom/intellij/util/IncorrectOperationException;
        //  1053   1072   1072   1076   Lcom/intellij/util/IncorrectOperationException;
        //  1079   1123   1126   1130   Lcom/intellij/util/IncorrectOperationException;
        //  1084   1137   1140   1144   Lcom/intellij/util/IncorrectOperationException;
        //  1130   1154   1157   1161   Lcom/intellij/util/IncorrectOperationException;
        //  1144   1178   1181   1185   Lcom/intellij/util/IncorrectOperationException;
        //  1185   1198   1198   1202   Lcom/intellij/util/IncorrectOperationException;
        //  1205   1308   1311   1315   Lcom/intellij/util/IncorrectOperationException;
        //  1315   1327   1330   1334   Lcom/intellij/util/IncorrectOperationException;
        //  1320   1346   1349   1353   Lcom/intellij/util/IncorrectOperationException;
        //  1334   1368   1371   1375   Lcom/intellij/util/IncorrectOperationException;
        //  1378   1396   1399   1403   Lcom/intellij/util/IncorrectOperationException;
        //  1391   1408   1411   1415   Lcom/intellij/util/IncorrectOperationException;
        //  1422   1438   1441   1445   Lcom/intellij/util/IncorrectOperationException;
        //  1430   1449   1452   1456   Lcom/intellij/util/IncorrectOperationException;
        //  1468   1489   1489   1493   Lcom/intellij/util/IncorrectOperationException;
        //  1502   1605   1608   1612   Lcom/intellij/util/IncorrectOperationException;
        //  1612   1671   1674   1678   Lcom/intellij/util/IncorrectOperationException;
        //  1678   1691   1694   1698   Lcom/intellij/util/IncorrectOperationException;
        //  1683   1706   1709   1713   Lcom/intellij/util/IncorrectOperationException;
        //  1698   1747   1750   1754   Lcom/intellij/util/IncorrectOperationException;
        //  1754   1777   1780   1784   Lcom/intellij/util/IncorrectOperationException;
        //  1784   1800   1803   1807   Lcom/intellij/util/IncorrectOperationException;
        //  1792   1815   1818   1822   Lcom/intellij/util/IncorrectOperationException;
        //  1807   1830   1833   1837   Lcom/intellij/util/IncorrectOperationException;
        //  1850   1875   1875   1879   Lcom/intellij/util/IncorrectOperationException;
        //  1890   1909   1912   1916   Lcom/intellij/util/IncorrectOperationException;
        //  1927   1942   1942   1946   Lcom/intellij/util/IncorrectOperationException;
        //  1949   1964   1964   1968   Lcom/intellij/util/IncorrectOperationException;
        //  1971   1993   1996   2000   Lcom/intellij/util/IncorrectOperationException;
        //  2010   2059   2062   2066   Lcom/intellij/util/IncorrectOperationException;
        //  2052   2078   2081   2085   Lcom/intellij/util/IncorrectOperationException;
        //  2106   2135   2138   2142   Lcom/intellij/util/IncorrectOperationException;
        //  2142   2195   2198   2202   Lcom/intellij/util/IncorrectOperationException;
        //  2215   2234   2237   2241   Lcom/intellij/util/IncorrectOperationException;
        //  2222   2253   2256   2260   Lcom/intellij/util/IncorrectOperationException;
        //  2241   2280   2283   2287   Lcom/intellij/util/IncorrectOperationException;
        //  2308   2337   2340   2344   Lcom/intellij/util/IncorrectOperationException;
        //  2344   2357   2360   2364   Lcom/intellij/util/IncorrectOperationException;
        //  2377   2401   2404   2408   Lcom/intellij/util/IncorrectOperationException;
        //  2384   2415   2418   2422   Lcom/intellij/util/IncorrectOperationException;
        //  2408   2434   2437   2441   Lcom/intellij/util/IncorrectOperationException;
        //  2422   2450   2453   2457   Lcom/intellij/util/IncorrectOperationException;
        //  2441   2472   2472   2476   Lcom/intellij/util/IncorrectOperationException;
        //  2476   2488   2491   2495   Lcom/intellij/util/IncorrectOperationException;
        //  2483   2507   2510   2514   Lcom/intellij/util/IncorrectOperationException;
        //  2495   2526   2529   2533   Lcom/intellij/util/IncorrectOperationException;
        //  2514   2605   2608   2612   Lcom/intellij/util/IncorrectOperationException;
        //  2615   2629   2632   2636   Lcom/intellij/util/IncorrectOperationException;
        //  2644   2656   2656   2660   Lcom/intellij/util/IncorrectOperationException;
        //  2660   2686   2689   2693   Lcom/intellij/util/IncorrectOperationException;
        //  2668   2708   2708   2712   Lcom/intellij/util/IncorrectOperationException;
        //  2712   2735   2735   2739   Lcom/intellij/util/IncorrectOperationException;
        //  2739   2766   2769   2773   Lcom/intellij/util/IncorrectOperationException;
        //  2820   2846   2849   2853   Lcom/intellij/util/IncorrectOperationException;
        //  2828   2856   2856   2860   Lcom/intellij/util/IncorrectOperationException;
        //  2865   2887   2887   2891   Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0868:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static OCSymbol getAppropriateToAppendSymbol(final OCCppNamespaceQualifier ocCppNamespaceQualifier, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "getAppropriateToAppendSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCSymbol ocSymbol = null;
        if (ocCppNamespaceQualifier != null) {
            final OCSymbolReference.LocalReference localReference = OCSymbolReference.getLocalReference(ocCppNamespaceQualifier, OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE_LIKE);
            for (PsiElement psiElement = ocCppNamespaceQualifier.getParent(); psiElement instanceof OCCppNamespaceQualifier; psiElement = psiElement.getParent()) {}
            final List filter = ContainerUtil.filter((Collection)localReference.resolveToSymbols(true, true, true, ocResolveContext), ocSymbol -> {
                Label_0031: {
                    try {
                        if (!OCSymbol.NON_PREDIFINITION_CONDITION.value((Object)ocSymbol)) {
                            return false;
                        }
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                        final boolean b = ocSymbolKind.canBeNamespace();
                        if (b) {
                            break Label_0031;
                        }
                        return false;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCSymbol ocSymbol2 = ocSymbol;
                        final OCSymbolKind ocSymbolKind = ocSymbol2.getKind();
                        final boolean b = ocSymbolKind.canBeNamespace();
                        if (b) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return false;
            });
            if (filter.size() == 0) {
                ocSymbol = null;
            }
            else if (filter.size() == 1) {
                ocSymbol = filter.get(0);
            }
            else {
                for (final OCSymbol ocSymbol2 : filter) {
                    if (Comparing.equal((Object)ocSymbol2.getContainingOCFile(), (Object)ocCppNamespaceQualifier.getContainingOCFile())) {
                        ocSymbol = ocSymbol2;
                        break;
                    }
                }
                if (ocSymbol == null) {
                    ocSymbol = filter.get(0);
                }
            }
        }
        return ocSymbol;
    }
    
    public OCInstanceVariableSymbol getSymbol() {
        try {
            if (!(this.getParent() instanceof OCSynthesizeProperty)) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return this.getContainingOCFile().findSymbol(this, OCInstanceVariableSymbol.class);
    }
    
    @NotNull
    public String getCanonicalText() {
        final String identifierName = OCElementUtil.getIdentifierName(this.findReferenceTokenInCall());
        String s5 = null;
        Label_0112: {
            if (this.getParent() instanceof OCStructLike) {
                final OCStructLike ocStructLike = (OCStructLike)this.getParent();
                String s4 = null;
                Label_0077: {
                    try {
                        if (ocStructLike.isDeclaration()) {
                            break Label_0112;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final OCStructLike ocStructLike2 = ocStructLike;
                        final OCSymbolKind ocSymbolKind = ocStructLike2.getKind();
                        final String s = ocSymbolKind.getNameLowercase();
                        final StringBuilder sb2 = sb.append(s);
                        final String s2 = " ";
                        final StringBuilder sb3 = sb2.append(s2);
                        final String s3 = identifierName;
                        final StringBuilder sb4 = sb3.append(s3);
                        s4 = sb4.toString();
                        if (s4 == null) {
                            break Label_0077;
                        }
                        return s4;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final StringBuilder sb = new StringBuilder();
                        final OCStructLike ocStructLike2 = ocStructLike;
                        final OCSymbolKind ocSymbolKind = ocStructLike2.getKind();
                        final String s = ocSymbolKind.getNameLowercase();
                        final StringBuilder sb2 = sb.append(s);
                        final String s2 = " ";
                        final StringBuilder sb3 = sb2.append(s2);
                        final String s3 = identifierName;
                        final StringBuilder sb4 = sb3.append(s3);
                        s4 = sb4.toString();
                        if (s4 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "getCanonicalText"));
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return s4;
            }
            try {
                s5 = identifierName;
                if (s5 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "getCanonicalText"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return s5;
    }
    
    public OCReferenceElement handleElementRename(final String s) throws IncorrectOperationException {
        Label_0040: {
            try {
                if (s.equals(this.getName())) {
                    return this.getElement();
                }
                final OCReferenceElementImpl ocReferenceElementImpl = this;
                final String s2 = s;
                ocReferenceElementImpl.setNameOfIdentifier(s2);
                final OCReferenceElementImpl ocReferenceElementImpl2 = this;
                final int n = 1;
                final Class[] array = new Class[n];
                final int n2 = 0;
                final Class<OCMacroCall> clazz = OCMacroCall.class;
                array[n2] = clazz;
                final PsiElement psiElement = PsiTreeUtil.getContextOfType((PsiElement)ocReferenceElementImpl2, array);
                if (psiElement != null) {
                    break Label_0040;
                }
                return this.getElement();
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                final OCReferenceElementImpl ocReferenceElementImpl = this;
                final String s2 = s;
                ocReferenceElementImpl.setNameOfIdentifier(s2);
                final OCReferenceElementImpl ocReferenceElementImpl2 = this;
                final int n = 1;
                final Class[] array = new Class[n];
                final int n2 = 0;
                final Class<OCMacroCall> clazz = OCMacroCall.class;
                array[n2] = clazz;
                final PsiElement psiElement = PsiTreeUtil.getContextOfType((PsiElement)ocReferenceElementImpl2, array);
                if (psiElement != null) {
                    FileSymbolTablesCache.getInstance(this.getProject()).scheduleReparseFile(this.getContainingOCFile());
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return this.getElement();
    }
    
    public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "bindToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0126: {
            Label_0076: {
                try {
                    if (!(this.getParent() instanceof OCReferenceExpression)) {
                        break Label_0126;
                    }
                    final OCReferenceElementImpl ocReferenceElementImpl = this;
                    final PsiElement psiElement = ocReferenceElementImpl.getParent();
                    final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)psiElement;
                    final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression.getSelfSuperToken();
                    if (selfSuperToken != null) {
                        break Label_0076;
                    }
                    break Label_0126;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCReferenceElementImpl ocReferenceElementImpl = this;
                    final PsiElement psiElement = ocReferenceElementImpl.getParent();
                    final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)psiElement;
                    final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression.getSelfSuperToken();
                    if (selfSuperToken == null) {
                        break Label_0126;
                    }
                    if (!(ocSymbol instanceof OCClassSymbol)) {
                        break Label_0126;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getParentOfType((PsiElement)this, (Class)OCMethod.class);
            try {
                if (ocMethod == null) {
                    return (PsiElement)this;
                }
                final OCMethod ocMethod2 = ocMethod;
                final boolean b = ocMethod2.isInstanceMethod();
                if (b) {
                    return (PsiElement)this;
                }
                break Label_0126;
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            try {
                final OCMethod ocMethod2 = ocMethod;
                final boolean b = ocMethod2.isInstanceMethod();
                if (b) {
                    return (PsiElement)this;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            try {
                if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                    return (PsiElement)this.handleElementRename(ocSymbol.getName());
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        OCQualifiedName ocQualifiedName = ((OCSymbolWithQualifiedName)ocSymbol).getResolvedQualifiedName();
        Label_0207: {
            Label_0184: {
                try {
                    if (!ocSymbol.getKind().isConstructorOrDestructor() || ocQualifiedName == null) {
                        break Label_0184;
                    }
                }
                catch (IncorrectOperationException ex7) {
                    throw a(ex7);
                }
                ocQualifiedName = ocQualifiedName.getQualifier();
                try {
                    if (ocQualifiedName == null) {
                        break Label_0207;
                    }
                    final OCQualifiedName ocQualifiedName2 = ocQualifiedName;
                    final OCReferenceElementImpl ocReferenceElementImpl2 = this;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                    final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                    final boolean b2 = OCBindUtil.setShortestPossibleName(ocQualifiedName2, ocReferenceElementImpl2, ocSymbolWithQualifiedName2);
                    if (!b2) {
                        break Label_0207;
                    }
                    return (PsiElement)this;
                }
                catch (IncorrectOperationException ex8) {
                    throw a(ex8);
                }
            }
            try {
                final OCQualifiedName ocQualifiedName2 = ocQualifiedName;
                final OCReferenceElementImpl ocReferenceElementImpl2 = this;
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol;
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName;
                final boolean b2 = OCBindUtil.setShortestPossibleName(ocQualifiedName2, ocReferenceElementImpl2, ocSymbolWithQualifiedName2);
                if (b2) {
                    return (PsiElement)this;
                }
                if (ocSymbol.getKind() != OCSymbolKind.TYPEDEF) {
                    return (PsiElement)this;
                }
            }
            catch (IncorrectOperationException ex9) {
                throw a(ex9);
            }
        }
        return OCChangeUtil.replaceHandlingMacros((PsiElement)this, (PsiElement)OCElementFactory.typeElementFromText(ocSymbol.getType().getBestNameInContext((PsiElement)this), (PsiElement)this));
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "bindToElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
        try {
            if (symbol != null) {
                return this.bindToSymbol(symbol);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return psiElement;
    }
    
    public boolean isReferenceTo(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //     4: ifne            13
        //     7: iconst_0       
        //     8: ireturn        
        //     9: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    12: athrow         
        //    13: aload_1        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    22: astore_2       
        //    23: aload_2        
        //    24: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    27: ifeq            96
        //    30: aload_0        
        //    31: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //    34: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    37: ifeq            96
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    46: athrow         
        //    47: aload_0        
        //    48: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.getParent:()Lcom/intellij/psi/PsiElement;
        //    51: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    54: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getInstanceVariableRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    59: ifnonnull       96
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    68: athrow         
        //    69: aload_0        
        //    70: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //    73: dup            
        //    74: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;
        //    77: dup            
        //    78: aconst_null    
        //    79: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INSTANCE_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    82: aconst_null    
        //    83: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolContext.<init>:(Lcom/jetbrains/cidr/lang/util/OCExpectedTypeUtil$Expectable;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //    86: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolContext;)V
        //    89: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.resolveToSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    92: astore_3       
        //    93: goto            101
        //    96: aload_0        
        //    97: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   100: astore_3       
        //   101: aload_2        
        //   102: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   105: ifeq            153
        //   108: aload_3        
        //   109: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   112: ifeq            153
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   121: athrow         
        //   122: aload_2        
        //   123: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   128: aload_3        
        //   129: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   134: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   137: ifeq            153
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   146: athrow         
        //   147: iconst_1       
        //   148: ireturn        
        //   149: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: aload_3        
        //   154: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   157: ifeq            188
        //   160: aload_3        
        //   161: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   164: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
        //   167: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //   172: aload_2        
        //   173: invokedynamic   test:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Ljava/util/function/Predicate;
        //   178: invokeinterface java/util/stream/Stream.anyMatch:(Ljava/util/function/Predicate;)Z
        //   183: ireturn        
        //   184: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   187: athrow         
        //   188: aload_3        
        //   189: ifnull          217
        //   192: aload_3        
        //   193: aload_2        
        //   194: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isSameSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //   199: ifeq            217
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   208: athrow         
        //   209: iconst_1       
        //   210: goto            218
        //   213: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   216: athrow         
        //   217: iconst_0       
        //   218: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
        //  23     40     43     47     Lcom/intellij/util/IncorrectOperationException;
        //  30     62     65     69     Lcom/intellij/util/IncorrectOperationException;
        //  101    115    118    122    Lcom/intellij/util/IncorrectOperationException;
        //  108    140    143    147    Lcom/intellij/util/IncorrectOperationException;
        //  122    149    149    153    Lcom/intellij/util/IncorrectOperationException;
        //  153    184    184    188    Lcom/intellij/util/IncorrectOperationException;
        //  188    202    205    209    Lcom/intellij/util/IncorrectOperationException;
        //  192    213    213    217    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0122:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @NotNull
    public Object[] getVariants() {
        Object[] empty_OBJECT_ARRAY;
        try {
            empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
            if (empty_OBJECT_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return empty_OBJECT_ARRAY;
    }
    
    public boolean isSoft() {
        return false;
    }
    
    public PsiMetaData getMetaData() {
        return (PsiMetaData)new PsiWritableMetaData() {
            public PsiElement getDeclaration() {
                return (PsiElement)OCReferenceElementImpl.this;
            }
            
            public String getName(final PsiElement psiElement) {
                return OCReferenceElementImpl.this.getName();
            }
            
            public String getName() {
                return OCReferenceElementImpl.this.getName();
            }
            
            public void setName(final String s) throws IncorrectOperationException {
                OCReferenceElementImpl.this.handleElementRename(s);
            }
            
            public void init(final PsiElement psiElement) {
                throw new UnsupportedOperationException("Not implemented");
            }
            
            @NotNull
            public Object[] getDependences() {
                throw new UnsupportedOperationException("Not implemented");
            }
        };
    }
    
    static {
        IN_HEADER_FILE = (ocSymbol -> {
            final VirtualFile containingFile = ocSymbol.getContainingFile();
            Label_0028: {
                try {
                    if (containingFile == null) {
                        return false;
                    }
                    final VirtualFile virtualFile = containingFile;
                    final String s = virtualFile.getName();
                    final boolean b = OCFileImpl.isHeaderFile(s);
                    if (b) {
                        break Label_0028;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final VirtualFile virtualFile = containingFile;
                    final String s = virtualFile.getName();
                    final boolean b = OCFileImpl.isHeaderFile(s);
                    if (b) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return false;
        });
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    private static class MyResolver implements ResolveCache.AbstractResolver<OCReferenceElement, Pair<OCSymbol, Boolean>>
    {
        private OCResolveContext myContext;
        
        public MyResolver(@NotNull final OCResolveContext myContext) {
            if (myContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl$MyResolver", "<init>"));
            }
            this.myContext = myContext;
        }
        
        @Override
        public Pair<OCSymbol, Boolean> resolve(@NotNull final OCReferenceElement ocReferenceElement, final boolean b) {
            try {
                if (ocReferenceElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCReferenceElementImpl$MyResolver", "resolve"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCReferenceElementImpl ocReferenceElementImpl = (OCReferenceElementImpl)ocReferenceElement;
            final OCSymbol resolveToSymbol = ocReferenceElementImpl.resolveToSymbol(ocReferenceElementImpl.a(this.myContext), this.myContext, false, false, false);
            try {
                if (resolveToSymbol != null) {
                    return (Pair<OCSymbol, Boolean>)new Pair((Object)resolveToSymbol, (Object)true);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return (Pair<OCSymbol, Boolean>)new Pair((Object)ocReferenceElementImpl.resolveToSymbol(null, this.myContext, false, false, false), (Object)false);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
