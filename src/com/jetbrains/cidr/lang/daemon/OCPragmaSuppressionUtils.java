// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;
import com.intellij.util.containers.MultiMap;
import com.intellij.psi.util.PsiModificationTracker;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.util.Function;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.UserDataHolder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Contract;
import java.util.Iterator;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCPragma;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.util.CachedValue;
import com.intellij.openapi.util.NotNullLazyKey;

public class OCPragmaSuppressionUtils
{
    public static final String CLANG = "clang";
    public static final String CIDR_IDE = "ide";
    private static NotNullLazyKey<CachedValue<ScopesStack>, OCFile> CACHE;
    
    @Contract("_, _, null -> null")
    public static OCPragma.Mode getDiagnosticMode(final OCFile ocFile, final int n, @Nullable final String s) {
        try {
            if (s == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Scope scope = findScope(ocFile, n);
        OCPragma.Mode mode = null;
        for (final Pair pair : scope.settings.get((Object)s)) {
            try {
                if (((TextRange)pair.first).getEndOffset() > n) {
                    break;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            mode = (OCPragma.Mode)pair.second;
        }
        return mode;
    }
    
    @NotNull
    private static Scope a(@NotNull final Scope p0, final int p1) {
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
        //    18: ldc             "scope"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findScope"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$300:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Ljava/util/List;
        //    48: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    53: astore_2       
        //    54: aload_2        
        //    55: invokeinterface java/util/Iterator.hasNext:()Z
        //    60: ifeq            170
        //    63: aload_2        
        //    64: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    69: checkcast       Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;
        //    72: astore_3       
        //    73: aload_3        
        //    74: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$200:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //    77: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    80: iload_1        
        //    81: if_icmpgt       167
        //    84: aload_3        
        //    85: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$400:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //    88: ifnull          167
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    97: athrow         
        //    98: iload_1        
        //    99: aload_3        
        //   100: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$400:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   103: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   106: if_icmpge       167
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   115: athrow         
        //   116: aload_3        
        //   117: iload_1        
        //   118: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;I)Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;
        //   121: dup            
        //   122: ifnonnull       166
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: new             Ljava/lang/IllegalStateException;
        //   135: dup            
        //   136: ldc             "@NotNull method %s.%s must not return null"
        //   138: ldc             2
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: ldc             0
        //   146: ldc             "com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils"
        //   148: aastore        
        //   149: dup            
        //   150: ldc             1
        //   152: ldc             "findScope"
        //   154: aastore        
        //   155: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   158: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   161: athrow         
        //   162: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: areturn        
        //   167: goto            54
        //   170: aload_0        
        //   171: dup            
        //   172: ifnonnull       209
        //   175: new             Ljava/lang/IllegalStateException;
        //   178: dup            
        //   179: ldc             "@NotNull method %s.%s must not return null"
        //   181: ldc             2
        //   183: anewarray       Ljava/lang/Object;
        //   186: dup            
        //   187: ldc             0
        //   189: ldc             "com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils"
        //   191: aastore        
        //   192: dup            
        //   193: ldc             1
        //   195: ldc             "findScope"
        //   197: aastore        
        //   198: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   201: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   204: athrow         
        //   205: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   208: athrow         
        //   209: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  73     91     94     98     Ljava/lang/IllegalArgumentException;
        //  84     109    112    116    Ljava/lang/IllegalArgumentException;
        //  98     125    128    132    Ljava/lang/IllegalArgumentException;
        //  116    162    162    166    Ljava/lang/IllegalArgumentException;
        //  170    205    205    209    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0098:
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
    public static Scope findScope(final OCFile ocFile, final int n) {
        Scope a;
        try {
            a = a(((ScopesStack)((CachedValue)OCPragmaSuppressionUtils.CACHE.getValue((UserDataHolder)ocFile)).getValue()).myRootScope, n);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils", "findScope"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return a;
    }
    
    @Nullable
    public static Scope findExactScope(final OCFile p0, final int p1, final int p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1        
        //     1: ifle            20
        //     4: aload_0        
        //     5: iload_1        
        //     6: iconst_1       
        //     7: isub           
        //     8: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    13: goto            21
        //    16: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    19: athrow         
        //    20: aconst_null    
        //    21: astore_3       
        //    22: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.PUSH:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //    25: aload_3        
        //    26: invokedynamic   fun:()Lcom/intellij/util/Function$Mono;
        //    31: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.getOffset:(Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Function$Mono;)I
        //    34: istore          4
        //    36: iload_2        
        //    37: aload_0        
        //    38: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getTextLength:()I
        //    43: if_icmpge       60
        //    46: aload_0        
        //    47: iload_2        
        //    48: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    53: goto            61
        //    56: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    59: athrow         
        //    60: aconst_null    
        //    61: astore_3       
        //    62: getstatic       com/jetbrains/cidr/lang/psi/OCPragma$Mode.POP:Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;
        //    65: aload_3        
        //    66: invokedynamic   fun:()Lcom/intellij/util/Function$Mono;
        //    71: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.getOffset:(Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Function$Mono;)I
        //    74: istore          5
        //    76: iload           4
        //    78: iconst_m1      
        //    79: if_icmpeq       176
        //    82: iload           5
        //    84: iconst_m1      
        //    85: if_icmpeq       176
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    94: athrow         
        //    95: aload_0        
        //    96: iload           4
        //    98: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.findScope:(Lcom/jetbrains/cidr/lang/psi/OCFile;I)Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;
        //   101: astore          6
        //   103: aload           6
        //   105: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$200:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   108: ifnull          173
        //   111: aload           6
        //   113: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$400:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   116: ifnull          173
        //   119: goto            126
        //   122: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   125: athrow         
        //   126: aload           6
        //   128: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$200:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   131: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   134: iload           4
        //   136: if_icmpne       173
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   145: athrow         
        //   146: aload           6
        //   148: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$400:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   151: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   154: iload           5
        //   156: if_icmpne       173
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: aload           6
        //   168: areturn        
        //   169: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: goto            258
        //   176: aload_0        
        //   177: aload_0        
        //   178: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getTextLength:()I
        //   183: iconst_1       
        //   184: isub           
        //   185: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.findScope:(Lcom/jetbrains/cidr/lang/psi/OCFile;I)Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;
        //   188: astore          6
        //   190: aload           6
        //   192: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$200:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   195: ifnull          258
        //   198: aload           6
        //   200: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$400:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   203: ifnull          258
        //   206: goto            213
        //   209: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   212: athrow         
        //   213: aload           6
        //   215: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$200:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   218: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   221: iload_1        
        //   222: if_icmpne       258
        //   225: goto            232
        //   228: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   231: athrow         
        //   232: aload           6
        //   234: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope.access$400:(Lcom/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils$Scope;)Lcom/intellij/openapi/util/TextRange;
        //   237: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   240: iload_2        
        //   241: if_icmpne       258
        //   244: goto            251
        //   247: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   250: athrow         
        //   251: aload           6
        //   253: areturn        
        //   254: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   257: athrow         
        //   258: aconst_null    
        //   259: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      16     16     20     Ljava/lang/IllegalArgumentException;
        //  36     56     56     60     Ljava/lang/IllegalArgumentException;
        //  76     88     91     95     Ljava/lang/IllegalArgumentException;
        //  103    119    122    126    Ljava/lang/IllegalArgumentException;
        //  111    139    142    146    Ljava/lang/IllegalArgumentException;
        //  126    159    162    166    Ljava/lang/IllegalArgumentException;
        //  146    169    169    173    Ljava/lang/IllegalArgumentException;
        //  190    206    209    213    Ljava/lang/IllegalArgumentException;
        //  198    225    228    232    Ljava/lang/IllegalArgumentException;
        //  213    244    247    251    Ljava/lang/IllegalArgumentException;
        //  232    254    254    258    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0126:
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
    
    protected static int getOffset(final OCPragma.Mode p0, final PsiElement p1, final Function.Mono<PsiElement> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_m1      
        //     1: istore_3       
        //     2: iload_3        
        //     3: ifge            70
        //     6: aload_1        
        //     7: ifnull          70
        //    10: goto            17
        //    13: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    16: athrow         
        //    17: aload_1        
        //    18: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isWhitespace:(Lcom/intellij/psi/PsiElement;)Z
        //    21: ifne            50
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: aload_1        
        //    32: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    37: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPragma;
        //    40: ifeq            70
        //    43: goto            50
        //    46: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    49: athrow         
        //    50: aload_2        
        //    51: aload_1        
        //    52: invokeinterface com/intellij/util/Function$Mono.fun:(Ljava/lang/Object;)Ljava/lang/Object;
        //    57: checkcast       Lcom/intellij/psi/PsiElement;
        //    60: astore_1       
        //    61: aload_1        
        //    62: aload_0        
        //    63: invokestatic    com/jetbrains/cidr/lang/daemon/OCPragmaSuppressionUtils.a:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;)I
        //    66: istore_3       
        //    67: goto            2
        //    70: iload_3        
        //    71: ireturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/psi/OCPragma$Mode;Lcom/intellij/psi/PsiElement;Lcom/intellij/util/Function$Mono<Lcom/intellij/psi/PsiElement;>;)I
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  2      10     13     17     Ljava/lang/IllegalArgumentException;
        //  6      24     27     31     Ljava/lang/IllegalArgumentException;
        //  17     43     46     50     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    
    private static int a(@Nullable final PsiElement psiElement, final OCPragma.Mode mode) {
        try {
            if (psiElement == null || !(psiElement.getParent() instanceof OCPragma)) {
                return -1;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCPragma ocPragma = (OCPragma)psiElement.getParent();
        final Pair<OCPragma.Mode, String> pragma = ocPragma.parsePragma();
        Label_0059: {
            try {
                if (pragma == null) {
                    return -1;
                }
                final Pair<OCPragma.Mode, String> pair = pragma;
                final Object o = pair.first;
                final OCPragma.Mode mode2 = mode;
                if (o == mode2) {
                    break Label_0059;
                }
                return -1;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final Pair<OCPragma.Mode, String> pair = pragma;
                final Object o = pair.first;
                final OCPragma.Mode mode2 = mode;
                if (o == mode2) {
                    return OCElementUtil.getRangeWithMacros((PsiElement)ocPragma).getStartOffset();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return -1;
    }
    
    static {
        OCPragmaSuppressionUtils.CACHE = (NotNullLazyKey<CachedValue<ScopesStack>, OCFile>)NotNullLazyKey.create("OCPragmaSuppressionUtils", ocFile -> CachedValuesManager.getManager(ocFile.getProject()).createCachedValue(() -> {
            final ScopesStack scopesStack = new ScopesStack();
            ocFile.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                final /* synthetic */ ScopesStack val$stack;
                final /* synthetic */ Document val$document = PsiDocumentManager.getInstance(ocFile.getProject()).getDocument((PsiFile)ocFile);
                
                @Override
                public void visitPragma(final OCPragma ocPragma) {
                    scopesStack.a(ocPragma, this.val$document);
                }
            });
            try {
                while (!scopesStack.myStack.isEmpty()) {
                    scopesStack.a(new TextRange(ocFile.getTextLength(), ocFile.getTextLength()));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return CachedValueProvider.Result.create((Object)scopesStack, new Object[] { PsiModificationTracker.MODIFICATION_COUNT });
        }, false));
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Scope
    {
        private MultiMap<String, Pair<TextRange, OCPragma.Mode>> settings;
        private int ownSettingsCnt;
        private TextRange push;
        private TextRange pop;
        private List<Scope> children;
        
        public Scope() {
            this.settings = (MultiMap<String, Pair<TextRange, OCPragma.Mode>>)new MultiMap();
            this.children = new ArrayList<Scope>();
        }
        
        public boolean isTopLevel() {
            return this.pop == null;
        }
        
        public TextRange getPush() {
            return this.push;
        }
        
        public TextRange getPop() {
            return this.pop;
        }
        
        public MultiMap<String, Pair<TextRange, OCPragma.Mode>> getSettings() {
            return this.settings;
        }
        
        public int getOwnSettingsCnt() {
            return this.ownSettingsCnt;
        }
    }
    
    private static class ScopesStack
    {
        private Stack<Scope> myStack;
        private Scope myCurScope;
        private Scope myRootScope;
        
        private ScopesStack() {
            this.myStack = new Stack<Scope>();
            this.myRootScope = new Scope();
            this.myCurScope = this.myRootScope;
        }
        
        private void a(final OCPragma ocPragma, final Document document) {
            final Pair<OCPragma.Mode, String> pragma = ocPragma.parsePragma();
            if (pragma != null) {
                TextRange rangeWithMacros = OCElementUtil.getRangeWithMacros((PsiElement)ocPragma);
                if (document != null) {
                    final int lineNumber = document.getLineNumber(rangeWithMacros.getEndOffset());
                    if (lineNumber < document.getLineCount() - 1) {
                        rangeWithMacros = new TextRange(rangeWithMacros.getStartOffset(), document.getLineStartOffset(lineNumber + 1));
                    }
                }
                switch ((OCPragma.Mode)pragma.first) {
                    case PUSH: {
                        this.b(rangeWithMacros);
                        break;
                    }
                    case POP: {
                        this.a(rangeWithMacros);
                        break;
                    }
                    case IGNORE:
                    case WARNING:
                    case ERROR:
                    case FATAL: {
                        this.myCurScope.settings.putValue(pragma.getSecond(), (Object)Pair.create((Object)rangeWithMacros, pragma.getFirst()));
                        this.myCurScope.ownSettingsCnt++;
                        break;
                    }
                }
            }
        }
        
        private void b(final TextRange textRange) {
            final Scope myCurScope = new Scope();
            myCurScope.settings = (MultiMap<String, Pair<TextRange, OCPragma.Mode>>)new MultiMap();
            myCurScope.settings.putAllValues(this.myCurScope.settings);
            myCurScope.push = textRange;
            this.myStack.push(this.myCurScope);
            this.myCurScope.children.add(myCurScope);
            this.myCurScope = myCurScope;
        }
        
        private void a(final TextRange textRange) {
            if (!this.myStack.isEmpty()) {
                this.myCurScope.pop = textRange;
                this.myCurScope = this.myStack.pop();
            }
        }
    }
}
