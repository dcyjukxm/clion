// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCBinaryExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import java.util.Collection;
import java.util.Iterator;
import com.intellij.formatting.Indent;
import java.util.ArrayList;
import java.util.List;
import com.intellij.formatting.Block;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;

public abstract class OCRecursiveBlockTransformer
{
    public static OCRecursiveBlockTransformer CALL_CHAIN_TRANSFORMER;
    public static OCRecursiveBlockTransformer BINARY_CHAIN_TRANSFORMER;
    
    protected abstract boolean needTransformation(@NotNull final ASTNode p0);
    
    protected abstract boolean needCommonWrapper(@NotNull final ASTNode p0);
    
    protected abstract boolean needKeysFromNodeBlock(@NotNull final ASTNode p0);
    
    protected abstract IElementType getAttrPseudotype(@NotNull final ASTNode p0);
    
    protected abstract boolean chainFirst(@NotNull final OCWrappingProcessor p0, @NotNull final ASTNode p1);
    
    @NotNull
    protected abstract SplitterType getSplitterType(@NotNull final OCWrappingProcessor p0, @NotNull final Block p1, @NotNull final ASTNode p2);
    
    protected void splitAndAdd(@NotNull final OCWrappingProcessor ocWrappingProcessor, @NotNull final OCCodeBlock ocCodeBlock, @NotNull final List<Block> list, @NotNull final Block block, @NotNull final List<Block> list2, @NotNull final ASTNode astNode) {
        try {
            if (ocWrappingProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerWrappingCalculator", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "splitAndAdd"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCodeBlock == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBlock", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "splitAndAdd"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerCollector", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "splitAndAdd"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (block == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeBlock", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "splitAndAdd"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (list2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeSubBlocks", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "splitAndAdd"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "splitAndAdd"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        final IElementType attrPseudotype = this.getAttrPseudotype(astNode);
        final OCFormatterInfo calculate = ocWrappingProcessor.calculate(astNode, attrPseudotype);
        final OCFormatterInfo empty = OCFormatterInfo.EMPTY;
        final ArrayList<Block> list3 = new ArrayList<Block>();
        ArrayList<Block> list4 = new ArrayList<Block>();
        boolean b2 = false;
        Label_0341: {
            Label_0332: {
                try {
                    if (ocCodeBlock.isInDirective()) {
                        break Label_0332;
                    }
                    final OCRecursiveBlockTransformer ocRecursiveBlockTransformer = this;
                    final OCWrappingProcessor ocWrappingProcessor2 = ocWrappingProcessor;
                    final ASTNode astNode2 = astNode;
                    final boolean b = ocRecursiveBlockTransformer.chainFirst(ocWrappingProcessor2, astNode2);
                    if (b) {
                        break Label_0332;
                    }
                    break Label_0332;
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
                try {
                    final OCRecursiveBlockTransformer ocRecursiveBlockTransformer = this;
                    final OCWrappingProcessor ocWrappingProcessor2 = ocWrappingProcessor;
                    final ASTNode astNode2 = astNode;
                    final boolean b = ocRecursiveBlockTransformer.chainFirst(ocWrappingProcessor2, astNode2);
                    if (b) {
                        b2 = true;
                        break Label_0341;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            b2 = false;
        }
        boolean b3 = b2;
        final Indent continuationWithoutFirstIndent = Indent.getContinuationWithoutFirstIndent();
        for (final Block block2 : list2) {
            final SplitterType splitterType = this.getSplitterType(ocWrappingProcessor, block2, astNode);
            Label_0550: {
                Label_0504: {
                    OCFormatterInfo ocFormatterInfo = null;
                    Label_0463: {
                        Label_0452: {
                            Label_0430: {
                                Label_0413: {
                                    try {
                                        if (splitterType == SplitterType.BlockNotSplitter) {
                                            break Label_0550;
                                        }
                                        final SplitterType splitterType2 = splitterType;
                                        final SplitterType splitterType3 = SplitterType.BlockToLeft;
                                        if (splitterType2 == splitterType3) {
                                            break Label_0413;
                                        }
                                        break Label_0430;
                                    }
                                    catch (IllegalArgumentException ex9) {
                                        throw a(ex9);
                                    }
                                    try {
                                        final SplitterType splitterType2 = splitterType;
                                        final SplitterType splitterType3 = SplitterType.BlockToLeft;
                                        if (splitterType2 == splitterType3) {
                                            list4.add(block2);
                                        }
                                    }
                                    catch (IllegalArgumentException ex10) {
                                        throw a(ex10);
                                    }
                                }
                                try {
                                    if (list4.isEmpty()) {
                                        break Label_0504;
                                    }
                                    final boolean b4 = b3;
                                    if (b4) {
                                        break Label_0452;
                                    }
                                    break Label_0452;
                                }
                                catch (IllegalArgumentException ex11) {
                                    throw a(ex11);
                                }
                            }
                            try {
                                final boolean b4 = b3;
                                if (b4) {
                                    ocFormatterInfo = calculate;
                                    break Label_0463;
                                }
                            }
                            catch (IllegalArgumentException ex12) {
                                throw a(ex12);
                            }
                        }
                        ocFormatterInfo = empty;
                    }
                    final OCFormatterInfo ocFormatterInfo2 = ocFormatterInfo;
                    list3.add((Block)new OCSimpleBlock(ocFormatterInfo2.wrap, ocFormatterInfo2.alignment, continuationWithoutFirstIndent, ocCodeBlock, list4));
                    list4 = new ArrayList<Block>();
                    try {
                        if (splitterType == SplitterType.BlockToRight) {
                            list4.add(block2);
                        }
                    }
                    catch (IllegalArgumentException ex13) {
                        throw a(ex13);
                    }
                }
                boolean b5 = false;
                Label_0545: {
                    try {
                        if (!ocCodeBlock.isInDirective()) {
                            b5 = true;
                            break Label_0545;
                        }
                    }
                    catch (IllegalArgumentException ex14) {
                        throw a(ex14);
                    }
                    b5 = false;
                }
                b3 = b5;
                continue;
            }
            list4.add(block2);
        }
        Label_0704: {
            Label_0652: {
                Label_0628: {
                    OCFormatterInfo ocFormatterInfo3 = null;
                    Label_0596: {
                        Label_0585: {
                            try {
                                if (list4.isEmpty()) {
                                    break Label_0628;
                                }
                                final boolean b6 = b3;
                                if (b6) {
                                    break Label_0585;
                                }
                                break Label_0585;
                            }
                            catch (IllegalArgumentException ex15) {
                                throw a(ex15);
                            }
                            try {
                                final boolean b6 = b3;
                                if (b6) {
                                    ocFormatterInfo3 = calculate;
                                    break Label_0596;
                                }
                            }
                            catch (IllegalArgumentException ex16) {
                                throw a(ex16);
                            }
                        }
                        ocFormatterInfo3 = empty;
                    }
                    final OCFormatterInfo ocFormatterInfo4 = ocFormatterInfo3;
                    list3.add((Block)new OCSimpleBlock(ocFormatterInfo4.wrap, ocFormatterInfo4.alignment, continuationWithoutFirstIndent, ocCodeBlock, list4));
                    try {
                        if (list3.isEmpty()) {
                            break Label_0704;
                        }
                        final OCCodeBlock ocCodeBlock2 = ocCodeBlock;
                        final boolean b7 = ocCodeBlock2.isInDirective();
                        if (!b7) {
                            break Label_0652;
                        }
                        break Label_0652;
                    }
                    catch (IllegalArgumentException ex17) {
                        throw a(ex17);
                    }
                }
                try {
                    final OCCodeBlock ocCodeBlock2 = ocCodeBlock;
                    final boolean b7 = ocCodeBlock2.isInDirective();
                    if (!b7) {
                        this.applyIndentCorrection(list3, ocWrappingProcessor);
                    }
                }
                catch (IllegalArgumentException ex18) {
                    throw a(ex18);
                }
            }
            list.add((Block)new OCSimpleBlock(block.getWrap(), block.getAlignment(), block.getIndent(), ocCodeBlock, list3));
        }
        ocCodeBlock.getLocalFormatterData().put(attrPseudotype, null);
    }
    
    protected void applyIndentCorrection(final List<Block> list, final OCWrappingProcessor ocWrappingProcessor) {
    }
    
    public boolean isTransformed(@NotNull final OCWrappingProcessor ocWrappingProcessor, @NotNull final OCCodeBlock ocCodeBlock, @NotNull final List<Block> list, @NotNull final OCCodeBlock ocCodeBlock2, @NotNull final ASTNode astNode) {
        try {
            if (ocWrappingProcessor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerWrappingCalculator", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "isTransformed"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocCodeBlock == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerBlock", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "isTransformed"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerCollector", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "isTransformed"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (ocCodeBlock2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeBlock", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "isTransformed"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer", "isTransformed"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        if (this.needTransformation(astNode)) {
            final List<Block> subBlocks = ocCodeBlock2.getSubBlocks();
            Label_0285: {
                Label_0264: {
                    try {
                        if (subBlocks.isEmpty()) {
                            return false;
                        }
                        final OCRecursiveBlockTransformer ocRecursiveBlockTransformer = this;
                        final ASTNode astNode2 = astNode;
                        final boolean b = ocRecursiveBlockTransformer.needKeysFromNodeBlock(astNode2);
                        if (b) {
                            break Label_0264;
                        }
                        break Label_0285;
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                    try {
                        final OCRecursiveBlockTransformer ocRecursiveBlockTransformer = this;
                        final ASTNode astNode2 = astNode;
                        final boolean b = ocRecursiveBlockTransformer.needKeysFromNodeBlock(astNode2);
                        if (b) {
                            ocCodeBlock.getLocalFormatterData().merge(ocCodeBlock2.getLocalFormatterData());
                        }
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    if (this.needCommonWrapper(astNode)) {
                        this.splitAndAdd(ocWrappingProcessor, ocCodeBlock, list, (Block)ocCodeBlock2, subBlocks, astNode);
                        return true;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            list.addAll(subBlocks);
            return true;
        }
        return false;
    }
    
    static {
        OCRecursiveBlockTransformer.CALL_CHAIN_TRANSFORMER = new OCCallChainTransformer();
        OCRecursiveBlockTransformer.BINARY_CHAIN_TRANSFORMER = new OCBinaryChainTransformer();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private enum SplitterType
    {
        BlockNotSplitter, 
        BlockToLeft, 
        BlockToRight;
    }
    
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
    
    public static class OCBinaryChainTransformer extends OCRecursiveBlockTransformer
    {
        @Override
        protected boolean needTransformation(@NotNull final ASTNode astNode) {
            try {
                if (astNode == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "needTransformation"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (astNode.getElementType() == OCElementTypes.BINARY_EXPRESSION) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return false;
        }
        
        @Override
        protected boolean needCommonWrapper(@NotNull final ASTNode astNode) {
            try {
                if (astNode == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "needCommonWrapper"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (!a(astNode, astNode.getTreeParent())) {
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
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "needKeysFromNodeBlock"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (!this.needCommonWrapper(astNode)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return false;
        }
        
        @Override
        protected IElementType getAttrPseudotype(@NotNull final ASTNode astNode) {
            try {
                if (astNode == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "getAttrPseudotype"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return OCWrappingProcessor.BINARY_EXPRESSION_PSEUDOTYPE;
        }
        
        @Override
        protected boolean chainFirst(@NotNull final OCWrappingProcessor ocWrappingProcessor, @NotNull final ASTNode astNode) {
            try {
                if (ocWrappingProcessor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "ownerWrappingCalculator", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "chainFirst"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (astNode == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer", "chainFirst"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return true;
        }
        
        @NotNull
        @Override
        protected SplitterType getSplitterType(@NotNull final OCWrappingProcessor p0, @NotNull final Block p1, @NotNull final ASTNode p2) {
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
            //    18: ldc             "ownerWrappingCalculator"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "getSplitterType"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    43: athrow         
            //    44: aload_2        
            //    45: ifnonnull       88
            //    48: new             Ljava/lang/IllegalArgumentException;
            //    51: dup            
            //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    54: ldc             3
            //    56: anewarray       Ljava/lang/Object;
            //    59: dup            
            //    60: ldc             0
            //    62: ldc             "nodeSubBlock"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "getSplitterType"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //    87: athrow         
            //    88: aload_3        
            //    89: ifnonnull       132
            //    92: new             Ljava/lang/IllegalArgumentException;
            //    95: dup            
            //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
            //    98: ldc             3
            //   100: anewarray       Ljava/lang/Object;
            //   103: dup            
            //   104: ldc             0
            //   106: ldc             "node"
            //   108: aastore        
            //   109: dup            
            //   110: ldc             1
            //   112: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
            //   114: aastore        
            //   115: dup            
            //   116: ldc             2
            //   118: ldc             "getSplitterType"
            //   120: aastore        
            //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //   127: athrow         
            //   128: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   131: athrow         
            //   132: aload_2        
            //   133: invokestatic    com/jetbrains/cidr/lang/formatting/OCCodeBlock.getBlockType:(Lcom/intellij/formatting/Block;)Lcom/intellij/psi/tree/IElementType;
            //   136: astore          4
            //   138: aload           4
            //   140: instanceof      Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
            //   143: ifeq            233
            //   146: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SHIFT_OPERATIONS:Lcom/intellij/psi/tree/TokenSet;
            //   149: aload           4
            //   151: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
            //   154: ifne            181
            //   157: goto            164
            //   160: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   163: athrow         
            //   164: aload_1        
            //   165: invokevirtual   com/jetbrains/cidr/lang/formatting/OCWrappingProcessor.getSettings:()Lcom/intellij/psi/codeStyle/CommonCodeStyleSettings;
            //   168: getfield        com/intellij/psi/codeStyle/CommonCodeStyleSettings.BINARY_OPERATION_SIGN_ON_NEXT_LINE:Z
            //   171: ifeq            191
            //   174: goto            181
            //   177: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   180: athrow         
            //   181: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType.BlockToRight:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType;
            //   184: goto            194
            //   187: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   190: athrow         
            //   191: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType.BlockToLeft:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType;
            //   194: dup            
            //   195: ifnonnull       232
            //   198: new             Ljava/lang/IllegalStateException;
            //   201: dup            
            //   202: ldc             "@NotNull method %s.%s must not return null"
            //   204: ldc             2
            //   206: anewarray       Ljava/lang/Object;
            //   209: dup            
            //   210: ldc             0
            //   212: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
            //   214: aastore        
            //   215: dup            
            //   216: ldc             1
            //   218: ldc             "getSplitterType"
            //   220: aastore        
            //   221: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   224: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   227: athrow         
            //   228: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   231: athrow         
            //   232: areturn        
            //   233: getstatic       com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType.BlockNotSplitter:Lcom/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$SplitterType;
            //   236: dup            
            //   237: ifnonnull       274
            //   240: new             Ljava/lang/IllegalStateException;
            //   243: dup            
            //   244: ldc             "@NotNull method %s.%s must not return null"
            //   246: ldc             2
            //   248: anewarray       Ljava/lang/Object;
            //   251: dup            
            //   252: ldc             0
            //   254: ldc             "com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer"
            //   256: aastore        
            //   257: dup            
            //   258: ldc             1
            //   260: ldc             "getSplitterType"
            //   262: aastore        
            //   263: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   266: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   269: athrow         
            //   270: invokestatic    com/jetbrains/cidr/lang/formatting/OCRecursiveBlockTransformer$OCBinaryChainTransformer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
            //   273: athrow         
            //   274: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                
            //  -----  -----  -----  -----  ------------------------------------
            //  0      40     40     44     Ljava/lang/IllegalArgumentException;
            //  44     84     84     88     Ljava/lang/IllegalArgumentException;
            //  88     128    128    132    Ljava/lang/IllegalArgumentException;
            //  138    157    160    164    Ljava/lang/IllegalArgumentException;
            //  146    174    177    181    Ljava/lang/IllegalArgumentException;
            //  164    187    187    191    Ljava/lang/IllegalArgumentException;
            //  194    228    228    232    Ljava/lang/IllegalArgumentException;
            //  233    270    270    274    Ljava/lang/IllegalArgumentException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0164:
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
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
        
        static class ChainOperations
        {
            private static TokenSet ADDITIVE;
            private static TokenSet MULTIPLICATIVE;
            private static TokenSet BINARY_SHIFT;
            private static TokenSet LOGICAL;
            private static TokenSet QUALIFYING;
            
            private static boolean a(@Nullable final ASTNode astNode, @Nullable final ASTNode astNode2) {
                if (astNode == null || astNode2 == null) {
                    return false;
                }
                final PsiElement psi = astNode.getPsi();
                final PsiElement psi2 = astNode2.getPsi();
                if (psi == null || psi2 == null || !(psi instanceof OCBinaryExpression) || !(psi2 instanceof OCBinaryExpression)) {
                    return false;
                }
                final OCElementType operationSign = ((OCBinaryExpression)psi).getOperationSign();
                final OCElementType operationSign2 = ((OCBinaryExpression)psi2).getOperationSign();
                return operationSign == operationSign2 || (ChainOperations.ADDITIVE.contains((IElementType)operationSign) && ChainOperations.ADDITIVE.contains((IElementType)operationSign2)) || (ChainOperations.MULTIPLICATIVE.contains((IElementType)operationSign) && ChainOperations.MULTIPLICATIVE.contains((IElementType)operationSign2)) || (ChainOperations.BINARY_SHIFT.contains((IElementType)operationSign) && ChainOperations.BINARY_SHIFT.contains((IElementType)operationSign2)) || (ChainOperations.LOGICAL.contains((IElementType)operationSign) && ChainOperations.LOGICAL.contains((IElementType)operationSign2)) || (ChainOperations.QUALIFYING.contains((IElementType)operationSign) && ChainOperations.QUALIFYING.contains((IElementType)operationSign2));
            }
            
            static {
                ChainOperations.ADDITIVE = TokenSet.create(new IElementType[] { OCTokenTypes.PLUS, OCTokenTypes.MINUS });
                ChainOperations.MULTIPLICATIVE = TokenSet.create(new IElementType[] { OCTokenTypes.MUL, OCTokenTypes.DIV, OCTokenTypes.PERC });
                ChainOperations.BINARY_SHIFT = TokenSet.create(new IElementType[] { OCTokenTypes.LTLT, OCTokenTypes.GTGT });
                ChainOperations.LOGICAL = TokenSet.create(new IElementType[] { OCTokenTypes.LT, OCTokenTypes.GT, OCTokenTypes.LTEQ, OCTokenTypes.GTEQ, OCTokenTypes.EQEQ, OCTokenTypes.EXCLEQ });
                ChainOperations.QUALIFYING = TokenSet.create(new IElementType[] { OCTokenTypes.DOT_MUL, OCTokenTypes.DEREF_MUL });
            }
        }
    }
}
