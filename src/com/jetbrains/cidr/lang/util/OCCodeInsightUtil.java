// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationOrExpression;
import com.jetbrains.cidr.lang.psi.OCForStatement;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCLoopStatement;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.resolve.OCExprValueCategory;
import com.jetbrains.cidr.lang.resolve.OCResolveOverloadsUtil;
import com.jetbrains.cidr.lang.types.OCTypeOwner;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import com.jetbrains.cidr.lang.types.visitors.OCArgumentDepLookupAccumulator;
import java.util.Collections;
import com.intellij.util.containers.ContainerUtil;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import java.util.Iterator;
import java.util.regex.Pattern;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.types.CVQualifiers;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.util.registry.RegistryValue;
import com.intellij.psi.search.ProjectScope;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.ide.util.EditorHelper;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Segment;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.visitors.OCVisitor;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCStatementExpression;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCPostfixExpression;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.psi.OCPrefixExpression;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.intellij.openapi.util.Comparing;
import com.intellij.psi.PsiErrorElement;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.parser.OCElementType;
import java.util.Map;

public class OCCodeInsightUtil
{
    private static Map<OCElementType, OCElementType> oppositeOperators;
    private static Map<OCElementType, OCElementType> flippedOperators;
    
    public static boolean isInPlainOldC(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          73
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //    10: ifeq            73
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    19: athrow         
        //    20: aload_0        
        //    21: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    26: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    29: ifeq            73
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    38: athrow         
        //    39: aload_0        
        //    40: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    45: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //    53: ifne            71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    73: iconst_0       
        //    74: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      13     16     20     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  4      32     35     39     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  20     56     59     63     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  39     67     67     71     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    public static boolean isInObjC(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          63
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //    10: ifeq            63
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    19: athrow         
        //    20: aload_0        
        //    21: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    26: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    29: ifeq            63
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    38: athrow         
        //    39: aload_0        
        //    40: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    45: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getKind:()Lcom/jetbrains/cidr/lang/OCLanguageKind;
        //    53: invokeinterface com/jetbrains/cidr/lang/OCLanguageKind.isObjC:()Z
        //    58: ireturn        
        //    59: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    62: athrow         
        //    63: iconst_0       
        //    64: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      13     16     20     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  4      32     35     39     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  20     59     59     63     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    public static boolean isInPlainOldC(@Nullable final PsiFile psiFile, @Nullable final OCInclusionContext ocInclusionContext) {
        Label_0035: {
            Label_0025: {
                try {
                    if (ocInclusionContext == null) {
                        break Label_0035;
                    }
                    final OCInclusionContext ocInclusionContext2 = ocInclusionContext;
                    final OCLanguageKind ocLanguageKind = ocInclusionContext2.getLanguageKind();
                    final boolean b = ocLanguageKind.isCpp();
                    if (!b) {
                        break Label_0025;
                    }
                    return false;
                }
                catch (CancelException ex) {
                    throw b(ex);
                }
                try {
                    final OCInclusionContext ocInclusionContext2 = ocInclusionContext;
                    final OCLanguageKind ocLanguageKind = ocInclusionContext2.getLanguageKind();
                    final boolean b = ocLanguageKind.isCpp();
                    if (!b) {
                        return true;
                    }
                }
                catch (CancelException ex2) {
                    throw b(ex2);
                }
            }
            return false;
            try {
                if (psiFile != null) {
                    return isInPlainOldC((PsiElement)psiFile);
                }
            }
            catch (CancelException ex3) {
                throw b(ex3);
            }
        }
        return true;
    }
    
    @Contract("null->false")
    public static boolean isValid(@Nullable final PsiElement psiElement) {
        try {
            if (!OCSearchScope.isInProjectSources(psiElement)) {
                return false;
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall(psiElement);
        try {
            if (rangeInMacroCall == null) {
                return true;
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        final PsiElement prevLeaf = PsiTreeUtil.prevLeaf(psiElement);
        final PsiElement nextLeaf = PsiTreeUtil.nextLeaf(psiElement);
        final PsiElement firstChild = PsiTreeUtil.firstChild(psiElement);
        try {
            if (!(firstChild instanceof OCMacroForeignLeafElement)) {
                return false;
            }
        }
        catch (CancelException ex3) {
            throw b(ex3);
        }
        final String macroName = ((OCMacroForeignLeafElement)firstChild).getMacroName();
        Label_0130: {
            Label_0095: {
                try {
                    if (prevLeaf instanceof PsiErrorElement) {
                        return false;
                    }
                    final PsiElement psiElement2 = nextLeaf;
                    final boolean b = psiElement2 instanceof PsiErrorElement;
                    if (b) {
                        return false;
                    }
                    break Label_0095;
                }
                catch (CancelException ex4) {
                    throw b(ex4);
                }
                try {
                    final PsiElement psiElement2 = nextLeaf;
                    final boolean b = psiElement2 instanceof PsiErrorElement;
                    if (b) {
                        return false;
                    }
                }
                catch (CancelException ex5) {
                    throw b(ex5);
                }
                try {
                    if (!(prevLeaf instanceof OCMacroForeignLeafElement)) {
                        break Label_0130;
                    }
                    final PsiElement psiElement3 = prevLeaf;
                    final OCMacroForeignLeafElement ocMacroForeignLeafElement = (OCMacroForeignLeafElement)psiElement3;
                    final String s = ocMacroForeignLeafElement.getMacroName();
                    final String s2 = macroName;
                    final boolean b2 = Comparing.equal(s, s2);
                    if (b2) {
                        return false;
                    }
                    break Label_0130;
                }
                catch (CancelException ex6) {
                    throw b(ex6);
                }
            }
            try {
                final PsiElement psiElement3 = prevLeaf;
                final OCMacroForeignLeafElement ocMacroForeignLeafElement = (OCMacroForeignLeafElement)psiElement3;
                final String s = ocMacroForeignLeafElement.getMacroName();
                final String s2 = macroName;
                final boolean b2 = Comparing.equal(s, s2);
                if (b2) {
                    return false;
                }
            }
            catch (CancelException ex7) {
                throw b(ex7);
            }
            try {
                if (!(nextLeaf instanceof OCMacroForeignLeafElement)) {
                    return true;
                }
                final PsiElement psiElement4 = nextLeaf;
                final OCMacroForeignLeafElement ocMacroForeignLeafElement2 = (OCMacroForeignLeafElement)psiElement4;
                final String s3 = ocMacroForeignLeafElement2.getMacroName();
                final String s4 = macroName;
                final boolean b3 = Comparing.equal(s3, s4);
                if (b3) {
                    return false;
                }
                return true;
            }
            catch (CancelException ex8) {
                throw b(ex8);
            }
        }
        try {
            final PsiElement psiElement4 = nextLeaf;
            final OCMacroForeignLeafElement ocMacroForeignLeafElement2 = (OCMacroForeignLeafElement)psiElement4;
            final String s3 = ocMacroForeignLeafElement2.getMacroName();
            final String s4 = macroName;
            final boolean b3 = Comparing.equal(s3, s4);
            if (b3) {
                return false;
            }
        }
        catch (CancelException ex9) {
            throw b(ex9);
        }
        return true;
    }
    
    @Nullable
    public static OCClassSymbol getClassInFile(final OCFile ocFile) {
        final String nameWithoutExtension = FileUtil.getNameWithoutExtension(ocFile.getName());
        final OCClassSymbol[] array = { null };
        ocFile.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            @Override
            public void visitClassDeclaration(final OCClassDeclaration ocClassDeclaration) {
                if (array[0] == null || (Comparing.equal(ocClassDeclaration.getName(), nameWithoutExtension) && (!Comparing.equal(array[0].getName(), nameWithoutExtension) || ocClassDeclaration.getCategory() == null))) {
                    array[0] = ocClassDeclaration.getSymbol();
                }
            }
        });
        return array[0];
    }
    
    @Nullable
    public static OCExpression findExpressionAtRange(final PsiFile psiFile, final int n, final int n2) {
        return findElementAtRange(psiFile, n, n2, OCExpression.class, true);
    }
    
    @Nullable
    public static <E extends PsiElement> E findElementAtRange(final PsiFile psiFile, final TextRange textRange, final Class<E> clazz, final boolean b) {
        return findElementAtRange(psiFile, textRange.getStartOffset(), textRange.getEndOffset(), clazz, b);
    }
    
    @Nullable
    public static <E extends PsiElement> E findElementAtRange(final PsiFile p0, final int p1, final int p2, final Class<E> p3, final boolean p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iload_1        
        //     2: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //     7: astore          5
        //     9: aload           5
        //    11: ifnull          118
        //    14: aload           5
        //    16: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    21: astore          6
        //    23: aload           6
        //    25: ifnonnull       34
        //    28: aconst_null    
        //    29: areturn        
        //    30: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    33: athrow         
        //    34: aload           6
        //    36: iload_1        
        //    37: iload_2        
        //    38: invokevirtual   com/intellij/openapi/util/TextRange.containsRange:(II)Z
        //    41: ifeq            51
        //    44: goto            118
        //    47: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    50: athrow         
        //    51: aload           5
        //    53: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    56: ifeq            106
        //    59: aload           5
        //    61: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getExpansionExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    69: astore          5
        //    71: aload           5
        //    73: ifnull          115
        //    76: aload           6
        //    78: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    81: iload_1        
        //    82: if_icmpne       115
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    91: athrow         
        //    92: aload           5
        //    94: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    99: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   102: istore_1       
        //   103: goto            115
        //   106: aload           5
        //   108: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   113: astore          5
        //   115: goto            9
        //   118: aload           5
        //   120: iconst_2       
        //   121: anewarray       Ljava/lang/Class;
        //   124: dup            
        //   125: iconst_0       
        //   126: aload_3        
        //   127: aastore        
        //   128: dup            
        //   129: iconst_1       
        //   130: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCall;.class
        //   132: aastore        
        //   133: invokestatic    com/intellij/psi/util/PsiTreeUtil.getNonStrictParentOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   136: astore          6
        //   138: aload           6
        //   140: ifnull          147
        //   143: aload           6
        //   145: astore          5
        //   147: aload           5
        //   149: ifnull          166
        //   152: aload           5
        //   154: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   159: goto            167
        //   162: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   165: athrow         
        //   166: aconst_null    
        //   167: astore          7
        //   169: aload           5
        //   171: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   174: ifeq            189
        //   177: aload           5
        //   179: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   182: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getExpansionExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   187: astore          5
        //   189: aload           5
        //   191: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCallArgument;
        //   194: ifeq            227
        //   197: aload           5
        //   199: invokeinterface com/intellij/psi/PsiElement.getChildren:()[Lcom/intellij/psi/PsiElement;
        //   204: arraylength    
        //   205: iconst_1       
        //   206: if_icmplt       227
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   215: athrow         
        //   216: aload           5
        //   218: invokeinterface com/intellij/psi/PsiElement.getChildren:()[Lcom/intellij/psi/PsiElement;
        //   223: iconst_0       
        //   224: aaload         
        //   225: astore          5
        //   227: aload_3        
        //   228: ldc             Lcom/jetbrains/cidr/lang/psi/OCExpression;.class
        //   230: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   233: ifeq            272
        //   236: aload           5
        //   238: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   241: ifeq            272
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   250: athrow         
        //   251: aload           5
        //   253: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpressionStatement;
        //   256: invokeinterface com/jetbrains/cidr/lang/psi/OCExpressionStatement.getExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   261: astore          5
        //   263: aload           5
        //   265: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   270: astore          7
        //   272: aload_3        
        //   273: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarator;.class
        //   275: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   278: ifeq            338
        //   281: aload           5
        //   283: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   286: ifeq            338
        //   289: goto            296
        //   292: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   295: athrow         
        //   296: aload           5
        //   298: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   301: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   306: astore          8
        //   308: aload           8
        //   310: ifnull          338
        //   313: aload           8
        //   315: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   320: iload_1        
        //   321: iload_2        
        //   322: invokevirtual   com/intellij/openapi/util/TextRange.equalsToRange:(II)Z
        //   325: ifeq            338
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   334: athrow         
        //   335: iconst_0       
        //   336: istore          4
        //   338: iload           4
        //   340: ifeq            378
        //   343: aload           7
        //   345: ifnull          378
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   354: athrow         
        //   355: aload           7
        //   357: iload_1        
        //   358: iload_2        
        //   359: invokevirtual   com/intellij/openapi/util/TextRange.equalsToRange:(II)Z
        //   362: ifne            378
        //   365: goto            372
        //   368: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   371: athrow         
        //   372: aconst_null    
        //   373: areturn        
        //   374: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   377: athrow         
        //   378: aload_3        
        //   379: aload           5
        //   381: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //   384: ifeq            396
        //   387: aload           5
        //   389: goto            397
        //   392: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   395: athrow         
        //   396: aconst_null    
        //   397: areturn        
        //    Signature:
        //  <E:Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/PsiFile;IILjava/lang/Class<TE;>;Z)TE; [from metadata: <E::Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/PsiFile;IILjava/lang/Class<TE;>;Z)TE;]
        //  
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  23     30     30     34     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  34     47     47     51     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  71     85     88     92     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  147    162    162    166    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  189    209    212    216    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  227    244    247    251    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  272    289    292    296    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  308    328    331    335    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  338    348    351    355    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  343    365    368    372    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  355    374    374    378    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  378    392    392    396    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0355:
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
    public static PsiElement[] findStatementsAtRange(final PsiFile p0, final int p1, final int p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iload_1        
        //     2: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //     7: astore          4
        //     9: aload_0        
        //    10: iload_2        
        //    11: iconst_1       
        //    12: isub           
        //    13: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    18: astore          5
        //    20: aload           4
        //    22: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    25: ifeq            48
        //    28: aload           4
        //    30: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    35: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    38: istore_1       
        //    39: aload_0        
        //    40: iload_1        
        //    41: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    46: astore          4
        //    48: aload           5
        //    50: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    53: ifeq            78
        //    56: aload           5
        //    58: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    63: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    66: istore_2       
        //    67: aload_0        
        //    68: iload_2        
        //    69: iconst_1       
        //    70: isub           
        //    71: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    76: astore          5
        //    78: aload           5
        //    80: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    83: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    86: if_acmpne       123
        //    89: aload           4
        //    91: aload           5
        //    93: if_acmpeq       123
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   102: athrow         
        //   103: aload           5
        //   105: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   110: astore          5
        //   112: aload           5
        //   114: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   119: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   122: istore_2       
        //   123: aload           4
        //   125: ifnull          140
        //   128: aload           5
        //   130: ifnonnull       146
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   139: athrow         
        //   140: aconst_null    
        //   141: areturn        
        //   142: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   145: athrow         
        //   146: aload           4
        //   148: aload           5
        //   150: invokestatic    com/intellij/psi/util/PsiTreeUtil.findCommonParent:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   153: astore          6
        //   155: aload           6
        //   157: ifnonnull       166
        //   160: aconst_null    
        //   161: areturn        
        //   162: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   165: athrow         
        //   166: aload           6
        //   168: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   171: ifne            254
        //   174: aload           6
        //   176: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCodeFragment;
        //   179: ifeq            196
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   188: athrow         
        //   189: goto            254
        //   192: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   195: athrow         
        //   196: aload           6
        //   198: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   201: ifeq            216
        //   204: aload           6
        //   206: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   211: astore          6
        //   213: goto            254
        //   216: aload           6
        //   218: ifnull          236
        //   221: aload           6
        //   223: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   226: ifeq            242
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   235: athrow         
        //   236: aconst_null    
        //   237: areturn        
        //   238: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   241: athrow         
        //   242: aload           6
        //   244: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   249: astore          6
        //   251: goto            166
        //   254: aload           6
        //   256: aload           4
        //   258: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   261: ifne            298
        //   264: aload           6
        //   266: aload           4
        //   268: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   273: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   276: ifne            298
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   285: athrow         
        //   286: aload           4
        //   288: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   293: astore          4
        //   295: goto            264
        //   298: iload_3        
        //   299: ifeq            329
        //   302: iload_1        
        //   303: aload           4
        //   305: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   310: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   313: if_icmpeq       329
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   322: athrow         
        //   323: aconst_null    
        //   324: areturn        
        //   325: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   328: athrow         
        //   329: aload           6
        //   331: aload           5
        //   333: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   336: ifne            373
        //   339: aload           6
        //   341: aload           5
        //   343: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   348: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   351: ifne            373
        //   354: goto            361
        //   357: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   360: athrow         
        //   361: aload           5
        //   363: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   368: astore          5
        //   370: goto            339
        //   373: iload_3        
        //   374: ifeq            449
        //   377: iload_2        
        //   378: aload           5
        //   380: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   385: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   388: if_icmpeq       449
        //   391: goto            398
        //   394: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   397: athrow         
        //   398: iload_2        
        //   399: iconst_1       
        //   400: iadd           
        //   401: aload           5
        //   403: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   408: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   411: if_icmpne       443
        //   414: goto            421
        //   417: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   420: athrow         
        //   421: aload_0        
        //   422: invokeinterface com/intellij/psi/PsiFile.getText:()Ljava/lang/String;
        //   427: iload_2        
        //   428: invokevirtual   java/lang/String.charAt:(I)C
        //   431: bipush          59
        //   433: if_icmpeq       449
        //   436: goto            443
        //   439: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   442: athrow         
        //   443: aconst_null    
        //   444: areturn        
        //   445: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   448: athrow         
        //   449: aload           6
        //   451: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   454: ifeq            582
        //   457: iload_3        
        //   458: ifeq            513
        //   461: goto            468
        //   464: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   467: athrow         
        //   468: aload           6
        //   470: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   475: aload           4
        //   477: if_acmpne       582
        //   480: goto            487
        //   483: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   486: athrow         
        //   487: aload           6
        //   489: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   494: aload           5
        //   496: if_acmpne       582
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   505: athrow         
        //   506: goto            544
        //   509: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   512: athrow         
        //   513: aload           6
        //   515: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   520: aload           4
        //   522: if_acmpeq       544
        //   525: aload           6
        //   527: invokeinterface com/intellij/psi/PsiElement.getLastChild:()Lcom/intellij/psi/PsiElement;
        //   532: aload           5
        //   534: if_acmpne       582
        //   537: goto            544
        //   540: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   543: athrow         
        //   544: aload           6
        //   546: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   551: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   554: ifeq            572
        //   557: goto            564
        //   560: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   563: athrow         
        //   564: aconst_null    
        //   565: goto            581
        //   568: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   571: athrow         
        //   572: iconst_1       
        //   573: anewarray       Lcom/intellij/psi/PsiElement;
        //   576: dup            
        //   577: iconst_0       
        //   578: aload           6
        //   580: aastore        
        //   581: areturn        
        //   582: new             Ljava/util/ArrayList;
        //   585: dup            
        //   586: invokespecial   java/util/ArrayList.<init>:()V
        //   589: astore          7
        //   591: aload           5
        //   593: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   596: ifeq            626
        //   599: aload           5
        //   601: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   606: astore          8
        //   608: aload           8
        //   610: ifnull          622
        //   613: aload           8
        //   615: goto            624
        //   618: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   621: athrow         
        //   622: aload           5
        //   624: astore          5
        //   626: iconst_0       
        //   627: istore          8
        //   629: aload           6
        //   631: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getAllChildren:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   634: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   639: astore          9
        //   641: aload           9
        //   643: invokeinterface java/util/Iterator.hasNext:()Z
        //   648: ifeq            731
        //   651: aload           9
        //   653: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   658: checkcast       Lcom/intellij/psi/PsiElement;
        //   661: astore          10
        //   663: aload           10
        //   665: aload           4
        //   667: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   670: ifeq            676
        //   673: iconst_1       
        //   674: istore          8
        //   676: iload           8
        //   678: ifeq            711
        //   681: aload           10
        //   683: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   686: ifne            711
        //   689: goto            696
        //   692: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   695: athrow         
        //   696: aload           7
        //   698: aload           10
        //   700: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   703: pop            
        //   704: goto            711
        //   707: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   710: athrow         
        //   711: aload           10
        //   713: aload           5
        //   715: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   718: ifeq            728
        //   721: goto            731
        //   724: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   727: athrow         
        //   728: goto            641
        //   731: aload           7
        //   733: invokestatic    com/intellij/util/containers/ContainerUtil.getLastItem:(Ljava/util/List;)Ljava/lang/Object;
        //   736: checkcast       Lcom/intellij/psi/PsiElement;
        //   739: astore          9
        //   741: aload           9
        //   743: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //   746: ifeq            793
        //   749: aload           9
        //   751: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   756: astore          10
        //   758: aload           10
        //   760: ifnull          793
        //   763: aload           10
        //   765: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //   768: ifeq            793
        //   771: goto            778
        //   774: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   777: athrow         
        //   778: aload           7
        //   780: aload           10
        //   782: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   785: pop            
        //   786: goto            793
        //   789: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   792: athrow         
        //   793: aload           7
        //   795: invokevirtual   java/util/ArrayList.iterator:()Ljava/util/Iterator;
        //   798: astore          10
        //   800: aload           10
        //   802: invokeinterface java/util/Iterator.hasNext:()Z
        //   807: ifeq            854
        //   810: aload           10
        //   812: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   817: checkcast       Lcom/intellij/psi/PsiElement;
        //   820: astore          11
        //   822: aload           11
        //   824: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //   827: ifne            851
        //   830: aload           11
        //   832: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isElementSignificant:(Lcom/intellij/psi/PsiElement;)Z
        //   835: ifeq            851
        //   838: goto            845
        //   841: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   844: athrow         
        //   845: aconst_null    
        //   846: areturn        
        //   847: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   850: athrow         
        //   851: goto            800
        //   854: aload           7
        //   856: invokestatic    com/intellij/psi/util/PsiUtilCore.toPsiElementArray:(Ljava/util/Collection;)[Lcom/intellij/psi/PsiElement;
        //   859: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  78     96     99     103    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  123    133    136    140    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  128    142    142    146    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  155    162    162    166    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  166    182    185    189    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  174    192    192    196    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  216    229    232    236    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  221    238    238    242    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  254    279    282    286    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  298    316    319    323    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  302    325    325    329    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  329    354    357    361    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  373    391    394    398    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  377    414    417    421    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  398    436    439    443    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  421    445    445    449    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  449    461    464    468    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  457    480    483    487    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  468    499    502    506    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  487    509    509    513    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  513    537    540    544    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  525    557    560    564    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  544    568    568    572    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  608    618    618    622    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  676    689    692    696    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  681    704    707    711    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  711    724    724    728    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  758    771    774    778    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  763    786    789    793    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  822    838    841    845    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  830    847    847    851    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0398:
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
    
    public static <E extends PsiElement> List<E> findElementOccurrences(@Nullable final PsiElement psiElement, @NotNull E diveIntoParentheses) {
        try {
            if (diveIntoParentheses == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "findElementOccurrences"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final ArrayList<E> list = new ArrayList<E>();
        Label_0116: {
            Label_0087: {
                Label_0072: {
                    try {
                        if (psiElement == null) {
                            break Label_0072;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final PsiElement psiElement3 = diveIntoParentheses;
                        final boolean b = false;
                        final boolean b2 = PsiTreeUtil.isAncestor(psiElement2, psiElement3, b);
                        if (!b2) {
                            break Label_0072;
                        }
                        break Label_0087;
                    }
                    catch (CancelException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final PsiElement psiElement3 = diveIntoParentheses;
                        final boolean b = false;
                        final boolean b2 = PsiTreeUtil.isAncestor(psiElement2, psiElement3, b);
                        if (!b2) {
                            list.add(diveIntoParentheses);
                        }
                    }
                    catch (CancelException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    if (!(diveIntoParentheses instanceof OCExpression) || OCElementUtil.getRangeInMacroCall(diveIntoParentheses) != null) {
                        break Label_0116;
                    }
                }
                catch (CancelException ex4) {
                    throw b(ex4);
                }
            }
            diveIntoParentheses = (E)OCParenthesesUtils.diveIntoParentheses((OCExpression)diveIntoParentheses);
            try {
                if (psiElement != null) {
                    a(diveIntoParentheses, OCElementUtil.getElementType(diveIntoParentheses), psiElement, list);
                }
            }
            catch (CancelException ex5) {
                throw b(ex5);
            }
        }
        return list;
    }
    
    private static <E extends PsiElement> void a(final E e, final IElementType elementType, PsiElement topmostParenthesized, final List<E> list) {
        Label_0057: {
            Label_0046: {
                Label_0024: {
                    try {
                        if (OCElementUtil.getElementType(topmostParenthesized) != elementType) {
                            break Label_0057;
                        }
                        final PsiElement psiElement = e;
                        final PsiElement psiElement2 = topmostParenthesized;
                        final boolean b = false;
                        final boolean b2 = OCElementUtil.areElementsEquivalent(psiElement, psiElement2, b);
                        if (b2) {
                            break Label_0024;
                        }
                        break Label_0057;
                    }
                    catch (CancelException ex) {
                        throw b(ex);
                    }
                    try {
                        final PsiElement psiElement = e;
                        final PsiElement psiElement2 = topmostParenthesized;
                        final boolean b = false;
                        final boolean b2 = OCElementUtil.areElementsEquivalent(psiElement, psiElement2, b);
                        if (!b2) {
                            break Label_0057;
                        }
                        if (!(topmostParenthesized instanceof OCExpression)) {
                            break Label_0046;
                        }
                    }
                    catch (CancelException ex2) {
                        throw b(ex2);
                    }
                }
                topmostParenthesized = (PsiElement)OCParenthesesUtils.topmostParenthesized((OCExpression)topmostParenthesized);
            }
            list.add((E)topmostParenthesized);
            return;
        }
        final PsiElement[] children = topmostParenthesized.getChildren();
        for (int length = children.length, i = 0; i < length; ++i) {
            a(e, elementType, children[i], (List<PsiElement>)list);
        }
    }
    
    public static boolean isLValue(final OCExpression ocExpression) {
        return OCLValueUtil.isLValue(ocExpression);
    }
    
    public static boolean isAssignmentLHS(final OCExpression ocExpression) {
        return OCLValueUtil.isAssignmentLHS(ocExpression);
    }
    
    public static boolean hasSideEffects(@NotNull final OCElement ocElement) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "hasSideEffects"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final Ref create = Ref.create((Object)false);
        ocElement.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            @Override
            public void visitPrefixExpression(final OCPrefixExpression ocPrefixExpression) {
                if (ocPrefixExpression.getOperationSign() == OCTokenTypes.PLUSPLUS || ocPrefixExpression.getOperationSign() == OCTokenTypes.MINUSMINUS) {
                    create.set((Object)true);
                }
                else {
                    super.visitPrefixExpression(ocPrefixExpression);
                }
            }
            
            @Override
            public void visitPostfixExpression(final OCPostfixExpression ocPostfixExpression) {
                if (ocPostfixExpression.getOperationSign() == OCTokenTypes.PLUSPLUS || ocPostfixExpression.getOperationSign() == OCTokenTypes.MINUSMINUS) {
                    create.set((Object)true);
                }
                else {
                    super.visitPostfixExpression(ocPostfixExpression);
                }
            }
            
            @Override
            public void visitAssignmentExpression(final OCAssignmentExpression ocAssignmentExpression) {
                create.set((Object)true);
            }
            
            @Override
            public void visitCallExpression(final OCCallExpression ocCallExpression) {
                create.set((Object)true);
            }
            
            @Override
            public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                create.set((Object)true);
            }
            
            @Override
            public void visitBlockExpression(final OCBlockExpression ocBlockExpression) {
                create.set((Object)true);
            }
            
            @Override
            public void visitLambdaExpression(final OCLambdaExpression ocLambdaExpression) {
                create.set((Object)true);
            }
            
            @Override
            public void visitStatementExpression(final OCStatementExpression ocStatementExpression) {
                create.set((Object)true);
            }
            
            @Override
            public void visitCppNewExpression(final OCCppNewExpression ocCppNewExpression) {
                create.set((Object)true);
            }
        });
        return (boolean)create.get();
    }
    
    public static <E extends PsiElement> void collectElements(final PsiFile p0, final Editor p1, final int p2, final Class<E> p3, final List<? super E> p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //     6: astore          5
        //     8: aload           5
        //    10: invokeinterface com/intellij/openapi/editor/Document.getCharsSequence:()Ljava/lang/CharSequence;
        //    15: astore          6
        //    17: iload_2        
        //    18: istore          7
        //    20: aload           5
        //    22: invokeinterface com/intellij/openapi/editor/Document.getTextLength:()I
        //    27: istore          8
        //    29: iload_2        
        //    30: iload           8
        //    32: if_icmplt       44
        //    35: iload           8
        //    37: iconst_1       
        //    38: isub           
        //    39: istore          7
        //    41: goto            68
        //    44: aload           6
        //    46: iload_2        
        //    47: invokeinterface java/lang/CharSequence.charAt:(I)C
        //    52: invokestatic    java/lang/Character.isJavaIdentifierPart:(C)Z
        //    55: ifne            68
        //    58: iinc            7, -1
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    67: athrow         
        //    68: iload           7
        //    70: ifge            79
        //    73: iload_2        
        //    74: istore          7
        //    76: goto            142
        //    79: aload           6
        //    81: iload           7
        //    83: invokeinterface java/lang/CharSequence.charAt:(I)C
        //    88: invokestatic    java/lang/Character.isJavaIdentifierPart:(C)Z
        //    91: ifne            142
        //    94: aload           6
        //    96: iload           7
        //    98: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   103: bipush          59
        //   105: if_icmpne       125
        //   108: goto            115
        //   111: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   114: athrow         
        //   115: iinc            7, -1
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   124: athrow         
        //   125: aload           6
        //   127: iload           7
        //   129: invokeinterface java/lang/CharSequence.charAt:(I)C
        //   134: bipush          41
        //   136: if_icmpeq       142
        //   139: iload_2        
        //   140: istore          7
        //   142: aload_0        
        //   143: iload           7
        //   145: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   150: astore          9
        //   152: aload           9
        //   154: aload_3        
        //   155: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.a:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   158: astore          10
        //   160: aload           10
        //   162: ifnull          403
        //   165: aload_3        
        //   166: aload           10
        //   168: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //   171: ifeq            188
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   180: athrow         
        //   181: aload           10
        //   183: astore          11
        //   185: goto            257
        //   188: aload           10
        //   190: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   193: ifeq            403
        //   196: aload_3        
        //   197: ldc             Lcom/jetbrains/cidr/lang/psi/OCExpression;.class
        //   199: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   202: ifeq            246
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   211: athrow         
        //   212: aload           10
        //   214: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   217: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getExpansionExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   222: astore          11
        //   224: aload           11
        //   226: ifnull          246
        //   229: aload           4
        //   231: aload           11
        //   233: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   238: pop            
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   245: athrow         
        //   246: aload           10
        //   248: aload_3        
        //   249: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.a:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   252: astore          10
        //   254: goto            160
        //   257: aload           4
        //   259: aload           11
        //   261: invokeinterface java/util/List.contains:(Ljava/lang/Object;)Z
        //   266: ifne            392
        //   269: aload           11
        //   271: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParenthesizedExpression;
        //   274: ifne            392
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   283: athrow         
        //   284: aload           11
        //   286: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //   289: ifne            392
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   298: athrow         
        //   299: aload           11
        //   301: instanceof      Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   304: ifeq            337
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   313: athrow         
        //   314: aload           11
        //   316: checkcast       Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   319: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   324: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVoid:()Z
        //   327: ifne            392
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   336: athrow         
        //   337: aload           11
        //   339: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   342: ifeq            375
        //   345: goto            352
        //   348: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   351: athrow         
        //   352: aload           11
        //   354: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   357: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getResolvedType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   362: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   365: ifne            392
        //   368: goto            375
        //   371: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   374: athrow         
        //   375: aload           4
        //   377: aload           11
        //   379: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   384: pop            
        //   385: goto            392
        //   388: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   391: athrow         
        //   392: aload           10
        //   394: aload_3        
        //   395: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.a:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   398: astore          10
        //   400: goto            160
        //   403: return         
        //    Signature:
        //  <E:Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;ILjava/lang/Class<TE;>;Ljava/util/List<-TE;>;)V [from metadata: <E::Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Editor;ILjava/lang/Class<TE;>;Ljava/util/List<-TE;>;)V]
        //  
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  44     61     64     68     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  79     108    111    115    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  94     118    121    125    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  160    174    177    181    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  188    205    208    212    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  224    239    242    246    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  257    277    280    284    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  269    292    295    299    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  284    307    310    314    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  299    330    333    337    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  314    345    348    352    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  337    368    371    375    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  352    385    388    392    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0284:
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
    private static <E extends PsiElement> PsiElement a(final PsiElement psiElement, final Class<E> clazz) {
        return PsiTreeUtil.getContextOfType(psiElement, new Class[] { clazz, OCMacroCall.class });
    }
    
    public static boolean isUniqueInScope(@Nullable final OCSymbolKind ocSymbolKind, final String s, @Nullable final PsiElement psiElement, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "isUniqueInScope"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        try {
            if (resolveNameInScope(ocSymbolKind, s, null, psiElement, project) == null) {
                return true;
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    public static OCSymbol resolveNameInScope(@Nullable final OCSymbolKind ocSymbolKind, final String s, @Nullable final String s2, @Nullable final PsiElement psiElement, @NotNull final Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "resolveNameInScope"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final CommonProcessors.FindFirstProcessor<OCSymbol> findFirstProcessor = new CommonProcessors.FindFirstProcessor<OCSymbol>() {
            protected boolean accept(final OCSymbol ocSymbol) {
                return !ocSymbol.isSynthetic() && (s2 == null || !(ocSymbol instanceof OCClassSymbol) || s2.equals(((OCClassSymbol)ocSymbol).getCategoryName())) && (ocSymbolKind == null || OCResolveUtil.isDuplicate(ocSymbolKind, ocSymbol.getKind()));
            }
        };
        Label_0084: {
            try {
                if (psiElement == null) {
                    OCGlobalProjectSymbolsCache.processTopLevelSymbols(project, (Processor<OCSymbol>)findFirstProcessor, s);
                    break Label_0084;
                }
            }
            catch (CancelException ex2) {
                throw b(ex2);
            }
            OCResolveUtil.processSymbols(s, psiElement, (Processor<OCSymbol>)findFirstProcessor);
            try {
                if (findFirstProcessor.isFound()) {
                    return (OCSymbol)findFirstProcessor.getFoundValue();
                }
            }
            catch (CancelException ex3) {
                throw b(ex3);
            }
        }
        final OCBlockStatement ocBlockStatement = (OCBlockStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCBlockStatement.class, false, new Class[] { OCBlockExpression.class, OCLambdaExpression.class });
        try {
            if (ocBlockStatement == null) {
                return null;
            }
        }
        catch (CancelException ex4) {
            throw b(ex4);
        }
        try {
            ocBlockStatement.accept((PsiElementVisitor)new OCVisitor() {
                @Override
                public void visitOCElement(final OCElement ocElement) {
                    ocElement.acceptChildren((PsiElementVisitor)this);
                }
                
                @Override
                public void visitDeclarator(final OCDeclarator ocDeclarator) {
                    try {
                        if (s.equals(ocDeclarator.getName())) {
                            throw new CancelException(ocDeclarator);
                        }
                    }
                    catch (CancelException ex) {
                        throw b(ex);
                    }
                }
                
                private static CancelException b(final CancelException ex) {
                    return ex;
                }
            });
        }
        catch (CancelException ex5) {
            return ex5.getDeclarator().getSymbol();
        }
        return null;
    }
    
    public static boolean showCallableInEditorAndSelectBody(@NotNull final PsiFile psiFile, @NotNull final Segment segment, @NotNull final Condition<OCBlockStatement> condition) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "showCallableInEditorAndSelectBody"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        try {
            if (segment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "segment", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "showCallableInEditorAndSelectBody"));
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        try {
            if (condition == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "condition", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "showCallableInEditorAndSelectBody"));
            }
        }
        catch (CancelException ex3) {
            throw b(ex3);
        }
        final OCCodeInsightUtil.1MyVisitor 1MyVisitor = new OCCodeInsightUtil.1MyVisitor(TextRange.create(segment), condition, psiFile);
        psiFile.accept((PsiElementVisitor)1MyVisitor);
        return 1MyVisitor.found;
    }
    
    public static void selectBody(final Editor editor, final OCBlockStatement ocBlockStatement) {
        try {
            if (ocBlockStatement == null) {
                return;
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        ASTNode astNode = ocBlockStatement.getNode().getFirstChildNode().getTreeNext();
        ASTNode astNode2 = ocBlockStatement.getNode().getLastChildNode().getTreePrev();
        while (OCTokenTypes.WHITESPACES.contains(astNode.getElementType())) {
            astNode = astNode.getTreeNext();
        }
        while (OCTokenTypes.WHITESPACES.contains(astNode2.getElementType())) {
            astNode2 = astNode2.getTreePrev();
        }
        try {
            if (astNode != ocBlockStatement.getNode().getLastChildNode()) {
                selectRange(editor, astNode.getStartOffset(), astNode2.getTextRange().getEndOffset());
                return;
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        selectRange(editor, ocBlockStatement.getTextOffset() + 1, ocBlockStatement.getTextOffset() + 1);
    }
    
    public static void selectElement(final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                return;
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final Editor openInEditor = EditorHelper.openInEditor(psiElement);
        try {
            if (openInEditor != null) {
                selectRange(openInEditor, psiElement.getTextRange().getStartOffset(), psiElement.getTextRange().getEndOffset());
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
    }
    
    public static void selectRange(final Editor editor, final int n, final int n2) {
        editor.getCaretModel().moveToOffset(n);
        editor.getSelectionModel().setSelection(n, n2);
        editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
    }
    
    public static String getClassNameWithCategory(final String s, final String s2) {
        try {
            if (s2 == null) {
                return s;
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder(s.length() + s2.length() + 3);
        StringBuilder append = null;
        String s3 = null;
        Label_0056: {
            try {
                append = sb.append(s).append(" + ");
                if (s2.isEmpty()) {
                    s3 = "()";
                    break Label_0056;
                }
            }
            catch (CancelException ex2) {
                throw b(ex2);
            }
            s3 = s2;
        }
        append.append(s3);
        return sb.toString();
    }
    
    @Nullable
    public static OCClassDeclaration getPrivateCategory(final OCClassDeclarationBase ocClassDeclarationBase) {
        for (final PsiElement psiElement : ocClassDeclarationBase.getContainingFile().getChildren()) {
            Label_0090: {
                if (psiElement instanceof OCClassDeclaration) {
                    final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement;
                    try {
                        if (!ocClassDeclarationBase.getName().equals(ocClassDeclaration.getName())) {
                            break Label_0090;
                        }
                        final String s = "";
                        final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                        final String s2 = ocClassDeclaration2.getCategory();
                        final boolean b = s.equals(s2);
                        if (b) {
                            return ocClassDeclaration;
                        }
                        break Label_0090;
                    }
                    catch (CancelException ex) {
                        throw b(ex);
                    }
                    try {
                        final String s = "";
                        final OCClassDeclaration ocClassDeclaration2 = ocClassDeclaration;
                        final String s2 = ocClassDeclaration2.getCategory();
                        final boolean b = s.equals(s2);
                        if (b) {
                            return ocClassDeclaration;
                        }
                    }
                    catch (CancelException ex2) {
                        throw b(ex2);
                    }
                }
            }
        }
        return null;
    }
    
    @NotNull
    public static Pair<MemberBeginEndSearchResult, OCType> getReturnTypeOfBeginEndPair(final OCExpression p0, @NotNull final OCStructType p1, @NotNull final OCFile p2) {
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
        //    18: ldc             "structType"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getReturnTypeOfBeginEndPair"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "file"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getReturnTypeOfBeginEndPair"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    87: athrow         
        //    88: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    91: dup            
        //    92: aload_2        
        //    93: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    96: astore_3       
        //    97: aload_0        
        //    98: aload_1        
        //    99: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getCVQualifiers:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/CVQualifiers;
        //   102: astore          4
        //   104: aload_1        
        //   105: aload           4
        //   107: aload_2        
        //   108: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   113: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.cloneWithAddedCVQualifiers:(Lcom/jetbrains/cidr/lang/types/CVQualifiers;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   116: astore          5
        //   118: aload_1        
        //   119: ldc             "begin"
        //   121: aload_3        
        //   122: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.collectMethods:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/Collection;
        //   125: astore          6
        //   127: aload           6
        //   129: new             Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //   132: dup            
        //   133: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   136: aconst_null    
        //   137: invokespecial   com/jetbrains/cidr/lang/resolve/OCArgumentsList.<init>:(Ljava/util/List;Ljava/util/List;)V
        //   140: aload           5
        //   142: aconst_null    
        //   143: aconst_null    
        //   144: iconst_1       
        //   145: iconst_1       
        //   146: iconst_1       
        //   147: iconst_1       
        //   148: iconst_0       
        //   149: aload_3        
        //   150: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.resolveOverloads:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;ZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   153: astore          7
        //   155: aload_1        
        //   156: ldc             "end"
        //   158: aload_3        
        //   159: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.collectMethods:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/Collection;
        //   162: astore          8
        //   164: aload           8
        //   166: new             Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;
        //   169: dup            
        //   170: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   173: aconst_null    
        //   174: invokespecial   com/jetbrains/cidr/lang/resolve/OCArgumentsList.<init>:(Ljava/util/List;Ljava/util/List;)V
        //   177: aload           5
        //   179: aconst_null    
        //   180: aconst_null    
        //   181: iconst_1       
        //   182: iconst_1       
        //   183: iconst_1       
        //   184: iconst_1       
        //   185: iconst_0       
        //   186: aload_3        
        //   187: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil.resolveOverloads:(Ljava/util/Collection;Lcom/jetbrains/cidr/lang/resolve/OCArgumentsList;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/resolve/OCExprValueCategory;Lcom/jetbrains/cidr/lang/resolve/references/OCOperatorReference$OperatorPlacement;ZZZZZLcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   190: astore          9
        //   192: aload           7
        //   194: ifnonnull       262
        //   197: aload           9
        //   199: ifnonnull       262
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   208: athrow         
        //   209: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult.NONE:Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult;
        //   212: aconst_null    
        //   213: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   216: dup            
        //   217: ifnonnull       261
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   226: athrow         
        //   227: new             Ljava/lang/IllegalStateException;
        //   230: dup            
        //   231: ldc             "@NotNull method %s.%s must not return null"
        //   233: ldc             2
        //   235: anewarray       Ljava/lang/Object;
        //   238: dup            
        //   239: ldc             0
        //   241: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //   243: aastore        
        //   244: dup            
        //   245: ldc             1
        //   247: ldc             "getReturnTypeOfBeginEndPair"
        //   249: aastore        
        //   250: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   253: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   256: athrow         
        //   257: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   260: athrow         
        //   261: areturn        
        //   262: aload           7
        //   264: ifnull          279
        //   267: aload           9
        //   269: ifnonnull       332
        //   272: goto            279
        //   275: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   278: athrow         
        //   279: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult.INVALID:Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult;
        //   282: aconst_null    
        //   283: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   286: dup            
        //   287: ifnonnull       331
        //   290: goto            297
        //   293: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   296: athrow         
        //   297: new             Ljava/lang/IllegalStateException;
        //   300: dup            
        //   301: ldc             "@NotNull method %s.%s must not return null"
        //   303: ldc             2
        //   305: anewarray       Ljava/lang/Object;
        //   308: dup            
        //   309: ldc             0
        //   311: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //   313: aastore        
        //   314: dup            
        //   315: ldc             1
        //   317: ldc             "getReturnTypeOfBeginEndPair"
        //   319: aastore        
        //   320: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   323: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   326: athrow         
        //   327: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   330: athrow         
        //   331: areturn        
        //   332: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil.$assertionsDisabled:Z
        //   335: ifne            365
        //   338: aload           7
        //   340: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   343: ifne            365
        //   346: goto            353
        //   349: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   352: athrow         
        //   353: new             Ljava/lang/AssertionError;
        //   356: dup            
        //   357: invokespecial   java/lang/AssertionError.<init>:()V
        //   360: athrow         
        //   361: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   364: athrow         
        //   365: aload           7
        //   367: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   372: aload_2        
        //   373: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   376: astore          10
        //   378: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil.$assertionsDisabled:Z
        //   381: ifne            411
        //   384: aload           9
        //   386: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   389: ifne            411
        //   392: goto            399
        //   395: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   398: athrow         
        //   399: new             Ljava/lang/AssertionError;
        //   402: dup            
        //   403: invokespecial   java/lang/AssertionError.<init>:()V
        //   406: athrow         
        //   407: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   410: athrow         
        //   411: aload           7
        //   413: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   418: aload_2        
        //   419: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   422: astore          11
        //   424: aload           11
        //   426: aload           10
        //   428: aload_2        
        //   429: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   432: ifeq            489
        //   435: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult.OK:Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult;
        //   438: aload           10
        //   440: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   443: dup            
        //   444: ifnonnull       488
        //   447: goto            454
        //   450: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   453: athrow         
        //   454: new             Ljava/lang/IllegalStateException;
        //   457: dup            
        //   458: ldc             "@NotNull method %s.%s must not return null"
        //   460: ldc             2
        //   462: anewarray       Ljava/lang/Object;
        //   465: dup            
        //   466: ldc             0
        //   468: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //   470: aastore        
        //   471: dup            
        //   472: ldc             1
        //   474: ldc             "getReturnTypeOfBeginEndPair"
        //   476: aastore        
        //   477: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   480: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   483: athrow         
        //   484: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   487: athrow         
        //   488: areturn        
        //   489: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult.NONE:Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult;
        //   492: aconst_null    
        //   493: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   496: dup            
        //   497: ifnonnull       534
        //   500: new             Ljava/lang/IllegalStateException;
        //   503: dup            
        //   504: ldc             "@NotNull method %s.%s must not return null"
        //   506: ldc             2
        //   508: anewarray       Ljava/lang/Object;
        //   511: dup            
        //   512: ldc             0
        //   514: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //   516: aastore        
        //   517: dup            
        //   518: ldc             1
        //   520: ldc             "getReturnTypeOfBeginEndPair"
        //   522: aastore        
        //   523: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   526: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   529: athrow         
        //   530: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   533: athrow         
        //   534: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/jetbrains/cidr/lang/psi/OCFile;)Lcom/intellij/openapi/util/Pair<Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult;Lcom/jetbrains/cidr/lang/types/OCType;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  44     84     84     88     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  192    202    205    209    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  197    220    223    227    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  209    257    257    261    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  262    272    275    279    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  267    290    293    297    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  279    327    327    331    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  332    346    349    353    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  338    361    361    365    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  378    392    395    399    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  384    407    407    411    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  424    447    450    454    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  435    484    484    488    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  489    530    530    534    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0209:
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
    
    public static boolean isCodeInsightAvailable(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "isCodeInsightAvailable"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final int textLength = psiFile.getTextLength();
        try {
            if (textLength < 0) {
                return false;
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        try {
            if (textLength <= getMaxFileLength()) {
                return true;
            }
        }
        catch (CancelException ex3) {
            throw b(ex3);
        }
        final VirtualFile virtualFile = psiFile.getVirtualFile();
        Label_0105: {
            try {
                if (virtualFile == null) {
                    return false;
                }
                final PsiFile psiFile2 = psiFile;
                final Project project = psiFile2.getProject();
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = isLibraryFile(project, virtualFile2);
                if (b) {
                    break Label_0105;
                }
                return false;
            }
            catch (CancelException ex4) {
                throw b(ex4);
            }
            try {
                final PsiFile psiFile2 = psiFile;
                final Project project = psiFile2.getProject();
                final VirtualFile virtualFile2 = virtualFile;
                final boolean b = isLibraryFile(project, virtualFile2);
                if (b) {
                    return true;
                }
            }
            catch (CancelException ex5) {
                throw b(ex5);
            }
        }
        return false;
    }
    
    protected static boolean isLibraryFile(@NotNull final Project project, @NotNull final VirtualFile virtualFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "isLibraryFile"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "isLibraryFile"));
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        return ProjectScope.getLibrariesScope(project).contains(virtualFile);
    }
    
    public static boolean isCodeInsightAvailable(final int p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_0        
        //     1: iflt            37
        //     4: iload_1        
        //     5: ifne            29
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    14: athrow         
        //    15: iload_0        
        //    16: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getMaxFileLength:()I
        //    19: if_icmpgt       37
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    28: athrow         
        //    29: iconst_1       
        //    30: goto            38
        //    33: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    36: athrow         
        //    37: iconst_0       
        //    38: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      8      11     15     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  4      22     25     29     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  15     33     33     37     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    
    public static int getMaxFileLength() {
        return a().asInteger();
    }
    
    public static void setMaxFileLength(final int value) {
        try {
            if (value > 0) {
                a().setValue(value);
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
    }
    
    public static void resetMaxFileLength() {
        a().resetToDefault();
    }
    
    @NotNull
    private static RegistryValue a() {
        RegistryValue value;
        try {
            value = Registry.get("cidr.max.intellisense.file.length");
            if (value == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getMaxFileLengthRegistryKey"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        return value;
    }
    
    public static Pair<List<PsiElement>, OCSymbolKind> getScopeAndKind(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_1       
        //     2: aconst_null    
        //     3: astore_3       
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    10: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    15: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;
        //    18: ifeq            30
        //    21: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_VALUE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    24: astore_1       
        //    25: aconst_null    
        //    26: astore_2       
        //    27: goto            670
        //    30: aload_0        
        //    31: iconst_4       
        //    32: anewarray       Ljava/lang/Class;
        //    35: dup            
        //    36: iconst_0       
        //    37: ldc             Lcom/jetbrains/cidr/lang/psi/OCLocalScopeable;.class
        //    39: aastore        
        //    40: dup            
        //    41: iconst_1       
        //    42: ldc             Lcom/jetbrains/cidr/lang/psi/OCEnum;.class
        //    44: aastore        
        //    45: dup            
        //    46: iconst_2       
        //    47: ldc             Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;.class
        //    49: aastore        
        //    50: dup            
        //    51: iconst_3       
        //    52: ldc             Lcom/jetbrains/cidr/lang/psi/OCLambdaIntroducer;.class
        //    54: aastore        
        //    55: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    58: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    61: astore_2       
        //    62: aload_2        
        //    63: instanceof      Lcom/jetbrains/cidr/lang/psi/OCEnum;
        //    66: ifeq            112
        //    69: aload_2        
        //    70: checkcast       Lcom/jetbrains/cidr/lang/psi/OCEnum;
        //    73: invokeinterface com/jetbrains/cidr/lang/psi/OCEnum.isEnumClass:()Z
        //    78: ifne            112
        //    81: goto            88
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    87: athrow         
        //    88: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.ENUM_CONST:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    91: astore_1       
        //    92: aload_0        
        //    93: iconst_1       
        //    94: anewarray       Ljava/lang/Class;
        //    97: dup            
        //    98: iconst_0       
        //    99: ldc             Lcom/jetbrains/cidr/lang/psi/OCLocalScopeable;.class
        //   101: aastore        
        //   102: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   105: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   108: astore_2       
        //   109: goto            670
        //   112: aload_2        
        //   113: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStructLike;
        //   116: ifeq            176
        //   119: aload_0        
        //   120: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   123: ifeq            169
        //   126: goto            133
        //   129: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   132: athrow         
        //   133: aload_0        
        //   134: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   137: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   142: ifnull          162
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   151: athrow         
        //   152: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_DECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   155: goto            165
        //   158: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   161: athrow         
        //   162: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.FUNCTION_PREDECLARATION:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   165: astore_1       
        //   166: goto            670
        //   169: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   172: astore_1       
        //   173: goto            670
        //   176: aload_2        
        //   177: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTemplateParameterList;
        //   180: ifeq            238
        //   183: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TEMPLATE_TYPE_PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   186: astore_1       
        //   187: aload_0        
        //   188: iconst_1       
        //   189: anewarray       Ljava/lang/Class;
        //   192: dup            
        //   193: iconst_0       
        //   194: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclaration;.class
        //   196: aastore        
        //   197: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   200: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   203: astore_2       
        //   204: aload_2        
        //   205: ifnull          670
        //   208: new             Ljava/util/ArrayList;
        //   211: dup            
        //   212: invokespecial   java/util/ArrayList.<init>:()V
        //   215: astore_3       
        //   216: aload_3        
        //   217: aload_2        
        //   218: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   223: pop            
        //   224: aload_3        
        //   225: aload_2        
        //   226: invokestatic    com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade.getCommentScope:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   229: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   234: pop            
        //   235: goto            670
        //   238: aload_2        
        //   239: instanceof      Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //   242: ifeq            579
        //   245: aload_2        
        //   246: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   251: astore          4
        //   253: aload           4
        //   255: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCatchSection;
        //   258: ifeq            279
        //   261: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.CATCH_EXCEPTION_VARIABLE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   264: astore_1       
        //   265: aload           4
        //   267: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCatchSection;
        //   270: invokeinterface com/jetbrains/cidr/lang/psi/OCCatchSection.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   275: astore_2       
        //   276: goto            576
        //   279: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   282: astore_1       
        //   283: aload_2        
        //   284: iconst_5       
        //   285: anewarray       Ljava/lang/Class;
        //   288: dup            
        //   289: iconst_0       
        //   290: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   292: aastore        
        //   293: dup            
        //   294: iconst_1       
        //   295: ldc             Lcom/jetbrains/cidr/lang/psi/OCParameterList;.class
        //   297: aastore        
        //   298: dup            
        //   299: iconst_2       
        //   300: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;.class
        //   302: aastore        
        //   303: dup            
        //   304: iconst_3       
        //   305: ldc             Lcom/jetbrains/cidr/lang/psi/OCDeclarator;.class
        //   307: aastore        
        //   308: dup            
        //   309: iconst_4       
        //   310: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //   312: aastore        
        //   313: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   316: astore          5
        //   318: aload           5
        //   320: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   323: ifeq            355
        //   326: aload           5
        //   328: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   333: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   336: ifeq            355
        //   339: goto            346
        //   342: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   345: athrow         
        //   346: aload           5
        //   348: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   353: astore          5
        //   355: aload           5
        //   357: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   360: ifeq            576
        //   363: new             Ljava/util/ArrayList;
        //   366: dup            
        //   367: invokespecial   java/util/ArrayList.<init>:()V
        //   370: astore_3       
        //   371: aload           5
        //   373: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   376: ifeq            437
        //   379: aload           5
        //   381: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   384: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getDeclarator:()Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   389: ldc             Lcom/jetbrains/cidr/lang/psi/OCConstructorInitializationList;.class
        //   391: invokestatic    com/intellij/psi/util/PsiTreeUtil.getChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   394: ifnonnull       421
        //   397: goto            404
        //   400: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   403: athrow         
        //   404: aload           5
        //   406: ldc             Lcom/jetbrains/cidr/lang/psi/OCCatchSection;.class
        //   408: invokestatic    com/intellij/psi/util/PsiTreeUtil.getChildOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   411: ifnull          437
        //   414: goto            421
        //   417: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   420: athrow         
        //   421: aload_3        
        //   422: aload           5
        //   424: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   429: pop            
        //   430: goto            560
        //   433: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   436: athrow         
        //   437: aload           5
        //   439: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   442: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   447: astore          6
        //   449: aload           5
        //   451: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   454: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getNoexceptSpecifier:()Lcom/jetbrains/cidr/lang/psi/OCNoexceptSpecifier;
        //   459: astore          7
        //   461: aload           5
        //   463: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   466: ifeq            484
        //   469: aload           5
        //   471: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   474: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getTrailingReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   479: astore          8
        //   481: goto            496
        //   484: aload           5
        //   486: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   489: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getReturnTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   494: astore          8
        //   496: aload_3        
        //   497: aload           6
        //   499: ifnull          511
        //   502: aload           6
        //   504: goto            512
        //   507: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   510: athrow         
        //   511: aload_2        
        //   512: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   517: pop            
        //   518: aload           7
        //   520: ifnull          539
        //   523: aload_3        
        //   524: aload           7
        //   526: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   531: pop            
        //   532: goto            539
        //   535: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   538: athrow         
        //   539: aload           8
        //   541: ifnull          560
        //   544: aload_3        
        //   545: aload           8
        //   547: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   552: pop            
        //   553: goto            560
        //   556: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   559: athrow         
        //   560: aload           5
        //   562: invokestatic    com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade.getCommentScope:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   565: astore          6
        //   567: aload_3        
        //   568: aload           6
        //   570: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   575: pop            
        //   576: goto            670
        //   579: aload_2        
        //   580: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   583: ifeq            648
        //   586: new             Ljava/util/ArrayList;
        //   589: dup            
        //   590: invokespecial   java/util/ArrayList.<init>:()V
        //   593: astore_3       
        //   594: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   597: astore_1       
        //   598: aload_2        
        //   599: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   602: invokeinterface com/jetbrains/cidr/lang/psi/OCMethod.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   607: astore          4
        //   609: aload           4
        //   611: ifnull          630
        //   614: aload_3        
        //   615: aload           4
        //   617: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   622: pop            
        //   623: goto            630
        //   626: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   629: athrow         
        //   630: aload_2        
        //   631: invokestatic    com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenFacade.getCommentScope:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   634: astore          5
        //   636: aload_3        
        //   637: aload           5
        //   639: invokeinterface java/util/List.addAll:(Ljava/util/Collection;)Z
        //   644: pop            
        //   645: goto            670
        //   648: aload_2        
        //   649: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaIntroducer;
        //   652: ifeq            670
        //   655: aload_2        
        //   656: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   661: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //   664: invokeinterface com/jetbrains/cidr/lang/psi/OCLambdaExpression.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   669: astore_2       
        //   670: aload_3        
        //   671: ifnonnull       700
        //   674: aload_2        
        //   675: ifnull          696
        //   678: goto            685
        //   681: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   684: athrow         
        //   685: aload_2        
        //   686: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   689: goto            699
        //   692: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   695: athrow         
        //   696: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   699: astore_3       
        //   700: aload_3        
        //   701: aload_1        
        //   702: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   705: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/Pair<Ljava/util/List<Lcom/intellij/psi/PsiElement;>;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  62     81     84     88     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  112    126    129    133    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  119    145    148    152    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  133    158    158    162    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  318    339    342    346    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  371    397    400    404    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  379    414    417    421    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  404    433    433    437    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  496    507    507    511    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  512    532    535    539    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  539    553    556    560    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  609    623    626    630    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  670    678    681    685    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  674    692    692    696    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0133:
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
    
    public static boolean isNonStaticFieldAccess(@NotNull final OCDeclaratorSymbol p0, @NotNull final OCStructSymbol p1, final OCExpression p2) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isNonStaticFieldAccess"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
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
        //    62: ldc             "parent"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isNonStaticFieldAccess"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isFriendOrStatic:()Z
        //    92: ifne            133
        //    95: aload_0        
        //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.isMutable:()Z
        //    99: ifne            133
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   108: athrow         
        //   109: aload_0        
        //   110: aload_1        
        //   111: aload_2        
        //   112: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isMemberAccess:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;Lcom/jetbrains/cidr/lang/psi/OCExpression;)Z
        //   115: ifeq            133
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   124: athrow         
        //   125: iconst_1       
        //   126: goto            134
        //   129: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   132: athrow         
        //   133: iconst_0       
        //   134: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  44     84     84     88     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  88     102    105    109    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  95     118    121    125    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  109    129    129    133    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    public static boolean isMemberAccess(@NotNull final OCSymbolWithQualifiedName p0, @NotNull final OCStructSymbol p1, final OCExpression p2) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isMemberAccess"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
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
        //    62: ldc             "parent"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isMemberAccess"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    87: athrow         
        //    88: aload_0        
        //    89: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //    92: ifne            118
        //    95: aload_0        
        //    96: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    99: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT_FIELD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   102: if_acmpeq       118
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   111: athrow         
        //   112: iconst_0       
        //   113: ireturn        
        //   114: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   117: athrow         
        //   118: aload_0        
        //   119: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   122: astore_3       
        //   123: aload_3        
        //   124: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   127: ifeq            148
        //   130: aload_3        
        //   131: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   134: aload_1        
        //   135: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isAncestor:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;)Z
        //   138: ifne            154
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   147: athrow         
        //   148: iconst_0       
        //   149: ireturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   153: athrow         
        //   154: aload_2        
        //   155: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   158: ifne            224
        //   161: aload_2        
        //   162: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   165: ifeq            232
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   174: athrow         
        //   175: aload_2        
        //   176: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   179: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   184: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   187: ifeq            232
        //   190: goto            197
        //   193: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   196: athrow         
        //   197: aload_2        
        //   198: checkcast       Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //   201: invokeinterface com/jetbrains/cidr/lang/psi/OCQualifiedExpression.getQualifier:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   206: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //   209: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.isCppThis:()Z
        //   214: ifeq            232
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   223: athrow         
        //   224: iconst_1       
        //   225: goto            233
        //   228: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   231: athrow         
        //   232: iconst_0       
        //   233: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  44     84     84     88     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  88     105    108    112    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  95     114    114    118    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  123    141    144    148    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  130    150    150    154    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  154    168    171    175    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  161    190    193    197    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  175    217    220    224    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  197    228    228    232    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0175:
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
    public static CVQualifiers getOuterFunctionCVQualifiers(@NotNull final OCDeclaratorSymbol ocDeclaratorSymbol, final OCExpression ocExpression, @Nullable final Ref<OCFunctionSymbol> ref) {
        try {
            if (ocDeclaratorSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getOuterFunctionCVQualifiers"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getParentOfType((PsiElement)ocExpression, (Class)OCFunctionDefinition.class);
        OCFunctionSymbol symbol = null;
        Label_0072: {
            try {
                if (ocFunctionDefinition != null) {
                    symbol = ocFunctionDefinition.getSymbol();
                    break Label_0072;
                }
            }
            catch (CancelException ex2) {
                throw b(ex2);
            }
            symbol = null;
        }
        final OCFunctionSymbol ocFunctionSymbol = symbol;
        try {
            if (ref != null) {
                ref.set((Object)ocFunctionSymbol);
            }
        }
        catch (CancelException ex3) {
            throw b(ex3);
        }
        if (ocFunctionSymbol != null) {
            final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ocFunctionSymbol.getResolvedOwner();
            Label_0131: {
                try {
                    if (!(resolvedOwner instanceof OCStructSymbol)) {
                        return null;
                    }
                    final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)resolvedOwner;
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final OCExpression ocExpression2 = ocExpression;
                    final boolean b = isNonStaticFieldAccess(ocDeclaratorSymbol2, ocStructSymbol2, ocExpression2);
                    if (b) {
                        break Label_0131;
                    }
                    return null;
                }
                catch (CancelException ex4) {
                    throw b(ex4);
                }
                try {
                    final OCDeclaratorSymbol ocDeclaratorSymbol2 = ocDeclaratorSymbol;
                    final OCStructSymbol ocStructSymbol = (OCStructSymbol)resolvedOwner;
                    final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                    final OCExpression ocExpression2 = ocExpression;
                    final boolean b = isNonStaticFieldAccess(ocDeclaratorSymbol2, ocStructSymbol2, ocExpression2);
                    if (b) {
                        return ocFunctionSymbol.getType().getCVQualifiers();
                    }
                }
                catch (CancelException ex5) {
                    throw b(ex5);
                }
            }
        }
        return null;
    }
    
    @NotNull
    public static CVQualifiers getCVQualifiers(final OCExpression ocExpression, final OCType ocType) {
        final boolean b = ocType instanceof OCStructType;
        CVQualifiers cvQualifiers = ocType.getTerminalType().getCVQualifiers();
        CVQualifiers cvQualifiers3 = null;
        Label_0082: {
            try {
                if (!b || !(ocExpression instanceof OCReferenceExpression)) {
                    break Label_0082;
                }
            }
            catch (CancelException ex) {
                throw b(ex);
            }
            final OCSymbol resolveToSymbol = ((OCReferenceExpression)ocExpression).resolveToSymbol();
            CVQualifiers outerFunctionCVQualifiers = null;
            Label_0068: {
                try {
                    if (resolveToSymbol instanceof OCDeclaratorSymbol) {
                        outerFunctionCVQualifiers = getOuterFunctionCVQualifiers((OCDeclaratorSymbol)resolveToSymbol, ocExpression, null);
                        break Label_0068;
                    }
                }
                catch (CancelException ex2) {
                    throw b(ex2);
                }
                outerFunctionCVQualifiers = null;
            }
            final CVQualifiers cvQualifiers2 = outerFunctionCVQualifiers;
            if (cvQualifiers2 != null) {
                cvQualifiers = cvQualifiers.or(cvQualifiers2);
            }
            try {
                cvQualifiers3 = cvQualifiers;
                if (cvQualifiers3 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getCVQualifiers"));
                }
            }
            catch (CancelException ex3) {
                throw b(ex3);
            }
        }
        return cvQualifiers3;
    }
    
    public static String getPrettyNameFromClassName(final Class clazz) {
        String s = clazz.getSimpleName();
        if (clazz.isAnonymousClass()) {
            s = clazz.getSuperclass().getSimpleName();
        }
        final StringBuilder sb = new StringBuilder();
        for (final String s2 : StringUtil.findMatches(StringUtil.trimEnd(StringUtil.trimEnd(StringUtil.trimStart(s, "OC"), "Inspection"), "IntentionAction"), Pattern.compile("([A-Z][a-z]*)"))) {
            try {
                if (sb.length() > 0) {
                    sb.append(' ').append(StringUtil.decapitalize(s2));
                    continue;
                }
            }
            catch (CancelException ex) {
                throw b(ex);
            }
            sb.append(s2);
        }
        return sb.toString();
    }
    
    public static boolean isLikeNull(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          60
        //     4: ldc             "nil"
        //     6: aload_0        
        //     7: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    10: ifne            52
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    19: athrow         
        //    20: ldc             "NULL"
        //    22: aload_0        
        //    23: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    26: ifne            52
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    35: athrow         
        //    36: ldc             "nullptr"
        //    38: aload_0        
        //    39: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    42: ifeq            60
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    51: athrow         
        //    52: iconst_1       
        //    53: goto            61
        //    56: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    59: athrow         
        //    60: iconst_0       
        //    61: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      13     16     20     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  4      29     32     36     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  20     45     48     52     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  36     56     56     60     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    public static boolean isInitializerListType(final OCType ocType, @Nullable final PsiFile psiFile) {
        try {
            if (!OCCompilerFeatures.supportsInitializerLists(psiFile) || !(ocType instanceof OCStructType)) {
                return false;
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final OCQualifiedName resolvedQualifiedName = ((OCStructType)ocType).getSymbol().getResolvedQualifiedName(false);
        Label_0056: {
            try {
                if (resolvedQualifiedName == null) {
                    return false;
                }
                final OCQualifiedName ocQualifiedName = resolvedQualifiedName;
                final String s = ocQualifiedName.toString();
                final String s2 = "::std::initializer_list";
                final boolean b = s.equals(s2);
                if (b) {
                    break Label_0056;
                }
                return false;
            }
            catch (CancelException ex2) {
                throw b(ex2);
            }
            try {
                final OCQualifiedName ocQualifiedName = resolvedQualifiedName;
                final String s = ocQualifiedName.toString();
                final String s2 = "::std::initializer_list";
                final boolean b = s.equals(s2);
                if (b) {
                    return true;
                }
            }
            catch (CancelException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    public static boolean isSimpleDeclaration(final String s, final String s2) {
        Label_0024: {
            try {
                if ("<unnamed>".equals(s2)) {
                    break Label_0024;
                }
                final String s3 = s;
                final String s4 = s2;
                final boolean b = s3.endsWith(s4);
                if (b) {
                    break Label_0024;
                }
                return false;
            }
            catch (CancelException ex) {
                throw b(ex);
            }
            try {
                final String s3 = s;
                final String s4 = s2;
                final boolean b = s3.endsWith(s4);
                if (b) {
                    return true;
                }
            }
            catch (CancelException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public static OCType getCollectionElementType(@NotNull final OCExpression p0, final OCType p1) {
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
        //    18: ldc             "expr"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCCodeInsightUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getCollectionElementType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    50: astore_2       
        //    51: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    54: dup            
        //    55: aload_2        
        //    56: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //    59: astore_3       
        //    60: aload_1        
        //    61: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    64: ifeq            75
        //    67: aload_1        
        //    68: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //    71: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //    74: astore_1       
        //    75: aconst_null    
        //    76: astore          4
        //    78: aload_1        
        //    79: instanceof      Lcom/jetbrains/cidr/lang/types/OCArrayType;
        //    82: ifeq            91
        //    85: aload_1        
        //    86: astore          4
        //    88: goto            330
        //    91: aload_1        
        //    92: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //    95: ifeq            114
        //    98: aload_0        
        //    99: aload_1        
        //   100: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   103: aload_2        
        //   104: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getReturnTypeOfBeginEndPair:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCStructType;Lcom/jetbrains/cidr/lang/psi/OCFile;)Lcom/intellij/openapi/util/Pair;
        //   107: goto            121
        //   110: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   113: athrow         
        //   114: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult.NONE:Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult;
        //   117: aconst_null    
        //   118: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   121: astore          5
        //   123: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil$5.$SwitchMap$com$jetbrains$cidr$lang$util$OCCodeInsightUtil$MemberBeginEndSearchResult:[I
        //   126: aload           5
        //   128: invokevirtual   com/intellij/openapi/util/Pair.getFirst:()Ljava/lang/Object;
        //   131: checkcast       Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult;
        //   134: invokevirtual   com/jetbrains/cidr/lang/util/OCCodeInsightUtil$MemberBeginEndSearchResult.ordinal:()I
        //   137: iaload         
        //   138: tableswitch {
        //                2: 164
        //                3: 317
        //                4: 320
        //          default: 330
        //        }
        //   164: ldc             "begin"
        //   166: aload_1        
        //   167: aload_0        
        //   168: aload_2        
        //   169: aload_3        
        //   170: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   173: astore          6
        //   175: ldc             "end"
        //   177: aload_1        
        //   178: aload_0        
        //   179: aload_2        
        //   180: aload_3        
        //   181: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.a:(Ljava/lang/String;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   184: astore          7
        //   186: aload           6
        //   188: ifnull          317
        //   191: aload           7
        //   193: ifnull          317
        //   196: goto            203
        //   199: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   202: athrow         
        //   203: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil.$assertionsDisabled:Z
        //   206: ifne            243
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   215: athrow         
        //   216: aload           6
        //   218: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   221: ifne            243
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   230: athrow         
        //   231: new             Ljava/lang/AssertionError;
        //   234: dup            
        //   235: invokespecial   java/lang/AssertionError.<init>:()V
        //   238: athrow         
        //   239: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   242: athrow         
        //   243: getstatic       com/jetbrains/cidr/lang/util/OCCodeInsightUtil.$assertionsDisabled:Z
        //   246: ifne            276
        //   249: aload           7
        //   251: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   254: ifne            276
        //   257: goto            264
        //   260: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   263: athrow         
        //   264: new             Ljava/lang/AssertionError;
        //   267: dup            
        //   268: invokespecial   java/lang/AssertionError.<init>:()V
        //   271: athrow         
        //   272: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.b:(Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;)Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //   275: athrow         
        //   276: aload           6
        //   278: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   283: aload_2        
        //   284: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   287: astore          8
        //   289: aload           7
        //   291: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getEffectiveType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   296: aload_2        
        //   297: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   300: astore          9
        //   302: aload           8
        //   304: aload           9
        //   306: aload_2        
        //   307: invokevirtual   com/jetbrains/cidr/lang/types/OCType.equals:(Ljava/lang/Object;Lcom/intellij/psi/PsiElement;)Z
        //   310: ifeq            317
        //   313: aload           8
        //   315: astore          4
        //   317: goto            330
        //   320: aload           5
        //   322: invokevirtual   com/intellij/openapi/util/Pair.getSecond:()Ljava/lang/Object;
        //   325: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   328: astore          4
        //   330: aload_0        
        //   331: aload           4
        //   333: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.getDereferencedType:(Lcom/jetbrains/cidr/lang/psi/OCExpression;Lcom/jetbrains/cidr/lang/types/OCType;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   336: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                            
        //  -----  -----  -----  -----  ----------------------------------------------------------------
        //  0      40     40     44     Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  91     110    110    114    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  186    196    199    203    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  191    209    212    216    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  203    224    227    231    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  216    239    239    243    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  243    257    260    264    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        //  249    272    272    276    Lcom/jetbrains/cidr/lang/util/OCCodeInsightUtil$CancelException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0203:
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
    private static OCSymbol a(@NotNull final String s, @NotNull final OCType ocType, @NotNull final OCExpression ocExpression, @NotNull final OCFile ocFile, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "methodName", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getGlobalBeginOrEnd"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getGlobalBeginOrEnd"));
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getGlobalBeginOrEnd"));
            }
        }
        catch (CancelException ex3) {
            throw b(ex3);
        }
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getGlobalBeginOrEnd"));
            }
        }
        catch (CancelException ex4) {
            throw b(ex4);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getGlobalBeginOrEnd"));
            }
        }
        catch (CancelException ex5) {
            throw b(ex5);
        }
        final OCQualifiedName interned = OCQualifiedName.interned(s);
        final List filter = ContainerUtil.filter((Collection)new ArrayList(OCSymbolReference.getGlobalReference(interned).resolveToSymbols((PsiFile)ocFile)), ocSymbol -> ocSymbol instanceof OCFunctionSymbol);
        final List<OCType> singletonList = Collections.singletonList(ocType);
        final List<OCExpression> singletonList2 = Collections.singletonList(ocExpression);
        return OCResolveOverloadsUtil.resolveOverloads(OCArgumentDepLookupAccumulator.doArgDepLookup(filter, singletonList, singletonList2, interned, ocResolveContext), new OCArgumentsList<Object>(singletonList, (List<OCTypeOwner>)singletonList2), null, null, null, true, true, true, true, false, ocResolveContext);
    }
    
    @Nullable
    public static OCType getDereferencedType(@NotNull final OCExpression ocExpression, @Nullable final OCType ocType) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expr", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getDereferencedType"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final OCFile containingOCFile = ocExpression.getContainingOCFile();
        try {
            if (ocType instanceof OCPointerType) {
                return ((OCPointerType)ocType).getRefType();
            }
        }
        catch (CancelException ex2) {
            throw b(ex2);
        }
        if (ocType instanceof OCStructType) {
            final OCFunctionSymbol resolveOperator = OCOperatorReference.resolveOperator("*", OCOperatorReference.OperatorPlacement.PREFIX, ocExpression, (OCStructType)ocType);
            try {
                if (resolveOperator != null) {
                    return resolveOperator.getEffectiveType().resolve((PsiFile)containingOCFile);
                }
            }
            catch (CancelException ex3) {
                throw b(ex3);
            }
            return null;
        }
        try {
            if (ocType instanceof OCMagicType) {
                return new OCMagicType();
            }
        }
        catch (CancelException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    public static boolean isUnnamed(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        final OCType type = ocDeclaratorSymbol.getType();
        Label_0029: {
            try {
                if (!(type instanceof OCStructType)) {
                    return false;
                }
                final OCType ocType = type;
                final OCStructType ocStructType = (OCStructType)ocType;
                final boolean b = ocStructType.isUnnamed();
                if (b) {
                    break Label_0029;
                }
                return false;
            }
            catch (CancelException ex) {
                throw b(ex);
            }
            try {
                final OCType ocType = type;
                final OCStructType ocStructType = (OCStructType)ocType;
                final boolean b = ocStructType.isUnnamed();
                if (b) {
                    return true;
                }
            }
            catch (CancelException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public static OCElementType getOppositeOperator(@NotNull final OCElementType ocElementType) {
        try {
            if (ocElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operator", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getOppositeOperator"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        return OCCodeInsightUtil.oppositeOperators.getOrDefault(ocElementType, null);
    }
    
    @Nullable
    public static OCElementType getFlippedOperator(@NotNull final OCElementType ocElementType) {
        try {
            if (ocElementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "operator", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "getFlippedOperator"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        return OCCodeInsightUtil.flippedOperators.getOrDefault(ocElementType, null);
    }
    
    public static boolean insideLoopHeader(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "insideLoopHeader"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCLoopStatement.class);
        if (ocStatement instanceof OCForeachStatement) {
            return PsiTreeUtil.isAncestor((PsiElement)((OCForeachStatement)ocStatement).getVariableDeclaration(), psiElement, false);
        }
        if (ocStatement instanceof OCForStatement) {
            final OCForStatement ocForStatement = (OCForStatement)ocStatement;
            Label_0125: {
                try {
                    if (PsiTreeUtil.isAncestor((PsiElement)ocForStatement.getInitializer(), psiElement, false)) {
                        break Label_0125;
                    }
                    final OCForStatement ocForStatement2 = ocForStatement;
                    final OCDeclarationOrExpression ocDeclarationOrExpression = ocForStatement2.getCondition();
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = false;
                    final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocDeclarationOrExpression, psiElement2, b);
                    if (b2) {
                        break Label_0125;
                    }
                    return false;
                }
                catch (CancelException ex2) {
                    throw b(ex2);
                }
                try {
                    final OCForStatement ocForStatement2 = ocForStatement;
                    final OCDeclarationOrExpression ocDeclarationOrExpression = ocForStatement2.getCondition();
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = false;
                    final boolean b2 = PsiTreeUtil.isAncestor((PsiElement)ocDeclarationOrExpression, psiElement2, b);
                    if (b2) {
                        return true;
                    }
                }
                catch (CancelException ex3) {
                    throw b(ex3);
                }
            }
            return false;
        }
        return ocStatement != null && PsiTreeUtil.isAncestor(((OCForStatement)ocStatement).getCondition(), psiElement, false);
    }
    
    public static boolean insideConditionalHeader(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCCodeInsightUtil", "insideConditionalHeader"));
            }
        }
        catch (CancelException ex) {
            throw b(ex);
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(psiElement, new Class[] { OCIfStatement.class, OCSwitchStatement.class });
        if (ocStatement instanceof OCIfStatement) {
            return PsiTreeUtil.isAncestor((PsiElement)((OCIfStatement)ocStatement).getCondition(), psiElement, false);
        }
        return ocStatement instanceof OCSwitchStatement && PsiTreeUtil.isAncestor((PsiElement)((OCSwitchStatement)ocStatement).getExpression(), psiElement, false);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCCodeInsightUtil.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (CancelException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        OCCodeInsightUtil.oppositeOperators = (Map<OCElementType, OCElementType>)ContainerUtil.newHashMap(new Pair((Object)OCTokenTypes.EQEQ, (Object)OCTokenTypes.EXCLEQ), new Pair[] { new Pair((Object)OCTokenTypes.EXCLEQ, (Object)OCTokenTypes.EQEQ), new Pair((Object)OCTokenTypes.LT, (Object)OCTokenTypes.GTEQ), new Pair((Object)OCTokenTypes.LTEQ, (Object)OCTokenTypes.GT), new Pair((Object)OCTokenTypes.GT, (Object)OCTokenTypes.LTEQ), new Pair((Object)OCTokenTypes.GTEQ, (Object)OCTokenTypes.LT) });
        OCCodeInsightUtil.flippedOperators = (Map<OCElementType, OCElementType>)ContainerUtil.newHashMap(new Pair((Object)OCTokenTypes.ANDAND, (Object)OCTokenTypes.ANDAND), new Pair[] { new Pair((Object)OCTokenTypes.OROR, (Object)OCTokenTypes.OROR), new Pair((Object)OCTokenTypes.EQEQ, (Object)OCTokenTypes.EQEQ), new Pair((Object)OCTokenTypes.EXCLEQ, (Object)OCTokenTypes.EXCLEQ), new Pair((Object)OCTokenTypes.GTEQ, (Object)OCTokenTypes.LTEQ), new Pair((Object)OCTokenTypes.LTEQ, (Object)OCTokenTypes.GTEQ), new Pair((Object)OCTokenTypes.GT, (Object)OCTokenTypes.LT), new Pair((Object)OCTokenTypes.LT, (Object)OCTokenTypes.GT) });
    }
    
    private static CancelException b(final CancelException ex) {
        return ex;
    }
    
    public enum MemberBeginEndSearchResult
    {
        NONE, 
        INVALID, 
        OK;
    }
    
    static class CancelException extends RuntimeException
    {
        private final OCDeclarator myDeclarator;
        
        public CancelException(final OCDeclarator myDeclarator) {
            this.myDeclarator = myDeclarator;
        }
        
        public OCDeclarator getDeclarator() {
            return this.myDeclarator;
        }
        
        @Override
        public Throwable fillInStackTrace() {
            return this;
        }
    }
}
