// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.quickfixes.OCRemoveElementsIntentionAction;
import java.util.Iterator;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.lang.annotation.AnnotationHolder;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.lang.annotation.AnnotationSession;
import com.jetbrains.cidr.lang.daemon.OCErrorAnnotator;
import com.jetbrains.cidr.lang.daemon.OCResolveAnnotator;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import com.intellij.lang.ImportOptimizer;

public class OCImportsOptimizer implements ImportOptimizer
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public boolean supports(final PsiFile psiFile) {
        return psiFile instanceof OCFile;
    }
    
    @NotNull
    public Runnable processFile(final PsiFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //     4: ifeq            26
        //     7: aload_1        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
        //    16: ifeq            75
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    25: athrow         
        //    26: getstatic       com/intellij/openapi/util/EmptyRunnable.INSTANCE:Ljava/lang/Runnable;
        //    29: dup            
        //    30: ifnonnull       74
        //    33: goto            40
        //    36: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    39: athrow         
        //    40: new             Ljava/lang/IllegalStateException;
        //    43: dup            
        //    44: ldc             "@NotNull method %s.%s must not return null"
        //    46: ldc             2
        //    48: anewarray       Ljava/lang/Object;
        //    51: dup            
        //    52: ldc             0
        //    54: ldc             "com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer"
        //    56: aastore        
        //    57: dup            
        //    58: ldc             1
        //    60: ldc             "processFile"
        //    62: aastore        
        //    63: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    66: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    69: athrow         
        //    70: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    73: athrow         
        //    74: areturn        
        //    75: aload_1        
        //    76: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    79: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.getUnusedImports:(Lcom/jetbrains/cidr/lang/psi/OCFile;)Ljava/util/List;
        //    82: astore_2       
        //    83: new             Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;
        //    86: dup            
        //    87: aload_2        
        //    88: ldc             ""
        //    90: ldc             ""
        //    92: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction.<init>:(Ljava/util/Collection;Ljava/lang/String;Ljava/lang/String;)V
        //    95: astore_3       
        //    96: aload_3        
        //    97: aload_1        
        //    98: invokedynamic   run:(Lcom/jetbrains/cidr/lang/quickfixes/OCRemoveElementsIntentionAction;Lcom/intellij/psi/PsiFile;)Ljava/lang/Runnable;
        //   103: dup            
        //   104: ifnonnull       141
        //   107: new             Ljava/lang/IllegalStateException;
        //   110: dup            
        //   111: ldc             "@NotNull method %s.%s must not return null"
        //   113: ldc             2
        //   115: anewarray       Ljava/lang/Object;
        //   118: dup            
        //   119: ldc             0
        //   121: ldc             "com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer"
        //   123: aastore        
        //   124: dup            
        //   125: ldc             1
        //   127: ldc             "processFile"
        //   129: aastore        
        //   130: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   133: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   136: athrow         
        //   137: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   140: athrow         
        //   141: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      19     22     26     Ljava/lang/IllegalStateException;
        //  7      33     36     40     Ljava/lang/IllegalStateException;
        //  26     70     70     74     Ljava/lang/IllegalStateException;
        //  96     137    137    141    Ljava/lang/IllegalStateException;
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
    
    public static List<OCIncludeDirective> getUnusedImports(final OCFile ocFile) {
        return a(ocFile, (Collection<? extends PsiElement>)Collections.singletonList(ocFile), false, true);
    }
    
    public static List<OCIncludeDirective> getUnusedImports(final OCFile ocFile, final Collection<? extends PsiElement> collection) {
        try {
            if (collection.isEmpty()) {
                return Collections.emptyList();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return a(ocFile, collection, false, false);
    }
    
    public static List<OCIncludeDirective> getUsedImports(final OCFile ocFile, final Collection<? extends PsiElement> collection) {
        try {
            if (collection.isEmpty()) {
                return Collections.emptyList();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return a(ocFile, collection, true, false);
    }
    
    private static List<OCIncludeDirective> a(final OCFile ocFile, final Collection<? extends PsiElement> collection, final boolean b, final boolean b2) {
        final OCResolveAnnotator ocResolveAnnotator = new OCResolveAnnotator();
        final OCErrorAnnotator ocErrorAnnotator = new OCErrorAnnotator();
        final AnnotationSession annotationSession = new AnnotationSession((PsiFile)ocFile);
        ocFile.pushAnnotationSession(annotationSession);
        for (final PsiElement psiElement : collection) {
            try {
                if (psiElement instanceof OCSymbolHolderVirtualPsiElement) {
                    OCFileSymbols.markImportNeeded(ocFile, ((OCSymbolHolderVirtualPsiElement)psiElement).getSymbol());
                    continue;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            Label_0117: {
                try {
                    if (OCImportsOptimizer.$assertionsDisabled) {
                        break Label_0117;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final PsiFile psiFile = psiElement2.getContainingFile();
                    final OCFile ocFile2 = ocFile;
                    if (psiFile != ocFile2) {
                        break Label_0117;
                    }
                    break Label_0117;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final PsiFile psiFile = psiElement2.getContainingFile();
                    final OCFile ocFile2 = ocFile;
                    if (psiFile != ocFile2) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            psiElement.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitElement(final PsiElement psiElement) {
                    super.visitElement(psiElement);
                    ocResolveAnnotator.annotate(psiElement, null, annotationSession);
                    ocErrorAnnotator.annotate(psiElement, null, annotationSession);
                }
            });
        }
        final List<OCIncludeDirective> doGetImports = doGetImports(ocFile, b, b2);
        ocFile.popAnnotationSession();
        return doGetImports;
    }
    
    public static List<OCIncludeDirective> doGetImports(final OCFile p0, final boolean p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Ljava/util/ArrayList;
        //     3: dup            
        //     4: invokespecial   java/util/ArrayList.<init>:()V
        //     7: astore_3       
        //     8: new             Ljava/util/HashSet;
        //    11: dup            
        //    12: invokespecial   java/util/HashSet.<init>:()V
        //    15: astore          4
        //    17: aload_0        
        //    18: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.findIncludeDirectives:()Ljava/util/List;
        //    23: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    28: astore          5
        //    30: aload           5
        //    32: invokeinterface java/util/Iterator.hasNext:()Z
        //    37: ifeq            249
        //    40: aload           5
        //    42: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    47: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //    50: astore          6
        //    52: aload           6
        //    54: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    57: ifeq            30
        //    60: aload           6
        //    62: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.isTopLevel:()Z
        //    67: ifeq            30
        //    70: aload           6
        //    72: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    77: aload_0        
        //    78: invokevirtual   java/lang/Object.equals:(Ljava/lang/Object;)Z
        //    81: ifne            91
        //    84: goto            30
        //    87: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    90: athrow         
        //    91: aload           6
        //    93: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.getIncludedFile:()Lcom/intellij/psi/PsiFile;
        //    98: astore          7
        //   100: aload           7
        //   102: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getFilePath:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   105: astore          8
        //   107: aload           7
        //   109: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   112: ifeq            246
        //   115: aload           8
        //   117: ifnull          246
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   126: athrow         
        //   127: aload_0        
        //   128: aload           8
        //   130: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.isImportUsed:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   133: ifne            170
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   142: athrow         
        //   143: iload_2        
        //   144: ifeq            226
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   153: athrow         
        //   154: aload_0        
        //   155: aload           8
        //   157: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/OCFileSymbols.isImportRequired:(Lcom/jetbrains/cidr/lang/psi/OCFile;Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   160: ifeq            226
        //   163: goto            170
        //   166: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   169: athrow         
        //   170: aload           4
        //   172: aload           8
        //   174: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   179: ifne            226
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   188: athrow         
        //   189: aload           4
        //   191: aload           8
        //   193: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   198: pop            
        //   199: iload_1        
        //   200: ifeq            246
        //   203: goto            210
        //   206: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   209: athrow         
        //   210: aload_3        
        //   211: aload           6
        //   213: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   218: pop            
        //   219: goto            246
        //   222: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   225: athrow         
        //   226: iload_1        
        //   227: ifne            246
        //   230: aload_3        
        //   231: aload           6
        //   233: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   238: pop            
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/lang/refactoring/OCImportsOptimizer.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   245: athrow         
        //   246: goto            30
        //   249: aload_3        
        //   250: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCFile;ZZ)Ljava/util/List<Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  70     87     87     91     Ljava/lang/IllegalStateException;
        //  107    120    123    127    Ljava/lang/IllegalStateException;
        //  115    136    139    143    Ljava/lang/IllegalStateException;
        //  127    147    150    154    Ljava/lang/IllegalStateException;
        //  143    163    166    170    Ljava/lang/IllegalStateException;
        //  154    182    185    189    Ljava/lang/IllegalStateException;
        //  170    203    206    210    Ljava/lang/IllegalStateException;
        //  189    222    222    226    Ljava/lang/IllegalStateException;
        //  226    239    242    246    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0127:
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
                if (!OCImportsOptimizer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
