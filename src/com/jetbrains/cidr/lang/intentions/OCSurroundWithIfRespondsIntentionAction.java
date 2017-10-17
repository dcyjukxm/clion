// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.intentions;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.openapi.util.TextRange;
import com.intellij.util.NotNullFunction;
import com.jetbrains.cidr.lang.psi.OCIfStatement;
import com.jetbrains.cidr.lang.editor.surround.OCStatementSurrounder;
import com.jetbrains.cidr.lang.editor.surround.OCIfRespondsToSurrounder;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.PsiElementBaseIntentionAction;

public class OCSurroundWithIfRespondsIntentionAction extends PsiElementBaseIntentionAction
{
    @NotNull
    public String getText() {
        String s;
        try {
            s = "Wrap with \"if ([ respondsToSelector: ])\"";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getFamilyName() {
        String text;
        try {
            text = this.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return text;
    }
    
    public boolean isAvailable(@NotNull final Project p0, final Editor p1, @NotNull final PsiElement p2) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    68: ldc             "com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAvailable"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ldc             Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;.class
        //    91: iconst_0       
        //    92: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;Z)Lcom/intellij/psi/PsiElement;
        //    95: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    98: astore          4
        //   100: aload           4
        //   102: ifnull          208
        //   105: aload           4
        //   107: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //   112: astore          5
        //   114: aload           5
        //   116: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   119: astore          6
        //   121: aload           6
        //   123: ifnull          198
        //   126: aload           6
        //   128: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isOptional:()Z
        //   133: ifne            198
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   142: athrow         
        //   143: aload           6
        //   145: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   150: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   153: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   158: ldc             "NSObject"
        //   160: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   163: ifeq            206
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   172: athrow         
        //   173: aload           6
        //   175: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   180: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   183: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   188: ifnull          206
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   197: athrow         
        //   198: iconst_1       
        //   199: goto            207
        //   202: invokestatic    com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   205: athrow         
        //   206: iconst_0       
        //   207: ireturn        
        //   208: iconst_0       
        //   209: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  121    136    139    143    Lcom/intellij/util/IncorrectOperationException;
        //  126    166    169    173    Lcom/intellij/util/IncorrectOperationException;
        //  143    191    194    198    Lcom/intellij/util/IncorrectOperationException;
        //  173    202    202    206    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0143:
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
    
    public void invoke(@NotNull final Project project, final Editor editor, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/intentions/OCSurroundWithIfRespondsIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)PsiTreeUtil.getParentOfType(psiElement, (Class)OCSendMessageExpression.class, false);
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType((PsiElement)ocSendMessageExpression, (Class)OCStatement.class);
        try {
            if (ocStatement != null) {
                new OCIfRespondsToSurrounder().doSurroundElements(project, new PsiElement[] { ocStatement }, ocSendMessageExpression);
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
