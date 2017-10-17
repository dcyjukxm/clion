// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.jetbrains.cidr.lang.documentation.doxygen.api.TagItem;
import com.jetbrains.cidr.doxygen.psi.impl.DxPsiImplUtil;
import com.intellij.lang.ASTNode;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.documentation.doxygen.api.InfoItem;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCommentGroup;

public class DoxygenCommentGroupImpl implements DoxygenCommentGroup
{
    @NotNull
    private final String options;
    @NotNull
    private final List<InfoItem> items;
    
    private DoxygenCommentGroupImpl(@NotNull final String options, @NotNull final List<InfoItem> items) {
        if (options == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "options", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentGroupImpl", "<init>"));
        }
        if (items == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "items", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentGroupImpl", "<init>"));
        }
        this.options = options;
        this.items = items;
    }
    
    @NotNull
    @Override
    public String getOptions() {
        String options;
        try {
            options = this.options;
            if (options == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentGroupImpl", "getOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return options;
    }
    
    @NotNull
    @Override
    public List<InfoItem> getContent() {
        List<InfoItem> items;
        try {
            items = this.items;
            if (items == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentGroupImpl", "getContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return items;
    }
    
    @Override
    public boolean isEmpty() {
        Label_0029: {
            try {
                if (!this.options.isEmpty()) {
                    return false;
                }
                final DoxygenCommentGroupImpl doxygenCommentGroupImpl = this;
                final List<InfoItem> list = doxygenCommentGroupImpl.items;
                final boolean b = list.isEmpty();
                if (b) {
                    break Label_0029;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final DoxygenCommentGroupImpl doxygenCommentGroupImpl = this;
                final List<InfoItem> list = doxygenCommentGroupImpl.items;
                final boolean b = list.isEmpty();
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    @Override
    public boolean equals(final Object o) {
        try {
            if (this == o) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0039: {
            try {
                if (o == null) {
                    return false;
                }
                final DoxygenCommentGroupImpl doxygenCommentGroupImpl = this;
                final Class<? extends DoxygenCommentGroupImpl> clazz = doxygenCommentGroupImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
                break Label_0039;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final DoxygenCommentGroupImpl doxygenCommentGroupImpl = this;
                final Class<? extends DoxygenCommentGroupImpl> clazz = doxygenCommentGroupImpl.getClass();
                final Object o2 = o;
                final Class<?> clazz2 = o2.getClass();
                if (clazz != clazz2) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final DoxygenCommentGroupImpl doxygenCommentGroupImpl2 = (DoxygenCommentGroupImpl)o;
        try {
            if (!this.options.equals(doxygenCommentGroupImpl2.options)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final int size = this.items.size();
        try {
            if (size != doxygenCommentGroupImpl2.items.size()) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        int n = 0;
        while (true) {
            Label_0146: {
                try {
                    if (n >= size) {
                        break;
                    }
                    final DoxygenCommentGroupImpl doxygenCommentGroupImpl3 = this;
                    final List<InfoItem> list = doxygenCommentGroupImpl3.items;
                    final int n2 = n;
                    final InfoItem infoItem = list.get(n2);
                    final InfoItem infoItem2 = infoItem;
                    final DoxygenCommentGroupImpl doxygenCommentGroupImpl4 = doxygenCommentGroupImpl2;
                    final List<InfoItem> list2 = doxygenCommentGroupImpl4.items;
                    final int n3 = n;
                    final InfoItem infoItem3 = list2.get(n3);
                    final boolean b = infoItem2.equals(infoItem3);
                    if (!b) {
                        return false;
                    }
                    break Label_0146;
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
                try {
                    final DoxygenCommentGroupImpl doxygenCommentGroupImpl3 = this;
                    final List<InfoItem> list = doxygenCommentGroupImpl3.items;
                    final int n2 = n;
                    final InfoItem infoItem = list.get(n2);
                    final InfoItem infoItem2 = infoItem;
                    final DoxygenCommentGroupImpl doxygenCommentGroupImpl4 = doxygenCommentGroupImpl2;
                    final List<InfoItem> list2 = doxygenCommentGroupImpl4.items;
                    final int n3 = n;
                    final InfoItem infoItem3 = list2.get(n3);
                    final boolean b = infoItem2.equals(infoItem3);
                    if (!b) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
            ++n;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return 31 * this.options.hashCode() + this.items.hashCode();
    }
    
    public String getDescription() {
        return StringUtil.join((Collection)this.items, InfoItem::getDescription, " ");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
}
