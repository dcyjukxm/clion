// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.documentation.doxygen.api.TagItem;
import java.util.List;
import com.jetbrains.cidr.lang.documentation.doxygen.api.InfoItem;

private static class CompositeInfoItem implements InfoItem
{
    private final String name;
    private final List<TagItem> tags;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public CompositeInfoItem(@NotNull final TagItem tagItem) {
        if (tagItem == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/documentation/DoxygenRender$CompositeInfoItem", "<init>"));
        }
        this.tags = new ArrayList<TagItem>();
        this.name = tagItem.getName();
        this.tags.add(tagItem);
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<TagItem> getTags() {
        return this.tags;
    }
    
    public void addItem(@NotNull final TagItem tagItem) {
        try {
            if (tagItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/documentation/DoxygenRender$CompositeInfoItem", "addItem"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        Label_0073: {
            try {
                if (CompositeInfoItem.$assertionsDisabled) {
                    break Label_0073;
                }
                final CompositeInfoItem compositeInfoItem = this;
                final String s = compositeInfoItem.name;
                final TagItem tagItem2 = tagItem;
                final String s2 = tagItem2.getName();
                final boolean b = s.equals(s2);
                if (!b) {
                    break Label_0073;
                }
                break Label_0073;
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            try {
                final CompositeInfoItem compositeInfoItem = this;
                final String s = compositeInfoItem.name;
                final TagItem tagItem2 = tagItem;
                final String s2 = tagItem2.getName();
                final boolean b = s.equals(s2);
                if (!b) {
                    throw new AssertionError();
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        this.tags.add(tagItem);
    }
    
    @NotNull
    @Override
    public String getDescription() {
        throw new UnsupportedOperationException();
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!DoxygenRender.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
        return ex;
    }
}
