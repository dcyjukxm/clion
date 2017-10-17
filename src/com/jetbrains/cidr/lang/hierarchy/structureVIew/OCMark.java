// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.hierarchy.structureVIew;

import kotlin.text.StringsKt;
import java.util.regex.Matcher;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.intellij.psi.PsiComment;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import com.intellij.psi.PsiElement;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u00102\u00020\u0001:\u0002\u0010\u0011B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000f\u001a\u00020\u0005H\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\nR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012" }, d2 = { "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark;", "", "isSeparatorBefore", "", "text", "", "isSeparatorAfter", "kind", "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark$Kind;", "(ZLjava/lang/String;ZLcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark$Kind;)V", "()Z", "getKind", "()Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark$Kind;", "getText", "()Ljava/lang/String;", "toString", "Companion", "Kind", "cidr-lang" })
public final class OCMark
{
    private final boolean isSeparatorBefore;
    @Nullable
    private final String text;
    private final boolean isSeparatorAfter;
    @NotNull
    private final Kind kind;
    @NotNull
    private static final OCMark SEPARATOR;
    @NotNull
    private static final OCMark TODO_SEPARATOR;
    @NotNull
    private static final OCMark FIXME_SEPARATOR;
    @NotNull
    private static final Kind DEFAULT_MARK_KIND;
    private static final Pattern COMMENT_PATTERN;
    private static final Pattern MARK_PATTERN;
    public static final Companion Companion;
    
    @NotNull
    @Override
    public String toString() {
        String string;
        if (this.isSeparatorBefore) {
            string = "-";
        }
        else {
            final StringBuilder append = new StringBuilder().append("");
            String text;
            if ((text = this.text) == null) {
                text = "";
            }
            string = append.append((Object)text).append(this.isSeparatorAfter ? "-" : "").toString();
        }
        return string;
    }
    
    public final boolean isSeparatorBefore() {
        return this.isSeparatorBefore;
    }
    
    @Nullable
    public final String getText() {
        return this.text;
    }
    
    public final boolean isSeparatorAfter() {
        return this.isSeparatorAfter;
    }
    
    @NotNull
    public final Kind getKind() {
        return this.kind;
    }
    
    public OCMark(final boolean isSeparatorBefore, @Nullable final String text, final boolean isSeparatorAfter, @NotNull final Kind kind) {
        Intrinsics.checkParameterIsNotNull((Object)kind, "kind");
        this.isSeparatorBefore = isSeparatorBefore;
        this.text = text;
        this.isSeparatorAfter = isSeparatorAfter;
        this.kind = kind;
    }
    
    static {
        Companion = new Companion(null);
        SEPARATOR = new OCMark(true, null, false, Kind.MARK);
        TODO_SEPARATOR = new OCMark(true, "TODO", false, Kind.TODO);
        FIXME_SEPARATOR = new OCMark(true, "FIXME", false, Kind.FIXME);
        DEFAULT_MARK_KIND = Kind.MARK;
        final Pattern compile = Pattern.compile(".*(MARK|TODO|FIXME):([^*]+?)\\s*(\\*|$)", 8);
        Intrinsics.checkExpressionValueIsNotNull((Object)compile, "Pattern.compile(\".*(MARK\u2026*|$)\", Pattern.MULTILINE)");
        COMMENT_PATTERN = compile;
        final Pattern compile2 = Pattern.compile("^\\s*(MARK|TODO|FIXME):(.*?)\\s*$", 8);
        Intrinsics.checkExpressionValueIsNotNull((Object)compile2, "Pattern.compile(\"^\\\\s*(M\u2026\\s*$\", Pattern.MULTILINE)");
        MARK_PATTERN = compile2;
    }
    
    @NotNull
    public static final /* synthetic */ OCMark access$getSEPARATOR$cp() {
        return OCMark.SEPARATOR;
    }
    
    @NotNull
    public static final /* synthetic */ OCMark access$getTODO_SEPARATOR$cp() {
        return OCMark.TODO_SEPARATOR;
    }
    
    @NotNull
    public static final /* synthetic */ OCMark access$getFIXME_SEPARATOR$cp() {
        return OCMark.FIXME_SEPARATOR;
    }
    
    @NotNull
    public static final /* synthetic */ Kind access$getDEFAULT_MARK_KIND$cp() {
        return OCMark.DEFAULT_MARK_KIND;
    }
    
    @NotNull
    public static final /* synthetic */ Pattern access$getCOMMENT_PATTERN$cp() {
        return OCMark.COMMENT_PATTERN;
    }
    
    @NotNull
    public static final /* synthetic */ Pattern access$getMARK_PATTERN$cp() {
        return OCMark.MARK_PATTERN;
    }
    
    @NotNull
    public static final OCMark getSEPARATOR() {
        return OCMark.Companion.getSEPARATOR();
    }
    
    @NotNull
    public static final OCMark getTODO_SEPARATOR() {
        return OCMark.Companion.getTODO_SEPARATOR();
    }
    
    @NotNull
    public static final OCMark getFIXME_SEPARATOR() {
        return OCMark.Companion.getFIXME_SEPARATOR();
    }
    
    @NotNull
    public static final Kind getDEFAULT_MARK_KIND() {
        return OCMark.Companion.getDEFAULT_MARK_KIND();
    }
    
    @JvmStatic
    public static final boolean isMarkElement(@NotNull final PsiElement psiElement) {
        Intrinsics.checkParameterIsNotNull((Object)psiElement, "element");
        return OCMark.Companion.isMarkElement(psiElement);
    }
    
    @JvmStatic
    @Nullable
    public static final OCMark createFromElement(@NotNull final PsiElement psiElement) {
        Intrinsics.checkParameterIsNotNull((Object)psiElement, "element");
        return OCMark.Companion.createFromElement(psiElement);
    }
    
    @JvmStatic
    @Nullable
    public static final OCMark createFromPragmaMarkText(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "pragmaText");
        return OCMark.Companion.createFromPragmaMarkText(s);
    }
    
    @JvmStatic
    @Nullable
    public static final OCMark createFromCommentText(@NotNull final String s) {
        Intrinsics.checkParameterIsNotNull((Object)s, "commentText");
        return OCMark.Companion.createFromCommentText(s);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006" }, d2 = { "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark$Kind;", "", "(Ljava/lang/String;I)V", "MARK", "TODO", "FIXME", "cidr-lang" })
    public enum Kind
    {
        MARK, 
        TODO, 
        FIXME;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\r2\u0006\u0010 \u001a\u00020\u001bH\u0007J\u0018\u0010!\u001a\u00020\r2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\u001bH\u0002J\u001a\u0010$\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u0004H\u0002J\u0010\u0010&\u001a\u00020'2\u0006\u0010\u001d\u001a\u00020\u001eH\u0007J\u0010\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\bH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001c\u0010\u0007\u001a\u00020\b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\u0002\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0002\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u0004X\u0082\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u001c\u0010\u0013\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0015\u0010\u0010R\u001c\u0010\u0016\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0002\u001a\u0004\b\u0018\u0010\u0010¨\u0006*" }, d2 = { "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark$Companion;", "", "()V", "COMMENT_PATTERN", "Ljava/util/regex/Pattern;", "getCOMMENT_PATTERN", "()Ljava/util/regex/Pattern;", "DEFAULT_MARK_KIND", "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark$Kind;", "DEFAULT_MARK_KIND$annotations", "getDEFAULT_MARK_KIND", "()Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark$Kind;", "FIXME_SEPARATOR", "Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark;", "FIXME_SEPARATOR$annotations", "getFIXME_SEPARATOR", "()Lcom/jetbrains/cidr/lang/hierarchy/structureVIew/OCMark;", "MARK_PATTERN", "getMARK_PATTERN", "SEPARATOR", "SEPARATOR$annotations", "getSEPARATOR", "TODO_SEPARATOR", "TODO_SEPARATOR$annotations", "getTODO_SEPARATOR", "createFromCommentText", "commentText", "", "createFromElement", "element", "Lcom/intellij/psi/PsiElement;", "createFromPragmaMarkText", "pragmaText", "createFromText", "kind", "text", "extractTextUsingPattern", "pattern", "isMarkElement", "", "separator", "markKind", "cidr-lang" })
    public static final class Companion
    {
        @NotNull
        public final OCMark getSEPARATOR() {
            return OCMark.access$getSEPARATOR$cp();
        }
        
        @NotNull
        public final OCMark getTODO_SEPARATOR() {
            return OCMark.access$getTODO_SEPARATOR$cp();
        }
        
        @NotNull
        public final OCMark getFIXME_SEPARATOR() {
            return OCMark.access$getFIXME_SEPARATOR$cp();
        }
        
        @NotNull
        public final Kind getDEFAULT_MARK_KIND() {
            return OCMark.access$getDEFAULT_MARK_KIND$cp();
        }
        
        private final Pattern b() {
            return OCMark.access$getCOMMENT_PATTERN$cp();
        }
        
        private final Pattern a() {
            return OCMark.access$getMARK_PATTERN$cp();
        }
        
        @JvmStatic
        public final boolean isMarkElement(@NotNull final PsiElement psiElement) {
            try {
                Intrinsics.checkParameterIsNotNull((Object)psiElement, "element");
                if (this.createFromElement(psiElement) != null) {
                    return true;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            return false;
        }
        
        @JvmStatic
        @Nullable
        public final OCMark createFromElement(@NotNull final PsiElement psiElement) {
            try {
                Intrinsics.checkParameterIsNotNull((Object)psiElement, "element");
                if (psiElement instanceof PsiComment) {
                    final Companion companion = this;
                    final String text = ((PsiComment)psiElement).getText();
                    Intrinsics.checkExpressionValueIsNotNull((Object)text, "element.text");
                    return companion.createFromCommentText(text);
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            if (psiElement instanceof OCPragma) {
                final ASTNode childByType = ((OCPragma)psiElement).getNode().findChildByType((IElementType)OCTokenTypes.PRAGMA_MARK_LITERAL);
                Companion companion3 = null;
                Label_0109: {
                    try {
                        if (childByType == null) {
                            return null;
                        }
                        final Companion companion2 = this;
                        companion3 = companion2;
                        final PsiElement psiElement2 = psiElement;
                        final OCPragma ocPragma = (OCPragma)psiElement2;
                        final ASTNode astNode = childByType;
                        final PsiElement psiElement3 = astNode.getPsi();
                        final boolean b = true;
                        final Pair<String, TextRange> pair = ocPragma.getContent(psiElement3, b);
                        final Object o = pair.first;
                        final String s2;
                        final String s = s2 = (String)o;
                        if (s2 != null) {
                            break Label_0109;
                        }
                        break Label_0109;
                    }
                    catch (TypeCastException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final Companion companion2 = this;
                        companion3 = companion2;
                        final PsiElement psiElement2 = psiElement;
                        final OCPragma ocPragma = (OCPragma)psiElement2;
                        final ASTNode astNode = childByType;
                        final PsiElement psiElement3 = astNode.getPsi();
                        final boolean b = true;
                        final Pair<String, TextRange> pair = ocPragma.getContent(psiElement3, b);
                        final Object o = pair.first;
                        final String s2;
                        final String s = s2 = (String)o;
                        if (s2 != null) {
                            return companion3.createFromPragmaMarkText(s);
                        }
                    }
                    catch (TypeCastException ex3) {
                        throw b(ex3);
                    }
                }
                final String s = "";
                return companion3.createFromPragmaMarkText(s);
            }
            return null;
        }
        
        @JvmStatic
        @Nullable
        public final OCMark createFromPragmaMarkText(@NotNull final String s) {
            try {
                Intrinsics.checkParameterIsNotNull((Object)s, "pragmaText");
                final OCMark ocMark;
                if ((ocMark = this.a(s, this.a())) != null) {
                    return ocMark;
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            return this.a(Kind.MARK, s);
        }
        
        @JvmStatic
        @Nullable
        public final OCMark createFromCommentText(@NotNull final String s) {
            Intrinsics.checkParameterIsNotNull((Object)s, "commentText");
            return this.a(s, this.b());
        }
        
        private final OCMark a(final String s, final Pattern pattern) {
            final Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                final String group = matcher.group(1);
                Intrinsics.checkExpressionValueIsNotNull((Object)group, "matcher.group(1)");
                final Kind value = Kind.valueOf(group);
                final String group2 = matcher.group(2);
                final Companion companion = this;
                final Kind kind = value;
                final String s2 = group2;
                Intrinsics.checkExpressionValueIsNotNull((Object)s2, "origText");
                return companion.a(kind, s2);
            }
            return null;
        }
        
        private final OCMark a(final Kind kind, final String s) {
            try {
                if (StringsKt.isBlank((CharSequence)s)) {
                    return this.a(kind);
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            try {
                if (s == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
            }
            catch (TypeCastException ex2) {
                throw b(ex2);
            }
            final String string = StringsKt.trim((CharSequence)s).toString();
            try {
                if (Intrinsics.areEqual((Object)"-", (Object)string)) {
                    return this.a(kind);
                }
            }
            catch (TypeCastException ex3) {
                throw b(ex3);
            }
            final String removeSuffix = StringsKt.removeSuffix(StringsKt.removePrefix(string, (CharSequence)"- "), (CharSequence)" -");
            String s2;
            try {
                s2 = removeSuffix;
                if (s2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
                }
            }
            catch (TypeCastException ex4) {
                throw b(ex4);
            }
            String s3 = StringsKt.trim((CharSequence)s2).toString();
            if (Intrinsics.areEqual((Object)kind, (Object)this.getDEFAULT_MARK_KIND()) ^ true) {
                s3 = kind.toString() + ": " + s3;
            }
            return new OCMark(StringsKt.startsWith$default(string, "- ", false, 2, (Object)null), s3, StringsKt.endsWith$default(string, " -", false, 2, (Object)null), kind);
        }
        
        private final OCMark a(final Kind kind) {
            try {
                switch (OCMark$Companion$WhenMappings.$EnumSwitchMapping$0[kind.ordinal()]) {
                    case 1: {
                        return this.getTODO_SEPARATOR();
                    }
                    case 2: {
                        break;
                    }
                    default: {
                        return this.getSEPARATOR();
                    }
                }
            }
            catch (TypeCastException ex) {
                throw b(ex);
            }
            return this.getFIXME_SEPARATOR();
            ocMark = this.getSEPARATOR();
            return ocMark;
        }
        
        private static TypeCastException b(final TypeCastException ex) {
            return ex;
        }
    }
}
