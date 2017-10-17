// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.jetbrains.cidr.lang.documentation.doxygen.api.TagItem;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.doxygen.psi.impl.DxPsiImplUtil;
import org.jetbrains.annotations.NotNull;
import com.intellij.lang.ASTNode;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.documentation.doxygen.api.InfoItem;
import java.util.List;

static class Builder implements ComplexItemBuilder
{
    private final List<InfoItem> tags;
    private String options;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    Builder() {
        this.tags = new ArrayList<InfoItem>();
        this.options = "";
    }
    
    public void addOptions(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentGroupImpl$Builder", "addOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0067: {
            try {
                if (Builder.$assertionsDisabled) {
                    break Label_0067;
                }
                final Builder builder = this;
                final String s = builder.options;
                final boolean b = s.isEmpty();
                if (!b) {
                    break Label_0067;
                }
                break Label_0067;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final Builder builder = this;
                final String s = builder.options;
                final boolean b = s.isEmpty();
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        this.options = "[" + StringUtil.join((Collection)DxPsiImplUtil.extractOptions(astNode), ", ") + "]";
    }
    
    @Override
    public void addTag(@NotNull final TagItem tagItem) {
        try {
            if (tagItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tag", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentGroupImpl$Builder", "addTag"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.tags.add(tagItem);
    }
    
    @Override
    public void addText(@NotNull String trim) {
        try {
            if (trim == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentGroupImpl$Builder", "addText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        trim = trim.trim();
        try {
            if (trim.isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.tags.add(new InfoItemImpl(trim));
    }
    
    public DoxygenCommentGroupImpl build() {
        return new DoxygenCommentGroupImpl(this.options, this.tags, null);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!DoxygenCommentGroupImpl.class.desiredAssertionStatus()) {
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
