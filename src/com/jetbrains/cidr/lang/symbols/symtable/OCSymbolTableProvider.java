// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.symtable;

import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.OCBuilderDriver;
import com.jetbrains.cidr.lang.symbols.BuilderDriverBase;
import com.jetbrains.cidr.lang.psi.impl.OCLazyElementBase;
import com.intellij.openapi.util.Ref;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.ASTStructure;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.lang.PsiBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import com.intellij.lang.NodeStructure;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.parser.OCParsing;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.lang.PsiBuilderFactory;
import com.jetbrains.cidr.lang.preprocessor.OCPreprocessingLexer;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.lang.LanguageParserDefinitions;
import com.intellij.lang.ParserDefinition;
import com.intellij.openapi.progress.ProgressManager;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCFile;

public class OCSymbolTableProvider extends SymbolTableProvider<OCFile>
{
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public boolean isSource(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "isSource"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return psiFile instanceof OCFile;
    }
    
    @Override
    public boolean isSource(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "isSource"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile.getFileType() == OCFileType.INSTANCE) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @Override
    public void onOutOfCodeBlockModification(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "onOutOfCodeBlockModification"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiFile instanceof OCFile) {
                FileSymbolTablesCache.getInstance(psiFile.getProject()).incCidrOutOfCodeBlockTracker();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    @NotNull
    @Override
    public FileSymbolTable calcTable(@NotNull final OCFile ocFile, @NotNull final VirtualFile virtualFile, @NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTable"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTable"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTable"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final ProgressIndicator progressIndicator = ProgressManager.getInstance().getProgressIndicator();
        try {
            if (progressIndicator != null) {
                progressIndicator.setText2(ocFile.getName());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final Project project = ocFile.getProject();
        final OCContextSignatureBuilder signatureBuilder = new OCContextSignatureBuilder(ocInclusionContext.getLanguageKind());
        final ParserDefinition parserDefinition = (ParserDefinition)LanguageParserDefinitions.INSTANCE.forLanguage((Language)OCLanguage.getInstance());
        Label_0220: {
            try {
                if (OCSymbolTableProvider.$assertionsDisabled) {
                    break Label_0220;
                }
                final ParserDefinition parserDefinition2 = parserDefinition;
                if (parserDefinition2 == null) {
                    break Label_0220;
                }
                break Label_0220;
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            try {
                final ParserDefinition parserDefinition2 = parserDefinition;
                if (parserDefinition2 == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        final OCInclusionContext deriveButDontCopyTypes = ocInclusionContext.deriveButDontCopyTypes(false);
        deriveButDontCopyTypes.setSignatureBuilder(signatureBuilder);
        final OCPreprocessingLexer ocPreprocessingLexer = new OCPreprocessingLexer(deriveButDontCopyTypes, ocFile);
        final String fileText = SymbolTableProvider.getFileText((PsiFile)ocFile, virtualFile);
        final PsiBuilder builder = PsiBuilderFactory.getInstance().createBuilder(parserDefinition, (Lexer)ocPreprocessingLexer, (CharSequence)fileText);
        Label_0338: {
            try {
                new OCParsing(builder, (IElementType)OCTokenTypes.OC_FILE, OCParsing.BlockParsingMode.SKIP).parseFileContents();
                deriveButDontCopyTypes.setSignatureBuilder(null);
                if (OCSymbolTableProvider.$assertionsDisabled) {
                    break Label_0338;
                }
                final OCInclusionContext ocInclusionContext2 = ocInclusionContext;
                final OCLanguageKind ocLanguageKind = ocInclusionContext2.getLanguageKind();
                final OCInclusionContext ocInclusionContext3 = deriveButDontCopyTypes;
                final OCLanguageKind ocLanguageKind2 = ocInclusionContext3.getLanguageKind();
                if (ocLanguageKind != ocLanguageKind2) {
                    break Label_0338;
                }
                break Label_0338;
            }
            catch (IllegalArgumentException ex7) {
                throw b(ex7);
            }
            try {
                final OCInclusionContext ocInclusionContext2 = ocInclusionContext;
                final OCLanguageKind ocLanguageKind = ocInclusionContext2.getLanguageKind();
                final OCInclusionContext ocInclusionContext3 = deriveButDontCopyTypes;
                final OCLanguageKind ocLanguageKind2 = ocInclusionContext3.getLanguageKind();
                if (ocLanguageKind != ocLanguageKind2) {
                    throw new AssertionError();
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
        }
        final FlyweightCapableTreeStructure lightTree = builder.getLightTree();
        final FileSymbolTable fileSymbolTable = new FileSymbolTable(virtualFile, project, signatureBuilder.build());
        FileSymbolTable a;
        try {
            a = a(ocFile, fileText, ocInclusionContext, fileSymbolTable, (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)lightTree, (com.intellij.lang.NodeStructure<Object>)NodeStructure.LIGHTER_NODE_STRUCTURE);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTable"));
            }
        }
        catch (IllegalArgumentException ex9) {
            throw b(ex9);
        }
        return a;
    }
    
    @NotNull
    @Override
    public FileSymbolTable calcTableUsingPSI(@NotNull final OCFile ocFile, @NotNull final VirtualFile virtualFile, @NotNull final OCInclusionContext ocInclusionContext) {
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTableUsingPSI"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "virtualFile", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTableUsingPSI"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (ocInclusionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTableUsingPSI"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final FileSymbolTable fileSymbolTable = new FileSymbolTable(virtualFile, ocFile.getProject(), new ContextSignature());
        final OCInclusionContext deriveButDontCopyTypes = ocInclusionContext.deriveButDontCopyTypes(false);
        FileSymbolTable a;
        try {
            a = a(ocFile, ocFile.getText(), deriveButDontCopyTypes, fileSymbolTable, (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)new ASTStructure(ocFile.getNode()) {
                @Override
                public int getChildren(@NotNull final ASTNode astNode, @NotNull final Ref<ASTNode[]> into) {
                    try {
                        if (astNode == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "astNode", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider$1", "getChildren"));
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (into == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "into", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider$1", "getChildren"));
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                    try {
                        if (astNode instanceof OCLazyElementBase) {
                            return 0;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw a(ex3);
                    }
                    return super.getChildren(astNode, into);
                }
                
                private static IllegalArgumentException a(final IllegalArgumentException ex) {
                    return ex;
                }
            }, (com.intellij.lang.NodeStructure<Object>)BuilderDriverBase.AST_NAMED_NODE_STRUCTURE);
            if (a == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/symtable/OCSymbolTableProvider", "calcTableUsingPSI"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return a;
    }
    
    private static <T> FileSymbolTable a(final OCFile ocFile, final String s, final OCInclusionContext ocInclusionContext, final FileSymbolTable fileSymbolTable, final FlyweightCapableTreeStructure<T> flyweightCapableTreeStructure, final NodeStructure<T> nodeStructure) {
        new OCBuilderDriver<Object>(ocFile, ocInclusionContext, s, (com.intellij.util.diff.FlyweightCapableTreeStructure<Object>)flyweightCapableTreeStructure, (com.intellij.lang.NodeStructure<Object>)nodeStructure, (Processor<OCSymbol>)new OCSymbolTableProcessor(fileSymbolTable)).processDeclarationsList(flyweightCapableTreeStructure.getRoot());
        fileSymbolTable.sortSymbols();
        return fileSymbolTable;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCSymbolTableProvider.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
}
