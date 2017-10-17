// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.intellij.psi.PsiReference;
import com.intellij.psi.ReferenceRange;
import com.intellij.openapi.util.TextRange;
import java.util.Iterator;
import com.intellij.psi.PsiElementResolveResult;
import java.util.ArrayList;
import com.intellij.psi.ResolveResult;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.util.IncorrectOperationException;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;
import com.intellij.psi.MultiRangeReference;
import com.intellij.psi.impl.source.resolve.reference.impl.PsiPolyVariantCachingReference;

private class SelectorReference extends PsiPolyVariantCachingReference implements MultiRangeReference, OCPolyVariantReference
{
    static final /* synthetic */ boolean $assertionsDisabled;
    final /* synthetic */ OCSendMessageExpressionImpl this$0;
    
    @NotNull
    public List<OCMethodSymbol> resolveToSymbols() {
        final ProbableResponders probableResponders = OCSendMessageExpressionImpl.this.getProbableResponders();
        List<OCMethodSymbol> allResponders = null;
        Label_0130: {
            List<OCMethodSymbol> list2 = null;
            Label_0095: {
                Label_0068: {
                    List<OCMethodSymbol> list = null;
                    Label_0033: {
                        try {
                            if (probableResponders.getKnownResponder() == null) {
                                break Label_0068;
                            }
                            final ProbableResponders probableResponders2 = probableResponders;
                            final OCMethodSymbol ocMethodSymbol = probableResponders2.getKnownResponder();
                            list = Collections.singletonList(ocMethodSymbol);
                            if (list == null) {
                                break Label_0033;
                            }
                            return list;
                        }
                        catch (IncorrectOperationException ex) {
                            throw a(ex);
                        }
                        try {
                            final ProbableResponders probableResponders2 = probableResponders;
                            final OCMethodSymbol ocMethodSymbol = probableResponders2.getKnownResponder();
                            list = Collections.singletonList(ocMethodSymbol);
                            if (list == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "resolveToSymbols"));
                            }
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                    }
                    return list;
                    try {
                        if (probableResponders.getFilteredByStaticnessResponders().isEmpty()) {
                            break Label_0130;
                        }
                        final ProbableResponders probableResponders3 = probableResponders;
                        list2 = probableResponders3.getFilteredByStaticnessResponders();
                        if (list2 == null) {
                            break Label_0095;
                        }
                        return list2;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final ProbableResponders probableResponders3 = probableResponders;
                    list2 = probableResponders3.getFilteredByStaticnessResponders();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "resolveToSymbols"));
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            return list2;
            try {
                allResponders = probableResponders.getAllResponders();
                if (allResponders == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "resolveToSymbols"));
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
        }
        return allResponders;
    }
    
    @Override
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
        //     9: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    12: athrow         
        //    13: aload_1        
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    22: astore_2       
        //    23: aload_0        
        //    24: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.resolveToSymbols:()Ljava/util/List;
        //    27: astore_3       
        //    28: aload_2        
        //    29: ifnonnull       54
        //    32: aload_3        
        //    33: invokeinterface java/util/List.isEmpty:()Z
        //    38: ifeq            54
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    47: athrow         
        //    48: iconst_1       
        //    49: ireturn        
        //    50: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    53: athrow         
        //    54: aload_2        
        //    55: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    58: ifeq            189
        //    61: new             Lcom/intellij/util/CommonProcessors$CollectProcessor;
        //    64: dup            
        //    65: invokespecial   com/intellij/util/CommonProcessors$CollectProcessor.<init>:()V
        //    68: astore          4
        //    70: aload_2        
        //    71: aload           4
        //    73: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //    78: pop            
        //    79: aload_3        
        //    80: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    85: astore          5
        //    87: aload           5
        //    89: invokeinterface java/util/Iterator.hasNext:()Z
        //    94: ifeq            186
        //    97: aload           5
        //    99: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   104: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   107: astore          6
        //   109: aload           6
        //   111: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   116: astore          7
        //   118: aload           4
        //   120: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //   123: aload           6
        //   125: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   130: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
        //   135: ifne            177
        //   138: aload           7
        //   140: ifnull          183
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   149: athrow         
        //   150: aload           4
        //   152: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //   155: aload           7
        //   157: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   162: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
        //   167: ifeq            183
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   176: athrow         
        //   177: iconst_1       
        //   178: ireturn        
        //   179: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   182: athrow         
        //   183: goto            87
        //   186: goto            248
        //   189: aload_2        
        //   190: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   193: ifeq            248
        //   196: aload_3        
        //   197: aload_2        
        //   198: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   203: ifne            238
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   212: athrow         
        //   213: aload_3        
        //   214: aload_2        
        //   215: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   218: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   223: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   228: ifeq            246
        //   231: goto            238
        //   234: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   237: athrow         
        //   238: iconst_1       
        //   239: goto            247
        //   242: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   245: athrow         
        //   246: iconst_0       
        //   247: ireturn        
        //   248: iconst_0       
        //   249: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      9      9      13     Lcom/intellij/util/IncorrectOperationException;
        //  28     41     44     48     Lcom/intellij/util/IncorrectOperationException;
        //  32     50     50     54     Lcom/intellij/util/IncorrectOperationException;
        //  118    143    146    150    Lcom/intellij/util/IncorrectOperationException;
        //  138    170    173    177    Lcom/intellij/util/IncorrectOperationException;
        //  150    179    179    183    Lcom/intellij/util/IncorrectOperationException;
        //  189    206    209    213    Lcom/intellij/util/IncorrectOperationException;
        //  196    231    234    238    Lcom/intellij/util/IncorrectOperationException;
        //  213    242    242    246    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
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
    @Override
    protected ResolveResult[] resolveInner(final boolean b, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "containingFile", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "resolveInner"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final List<OCMethodSymbol> resolveToSymbols = this.resolveToSymbols();
        final ArrayList<PsiElementResolveResult> list = new ArrayList<PsiElementResolveResult>();
        final Iterator<OCMethodSymbol> iterator = resolveToSymbols.iterator();
        while (iterator.hasNext()) {
            final PsiElement locateDefinition = iterator.next().locateDefinition();
            try {
                if (locateDefinition == null) {
                    continue;
                }
                list.add(new PsiElementResolveResult(locateDefinition, true));
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        ResolveResult[] array;
        try {
            array = list.toArray(new ResolveResult[list.size()]);
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "resolveInner"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return array;
    }
    
    public PsiElement getElement() {
        return (PsiElement)OCSendMessageExpressionImpl.this;
    }
    
    public TextRange getRangeInElement() {
        return ReferenceRange.getRange((PsiReference)this);
    }
    
    @NotNull
    public List<TextRange> getRanges() {
        final ArrayList<TextRange> list = new ArrayList<TextRange>();
        final int n = -this.getElement().getTextRange().getStartOffset();
        final Iterator<OCMessageArgument> iterator = OCSendMessageExpressionImpl.this.getArguments().iterator();
        while (iterator.hasNext()) {
            final PsiElement selectorIdentifier = iterator.next().getArgumentSelector().getSelectorIdentifier();
            if (selectorIdentifier != null) {
                final TextRange textRange = selectorIdentifier.getTextRange();
                try {
                    if (textRange.isEmpty()) {
                        continue;
                    }
                    list.add(textRange.shiftRight(n));
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
            }
        }
        ArrayList<TextRange> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "getRanges"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return list2;
    }
    
    @NotNull
    public String getCanonicalText() {
        String messageSelector;
        try {
            messageSelector = OCSendMessageExpressionImpl.this.getMessageSelector();
            if (messageSelector == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "getCanonicalText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return messageSelector;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        final PsiElement selectorIdentifier = OCSendMessageExpressionImpl.this.getArguments().get(0).getArgumentSelector().getSelectorIdentifier();
        try {
            if (selectorIdentifier != null) {
                return selectorIdentifier.replace(OCElementFactory.createIdentifier(s, (PsiElement)OCSendMessageExpressionImpl.this));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (PsiElement)OCSendMessageExpressionImpl.this;
    }
    
    public PsiElement bindToSymbol(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "bindToSymbol"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        String name = ocSymbol.getName();
        Label_0143: {
            Label_0127: {
                Label_0107: {
                    String s2 = null;
                    Label_0103: {
                        Label_0078: {
                            try {
                                if (!(ocSymbol instanceof OCPropertySymbol)) {
                                    break Label_0107;
                                }
                                final SelectorReference selectorReference = this;
                                final OCSendMessageExpressionImpl ocSendMessageExpressionImpl = selectorReference.this$0;
                                final String s = ocSendMessageExpressionImpl.getMessageSelector();
                                final boolean b = OCNameSuggester.isObjCGetter(s);
                                if (b) {
                                    break Label_0078;
                                }
                                break Label_0078;
                            }
                            catch (IncorrectOperationException ex2) {
                                throw a(ex2);
                            }
                            try {
                                final SelectorReference selectorReference = this;
                                final OCSendMessageExpressionImpl ocSendMessageExpressionImpl = selectorReference.this$0;
                                final String s = ocSendMessageExpressionImpl.getMessageSelector();
                                final boolean b = OCNameSuggester.isObjCGetter(s);
                                if (b) {
                                    s2 = ((OCPropertySymbol)ocSymbol).getGetterName();
                                    break Label_0103;
                                }
                            }
                            catch (IncorrectOperationException ex3) {
                                throw a(ex3);
                            }
                        }
                        s2 = ((OCPropertySymbol)ocSymbol).getSetterName();
                    }
                    name = s2;
                    break Label_0143;
                    try {
                        if (SelectorReference.$assertionsDisabled) {
                            break Label_0143;
                        }
                        final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocSymbol;
                        final boolean b2 = ocPropertySymbol instanceof OCMethodSymbol;
                        if (!b2) {
                            break Label_0127;
                        }
                        break Label_0143;
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocSymbol;
                    final boolean b2 = ocPropertySymbol instanceof OCMethodSymbol;
                    if (!b2) {
                        throw new AssertionError(((OCPropertySymbol)ocSymbol).getClass());
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            try {
                if (!OCSendMessageExpressionImpl.this.getMessageSelector().equals(name)) {
                    this.handleElementRename(name);
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        return this.getElement();
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "bindToElement"));
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
    
    @NotNull
    public Object[] getVariants() {
        Object[] array;
        try {
            array = new Object[0];
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCSendMessageExpressionImpl$SelectorReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return array;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSendMessageExpressionImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
