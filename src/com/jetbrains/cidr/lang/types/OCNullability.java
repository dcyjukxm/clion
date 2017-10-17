// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types;

import kotlin.jvm.JvmStatic;
import java.util.HashSet;
import com.intellij.util.containers.ContainerUtilRt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.jetbrains.cidr.lang.parser.OCElementType;
import kotlin.jvm.internal.Intrinsics;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import java.util.Set;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u001b\b\u0002\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e" }, d2 = { "Lcom/jetbrains/cidr/lang/types/OCNullability;", "", "tokens", "", "Lcom/intellij/psi/tree/IElementType;", "(Ljava/lang/String;I[Lcom/intellij/psi/tree/IElementType;)V", "matchingTypes", "", "getMatchingTypes", "()Ljava/util/Set;", "NULLABLE", "NONNULL", "UNSPECIFIED", "Companion", "cidr-lang" })
public enum OCNullability
{
    NULLABLE(array), 
    NONNULL(array2), 
    UNSPECIFIED(array3);
    
    @NotNull
    private final Set<IElementType> matchingTypes;
    public static final Companion Companion;
    
    static {
        final OCNullability[] $values = new OCNullability[3];
        final int n = 0;
        final String s = "NULLABLE";
        final boolean b = false;
        final IElementType[] array = new IElementType[2];
        final int n2 = 0;
        final OCElementType nullable_KEYWORD = OCTokenTypes.NULLABLE_KEYWORD;
        Intrinsics.checkExpressionValueIsNotNull((Object)nullable_KEYWORD, "OCTokenTypes.NULLABLE_KEYWORD");
        array[n2] = nullable_KEYWORD;
        final int n3 = 1;
        final OCElementType nullable_KEYWORD2 = OCTokenTypes._NULLABLE_KEYWORD;
        Intrinsics.checkExpressionValueIsNotNull((Object)nullable_KEYWORD2, "OCTokenTypes._NULLABLE_KEYWORD");
        array[n3] = nullable_KEYWORD2;
        final int n4 = 1;
        final String s2 = "NONNULL";
        final boolean b2 = true;
        final IElementType[] array2 = new IElementType[2];
        final int n5 = 0;
        final OCElementType nonnull_KEYWORD = OCTokenTypes.NONNULL_KEYWORD;
        Intrinsics.checkExpressionValueIsNotNull((Object)nonnull_KEYWORD, "OCTokenTypes.NONNULL_KEYWORD");
        array2[n5] = nonnull_KEYWORD;
        final int n6 = 1;
        final OCElementType nonnull_KEYWORD2 = OCTokenTypes._NONNULL_KEYWORD;
        Intrinsics.checkExpressionValueIsNotNull((Object)nonnull_KEYWORD2, "OCTokenTypes._NONNULL_KEYWORD");
        array2[n6] = nonnull_KEYWORD2;
        final int n7 = 2;
        final String s3 = "UNSPECIFIED";
        final int n8 = 2;
        final IElementType[] array3 = new IElementType[2];
        final int n9 = 0;
        final OCElementType null_UNSPECIFIED_KEYWORD = OCTokenTypes.NULL_UNSPECIFIED_KEYWORD;
        Intrinsics.checkExpressionValueIsNotNull((Object)null_UNSPECIFIED_KEYWORD, "OCTokenTypes.NULL_UNSPECIFIED_KEYWORD");
        array3[n9] = null_UNSPECIFIED_KEYWORD;
        final int n10 = 1;
        final OCElementType null_UNSPECIFIED_KEYWORD2 = OCTokenTypes._NULL_UNSPECIFIED_KEYWORD;
        Intrinsics.checkExpressionValueIsNotNull((Object)null_UNSPECIFIED_KEYWORD2, "OCTokenTypes._NULL_UNSPECIFIED_KEYWORD");
        array3[n10] = null_UNSPECIFIED_KEYWORD2;
        $VALUES = $values;
        Companion = new Companion(null);
    }
    
    @NotNull
    public final Set<IElementType> getMatchingTypes() {
        return this.matchingTypes;
    }
    
    protected OCNullability(final IElementType[] array) {
        Intrinsics.checkParameterIsNotNull((Object)array, "tokens");
        super(s, n);
        final HashSet hashSet = ContainerUtilRt.newHashSet(ArrayIteratorKt.iterator((Object[])array));
        Intrinsics.checkExpressionValueIsNotNull((Object)hashSet, "ContainerUtilRt.newHashSet(tokens.iterator())");
        this.matchingTypes = (HashSet<IElementType>)hashSet;
    }
    
    @JvmStatic
    @NotNull
    public static final OCNullability parseFrom(@NotNull final IElementType elementType) {
        Intrinsics.checkParameterIsNotNull((Object)elementType, "tokenType");
        return OCNullability.Companion.parseFrom(elementType);
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u0007" }, d2 = { "Lcom/jetbrains/cidr/lang/types/OCNullability$Companion;", "", "()V", "parseFrom", "Lcom/jetbrains/cidr/lang/types/OCNullability;", "tokenType", "Lcom/intellij/psi/tree/IElementType;", "cidr-lang" })
    public static final class Companion
    {
        @JvmStatic
        @NotNull
        public final OCNullability parseFrom(@NotNull final IElementType elementType) {
            Intrinsics.checkParameterIsNotNull((Object)elementType, "tokenType");
            final OCNullability[] array = OCNullability.values();
            for (int i = 0; i < array.length; ++i) {
                final OCNullability ocNullability = array[i];
                try {
                    if (ocNullability.getMatchingTypes().contains(elementType)) {
                        return ocNullability;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            throw new IllegalArgumentException("Invalid token type provided");
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
