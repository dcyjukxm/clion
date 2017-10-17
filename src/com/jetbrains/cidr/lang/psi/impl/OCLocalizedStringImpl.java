// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.psi.impl;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCStringsFile;
import javax.swing.Icon;
import com.intellij.navigation.ItemPresentation;
import java.util.Collections;
import com.jetbrains.cidr.lang.symbols.objc.OCLocalizedStringSymbol;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NonNls;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.intellij.extapi.psi.ASTWrapperPsiElement;

public class OCLocalizedStringImpl extends ASTWrapperPsiElement implements OCLocalizedString
{
    public OCLocalizedStringImpl(@NotNull final ASTNode node) {
        if (node == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl", "<init>"));
        }
        super(node);
    }
    
    @NotNull
    @Override
    public String getKey() {
        String a;
        try {
            a = this.a(0);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl", "getKey"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    @Override
    public String getValue() {
        String a;
        try {
            a = this.a(1);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl", "getValue"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @NotNull
    public String getName() {
        String key;
        try {
            key = this.getKey();
            if (key == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl", "getName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return key;
    }
    
    public PsiElement setName(@NonNls @NotNull final String s) throws IncorrectOperationException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl", "setName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement firstChild = this.getFirstChild();
        try {
            if (firstChild != null) {
                OCChangeUtil.changeText(this.getProject(), this.getContainingFile(), firstChild.getTextOffset() + 1, firstChild.getTextLength() - 2, s, false, false);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return (PsiElement)this;
    }
    
    public OCLocalizedStringSymbol getSymbol() {
        return new OCLocalizedStringSymbol(this.getProject(), this.getContainingFile().getVirtualFile(), this.getTextOffset(), this.getName(), Collections.emptyList());
    }
    
    @NotNull
    private String a(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.getNode:()Lcom/intellij/lang/ASTNode;
        //     4: invokeinterface com/intellij/lang/ASTNode.getFirstChildNode:()Lcom/intellij/lang/ASTNode;
        //     9: astore_2       
        //    10: aload_2        
        //    11: ifnull          141
        //    14: aload_2        
        //    15: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    20: astore_3       
        //    21: aload_3        
        //    22: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    25: if_acmpeq       42
        //    28: aload_3        
        //    29: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    32: if_acmpne       131
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    41: athrow         
        //    42: iload_1        
        //    43: iinc            1, -1
        //    46: ifne            131
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    55: athrow         
        //    56: aload_3        
        //    57: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    60: if_acmpne       86
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    69: athrow         
        //    70: aload_2        
        //    71: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //    76: invokestatic    com/intellij/openapi/util/text/StringUtil.unquoteString:(Ljava/lang/String;)Ljava/lang/String;
        //    79: goto            92
        //    82: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    85: athrow         
        //    86: aload_2        
        //    87: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //    92: dup            
        //    93: ifnonnull       130
        //    96: new             Ljava/lang/IllegalStateException;
        //    99: dup            
        //   100: ldc             "@NotNull method %s.%s must not return null"
        //   102: ldc             2
        //   104: anewarray       Ljava/lang/Object;
        //   107: dup            
        //   108: ldc             0
        //   110: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl"
        //   112: aastore        
        //   113: dup            
        //   114: ldc             1
        //   116: ldc             "getLiteralValue"
        //   118: aastore        
        //   119: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   122: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   125: athrow         
        //   126: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   129: athrow         
        //   130: areturn        
        //   131: aload_2        
        //   132: invokeinterface com/intellij/lang/ASTNode.getTreeNext:()Lcom/intellij/lang/ASTNode;
        //   137: astore_2       
        //   138: goto            10
        //   141: getstatic       com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.$assertionsDisabled:Z
        //   144: ifne            159
        //   147: new             Ljava/lang/AssertionError;
        //   150: dup            
        //   151: invokespecial   java/lang/AssertionError.<init>:()V
        //   154: athrow         
        //   155: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   158: athrow         
        //   159: aconst_null    
        //   160: dup            
        //   161: ifnonnull       198
        //   164: new             Ljava/lang/IllegalStateException;
        //   167: dup            
        //   168: ldc             "@NotNull method %s.%s must not return null"
        //   170: ldc             2
        //   172: anewarray       Ljava/lang/Object;
        //   175: dup            
        //   176: ldc             0
        //   178: ldc             "com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl"
        //   180: aastore        
        //   181: dup            
        //   182: ldc             1
        //   184: ldc             "getLiteralValue"
        //   186: aastore        
        //   187: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   190: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   193: athrow         
        //   194: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCLocalizedStringImpl.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   197: athrow         
        //   198: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  21     35     38     42     Lcom/intellij/util/IncorrectOperationException;
        //  28     49     52     56     Lcom/intellij/util/IncorrectOperationException;
        //  42     63     66     70     Lcom/intellij/util/IncorrectOperationException;
        //  56     82     82     86     Lcom/intellij/util/IncorrectOperationException;
        //  92     126    126    130    Lcom/intellij/util/IncorrectOperationException;
        //  141    155    155    159    Lcom/intellij/util/IncorrectOperationException;
        //  159    194    194    198    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    
    public ItemPresentation getPresentation() {
        return (ItemPresentation)new ItemPresentation() {
            public Icon getIcon(final boolean b) {
                return null;
            }
            
            public String getPresentableText() {
                return OCLocalizedStringImpl.this.getValue();
            }
            
            public String getLocationString() {
                return ((OCStringsFile)OCLocalizedStringImpl.this.getContainingFile()).getLocalizationName();
            }
        };
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCLocalizedStringImpl.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
