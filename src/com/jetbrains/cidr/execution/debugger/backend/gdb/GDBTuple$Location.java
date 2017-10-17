// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import org.jetbrains.annotations.Contract;
import java.util.regex.Matcher;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;

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
