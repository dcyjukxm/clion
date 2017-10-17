// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve;

import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import java.util.Arrays;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import java.util.Set;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceLikeSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCUsingSymbol;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCImplementation;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCLocalSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCGotoStatement;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.psi.OCParameterList;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import org.jetbrains.annotations.Contract;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.util.OCCommonProcessors;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.jetbrains.cidr.lang.OCBundle;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.util.containers.HashSet;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithSubstitution;
import com.jetbrains.cidr.lang.psi.OCTypeArgumentList;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.Iterator;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCTypeResolveVisitor;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCQualifiedNameWithArguments;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorHelper;
import com.jetbrains.cidr.lang.workspace.OCResolveConfiguration;
import com.jetbrains.cidr.lang.daemon.OCAnnotator;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCLocalScopeable;
import java.util.List;
import com.intellij.openapi.util.Key;

public class OCResolveUtil
{
    public static Key<Boolean> DISABLE_LOCAL_SYMBOL_TABLE;
    private static List<Class<? extends OCLocalScopeable>> stopSet;
    
    public static boolean isEarlierInCode(final OCSymbol p0, final VirtualFile p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //     6: ifnull          46
        //     9: aload_0        
        //    10: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    15: aload_1        
        //    16: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    19: ifeq            46
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aload_0        
        //    30: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //    35: iload_2        
        //    36: if_icmpgt       54
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: iconst_1       
        //    47: goto            55
        //    50: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    53: athrow         
        //    54: iconst_0       
        //    55: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      22     25     29     Ljava/lang/IllegalArgumentException;
        //  9      39     42     46     Ljava/lang/IllegalArgumentException;
        //  29     50     50     54     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    
    public static boolean isEarlierInCodeWithComplexOffset(final OCSymbol p0, final VirtualFile p1, final long p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //     6: ifnull          49
        //     9: aload_0        
        //    10: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    15: aload_1        
        //    16: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    19: ifeq            49
        //    22: goto            29
        //    25: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    28: athrow         
        //    29: aload_0        
        //    30: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getComplexOffset:()J
        //    35: lload_2        
        //    36: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.compare:(JJ)I
        //    39: ifgt            57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      22     25     29     Ljava/lang/IllegalArgumentException;
        //  9      42     45     49     Ljava/lang/IllegalArgumentException;
        //  29     53     53     57     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0029:
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
    
    public static boolean isInSameStructInCode(final OCSymbol ocSymbol, final int n) {
        try {
            if (!(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCSymbolWithQualifiedName parent = ((OCSymbolWithQualifiedName)ocSymbol).getParent();
        Label_0043: {
            try {
                if (!(parent instanceof OCStructSymbol)) {
                    return false;
                }
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = parent;
                final int n2 = ocSymbolWithQualifiedName.getOffset();
                final int n3 = n;
                if (n2 < n3) {
                    break Label_0043;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = parent;
                final int n2 = ocSymbolWithQualifiedName.getOffset();
                final int n3 = n;
                if (n2 < n3) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isInSameStructInCode(final OCSymbol p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //     6: astore_2       
        //     7: aload_0        
        //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    13: ifnull          47
        //    16: aload_2        
        //    17: ifnull          47
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: aload_1        
        //    29: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //    34: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isInSameStructInCode:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;I)Z
        //    37: ifeq            55
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    46: athrow         
        //    47: iconst_1       
        //    48: goto            56
        //    51: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: iconst_0       
        //    56: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      20     23     27     Ljava/lang/IllegalArgumentException;
        //  16     40     43     47     Ljava/lang/IllegalArgumentException;
        //  27     51     51     55     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    private static boolean a(final OCSymbol ocSymbol, @NotNull final PsiFile psiFile, final VirtualFile virtualFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "isInSameObjCClass"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!ocSymbol.isGlobal() || !Comparing.equal((Object)ocSymbol.getContainingFile(), (Object)virtualFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getParentOfType(ocSymbol.locateDefinition(), (Class)OCClassDeclaration.class);
        final OCClassDeclaration ocClassDeclaration2 = (OCClassDeclaration)PsiTreeUtil.getParentOfType(psiFile.findElementAt(n), (Class)OCClassDeclaration.class);
        Label_0125: {
            try {
                if (ocClassDeclaration == null) {
                    return false;
                }
                final OCClassDeclaration ocClassDeclaration3 = ocClassDeclaration;
                final OCClassDeclaration ocClassDeclaration4 = ocClassDeclaration2;
                if (ocClassDeclaration3 == ocClassDeclaration4) {
                    break Label_0125;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final OCClassDeclaration ocClassDeclaration3 = ocClassDeclaration;
                final OCClassDeclaration ocClassDeclaration4 = ocClassDeclaration2;
                if (ocClassDeclaration3 == ocClassDeclaration4) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    public static boolean isEarlierInCode(final OCSymbol p0, @Nullable final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       10
        //     4: iconst_1       
        //     5: ireturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //     9: athrow         
        //    10: aload_1        
        //    11: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    16: astore_2       
        //    17: aload_0        
        //    18: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    23: ifnull          63
        //    26: aload_2        
        //    27: ifnull          63
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    36: athrow         
        //    37: aload_0        
        //    38: aload_2        
        //    39: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    44: aload_1        
        //    45: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //    50: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isEarlierInCode:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/vfs/VirtualFile;I)Z
        //    53: ifeq            71
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      6      6      10     Ljava/lang/IllegalArgumentException;
        //  17     30     33     37     Ljava/lang/IllegalArgumentException;
        //  26     56     59     63     Ljava/lang/IllegalArgumentException;
        //  37     67     67     71     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
    
    public static boolean isEarlierInCode(final OCSymbol p0, final OCSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //     6: astore_2       
        //     7: aload_0        
        //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    13: ifnull          48
        //    16: aload_2        
        //    17: ifnull          48
        //    20: goto            27
        //    23: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    26: athrow         
        //    27: aload_0        
        //    28: aload_2        
        //    29: aload_1        
        //    30: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //    35: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isEarlierInCode:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/vfs/VirtualFile;I)Z
        //    38: ifeq            56
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: iconst_1       
        //    49: goto            57
        //    52: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: iconst_0       
        //    57: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      20     23     27     Ljava/lang/IllegalArgumentException;
        //  16     41     44     48     Ljava/lang/IllegalArgumentException;
        //  27     52     52     56     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    private static boolean a(final int n, final PsiFile psiFile) {
        try {
            if (PsiTreeUtil.getParentOfType(psiFile.findElementAt(n), (Class)OCBlockStatement.class) != null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public static boolean isDisabledSymbol(final OCSymbol ocSymbol, @NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "isDisabledSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0071: {
            try {
                if (!(ocSymbol instanceof OCInstanceVariableSymbol)) {
                    return false;
                }
                final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbol;
                final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                final PsiFile psiFile2 = psiFile;
                final boolean b = ocInstanceVariableSymbol2.isForbiddenClang4ImplicitIvar(psiFile2);
                if (b) {
                    break Label_0071;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbol;
                final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                final PsiFile psiFile2 = psiFile;
                final boolean b = ocInstanceVariableSymbol2.isForbiddenClang4ImplicitIvar(psiFile2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    public static boolean isSymbolAvailable(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolToCheck", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "isSymbolAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (checkAvailability(ocSymbol, psiElement) == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Nullable
    public static String checkAvailability(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "checkAvailability"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final OCResolveConfiguration activeConfiguration = OCInclusionContextUtil.getActiveConfiguration(psiElement);
        if (activeConfiguration != null) {
            final OCAnnotatorHelper[] annotatorHelpers = OCAnnotator.getAnnotatorHelpers();
            for (int length = annotatorHelpers.length, i = 0; i < length; ++i) {
                final String checkAvailability = annotatorHelpers[i].checkAvailability(ocSymbol, activeConfiguration);
                try {
                    if (checkAvailability != null) {
                        return checkAvailability;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        return null;
    }
    
    public static boolean isDependentCode(@NotNull final OCQualifiedName ocQualifiedName, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "isDependentCode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "isDependentCode"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        if (ocQualifiedName instanceof OCQualifiedNameWithArguments) {
            for (final OCTypeArgument ocTypeArgument : ((OCQualifiedNameWithArguments)ocQualifiedName).getArguments()) {
                if (ocTypeArgument instanceof OCType) {
                    final OCType substitute = ocResolveContext.getSubstitution().substitute(((OCType)ocTypeArgument).transformType(new OCTypeResolveVisitor(ocResolveContext)), ocResolveContext);
                    try {
                        if (substitute instanceof OCMagicType) {
                            return true;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
            }
        }
        final OCQualifiedName qualifier = ocQualifiedName.getQualifier();
        Label_0200: {
            try {
                if (qualifier == null) {
                    return false;
                }
                final OCQualifiedName ocQualifiedName2 = qualifier;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = isDependentCode(ocQualifiedName2, ocResolveContext2);
                if (b) {
                    break Label_0200;
                }
                return false;
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                final OCQualifiedName ocQualifiedName2 = qualifier;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = isDependentCode(ocQualifiedName2, ocResolveContext2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return false;
    }
    
    public static boolean isDependentCode(final OCExpression ocExpression, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "isDependentCode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        if (ocExpression instanceof OCReferenceExpression) {
            return isDependentCode(((OCReferenceExpression)ocExpression).getReferenceElement(), ocResolveContext);
        }
        if (ocExpression instanceof OCCallExpression) {
            final OCExpression functionReferenceExpression = ((OCCallExpression)ocExpression).getFunctionReferenceExpression();
            Label_0117: {
                try {
                    if (functionReferenceExpression.getResolvedType() instanceof OCMagicType) {
                        return true;
                    }
                    final OCExpression ocExpression2 = functionReferenceExpression;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final boolean b = isDependentCode(ocExpression2, ocResolveContext2);
                    if (b) {
                        return true;
                    }
                    break Label_0117;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCExpression ocExpression2 = functionReferenceExpression;
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final boolean b = isDependentCode(ocExpression2, ocResolveContext2);
                    if (b) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            for (final OCExpression ocExpression3 : ((OCCallExpression)ocExpression).getArguments()) {
                try {
                    if (ocExpression3.getResolvedType() instanceof OCMagicType) {
                        return true;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
        }
        return false;
    }
    
    public static boolean isDependentCode(@Nullable final OCReferenceElement ocReferenceElement, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "isDependentCode"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCCppNamespaceQualifier namespaceQualifier = null;
        Label_0062: {
            try {
                if (ocReferenceElement != null) {
                    namespaceQualifier = ocReferenceElement.getNamespaceQualifier();
                    break Label_0062;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            namespaceQualifier = null;
        }
        final OCCppNamespaceQualifier ocCppNamespaceQualifier = namespaceQualifier;
        OCTypeArgumentList templateArgumentList = null;
        Label_0081: {
            try {
                if (ocCppNamespaceQualifier != null) {
                    templateArgumentList = ocCppNamespaceQualifier.getTemplateArgumentList();
                    break Label_0081;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            templateArgumentList = null;
        }
        final OCTypeArgumentList list = templateArgumentList;
        if (list != null) {
            for (final OCElement ocElement : list.getArguments()) {
                if (ocElement instanceof OCTypeElement) {
                    final OCType transformType = ocResolveContext.getSubstitution().substitute(((OCTypeElement)ocElement).getType(), ocResolveContext).transformType(new OCTypeResolveVisitor(ocResolveContext));
                    try {
                        if (transformType instanceof OCMagicType) {
                            return true;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean hasNonResolvedTemplateParameters(final OCSymbolWithSubstitution p0, @NotNull final OCResolveContext p1) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/OCResolveUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "hasNonResolvedTemplateParameters"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    48: astore_2       
        //    49: aload_2        
        //    50: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    53: ifeq            179
        //    56: aload_2        
        //    57: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //    60: ifeq            166
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_2        
        //    71: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //    74: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //    79: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    84: astore_3       
        //    85: aload_3        
        //    86: invokeinterface java/util/Iterator.hasNext:()Z
        //    91: ifeq            166
        //    94: aload_3        
        //    95: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   100: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;
        //   103: astore          4
        //   105: aload_0        
        //   106: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithSubstitution.getSubstitution:()Lcom/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution;
        //   111: aload           4
        //   113: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeSubstitution.getSubstitutionFor:(Lcom/jetbrains/cidr/lang/symbols/OCTypeParameterSymbol;)Lcom/jetbrains/cidr/lang/types/OCTypeArgument;
        //   116: astore          5
        //   118: aload           5
        //   120: ifnull          157
        //   123: aload           5
        //   125: instanceof      Lcom/jetbrains/cidr/lang/types/OCType;
        //   128: ifeq            163
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: aload           5
        //   140: checkcast       Lcom/jetbrains/cidr/lang/types/OCType;
        //   143: aload_1        
        //   144: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isMagicInside:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   147: ifeq            163
        //   150: goto            157
        //   153: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: iconst_1       
        //   158: ireturn        
        //   159: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   162: athrow         
        //   163: goto            85
        //   166: aload_2        
        //   167: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //   170: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   175: astore_2       
        //   176: goto            49
        //   179: iconst_0       
        //   180: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  49     63     66     70     Ljava/lang/IllegalArgumentException;
        //  118    131    134    138    Ljava/lang/IllegalArgumentException;
        //  123    150    153    157    Ljava/lang/IllegalArgumentException;
        //  138    159    159    163    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0138:
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
    public static List<Pair<String, OCFunctionSymbol>> findExistingFunctions(@NotNull final Project project, @NotNull final List<String> list, @NotNull final List<OCType> list2, @Nullable final OCSymbol ocSymbol, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "findExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "names", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "findExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentTypes", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "findExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "findExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final ArrayList list3 = new ArrayList<Pair<String, OCFunctionSymbol>>();
        final HashSet set = new HashSet();
        ArrayList list4;
        try {
            final IllegalArgumentException ex5;
            final IllegalArgumentException ex7;
            final IllegalArgumentException ex9;
            final IllegalArgumentException ex11;
            final IllegalArgumentException ex13;
            final IllegalArgumentException ex15;
            final IllegalArgumentException ex17;
            final IllegalArgumentException ex19;
            final Iterator<String> iterator;
            final Object o;
            final Object o2;
            ProgressManager.getInstance().runProcessWithProgressSynchronously(() -> {
                try {
                    if (list == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "names", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$findExistingFunctions$2"));
                        throw ex5;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    if (project == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$findExistingFunctions$2"));
                        throw ex7;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                try {
                    if (ocResolveContext == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$findExistingFunctions$2"));
                        throw ex9;
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
                try {
                    if (list2 == null) {
                        new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentTypes", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$findExistingFunctions$2"));
                        throw ex11;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
                ApplicationManager.getApplication().runReadAction(() -> {
                    try {
                        if (list == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "names", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$null$1"));
                            throw ex13;
                        }
                    }
                    catch (IllegalArgumentException ex14) {
                        throw a(ex14);
                    }
                    try {
                        if (project == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$null$1"));
                            throw ex15;
                        }
                    }
                    catch (IllegalArgumentException ex16) {
                        throw a(ex16);
                    }
                    try {
                        if (ocResolveContext == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$null$1"));
                            throw ex17;
                        }
                    }
                    catch (IllegalArgumentException ex18) {
                        throw a(ex18);
                    }
                    try {
                        if (list2 == null) {
                            new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentTypes", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$null$1"));
                            throw ex19;
                        }
                    }
                    catch (IllegalArgumentException ex20) {
                        throw a(ex20);
                    }
                    list.iterator();
                    while (iterator.hasNext()) {
                        OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)(ocSymbol2 -> {
                            try {
                                if (ocResolveContext == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$null$0"));
                                }
                            }
                            catch (IllegalArgumentException ex) {
                                throw a(ex);
                            }
                            try {
                                if (list2 == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "argumentTypes", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$null$0"));
                                }
                            }
                            catch (IllegalArgumentException ex2) {
                                throw a(ex2);
                            }
                            try {
                                if (!(ocSymbol2 instanceof OCFunctionSymbol)) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException ex3) {
                                throw a(ex3);
                            }
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocSymbol2;
                            try {
                                if (((Set)o).contains(ocFunctionSymbol)) {
                                    return true;
                                }
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            ((Set<OCFunctionSymbol>)o).add(ocFunctionSymbol);
                            final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ocFunctionSymbol.getResolvedOwner(ocResolveContext, true);
                            OCStructSymbol ocStructSymbol = null;
                            Label_0241: {
                                Label_0170: {
                                    try {
                                        if (resolvedOwner != null) {
                                            if (resolvedOwner instanceof OCStructSymbol) {
                                                break Label_0170;
                                            }
                                        }
                                    }
                                    catch (IllegalArgumentException ex5) {
                                        throw a(ex5);
                                    }
                                    ocStructSymbol = null;
                                    break Label_0241;
                                    try {
                                        if (!resolvedOwner.equals(ocSymbol)) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalArgumentException ex6) {
                                        throw a(ex6);
                                    }
                                }
                                if (ocFunctionSymbol.isFriendOrStatic()) {
                                    ocStructSymbol = null;
                                }
                                else {
                                    final OCFunctionSymbol declarationInParent = ocFunctionSymbol.getDeclarationInParent();
                                    OCStructSymbol ocStructSymbol2 = null;
                                    Label_0239: {
                                        Label_0226: {
                                            try {
                                                if (declarationInParent == null) {
                                                    break Label_0226;
                                                }
                                                final OCFunctionSymbol ocFunctionSymbol2 = declarationInParent;
                                                final boolean b = ocFunctionSymbol2.isFriendOrStatic();
                                                if (b) {
                                                    break Label_0226;
                                                }
                                                break Label_0226;
                                            }
                                            catch (IllegalArgumentException ex7) {
                                                throw a(ex7);
                                            }
                                            try {
                                                final OCFunctionSymbol ocFunctionSymbol2 = declarationInParent;
                                                final boolean b = ocFunctionSymbol2.isFriendOrStatic();
                                                if (b) {
                                                    ocStructSymbol2 = null;
                                                    break Label_0239;
                                                }
                                            }
                                            catch (IllegalArgumentException ex8) {
                                                throw a(ex8);
                                            }
                                        }
                                        ocStructSymbol2 = (OCStructSymbol)resolvedOwner;
                                    }
                                    ocStructSymbol = ocStructSymbol2;
                                }
                            }
                            final List<OCType> parameterTypes = ocFunctionSymbol.getType().getParameterTypes();
                            Label_0278: {
                                int size;
                                int size2;
                                try {
                                    size = list2.size();
                                    size2 = parameterTypes.size();
                                    if (ocStructSymbol == null) {
                                        final int n = 0;
                                        break Label_0278;
                                    }
                                }
                                catch (IllegalArgumentException ex9) {
                                    throw a(ex9);
                                }
                                final int n = 1;
                                try {
                                    if (size != size2 + n) {
                                        return true;
                                    }
                                }
                                catch (IllegalArgumentException ex10) {
                                    throw a(ex10);
                                }
                            }
                            Label_0343: {
                                if (ocStructSymbol != null) {
                                    final OCStructType type = ocStructSymbol.getType();
                                    final OCType ocType = list2.get(0);
                                    try {
                                        if (ocType == null) {
                                            break Label_0343;
                                        }
                                        final OCType ocType2 = ocType;
                                        final OCStructType ocStructType = type;
                                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                                        final boolean b2 = true;
                                        final boolean b3 = true;
                                        final boolean b4 = ocType2.equalsAfterResolving(ocStructType, ocResolveContext2, b2, b3);
                                        if (!b4) {
                                            return true;
                                        }
                                        break Label_0343;
                                    }
                                    catch (IllegalArgumentException ex11) {
                                        throw a(ex11);
                                    }
                                    try {
                                        final OCType ocType2 = ocType;
                                        final OCStructType ocStructType = type;
                                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                                        final boolean b2 = true;
                                        final boolean b3 = true;
                                        final boolean b4 = ocType2.equalsAfterResolving(ocStructType, ocResolveContext2, b2, b3);
                                        if (!b4) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalArgumentException ex12) {
                                        throw a(ex12);
                                    }
                                }
                            }
                            int n2 = 0;
                            while (true) {
                                int n3 = 0;
                                int n4 = 0;
                                Label_0381: {
                                    Label_0372: {
                                        try {
                                            if (n2 >= parameterTypes.size()) {
                                                break;
                                            }
                                            n3 = n2;
                                            final OCStructSymbol ocStructSymbol3 = ocStructSymbol;
                                            if (ocStructSymbol3 == null) {
                                                break Label_0372;
                                            }
                                            break Label_0372;
                                        }
                                        catch (IllegalArgumentException ex13) {
                                            throw a(ex13);
                                        }
                                        try {
                                            n3 = n2;
                                            final OCStructSymbol ocStructSymbol3 = ocStructSymbol;
                                            if (ocStructSymbol3 == null) {
                                                n4 = 0;
                                                break Label_0381;
                                            }
                                        }
                                        catch (IllegalArgumentException ex14) {
                                            throw a(ex14);
                                        }
                                    }
                                    n4 = 1;
                                }
                                final OCType ocType3 = list2.get(n3 + n4);
                                final OCType ocType4 = parameterTypes.get(n2);
                                Label_0442: {
                                    try {
                                        if (ocType3 == null) {
                                            break Label_0442;
                                        }
                                        final OCType ocType5 = ocType3;
                                        final OCType ocType6 = ocType4;
                                        final OCResolveContext ocResolveContext3 = ocResolveContext;
                                        final boolean b5 = true;
                                        final boolean b6 = true;
                                        final boolean b7 = ocType5.equalsAfterResolving(ocType6, ocResolveContext3, b5, b6);
                                        if (!b7) {
                                            return true;
                                        }
                                        break Label_0442;
                                    }
                                    catch (IllegalArgumentException ex15) {
                                        throw a(ex15);
                                    }
                                    try {
                                        final OCType ocType5 = ocType3;
                                        final OCType ocType6 = ocType4;
                                        final OCResolveContext ocResolveContext3 = ocResolveContext;
                                        final boolean b5 = true;
                                        final boolean b6 = true;
                                        final boolean b7 = ocType5.equalsAfterResolving(ocType6, ocResolveContext3, b5, b6);
                                        if (!b7) {
                                            return true;
                                        }
                                    }
                                    catch (IllegalArgumentException ex16) {
                                        throw a(ex16);
                                    }
                                }
                                ++n2;
                            }
                            ((List<Pair>)o2).add(Pair.create((Object)ocFunctionSymbol.getName(), (Object)ocFunctionSymbol));
                            return true;
                        }), iterator.next(), false);
                    }
                });
                return;
            }, OCBundle.message("find.existing.operators.progress.title", new Object[0]), true, project);
            list4 = list3;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "findExistingFunctions"));
            }
        }
        catch (IllegalArgumentException ex21) {
            throw a(ex21);
        }
        return (List<Pair<String, OCFunctionSymbol>>)list4;
    }
    
    public static boolean processSymbols(@Nullable final String s, @NotNull final PsiElement psiElement, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return OCSymbolReference.getLocalReference(OCQualifiedName.with(s), psiElement).processPossibleSymbols(processor, psiElement.getContainingFile());
    }
    
    public static boolean processLocalAndMemberSymbols(@Nullable final String s, @NotNull final PsiElement psiElement, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalAndMemberSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalAndMemberSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!processLocalSymbols(s, psiElement, processor, false)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!processMemberSymbols(s, psiElement, processor)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return false;
    }
    
    public static boolean processLocalAndMemberSymbols(@Nullable final String s, @NotNull final PsiElement psiElement, @NotNull final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalAndMemberSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalAndMemberSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalAndMemberSymbols"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (!processLocalSymbols(s, psiElement, processor, ocResolveContext, false)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (!processMemberSymbols(s, psiElement, processor)) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return false;
    }
    
    public static boolean processGlobalSymbols(@Nullable final String s, @NotNull final PsiElement psiElement, @NotNull final Processor<OCSymbol> processor) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processGlobalSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processGlobalSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        try {
            if (!(containingFile instanceof OCFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return processGlobalSymbols(s, psiElement, (OCFile)containingFile, psiElement.getTextRange().getEndOffset(), processor);
    }
    
    public static boolean processGlobalSymbols(@Nullable final String s, @Nullable final PsiElement psiElement, final OCFile ocFile, final int n, final Processor<OCSymbol> processor) {
        final OCCommonProcessors.OrderedProcessor<OCSymbol> orderedProcessor = new OCCommonProcessors.OrderedProcessor<OCSymbol>((com.intellij.util.Processor<? super OCSymbol>)new ResolveFilteringProcessor(processor, (PsiFile)ocFile, n, false), (com.intellij.openapi.util.Condition<OCSymbol>[])new Condition[] { ocSymbol -> {
                try {
                    if (!ocSymbol.isPredeclaration()) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return false;
            }, Conditions.alwaysTrue() });
        Label_0131: {
            Label_0123: {
                Label_0076: {
                    try {
                        if (s == null) {
                            break Label_0131;
                        }
                        if (!s.startsWith("__builtin")) {
                            break Label_0076;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    final OCType fromText = new OCMagicType();
                    final OCSymbolKind ocSymbolKind = OCSymbolKind.BUILTIN_SYMBOL;
                    break Label_0131;
                    try {
                        if (!s.equals("_cmd") || PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCMethod.class }) == null) {
                            break Label_0123;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                final OCType fromText = OCReferenceType.fromText("SEL");
                final OCSymbolKind ocSymbolKind = OCSymbolKind.PARAMETER;
                break Label_0131;
            }
            final OCType fromText = null;
            final OCSymbolKind ocSymbolKind = OCSymbolKind.BUILTIN_SYMBOL;
            try {
                if (fromText != null) {
                    orderedProcessor.process(new OCDeclaratorSymbol(ocFile.getProject(), null, n, null, s, Collections.emptyList(), fromText, ocSymbolKind));
                    return orderedProcessor.finish();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final PsiElement context = ocFile.getContext();
        try {
            if (context != null) {
                return processSymbols(s, context, processor);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        OCStructType.processMembersOfNamespace(ocFile.getMembersContainer(false), s, true, false, (Processor<OCSymbol>)orderedProcessor, new OCResolveContext((PsiElement)ocFile));
        return orderedProcessor.finish();
    }
    
    public static boolean processMemberSymbols(@Nullable final String s, final PsiElement psiElement, final Processor<? super OCMemberSymbol> processor) {
        final OCObjectType a = a(psiElement);
        try {
            if (a == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCCommonProcessors.OrderedProcessor<Object> orderedProcessor = new OCCommonProcessors.OrderedProcessor<Object>((com.intellij.util.Processor<? super Object>)new ResolveFilteringProcessor((com.intellij.util.Processor<? super OCSymbol>)processor, psiElement), (com.intellij.openapi.util.Condition<Object>[])new Condition[] { p0 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_0        
                //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
                //     4: ifeq            26
                //     7: aload_0        
                //     8: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
                //    11: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getGeneratedFromProperty:()Ljava/lang/String;
                //    16: ifnull          64
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    25: athrow         
                //    26: aload_0        
                //    27: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
                //    30: ifeq            72
                //    33: goto            40
                //    36: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    39: athrow         
                //    40: aload_0        
                //    41: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    46: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                //    49: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
                //    54: ifnonnull       72
                //    57: goto            64
                //    60: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    63: athrow         
                //    64: iconst_1       
                //    65: goto            73
                //    68: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    71: athrow         
                //    72: iconst_0       
                //    73: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      19     22     26     Ljava/lang/IllegalArgumentException;
                //  7      33     36     40     Ljava/lang/IllegalArgumentException;
                //  26     57     60     64     Ljava/lang/IllegalArgumentException;
                //  40     68     68     72     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
            }, p1 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
                //     4: ifeq            32
                //     7: aload_1        
                //     8: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
                //    11: aload_0        
                //    12: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
                //    17: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:(Lcom/intellij/psi/PsiFile;)Z
                //    22: ifeq            75
                //    25: goto            32
                //    28: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    31: athrow         
                //    32: aload_1        
                //    33: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
                //    36: ifeq            83
                //    39: goto            46
                //    42: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    45: athrow         
                //    46: ldc             ""
                //    48: aload_1        
                //    49: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
                //    54: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
                //    57: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
                //    62: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //    65: ifeq            83
                //    68: goto            75
                //    71: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    74: athrow         
                //    75: iconst_1       
                //    76: goto            84
                //    79: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    82: athrow         
                //    83: iconst_0       
                //    84: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      25     28     32     Ljava/lang/IllegalArgumentException;
                //  7      39     42     46     Ljava/lang/IllegalArgumentException;
                //  32     68     71     75     Ljava/lang/IllegalArgumentException;
                //  46     79     79     83     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0032:
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
            }, Conditions.alwaysTrue() });
        a.processMembers(s, OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)orderedProcessor);
        return orderedProcessor.finish();
    }
    
    @Contract("_, null, _, _ -> true")
    public static boolean processLocalSymbols(@Nullable final String s, @Nullable final PsiElement psiElement, @NotNull final Processor<OCSymbol> processor, final boolean b) {
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return processLocalSymbols(s, psiElement, processor, new OCResolveContext((PsiElement)psiElement.getContainingFile()), b);
    }
    
    private static boolean a(@Nullable final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: iconst_2       
        //     2: anewarray       Ljava/lang/Class;
        //     5: dup            
        //     6: iconst_0       
        //     7: ldc             Lcom/jetbrains/cidr/lang/psi/OCTypeElement;.class
        //     9: aastore        
        //    10: dup            
        //    11: iconst_1       
        //    12: ldc             Lcom/jetbrains/cidr/lang/psi/impl/OCNoexceptSpecifierImpl;.class
        //    14: aastore        
        //    15: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    18: astore_2       
        //    19: aload_2        
        //    20: ifnull          67
        //    23: aload_2        
        //    24: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    29: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    32: ifne            61
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_2        
        //    43: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    48: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;
        //    51: ifeq            67
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    60: athrow         
        //    61: iconst_1       
        //    62: ireturn        
        //    63: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: iload_1        
        //    68: ifeq            107
        //    71: aload_0        
        //    72: iconst_2       
        //    73: anewarray       Ljava/lang/Class;
        //    76: dup            
        //    77: iconst_0       
        //    78: ldc             Lcom/jetbrains/cidr/lang/psi/OCParameterDeclaration;.class
        //    80: aastore        
        //    81: dup            
        //    82: iconst_1       
        //    83: ldc             Lcom/jetbrains/cidr/lang/psi/OCMethodSelectorPart;.class
        //    85: aastore        
        //    86: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    89: ifnull          107
        //    92: goto            99
        //    95: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    98: athrow         
        //    99: iconst_1       
        //   100: goto            108
        //   103: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: iconst_0       
        //   108: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  19     35     38     42     Ljava/lang/IllegalArgumentException;
        //  23     54     57     61     Ljava/lang/IllegalArgumentException;
        //  42     63     63     67     Ljava/lang/IllegalArgumentException;
        //  67     92     95     99     Ljava/lang/IllegalArgumentException;
        //  71     103    103    107    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    
    public static boolean processLocalSymbols(@Nullable final String s, @NotNull final PsiElement psiElement, @NotNull final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (processor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processLocalSymbols"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCFunctionDeclaration.class, OCMethod.class, OCParameterList.class });
        if (ocElement == null) {
            ocElement = (OCElement)PsiTreeUtil.getTopmostParentOfType(psiElement, (Class)OCBlockExpression.class);
        }
        if (ocElement == null) {
            ocElement = (OCElement)PsiTreeUtil.getTopmostParentOfType(psiElement, (Class)OCLambdaExpression.class);
        }
        try {
            if (ocElement == null) {
                return processGenericAndTemplateSymbols(s, psiElement, processor, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (psiElement.getParent() instanceof OCConstructorFieldInitializer) {
                return true;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        final ResolveFilteringProcessor resolveFilteringProcessor = new ResolveFilteringProcessor(processor, psiElement);
        Label_0349: {
            Label_0269: {
                try {
                    if (s == null) {
                        break Label_0349;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final Project project = psiElement2.getProject();
                    final Key<Boolean> key = OCResolveUtil.DISABLE_LOCAL_SYMBOL_TABLE;
                    final Object o = project.getUserData((Key)key);
                    final Boolean b2 = Boolean.TRUE;
                    if (o != b2) {
                        break Label_0269;
                    }
                    break Label_0349;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final Project project = psiElement2.getProject();
                    final Key<Boolean> key = OCResolveUtil.DISABLE_LOCAL_SYMBOL_TABLE;
                    final Object o = project.getUserData((Key)key);
                    final Boolean b2 = Boolean.TRUE;
                    if (o == b2) {
                        break Label_0349;
                    }
                    if (a(psiElement, b)) {
                        break Label_0349;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            final Iterable<OCSymbol> localSymbols = OCFileSymbols.getLocalSymbols(ocElement.getContainingOCFile(), s);
            try {
                if (localSymbols == null) {
                    break Label_0349;
                }
                final Iterable<OCSymbol> iterable = localSymbols;
                final String s2 = s;
                final PsiElement psiElement3 = psiElement;
                final OCElement ocElement2 = ocElement;
                final ASTNode astNode = ocElement2.getNode();
                final int n = astNode.getStartOffset();
                final ResolveFilteringProcessor resolveFilteringProcessor2 = resolveFilteringProcessor;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b3 = a(iterable, s2, psiElement3, n, (Processor<OCSymbol>)resolveFilteringProcessor2, ocResolveContext2);
                if (!b3) {
                    return false;
                }
                return processGenericAndTemplateSymbols(s, psiElement, processor, ocResolveContext);
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            try {
                final Iterable<OCSymbol> iterable = localSymbols;
                final String s2 = s;
                final PsiElement psiElement3 = psiElement;
                final OCElement ocElement2 = ocElement;
                final ASTNode astNode = ocElement2.getNode();
                final int n = astNode.getStartOffset();
                final ResolveFilteringProcessor resolveFilteringProcessor2 = resolveFilteringProcessor;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b3 = a(iterable, s2, psiElement3, n, (Processor<OCSymbol>)resolveFilteringProcessor2, ocResolveContext2);
                if (!b3) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
            return processGenericAndTemplateSymbols(s, psiElement, processor, ocResolveContext);
        }
        final boolean a = a(psiElement, (Processor<OCLocalSymbolDeclarator>)(ocLocalSymbolDeclarator -> {
            try {
                if (ocResolveContext == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$processLocalSymbols$6"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (processor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "processor", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "lambda$processLocalSymbols$6"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final OCUsingSymbol localSymbol = ocLocalSymbolDeclarator.getLocalSymbol();
            try {
                if (localSymbol == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final String name = localSymbol.getName();
            try {
                if (localSymbol.isUnnamed()) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            if (localSymbol.getKind() == OCSymbolKind.NAMESPACE_USING_SYMBOL) {
                for (final OCSymbol ocSymbol : ocResolveContext.resolveToSymbols(localSymbol.getSymbolReference())) {
                    try {
                        if (!(ocSymbol instanceof OCNamespaceSymbol)) {
                            continue;
                        }
                        final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                        final OCNamespaceSymbol ocNamespaceSymbol2 = ocNamespaceSymbol;
                        final String s2 = s;
                        final boolean b = true;
                        final boolean b2 = false;
                        final Processor processor3 = processor;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b3 = OCStructType.processMembersOfNamespace(ocNamespaceSymbol2, s2, b, b2, (Processor<OCSymbol>)processor3, ocResolveContext2);
                        if (!b3) {
                            return false;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final OCNamespaceSymbol ocNamespaceSymbol = (OCNamespaceSymbol)ocSymbol;
                        final OCNamespaceSymbol ocNamespaceSymbol2 = ocNamespaceSymbol;
                        final String s2 = s;
                        final boolean b = true;
                        final boolean b2 = false;
                        final Processor processor3 = processor;
                        final OCResolveContext ocResolveContext2 = ocResolveContext;
                        final boolean b3 = OCStructType.processMembersOfNamespace(ocNamespaceSymbol2, s2, b, b2, (Processor<OCSymbol>)processor3, ocResolveContext2);
                        if (!b3) {
                            return false;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
                return true;
            }
            Label_0255: {
                try {
                    if (s == null) {
                        break Label_0255;
                    }
                    final String s3 = name;
                    final String s4 = s;
                    final boolean b4 = s3.equals(s4);
                    if (!b4) {
                        return true;
                    }
                    break Label_0255;
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    final String s3 = name;
                    final String s4 = s;
                    final boolean b4 = s3.equals(s4);
                    if (!b4) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
                try {
                    if (localSymbol.getKind() == OCSymbolKind.SYMBOL_USING_SYMBOL) {
                        return ContainerUtil.process((List)ocResolveContext.resolveToSymbols(localSymbol.getSymbolReference()), processor);
                    }
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
            }
            return ((Processor)resolveFilteringProcessor).process((Object)localSymbol);
        }), psiElement.getParent() instanceof OCGotoStatement);
        try {
            if (a) {
                return processGenericAndTemplateSymbols(s, psiElement, processor, ocResolveContext);
            }
        }
        catch (IllegalArgumentException ex10) {
            throw a(ex10);
        }
        return false;
    }
    
    static boolean processGenericAndTemplateSymbols(@Nullable final String s, final PsiElement psiElement, final Processor<OCSymbol> processor, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "processGenericAndTemplateSymbols"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0071: {
            try {
                if (!OCTypeParameterSymbolResolver.processGenericSymbols(s, psiElement, processor, ocResolveContext)) {
                    return false;
                }
                final String s2 = s;
                final PsiElement psiElement2 = psiElement;
                final Processor<OCSymbol> processor2 = processor;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = OCTypeParameterSymbolResolver.processTemplateSymbols(s2, psiElement2, processor2, ocResolveContext2);
                if (b) {
                    break Label_0071;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s2 = s;
                final PsiElement psiElement2 = psiElement;
                final Processor<OCSymbol> processor2 = processor;
                final OCResolveContext ocResolveContext2 = ocResolveContext;
                final boolean b = OCTypeParameterSymbolResolver.processTemplateSymbols(s2, psiElement2, processor2, ocResolveContext2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    private static int b(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLocalSymbolDeclarator;
        //     4: ifeq            73
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLocalSymbolDeclarator;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCLocalSymbolDeclarator.getLocalSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    16: astore_1       
        //    17: aload_1        
        //    18: ifnull          70
        //    21: aload_1        
        //    22: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    27: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    30: if_acmpne       70
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    39: athrow         
        //    40: aload_1        
        //    41: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //    46: ifnull          70
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_1        
        //    57: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //    62: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    65: ireturn        
        //    66: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: goto            154
        //    73: aload_0        
        //    74: iconst_1       
        //    75: anewarray       Ljava/lang/Class;
        //    78: dup            
        //    79: iconst_0       
        //    80: ldc             Lcom/jetbrains/cidr/lang/psi/OCParameterList;.class
        //    82: aastore        
        //    83: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //    86: checkcast       Lcom/jetbrains/cidr/lang/psi/OCParameterList;
        //    89: astore_1       
        //    90: aload_1        
        //    91: ifnull          154
        //    94: aload_1        
        //    95: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getParent:()Lcom/intellij/psi/PsiElement;
        //   100: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   105: astore_2       
        //   106: aload_2        
        //   107: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   110: ifeq            147
        //   113: aload_2        
        //   114: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDefinition;
        //   117: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDefinition.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   122: astore_3       
        //   123: aload_3        
        //   124: ifnull          140
        //   127: aload_3        
        //   128: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getTextOffset:()I
        //   133: goto            146
        //   136: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   139: athrow         
        //   140: aload_2        
        //   141: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   146: ireturn        
        //   147: aload_1        
        //   148: invokeinterface com/jetbrains/cidr/lang/psi/OCParameterList.getTextOffset:()I
        //   153: ireturn        
        //   154: aload_0        
        //   155: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   160: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  17     33     36     40     Ljava/lang/IllegalArgumentException;
        //  21     49     52     56     Ljava/lang/IllegalArgumentException;
        //  40     66     66     70     Ljava/lang/IllegalArgumentException;
        //  123    136    136    140    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    private static boolean a(final Iterable<OCSymbol> p0, final String p1, final PsiElement p2, final int p3, final Processor<OCSymbol> p4, final OCResolveContext p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.b:(Lcom/intellij/psi/PsiElement;)I
        //     4: istore          6
        //     6: aload_0        
        //     7: instanceof      Ljava/util/Set;
        //    10: ifeq            106
        //    13: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveUtil.$assertionsDisabled:Z
        //    16: ifne            58
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: aload_0        
        //    27: checkcast       Ljava/util/Set;
        //    30: invokeinterface java/util/Set.size:()I
        //    35: iconst_1       
        //    36: if_icmpeq       58
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    45: athrow         
        //    46: new             Ljava/lang/AssertionError;
        //    49: dup            
        //    50: invokespecial   java/lang/AssertionError.<init>:()V
        //    53: athrow         
        //    54: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    57: athrow         
        //    58: aload_0        
        //    59: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //    64: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    69: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    72: astore          7
        //    74: aload           7
        //    76: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //    81: iload           6
        //    83: invokevirtual   com/intellij/openapi/util/TextRange.containsOffset:(I)Z
        //    86: ifeq            103
        //    89: aload           4
        //    91: aload           7
        //    93: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //    98: ireturn        
        //    99: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: goto            396
        //   106: aload_0        
        //   107: instanceof      Ljava/util/List;
        //   110: ifeq            367
        //   113: aload_0        
        //   114: checkcast       Ljava/util/List;
        //   117: astore          7
        //   119: iload           6
        //   121: aload           7
        //   123: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.findNearest:(ILjava/util/List;)I
        //   126: istore          8
        //   128: aload           7
        //   130: iload           8
        //   132: ineg           
        //   133: iconst_1       
        //   134: isub           
        //   135: invokeinterface java/util/List.listIterator:(I)Ljava/util/ListIterator;
        //   140: astore          9
        //   142: aload           9
        //   144: invokeinterface java/util/ListIterator.hasPrevious:()Z
        //   149: ifeq            364
        //   152: aload           9
        //   154: invokeinterface java/util/ListIterator.previous:()Ljava/lang/Object;
        //   159: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   162: astore          10
        //   164: aload           10
        //   166: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   171: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   174: iload_3        
        //   175: if_icmpge       185
        //   178: goto            364
        //   181: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: aload           10
        //   187: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   192: iload           6
        //   194: invokevirtual   com/intellij/openapi/util/TextRange.containsOffset:(I)Z
        //   197: ifeq            361
        //   200: aload           10
        //   202: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   207: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   210: if_acmpne       307
        //   213: goto            220
        //   216: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: aload           5
        //   222: aload           10
        //   224: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   227: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   230: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)Ljava/util/List;
        //   233: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   238: astore          11
        //   240: aload           11
        //   242: invokeinterface java/util/Iterator.hasNext:()Z
        //   247: ifeq            304
        //   250: aload           11
        //   252: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   257: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   260: astore          12
        //   262: aload           12
        //   264: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   267: ifeq            301
        //   270: aload           12
        //   272: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   275: aload_1        
        //   276: iconst_1       
        //   277: iconst_0       
        //   278: aload           4
        //   280: aload           5
        //   282: invokestatic    com/jetbrains/cidr/lang/types/OCStructType.processMembersOfNamespace:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceLikeSymbol;Ljava/lang/String;ZZLcom/intellij/util/Processor;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //   285: ifne            301
        //   288: goto            295
        //   291: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   294: athrow         
        //   295: iconst_0       
        //   296: ireturn        
        //   297: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: goto            240
        //   304: goto            361
        //   307: aload           10
        //   309: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   314: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   317: if_acmpne       343
        //   320: aload           5
        //   322: aload           10
        //   324: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol;
        //   327: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCUsingSymbol.getSymbolReference:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;
        //   330: invokevirtual   com/jetbrains/cidr/lang/symbols/OCResolveContext.resolveToSymbols:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolReference;)Ljava/util/List;
        //   333: aload           4
        //   335: invokestatic    com/intellij/util/containers/ContainerUtil.process:(Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   338: ireturn        
        //   339: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: aload           4
        //   345: aload           10
        //   347: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   352: ifne            361
        //   355: iconst_0       
        //   356: ireturn        
        //   357: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   360: athrow         
        //   361: goto            142
        //   364: goto            396
        //   367: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveUtil.$assertionsDisabled:Z
        //   370: ifne            396
        //   373: aload_0        
        //   374: ifnull          396
        //   377: goto            384
        //   380: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   383: athrow         
        //   384: new             Ljava/lang/AssertionError;
        //   387: dup            
        //   388: invokespecial   java/lang/AssertionError.<init>:()V
        //   391: athrow         
        //   392: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   395: athrow         
        //   396: iconst_1       
        //   397: ireturn        
        //    Signature:
        //  (Ljava/lang/Iterable<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Ljava/lang/String;Lcom/intellij/psi/PsiElement;ILcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  6      19     22     26     Ljava/lang/IllegalArgumentException;
        //  13     39     42     46     Ljava/lang/IllegalArgumentException;
        //  26     54     54     58     Ljava/lang/IllegalArgumentException;
        //  74     99     99     103    Ljava/lang/IllegalArgumentException;
        //  164    181    181    185    Ljava/lang/IllegalArgumentException;
        //  185    213    216    220    Ljava/lang/IllegalArgumentException;
        //  262    288    291    295    Ljava/lang/IllegalArgumentException;
        //  270    297    297    301    Ljava/lang/IllegalArgumentException;
        //  307    339    339    343    Ljava/lang/IllegalArgumentException;
        //  343    357    357    361    Ljava/lang/IllegalArgumentException;
        //  367    377    380    384    Ljava/lang/IllegalArgumentException;
        //  373    392    392    396    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    public static OCSymbol resolveLambdaLocalSymbolInTable(final List<OCDeclaratorSymbol> p0, final OCQualifiedName p1, final long p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //     3: dup            
        //     4: aconst_null    
        //     5: aconst_null    
        //     6: lload_2        
        //     7: aconst_null    
        //     8: aload_1        
        //     9: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    12: getstatic       com/jetbrains/cidr/lang/types/OCUnknownType.INSTANCE:Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //    15: aconst_null    
        //    16: iconst_0       
        //    17: newarray        I
        //    19: aconst_null    
        //    20: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    23: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    26: iconst_0       
        //    27: iconst_0       
        //    28: aconst_null    
        //    29: aconst_null    
        //    30: invokespecial   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;JLcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;Ljava/util/List;Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;[ILcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;Ljava/util/List;Ljava/util/List;IILcom/intellij/openapi/util/TextRange;Lcom/jetbrains/cidr/lang/symbols/OCVisibility;)V
        //    33: astore          4
        //    35: aload_0        
        //    36: aload           4
        //    38: getstatic       com/jetbrains/cidr/lang/symbols/expression/OCLambdaExpressionSymbol.variablesComparator:Ljava/util/Comparator;
        //    41: invokestatic    java/util/Collections.binarySearch:(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;)I
        //    44: istore          5
        //    46: iload           5
        //    48: iflt            70
        //    51: aload_0        
        //    52: iload           5
        //    54: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    59: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    62: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getInitializer:()Lcom/jetbrains/cidr/lang/symbols/expression/OCExpressionSymbol;
        //    65: areturn        
        //    66: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: iload           5
        //    72: iconst_2       
        //    73: iadd           
        //    74: ineg           
        //    75: istore          5
        //    77: iload           5
        //    79: iflt            160
        //    82: aload_0        
        //    83: iload           5
        //    85: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    90: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //    93: astore          6
        //    95: aload           6
        //    97: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getName:()Ljava/lang/String;
        //   100: aload_1        
        //   101: invokevirtual   com/jetbrains/cidr/lang/symbols/OCQualifiedName.getName:()Ljava/lang/String;
        //   104: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   107: ifeq            160
        //   110: aload           6
        //   112: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   115: ifnull          147
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: aload           6
        //   127: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   130: lload_2        
        //   131: invokestatic    com/jetbrains/cidr/lang/symbols/OCSymbolOffsetUtil.getTextOffset:(J)I
        //   134: invokevirtual   com/intellij/openapi/util/TextRange.contains:(I)Z
        //   137: ifeq            154
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   146: athrow         
        //   147: aload           6
        //   149: areturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: iinc            5, -1
        //   157: goto            77
        //   160: aconst_null    
        //   161: areturn        
        //    Signature:
        //  (Ljava/util/List<Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;>;Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;J)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  46     66     66     70     Ljava/lang/IllegalArgumentException;
        //  95     118    121    125    Ljava/lang/IllegalArgumentException;
        //  110    140    143    147    Ljava/lang/IllegalArgumentException;
        //  125    150    150    154    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0125:
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
    
    public static int findNearest(final int n, @NotNull final List<? extends OCSymbol> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "findNearest"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        int n2;
        int n3;
        final Object o;
        return Collections.binarySearch(list, (Object)null, (ocSymbol, ocSymbol2) -> {
            Label_0021_1: {
                try {
                    if (ocSymbol != null) {
                        ocSymbol.getScope().getStartOffset();
                        break Label_0021_1;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            Label_0043_1: {
                try {
                    if (ocSymbol2 != null) {
                        ocSymbol2.getScope().getStartOffset();
                        break Label_0043_1;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            Label_0072_1: {
                Label_0062_1: {
                    try {
                        if (n2 == n3) {
                            if (o == null) {
                                break Label_0062_1;
                            }
                            else {
                                break Label_0072_1;
                            }
                        }
                        else {
                            return n2 - n3;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        if (o == null) {
                            ++n2;
                            return n2 - n3;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                try {
                    if (ocSymbol2 == null) {
                        ++n3;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return n2 - n3;
        });
    }
    
    private static boolean a(final PsiElement p0, final Processor<OCLocalSymbolDeclarator> p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_0        
        //     3: astore          4
        //     5: aload           4
        //     7: ifnull          221
        //    10: new             Lcom/jetbrains/cidr/lang/resolve/OCLocalDeclarationsVisitor;
        //    13: dup            
        //    14: aload_1        
        //    15: aload_3        
        //    16: iload_2        
        //    17: invokespecial   com/jetbrains/cidr/lang/resolve/OCLocalDeclarationsVisitor.<init>:(Lcom/intellij/util/Processor;Lcom/intellij/psi/PsiElement;Z)V
        //    20: astore          5
        //    22: aload           4
        //    24: aload           5
        //    26: invokeinterface com/intellij/psi/PsiElement.accept:(Lcom/intellij/psi/PsiElementVisitor;)V
        //    31: aload           5
        //    33: invokevirtual   com/jetbrains/cidr/lang/resolve/OCLocalDeclarationsVisitor.getResult:()Z
        //    36: ifne            45
        //    39: iconst_0       
        //    40: ireturn        
        //    41: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload           4
        //    47: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    50: astore          6
        //    52: getstatic       com/jetbrains/cidr/lang/resolve/OCResolveUtil.stopSet:Ljava/util/List;
        //    55: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    60: astore          7
        //    62: aload           7
        //    64: invokeinterface java/util/Iterator.hasNext:()Z
        //    69: ifeq            127
        //    72: aload           7
        //    74: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    79: checkcast       Ljava/lang/Class;
        //    82: astore          8
        //    84: aload           8
        //    86: aload           6
        //    88: invokevirtual   java/lang/Class.isAssignableFrom:(Ljava/lang/Class;)Z
        //    91: ifeq            124
        //    94: aload           4
        //    96: iconst_1       
        //    97: anewarray       Ljava/lang/Class;
        //   100: dup            
        //   101: iconst_0       
        //   102: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   104: aastore        
        //   105: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   108: ifnonnull       124
        //   111: goto            118
        //   114: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   117: athrow         
        //   118: iconst_1       
        //   119: ireturn        
        //   120: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: goto            62
        //   127: aload           6
        //   129: ldc             Lcom/jetbrains/cidr/lang/psi/OCBlockExpression;.class
        //   131: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   134: ifne            171
        //   137: aload           6
        //   139: ldc             Lcom/jetbrains/cidr/lang/psi/OCLambdaExpression;.class
        //   141: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   144: ifne            171
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload           6
        //   156: ldc             Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;.class
        //   158: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   161: ifeq            206
        //   164: goto            171
        //   167: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: aload           4
        //   173: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   178: iconst_1       
        //   179: anewarray       Ljava/lang/Class;
        //   182: dup            
        //   183: iconst_0       
        //   184: ldc             Lcom/jetbrains/cidr/lang/psi/OCCallable;.class
        //   186: aastore        
        //   187: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   190: ifnonnull       206
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   199: athrow         
        //   200: iconst_1       
        //   201: ireturn        
        //   202: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   205: athrow         
        //   206: aload           4
        //   208: astore_3       
        //   209: aload           4
        //   211: invokeinterface com/intellij/psi/PsiElement.getContext:()Lcom/intellij/psi/PsiElement;
        //   216: astore          4
        //   218: goto            5
        //   221: iconst_1       
        //   222: ireturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/psi/OCLocalSymbolDeclarator;>;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  22     41     41     45     Ljava/lang/IllegalArgumentException;
        //  84     111    114    118    Ljava/lang/IllegalArgumentException;
        //  94     120    120    124    Ljava/lang/IllegalArgumentException;
        //  127    147    150    154    Ljava/lang/IllegalArgumentException;
        //  137    164    167    171    Ljava/lang/IllegalArgumentException;
        //  154    193    196    200    Ljava/lang/IllegalArgumentException;
        //  171    202    202    206    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0154:
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
    private static OCObjectType a(final PsiElement psiElement) {
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType(psiElement, false, new Class[] { OCInterface.class, OCImplementation.class, OCProtocol.class });
        try {
            if (ocClassDeclaration != null) {
                return ocClassDeclaration.getType();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public static boolean isDuplicate(final OCSymbolKind p0, final OCSymbolKind p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //     4: if_acmpeq       77
        //     7: aload_1        
        //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.METHOD:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    11: if_acmpeq       77
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    20: athrow         
        //    21: aload_0        
        //    22: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    25: if_acmpeq       77
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_1        
        //    36: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROPERTY:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    39: if_acmpeq       77
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    48: athrow         
        //    49: aload_0        
        //    50: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYNTHESIZE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    53: if_acmpeq       77
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    62: athrow         
        //    63: aload_1        
        //    64: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYNTHESIZE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    67: if_acmpne       99
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: aload_0        
        //    78: aload_1        
        //    79: if_acmpne       97
        //    82: goto            89
        //    85: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    88: athrow         
        //    89: iconst_1       
        //    90: goto            98
        //    93: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    96: athrow         
        //    97: iconst_0       
        //    98: ireturn        
        //    99: aload_0        
        //   100: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClass:()Z
        //   103: aload_1        
        //   104: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isClass:()Z
        //   107: if_icmpeq       116
        //   110: iconst_0       
        //   111: ireturn        
        //   112: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_0        
        //   117: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   120: aload_1        
        //   121: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //   124: if_icmpeq       133
        //   127: iconst_0       
        //   128: ireturn        
        //   129: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   132: athrow         
        //   133: aload_0        
        //   134: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   137: if_acmpne       148
        //   140: iconst_1       
        //   141: goto            149
        //   144: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: iconst_0       
        //   149: aload_1        
        //   150: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   153: if_acmpne       164
        //   156: iconst_1       
        //   157: goto            165
        //   160: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   163: athrow         
        //   164: iconst_0       
        //   165: if_icmpeq       174
        //   168: iconst_0       
        //   169: ireturn        
        //   170: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   173: athrow         
        //   174: aload_0        
        //   175: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   178: if_acmpne       215
        //   181: aload_1        
        //   182: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isGlobalVariable:()Z
        //   185: ifne            209
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_1        
        //   196: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunction:()Z
        //   199: ifeq            215
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: iconst_0       
        //   210: ireturn        
        //   211: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload_1        
        //   216: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PARAMETER:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   219: if_acmpne       256
        //   222: aload_0        
        //   223: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isGlobalVariable:()Z
        //   226: ifne            250
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   235: athrow         
        //   236: aload_0        
        //   237: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isFunction:()Z
        //   240: ifeq            256
        //   243: goto            250
        //   246: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   249: athrow         
        //   250: iconst_0       
        //   251: ireturn        
        //   252: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   255: athrow         
        //   256: iconst_1       
        //   257: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      14     17     21     Ljava/lang/IllegalArgumentException;
        //  7      28     31     35     Ljava/lang/IllegalArgumentException;
        //  21     42     45     49     Ljava/lang/IllegalArgumentException;
        //  35     56     59     63     Ljava/lang/IllegalArgumentException;
        //  49     70     73     77     Ljava/lang/IllegalArgumentException;
        //  63     82     85     89     Ljava/lang/IllegalArgumentException;
        //  77     93     93     97     Ljava/lang/IllegalArgumentException;
        //  99     112    112    116    Ljava/lang/IllegalArgumentException;
        //  116    129    129    133    Ljava/lang/IllegalArgumentException;
        //  133    144    144    148    Ljava/lang/IllegalArgumentException;
        //  149    160    160    164    Ljava/lang/IllegalArgumentException;
        //  165    170    170    174    Ljava/lang/IllegalArgumentException;
        //  174    188    191    195    Ljava/lang/IllegalArgumentException;
        //  181    202    205    209    Ljava/lang/IllegalArgumentException;
        //  195    211    211    215    Ljava/lang/IllegalArgumentException;
        //  215    229    232    236    Ljava/lang/IllegalArgumentException;
        //  222    243    246    250    Ljava/lang/IllegalArgumentException;
        //  236    252    252    256    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    
    public static boolean isDuplicate(final OCSymbol p0, final OCSymbol p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //     5: ifeq            14
        //     8: iconst_0       
        //     9: ireturn        
        //    10: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    13: athrow         
        //    14: aload_0        
        //    15: aload_1        
        //    16: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //    21: aload_1        
        //    22: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getComplexOffset:()J
        //    27: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.isEarlierInCodeWithComplexOffset:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/openapi/vfs/VirtualFile;J)Z
        //    30: ifne            39
        //    33: iconst_0       
        //    34: ireturn        
        //    35: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    38: athrow         
        //    39: aload_0        
        //    40: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    43: aload_1        
        //    44: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    47: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    50: ifeq            75
        //    53: aload_0        
        //    54: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //    59: aload_1        
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //    65: if_icmpeq       179
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_0        
        //    76: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //    81: ifeq            173
        //    84: goto            91
        //    87: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    90: athrow         
        //    91: aload_1        
        //    92: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //    97: ifeq            173
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: aload_0        
        //   108: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   111: ifeq            135
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aload_1        
        //   122: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   125: ifne            173
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_0        
        //   136: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   141: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isTemplateParameter:()Z
        //   144: ifne            173
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   153: athrow         
        //   154: aload_1        
        //   155: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   160: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isTemplateParameter:()Z
        //   163: ifeq            179
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: iconst_0       
        //   174: ireturn        
        //   175: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   178: athrow         
        //   179: aload_0        
        //   180: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   185: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   188: if_acmpne       199
        //   191: iconst_1       
        //   192: goto            200
        //   195: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   198: athrow         
        //   199: iconst_0       
        //   200: aload_1        
        //   201: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   206: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   209: if_acmpne       220
        //   212: iconst_1       
        //   213: goto            221
        //   216: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   219: athrow         
        //   220: iconst_0       
        //   221: if_icmpeq       230
        //   224: iconst_0       
        //   225: ireturn        
        //   226: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload_0        
        //   231: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   234: ifeq            312
        //   237: aload_1        
        //   238: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   241: ifeq            312
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_0        
        //   252: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   255: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   260: aload_1        
        //   261: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   264: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   269: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //   272: ifeq            306
        //   275: goto            282
        //   278: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   281: athrow         
        //   282: ldc             ""
        //   284: aload_0        
        //   285: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   288: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   293: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   296: ifeq            312
        //   299: goto            306
        //   302: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   305: athrow         
        //   306: iconst_0       
        //   307: ireturn        
        //   308: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   311: athrow         
        //   312: aload_0        
        //   313: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   316: ifeq            373
        //   319: aload_1        
        //   320: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   323: ifeq            373
        //   326: goto            333
        //   329: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   332: athrow         
        //   333: aload_0        
        //   334: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   337: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   342: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   345: aload_1        
        //   346: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   349: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   354: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //   357: ifne            373
        //   360: goto            367
        //   363: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   366: athrow         
        //   367: iconst_0       
        //   368: ireturn        
        //   369: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   372: athrow         
        //   373: aload_0        
        //   374: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   377: ifeq            425
        //   380: aload_1        
        //   381: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCDeclaratorSymbol;
        //   384: ifeq            425
        //   387: goto            394
        //   390: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   393: athrow         
        //   394: aload_0        
        //   395: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   400: aload_1        
        //   401: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getScope:()Lcom/intellij/openapi/util/TextRange;
        //   406: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   409: ifne            425
        //   412: goto            419
        //   415: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   418: athrow         
        //   419: iconst_0       
        //   420: ireturn        
        //   421: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   424: athrow         
        //   425: aload_0        
        //   426: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   429: ifeq            480
        //   432: aload_1        
        //   433: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   436: ifeq            480
        //   439: goto            446
        //   442: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   445: athrow         
        //   446: aload_0        
        //   447: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   450: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isStatic:()Z
        //   455: aload_1        
        //   456: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   459: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isStatic:()Z
        //   464: if_icmpeq       480
        //   467: goto            474
        //   470: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   473: athrow         
        //   474: iconst_0       
        //   475: ireturn        
        //   476: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   479: athrow         
        //   480: aload_0        
        //   481: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   484: ifeq            506
        //   487: aload_0        
        //   488: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   491: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   496: ifnonnull       539
        //   499: goto            506
        //   502: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   505: athrow         
        //   506: aload_1        
        //   507: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   510: ifeq            545
        //   513: goto            520
        //   516: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   519: athrow         
        //   520: aload_1        
        //   521: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   524: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   529: ifnull          545
        //   532: goto            539
        //   535: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   538: athrow         
        //   539: iconst_0       
        //   540: ireturn        
        //   541: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   544: athrow         
        //   545: aload_0        
        //   546: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   549: ifeq            566
        //   552: aload_1        
        //   553: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   556: ifne            594
        //   559: goto            566
        //   562: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   565: athrow         
        //   566: aload_0        
        //   567: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   570: ifeq            625
        //   573: goto            580
        //   576: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   579: athrow         
        //   580: aload_1        
        //   581: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   584: ifeq            625
        //   587: goto            594
        //   590: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   593: athrow         
        //   594: aload_0        
        //   595: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   600: aload_1        
        //   601: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   606: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   609: ifeq            625
        //   612: goto            619
        //   615: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   618: athrow         
        //   619: iconst_0       
        //   620: ireturn        
        //   621: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   624: athrow         
        //   625: aload_0        
        //   626: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   629: aload_1        
        //   630: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   633: if_icmpeq       642
        //   636: iconst_0       
        //   637: ireturn        
        //   638: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   641: athrow         
        //   642: aload_1        
        //   643: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   646: ifeq            680
        //   649: aload_0        
        //   650: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   655: aload_1        
        //   656: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   661: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   664: ifne            680
        //   667: goto            674
        //   670: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   673: athrow         
        //   674: iconst_0       
        //   675: ireturn        
        //   676: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   679: athrow         
        //   680: aload_0        
        //   681: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   684: ifeq            712
        //   687: aload_0        
        //   688: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   691: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:()Z
        //   696: ifeq            712
        //   699: goto            706
        //   702: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   705: athrow         
        //   706: iconst_0       
        //   707: ireturn        
        //   708: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   711: athrow         
        //   712: aload_1        
        //   713: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   716: ifeq            744
        //   719: aload_1        
        //   720: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   723: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:()Z
        //   728: ifeq            744
        //   731: goto            738
        //   734: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   737: athrow         
        //   738: iconst_0       
        //   739: ireturn        
        //   740: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   743: athrow         
        //   744: aload_0        
        //   745: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   750: astore_2       
        //   751: aload_0        
        //   752: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   755: ifeq            1102
        //   758: aload_1        
        //   759: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   762: ifeq            1102
        //   765: goto            772
        //   768: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   771: athrow         
        //   772: aload_2        
        //   773: ifnull          799
        //   776: goto            783
        //   779: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   782: athrow         
        //   783: aload_2        
        //   784: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isCpp:()Z
        //   789: ifne            835
        //   792: goto            799
        //   795: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   798: athrow         
        //   799: aload_0        
        //   800: ldc             "overloadable"
        //   802: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.hasAttribute:(Ljava/lang/String;)Z
        //   807: ifne            835
        //   810: goto            817
        //   813: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   816: athrow         
        //   817: aload_1        
        //   818: ldc             "overloadable"
        //   820: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.hasAttribute:(Ljava/lang/String;)Z
        //   825: ifeq            960
        //   828: goto            835
        //   831: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   834: athrow         
        //   835: aload_0        
        //   836: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   839: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   842: aload_1        
        //   843: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   846: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedQualifiedName:()Lcom/jetbrains/cidr/lang/symbols/OCQualifiedName;
        //   849: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   852: ifne            868
        //   855: goto            862
        //   858: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   861: athrow         
        //   862: iconst_0       
        //   863: ireturn        
        //   864: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   867: athrow         
        //   868: aload_0        
        //   869: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   872: ifeq            986
        //   875: aload_1        
        //   876: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   879: ifeq            986
        //   882: goto            889
        //   885: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   888: athrow         
        //   889: aload_0        
        //   890: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   893: aload_1        
        //   894: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   897: new             Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //   900: dup            
        //   901: aload_2        
        //   902: invokespecial   com/jetbrains/cidr/lang/symbols/OCResolveContext.<init>:(Lcom/intellij/psi/PsiElement;)V
        //   905: aload_0        
        //   906: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   909: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isTemplateSymbol:()Z
        //   912: ifne            939
        //   915: goto            922
        //   918: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   921: athrow         
        //   922: aload_1        
        //   923: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol;
        //   926: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isTemplateSymbol:()Z
        //   929: ifeq            947
        //   932: goto            939
        //   935: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   938: athrow         
        //   939: iconst_1       
        //   940: goto            948
        //   943: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   946: athrow         
        //   947: iconst_0       
        //   948: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.areSignaturesEqual:(Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;Z)Z
        //   951: ifne            986
        //   954: iconst_0       
        //   955: ireturn        
        //   956: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   959: athrow         
        //   960: aload_0        
        //   961: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   964: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   967: aload_1        
        //   968: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   971: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   974: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   977: ifne            986
        //   980: iconst_0       
        //   981: ireturn        
        //   982: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   985: athrow         
        //   986: aload_0        
        //   987: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   990: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   993: astore_3       
        //   994: aload_1        
        //   995: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //   998: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //  1001: astore          4
        //  1003: aload_3        
        //  1004: aload           4
        //  1006: if_acmpeq       1102
        //  1009: aload_3        
        //  1010: ifnull          1102
        //  1013: goto            1020
        //  1016: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1019: athrow         
        //  1020: aload           4
        //  1022: ifnull          1102
        //  1025: goto            1032
        //  1028: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1031: athrow         
        //  1032: aload_3        
        //  1033: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isPredeclaration:()Z
        //  1036: ifne            1102
        //  1039: goto            1046
        //  1042: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1045: athrow         
        //  1046: aload_3        
        //  1047: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1050: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //  1053: ifeq            1102
        //  1056: goto            1063
        //  1059: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1062: athrow         
        //  1063: aload           4
        //  1065: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.isPredeclaration:()Z
        //  1068: ifne            1102
        //  1071: goto            1078
        //  1074: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1077: athrow         
        //  1078: aload           4
        //  1080: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //  1083: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isStructLike:()Z
        //  1086: ifeq            1102
        //  1089: goto            1096
        //  1092: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1095: athrow         
        //  1096: iconst_0       
        //  1097: ireturn        
        //  1098: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1101: athrow         
        //  1102: aload_0        
        //  1103: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1106: ifeq            1167
        //  1109: aload_1        
        //  1110: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1113: ifeq            1167
        //  1116: goto            1123
        //  1119: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1122: athrow         
        //  1123: aload_0        
        //  1124: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1127: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //  1132: invokeinterface java/util/List.size:()I
        //  1137: aload_1        
        //  1138: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol;
        //  1141: invokeinterface com/jetbrains/cidr/lang/symbols/cpp/OCTemplateSymbol.getTemplateParameters:()Ljava/util/List;
        //  1146: invokeinterface java/util/List.size:()I
        //  1151: if_icmpeq       1167
        //  1154: goto            1161
        //  1157: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1160: athrow         
        //  1161: iconst_0       
        //  1162: ireturn        
        //  1163: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //  1166: athrow         
        //  1167: iconst_1       
        //  1168: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      10     10     14     Ljava/lang/IllegalArgumentException;
        //  14     35     35     39     Ljava/lang/IllegalArgumentException;
        //  39     68     71     75     Ljava/lang/IllegalArgumentException;
        //  53     84     87     91     Ljava/lang/IllegalArgumentException;
        //  75     100    103    107    Ljava/lang/IllegalArgumentException;
        //  91     114    117    121    Ljava/lang/IllegalArgumentException;
        //  107    128    131    135    Ljava/lang/IllegalArgumentException;
        //  121    147    150    154    Ljava/lang/IllegalArgumentException;
        //  135    166    169    173    Ljava/lang/IllegalArgumentException;
        //  154    175    175    179    Ljava/lang/IllegalArgumentException;
        //  179    195    195    199    Ljava/lang/IllegalArgumentException;
        //  200    216    216    220    Ljava/lang/IllegalArgumentException;
        //  221    226    226    230    Ljava/lang/IllegalArgumentException;
        //  230    244    247    251    Ljava/lang/IllegalArgumentException;
        //  237    275    278    282    Ljava/lang/IllegalArgumentException;
        //  251    299    302    306    Ljava/lang/IllegalArgumentException;
        //  282    308    308    312    Ljava/lang/IllegalArgumentException;
        //  312    326    329    333    Ljava/lang/IllegalArgumentException;
        //  319    360    363    367    Ljava/lang/IllegalArgumentException;
        //  333    369    369    373    Ljava/lang/IllegalArgumentException;
        //  373    387    390    394    Ljava/lang/IllegalArgumentException;
        //  380    412    415    419    Ljava/lang/IllegalArgumentException;
        //  394    421    421    425    Ljava/lang/IllegalArgumentException;
        //  425    439    442    446    Ljava/lang/IllegalArgumentException;
        //  432    467    470    474    Ljava/lang/IllegalArgumentException;
        //  446    476    476    480    Ljava/lang/IllegalArgumentException;
        //  480    499    502    506    Ljava/lang/IllegalArgumentException;
        //  487    513    516    520    Ljava/lang/IllegalArgumentException;
        //  506    532    535    539    Ljava/lang/IllegalArgumentException;
        //  520    541    541    545    Ljava/lang/IllegalArgumentException;
        //  545    559    562    566    Ljava/lang/IllegalArgumentException;
        //  552    573    576    580    Ljava/lang/IllegalArgumentException;
        //  566    587    590    594    Ljava/lang/IllegalArgumentException;
        //  580    612    615    619    Ljava/lang/IllegalArgumentException;
        //  594    621    621    625    Ljava/lang/IllegalArgumentException;
        //  625    638    638    642    Ljava/lang/IllegalArgumentException;
        //  642    667    670    674    Ljava/lang/IllegalArgumentException;
        //  649    676    676    680    Ljava/lang/IllegalArgumentException;
        //  680    699    702    706    Ljava/lang/IllegalArgumentException;
        //  687    708    708    712    Ljava/lang/IllegalArgumentException;
        //  712    731    734    738    Ljava/lang/IllegalArgumentException;
        //  719    740    740    744    Ljava/lang/IllegalArgumentException;
        //  751    765    768    772    Ljava/lang/IllegalArgumentException;
        //  758    776    779    783    Ljava/lang/IllegalArgumentException;
        //  772    792    795    799    Ljava/lang/IllegalArgumentException;
        //  783    810    813    817    Ljava/lang/IllegalArgumentException;
        //  799    828    831    835    Ljava/lang/IllegalArgumentException;
        //  817    855    858    862    Ljava/lang/IllegalArgumentException;
        //  835    864    864    868    Ljava/lang/IllegalArgumentException;
        //  868    882    885    889    Ljava/lang/IllegalArgumentException;
        //  875    915    918    922    Ljava/lang/IllegalArgumentException;
        //  889    932    935    939    Ljava/lang/IllegalArgumentException;
        //  922    943    943    947    Ljava/lang/IllegalArgumentException;
        //  948    956    956    960    Ljava/lang/IllegalArgumentException;
        //  960    982    982    986    Ljava/lang/IllegalArgumentException;
        //  1003   1013   1016   1020   Ljava/lang/IllegalArgumentException;
        //  1009   1025   1028   1032   Ljava/lang/IllegalArgumentException;
        //  1020   1039   1042   1046   Ljava/lang/IllegalArgumentException;
        //  1032   1056   1059   1063   Ljava/lang/IllegalArgumentException;
        //  1046   1071   1074   1078   Ljava/lang/IllegalArgumentException;
        //  1063   1089   1092   1096   Ljava/lang/IllegalArgumentException;
        //  1078   1098   1098   1102   Ljava/lang/IllegalArgumentException;
        //  1102   1116   1119   1123   Ljava/lang/IllegalArgumentException;
        //  1109   1154   1157   1161   Ljava/lang/IllegalArgumentException;
        //  1123   1163   1163   1167   Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0075:
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
    
    private static int a(final List<? extends OCSymbol> list, final int n) {
        final int n2;
        final int n3;
        final int binarySearch = Collections.binarySearch(list, (Object)null, (ocSymbol, ocSymbol2) -> {
            Label_0018_1: {
                try {
                    if (ocSymbol == null) {
                        break Label_0018_1;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                ocSymbol.getOffset();
            }
            try {
                if (ocSymbol2 == null) {
                    return n2 - n3;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocSymbol2.getOffset();
            return n2 - n3;
        });
        try {
            if (binarySearch >= 0) {
                return binarySearch;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return -binarySearch - 1;
    }
    
    private static int c(final List<? extends OCSymbol> p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1        
        //     1: iconst_m1      
        //     2: if_icmpne       13
        //     5: iconst_0       
        //     6: goto            32
        //     9: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    12: athrow         
        //    13: aload_0        
        //    14: iload_1        
        //    15: ifne            26
        //    18: iconst_0       
        //    19: goto            29
        //    22: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    25: athrow         
        //    26: iload_1        
        //    27: iconst_1       
        //    28: iadd           
        //    29: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/util/List;I)I
        //    32: istore_2       
        //    33: iload_2        
        //    34: ifle            95
        //    37: aload_0        
        //    38: iload_2        
        //    39: iconst_1       
        //    40: isub           
        //    41: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    46: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //    49: ifeq            95
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: aload_0        
        //    60: iload_2        
        //    61: iconst_1       
        //    62: isub           
        //    63: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    68: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //    71: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol.getLastElementOffset:()I
        //    74: iload_1        
        //    75: if_icmplt       95
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: iinc            2, -1
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: iload_2        
        //    96: ireturn        
        //    Signature:
        //  (Ljava/util/List<+Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;I)I
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      9      9      13     Ljava/lang/IllegalArgumentException;
        //  13     22     22     26     Ljava/lang/IllegalArgumentException;
        //  33     52     55     59     Ljava/lang/IllegalArgumentException;
        //  37     78     81     85     Ljava/lang/IllegalArgumentException;
        //  59     88     91     95     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    private static int b(final List<? extends OCSymbol> list, final int n) {
        try {
            if (n == -1) {
                return list.size();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a(list, n + 1);
    }
    
    public static boolean processSymbolsFromList(final Processor<OCSymbol> processor, final List<? extends OCSymbol> list, final int n, final int n2) {
        final int c = c(list, n);
        final int b = b(list, n2);
        int n3 = c;
        while (true) {
            Label_0058: {
                try {
                    if (n3 >= b) {
                        break;
                    }
                    ProgressManager.checkCanceled();
                    final Processor<OCSymbol> processor2 = processor;
                    final List<? extends OCSymbol> list2 = list;
                    final int n4 = n3;
                    final OCSymbol ocSymbol = (OCSymbol)list2.get(n4);
                    final boolean b2 = processor2.process((Object)ocSymbol);
                    if (!b2) {
                        return false;
                    }
                    break Label_0058;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    ProgressManager.checkCanceled();
                    final Processor<OCSymbol> processor2 = processor;
                    final List<? extends OCSymbol> list2 = list;
                    final int n4 = n3;
                    final OCSymbol ocSymbol = (OCSymbol)list2.get(n4);
                    final boolean b2 = processor2.process((Object)ocSymbol);
                    if (!b2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            ++n3;
        }
        return true;
    }
    
    public static boolean processSymbolsFromTwoLists(final Processor<OCSymbol> p0, final List<? extends OCSymbol> p1, final List<? extends OCSymbol> p2, final int p3, final int p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: iload_3        
        //     2: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.c:(Ljava/util/List;I)I
        //     5: istore          5
        //     7: aload_1        
        //     8: iload           4
        //    10: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.b:(Ljava/util/List;I)I
        //    13: istore          6
        //    15: aload_2        
        //    16: iload_3        
        //    17: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.c:(Ljava/util/List;I)I
        //    20: istore          7
        //    22: aload_2        
        //    23: iload           4
        //    25: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.b:(Ljava/util/List;I)I
        //    28: istore          8
        //    30: iload           5
        //    32: istore          9
        //    34: iload           7
        //    36: istore          10
        //    38: iload           9
        //    40: iload           6
        //    42: if_icmplt       59
        //    45: iload           10
        //    47: iload           8
        //    49: if_icmpge       213
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: invokestatic    com/intellij/openapi/progress/ProgressManager.checkCanceled:()V
        //    62: iload           9
        //    64: iload           6
        //    66: if_icmpge       94
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_1        
        //    77: iload           9
        //    79: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    84: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    87: goto            95
        //    90: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    93: athrow         
        //    94: aconst_null    
        //    95: astore          11
        //    97: iload           10
        //    99: iload           8
        //   101: if_icmpge       122
        //   104: aload_2        
        //   105: iload           10
        //   107: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   112: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   115: goto            123
        //   118: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   121: athrow         
        //   122: aconst_null    
        //   123: astore          12
        //   125: aload           11
        //   127: ifnull          186
        //   130: aload           12
        //   132: ifnull          176
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   141: athrow         
        //   142: aload           12
        //   144: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   149: aload           11
        //   151: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getOffset:()I
        //   156: if_icmpge       176
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload           12
        //   168: astore          13
        //   170: iinc            10, 1
        //   173: goto            193
        //   176: aload           11
        //   178: astore          13
        //   180: iinc            9, 1
        //   183: goto            193
        //   186: aload           12
        //   188: astore          13
        //   190: iinc            10, 1
        //   193: aload_0        
        //   194: aload           13
        //   196: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
        //   201: ifne            210
        //   204: iconst_0       
        //   205: ireturn        
        //   206: invokestatic    com/jetbrains/cidr/lang/resolve/OCResolveUtil.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   209: athrow         
        //   210: goto            38
        //   213: iconst_1       
        //   214: ireturn        
        //    Signature:
        //  (Lcom/intellij/util/Processor<Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Ljava/util/List<+Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;Ljava/util/List<+Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;II)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  38     52     55     59     Ljava/lang/IllegalArgumentException;
        //  45     69     72     76     Ljava/lang/IllegalArgumentException;
        //  59     90     90     94     Ljava/lang/IllegalArgumentException;
        //  97     118    118    122    Ljava/lang/IllegalArgumentException;
        //  125    135    138    142    Ljava/lang/IllegalArgumentException;
        //  130    159    162    166    Ljava/lang/IllegalArgumentException;
        //  193    206    206    210    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0059:
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
    
    public static <T extends OCSymbol> boolean processMap(final Processor<? super T> processor, @Nullable final String s, final MostlySingularMultiMap<String, T> mostlySingularMultiMap) {
        try {
            if (s != null) {
                return mostlySingularMultiMap.processForKey((Object)s, (Processor)processor);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mostlySingularMultiMap.processAllValues((Processor)processor);
    }
    
    @NotNull
    public static Collection<OCSymbol> resolveTemplateDeclarations(@NotNull final OCNamespaceQualifierOwner ocNamespaceQualifierOwner) {
        try {
            if (ocNamespaceQualifierOwner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "resolveTemplateDeclarations"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<OCSymbol> resolveToSymbols;
        try {
            resolveToSymbols = OCSymbolReference.getLocalReference(ocNamespaceQualifierOwner, OCSymbolReference.SymbolFilter.NONE).resolveToSymbols(false, false, false, new OCResolveContext((PsiElement)ocNamespaceQualifierOwner));
            if (resolveToSymbols == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "resolveTemplateDeclarations"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return resolveToSymbols;
    }
    
    @Nullable
    public static OCFunctionSymbol resolveCtorCall(@NotNull final OCDeclarator ocDeclarator) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "resolveCtorCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)ocDeclarator);
        final OCType resolve = ocDeclarator.getType().resolve(ocResolveContext);
        try {
            if (!(resolve instanceof OCStructType)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return resolveCtorCall(ocDeclarator.getInitializers(), (OCStructType)resolve, ocResolveContext);
    }
    
    @Nullable
    public static OCFunctionSymbol resolveCtorCall(@NotNull final List<OCExpression> list, @NotNull final OCStructType ocStructType, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializers", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "resolveCtorCall"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocStructType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structType", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "resolveCtorCall"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/OCResolveUtil", "resolveCtorCall"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final ArrayList<OCType> list2 = new ArrayList<OCType>();
        final Iterator<OCExpression> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(iterator.next().getType().resolve(ocResolveContext));
        }
        final OCSymbol constructor = ocStructType.findConstructor(new OCArgumentsList<Object>(list2, list), ocResolveContext, true, false, null);
        try {
            if (constructor instanceof OCFunctionSymbol) {
                return (OCFunctionSymbol)constructor;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        return null;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCResolveUtil.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        OCResolveUtil.DISABLE_LOCAL_SYMBOL_TABLE = (Key<Boolean>)Key.create("DISABLE_LOCAL_SYMBOL_TABLE");
        OCResolveUtil.stopSet = Arrays.asList(OCFunctionDefinition.class, OCMethod.class);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class ResolveFilteringProcessor<T extends OCSymbol> implements Processor<T>
    {
        private Processor<? super T> myProcessor;
        private PsiFile myFile;
        private int myTextOffset;
        private final VirtualFile myVirtualFile;
        private boolean myIgnoringImports;
        
        public ResolveFilteringProcessor(final Processor<? super T> myProcessor, @Nullable PsiFile containingFile, final int myTextOffset, final boolean myIgnoringImports) {
            this.myProcessor = myProcessor;
            this.myFile = containingFile;
            if (containingFile instanceof OCCodeFragment) {
                final PsiElement context = containingFile.getContext();
                if (context != null) {
                    containingFile = context.getContainingFile();
                    this.myTextOffset = context.getTextOffset();
                }
            }
            else {
                this.myTextOffset = myTextOffset;
            }
            this.myVirtualFile = OCInclusionContextUtil.getVirtualFile(containingFile);
            this.myIgnoringImports = myIgnoringImports;
        }
        
        public ResolveFilteringProcessor(final Processor<? super T> processor, final PsiElement psiElement) {
            this(processor, psiElement.getContainingFile(), psiElement.getTextOffset(), false);
        }
        
        public boolean process(final T t) {
            final OCSymbolKind kind = t.getKind();
            return (kind != OCSymbolKind.LABEL && kind != OCSymbolKind.FUNCTION_DECLARATION && !kind.isStructLike() && kind != OCSymbolKind.PARAMETER && kind != OCSymbolKind.STRUCT_FIELD && kind != OCSymbolKind.ENUM_CONST && kind != OCSymbolKind.SYMBOL_USING_SYMBOL && kind != OCSymbolKind.TEMPLATE_TYPE_PARAMETER && (!(t instanceof OCInstanceVariableSymbol) || ((OCInstanceVariableSymbol)t).getGeneratedFromProperty() == null) && !this.myIgnoringImports && (!OCResolveUtil.isInSameStructInCode(t, this.myTextOffset) || (kind == OCSymbolKind.TYPEDEF && !a(this.myTextOffset, this.myFile))) && !OCResolveUtil.isEarlierInCode(t, this.myVirtualFile, this.myTextOffset) && (this.myFile == null || !a(t, this.myFile, this.myVirtualFile, this.myTextOffset))) || (this.myFile != null && OCResolveUtil.isDisabledSymbol(t, this.myFile)) || this.myProcessor.process((Object)t);
        }
    }
}
