// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import org.jetbrains.annotations.Contract;
import com.intellij.formatting.ASTBlock;
import com.intellij.formatting.ChildAttributes;
import com.intellij.formatting.Spacing;
import com.intellij.lang.ASTNode;
import com.intellij.formatting.Alignment;
import org.jetbrains.annotations.Nullable;
import com.intellij.formatting.Wrap;
import com.intellij.openapi.util.TextRange;
import com.intellij.formatting.Indent;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.intellij.formatting.Block;

public class OCSimpleBlock implements Block, OCIndentChanger
{
    @NotNull
    protected OCCodeBlock myOwnerBlock;
    @NotNull
    private List<Block> myBlocks;
    @NotNull
    private Indent myIndent;
    private TextRange myTextRange;
    @Nullable
    private Wrap myWrap;
    @Nullable
    private Alignment myAlignment;
    @NotNull
    private ASTNode myFirst;
    @NotNull
    private ASTNode myLast;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCSimpleBlock(@Nullable final Wrap myWrap, @Nullable final Alignment myAlignment, @Nullable final Indent indent, @NotNull final OCCodeBlock myOwnerBlock, @NotNull final List<Block> myBlocks) {
        if (myOwnerBlock == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBlock", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "<init>"));
        }
        if (myBlocks == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "blocks", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "<init>"));
        }
        if (!OCSimpleBlock.$assertionsDisabled) {
            try {
                if (myBlocks.isEmpty()) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        Indent noneIndent = null;
        Label_0142: {
            try {
                this.myOwnerBlock = myOwnerBlock;
                this.myBlocks = myBlocks;
                if (indent != null) {
                    noneIndent = indent;
                    break Label_0142;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            noneIndent = Indent.getNoneIndent();
        }
        this.myIndent = noneIndent;
        this.myWrap = myWrap;
        this.myAlignment = myAlignment;
        this.myFirst = extractFirstNode(this.myBlocks.get(0));
        this.myLast = extractLastNode(this.myBlocks.get(this.myBlocks.size() - 1));
    }
    
    @NotNull
    public TextRange getTextRange() {
        try {
            if (this.myTextRange == null) {
                this.myTextRange = getRangeFromSubBlocks((Block)this);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        TextRange myTextRange;
        try {
            myTextRange = this.myTextRange;
            if (myTextRange == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getTextRange"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return myTextRange;
    }
    
    @NotNull
    public List<Block> getSubBlocks() {
        List<Block> myBlocks;
        try {
            myBlocks = this.myBlocks;
            if (myBlocks == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getSubBlocks"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myBlocks;
    }
    
    @Nullable
    public Wrap getWrap() {
        return this.myWrap;
    }
    
    @NotNull
    public Indent getIndent() {
        Indent myIndent;
        try {
            myIndent = this.myIndent;
            if (myIndent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getIndent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myIndent;
    }
    
    @Nullable
    public Alignment getAlignment() {
        return this.myAlignment;
    }
    
    @Nullable
    public Spacing getSpacing(@Nullable final Block block, @NotNull final Block block2) {
        try {
            if (block2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child2", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getSpacing"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myOwnerBlock.getSpacing(block, block2);
    }
    
    @NotNull
    public ChildAttributes getChildAttributes(final int p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokevirtual   com/jetbrains/cidr/lang/formatting/OCSimpleBlock.getSubBlocks:()Ljava/util/List;
        //     4: iload_1        
        //     5: iconst_1       
        //     6: isub           
        //     7: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    12: checkcast       Lcom/intellij/formatting/Block;
        //    15: astore_2       
        //    16: aload_2        
        //    17: instanceof      Lcom/intellij/formatting/ASTBlock;
        //    20: ifeq            102
        //    23: aload_2        
        //    24: checkcast       Lcom/intellij/formatting/ASTBlock;
        //    27: invokeinterface com/intellij/formatting/ASTBlock.getNode:()Lcom/intellij/lang/ASTNode;
        //    32: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/tree/IElementType;
        //    35: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.LBRACE:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //    38: if_acmpne       102
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    47: athrow         
        //    48: aload_0        
        //    49: getfield        com/jetbrains/cidr/lang/formatting/OCSimpleBlock.myOwnerBlock:Lcom/jetbrains/cidr/lang/formatting/OCCodeBlock;
        //    52: iconst_1       
        //    53: invokevirtual   com/jetbrains/cidr/lang/formatting/OCCodeBlock.getChildAttributes:(I)Lcom/intellij/formatting/ChildAttributes;
        //    56: dup            
        //    57: ifnonnull       101
        //    60: goto            67
        //    63: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    66: athrow         
        //    67: new             Ljava/lang/IllegalStateException;
        //    70: dup            
        //    71: ldc             "@NotNull method %s.%s must not return null"
        //    73: ldc             2
        //    75: anewarray       Ljava/lang/Object;
        //    78: dup            
        //    79: ldc             0
        //    81: ldc             "com/jetbrains/cidr/lang/formatting/OCSimpleBlock"
        //    83: aastore        
        //    84: dup            
        //    85: ldc             1
        //    87: ldc             "getChildAttributes"
        //    89: aastore        
        //    90: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    93: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    96: athrow         
        //    97: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: areturn        
        //   102: new             Lcom/intellij/formatting/ChildAttributes;
        //   105: dup            
        //   106: aload_2        
        //   107: invokeinterface com/intellij/formatting/Block.getIndent:()Lcom/intellij/formatting/Indent;
        //   112: aload_2        
        //   113: invokeinterface com/intellij/formatting/Block.getAlignment:()Lcom/intellij/formatting/Alignment;
        //   118: invokespecial   com/intellij/formatting/ChildAttributes.<init>:(Lcom/intellij/formatting/Indent;Lcom/intellij/formatting/Alignment;)V
        //   121: dup            
        //   122: ifnonnull       159
        //   125: new             Ljava/lang/IllegalStateException;
        //   128: dup            
        //   129: ldc             "@NotNull method %s.%s must not return null"
        //   131: ldc             2
        //   133: anewarray       Ljava/lang/Object;
        //   136: dup            
        //   137: ldc             0
        //   139: ldc             "com/jetbrains/cidr/lang/formatting/OCSimpleBlock"
        //   141: aastore        
        //   142: dup            
        //   143: ldc             1
        //   145: ldc             "getChildAttributes"
        //   147: aastore        
        //   148: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   151: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   154: athrow         
        //   155: invokestatic    com/jetbrains/cidr/lang/formatting/OCSimpleBlock.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   158: athrow         
        //   159: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  16     41     44     48     Ljava/lang/IllegalArgumentException;
        //  23     60     63     67     Ljava/lang/IllegalArgumentException;
        //  48     97     97     101    Ljava/lang/IllegalArgumentException;
        //  102    155    155    159    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    public boolean isIncomplete() {
        return this.myOwnerBlock.isIncomplete();
    }
    
    public boolean isLeaf() {
        return false;
    }
    
    @Override
    public String toString() {
        return getTextFromRange(this.myOwnerBlock.getNode(), this.getTextRange());
    }
    
    public ASTNode getFirstNode() {
        return this.myFirst;
    }
    
    public ASTNode getLastNode() {
        return this.myLast;
    }
    
    @Contract("null->null")
    public static ASTNode extractFirstNode(@Nullable final Block block) {
        try {
            if (block instanceof ASTBlock) {
                return ((ASTBlock)block).getNode();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (block instanceof OCSimpleBlock) {
                return ((OCSimpleBlock)block).getFirstNode();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @Contract("null->null")
    public static ASTNode extractLastNode(@Nullable final Block block) {
        try {
            if (block instanceof ASTBlock) {
                return ((ASTBlock)block).getNode();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (block instanceof OCSimpleBlock) {
                return ((OCSimpleBlock)block).getLastNode();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    public static TextRange getRangeFromSubBlocks(@NotNull final Block block) {
        try {
            if (block == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "block", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getRangeFromSubBlocks"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List subBlocks = block.getSubBlocks();
        TextRange textRange = null;
        Label_0120: {
            try {
                if (subBlocks.size() == 0) {
                    final TextRange empty_RANGE;
                    textRange = (empty_RANGE = TextRange.EMPTY_RANGE);
                    break Label_0120;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            TextRange empty_RANGE;
            textRange = (empty_RANGE = new TextRange(subBlocks.get(0).getTextRange().getStartOffset(), subBlocks.get(subBlocks.size() - 1).getTextRange().getEndOffset()));
            try {
                if (empty_RANGE == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getRangeFromSubBlocks"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return textRange;
    }
    
    public static String getTextFromRange(@NotNull ASTNode astNode, @NotNull final TextRange textRange) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerNode", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getTextFromRange"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/formatting/OCSimpleBlock", "getTextFromRange"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        ASTNode treeParent = astNode;
        while (true) {
            Label_0116: {
                try {
                    if (treeParent == null) {
                        break;
                    }
                    if (treeParent.getStartOffset() > textRange.getStartOffset()) {
                        break Label_0116;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                astNode = treeParent;
            }
            treeParent = treeParent.getTreeParent();
        }
        return textRange.shiftRight(-astNode.getStartOffset()).substring(astNode.getText()) + textRange;
    }
    
    public void putIndent(final Indent myIndent) {
        this.myIndent = myIndent;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSimpleBlock.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
