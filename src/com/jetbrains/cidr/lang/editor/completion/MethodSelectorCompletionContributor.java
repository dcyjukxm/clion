// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.openapi.editor.Document;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.psi.tree.TokenSet;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiErrorElement;
import com.jetbrains.cidr.lang.psi.OCArgumentSelector;
import com.intellij.patterns.PlatformPatterns;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.codeInsight.completion.CompletionInitializationContext;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCCastExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.openapi.util.Conditions;
import com.intellij.util.FilteringProcessor;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.util.UserDataHolder;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import java.util.HashSet;
import javax.swing.Icon;
import java.util.Set;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.types.OCObjectTypeContext;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.codeInsight.completion.CompletionType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.intellij.codeInsight.completion.CompletionResultSet;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.openapi.util.Key;
import com.intellij.psi.PsiElement;
import com.intellij.patterns.ElementPattern;

public class MethodSelectorCompletionContributor extends OCCompletionContributorBase
{
    public static final String CIDR_RULE_ZZZ = "CIDR_RULE_ZZZ";
    public static final ElementPattern<PsiElement> PLACE;
    public static final Key<PsiElement> POSSIBLE_RECEIVER_KEY;
    public static final PsiElementPattern.Capture<PsiElement> POSSIBLE_PLACE;
    private static final InsertHandler<LookupElement> REMOVE_EXTRA_COLON_HANDLER;
    public static final Key<Context> CONTEXT;
    
    public MethodSelectorCompletionContributor() {
        final OCCompletionProvider ocCompletionProvider = new OCCompletionProvider() {
            @Override
            protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
                try {
                    if (ocCompletionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$2", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final SendMessagePlace access$000 = a(ocCompletionParameters.getPosition());
                try {
                    if (access$000 == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final StringBuilder sb = new StringBuilder();
                final OCSendMessageExpression expression = access$000.expression;
                for (final OCMessageArgument ocMessageArgument : expression.getArguments()) {
                    try {
                        if (ocMessageArgument == access$000.argument) {
                            break;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    sb.append(ocMessageArgument.getArgumentSelector().getSelectorName());
                }
                MethodSelectorCompletionContributor.addCompletionsForReceiver(expression.getReceiverExpression(), sb.toString(), ocCompletionParameters, set, a(expression, ocCompletionParameters.getCompletionType()));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        this.register(CompletionType.BASIC, MethodSelectorCompletionContributor.PLACE, ocCompletionProvider);
        this.register(CompletionType.SMART, MethodSelectorCompletionContributor.PLACE, ocCompletionProvider);
        final OCCompletionProvider ocCompletionProvider2 = new OCCompletionProvider() {
            @Override
            protected void addCompletions(final String s, @NotNull final OCCompletionParameters ocCompletionParameters, final ProcessingContext processingContext, final CompletionResultSet set) {
                try {
                    if (ocCompletionParameters == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$3", "addCompletions"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                final PsiElement position = ocCompletionParameters.getPosition();
                final OCExpression methodReceiverExpression = MethodSelectorCompletionContributor.getMethodReceiverExpression(position);
                try {
                    if (methodReceiverExpression == null) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                Label_0119: {
                    if (MethodSelectorCompletionContributor.PLACE.accepts((Object)position)) {
                        final SendMessagePlace access$000 = a(position);
                        try {
                            if (access$000 == null) {
                                break Label_0119;
                            }
                            final SendMessagePlace sendMessagePlace = access$000;
                            final OCSendMessageExpression ocSendMessageExpression = sendMessagePlace.expression;
                            final OCExpression ocExpression = methodReceiverExpression;
                            final PsiElement psiElement = ocExpression.getParent();
                            if (ocSendMessageExpression == psiElement) {
                                return;
                            }
                            break Label_0119;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            final SendMessagePlace sendMessagePlace = access$000;
                            final OCSendMessageExpression ocSendMessageExpression = sendMessagePlace.expression;
                            final OCExpression ocExpression = methodReceiverExpression;
                            final PsiElement psiElement = ocExpression.getParent();
                            if (ocSendMessageExpression == psiElement) {
                                return;
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                }
                a(methodReceiverExpression, ocCompletionParameters, set);
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
        this.register(CompletionType.BASIC, (ElementPattern<? extends PsiElement>)MethodSelectorCompletionContributor.POSSIBLE_PLACE, ocCompletionProvider2);
        this.register(CompletionType.SMART, (ElementPattern<? extends PsiElement>)MethodSelectorCompletionContributor.POSSIBLE_PLACE, ocCompletionProvider2);
    }
    
    @Nullable
    private static SendMessagePlace a(final PsiElement psiElement) {
        final OCMessageArgument ocMessageArgument = (OCMessageArgument)PsiTreeUtil.getParentOfType(psiElement, (Class)OCMessageArgument.class);
        try {
            if (ocMessageArgument != null) {
                return new SendMessagePlace(ocMessageArgument);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public static void addCompletionsForReceiver(final OCExpression ocExpression, final String s, final OCCompletionParameters ocCompletionParameters, final CompletionResultSet set, final Condition<OCMethodSymbol> condition) {
        try {
            if (ocExpression == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCObjectTypeContext typeContext = ocExpression.getTypeContext(true, true);
        final OCLanguageKind kind = ocExpression.getContainingOCFile().getKind();
        Label_0153: {
            Label_0055: {
                try {
                    if (typeContext == null) {
                        break Label_0055;
                    }
                    final OCObjectTypeContext ocObjectTypeContext = typeContext;
                    final OCType ocType = ocObjectTypeContext.getOriginalType();
                    final boolean b = ocType.isUnknown();
                    if (b) {
                        break Label_0055;
                    }
                    break Label_0153;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCObjectTypeContext ocObjectTypeContext = typeContext;
                    final OCType ocType = ocObjectTypeContext.getOriginalType();
                    final boolean b = ocType.isUnknown();
                    if (!b) {
                        break Label_0153;
                    }
                    if (!(ocExpression instanceof OCReferenceExpression)) {
                        break Label_0153;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final OCReferenceElement referenceElement = ((OCReferenceExpression)ocExpression).getReferenceElement();
            OCSymbol resolveToSymbol = null;
            Label_0105: {
                try {
                    if (referenceElement != null) {
                        resolveToSymbol = referenceElement.resolveToSymbol(OCSymbolGroupContext.typeContext(kind));
                        break Label_0105;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                resolveToSymbol = null;
            }
            final OCSymbol ocSymbol = resolveToSymbol;
            if (ocSymbol != null) {
                final OCType effectiveResolvedType = ocSymbol.getEffectiveResolvedType();
                if (effectiveResolvedType instanceof OCObjectType) {
                    typeContext = new OCObjectTypeContext(OCObjectTypeContext.StaticMode.STATIC, (OCObjectType)effectiveResolvedType, ocSymbol.getType());
                }
            }
            try {
                if (typeContext == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        addCompletionForReceiverContext(ocCompletionParameters, set, new Context(s, (PsiElement)ocExpression, typeContext, condition));
    }
    
    public static void addCompletionForReceiverContext(@NotNull final OCCompletionParameters p0, @NotNull final CompletionResultSet p1, @NotNull final Context p2) {
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
        //    18: ldc             "parameters"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addCompletionForReceiverContext"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "result"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "addCompletionForReceiverContext"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "context"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "addCompletionForReceiverContext"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: aload_2        
        //   133: invokevirtual   com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context.getExactSelectorPrefix:()Ljava/lang/String;
        //   136: invokevirtual   java/lang/String.length:()I
        //   139: ifle            188
        //   142: aload_1        
        //   143: invokevirtual   com/intellij/codeInsight/completion/CompletionResultSet.getPrefixMatcher:()Lcom/intellij/codeInsight/completion/PrefixMatcher;
        //   146: invokevirtual   com/intellij/codeInsight/completion/PrefixMatcher.getPrefix:()Ljava/lang/String;
        //   149: invokevirtual   java/lang/String.length:()I
        //   152: ifne            188
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: aload_0        
        //   163: invokevirtual   com/jetbrains/cidr/lang/editor/completion/OCCompletionParameters.getPosition:()Lcom/intellij/psi/PsiElement;
        //   166: iconst_1       
        //   167: invokestatic    com/intellij/psi/util/PsiTreeUtil.prevLeaf:(Lcom/intellij/psi/PsiElement;Z)Lcom/intellij/psi/PsiElement;
        //   170: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   173: ifne            188
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: return         
        //   184: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   187: athrow         
        //   188: aload_1        
        //   189: aload_0        
        //   190: aload_2        
        //   191: invokestatic    com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor.a:(Lcom/intellij/codeInsight/completion/CompletionResultSet;Lcom/jetbrains/cidr/lang/editor/completion/OCCompletionParameters;Lcom/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context;)I
        //   194: pop            
        //   195: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    155    158    162    Ljava/lang/IllegalArgumentException;
        //  142    176    179    183    Ljava/lang/IllegalArgumentException;
        //  162    184    184    188    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0162:
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
    
    private static int a(@NotNull final CompletionResultSet set, @NotNull final OCCompletionParameters ocCompletionParameters, @NotNull final Context context) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "doAddCompletions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "doAddCompletions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (context == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "doAddCompletions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final int[] array = { 0 };
        final OCObjectTypeContext receiverContext = context.getReceiverContext();
        final Condition<OCMethodSymbol> condition = context.getCondition();
        final Processor<OCMethodSymbol> processor = getProcessor(ocCompletionParameters, set, array, context);
        Label_0236: {
            Label_0192: {
                try {
                    if (!receiverContext.getType().getName().equals("NSObject")) {
                        break Label_0236;
                    }
                    final Condition<OCMethodSymbol> condition2 = condition;
                    final boolean b = condition2 instanceof OCSmartCompletionContributor.TypeMatchingCondition;
                    if (b) {
                        break Label_0192;
                    }
                    break Label_0192;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                try {
                    final Condition<OCMethodSymbol> condition2 = condition;
                    final boolean b = condition2 instanceof OCSmartCompletionContributor.TypeMatchingCondition;
                    if (b) {
                        ((OCSmartCompletionContributor.TypeMatchingCondition)condition).avoidNSObjectMethods(false);
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ocCompletionParameters.getOriginalFile().getProject(), (Processor<OCSymbol>)new OCCommonProcessors.TypeFilteredProcessor((com.intellij.util.Processor<Object>)processor, OCMethodSymbol.class), null);
            return array[0];
            try {
                if (condition instanceof OCSmartCompletionContributor.TypeMatchingCondition) {
                    ((OCSmartCompletionContributor.TypeMatchingCondition)condition).setReceiverContext(receiverContext);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        receiverContext.getType().processMembers(OCMethodSymbol.class, processor);
        return array[0];
    }
    
    public static Processor<OCMethodSymbol> getProcessor(@NotNull final OCCompletionParameters ocCompletionParameters, @NotNull final CompletionResultSet set, final int[] array, @NotNull final Context context) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "getProcessor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "getProcessor"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (context == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "getProcessor"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCObjectTypeContext receiverContext = context.getReceiverContext();
        final String exactSelectorPrefix = context.getExactSelectorPrefix();
        boolean b = false;
        Label_0164: {
            try {
                if (receiverContext.getStaticMode() == OCObjectTypeContext.StaticMode.STATIC) {
                    b = true;
                    break Label_0164;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            b = false;
        }
        final Icon methodIcon = OCIcons.getMethodIcon(b, false, false);
        try {
            if (exactSelectorPrefix.length() == 0) {
                final boolean b2 = true;
                return (Processor<OCMethodSymbol>)new FilteringProcessor((Condition)context.getCondition(), (Processor)new Processor<OCMethodSymbol>() {
                    private Set<String> names;
                    final /* synthetic */ OCObjectTypeContext val$receiverContext;
                    final /* synthetic */ OCCompletionParameters val$parameters;
                    final /* synthetic */ boolean val$completeFullSelector;
                    final /* synthetic */ Context val$context;
                    final /* synthetic */ CompletionResultSet val$result;
                    final /* synthetic */ int[] val$addedCnt;
                    final /* synthetic */ String val$exactSelectorPrefix;
                    final /* synthetic */ Icon val$icon;
                    
                    {
                        this.names = new HashSet<String>();
                    }
                    
                    public boolean process(final OCMethodSymbol ocMethodSymbol) {
                        final String name = ocMethodSymbol.getName();
                        if (!this.val$receiverContext.fitsStaticness(ocMethodSymbol)) {
                            return true;
                        }
                        if (ocMethodSymbol.isUnavailable() || ocMethodSymbol.isForbiddenByARC((PsiElement)this.val$parameters.getOriginalFile())) {
                            return true;
                        }
                        final PsiFile originalFile = this.val$parameters.getOriginalFile();
                        if (!(originalFile instanceof OCFile) || !OCFileSymbols.isSymbolImported((OCFile)originalFile, ocMethodSymbol)) {
                            final OCFile containingOCFile = ocMethodSymbol.getContainingOCFile();
                            if (containingOCFile != null && !containingOCFile.isHeader()) {
                                return true;
                            }
                        }
                        if (!this.names.add(name)) {
                            return true;
                        }
                        if (this.val$completeFullSelector) {
                            LookupElement lookupElement2;
                            final LookupElement lookupElement = lookupElement2 = SymbolLookupBuilderUtil.lookup(ocMethodSymbol, null, this.val$receiverContext.getType(), this.val$parameters.getPosition(), Comparing.equal(((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getName(), this.val$receiverContext.getType().getClassName()), null);
                            MethodSelectorCompletionContributor.CONTEXT.set((UserDataHolder)lookupElement2, (Object)this.val$context);
                            while (lookupElement2 instanceof LookupElementDecorator) {
                                lookupElement2 = ((LookupElementDecorator)lookupElement2).getDelegate();
                                MethodSelectorCompletionContributor.CONTEXT.set((UserDataHolder)lookupElement2, (Object)this.val$context);
                            }
                            if (this.val$result.getPrefixMatcher().prefixMatches(lookupElement)) {
                                this.val$result.addElement(lookupElement);
                                final int[] val$addedCnt = this.val$addedCnt;
                                final int n = 0;
                                ++val$addedCnt[n];
                            }
                        }
                        else if (name.startsWith(this.val$exactSelectorPrefix)) {
                            final String substring = name.substring(this.val$exactSelectorPrefix.length());
                            if (!substring.isEmpty()) {
                                final int index = substring.indexOf(58);
                                this.val$result.addElement((LookupElement)LookupElementBuilder.create((index >= 0) ? substring.substring(0, index + 1) : substring).withIcon(this.val$icon).withInsertHandler(MethodSelectorCompletionContributor.REMOVE_EXTRA_COLON_HANDLER));
                                final int[] val$addedCnt2 = this.val$addedCnt;
                                final int n2 = 0;
                                ++val$addedCnt2[n2];
                            }
                        }
                        return true;
                    }
                });
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final boolean b2 = false;
        return (Processor<OCMethodSymbol>)new FilteringProcessor((Condition)context.getCondition(), (Processor)new Processor<OCMethodSymbol>(context, methodIcon) {
            private Set<String> names = new HashSet<String>();
            final /* synthetic */ OCObjectTypeContext val$receiverContext;
            final /* synthetic */ OCCompletionParameters val$parameters;
            final /* synthetic */ boolean val$completeFullSelector;
            final /* synthetic */ Context val$context;
            final /* synthetic */ CompletionResultSet val$result;
            final /* synthetic */ int[] val$addedCnt;
            final /* synthetic */ String val$exactSelectorPrefix;
            final /* synthetic */ Icon val$icon;
            
            public boolean process(final OCMethodSymbol ocMethodSymbol) {
                final String name = ocMethodSymbol.getName();
                if (!receiverContext.fitsStaticness(ocMethodSymbol)) {
                    return true;
                }
                if (ocMethodSymbol.isUnavailable() || ocMethodSymbol.isForbiddenByARC((PsiElement)ocCompletionParameters.getOriginalFile())) {
                    return true;
                }
                final PsiFile originalFile = ocCompletionParameters.getOriginalFile();
                if (!(originalFile instanceof OCFile) || !OCFileSymbols.isSymbolImported((OCFile)originalFile, ocMethodSymbol)) {
                    final OCFile containingOCFile = ocMethodSymbol.getContainingOCFile();
                    if (containingOCFile != null && !containingOCFile.isHeader()) {
                        return true;
                    }
                }
                if (!this.names.add(name)) {
                    return true;
                }
                if (b2) {
                    LookupElement lookupElement2;
                    final LookupElement lookupElement = lookupElement2 = SymbolLookupBuilderUtil.lookup(ocMethodSymbol, null, receiverContext.getType(), ocCompletionParameters.getPosition(), Comparing.equal(((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent().getName(), receiverContext.getType().getClassName()), null);
                    MethodSelectorCompletionContributor.CONTEXT.set((UserDataHolder)lookupElement2, (Object)context);
                    while (lookupElement2 instanceof LookupElementDecorator) {
                        lookupElement2 = ((LookupElementDecorator)lookupElement2).getDelegate();
                        MethodSelectorCompletionContributor.CONTEXT.set((UserDataHolder)lookupElement2, (Object)context);
                    }
                    if (set.getPrefixMatcher().prefixMatches(lookupElement)) {
                        set.addElement(lookupElement);
                        final int[] val$addedCnt = array;
                        final int n = 0;
                        ++val$addedCnt[n];
                    }
                }
                else if (name.startsWith(exactSelectorPrefix)) {
                    final String substring = name.substring(exactSelectorPrefix.length());
                    if (!substring.isEmpty()) {
                        final int index = substring.indexOf(58);
                        set.addElement((LookupElement)LookupElementBuilder.create((index >= 0) ? substring.substring(0, index + 1) : substring).withIcon(methodIcon).withInsertHandler(MethodSelectorCompletionContributor.REMOVE_EXTRA_COLON_HANDLER));
                        final int[] val$addedCnt2 = array;
                        final int n2 = 0;
                        ++val$addedCnt2[n2];
                    }
                }
                return true;
            }
        });
    }
    
    public static void addMethodCompletionsIfAppropriate(@NotNull final OCCompletionParameters ocCompletionParameters, @NotNull final CompletionResultSet set, @NotNull final PsiElement psiElement) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "addMethodCompletionsIfAppropriate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "addMethodCompletionsIfAppropriate"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "addMethodCompletionsIfAppropriate"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCExpression methodReceiverExpression = getMethodReceiverExpression(psiElement);
        try {
            if (methodReceiverExpression != null) {
                a(methodReceiverExpression, ocCompletionParameters, set);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
    }
    
    private static void a(final OCExpression ocExpression, @NotNull final OCCompletionParameters ocCompletionParameters, @NotNull final CompletionResultSet set) {
        try {
            if (ocCompletionParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "parameters", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "addMethodCompletionsForExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "addMethodCompletionsForExpression"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        addCompletionsForReceiver(ocExpression, "", ocCompletionParameters, set, a(ocExpression, ocCompletionParameters.getCompletionType()));
    }
    
    @NotNull
    private static Condition<OCMethodSymbol> a(@NotNull final OCExpression ocExpression, @NotNull final CompletionType completionType) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "getTypeFilter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (completionType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "completionType", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "getTypeFilter"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Object alwaysTrue = Conditions.alwaysTrue();
        if (completionType == CompletionType.SMART) {
            final OCDeclarator ocDeclarator = (OCDeclarator)PsiTreeUtil.getParentOfType((PsiElement)ocExpression, (Class)OCDeclarator.class);
            OCSymbol symbol = null;
            Label_0127: {
                try {
                    if (ocDeclarator != null) {
                        symbol = ocDeclarator.getSymbol();
                        break Label_0127;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                symbol = null;
            }
            final OCSymbol ocSymbol = symbol;
            final OCFile ocFile = (OCFile)ocExpression.getContainingFile();
            final OCType resolve = OCExpectedTypeUtil.getExpectedType(ocExpression).resolve((PsiFile)ocFile);
            if (resolve != OCUnknownType.INSTANCE) {
                alwaysTrue = new OCSmartCompletionContributor.TypeMatchingCondition(ocFile, (PsiElement)ocExpression, resolve, false, ocSymbol);
            }
        }
        Object o;
        try {
            o = alwaysTrue;
            if (o == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "getTypeFilter"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return (Condition<OCMethodSymbol>)o;
    }
    
    public static OCExpression getMethodReceiverExpression(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "getMethodReceiverExpression"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ProcessingContext processingContext = new ProcessingContext();
        try {
            if (!MethodSelectorCompletionContributor.POSSIBLE_PLACE.accepts((Object)psiElement, processingContext)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiElement psiElement2 = (PsiElement)processingContext.get((Key)MethodSelectorCompletionContributor.POSSIBLE_RECEIVER_KEY);
        final ASTNode node = psiElement2.getNode();
        final OCLanguageKind kind = ((OCFile)psiElement2.getContainingFile()).getKind();
        final IElementType elementType = node.getElementType();
        final OCExpression ocExpression = (OCExpression)PsiTreeUtil.getParentOfType(node.getPsi(), (Class)OCExpression.class);
        Label_0156: {
            Label_0150: {
                try {
                    if (elementType != OCTokenTypes.RPAR) {
                        break Label_0156;
                    }
                    final OCExpression ocExpression2 = ocExpression;
                    final boolean b = ocExpression2 instanceof OCCastExpression;
                    if (b) {
                        break Label_0150;
                    }
                    break Label_0156;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCExpression ocExpression2 = ocExpression;
                    final boolean b = ocExpression2 instanceof OCCastExpression;
                    if (b) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                if (ocExpression == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        Label_0192: {
            try {
                if (!(ocExpression instanceof OCBlockExpression)) {
                    break Label_0192;
                }
                final OCExpression ocExpression3 = ocExpression;
                final PsiElement psiElement3 = psiElement;
                final boolean b2 = true;
                final boolean b3 = PsiTreeUtil.isAncestor((PsiElement)ocExpression3, psiElement3, b2);
                if (b3) {
                    break Label_0192;
                }
                break Label_0192;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final OCExpression ocExpression3 = ocExpression;
                final PsiElement psiElement3 = psiElement;
                final boolean b2 = true;
                final boolean b3 = PsiTreeUtil.isAncestor((PsiElement)ocExpression3, psiElement3, b2);
                if (b3) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        Object o = null;
        Label_0272: {
            if (ocExpression.getResolvedType().isPointerToObjectCompatible()) {
                o = ocExpression;
            }
            else if (ocExpression instanceof OCReferenceExpression) {
                final OCReferenceElement referenceElement = ((OCReferenceExpression)ocExpression).getReferenceElement();
                try {
                    if (referenceElement == null || referenceElement.resolveToSymbol(OCSymbolGroupContext.typeContext(kind)) == null) {
                        break Label_0272;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                o = ocExpression;
            }
            while (true) {
                try {
                    if (o == null || !(((OCExpression)o).getParent() instanceof OCUnaryExpression)) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                o = ((OCExpression)o).getParent();
            }
        }
        return (OCExpression)o;
    }
    
    public void beforeCompletion(@NotNull final CompletionInitializationContext completionInitializationContext) {
        try {
            if (completionInitializationContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor", "beforeCompletion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int startOffset = completionInitializationContext.getStartOffset();
        final String text = completionInitializationContext.getEditor().getDocument().getText();
        if (text.regionMatches(startOffset, "<#", 0, 2)) {
            final int index = text.indexOf("#>", startOffset);
            try {
                if (index > startOffset) {
                    completionInitializationContext.setReplacementOffset(index + 2);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        String string = "CIDR_RULE_ZZZ";
        final PsiElement element = completionInitializationContext.getFile().findElementAt(startOffset - 1);
        Label_0203: {
            try {
                if (element == null || element.getNode().getElementType() != OCTokenTypes.IDENTIFIER) {
                    break Label_0203;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            string = element.getText().substring(startOffset - element.getTextOffset(), element.getText().length()) + "CIDR_RULE_ZZZ";
        }
        final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType(completionInitializationContext.getFile().findElementAt(startOffset), (Class)OCMacroCall.class);
        try {
            if (ocMacroCall != null) {
                completionInitializationContext.setDummyIdentifier(string);
                return;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)PsiTreeUtil.findElementOfClassAtOffsetWithStopSet(completionInitializationContext.getFile(), startOffset, (Class)OCSendMessageExpression.class, false, new Class[] { OCCallable.class });
        Label_0316: {
            try {
                if (ocSendMessageExpression == null || ocSendMessageExpression.getTextRange().getStartOffset() != startOffset) {
                    break Label_0316;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            ocSendMessageExpression = (OCSendMessageExpression)PsiTreeUtil.getParentOfType((PsiElement)ocSendMessageExpression, (Class)OCSendMessageExpression.class, true, new Class[] { OCCallable.class });
            try {
                if (ocSendMessageExpression != null) {
                    completionInitializationContext.setDummyIdentifier(string);
                    return;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        try {
            if (completionInitializationContext.getCompletionType() == CompletionType.SMART) {
                completionInitializationContext.setDummyIdentifier(string + ")");
                return;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw a(ex7);
        }
        completionInitializationContext.setDummyIdentifier(string + ";");
    }
    
    static {
        PLACE = StandardPatterns.or(new ElementPattern[] { PlatformPatterns.psiElement().withParent((Class)OCArgumentSelector.class), ((PsiElementPattern.Capture)PlatformPatterns.psiElement((IElementType)OCTokenTypes.IDENTIFIER).withParent((Class)PsiErrorElement.class)).withSuperParent(2, (Class)OCMessageArgument.class) });
        POSSIBLE_RECEIVER_KEY = new Key("POSSIBLE_SELECTOR");
        POSSIBLE_PLACE = (PsiElementPattern.Capture)((PsiElementPattern.Capture)PlatformPatterns.psiElement().andNot((ElementPattern)PlatformPatterns.psiComment())).afterLeaf((ElementPattern)((PsiElementPattern.Capture)PlatformPatterns.psiElement().withElementType(TokenSet.create(new IElementType[] { OCTokenTypes.IDENTIFIER, OCTokenTypes.RBRACKET, OCTokenTypes.RPAR, OCTokenTypes.RBRACE }))).save((Key)MethodSelectorCompletionContributor.POSSIBLE_RECEIVER_KEY));
        REMOVE_EXTRA_COLON_HANDLER = (InsertHandler)new InsertHandler<LookupElement>() {
            public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
                final Document document = insertionContext.getDocument();
                final int offset = insertionContext.getEditor().getCaretModel().getOffset();
                if (document.getCharsSequence().charAt(offset) == ':') {
                    document.deleteString(offset, offset + 1);
                }
            }
        };
        CONTEXT = new Key("CONTEXT");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class SendMessagePlace
    {
        @NotNull
        public final OCSendMessageExpression expression;
        @NotNull
        public final OCMessageArgument argument;
        
        public SendMessagePlace(@NotNull final OCMessageArgument argument) {
            if (argument == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argument", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$SendMessagePlace", "<init>"));
            }
            this.expression = (OCSendMessageExpression)argument.getParent();
            this.argument = argument;
        }
    }
    
    public static class Context
    {
        @NotNull
        private final String myExactSelectorPrefix;
        @NotNull
        private final OCObjectTypeContext myReceiverContext;
        @NotNull
        private final Condition<OCMethodSymbol> myCondition;
        @Nullable
        private final PsiElement myReceiver;
        
        public Context(@NotNull final String myExactSelectorPrefix, @Nullable final PsiElement myReceiver, @NotNull final OCObjectTypeContext myReceiverContext, @NotNull final Condition<OCMethodSymbol> myCondition) {
            if (myExactSelectorPrefix == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "<init>"));
            }
            if (myReceiverContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "<init>"));
            }
            if (myCondition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "<init>"));
            }
            this.myExactSelectorPrefix = myExactSelectorPrefix;
            this.myReceiver = myReceiver;
            this.myReceiverContext = myReceiverContext;
            this.myCondition = myCondition;
        }
        
        @NotNull
        public String getExactSelectorPrefix() {
            String myExactSelectorPrefix;
            try {
                myExactSelectorPrefix = this.myExactSelectorPrefix;
                if (myExactSelectorPrefix == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "getExactSelectorPrefix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myExactSelectorPrefix;
        }
        
        @NotNull
        public OCObjectTypeContext getReceiverContext() {
            OCObjectTypeContext myReceiverContext;
            try {
                myReceiverContext = this.myReceiverContext;
                if (myReceiverContext == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "getReceiverContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myReceiverContext;
        }
        
        @NotNull
        public Condition<OCMethodSymbol> getCondition() {
            Condition<OCMethodSymbol> myCondition;
            try {
                myCondition = this.myCondition;
                if (myCondition == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/MethodSelectorCompletionContributor$Context", "getCondition"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myCondition;
        }
        
        @Nullable
        public PsiElement getReceiver() {
            return this.myReceiver;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
