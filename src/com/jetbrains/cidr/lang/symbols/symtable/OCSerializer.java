// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.minlog.Log;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CalendarSerializer;
import java.util.Calendar;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$TimeZoneSerializer;
import java.util.TimeZone;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$KryoSerializableSerializer;
import com.esotericsoftware.kryo.KryoSerializable;
import com.esotericsoftware.kryo.serializers.MapSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$TreeMapSerializer;
import java.util.TreeMap;
import com.esotericsoftware.kryo.serializers.CollectionSerializer;
import java.util.Collection;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CollectionsSingletonSetSerializer;
import kotlin.collections.SetsKt;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CollectionsSingletonMapSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CollectionsSingletonListSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CollectionsEmptySetSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CollectionsEmptyMapSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CollectionsEmptyListSerializer;
import java.util.Collections;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$StringBuilderSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$StringBufferSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$CurrencySerializer;
import java.util.Currency;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$EnumSetSerializer;
import java.util.EnumSet;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$DateSerializer;
import java.util.Date;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$ClassSerializer;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$BigDecimalSerializer;
import java.math.BigDecimal;
import com.esotericsoftware.kryo.serializers.DefaultSerializers$BigIntegerSerializer;
import java.math.BigInteger;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$ObjectArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$StringArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$BooleanArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$DoubleArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$FloatArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$LongArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$IntArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$ShortArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$CharArraySerializer;
import com.esotericsoftware.kryo.serializers.DefaultArraySerializers$ByteArraySerializer;
import kotlin.collections.CollectionsKt;
import java.util.Map;
import kotlin.collections.MapsKt;
import com.intellij.util.containers.MultiMap;
import java.util.List;
import com.intellij.util.containers.ContainerUtilRt;
import com.intellij.util.SmartList;
import gnu.trove.THashMap;
import java.util.Set;
import gnu.trove.THashSet;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import com.intellij.util.containers.MostlySingularMultiMap;
import com.intellij.openapi.util.Pair;
import com.esotericsoftware.kryo.Serializer;
import kotlin.jvm.internal.Intrinsics;
import com.esotericsoftware.kryo.Kryo;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.JvmField;
import com.intellij.openapi.diagnostic.Logger;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0001\u000fB\u0007\b\u0016¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010\r\u001a\u00020\fH$J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u0010" }, d2 = { "Lcom/jetbrains/cidr/lang/symbols/symtable/OCSerializer;", "", "()V", "LOG", "Lcom/intellij/openapi/diagnostic/Logger;", "kryo", "Lcom/esotericsoftware/kryo/Kryo;", "getKryo", "()Lcom/esotericsoftware/kryo/Kryo;", "setKryo", "(Lcom/esotericsoftware/kryo/Kryo;)V", "registerDefaultSerializers", "", "registerSerializers", "registerUtilitySerializers", "SingletonSerializer", "cidr-lang" })
public abstract class OCSerializer
{
    @JvmField
    @NotNull
    protected final Logger LOG;
    @NotNull
    private Kryo kryo;
    
    @NotNull
    public final Kryo getKryo() {
        return this.kryo;
    }
    
    public final void setKryo(@NotNull final Kryo kryo) {
        Intrinsics.checkParameterIsNotNull((Object)kryo, "<set-?>");
        this.kryo = kryo;
    }
    
    protected abstract void registerSerializers();
    
    private final void a(final Kryo kryo) {
        kryo.register((Class)String.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$1());
        kryo.register((Class)Pair.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$2(kryo, kryo, (Class)Pair.class));
        kryo.register((Class)MostlySingularMultiMap.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$3(kryo, kryo, (Class)MostlySingularMultiMap.class));
        new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$4(kryo).registerValueList();
        kryo.register((Class)ArrayList.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$5());
        kryo.register((Class)HashMap.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$6());
        kryo.register((Class)com.intellij.util.containers.hash.HashMap.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$7());
        kryo.register((Class)com.intellij.util.containers.HashMap.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$8());
        kryo.register((Class)HashSet.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$9());
        kryo.register((Class)com.intellij.util.containers.HashSet.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$10());
        kryo.register((Class)THashSet.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$11());
        kryo.register((Class)Set.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$12());
        kryo.register((Class)THashMap.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$13());
        kryo.register((Class)SmartList.class, (Serializer)new OCSerializer$registerUtilitySerializers.OCSerializer$registerUtilitySerializers$14());
        final List emptyList = ContainerUtilRt.emptyList();
        kryo.register((Class)emptyList.getClass(), (Serializer)new SingletonSerializer<List>(emptyList));
        final MostlySingularMultiMap emptyMap = MostlySingularMultiMap.emptyMap();
        kryo.register((Class)emptyMap.getClass(), (Serializer)new SingletonSerializer<MostlySingularMultiMap>(emptyMap));
        final MultiMap empty = MultiMap.EMPTY;
        kryo.register((Class)empty.getClass(), (Serializer)new SingletonSerializer<MultiMap>(empty));
        final Map emptyMap2 = MapsKt.emptyMap();
        kryo.register((Class)emptyMap2.getClass(), (Serializer)new SingletonSerializer<Map>(emptyMap2));
        final List emptyList2 = CollectionsKt.emptyList();
        kryo.register((Class)emptyList2.getClass(), (Serializer)new SingletonSerializer<List>(emptyList2));
    }
    
    private final void b(final Kryo kryo) {
        kryo.register((Class)byte[].class, (Serializer)new DefaultArraySerializers$ByteArraySerializer());
        kryo.register((Class)char[].class, (Serializer)new DefaultArraySerializers$CharArraySerializer());
        kryo.register((Class)short[].class, (Serializer)new DefaultArraySerializers$ShortArraySerializer());
        kryo.register((Class)int[].class, (Serializer)new DefaultArraySerializers$IntArraySerializer());
        kryo.register((Class)long[].class, (Serializer)new DefaultArraySerializers$LongArraySerializer());
        kryo.register((Class)float[].class, (Serializer)new DefaultArraySerializers$FloatArraySerializer());
        kryo.register((Class)double[].class, (Serializer)new DefaultArraySerializers$DoubleArraySerializer());
        kryo.register((Class)boolean[].class, (Serializer)new DefaultArraySerializers$BooleanArraySerializer());
        kryo.register((Class)String[].class, (Serializer)new DefaultArraySerializers$StringArraySerializer());
        kryo.register((Class)Object[].class, (Serializer)new DefaultArraySerializers$ObjectArraySerializer(kryo, (Class)Object[].class));
        kryo.register((Class)BigInteger.class, (Serializer)new DefaultSerializers$BigIntegerSerializer());
        kryo.register((Class)BigDecimal.class, (Serializer)new DefaultSerializers$BigDecimalSerializer());
        kryo.register((Class)Class.class, (Serializer)new DefaultSerializers$ClassSerializer());
        kryo.register((Class)Date.class, (Serializer)new DefaultSerializers$DateSerializer());
        kryo.register((Class)EnumSet.class, (Serializer)new DefaultSerializers$EnumSetSerializer());
        try {
            kryo.register((Class)Class.forName("java.util.RegularEnumSet"), (Serializer)new DefaultSerializers$EnumSetSerializer());
        }
        catch (ClassNotFoundException ex) {
            this.LOG.error((Throwable)ex);
        }
        kryo.register((Class)Currency.class, (Serializer)new DefaultSerializers$CurrencySerializer());
        kryo.register((Class)StringBuffer.class, (Serializer)new DefaultSerializers$StringBufferSerializer());
        kryo.register((Class)StringBuilder.class, (Serializer)new DefaultSerializers$StringBuilderSerializer());
        kryo.register((Class)Collections.EMPTY_LIST.getClass(), (Serializer)new DefaultSerializers$CollectionsEmptyListSerializer());
        kryo.register((Class)Collections.EMPTY_MAP.getClass(), (Serializer)new DefaultSerializers$CollectionsEmptyMapSerializer());
        kryo.register((Class)Collections.EMPTY_SET.getClass(), (Serializer)new DefaultSerializers$CollectionsEmptySetSerializer());
        kryo.register((Class)CollectionsKt.listOf(new Object()).getClass(), (Serializer)new DefaultSerializers$CollectionsSingletonListSerializer());
        kryo.register((Class)Collections.singletonMap((Object)null, (Object)null).getClass(), (Serializer)new DefaultSerializers$CollectionsSingletonMapSerializer());
        kryo.register((Class)SetsKt.setOf(new Object()).getClass(), (Serializer)new DefaultSerializers$CollectionsSingletonSetSerializer());
        kryo.register((Class)Collection.class, (Serializer)new CollectionSerializer());
        kryo.register((Class)TreeMap.class, (Serializer)new DefaultSerializers$TreeMapSerializer());
        kryo.register((Class)Map.class, (Serializer)new MapSerializer());
        kryo.register((Class)KryoSerializable.class, (Serializer)new DefaultSerializers$KryoSerializableSerializer());
        kryo.register((Class)TimeZone.class, (Serializer)new DefaultSerializers$TimeZoneSerializer());
        kryo.register((Class)Calendar.class, (Serializer)new DefaultSerializers$CalendarSerializer());
    }
    
    public OCSerializer() {
        final Logger instance = Logger.getInstance((Class)this.getClass());
        Intrinsics.checkExpressionValueIsNotNull((Object)instance, "Logger.getInstance(this.javaClass)");
        this.LOG = instance;
        (this.kryo = new Kryo() {
            final /* synthetic */ OCSerializer this$0;
            
            public <T> T newInstance(@NotNull final Class<T> clazz) {
                Intrinsics.checkParameterIsNotNull((Object)clazz, "type");
                this.this$0.LOG.error("Serializing default type; please add a custom serializer with overridden create() method for " + clazz);
                return (T)super.newInstance((Class)clazz);
            }
        }).setAutoReset(true);
        this.kryo.setRegistrationRequired(true);
        Log.set(4);
        this.a(this.kryo);
        this.b(this.kryo);
        this.registerSerializers();
    }
    
    @Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 1, d1 = { "\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00028\u0000¢\u0006\u0002\u0010\u0004J+\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0016¢\u0006\u0002\u0010\rJ%\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0013R\u0010\u0010\u0003\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0014" }, d2 = { "Lcom/jetbrains/cidr/lang/symbols/symtable/OCSerializer$SingletonSerializer;", "T", "Lcom/esotericsoftware/kryo/Serializer;", "myInstance", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "read", "kryo", "Lcom/esotericsoftware/kryo/Kryo;", "input", "Lcom/esotericsoftware/kryo/io/Input;", "type", "Ljava/lang/Class;", "(Lcom/esotericsoftware/kryo/Kryo;Lcom/esotericsoftware/kryo/io/Input;Ljava/lang/Class;)Ljava/lang/Object;", "write", "", "output", "Lcom/esotericsoftware/kryo/io/Output;", "object", "(Lcom/esotericsoftware/kryo/Kryo;Lcom/esotericsoftware/kryo/io/Output;Ljava/lang/Object;)V", "cidr-lang" })
    public static final class SingletonSerializer<T> extends Serializer<T>
    {
        private final T myInstance;
        
        public void write(@NotNull final Kryo kryo, @NotNull final Output output, final T t) {
            Intrinsics.checkParameterIsNotNull((Object)kryo, "kryo");
            Intrinsics.checkParameterIsNotNull((Object)output, "output");
        }
        
        public T read(@NotNull final Kryo kryo, @NotNull final Input input, @NotNull final Class<T> clazz) {
            Intrinsics.checkParameterIsNotNull((Object)kryo, "kryo");
            Intrinsics.checkParameterIsNotNull((Object)input, "input");
            Intrinsics.checkParameterIsNotNull((Object)clazz, "type");
            return this.myInstance;
        }
        
        public SingletonSerializer(final T myInstance) {
            this.myInstance = myInstance;
        }
    }
}
