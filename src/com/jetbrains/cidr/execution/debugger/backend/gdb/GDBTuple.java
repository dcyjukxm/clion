// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.intellij.openapi.util.Pair;
import com.intellij.util.SmartList;
import com.jetbrains.cidr.execution.debugger.memory.Address;
import com.intellij.execution.ExecutionException;
import java.util.function.Supplier;
import java.util.function.Function;
import org.jetbrains.annotations.Contract;
import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class GDBTuple extends ArrayList<Object>
{
    private static final List<String> TRUE_STRINGS;
    private static final List<String> FALSE_STRINGS;
    
    public static GDBTuple of(final Object... array) {
        final GDBTuple gdbTuple = new GDBTuple();
        gdbTuple.addAll(Arrays.asList(array));
        return gdbTuple;
    }
    
    @Contract("_, _, !null -> !null")
    protected <T> T get(final String s, @NotNull final Class<T> clazz, @Nullable final T t) {
        try {
            if (clazz == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "get"));
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        final Iterator<Object> iterator = this.iterator();
        while (iterator.hasNext()) {
            final Object a = a(iterator.next(), s);
            try {
                if (clazz.isInstance(a)) {
                    return clazz.cast(a);
                }
                continue;
            }
            catch (NumberFormatException ex2) {
                throw a(ex2);
            }
        }
        return t;
    }
    
    @Nullable
    public String getString(final String s) {
        return this.getString(s, null);
    }
    
    @Contract("_, !null -> !null")
    public String getString(final String s, @Nullable final String s2) {
        return this.get(s, String.class, s2);
    }
    
    @NotNull
    public <X extends Throwable> String getRequiredStringOrThrow(final String s, @NotNull final Function<String, ? extends X> function) throws X, Throwable {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionConstructor", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredStringOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        String requiredStringOrThrow;
        try {
            requiredStringOrThrow = this.getRequiredStringOrThrow(s, a((Function<String, ? extends Throwable>)function, this.b(s)));
            if (requiredStringOrThrow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredStringOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        return requiredStringOrThrow;
    }
    
    @NotNull
    public <X extends Throwable> String getRequiredStringOrThrow(final String s, @NotNull final Supplier<? extends X> supplier) throws X, Throwable {
        try {
            if (supplier == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionSupplier", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredStringOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        String s2;
        try {
            s2 = a(this.getString(s), (Supplier<? extends Throwable>)supplier);
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredStringOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        return s2;
    }
    
    @NotNull
    public String getRequiredString(final String s) throws ExecutionException {
        String requiredStringOrThrow;
        try {
            requiredStringOrThrow = this.getRequiredStringOrThrow(s, (Function<String, ? extends Throwable>)ExecutionException::new);
            if (requiredStringOrThrow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredString"));
            }
        }
        catch (ExecutionException ex) {
            throw a((Throwable)ex);
        }
        return requiredStringOrThrow;
    }
    
    @Nullable
    public GDBTuple getTuple(final String s) {
        return this.get(s, GDBTuple.class, null);
    }
    
    @NotNull
    public GDBTuple getTupleOrEmpty(final String s) {
        GDBTuple tuple = this.getTuple(s);
        if (tuple == null) {
            tuple = new GDBTuple();
        }
        GDBTuple gdbTuple;
        try {
            gdbTuple = tuple;
            if (gdbTuple == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getTupleOrEmpty"));
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        return gdbTuple;
    }
    
    @NotNull
    public GDBTuple getRequiredTuple(final String s) throws ExecutionException {
        GDBTuple requiredTupleOrThrow;
        try {
            requiredTupleOrThrow = this.getRequiredTupleOrThrow(s, (Function<String, ? extends Throwable>)ExecutionException::new);
            if (requiredTupleOrThrow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredTuple"));
            }
        }
        catch (ExecutionException ex) {
            throw a((Throwable)ex);
        }
        return requiredTupleOrThrow;
    }
    
    @NotNull
    public <X extends Throwable> GDBTuple getRequiredTupleOrThrow(final String s, @NotNull final Function<String, ? extends X> function) throws X, Throwable {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionConstructor", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredTupleOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        GDBTuple requiredTupleOrThrow;
        try {
            requiredTupleOrThrow = this.getRequiredTupleOrThrow(s, a((Function<String, ? extends Throwable>)function, this.b(s)));
            if (requiredTupleOrThrow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredTupleOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        return requiredTupleOrThrow;
    }
    
    @NotNull
    public <X extends Throwable> GDBTuple getRequiredTupleOrThrow(final String s, @NotNull final Supplier<? extends X> supplier) throws X, Throwable {
        try {
            if (supplier == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionSupplier", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredTupleOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        GDBTuple gdbTuple;
        try {
            gdbTuple = a(this.getTuple(s), (Supplier<? extends Throwable>)supplier);
            if (gdbTuple == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredTupleOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        return gdbTuple;
    }
    
    @Nullable
    public Address getAddress(final String s) {
        return this.getAddress(s, null);
    }
    
    @Contract("_, !null -> !null")
    public Address getAddress(final String s, @Nullable final Address address) {
        final String string = this.getString(s);
        try {
            if (string != null) {
                return a(string, address);
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        return address;
    }
    
    @NotNull
    public Address getRequiredAddress(final String s) throws ExecutionException {
        Address requiredAddressOrThrow;
        try {
            requiredAddressOrThrow = this.getRequiredAddressOrThrow(s, (Function<String, ? extends Throwable>)ExecutionException::new);
            if (requiredAddressOrThrow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredAddress"));
            }
        }
        catch (ExecutionException ex) {
            throw a((Throwable)ex);
        }
        return requiredAddressOrThrow;
    }
    
    @NotNull
    public <X extends Throwable> Address getRequiredAddressOrThrow(final String s, @NotNull final Function<String, ? extends X> function) throws X, Throwable {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionConstructor", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredAddressOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final String s2 = a(this.getString(s), a((Function<String, ? extends Throwable>)function, this.b(s)));
        Address address;
        try {
            address = a(a(s2, (Address)null), a((Function<String, ? extends Throwable>)function, () -> "Malformed number value '" + s2 + "' for key '" + s + "' in tuple: " + this));
            if (address == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredAddressOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        return address;
    }
    
    @Contract("_, !null -> !null")
    private static Address a(@NotNull final String s, @Nullable final Address address) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "parseAddress"));
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        try {
            return Address.parseHexString(s);
        }
        catch (NumberFormatException ex2) {
            return address;
        }
    }
    
    @Contract("_, !null -> !null")
    public Integer getInteger(final String s, @Nullable final Integer n) {
        final String string = this.getString(s);
        try {
            if (string != null) {
                return a(string, n);
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        return n;
    }
    
    public int getInt(final String s, final int n) {
        return this.getInteger(s, n);
    }
    
    public int getRequiredInt(final String s) throws ExecutionException {
        return this.getRequiredIntOrThrow(s, (Function<String, ? extends Throwable>)ExecutionException::new);
    }
    
    public <X extends Throwable> int getRequiredIntOrThrow(final String s, @NotNull final Function<String, ? extends X> function) throws X, Throwable {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionConstructor", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredIntOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final String s2;
        return a(a(a(this.getString(s), a((Function<String, ? extends Throwable>)function, this.b(s))), (Integer)null), a((Function<String, ? extends Throwable>)function, () -> "Malformed number value '" + s2 + "' for key '" + s + "' in tuple: " + this));
    }
    
    @Contract("_, !null -> !null")
    private static Integer a(@NotNull final String s, @Nullable final Integer n) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "s", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "parseInteger"));
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        try {
            return Integer.decode(s);
        }
        catch (NumberFormatException ex2) {
            return n;
        }
    }
    
    public boolean getBoolean(final String s) {
        return this.getBoolean(s, false);
    }
    
    public boolean getBoolean(final String s, final boolean b) {
        final String string = this.getString(s);
        if (string != null) {
            final String lowerCase = string.toLowerCase();
            try {
                if (GDBTuple.TRUE_STRINGS.contains(lowerCase)) {
                    return true;
                }
            }
            catch (NumberFormatException ex) {
                throw a(ex);
            }
            try {
                if (GDBTuple.FALSE_STRINGS.contains(lowerCase)) {
                    return false;
                }
            }
            catch (NumberFormatException ex2) {
                throw a(ex2);
            }
            final Integer a = a(string, (Integer)null);
            Label_0081: {
                try {
                    if (a == null) {
                        return b;
                    }
                    final Integer n = a;
                    final int n2 = n;
                    if (n2 != 0) {
                        break Label_0081;
                    }
                    return false;
                }
                catch (NumberFormatException ex3) {
                    throw a(ex3);
                }
                try {
                    final Integer n = a;
                    final int n2 = n;
                    if (n2 != 0) {
                        return true;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw a(ex4);
                }
            }
            return false;
        }
        return b;
    }
    
    @Nullable
    public Location getLocation(final String s) {
        return this.getLocation(s, (Location)null);
    }
    
    @Contract("_, !null -> !null")
    public Location getLocation(final String s, @Nullable final Location location) {
        final String string = this.getString(s);
        try {
            if (string != null) {
                return Location.tryParse(string, location);
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        return location;
    }
    
    @NotNull
    public Location getRequiredLocation(final String s) throws ExecutionException {
        Location requiredLocationOrThrow;
        try {
            requiredLocationOrThrow = this.getRequiredLocationOrThrow(s, (Function<String, ? extends Throwable>)ExecutionException::new);
            if (requiredLocationOrThrow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredLocation"));
            }
        }
        catch (ExecutionException ex) {
            throw a((Throwable)ex);
        }
        return requiredLocationOrThrow;
    }
    
    @NotNull
    public <X extends Throwable> Location getRequiredLocationOrThrow(final String s, @NotNull final Function<String, ? extends X> function) throws X, Throwable {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionConstructor", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredLocationOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final String s2 = a(this.getString(s), a((Function<String, ? extends Throwable>)function, this.b(s)));
        Location location;
        try {
            location = a(Location.tryParse(s2), a((Function<String, ? extends Throwable>)function, () -> "Malformed location string '" + s2 + "' for key '" + s + "' in tuple: " + this));
            if (location == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredLocationOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        return location;
    }
    
    @Nullable
    public Location getLocation(final String s, final String s2) {
        return this.getLocation(s, s2, null);
    }
    
    @Contract("_, _, !null -> !null")
    public Location getLocation(final String s, final String s2, @Nullable final Location location) {
        final String string = this.getString(s);
        final Integer integer = this.getInteger(s2, null);
        Label_0032: {
            try {
                if (string == null) {
                    return location;
                }
                final Integer n = integer;
                if (n != null) {
                    break Label_0032;
                }
                return location;
            }
            catch (NumberFormatException ex) {
                throw a(ex);
            }
            try {
                final Integer n = integer;
                if (n != null) {
                    return Location.fromFileLineNumber(string, integer);
                }
            }
            catch (NumberFormatException ex2) {
                throw a(ex2);
            }
        }
        return location;
    }
    
    @NotNull
    public Location getRequiredLocation(final String s, final String s2) throws ExecutionException {
        Location requiredLocationOrThrow;
        try {
            requiredLocationOrThrow = this.getRequiredLocationOrThrow(s, s2, (Function<String, ? extends Throwable>)ExecutionException::new);
            if (requiredLocationOrThrow == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredLocation"));
            }
        }
        catch (ExecutionException ex) {
            throw a((Throwable)ex);
        }
        return requiredLocationOrThrow;
    }
    
    @NotNull
    public <X extends Throwable> Location getRequiredLocationOrThrow(final String s, final String s2, @NotNull final Function<String, ? extends X> function) throws X, Throwable {
        try {
            if (function == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionConstructor", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredLocationOrThrow"));
            }
        }
        catch (Throwable t) {
            throw a(t);
        }
        final String s3 = a(this.getString(s), a((Function<String, ? extends Throwable>)function, this.b(s)));
        final int requiredIntOrThrow = this.getRequiredIntOrThrow(s2, (Function<String, ? extends Throwable>)function);
        Location fromFileLineNumber;
        try {
            fromFileLineNumber = Location.fromFileLineNumber(s3, requiredIntOrThrow);
            if (fromFileLineNumber == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getRequiredLocationOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        return fromFileLineNumber;
    }
    
    @NotNull
    public <T> List<T> getAll(final String s, final Class<T> clazz) {
        final SmartList list = new SmartList();
        final Iterator<Object> iterator = this.iterator();
        while (iterator.hasNext()) {
            final Object a = a(iterator.next(), s);
            try {
                if (a == null) {
                    continue;
                }
                ((List<T>)list).add(clazz.cast(a));
            }
            catch (NumberFormatException ex) {
                throw a(ex);
            }
        }
        SmartList list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getAll"));
            }
        }
        catch (NumberFormatException ex2) {
            throw a(ex2);
        }
        return (List<T>)list2;
    }
    
    @NotNull
    public <T> Pair<T, List<T>> getWithSuccessors(final String s, final Class<T> clazz) {
        com.intellij.openapi.util.Pair<T, List<T>> withSuccessors;
        try {
            withSuccessors = this.getWithSuccessors(s, clazz, (T)null);
            if (withSuccessors == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getWithSuccessors"));
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        return withSuccessors;
    }
    
    @NotNull
    public <T> Pair<T, List<T>> getWithSuccessors(final String s, final Class<T> clazz, @Nullable final T t) {
        final SmartList list = new SmartList();
        Object a = null;
        for (final Object next : this) {
            if (a == null) {
                a = a(next, s);
            }
            else {
                try {
                    if (next instanceof Pair) {
                        break;
                    }
                }
                catch (NumberFormatException ex) {
                    throw a(ex);
                }
                ((List<T>)list).add(clazz.cast(next));
            }
        }
        if (a == null) {
            a = t;
        }
        Pair create;
        try {
            create = Pair.create(a, (Object)list);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getWithSuccessors"));
            }
        }
        catch (NumberFormatException ex2) {
            throw a(ex2);
        }
        return (Pair<T, List<T>>)create;
    }
    
    @NotNull
    private Supplier<String> b(final String s) {
        Supplier<String> supplier;
        try {
            supplier = (() -> "Missing required key '" + s + "' in tuple: " + this);
            if (supplier == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "missingKeyError"));
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        return supplier;
    }
    
    @NotNull
    private static <X extends Throwable> Supplier<X> a(@NotNull final Function<String, ? extends X> p0, @NotNull final Supplier<String> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "exceptionConstructor"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "withMessage"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "messageSupplier"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "withMessage"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_1        
        //    90: invokedynamic   get:(Ljava/util/function/Function;Ljava/util/function/Supplier;)Ljava/util/function/Supplier;
        //    95: dup            
        //    96: ifnonnull       133
        //    99: new             Ljava/lang/IllegalStateException;
        //   102: dup            
        //   103: ldc             "@NotNull method %s.%s must not return null"
        //   105: ldc             2
        //   107: anewarray       Ljava/lang/Object;
        //   110: dup            
        //   111: ldc             0
        //   113: ldc             "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple"
        //   115: aastore        
        //   116: dup            
        //   117: ldc             1
        //   119: ldc             "withMessage"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   128: athrow         
        //   129: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple.a:(Ljava/lang/Throwable;)Ljava/lang/Throwable;
        //   132: athrow         
        //   133: areturn        
        //    Signature:
        //  <X:Ljava/lang/Throwable;>(Ljava/util/function/Function<Ljava/lang/String;+TX;>;Ljava/util/function/Supplier<Ljava/lang/String;>;)Ljava/util/function/Supplier<TX;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     84     84     88     Ljava/lang/NumberFormatException;
        //  88     129    129    133    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    @NotNull
    private static <T, X extends Throwable> T a(@Nullable final T t, @NotNull final Supplier<? extends X> supplier) throws X, Throwable {
        try {
            if (supplier == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "exceptionSupplier", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "checkNotNullOrThrow"));
            }
        }
        catch (Throwable t2) {
            throw a(t2);
        }
        try {
            if (t == null) {
                throw (Throwable)supplier.get();
            }
        }
        catch (Throwable t3) {
            throw a(t3);
        }
        try {
            if (t == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "checkNotNullOrThrow"));
            }
        }
        catch (Throwable t4) {
            throw a(t4);
        }
        return t;
    }
    
    @Nullable
    private static <T> T a(@NotNull final Object o, final String s) {
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "val", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple", "getIfEquals"));
            }
        }
        catch (NumberFormatException ex) {
            throw a(ex);
        }
        try {
            if (o.equals(s)) {
                return (T)o;
            }
        }
        catch (NumberFormatException ex2) {
            throw a(ex2);
        }
        Label_0086: {
            try {
                if (!(o instanceof Pair)) {
                    return null;
                }
                final Object o2 = o;
                final Pair pair = (Pair)o2;
                final Object o3 = pair.first;
                final String s2 = s;
                final boolean b = o3.equals(s2);
                if (b) {
                    break Label_0086;
                }
                return null;
            }
            catch (NumberFormatException ex3) {
                throw a(ex3);
            }
            try {
                final Object o2 = o;
                final Pair pair = (Pair)o2;
                final Object o3 = pair.first;
                final String s2 = s;
                final boolean b = o3.equals(s2);
                if (b) {
                    return (T)((Pair)o).second;
                }
            }
            catch (NumberFormatException ex4) {
                throw a(ex4);
            }
        }
        return null;
    }
    
    static {
        TRUE_STRINGS = Arrays.asList("true", "yes", "on");
        FALSE_STRINGS = Arrays.asList("false", "no", "off");
    }
    
    private static Throwable a(final Throwable t) {
        return t;
    }
    
    public static class Location
    {
        private static final Pattern LOCATION_PATTERN;
        @NotNull
        public final String path;
        public final int line;
        
        public Location(@NotNull final String path, final int line) {
            if (path == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple$Location", "<init>"));
            }
            this.path = path;
            this.line = line;
        }
        
        @NotNull
        public static Location fromFileLineNumber(@NotNull final String s, final int n) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple$Location", "fromFileLineNumber"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Location location;
            try {
                location = new Location(s, n - 1);
                if (location == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple$Location", "fromFileLineNumber"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return location;
        }
        
        @Nullable
        public static Location tryParse(@NotNull final String s) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "locationString", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple$Location", "tryParse"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return tryParse(s, null);
        }
        
        @Contract("_, !null -> !null")
        public static Location tryParse(@NotNull final String s, @Nullable final Location location) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "locationString", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple$Location", "tryParse"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final Matcher matcher = Location.LOCATION_PATTERN.matcher(s);
            try {
                if (!matcher.matches()) {
                    return location;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return fromFileLineNumber(matcher.group(1), Integer.parseInt(matcher.group(2)));
        }
        
        static {
            LOCATION_PATTERN = Pattern.compile("^(.*):(\\d+)$");
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
