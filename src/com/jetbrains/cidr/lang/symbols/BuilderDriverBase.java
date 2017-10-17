// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols;

import java.util.Collection;
import java.util.ArrayList;
import com.intellij.util.containers.Stack;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.expression.OCLambdaExpressionSymbol;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.symtable.OCNamesInternary;
import com.intellij.lang.ForeignLeafType;
import com.intellij.psi.tree.IElementType;
import com.intellij.openapi.progress.ProgressManager;
import java.util.Iterator;
import com.intellij.openapi.util.Ref;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.project.Project;
import com.intellij.util.Processor;
import com.intellij.lang.NodeStructure;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;

public class BuilderDriverBase<T, FILE extends PsiFile>
{
    private static final String LOG_UNRESOLVED_SYMBOLS_PROPERTY = "appCode.LogUnresolvedSymbols";
    protected static final boolean LOG_UNRESOLVED_SYMBOLS;
    public static final NamedNodeStructure<ASTNode> AST_NAMED_NODE_STRUCTURE;
    protected final FILE file;
    protected final OCInclusionContext context;
    protected final String text;
    protected final FlyweightCapableTreeStructure<T> structure;
    protected final NodeStructure<T> nodeStructure;
    protected final Processor<OCSymbol> builder;
    protected final Project project;
    protected final OCLanguageKind myLanguageKind;
    protected final VirtualFile myVirtualFile;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    protected int offset(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "offset"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.nodeStructure.getStartOffset((Object)t);
    }
    
    public BuilderDriverBase(@NotNull final FILE file, @NotNull final OCInclusionContext context, @Nullable final String text, @NotNull final FlyweightCapableTreeStructure<T> structure, @NotNull final NodeStructure<T> nodeStructure, @NotNull final Processor<OCSymbol> builder, @Nullable final VirtualFile myVirtualFile) {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "<init>"));
        }
        if (context == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "<init>"));
        }
        if (structure == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "structure", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "<init>"));
        }
        if (nodeStructure == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "nodeStructure", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "<init>"));
        }
        if (builder == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "builder", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "<init>"));
        }
        this.file = file;
        this.context = context;
        this.text = text;
        this.structure = structure;
        this.nodeStructure = nodeStructure;
        this.builder = builder;
        this.project = file.getProject();
        this.myLanguageKind = context.getLanguageKind();
        this.myVirtualFile = myVirtualFile;
    }
    
    @NotNull
    protected Iterable<T> getChildren(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "getChildren"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Ref create = Ref.create((Object)null);
        final int children = this.structure.getChildren((Object)t, create);
        Iterable<T> iterable;
        try {
            iterable = new Iterable<T>() {
                @NotNull
                @Override
                public Iterator<T> iterator() {
                    Iterator<T> iterator;
                    try {
                        iterator = new Iterator<T>() {
                            int idx = 0;
                            final /* synthetic */ BuilderDriverBase$2 this$1;
                            
                            @Override
                            public boolean hasNext() {
                                Label_0034: {
                                    try {
                                        if (create.isNull()) {
                                            return false;
                                        }
                                        final Iterator<T> iterator = this;
                                        final int n = iterator.idx;
                                        final Iterator<T> iterator2 = this;
                                        final Iterable<T> iterable = iterator2.this$1;
                                        final int n2 = children;
                                        if (n < n2) {
                                            break Label_0034;
                                        }
                                        return false;
                                    }
                                    catch (UnsupportedOperationException ex) {
                                        throw b(ex);
                                    }
                                    try {
                                        final Iterator<T> iterator = this;
                                        final int n = iterator.idx;
                                        final Iterator<T> iterator2 = this;
                                        final Iterable<T> iterable = iterator2.this$1;
                                        final int n2 = children;
                                        if (n < n2) {
                                            return true;
                                        }
                                    }
                                    catch (UnsupportedOperationException ex2) {
                                        throw b(ex2);
                                    }
                                }
                                return false;
                            }
                            
                            @Override
                            public T next() {
                                ProgressManager.checkCanceled();
                                return (T)((Object[])create.get())[this.idx++];
                            }
                            
                            @Override
                            public void remove() {
                                throw new UnsupportedOperationException();
                            }
                            
                            private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
                                return ex;
                            }
                        };
                        if (iterator == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$2", "iterator"));
                        }
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    return iterator;
                }
                
                private static IllegalStateException a(final IllegalStateException ex) {
                    return ex;
                }
            };
            if (iterable == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "getChildren"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return iterable;
    }
    
    @NotNull
    protected IElementType type(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "child", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "type"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        IElementType elementType = this.nodeStructure.getTokenType((Object)t);
        if (elementType instanceof ForeignLeafType) {
            elementType = ((ForeignLeafType)elementType).getDelegate();
        }
        ForeignLeafType foreignLeafType;
        try {
            foreignLeafType = (ForeignLeafType)elementType;
            if (foreignLeafType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "type"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return foreignLeafType;
    }
    
    @NotNull
    protected String nodeText(@NotNull final T t) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "nodeText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String intern;
        try {
            intern = OCNamesInternary.intern(this.nodeTextNoIntern(t, false));
            if (intern == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "nodeText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return intern;
    }
    
    @NotNull
    protected String nodeTextNoIntern(@NotNull final T t, final boolean b) {
        try {
            if (t == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "nodeTextNoIntern"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String a = this.a(t, null, b);
        String s2 = null;
        Label_0081: {
            Label_0069: {
                try {
                    if (BuilderDriverBase.$assertionsDisabled) {
                        break Label_0081;
                    }
                    final String s = a;
                    if (s == null) {
                        break Label_0069;
                    }
                    break Label_0081;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final String s = a;
                    if (s == null) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            try {
                s2 = a;
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase", "nodeTextNoIntern"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return s2;
    }
    
    @Nullable
    private String a(@NotNull final T p0, @Nullable final StringBuilder p1, final boolean p2) {
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
        //    18: ldc             "node"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/symbols/BuilderDriverBase"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "nodeText"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: aload_1        
        //    46: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase.type:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //    49: astore          4
        //    51: aload_0        
        //    52: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //    55: aload_1        
        //    56: invokeinterface com/intellij/lang/NodeStructure.getTokenType:(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;
        //    61: astore          5
        //    63: iload_3        
        //    64: ifne            107
        //    67: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.DIRECTIVES:Lcom/intellij/psi/tree/TokenSet;
        //    70: aload           4
        //    72: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    75: ifne            100
        //    78: goto            85
        //    81: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    84: athrow         
        //    85: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.MACRO_CALL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    88: aload           4
        //    90: if_acmpne       107
        //    93: goto            100
        //    96: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    99: athrow         
        //   100: ldc             ""
        //   102: areturn        
        //   103: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   106: athrow         
        //   107: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //   110: aload           4
        //   112: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   115: ifeq            146
        //   118: aload_2        
        //   119: ifnull          143
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   128: athrow         
        //   129: aload_2        
        //   130: ldc             " "
        //   132: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   135: pop            
        //   136: goto            143
        //   139: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   142: athrow         
        //   143: ldc             " "
        //   145: areturn        
        //   146: aload           5
        //   148: instanceof      Lcom/intellij/lang/ForeignLeafType;
        //   151: ifeq            185
        //   154: aload           5
        //   156: checkcast       Lcom/intellij/lang/ForeignLeafType;
        //   159: invokevirtual   com/intellij/lang/ForeignLeafType.getValue:()Ljava/lang/String;
        //   162: astore          6
        //   164: aload_2        
        //   165: ifnull          182
        //   168: aload_2        
        //   169: aload           6
        //   171: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   174: pop            
        //   175: goto            182
        //   178: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   181: athrow         
        //   182: aload           6
        //   184: areturn        
        //   185: aload_1        
        //   186: instanceof      Lcom/intellij/psi/impl/source/tree/ForeignLeafPsiElement;
        //   189: ifeq            222
        //   192: aload_1        
        //   193: checkcast       Lcom/intellij/psi/impl/source/tree/ForeignLeafPsiElement;
        //   196: invokevirtual   com/intellij/psi/impl/source/tree/ForeignLeafPsiElement.getText:()Ljava/lang/String;
        //   199: astore          6
        //   201: aload_2        
        //   202: ifnull          219
        //   205: aload_2        
        //   206: aload           6
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: pop            
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   218: athrow         
        //   219: aload           6
        //   221: areturn        
        //   222: aload           5
        //   224: instanceof      Lcom/intellij/lang/TokenWrapper;
        //   227: ifeq            290
        //   230: aload_0        
        //   231: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.text:Ljava/lang/String;
        //   234: ifnull          276
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   243: athrow         
        //   244: aload_0        
        //   245: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.text:Ljava/lang/String;
        //   248: aload_0        
        //   249: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   252: aload_1        
        //   253: invokeinterface com/intellij/lang/NodeStructure.getStartOffset:(Ljava/lang/Object;)I
        //   258: aload_0        
        //   259: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   262: aload_1        
        //   263: invokeinterface com/intellij/lang/NodeStructure.getEndOffset:(Ljava/lang/Object;)I
        //   268: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   271: areturn        
        //   272: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   275: athrow         
        //   276: aload_0        
        //   277: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   280: checkcast       Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$NamedNodeStructure;
        //   283: aload_1        
        //   284: invokeinterface com/jetbrains/cidr/lang/symbols/BuilderDriverBase$NamedNodeStructure.getNodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   289: areturn        
        //   290: aload_0        
        //   291: aload_1        
        //   292: invokevirtual   com/jetbrains/cidr/lang/symbols/BuilderDriverBase.getChildren:(Ljava/lang/Object;)Ljava/lang/Iterable;
        //   295: invokeinterface java/lang/Iterable.iterator:()Ljava/util/Iterator;
        //   300: invokestatic    com/intellij/util/containers/ContainerUtil.collect:(Ljava/util/Iterator;)Ljava/util/List;
        //   303: astore          6
        //   305: aload           6
        //   307: invokeinterface java/util/List.size:()I
        //   312: iconst_1       
        //   313: if_icmpne       335
        //   316: aload_0        
        //   317: aload           6
        //   319: iconst_0       
        //   320: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   325: aload_2        
        //   326: iload_3        
        //   327: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/Object;Ljava/lang/StringBuilder;Z)Ljava/lang/String;
        //   330: areturn        
        //   331: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   334: athrow         
        //   335: aload           6
        //   337: invokeinterface java/util/List.size:()I
        //   342: iconst_1       
        //   343: if_icmple       422
        //   346: iconst_0       
        //   347: istore          7
        //   349: aload_2        
        //   350: ifnonnull       364
        //   353: iconst_1       
        //   354: istore          7
        //   356: new             Ljava/lang/StringBuilder;
        //   359: dup            
        //   360: invokespecial   java/lang/StringBuilder.<init>:()V
        //   363: astore_2       
        //   364: aload           6
        //   366: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   371: astore          8
        //   373: aload           8
        //   375: invokeinterface java/util/Iterator.hasNext:()Z
        //   380: ifeq            404
        //   383: aload           8
        //   385: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   390: astore          9
        //   392: aload_0        
        //   393: aload           9
        //   395: aload_2        
        //   396: iload_3        
        //   397: invokespecial   com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/Object;Ljava/lang/StringBuilder;Z)Ljava/lang/String;
        //   400: pop            
        //   401: goto            373
        //   404: iload           7
        //   406: ifeq            420
        //   409: aload_2        
        //   410: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   413: goto            421
        //   416: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   419: athrow         
        //   420: aconst_null    
        //   421: areturn        
        //   422: aload_0        
        //   423: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.text:Ljava/lang/String;
        //   426: ifnull          461
        //   429: aload_0        
        //   430: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.text:Ljava/lang/String;
        //   433: aload_0        
        //   434: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   437: aload_1        
        //   438: invokeinterface com/intellij/lang/NodeStructure.getStartOffset:(Ljava/lang/Object;)I
        //   443: aload_0        
        //   444: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   447: aload_1        
        //   448: invokeinterface com/intellij/lang/NodeStructure.getEndOffset:(Ljava/lang/Object;)I
        //   453: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   456: astore          7
        //   458: goto            476
        //   461: aload_0        
        //   462: getfield        com/jetbrains/cidr/lang/symbols/BuilderDriverBase.nodeStructure:Lcom/intellij/lang/NodeStructure;
        //   465: checkcast       Lcom/jetbrains/cidr/lang/symbols/BuilderDriverBase$NamedNodeStructure;
        //   468: aload_1        
        //   469: invokeinterface com/jetbrains/cidr/lang/symbols/BuilderDriverBase$NamedNodeStructure.getNodeText:(Ljava/lang/Object;)Ljava/lang/String;
        //   474: astore          7
        //   476: aload_2        
        //   477: ifnull          494
        //   480: aload_2        
        //   481: aload           7
        //   483: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   486: pop            
        //   487: goto            494
        //   490: invokestatic    com/jetbrains/cidr/lang/symbols/BuilderDriverBase.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   493: athrow         
        //   494: aload           7
        //   496: areturn        
        //    Signature:
        //  (TT;Ljava/lang/StringBuilder;Z)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  63     78     81     85     Ljava/lang/IllegalArgumentException;
        //  67     93     96     100    Ljava/lang/IllegalArgumentException;
        //  85     103    103    107    Ljava/lang/IllegalArgumentException;
        //  107    122    125    129    Ljava/lang/IllegalArgumentException;
        //  118    136    139    143    Ljava/lang/IllegalArgumentException;
        //  164    175    178    182    Ljava/lang/IllegalArgumentException;
        //  201    212    215    219    Ljava/lang/IllegalArgumentException;
        //  222    237    240    244    Ljava/lang/IllegalArgumentException;
        //  230    272    272    276    Ljava/lang/IllegalArgumentException;
        //  305    331    331    335    Ljava/lang/IllegalArgumentException;
        //  404    416    416    420    Ljava/lang/IllegalArgumentException;
        //  476    487    490    494    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0085:
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
                if (!BuilderDriverBase.class.desiredAssertionStatus()) {
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
        LOG_UNRESOLVED_SYMBOLS = Comparing.equal(System.getProperty("appCode.LogUnresolvedSymbols"), "true");
        AST_NAMED_NODE_STRUCTURE = new NamedNodeStructure<ASTNode>() {
            public int getStartOffset(@NotNull final ASTNode astNode) {
                try {
                    if (astNode == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$1", "getStartOffset"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    if (astNode instanceof OCMacroForeignLeafElement) {
                        return ((OCMacroForeignLeafElement)astNode).getRealStartOffset();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                return astNode.getStartOffset();
            }
            
            public int getEndOffset(@NotNull final ASTNode astNode) {
                try {
                    if (astNode == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$1", "getEndOffset"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return astNode.getTextRange().getEndOffset();
            }
            
            public IElementType getTokenType(@NotNull final ASTNode astNode) {
                try {
                    if (astNode == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$1", "getTokenType"));
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                return astNode.getElementType();
            }
            
            @Override
            public String getNodeText(final ASTNode astNode) {
                return astNode.getText();
            }
            
            private static IllegalArgumentException a(final IllegalArgumentException ex) {
                return ex;
            }
        };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class DeclarationContext<T>
    {
        private OCSymbolKind declaratorType;
        private Object parent;
        private int lambdaParameterIndex;
        private OCLambdaExpressionSymbol lambda;
        protected OCSymbolWithQualifiedName myParentSymbol;
        private OCVisibility visibility;
        protected boolean isDeclarationWithoutDeclarators;
        protected boolean isDeclarationWithoutType;
        private boolean isBaseClause;
        private boolean isTemplateValueParameter;
        private boolean isLambdaInitCapture;
        private List<String> attributes;
        private PsiElement localContext;
        protected T myKRParamterList;
        private ASTNode forCollection;
        private Stack<List<OCSymbolReference>> referencesInDeclaration;
        private List<T> myTemplateParameters;
        private boolean myIsTemplateSymbol;
        private boolean myInsideTemplateParams;
        private boolean myAssumeNonNull;
        private int myModifiers;
        
        public DeclarationContext(final OCSymbolKind declaratorType, final Object parent, final OCSymbolWithQualifiedName myParentSymbol, final OCVisibility visibility, final PsiElement localContext, final boolean myAssumeNonNull) {
            this.lambdaParameterIndex = -1;
            this.referencesInDeclaration = (Stack<List<OCSymbolReference>>)new Stack();
            this.myTemplateParameters = new ArrayList<T>();
            this.myModifiers = 0;
            this.declaratorType = declaratorType;
            this.parent = parent;
            this.myParentSymbol = myParentSymbol;
            this.visibility = visibility;
            this.localContext = localContext;
            this.myAssumeNonNull = myAssumeNonNull;
        }
        
        public DeclarationContext(final OCSymbolKind declaratorType) {
            this.lambdaParameterIndex = -1;
            this.referencesInDeclaration = (Stack<List<OCSymbolReference>>)new Stack();
            this.myTemplateParameters = new ArrayList<T>();
            this.myModifiers = 0;
            this.declaratorType = declaratorType;
        }
        
        public DeclarationContext() {
            this.lambdaParameterIndex = -1;
            this.referencesInDeclaration = (Stack<List<OCSymbolReference>>)new Stack();
            this.myTemplateParameters = new ArrayList<T>();
            this.myModifiers = 0;
        }
        
        public OCSymbolKind getDeclaratorType() {
            return this.declaratorType;
        }
        
        public void setDeclaratorType(final OCSymbolKind declaratorType) {
            this.declaratorType = declaratorType;
        }
        
        public Object getParent() {
            return this.parent;
        }
        
        public OCSymbolWithQualifiedName getParentSymbol() {
            return this.myParentSymbol;
        }
        
        public OCVisibility getVisibility() {
            try {
                if (this.isFriend()) {
                    return OCVisibility.NULL;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return this.visibility;
        }
        
        public boolean isFriend() {
            return this.hasModifier(OCSymbolAttribute.FRIEND);
        }
        
        public boolean isVirtual() {
            return this.hasModifier(OCSymbolAttribute.VIRTUAL);
        }
        
        public boolean isConstexpr() {
            return this.hasModifier(OCSymbolAttribute.CONSTEXPR);
        }
        
        public boolean isBaseClause() {
            return this.isBaseClause;
        }
        
        public void setBaseClause(final boolean isBaseClause) {
            this.isBaseClause = isBaseClause;
        }
        
        public boolean isTemplateValueParameter() {
            return this.isTemplateValueParameter;
        }
        
        public void setTemplateValueParameter(final boolean isTemplateValueParameter) {
            this.isTemplateValueParameter = isTemplateValueParameter;
        }
        
        void addModifier(@NotNull final OCSymbolAttribute ocSymbolAttribute) {
            try {
                if (ocSymbolAttribute == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifier", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "addModifier"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.myModifiers |= ocSymbolAttribute.getMask();
        }
        
        int getModifiers() {
            return this.myModifiers;
        }
        
        public boolean hasModifier(@NotNull final OCSymbolAttribute ocSymbolAttribute) {
            try {
                if (ocSymbolAttribute == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "modifier", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "hasModifier"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if ((this.myModifiers & ocSymbolAttribute.getMask()) != 0x0) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return false;
        }
        
        public List<String> getAttributes() {
            return this.attributes;
        }
        
        public void addAttributes(final List<String> attributes) {
            try {
                if (this.attributes == null) {
                    this.attributes = attributes;
                    return;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            this.attributes.addAll(attributes);
        }
        
        public PsiElement getLocalContext() {
            return this.localContext;
        }
        
        public void setLocalContext(@Nullable final PsiElement localContext) {
            this.localContext = localContext;
        }
        
        public List<T> getTemplateParameters() {
            return this.myTemplateParameters;
        }
        
        public void setDeclarationWithoutDeclarators(final boolean isDeclarationWithoutDeclarators) {
            this.isDeclarationWithoutDeclarators = isDeclarationWithoutDeclarators;
        }
        
        public void setDeclarationWithoutType(final boolean isDeclarationWithoutType) {
            this.isDeclarationWithoutType = isDeclarationWithoutType;
        }
        
        public ASTNode getForCollection() {
            return this.forCollection;
        }
        
        public void setForCollection(final ASTNode forCollection) {
            this.forCollection = forCollection;
        }
        
        @NotNull
        public List<OCSymbolReference> getReferencesInDeclaration() {
            List list;
            try {
                list = (List)this.referencesInDeclaration.peek();
                if (list == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "getReferencesInDeclaration"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return (List<OCSymbolReference>)list;
        }
        
        public void addSymbolReference(@NotNull final OCSymbolReference ocSymbolReference) {
            try {
                if (ocSymbolReference == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/symbols/BuilderDriverBase$DeclarationContext", "addSymbolReference"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                if (!this.referencesInDeclaration.isEmpty()) {
                    ((List)this.referencesInDeclaration.peek()).add(ocSymbolReference);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        
        public void pushDeclaration() {
            this.referencesInDeclaration.push((Object)new ArrayList());
        }
        
        public void popDeclaration() {
            this.referencesInDeclaration.pop();
        }
        
        public boolean isTemplateSymbol() {
            return this.myIsTemplateSymbol;
        }
        
        public void setTemplateSymbol(final boolean myIsTemplateSymbol) {
            this.myIsTemplateSymbol = myIsTemplateSymbol;
        }
        
        public boolean isInsideTemplateParams() {
            return this.myInsideTemplateParams;
        }
        
        public void setInsideTemplateParams(final boolean myInsideTemplateParams) {
            this.myInsideTemplateParams = myInsideTemplateParams;
        }
        
        public boolean isAssumeNonNull() {
            return this.myAssumeNonNull;
        }
        
        public void setAssumeNonNull(final boolean myAssumeNonNull) {
            this.myAssumeNonNull = myAssumeNonNull;
        }
        
        @Nullable
        public OCLambdaExpressionSymbol getLambda() {
            return this.lambda;
        }
        
        public void setLambda(final OCLambdaExpressionSymbol lambda) {
            this.lambda = lambda;
        }
        
        public int getLambdaParameterIndex() {
            return this.lambdaParameterIndex;
        }
        
        public void setLambdaParameterIndex(final int lambdaParameterIndex) {
            this.lambdaParameterIndex = lambdaParameterIndex;
        }
        
        public boolean isLambdaInitCapture() {
            return this.isLambdaInitCapture;
        }
        
        public void setLambdaInitCapture(final boolean isLambdaInitCapture) {
            this.isLambdaInitCapture = isLambdaInitCapture;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public interface NamedNodeStructure<T> extends NodeStructure<T>
    {
        String getNodeText(final T p0);
    }
}
