// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.intellij.openapi.util.text.StringUtil;
import java.util.Arrays;
import org.antlr.v4.runtime.tree.ParseTree;
import com.jetbrains.cidr.execution.debugger.CidrDebuggerLog;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import java.util.BitSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.misc.Nullable;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;

public class GDBResponse
{
    public static GDBResponse parse(final String s) throws ResponseParseException {
        return new Parser(s).parse();
    }
    
    private static String a(final RecordCategory recordCategory, final RecordType recordType, final GDBTuple gdbTuple) {
        final StringBuilder sb = new StringBuilder(recordCategory.toString());
        try {
            sb.append(recordType);
            if (!gdbTuple.isEmpty()) {
                sb.append(",");
                a(sb, gdbTuple, false);
            }
        }
        catch (RuntimeException ex) {
            throw b(ex);
        }
        return sb.toString();
    }
    
    private static void a(final StringBuilder p0, final Object p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: instanceof      Lcom/intellij/openapi/util/Pair;
        //     4: ifeq            45
        //     7: aload_0        
        //     8: aload_1        
        //     9: checkcast       Lcom/intellij/openapi/util/Pair;
        //    12: getfield        com/intellij/openapi/util/Pair.first:Ljava/lang/Object;
        //    15: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //    18: pop            
        //    19: aload_0        
        //    20: ldc             "="
        //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    25: pop            
        //    26: aload_0        
        //    27: aload_1        
        //    28: checkcast       Lcom/intellij/openapi/util/Pair;
        //    31: getfield        com/intellij/openapi/util/Pair.second:Ljava/lang/Object;
        //    34: iconst_1       
        //    35: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.a:(Ljava/lang/StringBuilder;Ljava/lang/Object;Z)V
        //    38: goto            255
        //    41: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    44: athrow         
        //    45: aload_1        
        //    46: instanceof      Ljava/util/List;
        //    49: ifeq            192
        //    52: iload_2        
        //    53: ifeq            93
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    62: athrow         
        //    63: aload_0        
        //    64: aload_1        
        //    65: instanceof      Lcom/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple;
        //    68: ifeq            87
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    77: athrow         
        //    78: bipush          123
        //    80: goto            89
        //    83: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    86: athrow         
        //    87: bipush          91
        //    89: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //    92: pop            
        //    93: iconst_1       
        //    94: istore_3       
        //    95: aload_1        
        //    96: checkcast       Ljava/util/List;
        //    99: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   104: astore          4
        //   106: aload           4
        //   108: invokeinterface java/util/Iterator.hasNext:()Z
        //   113: ifeq            155
        //   116: aload           4
        //   118: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   123: astore          5
        //   125: iload_3        
        //   126: ifne            143
        //   129: aload_0        
        //   130: bipush          44
        //   132: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   135: pop            
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   142: athrow         
        //   143: iconst_0       
        //   144: istore_3       
        //   145: aload_0        
        //   146: aload           5
        //   148: iconst_1       
        //   149: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.a:(Ljava/lang/StringBuilder;Ljava/lang/Object;Z)V
        //   152: goto            106
        //   155: iload_2        
        //   156: ifeq            189
        //   159: aload_0        
        //   160: aload_1        
        //   161: instanceof      Lcom/jetbrains/cidr/execution/debugger/backend/gdb/GDBTuple;
        //   164: ifeq            183
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   173: athrow         
        //   174: bipush          125
        //   176: goto            185
        //   179: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   182: athrow         
        //   183: bipush          93
        //   185: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   188: pop            
        //   189: goto            255
        //   192: aload_1        
        //   193: instanceof      Ljava/lang/String;
        //   196: ifeq            228
        //   199: aload_0        
        //   200: ldc             "\""
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: aload_1        
        //   206: checkcast       Ljava/lang/String;
        //   209: invokestatic    com/intellij/openapi/util/text/StringUtil.escapeQuotes:(Ljava/lang/String;)Ljava/lang/String;
        //   212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   215: ldc             "\""
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: pop            
        //   221: goto            255
        //   224: invokestatic    com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   227: athrow         
        //   228: new             Ljava/lang/RuntimeException;
        //   231: dup            
        //   232: new             Ljava/lang/StringBuilder;
        //   235: dup            
        //   236: invokespecial   java/lang/StringBuilder.<init>:()V
        //   239: ldc             "unknown object: "
        //   241: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   244: aload_1        
        //   245: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   248: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   251: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   254: athrow         
        //   255: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  0      41     41     45     Ljava/lang/RuntimeException;
        //  45     56     59     63     Ljava/lang/RuntimeException;
        //  52     71     74     78     Ljava/lang/RuntimeException;
        //  63     83     83     87     Ljava/lang/RuntimeException;
        //  125    136    139    143    Ljava/lang/RuntimeException;
        //  155    167    170    174    Ljava/lang/RuntimeException;
        //  159    179    179    183    Ljava/lang/RuntimeException;
        //  192    224    224    228    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0063:
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
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
    
    private static class Parser
    {
        String text;
        
        private Parser(final String text) {
            this.text = text;
        }
        
        public GDBResponse parse() throws ResponseParseException {
            final GdbParser gdbParser = new GdbParser((TokenStream)new CommonTokenStream((TokenSource)new GdbLexer((CharStream)new ANTLRInputStream(this.text))));
            gdbParser.removeErrorListeners();
            gdbParser.addErrorListener((ANTLRErrorListener)new ANTLRErrorListener() {
                public void syntaxError(final Recognizer<?, ?> recognizer, @Nullable final Object o, final int n, final int n2, final String s, @Nullable final RecognitionException ex) {
                    throw new RuntimeException("Syntax error: " + s + " in \"" + Parser.this.text + "\" line " + n + " position " + n2);
                }
                
                public void reportAmbiguity(@NotNull final org.antlr.v4.runtime.Parser parser, @NotNull final DFA dfa, final int n, final int n2, final boolean b, @NotNull final BitSet set, @NotNull final ATNConfigSet set2) {
                    CidrDebuggerLog.LOG.warn("Ambiguity in \"" + Parser.this.text + "\" started " + n + " stopped " + n2);
                }
                
                public void reportAttemptingFullContext(@NotNull final org.antlr.v4.runtime.Parser parser, @NotNull final DFA dfa, final int n, final int n2, @Nullable final BitSet set, @NotNull final ATNConfigSet set2) {
                    CidrDebuggerLog.LOG.warn("Attempting full context in \"" + Parser.this.text + "\" started " + n + " stopped " + n2);
                }
                
                public void reportContextSensitivity(@NotNull final org.antlr.v4.runtime.Parser parser, @NotNull final DFA dfa, final int n, final int n2, final int n3, @NotNull final ATNConfigSet set) {
                    CidrDebuggerLog.LOG.error("Context sensitivity in \"" + Parser.this.text + "\" started " + n + " stopped " + n2);
                }
            });
            try {
                return (GDBResponse)new GDBResponseVisitor().visit((ParseTree)gdbParser.response());
            }
            catch (Throwable t) {
                throw new ResponseParseException(t.getMessage(), t);
            }
        }
    }
    
    public interface RecordCategory
    {
        @org.jetbrains.annotations.NotNull
        String getPrefix();
        
        @org.jetbrains.annotations.NotNull
        default <T extends RecordCategory> T forPrefix(@org.jetbrains.annotations.NotNull final T[] array, @org.jetbrains.annotations.NotNull final String s) {
            try {
                if (array == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "values", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$RecordCategory", "forPrefix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$RecordCategory", "forPrefix"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            for (final RecordCategory recordCategory : array) {
                Label_0170: {
                    RecordCategory recordCategory2 = null;
                    Label_0135: {
                        try {
                            if (!recordCategory.getPrefix().equals(s)) {
                                break Label_0170;
                            }
                            recordCategory2 = recordCategory;
                            if (recordCategory2 == null) {
                                break Label_0135;
                            }
                            return (T)recordCategory2;
                        }
                        catch (IllegalArgumentException ex3) {
                            throw a(ex3);
                        }
                        try {
                            recordCategory2 = recordCategory;
                            if (recordCategory2 == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$RecordCategory", "forPrefix"));
                            }
                        }
                        catch (IllegalArgumentException ex4) {
                            throw a(ex4);
                        }
                    }
                    return (T)recordCategory2;
                }
            }
            throw new IllegalArgumentException("Unknown RecordCategory prefix, expected one of " + Arrays.toString(array));
        }
        
        default IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public abstract static class Record<CATEGORY_TYPE extends RecordCategory, TYPE_TYPE extends RecordType> extends GDBResponse
    {
        @org.jetbrains.annotations.NotNull
        private final CATEGORY_TYPE myCategory;
        @org.jetbrains.annotations.NotNull
        private final TYPE_TYPE myType;
        @org.jetbrains.annotations.NotNull
        private final GDBTuple myResultList;
        
        public Record(@org.jetbrains.annotations.NotNull final CATEGORY_TYPE myCategory, @org.jetbrains.annotations.NotNull final TYPE_TYPE myType, @org.jetbrains.annotations.NotNull final GDBTuple myResultList) {
            if (myCategory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "category", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "<init>"));
            }
            if (myType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "<init>"));
            }
            if (myResultList == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultList", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "<init>"));
            }
            this.myCategory = myCategory;
            this.myType = myType;
            this.myResultList = myResultList;
        }
        
        @org.jetbrains.annotations.NotNull
        public CATEGORY_TYPE getCategory() {
            RecordCategory myCategory;
            try {
                myCategory = this.myCategory;
                if (myCategory == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "getCategory"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return (CATEGORY_TYPE)myCategory;
        }
        
        @org.jetbrains.annotations.NotNull
        public TYPE_TYPE getType() {
            RecordType myType;
            try {
                myType = this.myType;
                if (myType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "getType"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return (TYPE_TYPE)myType;
        }
        
        @org.jetbrains.annotations.NotNull
        public GDBTuple getResultList() {
            GDBTuple myResultList;
            try {
                myResultList = this.myResultList;
                if (myResultList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$Record", "getResultList"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myResultList;
        }
        
        @Override
        public String toString() {
            return a(this.getCategory(), this.getType(), this.myResultList);
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class ResultRecord extends Record<Category, Type>
    {
        public ResultRecord(@org.jetbrains.annotations.NotNull final Category category, @org.jetbrains.annotations.NotNull final Type type, @org.jetbrains.annotations.NotNull final GDBTuple gdbTuple) {
            if (category == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord", "<init>"));
            }
            if (type == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord", "<init>"));
            }
            if (gdbTuple == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultList", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord", "<init>"));
            }
            super(category, type, gdbTuple);
        }
        
        public enum Category implements RecordCategory
        {
            result("^");
            
            private final String myPrefix;
            
            private Category(final String myPrefix) {
                this.myPrefix = myPrefix;
            }
            
            @org.jetbrains.annotations.NotNull
            @Override
            public String getPrefix() {
                String myPrefix;
                try {
                    myPrefix = this.myPrefix;
                    if (myPrefix == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$ResultRecord$Category", "getPrefix"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return myPrefix;
            }
            
            @Override
            public String toString() {
                return this.myPrefix;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }
        
        public enum Type implements RecordType
        {
            done, 
            running, 
            connected, 
            error, 
            exit, 
            stepping, 
            continuing, 
            result, 
            tuple_value, 
            list_value, 
            str_value;
        }
    }
    
    public static class AsyncRecord extends Record<Category, Type>
    {
        public AsyncRecord(@org.jetbrains.annotations.NotNull final Category category, @org.jetbrains.annotations.NotNull final String s, @org.jetbrains.annotations.NotNull final GDBTuple gdbTuple) {
            if (category == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "category", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord", "<init>"));
            }
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord", "<init>"));
            }
            if (gdbTuple == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resultList", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord", "<init>"));
            }
            super(category, new Type(s), gdbTuple);
        }
        
        public enum Category implements RecordCategory
        {
            exec("*"), 
            notify("="), 
            status("+");
            
            private final String myPrefix;
            
            private Category(final String myPrefix) {
                this.myPrefix = myPrefix;
            }
            
            @org.jetbrains.annotations.NotNull
            @Override
            public String getPrefix() {
                String myPrefix;
                try {
                    myPrefix = this.myPrefix;
                    if (myPrefix == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Category", "getPrefix"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return myPrefix;
            }
            
            @Override
            public String toString() {
                return this.myPrefix;
            }
            
            static Category forPrefix(@org.jetbrains.annotations.NotNull final String s) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Category", "forPrefix"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return RecordCategory.forPrefix(values(), s);
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }
        
        public static class Type implements RecordType
        {
            @org.jetbrains.annotations.NotNull
            private final String myValue;
            
            public Type(@org.jetbrains.annotations.NotNull final String myValue) {
                if (myValue == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "value", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Type", "<init>"));
                }
                this.myValue = myValue;
            }
            
            @org.jetbrains.annotations.NotNull
            public String getValue() {
                String myValue;
                try {
                    myValue = this.myValue;
                    if (myValue == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$AsyncRecord$Type", "getValue"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return myValue;
            }
            
            @Override
            public String toString() {
                return this.myValue;
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
                        final Type type = this;
                        final Class<? extends Type> clazz = type.getClass();
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
                        final Type type = this;
                        final Class<? extends Type> clazz = type.getClass();
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
                final Type type2 = (Type)o;
                try {
                    if (!this.myValue.equals(type2.myValue)) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                return true;
            }
            
            @Override
            public int hashCode() {
                return this.myValue.hashCode();
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        }
    }
    
    public static class StreamRecord extends GDBResponse
    {
        private final Category myCategory;
        private final String myText;
        
        public StreamRecord(@org.jetbrains.annotations.NotNull final Category myCategory, @org.jetbrains.annotations.NotNull final String myText) {
            if (myCategory == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "category", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "<init>"));
            }
            if (myText == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "<init>"));
            }
            this.myCategory = myCategory;
            this.myText = myText;
        }
        
        @org.jetbrains.annotations.NotNull
        public Category getCategory() {
            Category myCategory;
            try {
                myCategory = this.myCategory;
                if (myCategory == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "getCategory"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myCategory;
        }
        
        @org.jetbrains.annotations.NotNull
        public String getText() {
            String myText;
            try {
                myText = this.myText;
                if (myText == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord", "getText"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myText;
        }
        
        @Override
        public String toString() {
            return this.myCategory.myPrefix + "\"" + StringUtil.escapeStringCharacters(this.myText) + "\"";
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
        
        public enum Category implements RecordCategory
        {
            console("~"), 
            target("@"), 
            log("&");
            
            private final String myPrefix;
            
            private Category(final String myPrefix) {
                this.myPrefix = myPrefix;
            }
            
            @org.jetbrains.annotations.NotNull
            @Override
            public String getPrefix() {
                String myPrefix;
                try {
                    myPrefix = this.myPrefix;
                    if (myPrefix == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord$Category", "getPrefix"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return myPrefix;
            }
            
            @Override
            public String toString() {
                return this.myPrefix;
            }
            
            static Category forPrefix(@org.jetbrains.annotations.NotNull final String s) {
                try {
                    if (s == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "prefix", "com/jetbrains/cidr/execution/debugger/backend/gdb/GDBResponse$StreamRecord$Category", "forPrefix"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return RecordCategory.forPrefix(values(), s);
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }
    }
    
    public static class ResponseParseException extends Exception
    {
        public ResponseParseException(final String s, final Throwable t) {
            super(s, t);
        }
    }
    
    public interface RecordType
    {
    }
}
