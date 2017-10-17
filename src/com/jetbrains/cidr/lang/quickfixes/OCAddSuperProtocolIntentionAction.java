// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.refactoring.util.OCElementsMover;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCInterface;
import com.jetbrains.cidr.lang.psi.OCClassDeclarationBase;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;

public class OCAddSuperProtocolIntentionAction extends OCSymbolQuickFix<OCClassSymbol>
{
    private String myProtocol;
    private boolean myPrivateCategoryMode;
    
    public OCAddSuperProtocolIntentionAction(final OCClassSymbol ocClassSymbol, final String myProtocol, final boolean myPrivateCategoryMode) {
        super(ocClassSymbol);
        this.myProtocol = myProtocol;
        this.myPrivateCategoryMode = myPrivateCategoryMode;
    }
    
    @Override
    protected String getTextInternal(final OCClassSymbol ocClassSymbol) {
        StringBuilder append;
        try {
            append = new StringBuilder().append("Adopt '").append(this.myProtocol).append("' by ");
            if (this.myPrivateCategoryMode) {
                final String s = "private category '" + ocClassSymbol.getName() + "()'";
                return append.append(s).toString();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String s = "class '" + ocClassSymbol.getName() + "'";
        return append.append(s).toString();
    }
    
    @NotNull
    public String getFamilyName() {
        String s = null;
        Label_0018: {
            try {
                if (this.myPrivateCategoryMode) {
                    final String s2;
                    s = (s2 = "Adopt protocol by the class");
                    break Label_0018;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            String s2;
            s = (s2 = "Adopt protocol by the private category");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction", "getFamilyName"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(final OCClassSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction.myProtocol:Ljava/lang/String;
        //     4: ifnull          56
        //     7: aload_1        
        //     8: ifnull          56
        //    11: goto            18
        //    14: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    17: athrow         
        //    18: aload_1        
        //    19: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //    22: ifeq            56
        //    25: goto            32
        //    28: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    31: athrow         
        //    32: aload_1        
        //    33: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.isPredeclaration:()Z
        //    38: ifne            56
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    47: athrow         
        //    48: iconst_1       
        //    49: goto            57
        //    52: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCAddSuperProtocolIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    55: athrow         
        //    56: iconst_0       
        //    57: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      11     14     18     Ljava/lang/IllegalStateException;
        //  7      25     28     32     Ljava/lang/IllegalStateException;
        //  18     41     44     48     Ljava/lang/IllegalStateException;
        //  32     52     52     56     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    protected void invoke(final OCClassSymbol ocClassSymbol) {
        Object resolveClassDeclaration = OCElementUtil.resolveClassDeclaration(ocClassSymbol);
        try {
            if (resolveClassDeclaration == null) {
                return;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        if (this.myPrivateCategoryMode) {
            OCClassDeclaration privateCategory = OCCodeInsightUtil.getPrivateCategory((OCClassDeclarationBase)resolveClassDeclaration);
            if (privateCategory == null) {
                privateCategory = OCChangeUtil.addBefore((PsiElement)((OCClassDeclaration)resolveClassDeclaration).getContainingFile(), OCElementFactory.interfaceByName(ocClassSymbol.getName() + "()", (PsiElement)resolveClassDeclaration), (PsiElement)resolveClassDeclaration);
            }
            resolveClassDeclaration = privateCategory;
        }
        this.a((OCClassDeclaration)resolveClassDeclaration);
    }
    
    private void a(final OCClassDeclaration ocClassDeclaration) {
        new OCElementsMover().addBaseProtocol(ocClassDeclaration, this.myProtocol);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
