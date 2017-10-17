// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.memory;

import kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.TreeMap;
import kotlin._Assertions;
import java.util.Iterator;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;
import java.util.List;
import java.util.Collections;
import kotlin.ranges.RangesKt;
import java.util.Collection;
import java.util.EventListener;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import java.util.function.BiFunction;
import java.util.NavigableMap;
import com.intellij.util.EventDispatcher;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0000\n\u0002\u0010 \n\u0002\b\u0011\u0018\u0000 +*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003+,-B%\u0012\u001e\u0010\u0004\u001a\u001a\u0012\u0006\b\u0000\u0012\u00020\u0006\u0012\u0006\b\u0000\u0012\u00020\u0007\u0012\u0006\b\u0001\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\bJ\u0014\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bJ\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0006H\u0002J\u0016\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u0010H\u0086\u0002¢\u0006\u0002\u0010\u001aJ\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b2\u0006\u0010\u0017\u001a\u00020\u0006H\u0086\u0002J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d2\u0006\u0010\u0019\u001a\u00020\u0010J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u001d2\u0006\u0010\u0017\u001a\u00020\u0006J\u0015\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010 J\u001d\u0010\u001e\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\"J\u001f\u0010#\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\"J\u0015\u0010$\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010 J\u001d\u0010$\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\"J\u0013\u0010%\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00028\u0000¢\u0006\u0002\u0010&J\u001d\u0010'\u001a\u00028\u00002\u0006\u0010\u001f\u001a\u00028\u00002\u0006\u0010(\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010)J\u000e\u0010*\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0006R\"\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\n8\u0002X\u0083\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00028\u00000\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R)\u0010\u0004\u001a\u001a\u0012\u0006\b\u0000\u0012\u00020\u0006\u0012\u0006\b\u0000\u0012\u00020\u0007\u0012\u0006\b\u0001\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006." }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace;", "R", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "", "regionFactory", "Ljava/util/function/BiFunction;", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "", "(Ljava/util/function/BiFunction;)V", "myRegionEventDispatcher", "Lcom/intellij/util/EventDispatcher;", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Listener;", "myRegionEventDispatcher$annotations", "()V", "myRegionMap", "Ljava/util/NavigableMap;", "Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "getRegionFactory", "()Ljava/util/function/BiFunction;", "addListener", "", "listener", "checkPreallocated", "range", "get", "address", "(Lcom/jetbrains/cidr/execution/debugger/memory/Address;)Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "", "preallocate", "", "putRegion", "region", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;)Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "isAllocated", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;Z)Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "putRegionIfNotEmpty", "replaceRegion", "save", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;)V", "splitUnallocated", "byRange", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;)Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "unallocate", "Companion", "Listener", "Region", "cidr-debugger" })
public final class AddressSpace<R extends Region>
{
    private final EventDispatcher<Listener<R>> myRegionEventDispatcher;
    private final NavigableMap<Address, R> myRegionMap;
    @NotNull
    private final BiFunction<? super AddressRange, ? super Boolean, ? extends R> regionFactory;
    private static final long DEFAULT_RANGE_BYTES_AFTER = 512L;
    private static final long DEFAULT_RANGE_BYTES_GROWTH = 256L;
    public static final Companion Companion;
    
    public final void addListener(@NotNull final Listener<? super R> listener) {
        Intrinsics.checkParameterIsNotNull((Object)listener, "listener");
        this.myRegionEventDispatcher.addListener((EventListener)listener);
    }
    
    @NotNull
    public final R get(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        final Region value = this.myRegionMap.floorEntry(address).getValue();
        Intrinsics.checkExpressionValueIsNotNull((Object)value, "myRegionMap.floorEntry(address).value");
        return (R)value;
    }
    
    @NotNull
    public final Collection<R> get(@NotNull final AddressRange addressRange) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        final AddressRange range = this.get(addressRange.getEndInclusive()).getRange();
        final boolean equal = Intrinsics.areEqual((Object)range.getEndInclusive(), (Object)addressRange.getEndInclusive());
        Address endInclusive = null;
        Label_0067: {
            try {
                if (equal) {
                    endInclusive = range.getEndInclusive();
                    break Label_0067;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            endInclusive = (Address)RangesKt.coerceAtLeast((Comparable)range.getStart(), (Comparable)addressRange.getStart());
        }
        final Collection<Object> unmodifiableCollection = (Collection<Object>)Collections.unmodifiableCollection(this.myRegionMap.subMap(addressRange.getStart(), true, endInclusive, equal).values());
        Intrinsics.checkExpressionValueIsNotNull((Object)unmodifiableCollection, "Collections.unmodifiableCollection(subMap.values)");
        return (Collection<R>)unmodifiableCollection;
    }
    
    @NotNull
    public final List<AddressRange> preallocate(@NotNull final Address address) {
        Intrinsics.checkParameterIsNotNull((Object)address, "address");
        final AddressRange rangeForAddress$default = AddressSpace.Companion.rangeForAddress$default(AddressSpace.Companion, address, false, 2, (Object)null);
        final Region value = this.get(address);
        Label_0052: {
            try {
                if (!value.isAllocated()) {
                    return this.preallocate(AddressSpace.Companion.rangeForAddress(address, true));
                }
                final Region region = value;
                final AddressRange addressRange = region.getRange();
                final AddressRange addressRange2 = rangeForAddress$default;
                final boolean b = addressRange.covers(addressRange2);
                if (b) {
                    break Label_0052;
                }
                return this.preallocate(AddressSpace.Companion.rangeForAddress(address, true));
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final Region region = value;
                final AddressRange addressRange = region.getRange();
                final AddressRange addressRange2 = rangeForAddress$default;
                final boolean b = addressRange.covers(addressRange2);
                if (b) {
                    return (List<AddressRange>)CollectionsKt.emptyList();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return this.preallocate(AddressSpace.Companion.rangeForAddress(address, true));
    }
    
    @NotNull
    public final List<AddressRange> preallocate(@NotNull final AddressRange addressRange) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        final ArrayList<Object> list = new ArrayList<Object>();
        final Region value = this.get(addressRange.getStart());
        try {
            if (!value.isAllocated()) {
                list.add(this.a(value, addressRange));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Collection<Object> collection = this.get(addressRange);
        final ArrayList<Region> list2 = new ArrayList<Region>();
        for (final Region next : collection) {
            final Region region = next;
            Label_0135: {
                try {
                    if (!region.isAllocated()) {
                        final boolean b = true;
                        break Label_0135;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                final boolean b = false;
                try {
                    if (!b) {
                        continue;
                    }
                    list2.add(next);
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
        }
        final ArrayList<Region> list4;
        final ArrayList<Region> list3 = new ArrayList<Region>(CollectionsKt.collectionSizeOrDefault((Iterable)(list4 = list2), 10));
        final Iterator<Object> iterator2 = list4.iterator();
        while (iterator2.hasNext()) {
            list3.add(this.a(iterator2.next().getRange(), true));
        }
        CollectionsKt.toCollection((Iterable)list3, (Collection)list);
        final Region value2 = this.get(addressRange.getEndInclusive());
        try {
            if (!value2.isAllocated()) {
                list.add(this.a(value2, addressRange));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        final ArrayList<Object> list6;
        final ArrayList<AddressRange> list5 = new ArrayList<AddressRange>(CollectionsKt.collectionSizeOrDefault((Iterable)(list6 = list), 10));
        final Iterator<Region> iterator3 = list6.iterator();
        while (iterator3.hasNext()) {
            list5.add(iterator3.next().getRange());
        }
        return list5;
    }
    
    private final R a(final R r, final AddressRange addressRange) {
        boolean b = false;
        Label_0018: {
            try {
                if (!r.isAllocated()) {
                    b = true;
                    break Label_0018;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            b = false;
        }
        final boolean b2 = b;
        Label_0053: {
            try {
                if (!_Assertions.ENABLED || b2) {
                    break Label_0053;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            throw new AssertionError((Object)"Assertion failed");
        }
        final AddressRange range = r.getRange();
        final AddressRange intersectWith = addressRange.intersectWith(range);
        try {
            if (intersectWith.isEmpty()) {
                throw new IllegalArgumentException("" + addressRange + " must intersect " + range);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final AddressRange addressRange2 = intersectWith;
        this.myRegionMap.remove(range.getStart());
        final Region c = this.c(range.headUntil(addressRange2.getStart()), false);
        final Region b3 = this.b(addressRange2, true);
        ((Listener)this.myRegionEventDispatcher.getMulticaster()).regionSplit((Region)r, CollectionsKt.filterNotNull((Iterable)CollectionsKt.arrayListOf((Object[])new Region[] { c, b3, this.c(range.tailFrom(addressRange2.getEndInclusive().plus(1)), false) })));
        return (R)b3;
    }
    
    public final void unallocate(@NotNull final AddressRange addressRange) {
        Intrinsics.checkParameterIsNotNull((Object)addressRange, "range");
        this.a(this.a(addressRange), false);
    }
    
    public final void save(@NotNull final R r) {
        Intrinsics.checkParameterIsNotNull((Object)r, "region");
        this.a(r.getRange());
        this.a(r);
    }
    
    private final AddressRange a(final AddressRange p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/memory/AddressSpace.myRegionMap:Ljava/util/NavigableMap;
        //     4: aload_1        
        //     5: invokevirtual   com/jetbrains/cidr/execution/debugger/memory/AddressRange.getStart:()Lcom/jetbrains/cidr/execution/debugger/memory/Address;
        //     8: invokeinterface java/util/NavigableMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    13: checkcast       Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;
        //    16: astore_2       
        //    17: aload_2        
        //    18: ifnull          59
        //    21: aload_1        
        //    22: aload_2        
        //    23: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region.getRange:()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;
        //    28: invokestatic    kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    31: iconst_1       
        //    32: ixor           
        //    33: ifne            59
        //    36: goto            43
        //    39: invokestatic    com/jetbrains/cidr/execution/debugger/memory/AddressSpace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    42: athrow         
        //    43: aload_2        
        //    44: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region.isAllocated:()Z
        //    49: ifne            76
        //    52: goto            59
        //    55: invokestatic    com/jetbrains/cidr/execution/debugger/memory/AddressSpace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    58: athrow         
        //    59: new             Ljava/lang/IllegalStateException;
        //    62: dup            
        //    63: ldc             "Range must be preallocated first"
        //    65: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    68: checkcast       Ljava/lang/Throwable;
        //    71: athrow         
        //    72: invokestatic    com/jetbrains/cidr/execution/debugger/memory/AddressSpace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    75: athrow         
        //    76: aload_1        
        //    77: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  17     36     39     43     Ljava/lang/IllegalArgumentException;
        //  21     52     55     59     Ljava/lang/IllegalArgumentException;
        //  43     72     72     76     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0043:
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private final R b(final R r) {
        this.myRegionMap.put(r.getRange().getStart(), r);
        return r;
    }
    
    private final R b(final AddressRange addressRange, final boolean b) {
        boolean b2 = false;
        Label_0016: {
            try {
                if (!addressRange.isEmpty()) {
                    b2 = true;
                    break Label_0016;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            b2 = false;
        }
        final boolean b3 = b2;
        try {
            if (!_Assertions.ENABLED || b3) {
                return (R)this.b((Region)this.regionFactory.apply(addressRange, b));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        throw new AssertionError((Object)"Region range must not be empty");
    }
    
    private final R c(final AddressRange addressRange, final boolean b) {
        try {
            if (addressRange.isEmpty()) {
                final Region b2 = null;
                return (R)b2;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Region b2 = this.b(addressRange, b);
        return (R)b2;
    }
    
    private final R a(final R p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/execution/debugger/memory/AddressSpace.myRegionMap:Ljava/util/NavigableMap;
        //     4: aload_1        
        //     5: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region.getRange:()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;
        //    10: invokevirtual   com/jetbrains/cidr/execution/debugger/memory/AddressRange.getStart:()Lcom/jetbrains/cidr/execution/debugger/memory/Address;
        //    13: invokeinterface java/util/NavigableMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    18: checkcast       Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;
        //    21: astore_2       
        //    22: aload_2        
        //    23: ifnull          53
        //    26: aload_1        
        //    27: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region.getRange:()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;
        //    32: aload_2        
        //    33: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region.getRange:()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;
        //    38: invokestatic    kotlin/jvm/internal/Intrinsics.areEqual:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    41: iconst_1       
        //    42: ixor           
        //    43: ifeq            122
        //    46: goto            53
        //    49: invokestatic    com/jetbrains/cidr/execution/debugger/memory/AddressSpace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    52: athrow         
        //    53: new             Ljava/lang/IllegalStateException;
        //    56: dup            
        //    57: new             Ljava/lang/StringBuilder;
        //    60: dup            
        //    61: invokespecial   java/lang/StringBuilder.<init>:()V
        //    64: ldc             "Can only replace regions of the same range: "
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: aload_1        
        //    70: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region.getRange:()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;
        //    75: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    78: ldc             " != "
        //    80: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    83: aload_2        
        //    84: dup            
        //    85: ifnull          107
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/execution/debugger/memory/AddressSpace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region.getRange:()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;
        //   100: goto            109
        //   103: invokestatic    com/jetbrains/cidr/execution/debugger/memory/AddressSpace.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: pop            
        //   108: aconst_null    
        //   109: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   112: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   115: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   118: checkcast       Ljava/lang/Throwable;
        //   121: athrow         
        //   122: aload_0        
        //   123: aload_1        
        //   124: invokespecial   com/jetbrains/cidr/execution/debugger/memory/AddressSpace.b:(Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;)Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;
        //   127: pop            
        //   128: aload_0        
        //   129: getfield        com/jetbrains/cidr/execution/debugger/memory/AddressSpace.myRegionEventDispatcher:Lcom/intellij/util/EventDispatcher;
        //   132: invokevirtual   com/intellij/util/EventDispatcher.getMulticaster:()Ljava/util/EventListener;
        //   135: checkcast       Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Listener;
        //   138: aload_2        
        //   139: iconst_1       
        //   140: anewarray       Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;
        //   143: dup            
        //   144: iconst_0       
        //   145: aload_1        
        //   146: aastore        
        //   147: invokestatic    kotlin/collections/CollectionsKt.arrayListOf:([Ljava/lang/Object;)Ljava/util/ArrayList;
        //   150: checkcast       Ljava/util/List;
        //   153: invokeinterface com/jetbrains/cidr/execution/debugger/memory/AddressSpace$Listener.regionSplit:(Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;Ljava/util/List;)V
        //   158: aload_1        
        //   159: areturn        
        //    Signature:
        //  (TR;)TR;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  22     46     49     53     Ljava/lang/IllegalArgumentException;
        //  26     88     91     95     Ljava/lang/IllegalArgumentException;
        //  53     103    103    107    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private final R a(final AddressRange addressRange, final boolean b) {
        return (R)this.a((Region)this.regionFactory.apply(addressRange, b));
    }
    
    @NotNull
    public final BiFunction<? super AddressRange, ? super Boolean, ? extends R> getRegionFactory() {
        return this.regionFactory;
    }
    
    public AddressSpace(@NotNull final BiFunction<? super AddressRange, ? super Boolean, ? extends R> regionFactory) {
        Intrinsics.checkParameterIsNotNull((Object)regionFactory, "regionFactory");
        this.regionFactory = regionFactory;
        final EventDispatcher create = EventDispatcher.create((Class)Listener.class);
        Intrinsics.checkExpressionValueIsNotNull((Object)create, "EventDispatcher.create(L\u2026va as Class<Listener<R>>)");
        this.myRegionEventDispatcher = (com.intellij.util.EventDispatcher<Listener<R>>)create;
        this.myRegionMap = new TreeMap<Address, R>();
        this.b(AddressRange.WHOLE, false);
    }
    
    static {
        Companion = new Companion(null);
    }
    
    public static final /* synthetic */ long access$getDEFAULT_RANGE_BYTES_AFTER$cp() {
        return AddressSpace.DEFAULT_RANGE_BYTES_AFTER;
    }
    
    public static final /* synthetic */ long access$getDEFAULT_RANGE_BYTES_GROWTH$cp() {
        return AddressSpace.DEFAULT_RANGE_BYTES_GROWTH;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\u000f" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Companion;", "", "()V", "DEFAULT_RANGE_BYTES_AFTER", "", "getDEFAULT_RANGE_BYTES_AFTER", "()J", "DEFAULT_RANGE_BYTES_GROWTH", "getDEFAULT_RANGE_BYTES_GROWTH", "rangeForAddress", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "address", "Lcom/jetbrains/cidr/execution/debugger/memory/Address;", "grow", "", "cidr-debugger" })
    public static final class Companion
    {
        public final long getDEFAULT_RANGE_BYTES_AFTER() {
            return AddressSpace.access$getDEFAULT_RANGE_BYTES_AFTER$cp();
        }
        
        public final long getDEFAULT_RANGE_BYTES_GROWTH() {
            return AddressSpace.access$getDEFAULT_RANGE_BYTES_GROWTH$cp();
        }
        
        @NotNull
        public final AddressRange rangeForAddress(@NotNull final Address address, final boolean b) {
            Intrinsics.checkParameterIsNotNull((Object)address, "address");
            return address.rangeTo(address.plus(Address.Companion.getCoercing(this.getDEFAULT_RANGE_BYTES_AFTER() + (b ? this.getDEFAULT_RANGE_BYTES_GROWTH() : 0L) - 1)));
        }
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "", "isAllocated", "", "()Z", "range", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "getRange", "()Lcom/jetbrains/cidr/execution/debugger/memory/AddressRange;", "cidr-debugger" })
    public interface Region
    {
        @NotNull
        AddressRange getRange();
        
        boolean isAllocated();
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0001\u0010\u0001 \u0000*\u00020\u00022\u00020\u0003J#\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00028\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\bH&¢\u0006\u0002\u0010\t¨\u0006\n" }, d2 = { "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Listener;", "R", "Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;", "Ljava/util/EventListener;", "regionSplit", "", "oldRegion", "newRegions", "", "(Lcom/jetbrains/cidr/execution/debugger/memory/AddressSpace$Region;Ljava/util/List;)V", "cidr-debugger" })
    public interface Listener<R extends Region> extends EventListener
    {
        void regionSplit(@NotNull final R p0, @NotNull final List<? extends R> p1);
    }
}
