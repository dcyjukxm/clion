// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.search;

import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Vector;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Stack;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.util.NullableFunction;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.intellij.util.Function;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.psi.search.TextOccurenceProcessor;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.ReadAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.impl.search.PsiSearchHelperImpl;
import java.util.EnumSet;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.psi.PsiReference;
import com.intellij.util.QueryExecutor;

public class OCFunctionReferenceSearch implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters>
{
    private final boolean myFindAllPotentialCalls;
    private static final EnumSet<PsiSearchHelperImpl.Options> UDL;
    private static final EnumSet<PsiSearchHelperImpl.Options> ORDINAL;
    
    public OCFunctionReferenceSearch() {
        this(false);
    }
    
    public OCFunctionReferenceSearch(final boolean myFindAllPotentialCalls) {
        this.myFindAllPotentialCalls = myFindAllPotentialCalls;
    }
    
    public boolean execute(@NotNull final ReferencesSearch.SearchParameters searchParameters, @NotNull final Processor<PsiReference> processor) {
        try {
            if (searchParameters == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "queryParameters", "com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "consumer", "com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch", "execute"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return (boolean)new ReadAction<Boolean>() {
            protected void run(@NotNull final Result<Boolean> result) {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$1", "run"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                result.setResult((Object)OCFunctionReferenceSearch.this.a(searchParameters, processor));
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }.execute().getResultObject();
    }
    
    @NotNull
    private Boolean a(final ReferencesSearch.SearchParameters p0, final Processor<PsiReference> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.getElementToSearch:()Lcom/intellij/psi/PsiElement;
        //     4: astore_3       
        //     5: aload_3        
        //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     9: ifeq            19
        //    12: aload_3        
        //    13: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    18: astore_3       
        //    19: aload_3        
        //    20: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    23: ifne            76
        //    26: iconst_1       
        //    27: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    30: dup            
        //    31: ifnonnull       75
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    40: athrow         
        //    41: new             Ljava/lang/IllegalStateException;
        //    44: dup            
        //    45: ldc             "@NotNull method %s.%s must not return null"
        //    47: ldc             2
        //    49: anewarray       Ljava/lang/Object;
        //    52: dup            
        //    53: ldc             0
        //    55: ldc             "com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch"
        //    57: aastore        
        //    58: dup            
        //    59: ldc             1
        //    61: ldc             "doExecute"
        //    63: aastore        
        //    64: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    67: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    70: athrow         
        //    71: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: areturn        
        //    76: aload_3        
        //    77: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    80: astore          4
        //    82: aload_3        
        //    83: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //    88: invokestatic    com/intellij/psi/search/PsiSearchHelper$SERVICE.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/search/PsiSearchHelper;
        //    91: astore          5
        //    93: aload           4
        //    95: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getName:()Ljava/lang/String;
        //   100: astore          6
        //   102: aload           6
        //   104: ifnull          122
        //   107: aload           6
        //   109: invokevirtual   java/lang/String.isEmpty:()Z
        //   112: ifeq            172
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: iconst_1       
        //   123: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   126: dup            
        //   127: ifnonnull       171
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: new             Ljava/lang/IllegalStateException;
        //   140: dup            
        //   141: ldc             "@NotNull method %s.%s must not return null"
        //   143: ldc             2
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "doExecute"
        //   159: aastore        
        //   160: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   163: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   166: athrow         
        //   167: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: areturn        
        //   172: new             Lcom/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor;
        //   175: dup            
        //   176: aload           4
        //   178: aload_2        
        //   179: aload_0        
        //   180: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.myFindAllPotentialCalls:Z
        //   183: invokespecial   com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.<init>:(Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;Lcom/intellij/util/Processor;Z)V
        //   186: astore          7
        //   188: aload           5
        //   190: checkcast       Lcom/intellij/psi/impl/search/PsiSearchHelperImpl;
        //   193: aload           7
        //   195: aload_1        
        //   196: invokevirtual   com/intellij/psi/search/searches/ReferencesSearch$SearchParameters.getEffectiveSearchScope:()Lcom/intellij/psi/search/SearchScope;
        //   199: aload           6
        //   201: iconst_1       
        //   202: aload           4
        //   204: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.isOperator:()Z
        //   209: ifeq            222
        //   212: getstatic       com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.UDL:Ljava/util/EnumSet;
        //   215: goto            225
        //   218: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   221: athrow         
        //   222: getstatic       com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.ORDINAL:Ljava/util/EnumSet;
        //   225: aconst_null    
        //   226: invokevirtual   com/intellij/psi/impl/search/PsiSearchHelperImpl.processElementsWithWord:(Lcom/intellij/psi/search/TextOccurenceProcessor;Lcom/intellij/psi/search/SearchScope;Ljava/lang/String;SLjava/util/EnumSet;Ljava/lang/String;)Z
        //   229: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   232: dup            
        //   233: ifnonnull       270
        //   236: new             Ljava/lang/IllegalStateException;
        //   239: dup            
        //   240: ldc             "@NotNull method %s.%s must not return null"
        //   242: ldc             2
        //   244: anewarray       Ljava/lang/Object;
        //   247: dup            
        //   248: ldc             0
        //   250: ldc             "com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch"
        //   252: aastore        
        //   253: dup            
        //   254: ldc             1
        //   256: ldc             "doExecute"
        //   258: aastore        
        //   259: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   262: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   265: athrow         
        //   266: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   269: athrow         
        //   270: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;Lcom/intellij/util/Processor<Lcom/intellij/psi/PsiReference;>;)Ljava/lang/Boolean;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     34     37     41     Ljava/lang/IllegalArgumentException;
        //  26     71     71     75     Ljava/lang/IllegalArgumentException;
        //  102    115    118    122    Ljava/lang/IllegalArgumentException;
        //  107    130    133    137    Ljava/lang/IllegalArgumentException;
        //  122    167    167    171    Ljava/lang/IllegalArgumentException;
        //  188    218    218    222    Ljava/lang/IllegalArgumentException;
        //  225    266    266    270    Ljava/lang/IllegalArgumentException;
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
    
    public static boolean isCallViaReference(OCExpression diveIntoParenthesesAndCasts) {
        diveIntoParenthesesAndCasts = OCParenthesesUtils.diveIntoParenthesesAndCasts(diveIntoParenthesesAndCasts);
        Label_0040: {
            try {
                if (!(diveIntoParenthesesAndCasts instanceof OCUnaryExpression)) {
                    break Label_0040;
                }
                final OCExpression ocExpression = diveIntoParenthesesAndCasts;
                final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression;
                final OCElementType ocElementType = ocUnaryExpression.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.MUL;
                if (ocElementType == ocPunctuatorElementType) {
                    return true;
                }
                break Label_0040;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final OCExpression ocExpression = diveIntoParenthesesAndCasts;
                final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)ocExpression;
                final OCElementType ocElementType = ocUnaryExpression.getOperationSign();
                final OCPunctuatorElementType ocPunctuatorElementType = OCTokenTypes.MUL;
                if (ocElementType == ocPunctuatorElementType) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        if (diveIntoParenthesesAndCasts != null) {
            final OCType resolvedType = diveIntoParenthesesAndCasts.getResolvedType();
            Label_0072: {
                try {
                    if (resolvedType instanceof OCPointerType) {
                        break Label_0072;
                    }
                    final OCType ocType = resolvedType;
                    final boolean b = ocType instanceof OCCppReferenceType;
                    if (b) {
                        break Label_0072;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final OCType ocType = resolvedType;
                    final boolean b = ocType instanceof OCCppReferenceType;
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return false;
        }
        return false;
    }
    
    static {
        UDL = EnumSet.of(PsiSearchHelperImpl.Options.CASE_SENSITIVE_SEARCH, PsiSearchHelperImpl.Options.PROCESS_INJECTED_PSI);
        ORDINAL = EnumSet.of(PsiSearchHelperImpl.Options.PROCESS_ONLY_JAVA_IDENTIFIERS_IF_POSSIBLE, PsiSearchHelperImpl.Options.CASE_SENSITIVE_SEARCH, PsiSearchHelperImpl.Options.PROCESS_INJECTED_PSI);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private static class MyOccurenceProcessor implements TextOccurenceProcessor
    {
        private final OCSymbolWithQualifiedName myMethodSymbol;
        private final Processor<PsiReference> myConsumer;
        private final boolean myFindAllPotentialCalls;
        private final OCType myResolvedType;
        private final Collection<OCQualifiedName> myPossibleOwners;
        private final OCQualifiedName myOwner;
        
        public MyOccurenceProcessor(final OCFunctionDeclaration ocFunctionDeclaration, final Processor<PsiReference> myConsumer, final boolean myFindAllPotentialCalls) {
            this.myMethodSymbol = ocFunctionDeclaration.getSymbol();
            this.myConsumer = myConsumer;
            this.myFindAllPotentialCalls = myFindAllPotentialCalls;
            if (this.myMethodSymbol != null) {
                this.myResolvedType = this.myMethodSymbol.getResolvedType();
                final OCSymbolWithQualifiedName resolvedOwner = this.myMethodSymbol.getResolvedOwner();
                if (resolvedOwner instanceof OCStructSymbol) {
                    final NullableFunction nullableFunction = ocStructSymbol -> ocStructSymbol.getResolvedQualifiedName();
                    this.myOwner = (OCQualifiedName)nullableFunction.fun((Object)resolvedOwner);
                    if (this.myFindAllPotentialCalls) {
                        final CommonProcessors.CollectProcessor collectProcessor = new CommonProcessors.CollectProcessor();
                        collectProcessor.process((Object)resolvedOwner);
                        processAncestors((OCStructSymbol)resolvedOwner, (Processor<OCStructSymbol>)collectProcessor);
                        this.myPossibleOwners = (Collection<OCQualifiedName>)ContainerUtil.mapNotNull(collectProcessor.getResults(), (Function)nullableFunction);
                    }
                    else {
                        this.myPossibleOwners = (Collection<OCQualifiedName>)ContainerUtil.mapNotNull(OCStructInheritorsSearch.search((OCStructSymbol)resolvedOwner, (PsiElement)ocFunctionDeclaration).findAll(), (Function)nullableFunction);
                    }
                }
                else {
                    this.myOwner = OCQualifiedName.GLOBAL;
                    this.myPossibleOwners = (Collection<OCQualifiedName>)Collections.emptySet();
                }
            }
            else {
                this.myOwner = null;
                this.myResolvedType = null;
                this.myPossibleOwners = (Collection<OCQualifiedName>)Collections.emptySet();
            }
        }
        
        public static boolean processAncestors(final OCStructSymbol ocStructSymbol, final Processor<OCStructSymbol> processor) {
            final OCFile containingOCFile = ocStructSymbol.getContainingOCFile();
            try {
                if (containingOCFile == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)containingOCFile);
            final HashSet<OCStructSymbol> set = new HashSet<OCStructSymbol>();
            final Stack<OCStructSymbol> stack = new Stack<OCStructSymbol>();
            stack.add(ocStructSymbol);
            while (!stack.isEmpty()) {
                final OCStructSymbol ocStructSymbol2 = stack.pop();
                try {
                    if (set.contains(ocStructSymbol2)) {
                        continue;
                    }
                    set.add(ocStructSymbol2);
                    final OCStructSymbol ocStructSymbol4;
                    OCStructSymbol ocStructSymbol3;
                    final Vector<OCStructSymbol> vector;
                    final OCStructSymbol ocStructSymbol5;
                    Object o;
                    final Processor processor2;
                    final boolean b;
                    ocStructSymbol2.processBaseClasses(ocResolveContext, (ocSymbol, p3) -> {
                        try {
                            if (ocSymbol instanceof OCStructSymbol) {
                                ocStructSymbol3 = ocStructSymbol4;
                                vector.add(ocStructSymbol3);
                                o = ocStructSymbol5;
                                processor2.process(o);
                                if (!b) {
                                    return false;
                                }
                                else {
                                    return true;
                                }
                            }
                            else {
                                return true;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            ocStructSymbol3 = ocStructSymbol4;
                            vector.add(ocStructSymbol3);
                            o = ocStructSymbol5;
                            processor2.process(o);
                            if (!b) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        return true;
                    });
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return true;
        }
        
        public boolean execute(@NotNull final PsiElement p0, final int p1) {
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
            //    18: ldc             "element"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "execute"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aconst_null    
            //    45: astore_3       
            //    46: iconst_0       
            //    47: istore          4
            //    49: aload_1        
            //    50: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
            //    55: astore          5
            //    57: aload           5
            //    59: instanceof      Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference;
            //    62: ifeq            77
            //    65: aload           5
            //    67: checkcast       Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference;
            //    70: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCOperatorReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    73: astore_3       
            //    74: goto            151
            //    77: aload_1        
            //    78: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
            //    81: ifeq            120
            //    84: aload_1        
            //    85: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
            //    88: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //    93: astore_3       
            //    94: aload_1        
            //    95: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
            //    98: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
            //   103: ifnonnull       114
            //   106: iconst_1       
            //   107: goto            115
            //   110: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   113: athrow         
            //   114: iconst_0       
            //   115: istore          4
            //   117: goto            151
            //   120: aload_1        
            //   121: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
            //   124: ifeq            151
            //   127: aload_1        
            //   128: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
            //   131: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   136: astore_3       
            //   137: aload_1        
            //   138: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
            //   141: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
            //   146: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch.isCallViaReference:(Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
            //   149: istore          4
            //   151: aload_3        
            //   152: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   155: ifeq            526
            //   158: aload_3        
            //   159: aload_0        
            //   160: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myMethodSymbol:Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
            //   163: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
            //   166: ifeq            196
            //   169: goto            176
            //   172: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   175: athrow         
            //   176: aload_0        
            //   177: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myConsumer:Lcom/intellij/util/Processor;
            //   180: aload_1        
            //   181: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
            //   186: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   191: ireturn        
            //   192: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   195: athrow         
            //   196: aload_3        
            //   197: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   200: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   203: astore          6
            //   205: aload           6
            //   207: ifnull          526
            //   210: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor;
            //   213: dup            
            //   214: aload_0        
            //   215: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myResolvedType:Lcom/jetbrains/cidr/lang/types/OCType;
            //   218: iconst_1       
            //   219: iconst_1       
            //   220: iconst_1       
            //   221: iconst_1       
            //   222: iconst_1       
            //   223: aload_3        
            //   224: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
            //   229: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.<init>:(Lcom/jetbrains/cidr/lang/types/OCType;ZZZZZLcom/intellij/psi/PsiFile;)V
            //   232: astore          7
            //   234: aload_0        
            //   235: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myOwner:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   238: ifnull          310
            //   241: aload_0        
            //   242: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myOwner:Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   245: aload           6
            //   247: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   250: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.equals:(Ljava/lang/Object;)Z
            //   253: ifeq            310
            //   256: goto            263
            //   259: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   262: athrow         
            //   263: aload_3        
            //   264: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   269: aload           7
            //   271: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
            //   274: checkcast       Ljava/lang/Boolean;
            //   277: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //   280: ifeq            526
            //   283: goto            290
            //   286: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   289: athrow         
            //   290: aload_0        
            //   291: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myConsumer:Lcom/intellij/util/Processor;
            //   294: aload_1        
            //   295: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
            //   300: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   305: ireturn        
            //   306: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   309: athrow         
            //   310: aload_0        
            //   311: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myPossibleOwners:Ljava/util/Collection;
            //   314: aload           6
            //   316: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getQualifier:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
            //   319: invokeinterface java/util/Collection.contains:(Ljava/lang/Object;)Z
            //   324: ifeq            526
            //   327: aload_0        
            //   328: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myFindAllPotentialCalls:Z
            //   331: ifne            388
            //   334: goto            341
            //   337: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   340: athrow         
            //   341: aload_3        
            //   342: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   347: aload           7
            //   349: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
            //   352: checkcast       Ljava/lang/Boolean;
            //   355: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //   358: ifeq            526
            //   361: goto            368
            //   364: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   367: athrow         
            //   368: aload_0        
            //   369: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myConsumer:Lcom/intellij/util/Processor;
            //   372: aload_1        
            //   373: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
            //   378: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   383: ireturn        
            //   384: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   387: athrow         
            //   388: iload           4
            //   390: ifeq            526
            //   393: aload_1        
            //   394: iconst_1       
            //   395: anewarray       Ljava/lang/Class;
            //   398: dup            
            //   399: iconst_0       
            //   400: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
            //   402: aastore        
            //   403: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
            //   406: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
            //   409: astore          8
            //   411: aload           8
            //   413: ifnull          474
            //   416: aload           8
            //   418: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
            //   423: astore          9
            //   425: aload           9
            //   427: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   430: ifeq            474
            //   433: aload           9
            //   435: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
            //   438: astore          10
            //   440: aload           10
            //   442: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppDestructor:()Z
            //   445: ifne            471
            //   448: aload           10
            //   450: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
            //   453: ifne            471
            //   456: goto            463
            //   459: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   462: athrow         
            //   463: iconst_1       
            //   464: goto            472
            //   467: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   470: athrow         
            //   471: iconst_0       
            //   472: istore          4
            //   474: iload           4
            //   476: ifeq            526
            //   479: aload_3        
            //   480: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getType:()Lcom/jetbrains/cidr/lang/types/OCType;
            //   485: aload           7
            //   487: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
            //   490: checkcast       Ljava/lang/Boolean;
            //   493: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //   496: ifeq            526
            //   499: goto            506
            //   502: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   505: athrow         
            //   506: aload_0        
            //   507: getfield        com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.myConsumer:Lcom/intellij/util/Processor;
            //   510: aload_1        
            //   511: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
            //   516: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
            //   521: ireturn        
            //   522: invokestatic    com/jetbrains/cidr/lang/search/OCFunctionReferenceSearch$MyOccurenceProcessor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   525: athrow         
            //   526: iconst_1       
            //   527: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  94     110    110    114    Ljava/lang/IllegalArgumentException;
            //  151    169    172    176    Ljava/lang/IllegalArgumentException;
            //  158    192    192    196    Ljava/lang/IllegalArgumentException;
            //  234    256    259    263    Ljava/lang/IllegalArgumentException;
            //  241    283    286    290    Ljava/lang/IllegalArgumentException;
            //  263    306    306    310    Ljava/lang/IllegalArgumentException;
            //  310    334    337    341    Ljava/lang/IllegalArgumentException;
            //  327    361    364    368    Ljava/lang/IllegalArgumentException;
            //  341    384    384    388    Ljava/lang/IllegalArgumentException;
            //  440    456    459    463    Ljava/lang/IllegalArgumentException;
            //  448    467    467    471    Ljava/lang/IllegalArgumentException;
            //  474    499    502    506    Ljava/lang/IllegalArgumentException;
            //  479    522    522    526    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0263:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
