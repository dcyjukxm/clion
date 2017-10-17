// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.formatting;

import org.jetbrains.annotations.Nullable;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import com.intellij.formatting.Indent;
import com.intellij.psi.tree.IElementType;

class OCCodeBlock$1 extends OCFormatterUtil.LeftBracesProcessor<Object> {
    final /* synthetic */ IElementType val$nodeType;
    final /* synthetic */ OCCodeBlock this$0;
    
    @Override
    public Object processNamespace() {
        Label_0136: {
            try {
                switch (OCCodeBlock.this.myOCSettings.NAMESPACE_BRACE_PLACEMENT) {
                    case 3: {
                        OCCodeBlock.this.myBracesIndent = Indent.getNormalIndent();
                        OCCodeBlock.this.myChildIndent = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.mySettings.getIndentOptions().INDENT_SIZE + OCCodeBlock.this.myOCSettings.INDENT_NAMESPACE_MEMBERS);
                        return null;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        break Label_0136;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCCodeBlock.this.myBracesIndent = Indent.getNormalIndent();
            OCCodeBlock.this.myChildIndent = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.mySettings.getIndentOptions().INDENT_SIZE * 2 + OCCodeBlock.this.myOCSettings.INDENT_NAMESPACE_MEMBERS);
            return null;
        }
        OCCodeBlock.this.myChildIndent = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.myOCSettings.INDENT_NAMESPACE_MEMBERS);
        return null;
    }
    
    @Override
    public Object processInterfaceOrStructure() {
        OCFormatterUtil.LeftBracesProcessor<Object> leftBracesProcessor = null;
        Label_0032: {
            try {
                if (!OCFormatterUtil.isStructure(this.val$nodeType)) {
                    return this.a(OCCodeBlock.this.mySettings.CLASS_BRACE_STYLE);
                }
                leftBracesProcessor = this;
                final OCFormatterUtil.LeftBracesProcessor<Object> leftBracesProcessor2 = this;
                final OCFormatterUtil.LeftBracesProcessor<Object> leftBracesProcessor3 = this;
                final OCCodeBlock ocCodeBlock = leftBracesProcessor3.this$0;
                final ASTNode astNode = OCCodeBlock.access$000(ocCodeBlock);
                final boolean b = leftBracesProcessor2.a(astNode);
                if (b) {
                    break Label_0032;
                }
                break Label_0032;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                leftBracesProcessor = this;
                final OCFormatterUtil.LeftBracesProcessor<Object> leftBracesProcessor2 = this;
                final OCFormatterUtil.LeftBracesProcessor<Object> leftBracesProcessor3 = this;
                final OCCodeBlock ocCodeBlock = leftBracesProcessor3.this$0;
                final ASTNode astNode = OCCodeBlock.access$000(ocCodeBlock);
                final boolean b = leftBracesProcessor2.a(astNode);
                if (b) {
                    final int n = OCCodeBlock.this.myOCSettings.INDENT_C_STRUCT_MEMBERS;
                    return leftBracesProcessor.b(n);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final int n = OCCodeBlock.this.myOCSettings.INDENT_CLASS_MEMBERS;
        return leftBracesProcessor.b(n);
        o = this.a(OCCodeBlock.this.mySettings.CLASS_BRACE_STYLE);
        return o;
    }
    
    private boolean a(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "struct", "com/jetbrains/cidr/lang/formatting/OCCodeBlock$1", "isPlainStruct"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0075: {
            try {
                if (astNode.findChildByType((IElementType)OCTokenTypes.CLASS_KEYWORD) != null) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final TokenSet set = OCTokenTypes.CPP_VISIBILITY_KEYWORDS;
                final ASTNode astNode3 = astNode2.findChildByType(set);
                if (astNode3 == null) {
                    break Label_0075;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final ASTNode astNode2 = astNode;
                final TokenSet set = OCTokenTypes.CPP_VISIBILITY_KEYWORDS;
                final ASTNode astNode3 = astNode2.findChildByType(set);
                if (astNode3 == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Override
    public Object processMethod() {
        return this.a(OCCodeBlock.this.myOCSettings.METHOD_BRACE_PLACEMENT);
    }
    
    @Override
    public Object processFunction() {
        return this.a(OCCodeBlock.this.myOCSettings.FUNCTION_BRACE_PLACEMENT);
    }
    
    @Override
    public Object processBlock() {
        Label_0120: {
            try {
                switch (OCCodeBlock.this.myOCSettings.BLOCK_BRACE_PLACEMENT) {
                    case 3: {
                        OCCodeBlock.this.myBracesIndent = Indent.getNormalIndent();
                        OCCodeBlock.this.myChildIndent = Indent.getSpaceIndent(OCCodeBlock.this.myOCSettings.INDENT_INSIDE_CODE_BLOCK);
                        return null;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        break Label_0120;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCCodeBlock.this.myBracesIndent = Indent.getNormalIndent();
            OCCodeBlock.this.myChildIndent = Indent.getSpaceIndent(OCCodeBlock.this.mySettings.getIndentOptions().INDENT_SIZE + OCCodeBlock.this.myOCSettings.INDENT_INSIDE_CODE_BLOCK);
            return null;
        }
        OCCodeBlock.this.myChildIndent = Indent.getSpaceIndent(OCCodeBlock.this.myOCSettings.INDENT_INSIDE_CODE_BLOCK);
        return null;
    }
    
    @Override
    public Object processGeneral() {
        return this.a(OCCodeBlock.this.mySettings.BRACE_STYLE);
    }
    
    @Nullable
    private Object a(final int n) {
        Label_0137: {
            try {
                switch (n) {
                    case 3: {
                        final OCCodeBlock this$0 = OCCodeBlock.this;
                        final OCCodeBlock this$2 = OCCodeBlock.this;
                        final Indent normalIndent = Indent.getNormalIndent();
                        this$2.myChildIndent = normalIndent;
                        this$0.myBracesIndent = normalIndent;
                        OCCodeBlock.this.myChildIndentEx = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.myOCSettings.INDENT_VISIBILITY_KEYWORDS);
                        return null;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        break Label_0137;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCCodeBlock.this.myBracesIndent = Indent.getNormalIndent();
            OCCodeBlock.this.myChildIndent = OCCodeBlock.getDoubleIndent(OCCodeBlock.this.mySettings);
            OCCodeBlock.this.myChildIndentEx = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.mySettings.getIndentOptions().INDENT_SIZE + OCCodeBlock.this.myOCSettings.INDENT_VISIBILITY_KEYWORDS);
            return null;
        }
        OCCodeBlock.this.myChildIndentEx = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.myOCSettings.INDENT_VISIBILITY_KEYWORDS);
        return null;
    }
    
    @Nullable
    private Object b(final int n) {
        Label_0131: {
            try {
                switch (OCCodeBlock.this.mySettings.CLASS_BRACE_STYLE) {
                    case 3: {
                        final OCCodeBlock this$0 = OCCodeBlock.this;
                        final OCCodeBlock this$2 = OCCodeBlock.this;
                        final Indent spaceIndentEnforcedToChildren = OCCodeBlock.getSpaceIndentEnforcedToChildren(n);
                        this$2.myChildIndent = spaceIndentEnforcedToChildren;
                        this$0.myBracesIndent = spaceIndentEnforcedToChildren;
                        OCCodeBlock.this.myChildIndentEx = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.myOCSettings.INDENT_VISIBILITY_KEYWORDS);
                        return null;
                    }
                    case 4: {
                        break;
                    }
                    default: {
                        break Label_0131;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            OCCodeBlock.this.myBracesIndent = OCCodeBlock.getSpaceIndentEnforcedToChildren(n);
            OCCodeBlock.this.myChildIndent = OCCodeBlock.getSpaceIndentEnforcedToChildren(n * 2);
            OCCodeBlock.this.myChildIndentEx = OCCodeBlock.getSpaceIndentEnforcedToChildren(n + OCCodeBlock.this.myOCSettings.INDENT_VISIBILITY_KEYWORDS);
            return null;
        }
        OCCodeBlock.this.myChildIndent = OCCodeBlock.getSpaceIndentEnforcedToChildren(n);
        OCCodeBlock.this.myChildIndentEx = OCCodeBlock.getSpaceIndentEnforcedToChildren(OCCodeBlock.this.myOCSettings.INDENT_VISIBILITY_KEYWORDS);
        return null;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}