// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.util.containers.hash.HashMap;
import java.util.ArrayList;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCmd;
import java.util.Iterator;
import java.util.List;
import com.jetbrains.cidr.lang.documentation.doxygen.api.TagItem;
import com.jetbrains.cidr.lang.documentation.doxygen.api.InfoItem;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenFacade;
import com.intellij.psi.PsiElement;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCommentGroup;
import java.util.Map;

public class DoxygenRender
{
    private static Map<String, String> TAG_TABLE;
    private static final Map<String, String> formattingTags;
    @Nullable
    private final DoxygenCommentGroup docComment;
    
    private static String b(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "replaceFormattingTags"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Pattern compile = Pattern.compile("(\\\\|@)(a|b|c|e|em|p)\\s+([^\\\\@&&[\\S]]+)");
        final Matcher matcher = compile.matcher(s);
        String replaceFirst = s;
        while (matcher.find()) {
            final String s2 = DoxygenRender.formattingTags.get(matcher.group(2));
            replaceFirst = s.replaceFirst(compile.pattern(), " <" + s2 + ">$3</" + s2 + ">");
        }
        try {
            if (s.equals(replaceFirst)) {
                return replaceFirst;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return b(replaceFirst);
    }
    
    @NotNull
    private static String a(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "extractName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0200: {
            String s10 = null;
            Label_0165: {
                Label_0114: {
                    String s4 = null;
                    Label_0079: {
                        try {
                            if (!DoxygenRender.TAG_TABLE.containsKey(s)) {
                                break Label_0114;
                            }
                            final Map<String, String> map = DoxygenRender.TAG_TABLE;
                            final String s2 = s;
                            final String s3 = map.get(s2);
                            s4 = s3;
                            if (s4 == null) {
                                break Label_0079;
                            }
                            return s4;
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                        try {
                            final Map<String, String> map = DoxygenRender.TAG_TABLE;
                            final String s2 = s;
                            final String s3 = map.get(s2);
                            s4 = s3;
                            if (s4 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "extractName"));
                            }
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                    }
                    return s4;
                    try {
                        if (s.isEmpty()) {
                            break Label_0200;
                        }
                        final StringBuilder sb = new StringBuilder();
                        final String s5 = s;
                        final int n = 0;
                        final int n2 = 1;
                        final String s6 = s5.substring(n, n2);
                        final Locale locale = Locale.ENGLISH;
                        final String s7 = s6.toUpperCase(locale);
                        final StringBuilder sb2 = sb.append(s7);
                        final String s8 = s;
                        final int n3 = 1;
                        final String s9 = s8.substring(n3);
                        final StringBuilder sb3 = sb2.append(s9);
                        s10 = sb3.toString();
                        if (s10 == null) {
                            break Label_0165;
                        }
                        return s10;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                }
                try {
                    final StringBuilder sb = new StringBuilder();
                    final String s5 = s;
                    final int n = 0;
                    final int n2 = 1;
                    final String s6 = s5.substring(n, n2);
                    final Locale locale = Locale.ENGLISH;
                    final String s7 = s6.toUpperCase(locale);
                    final StringBuilder sb2 = sb.append(s7);
                    final String s8 = s;
                    final int n3 = 1;
                    final String s9 = s8.substring(n3);
                    final StringBuilder sb3 = sb2.append(s9);
                    s10 = sb3.toString();
                    if (s10 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "extractName"));
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
            }
            return s10;
            try {
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "extractName"));
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return s;
    }
    
    public DoxygenRender(@NotNull final PsiElement psiElement) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "<init>"));
        }
        this.docComment = DoxygenFacade.getCommentGroup(psiElement);
    }
    
    public boolean hasDoxygenComments() {
        Label_0026: {
            try {
                if (this.docComment == null) {
                    return false;
                }
                final DoxygenRender doxygenRender = this;
                final DoxygenCommentGroup doxygenCommentGroup = doxygenRender.docComment;
                final boolean b = doxygenCommentGroup.isEmpty();
                if (!b) {
                    break Label_0026;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final DoxygenRender doxygenRender = this;
                final DoxygenCommentGroup doxygenCommentGroup = doxygenRender.docComment;
                final boolean b = doxygenCommentGroup.isEmpty();
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public void render(@NotNull final StringBuilder sb) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "render"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.docComment == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HtmlHelper.HtmlDoc htmlDoc = new HtmlHelper.HtmlDoc();
        final List<InfoItem> content = this.docComment.getContent();
        boolean b = false;
        final StringBuilder sb2 = new StringBuilder();
        final String options = this.docComment.getOptions();
        try {
            if (!options.isEmpty()) {
                sb2.append(HtmlHelper.code(options));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        if (!content.isEmpty()) {
            final InfoItem infoItem = content.get(0);
            if (!(infoItem instanceof TagItem)) {
                b = true;
                final String b2 = b(infoItem.getDescription());
                Label_0190: {
                    try {
                        if (sb2.length() <= 0) {
                            break Label_0190;
                        }
                        final String s = b2;
                        final boolean b3 = s.isEmpty();
                        if (!b3) {
                            break Label_0190;
                        }
                        break Label_0190;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final String s = b2;
                        final boolean b3 = s.isEmpty();
                        if (!b3) {
                            sb2.append(" ");
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                }
                sb2.append(b2);
            }
        }
        try {
            if (sb2.length() > 0) {
                htmlDoc.addText(HtmlHelper.paragraph(sb2.toString()));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        List<InfoItem> subList = null;
        Label_0266: {
            try {
                if (b) {
                    subList = content.subList(1, content.size());
                    break Label_0266;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            subList = content;
        }
        for (final InfoItem infoItem2 : a(subList)) {
            try {
                if (infoItem2 instanceof CompositeInfoItem) {
                    a(sb, htmlDoc, (CompositeInfoItem)infoItem2);
                    continue;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            a(infoItem2, htmlDoc);
        }
        sb.append(htmlDoc.toString());
    }
    
    private static void a(@NotNull final InfoItem infoItem, @NotNull final HtmlHelper.HtmlDoc htmlDoc) {
        try {
            if (infoItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "renderSimpleElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (htmlDoc == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "html", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "renderSimpleElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        htmlDoc.addText(HtmlHelper.paragraph(b(infoItem.getDescription())));
    }
    
    private static void a(@NotNull final StringBuilder sb, @NotNull final HtmlHelper.HtmlDoc htmlDoc, @NotNull final CompositeInfoItem compositeInfoItem) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "renderCompositeItem"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (htmlDoc == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "html", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "renderCompositeItem"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (compositeInfoItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "renderCompositeItem"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final String name = compositeInfoItem.getName();
        if (DoxygenCmd.BRIEF.toString().equals(name)) {
            final Iterator<TagItem> iterator = compositeInfoItem.getTags().iterator();
            while (iterator.hasNext()) {
                sb.append(HtmlHelper.newLine(b(iterator.next().getDescription())));
            }
        }
        else if (DoxygenCmd.SURROUND_TAGS.containsKey(name)) {
            final Iterator<TagItem> iterator2 = compositeInfoItem.getTags().iterator();
            while (iterator2.hasNext()) {
                htmlDoc.addText(HtmlHelper.pre(HtmlHelper.code(iterator2.next().getDescription())));
            }
        }
        else {
            final HtmlHelper.DL dl = new HtmlHelper.DL();
            dl.defineTerm(HtmlHelper.bold(a(name)));
            final HtmlHelper.Table table = new HtmlHelper.Table();
            final Iterator<TagItem> iterator3 = compositeInfoItem.getTags().iterator();
            while (iterator3.hasNext()) {
                a(iterator3.next(), table);
            }
            dl.detailedDescription(table);
            htmlDoc.addItem(dl);
        }
    }
    
    private static void a(@NotNull final TagItem tagItem, @NotNull final HtmlHelper.Table table) {
        try {
            if (tagItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "tag", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "renderTagParams"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (table == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "table", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "renderTagParams"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final HtmlHelper.TableRow withData = HtmlHelper.TableRow.withData(HtmlHelper.code(tagItem.getOptions()));
        final List<String> parameters = tagItem.getParameters();
        try {
            if (!parameters.isEmpty()) {
                withData.addData(HtmlHelper.bold(StringUtil.join((Collection)parameters, ", ")));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final StringBuilder sb = new StringBuilder();
        for (final InfoItem infoItem : tagItem.getContent()) {
            final String description = infoItem.getDescription();
            String s = null;
            Label_0246: {
                Label_0239: {
                    try {
                        if (!(infoItem instanceof TagItem) || !DoxygenCmd.SURROUND_TAGS.containsKey(((TagItem)infoItem).getName())) {
                            break Label_0239;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw a(ex4);
                    }
                    s = HtmlHelper.pre(HtmlHelper.code(description));
                    break Label_0246;
                }
                s = b(description);
            }
            sb.append(s);
        }
        withData.addData(sb.toString());
        table.addRow(withData);
    }
    
    @NotNull
    private static List<InfoItem> a(@NotNull final List<InfoItem> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "items", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "squash"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        List<InfoItem> a;
        try {
            a = a(list, 0, null);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "squash"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a;
    }
    
    @NotNull
    private static List<InfoItem> a(@NotNull final List<InfoItem> list, int n, @Nullable CompositeInfoItem compositeInfoItem) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "items", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "squash"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final ArrayList<TagItem> list2 = new ArrayList<TagItem>();
        if (n < list.size()) {
            final InfoItem infoItem = list.get(n);
            if (infoItem instanceof TagItem) {
                final TagItem tagItem = (TagItem)infoItem;
                final String name = tagItem.getName();
                Label_0195: {
                    Label_0164: {
                        Label_0141: {
                            Label_0134: {
                                try {
                                    if (!DoxygenCmd.PARAM.toString().equals(name)) {
                                        break Label_0141;
                                    }
                                    final TagItem tagItem2 = tagItem;
                                    final List<String> list3 = tagItem2.getParameters();
                                    final boolean b = list3.isEmpty();
                                    if (b) {
                                        break Label_0134;
                                    }
                                    break Label_0141;
                                }
                                catch (IllegalArgumentException ex2) {
                                    throw a(ex2);
                                }
                                try {
                                    final TagItem tagItem2 = tagItem;
                                    final List<String> list3 = tagItem2.getParameters();
                                    final boolean b = list3.isEmpty();
                                    if (b) {
                                        break Label_0195;
                                    }
                                }
                                catch (IllegalArgumentException ex3) {
                                    throw a(ex3);
                                }
                            }
                            try {
                                if (compositeInfoItem == null) {
                                    break Label_0164;
                                }
                                final String s = name;
                                final CompositeInfoItem compositeInfoItem2 = compositeInfoItem;
                                final String s2 = compositeInfoItem2.getName();
                                final boolean b2 = s.equals(s2);
                                if (b2) {
                                    break Label_0164;
                                }
                                break Label_0164;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                        }
                        try {
                            final String s = name;
                            final CompositeInfoItem compositeInfoItem2 = compositeInfoItem;
                            final String s2 = compositeInfoItem2.getName();
                            final boolean b2 = s.equals(s2);
                            if (b2) {
                                compositeInfoItem.addItem(tagItem);
                                break Label_0195;
                            }
                        }
                        catch (IllegalArgumentException ex5) {
                            throw a(ex5);
                        }
                    }
                    compositeInfoItem = new CompositeInfoItem(tagItem);
                    list2.add((TagItem)compositeInfoItem);
                }
            }
            else {
                compositeInfoItem = null;
                list2.add((TagItem)infoItem);
            }
            list2.addAll((Collection<?>)a(list, ++n, compositeInfoItem));
        }
        ArrayList<TagItem> list4;
        try {
            list4 = list2;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/DoxygenRender", "squash"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return (List<InfoItem>)list4;
    }
    
    static {
        (DoxygenRender.TAG_TABLE = (Map<String, String>)new HashMap()).put(DoxygenCmd.ATTENTION.toString(), "Attention");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.AUTHOR.toString(), "Author");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.AUTHORS.toString(), "Authors");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.BUG.toString(), "Bug");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.COPYRIGHT.toString(), "Copyright");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.DATE.toString(), "Date");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.DEPRECATED.toString(), "Deprecated");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.INVARIANT.toString(), "Invariant");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.PARAM.toString(), "Parameters");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.TPARAM.toString(), "Template Parameters");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.PRE.toString(), "Precondition");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.POST.toString(), "Postcondition");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.REMARK.toString(), "Remarks");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.REMARKS.toString(), "Remarks");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.SA.toString(), "See also");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.SEE.toString(), "See also");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.SINCE.toString(), "Since");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.TODO.toString(), "Todo");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.VERSION.toString(), "Version");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.WARNING.toString(), "Warning");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.RETURN.toString(), "Returns");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.RETURNS.toString(), "Returns");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.RESULT.toString(), "Returns");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.RETVAL.toString(), "Return values");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.THROW.toString(), "Exceptions");
        DoxygenRender.TAG_TABLE.put(DoxygenCmd.THROWS.toString(), "Exceptions");
        DoxygenRender.TAG_TABLE.put("exception", "Exceptions");
        (formattingTags = (Map)new HashMap()).put("a", "em");
        DoxygenRender.formattingTags.put("b", "b");
        DoxygenRender.formattingTags.put("c", "tt");
        DoxygenRender.formattingTags.put("e", "em");
        DoxygenRender.formattingTags.put("em", "em");
        DoxygenRender.formattingTags.put("p", "tt");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
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
}
