// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.refactoring.util.OCBindUtil;
import com.jetbrains.cidr.lang.resolve.references.OCPolyVariantReferenceImpl;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityAfterResolvingVisitor;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.psi.OCTemplateArgumentList;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.intellij.util.IncorrectOperationException;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;

public class OCCppNamespaceQualifierImpl extends OCElementWithReferenceBase<OCPolyVariantReference<OCSymbol>> implements OCCppNamespaceQualifier
{
    public OCCppNamespaceQualifierImpl(@NotNull final ASTNode astNode) {
        if (astNode == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl", "<init>"));
        }
        super(astNode);
    }
    
    @Nullable
    @Override
    public OCCppNamespaceQualifier getNamespaceQualifier() {
        return this.findChildByType(OCElementTypes.CPP_NAMESPACE_QUALIFIER);
    }
    
    @Nullable
    public PsiElement getNameIdentifier() {
        return this.findNameStartTokenInCall();
    }
    
    public TextRange getRangeInElement() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier != null) {
                return TextRange.from(nameIdentifier.getStartOffsetInParent(), nameIdentifier.getTextLength());
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return TextRange.EMPTY_RANGE;
    }
    
    @Nullable
    public OCTypeArgumentList getTemplateArgumentList() {
        return this.findChildByType(OCElementTypes.TEMPLATE_ARGUMENT_LIST);
    }
    
    public String getName() {
        final PsiElement nameIdentifier = this.getNameIdentifier();
        try {
            if (nameIdentifier != null) {
                return nameIdentifier.getText();
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        OCElementUtil.replaceWithIdentifier(this.getNameIdentifier(), s, (PsiElement)this);
        return (PsiElement)this;
    }
    
    @Override
    public void accept(@NotNull final OCVisitor ocVisitor) {
        try {
            if (ocVisitor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "visitor", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl", "accept"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        ocVisitor.visitNamespaceQualifier(this);
    }
    
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols() {
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = this.resolveToSymbols(new OCResolveContext((PsiElement)this));
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return resolveToSymbols;
    }
    
    @NotNull
    public Collection<OCSymbol> resolveTemplateDeclarations() {
        Collection<OCSymbol> resolveTemplateDeclarations;
        try {
            resolveTemplateDeclarations = OCResolveUtil.resolveTemplateDeclarations(this);
            if (resolveTemplateDeclarations == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl", "resolveTemplateDeclarations"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return resolveTemplateDeclarations;
    }
    
    @NotNull
    @Override
    public List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl", "resolveToSymbols"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ArrayList list = new ArrayList<OCSymbol>();
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor((com.intellij.util.Processor<? super Object>)(p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
            //     4: ifeq            56
            //     7: aload_1        
            //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
            //    13: ifeq            50
            //    16: goto            23
            //    19: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    22: athrow         
            //    23: aload_0        
            //    24: invokevirtual   java/util/ArrayList.size:()I
            //    27: ifne            56
            //    30: goto            37
            //    33: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    36: athrow         
            //    37: aload_0        
            //    38: aload_1        
            //    39: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
            //    42: pop            
            //    43: goto            56
            //    46: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    49: athrow         
            //    50: aload_0        
            //    51: aload_1        
            //    52: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
            //    55: pop            
            //    56: aload_1        
            //    57: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceAliasSymbol;
            //    60: ifeq            90
            //    63: aload_0        
            //    64: invokevirtual   java/util/ArrayList.size:()I
            //    67: ifne            138
            //    70: goto            77
            //    73: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    76: athrow         
            //    77: aload_0        
            //    78: aload_1        
            //    79: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
            //    82: pop            
            //    83: goto            138
            //    86: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    89: athrow         
            //    90: aload_1        
            //    91: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
            //    94: ifne            125
            //    97: aload_1        
            //    98: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
            //   101: ifne            125
            //   104: goto            111
            //   107: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //   110: athrow         
            //   111: aload_1        
            //   112: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCAliasUsingSymbol;
            //   115: ifeq            138
            //   118: goto            125
            //   121: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //   124: athrow         
            //   125: aload_0        
            //   126: aload_1        
            //   127: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
            //   130: pop            
            //   131: goto            138
            //   134: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //   137: athrow         
            //   138: iconst_1       
            //   139: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                           
            //  -----  -----  -----  -----  -----------------------------------------------
            //  0      16     19     23     Lcom/intellij/util/IncorrectOperationException;
            //  7      30     33     37     Lcom/intellij/util/IncorrectOperationException;
            //  23     46     46     50     Lcom/intellij/util/IncorrectOperationException;
            //  56     70     73     77     Lcom/intellij/util/IncorrectOperationException;
            //  63     86     86     90     Lcom/intellij/util/IncorrectOperationException;
            //  90     104    107    111    Lcom/intellij/util/IncorrectOperationException;
            //  97     118    121    125    Lcom/intellij/util/IncorrectOperationException;
            //  111    131    134    138    Lcom/intellij/util/IncorrectOperationException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
        }), (com.intellij.openapi.util.Condition<Object>[])new Condition[] { OCSymbol.NON_PREDIFINITION_CONDITION, Conditions.alwaysTrue() });
        for (PsiElement psiElement = this.getParent(); psiElement instanceof OCCppNamespaceQualifier; psiElement = psiElement.getParent()) {}
        final OCSymbolReference.LocalReference localReference = OCSymbolReference.getLocalReference(this, OCSymbolReference.SymbolKindFilter.ONLY_NAMESPACE_LIKE);
        List<OCSymbol> list2 = localReference.resolveToSymbols(false, true, true, ocResolveContext);
        if (list2.isEmpty()) {
            list2 = localReference.resolveToSymbols(false, true, false, ocResolveContext);
        }
        ArrayList list3 = null;
        Label_0225: {
            try {
                ContainerUtil.process((List)list2, (Processor)orderedProcessor);
                orderedProcessor.finish();
                if (!list.isEmpty() || !this.getContainingOCFile().isInLibraries()) {
                    break Label_0225;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            final OCResolveContext ocResolveContext2 = new OCResolveContext((PsiElement)this.getContainingOCFile());
            ocResolveContext2.setProcessNonImported(true);
            ContainerUtil.process((List)ocResolveContext2.resolveToSymbols(localReference, false, true), (Processor)orderedProcessor);
            orderedProcessor.finish();
            try {
                list3 = list;
                if (list3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl", "resolveToSymbols"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return (List<OCSymbol>)list3;
    }
    
    @Nullable
    @Override
    public OCSymbol getPredeclarationInParent(final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, final boolean b) {
        final PsiFile containingFile = this.getContainingFile();
        final OCType resolvedType = ocSymbolWithQualifiedName.getResolvedType();
        final OCTypeEqualityAfterResolvingVisitor ocTypeEqualityAfterResolvingVisitor = new OCTypeEqualityAfterResolvingVisitor(resolvedType, true, false, true, false, true, containingFile);
        final OCTypeEqualityAfterResolvingVisitor ocTypeEqualityAfterResolvingVisitor2 = new OCTypeEqualityAfterResolvingVisitor(resolvedType, true, true, true, false, true, containingFile);
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return !OCSymbolWithQualifiedName.isFriend(ocSymbol);
            }
        };
        final OCCommonProcessors.OrderedProcessor orderedProcessor = new OCCommonProcessors.OrderedProcessor<OCSymbol>((com.intellij.util.Processor<? super OCSymbol>)findFirstProcessor, (com.intellij.openapi.util.Condition<OCSymbol>[])new Condition[] { ocSymbol -> {
                try {
                    if (!b || !(ocSymbol instanceof OCFunctionSymbol)) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return ocTypeEqualityAfterResolvingVisitor.equal(ocSymbol.getType().resolve((PsiFile)ocSymbol.getContainingOCFile()));
            }, ocSymbol -> {
                try {
                    if (!b || !(ocSymbol instanceof OCFunctionSymbol)) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return ocTypeEqualityAfterResolvingVisitor2.isFunctionSignatureEqual(ocSymbol.getType().resolve((PsiFile)ocSymbol.getContainingOCFile()));
            } });
        for (final OCSymbol ocSymbol : this.resolveToSymbols()) {
            if (ocSymbol.getKind() == OCSymbolKind.TYPEDEF) {
                final OCType resolvedType2 = ocSymbol.getResolvedType();
                if (resolvedType2 instanceof OCStructType) {
                    final Iterator<OCStructSymbol> iterator2 = ((OCStructType)resolvedType2).getStructs().iterator();
                    while (iterator2.hasNext()) {
                        iterator2.next().processMembers(ocSymbolWithQualifiedName.getName(), (Processor<OCSymbol>)orderedProcessor);
                    }
                }
            }
            try {
                if (!(ocSymbol instanceof OCNamespaceSymbol)) {
                    continue;
                }
                ((OCNamespaceSymbol)ocSymbol).processMembers(ocSymbolWithQualifiedName.getName(), (Processor<OCSymbol>)orderedProcessor);
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        orderedProcessor.finish();
        return (OCSymbol)findFirstProcessor.getFoundValue();
    }
    
    @Nullable
    @Override
    protected OCPolyVariantReference<OCSymbol> createReference() {
        return new OCPolyVariantReferenceImpl<OCSymbol>() {
            @NotNull
            @Override
            public List<OCSymbol> resolveToSymbols() {
                List<OCSymbol> resolveToSymbols;
                try {
                    resolveToSymbols = OCCppNamespaceQualifierImpl.this.resolveToSymbols();
                    if (resolveToSymbols == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "resolveToSymbols"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return resolveToSymbols;
            }
            
            @NotNull
            @Override
            public List<OCSymbol> resolveToSymbols(@NotNull final OCResolveContext ocResolveContext) {
                try {
                    if (ocResolveContext == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "resolveToSymbols"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                List<OCSymbol> resolveToSymbols;
                try {
                    resolveToSymbols = OCCppNamespaceQualifierImpl.this.resolveToSymbols(ocResolveContext);
                    if (resolveToSymbols == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "resolveToSymbols"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                return resolveToSymbols;
            }
            
            public PsiElement getElement() {
                return (PsiElement)OCCppNamespaceQualifierImpl.this;
            }
            
            public TextRange getRangeInElement() {
                return OCCppNamespaceQualifierImpl.this.getRangeInElement();
            }
            
            @NotNull
            public String getCanonicalText() {
                final String name = OCCppNamespaceQualifierImpl.this.getName();
                String s = null;
                Label_0022: {
                    try {
                        if (name != null) {
                            final String s2;
                            s = (s2 = name);
                            break Label_0022;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    String s2;
                    s = (s2 = "");
                    try {
                        if (s2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "getCanonicalText"));
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return s;
            }
            
            public PsiElement handleElementRename(final String name) throws IncorrectOperationException {
                return OCCppNamespaceQualifierImpl.this.setName(name);
            }
            
            @Override
            public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
                try {
                    if (ocSymbol == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "bindToSymbol"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                final List resolveToSymbols = ((OCElementWithReferenceBase<OCPolyVariantReference>)OCCppNamespaceQualifierImpl.this).getReference().resolveToSymbols();
                try {
                    if (resolveToSymbols.contains(ocSymbol)) {
                        return (PsiElement)OCCppNamespaceQualifierImpl.this;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                        return (PsiElement)OCCppNamespaceQualifierImpl.this;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                final OCQualifiedName resolvedQualifiedName = ((OCSymbolWithQualifiedName)ocSymbol).getResolvedQualifiedName();
                try {
                    if (resolvedQualifiedName != null) {
                        OCBindUtil.setShortestPossibleName(resolvedQualifiedName, OCCppNamespaceQualifierImpl.this, (OCSymbolWithQualifiedName)ocSymbol);
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                return (PsiElement)OCCppNamespaceQualifierImpl.this;
            }
            
            public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
                try {
                    if (psiElement == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCCppNamespaceQualifierImpl$2", "bindToElement"));
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
            
            public boolean isSoft() {
                return false;
            }
            
            private static IncorrectOperationException a(final IncorrectOperationException ex) {
                return ex;
            }
        };
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
