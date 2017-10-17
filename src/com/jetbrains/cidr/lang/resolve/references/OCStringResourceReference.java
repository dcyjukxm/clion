// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.resolve.references;

import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import java.util.Map;
import java.util.Set;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.openapi.util.TextRange;
import com.intellij.util.IncorrectOperationException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import com.jetbrains.cidr.lang.editor.completion.OCCompletionPriority;
import com.intellij.icons.AllIcons;
import com.intellij.util.ui.PlatformColors;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.codeInsight.lookup.LookupElementRenderer;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import java.util.HashMap;
import java.util.ArrayList;
import com.intellij.codeInsight.lookup.LookupElement;
import java.util.List;
import com.intellij.util.Processor;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.jetbrains.cidr.lang.psi.OCStringsFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;

public class OCStringResourceReference extends PsiCachingReferenceBase<PsiElement> implements OCResourceReference
{
    public static final String DEFAULT_TABLE = "Localizable";
    private String myTable;
    
    public OCStringResourceReference(final PsiElement psiElement, @Nullable final String myTable) {
        super(psiElement, false);
        this.myTable = myTable;
    }
    
    @Nullable
    public String getTableFileName() {
        try {
            if (this.myTable != null) {
                return this.myTable + ".strings";
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @NotNull
    public String getCanonicalText() {
        String unquoteString = null;
        Label_0071: {
            String s2 = null;
            Label_0036: {
                try {
                    if (!(this.myElement instanceof OCLiteralExpression)) {
                        break Label_0071;
                    }
                    final OCStringResourceReference ocStringResourceReference = this;
                    final PsiElement psiElement = ocStringResourceReference.myElement;
                    final OCLiteralExpression ocLiteralExpression = (OCLiteralExpression)psiElement;
                    final String s = ocLiteralExpression.getRawLiteralText();
                    s2 = StringUtil.unquoteString(s);
                    if (s2 == null) {
                        break Label_0036;
                    }
                    return s2;
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                try {
                    final OCStringResourceReference ocStringResourceReference = this;
                    final PsiElement psiElement = ocStringResourceReference.myElement;
                    final OCLiteralExpression ocLiteralExpression = (OCLiteralExpression)psiElement;
                    final String s = ocLiteralExpression.getRawLiteralText();
                    s2 = StringUtil.unquoteString(s);
                    if (s2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference", "getCanonicalText"));
                    }
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
            return s2;
            try {
                unquoteString = StringUtil.unquoteString(this.myElement.getText());
                if (unquoteString == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference", "getCanonicalText"));
                }
            }
            catch (UnsupportedOperationException ex3) {
                throw b(ex3);
            }
        }
        return unquoteString;
    }
    
    private static int a(final OCStringsFile p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCStringsFile.getLocalizationName:()Ljava/lang/String;
        //     6: invokevirtual   java/lang/String.toLowerCase:()Ljava/lang/String;
        //     9: astore_1       
        //    10: aload_1        
        //    11: ldc             "en"
        //    13: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    16: ifne            51
        //    19: aload_1        
        //    20: ldc             "english"
        //    22: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    25: ifne            51
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    34: athrow         
        //    35: aload_1        
        //    36: ldc             "default"
        //    38: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    41: ifeq            59
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    50: athrow         
        //    51: iconst_1       
        //    52: goto            60
        //    55: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    58: athrow         
        //    59: iconst_0       
        //    60: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  10     28     31     35     Ljava/lang/UnsupportedOperationException;
        //  19     44     47     51     Ljava/lang/UnsupportedOperationException;
        //  35     55     55     59     Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
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
    
    public OCLocalizedString resolveInner() {
        final String canonicalText = this.getCanonicalText();
        final Ref create = Ref.create((Object)null);
        this.processStringFiles((Processor<OCStringsFile>)(p2 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_2        
            //     1: aload_0        
            //     2: invokeinterface com/jetbrains/cidr/lang/psi/OCStringsFile.findStringPair:(Ljava/lang/String;)Lcom/jetbrains/cidr/lang/psi/OCLocalizedString;
            //     7: astore_3       
            //     8: aload_3        
            //     9: ifnull          52
            //    12: aload_1        
            //    13: invokevirtual   com/intellij/openapi/util/Ref.isNull:()Z
            //    16: ifne            40
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    25: athrow         
            //    26: aload_2        
            //    27: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.a:(Lcom/jetbrains/cidr/lang/psi/OCStringsFile;)I
            //    30: ifle            52
            //    33: goto            40
            //    36: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    39: athrow         
            //    40: aload_1        
            //    41: aload_3        
            //    42: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
            //    45: goto            52
            //    48: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    51: athrow         
            //    52: iconst_1       
            //    53: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                     
            //  -----  -----  -----  -----  -----------------------------------------
            //  8      19     22     26     Ljava/lang/UnsupportedOperationException;
            //  12     33     36     40     Ljava/lang/UnsupportedOperationException;
            //  26     45     48     52     Ljava/lang/UnsupportedOperationException;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }));
        return (OCLocalizedString)create.get();
    }
    
    @NotNull
    public Object[] getVariants() {
        Object[] array;
        try {
            array = new Object[0];
            if (array == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference", "getVariants"));
            }
        }
        catch (UnsupportedOperationException ex) {
            throw b(ex);
        }
        return array;
    }
    
    @NotNull
    @Override
    public List<LookupElement> getLookupElements(final boolean b) {
        final ArrayList<LookupElement> list = new ArrayList<LookupElement>();
        final HashMap hashMap = new HashMap();
        final HashMap<Object, OCLocalizedString> hashMap2 = new HashMap<Object, OCLocalizedString>();
        this.processStringFiles((Processor<OCStringsFile>)(ocStringsFile -> {
            for (final OCLocalizedString ocLocalizedString : ocStringsFile.getStringPairs()) {
                final int a = a(ocStringsFile);
                final Integer n = hashMap.get(ocLocalizedString.getKey());
                Label_0073: {
                    try {
                        if (n == null) {
                            break Label_0073;
                        }
                        final Integer n2 = n;
                        final int n3 = n2;
                        final int n4 = a;
                        if (n3 < n4) {
                            break Label_0073;
                        }
                        break Label_0073;
                    }
                    catch (UnsupportedOperationException ex) {
                        throw b(ex);
                    }
                    try {
                        final Integer n2 = n;
                        final int n3 = n2;
                        final int n4 = a;
                        if (n3 < n4) {
                            hashMap.put(ocLocalizedString.getKey(), a);
                            hashMap2.put(ocLocalizedString.getKey(), ocLocalizedString);
                        }
                    }
                    catch (UnsupportedOperationException ex2) {
                        throw b(ex2);
                    }
                }
            }
            return true;
        }));
        for (final OCLocalizedString ocLocalizedString : hashMap2.values()) {
            String s = null;
            Label_0122: {
                try {
                    if (b) {
                        s = "@\"" + ocLocalizedString.getKey() + "\"";
                        break Label_0122;
                    }
                }
                catch (UnsupportedOperationException ex) {
                    throw b(ex);
                }
                s = ocLocalizedString.getKey();
            }
            final String s2 = s;
            list.add(OCCompletionPriority.elementWithPriority((LookupElement)LookupElementBuilder.create(((OCSymbolDeclarator<Object>)ocLocalizedString).getSymbol(), s2).withRenderer((LookupElementRenderer)new LookupElementRenderer<LookupElement>() {
                public void renderElement(final LookupElement lookupElement, final LookupElementPresentation lookupElementPresentation) {
                    lookupElementPresentation.setItemText(s2);
                    lookupElementPresentation.setTailText("=" + ocLocalizedString.getValue(), PlatformColors.BLUE);
                    lookupElementPresentation.setTypeText(((OCStringsFile)ocLocalizedString.getContainingFile()).getLocalizationName(), AllIcons.FileTypes.Properties);
                }
            }), OCCompletionPriority.HIGHEST_PRIORITY));
        }
        ArrayList<LookupElement> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference", "getLookupElements"));
            }
        }
        catch (UnsupportedOperationException ex2) {
            throw b(ex2);
        }
        return list2;
    }
    
    public boolean processStringFiles(final Processor<OCStringsFile> processor) {
        final String tableFileName = this.getTableFileName();
        for (final VirtualFile virtualFile : OCResourceFilesProvider.getAccessibleResources(this.myElement)) {
            try {
                if (tableFileName != null) {
                    if (!virtualFile.getName().equals(tableFileName)) {
                        continue;
                    }
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            final PsiFile file = this.myElement.getManager().findFile(virtualFile);
            if (file instanceof OCStringsFile) {
                final OCStringsFile ocStringsFile = (OCStringsFile)file;
                try {
                    if (!processor.process((Object)ocStringsFile)) {
                        return false;
                    }
                    continue;
                }
                catch (UnsupportedOperationException ex2) {
                    throw b(ex2);
                }
            }
        }
        return true;
    }
    
    public boolean areAllLanguagesLocalized() {
        return this.getNotLocalizedLanguages().isEmpty();
    }
    
    public List<String> getNotLocalizedLanguages() {
        final HashSet<Object> set = new HashSet<Object>();
        final HashSet<String> set2 = new HashSet<String>();
        this.processStringFiles((Processor<OCStringsFile>)(ocStringsFile -> {
            try {
                if (ocStringsFile.findStringPair(s) == null) {
                    set2.add(ocStringsFile.getLocalizationName());
                    return true;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            set.add(ocStringsFile.getLocalizationName());
            return true;
        }));
        set2.removeAll(set);
        if (set2.size() > 3) {
            final Iterator<Object> iterator = set2.iterator();
            return Arrays.asList(iterator.next(), iterator.next(), iterator.next());
        }
        return new ArrayList<String>(set2);
    }
    
    public PsiElement bindToElement(@NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference", "bindToElement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw b((RuntimeException)ex);
        }
        throw new UnsupportedOperationException();
    }
    
    public PsiElement getElement() {
        return this.myElement;
    }
    
    public TextRange getRangeInElement() {
        final PsiElement a = this.a();
        if (a != null) {
            final int n = a.getTextOffset() - this.myElement.getTextOffset();
            return new TextRange(n + 1, n + a.getTextLength() - 1);
        }
        return new TextRange(0, this.myElement.getTextLength());
    }
    
    @Nullable
    private PsiElement a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.myElement:Lcom/intellij/psi/PsiElement;
        //     4: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //     9: astore_1       
        //    10: aload_0        
        //    11: getfield        com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.myElement:Lcom/intellij/psi/PsiElement;
        //    14: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl;
        //    17: ifeq            59
        //    20: aload_0        
        //    21: getfield        com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.myElement:Lcom/intellij/psi/PsiElement;
        //    24: checkcast       Lcom/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl;
        //    27: invokevirtual   com/jetbrains/cidr/lang/psi/impl/OCLiteralExpressionImpl.findStringLiteralNodes:()Ljava/util/List;
        //    30: astore_2       
        //    31: aload_2        
        //    32: invokeinterface java/util/List.isEmpty:()Z
        //    37: ifeq            48
        //    40: aconst_null    
        //    41: goto            58
        //    44: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    47: athrow         
        //    48: aload_2        
        //    49: iconst_0       
        //    50: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    55: checkcast       Lcom/intellij/lang/ASTNode;
        //    58: astore_1       
        //    59: aload_1        
        //    60: ifnull          131
        //    63: aload_1        
        //    64: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    69: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.STRING_LITERAL:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    72: if_acmpne       131
        //    75: goto            82
        //    78: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    81: athrow         
        //    82: aload_1        
        //    83: invokeinterface com/intellij/lang/ASTNode.getStartOffset:()I
        //    88: ifgt            120
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    97: athrow         
        //    98: aload_0        
        //    99: getfield        com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.myElement:Lcom/intellij/psi/PsiElement;
        //   102: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   107: instanceof      Lcom/jetbrains/cidr/lang/psi/OCStringsFile;
        //   110: ifeq            131
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   119: athrow         
        //   120: aload_1        
        //   121: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   126: areturn        
        //   127: invokestatic    com/jetbrains/cidr/lang/resolve/references/OCStringResourceReference.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   130: athrow         
        //   131: aconst_null    
        //   132: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  31     44     44     48     Ljava/lang/UnsupportedOperationException;
        //  59     75     78     82     Ljava/lang/UnsupportedOperationException;
        //  63     91     94     98     Ljava/lang/UnsupportedOperationException;
        //  82     113    116    120    Ljava/lang/UnsupportedOperationException;
        //  98     127    127    131    Ljava/lang/UnsupportedOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0082:
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
    
    public boolean isReferenceTo(final PsiElement psiElement) {
        Label_0039: {
            try {
                if (!(psiElement instanceof OCLocalizedString)) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final OCLocalizedString ocLocalizedString = (OCLocalizedString)psiElement2;
                final String s = ocLocalizedString.getKey();
                final OCStringResourceReference ocStringResourceReference = this;
                final String s2 = ocStringResourceReference.getCanonicalText();
                final boolean b = s.equals(s2);
                if (!b) {
                    return false;
                }
                break Label_0039;
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final OCLocalizedString ocLocalizedString = (OCLocalizedString)psiElement2;
                final String s = ocLocalizedString.getKey();
                final OCStringResourceReference ocStringResourceReference = this;
                final String s2 = ocStringResourceReference.getCanonicalText();
                final boolean b = s.equals(s2);
                if (!b) {
                    return false;
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
        }
        final String tableFileName = this.getTableFileName();
        final PsiFile containingFile = psiElement.getContainingFile();
        try {
            if (!(containingFile instanceof OCStringsFile)) {
                return false;
            }
        }
        catch (UnsupportedOperationException ex3) {
            throw b(ex3);
        }
        try {
            if (!OCResourceFilesProvider.isAccessible(this.myElement, containingFile.getVirtualFile())) {
                return false;
            }
        }
        catch (UnsupportedOperationException ex4) {
            throw b(ex4);
        }
        final String name = containingFile.getName();
        Label_0114: {
            try {
                if (tableFileName == null) {
                    break Label_0114;
                }
                final String s3 = tableFileName;
                final String s4 = name;
                final boolean b2 = s3.equals(s4);
                if (b2) {
                    break Label_0114;
                }
                return false;
            }
            catch (UnsupportedOperationException ex5) {
                throw b(ex5);
            }
            try {
                final String s3 = tableFileName;
                final String s4 = name;
                final boolean b2 = s3.equals(s4);
                if (b2) {
                    return true;
                }
            }
            catch (UnsupportedOperationException ex6) {
                throw b(ex6);
            }
        }
        return false;
    }
    
    public PsiElement handleElementRename(final String s) throws IncorrectOperationException {
        final PsiElement a = this.a();
        Label_0098: {
            try {
                if (a == null || !(a.getParent() instanceof OCLiteralExpression)) {
                    break Label_0098;
                }
            }
            catch (IncorrectOperationException ex) {
                throw b((RuntimeException)ex);
            }
            FileSymbolTablesCache.getInstance(this.myElement.getProject()).scheduleReparseFile((OCFile)this.myElement.getContainingFile());
            return OCChangeUtil.replaceHandlingMacros(a.getParent(), (PsiElement)OCElementFactory.expressionFromText("@\"" + s + "\"", this.myElement));
            try {
                assert false : this.myElement.getClass();
            }
            catch (IncorrectOperationException ex2) {
                throw b((RuntimeException)ex2);
            }
        }
        return this.myElement;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCStringResourceReference.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
