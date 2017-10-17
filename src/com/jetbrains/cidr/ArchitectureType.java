// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum ArchitectureType
{
    I386("i386"), 
    X86_64("x86_64"), 
    ARM("arm"), 
    PPC("ppc"), 
    UNKNOWN("unknown");
    
    private final String myArchitectureId;
    
    private ArchitectureType(final String myArchitectureId) {
        this.myArchitectureId = myArchitectureId;
    }
    
    @NotNull
    public static ArchitectureType forArchitecture(@Nullable final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          259
        //     4: aload_0        
        //     5: getstatic       com/jetbrains/cidr/ArchitectureType.I386:Lcom/jetbrains/cidr/ArchitectureType;
        //     8: invokevirtual   com/jetbrains/cidr/ArchitectureType.getId:()Ljava/lang/String;
        //    11: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    14: ifeq            73
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    23: athrow         
        //    24: getstatic       com/jetbrains/cidr/ArchitectureType.I386:Lcom/jetbrains/cidr/ArchitectureType;
        //    27: dup            
        //    28: ifnonnull       72
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    37: athrow         
        //    38: new             Ljava/lang/IllegalStateException;
        //    41: dup            
        //    42: ldc             "@NotNull method %s.%s must not return null"
        //    44: ldc             2
        //    46: anewarray       Ljava/lang/Object;
        //    49: dup            
        //    50: ldc             0
        //    52: ldc             "com/jetbrains/cidr/ArchitectureType"
        //    54: aastore        
        //    55: dup            
        //    56: ldc             1
        //    58: ldc             "forArchitecture"
        //    60: aastore        
        //    61: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    64: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    67: athrow         
        //    68: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    71: athrow         
        //    72: areturn        
        //    73: aload_0        
        //    74: getstatic       com/jetbrains/cidr/ArchitectureType.X86_64:Lcom/jetbrains/cidr/ArchitectureType;
        //    77: invokevirtual   com/jetbrains/cidr/ArchitectureType.getId:()Ljava/lang/String;
        //    80: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    83: ifeq            135
        //    86: getstatic       com/jetbrains/cidr/ArchitectureType.X86_64:Lcom/jetbrains/cidr/ArchitectureType;
        //    89: dup            
        //    90: ifnonnull       134
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //    99: athrow         
        //   100: new             Ljava/lang/IllegalStateException;
        //   103: dup            
        //   104: ldc             "@NotNull method %s.%s must not return null"
        //   106: ldc             2
        //   108: anewarray       Ljava/lang/Object;
        //   111: dup            
        //   112: ldc             0
        //   114: ldc             "com/jetbrains/cidr/ArchitectureType"
        //   116: aastore        
        //   117: dup            
        //   118: ldc             1
        //   120: ldc             "forArchitecture"
        //   122: aastore        
        //   123: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   126: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   129: athrow         
        //   130: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   133: athrow         
        //   134: areturn        
        //   135: aload_0        
        //   136: getstatic       com/jetbrains/cidr/ArchitectureType.ARM:Lcom/jetbrains/cidr/ArchitectureType;
        //   139: invokevirtual   com/jetbrains/cidr/ArchitectureType.getId:()Ljava/lang/String;
        //   142: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   145: ifeq            197
        //   148: getstatic       com/jetbrains/cidr/ArchitectureType.ARM:Lcom/jetbrains/cidr/ArchitectureType;
        //   151: dup            
        //   152: ifnonnull       196
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   161: athrow         
        //   162: new             Ljava/lang/IllegalStateException;
        //   165: dup            
        //   166: ldc             "@NotNull method %s.%s must not return null"
        //   168: ldc             2
        //   170: anewarray       Ljava/lang/Object;
        //   173: dup            
        //   174: ldc             0
        //   176: ldc             "com/jetbrains/cidr/ArchitectureType"
        //   178: aastore        
        //   179: dup            
        //   180: ldc             1
        //   182: ldc             "forArchitecture"
        //   184: aastore        
        //   185: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   188: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   191: athrow         
        //   192: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   195: athrow         
        //   196: areturn        
        //   197: aload_0        
        //   198: getstatic       com/jetbrains/cidr/ArchitectureType.PPC:Lcom/jetbrains/cidr/ArchitectureType;
        //   201: invokevirtual   com/jetbrains/cidr/ArchitectureType.getId:()Ljava/lang/String;
        //   204: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   207: ifeq            259
        //   210: getstatic       com/jetbrains/cidr/ArchitectureType.PPC:Lcom/jetbrains/cidr/ArchitectureType;
        //   213: dup            
        //   214: ifnonnull       258
        //   217: goto            224
        //   220: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   223: athrow         
        //   224: new             Ljava/lang/IllegalStateException;
        //   227: dup            
        //   228: ldc             "@NotNull method %s.%s must not return null"
        //   230: ldc             2
        //   232: anewarray       Ljava/lang/Object;
        //   235: dup            
        //   236: ldc             0
        //   238: ldc             "com/jetbrains/cidr/ArchitectureType"
        //   240: aastore        
        //   241: dup            
        //   242: ldc             1
        //   244: ldc             "forArchitecture"
        //   246: aastore        
        //   247: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   250: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   253: athrow         
        //   254: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   257: athrow         
        //   258: areturn        
        //   259: getstatic       com/jetbrains/cidr/ArchitectureType.UNKNOWN:Lcom/jetbrains/cidr/ArchitectureType;
        //   262: dup            
        //   263: ifnonnull       300
        //   266: new             Ljava/lang/IllegalStateException;
        //   269: dup            
        //   270: ldc             "@NotNull method %s.%s must not return null"
        //   272: ldc             2
        //   274: anewarray       Ljava/lang/Object;
        //   277: dup            
        //   278: ldc             0
        //   280: ldc             "com/jetbrains/cidr/ArchitectureType"
        //   282: aastore        
        //   283: dup            
        //   284: ldc             1
        //   286: ldc             "forArchitecture"
        //   288: aastore        
        //   289: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   292: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   295: athrow         
        //   296: invokestatic    com/jetbrains/cidr/ArchitectureType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
        //   299: athrow         
        //   300: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      17     20     24     Ljava/lang/IllegalStateException;
        //  4      31     34     38     Ljava/lang/IllegalStateException;
        //  24     68     68     72     Ljava/lang/IllegalStateException;
        //  73     93     96     100    Ljava/lang/IllegalStateException;
        //  86     130    130    134    Ljava/lang/IllegalStateException;
        //  135    155    158    162    Ljava/lang/IllegalStateException;
        //  148    192    192    196    Ljava/lang/IllegalStateException;
        //  197    217    220    224    Ljava/lang/IllegalStateException;
        //  210    254    254    258    Ljava/lang/IllegalStateException;
        //  259    296    296    300    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
    public String getId() {
        String myArchitectureId;
        try {
            myArchitectureId = this.myArchitectureId;
            if (myArchitectureId == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/ArchitectureType", "getId"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return myArchitectureId;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
