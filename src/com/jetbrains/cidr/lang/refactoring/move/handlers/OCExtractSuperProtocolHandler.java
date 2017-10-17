// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.move.handlers;

import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCExtractSuperProtocolDialog;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCAbstractMoveDialog;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.intellij.openapi.project.Project;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.jetbrains.cidr.lang.OCBundle;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;

public class OCExtractSuperProtocolHandler extends OCMoveRefactoringHandler
{
    @Override
    protected String getTitle() {
        return "Extract Super Protocol";
    }
    
    @Override
    protected void showDialog(final OCSymbolDeclarator ocSymbolDeclarator, final Condition<PsiElement> condition, final Editor editor) {
        final Project project = ocSymbolDeclarator.getProject();
        final OCClassSymbol symbol = ocSymbolDeclarator.getSymbol();
        Label_0101: {
            try {
                if (!(symbol instanceof OCClassSymbol) || symbol.getCategoryName() == null) {
                    break Label_0101;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final OCInterfaceSymbol mainInterface = symbol.getMainInterface();
            Label_0076: {
                try {
                    if (mainInterface == null) {
                        break Label_0101;
                    }
                    final OCInterfaceSymbol ocInterfaceSymbol = mainInterface;
                    final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                    if (!b) {
                        break Label_0076;
                    }
                    break Label_0101;
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCInterfaceSymbol ocInterfaceSymbol = mainInterface;
                    final boolean b = OCSearchScope.isInProjectSources(ocInterfaceSymbol);
                    if (!b) {
                        CommonRefactoringUtil.showErrorHint(project, editor, OCBundle.message("refactoring.category.not.in.project", new Object[0]), this.getTitle(), (String)null);
                        return;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
        }
        super.showDialog(ocSymbolDeclarator, condition, editor);
    }
    
    @Override
    protected OCAbstractMoveDialog createDialog(final OCSymbolDeclarator ocSymbolDeclarator, final OCSymbol ocSymbol, final Condition<PsiElement> condition, final Editor editor) {
        return new OCExtractSuperProtocolDialog(ocSymbolDeclarator, ocSymbol, condition);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.extractSuperProtocol";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSuperProtocolHandler", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean acceptsElement(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     4: ifeq            48
        //     7: aload_1        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    16: astore_2       
        //    17: aload_2        
        //    18: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInstanceVariableSymbol;
        //    21: ifne            38
        //    24: aload_2        
        //    25: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    28: ifeq            46
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSuperProtocolHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    37: athrow         
        //    38: iconst_1       
        //    39: goto            47
        //    42: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSuperProtocolHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    45: athrow         
        //    46: iconst_0       
        //    47: ireturn        
        //    48: aload_1        
        //    49: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    52: ifne            83
        //    55: aload_1        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //    59: ifne            83
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSuperProtocolHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    68: athrow         
        //    69: aload_1        
        //    70: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProperty;
        //    73: ifeq            91
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSuperProtocolHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    82: athrow         
        //    83: iconst_1       
        //    84: goto            92
        //    87: invokestatic    com/jetbrains/cidr/lang/refactoring/move/handlers/OCExtractSuperProtocolHandler.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    90: athrow         
        //    91: iconst_0       
        //    92: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  17     31     34     38     Ljava/lang/IllegalStateException;
        //  24     42     42     46     Ljava/lang/IllegalStateException;
        //  48     62     65     69     Ljava/lang/IllegalStateException;
        //  55     76     79     83     Ljava/lang/IllegalStateException;
        //  69     87     87     91     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
