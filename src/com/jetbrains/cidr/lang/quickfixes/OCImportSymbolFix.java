// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import java.util.Set;
import java.awt.Color;
import com.intellij.ui.FileColorManager;
import com.intellij.util.ui.UIUtil;
import java.awt.Component;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.ui.speedSearch.SpeedSearchUtil;
import com.intellij.ui.SimpleTextAttributes;
import javax.swing.JList;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.intellij.ui.speedSearch.SpeedSearch;
import com.intellij.ui.ColoredListCellRenderer;
import javax.swing.JPanel;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.lang.OCIncludeHelpers;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContextUtil;
import com.jetbrains.cidr.lang.OCFileTypeHelpers;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.intellij.psi.util.PsiTreeUtil;
import javax.swing.ListCellRenderer;
import com.intellij.openapi.ui.popup.ListPopupStep;
import com.intellij.ui.popup.list.ListPopupImpl;
import javax.swing.Icon;
import com.intellij.openapi.ui.popup.PopupStep;
import com.intellij.openapi.ui.popup.util.BaseListPopupStep;
import com.jetbrains.cidr.lang.preprocessor.OCImportGraph;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTable;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import java.util.Map;
import com.jetbrains.cidr.lang.psi.OCClassPredeclaration;
import com.jetbrains.cidr.lang.psi.OCClassPredeclarationList;
import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.psi.OCCodeFragment;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.codeInsight.daemon.impl.ShowAutoImportPass;
import com.jetbrains.cidr.lang.OCLog;
import com.intellij.codeInsight.hint.HintManager;
import com.jetbrains.cidr.lang.settings.OCCodeInsightSettings;
import com.intellij.openapi.editor.Editor;
import java.util.Collections;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbols;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.impl.symbols.OCFileGlobalSymbolsCache;
import com.jetbrains.cidr.lang.autoImport.OCAutoImportHelper;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.symtable.OCFileSymbols;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import gnu.trove.THashSet;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCSymbolGroupContext;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import org.jetbrains.annotations.NotNull;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.codeInsight.intention.HighPriorityAction;
import com.intellij.codeInspection.HintAction;

public class OCImportSymbolFix implements HintAction, HighPriorityAction
{
    @Nullable
    private final PsiElement myElement;
    @Nullable
    private final OCFile myCurrentFile;
    private final boolean myForceIncludeMode;
    private final boolean myIgnoreFileScopeCheck;
    @NotNull
    private final List<AutoImportItem> myItems;
    
    public OCImportSymbolFix(@NotNull final OCReferenceElement ocReferenceElement) {
        if (ocReferenceElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "<init>"));
        }
        this(ocReferenceElement, null);
    }
    
    public OCImportSymbolFix(@NotNull final OCReferenceElement myElement, @Nullable OCSymbolGroupContext union) {
        if (myElement == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "reference", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "<init>"));
        }
        this.myItems = new ArrayList<AutoImportItem>();
        this.myElement = (PsiElement)myElement;
        this.myCurrentFile = myElement.getContainingOCFile();
        this.myForceIncludeMode = false;
        this.myIgnoreFileScopeCheck = false;
        if (union == null) {
            union = OCSymbolGroupContext.union(myElement.getSymbolContext(), OCSymbolKind.MACRO);
        }
        try {
            if (this.myCurrentFile == null || myElement.resolveToSymbol(union) != null) {
                return;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final THashSet set = new THashSet();
        final ArrayList<AutoImportItem> list = new ArrayList<AutoImportItem>();
        final Iterator<OCSymbol> iterator = myElement.resolveToOverloadsSymbols(union, true).iterator();
        while (iterator.hasNext()) {
            final OCSymbol a = a(iterator.next());
            if (a != null) {
                final VirtualFile containingFile = a.getContainingFile();
                Label_0226: {
                    try {
                        if (containingFile == null) {
                            continue;
                        }
                        final THashSet set2 = set;
                        final VirtualFile virtualFile = containingFile;
                        final boolean b = ((Set<VirtualFile>)set2).add(virtualFile);
                        if (b) {
                            break Label_0226;
                        }
                        continue;
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final THashSet set2 = set;
                        final VirtualFile virtualFile = containingFile;
                        final boolean b = ((Set<VirtualFile>)set2).add(virtualFile);
                        if (!b) {
                            continue;
                        }
                        this.a(a, null, this.myCurrentFile, false, list);
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
            }
        }
        this.myItems.addAll(a(list, this.myCurrentFile, this.myElement, false));
    }
    
    public OCImportSymbolFix(@Nullable final PsiElement psiElement, @Nullable final OCSymbol ocSymbol) {
        this(psiElement, ocSymbol, false);
    }
    
    public OCImportSymbolFix(@Nullable final PsiElement psiElement, @Nullable final OCSymbol ocSymbol, final boolean b) {
        this(psiElement, ocSymbol, b, true);
    }
    
    public OCImportSymbolFix(@Nullable final PsiElement psiElement, @Nullable final OCSymbol ocSymbol, final boolean b, final boolean b2) {
        this(psiElement, ocSymbol, b, b2, false);
    }
    
    public OCImportSymbolFix(@Nullable final PsiElement myElement, @Nullable final OCSymbol ocSymbol, final boolean myForceIncludeMode, final boolean b, final boolean myIgnoreFileScopeCheck) {
        this.myItems = new ArrayList<AutoImportItem>();
        this.myElement = myElement;
        this.myCurrentFile = ((myElement != null) ? ((OCFile)myElement.getContainingFile()) : null);
        this.myForceIncludeMode = myForceIncludeMode;
        this.myIgnoreFileScopeCheck = myIgnoreFileScopeCheck;
        if (ocSymbol != null) {
            final OCSymbol a = a(ocSymbol);
            Label_0081: {
                try {
                    if (this.myCurrentFile == null) {
                        return;
                    }
                    final OCSymbol ocSymbol2 = a;
                    if (ocSymbol2 != null) {
                        break Label_0081;
                    }
                    return;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCSymbol ocSymbol2 = a;
                    if (ocSymbol2 == null) {
                        return;
                    }
                    if (OCFileSymbols.isSymbolImported(this.myCurrentFile, a, myElement)) {
                        return;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            final ArrayList<AutoImportItem> list = new ArrayList<AutoImportItem>();
            this.a(a, this.b(a), this.myCurrentFile, b, list);
            this.myItems.addAll(a(list, this.myCurrentFile, this.myElement, false));
        }
    }
    
    private void a(@NotNull final OCSymbol ocSymbol, @Nullable final PsiElement psiElement, @NotNull final OCFile ocFile, final boolean b, @NotNull final List<AutoImportItem> list) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "toImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "collectPossibleItems"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "collectPossibleItems"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "collectPossibleItems"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        for (final VirtualFile virtualFile : a(ocSymbol, b, (PsiFile)ocFile)) {
            final OCAutoImportHelper.ImportSpecification a = a(virtualFile, (PsiFile)ocFile);
            try {
                if (a == null) {
                    continue;
                }
                list.add(new AutoImportItem(ocSymbol, psiElement, virtualFile, a));
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
    }
    
    @Nullable
    private static OCSymbol a(@Nullable final OCSymbol p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       10
        //     4: aconst_null    
        //     5: areturn        
        //     6: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //     9: athrow         
        //    10: aload_0        
        //    11: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getProject:()Lcom/intellij/openapi/project/Project;
        //    16: ifnonnull       25
        //    19: aconst_null    
        //    20: areturn        
        //    21: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    24: athrow         
        //    25: aload_0        
        //    26: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isPredeclaration:()Z
        //    31: ifeq            68
        //    34: aload_0        
        //    35: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    38: ifne            62
        //    41: goto            48
        //    44: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    47: athrow         
        //    48: aload_0        
        //    49: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    52: ifeq            68
        //    55: goto            62
        //    58: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    61: athrow         
        //    62: aconst_null    
        //    63: areturn        
        //    64: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    67: athrow         
        //    68: aload_0        
        //    69: instanceof      Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    72: ifeq            91
        //    75: aload_0        
        //    76: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolWithParent;
        //    79: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbolWithParent.getParent:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    84: goto            92
        //    87: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    90: athrow         
        //    91: aconst_null    
        //    92: astore_1       
        //    93: aload_1        
        //    94: ifnull          116
        //    97: aload_1        
        //    98: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   103: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   106: if_acmpne       145
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   115: athrow         
        //   116: aload_0        
        //   117: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   122: ifnonnull       140
        //   125: goto            132
        //   128: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aconst_null    
        //   133: goto            141
        //   136: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   139: athrow         
        //   140: aload_0        
        //   141: astore_0       
        //   142: goto            164
        //   145: aload_0        
        //   146: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCNamespaceSymbol;
        //   149: ifeq            159
        //   152: goto            164
        //   155: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   158: athrow         
        //   159: aload_1        
        //   160: astore_0       
        //   161: goto            68
        //   164: aload_0        
        //   165: ifnull          190
        //   168: aload_0        
        //   169: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //   174: ifne            190
        //   177: goto            184
        //   180: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   183: athrow         
        //   184: aconst_null    
        //   185: areturn        
        //   186: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   189: athrow         
        //   190: aload_0        
        //   191: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      6      6      10     Lcom/intellij/util/IncorrectOperationException;
        //  10     21     21     25     Lcom/intellij/util/IncorrectOperationException;
        //  25     41     44     48     Lcom/intellij/util/IncorrectOperationException;
        //  34     55     58     62     Lcom/intellij/util/IncorrectOperationException;
        //  48     64     64     68     Lcom/intellij/util/IncorrectOperationException;
        //  68     87     87     91     Lcom/intellij/util/IncorrectOperationException;
        //  93     109    112    116    Lcom/intellij/util/IncorrectOperationException;
        //  97     125    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  116    136    136    140    Lcom/intellij/util/IncorrectOperationException;
        //  145    155    155    159    Lcom/intellij/util/IncorrectOperationException;
        //  164    177    180    184    Lcom/intellij/util/IncorrectOperationException;
        //  168    186    186    190    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0048:
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
    
    @Nullable
    private PsiElement b(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "calculateInsertBefore"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        if (this.myElement != null) {
            final OCFile ocFile = (OCFile)this.myElement.getContainingFile();
            final OCFileGlobalSymbols forFile = OCFileGlobalSymbolsCache.getInstance(ocFile.getProject()).forFile(ocFile);
            Pair<OCSymbol, VirtualFile> pair = forFile.getUndefinedClasses().get(ocSymbol.getName());
            if (pair == null) {
                pair = forFile.getUndefinedProtocols().get(ocSymbol.getName());
            }
            try {
                if (pair != null) {
                    return (PsiElement)ocFile.findIncludeDirective((VirtualFile)pair.getSecond());
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    public boolean hasAutoImportItems() {
        try {
            if (!this.getAutoImportItems().isEmpty()) {
                return true;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return false;
    }
    
    private boolean a() {
        OCSymbolKind ocSymbolKind = null;
        final Iterator<AutoImportItem> iterator = this.getAutoImportItems().iterator();
        while (iterator.hasNext()) {
            final OCSymbolKind kind = iterator.next().mySymbolToImport.getKind();
            Label_0065: {
                try {
                    if (ocSymbolKind == null) {
                        break Label_0065;
                    }
                    final OCSymbolKind ocSymbolKind2 = ocSymbolKind;
                    final OCSymbolKind ocSymbolKind3 = kind;
                    if (ocSymbolKind2 != ocSymbolKind3) {
                        return true;
                    }
                    break Label_0065;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCSymbolKind ocSymbolKind2 = ocSymbolKind;
                    final OCSymbolKind ocSymbolKind3 = kind;
                    if (ocSymbolKind2 != ocSymbolKind3) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            ocSymbolKind = kind;
        }
        return false;
    }
    
    @NotNull
    public synchronized List<AutoImportItem> getAutoImportItems() {
        List<AutoImportItem> myItems;
        try {
            myItems = this.myItems;
            if (myItems == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getAutoImportItems"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return myItems;
    }
    
    @NotNull
    private static List<AutoImportItem> a(@NotNull final List<AutoImportItem> list, @NotNull final OCFile ocFile, @NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "items", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "prepareAutoImportItems"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "prepareAutoImportItems"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "prepareAutoImportItems"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final THashSet set = new THashSet();
        final ArrayList<Object> list2 = new ArrayList<Object>(list);
        final ArrayList<AutoImportItem> list3 = new ArrayList<AutoImportItem>(list.size());
        while (!list2.isEmpty()) {
            final AutoImportItem autoImportItem3 = list2.remove(0);
            final ImportStyle a = a(autoImportItem3, psiElement, b);
            Label_0259: {
                try {
                    if (!a(autoImportItem3, psiElement)) {
                        break Label_0259;
                    }
                    if (a == ImportStyle.INCLUDE) {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                if (autoImportItem3.mySymbolToImport.getOffset() <= psiElement.getTextOffset()) {
                    continue;
                }
                try {
                    if (autoImportItem3.mySymbolToImport instanceof OCImplementationSymbol) {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            final OCAutoImportHelper.ImportSpecification access$100 = autoImportItem3.a();
            String s = null;
            Label_0296: {
                try {
                    if (a == ImportStyle.INCLUDE) {
                        s = access$100.getImportText();
                        break Label_0296;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
                s = autoImportItem3.mySymbolToImport.getName();
            }
            final String s2 = s;
            Label_0330: {
                try {
                    if (!a((PsiFile)ocFile, s2, psiElement)) {
                        continue;
                    }
                    final THashSet set2 = set;
                    final AutoImportItem autoImportItem4 = autoImportItem3;
                    final VirtualFile virtualFile = autoImportItem4.getFileToImport();
                    final boolean b2 = ((Set<VirtualFile>)set2).add(virtualFile);
                    if (b2) {
                        break Label_0330;
                    }
                    continue;
                }
                catch (IncorrectOperationException ex7) {
                    throw a(ex7);
                }
                try {
                    final THashSet set2 = set;
                    final AutoImportItem autoImportItem4 = autoImportItem3;
                    final VirtualFile virtualFile = autoImportItem4.getFileToImport();
                    final boolean b2 = ((Set<VirtualFile>)set2).add(virtualFile);
                    if (!b2) {
                        continue;
                    }
                    list3.add(autoImportItem3);
                }
                catch (IncorrectOperationException ex8) {
                    throw a(ex8);
                }
            }
        }
        ArrayList<AutoImportItem> list4;
        try {
            final int n;
            final OCSymbolKind ocSymbolKind;
            final OCSymbolKind ocSymbolKind2;
            final int n2;
            final int n3;
            final int n4;
            final boolean b3;
            final boolean b4;
            final int n5;
            final int n6;
            final int n7;
            final int n8;
            final VirtualFile virtualFile2;
            final VirtualFile virtualFile3;
            Collections.sort((List<Object>)list3, (autoImportItem, autoImportItem2) -> {
                autoImportItem.getFileToImport();
                autoImportItem2.getFileToImport();
                Comparing.compare((Comparable)autoImportItem.a().getKind(), (Comparable)autoImportItem2.a().getKind());
                try {
                    if (n != 0) {
                        return n;
                    }
                }
                catch (IncorrectOperationException ex9) {
                    throw a(ex9);
                }
                autoImportItem.mySymbolToImport.getKind();
                autoImportItem2.mySymbolToImport.getKind();
                Comparing.compare(ocSymbolKind.isClass(), ocSymbolKind2.isClass());
                try {
                    if (n2 != 0) {
                        return -n2;
                    }
                }
                catch (IncorrectOperationException ex10) {
                    throw a(ex10);
                }
                Comparing.compare(ocSymbolKind.isStructLike(), ocSymbolKind2.isStructLike());
                try {
                    if (n3 != 0) {
                        return -n3;
                    }
                }
                catch (IncorrectOperationException ex11) {
                    throw a(ex11);
                }
                Comparing.compare(ocSymbolKind.isTypedefOrAlias(), ocSymbolKind2.isTypedefOrAlias());
                try {
                    if (n4 != 0) {
                        return -n4;
                    }
                }
                catch (IncorrectOperationException ex12) {
                    throw a(ex12);
                }
                Label_0153_1: {
                    try {
                        if (ocSymbolKind == OCSymbolKind.MACRO) {
                            break Label_0153_1;
                        }
                    }
                    catch (IncorrectOperationException ex13) {
                        throw a(ex13);
                    }
                    try {
                        if (ocSymbolKind2 == OCSymbolKind.MACRO) {
                            break Label_0153_1;
                        }
                    }
                    catch (IncorrectOperationException ex14) {
                        throw a(ex14);
                    }
                }
                Comparing.compare(b3, b4);
                try {
                    if (n5 != 0) {
                        return -n5;
                    }
                }
                catch (IncorrectOperationException ex15) {
                    throw a(ex15);
                }
                Comparing.compare(ocSymbolKind.isCallable(), ocSymbolKind2.isCallable());
                try {
                    if (n6 != 0) {
                        return -n6;
                    }
                }
                catch (IncorrectOperationException ex16) {
                    throw a(ex16);
                }
                Comparing.compare(ocSymbolKind.isVariable(), ocSymbolKind2.isVariable());
                try {
                    if (n7 != 0) {
                        return -n7;
                    }
                }
                catch (IncorrectOperationException ex17) {
                    throw a(ex17);
                }
                n8 = ocSymbolKind.ordinal() - ocSymbolKind2.ordinal();
                try {
                    if (n8 != 0) {
                        return n8;
                    }
                }
                catch (IncorrectOperationException ex18) {
                    throw a(ex18);
                }
                return StringUtil.compare(virtualFile2.getPath(), virtualFile3.getPath(), true);
            });
            list4 = list3;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "prepareAutoImportItems"));
            }
        }
        catch (IncorrectOperationException ex19) {
            throw a(ex19);
        }
        return list4;
    }
    
    public boolean showHint(@NotNull final Editor editor) {
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "showHint"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Logger log = null;
        boolean b3 = false;
        Label_0095: {
            Label_0076: {
                try {
                    if (!OCCodeInsightSettings.getInstance().SHOW_IMPORT_POPUP) {
                        return false;
                    }
                    final HintManager hintManager = HintManager.getInstance();
                    final boolean b = true;
                    final boolean b2 = hintManager.hasShownHintsThatWillHideByOtherHint(b);
                    if (b2) {
                        return false;
                    }
                    break Label_0076;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final HintManager hintManager = HintManager.getInstance();
                    final boolean b = true;
                    final boolean b2 = hintManager.hasShownHintsThatWillHideByOtherHint(b);
                    if (b2) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    log = OCLog.LOG;
                    if (this.myElement != null) {
                        b3 = true;
                        break Label_0095;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            b3 = false;
        }
        log.assertTrue(b3);
        final Pair<String, Boolean> text = this.getText(true);
        final String message = ShowAutoImportPass.getMessage((boolean)text.second, StringUtil.escapeXml((String)text.first));
        final TextRange rangeWithMacros = OCElementUtil.getRangeWithMacros(this.myElement);
        final PsiFile containingFile = this.myElement.getContainingFile();
        HintManager.getInstance().showQuestionHint(editor, message, rangeWithMacros.getStartOffset(), rangeWithMacros.getEndOffset(), () -> {
            try {
                if (editor == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "lambda$showHint$1"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            Label_0073: {
                try {
                    if (!this.myElement.isValid()) {
                        return true;
                    }
                    final OCImportSymbolFix ocImportSymbolFix = this;
                    final Project project2 = project;
                    final Editor editor2 = editor;
                    final PsiFile psiFile2 = containingFile;
                    final boolean b = ocImportSymbolFix.isAvailable(project2, editor2, psiFile2);
                    if (b) {
                        break Label_0073;
                    }
                    return true;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCImportSymbolFix ocImportSymbolFix = this;
                    final Project project2 = project;
                    final Editor editor2 = editor;
                    final PsiFile psiFile2 = containingFile;
                    final boolean b = ocImportSymbolFix.isAvailable(project2, editor2, psiFile2);
                    if (b) {
                        this.invoke(project, editor, containingFile);
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return true;
        });
        return true;
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Import symbol";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    public String getText() {
        String s2 = null;
        Label_0057: {
            String s = null;
            Label_0022: {
                try {
                    if (this.hasAutoImportItems()) {
                        break Label_0057;
                    }
                    final OCImportSymbolFix ocImportSymbolFix = this;
                    s = ocImportSymbolFix.getFamilyName();
                    if (s == null) {
                        break Label_0022;
                    }
                    return s;
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                try {
                    final OCImportSymbolFix ocImportSymbolFix = this;
                    s = ocImportSymbolFix.getFamilyName();
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getText"));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
            }
            return s;
            try {
                s2 = (String)this.getText(false).first;
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getText"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return s2;
    }
    
    @NotNull
    public Pair<String, Boolean> getText(final boolean b) {
        final List<AutoImportItem> autoImportItems = this.getAutoImportItems();
        String s2 = null;
        Label_0102: {
            Label_0082: {
                StringBuilder sb = null;
                String s = null;
                Label_0041: {
                    Label_0030: {
                        try {
                            if (!this.a()) {
                                break Label_0082;
                            }
                            sb = new StringBuilder();
                            final boolean b2 = b;
                            if (b2) {
                                break Label_0030;
                            }
                            break Label_0030;
                        }
                        catch (IncorrectOperationException ex) {
                            throw a(ex);
                        }
                        try {
                            sb = new StringBuilder();
                            final boolean b2 = b;
                            if (b2) {
                                s = "which";
                                break Label_0041;
                            }
                        }
                        catch (IncorrectOperationException ex2) {
                            throw a(ex2);
                        }
                    }
                    s = "symbol";
                }
                s2 = sb.append(s).append(" '").append(autoImportItems.get(0).mySymbolToImport.getName()).append("'").toString();
                break Label_0102;
            }
            s2 = (String)autoImportItems.get(0).getTitleAndLocation().first;
        }
        if (!b) {
            s2 = "Import " + s2;
        }
        if (autoImportItems.size() == 1) {
            s2 = s2 + " from " + (String)autoImportItems.get(0).getTitleAndLocation().second;
        }
        Pair create = null;
        Label_0198: {
            String s3;
            try {
                s3 = s2;
                if (autoImportItems.size() > 1) {
                    final boolean b3 = true;
                    break Label_0198;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            final boolean b3 = false;
            try {
                create = Pair.create((Object)s3, (Object)b3);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getText"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        return (Pair<String, Boolean>)create;
    }
    
    @NotNull
    private static String a(@NotNull final AutoImportItem autoImportItem, @NotNull final ImportStyle importStyle, @NotNull final PsiElement psiElement) {
        try {
            if (autoImportItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getTextToInsert"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (importStyle == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "importStyle", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getTextToInsert"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getTextToInsert"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        Label_0293: {
            String string = null;
            Label_0231: {
                StringBuilder sb = null;
                Label_0220: {
                    Label_0189: {
                        String s = null;
                        Label_0154: {
                            try {
                                if (psiElement.isValid()) {
                                    break Label_0189;
                                }
                                s = "";
                                if (s == null) {
                                    break Label_0154;
                                }
                                return s;
                            }
                            catch (IncorrectOperationException ex4) {
                                throw a(ex4);
                            }
                            try {
                                s = "";
                                if (s == null) {
                                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getTextToInsert"));
                                }
                            }
                            catch (IncorrectOperationException ex5) {
                                throw a(ex5);
                            }
                        }
                        return s;
                        try {
                            if (importStyle != ImportStyle.PREDECLARE) {
                                break Label_0293;
                            }
                            sb = new StringBuilder();
                            final AutoImportItem autoImportItem2 = autoImportItem;
                            final OCSymbol ocSymbol = autoImportItem2.mySymbolToImport;
                            final boolean b = ocSymbol instanceof OCProtocolSymbol;
                            if (b) {
                                break Label_0220;
                            }
                            break Label_0220;
                        }
                        catch (IncorrectOperationException ex6) {
                            throw a(ex6);
                        }
                    }
                    try {
                        sb = new StringBuilder();
                        final AutoImportItem autoImportItem2 = autoImportItem;
                        final OCSymbol ocSymbol = autoImportItem2.mySymbolToImport;
                        final boolean b = ocSymbol instanceof OCProtocolSymbol;
                        if (b) {
                            final String s2 = "@protocol ";
                            break Label_0231;
                        }
                    }
                    catch (IncorrectOperationException ex7) {
                        throw a(ex7);
                    }
                }
                final String s2 = "@class ";
                try {
                    string = sb.append(s2).append(autoImportItem.mySymbolToImport.getName()).append(";").toString();
                    if (string == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getTextToInsert"));
                    }
                }
                catch (IncorrectOperationException ex8) {
                    throw a(ex8);
                }
            }
            return string;
        }
        final PsiFile containingFile = psiElement.getContainingFile();
        String s3 = null;
        Label_0342: {
            Label_0331: {
                try {
                    if (!(containingFile instanceof OCFile)) {
                        break Label_0331;
                    }
                    final OCFile ocFile = (OCFile)containingFile;
                    final OCFile ocFile2 = ocFile;
                    final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                    final boolean b2 = ocLanguageKind.isObjC();
                    if (b2) {
                        break Label_0331;
                    }
                    break Label_0331;
                }
                catch (IncorrectOperationException ex9) {
                    throw a(ex9);
                }
                try {
                    final OCFile ocFile = (OCFile)containingFile;
                    final OCFile ocFile2 = ocFile;
                    final OCLanguageKind ocLanguageKind = ocFile2.getKind();
                    final boolean b2 = ocLanguageKind.isObjC();
                    if (b2) {
                        s3 = "#import";
                        break Label_0342;
                    }
                }
                catch (IncorrectOperationException ex10) {
                    throw a(ex10);
                }
            }
            s3 = "#include";
        }
        final String s4 = s3;
        String string2;
        try {
            string2 = s4 + " " + autoImportItem.a().getImportText();
            if (string2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getTextToInsert"));
            }
        }
        catch (IncorrectOperationException ex11) {
            throw a(ex11);
        }
        return string2;
    }
    
    @NotNull
    private static ImportStyle a(@NotNull final AutoImportItem p0, @NotNull final PsiElement p1, final boolean p2) {
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
        //    18: ldc             "item"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getImportStyle"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "element"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getImportStyle"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: iload_2        
        //    89: ifne            109
        //    92: aload_0        
        //    93: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem.access$000:(Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    96: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    99: ifne            158
        //   102: goto            109
        //   105: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   108: athrow         
        //   109: getstatic       com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle.INCLUDE:Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;
        //   112: dup            
        //   113: ifnonnull       157
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   122: athrow         
        //   123: new             Ljava/lang/IllegalStateException;
        //   126: dup            
        //   127: ldc             "@NotNull method %s.%s must not return null"
        //   129: ldc             2
        //   131: anewarray       Ljava/lang/Object;
        //   134: dup            
        //   135: ldc             0
        //   137: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   139: aastore        
        //   140: dup            
        //   141: ldc             1
        //   143: ldc             "getImportStyle"
        //   145: aastore        
        //   146: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   149: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   152: athrow         
        //   153: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   156: athrow         
        //   157: areturn        
        //   158: aload_1        
        //   159: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   164: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSuperClassRef;
        //   167: istore_3       
        //   168: aload_1        
        //   169: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   174: instanceof      Lcom/jetbrains/cidr/lang/psi/OCProtocolList;
        //   177: istore          4
        //   179: iload_3        
        //   180: ifne            195
        //   183: iload           4
        //   185: ifeq            244
        //   188: goto            195
        //   191: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   194: athrow         
        //   195: getstatic       com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle.INCLUDE:Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;
        //   198: dup            
        //   199: ifnonnull       243
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   208: athrow         
        //   209: new             Ljava/lang/IllegalStateException;
        //   212: dup            
        //   213: ldc             "@NotNull method %s.%s must not return null"
        //   215: ldc             2
        //   217: anewarray       Ljava/lang/Object;
        //   220: dup            
        //   221: ldc             0
        //   223: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   225: aastore        
        //   226: dup            
        //   227: ldc             1
        //   229: ldc             "getImportStyle"
        //   231: aastore        
        //   232: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   235: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   238: athrow         
        //   239: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   242: athrow         
        //   243: areturn        
        //   244: aload_0        
        //   245: aload_1        
        //   246: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem;Lcom/intellij/psi/PsiElement;)Z
        //   249: ifeq            301
        //   252: getstatic       com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle.PREDECLARE:Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;
        //   255: dup            
        //   256: ifnonnull       300
        //   259: goto            266
        //   262: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   265: athrow         
        //   266: new             Ljava/lang/IllegalStateException;
        //   269: dup            
        //   270: ldc             "@NotNull method %s.%s must not return null"
        //   272: ldc             2
        //   274: anewarray       Ljava/lang/Object;
        //   277: dup            
        //   278: ldc             0
        //   280: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   282: aastore        
        //   283: dup            
        //   284: ldc             1
        //   286: ldc             "getImportStyle"
        //   288: aastore        
        //   289: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   292: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   295: athrow         
        //   296: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   299: athrow         
        //   300: areturn        
        //   301: invokestatic    com/jetbrains/cidr/lang/settings/OCCodeInsightSettings.getInstance:()Lcom/jetbrains/cidr/lang/settings/OCCodeInsightSettings;
        //   304: getfield        com/jetbrains/cidr/lang/settings/OCCodeInsightSettings.HEADER_IMPORT_STYLE:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$HeaderImportStyle;
        //   307: getstatic       com/jetbrains/cidr/lang/settings/OCCodeStyleSettings$HeaderImportStyle.IMPORT:Lcom/jetbrains/cidr/lang/settings/OCCodeStyleSettings$HeaderImportStyle;
        //   310: if_acmpeq       337
        //   313: aload_1        
        //   314: invokeinterface com/intellij/psi/PsiElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //   319: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   322: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
        //   327: ifne            345
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   336: athrow         
        //   337: iconst_1       
        //   338: goto            346
        //   341: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   344: athrow         
        //   345: iconst_0       
        //   346: istore          5
        //   348: iload           5
        //   350: ifeq            363
        //   353: getstatic       com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle.INCLUDE:Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;
        //   356: goto            366
        //   359: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   362: athrow         
        //   363: getstatic       com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle.PREDECLARE:Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;
        //   366: dup            
        //   367: ifnonnull       404
        //   370: new             Ljava/lang/IllegalStateException;
        //   373: dup            
        //   374: ldc             "@NotNull method %s.%s must not return null"
        //   376: ldc             2
        //   378: anewarray       Ljava/lang/Object;
        //   381: dup            
        //   382: ldc             0
        //   384: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   386: aastore        
        //   387: dup            
        //   388: ldc             1
        //   390: ldc             "getImportStyle"
        //   392: aastore        
        //   393: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   396: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   399: athrow         
        //   400: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   403: athrow         
        //   404: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     102    105    109    Lcom/intellij/util/IncorrectOperationException;
        //  92     116    119    123    Lcom/intellij/util/IncorrectOperationException;
        //  109    153    153    157    Lcom/intellij/util/IncorrectOperationException;
        //  179    188    191    195    Lcom/intellij/util/IncorrectOperationException;
        //  183    202    205    209    Lcom/intellij/util/IncorrectOperationException;
        //  195    239    239    243    Lcom/intellij/util/IncorrectOperationException;
        //  244    259    262    266    Lcom/intellij/util/IncorrectOperationException;
        //  252    296    296    300    Lcom/intellij/util/IncorrectOperationException;
        //  301    330    333    337    Lcom/intellij/util/IncorrectOperationException;
        //  313    341    341    345    Lcom/intellij/util/IncorrectOperationException;
        //  348    359    359    363    Lcom/intellij/util/IncorrectOperationException;
        //  366    400    400    404    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0109:
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
    
    public boolean isAvailable(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0103: {
            Label_0071: {
                try {
                    if (!(psiFile instanceof OCFile)) {
                        return false;
                    }
                    final PsiFile psiFile2 = psiFile;
                    final boolean b = psiFile2 instanceof OCCodeFragment;
                    if (b) {
                        return false;
                    }
                    break Label_0071;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiFile psiFile2 = psiFile;
                    final boolean b = psiFile2 instanceof OCCodeFragment;
                    if (b) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    if (this.myElement == null) {
                        return false;
                    }
                    final OCImportSymbolFix ocImportSymbolFix = this;
                    final PsiElement psiElement = ocImportSymbolFix.myElement;
                    final boolean b2 = psiElement.isValid();
                    if (!b2) {
                        return false;
                    }
                    break Label_0103;
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            try {
                final OCImportSymbolFix ocImportSymbolFix = this;
                final PsiElement psiElement = ocImportSymbolFix.myElement;
                final boolean b2 = psiElement.isValid();
                if (!b2) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            try {
                if (this.myIgnoreFileScopeCheck) {
                    return this.hasAutoImportItems();
                }
                final OCImportSymbolFix ocImportSymbolFix2 = this;
                final PsiElement psiElement2 = ocImportSymbolFix2.myElement;
                final boolean b3 = OCSearchScope.isInProjectSources(psiElement2);
                if (!b3) {
                    return false;
                }
                return this.hasAutoImportItems();
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        try {
            final OCImportSymbolFix ocImportSymbolFix2 = this;
            final PsiElement psiElement2 = ocImportSymbolFix2.myElement;
            final boolean b3 = OCSearchScope.isInProjectSources(psiElement2);
            if (!b3) {
                return false;
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        return this.hasAutoImportItems();
    }
    
    private static boolean a(@NotNull final PsiFile psiFile, @NotNull final String s, @NotNull final PsiElement psiElement) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isImportRequired"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "textToInsert", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isImportRequired"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "unresolvedElement", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isImportRequired"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        for (final PsiElement psiElement2 : psiFile.getChildren()) {
            Label_0194: {
                try {
                    if (!(psiElement2 instanceof OCIncludeDirective)) {
                        break Label_0194;
                    }
                    final PsiElement psiElement3 = psiElement2;
                    final String s2 = psiElement3.getText();
                    final String s3 = s;
                    final boolean b = s2.contains(s3);
                    if (b) {
                        return false;
                    }
                    break Label_0194;
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
                try {
                    final PsiElement psiElement3 = psiElement2;
                    final String s2 = psiElement3.getText();
                    final String s3 = s;
                    final boolean b = s2.contains(s3);
                    if (b) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
            }
            if (psiElement2 instanceof OCClassPredeclarationList) {
                for (final OCClassPredeclaration ocClassPredeclaration : ((OCClassPredeclarationList)psiElement2).getPredeclarations()) {
                    try {
                        if (s.equals(ocClassPredeclaration.getName())) {
                            return false;
                        }
                        continue;
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                }
            }
            try {
                if (psiElement2.getTextOffset() >= psiElement.getTextOffset()) {
                    break;
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
        }
        return true;
    }
    
    private static boolean a(@NotNull final AutoImportItem autoImportItem, @NotNull final PsiElement psiElement) {
        try {
            if (autoImportItem == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "item", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isFromSameFile"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isFromSameFile"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        Label_0130: {
            try {
                if (!psiElement.isValid()) {
                    return false;
                }
                final AutoImportItem autoImportItem2 = autoImportItem;
                final OCSymbol ocSymbol = autoImportItem2.mySymbolToImport;
                final VirtualFile virtualFile = ocSymbol.getContainingFile();
                final PsiElement psiElement2 = psiElement;
                final PsiFile psiFile = psiElement2.getContainingFile();
                final VirtualFile virtualFile2 = psiFile.getVirtualFile();
                final boolean b = Comparing.equal((Object)virtualFile, (Object)virtualFile2);
                if (b) {
                    break Label_0130;
                }
                return false;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            try {
                final AutoImportItem autoImportItem2 = autoImportItem;
                final OCSymbol ocSymbol = autoImportItem2.mySymbolToImport;
                final VirtualFile virtualFile = ocSymbol.getContainingFile();
                final PsiElement psiElement2 = psiElement;
                final PsiFile psiFile = psiElement2.getContainingFile();
                final VirtualFile virtualFile2 = psiFile.getVirtualFile();
                final boolean b = Comparing.equal((Object)virtualFile, (Object)virtualFile2);
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
    
    private static boolean a(@NotNull final Project p0, @NotNull final VirtualFile p1, @NotNull final Map<VirtualFile, Boolean> p2, final boolean p3) {
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
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "accumulateImportCandidates"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "mainCandidate"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "accumulateImportCandidates"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: aload_2        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "acc"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "accumulateImportCandidates"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   131: athrow         
        //   132: aload_2        
        //   133: aload_1        
        //   134: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   139: ifeq            148
        //   142: iconst_0       
        //   143: ireturn        
        //   144: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   147: athrow         
        //   148: aload_2        
        //   149: aload_1        
        //   150: iload_3        
        //   151: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   154: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   159: pop            
        //   160: aload_0        
        //   161: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
        //   164: astore          4
        //   166: aload_0        
        //   167: invokestatic    com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;
        //   170: invokevirtual   com/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache.ensurePendingFilesProcessed:()V
        //   173: aload_1        
        //   174: aload_0        
        //   175: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/project/Project;)Ljava/util/Collection;
        //   178: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   183: astore          5
        //   185: aload           5
        //   187: invokeinterface java/util/Iterator.hasNext:()Z
        //   192: ifeq            315
        //   195: aload           5
        //   197: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   202: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   205: astore          6
        //   207: aload           6
        //   209: ifnull          312
        //   212: aload           6
        //   214: invokevirtual   com/intellij/openapi/vfs/VirtualFile.isValid:()Z
        //   217: ifeq            312
        //   220: goto            227
        //   223: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   226: athrow         
        //   227: aload           6
        //   229: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   232: invokestatic    com/jetbrains/cidr/lang/psi/impl/OCFileImpl.isHeaderFile:(Ljava/lang/String;)Z
        //   235: ifeq            312
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   244: athrow         
        //   245: aload_2        
        //   246: aload           6
        //   248: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   253: ifne            312
        //   256: goto            263
        //   259: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   262: athrow         
        //   263: iload_3        
        //   264: ifeq            299
        //   267: goto            274
        //   270: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   273: athrow         
        //   274: aload           4
        //   276: aload           6
        //   278: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/jetbrains/cidr/lang/symbols/symtable/FileSymbolTablesCache;Lcom/intellij/openapi/vfs/VirtualFile;)Z
        //   281: ifeq            299
        //   284: goto            291
        //   287: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   290: athrow         
        //   291: iconst_1       
        //   292: goto            300
        //   295: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   298: athrow         
        //   299: iconst_0       
        //   300: istore          7
        //   302: aload_0        
        //   303: aload           6
        //   305: aload_2        
        //   306: iload           7
        //   308: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Ljava/util/Map;Z)Z
        //   311: pop            
        //   312: goto            185
        //   315: iconst_1       
        //   316: ireturn        
        //    Signature:
        //  (Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Ljava/util/Map<Lcom/intellij/openapi/vfs/VirtualFile;Ljava/lang/Boolean;>;Z)Z
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  88     128    128    132    Lcom/intellij/util/IncorrectOperationException;
        //  132    144    144    148    Lcom/intellij/util/IncorrectOperationException;
        //  207    220    223    227    Lcom/intellij/util/IncorrectOperationException;
        //  212    238    241    245    Lcom/intellij/util/IncorrectOperationException;
        //  227    256    259    263    Lcom/intellij/util/IncorrectOperationException;
        //  245    267    270    274    Lcom/intellij/util/IncorrectOperationException;
        //  263    284    287    291    Lcom/intellij/util/IncorrectOperationException;
        //  274    295    295    299    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0227:
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
    
    private static boolean a(@NotNull final FileSymbolTablesCache fileSymbolTablesCache, @NotNull final VirtualFile virtualFile) {
        try {
            if (fileSymbolTablesCache == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cache", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isUmbrellaFile"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "includer", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "isUmbrellaFile"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final Ref ref = new Ref((Object)true);
        for (final FileSymbolTable fileSymbolTable : fileSymbolTablesCache.allTablesForFile(virtualFile)) {
            try {
                fileSymbolTable.shallowProcessSymbols((Processor<OCSymbol>)(p1 -> {
                    // 
                    // This method could not be decompiled.
                    // 
                    // Original Bytecode:
                    // 
                    //     0: aload_1        
                    //     1: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //     6: astore_2       
                    //     7: aload_2        
                    //     8: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    11: if_acmpeq       112
                    //    14: aload_2        
                    //    15: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.UNDEF_MACRO:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    18: if_acmpeq       112
                    //    21: goto            28
                    //    24: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    27: athrow         
                    //    28: aload_2        
                    //    29: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.SYMBOL_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    32: if_acmpeq       112
                    //    35: goto            42
                    //    38: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    41: athrow         
                    //    42: aload_2        
                    //    43: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_USING_SYMBOL:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    46: if_acmpeq       112
                    //    49: goto            56
                    //    52: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    55: athrow         
                    //    56: aload_2        
                    //    57: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.NAMESPACE_ALIAS:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    60: if_acmpeq       112
                    //    63: goto            70
                    //    66: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    69: athrow         
                    //    70: aload_2        
                    //    71: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.IMPORT:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    74: if_acmpeq       112
                    //    77: goto            84
                    //    80: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    83: athrow         
                    //    84: aload_2        
                    //    85: getstatic       com/jetbrains/cidr/lang/symbols/OCSymbolKind.TYPEDEF:Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
                    //    88: if_acmpeq       112
                    //    91: goto            98
                    //    94: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //    97: athrow         
                    //    98: aload_0        
                    //    99: iconst_0       
                    //   100: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
                    //   103: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
                    //   106: iconst_0       
                    //   107: ireturn        
                    //   108: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
                    //   111: athrow         
                    //   112: iconst_1       
                    //   113: ireturn        
                    //    Exceptions:
                    //  Try           Handler
                    //  Start  End    Start  End    Type                                           
                    //  -----  -----  -----  -----  -----------------------------------------------
                    //  7      21     24     28     Lcom/intellij/util/IncorrectOperationException;
                    //  14     35     38     42     Lcom/intellij/util/IncorrectOperationException;
                    //  28     49     52     56     Lcom/intellij/util/IncorrectOperationException;
                    //  42     63     66     70     Lcom/intellij/util/IncorrectOperationException;
                    //  56     77     80     84     Lcom/intellij/util/IncorrectOperationException;
                    //  70     91     94     98     Lcom/intellij/util/IncorrectOperationException;
                    //  84     108    108    112    Lcom/intellij/util/IncorrectOperationException;
                    // 
                    // The error that occurred was:
                    // 
                    // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
                if (!(boolean)ref.get()) {
                    break;
                }
                continue;
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return (boolean)ref.get();
    }
    
    @NotNull
    private static Collection<VirtualFile> a(@NotNull final VirtualFile virtualFile, @NotNull final Project project) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getIncludingFiles"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getIncludingFiles"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        Collection<VirtualFile> immediateIncludingFiles;
        try {
            immediateIncludingFiles = OCImportGraph.findImmediateIncludingFiles(project, virtualFile, false);
            if (immediateIncludingFiles == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getIncludingFiles"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return immediateIncludingFiles;
    }
    
    public void invoke(@NotNull final Project project, @NotNull final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (editor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "editor", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "invoke"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (this.getAutoImportItems().size() == 1) {
                this.getAutoImportItems().get(0).invoke(project, psiFile);
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        new ListPopupImpl(new BaseListPopupStep<AutoImportItem>("Symbol to Import", this.getAutoImportItems()) {
            public boolean isAutoSelectionEnabled() {
                return false;
            }
            
            public boolean isSpeedSearchEnabled() {
                return true;
            }
            
            public PopupStep onChosen(final AutoImportItem autoImportItem, final boolean b) {
                try {
                    if (autoImportItem == null) {
                        return OCImportSymbolFix$1.FINAL_CHOICE;
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    if (b) {
                        autoImportItem.invoke(project, psiFile);
                        return OCImportSymbolFix$1.FINAL_CHOICE;
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
                return OCImportSymbolFix$1.FINAL_CHOICE;
            }
            
            public boolean hasSubstep(final AutoImportItem autoImportItem) {
                return false;
            }
            
            @NotNull
            public String getTextFor(final AutoImportItem autoImportItem) {
                final Pair<String, String> titleAndLocation = autoImportItem.getTitleAndLocation();
                String string;
                try {
                    string = (String)titleAndLocation.first + " @ " + (String)titleAndLocation.second;
                    if (string == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$1", "getTextFor"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return string;
            }
            
            public Icon getIconFor(final AutoImportItem autoImportItem) {
                return autoImportItem.mySymbolToImport.getIcon();
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        }) {
            @Override
            protected ListCellRenderer getListElementRenderer() {
                return new MyRenderer(this.mySpeedSearch);
            }
        }.showInBestPositionFor(editor);
    }
    
    public static PsiElement addImportToFile(final OCFile p0, final String p1, @NotNull final ImportStyle p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_2        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "importStyle"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addImportToFile"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aconst_null    
        //    45: astore          4
        //    47: aconst_null    
        //    48: astore          5
        //    50: aconst_null    
        //    51: astore          6
        //    53: aconst_null    
        //    54: astore          7
        //    56: aconst_null    
        //    57: astore          8
        //    59: aload_1        
        //    60: aload_0        
        //    61: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.topLevelDeclarationFromText:(Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    64: astore          9
        //    66: aload_0        
        //    67: iconst_0       
        //    68: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getGuardIfndef:(Lcom/intellij/psi/PsiFile;Z)Lcom/jetbrains/cidr/lang/psi/OCDirective;
        //    71: astore          11
        //    73: aload           11
        //    75: ifnull          93
        //    78: aload           11
        //    80: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getNextNonWhitespaceSibling:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    83: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    88: astore          10
        //    90: goto            101
        //    93: aload_0        
        //    94: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    99: astore          10
        //   101: iconst_1       
        //   102: istore          12
        //   104: aconst_null    
        //   105: astore          13
        //   107: aload           10
        //   109: astore          14
        //   111: aload           14
        //   113: ifnull          280
        //   116: iload_3        
        //   117: iconst_m1      
        //   118: if_icmpeq       153
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   127: athrow         
        //   128: aload           14
        //   130: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   135: iload_3        
        //   136: if_icmplt       153
        //   139: goto            146
        //   142: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   145: athrow         
        //   146: goto            280
        //   149: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   152: athrow         
        //   153: iload           12
        //   155: ifeq            183
        //   158: aload           14
        //   160: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isEssentialNode:(Lcom/intellij/psi/PsiElement;)Z
        //   163: ifeq            179
        //   166: goto            173
        //   169: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   172: athrow         
        //   173: iconst_0       
        //   174: istore          12
        //   176: goto            183
        //   179: aload           14
        //   181: astore          13
        //   183: aload           14
        //   185: instanceof      Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   188: ifeq            256
        //   191: aload           14
        //   193: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   198: aload_1        
        //   199: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   202: ifeq            219
        //   205: goto            212
        //   208: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   211: athrow         
        //   212: aload           14
        //   214: areturn        
        //   215: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   218: athrow         
        //   219: aload           14
        //   221: astore          5
        //   223: aload           4
        //   225: ifnonnull       232
        //   228: aload           14
        //   230: astore          4
        //   232: aload           14
        //   234: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   237: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.isAngleBrackets:()Z
        //   242: ifeq            252
        //   245: aload           14
        //   247: astore          6
        //   249: goto            256
        //   252: aload           14
        //   254: astore          7
        //   256: aload           14
        //   258: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassPredeclarationList;
        //   261: ifeq            268
        //   264: aload           14
        //   266: astore          8
        //   268: aload           14
        //   270: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   275: astore          14
        //   277: goto            111
        //   280: aload           5
        //   282: astore          14
        //   284: iconst_0       
        //   285: istore          15
        //   287: aload_2        
        //   288: getstatic       com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle.INCLUDE:Lcom/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$ImportStyle;
        //   291: if_acmpne       355
        //   294: aload           9
        //   296: checkcast       Lcom/jetbrains/cidr/lang/psi/OCIncludeDirective;
        //   299: invokeinterface com/jetbrains/cidr/lang/psi/OCIncludeDirective.isAngleBrackets:()Z
        //   304: ifeq            343
        //   307: goto            314
        //   310: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   313: athrow         
        //   314: aload           6
        //   316: ifnull          333
        //   319: goto            326
        //   322: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   325: athrow         
        //   326: aload           6
        //   328: astore          14
        //   330: goto            368
        //   333: aload           4
        //   335: astore          14
        //   337: iconst_1       
        //   338: istore          15
        //   340: goto            368
        //   343: aload           7
        //   345: ifnull          368
        //   348: aload           7
        //   350: astore          14
        //   352: goto            368
        //   355: aload           8
        //   357: astore          14
        //   359: aload           14
        //   361: ifnonnull       368
        //   364: aload           5
        //   366: astore          14
        //   368: aload           14
        //   370: ifnonnull       380
        //   373: aload           13
        //   375: astore          14
        //   377: iconst_0       
        //   378: istore          15
        //   380: aload           14
        //   382: ifnonnull       396
        //   385: aload_0        
        //   386: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   391: astore          14
        //   393: iconst_1       
        //   394: istore          15
        //   396: aload_0        
        //   397: aload           9
        //   399: aload           14
        //   401: iload           15
        //   403: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Lcom/intellij/psi/PsiElement;
        //   406: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  111    121    124    128    Lcom/intellij/util/IncorrectOperationException;
        //  116    139    142    146    Lcom/intellij/util/IncorrectOperationException;
        //  128    149    149    153    Lcom/intellij/util/IncorrectOperationException;
        //  153    166    169    173    Lcom/intellij/util/IncorrectOperationException;
        //  183    205    208    212    Lcom/intellij/util/IncorrectOperationException;
        //  191    215    215    219    Lcom/intellij/util/IncorrectOperationException;
        //  287    307    310    314    Lcom/intellij/util/IncorrectOperationException;
        //  294    319    322    326    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0128:
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
    
    public boolean startInWriteAction() {
        return false;
    }
    
    public static void fixAtCaret(final Editor editor, final PsiFile psiFile, final OCSymbol ocSymbol) {
        try {
            if (!OCCodeInsightSettings.getInstance().ALLOW_IMPORT_IN_COMPLETION) {
                return;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final int a = a(editor);
        try {
            if (a >= 0) {
                fixSymbolAtOffset(editor, psiFile, a, ocSymbol);
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
    }
    
    public static void fixAtCaret(final Editor editor, final PsiFile psiFile, final OCSymbolGroupContext ocSymbolGroupContext) {
        try {
            if (!OCCodeInsightSettings.getInstance().ALLOW_IMPORT_IN_COMPLETION) {
                return;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final int a = a(editor);
        if (a >= 0) {
            final OCReferenceElement ocReferenceElement = (OCReferenceElement)PsiTreeUtil.findElementOfClassAtOffset(psiFile, a, (Class)OCReferenceElement.class, false);
            try {
                if (ocReferenceElement != null) {
                    new OCImportSymbolFix(ocReferenceElement, ocSymbolGroupContext).fixFirstItem(psiFile.getProject(), psiFile);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
    }
    
    private static int a(final Editor editor) {
        final CharSequence charsSequence = editor.getDocument().getCharsSequence();
        int offset = editor.getCaretModel().getOffset();
        try {
            if (offset >= charsSequence.length()) {
                return -1;
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        Label_0074: {
            while (true) {
                Label_0064: {
                    try {
                        if (offset <= 0) {
                            break Label_0074;
                        }
                        final CharSequence charSequence = charsSequence;
                        final int n = offset;
                        final char c = charSequence.charAt(n);
                        final boolean b = Character.isJavaIdentifierPart(c);
                        if (b) {
                            break Label_0064;
                        }
                        break Label_0074;
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final CharSequence charSequence = charsSequence;
                        final int n = offset;
                        final char c = charSequence.charAt(n);
                        final boolean b = Character.isJavaIdentifierPart(c);
                        if (b) {
                            --offset;
                            continue;
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                }
                break;
            }
            try {
                if (offset <= 0) {
                    return -1;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
        }
        Label_0118: {
            while (true) {
                Label_0108: {
                    try {
                        if (offset <= 0) {
                            break Label_0118;
                        }
                        final CharSequence charSequence2 = charsSequence;
                        final int n2 = offset;
                        final char c2 = charSequence2.charAt(n2);
                        final boolean b2 = Character.isWhitespace(c2);
                        if (b2) {
                            break Label_0108;
                        }
                        break Label_0118;
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final CharSequence charSequence2 = charsSequence;
                        final int n2 = offset;
                        final char c2 = charSequence2.charAt(n2);
                        final boolean b2 = Character.isWhitespace(c2);
                        if (b2) {
                            --offset;
                            continue;
                        }
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                }
                break;
            }
            try {
                if (offset <= 0) {
                    return -1;
                }
            }
            catch (IncorrectOperationException ex7) {
                throw a(ex7);
            }
        }
        --offset;
        Label_0165: {
            while (true) {
                Label_0155: {
                    try {
                        if (offset <= 0) {
                            break Label_0165;
                        }
                        final CharSequence charSequence3 = charsSequence;
                        final int n3 = offset;
                        final char c3 = charSequence3.charAt(n3);
                        final boolean b3 = Character.isWhitespace(c3);
                        if (b3) {
                            break Label_0155;
                        }
                        break Label_0165;
                    }
                    catch (IncorrectOperationException ex8) {
                        throw a(ex8);
                    }
                    try {
                        final CharSequence charSequence3 = charsSequence;
                        final int n3 = offset;
                        final char c3 = charSequence3.charAt(n3);
                        final boolean b3 = Character.isWhitespace(c3);
                        if (b3) {
                            --offset;
                            continue;
                        }
                    }
                    catch (IncorrectOperationException ex9) {
                        throw a(ex9);
                    }
                }
                break;
            }
            try {
                if (offset <= 0) {
                    return -1;
                }
            }
            catch (IncorrectOperationException ex10) {
                throw a(ex10);
            }
        }
        return offset;
    }
    
    public static void fixSymbolAtOffset(final Editor editor, final PsiFile psiFile, final int n, final OCSymbol ocSymbol) {
        new OCImportSymbolFix(PsiTreeUtil.findElementOfClassAtOffset(psiFile, n, (Class)OCElement.class, false), ocSymbol).fixFirstItem(psiFile.getProject(), psiFile);
    }
    
    public static void fixAllSymbolsRecursively(final PsiElement psiElement) {
        fixAllSymbolsRecursively(psiElement, null);
    }
    
    public static void fixAllSymbolsRecursively(final PsiElement psiElement, @Nullable final TextRange textRange) {
        final PsiFile containingFile = psiElement.getContainingFile();
        psiElement.accept((PsiElementVisitor)new OCRecursiveVisitor(textRange) {
            final /* synthetic */ Project val$project = containingFile.getProject();
            
            @Override
            public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
                new OCImportSymbolFix(ocReferenceElement).fixFirstItem(this.val$project, containingFile);
            }
        });
    }
    
    public boolean fixFirstItem(@NotNull final Project project, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "fixFirstItem"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "fixFirstItem"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        if (this.isAvailable(project, null, psiFile)) {
            final AutoImportItem autoImportItem = (AutoImportItem)ContainerUtil.getFirstItem((List)this.getAutoImportItems());
            try {
                if (autoImportItem != null) {
                    autoImportItem.invoke(project, psiFile);
                    return true;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
        }
        return false;
    }
    
    @Nullable
    private static OCAutoImportHelper.ImportSpecification a(@NotNull final VirtualFile virtualFile, @NotNull final PsiFile psiFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getFileNameToImport"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "currentFile", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix", "getFileNameToImport"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (!OCFileTypeHelpers.isHeaderFile(virtualFile.getName())) {
                return null;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final Ref create = Ref.create((Object)null);
        OCIncludeHelpers.processImportSpecifications(psiFile.getProject(), OCInclusionContextUtil.getResolveRootAndActiveConfiguration(psiFile.getVirtualFile(), psiFile.getProject()), psiFile.getVirtualFile(), virtualFile, (Processor<OCAutoImportHelper.ImportSpecification>)(importSpecification -> {
            create.set((Object)importSpecification);
            return false;
        }));
        return (OCAutoImportHelper.ImportSpecification)create.get();
    }
    
    @NotNull
    private static Collection<VirtualFile> a(@NotNull final OCSymbol p0, final boolean p1, @NotNull final PsiFile p2) {
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
        //    18: ldc             "symbol"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getFilesToImport"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
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
        //    62: ldc             "currentFile"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getFilesToImport"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    87: athrow         
        //    88: new             Lgnu/trove/THashMap;
        //    91: dup            
        //    92: invokespecial   gnu/trove/THashMap.<init>:()V
        //    95: astore_3       
        //    96: aload_0        
        //    97: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingOCFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //   102: astore          4
        //   104: aload           4
        //   106: ifnonnull       158
        //   109: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   112: dup            
        //   113: ifnonnull       157
        //   116: goto            123
        //   119: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   122: athrow         
        //   123: new             Ljava/lang/IllegalStateException;
        //   126: dup            
        //   127: ldc             "@NotNull method %s.%s must not return null"
        //   129: ldc             2
        //   131: anewarray       Ljava/lang/Object;
        //   134: dup            
        //   135: ldc             0
        //   137: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   139: aastore        
        //   140: dup            
        //   141: ldc             1
        //   143: ldc             "getFilesToImport"
        //   145: aastore        
        //   146: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   149: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   152: athrow         
        //   153: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   156: athrow         
        //   157: areturn        
        //   158: aload           4
        //   160: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   165: astore          5
        //   167: aload           5
        //   169: ifnull          194
        //   172: aload           4
        //   174: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   179: aload           5
        //   181: aload_3        
        //   182: iconst_1       
        //   183: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Ljava/util/Map;Z)Z
        //   186: pop            
        //   187: goto            194
        //   190: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   193: athrow         
        //   194: aload_0        
        //   195: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //   200: astore          6
        //   202: aload_0        
        //   203: aload_2        
        //   204: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCIncludeSuggester.getRecommendedHeaderNames:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;Lcom/intellij/psi/PsiFile;)Ljava/util/Collection;
        //   207: astore          7
        //   209: aload_3        
        //   210: aload           6
        //   212: aload           7
        //   214: invokedynamic   value:(Ljava/lang/String;Ljava/util/Collection;)Lcom/intellij/openapi/util/Condition;
        //   219: invokestatic    com/intellij/util/containers/ContainerUtil.filter:(Ljava/util/Map;Lcom/intellij/openapi/util/Condition;)Ljava/util/Map;
        //   222: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //   227: astore          8
        //   229: aload           8
        //   231: invokeinterface java/util/Set.isEmpty:()Z
        //   236: ifne            287
        //   239: aload           8
        //   241: dup            
        //   242: ifnonnull       286
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   251: athrow         
        //   252: new             Ljava/lang/IllegalStateException;
        //   255: dup            
        //   256: ldc             "@NotNull method %s.%s must not return null"
        //   258: ldc             2
        //   260: anewarray       Ljava/lang/Object;
        //   263: dup            
        //   264: ldc             0
        //   266: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   268: aastore        
        //   269: dup            
        //   270: ldc             1
        //   272: ldc             "getFilesToImport"
        //   274: aastore        
        //   275: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   278: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   281: athrow         
        //   282: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   285: athrow         
        //   286: areturn        
        //   287: new             Ljava/util/ArrayList;
        //   290: dup            
        //   291: invokespecial   java/util/ArrayList.<init>:()V
        //   294: astore          9
        //   296: aload_3        
        //   297: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   302: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   307: astore          10
        //   309: aload           10
        //   311: invokeinterface java/util/Iterator.hasNext:()Z
        //   316: ifeq            372
        //   319: aload           10
        //   321: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   326: checkcast       Ljava/util/Map$Entry;
        //   329: astore          11
        //   331: aload           11
        //   333: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   338: checkcast       Ljava/lang/Boolean;
        //   341: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   344: ifeq            369
        //   347: aload           9
        //   349: aload           11
        //   351: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   356: invokeinterface java/util/Collection.add:(Ljava/lang/Object;)Z
        //   361: pop            
        //   362: goto            369
        //   365: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   368: athrow         
        //   369: goto            309
        //   372: aload           4
        //   374: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getProject:()Lcom/intellij/openapi/project/Project;
        //   379: astore          10
        //   381: aconst_null    
        //   382: astore          11
        //   384: ldc             2147483647
        //   386: istore          12
        //   388: aload           9
        //   390: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   395: astore          13
        //   397: aload           13
        //   399: invokeinterface java/util/Iterator.hasNext:()Z
        //   404: ifeq            508
        //   407: aload           13
        //   409: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   414: checkcast       Lcom/intellij/openapi/vfs/VirtualFile;
        //   417: astore          14
        //   419: iload_1        
        //   420: ifne            467
        //   423: aload           14
        //   425: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   428: ldc             "_"
        //   430: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   433: ifeq            467
        //   436: goto            443
        //   439: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   442: athrow         
        //   443: aload           14
        //   445: aload           10
        //   447: invokestatic    com/jetbrains/cidr/lang/search/scopes/OCSearchScope.isInLibraries:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/openapi/project/Project;)Z
        //   450: ifeq            467
        //   453: goto            460
        //   456: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   459: athrow         
        //   460: goto            397
        //   463: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   466: athrow         
        //   467: aload           14
        //   469: aload_2        
        //   470: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/openapi/vfs/VirtualFile;Lcom/intellij/psi/PsiFile;)Lcom/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification;
        //   473: astore          15
        //   475: aload           15
        //   477: ifnull          505
        //   480: aload           15
        //   482: invokevirtual   com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification.getImportPath:()Ljava/lang/String;
        //   485: invokevirtual   java/lang/String.length:()I
        //   488: istore          16
        //   490: iload           16
        //   492: iload           12
        //   494: if_icmpge       505
        //   497: aload           14
        //   499: astore          11
        //   501: iload           16
        //   503: istore          12
        //   505: goto            397
        //   508: aload           11
        //   510: ifnonnull       523
        //   513: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   516: goto            528
        //   519: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   522: athrow         
        //   523: aload           11
        //   525: invokestatic    java/util/Collections.singletonList:(Ljava/lang/Object;)Ljava/util/List;
        //   528: dup            
        //   529: ifnonnull       566
        //   532: new             Ljava/lang/IllegalStateException;
        //   535: dup            
        //   536: ldc             "@NotNull method %s.%s must not return null"
        //   538: ldc             2
        //   540: anewarray       Ljava/lang/Object;
        //   543: dup            
        //   544: ldc             0
        //   546: ldc             "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix"
        //   548: aastore        
        //   549: dup            
        //   550: ldc             1
        //   552: ldc             "getFilesToImport"
        //   554: aastore        
        //   555: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   558: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   561: athrow         
        //   562: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix.a:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   565: athrow         
        //   566: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/symbols/OCSymbol;ZLcom/intellij/psi/PsiFile;)Ljava/util/Collection<Lcom/intellij/openapi/vfs/VirtualFile;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     84     84     88     Lcom/intellij/util/IncorrectOperationException;
        //  104    116    119    123    Lcom/intellij/util/IncorrectOperationException;
        //  109    153    153    157    Lcom/intellij/util/IncorrectOperationException;
        //  167    187    190    194    Lcom/intellij/util/IncorrectOperationException;
        //  229    245    248    252    Lcom/intellij/util/IncorrectOperationException;
        //  239    282    282    286    Lcom/intellij/util/IncorrectOperationException;
        //  331    362    365    369    Lcom/intellij/util/IncorrectOperationException;
        //  419    436    439    443    Lcom/intellij/util/IncorrectOperationException;
        //  423    453    456    460    Lcom/intellij/util/IncorrectOperationException;
        //  443    463    463    467    Lcom/intellij/util/IncorrectOperationException;
        //  508    519    519    523    Lcom/intellij/util/IncorrectOperationException;
        //  528    562    562    566    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0443:
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
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
    
    public enum ImportStyle
    {
        INCLUDE, 
        PREDECLARE;
    }
    
    public class AutoImportItem
    {
        @NotNull
        private final OCSymbol mySymbolToImport;
        @Nullable
        private final PsiElement myInsertBefore;
        @NotNull
        private final VirtualFile myFileToImport;
        @NotNull
        private final OCAutoImportHelper.ImportSpecification myFileNameToImport;
        @Nullable
        private Pair<String, String> myCachedTitleAndLocation;
        static final /* synthetic */ boolean $assertionsDisabled;
        
        public AutoImportItem(@Nullable final OCSymbol mySymbolToImport, @NotNull final PsiElement myInsertBefore, @NotNull final VirtualFile myFileToImport, final OCAutoImportHelper.ImportSpecification myFileNameToImport) {
            if (mySymbolToImport == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbolToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "<init>"));
            }
            if (myFileToImport == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "<init>"));
            }
            if (myFileNameToImport == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileNameToImport", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "<init>"));
            }
            this.mySymbolToImport = mySymbolToImport;
            this.myInsertBefore = myInsertBefore;
            this.myFileToImport = myFileToImport;
            this.myFileNameToImport = myFileNameToImport;
        }
        
        @Override
        public String toString() {
            final Pair<String, String> titleAndLocation = this.getTitleAndLocation();
            return (String)titleAndLocation.first + "@" + (String)titleAndLocation.second;
        }
        
        @NotNull
        public Pair<String, String> getTitleAndLocation() {
            Label_0057: {
                Pair<String, String> pair = null;
                Label_0022: {
                    try {
                        if (this.myCachedTitleAndLocation == null) {
                            break Label_0057;
                        }
                        final AutoImportItem autoImportItem = this;
                        pair = autoImportItem.myCachedTitleAndLocation;
                        if (pair == null) {
                            break Label_0022;
                        }
                        return pair;
                    }
                    catch (IncorrectOperationException ex) {
                        throw a(ex);
                    }
                    try {
                        final AutoImportItem autoImportItem = this;
                        pair = autoImportItem.myCachedTitleAndLocation;
                        if (pair == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getTitleAndLocation"));
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw a(ex2);
                    }
                }
                return pair;
            }
            String s = this.mySymbolToImport.getKindLowercase();
            if (this.mySymbolToImport instanceof OCClassSymbol) {
                s = "@" + s;
            }
            String s2 = this.mySymbolToImport.getName();
            if (this.mySymbolToImport instanceof OCSymbolWithQualifiedName) {
                final OCQualifiedName resolvedQualifiedName = ((OCSymbolWithQualifiedName)this.mySymbolToImport).getResolvedQualifiedName(true, false);
                if (resolvedQualifiedName != null) {
                    s2 = resolvedQualifiedName.getCanonicalName(true, false);
                }
            }
            Pair create;
            try {
                create = Pair.create((Object)(s + " '" + s2 + "'"), (Object)this.a().getImportText());
                if ((this.myCachedTitleAndLocation = (Pair<String, String>)create) == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getTitleAndLocation"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw a(ex3);
            }
            return (Pair<String, String>)create;
        }
        
        @NotNull
        private OCAutoImportHelper.ImportSpecification a() {
            OCAutoImportHelper.ImportSpecification myFileNameToImport;
            try {
                myFileNameToImport = this.myFileNameToImport;
                if (myFileNameToImport == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getFileSpecToImport"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return myFileNameToImport;
        }
        
        @NotNull
        public VirtualFile getFileToImport() {
            VirtualFile myFileToImport;
            try {
                myFileToImport = this.myFileToImport;
                if (myFileToImport == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "getFileToImport"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            return myFileToImport;
        }
        
        public void invoke(@NotNull final Project project, @NotNull final PsiFile psiFile) throws IncorrectOperationException {
            try {
                if (project == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "invoke"));
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            try {
                if (psiFile == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem", "invoke"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
            PsiDocumentManager.getInstance(project).commitAllDocuments();
            final VirtualFile fileToImport = this.getFileToImport();
            final VirtualFile virtualFile = psiFile.getVirtualFile();
            final OCAutoImportHelper[] array = (OCAutoImportHelper[])Extensions.getExtensions((ExtensionPointName)OCAutoImportHelper.EP_NAME);
            for (int length = array.length, i = 0; i < length; ++i) {
                array[i].addHeaderSearchPath(project, virtualFile, fileToImport);
            }
            final PsiElement access$200 = OCImportSymbolFix.this.myElement;
            Label_0185: {
                try {
                    if (AutoImportItem.$assertionsDisabled) {
                        break Label_0185;
                    }
                    final PsiElement psiElement = access$200;
                    if (psiElement == null) {
                        break Label_0185;
                    }
                    break Label_0185;
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                try {
                    final PsiElement psiElement = access$200;
                    if (psiElement == null) {
                        throw new AssertionError();
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw a(ex4);
                }
            }
            final ImportStyle access$201 = a(this, access$200, OCImportSymbolFix.this.myForceIncludeMode);
            OCFile associatedFile = null;
            Label_0237: {
                try {
                    if (access$201 == ImportStyle.PREDECLARE) {
                        associatedFile = ((OCFile)psiFile).getAssociatedFile();
                        break Label_0237;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw a(ex5);
                }
                associatedFile = null;
            }
            final OCFile ocFile = associatedFile;
            new WriteCommandAction(project, "Import " + (String)this.getTitleAndLocation().first, new PsiFile[] { psiFile, ocFile }) {
                public void run(@NotNull final Result result) throws Throwable {
                    try {
                        if (result == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$AutoImportItem$1", "run"));
                        }
                    }
                    catch (Throwable t) {
                        throw a(t);
                    }
                    int n = access$200.getTextOffset();
                    if (AutoImportItem.this.myInsertBefore != null) {
                        n = Math.min(n, AutoImportItem.this.myInsertBefore.getTextOffset());
                    }
                    try {
                        OCImportSymbolFix.addImportToFile((OCFile)psiFile, a(AutoImportItem.this, access$201, access$200), access$201, n);
                        if (ocFile != null) {
                            OCImportSymbolFix.addImportToFile(ocFile, a(AutoImportItem.this, ImportStyle.INCLUDE, access$200), ImportStyle.INCLUDE, n);
                        }
                    }
                    catch (Throwable t2) {
                        throw a(t2);
                    }
                }
                
                private static Throwable a(final Throwable t) {
                    return t;
                }
            }.execute();
        }
        
        static {
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!OCImportSymbolFix.class.desiredAssertionStatus()) {
                        $assertionsDisabled2 = true;
                        break Label_0017;
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                $assertionsDisabled2 = false;
            }
            $assertionsDisabled = $assertionsDisabled2;
        }
        
        private static IncorrectOperationException a(final IncorrectOperationException ex) {
            return ex;
        }
    }
    
    private static class MyRenderer extends JPanel implements ListCellRenderer<AutoImportItem>
    {
        final ColoredListCellRenderer<AutoImportItem> myLeft;
        final ColoredListCellRenderer<AutoImportItem> myRight;
        
        public MyRenderer(final SpeedSearch speedSearch) {
            super(new BorderLayout());
            this.myLeft = new ColoredListCellRenderer<AutoImportItem>() {
                protected void customizeCellRenderer(@NotNull final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
                    try {
                        if (list == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$MyRenderer$1", "customizeCellRenderer"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    MyRenderer.this.myLeft.clear();
                    final Pair<String, String> titleAndLocation = autoImportItem.getTitleAndLocation();
                    MyRenderer.this.myLeft.setIcon(autoImportItem.mySymbolToImport.getIcon());
                    SpeedSearchUtil.appendColoredFragmentForMatcher((String)titleAndLocation.first, (SimpleColoredComponent)this, SimpleTextAttributes.REGULAR_ATTRIBUTES, speedSearch.getMatcher(), MyRenderer.this.getBackground(), b);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            this.myRight = new ColoredListCellRenderer<AutoImportItem>() {
                protected void customizeCellRenderer(@NotNull final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
                    try {
                        if (list == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "list", "com/jetbrains/cidr/lang/quickfixes/OCImportSymbolFix$MyRenderer$2", "customizeCellRenderer"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    SpeedSearchUtil.appendColoredFragmentForMatcher((String)autoImportItem.getTitleAndLocation().second, (SimpleColoredComponent)this, SimpleTextAttributes.GRAY_ATTRIBUTES, speedSearch.getMatcher(), MyRenderer.this.getBackground(), b);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            };
            this.add((Component)this.myLeft, "Center");
            this.add((Component)this.myRight, "East");
        }
        
        @Override
        public Component getListCellRendererComponent(final JList<? extends AutoImportItem> list, final AutoImportItem autoImportItem, final int n, final boolean b, final boolean b2) {
            Color background = b ? UIUtil.getListSelectionBackground() : UIUtil.getListBackground();
            if (!b && autoImportItem instanceof AutoImportItem) {
                final PsiFile containingPsiFile = autoImportItem.mySymbolToImport.getContainingPsiFile();
                if (containingPsiFile != null) {
                    final FileColorManager instance = FileColorManager.getInstance(containingPsiFile.getProject());
                    if (instance.isEnabled()) {
                        final Color rendererBackground = instance.getRendererBackground(containingPsiFile);
                        if (rendererBackground != null) {
                            background = rendererBackground;
                        }
                    }
                }
            }
            this.setBackground(background);
            this.myLeft.getListCellRendererComponent((JList)list, (Object)autoImportItem, n, b, false);
            this.myRight.getListCellRendererComponent((JList)list, (Object)autoImportItem, n, b, false);
            return this;
        }
    }
}
