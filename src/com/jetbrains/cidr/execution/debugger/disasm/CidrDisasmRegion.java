// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.disasm;

import kotlin.ranges.ClosedRange$DefaultImpls;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.JvmField;
import kotlin.ranges.ClosedRange;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import kotlin.collections.MapsKt;
import java.util.Arrays;
import kotlin.TypeCastException;
import kotlin.TuplesKt;
import kotlin.Pair;
import java.util.ArrayList;
import java.util.TreeMap;
import kotlin._Assertions;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import kotlin.ranges.RangesKt;
import java.util.Iterator;
import com.intellij.openapi.util.TextRange;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.RangeMarker;
import com.jetbrains.cidr.execution.debugger.backend.LLInstruction;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import java.util.NavigableMap;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.debugger.memory.AddressRange;
import kotlin.Metadata;
import com.jetbrains.cidr.execution.debugger.memory.AddressSpace;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B'\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\u0002\u0010\tB\u001d\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\u000bB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\fJ\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0014J\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0014J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020\u0014J\u0006\u0010#\u001a\u00020\u001dJ\u0006\u0010$\u001a\u00020%J!\u0010$\u001a\u0002H&\"\f\b\u0000\u0010&*\u00060'j\u0002`(2\u0006\u0010)\u001a\u0002H&¢\u0006\u0002\u0010*J \u0010+\u001a\u00020\u001d\"\b\b\u0000\u0010,*\u00020\u00002\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0\u0007H\u0007J\b\u0010.\u001a\u00020%H\u0016R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0011R\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\b\u0018\u00010\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006/" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/disasm/CidrDisasmRegion;", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "range", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "isAllocated", "", "instructions", "", "Lcom/jetbrains/cidr/execution/debugger/backend/LLInstruction;", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;ZLjava/util/List;)V", "lines", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;Ljava/util/List;)V", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;Z)V", "document", "Lcom/intellij/openapi/editor/Document;", "getDocument", "()Lcom/intellij/openapi/editor/Document;", "()Z", "myInstructionMap", "Ljava/util/NavigableMap;", "Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "myMarkerInDocument", "Lcom/intellij/openapi/editor/RangeMarker;", "getMyMarkerInDocument", "()Lcom/intellij/openapi/editor/RangeMarker;", "myRangeMarker", "getRange", "()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "addToDocument", "", "offset", "getInstructionIndex", "address", "getLineNumberInDocument", "getOffsetInDocument", "removeFromDocument", "render", "", "A", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "buffer", "(Ljava/lang/Appendable;)Ljava/lang/Appendable;", "replaceWith", "T", "newRegions", "toString", "cidr-debugger" })
public final class CidrDisasmRegion implements Region
{
    @NotNull
    private final AddressRange range;
    private final boolean isAllocated;
    private final NavigableMap<Address, LLInstruction> myInstructionMap;
    private RangeMarker myRangeMarker;
    
    @NotNull
    @Override
    public AddressRange getRange() {
        return this.range;
    }
    
    @Override
    public boolean isAllocated() {
        return this.isAllocated;
    }
    
    private final RangeMarker a() {
        try {
            final RangeMarker myRangeMarker = this.myRangeMarker;
            if (myRangeMarker != null) {
                return myRangeMarker;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        throw new IllegalStateException("Region has not been added to a document");
    }
    
    @Nullable
    public final Document getDocument() {
        try {
            final RangeMarker myRangeMarker = this.myRangeMarker;
            if (myRangeMarker != null) {
                return myRangeMarker.getDocument();
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    @NotNull
    public final <A extends Appendable> A render(@NotNull final A a) {
        try {
            Intrinsics.checkParameterIsNotNull((Object)a, "buffer");
            a.append("\n! [" + this.getRange() + "]:\n");
            if (this.myInstructionMap != null) {
                CollectionsKt.joinTo$default((Iterable)this.myInstructionMap.values(), (Appendable)a, (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)CidrDisasmRegion$render.CidrDisasmRegion$render$1.INSTANCE, 60, (Object)null);
                return a;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        String s = null;
        Label_0123: {
            try {
                a.append("!\t");
                if (this.isAllocated()) {
                    s = "disassembling...";
                    break Label_0123;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            s = "not disassembled yet";
        }
        a.append(s);
        a.append("\n");
        return a;
    }
    
    @NotNull
    public final String render() {
        final String string = this.render(new StringBuilder()).toString();
        Intrinsics.checkExpressionValueIsNotNull((Object)string, "render(StringBuilder()).toString()");
        return string;
    }
    
    public final int addToDocument(@NotNull final Document document, final int n) {
        Intrinsics.checkParameterIsNotNull((Object)document, "document");
        final RangeMarker myRangeMarker = this.myRangeMarker;
        if (myRangeMarker != null) {
            throw new IllegalStateException("Attempting to add an already added region at " + myRangeMarker);
        }
        final String render = this.render();
        document.insertString(n, (CharSequence)render);
        this.myRangeMarker = document.createRangeMarker(n, n + render.length());
        return render.length();
    }
    
    public final int removeFromDocument() {
        RangeMarker myRangeMarker = null;
        Label_0029: {
            try {
                myRangeMarker = this.myRangeMarker;
                if (myRangeMarker != null) {
                    break Label_0029;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            throw new IllegalStateException("Attempting to remove a not yet added region");
        }
        final RangeMarker rangeMarker = myRangeMarker;
        final int startOffset = rangeMarker.getStartOffset();
        rangeMarker.getDocument().deleteString(startOffset, rangeMarker.getEndOffset());
        rangeMarker.dispose();
        this.myRangeMarker = null;
        return startOffset;
    }
    
    public final <T extends CidrDisasmRegion> int replaceWith(@NotNull final List<? extends T> list) {
        RangeMarker myRangeMarker = null;
        Label_0035: {
            try {
                Intrinsics.checkParameterIsNotNull((Object)list, "newRegions");
                myRangeMarker = this.myRangeMarker;
                if (myRangeMarker != null) {
                    break Label_0035;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            throw new IllegalStateException("Attempting to replace a not yet added region");
        }
        final RangeMarker rangeMarker = myRangeMarker;
        final Document document = rangeMarker.getDocument();
        final int startOffset = rangeMarker.getStartOffset();
        final StringBuilder sb = new StringBuilder();
        final TextRange[] array = new TextRange[list.size()];
        final List<? extends T> list2 = list;
        int n = 0;
        for (final CidrDisasmRegion next : list2) {
            final int n2 = n++;
            final CidrDisasmRegion cidrDisasmRegion = next;
            final int n3 = n2;
            if (cidrDisasmRegion.myRangeMarker != null) {
                throw new IllegalStateException("Attempting to add an already added region at " + cidrDisasmRegion);
            }
            array[n3] = new TextRange(startOffset + sb.length(), startOffset + cidrDisasmRegion.render(sb).length());
        }
        document.replaceString(startOffset, rangeMarker.getEndOffset(), (CharSequence)sb);
        final List<? extends T> list3 = list;
        int n4 = 0;
        for (final CidrDisasmRegion next2 : list3) {
            final int n5 = n4++;
            final CidrDisasmRegion cidrDisasmRegion2 = next2;
            final int n6 = n5;
            CidrDisasmRegion cidrDisasmRegion3;
            Document document2;
            TextRange textRange;
            try {
                cidrDisasmRegion3 = cidrDisasmRegion2;
                document2 = document;
                textRange = array[n6];
                if (textRange == null) {
                    Intrinsics.throwNpe();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            cidrDisasmRegion3.myRangeMarker = document2.createRangeMarker(textRange);
        }
        rangeMarker.dispose();
        this.myRangeMarker = null;
        return startOffset + sb.length();
    }
    
    public final int getInstructionIndex(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        final int n = 1;
        final CidrDisasmRegion cidrDisasmRegion = this;
        Label_0116: {
            NavigableMap<Address, LLInstruction> myInstructionMap2 = null;
            Label_0086: {
                Label_0061: {
                    Label_0053: {
                        try {
                            if (!(cidrDisasmRegion.getRange().contains((Comparable)address) ^ true)) {
                                break Label_0086;
                            }
                            final Address address2 = address;
                            final CidrDisasmRegion cidrDisasmRegion2 = cidrDisasmRegion;
                            final AddressRange addressRange = cidrDisasmRegion2.getRange();
                            final Address address3 = addressRange.getStart();
                            final int n2 = address2.compareTo(address3);
                            if (n2 < 0) {
                                break Label_0053;
                            }
                            break Label_0061;
                        }
                        catch (IllegalStateException ex) {
                            throw a(ex);
                        }
                        try {
                            final Address address2 = address;
                            final CidrDisasmRegion cidrDisasmRegion2 = cidrDisasmRegion;
                            final AddressRange addressRange = cidrDisasmRegion2.getRange();
                            final Address address3 = addressRange.getStart();
                            final int n2 = address2.compareTo(address3);
                            if (n2 < 0) {
                                final int n3 = -1;
                                return n + n3;
                            }
                        }
                        catch (IllegalStateException ex2) {
                            throw a(ex2);
                        }
                    }
                    try {
                        final NavigableMap<Address, LLInstruction> myInstructionMap = cidrDisasmRegion.myInstructionMap;
                        if (myInstructionMap != null) {
                            final int n3 = myInstructionMap.size();
                            return n + n3;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                final int n3 = 1;
                return n + n3;
                try {
                    myInstructionMap2 = cidrDisasmRegion.myInstructionMap;
                    if (myInstructionMap2 == null) {
                        break Label_0116;
                    }
                    final Address address4 = Address.MIN_VALUE;
                    final boolean b = true;
                    final Address address5 = address;
                    final boolean b2 = true;
                    final NavigableMap<Address, LLInstruction> navigableMap2;
                    final NavigableMap<Address, LLInstruction> navigableMap = navigableMap2 = myInstructionMap2.subMap(address4, b, address5, b2);
                    if (navigableMap2 != null) {
                        break Label_0116;
                    }
                    break Label_0116;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final Address address4 = Address.MIN_VALUE;
                final boolean b = true;
                final Address address5 = address;
                final boolean b2 = true;
                final NavigableMap<Address, LLInstruction> navigableMap2;
                final NavigableMap<Address, LLInstruction> navigableMap = navigableMap2 = myInstructionMap2.subMap(address4, b, address5, b2);
                if (navigableMap2 != null) {
                    final int n3 = navigableMap.size();
                    return n + n3;
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
        }
        final int n3 = 1;
        return n + n3;
    }
    
    public final int getLineNumberInDocument(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        final RangeMarker a = this.a();
        final int lineNumber = a.getDocument().getLineNumber(a.getStartOffset());
        final int instructionIndex = this.getInstructionIndex(address);
        final int coerceIn;
        final int n = coerceIn = RangesKt.coerceIn(lineNumber + instructionIndex, 0, a.getDocument().getLineCount() - 1);
        try {
            if (lineNumber + instructionIndex != coerceIn) {
                CidrDebuggerLog.LOG.warn("Computed line number " + lineNumber + '+' + instructionIndex + " is outside the document boundary " + coerceIn);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return n;
    }
    
    public final int getOffsetInDocument(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        final RangeMarker a = this.a();
        return RangesKt.coerceIn(a.getDocument().getLineStartOffset(this.getLineNumberInDocument(address)), 0, a.getDocument().getTextLength() - 1);
    }
    
    @NotNull
    @Override
    public String toString() {
        return "CidrDisasmRegion(range=" + this.getRange() + ", isAllocated=" + this.isAllocated() + ", myRangeMarker=" + this.myRangeMarker + ')';
    }
    
    private CidrDisasmRegion(final AddressRange range, final boolean isAllocated, final List<LLInstruction> list) {
        final boolean b = !range.isEmpty();
        boolean b2 = false;
        Label_0077: {
            Label_0068: {
                Label_0053: {
                    try {
                        if (!_Assertions.ENABLED || b) {
                            break Label_0053;
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    throw new AssertionError((Object)"Range must not be empty");
                    try {
                        if (isAllocated) {
                            break Label_0068;
                        }
                        final List<LLInstruction> list2 = list;
                        if (list2 == null) {
                            break Label_0068;
                        }
                        break Label_0068;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                try {
                    final List<LLInstruction> list2 = list;
                    if (list2 == null) {
                        b2 = true;
                        break Label_0077;
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        Label_0114: {
            try {
                if (!_Assertions.ENABLED || b3) {
                    break Label_0114;
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            throw new AssertionError((Object)"An unallocated region must not have content");
        }
        this.range = range;
        this.isAllocated = isAllocated;
        CidrDisasmRegion cidrDisasmRegion = this;
        NavigableMap<Address, LLInstruction> navigableMap;
        if (list != null) {
            final TreeMap treeMap = new(java.util.TreeMap.class);
            final List<LLInstruction> list3 = list;
            final TreeMap treeMap2 = treeMap;
            final TreeMap treeMap3 = treeMap;
            final Iterable<Object> iterable = (Iterable<Object>)list3;
            final ArrayList<Pair> list4 = new ArrayList<Pair>(CollectionsKt.collectionSizeOrDefault((Iterable)list3, 10));
            for (final LLInstruction next : iterable) {
                final ArrayList<Pair> list5 = list4;
                final LLInstruction llInstruction = next;
                list5.add(TuplesKt.to((Object)llInstruction.getAddress(), (Object)llInstruction));
            }
            final ArrayList<Pair> list6 = list4;
            final TreeMap treeMap4 = treeMap3;
            final TreeMap treeMap5 = treeMap2;
            final ArrayList<Pair> list7 = list6;
            final TreeMap treeMap6 = treeMap5;
            final TreeMap treeMap7 = treeMap4;
            final ArrayList<Pair> list8 = list7;
            final Pair[] array = list8.toArray(new Pair[list8.size()]);
            if (array == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            final Pair[] array2 = array;
            final TreeMap treeMap8 = treeMap7;
            final TreeMap treeMap9 = treeMap6;
            final Pair[] array3 = array2;
            new TreeMap(MapsKt.mapOf((Pair[])Arrays.copyOf(array3, array3.length)));
            final TreeMap treeMap10 = treeMap8;
            cidrDisasmRegion = this;
            navigableMap = (NavigableMap<Address, LLInstruction>)treeMap10;
        }
        else {
            navigableMap = null;
        }
        cidrDisasmRegion.myInstructionMap = navigableMap;
    }
    
    public CidrDisasmRegion(@NotNull final AddressRange addressRange, @NotNull final List<LLInstruction> list) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        Intrinsics.checkParameterIsNotNull((Object)list, "lines");
        this(addressRange, true, list);
    }
    
    public CidrDisasmRegion(@NotNull final AddressRange addressRange, final boolean b) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        this(addressRange, b, null);
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
