// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.SmartPsiFileRange;

public static class ForFile extends OCSuppressClangDiagnosticIntentionAction
{
    private SmartPsiFileRange myRange;
    
    public ForFile(@Nullable final OCFile ocFile, @Nullable final TextRange textRange, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
        super((PsiElement)ocFile, ocSuppressionGroup, "file", null);
        if (ocFile != null) {
            try {
                if (textRange != null) {
                    this.myRange = SmartPointerManager.getInstance(ocFile.getProject()).createSmartPsiFileRangePointer((PsiFile)ocFile, textRange);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
    }
    
    @Override
    public boolean isAvailable(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiElement psiElement) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile", "isAvailable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0112: {
            try {
                if (!super.isAvailable(project, editor, psiElement)) {
                    return false;
                }
                final ForFile forFile = this;
                final SmartPsiFileRange smartPsiFileRange = forFile.myRange;
                if (smartPsiFileRange != null) {
                    break Label_0112;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final ForFile forFile = this;
                final SmartPsiFileRange smartPsiFileRange = forFile.myRange;
                if (smartPsiFileRange != null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    protected TextRange getSuppressionRange(final OCFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.myRange:Lcom/intellij/psi/SmartPsiFileRange;
        //     4: invokeinterface com/intellij/psi/SmartPsiFileRange.getRange:()Lcom/intellij/openapi/util/Segment;
        //     9: astore_2       
        //    10: aload_2        
        //    11: ifnull          36
        //    14: aload_2        
        //    15: invokeinterface com/intellij/openapi/util/Segment.getEndOffset:()I
        //    20: aload_1        
        //    21: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getTextLength:()I
        //    26: if_icmple       42
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aconst_null    
        //    37: areturn        
        //    38: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_2        
        //    43: invokeinterface com/intellij/openapi/util/Segment.getStartOffset:()I
        //    48: istore_3       
        //    49: aload_1        
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getChildren:()[Lcom/intellij/psi/PsiElement;
        //    55: astore          4
        //    57: aload           4
        //    59: arraylength    
        //    60: istore          5
        //    62: iconst_0       
        //    63: istore          6
        //    65: iload           6
        //    67: iload           5
        //    69: if_icmpge       240
        //    72: aload           4
        //    74: iload           6
        //    76: aaload         
        //    77: astore          7
        //    79: aload           7
        //    81: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    86: astore          8
        //    88: aload           7
        //    90: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPragma;
        //    93: ifeq            157
        //    96: aload_1        
        //    97: aload           7
        //    99: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   104: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.findScope:(Lcom/jetbrains/cidr/lang/psi/OCFile;I)Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;
        //   107: astore          9
        //   109: aload           9
        //   111: invokevirtual   com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.getPush:()Lcom/intellij/openapi/util/TextRange;
        //   114: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   117: aload           7
        //   119: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   124: if_icmpne       157
        //   127: new             Lcom/intellij/openapi/util/TextRange;
        //   130: dup            
        //   131: aload           9
        //   133: invokevirtual   com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.getPush:()Lcom/intellij/openapi/util/TextRange;
        //   136: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   139: aload           9
        //   141: invokevirtual   com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.getPop:()Lcom/intellij/openapi/util/TextRange;
        //   144: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   147: iconst_1       
        //   148: isub           
        //   149: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   152: areturn        
        //   153: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   156: athrow         
        //   157: aload           7
        //   159: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //   162: ifne            180
        //   165: aload           7
        //   167: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   170: ifeq            211
        //   173: goto            180
        //   176: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   179: athrow         
        //   180: aload           8
        //   182: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   185: iload_3        
        //   186: if_icmpge       211
        //   189: goto            196
        //   192: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload           7
        //   198: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPragma;
        //   201: ifeq            234
        //   204: goto            211
        //   207: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   210: athrow         
        //   211: new             Lcom/intellij/openapi/util/TextRange;
        //   214: dup            
        //   215: aload           8
        //   217: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   220: aload_1        
        //   221: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getTextLength:()I
        //   226: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //   229: areturn        
        //   230: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForFile.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   233: athrow         
        //   234: iinc            6, 1
        //   237: goto            65
        //   240: aload_1        
        //   241: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   246: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  10     29     32     36     Ljava/lang/IllegalArgumentException;
        //  14     38     38     42     Ljava/lang/IllegalArgumentException;
        //  109    153    153    157    Ljava/lang/IllegalArgumentException;
        //  157    173    176    180    Ljava/lang/IllegalArgumentException;
        //  165    189    192    196    Ljava/lang/IllegalArgumentException;
        //  180    204    207    211    Ljava/lang/IllegalArgumentException;
        //  196    230    230    234    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0180:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
