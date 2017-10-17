// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.injected.InjectionBackgroundSuppressor;
import com.intellij.psi.PsiDocCommentBase;
import com.intellij.psi.impl.source.tree.PsiCommentImpl;
import com.intellij.psi.impl.source.tree.LeafElement;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.tree.IElementType;
import com.intellij.core.CoreASTFactory;

public class OCASTFactory extends CoreASTFactory
{
    @NotNull
    @Override
    public LeafElement createComment(@NotNull final IElementType p0, @NotNull final CharSequence p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "type"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/editor/OCASTFactory"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "createComment"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
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
        //    62: ldc             "text"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/editor/OCASTFactory"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "createComment"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    87: athrow         
        //    88: aload_2        
        //    89: invokeinterface java/lang/CharSequence.toString:()Ljava/lang/String;
        //    94: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //    97: astore_3       
        //    98: aload_1        
        //    99: getstatic       com/jetbrains/cidr/lang/editor/OCCommenter.COMMENT_TOKEN_TYPE:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   102: if_acmpne       208
        //   105: aload_3        
        //   106: ldc             "/**"
        //   108: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   111: ifne            137
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: aload_3        
        //   122: ldc             "/*!"
        //   124: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   127: ifeq            302
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   136: athrow         
        //   137: aload_3        
        //   138: ldc             "/**/"
        //   140: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   143: ifne            302
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: new             Lcom/jetbrains/cidr/lang/editor/OCASTFactory$PsiCommentPlaceholder;
        //   156: dup            
        //   157: aload_1        
        //   158: aload_2        
        //   159: invokespecial   com/jetbrains/cidr/lang/editor/OCASTFactory$PsiCommentPlaceholder.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;)V
        //   162: dup            
        //   163: ifnonnull       207
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   172: athrow         
        //   173: new             Ljava/lang/IllegalStateException;
        //   176: dup            
        //   177: ldc             "@NotNull method %s.%s must not return null"
        //   179: ldc             2
        //   181: anewarray       Ljava/lang/Object;
        //   184: dup            
        //   185: ldc             0
        //   187: ldc             "com/jetbrains/cidr/lang/editor/OCASTFactory"
        //   189: aastore        
        //   190: dup            
        //   191: ldc             1
        //   193: ldc             "createComment"
        //   195: aastore        
        //   196: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   199: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   202: athrow         
        //   203: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   206: athrow         
        //   207: areturn        
        //   208: aload_1        
        //   209: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.EOL_COMMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   212: if_acmpne       302
        //   215: aload_3        
        //   216: ldc             "///"
        //   218: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   221: ifne            247
        //   224: goto            231
        //   227: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   230: athrow         
        //   231: aload_3        
        //   232: ldc             "//!"
        //   234: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   237: ifeq            302
        //   240: goto            247
        //   243: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   246: athrow         
        //   247: new             Lcom/jetbrains/cidr/lang/editor/OCASTFactory$PsiCommentPlaceholder;
        //   250: dup            
        //   251: aload_1        
        //   252: aload_2        
        //   253: invokespecial   com/jetbrains/cidr/lang/editor/OCASTFactory$PsiCommentPlaceholder.<init>:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;)V
        //   256: dup            
        //   257: ifnonnull       301
        //   260: goto            267
        //   263: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   266: athrow         
        //   267: new             Ljava/lang/IllegalStateException;
        //   270: dup            
        //   271: ldc             "@NotNull method %s.%s must not return null"
        //   273: ldc             2
        //   275: anewarray       Ljava/lang/Object;
        //   278: dup            
        //   279: ldc             0
        //   281: ldc             "com/jetbrains/cidr/lang/editor/OCASTFactory"
        //   283: aastore        
        //   284: dup            
        //   285: ldc             1
        //   287: ldc             "createComment"
        //   289: aastore        
        //   290: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   293: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   296: athrow         
        //   297: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   300: athrow         
        //   301: areturn        
        //   302: aload_0        
        //   303: aload_1        
        //   304: aload_2        
        //   305: invokespecial   com/intellij/core/CoreASTFactory.createComment:(Lcom/intellij/psi/tree/IElementType;Ljava/lang/CharSequence;)Lcom/intellij/psi/impl/source/tree/LeafElement;
        //   308: dup            
        //   309: ifnonnull       346
        //   312: new             Ljava/lang/IllegalStateException;
        //   315: dup            
        //   316: ldc             "@NotNull method %s.%s must not return null"
        //   318: ldc             2
        //   320: anewarray       Ljava/lang/Object;
        //   323: dup            
        //   324: ldc             0
        //   326: ldc             "com/jetbrains/cidr/lang/editor/OCASTFactory"
        //   328: aastore        
        //   329: dup            
        //   330: ldc             1
        //   332: ldc             "createComment"
        //   334: aastore        
        //   335: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   338: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   341: athrow         
        //   342: invokestatic    com/jetbrains/cidr/lang/editor/OCASTFactory.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   345: athrow         
        //   346: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  98     114    117    121    Ljava/lang/IllegalArgumentException;
        //  105    130    133    137    Ljava/lang/IllegalArgumentException;
        //  121    146    149    153    Ljava/lang/IllegalArgumentException;
        //  137    166    169    173    Ljava/lang/IllegalArgumentException;
        //  153    203    203    207    Ljava/lang/IllegalArgumentException;
        //  208    224    227    231    Ljava/lang/IllegalArgumentException;
        //  215    240    243    247    Ljava/lang/IllegalArgumentException;
        //  231    260    263    267    Ljava/lang/IllegalArgumentException;
        //  247    297    297    301    Ljava/lang/IllegalArgumentException;
        //  302    342    342    346    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0121:
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
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class PsiCommentPlaceholder extends PsiCommentImpl implements PsiDocCommentBase, InjectionBackgroundSuppressor
    {
        public PsiCommentPlaceholder(final IElementType type, final CharSequence text) {
            super(type, text);
        }
        
        @Nullable
        public PsiElement getOwner() {
            for (PsiElement psiElement = this.getNextSibling(); psiElement != null; psiElement = psiElement.getNextSibling()) {
                if (!(psiElement instanceof PsiWhiteSpace)) {
                    if (!(psiElement instanceof PsiComment)) {
                        if (!(psiElement instanceof OCMacroCall)) {
                            if (psiElement instanceof OCCallable || psiElement instanceof OCDeclaration) {
                                return psiElement;
                            }
                            break;
                        }
                    }
                }
            }
            final PsiElement parent = this.getParent();
            if (parent instanceof OCCallable || parent instanceof OCDeclaration) {
                return parent;
            }
            return null;
        }
    }
}
