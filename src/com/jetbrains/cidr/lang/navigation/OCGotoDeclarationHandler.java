// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.navigation;

import java.util.Set;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.util.containers.hash.HashSet;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.intellij.util.Processor;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import java.util.Collection;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import org.jetbrains.annotations.Contract;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.navigation.actions.GotoDeclarationHandler;

public class OCGotoDeclarationHandler implements GotoDeclarationHandler
{
    @Nullable
    @Override
    public PsiElement[] getGotoDeclarationTargets(@Nullable final PsiElement p0, final int p1, final Editor p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnull          17
        //     4: aload_1        
        //     5: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    10: goto            18
        //    13: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aconst_null    
        //    18: astore          4
        //    20: aload           4
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    25: ifne            34
        //    28: aconst_null    
        //    29: areturn        
        //    30: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    33: athrow         
        //    34: new             Ljava/util/ArrayList;
        //    37: dup            
        //    38: invokespecial   java/util/ArrayList.<init>:()V
        //    41: astore          5
        //    43: aload_3        
        //    44: aload_1        
        //    45: iload_2        
        //    46: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;I)Ljava/util/Collection;
        //    49: astore          6
        //    51: aload           6
        //    53: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //    58: astore          7
        //    60: aload           7
        //    62: invokeinterface java/util/Iterator.hasNext:()Z
        //    67: ifeq            186
        //    70: aload           7
        //    72: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    77: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    80: astore          8
        //    82: aload           8
        //    84: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    89: astore          9
        //    91: aload           4
        //    93: aload_3        
        //    94: invokeinterface com/intellij/openapi/editor/Editor.getDocument:()Lcom/intellij/openapi/editor/Document;
        //    99: iload_2        
        //   100: invokestatic    com/intellij/codeInsight/TargetElementUtil.adjustOffset:(Lcom/intellij/psi/PsiFile;Lcom/intellij/openapi/editor/Document;I)I
        //   103: istore          10
        //   105: aload           9
        //   107: ifnull          183
        //   110: aload           9
        //   112: aload_1        
        //   113: iload           10
        //   115: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;I)Z
        //   118: ifne            183
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   127: athrow         
        //   128: aload           9
        //   130: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   133: ifeq            173
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: aload           4
        //   145: iload_2        
        //   146: invokeinterface com/intellij/psi/PsiFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   151: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   154: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   157: if_acmpeq       173
        //   160: goto            167
        //   163: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   166: athrow         
        //   167: aconst_null    
        //   168: areturn        
        //   169: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: aload           5
        //   175: aload           9
        //   177: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   182: pop            
        //   183: goto            60
        //   186: aload           5
        //   188: aload           5
        //   190: invokeinterface java/util/List.size:()I
        //   195: anewarray       Lcom/intellij/psi/PsiElement;
        //   198: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   203: checkcast       [Lcom/intellij/psi/PsiElement;
        //   206: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     13     17     Ljava/lang/IllegalArgumentException;
        //  20     30     30     34     Ljava/lang/IllegalArgumentException;
        //  105    121    124    128    Ljava/lang/IllegalArgumentException;
        //  110    136    139    143    Ljava/lang/IllegalArgumentException;
        //  128    160    163    167    Ljava/lang/IllegalArgumentException;
        //  143    169    169    173    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0128:
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
    
    @Contract("null,_,_ -> false; _,null,_ -> false")
    private static boolean a(@Nullable final PsiElement p0, @Nullable final PsiElement p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          65
        //     4: aload_1        
        //     5: ifnull          65
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    14: athrow         
        //    15: aload_0        
        //    16: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    21: iload_2        
        //    22: invokevirtual   com/intellij/openapi/util/TextRange.contains:(I)Z
        //    25: ifeq            65
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    34: athrow         
        //    35: aload_0        
        //    36: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    41: aload_1        
        //    42: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    47: if_acmpne       65
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: iconst_1       
        //    58: goto            66
        //    61: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    64: athrow         
        //    65: iconst_0       
        //    66: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      11     15     Ljava/lang/IllegalArgumentException;
        //  4      28     31     35     Ljava/lang/IllegalArgumentException;
        //  15     50     53     57     Ljava/lang/IllegalArgumentException;
        //  35     61     61     65     Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public String getActionText(final DataContext dataContext) {
        final Editor editor = (Editor)CommonDataKeys.EDITOR.getData(dataContext);
        final Project project = (Project)CommonDataKeys.PROJECT.getData(dataContext);
        try {
            if (editor == null || project == null) {
                return "Declaration";
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int offset = editor.getCaretModel().getOffset();
        final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(editor.getDocument());
        PsiElement element = null;
        Label_0094: {
            try {
                if (psiFile instanceof OCFile) {
                    element = psiFile.findElementAt(offset);
                    break Label_0094;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            element = null;
        }
        final PsiElement psiElement = element;
        if (psiElement != null) {
            final Collection<? extends OCSymbol> a = a(editor, psiElement, offset);
            try {
                if (a.isEmpty()) {
                    return "Declaration";
                }
                final Collection<? extends OCSymbol> collection = a;
                final Iterator<? extends OCSymbol> iterator = collection.iterator();
                final OCSymbol ocSymbol = (OCSymbol)iterator.next();
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = ocSymbol2.isDefinition();
                if (b) {
                    return "Definition";
                }
                return "Declaration";
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final Collection<? extends OCSymbol> collection = a;
                final Iterator<? extends OCSymbol> iterator = collection.iterator();
                final OCSymbol ocSymbol = (OCSymbol)iterator.next();
                final OCSymbol ocSymbol2 = ocSymbol;
                final boolean b = ocSymbol2.isDefinition();
                if (b) {
                    return "Definition";
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return "Declaration";
    }
    
    private static Collection<? extends OCSymbol> a(final Editor p0, @NotNull final PsiElement p1, final int p2) {
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
        //    18: ldc             "sourceElement"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getTargetSymbols"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: invokestatic    com/intellij/codeInsight/TargetElementUtil.getInstance:()Lcom/intellij/codeInsight/TargetElementUtil;
        //    47: aload_0        
        //    48: invokestatic    com/intellij/codeInsight/navigation/ImplementationSearcher.getFlags:()I
        //    51: sipush          4096
        //    54: ior            
        //    55: iload_2        
        //    56: invokevirtual   com/intellij/codeInsight/TargetElementUtil.findTargetElement:(Lcom/intellij/openapi/editor/Editor;II)Lcom/intellij/psi/PsiElement;
        //    59: astore_3       
        //    60: aload_3        
        //    61: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //    64: ifeq            79
        //    67: aload_3        
        //    68: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //    71: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;)Ljava/util/Collection;
        //    74: areturn        
        //    75: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    78: athrow         
        //    79: aload_1        
        //    80: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    85: astore          4
        //    87: aload           4
        //    89: ifnull          148
        //    92: aload           4
        //    94: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    99: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   102: if_acmpne       148
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   111: athrow         
        //   112: aload_1        
        //   113: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   118: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   121: ifeq            148
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   130: athrow         
        //   131: aload_1        
        //   132: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   137: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   140: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;)Ljava/util/Collection;
        //   143: areturn        
        //   144: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   147: athrow         
        //   148: aload_3        
        //   149: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   152: ifeq            171
        //   155: aload_3        
        //   156: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   159: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   164: goto            172
        //   167: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   170: athrow         
        //   171: aconst_null    
        //   172: astore          4
        //   174: aload           4
        //   176: ifnonnull       187
        //   179: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   182: areturn        
        //   183: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   186: athrow         
        //   187: aload           4
        //   189: instanceof      Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   192: ifeq            208
        //   195: aload           4
        //   197: checkcast       Lcom/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol;
        //   200: invokevirtual   com/jetbrains/cidr/lang/resolve/OCResolveOverloadsUtil$OCFunctionGroupSymbol.getOverloads:()Ljava/util/List;
        //   203: areturn        
        //   204: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   207: athrow         
        //   208: new             Lcom/intellij/util/containers/hash/HashSet;
        //   211: dup            
        //   212: invokespecial   com/intellij/util/containers/hash/HashSet.<init>:()V
        //   215: astore          5
        //   217: aload_3        
        //   218: aload_1        
        //   219: iload_2        
        //   220: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;I)Z
        //   223: istore          6
        //   225: aload           4
        //   227: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isCallable:()Z
        //   232: ifne            247
        //   235: iload           6
        //   237: ifeq            796
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: aconst_null    
        //   248: astore          7
        //   250: aload           4
        //   252: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   255: ifeq            331
        //   258: aload_0        
        //   259: iload_2        
        //   260: invokestatic    com/intellij/codeInsight/TargetElementUtil.findReference:(Lcom/intellij/openapi/editor/Editor;I)Lcom/intellij/psi/PsiReference;
        //   263: astore          8
        //   265: aload           8
        //   267: ifnull          331
        //   270: aload           8
        //   272: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   277: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   280: ifeq            331
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: aload           8
        //   292: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   297: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   300: astore          9
        //   302: aload           9
        //   304: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverContext:()Lcom/jetbrains/cidr/lang/types/OCObjectTypeContext;
        //   309: astore          10
        //   311: aload           10
        //   313: ifnull          331
        //   316: aload           10
        //   318: aload           9
        //   320: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //   325: iconst_0       
        //   326: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectTypeContext.getKnownResponder:(Ljava/lang/String;Z)Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   329: astore          7
        //   331: aload           4
        //   333: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   336: ifeq            393
        //   339: aload           4
        //   341: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   344: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   349: ifnull          393
        //   352: goto            359
        //   355: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   358: athrow         
        //   359: aload           4
        //   361: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   364: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getMainInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   369: astore          8
        //   371: aload           8
        //   373: ifnull          393
        //   376: aload           5
        //   378: aload           8
        //   380: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   385: pop            
        //   386: goto            393
        //   389: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   392: athrow         
        //   393: aload           7
        //   395: ifnonnull       407
        //   398: aload           4
        //   400: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getDefinitionSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   405: astore          7
        //   407: aload           7
        //   409: aload           4
        //   411: if_acmpne       731
        //   414: iload           6
        //   416: ifeq            731
        //   419: goto            426
        //   422: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   425: athrow         
        //   426: aload_3        
        //   427: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   430: ifeq            456
        //   433: goto            440
        //   436: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   439: athrow         
        //   440: aload_3        
        //   441: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   444: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //   449: goto            457
        //   452: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   455: athrow         
        //   456: aconst_null    
        //   457: astore          8
        //   459: aload           8
        //   461: ifnull          478
        //   464: aload           8
        //   466: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier.resolveToSymbols:()Ljava/util/List;
        //   471: goto            481
        //   474: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   477: athrow         
        //   478: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   481: astore          9
        //   483: aload           9
        //   485: invokeinterface java/util/List.size:()I
        //   490: iconst_1       
        //   491: if_icmpne       512
        //   494: aload           9
        //   496: iconst_0       
        //   497: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   502: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   505: goto            513
        //   508: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   511: athrow         
        //   512: aconst_null    
        //   513: astore          10
        //   515: aload           10
        //   517: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   520: ifeq            545
        //   523: aload           10
        //   525: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   528: aload           4
        //   530: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   535: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.findMember:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   538: goto            546
        //   541: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   544: athrow         
        //   545: aconst_null    
        //   546: astore          11
        //   548: aload           11
        //   550: ifnonnull       562
        //   553: aload           4
        //   555: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   560: astore          11
        //   562: aload           11
        //   564: ifnull          641
        //   567: aload           4
        //   569: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   572: ifeq            624
        //   575: goto            582
        //   578: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   581: athrow         
        //   582: ldc             ""
        //   584: aload           4
        //   586: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   589: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   594: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   597: ifeq            624
        //   600: goto            607
        //   603: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   606: athrow         
        //   607: aload           5
        //   609: invokeinterface java/util/Set.isEmpty:()Z
        //   614: ifeq            641
        //   617: goto            624
        //   620: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   623: athrow         
        //   624: aload           5
        //   626: aload           11
        //   628: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   633: pop            
        //   634: goto            641
        //   637: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   640: athrow         
        //   641: aload           4
        //   643: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   646: ifeq            728
        //   649: new             Lcom/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler$1;
        //   652: dup            
        //   653: invokespecial   com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler$1.<init>:()V
        //   656: astore          12
        //   658: aload           4
        //   660: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   663: aload           12
        //   665: iconst_1       
        //   666: iconst_0       
        //   667: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.processMembersHierarchy:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/intellij/util/Processor;ZZ)Z
        //   670: pop            
        //   671: aload           12
        //   673: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
        //   676: ifeq            699
        //   679: aload           5
        //   681: aload           12
        //   683: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   686: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   691: pop            
        //   692: goto            699
        //   695: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   698: athrow         
        //   699: aload           4
        //   701: invokestatic    com/jetbrains/cidr/lang/navigation/OCLineMarkerProvider.getRelatedSymbol:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   704: astore          13
        //   706: aload           13
        //   708: ifnull          728
        //   711: aload           5
        //   713: aload           13
        //   715: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   720: pop            
        //   721: goto            728
        //   724: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   727: athrow         
        //   728: goto            796
        //   731: aload           7
        //   733: ifnull          753
        //   736: aload           5
        //   738: aload           7
        //   740: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   745: pop            
        //   746: goto            796
        //   749: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   752: athrow         
        //   753: aload           4
        //   755: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   758: ifeq            796
        //   761: new             Lcom/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler$2;
        //   764: dup            
        //   765: invokespecial   com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler$2.<init>:()V
        //   768: astore          8
        //   770: aload           4
        //   772: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMemberSymbol;
        //   775: aload           8
        //   777: iconst_0       
        //   778: iconst_1       
        //   779: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.processMembersHierarchy:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;Lcom/intellij/util/Processor;ZZ)Z
        //   782: pop            
        //   783: aload           5
        //   785: aload           8
        //   787: invokevirtual   com/intellij/util/CommonProcessors$CollectProcessor.getResults:()Ljava/util/Collection;
        //   790: invokeinterface java/util/Set.addAll:(Ljava/util/Collection;)Z
        //   795: pop            
        //   796: aload           4
        //   798: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCLocalizedStringSymbol;
        //   801: ifeq            826
        //   804: aload           4
        //   806: aload           5
        //   808: invokedynamic   process:(Ljava/util/Set;)Lcom/intellij/util/Processor;
        //   813: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.processSameSymbols:(Lcom/intellij/util/Processor;)Z
        //   818: pop            
        //   819: goto            826
        //   822: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   825: athrow         
        //   826: aload           4
        //   828: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   831: ifeq            966
        //   834: aload           4
        //   836: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   839: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getGeneratedFromProperty:()Ljava/lang/String;
        //   844: ifnull          966
        //   847: goto            854
        //   850: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   853: athrow         
        //   854: aload           4
        //   856: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   861: astore          7
        //   863: aload           4
        //   865: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   868: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.getAssociatedProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   873: astore          8
        //   875: aload           8
        //   877: ifnull          966
        //   880: aload           4
        //   882: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //   885: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol.isClang4ImplicitIvar:()Z
        //   890: ifne            962
        //   893: goto            900
        //   896: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   899: athrow         
        //   900: aload           7
        //   902: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   905: ifeq            966
        //   908: goto            915
        //   911: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   914: athrow         
        //   915: aload           7
        //   917: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   922: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   925: ifeq            966
        //   928: goto            935
        //   931: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   934: athrow         
        //   935: aload           7
        //   937: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   942: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //   945: invokeinterface com/jetbrains/cidr/lang/psi/OCSynthesizeProperty.getPropertyRef:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   950: aload           7
        //   952: if_acmpne       966
        //   955: goto            962
        //   958: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   961: athrow         
        //   962: aload           8
        //   964: astore          4
        //   966: aload           5
        //   968: invokeinterface java/util/Set.isEmpty:()Z
        //   973: ifeq            993
        //   976: aload           5
        //   978: aload           4
        //   980: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   985: pop            
        //   986: goto            993
        //   989: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   992: athrow         
        //   993: aload           5
        //   995: areturn        
        //    Signature:
        //  (Lcom/intellij/openapi/editor/Editor;Lcom/intellij/psi/PsiElement;I)Ljava/util/Collection<+Lcom/jetbrains/cidr/lang/symbols/OCSymbol;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  60     75     75     79     Ljava/lang/IllegalArgumentException;
        //  87     105    108    112    Ljava/lang/IllegalArgumentException;
        //  92     124    127    131    Ljava/lang/IllegalArgumentException;
        //  112    144    144    148    Ljava/lang/IllegalArgumentException;
        //  148    167    167    171    Ljava/lang/IllegalArgumentException;
        //  174    183    183    187    Ljava/lang/IllegalArgumentException;
        //  187    204    204    208    Ljava/lang/IllegalArgumentException;
        //  225    240    243    247    Ljava/lang/IllegalArgumentException;
        //  265    283    286    290    Ljava/lang/IllegalArgumentException;
        //  331    352    355    359    Ljava/lang/IllegalArgumentException;
        //  371    386    389    393    Ljava/lang/IllegalArgumentException;
        //  407    419    422    426    Ljava/lang/IllegalArgumentException;
        //  414    433    436    440    Ljava/lang/IllegalArgumentException;
        //  426    452    452    456    Ljava/lang/IllegalArgumentException;
        //  459    474    474    478    Ljava/lang/IllegalArgumentException;
        //  483    508    508    512    Ljava/lang/IllegalArgumentException;
        //  515    541    541    545    Ljava/lang/IllegalArgumentException;
        //  562    575    578    582    Ljava/lang/IllegalArgumentException;
        //  567    600    603    607    Ljava/lang/IllegalArgumentException;
        //  582    617    620    624    Ljava/lang/IllegalArgumentException;
        //  607    634    637    641    Ljava/lang/IllegalArgumentException;
        //  658    692    695    699    Ljava/lang/IllegalArgumentException;
        //  706    721    724    728    Ljava/lang/IllegalArgumentException;
        //  731    749    749    753    Ljava/lang/IllegalArgumentException;
        //  796    819    822    826    Ljava/lang/IllegalArgumentException;
        //  826    847    850    854    Ljava/lang/IllegalArgumentException;
        //  875    893    896    900    Ljava/lang/IllegalArgumentException;
        //  880    908    911    915    Ljava/lang/IllegalArgumentException;
        //  900    928    931    935    Ljava/lang/IllegalArgumentException;
        //  915    955    958    962    Ljava/lang/IllegalArgumentException;
        //  966    986    989    993    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0112:
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
    
    private static ArrayList<OCSymbol> a(final OCStructSymbol ocStructSymbol) {
        final ArrayList<OCSymbol> list = new ArrayList<OCSymbol>();
        final OCSymbol[] array = { null };
        final OCSymbol[] array2 = { null };
        Label_0059: {
            try {
                if (ocStructSymbol.isSpecialization()) {
                    ocStructSymbol.processSameSymbols((Processor<OCSymbol>)(p4 -> {
                        // 
                        // This method could not be decompiled.
                        // 
                        // Original Bytecode:
                        // 
                        //     0: aload           4
                        //     2: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                        //     5: ifeq            97
                        //     8: aload           4
                        //    10: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                        //    13: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSpecialization:()Z
                        //    16: ifeq            57
                        //    19: goto            26
                        //    22: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    25: athrow         
                        //    26: aload           4
                        //    28: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
                        //    33: ifne            97
                        //    36: goto            43
                        //    39: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    42: athrow         
                        //    43: aload_0        
                        //    44: aload           4
                        //    46: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
                        //    49: pop            
                        //    50: goto            97
                        //    53: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    56: athrow         
                        //    57: aload           4
                        //    59: aload_1        
                        //    60: if_acmpeq       97
                        //    63: aload           4
                        //    65: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
                        //    70: ifeq            92
                        //    73: goto            80
                        //    76: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    79: athrow         
                        //    80: aload_2        
                        //    81: iconst_0       
                        //    82: aload           4
                        //    84: aastore        
                        //    85: goto            97
                        //    88: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                        //    91: athrow         
                        //    92: aload_3        
                        //    93: iconst_0       
                        //    94: aload           4
                        //    96: aastore        
                        //    97: iconst_1       
                        //    98: ireturn        
                        //    Exceptions:
                        //  Try           Handler
                        //  Start  End    Start  End    Type                                
                        //  -----  -----  -----  -----  ------------------------------------
                        //  0      19     22     26     Ljava/lang/IllegalArgumentException;
                        //  8      36     39     43     Ljava/lang/IllegalArgumentException;
                        //  26     53     53     57     Ljava/lang/IllegalArgumentException;
                        //  57     73     76     80     Ljava/lang/IllegalArgumentException;
                        //  63     88     88     92     Ljava/lang/IllegalArgumentException;
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
                    }));
                    break Label_0059;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ocStructSymbol.processSameSymbols((Processor<OCSymbol>)(p3 -> {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_3        
                //     1: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                //     4: ifeq            67
                //     7: aload_3        
                //     8: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
                //    11: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.isSpecialization:()Z
                //    14: ifne            67
                //    17: goto            24
                //    20: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    23: athrow         
                //    24: aload_3        
                //    25: aload_0        
                //    26: if_acmpeq       67
                //    29: goto            36
                //    32: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    35: athrow         
                //    36: aload_3        
                //    37: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
                //    42: ifeq            63
                //    45: goto            52
                //    48: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    51: athrow         
                //    52: aload_1        
                //    53: iconst_0       
                //    54: aload_3        
                //    55: aastore        
                //    56: goto            67
                //    59: invokestatic    com/jetbrains/cidr/lang/navigation/OCGotoDeclarationHandler.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    62: athrow         
                //    63: aload_2        
                //    64: iconst_0       
                //    65: aload_3        
                //    66: aastore        
                //    67: iconst_1       
                //    68: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      17     20     24     Ljava/lang/IllegalArgumentException;
                //  7      29     32     36     Ljava/lang/IllegalArgumentException;
                //  24     45     48     52     Ljava/lang/IllegalArgumentException;
                //  36     59     59     63     Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
            }));
            try {
                if (array2[0] != null) {
                    list.add(0, array2[0]);
                    return (ArrayList<OCSymbol>)list;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        try {
            if (array[0] != null) {
                list.add(array[0]);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return (ArrayList<OCSymbol>)list;
    }
    
    private static Collection<OCSymbol> a(final OCCppNamespaceQualifier ocCppNamespaceQualifier) {
        Collection<OCSymbol> collection;
        if (ocCppNamespaceQualifier.getParent() instanceof OCReferenceElement) {
            collection = ((OCReferenceElement)ocCppNamespaceQualifier.getParent()).resolveToOverloadsSymbols();
        }
        else {
            if (!(ocCppNamespaceQualifier.getParent() instanceof OCCppNamespaceQualifier)) {
                return (Collection<OCSymbol>)Collections.emptyList();
            }
            collection = a((OCCppNamespaceQualifier)ocCppNamespaceQualifier.getParent());
        }
        final HashSet set = new HashSet();
        for (final OCSymbol ocSymbol : ocCppNamespaceQualifier.resolveToSymbols()) {
            for (final OCSymbol ocSymbol2 : collection) {
                Label_0168: {
                    try {
                        if (!(ocSymbol2 instanceof OCSymbolWithQualifiedName)) {
                            continue;
                        }
                        final OCSymbol ocSymbol3 = ocSymbol;
                        final OCSymbol ocSymbol4 = ocSymbol2;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol4;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName.getParent();
                        final boolean b = ocSymbol3.equals(ocSymbolWithQualifiedName2);
                        if (b) {
                            break Label_0168;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCSymbol ocSymbol3 = ocSymbol;
                        final OCSymbol ocSymbol4 = ocSymbol2;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName = (OCSymbolWithQualifiedName)ocSymbol4;
                        final OCSymbolWithQualifiedName ocSymbolWithQualifiedName2 = ocSymbolWithQualifiedName.getParent();
                        final boolean b = ocSymbol3.equals(ocSymbolWithQualifiedName2);
                        if (!b) {
                            continue;
                        }
                        set.add((Object)ocSymbol);
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
            }
        }
        return (Collection<OCSymbol>)set;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
