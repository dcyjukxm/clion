// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.jetbrains.cidr.lang.editor.completion.OCCompletionPriority;
import com.intellij.icons.AllIcons;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.codeInsight.lookup.LookupElementRenderer;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.ResolveResult;
import com.intellij.openapi.util.io.FileUtil;
import java.util.Iterator;
import java.util.Collections;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFileVisitor;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.Processor;
import com.intellij.util.containers.hash.HashSet;
import java.util.ArrayList;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.List;
import com.intellij.util.ArrayUtil;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.util.TextRange;
import org.jetbrains.annotations.NotNull;
import java.util.Set;
import com.intellij.psi.PsiElement;

public class OCFileResourceReference extends PsiCachingReferenceBase<PsiElement> implements OCResourceReference
{
    private Set<String> myExtensions;
    private boolean myExtensionInLiteral;
    private final boolean myIsDirectory;
    
    public OCFileResourceReference(final PsiElement psiElement, final Set<String> myExtensions, final boolean myExtensionInLiteral, final boolean myIsDirectory) {
        super(psiElement, false);
        this.myExtensions = myExtensions;
        this.myExtensionInLiteral = myExtensionInLiteral;
        this.myIsDirectory = myIsDirectory;
    }
    
    @Override
    public PsiElement resolveInner() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/intellij/util/CommonProcessors$FindFirstProcessor;
        //     3: dup            
        //     4: invokespecial   com/intellij/util/CommonProcessors$FindFirstProcessor.<init>:()V
        //     7: astore_1       
        //     8: new             Ljava/io/File;
        //    11: dup            
        //    12: aload_0        
        //    13: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.getCanonicalText:()Ljava/lang/String;
        //    16: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    19: astore_2       
        //    20: aload_2        
        //    21: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    24: astore_3       
        //    25: aload_0        
        //    26: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensionInLiteral:Z
        //    29: ifne            66
        //    32: aload_0        
        //    33: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensions:Ljava/util/Set;
        //    36: ifnull          71
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    45: athrow         
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensions:Ljava/util/Set;
        //    50: invokeinterface java/util/Set.size:()I
        //    55: iconst_1       
        //    56: if_icmpne       71
        //    59: goto            66
        //    62: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    65: athrow         
        //    66: aload_3        
        //    67: invokestatic    com/intellij/openapi/util/io/FileUtil.getNameWithoutExtension:(Ljava/lang/String;)Ljava/lang/String;
        //    70: astore_3       
        //    71: new             Ljava/util/ArrayList;
        //    74: dup            
        //    75: invokespecial   java/util/ArrayList.<init>:()V
        //    78: astore          4
        //    80: aload_2        
        //    81: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //    84: astore          5
        //    86: aload           5
        //    88: ifnull          114
        //    91: aload           4
        //    93: iconst_0       
        //    94: aload           5
        //    96: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //    99: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //   104: aload           5
        //   106: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   109: astore          5
        //   111: goto            86
        //   114: aload_0        
        //   115: aload_3        
        //   116: aload           4
        //   118: aload_1        
        //   119: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Ljava/lang/String;Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   122: pop            
        //   123: aload_1        
        //   124: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.isFound:()Z
        //   127: ifne            215
        //   130: aload_0        
        //   131: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensions:Ljava/util/Set;
        //   134: ifnull          215
        //   137: goto            144
        //   140: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   143: athrow         
        //   144: aload_0        
        //   145: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensions:Ljava/util/Set;
        //   148: ldc             "png"
        //   150: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   155: ifeq            215
        //   158: goto            165
        //   161: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   164: athrow         
        //   165: aload_3        
        //   166: ldc             "@2x"
        //   168: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   171: ifne            215
        //   174: goto            181
        //   177: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   180: athrow         
        //   181: aload_0        
        //   182: new             Ljava/lang/StringBuilder;
        //   185: dup            
        //   186: invokespecial   java/lang/StringBuilder.<init>:()V
        //   189: aload_3        
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: ldc             "@2x"
        //   195: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   198: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   201: aload           4
        //   203: aload_1        
        //   204: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Ljava/lang/String;Ljava/util/List;Lcom/intellij/util/Processor;)Z
        //   207: pop            
        //   208: goto            215
        //   211: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   214: athrow         
        //   215: aload_1        
        //   216: invokevirtual   com/intellij/util/CommonProcessors$FindFirstProcessor.getFoundValue:()Ljava/lang/Object;
        //   219: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   222: astore          6
        //   224: aload           6
        //   226: ifnull          255
        //   229: aload           6
        //   231: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //   234: ifeq            255
        //   237: goto            244
        //   240: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   243: athrow         
        //   244: aload_0        
        //   245: aload           6
        //   247: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/psi/PsiElement;
        //   250: areturn        
        //   251: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   254: athrow         
        //   255: aconst_null    
        //   256: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  25     39     42     46     Lcom/intellij/util/IncorrectOperationException;
        //  32     59     62     66     Lcom/intellij/util/IncorrectOperationException;
        //  114    137    140    144    Lcom/intellij/util/IncorrectOperationException;
        //  130    158    161    165    Lcom/intellij/util/IncorrectOperationException;
        //  144    174    177    181    Lcom/intellij/util/IncorrectOperationException;
        //  165    208    211    215    Lcom/intellij/util/IncorrectOperationException;
        //  224    237    240    244    Lcom/intellij/util/IncorrectOperationException;
        //  229    251    251    255    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0144:
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
    public String getCanonicalText() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myElement:Lcom/intellij/psi/PsiElement;
        //     4: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //     7: ifeq            26
        //    10: aload_0        
        //    11: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.getElement:()Lcom/intellij/psi/PsiElement;
        //    14: checkcast       Lcom/jetbrains/cidr/lang/psi/OCLiteralExpression;
        //    17: invokeinterface com/jetbrains/cidr/lang/psi/OCLiteralExpression.getUnescapedLiteralText:()Ljava/lang/String;
        //    22: astore_1       
        //    23: goto            39
        //    26: aload_0        
        //    27: invokevirtual   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.getElement:()Lcom/intellij/psi/PsiElement;
        //    30: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //    35: invokestatic    com/intellij/openapi/util/text/StringUtil.unquoteString:(Ljava/lang/String;)Ljava/lang/String;
        //    38: astore_1       
        //    39: aload_0        
        //    40: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensionInLiteral:Z
        //    43: ifne            80
        //    46: aload_0        
        //    47: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensions:Ljava/util/Set;
        //    50: ifnull          80
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    59: athrow         
        //    60: aload_0        
        //    61: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensions:Ljava/util/Set;
        //    64: invokeinterface java/util/Set.size:()I
        //    69: iconst_1       
        //    70: if_icmpeq       127
        //    73: goto            80
        //    76: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    79: athrow         
        //    80: aload_1        
        //    81: dup            
        //    82: ifnonnull       126
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    91: athrow         
        //    92: new             Ljava/lang/IllegalStateException;
        //    95: dup            
        //    96: ldc             "@NotNull method %s.%s must not return null"
        //    98: ldc             2
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "getCanonicalText"
        //   114: aastore        
        //   115: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   118: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   121: athrow         
        //   122: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   125: athrow         
        //   126: areturn        
        //   127: new             Ljava/lang/StringBuilder;
        //   130: dup            
        //   131: invokespecial   java/lang/StringBuilder.<init>:()V
        //   134: aload_1        
        //   135: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   138: ldc             "."
        //   140: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   143: aload_0        
        //   144: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.myExtensions:Ljava/util/Set;
        //   147: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   152: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   157: checkcast       Ljava/lang/String;
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   166: dup            
        //   167: ifnonnull       204
        //   170: new             Ljava/lang/IllegalStateException;
        //   173: dup            
        //   174: ldc             "@NotNull method %s.%s must not return null"
        //   176: ldc             2
        //   178: anewarray       Ljava/lang/Object;
        //   181: dup            
        //   182: ldc             0
        //   184: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference"
        //   186: aastore        
        //   187: dup            
        //   188: ldc             1
        //   190: ldc             "getCanonicalText"
        //   192: aastore        
        //   193: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   196: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   199: athrow         
        //   200: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   203: athrow         
        //   204: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  39     53     56     60     Lcom/intellij/util/IncorrectOperationException;
        //  46     73     76     80     Lcom/intellij/util/IncorrectOperationException;
        //  60     85     88     92     Lcom/intellij/util/IncorrectOperationException;
        //  80     122    122    126    Lcom/intellij/util/IncorrectOperationException;
        //  127    200    200    204    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0060:
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
    
    public TextRange getRangeInElement() {
        try {
            if (OCElementUtil.getElementType(this.myElement) == OCTokenTypes.STRING_LITERAL) {
                return new TextRange(1, this.myElement.getTextLength() - 1);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return super.getRangeInElement();
    }
    
    @NotNull
    public Object[] getVariants() {
        Object[] empty_OBJECT_ARRAY;
        try {
            empty_OBJECT_ARRAY = ArrayUtil.EMPTY_OBJECT_ARRAY;
            if (empty_OBJECT_ARRAY == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference", "getVariants"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return empty_OBJECT_ARRAY;
    }
    
    @NotNull
    @Override
    public List<LookupElement> getLookupElements(final boolean b) {
        final ArrayList list = new ArrayList<LookupElement>();
        final HashSet set = new HashSet();
        ArrayList list2;
        try {
            this.a(null, null, (Processor<VirtualFile>)(virtualFile -> {
                try {
                    if (((Set)set).contains(virtualFile)) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                String s = null;
                Label_0050: {
                    try {
                        ((Set<VirtualFile>)set).add(virtualFile);
                        if (this.myExtensionInLiteral) {
                            s = virtualFile.getName();
                            break Label_0050;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    s = virtualFile.getNameWithoutExtension();
                }
                final String s2 = s;
                String string = null;
                Label_0090: {
                    try {
                        if (b) {
                            string = "@\"" + s2 + "\"";
                            break Label_0090;
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    string = s2;
                }
                final String s3 = string;
                list.add(OCCompletionPriority.elementWithPriority((LookupElement)LookupElementBuilder.create((Object)new ResolveResult() {
                    final /* synthetic */ VirtualFile val$virtualFile;
                    
                    public PsiElement getElement() {
                        return OCFileResourceReference.this.a(this.val$virtualFile);
                    }
                    
                    public boolean isValidResult() {
                        final PsiElement element = this.getElement();
                        return element != null && element.isValid();
                    }
                }, s3).withRenderer((LookupElementRenderer)new LookupElementRenderer<LookupElement>() {
                    final /* synthetic */ String val$text;
                    final /* synthetic */ VirtualFile val$virtualFile;
                    
                    public void renderElement(final LookupElement lookupElement, final LookupElementPresentation lookupElementPresentation) {
                        lookupElementPresentation.setIcon(AllIcons.FileTypes.UiForm);
                        lookupElementPresentation.setItemText(this.val$text);
                        lookupElementPresentation.setTypeText(this.val$virtualFile.getParent().getName());
                    }
                }), OCCompletionPriority.HIGHEST_PRIORITY));
                return true;
            }));
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference", "getLookupElements"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return (List<LookupElement>)list2;
    }
    
    private PsiElement a(final VirtualFile virtualFile) {
        final PsiManager manager = this.myElement.getManager();
        try {
            if (virtualFile.isDirectory()) {
                final Object o = manager.findDirectory(virtualFile);
                return (PsiElement)o;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final Object o = manager.findFile(virtualFile);
        return (PsiElement)o;
    }
    
    private boolean a(final VirtualFile virtualFile, @Nullable final String s, final List<String> list, final int n, final Processor<VirtualFile> processor) {
        Label_0083: {
            try {
                if (n >= list.size()) {
                    break Label_0083;
                }
                if (!virtualFile.isDirectory()) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final VirtualFile child = virtualFile.findChild((String)list.get(n));
            Label_0071: {
                try {
                    if (child == null) {
                        break Label_0071;
                    }
                    final OCFileResourceReference ocFileResourceReference = this;
                    final VirtualFile virtualFile2 = child;
                    final String s2 = s;
                    final List<String> list2 = list;
                    final int n2 = n;
                    final int n3 = 1;
                    final int n4 = n2 + n3;
                    final Processor<VirtualFile> processor2 = processor;
                    final boolean b = ocFileResourceReference.a(virtualFile2, s2, list2, n4, processor2);
                    if (b) {
                        break Label_0071;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCFileResourceReference ocFileResourceReference = this;
                    final VirtualFile virtualFile2 = child;
                    final String s2 = s;
                    final List<String> list2 = list;
                    final int n2 = n;
                    final int n3 = 1;
                    final int n4 = n2 + n3;
                    final Processor<VirtualFile> processor2 = processor;
                    final boolean b = ocFileResourceReference.a(virtualFile2, s2, list2, n4, processor2);
                    if (b) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return false;
        }
        final VirtualFileVisitor.Result visitChildrenRecursively = VfsUtilCore.visitChildrenRecursively(virtualFile, (VirtualFileVisitor)new VirtualFileVisitor(new VirtualFileVisitor.Option[0]) {
            @NotNull
            public VirtualFileVisitor.Result visitFileEx(@NotNull final VirtualFile p0) {
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
                //    18: ldc             "child"
                //    20: aastore        
                //    21: dup            
                //    22: ldc             1
                //    24: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3"
                //    26: aastore        
                //    27: dup            
                //    28: ldc             2
                //    30: ldc             "visitFileEx"
                //    32: aastore        
                //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
                //    39: athrow         
                //    40: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    43: athrow         
                //    44: aload_1        
                //    45: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isDirectory:()Z
                //    48: aload_0        
                //    49: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
                //    52: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$000:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Z
                //    55: if_icmpne       146
                //    58: aload_0        
                //    59: aload_1        
                //    60: invokespecial   com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Lcom/intellij/openapi/vfs/VirtualFile;)Z
                //    63: ifeq            146
                //    66: goto            73
                //    69: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    72: athrow         
                //    73: aload_0        
                //    74: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$processor:Lcom/intellij/util/Processor;
                //    77: aload_1        
                //    78: invokeinterface com/intellij/util/Processor.process:(Ljava/lang/Object;)Z
                //    83: ifne            146
                //    86: goto            93
                //    89: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    92: athrow         
                //    93: aload_0        
                //    94: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$file:Lcom/intellij/openapi/vfs/VirtualFile;
                //    97: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.skipTo:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
                //   100: dup            
                //   101: ifnonnull       145
                //   104: goto            111
                //   107: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   110: athrow         
                //   111: new             Ljava/lang/IllegalStateException;
                //   114: dup            
                //   115: ldc             "@NotNull method %s.%s must not return null"
                //   117: ldc             2
                //   119: anewarray       Ljava/lang/Object;
                //   122: dup            
                //   123: ldc             0
                //   125: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3"
                //   127: aastore        
                //   128: dup            
                //   129: ldc             1
                //   131: ldc             "visitFileEx"
                //   133: aastore        
                //   134: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   137: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                //   140: athrow         
                //   141: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   144: athrow         
                //   145: areturn        
                //   146: getstatic       com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.CONTINUE:Lcom/intellij/openapi/vfs/VirtualFileVisitor$Result;
                //   149: dup            
                //   150: ifnonnull       187
                //   153: new             Ljava/lang/IllegalStateException;
                //   156: dup            
                //   157: ldc             "@NotNull method %s.%s must not return null"
                //   159: ldc             2
                //   161: anewarray       Ljava/lang/Object;
                //   164: dup            
                //   165: ldc             0
                //   167: ldc             "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3"
                //   169: aastore        
                //   170: dup            
                //   171: ldc             1
                //   173: ldc             "visitFileEx"
                //   175: aastore        
                //   176: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
                //   179: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
                //   182: athrow         
                //   183: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   186: athrow         
                //   187: areturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  0      40     40     44     Ljava/lang/IllegalArgumentException;
                //  44     66     69     73     Ljava/lang/IllegalArgumentException;
                //  58     86     89     93     Ljava/lang/IllegalArgumentException;
                //  73     104    107    111    Ljava/lang/IllegalArgumentException;
                //  93     141    141    145    Ljava/lang/IllegalArgumentException;
                //  146    183    183    187    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
            
            private boolean a(final VirtualFile p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     0: aload_1        
                //     1: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getExtension:()Ljava/lang/String;
                //     4: astore_2       
                //     5: aload_0        
                //     6: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
                //     9: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$100:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Ljava/util/Set;
                //    12: ifnull          58
                //    15: aload_2        
                //    16: ifnull          52
                //    19: goto            26
                //    22: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    25: athrow         
                //    26: aload_0        
                //    27: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
                //    30: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$100:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Ljava/util/Set;
                //    33: aload_2        
                //    34: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
                //    37: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
                //    42: ifne            58
                //    45: goto            52
                //    48: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    51: athrow         
                //    52: iconst_0       
                //    53: ireturn        
                //    54: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    57: athrow         
                //    58: aload_0        
                //    59: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$fileName:Ljava/lang/String;
                //    62: ifnonnull       71
                //    65: iconst_1       
                //    66: ireturn        
                //    67: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    70: athrow         
                //    71: aload_1        
                //    72: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
                //    75: aload_0        
                //    76: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$fileName:Ljava/lang/String;
                //    79: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //    82: ifeq            91
                //    85: iconst_1       
                //    86: ireturn        
                //    87: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //    90: athrow         
                //    91: aload_0        
                //    92: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.this$0:Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;
                //    95: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference.access$100:(Lcom/jetbrains/cidr/lang/resolve/references/OCFileResourceReference;)Ljava/util/Set;
                //    98: ifnull          140
                //   101: aload_1        
                //   102: invokestatic    com/jetbrains/cidr/lang/OCGroupedFileNamings.getGroupedFileNaming:(Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/jetbrains/cidr/lang/OCGroupedFileNaming;
                //   105: astore_3       
                //   106: aload_3        
                //   107: ifnull          140
                //   110: aload_3        
                //   111: aload_1        
                //   112: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getNameWithoutExtension:()Ljava/lang/String;
                //   115: invokeinterface com/jetbrains/cidr/lang/OCGroupedFileNaming.getBaseName:(Ljava/lang/String;)Ljava/lang/String;
                //   120: astore          4
                //   122: aload           4
                //   124: aload_0        
                //   125: getfield        com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.val$fileName:Ljava/lang/String;
                //   128: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
                //   131: ifeq            140
                //   134: iconst_1       
                //   135: ireturn        
                //   136: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference$3.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
                //   139: athrow         
                //   140: iconst_0       
                //   141: ireturn        
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                                
                //  -----  -----  -----  -----  ------------------------------------
                //  5      19     22     26     Ljava/lang/IllegalArgumentException;
                //  15     45     48     52     Ljava/lang/IllegalArgumentException;
                //  26     54     54     58     Ljava/lang/IllegalArgumentException;
                //  58     67     67     71     Ljava/lang/IllegalArgumentException;
                //  71     87     87     91     Ljava/lang/IllegalArgumentException;
                //  122    136    136    140    Ljava/lang/IllegalArgumentException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1163)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1010)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        });
        try {
            if (visitChildrenRecursively.skipToParent == null) {
                return true;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return false;
    }
    
    private boolean a(@Nullable final String s, @Nullable final List<String> list, final Processor<VirtualFile> processor) {
        for (final VirtualFile virtualFile : OCResourceFilesProvider.getAccessibleResources(this.myElement)) {
            try {
                if (!this.a(virtualFile, s, Collections.emptyList(), 0, processor)) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            Label_0076: {
                try {
                    if (!virtualFile.isDirectory()) {
                        continue;
                    }
                    final List<String> list2 = list;
                    if (list2 != null) {
                        break Label_0076;
                    }
                    continue;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final List<String> list2 = list;
                    if (list2 == null) {
                        continue;
                    }
                    if (s == null) {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            int n = 0;
            while (true) {
                Label_0128: {
                    try {
                        if (n >= list.size()) {
                            break;
                        }
                        final OCFileResourceReference ocFileResourceReference = this;
                        final VirtualFile virtualFile2 = virtualFile;
                        final String s2 = s;
                        final List<String> list3 = list;
                        final int n2 = n;
                        final Processor<VirtualFile> processor2 = processor;
                        final boolean b = ocFileResourceReference.a(virtualFile2, s2, list3, n2, processor2);
                        if (!b) {
                            return false;
                        }
                        break Label_0128;
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final OCFileResourceReference ocFileResourceReference = this;
                        final VirtualFile virtualFile2 = virtualFile;
                        final String s2 = s;
                        final List<String> list3 = list;
                        final int n2 = n;
                        final Processor<VirtualFile> processor2 = processor;
                        final boolean b = ocFileResourceReference.a(virtualFile2, s2, list3, n2, processor2);
                        if (!b) {
                            return false;
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                ++n;
            }
        }
        return true;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        try {
            if (this.myExtensionInLiteral) {
                final String nameWithoutExtension = s;
                return super.handleElementRename(nameWithoutExtension);
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final String nameWithoutExtension = FileUtil.getNameWithoutExtension(s);
        return super.handleElementRename(nameWithoutExtension);
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCFileResourceReference", "bindToElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return psiElement;
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
