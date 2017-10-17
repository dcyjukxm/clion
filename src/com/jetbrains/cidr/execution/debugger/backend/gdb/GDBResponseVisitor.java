// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import java.util.regex.Matcher;
import com.intellij.openapi.util.Couple;
import java.util.stream.Collector;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.List;
import org.antlr.v4.runtime.misc.NotNull;
import com.intellij.openapi.util.Pair;
import org.jetbrains.annotations.Nullable;
import java.util.regex.Pattern;

public class GDBResponseVisitor extends GdbBaseVisitor<GDBResponse>
{
    private static final Pattern ID_PATTERN;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    private static <T> T b(final GDBResponse.ResultRecord resultRecord) {
        final GDBTuple resultList = resultRecord.getResultList();
        Label_0055: {
            try {
                switch (((GDBResponse.Record<CATEGORY_TYPE, GDBResponse.ResultRecord.Type>)resultRecord).getType()) {
                    case tuple_value: {
                        return (T)resultList;
                    }
                    case list_value:
                    case str_value: {
                        break;
                    }
                    default: {
                        break Label_0055;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return (T)a(resultList);
            try {
                assert false;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    private static <T> T a(final GDBResponse.ResultRecord resultRecord) {
        return a(resultRecord.getResultList());
    }
    
    private static <T> T a(final GDBTuple gdbTuple) {
        try {
            if (gdbTuple.size() != 1) {
                throw new IllegalArgumentException("Expected tuple of a single element: " + gdbTuple);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (T)gdbTuple.get(0);
    }
    
    public static GDBResponse getResponse(@Nullable final String s, final GDBResponse.ResultRecord resultRecord) {
        Object o = b(resultRecord);
        if (s != null) {
            o = Pair.create((Object)s, o);
        }
        return new GDBResponse.ResultRecord(GDBResponse.ResultRecord.Category.result, GDBResponse.ResultRecord.Type.result, GDBTuple.of(o));
    }
    
    @Override
    public GDBResponse visitResult(@NotNull final GdbParser.ResultContext resultContext) {
        final GDBResponse.ResultRecord resultRecord = ((GdbBaseVisitor<GDBResponse.ResultRecord>)this).visitValue(resultContext.value());
        try {
            if (resultContext.variable == null) {
                final String text = null;
                return getResponse(text, resultRecord);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String text = resultContext.variable.getText();
        return getResponse(text, resultRecord);
    }
    
    @Override
    public GDBResponse visitCnst(@NotNull final GdbParser.CnstContext cnstContext) {
        return new GDBResponse.ResultRecord(GDBResponse.ResultRecord.Category.result, GDBResponse.ResultRecord.Type.str_value, GDBTuple.of(a(cnstContext.cString(), true)));
    }
    
    @Override
    public GDBResponse visitTuple(@NotNull final GdbParser.TupleContext tupleContext) {
        return new GDBResponse.ResultRecord(GDBResponse.ResultRecord.Category.result, GDBResponse.ResultRecord.Type.tuple_value, this.b(tupleContext.result()));
    }
    
    private GDBTuple b(final List<GdbParser.ResultContext> list) {
        return list.stream().map(resultContext -> a((GDBResponse.ResultRecord)this.visitResult(resultContext))).collect((Collector<? super Object, ?, GDBTuple>)Collectors.toCollection((Supplier<R>)GDBTuple::new));
    }
    
    private GDBTuple a(final List<GdbParser.ResultContext> list) {
        return GDBTuple.of(this.b(list));
    }
    
    @Override
    public GDBResponse visitList(@NotNull final GdbParser.ListContext listContext) {
        return new GDBResponse.ResultRecord(GDBResponse.ResultRecord.Category.result, GDBResponse.ResultRecord.Type.list_value, this.a(listContext.result()));
    }
    
    @Override
    public GDBResponse visitResultRecord(@NotNull final GdbParser.ResultRecordContext resultRecordContext) {
        GDBTuple gdbTuple = this.b(resultRecordContext.result());
        if (resultRecordContext.listTailToken != null) {
            gdbTuple = GDBTuple.of(Couple.of((Object)"changelist", (Object)gdbTuple));
        }
        else {
            try {
                if (gdbTuple.size() != 1 || !(gdbTuple.get(0) instanceof String)) {
                    return new GDBResponse.ResultRecord(GDBResponse.ResultRecord.Category.result, GDBResponse.ResultRecord.Type.valueOf(resultRecordContext.resultClass.getText()), gdbTuple);
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            gdbTuple = GDBTuple.of(Couple.of((Object)"msg", gdbTuple.get(0)));
        }
        return new GDBResponse.ResultRecord(GDBResponse.ResultRecord.Category.result, GDBResponse.ResultRecord.Type.valueOf(resultRecordContext.resultClass.getText()), gdbTuple);
    }
    
    @Override
    public GDBResponse visitAsyncRecord(@NotNull final GdbParser.AsyncRecordContext asyncRecordContext) {
        String s;
        String s2;
        if (asyncRecordContext.quirk == null) {
            s = asyncRecordContext.prefix.getText();
            s2 = asyncRecordContext.asyncClass.getText();
        }
        else {
            final String text = asyncRecordContext.quirk.getText();
            s = text.substring(0, 1);
            s2 = a(text, 1);
        }
        return new GDBResponse.AsyncRecord(GDBResponse.AsyncRecord.Category.forPrefix(s), s2, this.b(asyncRecordContext.result()));
    }
    
    private static String a(final String s, final int n) {
        final Matcher matcher = GDBResponseVisitor.ID_PATTERN.matcher(s);
        try {
            if (!matcher.find(n)) {
                throw new RuntimeException("Unable to parse: '" + s + "'");
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return matcher.group();
    }
    
    @Override
    public GDBResponse visitStreamRecord(@NotNull final GdbParser.StreamRecordContext streamRecordContext) {
        return new GDBResponse.StreamRecord(GDBResponse.StreamRecord.Category.forPrefix(streamRecordContext.prefix.getText()), a(streamRecordContext.cString(), true));
    }
    
    @NotNull
    private static String a(@NotNull final GdbParser.CStringContext cStringContext, final boolean b) {
        final String text = cStringContext.getText();
        Label_0026: {
            try {
                if (GDBResponseVisitor.$assertionsDisabled) {
                    break Label_0026;
                }
                final String s = text;
                final int n = s.length();
                final int n2 = 2;
                if (n < n2) {
                    break Label_0026;
                }
                break Label_0026;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final String s = text;
                final int n = s.length();
                final int n2 = 2;
                if (n < n2) {
                    throw new AssertionError((Object)"at least the enclosing quotes must be there");
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        final String substring = text.substring(1, text.length() - 1);
        try {
            if (b) {
                return DebuggerDriver.unescapeString(substring);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return substring;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!GDBResponseVisitor.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
        ID_PATTERN = Pattern.compile("[\\w-]*");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
