// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.application.WriteAction;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.util.CommonProcessors;
import com.intellij.util.Consumer;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.openapi.editor.RangeMarker;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;

public class OCGoogleGenerateTestHandler extends OCCppGenerateTestHandler
{
    public OCGoogleGenerateTestHandler(final String s, final String s2) {
        super(s, s2);
    }
    
    @Override
    public boolean isValidFor(final Editor p0, final PsiFile p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: aload_2        
        //     3: invokespecial   com/jetbrains/cidr/generate/handlers/OCCppGenerateTestHandler.isValidFor:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiFile;)Z
        //     6: ifeq            50
        //     9: aload_2        
        //    10: invokeinterface com/intellij/psi/PsiFile.getProject:()Lcom/intellij/openapi/project/Project;
        //    15: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.areSymbolsLoaded:(Lcom/intellij/openapi/project/Project;)Z
        //    18: ifeq            50
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    27: athrow         
        //    28: aload_2        
        //    29: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.fileIncludesGoogleTest:(Lcom/intellij/psi/PsiFile;)Z
        //    32: ifeq            50
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: iconst_1       
        //    43: goto            51
        //    46: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: iconst_0       
        //    51: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      21     24     28     Ljava/lang/IllegalArgumentException;
        //  9      35     38     42     Ljava/lang/IllegalArgumentException;
        //  28     46     46     50     Ljava/lang/IllegalArgumentException;
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
    protected OCCppActionContext<OCStructSymbol, OCFunctionSymbol> evaluateActionContext(final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
        return this.evaluateActionContext(null, (PsiElement)psiFile);
    }
    
    @Override
    protected void onTemplateFinished(@NotNull final PsiFile psiFile, @NotNull final RangeMarker rangeMarker) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler", "onTemplateFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (rangeMarker == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeMarker", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler", "onTemplateFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        PsiElement psiElement = a(psiFile, rangeMarker);
        if (!(psiElement instanceof OCMacroCall)) {
            psiElement = PsiTreeUtil.getNextSiblingOfType(psiElement, (Class)OCMacroCall.class);
        }
        if (psiElement instanceof OCMacroCall) {
            final OCReferenceElement macroReferenceElement = ((OCMacroCall)psiElement).getMacroReferenceElement();
            final List<OCMacroCallArgument> arguments = ((OCMacroCall)psiElement).getArguments();
            Label_0164: {
                try {
                    if (macroReferenceElement == null) {
                        return;
                    }
                    final OCReferenceElement ocReferenceElement = macroReferenceElement;
                    final String s = ocReferenceElement.getName();
                    final String s2 = "TEST";
                    final boolean b = s.equals(s2);
                    if (b) {
                        break Label_0164;
                    }
                    return;
                }
                catch (IllegalArgumentException ex3) {
                    throw c(ex3);
                }
                try {
                    final OCReferenceElement ocReferenceElement = macroReferenceElement;
                    final String s = ocReferenceElement.getName();
                    final String s2 = "TEST";
                    final boolean b = s.equals(s2);
                    if (!b) {
                        return;
                    }
                    if (arguments.size() != 2) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
            }
            final String argumentValue = CidrGoogleTestUtil.extractArgumentValue((PsiElement)arguments.get(0));
            final String argumentValue2 = CidrGoogleTestUtil.extractArgumentValue((PsiElement)arguments.get(1));
            try {
                if (b(macroReferenceElement, argumentValue)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw c(ex5);
            }
            try {
                if (a(macroReferenceElement, argumentValue)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw c(ex6);
            }
            try {
                if (a(macroReferenceElement, argumentValue, argumentValue2)) {
                    return;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw c(ex7);
            }
            c(macroReferenceElement, argumentValue);
        }
    }
    
    private static boolean c(final OCReferenceElement ocReferenceElement, final String s) {
        return a(s, ocReferenceElement, "TEST_F", (Condition<OCSymbol>)(ocSymbol -> {
            Label_0024: {
                try {
                    if (!(ocSymbol instanceof OCStructSymbol)) {
                        return false;
                    }
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final boolean b = CidrGoogleTestUtil.isGoogleTestClass(ocStructSymbol2);
                    if (b) {
                        break Label_0024;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final boolean b = CidrGoogleTestUtil.isGoogleTestClass(ocStructSymbol2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return false;
        }), null);
    }
    
    private static boolean a(final OCReferenceElement ocReferenceElement, final String s, final String s2) {
        return a("gtest_registered_test_names_" + s + "_", ocReferenceElement, "TYPED_TEST_P", null, (Consumer<OCSymbol>)(ocSymbol -> {
            final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getPrevSiblingOfType((PsiElement)PsiTreeUtil.getParentOfType(ocSymbol.locateDefinition(), (Class)OCDeclaration.class), (Class)OCMacroCall.class);
            if (ocMacroCall != null) {
                final OCReferenceElement macroReferenceElement = ocMacroCall.getMacroReferenceElement();
                try {
                    if (macroReferenceElement == null || !macroReferenceElement.getName().equals("REGISTER_TYPED_TEST_CASE_P")) {
                        return;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                final List<OCMacroCallArgument> arguments = ocMacroCall.getArguments();
                OCChangeUtil.addAfter((PsiElement)ocMacroCall, OCElementFactory.expressionFromText(s2, (PsiElement)ocMacroCall), OCChangeUtil.addAfter((PsiElement)ocMacroCall, OCElementFactory.create(OCTokenTypes.COMMA, (PsiElement)ocMacroCall), (PsiElement)arguments.get(arguments.size() - 1)));
            }
        }));
    }
    
    private static boolean a(final OCReferenceElement ocReferenceElement, final String s) {
        return a("gtest_type_params_" + s + "_", ocReferenceElement, "TYPED_TEST", null, null);
    }
    
    private static boolean b(final OCReferenceElement ocReferenceElement, final String s) {
        return a(s, ocReferenceElement, "TEST_P", (Condition<OCSymbol>)(ocSymbol -> {
            Label_0038: {
                try {
                    if (!(ocSymbol instanceof OCStructSymbol)) {
                        return false;
                    }
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final int n = 2;
                    final String[] array = new String[n];
                    final int n2 = 0;
                    final String s = "TestWithParam";
                    array[n2] = s;
                    final int n3 = 1;
                    final String s2 = "WithParamInterface";
                    array[n3] = s2;
                    final boolean b = CidrGoogleTestUtil.isGoogleTestClassWithAnyAncestor(ocStructSymbol2, array);
                    if (b) {
                        break Label_0038;
                    }
                    return false;
                }
                catch (IllegalArgumentException ex) {
                    throw c(ex);
                }
                try {
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final int n = 2;
                    final String[] array = new String[n];
                    final int n2 = 0;
                    final String s = "TestWithParam";
                    array[n2] = s;
                    final int n3 = 1;
                    final String s2 = "WithParamInterface";
                    array[n3] = s2;
                    final boolean b = CidrGoogleTestUtil.isGoogleTestClassWithAnyAncestor(ocStructSymbol2, array);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw c(ex2);
                }
            }
            return false;
        }), null);
    }
    
    private static boolean a(@NotNull final String s, @NotNull final OCReferenceElement ocReferenceElement, @NotNull final String s2, @Nullable final Condition<OCSymbol> condition, @Nullable final Consumer<OCSymbol> consumer) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suiteName", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler", "correctMacroNameIfNeeded"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (ocReferenceElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "referenceElement", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler", "correctMacroNameIfNeeded"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "correctMacroName", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler", "correctMacroNameIfNeeded"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        final OCFile containingOCFile = ocReferenceElement.getContainingOCFile();
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return containingOCFile.equals(ocSymbol.getContainingOCFile()) && (condition == null || condition.value((Object)ocSymbol));
            }
        };
        try {
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(ocReferenceElement.getProject(), (Processor<OCSymbol>)findFirstProcessor, s);
            if (findFirstProcessor.isFound()) {
                new WriteAction() {
                    protected void run(@NotNull final Result result) throws Throwable {
                        try {
                            if (result == null) {
                                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler$2", "run"));
                            }
                        }
                        catch (Throwable t) {
                            throw a(t);
                        }
                        try {
                            if (consumer != null) {
                                consumer.consume(findFirstProcessor.getFoundValue());
                            }
                        }
                        catch (Throwable t2) {
                            throw a(t2);
                        }
                        ocReferenceElement.setNameOfIdentifier(s2);
                        FileSymbolTablesCache.getInstance(containingOCFile.getProject()).scheduleReparseFile(containingOCFile);
                    }
                    
                    private static Throwable a(final Throwable t) {
                        return t;
                    }
                }.execute();
            }
        }
        catch (IllegalArgumentException ex4) {
            throw c(ex4);
        }
        return findFirstProcessor.isFound();
    }
    
    @Nullable
    private static PsiElement a(@NotNull final PsiFile psiFile, @NotNull final RangeMarker rangeMarker) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler", "getFirstInsertedElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (rangeMarker == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeMarker", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler", "getFirstInsertedElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        PsiElement psiElement;
        for (psiElement = psiFile.findElementAt(rangeMarker.getStartOffset()); psiElement instanceof PsiWhiteSpace; psiElement = psiElement.getNextSibling()) {}
        return PsiTreeUtil.getContextOfType(psiElement, false, new Class[] { OCMacroCall.class, OCDeclaration.class });
    }
    
    @Override
    protected int getInsertPos(@NotNull final PsiElement p0, final int p1, @Nullable final PsiElement p2, @NotNull final List<OCFunctionSymbol> p3, @NotNull final OCCppActionContext<OCStructSymbol, OCFunctionSymbol> p4) {
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
        //    24: ldc             "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getInsertPos"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload           4
        //    46: ifnonnull       89
        //    49: new             Ljava/lang/IllegalArgumentException;
        //    52: dup            
        //    53: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    55: ldc             3
        //    57: anewarray       Ljava/lang/Object;
        //    60: dup            
        //    61: ldc             0
        //    63: ldc             "members"
        //    65: aastore        
        //    66: dup            
        //    67: ldc             1
        //    69: ldc             "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             2
        //    75: ldc             "getInsertPos"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: aload           5
        //    91: ifnonnull       134
        //    94: new             Ljava/lang/IllegalArgumentException;
        //    97: dup            
        //    98: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   100: ldc             3
        //   102: anewarray       Ljava/lang/Object;
        //   105: dup            
        //   106: ldc             0
        //   108: ldc             "actionContext"
        //   110: aastore        
        //   111: dup            
        //   112: ldc             1
        //   114: ldc             "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler"
        //   116: aastore        
        //   117: dup            
        //   118: ldc             2
        //   120: ldc             "getInsertPos"
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   129: athrow         
        //   130: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   133: athrow         
        //   134: iconst_0       
        //   135: istore          6
        //   137: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   142: astore          7
        //   144: aload           7
        //   146: aload_3        
        //   147: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
        //   152: ifeq            201
        //   155: getstatic       com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.$assertionsDisabled:Z
        //   158: ifne            191
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload_3        
        //   169: ifnonnull       191
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: new             Ljava/lang/AssertionError;
        //   182: dup            
        //   183: invokespecial   java/lang/AssertionError.<init>:()V
        //   186: athrow         
        //   187: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload_3        
        //   192: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   197: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   200: ireturn        
        //   201: aload_3        
        //   202: iconst_1       
        //   203: anewarray       Ljava/lang/Class;
        //   206: dup            
        //   207: iconst_0       
        //   208: ldc             Lcom/jetbrains/cidr/lang/psi/OCStruct;.class
        //   210: aastore        
        //   211: invokestatic    com/intellij/psi/util/PsiTreeUtil.getNonStrictParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   214: checkcast       Lcom/jetbrains/cidr/lang/psi/OCStruct;
        //   217: astore          8
        //   219: aload           8
        //   221: ifnull          240
        //   224: aload           8
        //   226: invokeinterface com/jetbrains/cidr/lang/psi/OCStruct.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   231: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   234: iconst_0       
        //   235: invokestatic    com/jetbrains/cidr/execution/testing/google/CidrGoogleTestUtil.isGoogleTestFixture:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Z)Z
        //   238: istore          6
        //   240: aload_3        
        //   241: iconst_1       
        //   242: aload           7
        //   244: invokestatic    com/intellij/psi/util/PsiTreeUtil.findFirstParent:(Lcom/intellij/psi/PsiElement;ZLcom/intellij/openapi/util/Condition;)Lcom/intellij/psi/PsiElement;
        //   247: astore_3       
        //   248: iload           6
        //   250: ifeq            263
        //   253: aload_3        
        //   254: ldc             Lcom/intellij/psi/PsiWhiteSpace;.class
        //   256: invokestatic    com/intellij/psi/util/PsiTreeUtil.getNextSiblingOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   259: astore_3       
        //   260: goto            270
        //   263: aload_3        
        //   264: ldc             Lcom/intellij/psi/PsiWhiteSpace;.class
        //   266: invokestatic    com/intellij/psi/util/PsiTreeUtil.getPrevSiblingOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   269: astore_3       
        //   270: aload_3        
        //   271: ifnull          288
        //   274: aload_3        
        //   275: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   280: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   283: ireturn        
        //   284: invokestatic    com/jetbrains/cidr/generate/handlers/OCGoogleGenerateTestHandler.c:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   287: athrow         
        //   288: iconst_m1      
        //   289: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;ILcom/intellij/psi/PsiElement;Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;Lcom/jetbrains/cidr/lang/generate/actions/OCCppActionContext<Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;>;)I
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     85     85     89     Ljava/lang/IllegalArgumentException;
        //  89     130    130    134    Ljava/lang/IllegalArgumentException;
        //  144    161    164    168    Ljava/lang/IllegalArgumentException;
        //  155    172    175    179    Ljava/lang/IllegalArgumentException;
        //  168    187    187    191    Ljava/lang/IllegalArgumentException;
        //  270    284    284    288    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0168:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCGoogleGenerateTestHandler.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
