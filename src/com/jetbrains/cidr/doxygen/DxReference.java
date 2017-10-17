// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.doxygen;

import com.intellij.util.ArrayUtil;
import java.util.ArrayList;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiDocCommentBase;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.doxygen.psi.impl.DxPsiImplUtil;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenCmd;
import java.util.Optional;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.doxygen.psi.DxDocComment;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.doxygen.psi.DxDocTag;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.doxygen.psi.DxParamId;
import com.intellij.psi.PsiReferenceBase;

public class DxReference extends PsiReferenceBase<DxParamId> implements PsiReference
{
    public DxReference(@NotNull final PsiElement psiElement, @NotNull final TextRange textRange) {
        if (psiElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/doxygen/DxReference", "<init>"));
        }
        if (textRange == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textRange", "com/jetbrains/cidr/doxygen/DxReference", "<init>"));
        }
        super((PsiElement)psiElement, textRange);
    }
    
    @Nullable
    public PsiElement resolve() {
        final DxDocTag dxDocTag = (DxDocTag)PsiTreeUtil.getParentOfType(this.myElement, (Class)DxDocTag.class);
        final DxDocComment dxDocComment = (DxDocComment)PsiTreeUtil.getParentOfType(this.myElement, (Class)DxDocComment.class);
        try {
            if (dxDocTag == null || dxDocComment == null) {
                return null;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Object o = Optional.empty();
        if (dxDocTag.getName().equals(DoxygenCmd.PARAM.toString())) {
            o = this.a(dxDocComment);
        }
        else if (dxDocTag.getName().equals(DoxygenCmd.TPARAM.toString())) {
            o = this.b(dxDocComment);
        }
        try {
            if (((Optional)o).isPresent()) {
                return ((Optional<PsiElement>)o).get();
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        return DxPsiImplUtil.setName((DxParamId)this.getElement(), s);
    }
    
    public TextRange getRangeInElement() {
        return TextRange.create(0, ((DxParamId)this.myElement).getText().length());
    }
    
    private Optional<? extends PsiNamedElement> a(@NotNull final DxDocComment dxDocComment) {
        try {
            if (dxDocComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/DxReference", "resolveParam"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final OCCallable<?> function = DoxygenUtil.findFunction(DoxygenUtil.getHostComment((PsiElement)dxDocComment));
        Label_0074: {
            try {
                if (function == null) {
                    return Optional.empty();
                }
                final OCCallable<?> ocCallable = function;
                final List<? extends PsiNamedElement> list = ocCallable.getParameters();
                if (list != null) {
                    break Label_0074;
                }
                return Optional.empty();
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            try {
                final OCCallable<?> ocCallable = function;
                final List<? extends PsiNamedElement> list = ocCallable.getParameters();
                if (list != null) {
                    return (Optional<? extends PsiNamedElement>)function.getParameters().stream().filter(psiNamedElement -> ((DxParamId)this.myElement).getText().equals(psiNamedElement.getName())).findFirst();
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return Optional.empty();
    }
    
    private Optional<? extends PsiNamedElement> b(@NotNull final DxDocComment dxDocComment) {
        try {
            if (dxDocComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/doxygen/DxReference", "resolveTemplateParam"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiComment hostComment = DoxygenUtil.getHostComment((PsiElement)dxDocComment);
        if (hostComment instanceof PsiDocCommentBase) {
            final boolean b;
            return Optional.ofNullable(DoxygenUtil.traverseTemplateParametersList(((PsiDocCommentBase)hostComment).getOwner(), (p0, s) -> {
                try {
                    if (!((DxParamId)this.myElement).getText().equals(s)) {
                        return b;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                return b;
            }));
        }
        return Optional.empty();
    }
    
    @NotNull
    public Object[] getVariants() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/doxygen/DxReference.myElement:Lcom/intellij/psi/PsiElement;
        //     4: checkcast       Lcom/jetbrains/cidr/doxygen/psi/DxParamId;
        //     7: invokeinterface com/jetbrains/cidr/doxygen/psi/DxParamId.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //    12: astore_1       
        //    13: aload_1        
        //    14: ifnull          96
        //    17: aload_1        
        //    18: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    23: astore_2       
        //    24: aload_2        
        //    25: ifnull          96
        //    28: aload_2        
        //    29: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    34: getstatic       com/jetbrains/cidr/doxygen/psi/DxTypes.ELLIPSIS:Lcom/intellij/psi/tree/IElementType;
        //    37: if_acmpne       96
        //    40: goto            47
        //    43: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    46: athrow         
        //    47: getstatic       com/intellij/util/ArrayUtil.EMPTY_OBJECT_ARRAY:[Ljava/lang/Object;
        //    50: dup            
        //    51: ifnonnull       95
        //    54: goto            61
        //    57: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    60: athrow         
        //    61: new             Ljava/lang/IllegalStateException;
        //    64: dup            
        //    65: ldc             "@NotNull method %s.%s must not return null"
        //    67: ldc             2
        //    69: anewarray       Ljava/lang/Object;
        //    72: dup            
        //    73: ldc             0
        //    75: ldc             "com/jetbrains/cidr/doxygen/DxReference"
        //    77: aastore        
        //    78: dup            
        //    79: ldc             1
        //    81: ldc             "getVariants"
        //    83: aastore        
        //    84: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    87: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    90: athrow         
        //    91: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    94: athrow         
        //    95: areturn        
        //    96: aload_0        
        //    97: getfield        com/jetbrains/cidr/doxygen/DxReference.myElement:Lcom/intellij/psi/PsiElement;
        //   100: ldc             Lcom/jetbrains/cidr/doxygen/psi/DxDocTag;.class
        //   102: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   105: checkcast       Lcom/jetbrains/cidr/doxygen/psi/DxDocTag;
        //   108: astore_2       
        //   109: aload_0        
        //   110: getfield        com/jetbrains/cidr/doxygen/DxReference.myElement:Lcom/intellij/psi/PsiElement;
        //   113: ldc             Lcom/jetbrains/cidr/doxygen/psi/DxDocComment;.class
        //   115: invokestatic    com/intellij/psi/util/PsiTreeUtil.getParentOfType:(Lcom/intellij/psi/PsiElement;Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   118: checkcast       Lcom/jetbrains/cidr/doxygen/psi/DxDocComment;
        //   121: astore_3       
        //   122: aload_2        
        //   123: ifnull          281
        //   126: aload_3        
        //   127: ifnull          281
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   136: athrow         
        //   137: aload_3        
        //   138: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.getHostComment:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiComment;
        //   141: astore          4
        //   143: aload_2        
        //   144: invokeinterface com/jetbrains/cidr/doxygen/psi/DxDocTag.getName:()Ljava/lang/String;
        //   149: getstatic       com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd.PARAM:Lcom/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd;
        //   152: invokevirtual   com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd.toString:()Ljava/lang/String;
        //   155: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   158: ifeq            212
        //   161: aload           4
        //   163: invokestatic    com/jetbrains/cidr/doxygen/DxReference.b:(Lcom/intellij/psi/PsiComment;)[Ljava/lang/Object;
        //   166: dup            
        //   167: ifnonnull       211
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   176: athrow         
        //   177: new             Ljava/lang/IllegalStateException;
        //   180: dup            
        //   181: ldc             "@NotNull method %s.%s must not return null"
        //   183: ldc             2
        //   185: anewarray       Ljava/lang/Object;
        //   188: dup            
        //   189: ldc             0
        //   191: ldc             "com/jetbrains/cidr/doxygen/DxReference"
        //   193: aastore        
        //   194: dup            
        //   195: ldc             1
        //   197: ldc             "getVariants"
        //   199: aastore        
        //   200: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   203: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   206: athrow         
        //   207: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   210: athrow         
        //   211: areturn        
        //   212: aload_2        
        //   213: invokeinterface com/jetbrains/cidr/doxygen/psi/DxDocTag.getName:()Ljava/lang/String;
        //   218: getstatic       com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd.TPARAM:Lcom/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd;
        //   221: invokevirtual   com/jetbrains/cidr/lang/documentation/doxygen/api/DoxygenCmd.toString:()Ljava/lang/String;
        //   224: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   227: ifeq            281
        //   230: aload           4
        //   232: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/psi/PsiComment;)[Ljava/lang/Object;
        //   235: dup            
        //   236: ifnonnull       280
        //   239: goto            246
        //   242: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   245: athrow         
        //   246: new             Ljava/lang/IllegalStateException;
        //   249: dup            
        //   250: ldc             "@NotNull method %s.%s must not return null"
        //   252: ldc             2
        //   254: anewarray       Ljava/lang/Object;
        //   257: dup            
        //   258: ldc             0
        //   260: ldc             "com/jetbrains/cidr/doxygen/DxReference"
        //   262: aastore        
        //   263: dup            
        //   264: ldc             1
        //   266: ldc             "getVariants"
        //   268: aastore        
        //   269: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   272: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   275: athrow         
        //   276: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   279: athrow         
        //   280: areturn        
        //   281: getstatic       com/intellij/util/ArrayUtil.EMPTY_OBJECT_ARRAY:[Ljava/lang/Object;
        //   284: dup            
        //   285: ifnonnull       322
        //   288: new             Ljava/lang/IllegalStateException;
        //   291: dup            
        //   292: ldc             "@NotNull method %s.%s must not return null"
        //   294: ldc             2
        //   296: anewarray       Ljava/lang/Object;
        //   299: dup            
        //   300: ldc             0
        //   302: ldc             "com/jetbrains/cidr/doxygen/DxReference"
        //   304: aastore        
        //   305: dup            
        //   306: ldc             1
        //   308: ldc             "getVariants"
        //   310: aastore        
        //   311: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   314: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   317: athrow         
        //   318: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   321: athrow         
        //   322: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  24     40     43     47     Lcom/intellij/util/IncorrectOperationException;
        //  28     54     57     61     Lcom/intellij/util/IncorrectOperationException;
        //  47     91     91     95     Lcom/intellij/util/IncorrectOperationException;
        //  122    130    133    137    Lcom/intellij/util/IncorrectOperationException;
        //  143    170    173    177    Lcom/intellij/util/IncorrectOperationException;
        //  161    207    207    211    Lcom/intellij/util/IncorrectOperationException;
        //  212    239    242    246    Lcom/intellij/util/IncorrectOperationException;
        //  230    276    276    280    Lcom/intellij/util/IncorrectOperationException;
        //  281    318    318    322    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0047:
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
    
    private static Object[] b(@Nullable final PsiComment p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/doxygen/DoxygenUtil.findFunction:(Lcom/intellij/psi/PsiComment;)Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //     4: astore_1       
        //     5: aload_1        
        //     6: ifnull          56
        //     9: aload_1        
        //    10: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getParameters:()Ljava/util/List;
        //    15: astore_2       
        //    16: aload_2        
        //    17: ifnull          56
        //    20: aload_2        
        //    21: invokeinterface java/util/List.stream:()Ljava/util/stream/Stream;
        //    26: invokedynamic   apply:()Ljava/util/function/Function;
        //    31: invokeinterface java/util/stream/Stream.map:(Ljava/util/function/Function;)Ljava/util/stream/Stream;
        //    36: invokedynamic   test:()Ljava/util/function/Predicate;
        //    41: invokeinterface java/util/stream/Stream.filter:(Ljava/util/function/Predicate;)Ljava/util/stream/Stream;
        //    46: invokeinterface java/util/stream/Stream.toArray:()[Ljava/lang/Object;
        //    51: areturn        
        //    52: invokestatic    com/jetbrains/cidr/doxygen/DxReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    55: athrow         
        //    56: getstatic       com/intellij/util/ArrayUtil.EMPTY_OBJECT_ARRAY:[Ljava/lang/Object;
        //    59: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  16     52     52     56     Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
        //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
    
    private static Object[] a(@Nullable final PsiComment psiComment) {
        if (psiComment instanceof PsiDocCommentBase) {
            final PsiElement owner = ((PsiDocCommentBase)psiComment).getOwner();
            final ArrayList list = new ArrayList();
            final List<String> list2;
            DoxygenUtil.traverseTemplateParametersList(owner, s -> {
                try {
                    if (b(s)) {
                        list2.add(s);
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return;
            });
            return list.toArray();
        }
        return ArrayUtil.EMPTY_OBJECT_ARRAY;
    }
    
    private static boolean b(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/doxygen/DxReference", "isValidIdentifier"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (s.isEmpty()) {
                return false;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        Label_0084: {
            try {
                if (s.startsWith("...")) {
                    break Label_0084;
                }
                final String s2 = s;
                final int n = 0;
                final char c = s2.charAt(n);
                final boolean b = Character.isJavaIdentifierPart(c);
                if (b) {
                    break Label_0084;
                }
                return false;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final String s2 = s;
                final int n = 0;
                final char c = s2.charAt(n);
                final boolean b = Character.isJavaIdentifierPart(c);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return false;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
