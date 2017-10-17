// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.v2;

import com.jetbrains.cidr.lang.types.visitors.OCTypeEqualityVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiElement;
import java.util.Collection;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.resolve.OCArgumentsList;
import java.util.Collections;
import com.jetbrains.cidr.lang.types.OCVariadicType;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.OCIcons;
import com.jetbrains.cidr.lang.OCTestFrameworks;
import javax.swing.Icon;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.openapi.util.Ref;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.symbols.OCSymbolAttribute;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.visitors.OCTypeSubstitution;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import java.util.List;
import com.jetbrains.cidr.lang.types.OCFunctionType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbolImpl;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;

public class TemplatesUtils
{
    public static OCTemplateSymbol getMoreSpecializedTemplate(final OCFunctionSymbol ocFunctionSymbol, final OCFunctionSymbol ocFunctionSymbol2, final int n, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils", "getMoreSpecializedTemplate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final boolean atLeastAsSpecializedAs = isAtLeastAsSpecializedAs(ocFunctionSymbol, ocFunctionSymbol2, n, ocResolveContext);
        final boolean atLeastAsSpecializedAs2 = isAtLeastAsSpecializedAs(ocFunctionSymbol2, ocFunctionSymbol, n, ocResolveContext);
        Label_0108: {
            Label_0091: {
                Label_0081: {
                    try {
                        if (atLeastAsSpecializedAs == atLeastAsSpecializedAs2) {
                            break Label_0091;
                        }
                        final boolean b = atLeastAsSpecializedAs;
                        if (b) {
                            break Label_0081;
                        }
                        return ocFunctionSymbol2;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final boolean b = atLeastAsSpecializedAs;
                        if (b) {
                            return ocFunctionSymbol;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                }
                return ocFunctionSymbol2;
                try {
                    if (atLeastAsSpecializedAs) {
                        break Label_0108;
                    }
                    final boolean b2 = atLeastAsSpecializedAs2;
                    if (!b2) {
                        break Label_0108;
                    }
                    break Label_0108;
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final boolean b2 = atLeastAsSpecializedAs2;
                if (!b2) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        final boolean variadicTemplate = ocFunctionSymbol.isVariadicTemplate();
        final boolean variadicTemplate2 = ocFunctionSymbol2.isVariadicTemplate();
        Label_0145: {
            try {
                if (variadicTemplate == variadicTemplate2) {
                    return null;
                }
                final boolean b3 = variadicTemplate;
                if (b3) {
                    break Label_0145;
                }
                return ocFunctionSymbol;
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            try {
                final boolean b3 = variadicTemplate;
                if (b3) {
                    return ocFunctionSymbol2;
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
        }
        return ocFunctionSymbol;
    }
    
    public static boolean isAtLeastAsSpecializedAs(@NotNull final OCFunctionSymbol p0, @NotNull final OCFunctionSymbol p1, final int p2, @NotNull final OCResolveContext p3) {
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
        //    18: ldc             "FT1"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isAtLeastAsSpecializedAs"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "FT2"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "isAtLeastAsSpecializedAs"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "context"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "isAtLeastAsSpecializedAs"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   131: athrow         
        //   132: getstatic       com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils.$assertionsDisabled:Z
        //   135: ifne            178
        //   138: aload_0        
        //   139: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isTemplateSymbol:()Z
        //   142: ifeq            166
        //   145: goto            152
        //   148: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   151: athrow         
        //   152: aload_1        
        //   153: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.isTemplateSymbol:()Z
        //   156: ifne            178
        //   159: goto            166
        //   162: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   165: athrow         
        //   166: new             Ljava/lang/AssertionError;
        //   169: dup            
        //   170: invokespecial   java/lang/AssertionError.<init>:()V
        //   173: athrow         
        //   174: invokestatic    com/jetbrains/cidr/lang/resolve/v2/TemplatesUtils.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   177: athrow         
        //   178: invokestatic    com/jetbrains/cidr/lang/types/OCTypeUtils.newTypeParameterMap:()Ljava/util/Map;
        //   181: astore          4
        //   183: aload_0        
        //   184: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getTypeWithoutSubstitution:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   187: aload_3        
        //   188: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   191: astore          5
        //   193: aload_1        
        //   194: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCFunctionSymbol.getTypeWithoutSubstitution:()Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   197: aload_3        
        //   198: invokevirtual   com/jetbrains/cidr/lang/types/OCFunctionType.resolve:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Lcom/jetbrains/cidr/lang/types/OCType;
        //   201: astore          6
        //   203: new             Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor;
        //   206: dup            
        //   207: iconst_0       
        //   208: iconst_0       
        //   209: iconst_0       
        //   210: iconst_0       
        //   211: iconst_1       
        //   212: aload           5
        //   214: aconst_null    
        //   215: aload           4
        //   217: aconst_null    
        //   218: aload_3        
        //   219: invokespecial   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.<init>:(ZZZZZLcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeOwner;Ljava/util/Map;Ljava/util/Set;Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)V
        //   222: astore          7
        //   224: aload           7
        //   226: iload_2        
        //   227: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.setRelevantFunctionArgumentsCount:(I)V
        //   230: aload           7
        //   232: aload           6
        //   234: aload           5
        //   236: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor.unify:(Lcom/jetbrains/cidr/lang/types/OCTypeArgument;Lcom/jetbrains/cidr/lang/types/OCTypeArgument;)Lcom/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult;
        //   239: invokevirtual   com/jetbrains/cidr/lang/types/visitors/OCTypeUnificationVisitor$UnificationResult.isUnified:()Z
        //   242: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  88     128    128    132    Ljava/lang/IllegalArgumentException;
        //  132    145    148    152    Ljava/lang/IllegalArgumentException;
        //  138    159    162    166    Ljava/lang/IllegalArgumentException;
        //  152    174    174    178    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0152:
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
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!TemplatesUtils.class.desiredAssertionStatus()) {
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
