// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;

public class OCPredeclareSymbolIntentionAction extends OCPsiElementQuickFix<OCReferenceElement>
{
    private String myName;
    private OCSymbolKind mySymbolKind;
    
    public OCPredeclareSymbolIntentionAction(final OCReferenceElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: aload_1        
        //     2: invokespecial   com/jetbrains/cidr/lang/quickfixes/OCPsiElementQuickFix.<init>:(Lcom/intellij/psi/PsiElement;)V
        //     5: aload_0        
        //     6: aload_1        
        //     7: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getName:()Ljava/lang/String;
        //    12: putfield        com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.myName:Ljava/lang/String;
        //    15: aload_1        
        //    16: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getSymbolContext:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolGroupContext;
        //    21: astore_2       
        //    22: aload_2        
        //    23: ifnull          57
        //    26: aload_2        
        //    27: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    30: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.isSuitableSymbolKind:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Z
        //    33: ifeq            57
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    42: athrow         
        //    43: aload_0        
        //    44: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.PROTOCOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    47: putfield        com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    50: goto            151
        //    53: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    56: athrow         
        //    57: aload_2        
        //    58: ifnull          92
        //    61: aload_2        
        //    62: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    65: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.isSuitableSymbolKind:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Z
        //    68: ifeq            92
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    77: athrow         
        //    78: aload_0        
        //    79: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.INTERFACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    82: putfield        com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    85: goto            151
        //    88: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    91: athrow         
        //    92: aload_2        
        //    93: ifnull          151
        //    96: aload_2        
        //    97: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   100: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolGroupContext.isSuitableSymbolKind:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;)Z
        //   103: ifeq            151
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   112: athrow         
        //   113: aload_1        
        //   114: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   119: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   124: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //   127: ifne            151
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   136: athrow         
        //   137: aload_0        
        //   138: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.STRUCT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   141: putfield        com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   144: goto            151
        //   147: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   150: athrow         
        //   151: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  22     36     39     43     Ljava/lang/IllegalStateException;
        //  26     53     53     57     Ljava/lang/IllegalStateException;
        //  57     71     74     78     Ljava/lang/IllegalStateException;
        //  61     88     88     92     Ljava/lang/IllegalStateException;
        //  92     106    109    113    Ljava/lang/IllegalStateException;
        //  96     130    133    137    Ljava/lang/IllegalStateException;
        //  113    144    147    151    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0113:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    protected String getTextInternal() {
        StringBuilder append;
        try {
            append = new StringBuilder().append("Predeclare ");
            if (this.mySymbolKind == OCSymbolKind.PROTOCOL) {
                final String s = "protocol '";
                return append.append(s).append(this.myName).append("'").toString();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String s = "class '";
        return append.append(s).append(this.myName).append("'").toString();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Import/predeclare symbol";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, final PsiFile p2) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    43: athrow         
        //    44: aload_3        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    48: ifeq            101
        //    51: aload_3        
        //    52: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCodeFragment;
        //    55: ifne            101
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    64: athrow         
        //    65: aload_3        
        //    66: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    69: ifeq            101
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    78: athrow         
        //    79: aload_0        
        //    80: getfield        com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.mySymbolKind:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    83: ifnull          101
        //    86: goto            93
        //    89: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: iconst_1       
        //    94: goto            102
        //    97: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   100: athrow         
        //   101: iconst_0       
        //   102: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/IllegalStateException;
        //  44     58     61     65     Ljava/lang/IllegalStateException;
        //  51     72     75     79     Ljava/lang/IllegalStateException;
        //  65     86     89     93     Ljava/lang/IllegalStateException;
        //  79     97     97     101    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    protected void invoke(final PsiFile psiFile, @NotNull final OCReferenceElement ocReferenceElement) {
        try {
            if (ocReferenceElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCPredeclareSymbolIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        String s;
        if (this.mySymbolKind == OCSymbolKind.PROTOCOL) {
            s = "@protocol " + this.myName + ";";
        }
        else if (this.mySymbolKind == OCSymbolKind.INTERFACE) {
            s = "@class " + this.myName + ";";
        }
        else {
            s = "class " + this.myName + ";";
        }
        OCChangeUtil.addBefore((PsiElement)psiFile, OCElementFactory.topLevelDeclarationFromText(s, (PsiElement)psiFile), (PsiElement)ocReferenceElement);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
