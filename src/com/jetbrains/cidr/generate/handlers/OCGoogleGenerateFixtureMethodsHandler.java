// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import com.jetbrains.cidr.lang.psi.OCStruct;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import java.util.Collections;
import java.util.Collection;
import com.jetbrains.cidr.lang.generate.actions.OCCppActionContext;
import com.intellij.openapi.util.Couple;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.CommonProcessors;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.execution.testing.google.CidrGoogleTestUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.symtable.FileSymbolTablesCache;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;

public class OCGoogleGenerateFixtureMethodsHandler extends OCCppGenerateTestHandler
{
    public OCGoogleGenerateFixtureMethodsHandler(final String s, final String s2) {
        super(s, s2);
    }
    
    @Override
    public boolean isValidFor(final Editor editor, final PsiFile psiFile) {
        Label_0028: {
            try {
                if (!FileSymbolTablesCache.areSymbolsLoaded(psiFile.getProject())) {
                    return false;
                }
                final OCGoogleGenerateFixtureMethodsHandler ocGoogleGenerateFixtureMethodsHandler = this;
                final Editor editor2 = editor;
                final PsiFile psiFile2 = psiFile;
                final boolean b = ocGoogleGenerateFixtureMethodsHandler.isValidFor(editor2, psiFile2);
                if (b) {
                    break Label_0028;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw c(ex);
            }
            try {
                final OCGoogleGenerateFixtureMethodsHandler ocGoogleGenerateFixtureMethodsHandler = this;
                final Editor editor2 = editor;
                final PsiFile psiFile2 = psiFile;
                final boolean b = ocGoogleGenerateFixtureMethodsHandler.isValidFor(editor2, psiFile2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw c(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    @Override
    protected OCStructSymbol getParent(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler", "getParent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler", "getParent"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        OCStructSymbol googleTestSymbol = super.getParent(project, editor, psiFile);
        Label_0123: {
            try {
                if (googleTestSymbol != null) {
                    return googleTestSymbol;
                }
                final Editor editor2 = editor;
                if (editor2 == null) {
                    return googleTestSymbol;
                }
                break Label_0123;
            }
            catch (IllegalArgumentException ex3) {
                throw c(ex3);
            }
            try {
                final Editor editor2 = editor;
                if (editor2 == null) {
                    return googleTestSymbol;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw c(ex4);
            }
        }
        final PsiElement element = psiFile.findElementAt(editor.getCaretModel().getOffset());
        if (element != null) {
            final OCFunctionDefinition ocFunctionDefinition = (OCFunctionDefinition)PsiTreeUtil.getContextOfType(element, new Class[] { OCFunctionDefinition.class });
            if (ocFunctionDefinition != null) {
                final OCFunctionSymbol symbol = ocFunctionDefinition.getSymbol();
                if (symbol != null) {
                    final OCSymbolWithQualifiedName<OCElement> resolvedOwner = symbol.getResolvedOwner();
                    if (resolvedOwner instanceof OCStructSymbol) {
                        googleTestSymbol = (OCStructSymbol)resolvedOwner;
                    }
                }
            }
            if (googleTestSymbol == null) {
                final OCMacroCall googleTestMacros = CidrGoogleTestUtil.findGoogleTestMacros(element);
                if (googleTestMacros != null) {
                    final List<OCMacroCallArgument> arguments = googleTestMacros.getArguments();
                    if (arguments.size() >= 2) {
                        googleTestSymbol = CidrGoogleTestUtil.findGoogleTestSymbol(element.getProject(), CidrGoogleTestUtil.extractArgumentValue((PsiElement)arguments.get(0)), CidrGoogleTestUtil.extractArgumentValue((PsiElement)arguments.get(1)));
                    }
                }
            }
            if (googleTestSymbol == null) {
                final Couple<String> fullSuiteNameFromMacro = CidrGoogleTestUtil.extractFullSuiteNameFromMacro(element);
                if (fullSuiteNameFromMacro != null) {
                    final CommonProcessors.FindProcessor<OCSymbol> findProcessor = new CommonProcessors.FindProcessor<OCSymbol>() {
                        protected boolean accept(final OCSymbol ocSymbol) {
                            return ocSymbol instanceof OCStructSymbol;
                        }
                    };
                    OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)findProcessor, (String)fullSuiteNameFromMacro.first);
                    if (findProcessor.isFound()) {
                        googleTestSymbol = (OCStructSymbol)findProcessor.getFoundValue();
                    }
                }
            }
        }
        try {
            if (googleTestSymbol != null) {
                return CidrGoogleTestUtil.findGoogleTestFixture(googleTestSymbol, true);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw c(ex5);
        }
        return null;
    }
    
    @Nullable
    @Override
    protected OCCppActionContext<OCStructSymbol, OCFunctionSymbol> evaluateActionContext(final OCStructSymbol ocStructSymbol, final PsiElement psiElement) {
        return new OCCppActionContext<OCStructSymbol, OCFunctionSymbol>(ocStructSymbol, psiElement) {
            @NotNull
            @Override
            public Collection<OCFunctionSymbol> getMemberCandidates() {
                List<OCFunctionSymbol> emptyList;
                try {
                    emptyList = Collections.emptyList();
                    if (emptyList == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler$2", "getMemberCandidates"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return emptyList;
            }
            
            @Override
            public boolean isValid() {
                return CidrGoogleTestUtil.isGoogleTestFixture(ocStructSymbol, true);
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    @Override
    protected int getInsertPos(@NotNull PsiElement nextSibling, final int n, final PsiElement psiElement, @NotNull final List<OCFunctionSymbol> list, @NotNull final OCCppActionContext<OCStructSymbol, OCFunctionSymbol> ocCppActionContext) {
        try {
            if (nextSibling == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler", "getInsertPos"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "members", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler", "getInsertPos"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        try {
            if (ocCppActionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler", "getInsertPos"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw c(ex3);
        }
        final PsiElement locateDefinition = ocCppActionContext.getParent().locateDefinition();
        while (true) {
            Label_0178: {
                try {
                    if (locateDefinition == null) {
                        break Label_0202;
                    }
                    final PsiElement psiElement2 = locateDefinition;
                    final PsiFile psiFile = psiElement2.getContainingFile();
                    final PsiElement psiElement3 = nextSibling;
                    final PsiFile psiFile2 = psiElement3.getContainingFile();
                    final boolean b = psiFile.equals(psiFile2);
                    if (b) {
                        break Label_0178;
                    }
                    break Label_0202;
                }
                catch (IllegalArgumentException ex4) {
                    throw c(ex4);
                }
                try {
                    final PsiElement psiElement2 = locateDefinition;
                    final PsiFile psiFile = psiElement2.getContainingFile();
                    final PsiElement psiElement3 = nextSibling;
                    final PsiFile psiFile2 = psiElement3.getContainingFile();
                    final boolean b = psiFile.equals(psiFile2);
                    if (!b) {
                        break Label_0202;
                    }
                    if (locateDefinition.getTextRange().contains(n)) {
                        break Label_0202;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw c(ex5);
                }
            }
            Label_0199: {
                break Label_0199;
                while (OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(OCElementUtil.getElementType(nextSibling))) {
                    nextSibling = nextSibling.getNextSibling();
                }
                final PsiElement nonStrictParentOfType = PsiTreeUtil.getNonStrictParentOfType(nextSibling, new Class[] { OCStruct.class, OCFunctionDefinition.class });
                try {
                    if (nonStrictParentOfType == null) {
                        return -1;
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw c(ex6);
                }
                try {
                    if (nonStrictParentOfType instanceof OCStruct) {
                        return ((OCStruct)nonStrictParentOfType).getFunctionsStartOffset();
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw c(ex7);
                }
                return nonStrictParentOfType.getTextRange().getStartOffset();
            }
            nextSibling = locateDefinition;
            continue;
        }
    }
    
    @NotNull
    @Override
    protected String getTemplateText(@NotNull final FileTemplateDescriptor fileTemplateDescriptor, final Project project) {
        try {
            if (fileTemplateDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateDesc", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler", "getTemplateText"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw c(ex);
        }
        String string;
        try {
            string = "protected:" + super.getTemplateText(fileTemplateDescriptor, project);
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/generate/handlers/OCGoogleGenerateFixtureMethodsHandler", "getTemplateText"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw c(ex2);
        }
        return string;
    }
    
    private static IllegalArgumentException c(final IllegalArgumentException ex) {
        return ex;
    }
}
