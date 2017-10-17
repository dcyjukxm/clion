// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.interpreter;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import java.util.HashMap;

public class CMakeScope
{
    private HashMap<String, String> myVars;
    
    public CMakeScope() {
        this.myVars = new HashMap<String, String>();
    }
    
    @Nullable
    public String getVariableValue(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope", "getVariableValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myVars.get(s);
    }
    
    public void setVariableValue(@NotNull final String s, @Nullable final String s2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope", "setVariableValue"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (StringUtil.isEmpty(s2)) {
                this.myVars.remove(s);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        this.myVars.put(s, s2);
    }
    
    @Nullable
    public String eval(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "str", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope", "eval"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0075: {
            try {
                if (s.contains("${")) {
                    break Label_0075;
                }
                final String s2 = s;
                final String s3 = "\\";
                final boolean b = s2.contains(s3);
                if (!b) {
                    return s;
                }
                break Label_0075;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final String s2 = s;
                final String s3 = "\\";
                final boolean b = s2.contains(s3);
                if (!b) {
                    return s;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final ArrayList<StringFragment> list = new ArrayList<StringFragment>();
        a(s, 0, list, 0);
        return evalFragments(list, this);
    }
    
    @Nullable
    public static String evalFragments(@NotNull final List<StringFragment> list, @NotNull final CMakeScope cMakeScope) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fragments", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope", "evalFragments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (cMakeScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope", "evalFragments"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final StringBuilder sb = new StringBuilder();
        for (final StringFragment stringFragment : list) {
            try {
                if (!stringFragment.eval(cMakeScope, sb)) {
                    return null;
                }
                continue;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return sb.toString();
    }
    
    private static int a(@NotNull final String p0, final int p1, @NotNull final List<StringFragment> p2, final int p3) {
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
        //    18: ldc             "str"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "parseFragments"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "fragments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "parseFragments"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_0        
        //    89: invokevirtual   java/lang/String.length:()I
        //    92: istore          4
        //    94: iload_1        
        //    95: iload           4
        //    97: if_icmplt       106
        //   100: iload_1        
        //   101: ireturn        
        //   102: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   105: athrow         
        //   106: iload_1        
        //   107: istore          5
        //   109: iload           5
        //   111: iload           4
        //   113: if_icmpge       432
        //   116: aload_0        
        //   117: iload           5
        //   119: invokevirtual   java/lang/String.charAt:(I)C
        //   122: istore          6
        //   124: iload           6
        //   126: bipush          92
        //   128: if_icmpne       226
        //   131: aload_0        
        //   132: iload_1        
        //   133: iload           5
        //   135: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   138: astore          7
        //   140: ldc             ""
        //   142: astore          8
        //   144: iinc            5, 1
        //   147: iload           5
        //   149: iload           4
        //   151: if_icmpge       186
        //   154: aload_0        
        //   155: iload           5
        //   157: invokevirtual   java/lang/String.charAt:(I)C
        //   160: istore          9
        //   162: iload           9
        //   164: bipush          59
        //   166: if_icmpne       176
        //   169: ldc             "\\;"
        //   171: astore          8
        //   173: goto            183
        //   176: iload           9
        //   178: invokestatic    java/lang/Character.toString:(C)Ljava/lang/String;
        //   181: astore          8
        //   183: iinc            5, 1
        //   186: aload_2        
        //   187: new             Lcom/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment;
        //   190: dup            
        //   191: new             Ljava/lang/StringBuilder;
        //   194: dup            
        //   195: invokespecial   java/lang/StringBuilder.<init>:()V
        //   198: aload           7
        //   200: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   203: aload           8
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   211: invokespecial   com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment.<init>:(Ljava/lang/String;)V
        //   214: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   219: pop            
        //   220: iload           5
        //   222: istore_1       
        //   223: goto            412
        //   226: iload           6
        //   228: bipush          125
        //   230: if_icmpne       251
        //   233: iload_3        
        //   234: ifle            251
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   243: athrow         
        //   244: goto            432
        //   247: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: iload           6
        //   253: bipush          36
        //   255: if_icmpne       412
        //   258: iload           5
        //   260: iconst_1       
        //   261: iadd           
        //   262: iload           4
        //   264: if_icmpge       412
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   273: athrow         
        //   274: aload_0        
        //   275: iload           5
        //   277: iconst_1       
        //   278: iadd           
        //   279: invokevirtual   java/lang/String.charAt:(I)C
        //   282: bipush          123
        //   284: if_icmpne       412
        //   287: goto            294
        //   290: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   293: athrow         
        //   294: iload_1        
        //   295: iload           5
        //   297: if_icmpge       335
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   306: athrow         
        //   307: aload_2        
        //   308: new             Lcom/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment;
        //   311: dup            
        //   312: aload_0        
        //   313: iload_1        
        //   314: iload           5
        //   316: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   319: invokespecial   com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment.<init>:(Ljava/lang/String;)V
        //   322: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   327: pop            
        //   328: goto            335
        //   331: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: new             Ljava/util/ArrayList;
        //   338: dup            
        //   339: invokespecial   java/util/ArrayList.<init>:()V
        //   342: astore          7
        //   344: iload           5
        //   346: iconst_2       
        //   347: iadd           
        //   348: istore          8
        //   350: aload_0        
        //   351: iload           8
        //   353: aload           7
        //   355: iload_3        
        //   356: iconst_1       
        //   357: iadd           
        //   358: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/String;ILjava/util/List;I)I
        //   361: istore          5
        //   363: iload           5
        //   365: iload           4
        //   367: if_icmpge       378
        //   370: iconst_1       
        //   371: goto            379
        //   374: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   377: athrow         
        //   378: iconst_0       
        //   379: istore          9
        //   381: aload_2        
        //   382: new             Lcom/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment;
        //   385: dup            
        //   386: aload_0        
        //   387: iload           8
        //   389: iload           5
        //   391: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   394: aload           7
        //   396: iload           9
        //   398: invokespecial   com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment.<init>:(Ljava/lang/String;Ljava/util/List;Z)V
        //   401: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   406: pop            
        //   407: iload           5
        //   409: iconst_1       
        //   410: iadd           
        //   411: istore_1       
        //   412: iload           5
        //   414: iload           4
        //   416: if_icmplt       426
        //   419: goto            432
        //   422: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   425: athrow         
        //   426: iinc            5, 1
        //   429: goto            109
        //   432: iload_1        
        //   433: iload           5
        //   435: if_icmpge       466
        //   438: aload_2        
        //   439: new             Lcom/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment;
        //   442: dup            
        //   443: aload_0        
        //   444: iload_1        
        //   445: iload           5
        //   447: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   450: invokespecial   com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment.<init>:(Ljava/lang/String;)V
        //   453: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   458: pop            
        //   459: goto            466
        //   462: invokestatic    com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   465: athrow         
        //   466: iload           5
        //   468: ireturn        
        //    Signature:
        //  (Ljava/lang/String;ILjava/util/List<Lcom/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$StringFragment;>;I)I
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  94     102    102    106    Ljava/lang/IllegalArgumentException;
        //  226    237    240    244    Ljava/lang/IllegalArgumentException;
        //  233    247    247    251    Ljava/lang/IllegalArgumentException;
        //  251    267    270    274    Ljava/lang/IllegalArgumentException;
        //  258    287    290    294    Ljava/lang/IllegalArgumentException;
        //  274    300    303    307    Ljava/lang/IllegalArgumentException;
        //  294    328    331    335    Ljava/lang/IllegalArgumentException;
        //  363    374    374    378    Ljava/lang/IllegalArgumentException;
        //  412    422    422    426    Ljava/lang/IllegalArgumentException;
        //  432    459    462    466    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0274:
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
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private abstract static class StringFragment
    {
        @NotNull
        private final String myString;
        
        public StringFragment(@NotNull final String myString) {
            if (myString == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$StringFragment", "<init>"));
            }
            this.myString = myString;
        }
        
        public abstract boolean eval(@NotNull final CMakeScope p0, @NotNull final StringBuilder p1);
        
        @NotNull
        protected String getFragmentString() {
            String myString;
            try {
                myString = this.myString;
                if (myString == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$StringFragment", "getFragmentString"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myString;
        }
        
        abstract boolean isValid();
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class PlainStringFragment extends StringFragment
    {
        public PlainStringFragment(final String s) {
            super(s);
        }
        
        @Override
        public boolean eval(@NotNull final CMakeScope cMakeScope, @NotNull final StringBuilder sb) {
            try {
                if (cMakeScope == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment", "eval"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (sb == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$PlainStringFragment", "eval"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            sb.append(this.getFragmentString());
            return true;
        }
        
        @Override
        boolean isValid() {
            return true;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    private static class VarStringFragment extends StringFragment
    {
        @NotNull
        private final List<StringFragment> myFragments;
        private final boolean myValid;
        
        public VarStringFragment(@NotNull final String s, @NotNull final List<StringFragment> myFragments, final boolean myValid) {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "string", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "<init>"));
            }
            if (myFragments == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fragments", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "<init>"));
            }
            super(s);
            this.myFragments = myFragments;
            this.myValid = myValid;
        }
        
        @Override
        public boolean eval(@NotNull final CMakeScope cMakeScope, @NotNull final StringBuilder sb) {
            try {
                if (cMakeScope == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "eval"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (sb == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/cpp/cmake/interpreter/CMakeScope$VarStringFragment", "eval"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                if (!this.isValid()) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            final String evalFragments = CMakeScope.evalFragments(this.myFragments, cMakeScope);
            try {
                if (evalFragments == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            final String variableValue = cMakeScope.getVariableValue(evalFragments);
            try {
                if (variableValue == null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            sb.append(variableValue);
            return true;
        }
        
        public boolean isValid() {
            return this.myValid;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
