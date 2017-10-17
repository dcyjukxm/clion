// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.refactoring.util.OCElementsMover;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCSymbol;

public class OCSetSuperclassIntentionAction extends OCSymbolQuickFix<OCSymbol<?>>
{
    private OCSymbol mySuperClass;
    private OCVisibility myVisibility;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCSetSuperclassIntentionAction(final OCSymbol ocSymbol, final OCSymbol mySuperClass, @Nullable final OCVisibility myVisibility) {
        super(ocSymbol);
        this.mySuperClass = mySuperClass;
        this.myVisibility = myVisibility;
    }
    
    @Override
    protected String getTextInternal(final OCSymbol ocSymbol) {
        StringBuilder append = null;
        Label_0130: {
            Label_0118: {
                Label_0098: {
                    if (ocSymbol instanceof OCInterfaceSymbol) {
                        final String superClassName = ((OCInterfaceSymbol)ocSymbol).getSuperClassName();
                        Label_0038: {
                            try {
                                if (superClassName.isEmpty()) {
                                    break Label_0098;
                                }
                                final OCSetSuperclassIntentionAction ocSetSuperclassIntentionAction = this;
                                final OCSymbol ocSymbol2 = ocSetSuperclassIntentionAction.mySuperClass;
                                if (ocSymbol2 != null) {
                                    break Label_0038;
                                }
                                break Label_0098;
                            }
                            catch (IllegalStateException ex) {
                                throw a(ex);
                            }
                            try {
                                final OCSetSuperclassIntentionAction ocSetSuperclassIntentionAction = this;
                                final OCSymbol ocSymbol2 = ocSetSuperclassIntentionAction.mySuperClass;
                                if (ocSymbol2 != null) {
                                    return "Change superclass of " + ocSymbol.getNameWithKindLowercase() + " from '" + superClassName + "' to '" + this.mySuperClass.getPresentableName() + "'";
                                }
                            }
                            catch (IllegalStateException ex2) {
                                throw a(ex2);
                            }
                        }
                    }
                    try {
                        if (OCSetSuperclassIntentionAction.$assertionsDisabled) {
                            break Label_0130;
                        }
                        final OCSetSuperclassIntentionAction ocSetSuperclassIntentionAction2 = this;
                        final OCSymbol ocSymbol3 = ocSetSuperclassIntentionAction2.mySuperClass;
                        if (ocSymbol3 == null) {
                            break Label_0118;
                        }
                        break Label_0130;
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                try {
                    final OCSetSuperclassIntentionAction ocSetSuperclassIntentionAction2 = this;
                    final OCSymbol ocSymbol3 = ocSetSuperclassIntentionAction2.mySuperClass;
                    if (ocSymbol3 == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            try {
                append = new StringBuilder().append("Set '").append(this.mySuperClass.getPresentableName()).append("' as a ");
                if (this.myVisibility != null) {
                    final String string = this.myVisibility.toString() + " ";
                    return append.append(string).append("superclass of ").append(ocSymbol.getNameWithKindLowercase()).toString();
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        final String string = "";
        return append.append(string).append("superclass of ").append(ocSymbol.getNameWithKindLowercase()).toString();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Change superclass";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInProjectSources:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)Z
        //     4: ifeq            93
        //     7: aload_1        
        //     8: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //    13: ifne            93
        //    16: goto            23
        //    19: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    22: athrow         
        //    23: aload_1        
        //    24: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    27: ifne            51
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    36: athrow         
        //    37: aload_1        
        //    38: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    41: ifeq            93
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    50: athrow         
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.mySuperClass:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    55: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //    58: ifne            85
        //    61: goto            68
        //    64: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    67: athrow         
        //    68: aload_0        
        //    69: getfield        com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.mySuperClass:Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    72: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    75: ifeq            93
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    84: athrow         
        //    85: iconst_1       
        //    86: goto            94
        //    89: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCSetSuperclassIntentionAction.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    92: athrow         
        //    93: iconst_0       
        //    94: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      16     19     23     Ljava/lang/IllegalStateException;
        //  7      30     33     37     Ljava/lang/IllegalStateException;
        //  23     44     47     51     Ljava/lang/IllegalStateException;
        //  37     61     64     68     Ljava/lang/IllegalStateException;
        //  51     78     81     85     Ljava/lang/IllegalStateException;
        //  68     89     89     93     Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    protected void invoke(final OCSymbol ocSymbol) {
        final OCSymbolDeclarator ocSymbolDeclarator = ocSymbol.locateDefinition();
        Label_0031: {
            try {
                if (!(this.mySuperClass instanceof OCInterfaceSymbol)) {
                    break Label_0031;
                }
                final OCSymbolDeclarator ocSymbolDeclarator2 = ocSymbolDeclarator;
                if (ocSymbolDeclarator2 != null) {
                    break Label_0031;
                }
                return;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCSymbolDeclarator ocSymbolDeclarator2 = ocSymbolDeclarator;
                if (ocSymbolDeclarator2 != null) {
                    new OCElementsMover().setSuperClass((OCClassDeclaration)ocSymbolDeclarator, this.mySuperClass.getName());
                }
                return;
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        new OCElementsMover().removeBaseClass((OCStruct)ocSymbolDeclarator, (OCStructSymbol)this.mySuperClass, false);
        new OCElementsMover().addBaseClass((OCStruct)ocSymbolDeclarator, (OCStructSymbol)this.mySuperClass, this.myVisibility, false);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSetSuperclassIntentionAction.class.desiredAssertionStatus()) {
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
