// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.types.visitors;

import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.util.containers.HashSet;
import java.util.Set;
import com.jetbrains.cidr.lang.types.OCObjectType;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.types.OCType;

public class OCTypeEqualityAfterResolvingVisitor extends OCTypeEqualityVisitor
{
    private final boolean myAssumeNotResolvedTypesEquals;
    private final boolean myAssumeMagicTypesEquals;
    private final boolean myAssumeTollFreeTypesEquals;
    
    public OCTypeEqualityAfterResolvingVisitor(final OCType ocType, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, @Nullable final PsiFile psiFile) {
        this(ocType, b, b2, b3, b4, b5, new OCResolveContext((PsiElement)psiFile));
    }
    
    public OCTypeEqualityAfterResolvingVisitor(final OCType ocType, final boolean myAssumeNotResolvedTypesEquals, final boolean myAssumeMagicTypesEquals, final boolean b, final boolean b2, final boolean myAssumeTollFreeTypesEquals, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor", "<init>"));
        }
        super(ocType, true, b2, false, false, true, true, b, ocResolveContext);
        this.myAssumeNotResolvedTypesEquals = myAssumeNotResolvedTypesEquals;
        this.myAssumeMagicTypesEquals = myAssumeMagicTypesEquals;
        this.myAssumeTollFreeTypesEquals = myAssumeTollFreeTypesEquals;
    }
    
    public OCTypeEqualityAfterResolvingVisitor(final OCType ocType, final boolean b, @NotNull final OCResolveContext ocResolveContext) {
        if (ocResolveContext == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor", "<init>"));
        }
        this(ocType, b, false, false, false, true, ocResolveContext);
    }
    
    @Override
    public boolean equal(final OCType p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myAssumeMagicTypesEquals:Z
        //     4: ifeq            75
        //     7: aload_0        
        //     8: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    11: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    14: ifne            69
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_1        
        //    25: instanceof      Lcom/jetbrains/cidr/lang/types/OCMagicType;
        //    28: ifne            69
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    42: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //    45: ifne            69
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    54: athrow         
        //    55: aload_1        
        //    56: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //    59: ifeq            75
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    68: athrow         
        //    69: iconst_1       
        //    70: ireturn        
        //    71: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    74: athrow         
        //    75: aload_0        
        //    76: aload_0        
        //    77: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    80: aload_0        
        //    81: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    84: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    87: putfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    90: aload_1        
        //    91: aload_0        
        //    92: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myContext:Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;
        //    95: invokevirtual   com/jetbrains/cidr/lang/types/OCType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //    98: astore_3       
        //    99: iload_2        
        //   100: ifeq            130
        //   103: aload_0        
        //   104: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   107: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   110: aload_1        
        //   111: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isConst:()Z
        //   114: if_icmpeq       130
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   123: athrow         
        //   124: iconst_0       
        //   125: ireturn        
        //   126: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   129: athrow         
        //   130: iload_2        
        //   131: ifeq            161
        //   134: aload_0        
        //   135: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   138: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   141: aload_1        
        //   142: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isVolatile:()Z
        //   145: if_icmpeq       161
        //   148: goto            155
        //   151: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   154: athrow         
        //   155: iconst_0       
        //   156: ireturn        
        //   157: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: aload_0        
        //   162: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   165: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   168: ifne            185
        //   171: aload_3        
        //   172: instanceof      Lcom/jetbrains/cidr/lang/types/OCUnknownType;
        //   175: ifeq            191
        //   178: goto            185
        //   181: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   184: athrow         
        //   185: iconst_1       
        //   186: ireturn        
        //   187: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload_0        
        //   192: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myAssumeNotResolvedTypesEquals:Z
        //   195: ifeq            235
        //   198: aload_0        
        //   199: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   202: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   205: ifne            229
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   214: athrow         
        //   215: aload_3        
        //   216: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   219: ifeq            235
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   228: athrow         
        //   229: iconst_1       
        //   230: ireturn        
        //   231: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   234: athrow         
        //   235: aload_0        
        //   236: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   239: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   242: ifeq            265
        //   245: aload_3        
        //   246: instanceof      Lcom/jetbrains/cidr/lang/types/OCTypeParameterType;
        //   249: ifeq            265
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   258: athrow         
        //   259: iconst_1       
        //   260: ireturn        
        //   261: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   264: athrow         
        //   265: aload_0        
        //   266: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myAssumeTollFreeTypesEquals:Z
        //   269: ifeq            296
        //   272: aload_0        
        //   273: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //   276: aload_3        
        //   277: invokestatic    com/jetbrains/cidr/lang/types/OCTollFreeBridges.isCompatible:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/jetbrains/cidr/lang/types/OCType;)Z
        //   280: ifeq            296
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   289: athrow         
        //   290: iconst_1       
        //   291: ireturn        
        //   292: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   295: athrow         
        //   296: aload_3        
        //   297: aload_0        
        //   298: invokevirtual   com/jetbrains/cidr/lang/types/OCType.accept:(Lcom/jetbrains/cidr/lang/types/visitors/OCTypeVisitor;)Ljava/lang/Object;
        //   301: checkcast       Ljava/lang/Boolean;
        //   304: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   307: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  7      31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     48     51     55     Ljava/lang/IllegalArgumentException;
        //  38     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     71     71     75     Ljava/lang/IllegalArgumentException;
        //  99     117    120    124    Ljava/lang/IllegalArgumentException;
        //  103    126    126    130    Ljava/lang/IllegalArgumentException;
        //  130    148    151    155    Ljava/lang/IllegalArgumentException;
        //  134    157    157    161    Ljava/lang/IllegalArgumentException;
        //  161    178    181    185    Ljava/lang/IllegalArgumentException;
        //  171    187    187    191    Ljava/lang/IllegalArgumentException;
        //  191    208    211    215    Ljava/lang/IllegalArgumentException;
        //  198    222    225    229    Ljava/lang/IllegalArgumentException;
        //  215    231    231    235    Ljava/lang/IllegalArgumentException;
        //  235    252    255    259    Ljava/lang/IllegalArgumentException;
        //  245    261    261    265    Ljava/lang/IllegalArgumentException;
        //  265    283    286    290    Ljava/lang/IllegalArgumentException;
        //  272    292    292    296    Ljava/lang/IllegalArgumentException;
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
    
    @Override
    public Boolean visitObjectType(final OCObjectType p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: aload_0        
        //     2: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //     5: if_acmpne       17
        //     8: iconst_1       
        //     9: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    12: areturn        
        //    13: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload_0        
        //    18: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    21: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    24: ifne            36
        //    27: iconst_0       
        //    28: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    31: areturn        
        //    32: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    35: athrow         
        //    36: aload_0        
        //    37: getfield        com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.myType:Lcom/jetbrains/cidr/lang/types/OCType;
        //    40: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //    43: astore_2       
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //    48: ifeq            70
        //    51: aload_1        
        //    52: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAugmentedProtocols:()Ljava/util/List;
        //    55: invokeinterface java/util/List.isEmpty:()Z
        //    60: ifne            135
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: aload_2        
        //    71: instanceof      Lcom/jetbrains/cidr/lang/types/OCIdType;
        //    74: ifeq            103
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_2        
        //    85: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getAugmentedProtocols:()Ljava/util/List;
        //    88: invokeinterface java/util/List.isEmpty:()Z
        //    93: ifne            135
        //    96: goto            103
        //    99: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_1        
        //   104: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Ljava/util/Set;
        //   107: aload_2        
        //   108: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.a:(Lcom/jetbrains/cidr/lang/types/OCObjectType;)Ljava/util/Set;
        //   111: invokeinterface java/util/Set.equals:(Ljava/lang/Object;)Z
        //   116: ifne            135
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: iconst_0       
        //   127: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   130: areturn        
        //   131: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   134: athrow         
        //   135: aload_1        
        //   136: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   139: astore_3       
        //   140: aload_2        
        //   141: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getInterface:()Lcom/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol;
        //   144: astore          4
        //   146: aload_3        
        //   147: ifnull          162
        //   150: aload           4
        //   152: ifnonnull       188
        //   155: goto            162
        //   158: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   161: athrow         
        //   162: aload_3        
        //   163: aload           4
        //   165: if_acmpne       183
        //   168: goto            175
        //   171: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   174: athrow         
        //   175: iconst_1       
        //   176: goto            184
        //   179: invokestatic    com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   182: athrow         
        //   183: iconst_0       
        //   184: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   187: areturn        
        //   188: aload_3        
        //   189: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getName:()Ljava/lang/String;
        //   194: aload           4
        //   196: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCInterfaceSymbol.getName:()Ljava/lang/String;
        //   201: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/String;Ljava/lang/String;)Z
        //   204: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   207: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      13     13     17     Ljava/lang/IllegalArgumentException;
        //  17     32     32     36     Ljava/lang/IllegalArgumentException;
        //  44     63     66     70     Ljava/lang/IllegalArgumentException;
        //  51     77     80     84     Ljava/lang/IllegalArgumentException;
        //  70     96     99     103    Ljava/lang/IllegalArgumentException;
        //  84     119    122    126    Ljava/lang/IllegalArgumentException;
        //  103    131    131    135    Ljava/lang/IllegalArgumentException;
        //  146    155    158    162    Ljava/lang/IllegalArgumentException;
        //  150    168    171    175    Ljava/lang/IllegalArgumentException;
        //  162    179    179    183    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    
    private static Set<String> a(final OCObjectType ocObjectType) {
        final HashSet set = new HashSet();
        final Iterator<OCProtocolSymbol> iterator = ocObjectType.getAugmentedProtocols().iterator();
        while (iterator.hasNext()) {
            ((Set<String>)set).add(iterator.next().getName());
        }
        return (Set<String>)set;
    }
    
    @Override
    protected Boolean substitutionTypesEqual(@NotNull final OCType ocType, @NotNull final OCType ocType2, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "thatSust", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor", "substitutionTypesEqual"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocType2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "mySust", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor", "substitutionTypesEqual"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/types/visitors/OCTypeEqualityAfterResolvingVisitor", "substitutionTypesEqual"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return ocType.accept((OCTypeVisitor<Boolean>)new OCTypeEqualityAfterResolvingVisitor(ocType2, true, this.myAssumeMagicTypesEquals, this.myDontCheckCV, false, true, this.myContext));
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
