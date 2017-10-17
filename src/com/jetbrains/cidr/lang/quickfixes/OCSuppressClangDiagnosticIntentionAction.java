// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.intellij.codeInspection.CommonProblemDescriptor;
import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.codeInspection.SuppressQuickFix;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiFileRange;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.daemon.OCSuppressionGroup;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.codeInspection.SuppressIntentionAction;

public class OCSuppressClangDiagnosticIntentionAction extends SuppressIntentionAction
{
    private SmartPsiElementPointer<PsiElement> myElementPtr;
    private String myCompiler;
    private String mySuppressOption;
    private String mySuppressScope;
    
    private OCSuppressClangDiagnosticIntentionAction(@Nullable final PsiElement psiElement, @Nullable final OCSuppressionGroup ocSuppressionGroup, final String mySuppressScope) {
        this.myElementPtr = OCElementUtil.createPsiElementPointer(psiElement);
        String compiler;
        if (ocSuppressionGroup == null) {
            compiler = null;
        }
        else {
            compiler = ocSuppressionGroup.compiler;
        }
        String suppressionOption = null;
        Label_0045: {
            try {
                this.myCompiler = compiler;
                if (ocSuppressionGroup == null) {
                    suppressionOption = null;
                    break Label_0045;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            suppressionOption = ocSuppressionGroup.suppressionOption;
        }
        this.mySuppressOption = suppressionOption;
        this.mySuppressScope = mySuppressScope;
    }
    
    @NotNull
    public String getFamilyName() {
        String string;
        try {
            string = "Suppress for " + this.mySuppressScope;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @NotNull
    public String getText() {
        String string;
        try {
            string = "Suppress \"" + this.mySuppressOption + "\" for " + this.mySuppressScope;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Nullable
    protected TextRange getSuppressionRange(final OCFile ocFile) {
        final PsiElement psiElementByPointer = OCElementUtil.getPsiElementByPointer(this.myElementPtr);
        try {
            if (psiElementByPointer != null) {
                return OCElementUtil.getRangeWithMacros(psiElementByPointer);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public boolean isAvailable(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiElement p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: invokeinterface com/intellij/psi/PsiElement.isValid:()Z
        //    94: ifeq            133
        //    97: aload_0        
        //    98: getfield        com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.myElementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //   101: ifnull          133
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   110: athrow         
        //   111: aload_0        
        //   112: getfield        com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.mySuppressOption:Ljava/lang/String;
        //   115: ifnull          133
        //   118: goto            125
        //   121: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   124: athrow         
        //   125: iconst_1       
        //   126: goto            134
        //   129: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   132: athrow         
        //   133: iconst_0       
        //   134: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     104    107    111    Lcom/intellij/util/IncorrectOperationException;
        //  97     118    121    125    Lcom/intellij/util/IncorrectOperationException;
        //  111    129    129    133    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0111:
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
    
    public void invoke(@NotNull final Project p0, @Nullable final Editor p1, @NotNull final PsiElement p2) throws IncorrectOperationException {
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
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "invoke"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_3        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "invoke"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    94: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    97: astore          4
        //    99: aload           4
        //   101: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   106: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   109: aload           4
        //   111: invokevirtual   com/intellij/psi/PsiDocumentManager.getDocument:(Lcom/intellij/psi/PsiFile;)Lcom/intellij/openapi/editor/Document;
        //   114: astore          5
        //   116: aload_3        
        //   117: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   120: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   123: istore          6
        //   125: aload_0        
        //   126: aload           4
        //   128: invokevirtual   com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.getSuppressionRange:(Lcom/jetbrains/cidr/lang/psi/OCFile;)Lcom/intellij/openapi/util/TextRange;
        //   131: astore          7
        //   133: aload           5
        //   135: ifnull          150
        //   138: aload           7
        //   140: ifnonnull       155
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   149: athrow         
        //   150: return         
        //   151: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   154: athrow         
        //   155: aload           4
        //   157: iload           6
        //   159: aload_0        
        //   160: getfield        com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.mySuppressOption:Ljava/lang/String;
        //   163: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.getDiagnosticMode:(Lcom/jetbrains/cidr/lang/psi/OCFile;ILjava/lang/String;)Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   166: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.IGNORE:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //   169: if_acmpne       177
        //   172: return         
        //   173: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   176: athrow         
        //   177: aload           5
        //   179: aload           7
        //   181: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   184: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   189: istore          8
        //   191: aload           5
        //   193: aload           7
        //   195: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   198: invokeinterface com/intellij/openapi/editor/Document.getLineNumber:(I)I
        //   203: istore          9
        //   205: iload           8
        //   207: iflt            409
        //   210: iload           8
        //   212: aload           5
        //   214: invokeinterface com/intellij/openapi/editor/Document.getLineCount:()I
        //   219: if_icmpge       409
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   228: athrow         
        //   229: iload           9
        //   231: iflt            409
        //   234: goto            241
        //   237: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   240: athrow         
        //   241: iload           9
        //   243: aload           5
        //   245: invokeinterface com/intellij/openapi/editor/Document.getLineCount:()I
        //   250: if_icmpge       409
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   259: athrow         
        //   260: aload           5
        //   262: iload           8
        //   264: invokeinterface com/intellij/openapi/editor/Document.getLineStartOffset:(I)I
        //   269: istore          10
        //   271: aload           5
        //   273: iload           9
        //   275: invokeinterface com/intellij/openapi/editor/Document.getLineEndOffset:(I)I
        //   280: istore          11
        //   282: iconst_1       
        //   283: istore          12
        //   285: aload           4
        //   287: iload           10
        //   289: iload           11
        //   291: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.findExactScope:(Lcom/jetbrains/cidr/lang/psi/OCFile;II)Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;
        //   294: ifnull          300
        //   297: iconst_0       
        //   298: istore          12
        //   300: iload           12
        //   302: ifeq            323
        //   305: aload           5
        //   307: iload           11
        //   309: ldc             "\n#pragma clang diagnostic pop"
        //   311: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   322: athrow         
        //   323: aload           5
        //   325: iload           10
        //   327: new             Ljava/lang/StringBuilder;
        //   330: dup            
        //   331: invokespecial   java/lang/StringBuilder.<init>:()V
        //   334: ldc             "#pragma "
        //   336: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   339: aload_0        
        //   340: getfield        com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.myCompiler:Ljava/lang/String;
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: ldc             " diagnostic ignored \""
        //   348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   351: aload_0        
        //   352: getfield        com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.mySuppressOption:Ljava/lang/String;
        //   355: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   358: ldc             "\"\n"
        //   360: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   363: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   366: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   371: iload           12
        //   373: ifeq            394
        //   376: aload           5
        //   378: iload           10
        //   380: ldc             "#pragma clang diagnostic push\n"
        //   382: invokeinterface com/intellij/openapi/editor/Document.insertString:(ILjava/lang/CharSequence;)V
        //   387: goto            394
        //   390: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   393: athrow         
        //   394: aload           4
        //   396: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   401: invokestatic    com/intellij/psi/PsiDocumentManager.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/intellij/psi/PsiDocumentManager;
        //   404: aload           5
        //   406: invokevirtual   com/intellij/psi/PsiDocumentManager.commitDocument:(Lcom/intellij/openapi/editor/Document;)V
        //   409: return         
        //    Exceptions:
        //  throws com.intellij.util.IncorrectOperationException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  133    143    146    150    Lcom/intellij/util/IncorrectOperationException;
        //  138    151    151    155    Lcom/intellij/util/IncorrectOperationException;
        //  155    173    173    177    Lcom/intellij/util/IncorrectOperationException;
        //  205    222    225    229    Lcom/intellij/util/IncorrectOperationException;
        //  210    234    237    241    Lcom/intellij/util/IncorrectOperationException;
        //  229    253    256    260    Lcom/intellij/util/IncorrectOperationException;
        //  300    316    319    323    Lcom/intellij/util/IncorrectOperationException;
        //  323    387    390    394    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0229:
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    public static class ForStatement extends OCSuppressClangDiagnosticIntentionAction
    {
        private ForStatement(@Nullable final OCElement ocElement, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
            super((PsiElement)((ocElement != null && !(ocElement instanceof OCFunctionDeclaration)) ? ocElement : null), ocSuppressionGroup, "statement", null);
        }
        
        public ForStatement(@Nullable final PsiFile psiFile, @NotNull final TextRange textRange, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForStatement", "<init>"));
            }
            this((OCElement)OCElementUtil.getParentOfType(psiFile, textRange, OCStatement.class, OCDeclaration.class), ocSuppressionGroup);
        }
    }
    
    public static class ForCallable extends OCSuppressClangDiagnosticIntentionAction
    {
        private ForCallable(@Nullable final OCCallable ocCallable, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
            super((PsiElement)ocCallable, ocSuppressionGroup, (ocCallable != null) ? ocCallable.getKind().toStringLowercase() : "", null);
        }
        
        public ForCallable(@Nullable final PsiFile psiFile, @NotNull final TextRange textRange, @Nullable final OCSuppressionGroup ocSuppressionGroup) {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$ForCallable", "<init>"));
            }
            this((OCCallable)OCElementUtil.getParentOfType(psiFile, textRange, OCMethod.class, OCFunctionDeclaration.class), ocSuppressionGroup);
        }
    }
    
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
    
    private abstract static class SuppressFix implements SuppressQuickFix
    {
        @Nullable
        protected OCSuppressionGroup mySuppressionGroup;
        
        protected SuppressFix(@Nullable final OCSuppressionGroup mySuppressionGroup) {
            this.mySuppressionGroup = mySuppressionGroup;
        }
        
        @NotNull
        protected abstract OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement p0);
        
        public boolean isAvailable(@NotNull final Project project, @NotNull final PsiElement psiElement) {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "isAvailable"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "isAvailable"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return this.createFix(psiElement).isAvailable(project, null, psiElement);
        }
        
        @NotNull
        public String getFamilyName() {
            String name;
            try {
                name = this.getName();
                if (name == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "getFamilyName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return name;
        }
        
        public void applyFix(@NotNull final Project project, @NotNull final ProblemDescriptor problemDescriptor) {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "applyFix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (problemDescriptor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "descriptor", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFix", "applyFix"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            this.createFix(problemDescriptor.getPsiElement()).invoke(project, null, problemDescriptor.getPsiElement());
        }
        
        public boolean isSuppressAll() {
            return false;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class SuppressFixForStatement extends SuppressFix
    {
        public SuppressFixForStatement(@Nullable final OCSuppressionGroup ocSuppressionGroup) {
            super(ocSuppressionGroup);
        }
        
        @NotNull
        @Override
        protected OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement psiElement) {
            ForStatement forStatement;
            try {
                forStatement = new ForStatement(psiElement.getContainingFile(), OCElementUtil.getRangeWithMacros(psiElement), this.mySuppressionGroup);
                if (forStatement == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForStatement", "createFix"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return forStatement;
        }
        
        @NotNull
        public String getName() {
            String s;
            try {
                s = "Suppress for statement";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForStatement", "getName"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class SuppressFixForCallable extends SuppressFix
    {
        public SuppressFixForCallable(@Nullable final OCSuppressionGroup ocSuppressionGroup) {
            super(ocSuppressionGroup);
        }
        
        @NotNull
        @Override
        protected OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement psiElement) {
            ForCallable forCallable;
            try {
                forCallable = new ForCallable(psiElement.getContainingFile(), OCElementUtil.getRangeWithMacros(psiElement), this.mySuppressionGroup);
                if (forCallable == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForCallable", "createFix"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return forCallable;
        }
        
        @NotNull
        public String getName() {
            String s;
            try {
                s = "Suppress for method/function";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForCallable", "getName"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
    
    public static class SuppressFixForFile extends SuppressFix
    {
        public SuppressFixForFile(@Nullable final OCSuppressionGroup ocSuppressionGroup) {
            super(ocSuppressionGroup);
        }
        
        @NotNull
        @Override
        protected OCSuppressClangDiagnosticIntentionAction createFix(final PsiElement psiElement) {
            ForFile forFile;
            try {
                forFile = new ForFile((OCFile)psiElement.getContainingFile(), OCElementUtil.getRangeWithMacros(psiElement), this.mySuppressionGroup);
                if (forFile == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForFile", "createFix"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return forFile;
        }
        
        @NotNull
        public String getName() {
            String s;
            try {
                s = "Suppress for file";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSuppressClangDiagnosticIntentionAction$SuppressFixForFile", "getName"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return s;
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
