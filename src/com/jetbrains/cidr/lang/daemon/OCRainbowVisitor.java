// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCThisSelfSuperSymbol;
import com.jetbrains.cidr.lang.editor.colors.OCHighlightingKeys;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.codeInsight.daemon.impl.HighlightVisitor;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCCallable;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.lang.annotation.AnnotationSession;
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.intellij.codeInsight.daemon.RainbowVisitor;

public class OCRainbowVisitor extends RainbowVisitor
{
    @Override
    public boolean suitableForFile(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/OCRainbowVisitor", "suitableForFile"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return psiFile instanceof OCFile;
    }
    
    @Override
    public boolean analyze(@NotNull final PsiFile psiFile, final boolean b, @NotNull final HighlightInfoHolder highlightInfoHolder, @NotNull final Runnable runnable) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/daemon/OCRainbowVisitor", "analyze"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (highlightInfoHolder == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "holder", "com/jetbrains/cidr/lang/daemon/OCRainbowVisitor", "analyze"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (runnable == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "action", "com/jetbrains/cidr/lang/daemon/OCRainbowVisitor", "analyze"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final OCFile ocFile = (OCFile)psiFile;
        try {
            ocFile.pushAnnotationSession(new AnnotationSession((PsiFile)ocFile));
            return super.analyze(psiFile, b, highlightInfoHolder, runnable);
        }
        finally {
            ocFile.popAnnotationSession();
        }
    }
    
    @Override
    public void visit(@NotNull final PsiElement p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCRainbowVisitor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "visit"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    48: ifeq            147
        //    51: aload_1        
        //    52: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    57: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    60: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.THIS_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //    63: if_acmpeq       125
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    72: athrow         
        //    73: aload_1        
        //    74: checkcast       Lcom/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner;
        //    77: invokeinterface com/jetbrains/cidr/lang/psi/OCNamespaceQualifierOwner.getNamespaceQualifier:()Lcom/jetbrains/cidr/lang/psi/OCCppNamespaceQualifier;
        //    82: ifnonnull       125
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_1        
        //    93: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    98: instanceof      Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //   101: ifne            125
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: aload_1        
        //   112: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCMacroReferenceElementImpl;
        //   115: ifeq            130
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   124: athrow         
        //   125: return         
        //   126: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: aload_0        
        //   131: aload_1        
        //   132: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   135: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getName:()Ljava/lang/String;
        //   140: aload_1        
        //   141: invokevirtual   com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.processCandidate:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)V
        //   144: goto            303
        //   147: aload_1        
        //   148: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLocalSymbolDeclarator;
        //   151: ifeq            303
        //   154: aload_1        
        //   155: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   158: ifeq            303
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: aload_1        
        //   169: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   172: astore_2       
        //   173: aload_2        
        //   174: ifnonnull       182
        //   177: return         
        //   178: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload_1        
        //   183: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   186: astore_3       
        //   187: aload_3        
        //   188: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   193: astore          4
        //   195: aload_2        
        //   196: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   199: ifne            216
        //   202: aload_2        
        //   203: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   206: ifeq            291
        //   209: goto            216
        //   212: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   215: athrow         
        //   216: aload_2        
        //   217: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   220: ifeq            251
        //   223: goto            230
        //   226: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   229: athrow         
        //   230: aload           4
        //   232: aload_2        
        //   233: checkcast       Lcom/intellij/psi/PsiNameIdentifierOwner;
        //   236: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   241: if_acmpeq       286
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload_2        
        //   252: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   255: ifeq            291
        //   258: goto            265
        //   261: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: aload           4
        //   267: aload_2        
        //   268: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //   271: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getNameIdentifier:()Lcom/intellij/psi/PsiElement;
        //   276: if_acmpne       291
        //   279: goto            286
        //   282: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   285: athrow         
        //   286: return         
        //   287: invokestatic    com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   290: athrow         
        //   291: aload_0        
        //   292: aload_3        
        //   293: invokeinterface com/intellij/psi/PsiNameIdentifierOwner.getName:()Ljava/lang/String;
        //   298: aload           4
        //   300: invokevirtual   com/jetbrains/cidr/lang/daemon/OCRainbowVisitor.processCandidate:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)V
        //   303: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     66     69     73     Ljava/lang/IllegalArgumentException;
        //  51     85     88     92     Ljava/lang/IllegalArgumentException;
        //  73     104    107    111    Ljava/lang/IllegalArgumentException;
        //  92     118    121    125    Ljava/lang/IllegalArgumentException;
        //  111    126    126    130    Ljava/lang/IllegalArgumentException;
        //  147    161    164    168    Ljava/lang/IllegalArgumentException;
        //  173    178    178    182    Ljava/lang/IllegalArgumentException;
        //  195    209    212    216    Ljava/lang/IllegalArgumentException;
        //  202    223    226    230    Ljava/lang/IllegalArgumentException;
        //  216    244    247    251    Ljava/lang/IllegalArgumentException;
        //  230    258    261    265    Ljava/lang/IllegalArgumentException;
        //  251    279    282    286    Ljava/lang/IllegalArgumentException;
        //  265    287    287    291    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    @Contract("null -> null")
    private static OCCallable a(@Nullable final PsiElement psiElement) {
        return (OCCallable)PsiTreeUtil.getParentOfType(psiElement, (Class)OCCallable.class);
    }
    
    @NotNull
    @Override
    public HighlightVisitor clone() {
        OCRainbowVisitor ocRainbowVisitor;
        try {
            ocRainbowVisitor = new OCRainbowVisitor();
            if (ocRainbowVisitor == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCRainbowVisitor", "clone"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocRainbowVisitor;
    }
    
    void processCandidate(@Nullable final String s, @Nullable final PsiElement psiElement) {
        Label_0020: {
            try {
                if (psiElement == null) {
                    return;
                }
                final String s2 = s;
                if (s2 == null) {
                    return;
                }
                break Label_0020;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final String s2 = s;
                if (s2 == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        OCResolveUtil.processLocalSymbols(s, psiElement, (Processor<OCSymbol>)(ocSymbol -> {
            final OCSymbolKind kind = ocSymbol.getKind();
            TextAttributesKey textAttributesKey = null;
            switch (kind) {
                case LOCAL_VARIABLE:
                case CATCH_EXCEPTION_VARIABLE: {
                    textAttributesKey = OCHighlightingKeys.LOCAL_VARIABLE;
                    break;
                }
                case PARAMETER: {
                    if (!(ocSymbol instanceof OCThisSelfSuperSymbol)) {
                        textAttributesKey = OCHighlightingKeys.PARAMETER;
                        break;
                    }
                    break;
                }
            }
            if (textAttributesKey != null) {
                final PsiFile containingPsiFile = ocSymbol.getContainingPsiFile();
                if (containingPsiFile != null) {
                    final OCCallable a = a(containingPsiFile.findElementAt(ocSymbol.getOffset()));
                    try {
                        if (a != null) {
                            this.addInfo(this.getInfo((PsiElement)a, psiElement, ocSymbol.getName(), textAttributesKey));
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                }
            }
            return true;
        }), true);
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
