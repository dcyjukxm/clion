// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.OCSwitchStatement;
import com.intellij.psi.SmartPsiElementPointer;
import com.jetbrains.cidr.lang.intentions.OCCreateMissingSwitchCasesIntentionAction;

public class OCCreateMissingSwitchCasesFix extends OCCreateMissingSwitchCasesIntentionAction
{
    private boolean myDefaultIsMissing;
    protected SmartPsiElementPointer<OCSwitchStatement> mySwitchStatementPtr;
    
    public OCCreateMissingSwitchCasesFix(final OCSwitchStatement ocSwitchStatement, final boolean myDefaultIsMissing) {
        this.myDefaultIsMissing = myDefaultIsMissing;
        this.mySwitchStatementPtr = OCElementUtil.createPsiElementPointer(ocSwitchStatement);
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
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAvailable"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: getfield        com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix.mySwitchStatementPtr:Lcom/intellij/psi/SmartPsiElementPointer;
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getPsiElementByPointer:(Lcom/intellij/psi/SmartPsiElementPointer;)Lcom/intellij/psi/PsiElement;
        //    51: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSwitchStatement;
        //    54: astore          4
        //    56: aload           4
        //    58: ifnull          104
        //    61: aload           4
        //    63: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isValid:(Lcom/intellij/psi/PsiElement;)Z
        //    66: ifeq            104
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload           4
        //    78: invokeinterface com/jetbrains/cidr/lang/psi/OCSwitchStatement.getBody:()Lcom/jetbrains/cidr/lang/psi/OCStatement;
        //    83: instanceof      Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //    86: ifeq            104
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: iconst_1       
        //    97: goto            105
        //   100: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   103: athrow         
        //   104: iconst_0       
        //   105: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  56     69     72     76     Ljava/lang/IllegalArgumentException;
        //  61     89     92     96     Ljava/lang/IllegalArgumentException;
        //  76     100    100    104    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    protected OCSwitchStatement getSwitchStatement(final Editor editor, final PsiFile psiFile) {
        return OCElementUtil.getPsiElementByPointer(this.mySwitchStatementPtr);
    }
    
    @NotNull
    @Override
    public String getText() {
        String s = null;
        Label_0020: {
            try {
                if (this.myDefaultIsMissing) {
                    final String text;
                    s = (text = "Create missing default case");
                    break Label_0020;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String text;
            s = (text = super.getText());
            try {
                if (text == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCCreateMissingSwitchCasesFix", "getText"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @Override
    protected void invoke(final OCStatement ocStatement, final List<OCDeclaratorSymbol> list) {
        try {
            if (this.myDefaultIsMissing) {
                OCChangeUtil.add((PsiElement)ocStatement, OCElementFactory.statementFromText("default:break;", (PsiElement)ocStatement));
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        super.invoke(ocStatement, list);
    }
    
    @Override
    protected boolean proceedIfDefaultOrUnresolved(final boolean b) {
        try {
            if (!b) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
