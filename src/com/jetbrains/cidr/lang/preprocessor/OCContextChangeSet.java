// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.preprocessor;

import com.intellij.util.text.CharSequenceSubSequence;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import gnu.trove.TIntObjectHashMap;
import org.jetbrains.annotations.NotNull;

public class OCContextChangeSet
{
    @NotNull
    private final String myText;
    @NotNull
    private TIntObjectHashMap<OCContextChange> myChangeMap;
    private int myLastOffset;
    private boolean myIsUpdated;
    private boolean myUsingOriginalMap;
    
    private OCContextChangeSet(@NotNull final String myText, @NotNull final TIntObjectHashMap<OCContextChange> myChangeMap, final boolean myIsUpdated, final int myLastOffset, final boolean myUsingOriginalMap) {
        if (myText == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet", "<init>"));
        }
        if (myChangeMap == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "changeMap", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet", "<init>"));
        }
        this.myText = myText;
        this.myIsUpdated = myIsUpdated;
        this.myChangeMap = myChangeMap;
        this.myLastOffset = myLastOffset;
        this.myUsingOriginalMap = myUsingOriginalMap;
    }
    
    public boolean isUpdated() {
        return this.myIsUpdated;
    }
    
    @NotNull
    public String getText() {
        String myText;
        try {
            myText = this.myText;
            if (myText == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet", "getText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myText;
    }
    
    @Nullable
    public OCContextChange getChange(final int n) {
        try {
            if (n > this.myLastOffset) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (OCContextChange)this.myChangeMap.get(n);
    }
    
    public void setChange(final int myLastOffset, @NotNull final OCContextChange ocContextChange) {
        try {
            if (ocContextChange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "change", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet", "setChange"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myUsingOriginalMap) {
                this.myUsingOriginalMap = false;
                this.myChangeMap = (TIntObjectHashMap<OCContextChange>)this.myChangeMap.clone();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (myLastOffset > this.myLastOffset) {
                this.myLastOffset = myLastOffset;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        this.myChangeMap.put(myLastOffset, (Object)ocContextChange);
        this.myIsUpdated = true;
    }
    
    @NotNull
    public static OCContextChangeSet reuse(@NotNull final String p0, @Nullable final OCContextChangeSet p1, final int p2, final int p3) {
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
        //    18: ldc             "newText"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "reuse"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_1        
        //    45: ifnonnull       112
        //    48: new             Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;
        //    51: dup            
        //    52: aload_0        
        //    53: new             Lgnu/trove/TIntObjectHashMap;
        //    56: dup            
        //    57: invokespecial   gnu/trove/TIntObjectHashMap.<init>:()V
        //    60: iconst_1       
        //    61: iconst_0       
        //    62: iconst_0       
        //    63: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.<init>:(Ljava/lang/String;Lgnu/trove/TIntObjectHashMap;ZIZ)V
        //    66: dup            
        //    67: ifnonnull       111
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    76: athrow         
        //    77: new             Ljava/lang/IllegalStateException;
        //    80: dup            
        //    81: ldc             "@NotNull method %s.%s must not return null"
        //    83: ldc             2
        //    85: anewarray       Ljava/lang/Object;
        //    88: dup            
        //    89: ldc             0
        //    91: ldc             "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet"
        //    93: aastore        
        //    94: dup            
        //    95: ldc             1
        //    97: ldc             "reuse"
        //    99: aastore        
        //   100: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   103: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   106: athrow         
        //   107: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: areturn        
        //   112: iload_3        
        //   113: ifge            121
        //   116: aload_0        
        //   117: invokevirtual   java/lang/String.length:()I
        //   120: istore_3       
        //   121: getstatic       com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.$assertionsDisabled:Z
        //   124: ifne            150
        //   127: iload_2        
        //   128: ifge            150
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   137: athrow         
        //   138: new             Ljava/lang/AssertionError;
        //   141: dup            
        //   142: invokespecial   java/lang/AssertionError.<init>:()V
        //   145: athrow         
        //   146: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   149: athrow         
        //   150: getstatic       com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.$assertionsDisabled:Z
        //   153: ifne            195
        //   156: iload_3        
        //   157: iload_2        
        //   158: if_icmplt       183
        //   161: goto            168
        //   164: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   167: athrow         
        //   168: iload_3        
        //   169: aload_0        
        //   170: invokevirtual   java/lang/String.length:()I
        //   173: if_icmple       195
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: new             Ljava/lang/AssertionError;
        //   186: dup            
        //   187: invokespecial   java/lang/AssertionError.<init>:()V
        //   190: athrow         
        //   191: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   194: athrow         
        //   195: aload_1        
        //   196: getfield        com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.myLastOffset:I
        //   199: istore          4
        //   201: aload_1        
        //   202: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.getText:()Ljava/lang/String;
        //   205: astore          5
        //   207: aload           5
        //   209: invokevirtual   java/lang/String.length:()I
        //   212: iload_3        
        //   213: iload           4
        //   215: iconst_1       
        //   216: iadd           
        //   217: invokestatic    java/lang/Math.min:(II)I
        //   220: invokestatic    java/lang/Math.min:(II)I
        //   223: istore_3       
        //   224: iload_3        
        //   225: iload_2        
        //   226: aload           5
        //   228: invokevirtual   java/lang/String.length:()I
        //   231: invokestatic    java/lang/Math.min:(II)I
        //   234: aload_0        
        //   235: invokevirtual   java/lang/String.length:()I
        //   238: invokestatic    java/lang/Math.min:(II)I
        //   241: invokestatic    java/lang/Math.min:(II)I
        //   244: istore_2       
        //   245: iload_2        
        //   246: aload           5
        //   248: iload_2        
        //   249: iload_3        
        //   250: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
        //   253: aload_0        
        //   254: iload_2        
        //   255: aload_0        
        //   256: invokevirtual   java/lang/String.length:()I
        //   259: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/CharSequence;II)Ljava/lang/CharSequence;
        //   262: invokestatic    com/intellij/openapi/util/text/StringUtil.commonPrefixLength:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)I
        //   265: iadd           
        //   266: istore          6
        //   268: iload_3        
        //   269: iload           6
        //   271: if_icmpne       336
        //   274: new             Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;
        //   277: dup            
        //   278: aload_0        
        //   279: aload_1        
        //   280: getfield        com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.myChangeMap:Lgnu/trove/TIntObjectHashMap;
        //   283: iconst_0       
        //   284: iload           4
        //   286: iconst_1       
        //   287: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.<init>:(Ljava/lang/String;Lgnu/trove/TIntObjectHashMap;ZIZ)V
        //   290: dup            
        //   291: ifnonnull       335
        //   294: goto            301
        //   297: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: new             Ljava/lang/IllegalStateException;
        //   304: dup            
        //   305: ldc             "@NotNull method %s.%s must not return null"
        //   307: ldc             2
        //   309: anewarray       Ljava/lang/Object;
        //   312: dup            
        //   313: ldc             0
        //   315: ldc             "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet"
        //   317: aastore        
        //   318: dup            
        //   319: ldc             1
        //   321: ldc             "reuse"
        //   323: aastore        
        //   324: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   327: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   330: athrow         
        //   331: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: areturn        
        //   336: iconst_1       
        //   337: newarray        I
        //   339: dup            
        //   340: iconst_0       
        //   341: iconst_0       
        //   342: iastore        
        //   343: astore          7
        //   345: aload_1        
        //   346: getfield        com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.myChangeMap:Lgnu/trove/TIntObjectHashMap;
        //   349: invokevirtual   gnu/trove/TIntObjectHashMap.clone:()Lgnu/trove/TIntObjectHashMap;
        //   352: astore          8
        //   354: aload           8
        //   356: new             Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet$1;
        //   359: dup            
        //   360: iload           6
        //   362: aload           7
        //   364: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet$1.<init>:(I[I)V
        //   367: invokevirtual   gnu/trove/TIntObjectHashMap.retainEntries:(Lgnu/trove/TIntObjectProcedure;)Z
        //   370: pop            
        //   371: aload           7
        //   373: iconst_0       
        //   374: iaload         
        //   375: istore          9
        //   377: new             Lcom/jetbrains/cidr/lang/preprocessor/OCContextChangeSet;
        //   380: dup            
        //   381: aload_0        
        //   382: aload           8
        //   384: iconst_1       
        //   385: iload           9
        //   387: iconst_0       
        //   388: invokespecial   com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.<init>:(Ljava/lang/String;Lgnu/trove/TIntObjectHashMap;ZIZ)V
        //   391: dup            
        //   392: ifnonnull       429
        //   395: new             Ljava/lang/IllegalStateException;
        //   398: dup            
        //   399: ldc             "@NotNull method %s.%s must not return null"
        //   401: ldc             2
        //   403: anewarray       Ljava/lang/Object;
        //   406: dup            
        //   407: ldc             0
        //   409: ldc             "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet"
        //   411: aastore        
        //   412: dup            
        //   413: ldc             1
        //   415: ldc             "reuse"
        //   417: aastore        
        //   418: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   421: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   424: athrow         
        //   425: invokestatic    com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   428: athrow         
        //   429: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     70     73     77     Ljava/lang/IllegalArgumentException;
        //  48     107    107    111    Ljava/lang/IllegalArgumentException;
        //  121    131    134    138    Ljava/lang/IllegalArgumentException;
        //  127    146    146    150    Ljava/lang/IllegalArgumentException;
        //  150    161    164    168    Ljava/lang/IllegalArgumentException;
        //  156    176    179    183    Ljava/lang/IllegalArgumentException;
        //  168    191    191    195    Ljava/lang/IllegalArgumentException;
        //  268    294    297    301    Ljava/lang/IllegalArgumentException;
        //  274    331    331    335    Ljava/lang/IllegalArgumentException;
        //  377    425    425    429    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0168:
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
    
    @NotNull
    public List<OCContextChange> getChanges() {
        final ArrayList<Object> list = new ArrayList<Object>();
        ArrayList<Object> list2;
        try {
            this.myChangeMap.forEachValue(ocContextChange -> {
                list.add(ocContextChange);
                return true;
            });
            Collections.sort(list, (ocContextChange, ocContextChange2) -> ocContextChange.getOffset() - ocContextChange2.getOffset());
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/preprocessor/OCContextChangeSet", "getChanges"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (List<OCContextChange>)list2;
    }
    
    private static CharSequence a(final CharSequence charSequence, final int n, final int n2) {
        return (CharSequence)new CharSequenceSubSequence(charSequence, n, n2);
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCContextChangeSet.class.desiredAssertionStatus()) {
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
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
