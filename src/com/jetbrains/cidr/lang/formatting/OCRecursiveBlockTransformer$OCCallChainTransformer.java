// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.Condition;
import com.intellij.formatting.Block;
import java.util.List;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

protected static class OCCallChainTransformer extends OCRecursiveBlockTransformer
{
    @Override
    protected boolean needTransformation(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "needTransformation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final IElementType elementType = astNode.getElementType();
        Label_0072: {
            try {
                if (elementType == OCElementTypes.QUALIFIED_EXPRESSION) {
                    break Label_0072;
                }
                final OCElementType ocElementType = (OCElementType)elementType;
                final OCElementType ocElementType2 = OCElementTypes.CALL_EXPRESSION;
                if (ocElementType == ocElementType2) {
                    break Label_0072;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final OCElementType ocElementType = (OCElementType)elementType;
                final OCElementType ocElementType2 = OCElementTypes.CALL_EXPRESSION;
                if (ocElementType == ocElementType2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @Override
    protected boolean needCommonWrapper(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "needCommonWrapper"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!this.needTransformation(astNode.getTreeParent())) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @Override
    protected boolean needKeysFromNodeBlock(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "needKeysFromNodeBlock"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return true;
    }
    
    @Override
    protected IElementType getAttrPseudotype(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "getAttrPseudotype"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return OCWrappingProcessor.CHAINED_CALL_PSEUDOTYPE;
    }
    
    @Override
    protected boolean chainFirst(@NotNull final OCWrappingProcessor ocWrappingProcessor, @NotNull final ASTNode astNode) {
        try {
            if (ocWrappingProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerWrappingCalculator", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "chainFirst"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "chainFirst"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @Override
    protected void applyIndentCorrection(final List<Block> list, final OCWrappingProcessor ocWrappingProcessor) {
        OCFormatterUtil.applyIndentCorrection(false, list, ocWrappingProcessor.getSettings().METHOD_CALL_CHAIN_WRAP, ocWrappingProcessor.getSettings().KEEP_LINE_BREAKS, (Condition<ASTNode>)(astNode -> {
            try {
                if (OCElementUtil.getElementType(astNode) == OCElementTypes.QUALIFIED_EXPRESSION_ACCESSOR) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return false;
        }));
    }
    
    @NotNull
    @Override
    protected SplitterType getSplitterType(@NotNull final OCWrappingProcessor ocWrappingProcessor, @NotNull final Block block, @NotNull final ASTNode astNode) {
        try {
            if (ocWrappingProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerWrappingCalculator", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "getSplitterType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (block == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeSubBlock", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "getSplitterType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "getSplitterType"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        SplitterType splitterType = null;
        Label_0155: {
            try {
                if (OCCodeBlock.getBlockType(block) == OCElementTypes.QUALIFIED_EXPRESSION_ACCESSOR) {
                    final SplitterType splitterType2;
                    splitterType = (splitterType2 = SplitterType.BlockToRight);
                    break Label_0155;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            SplitterType splitterType2;
            splitterType = (splitterType2 = SplitterType.BlockNotSplitter);
            try {
                if (splitterType2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCCallChainTransformer", "getSplitterType"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return splitterType;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
