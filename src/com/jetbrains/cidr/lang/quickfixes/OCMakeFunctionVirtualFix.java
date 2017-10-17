// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

public class OCMakeFunctionVirtualFix extends OCSymbolQuickFix<OCFunctionSymbol>
{
    private boolean isPure;
    
    public OCMakeFunctionVirtualFix(final OCFunctionSymbol ocFunctionSymbol, final boolean isPure) {
        super(ocFunctionSymbol);
        this.isPure = isPure;
    }
    
    @Override
    protected boolean isAvailable(final OCFunctionSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //     4: ifeq            57
        //     7: aload_1        
        //     8: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getParent:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    11: ifnull          57
        //    14: goto            21
        //    17: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    20: athrow         
        //    21: aload_1        
        //    22: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isCppConstructor:()Z
        //    25: ifne            57
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    34: athrow         
        //    35: aload_1        
        //    36: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isFriendOrStatic:()Z
        //    39: ifne            57
        //    42: goto            49
        //    45: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    48: athrow         
        //    49: iconst_1       
        //    50: goto            58
        //    53: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    56: athrow         
        //    57: iconst_0       
        //    58: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      14     17     21     Ljava/lang/IllegalStateException;
        //  7      28     31     35     Ljava/lang/IllegalStateException;
        //  21     42     45     49     Ljava/lang/IllegalStateException;
        //  35     53     53     57     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
    protected String getTextInternal(final OCFunctionSymbol ocFunctionSymbol) {
        StringBuilder append;
        try {
            append = new StringBuilder().append("Make ").append(ocFunctionSymbol.getParent().getName()).append("::").append(ocFunctionSymbol.getName());
            if (this.isPure) {
                final String s = " pure virtual";
                return append.append(s).toString();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final String s = " virtual";
        return append.append(s).toString();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Make function virtual";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCMakeFunctionVirtualFix", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected void invoke(final OCFunctionSymbol ocFunctionSymbol) {
        final OCFunctionDeclaration locateFunctionDefinition = ocFunctionSymbol.locateFunctionDefinition();
        Label_0059: {
            try {
                if (locateFunctionDefinition == null) {
                    return;
                }
                if (!this.isPure) {
                    break Label_0059;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            OCChangeUtil.changeText(locateFunctionDefinition.getProject(), locateFunctionDefinition.getContainingFile(), locateFunctionDefinition.getDeclarator().getTextRange().getEndOffset(), 0, "= 0", false);
        }
        if (!ocFunctionSymbol.isVirtual()) {
            OCChangeUtil.changeText(locateFunctionDefinition.getProject(), locateFunctionDefinition.getContainingFile(), locateFunctionDefinition.getTextRange().getStartOffset(), 0, "virtual ", false);
        }
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
