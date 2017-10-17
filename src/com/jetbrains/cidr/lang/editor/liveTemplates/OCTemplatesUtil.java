// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.Set;
import com.intellij.codeInsight.template.Result;
import com.intellij.codeInsight.template.Expression;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.types.OCType;
import java.util.Iterator;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.psi.text.BlockSupport;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.PsiDocumentManager;
import java.util.Collection;
import com.intellij.codeInsight.template.ExpressionContext;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import java.util.ArrayList;
import java.util.HashSet;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.diagnostic.Logger;

public class OCTemplatesUtil
{
    private static final Logger LOG;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    @NotNull
    public static List<OCDeclaratorSymbol> getVariablesVisibleAt(final PsiElement psiElement, final boolean b) {
        final HashSet set = new HashSet();
        final ArrayList list = new ArrayList<OCDeclaratorSymbol>();
        final Processor processor = ocSymbol -> {
            try {
                if (!(ocSymbol instanceof OCDeclaratorSymbol) || !set.add(ocSymbol.getName())) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            final OCSymbolKind kind = ocSymbol.getKind();
            Label_0057: {
                try {
                    if (kind.isLocal()) {
                        break Label_0057;
                    }
                    final OCSymbolKind ocSymbolKind = kind;
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.STRUCT_FIELD;
                    if (ocSymbolKind == ocSymbolKind2) {
                        break Label_0057;
                    }
                    return true;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCSymbolKind ocSymbolKind = kind;
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.STRUCT_FIELD;
                    if (ocSymbolKind == ocSymbolKind2) {
                        list.add(ocSymbol);
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            return true;
        };
        ArrayList list2 = null;
        Label_0053: {
            try {
                if (b) {
                    OCResolveUtil.processSymbols(null, psiElement, (Processor<OCSymbol>)processor);
                    break Label_0053;
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
            OCResolveUtil.processLocalSymbols(null, psiElement, (Processor<OCSymbol>)processor, false);
            try {
                list2 = list;
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getVariablesVisibleAt"));
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        return (List<OCDeclaratorSymbol>)list2;
    }
    
    @Nullable
    public static Collection<String> getNames(final ExpressionContext expressionContext) {
        final Project project = expressionContext.getProject();
        final int startOffset = expressionContext.getStartOffset();
        Collection<String> collection = null;
        final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(expressionContext.getEditor().getDocument());
        final PsiElement element = psiFile.findElementAt(startOffset);
        if (element.getNode().getElementType() == OCTokenTypes.IDENTIFIER) {
            collection = a(project, element);
        }
        else {
            final PsiFile psiFile2 = (PsiFile)psiFile.copy();
            final Project project2;
            final BlockSupport blockSupport;
            final PsiFile psiFile3;
            final int n;
            ApplicationManager.getApplication().runWriteAction(() -> {
                BlockSupport.getInstance(project2);
                try {
                    blockSupport.reparseRange(psiFile3, n, n, "xxx");
                }
                catch (IncorrectOperationException ex) {
                    OCTemplatesUtil.LOG.error((Throwable)ex);
                }
                return;
            });
            final PsiElement element2 = psiFile2.findElementAt(startOffset);
            if (element2.getNode().getElementType() == OCTokenTypes.IDENTIFIER) {
                collection = a(project, element2);
            }
        }
        return collection;
    }
    
    @Nullable
    private static Collection<String> a(final Project project, final PsiElement psiElement) {
        if (psiElement.getParent() instanceof OCDeclarator) {
            final OCDeclarator ocDeclarator = (OCDeclarator)psiElement.getParent();
            try {
                if (psiElement.equals(ocDeclarator.getNameIdentifier())) {
                    return OCNameSuggester.suggestForDeclaration(ocDeclarator, false, (Collection<String>)Collections.emptyList());
                }
            }
            catch (IncorrectOperationException ex) {
                throw a(ex);
            }
        }
        return null;
    }
    
    @NotNull
    public static PsiElement getInsertionPlace(@NotNull final ExpressionContext expressionContext) {
        try {
            if (expressionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getInsertionPlace"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final Editor editor = expressionContext.getEditor();
        Logger log = null;
        boolean b = false;
        Label_0083: {
            Label_0074: {
                try {
                    log = OCLog.LOG;
                    if (editor == null) {
                        break Label_0074;
                    }
                    final Editor editor2 = editor;
                    final Project project = editor2.getProject();
                    if (project != null) {
                        break Label_0074;
                    }
                    break Label_0074;
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                try {
                    final Editor editor2 = editor;
                    final Project project = editor2.getProject();
                    if (project != null) {
                        b = true;
                        break Label_0083;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
            }
            b = false;
        }
        log.assertTrue(b, (Object)"Template call in undefined editor");
        final PsiFile psiFile = PsiDocumentManager.getInstance(editor.getProject()).getPsiFile(editor.getDocument());
        Logger log2 = null;
        boolean b2 = false;
        Label_0124: {
            try {
                log2 = OCLog.LOG;
                if (psiFile != null) {
                    b2 = true;
                    break Label_0124;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw a(ex4);
            }
            b2 = false;
        }
        log2.assertTrue(b2, (Object)"Template call in undefined file");
        final PsiElement element = psiFile.findElementAt(expressionContext.getStartOffset());
        PsiElement psiElement = null;
        Label_0159: {
            Logger log3;
            try {
                log3 = OCLog.LOG;
                if (element != null) {
                    final boolean b3 = true;
                    break Label_0159;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw a(ex5);
            }
            final boolean b3 = false;
            try {
                log3.assertTrue(b3, (Object)"Template call in undefined context");
                psiElement = element;
                if (psiElement == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getInsertionPlace"));
                }
            }
            catch (IncorrectOperationException ex6) {
                throw a(ex6);
            }
        }
        return psiElement;
    }
    
    @NotNull
    public static List<OCDeclaratorSymbol> getVariablesWithBeginEnd(@NotNull final ExpressionContext expressionContext) {
        try {
            if (expressionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getVariablesWithBeginEnd"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final PsiElement insertionPlace = getInsertionPlace(expressionContext);
        final ArrayList<OCDeclaratorSymbol> list = new ArrayList<OCDeclaratorSymbol>();
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : getVariablesVisibleAt(insertionPlace, true)) {
            OCType ocType = ocDeclaratorSymbol.getResolvedType();
            if (!ocType.isUnresolved(insertionPlace)) {
                try {
                    if (ocType.isUnknown()) {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw a(ex2);
                }
                if (ocType instanceof OCCppReferenceType) {
                    ocType = ((OCCppReferenceType)ocType).getRefType();
                }
                final OCExpression expressionFromText = OCElementFactory.expressionFromText(ocDeclaratorSymbol.getName(), insertionPlace);
                Label_0171: {
                    try {
                        if (OCTemplatesUtil.$assertionsDisabled) {
                            break Label_0171;
                        }
                        final OCExpression ocExpression = expressionFromText;
                        if (ocExpression == null) {
                            break Label_0171;
                        }
                        break Label_0171;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw a(ex3);
                    }
                    try {
                        final OCExpression ocExpression = expressionFromText;
                        if (ocExpression == null) {
                            throw new AssertionError();
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                }
                final OCFile containingOCFile = expressionFromText.getContainingOCFile();
                Label_0228: {
                    try {
                        if (!(ocType instanceof OCStructType)) {
                            continue;
                        }
                        final OCExpression ocExpression2 = expressionFromText;
                        final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType;
                        final OCStructType ocStructType = (OCStructType)ocCppReferenceType;
                        final OCFile ocFile = containingOCFile;
                        final Pair<OCCodeInsightUtil.MemberBeginEndSearchResult, OCType> pair = OCCodeInsightUtil.getReturnTypeOfBeginEndPair(ocExpression2, ocStructType, ocFile);
                        final Object o = pair.getFirst();
                        final OCCodeInsightUtil.MemberBeginEndSearchResult memberBeginEndSearchResult = OCCodeInsightUtil.MemberBeginEndSearchResult.OK;
                        if (o == memberBeginEndSearchResult) {
                            break Label_0228;
                        }
                        continue;
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                    try {
                        final OCExpression ocExpression2 = expressionFromText;
                        final OCCppReferenceType ocCppReferenceType = (OCCppReferenceType)ocType;
                        final OCStructType ocStructType = (OCStructType)ocCppReferenceType;
                        final OCFile ocFile = containingOCFile;
                        final Pair<OCCodeInsightUtil.MemberBeginEndSearchResult, OCType> pair = OCCodeInsightUtil.getReturnTypeOfBeginEndPair(ocExpression2, ocStructType, ocFile);
                        final Object o = pair.getFirst();
                        final OCCodeInsightUtil.MemberBeginEndSearchResult memberBeginEndSearchResult = OCCodeInsightUtil.MemberBeginEndSearchResult.OK;
                        if (o != memberBeginEndSearchResult) {
                            continue;
                        }
                        list.add(ocDeclaratorSymbol);
                    }
                    catch (IncorrectOperationException ex6) {
                        throw a(ex6);
                    }
                }
            }
        }
        ArrayList<OCDeclaratorSymbol> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getVariablesWithBeginEnd"));
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        return list2;
    }
    
    @NotNull
    public static List<OCDeclaratorSymbol> getVariablesWithMethods(@NotNull final ExpressionContext expressionContext, @NotNull final List<String> list) {
        try {
            if (expressionContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getVariablesWithMethods"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "requiredMethods", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getVariablesWithMethods"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        final PsiElement insertionPlace = getInsertionPlace(expressionContext);
        final ArrayList<OCDeclaratorSymbol> list2 = new ArrayList<OCDeclaratorSymbol>();
        for (final OCDeclaratorSymbol ocDeclaratorSymbol : getVariablesVisibleAt(insertionPlace, true)) {
            OCType ocType = ocDeclaratorSymbol.getResolvedType();
            if (!ocType.isUnresolved(insertionPlace)) {
                try {
                    if (ocType.isUnknown()) {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw a(ex3);
                }
                if (ocType instanceof OCCppReferenceType) {
                    ocType = ((OCCppReferenceType)ocType).getRefType();
                }
                if (!(ocType instanceof OCStructType)) {
                    continue;
                }
                final OCExpression expressionFromText = OCElementFactory.expressionFromText(ocDeclaratorSymbol.getName(), insertionPlace);
                Label_0225: {
                    try {
                        if (OCTemplatesUtil.$assertionsDisabled) {
                            break Label_0225;
                        }
                        final OCExpression ocExpression = expressionFromText;
                        if (ocExpression == null) {
                            break Label_0225;
                        }
                        break Label_0225;
                    }
                    catch (IncorrectOperationException ex4) {
                        throw a(ex4);
                    }
                    try {
                        final OCExpression ocExpression = expressionFromText;
                        if (ocExpression == null) {
                            throw new AssertionError();
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw a(ex5);
                    }
                }
                final OCFile containingOCFile = expressionFromText.getContainingOCFile();
                final OCStructType ocStructType = (OCStructType)ocType;
                boolean b = true;
                final Iterator<String> iterator2 = list.iterator();
                while (iterator2.hasNext()) {
                    if (!a(ocStructType, containingOCFile, iterator2.next())) {
                        b = false;
                        break;
                    }
                }
                try {
                    if (!b) {
                        continue;
                    }
                    list2.add(ocDeclaratorSymbol);
                }
                catch (IncorrectOperationException ex6) {
                    throw a(ex6);
                }
            }
        }
        ArrayList<OCDeclaratorSymbol> list3;
        try {
            list3 = list2;
            if (list3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getVariablesWithMethods"));
            }
        }
        catch (IncorrectOperationException ex7) {
            throw a(ex7);
        }
        return list3;
    }
    
    private static boolean a(@NotNull final OCStructType ocStructType, @NotNull final OCFile ocFile, @NotNull final String s) {
        try {
            if (ocStructType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "hasMemberFunction"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        try {
            if (ocFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "hasMemberFunction"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw a(ex2);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "hasMemberFunction"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)ocFile);
        try {
            if (!ocStructType.collectMethods(s, ocResolveContext).isEmpty()) {
                return true;
            }
        }
        catch (IncorrectOperationException ex4) {
            throw a(ex4);
        }
        return false;
    }
    
    @NotNull
    public static List<String> getStringsList(@NotNull final Expression[] array, final ExpressionContext expressionContext) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "params", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getStringsList"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw a(ex);
        }
        final ArrayList<String> list = new ArrayList<String>();
        for (int length = array.length, i = 0; i < length; ++i) {
            final Result calculateResult = array[i].calculateResult(expressionContext);
            try {
                if (calculateResult != null) {
                    list.add(calculateResult.toString());
                }
            }
            catch (IncorrectOperationException ex2) {
                throw a(ex2);
            }
        }
        ArrayList<String> list2;
        try {
            list2 = list;
            if (list2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/liveTemplates/OCTemplatesUtil", "getStringsList"));
            }
        }
        catch (IncorrectOperationException ex3) {
            throw a(ex3);
        }
        return list2;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCTemplatesUtil.class.desiredAssertionStatus()) {
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
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.editor.liveTemplates.OCTemplatesUtil");
    }
    
    private static IncorrectOperationException a(final IncorrectOperationException ex) {
        return ex;
    }
}
