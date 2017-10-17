// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen.comment;

import com.jetbrains.cidr.doxygen.psi.DxParam;
import org.jetbrains.annotations.NotNull;
import java.util.Collections;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import java.util.ArrayList;
import com.jetbrains.cidr.doxygen.psi.DxDocTag;
import com.jetbrains.cidr.lang.documentation.doxygen.api.InfoItem;
import java.util.List;
import com.jetbrains.cidr.lang.documentation.doxygen.api.TagItem;

public class DoxygenCommentTagImpl implements TagItem, ComplexItemBuilder
{
    private final String name;
    private final String options;
    private final List<String> params;
    private final List<InfoItem> items;
    
    public DoxygenCommentTagImpl(final DxDocTag dxDocTag) {
        this.items = new ArrayList<InfoItem>();
        this.name = dxDocTag.getName();
        if (dxDocTag.getOptions().isEmpty()) {
            this.options = "";
        }
        else {
            this.options = "[" + StringUtil.join((Collection)dxDocTag.getOptions(), ", ") + "]";
        }
        this.params = dxDocTag.getParamList().stream().map(dxParam -> dxParam.getName()).collect((Collector<? super Object, ?, List<String>>)Collectors.toList());
    }
    
    public DoxygenCommentTagImpl(final String name, final String options, final String s, final String s2) {
        this.items = new ArrayList<InfoItem>();
        this.name = name;
        this.options = options;
        this.params = Collections.singletonList(s);
        this.addText(s2);
    }
    
    @Override
    public void addText(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl", "addText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s.isEmpty()) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.items.add(new InfoItemImpl(s));
    }
    
    @Override
    public void addTag(@NotNull final TagItem tagItem) {
        try {
            if (tagItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl", "addTag"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.items.add(tagItem);
    }
    
    @NotNull
    @Override
    public String getName() {
        String name;
        try {
            name = this.name;
            if (name == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name;
    }
    
    @NotNull
    @Override
    public String getOptions() {
        String options;
        try {
            options = this.options;
            if (options == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl", "getOptions"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return options;
    }
    
    @NotNull
    @Override
    public List<String> getParameters() {
        List<String> params;
        try {
            params = this.params;
            if (params == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl", "getParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return params;
    }
    
    @NotNull
    @Override
    public String getDescription() {
        String trimTrailing;
        try {
            trimTrailing = StringUtil.trimTrailing(StringUtil.join((Collection)this.items, InfoItem::getDescription, " "));
            if (trimTrailing == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl", "getDescription"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return trimTrailing;
    }
    
    @NotNull
    @Override
    public List<InfoItem> getContent() {
        ArrayList list;
        try {
            list = new ArrayList<InfoItem>(this.items);
            if (list == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/doxygen/comment/DoxygenCommentTagImpl", "getContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<InfoItem>)list;
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
                final DoxygenCommentTagImpl doxygenCommentTagImpl = this;
                final Class<? extends DoxygenCommentTagImpl> clazz = doxygenCommentTagImpl.getClass();
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
                final DoxygenCommentTagImpl doxygenCommentTagImpl = this;
                final Class<? extends DoxygenCommentTagImpl> clazz = doxygenCommentTagImpl.getClass();
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
        final DoxygenCommentTagImpl doxygenCommentTagImpl2 = (DoxygenCommentTagImpl)o;
        Label_0227: {
            Label_0220: {
                Label_0190: {
                    Label_0177: {
                        Label_0170: {
                            Label_0140: {
                                Label_0127: {
                                    Label_0120: {
                                        Label_0092: {
                                            Label_0079: {
                                                Label_0072: {
                                                    try {
                                                        if (this.name == null) {
                                                            break Label_0079;
                                                        }
                                                        final DoxygenCommentTagImpl doxygenCommentTagImpl3 = this;
                                                        final String s = doxygenCommentTagImpl3.name;
                                                        final DoxygenCommentTagImpl doxygenCommentTagImpl4 = doxygenCommentTagImpl2;
                                                        final String s2 = doxygenCommentTagImpl4.name;
                                                        final boolean b = s.equals(s2);
                                                        if (!b) {
                                                            break Label_0072;
                                                        }
                                                        break Label_0092;
                                                    }
                                                    catch (IllegalArgumentException ex4) {
                                                        throw a(ex4);
                                                    }
                                                    try {
                                                        final DoxygenCommentTagImpl doxygenCommentTagImpl3 = this;
                                                        final String s = doxygenCommentTagImpl3.name;
                                                        final DoxygenCommentTagImpl doxygenCommentTagImpl4 = doxygenCommentTagImpl2;
                                                        final String s2 = doxygenCommentTagImpl4.name;
                                                        final boolean b = s.equals(s2);
                                                        if (!b) {
                                                            return false;
                                                        }
                                                        break Label_0092;
                                                    }
                                                    catch (IllegalArgumentException ex5) {
                                                        throw a(ex5);
                                                    }
                                                }
                                                try {
                                                    if (doxygenCommentTagImpl2.name != null) {
                                                        return false;
                                                    }
                                                }
                                                catch (IllegalArgumentException ex6) {
                                                    throw a(ex6);
                                                }
                                            }
                                            try {
                                                if (this.options == null) {
                                                    break Label_0127;
                                                }
                                                final DoxygenCommentTagImpl doxygenCommentTagImpl5 = this;
                                                final String s3 = doxygenCommentTagImpl5.options;
                                                final DoxygenCommentTagImpl doxygenCommentTagImpl6 = doxygenCommentTagImpl2;
                                                final String s4 = doxygenCommentTagImpl6.options;
                                                final boolean b2 = s3.equals(s4);
                                                if (!b2) {
                                                    break Label_0120;
                                                }
                                                break Label_0140;
                                            }
                                            catch (IllegalArgumentException ex7) {
                                                throw a(ex7);
                                            }
                                        }
                                        try {
                                            final DoxygenCommentTagImpl doxygenCommentTagImpl5 = this;
                                            final String s3 = doxygenCommentTagImpl5.options;
                                            final DoxygenCommentTagImpl doxygenCommentTagImpl6 = doxygenCommentTagImpl2;
                                            final String s4 = doxygenCommentTagImpl6.options;
                                            final boolean b2 = s3.equals(s4);
                                            if (!b2) {
                                                return false;
                                            }
                                            break Label_0140;
                                        }
                                        catch (IllegalArgumentException ex8) {
                                            throw a(ex8);
                                        }
                                    }
                                    try {
                                        if (doxygenCommentTagImpl2.options != null) {
                                            return false;
                                        }
                                    }
                                    catch (IllegalArgumentException ex9) {
                                        throw a(ex9);
                                    }
                                }
                                try {
                                    if (this.params == null) {
                                        break Label_0177;
                                    }
                                    final DoxygenCommentTagImpl doxygenCommentTagImpl7 = this;
                                    final List<String> list = doxygenCommentTagImpl7.params;
                                    final DoxygenCommentTagImpl doxygenCommentTagImpl8 = doxygenCommentTagImpl2;
                                    final List<String> list2 = doxygenCommentTagImpl8.params;
                                    final boolean b3 = list.equals(list2);
                                    if (!b3) {
                                        break Label_0170;
                                    }
                                    break Label_0190;
                                }
                                catch (IllegalArgumentException ex10) {
                                    throw a(ex10);
                                }
                            }
                            try {
                                final DoxygenCommentTagImpl doxygenCommentTagImpl7 = this;
                                final List<String> list = doxygenCommentTagImpl7.params;
                                final DoxygenCommentTagImpl doxygenCommentTagImpl8 = doxygenCommentTagImpl2;
                                final List<String> list2 = doxygenCommentTagImpl8.params;
                                final boolean b3 = list.equals(list2);
                                if (!b3) {
                                    return false;
                                }
                                break Label_0190;
                            }
                            catch (IllegalArgumentException ex11) {
                                throw a(ex11);
                            }
                        }
                        try {
                            if (doxygenCommentTagImpl2.params != null) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex12) {
                            throw a(ex12);
                        }
                    }
                    try {
                        if (this.items == null) {
                            break Label_0227;
                        }
                        final DoxygenCommentTagImpl doxygenCommentTagImpl9 = this;
                        final List<InfoItem> list3 = doxygenCommentTagImpl9.items;
                        final DoxygenCommentTagImpl doxygenCommentTagImpl10 = doxygenCommentTagImpl2;
                        final List<InfoItem> list4 = doxygenCommentTagImpl10.items;
                        final boolean b4 = list3.equals(list4);
                        if (!b4) {
                            break Label_0220;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex13) {
                        throw a(ex13);
                    }
                }
                try {
                    final DoxygenCommentTagImpl doxygenCommentTagImpl9 = this;
                    final List<InfoItem> list3 = doxygenCommentTagImpl9.items;
                    final DoxygenCommentTagImpl doxygenCommentTagImpl10 = doxygenCommentTagImpl2;
                    final List<InfoItem> list4 = doxygenCommentTagImpl10.items;
                    final boolean b4 = list3.equals(list4);
                    if (!b4) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex14) {
                    throw a(ex14);
                }
            }
            try {
                if (doxygenCommentTagImpl2.items != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex15) {
                throw a(ex15);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.name != null) {
                    hashCode = this.name.hashCode();
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2 = 0;
        int hashCode2 = 0;
        Label_0049: {
            try {
                n2 = 31 * n;
                if (this.options != null) {
                    hashCode2 = this.options.hashCode();
                    break Label_0049;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            hashCode2 = 0;
        }
        final int n3 = n2 + hashCode2;
        int n4 = 0;
        int hashCode3 = 0;
        Label_0079: {
            try {
                n4 = 31 * n3;
                if (this.params != null) {
                    hashCode3 = this.params.hashCode();
                    break Label_0079;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            hashCode3 = 0;
        }
        final int n5 = n4 + hashCode3;
        int n6;
        try {
            n6 = 31 * n5;
            if (this.items != null) {
                final int hashCode4 = this.items.hashCode();
                return n6 + hashCode4;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final int hashCode4 = 0;
        return n6 + hashCode4;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
