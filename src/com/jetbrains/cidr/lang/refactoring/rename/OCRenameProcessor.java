// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.rename;

import com.jetbrains.cidr.lang.symbols.OCSymbolBase;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Comparing;
import com.intellij.refactoring.util.NonCodeUsageInfo;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.refactoring.OCRenameProcessorExtension;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCLocalizedString;
import com.intellij.openapi.extensions.ExtensionPointName;
import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.openapi.editor.Editor;
import com.intellij.util.containers.MultiMap;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.psi.PsiNamedElement;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.intellij.refactoring.listeners.RefactoringElementListener;
import com.intellij.usageView.UsageInfo;
import com.intellij.psi.PsiReference;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.objc.OCProtocolSymbol;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.io.FileUtilRt;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.symbols.objc.OCSynthesizeSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.OCSymbolHolderVirtualPsiElement;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.psi.OCCategoryName;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.objc.OCCompatibilityAliasSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.psi.search.SearchScope;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCSynthesizeProperty;
import com.intellij.refactoring.rename.RenamePsiElementProcessor;

public class OCRenameProcessor extends RenamePsiElementProcessor
{
    private OCSynthesizeProperty myOldSynthesize;
    private OCSynthesizeProperty myNewSynthesize;
    
    @Override
    public boolean canProcessElement(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "canProcessElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_1        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCElement;
        //    48: ifeq            65
        //    51: aload_1        
        //    52: instanceof      Lcom/intellij/psi/PsiNamedElement;
        //    55: ifne            112
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    64: athrow         
        //    65: aload_1        
        //    66: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    69: ifeq            98
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    78: athrow         
        //    79: aload_1        
        //    80: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    85: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSynthesizeProperty;
        //    88: ifne            112
        //    91: goto            98
        //    94: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    97: athrow         
        //    98: aload_1        
        //    99: instanceof      Lcom/jetbrains/cidr/lang/psi/OCLocalizedString;
        //   102: ifeq            120
        //   105: goto            112
        //   108: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   111: athrow         
        //   112: iconst_1       
        //   113: goto            121
        //   116: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   119: athrow         
        //   120: iconst_0       
        //   121: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  44     58     61     65     Lcom/intellij/util/IncorrectOperationException;
        //  51     72     75     79     Lcom/intellij/util/IncorrectOperationException;
        //  65     91     94     98     Lcom/intellij/util/IncorrectOperationException;
        //  79     105    108    112    Lcom/intellij/util/IncorrectOperationException;
        //  98     116    116    120    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0065:
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
    public void prepareRenaming(PsiElement locateDefinition, final String s, final Map<PsiElement, String> map, final SearchScope searchScope) {
        FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.rename");
        final OCSynthesizeProperty ocSynthesizeProperty = null;
        this.myNewSynthesize = ocSynthesizeProperty;
        this.myOldSynthesize = ocSynthesizeProperty;
        final OCSymbol<?> rawSymbolFromNamedElement = OCElementUtil.getRawSymbolFromNamedElement(locateDefinition);
        Label_0437: {
            Label_0251: {
                Label_0091: {
                    Label_0061: {
                        Label_0044: {
                            try {
                                if (rawSymbolFromNamedElement == null) {
                                    break Label_0251;
                                }
                                final OCSymbol<?> ocSymbol = rawSymbolFromNamedElement;
                                final boolean b = ocSymbol instanceof OCStructSymbol;
                                if (b) {
                                    break Label_0044;
                                }
                                break Label_0061;
                            }
                            catch (IncorrectOperationException ex) {
                                throw c(ex);
                            }
                            try {
                                final OCSymbol<?> ocSymbol = rawSymbolFromNamedElement;
                                final boolean b = ocSymbol instanceof OCStructSymbol;
                                if (b) {
                                    a((OCStructSymbol)rawSymbolFromNamedElement, s, map);
                                    break Label_0091;
                                }
                            }
                            catch (IncorrectOperationException ex2) {
                                throw c(ex2);
                            }
                        }
                        try {
                            if (!(rawSymbolFromNamedElement instanceof OCClassSymbol)) {
                                rawSymbolFromNamedElement.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol -> {
                                    c(s, map, ocSymbol);
                                    return true;
                                }));
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw c(ex3);
                        }
                    }
                    try {
                        if (rawSymbolFromNamedElement instanceof OCSymbolWithParent) {
                            OCSearchUtil.processMembersHierarchy((OCStructSymbol)rawSymbolFromNamedElement, (com.intellij.util.Processor<? super OCStructSymbol>)(ocSymbolWithParent -> {
                                c(s, map, ocSymbolWithParent);
                                return true;
                            }), false, true);
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw c(ex4);
                    }
                }
                Label_0210: {
                    Label_0150: {
                        try {
                            if (!(rawSymbolFromNamedElement instanceof OCFunctionSymbol)) {
                                break Label_0210;
                            }
                            final OCStructSymbol ocStructSymbol = (OCStructSymbol)rawSymbolFromNamedElement;
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocStructSymbol;
                            final boolean b2 = ocFunctionSymbol.isCppConstructor();
                            if (!b2) {
                                break Label_0150;
                            }
                            break Label_0150;
                        }
                        catch (IncorrectOperationException ex5) {
                            throw c(ex5);
                        }
                        try {
                            final OCStructSymbol ocStructSymbol = (OCStructSymbol)rawSymbolFromNamedElement;
                            final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)ocStructSymbol;
                            final boolean b2 = ocFunctionSymbol.isCppConstructor();
                            if (!b2) {
                                if (!((OCFunctionSymbol)rawSymbolFromNamedElement).isCppDestructor()) {
                                    break Label_0210;
                                }
                            }
                        }
                        catch (IncorrectOperationException ex6) {
                            throw c(ex6);
                        }
                    }
                    final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ((OCFunctionSymbol)rawSymbolFromNamedElement).getResolvedOwner();
                    try {
                        if (resolvedOwner instanceof OCStructSymbol) {
                            c(s, map, resolvedOwner);
                            a((OCStructSymbol)resolvedOwner, s, map);
                        }
                    }
                    catch (IncorrectOperationException ex7) {
                        throw c(ex7);
                    }
                }
                final OCSymbol<OCElement> definitionSymbol = rawSymbolFromNamedElement.getDefinitionSymbol();
                if (definitionSymbol != null) {
                    locateDefinition = (PsiElement)definitionSymbol.locateDefinition();
                    processAssociatedSymbols(definitionSymbol, new AssociatedElementsProcessor() {
                        @Override
                        public boolean processIvar(final OCInstanceVariableSymbol ocInstanceVariableSymbol, final OCPropertySymbol ocPropertySymbol) {
                            OCRenameProcessor.this.a(s, map, ocPropertySymbol, ocInstanceVariableSymbol);
                            return true;
                        }
                        
                        @Override
                        public boolean processPropertyAccessors(final OCPropertySymbol ocPropertySymbol) {
                            a(s, map, ocPropertySymbol);
                            return true;
                        }
                        
                        @Override
                        public boolean processProperty(final OCPropertySymbol ocPropertySymbol, final OCSymbol ocSymbol) {
                            OCRenameProcessor.this.a(s, map, ocSymbol, ocPropertySymbol);
                            return true;
                        }
                        
                        @Override
                        public boolean processClassAlias(final OCClassSymbol ocClassSymbol, final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol) {
                            a(s, map, ocClassSymbol, ocCompatibilityAliasSymbol);
                            return true;
                        }
                        
                        @Override
                        public boolean processClass(final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol, final OCClassSymbol ocClassSymbol) {
                            a(s, map, ocCompatibilityAliasSymbol, ocClassSymbol, searchScope);
                            return true;
                        }
                    }, searchScope);
                }
                try {
                    if (locateDefinition instanceof OCFile) {
                        a((OCFile)locateDefinition, s, map, searchScope);
                        break Label_0437;
                    }
                }
                catch (IncorrectOperationException ex8) {
                    throw c(ex8);
                }
            }
            if (locateDefinition instanceof OCClassDeclaration) {
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)locateDefinition;
                final String category = ocClassDeclaration.getCategory();
                String name = null;
                String s2 = null;
                String string = null;
                Label_0344: {
                    try {
                        name = ocClassDeclaration.getName();
                        s2 = category;
                        if (category != null) {
                            string = s + "+" + category;
                            break Label_0344;
                        }
                    }
                    catch (IncorrectOperationException ex9) {
                        throw c(ex9);
                    }
                    string = s;
                }
                a(name, s2, string, map, searchScope, locateDefinition instanceof OCProtocol, locateDefinition.getProject());
            }
            else if (locateDefinition instanceof OCCategoryName) {
                final OCClassDeclaration ocClassDeclaration2 = (OCClassDeclaration)locateDefinition.getParent();
                a(ocClassDeclaration2.getName(), ocClassDeclaration2.getCategory(), ocClassDeclaration2.getName() + "+" + s, map, searchScope, false, locateDefinition.getProject());
            }
        }
        final Iterator<PsiElement> iterator = map.keySet().iterator();
        while (true) {
            try {
                Block_43: {
                    while (iterator.hasNext()) {
                        if (!OCSearchScope.isInProjectSources(iterator.next())) {
                            break Block_43;
                        }
                    }
                    break;
                }
            }
            catch (IncorrectOperationException ex10) {
                throw c(ex10);
            }
            iterator.remove();
        }
    }
    
    private static void c(final String s, final Map<PsiElement, String> map, final OCSymbol ocSymbol) {
        if (ocSymbol != null) {
            final PsiElement locateDefinition = ocSymbol.locateDefinition();
            try {
                if (locateDefinition != null) {
                    map.put(locateDefinition, s);
                }
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
        }
    }
    
    public static void processAssociatedSymbols(final OCSymbol ocSymbol, final AssociatedElementsProcessor associatedElementsProcessor, final SearchScope searchScope) {
        if (ocSymbol instanceof OCPropertySymbol) {
            final OCPropertySymbol ocPropertySymbol = (OCPropertySymbol)ocSymbol;
            final OCInstanceVariableSymbol associatedIvar = ocPropertySymbol.getAssociatedIvar();
            Label_0051: {
                try {
                    if (associatedIvar == null) {
                        break Label_0051;
                    }
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol = associatedIvar;
                    final String s = ocInstanceVariableSymbol.getName();
                    final OCClassSymbol ocClassSymbol = (OCClassSymbol)ocSymbol;
                    final String s2 = ocClassSymbol.getName();
                    final boolean b = OCElementUtil.endsWithIgnoringFirstLetterCase(s, s2);
                    if (b) {
                        break Label_0051;
                    }
                    break Label_0051;
                }
                catch (IncorrectOperationException ex) {
                    throw c(ex);
                }
                try {
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol = associatedIvar;
                    final String s = ocInstanceVariableSymbol.getName();
                    final OCClassSymbol ocClassSymbol = (OCClassSymbol)ocSymbol;
                    final String s2 = ocClassSymbol.getName();
                    final boolean b = OCElementUtil.endsWithIgnoringFirstLetterCase(s, s2);
                    if (b) {
                        associatedIvar.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol -> associatedElementsProcessor.processIvar((OCInstanceVariableSymbol)ocSymbol, ocPropertySymbol)));
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw c(ex2);
                }
            }
            ocPropertySymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol -> associatedElementsProcessor.processPropertyAccessors((OCPropertySymbol)ocSymbol)));
        }
        else if (ocSymbol instanceof OCInstanceVariableSymbol) {
            final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = (OCInstanceVariableSymbol)ocSymbol;
            final OCPropertySymbol associatedProperty = ocInstanceVariableSymbol2.getAssociatedProperty();
            Label_0140: {
                try {
                    if (associatedProperty == null) {
                        return;
                    }
                    final OCPropertySymbol ocPropertySymbol2 = (OCPropertySymbol)ocSymbol;
                    final String s3 = ocPropertySymbol2.getName();
                    final OCPropertySymbol ocPropertySymbol3 = associatedProperty;
                    final String s4 = ocPropertySymbol3.getName();
                    final boolean b2 = OCElementUtil.endsWithIgnoringFirstLetterCase(s3, s4);
                    if (b2) {
                        break Label_0140;
                    }
                    return;
                }
                catch (IncorrectOperationException ex3) {
                    throw c(ex3);
                }
                try {
                    final OCPropertySymbol ocPropertySymbol2 = (OCPropertySymbol)ocSymbol;
                    final String s3 = ocPropertySymbol2.getName();
                    final OCPropertySymbol ocPropertySymbol3 = associatedProperty;
                    final String s4 = ocPropertySymbol3.getName();
                    final boolean b2 = OCElementUtil.endsWithIgnoringFirstLetterCase(s3, s4);
                    if (b2) {
                        associatedProperty.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol -> associatedElementsProcessor.processProperty((OCPropertySymbol)ocSymbol, ocInstanceVariableSymbol2)));
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw c(ex4);
                }
            }
        }
        else if (ocSymbol instanceof OCMethodSymbol) {
            OCPropertySymbol generatedFromProperty = ((OCMethodSymbol)ocSymbol).getGeneratedFromProperty();
            if (generatedFromProperty == null) {
                final OCMethodSymbol ocMethodSymbol = (OCMethodSymbol)ocSymbol.getAssociatedSymbol();
                OCPropertySymbol generatedFromProperty2 = null;
                Label_0217: {
                    try {
                        if (ocMethodSymbol != null) {
                            generatedFromProperty2 = ocMethodSymbol.getGeneratedFromProperty();
                            break Label_0217;
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw c(ex5);
                    }
                    generatedFromProperty2 = null;
                }
                generatedFromProperty = generatedFromProperty2;
            }
            try {
                if (generatedFromProperty != null) {
                    generatedFromProperty.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol2 -> associatedElementsProcessor.processProperty((OCPropertySymbol)ocSymbol2, ocSymbol)));
                }
            }
            catch (IncorrectOperationException ex6) {
                throw c(ex6);
            }
        }
        else if (ocSymbol instanceof OCCompatibilityAliasSymbol) {
            final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol = (OCCompatibilityAliasSymbol)ocSymbol;
            final OCType resolvedType = ocCompatibilityAliasSymbol.getResolvedType();
            if (resolvedType instanceof OCObjectType) {
                final OCClassSymbol classSymbol = ((OCObjectType)resolvedType).getClassSymbol();
                Label_0311: {
                    try {
                        if (classSymbol == null) {
                            return;
                        }
                        final OCClassSymbol ocClassSymbol2 = classSymbol;
                        final String s5 = ocClassSymbol2.getName();
                        final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol2 = ocCompatibilityAliasSymbol;
                        final String s6 = ocCompatibilityAliasSymbol2.getName();
                        final boolean b3 = OCElementUtil.endsWithIgnoringFirstLetterCase(s5, s6);
                        if (b3) {
                            break Label_0311;
                        }
                        return;
                    }
                    catch (IncorrectOperationException ex7) {
                        throw c(ex7);
                    }
                    try {
                        final OCClassSymbol ocClassSymbol2 = classSymbol;
                        final String s5 = ocClassSymbol2.getName();
                        final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol2 = ocCompatibilityAliasSymbol;
                        final String s6 = ocCompatibilityAliasSymbol2.getName();
                        final boolean b3 = OCElementUtil.endsWithIgnoringFirstLetterCase(s5, s6);
                        if (b3) {
                            associatedElementsProcessor.processClass(ocCompatibilityAliasSymbol, classSymbol);
                        }
                    }
                    catch (IncorrectOperationException ex8) {
                        throw c(ex8);
                    }
                }
            }
        }
        else if (ocSymbol instanceof OCClassSymbol) {
            final OCClassSymbol ocClassSymbol3 = (OCClassSymbol)ocSymbol;
            final Project project = ocClassSymbol3.getProject();
            try {
                if (project == null) {
                    return;
                }
            }
            catch (IncorrectOperationException ex9) {
                throw c(ex9);
            }
            OCGlobalProjectSymbolsCache.processAliasNamesForType(project, ocClassSymbol3.getName(), (Processor<String>)(s -> {
                OCGlobalProjectSymbolsCache.processTopLevelSymbols(project, (Processor<OCSymbol>)(ocSymbol -> {
                    try {
                        if (!(ocSymbol instanceof OCCompatibilityAliasSymbol) || !a(ocSymbol, searchScope)) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw c(ex);
                    }
                    final OCType resolvedType = ocSymbol.getResolvedType();
                    try {
                        if (!(resolvedType instanceof OCObjectType)) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw c(ex2);
                    }
                    final OCClassSymbol classSymbol = ((OCObjectType)resolvedType).getClassSymbol();
                    Label_0081: {
                        try {
                            if (classSymbol == null) {
                                return true;
                            }
                            final OCClassSymbol ocClassSymbol2 = classSymbol;
                            final OCClassSymbol ocClassSymbol3 = ocClassSymbol3;
                            final boolean b = ocClassSymbol2.equals(ocClassSymbol3);
                            if (!b) {
                                return true;
                            }
                            break Label_0081;
                        }
                        catch (IncorrectOperationException ex3) {
                            throw c(ex3);
                        }
                        try {
                            final OCClassSymbol ocClassSymbol2 = classSymbol;
                            final OCClassSymbol ocClassSymbol3 = ocClassSymbol3;
                            final boolean b = ocClassSymbol2.equals(ocClassSymbol3);
                            if (!b) {
                                return true;
                            }
                        }
                        catch (IncorrectOperationException ex4) {
                            throw c(ex4);
                        }
                        try {
                            if (!OCElementUtil.endsWithIgnoringFirstLetterCase(ocClassSymbol3.getName(), ocSymbol.getName())) {
                                return true;
                            }
                            final AssociatedElementsProcessor associatedElementsProcessor2 = associatedElementsProcessor;
                            final OCClassSymbol ocClassSymbol4 = ocClassSymbol3;
                            final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol = (OCCompatibilityAliasSymbol)ocSymbol;
                            final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol2 = ocCompatibilityAliasSymbol;
                            final boolean b2 = associatedElementsProcessor2.processClassAlias(ocClassSymbol4, ocCompatibilityAliasSymbol2);
                            if (!b2) {
                                return false;
                            }
                            return true;
                        }
                        catch (IncorrectOperationException ex5) {
                            throw c(ex5);
                        }
                    }
                    try {
                        final AssociatedElementsProcessor associatedElementsProcessor2 = associatedElementsProcessor;
                        final OCClassSymbol ocClassSymbol4 = ocClassSymbol3;
                        final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol = (OCCompatibilityAliasSymbol)ocSymbol;
                        final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol2 = ocCompatibilityAliasSymbol;
                        final boolean b2 = associatedElementsProcessor2.processClassAlias(ocClassSymbol4, ocCompatibilityAliasSymbol2);
                        if (!b2) {
                            return false;
                        }
                    }
                    catch (IncorrectOperationException ex6) {
                        throw c(ex6);
                    }
                    return true;
                }), s);
                return true;
            }));
        }
    }
    
    private static boolean a(final OCSymbol ocSymbol, final SearchScope searchScope) {
        Label_0030: {
            try {
                if (!(searchScope instanceof LocalSearchScope)) {
                    break Label_0030;
                }
                final SearchScope searchScope2 = searchScope;
                final LocalSearchScope localSearchScope = (LocalSearchScope)searchScope2;
                final OCSymbol ocSymbol2 = ocSymbol;
                final VirtualFile virtualFile = ocSymbol2.getContainingFile();
                final boolean b = localSearchScope.isInScope(virtualFile);
                if (b) {
                    break Label_0030;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            try {
                final SearchScope searchScope2 = searchScope;
                final LocalSearchScope localSearchScope = (LocalSearchScope)searchScope2;
                final OCSymbol ocSymbol2 = ocSymbol;
                final VirtualFile virtualFile = ocSymbol2.getContainingFile();
                final boolean b = localSearchScope.isInScope(virtualFile);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    private void a(final String s, final Map<PsiElement, String> map, final OCPropertySymbol ocPropertySymbol, final OCInstanceVariableSymbol ocInstanceVariableSymbol) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocInstanceVariableSymbol.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        final String a = a(s, ocInstanceVariableSymbol.getName(), ocPropertySymbol.getName());
        try {
            if (ocInstanceVariableSymbol.isClang4ImplicitIvar()) {
                map.put((PsiElement)new OCSymbolHolderVirtualPsiElement(ocInstanceVariableSymbol), a);
                return;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        try {
            if (ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS) {
                c(a, map, ocInstanceVariableSymbol);
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        ocInstanceVariableSymbol.processSynthesizes((Processor<OCSynthesizeSymbol>)findFirstProcessor);
        final OCSynthesizeSymbol ocSynthesizeSymbol = (OCSynthesizeSymbol)findFirstProcessor.getFoundValue();
        Label_0146: {
            try {
                if (ocSynthesizeSymbol == null) {
                    return;
                }
                final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                final boolean b = ocSynthesizeSymbol2.hasIvar();
                if (!b) {
                    break Label_0146;
                }
                return;
            }
            catch (IncorrectOperationException ex3) {
                throw c(ex3);
            }
            try {
                final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                final boolean b = ocSynthesizeSymbol2.hasIvar();
                if (!b) {
                    this.myOldSynthesize = ((OCSymbolBase<OCSynthesizeProperty>)ocSynthesizeSymbol).locateDefinition();
                    this.myNewSynthesize = OCElementFactory.synthesize(s, ocInstanceVariableSymbol.getName(), (PsiElement)this.myOldSynthesize);
                }
            }
            catch (IncorrectOperationException ex4) {
                throw c(ex4);
            }
        }
    }
    
    private static String a(final String p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   java/lang/String.length:()I
        //     4: aload_2        
        //     5: invokevirtual   java/lang/String.length:()I
        //     8: isub           
        //     9: istore_3       
        //    10: aload_0        
        //    11: astore          4
        //    13: iload_3        
        //    14: aload_1        
        //    15: invokevirtual   java/lang/String.length:()I
        //    18: if_icmpge       113
        //    21: aload_1        
        //    22: iload_3        
        //    23: invokevirtual   java/lang/String.charAt:(I)C
        //    26: invokestatic    java/lang/Character.isUpperCase:(C)Z
        //    29: ifeq            113
        //    32: goto            39
        //    35: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    38: athrow         
        //    39: aload_2        
        //    40: iconst_0       
        //    41: invokevirtual   java/lang/String.charAt:(I)C
        //    44: invokestatic    java/lang/Character.isUpperCase:(C)Z
        //    47: ifeq            106
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    56: athrow         
        //    57: aload_0        
        //    58: iconst_0       
        //    59: invokevirtual   java/lang/String.charAt:(I)C
        //    62: invokestatic    java/lang/Character.isUpperCase:(C)Z
        //    65: ifne            106
        //    68: goto            75
        //    71: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    74: athrow         
        //    75: iload_3        
        //    76: ifeq            113
        //    79: goto            86
        //    82: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    85: athrow         
        //    86: aload_1        
        //    87: iload_3        
        //    88: iconst_1       
        //    89: isub           
        //    90: invokevirtual   java/lang/String.charAt:(I)C
        //    93: invokestatic    java/lang/Character.isLetter:(C)Z
        //    96: ifeq            113
        //    99: goto            106
        //   102: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   105: athrow         
        //   106: aload           4
        //   108: invokestatic    com/intellij/openapi/util/text/StringUtil.capitalize:(Ljava/lang/String;)Ljava/lang/String;
        //   111: astore          4
        //   113: new             Ljava/lang/StringBuilder;
        //   116: dup            
        //   117: invokespecial   java/lang/StringBuilder.<init>:()V
        //   120: aload_1        
        //   121: iconst_0       
        //   122: iload_3        
        //   123: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   129: aload           4
        //   131: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   134: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   137: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  13     32     35     39     Lcom/intellij/util/IncorrectOperationException;
        //  21     50     53     57     Lcom/intellij/util/IncorrectOperationException;
        //  39     68     71     75     Lcom/intellij/util/IncorrectOperationException;
        //  57     79     82     86     Lcom/intellij/util/IncorrectOperationException;
        //  75     99     102    106    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
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
    
    private static void a(final String s, final Map<PsiElement, String> map, final OCPropertySymbol ocPropertySymbol) {
        ((OCSymbolWithParent<T, OCClassSymbol>)ocPropertySymbol).getParent().processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)(p3 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_3        
            //     1: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
            //     6: aload_0        
            //     7: if_acmpne       122
            //    10: aload_3        
            //    11: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isGetter:()Z
            //    16: ifeq            68
            //    19: goto            26
            //    22: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    25: athrow         
            //    26: aload_0        
            //    27: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.GETTER:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
            //    30: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
            //    35: ifne            68
            //    38: goto            45
            //    41: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    44: athrow         
            //    45: aload_1        
            //    46: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
            //    49: dup            
            //    50: aload_3        
            //    51: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //    54: aload_2        
            //    55: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //    60: pop            
            //    61: goto            122
            //    64: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    67: athrow         
            //    68: aload_3        
            //    69: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
            //    74: ifeq            122
            //    77: aload_0        
            //    78: getstatic       com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute.SETTER:Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;
            //    81: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.hasAttribute:(Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol$PropertyAttribute;)Z
            //    86: ifne            122
            //    89: goto            96
            //    92: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //    95: athrow         
            //    96: aload_1        
            //    97: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
            //   100: dup            
            //   101: aload_3        
            //   102: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
            //   105: aload_2        
            //   106: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.getObjCSetterFromGetter:(Ljava/lang/String;)Ljava/lang/String;
            //   109: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //   114: pop            
            //   115: goto            122
            //   118: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
            //   121: athrow         
            //   122: iconst_1       
            //   123: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                                           
            //  -----  -----  -----  -----  -----------------------------------------------
            //  0      19     22     26     Lcom/intellij/util/IncorrectOperationException;
            //  10     38     41     45     Lcom/intellij/util/IncorrectOperationException;
            //  26     64     64     68     Lcom/intellij/util/IncorrectOperationException;
            //  68     89     92     96     Lcom/intellij/util/IncorrectOperationException;
            //  77     115    118    122    Lcom/intellij/util/IncorrectOperationException;
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
        ocPropertySymbol.processAccessorMethods((Processor<? super OCMethodSymbol>)(ocMethodSymbol -> {
            final String name = ocMethodSymbol.getName();
            String string = null;
            Label_0113: {
                Label_0073: {
                    try {
                        if (name.equals(ocPropertySymbol.getAttributeValue(OCPropertySymbol.PropertyAttribute.GETTER))) {
                            return true;
                        }
                        final String s2 = name;
                        final StringBuilder sb = new StringBuilder();
                        final OCPropertySymbol ocPropertySymbol2 = ocPropertySymbol;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute = OCPropertySymbol.PropertyAttribute.SETTER;
                        final String s3 = ocPropertySymbol2.getAttributeValue(propertyAttribute);
                        final StringBuilder sb2 = sb.append(s3);
                        final String s4 = ":";
                        final StringBuilder sb3 = sb2.append(s4);
                        final String s5 = sb3.toString();
                        final boolean b = s2.equals(s5);
                        if (b) {
                            return true;
                        }
                        break Label_0073;
                    }
                    catch (IncorrectOperationException ex) {
                        throw c(ex);
                    }
                    try {
                        final String s2 = name;
                        final StringBuilder sb = new StringBuilder();
                        final OCPropertySymbol ocPropertySymbol2 = ocPropertySymbol;
                        final OCPropertySymbol.PropertyAttribute propertyAttribute = OCPropertySymbol.PropertyAttribute.SETTER;
                        final String s3 = ocPropertySymbol2.getAttributeValue(propertyAttribute);
                        final StringBuilder sb2 = sb.append(s3);
                        final String s4 = ":";
                        final StringBuilder sb3 = sb2.append(s4);
                        final String s5 = sb3.toString();
                        final boolean b = s2.equals(s5);
                        if (b) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw c(ex2);
                    }
                    try {
                        if (OCElementUtil.startsWithWord(name, "set")) {
                            string = "set" + StringUtil.capitalize(s);
                            break Label_0113;
                        }
                    }
                    catch (IncorrectOperationException ex3) {
                        throw c(ex3);
                    }
                }
                string = s;
            }
            c(string, map, ocMethodSymbol);
            final OCDeclaratorSymbol parameter = ocMethodSymbol.getSelectors().get(0).getParameter();
            Label_0173: {
                try {
                    if (parameter == null) {
                        return true;
                    }
                    final OCDeclaratorSymbol ocDeclaratorSymbol = parameter;
                    final String s6 = ocDeclaratorSymbol.getName();
                    final OCPropertySymbol ocPropertySymbol3 = ocPropertySymbol;
                    final String s7 = ocPropertySymbol3.getName();
                    final boolean b2 = OCElementUtil.endsWithIgnoringFirstLetterCase(s6, s7);
                    if (b2) {
                        break Label_0173;
                    }
                    return true;
                }
                catch (IncorrectOperationException ex4) {
                    throw c(ex4);
                }
                try {
                    final OCDeclaratorSymbol ocDeclaratorSymbol = parameter;
                    final String s6 = ocDeclaratorSymbol.getName();
                    final OCPropertySymbol ocPropertySymbol3 = ocPropertySymbol;
                    final String s7 = ocPropertySymbol3.getName();
                    final boolean b2 = OCElementUtil.endsWithIgnoringFirstLetterCase(s6, s7);
                    if (b2) {
                        c(a(s, parameter.getName(), ocPropertySymbol.getName()), map, parameter);
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw c(ex5);
                }
            }
            return true;
        }), true);
    }
    
    private void a(String s, final Map<PsiElement, String> map, final OCSymbol ocSymbol, final OCPropertySymbol ocPropertySymbol) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocSymbol.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        if (ocSymbol instanceof OCInstanceVariableSymbol) {
            final OCInstanceVariableSymbol ocInstanceVariableSymbol = (OCInstanceVariableSymbol)ocSymbol;
            Label_0179: {
                try {
                    if (!ocCodeStyleSettings.REFACTOR_PROPERTIES_AND_IVARS) {
                        if (!ocInstanceVariableSymbol.isClang4ImplicitIvar()) {
                            break Label_0179;
                        }
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw c(ex);
                }
                final String substring = ocInstanceVariableSymbol.getName().substring(0, ocInstanceVariableSymbol.getName().length() - ocPropertySymbol.getName().length());
                String s2 = null;
                Label_0160: {
                    Label_0157: {
                        try {
                            if (!s.startsWith(substring) || s.length() <= substring.length()) {
                                break Label_0157;
                            }
                        }
                        catch (IncorrectOperationException ex2) {
                            throw c(ex2);
                        }
                        s2 = s.substring(substring.length());
                        if (Character.isLowerCase(ocPropertySymbol.getName().charAt(0))) {
                            s2 = StringUtil.decapitalize(s2);
                        }
                        break Label_0160;
                    }
                    s2 = s;
                }
                c(s2, map, ocPropertySymbol);
                a(s2, map, ocPropertySymbol);
                return;
            }
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            ocInstanceVariableSymbol.processSynthesizes((Processor<OCSynthesizeSymbol>)findFirstProcessor);
            final OCSynthesizeSymbol ocSynthesizeSymbol = (OCSynthesizeSymbol)findFirstProcessor.getFoundValue();
            Label_0228: {
                try {
                    if (ocSynthesizeSymbol == null) {
                        return;
                    }
                    final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                    final boolean b = ocSynthesizeSymbol2.hasIvar();
                    if (!b) {
                        break Label_0228;
                    }
                    return;
                }
                catch (IncorrectOperationException ex3) {
                    throw c(ex3);
                }
                try {
                    final OCSynthesizeSymbol ocSynthesizeSymbol2 = ocSynthesizeSymbol;
                    final boolean b = ocSynthesizeSymbol2.hasIvar();
                    if (!b) {
                        this.myOldSynthesize = ((OCSymbolBase<OCSynthesizeProperty>)ocSynthesizeSymbol).locateDefinition();
                        this.myNewSynthesize = OCElementFactory.synthesize(ocPropertySymbol.getName(), s, (PsiElement)this.myOldSynthesize);
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw c(ex4);
                }
            }
        }
        else if (ocSymbol instanceof OCMethodSymbol) {
            if (((OCMethodSymbol)ocSymbol).isSetter()) {
                final String objCGetterFromSetter = OCNameSuggester.getObjCGetterFromSetter(s);
                String s3 = null;
                Label_0313: {
                    try {
                        if (objCGetterFromSetter != null) {
                            s3 = objCGetterFromSetter;
                            break Label_0313;
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw c(ex5);
                    }
                    s3 = s;
                }
                s = s3;
            }
            c(s, map, ocPropertySymbol);
            a(s, map, ocPropertySymbol);
        }
    }
    
    private static void a(final String s, final Map<PsiElement, String> map, @NotNull final OCClassSymbol ocClassSymbol, final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol) {
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "clazz", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor", "prepareClassAlias"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final String substring = ocClassSymbol.getName().substring(0, ocClassSymbol.getName().length() - ocCompatibilityAliasSymbol.getName().length());
        try {
            if (!s.startsWith(substring) || s.length() <= substring.length()) {
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        if (((OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocClassSymbol.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class)).REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES) {
            c(s.substring(substring.length()), map, ocCompatibilityAliasSymbol);
        }
    }
    
    private static void a(final OCFile ocFile, final String s, final Map<PsiElement, String> map, final SearchScope searchScope) {
        try {
            if (!FileUtilRt.extensionEquals(s, FileUtilRt.getExtension(ocFile.getName()))) {
                map.put((PsiElement)ocFile, s);
                return;
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final Project project = ocFile.getProject();
        final OCSymbol sameNamedClass = ocFile.getSameNamedClass();
        try {
            if (sameNamedClass instanceof OCClassSymbol) {
                a(sameNamedClass.getName(), ((OCClassSymbol)sameNamedClass).getCategoryName(), FileUtil.getNameWithoutExtension(s), map, searchScope, sameNamedClass instanceof OCProtocolSymbol, project);
                return;
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        try {
            if (sameNamedClass instanceof OCStructSymbol) {
                a((OCStructSymbol)sameNamedClass, FileUtil.getNameWithoutExtension(s), map);
                return;
            }
        }
        catch (IncorrectOperationException ex3) {
            throw c(ex3);
        }
        a(ocFile, s, map);
    }
    
    private static void a(OCFile associatedFileWithSameName, final String s, final Map<PsiElement, String> map) {
        map.put((PsiElement)associatedFileWithSameName, s);
        associatedFileWithSameName = associatedFileWithSameName.getAssociatedFileWithSameName();
        try {
            if (associatedFileWithSameName != null) {
                map.put((PsiElement)associatedFileWithSameName, FileUtil.getNameWithoutExtension(s) + '.' + FileUtilRt.getExtension(associatedFileWithSameName.getName()));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
    }
    
    private static void a(final OCStructSymbol ocStructSymbol, final String s, final Map<PsiElement, String> map) {
        ocStructSymbol.processSameSymbols((Processor<OCSymbol>)(ocSymbol -> {
            final OCStructSymbol ocStructSymbol = (OCStructSymbol)ocSymbol;
            c(s, map, ocStructSymbol);
            if (!ocStructSymbol.isPredeclaration()) {
                final Processor processor = ocFunctionSymbol -> {
                    c(s, map, ocFunctionSymbol);
                    return true;
                };
                ocStructSymbol.processConstructors((Processor<? super OCFunctionSymbol>)processor, true);
                ocStructSymbol.processDestructors((Processor<OCFunctionSymbol>)processor, true);
            }
            return true;
        }));
        final OCFile containingOCFile = ocStructSymbol.getContainingOCFile();
        Label_0047: {
            try {
                if (containingOCFile == null) {
                    return;
                }
                final OCFile ocFile = containingOCFile;
                final String s2 = ocFile.getName();
                final String s3 = FileUtil.getNameWithoutExtension(s2);
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final String s4 = ocStructSymbol2.getName();
                final boolean b = s3.equals(s4);
                if (b) {
                    break Label_0047;
                }
                return;
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            try {
                final OCFile ocFile = containingOCFile;
                final String s2 = ocFile.getName();
                final String s3 = FileUtil.getNameWithoutExtension(s2);
                final OCStructSymbol ocStructSymbol2 = ocStructSymbol;
                final String s4 = ocStructSymbol2.getName();
                final boolean b = s3.equals(s4);
                if (b) {
                    a(containingOCFile, s + '.' + FileUtilRt.getExtension(containingOCFile.getName()), map);
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
        }
    }
    
    private static void a(final String s, final Map<PsiElement, String> map, final OCCompatibilityAliasSymbol ocCompatibilityAliasSymbol, final OCClassSymbol ocClassSymbol, final SearchScope searchScope) {
        if (((OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocCompatibilityAliasSymbol.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class)).REFACTOR_COMPATIBILITY_ALIASES_AND_CLASSES) {
            String s2 = ocClassSymbol.getName().substring(0, ocClassSymbol.getName().length() - ocCompatibilityAliasSymbol.getName().length()) + s;
            final String categoryName = ocClassSymbol.getCategoryName();
            if (categoryName != null) {
                s2 = s2 + "+" + categoryName;
            }
            a(ocClassSymbol.getName(), categoryName, s2, map, searchScope, ocClassSymbol instanceof OCProtocolSymbol, ocClassSymbol.getProject());
        }
    }
    
    private static void a(final String s, final String s2, final String s3, final Map<PsiElement, String> map, final SearchScope searchScope, final boolean b, final Project project) {
        final int lastIndex = s3.lastIndexOf(43);
        String substring = null;
        Label_0028: {
            try {
                if (lastIndex > 0) {
                    substring = s3.substring(0, lastIndex);
                    break Label_0028;
                }
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            substring = s3;
        }
        OCGlobalProjectSymbolsCache.processTopLevelSymbols(project, (Processor<OCSymbol>)(ocSymbol -> {
            Label_0024: {
                try {
                    if (!(ocSymbol instanceof OCClassSymbol)) {
                        return true;
                    }
                    final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                    final SearchScope searchScope2 = searchScope;
                    final boolean b2 = a(ocSymbol2, searchScope2);
                    if (b2) {
                        break Label_0024;
                    }
                    return true;
                }
                catch (IncorrectOperationException ex) {
                    throw c(ex);
                }
                try {
                    final OCSymbol<PsiElement> ocSymbol2 = (OCSymbol<PsiElement>)ocSymbol;
                    final SearchScope searchScope2 = searchScope;
                    final boolean b2 = a(ocSymbol2, searchScope2);
                    if (!b2) {
                        return true;
                    }
                    if (b != ocSymbol instanceof OCProtocolSymbol) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw c(ex2);
                }
            }
            final boolean equal = Comparing.equal(((OCClassSymbol)ocSymbol).getCategoryName(), s2);
            Label_0083: {
                try {
                    if (equal) {
                        break Label_0083;
                    }
                    final String s5 = s;
                    final String s6 = substring;
                    final boolean b3 = s5.equals(s6);
                    if (b3) {
                        return true;
                    }
                    break Label_0083;
                }
                catch (IncorrectOperationException ex3) {
                    throw c(ex3);
                }
                try {
                    final String s5 = s;
                    final String s6 = substring;
                    final boolean b3 = s5.equals(s6);
                    if (b3) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex4) {
                    throw c(ex4);
                }
            }
            final PsiElement locateDefinition = ocSymbol.locateDefinition();
            Label_0140: {
                Label_0109: {
                    try {
                        if (locateDefinition == null) {
                            return true;
                        }
                        final boolean b4 = equal;
                        if (b4) {
                            break Label_0109;
                        }
                        break Label_0109;
                    }
                    catch (IncorrectOperationException ex5) {
                        throw c(ex5);
                    }
                    try {
                        final boolean b4 = equal;
                        if (b4) {
                            map.put(locateDefinition, s3);
                            break Label_0140;
                        }
                    }
                    catch (IncorrectOperationException ex6) {
                        throw c(ex6);
                    }
                }
                map.put(locateDefinition, substring);
            }
            final PsiFile containingFile = locateDefinition.getContainingFile();
            final String name = containingFile.getName();
            String s7 = FileUtil.getNameWithoutExtension(name);
            final int lastIndex = s7.lastIndexOf(43);
            String substring = null;
            if (lastIndex > 0) {
                substring = s7.substring(lastIndex + 1);
                s7 = s7.substring(0, lastIndex);
            }
            Label_0228: {
                try {
                    if (!s7.equals(s)) {
                        return true;
                    }
                    final String s8 = s2;
                    final String s9 = substring;
                    final boolean b5 = Comparing.equal(s8, s9);
                    if (b5) {
                        break Label_0228;
                    }
                    break Label_0228;
                }
                catch (IncorrectOperationException ex7) {
                    throw c(ex7);
                }
                try {
                    final String s8 = s2;
                    final String s9 = substring;
                    final boolean b5 = Comparing.equal(s8, s9);
                    if (b5) {
                        map.put(containingFile, s3 + "." + FileUtilRt.getExtension(name));
                        return true;
                    }
                }
                catch (IncorrectOperationException ex8) {
                    throw c(ex8);
                }
            }
            map.put(containingFile, substring + name.substring(s7.length()));
            return true;
        }), s);
    }
    
    private static boolean a(final OCMethod ocMethod) {
        return ocMethod.getSelector().endsWith(":");
    }
    
    private static boolean a(final OCSendMessageExpression ocSendMessageExpression) {
        return ocSendMessageExpression.getMessageSelector().endsWith(":");
    }
    
    private static boolean a(final OCPropertyAttribute ocPropertyAttribute) {
        return "setter".equals(ocPropertyAttribute.getName());
    }
    
    @NotNull
    @Override
    public Collection<PsiReference> findReferences(final PsiElement psiElement) {
        Collection<PsiReference> references;
        try {
            references = this.findReferences(psiElement, null);
            if (references == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor", "findReferences"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return references;
    }
    
    @NotNull
    public Collection<PsiReference> findReferences(final PsiElement p0, final SearchScope p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore          4
        //     5: aload_1        
        //     6: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.a:(Lcom/intellij/psi/PsiElement;)Z
        //     9: ifeq            37
        //    12: aload_1        
        //    13: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //    16: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    19: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //    22: astore          4
        //    24: aload           4
        //    26: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //    31: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    36: astore_3       
        //    37: aload_3        
        //    38: ifnonnull       90
        //    41: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //    44: dup            
        //    45: ifnonnull       89
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    54: athrow         
        //    55: new             Ljava/lang/IllegalStateException;
        //    58: dup            
        //    59: ldc             "@NotNull method %s.%s must not return null"
        //    61: ldc             2
        //    63: anewarray       Ljava/lang/Object;
        //    66: dup            
        //    67: ldc             0
        //    69: ldc             "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor"
        //    71: aastore        
        //    72: dup            
        //    73: ldc             1
        //    75: ldc             "findReferences"
        //    77: aastore        
        //    78: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    88: athrow         
        //    89: areturn        
        //    90: aload_3        
        //    91: instanceof      Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //    94: ifeq            165
        //    97: aload_3        
        //    98: checkcast       Lcom/jetbrains/cidr/lang/psi/OCClassDeclaration;
        //   101: invokeinterface com/jetbrains/cidr/lang/psi/OCClassDeclaration.getCategoryElement:()Lcom/jetbrains/cidr/lang/psi/OCCategoryName;
        //   106: ifnull          165
        //   109: goto            116
        //   112: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   115: athrow         
        //   116: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   119: dup            
        //   120: ifnonnull       164
        //   123: goto            130
        //   126: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   129: athrow         
        //   130: new             Ljava/lang/IllegalStateException;
        //   133: dup            
        //   134: ldc             "@NotNull method %s.%s must not return null"
        //   136: ldc             2
        //   138: anewarray       Ljava/lang/Object;
        //   141: dup            
        //   142: ldc             0
        //   144: ldc             "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor"
        //   146: aastore        
        //   147: dup            
        //   148: ldc             1
        //   150: ldc             "findReferences"
        //   152: aastore        
        //   153: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   156: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   159: athrow         
        //   160: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   163: athrow         
        //   164: areturn        
        //   165: new             Lcom/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$MySearchParameters;
        //   168: dup            
        //   169: aload_3        
        //   170: aload_2        
        //   171: ifnull          182
        //   174: aload_2        
        //   175: goto            188
        //   178: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   181: athrow         
        //   182: aload_3        
        //   183: invokeinterface com/intellij/psi/PsiElement.getUseScope:()Lcom/intellij/psi/search/SearchScope;
        //   188: iconst_0       
        //   189: iconst_1       
        //   190: invokespecial   com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$MySearchParameters.<init>:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/search/SearchScope;ZZ)V
        //   193: invokestatic    com/intellij/psi/search/searches/ReferencesSearch.search:(Lcom/intellij/psi/search/searches/ReferencesSearch$SearchParameters;)Lcom/intellij/util/Query;
        //   196: invokeinterface com/intellij/util/Query.findAll:()Ljava/util/Collection;
        //   201: astore          5
        //   203: aload           5
        //   205: invokeinterface java/util/Collection.iterator:()Ljava/util/Iterator;
        //   210: astore          6
        //   212: aload           6
        //   214: invokeinterface java/util/Iterator.hasNext:()Z
        //   219: ifeq            474
        //   222: aload           6
        //   224: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   229: checkcast       Lcom/intellij/psi/PsiReference;
        //   232: invokeinterface com/intellij/psi/PsiReference.getElement:()Lcom/intellij/psi/PsiElement;
        //   237: astore          7
        //   239: aload           7
        //   241: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   244: ifeq            298
        //   247: aload           4
        //   249: ifnull          284
        //   252: goto            259
        //   255: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   258: athrow         
        //   259: aload           4
        //   261: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
        //   266: aload           7
        //   268: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   271: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.a:(Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;)Z
        //   274: if_icmpeq       471
        //   277: goto            284
        //   280: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   283: athrow         
        //   284: aload           6
        //   286: invokeinterface java/util/Iterator.remove:()V
        //   291: goto            471
        //   294: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   297: athrow         
        //   298: aload           7
        //   300: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   303: ifeq            357
        //   306: aload           4
        //   308: ifnull          343
        //   311: goto            318
        //   314: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   317: athrow         
        //   318: aload           4
        //   320: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
        //   325: aload           7
        //   327: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMethod;
        //   330: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.a:(Lcom/jetbrains/cidr/lang/psi/OCMethod;)Z
        //   333: if_icmpeq       471
        //   336: goto            343
        //   339: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   342: athrow         
        //   343: aload           6
        //   345: invokeinterface java/util/Iterator.remove:()V
        //   350: goto            471
        //   353: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   356: athrow         
        //   357: aload           7
        //   359: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;
        //   362: ifeq            416
        //   365: aload           4
        //   367: ifnull          402
        //   370: goto            377
        //   373: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   376: athrow         
        //   377: aload           4
        //   379: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.isSetter:()Z
        //   384: aload           7
        //   386: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;
        //   389: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.a:(Lcom/jetbrains/cidr/lang/psi/OCPropertyAttribute;)Z
        //   392: if_icmpeq       471
        //   395: goto            402
        //   398: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   401: athrow         
        //   402: aload           6
        //   404: invokeinterface java/util/Iterator.remove:()V
        //   409: goto            471
        //   412: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   415: athrow         
        //   416: aload           4
        //   418: ifnull          435
        //   421: aload           6
        //   423: invokeinterface java/util/Iterator.remove:()V
        //   428: goto            471
        //   431: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   434: athrow         
        //   435: aload_1        
        //   436: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   439: ifeq            471
        //   442: aload           7
        //   444: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //   447: ifeq            471
        //   450: goto            457
        //   453: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   456: athrow         
        //   457: aload           6
        //   459: invokeinterface java/util/Iterator.remove:()V
        //   464: goto            471
        //   467: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   470: athrow         
        //   471: goto            212
        //   474: aload           5
        //   476: dup            
        //   477: ifnonnull       514
        //   480: new             Ljava/lang/IllegalStateException;
        //   483: dup            
        //   484: ldc             "@NotNull method %s.%s must not return null"
        //   486: ldc             2
        //   488: anewarray       Ljava/lang/Object;
        //   491: dup            
        //   492: ldc             0
        //   494: ldc             "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor"
        //   496: aastore        
        //   497: dup            
        //   498: ldc             1
        //   500: ldc             "findReferences"
        //   502: aastore        
        //   503: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   506: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   509: athrow         
        //   510: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   513: athrow         
        //   514: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/search/SearchScope;)Ljava/util/Collection<Lcom/intellij/psi/PsiReference;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  37     48     51     55     Lcom/intellij/util/IncorrectOperationException;
        //  41     85     85     89     Lcom/intellij/util/IncorrectOperationException;
        //  90     109    112    116    Lcom/intellij/util/IncorrectOperationException;
        //  97     123    126    130    Lcom/intellij/util/IncorrectOperationException;
        //  116    160    160    164    Lcom/intellij/util/IncorrectOperationException;
        //  165    178    178    182    Lcom/intellij/util/IncorrectOperationException;
        //  239    252    255    259    Lcom/intellij/util/IncorrectOperationException;
        //  247    277    280    284    Lcom/intellij/util/IncorrectOperationException;
        //  259    294    294    298    Lcom/intellij/util/IncorrectOperationException;
        //  298    311    314    318    Lcom/intellij/util/IncorrectOperationException;
        //  306    336    339    343    Lcom/intellij/util/IncorrectOperationException;
        //  318    353    353    357    Lcom/intellij/util/IncorrectOperationException;
        //  357    370    373    377    Lcom/intellij/util/IncorrectOperationException;
        //  365    395    398    402    Lcom/intellij/util/IncorrectOperationException;
        //  377    412    412    416    Lcom/intellij/util/IncorrectOperationException;
        //  416    431    431    435    Lcom/intellij/util/IncorrectOperationException;
        //  435    450    453    457    Lcom/intellij/util/IncorrectOperationException;
        //  442    464    467    471    Lcom/intellij/util/IncorrectOperationException;
        //  474    510    510    514    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0116:
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
    
    private static boolean a(final PsiElement psiElement) {
        Label_0027: {
            try {
                if (!(psiElement instanceof OCSymbolHolderVirtualPsiElement)) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement = (OCSymbolHolderVirtualPsiElement)psiElement2;
                final OCSymbol ocSymbol = ocSymbolHolderVirtualPsiElement.getSymbol();
                final boolean b = ocSymbol instanceof OCMethodSymbol;
                if (b) {
                    break Label_0027;
                }
                return false;
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final OCSymbolHolderVirtualPsiElement ocSymbolHolderVirtualPsiElement = (OCSymbolHolderVirtualPsiElement)psiElement2;
                final OCSymbol ocSymbol = ocSymbolHolderVirtualPsiElement.getSymbol();
                final boolean b = ocSymbol instanceof OCMethodSymbol;
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Override
    public void renameElement(final PsiElement psiElement, final String s, final UsageInfo[] array, @Nullable final RefactoringElementListener refactoringElementListener) throws IncorrectOperationException {
        final ArrayList<UsageInfo> list = new ArrayList<UsageInfo>();
        for (final UsageInfo usageInfo : array) {
            final PsiElement element = usageInfo.getElement();
            Label_0304: {
                Label_0294: {
                    try {
                        if (element == null || !OCElementUtil.isPartOfMacroSubstitution(element)) {
                            break Label_0294;
                        }
                    }
                    catch (IncorrectOperationException ex) {
                        throw c(ex);
                    }
                    final OCMacroRange rangeInMacroCall = OCElementUtil.getRangeInMacroCall(element);
                    Label_0270: {
                        Label_0088: {
                            try {
                                if (rangeInMacroCall == null) {
                                    break Label_0270;
                                }
                                final OCMacroRange ocMacroRange = rangeInMacroCall;
                                final boolean b = ocMacroRange.mapsToArguments();
                                if (!b) {
                                    break Label_0088;
                                }
                                break Label_0270;
                            }
                            catch (IncorrectOperationException ex2) {
                                throw c(ex2);
                            }
                            try {
                                final OCMacroRange ocMacroRange = rangeInMacroCall;
                                final boolean b = ocMacroRange.mapsToArguments();
                                if (b) {
                                    break Label_0270;
                                }
                                if (!(psiElement instanceof PsiNamedElement)) {
                                    break Label_0270;
                                }
                            }
                            catch (IncorrectOperationException ex3) {
                                throw c(ex3);
                            }
                        }
                        final String name = ((PsiNamedElement)psiElement).getName();
                        final OCMacroSymbol resolveToSymbol = rangeInMacroCall.getMacroCall().resolveToSymbol();
                        OCDefineDirective ocDefineDirective = null;
                        Label_0146: {
                            try {
                                if (resolveToSymbol != null) {
                                    ocDefineDirective = resolveToSymbol.locateDefinition();
                                    break Label_0146;
                                }
                            }
                            catch (IncorrectOperationException ex4) {
                                throw c(ex4);
                            }
                            ocDefineDirective = null;
                        }
                        final OCDefineDirective ocDefineDirective2 = ocDefineDirective;
                        if (ocDefineDirective2 != null) {
                            final PsiElement nameIdentifier = ocDefineDirective2.getNameIdentifier();
                            final ASTNode[] children = ocDefineDirective2.getNode().getChildren(TokenSet.create(new IElementType[] { OCTokenTypes.IDENTIFIER }));
                            for (int length2 = children.length, j = 0; j < length2; ++j) {
                                final PsiElement psi = children[j].getPsi();
                                Label_0249: {
                                    try {
                                        if (psi == nameIdentifier) {
                                            continue;
                                        }
                                        final PsiElement psiElement2 = psi;
                                        final String s2 = psiElement2.getText();
                                        final String s3 = name;
                                        final boolean b2 = s2.equals(s3);
                                        if (b2) {
                                            break Label_0249;
                                        }
                                        continue;
                                    }
                                    catch (IncorrectOperationException ex5) {
                                        throw c(ex5);
                                    }
                                    try {
                                        final PsiElement psiElement2 = psi;
                                        final String s2 = psiElement2.getText();
                                        final String s3 = name;
                                        final boolean b2 = s2.equals(s3);
                                        if (b2) {
                                            OCElementUtil.replaceWithIdentifier(psi, s, element);
                                        }
                                    }
                                    catch (IncorrectOperationException ex6) {
                                        throw c(ex6);
                                    }
                                }
                            }
                        }
                    }
                    FileSymbolTablesCache.getInstance(psiElement.getProject()).scheduleReparseFile((OCFile)psiElement.getContainingFile());
                    break Label_0304;
                }
                list.add(usageInfo);
            }
        }
        super.renameElement(psiElement, s, list.toArray(new UsageInfo[list.size()]), refactoringElementListener);
    }
    
    @Override
    public void findExistingNameConflicts(final PsiElement p0, final String p1, final MultiMap<PsiElement, String> p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_1        
        //     4: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCategoryName;
        //     7: ifeq            81
        //    10: aload_1        
        //    11: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    16: astore_1       
        //    17: aload_1        
        //    18: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    21: ifeq            40
        //    24: aload_1        
        //    25: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //    28: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    33: goto            41
        //    36: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    39: athrow         
        //    40: aconst_null    
        //    41: astore          4
        //    43: aload           4
        //    45: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //    48: ifeq            259
        //    51: aload           4
        //    53: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    58: aload           4
        //    60: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getName:()Ljava/lang/String;
        //    65: aload_2        
        //    66: aconst_null    
        //    67: aload_1        
        //    68: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //    73: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.resolveNameInScope:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    76: astore          5
        //    78: goto            259
        //    81: aload_1        
        //    82: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    85: ifeq            150
        //    88: aload_1        
        //    89: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //    94: aload_2        
        //    95: invokestatic    com/jetbrains/cidr/lang/search/OCSearchUtil.getProjectVirtualFilesByName:(Lcom/intellij/openapi/project/Project;Ljava/lang/String;)Ljava/util/Collection;
        //    98: invokeinterface java/util/Collection.isEmpty:()Z
        //   103: ifne            149
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   112: athrow         
        //   113: aload_3        
        //   114: aload_1        
        //   115: new             Ljava/lang/StringBuilder;
        //   118: dup            
        //   119: invokespecial   java/lang/StringBuilder.<init>:()V
        //   122: ldc             "File '"
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: aload_2        
        //   128: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   131: ldc             "' already exists in the project"
        //   133: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   136: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   139: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   142: goto            149
        //   145: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   148: athrow         
        //   149: return         
        //   150: aload_1        
        //   151: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   154: ifeq            173
        //   157: aload_1        
        //   158: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSymbolDeclarator;
        //   161: invokeinterface com/jetbrains/cidr/lang/psi/OCSymbolDeclarator.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   166: goto            174
        //   169: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   172: athrow         
        //   173: aconst_null    
        //   174: astore          4
        //   176: aload           4
        //   178: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   181: ifeq            201
        //   184: aload           4
        //   186: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   189: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getCategoryName:()Ljava/lang/String;
        //   194: goto            202
        //   197: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   200: athrow         
        //   201: aconst_null    
        //   202: astore          6
        //   204: aload           4
        //   206: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   209: ifeq            220
        //   212: aconst_null    
        //   213: goto            221
        //   216: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   219: athrow         
        //   220: aload_1        
        //   221: astore          7
        //   223: aload           4
        //   225: ifnull          242
        //   228: aload           4
        //   230: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //   235: goto            243
        //   238: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   241: athrow         
        //   242: aconst_null    
        //   243: aload_2        
        //   244: aload           6
        //   246: aload           7
        //   248: aload_1        
        //   249: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   254: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.resolveNameInScope:(Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;Ljava/lang/String;Ljava/lang/String;Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   257: astore          5
        //   259: aload           5
        //   261: ifnull          395
        //   264: aload           4
        //   266: ifnull          300
        //   269: goto            276
        //   272: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   275: athrow         
        //   276: aload           4
        //   278: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //   283: aload           5
        //   285: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //   290: if_icmpne       395
        //   293: goto            300
        //   296: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   299: athrow         
        //   300: aload           5
        //   302: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.isGlobal:()Z
        //   307: ifeq            334
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   316: athrow         
        //   317: aload           5
        //   319: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getContainingFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   324: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getName:()Ljava/lang/String;
        //   327: goto            336
        //   330: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   333: athrow         
        //   334: ldc             "the scope"
        //   336: astore          6
        //   338: aload           5
        //   340: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   345: astore          7
        //   347: aload           7
        //   349: ifnull          395
        //   352: aload_3        
        //   353: aload           7
        //   355: new             Ljava/lang/StringBuilder;
        //   358: dup            
        //   359: invokespecial   java/lang/StringBuilder.<init>:()V
        //   362: aload           5
        //   364: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   369: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   372: ldc             " already exists in "
        //   374: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   377: aload           6
        //   379: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   382: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   385: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   388: goto            395
        //   391: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   394: athrow         
        //   395: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Ljava/lang/String;Lcom/intellij/util/containers/MultiMap<Lcom/intellij/psi/PsiElement;Ljava/lang/String;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  17     36     36     40     Lcom/intellij/util/IncorrectOperationException;
        //  81     106    109    113    Lcom/intellij/util/IncorrectOperationException;
        //  88     142    145    149    Lcom/intellij/util/IncorrectOperationException;
        //  150    169    169    173    Lcom/intellij/util/IncorrectOperationException;
        //  176    197    197    201    Lcom/intellij/util/IncorrectOperationException;
        //  204    216    216    220    Lcom/intellij/util/IncorrectOperationException;
        //  223    238    238    242    Lcom/intellij/util/IncorrectOperationException;
        //  259    269    272    276    Lcom/intellij/util/IncorrectOperationException;
        //  264    293    296    300    Lcom/intellij/util/IncorrectOperationException;
        //  276    310    313    317    Lcom/intellij/util/IncorrectOperationException;
        //  300    330    330    334    Lcom/intellij/util/IncorrectOperationException;
        //  347    388    391    395    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0276:
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
    @Override
    public PsiElement substituteElementToRename(@Nullable final PsiElement p0, @Nullable final Editor p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: astore_3       
        //     2: aload_1        
        //     3: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //     6: ifeq            19
        //     9: aload_1        
        //    10: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    13: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getExtendedContext:()Lcom/intellij/psi/PsiElement;
        //    18: astore_1       
        //    19: aload_1        
        //    20: instanceof      Lcom/intellij/psi/PsiNameIdentifierOwner;
        //    23: ifeq            32
        //    26: aload_1        
        //    27: iconst_0       
        //    28: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.substituteNamedElementToRename:(Lcom/intellij/psi/PsiElement;Z)Lcom/intellij/psi/PsiElement;
        //    31: astore_1       
        //    32: aload_1        
        //    33: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    36: ifeq            101
        //    39: aload_1        
        //    40: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    43: invokeinterface com/jetbrains/cidr/lang/psi/OCFunctionDeclaration.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    48: checkcast       Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    51: astore          4
        //    53: aload           4
        //    55: ifnull          101
        //    58: aload           4
        //    60: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getKind:()Lcom/jetbrains/cidr/lang/symbols/OCSymbolKind;
        //    63: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolKind.isConstructorOrDestructor:()Z
        //    66: ifeq            101
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    75: athrow         
        //    76: aload           4
        //    78: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.getResolvedOwner:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName;
        //    81: astore          5
        //    83: aload           5
        //    85: instanceof      Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //    88: ifeq            101
        //    91: aload           5
        //    93: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCSymbolWithQualifiedName.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //    96: areturn        
        //    97: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   100: athrow         
        //   101: aload_1        
        //   102: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.a:(Lcom/intellij/psi/PsiElement;)Z
        //   105: ifne            122
        //   108: aload_1        
        //   109: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   112: ifeq            289
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   121: athrow         
        //   122: aload_1        
        //   123: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   126: ifeq            152
        //   129: goto            136
        //   132: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   135: athrow         
        //   136: aload_1        
        //   137: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   140: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   145: goto            162
        //   148: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   151: athrow         
        //   152: aload_1        
        //   153: checkcast       Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //   156: invokevirtual   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   159: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   162: astore          4
        //   164: aload           4
        //   166: instanceof      Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   169: ifeq            223
        //   172: aload           4
        //   174: invokeinterface com/jetbrains/cidr/lang/symbols/OCSymbol.getAssociatedSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //   179: astore          4
        //   181: aload           4
        //   183: ifnull          203
        //   186: aload           4
        //   188: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   191: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.getGeneratedFromProperty:()Lcom/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol;
        //   196: goto            204
        //   199: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   202: athrow         
        //   203: aconst_null    
        //   204: astore          5
        //   206: aload           5
        //   208: ifnull          223
        //   211: aload           5
        //   213: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCPropertySymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   218: areturn        
        //   219: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   222: athrow         
        //   223: aload_1        
        //   224: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   227: ifeq            289
        //   230: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   233: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   238: ifne            289
        //   241: goto            248
        //   244: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   247: athrow         
        //   248: aload_1        
        //   249: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   252: aload_1        
        //   253: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.getHandler:(Lcom/jetbrains/cidr/lang/psi/OCCallable;Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler;
        //   256: astore          5
        //   258: aload_1        
        //   259: invokeinterface com/intellij/psi/PsiElement.getProject:()Lcom/intellij/openapi/project/Project;
        //   264: aload_2        
        //   265: aload           5
        //   267: invokestatic    com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureActionHandler.checkVariableArguments:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/editor/Editor;Lcom/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler;)Z
        //   270: ifne            287
        //   273: aload           5
        //   275: invokeinterface com/jetbrains/cidr/lang/refactoring/changeSignature/OCChangeSignatureHandler.invoke:()V
        //   280: goto            287
        //   283: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   286: athrow         
        //   287: aconst_null    
        //   288: areturn        
        //   289: aload_0        
        //   290: aload_3        
        //   291: aload_2        
        //   292: invokespecial   com/intellij/refactoring/rename/RenamePsiElementProcessor.substituteElementToRename:(Lcom/intellij/psi/PsiElement;Lcom/intellij/openapi/editor/Editor;)Lcom/intellij/psi/PsiElement;
        //   295: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  53     69     72     76     Lcom/intellij/util/IncorrectOperationException;
        //  83     97     97     101    Lcom/intellij/util/IncorrectOperationException;
        //  101    115    118    122    Lcom/intellij/util/IncorrectOperationException;
        //  108    129    132    136    Lcom/intellij/util/IncorrectOperationException;
        //  122    148    148    152    Lcom/intellij/util/IncorrectOperationException;
        //  181    199    199    203    Lcom/intellij/util/IncorrectOperationException;
        //  206    219    219    223    Lcom/intellij/util/IncorrectOperationException;
        //  223    241    244    248    Lcom/intellij/util/IncorrectOperationException;
        //  258    280    283    287    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0122:
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
    public Runnable getPostRenameCallback(final PsiElement psiElement, final String s, final RefactoringElementListener refactoringElementListener) {
        final OCSynthesizeProperty ocSynthesizeProperty;
        return () -> {
            try {
                if (this.myOldSynthesize != null) {
                    this.myOldSynthesize.replace((PsiElement)this.myNewSynthesize);
                    this.myNewSynthesize = ocSynthesizeProperty;
                    this.myOldSynthesize = ocSynthesizeProperty;
                }
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
        };
    }
    
    @Override
    public boolean isToSearchInComments(final PsiElement psiElement) {
        return true;
    }
    
    @Override
    public boolean isToSearchForTextOccurrences(final PsiElement psiElement) {
        if (psiElement instanceof OCSymbolDeclarator) {
            final OCSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            try {
                if (symbol == null) {
                    return true;
                }
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final boolean b = ocSymbolKind.isLocal();
                if (b) {
                    return false;
                }
                return true;
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            try {
                final OCSymbol ocSymbol = symbol;
                final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                final boolean b = ocSymbolKind.isLocal();
                if (b) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
        }
        return true;
    }
    
    @Override
    public PsiElement getElementToSearchInStringsAndComments(final PsiElement psiElement) {
        Label_0032: {
            Label_0026: {
                try {
                    if (!(psiElement instanceof OCClassDeclaration)) {
                        break Label_0032;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement2;
                    final OCCategoryName ocCategoryName = ocClassDeclaration.getCategoryElement();
                    if (ocCategoryName != null) {
                        break Label_0026;
                    }
                    break Label_0032;
                }
                catch (IncorrectOperationException ex) {
                    throw c(ex);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement2;
                    final OCCategoryName ocCategoryName = ocClassDeclaration.getCategoryElement();
                    if (ocCategoryName != null) {
                        return null;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw c(ex2);
                }
            }
            try {
                if (psiElement instanceof OCFile) {
                    return null;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw c(ex3);
            }
        }
        return psiElement;
    }
    
    @NotNull
    public static RenameUsages validateUsages(final PsiNamedElement psiNamedElement, final Ref<UsageInfo[]> ref) {
        for (final RenamePsiElementProcessor renamePsiElementProcessor : (RenamePsiElementProcessor[])Extensions.getExtensions((ExtensionPointName)OCRenameProcessor.EP_NAME)) {
            Label_0092: {
                RenameUsages renameUsages = null;
                Label_0057: {
                    try {
                        if (!(renamePsiElementProcessor instanceof OCRenameProcessor)) {
                            break Label_0092;
                        }
                        final OCRenameProcessor ocRenameProcessor = (OCRenameProcessor)renamePsiElementProcessor;
                        final OCRenameProcessor ocRenameProcessor2 = ocRenameProcessor;
                        final PsiNamedElement psiNamedElement2 = psiNamedElement;
                        final Ref<UsageInfo[]> ref2 = ref;
                        renameUsages = ocRenameProcessor2.doValidateUsages(psiNamedElement2, ref2);
                        if (renameUsages == null) {
                            break Label_0057;
                        }
                        return renameUsages;
                    }
                    catch (IncorrectOperationException ex) {
                        throw c(ex);
                    }
                    try {
                        final OCRenameProcessor ocRenameProcessor = (OCRenameProcessor)renamePsiElementProcessor;
                        final OCRenameProcessor ocRenameProcessor2 = ocRenameProcessor;
                        final PsiNamedElement psiNamedElement2 = psiNamedElement;
                        final Ref<UsageInfo[]> ref2 = ref;
                        renameUsages = ocRenameProcessor2.doValidateUsages(psiNamedElement2, ref2);
                        if (renameUsages == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor", "validateUsages"));
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw c(ex2);
                    }
                }
                return renameUsages;
            }
        }
        RenameUsages cancel;
        try {
            cancel = RenameUsages.CANCEL;
            if (cancel == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor", "validateUsages"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw c(ex3);
        }
        return cancel;
    }
    
    public RenameUsages doValidateUsages(final PsiNamedElement p0, final Ref<UsageInfo[]> p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //     6: astore_3       
        //     7: iconst_0       
        //     8: istore          4
        //    10: aconst_null    
        //    11: astore          5
        //    13: new             Ljava/util/ArrayList;
        //    16: dup            
        //    17: invokespecial   java/util/ArrayList.<init>:()V
        //    20: astore          6
        //    22: aload_2        
        //    23: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //    26: checkcast       [Lcom/intellij/usageView/UsageInfo;
        //    29: astore          7
        //    31: aload           7
        //    33: arraylength    
        //    34: istore          8
        //    36: iconst_0       
        //    37: istore          9
        //    39: iload           9
        //    41: iload           8
        //    43: if_icmpge       138
        //    46: aload           7
        //    48: iload           9
        //    50: aaload         
        //    51: astore          10
        //    53: aload           10
        //    55: instanceof      Lcom/intellij/refactoring/util/NonCodeUsageInfo;
        //    58: ifeq            98
        //    61: aload_0        
        //    62: aload_1        
        //    63: aload           10
        //    65: invokespecial   com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.a:(Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/usageView/UsageInfo;)Z
        //    68: ifne            132
        //    71: goto            78
        //    74: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    77: athrow         
        //    78: iinc            4, 1
        //    81: aload           6
        //    83: aload           10
        //    85: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    90: pop            
        //    91: goto            132
        //    94: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    97: athrow         
        //    98: aload           6
        //   100: aload           10
        //   102: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   107: pop            
        //   108: aload           10
        //   110: invokevirtual   com/intellij/usageView/UsageInfo.getReference:()Lcom/intellij/psi/PsiReference;
        //   113: instanceof      Lcom/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$BadMacroSubstitutionReference;
        //   116: ifeq            132
        //   119: aload           10
        //   121: invokevirtual   com/intellij/usageView/UsageInfo.getReference:()Lcom/intellij/psi/PsiReference;
        //   124: checkcast       Lcom/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$BadMacroSubstitutionReference;
        //   127: invokevirtual   com/jetbrains/cidr/lang/search/OCElementInMacroSubstitutionReferenceSearch$BadMacroSubstitutionReference.getMacroSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol;
        //   130: astore          5
        //   132: iinc            9, 1
        //   135: goto            39
        //   138: aload_2        
        //   139: aload           6
        //   141: aload           6
        //   143: invokeinterface java/util/List.size:()I
        //   148: anewarray       Lcom/intellij/usageView/UsageInfo;
        //   151: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   156: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   159: aload           5
        //   161: ifnull          276
        //   164: new             Ljava/lang/StringBuilder;
        //   167: dup            
        //   168: invokespecial   java/lang/StringBuilder.<init>:()V
        //   171: aload           5
        //   173: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCMacroSymbol.getNameWithKindUppercase:()Ljava/lang/String;
        //   176: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   179: ldc             " has several usages with different mappings for \""
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: aload_3        
        //   185: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   188: ldc             "\". Rename may break the code. Would you like to proceed?"
        //   190: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   193: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   196: astore          7
        //   198: iconst_3       
        //   199: anewarray       Ljava/lang/String;
        //   202: dup            
        //   203: iconst_0       
        //   204: ldc             "Show usages"
        //   206: aastore        
        //   207: dup            
        //   208: iconst_1       
        //   209: ldc             "Cancel"
        //   211: aastore        
        //   212: dup            
        //   213: iconst_2       
        //   214: ldc             "Proceed"
        //   216: aastore        
        //   217: astore          8
        //   219: aload           7
        //   221: ldc             "Rename"
        //   223: aload           8
        //   225: iconst_0       
        //   226: iconst_1       
        //   227: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   230: aconst_null    
        //   231: invokestatic    com/intellij/openapi/ui/Messages.showDialog:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;IILjavax/swing/Icon;Lcom/intellij/openapi/ui/DialogWrapper$DoNotAskOption;)I
        //   234: istore          9
        //   236: iload           9
        //   238: ifne            249
        //   241: getstatic       com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages.SHOW_USAGES:Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //   244: areturn        
        //   245: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   248: athrow         
        //   249: iload           9
        //   251: iconst_1       
        //   252: if_icmpeq       268
        //   255: iload           9
        //   257: iconst_m1      
        //   258: if_icmpne       276
        //   261: goto            268
        //   264: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   267: athrow         
        //   268: getstatic       com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages.CANCEL:Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //   271: areturn        
        //   272: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   275: athrow         
        //   276: iload           4
        //   278: ifle            493
        //   281: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   284: invokeinterface com/intellij/openapi/application/Application.isUnitTestMode:()Z
        //   289: ifne            493
        //   292: goto            299
        //   295: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   298: athrow         
        //   299: new             Ljava/lang/StringBuilder;
        //   302: dup            
        //   303: invokespecial   java/lang/StringBuilder.<init>:()V
        //   306: iload           4
        //   308: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   311: ldc             " usage"
        //   313: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   316: iload           4
        //   318: iconst_1       
        //   319: if_icmple       338
        //   322: goto            329
        //   325: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   328: athrow         
        //   329: ldc             "s were"
        //   331: goto            340
        //   334: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   337: athrow         
        //   338: ldc             " was"
        //   340: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   343: ldc             " found in comments and non-code files.\nWould you like to rename "
        //   345: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   348: iload           4
        //   350: iconst_1       
        //   351: if_icmple       363
        //   354: ldc             "them"
        //   356: goto            365
        //   359: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   362: athrow         
        //   363: ldc             "it"
        //   365: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   368: ldc             "?"
        //   370: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   373: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   376: astore          7
        //   378: iconst_4       
        //   379: anewarray       Ljava/lang/String;
        //   382: dup            
        //   383: iconst_0       
        //   384: ldc             "Show Usages"
        //   386: aastore        
        //   387: dup            
        //   388: iconst_1       
        //   389: ldc             "Cancel"
        //   391: aastore        
        //   392: dup            
        //   393: iconst_2       
        //   394: ldc             "Rename Only Code Usages"
        //   396: aastore        
        //   397: dup            
        //   398: iconst_3       
        //   399: ldc             "Rename All Usages"
        //   401: aastore        
        //   402: astore          8
        //   404: aload           7
        //   406: ldc             "Rename"
        //   408: aload           8
        //   410: iconst_0       
        //   411: iconst_2       
        //   412: invokestatic    com/intellij/openapi/ui/Messages.getQuestionIcon:()Ljavax/swing/Icon;
        //   415: aconst_null    
        //   416: invokestatic    com/intellij/openapi/ui/Messages.showDialog:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;IILjavax/swing/Icon;Lcom/intellij/openapi/ui/DialogWrapper$DoNotAskOption;)I
        //   419: istore          9
        //   421: iload           9
        //   423: iconst_3       
        //   424: if_icmpne       435
        //   427: getstatic       com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages.RENAME:Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //   430: areturn        
        //   431: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   434: athrow         
        //   435: iload           9
        //   437: iconst_2       
        //   438: if_icmpne       476
        //   441: aload_2        
        //   442: aload_2        
        //   443: invokevirtual   com/intellij/openapi/util/Ref.get:()Ljava/lang/Object;
        //   446: checkcast       [Ljava/lang/Object;
        //   449: invokedynamic   value:()Lcom/intellij/openapi/util/Condition;
        //   454: invokestatic    com/intellij/util/containers/ContainerUtil.filter:([Ljava/lang/Object;Lcom/intellij/openapi/util/Condition;)Ljava/util/List;
        //   457: getstatic       com/intellij/usageView/UsageInfo.EMPTY_ARRAY:[Lcom/intellij/usageView/UsageInfo;
        //   460: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   465: invokevirtual   com/intellij/openapi/util/Ref.set:(Ljava/lang/Object;)V
        //   468: getstatic       com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages.RENAME:Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //   471: areturn        
        //   472: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   475: athrow         
        //   476: iload           9
        //   478: ifne            489
        //   481: getstatic       com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages.SHOW_USAGES:Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //   484: areturn        
        //   485: invokestatic    com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor.c:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   488: athrow         
        //   489: getstatic       com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages.CANCEL:Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //   492: areturn        
        //   493: getstatic       com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages.RENAME:Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //   496: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiNamedElement;Lcom/intellij/openapi/util/Ref<[Lcom/intellij/usageView/UsageInfo;>;)Lcom/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor$RenameUsages;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  53     71     74     78     Lcom/intellij/util/IncorrectOperationException;
        //  61     94     94     98     Lcom/intellij/util/IncorrectOperationException;
        //  236    245    245    249    Lcom/intellij/util/IncorrectOperationException;
        //  249    261    264    268    Lcom/intellij/util/IncorrectOperationException;
        //  255    272    272    276    Lcom/intellij/util/IncorrectOperationException;
        //  276    292    295    299    Lcom/intellij/util/IncorrectOperationException;
        //  281    322    325    329    Lcom/intellij/util/IncorrectOperationException;
        //  299    334    334    338    Lcom/intellij/util/IncorrectOperationException;
        //  340    359    359    363    Lcom/intellij/util/IncorrectOperationException;
        //  421    431    431    435    Lcom/intellij/util/IncorrectOperationException;
        //  435    472    472    476    Lcom/intellij/util/IncorrectOperationException;
        //  476    485    485    489    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0299:
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
    
    private boolean a(@NotNull final PsiNamedElement psiNamedElement, @NotNull final UsageInfo usageInfo) {
        try {
            if (psiNamedElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elementToRename", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor", "skipNonCodeUsage"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        try {
            if (usageInfo == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "usageInfo", "com/jetbrains/cidr/lang/refactoring/rename/OCRenameProcessor", "skipNonCodeUsage"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw c(ex2);
        }
        final PsiElement element = usageInfo.getElement();
        PsiElement parent = null;
        Label_0111: {
            try {
                if (element != null) {
                    parent = element.getParent();
                    break Label_0111;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw c(ex3);
            }
            parent = null;
        }
        final PsiElement psiElement = parent;
        try {
            if (psiElement instanceof OCLocalizedString) {
                return true;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw c(ex4);
        }
        Label_0227: {
            Label_0146: {
                try {
                    if (element == null) {
                        break Label_0227;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCLiteralExpression;
                    if (b) {
                        break Label_0146;
                    }
                    break Label_0227;
                }
                catch (IncorrectOperationException ex5) {
                    throw c(ex5);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = psiElement2 instanceof OCLiteralExpression;
                    if (!b) {
                        break Label_0227;
                    }
                    if (!(psiNamedElement instanceof OCLocalizedString)) {
                        break Label_0227;
                    }
                }
                catch (IncorrectOperationException ex6) {
                    throw c(ex6);
                }
            }
            final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getParentOfType(psiElement, (Class)OCMacroCall.class);
            OCReferenceElement macroReferenceElement = null;
            Label_0192: {
                try {
                    if (ocMacroCall != null) {
                        macroReferenceElement = ocMacroCall.getMacroReferenceElement();
                        break Label_0192;
                    }
                }
                catch (IncorrectOperationException ex7) {
                    throw c(ex7);
                }
                macroReferenceElement = null;
            }
            final OCReferenceElement ocReferenceElement = macroReferenceElement;
            try {
                if (ocReferenceElement == null) {
                    break Label_0227;
                }
                final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                final String s = ocReferenceElement2.getName();
                final String s2 = "NSLocalizedString";
                final boolean b2 = s.startsWith(s2);
                if (b2) {
                    return true;
                }
                break Label_0227;
            }
            catch (IncorrectOperationException ex8) {
                throw c(ex8);
            }
            try {
                final OCReferenceElement ocReferenceElement2 = ocReferenceElement;
                final String s = ocReferenceElement2.getName();
                final String s2 = "NSLocalizedString";
                final boolean b2 = s.startsWith(s2);
                if (b2) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex9) {
                throw c(ex9);
            }
        }
        for (final OCRenameProcessorExtension ocRenameProcessorExtension : (OCRenameProcessorExtension[])Extensions.getExtensions((ExtensionPointName)OCRenameProcessorExtension.EP)) {
            try {
                if (ocRenameProcessorExtension.skipNonCodeUsage(psiNamedElement, usageInfo)) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex10) {
                throw c(ex10);
            }
        }
        return false;
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
    
    public enum RenameUsages
    {
        RENAME, 
        CANCEL, 
        SHOW_USAGES;
    }
    
    interface AssociatedElementsProcessor
    {
        boolean processIvar(final OCInstanceVariableSymbol p0, final OCPropertySymbol p1);
        
        boolean processPropertyAccessors(final OCPropertySymbol p0);
        
        boolean processProperty(final OCPropertySymbol p0, final OCSymbol p1);
        
        boolean processClassAlias(final OCClassSymbol p0, final OCCompatibilityAliasSymbol p1);
        
        boolean processClass(final OCCompatibilityAliasSymbol p0, final OCClassSymbol p1);
    }
}
