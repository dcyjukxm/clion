// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.documentation;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.jetbrains.cidr.lang.psi.OCPropertyAttribute;
import java.util.LinkedList;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import java.util.Collections;
import com.jetbrains.cidr.lang.documentation.doxygen.api.DoxygenFacade;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.intellij.psi.tree.IElementType;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.openapi.vfs.CharsetToolkit;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.ExecutionException;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.psi.PsiNamedElement;
import com.jetbrains.cidr.lang.psi.OCProtocol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.types.visitors.OCTypeVisitor;
import com.jetbrains.cidr.lang.types.visitors.OCTypeParameterResolveVisitor;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.lang.Language;
import com.intellij.psi.impl.source.codeStyle.CodeFormatterFacade;
import com.intellij.psi.impl.source.SourceTreeToPsiMap;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiComment;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCTypeParameterSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCTemplateSymbol;
import com.jetbrains.cidr.lang.types.OCTypeArgument;
import com.jetbrains.cidr.lang.psi.OCNoexceptSpecifier;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolReference;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.psi.OCPropertyAttributesList;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.jetbrains.cidr.lang.search.usages.OCFindUsagesProvider;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.psi.OCStructLike;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceAliasSymbol;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceAlias;
import com.jetbrains.cidr.lang.symbols.cpp.OCAliasUsingSymbol;
import com.jetbrains.cidr.lang.psi.OCCppUsingStatement;
import com.jetbrains.cidr.lang.symbols.cpp.OCMacroSymbol;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCCategoryName;
import com.jetbrains.cidr.lang.psi.OCTypeParameterDeclaration;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.util.OCDocUtil;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.psi.OCCppNamespace;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.Iterator;
import com.intellij.psi.PsiReference;
import com.intellij.codeInsight.CodeInsightBundle;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.codeInsight.TargetElementUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiManager;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import java.util.List;
import com.intellij.psi.PsiElement;
import com.intellij.util.containers.hash.HashMap;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import java.util.Map;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.documentation.DocumentationProvider;

public class CidrDocumentationProvider implements DocumentationProvider
{
    public static final String PSI_LINK_PREFIX_METHOD = "method,";
    @Nullable
    final ExternalProvider myExternalProvider;
    private Map<String, CodeStyleSettings> mySettings;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public CidrDocumentationProvider() {
        this(null);
    }
    
    public CidrDocumentationProvider(@Nullable final ExternalProvider myExternalProvider) {
        this.mySettings = (Map<String, CodeStyleSettings>)new HashMap();
        this.myExternalProvider = myExternalProvider;
    }
    
    @Nullable
    public String getQuickNavigateInfo(final PsiElement psiElement, final PsiElement psiElement2) {
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final String quickDocContent = this.quickDocContent(psiElement, psiElement2, false);
        try {
            if (quickDocContent != null) {
                return wrapDocInHtml(quickDocContent);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    public List<String> getUrlFor(final PsiElement psiElement, final PsiElement psiElement2) {
        return null;
    }
    
    @Nullable
    public String generateDoc(PsiElement parent, @Nullable final PsiElement psiElement) {
        try {
            if (parent == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (parent instanceof OCMethodSelectorPart) {
            parent = parent.getParent();
        }
        if (parent instanceof OCSendMessageExpression) {
            final String a = a((OCSendMessageExpression)parent);
            try {
                if (a != null) {
                    return a;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final String generateDocInnerHtml = this.generateDocInnerHtml(parent, psiElement, false);
        try {
            if (generateDocInnerHtml == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return wrapDocInHtml(generateDocInnerHtml);
    }
    
    @Nullable
    public PsiElement getDocumentationElementForLookupItem(@Nullable final PsiManager psiManager, @Nullable final Object o, @Nullable final PsiElement psiElement) {
        try {
            if (o instanceof OCSymbol) {
                return ((OCSymbol)o).locateDefinition();
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    public PsiElement getDocumentationElementForLink(@Nullable final PsiManager psiManager, @Nullable String substring, @Nullable final PsiElement psiElement) {
        Label_0015: {
            try {
                if (substring == null) {
                    break Label_0015;
                }
                final PsiManager psiManager2 = psiManager;
                if (psiManager2 == null) {
                    break Label_0015;
                }
                break Label_0015;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiManager psiManager2 = psiManager;
                if (psiManager2 == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        if (substring.startsWith("method,")) {
            substring = substring.substring("method,".length());
            final List split = StringUtil.split(substring, ",");
            if (split.size() == 3) {
                final String s = split.get(1);
                final String s2 = split.get(2);
                final VirtualFile fileByPath = LocalFileSystem.getInstance().findFileByPath(s);
                try {
                    if (fileByPath == null || !fileByPath.isValid()) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                final PsiFile file = psiManager.findFile(fileByPath);
                try {
                    if (file == null || !file.isValid()) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                final PsiElement element = file.findElementAt(Integer.parseInt(s2));
                Label_0181: {
                    try {
                        if (element == null) {
                            return null;
                        }
                        final PsiElement psiElement2 = element;
                        final boolean b = psiElement2.isValid();
                        if (b) {
                            break Label_0181;
                        }
                        return null;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final PsiElement psiElement2 = element;
                        final boolean b = psiElement2.isValid();
                        if (b) {
                            return PsiTreeUtil.getParentOfType(element, (Class)OCMethod.class, false);
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
            }
        }
        else {
            final int lastIndex = substring.lastIndexOf(35);
            if (lastIndex > -1) {
                final String substring2 = substring.substring(0, lastIndex);
                final String substring3 = substring.substring(lastIndex + 1);
                final VirtualFile fileByPath2 = LocalFileSystem.getInstance().findFileByPath(substring2);
                try {
                    if (fileByPath2 == null || !fileByPath2.isValid()) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
                final PsiFile file2 = psiManager.findFile(fileByPath2);
                try {
                    if (file2 == null || !file2.isValid()) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
                final int int1 = Integer.parseInt(substring3);
                return TargetElementUtil.getInstance().getNamedElement(file2.findElementAt(int1), int1);
            }
        }
        return null;
    }
    
    @Nullable
    private static String a(@NotNull final OCMethodSymbol ocMethodSymbol) {
        try {
            if (ocMethodSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "buildLinkForMethod"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final StringBuilder append = new StringBuilder("psi_element://").append("method,");
        append.append(ocMethodSymbol.getName());
        final VirtualFile containingFile = ocMethodSymbol.getContainingFile();
        try {
            if (containingFile != null) {
                append.append(',').append(containingFile.getPath()).append(',').append(ocMethodSymbol.getOffset());
                return append.toString();
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    private static String a(@NotNull final OCSendMessageExpression ocSendMessageExpression) {
        try {
            if (ocSendMessageExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sme", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "methodCandidates"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiReference reference = ocSendMessageExpression.getReference();
        try {
            if (reference == null || !(reference instanceof OCPolyVariantReference)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final List<Object> resolveToSymbols = ((OCPolyVariantReference<Object>)reference).resolveToSymbols();
        final StringBuilder sb = new StringBuilder("");
        for (final OCMethodSymbol next : resolveToSymbols) {
            if (next instanceof OCMethodSymbol) {
                final OCMethodSymbol ocMethodSymbol = next;
                final OCClassSymbol ocClassSymbol = ((OCSymbolWithParent<T, OCClassSymbol>)ocMethodSymbol).getParent();
                final String a = a(ocMethodSymbol);
                try {
                    if (a == null) {
                        continue;
                    }
                    sb.append("<a href=\"").append(a).append("\">").append(ocMethodSymbol.getSignature()).append(" (").append(ocClassSymbol.getPresentableName()).append(")</a><br>");
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
        }
        return CodeInsightBundle.message("javadoc.candidates", new Object[] { ocSendMessageExpression.getExpectedMethodSignature(), sb.toString() });
    }
    
    @Nullable
    protected String quickDocContent(@NotNull PsiElement psiElement, @Nullable PsiElement parent, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "quickDocContent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0074: {
            try {
                if (parent == null || !(parent.getParent() instanceof OCReferenceElement)) {
                    break Label_0074;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            parent = parent.getParent();
        }
        final PsiElement c = c(psiElement);
        try {
            if (c == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        psiElement = c;
        try {
            if (psiElement instanceof OCMethod) {
                return a((OCMethod)psiElement);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        if (psiElement instanceof OCCppNamespace) {
            final StringBuilder sb = new StringBuilder(a(psiElement.getContainingFile()));
            final OCNamespaceSymbol symbol = ((OCCppNamespace)psiElement).getSymbol();
            if (symbol instanceof OCNamespaceSymbol) {
                final OCNamespaceSymbol ocNamespaceSymbol = symbol;
                try {
                    if (ocNamespaceSymbol.isInlineNamespace()) {
                        sb.append(OCTokenTypes.INLINE_KEYWORD.getName()).append(" ");
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
                sb.append(symbol.getKindLowercase()).append(" ");
                final String namespace = OCDocUtil.getNamespace(ocNamespaceSymbol);
                try {
                    if (!namespace.isEmpty()) {
                        sb.append(namespace).append("::");
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw b(ex6);
                }
                return sb.append("<b>").append(symbol.getName()).append("</b>").toString();
            }
            return null;
        }
        else {
            if (psiElement instanceof OCClassDeclaration) {
                final StringBuilder sb2 = new StringBuilder();
                final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)psiElement;
                final OCObjectType type = ocClassDeclaration.getType();
                if (type != null) {
                    final OCInterfaceSymbol interface1 = type.getInterface();
                    if (interface1 != null) {
                        final OCFile containingOCFile = interface1.getContainingOCFile();
                        try {
                            if (containingOCFile != null) {
                                sb2.append(a((PsiFile)containingOCFile));
                            }
                        }
                        catch (IllegalArgumentException ex7) {
                            throw b(ex7);
                        }
                    }
                }
                return sb2.append(a(ocClassDeclaration)).toString();
            }
            if (psiElement instanceof OCMethodSelectorPart) {
                final StringBuilder sb3 = new StringBuilder();
                a(sb3, ((OCMethodSelectorPart)psiElement).getRawType(), (PsiFile)((OCMethodSelectorPart)psiElement).getContainingOCFile(), true);
                sb3.append(" <b>");
                sb3.append(((OCMethodSelectorPart)psiElement).getName());
                sb3.append("</b>");
                return sb3.toString();
            }
            try {
                if (psiElement instanceof OCTypeParameterDeclaration) {
                    return a((OCTypeParameterDeclaration)psiElement);
                }
            }
            catch (IllegalArgumentException ex8) {
                throw b(ex8);
            }
            if (psiElement instanceof OCCategoryName) {
                final PsiElement parent2 = ((OCCategoryName)psiElement).getParent();
                if (parent2 instanceof OCClassDeclaration) {
                    final OCClassDeclaration ocClassDeclaration2 = (OCClassDeclaration)parent2;
                    try {
                        if (b) {
                            return this.generateDocInnerHtml((PsiElement)ocClassDeclaration2, parent, false);
                        }
                    }
                    catch (IllegalArgumentException ex9) {
                        throw b(ex9);
                    }
                    return this.quickDocContent((PsiElement)ocClassDeclaration2, parent, false);
                }
                return null;
            }
            else {
                if (psiElement instanceof OCDeclarator) {
                    final OCDeclarator ocDeclarator = (OCDeclarator)psiElement;
                    final StringBuilder sb4 = new StringBuilder();
                    Object o = null;
                    Object reference = null;
                    Label_0577: {
                        try {
                            if (parent == null) {
                                reference = null;
                                break Label_0577;
                            }
                        }
                        catch (IllegalArgumentException ex10) {
                            throw b(ex10);
                        }
                        reference = parent.getReference();
                    }
                    final Object o2 = reference;
                    if (o2 instanceof OCReference) {
                        o = ((OCReference<OCFunctionSymbol>)o2).resolveToSymbol();
                    }
                    if (o == null) {
                        o = ocDeclarator.getSymbol();
                    }
                    Object containingOCFile2 = null;
                    Label_0633: {
                        try {
                            if (o == null) {
                                containingOCFile2 = null;
                                break Label_0633;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw b(ex11);
                        }
                        containingOCFile2 = ((OCSymbol)o).getContainingOCFile();
                    }
                    final PsiFile psiFile = (PsiFile)containingOCFile2;
                    Label_0678: {
                        Label_0660: {
                            try {
                                if (psiFile == null) {
                                    break Label_0678;
                                }
                                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)o;
                                final OCSymbolKind ocSymbolKind = ocFunctionSymbol.getKind();
                                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.LOCAL_VARIABLE;
                                if (ocSymbolKind != ocSymbolKind2) {
                                    break Label_0660;
                                }
                                break Label_0678;
                            }
                            catch (IllegalArgumentException ex12) {
                                throw b(ex12);
                            }
                            try {
                                final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)o;
                                final OCSymbolKind ocSymbolKind = ocFunctionSymbol.getKind();
                                final OCSymbolKind ocSymbolKind2 = OCSymbolKind.LOCAL_VARIABLE;
                                if (ocSymbolKind != ocSymbolKind2) {
                                    sb4.append(a(psiFile));
                                }
                            }
                            catch (IllegalArgumentException ex13) {
                                throw b(ex13);
                            }
                        }
                        try {
                            if (o != null) {
                                b((OCSymbol)o, sb4);
                                a((OCSymbol)o, sb4);
                            }
                        }
                        catch (IllegalArgumentException ex14) {
                            throw b(ex14);
                        }
                    }
                    final PsiElement parent3 = psiElement.getParent().getParent();
                    if (parent3 instanceof OCProperty) {
                        final OCProperty ocProperty = (OCProperty)parent3;
                        final String c2 = this.c(ocProperty);
                        try {
                            if (c2 != null) {
                                return c2;
                            }
                        }
                        catch (IllegalArgumentException ex15) {
                            throw b(ex15);
                        }
                        final OCClassDeclaration containingClass = ocProperty.getContainingClass();
                        try {
                            if (containingClass != null) {
                                sb4.append(a(containingClass)).append("<br>");
                            }
                        }
                        catch (IllegalArgumentException ex16) {
                            throw b(ex16);
                        }
                        sb4.append("@property ");
                        final OCPropertyAttributesList propertyAttributesList = ocProperty.getPropertyAttributesList();
                        if (propertyAttributesList != null) {
                            sb4.append("(").append(StringUtil.join((Collection)propertyAttributesList.getAttributes(), ocPropertyAttribute -> ocPropertyAttribute.getText(), ", ")).append(") ");
                        }
                    }
                    else if (parent3 instanceof OCInstanceVariablesList) {
                        final OCClassDeclaration ocClassDeclaration3 = (OCClassDeclaration)PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCClassDeclaration.class });
                        try {
                            if (ocClassDeclaration3 != null) {
                                sb4.append(a(ocClassDeclaration3)).append("<br>");
                            }
                        }
                        catch (IllegalArgumentException ex17) {
                            throw b(ex17);
                        }
                    }
                    Label_0945: {
                        Label_0930: {
                            try {
                                if (o == null) {
                                    break Label_0945;
                                }
                                final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)o;
                                final OCSymbolKind ocSymbolKind3 = ocFunctionSymbol2.getKind();
                                final OCSymbolKind ocSymbolKind4 = OCSymbolKind.INSTANCE_VARIABLE;
                                if (ocSymbolKind3 == ocSymbolKind4) {
                                    break Label_0930;
                                }
                                break Label_0945;
                            }
                            catch (IllegalArgumentException ex18) {
                                throw b(ex18);
                            }
                            try {
                                final OCFunctionSymbol ocFunctionSymbol2 = (OCFunctionSymbol)o;
                                final OCSymbolKind ocSymbolKind3 = ocFunctionSymbol2.getKind();
                                final OCSymbolKind ocSymbolKind4 = OCSymbolKind.INSTANCE_VARIABLE;
                                if (ocSymbolKind3 == ocSymbolKind4) {
                                    sb4.append("ivar ");
                                    break Label_0945;
                                }
                            }
                            catch (IllegalArgumentException ex19) {
                                throw b(ex19);
                            }
                        }
                        try {
                            if (((OCSymbol)o).getKind() == OCSymbolKind.TYPEDEF) {
                                sb4.append("typedef ");
                            }
                        }
                        catch (IllegalArgumentException ex20) {
                            throw b(ex20);
                        }
                    }
                    Label_1326: {
                        if (o instanceof OCFunctionSymbol) {
                            OCFunctionSymbol ocFunctionSymbol3 = (OCFunctionSymbol)o;
                            if (ocFunctionSymbol3.isFriend()) {
                                final OCFunctionSymbol ocFunctionSymbol4 = (OCFunctionSymbol)ocFunctionSymbol3.getDefinitionSymbol();
                                if (ocFunctionSymbol4 != null) {
                                    ocFunctionSymbol3 = ocFunctionSymbol4;
                                }
                            }
                            Label_1079: {
                                try {
                                    sb4.append(a(ocFunctionSymbol3));
                                    OCDocUtil.extractModifiers(ocFunctionSymbol3, sb4);
                                    if (ocFunctionSymbol3.isCppConstructor() || ocFunctionSymbol3.isCppDestructor()) {
                                        break Label_1079;
                                    }
                                }
                                catch (IllegalArgumentException ex21) {
                                    throw b(ex21);
                                }
                                final String b2 = b(ocFunctionSymbol3);
                                sb4.append(b2).append(OCDocUtil.delimiter(b2));
                            }
                            sb4.append(c(ocFunctionSymbol3));
                            sb4.append("<b>").append(escapeHTML(ocFunctionSymbol3.getName())).append("</b>");
                            this.a(ocDeclarator, ocFunctionSymbol3, sb4);
                        }
                        else {
                            Label_1278: {
                                boolean b3 = false;
                                Label_1185: {
                                    Label_1176: {
                                        try {
                                            if (!(o instanceof OCDeclaratorSymbol)) {
                                                break Label_1278;
                                            }
                                            final StringBuilder sb5 = sb4;
                                            final OCFunctionSymbol ocFunctionSymbol5 = (OCFunctionSymbol)o;
                                            final String s2 = a(ocFunctionSymbol5);
                                            sb5.append(s2);
                                            final OCFunctionSymbol ocFunctionSymbol6 = (OCFunctionSymbol)o;
                                            final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)ocFunctionSymbol6;
                                            final StringBuilder sb6 = sb4;
                                            OCDocUtil.extractModifiers(ocDeclaratorSymbol, sb6);
                                            final OCFunctionSymbol ocFunctionSymbol7 = (OCFunctionSymbol)o;
                                            final OCSymbolKind ocSymbolKind5 = ocFunctionSymbol7.getKind();
                                            final OCSymbolKind ocSymbolKind6 = OCSymbolKind.ENUM_CONST;
                                            if (ocSymbolKind5 == ocSymbolKind6) {
                                                break Label_1176;
                                            }
                                            break Label_1176;
                                        }
                                        catch (IllegalArgumentException ex22) {
                                            throw b(ex22);
                                        }
                                        try {
                                            final StringBuilder sb5 = sb4;
                                            final OCFunctionSymbol ocFunctionSymbol5 = (OCFunctionSymbol)o;
                                            final String s2 = a(ocFunctionSymbol5);
                                            sb5.append(s2);
                                            final OCFunctionSymbol ocFunctionSymbol6 = (OCFunctionSymbol)o;
                                            final OCDeclaratorSymbol ocDeclaratorSymbol = (OCDeclaratorSymbol)ocFunctionSymbol6;
                                            final StringBuilder sb6 = sb4;
                                            OCDocUtil.extractModifiers(ocDeclaratorSymbol, sb6);
                                            final OCFunctionSymbol ocFunctionSymbol7 = (OCFunctionSymbol)o;
                                            final OCSymbolKind ocSymbolKind5 = ocFunctionSymbol7.getKind();
                                            final OCSymbolKind ocSymbolKind6 = OCSymbolKind.ENUM_CONST;
                                            if (ocSymbolKind5 == ocSymbolKind6) {
                                                b3 = true;
                                                break Label_1185;
                                            }
                                        }
                                        catch (IllegalArgumentException ex23) {
                                            throw b(ex23);
                                        }
                                    }
                                    b3 = false;
                                }
                                final boolean b4 = b3;
                                final String b5 = b((OCSymbol)o);
                                Label_1219: {
                                    StringBuilder append;
                                    String s3;
                                    try {
                                        append = sb4.append(b5);
                                        s3 = b5;
                                        if (b4) {
                                            final String s4 = "::";
                                            break Label_1219;
                                        }
                                    }
                                    catch (IllegalArgumentException ex24) {
                                        throw b(ex24);
                                    }
                                    final String s4 = " ";
                                    try {
                                        append.append(OCDocUtil.delimiter(s3, s4));
                                        if (!b4) {
                                            sb4.append(c((OCSymbol)o));
                                        }
                                    }
                                    catch (IllegalArgumentException ex25) {
                                        throw b(ex25);
                                    }
                                }
                                sb4.append("<b>").append(escapeHTML(((OCSymbol)o).getName())).append("</b>");
                                break Label_1326;
                            }
                            a(sb4, ocDeclarator.getRawType(), psiElement.getContainingFile(), false);
                            sb4.append(" <b>");
                            sb4.append(ocDeclarator.getName());
                            sb4.append("</b>");
                        }
                    }
                    this.a(ocDeclarator, sb4);
                    return sb4.toString();
                }
                if (psiElement instanceof OCDefineDirective) {
                    final PsiFile containingFile = psiElement.getContainingFile();
                    final StringBuilder sb7 = new StringBuilder();
                    sb7.append(a(containingFile));
                    final OCMacroSymbol ocMacroSymbol = ((OCDefineDirective)psiElement).getSymbol();
                    try {
                        if (ocMacroSymbol == null) {
                            return "";
                        }
                    }
                    catch (IllegalArgumentException ex26) {
                        throw b(ex26);
                    }
                    try {
                        sb7.append("#define <b>").append(escapeHTML(ocMacroSymbol.getName())).append("</b>");
                        if (ocMacroSymbol.hasParameterList()) {
                            sb7.append('(');
                            sb7.append(StringUtil.join((Collection)ocMacroSymbol.getParameterNames(), s -> escapeHTML(s), ", "));
                            sb7.append(')');
                        }
                    }
                    catch (IllegalArgumentException ex27) {
                        throw b(ex27);
                    }
                    sb7.append(" ").append(escapeHTML(ocMacroSymbol.getSubstitution()));
                    return sb7.toString();
                }
                if (psiElement instanceof OCCppUsingStatement) {
                    final StringBuilder sb8 = new StringBuilder(a(psiElement.getContainingFile()));
                    final OCSymbol symbol2 = ((OCCppUsingStatement)psiElement).getSymbol();
                    try {
                        if (symbol2 instanceof OCAliasUsingSymbol) {
                            sb8.append("using <b>");
                            sb8.append(symbol2.getName());
                            sb8.append("</b> = ");
                            sb8.append(b(symbol2));
                        }
                    }
                    catch (IllegalArgumentException ex28) {
                        throw b(ex28);
                    }
                    return sb8.toString();
                }
                if (psiElement instanceof OCCppNamespaceAlias) {
                    final StringBuilder sb9 = new StringBuilder(a(psiElement.getContainingFile()));
                    final OCNamespaceAliasSymbol ocNamespaceAliasSymbol = ((OCCppNamespaceAlias)psiElement).getSymbol();
                    Logger log = null;
                    boolean b6 = false;
                    Label_1659: {
                        try {
                            log = OCLog.LOG;
                            if (ocNamespaceAliasSymbol != null) {
                                b6 = true;
                                break Label_1659;
                            }
                        }
                        catch (IllegalArgumentException ex29) {
                            throw b(ex29);
                        }
                        b6 = false;
                    }
                    if (log.assertTrue(b6)) {
                        final OCSymbolKind kind = ocNamespaceAliasSymbol.getKind();
                        final OCSymbolReference namespaceReference = ocNamespaceAliasSymbol.getNamespaceReference();
                        sb9.append(kind.getNameLowercase()).append(" ");
                        sb9.append("<b>").append(ocNamespaceAliasSymbol.getName()).append("</b> = ");
                        sb9.append(namespaceReference.getQualifiedName().getCanonicalName(true));
                    }
                    return sb9.toString();
                }
                if (psiElement instanceof OCStructLike) {
                    final OCStructSymbol ocStructSymbol = ((OCStructLike)psiElement).getSymbol();
                    final StringBuilder sb10 = new StringBuilder(a(psiElement.getContainingFile()));
                    try {
                        if (ocStructSymbol == null) {
                            return sb10.toString();
                        }
                    }
                    catch (IllegalArgumentException ex30) {
                        throw b(ex30);
                    }
                    b(ocStructSymbol, sb10);
                    a((OCSymbol)ocStructSymbol, sb10);
                    final String c3 = c(ocStructSymbol);
                    final String a = a(ocStructSymbol);
                    Label_1849: {
                        try {
                            if (ocStructSymbol.isPredeclaration()) {
                                OCDocUtil.extractModifiers(ocStructSymbol, sb10);
                                break Label_1849;
                            }
                        }
                        catch (IllegalArgumentException ex31) {
                            throw b(ex31);
                        }
                        sb10.append(a);
                        try {
                            sb10.append(ocStructSymbol.getKindLowercase()).append(" ");
                            if (ocStructSymbol.isEnumClass()) {
                                sb10.append("class ");
                            }
                        }
                        catch (IllegalArgumentException ex32) {
                            throw b(ex32);
                        }
                    }
                    try {
                        sb10.append(c3).append("<b>").append(ocStructSymbol.getName()).append("</b>");
                        a(ocStructSymbol, sb10);
                        if (ocStructSymbol.isFinal()) {
                            sb10.append(" ").append(OCTokenTypes.FINAL_CPP_KEYWORD.getName());
                        }
                    }
                    catch (IllegalArgumentException ex33) {
                        throw b(ex33);
                    }
                    sb10.append(a(ocStructSymbol, psiElement));
                    return sb10.toString().replaceAll("\\s+", " ");
                }
                final OCFindUsagesProvider ocFindUsagesProvider = new OCFindUsagesProvider();
                try {
                    if (ocFindUsagesProvider.canFindUsagesFor(psiElement)) {
                        return a(psiElement.getContainingFile()) + ocFindUsagesProvider.getType(psiElement) + "  <b>" + ocFindUsagesProvider.getDescriptiveName(psiElement) + "</b>";
                    }
                }
                catch (IllegalArgumentException ex34) {
                    throw b(ex34);
                }
                return null;
            }
        }
    }
    
    @NotNull
    private static String a(@NotNull final OCStructSymbol ocStructSymbol, @NotNull final PsiElement psiElement) {
        try {
            if (ocStructSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getBaseClasses"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getBaseClasses"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final Collection<Pair<OCType, OCVisibility>> baseCppClassesWithVisibility = ocStructSymbol.getBaseCppClassesWithVisibility(new OCResolveContext(psiElement.getContext()));
        if (!baseCppClassesWithVisibility.isEmpty()) {
            final String join = StringUtil.join((Collection)baseCppClassesWithVisibility, pair -> {
                final OCVisibility ocVisibility = (OCVisibility)pair.getSecond();
                String string = "";
                if (ocVisibility != null) {
                    string = ocVisibility + " ";
                }
                return string + OCDocUtil.getCanonicalName((OCType)pair.getFirst(), list, ocFile);
            }, ", ");
            String string;
            try {
                string = " : " + join;
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getBaseClasses"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            return string;
        }
        String s;
        try {
            s = "";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getBaseClasses"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return s;
    }
    
    private void a(@NotNull OCDeclarator ocDeclarator, @NotNull final StringBuilder sb) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "processInitializer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "processInitializer"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        ocDeclarator = this.a(ocDeclarator, OCDeclarator.class);
        OCExpression ocExpression = ocDeclarator.getInitializer();
        Label_0131: {
            try {
                if (ocExpression != null) {
                    sb.append(" =");
                    break Label_0131;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            ocExpression = ocDeclarator.getInitializerList();
            try {
                if (ocExpression != null) {
                    sb.append(" ");
                    a(ocExpression, sb);
                    return;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        final OCArgumentList argumentList = ocDeclarator.getArgumentList();
        try {
            if (argumentList != null) {
                a(argumentList, sb);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
    }
    
    private static void a(@NotNull final OCElement ocElement, @NotNull final StringBuilder sb) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "initializer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "processExpressionElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "processExpressionElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final String textWithMacros = ocElement.getTextWithMacros();
        final int index = textWithMacros.indexOf(10);
        if (index != -1) {
            sb.append(escapeHTML(textWithMacros.substring(0, index).replaceAll("\\s+", " "))).append("...");
        }
        else {
            sb.append(escapeHTML(textWithMacros));
        }
    }
    
    private void a(OCDeclarator ocDeclarator, final OCFunctionSymbol ocFunctionSymbol, final StringBuilder sb) {
        ocDeclarator = this.a(ocDeclarator, OCDeclarator.class);
        final Collection<OCTypeArgument> substitutedTypes = ocFunctionSymbol.getSubstitution().getSubstitutedTypes();
        Label_0074: {
            Label_0042: {
                try {
                    if (substitutedTypes == null) {
                        break Label_0042;
                    }
                    final Collection<OCTypeArgument> collection = substitutedTypes;
                    final boolean b = collection.isEmpty();
                    if (!b) {
                        break Label_0042;
                    }
                    break Label_0042;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final Collection<OCTypeArgument> collection = substitutedTypes;
                    final boolean b = collection.isEmpty();
                    if (!b) {
                        sb.append(OCDocUtil.getParametersSignature(ocFunctionSymbol, false));
                        break Label_0074;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            a(ocFunctionSymbol, sb);
            sb.append(OCDocUtil.getParametersSignature(ocFunctionSymbol, true));
        }
        OCDocUtil.extractFuncPostModifiers(ocFunctionSymbol, sb);
        final OCNoexceptSpecifier ocNoexceptSpecifier = (OCNoexceptSpecifier)PsiTreeUtil.findChildOfType((PsiElement)ocDeclarator, (Class)OCNoexceptSpecifier.class);
        try {
            if (ocNoexceptSpecifier != null) {
                a(ocNoexceptSpecifier, sb.append(" "));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
    }
    
    @NotNull
    private static String a(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getTemplateParameters"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String s = null;
        Label_0168: {
            try {
                if (!(ocSymbol instanceof OCTemplateSymbol) || !(ocSymbol instanceof OCSymbolWithQualifiedName)) {
                    break Label_0168;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final StringBuilder sb = new StringBuilder();
            final OCTemplateSymbol<?> ocTemplateSymbol = (OCTemplateSymbol<?>)ocSymbol;
            if (ocTemplateSymbol.isTemplateSymbol()) {
                final List<OCSymbolWithQualifiedName> parents = OCDocUtil.getParents((OCSymbolWithQualifiedName<?>)ocTemplateSymbol);
                final OCFile containingOCFile = ocSymbol.getContainingOCFile();
                final List<OCTypeParameterSymbol> templateParameters = ocTemplateSymbol.getTemplateParameters();
                String string;
                try {
                    OCDocUtil.wrapTemplateParams(templateParameters, parents, containingOCFile, sb);
                    string = sb.append("<br>").toString();
                    if (string == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getTemplateParameters"));
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                return string;
            }
            try {
                s = "";
                if (s == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getTemplateParameters"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return s;
    }
    
    @NotNull
    private <T extends PsiElement> T a(@NotNull final OCElement ocElement, final Class<T> clazz) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "cleanAndReformat"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiElement copy = ocElement.copy();
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        copy.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            public void visitComment(final PsiComment psiComment) {
                list.add(psiComment);
            }
        });
        final Iterator<Object> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().delete();
        }
        PsiElement psiElement2 = null;
        Label_0216: {
            if (SourceTreeToPsiMap.hasTreeElement(copy)) {
                final Language language = copy.getLanguage();
                final PsiElement treeElementToPsi = SourceTreeToPsiMap.treeElementToPsi(new CodeFormatterFacade(this.a(language), language).processElement(SourceTreeToPsiMap.psiElementToTree(copy)));
                PsiElement psiElement = null;
                Label_0181: {
                    try {
                        if (treeElementToPsi == null) {
                            break Label_0216;
                        }
                        psiElement = treeElementToPsi;
                        if (psiElement == null) {
                            break Label_0181;
                        }
                        return (T)psiElement;
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        psiElement = treeElementToPsi;
                        if (psiElement == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "cleanAndReformat"));
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                return (T)psiElement;
            }
            try {
                psiElement2 = copy;
                if (psiElement2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "cleanAndReformat"));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return (T)psiElement2;
    }
    
    private CodeStyleSettings a(final Language language) {
        final String id = language.getID();
        if (!this.mySettings.containsKey(id)) {
            final CodeStyleSettings codeStyleSettings = new CodeStyleSettings();
            codeStyleSettings.getCommonSettings(language).KEEP_LINE_BREAKS = true;
            codeStyleSettings.getCommonSettings(language).SPACE_WITHIN_BRACES = true;
            ((OCCodeStyleSettings)codeStyleSettings.getCustomSettings((Class)OCCodeStyleSettings.class)).DO_NOT_ADD_BREAKS = true;
            this.mySettings.put(id, codeStyleSettings);
        }
        return this.mySettings.get(id);
    }
    
    private static void a(@NotNull final OCSymbolWithQualifiedName ocSymbolWithQualifiedName, @NotNull final StringBuilder sb) {
        try {
            if (ocSymbolWithQualifiedName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "extractTemplateSpecialization"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sb", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "extractTemplateSpecialization"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (ocSymbolWithQualifiedName instanceof OCTemplateSymbol) {
            final List templateSpecialization = ((OCTemplateSymbol)ocSymbolWithQualifiedName).getTemplateSpecialization();
            try {
                if (templateSpecialization == null || templateSpecialization.isEmpty()) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            OCDocUtil.wrapTemplateArgs(templateSpecialization, OCDocUtil.getParents(ocSymbolWithQualifiedName), ocSymbolWithQualifiedName.getContainingOCFile(), sb);
        }
    }
    
    private static void b(@NotNull final OCSymbol ocSymbol, @NotNull final StringBuilder sb) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addNamespace"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addNamespace"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (!(ocSymbol instanceof OCSymbolWithQualifiedName) || ocSymbol.getKind() == OCSymbolKind.PARAMETER) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final String namespace = OCDocUtil.getNamespace((OCSymbolWithQualifiedName<?>)ocSymbol);
        try {
            if (namespace.length() > 0) {
                sb.append("namespace ").append(namespace).append("<br><br>");
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
    }
    
    private static void a(@NotNull final OCSymbol ocSymbol, @NotNull final StringBuilder sb) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addVisibility"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addVisibility"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (ocSymbol instanceof OCSymbolWithQualifiedName) {
            final OCVisibility visibility = ((OCSymbolWithQualifiedName)ocSymbol).getVisibility();
            Label_0121: {
                try {
                    if (visibility == null) {
                        return;
                    }
                    final OCVisibility ocVisibility = visibility;
                    final OCVisibility ocVisibility2 = OCVisibility.NULL;
                    if (ocVisibility != ocVisibility2) {
                        break Label_0121;
                    }
                    return;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    final OCVisibility ocVisibility = visibility;
                    final OCVisibility ocVisibility2 = OCVisibility.NULL;
                    if (ocVisibility != ocVisibility2) {
                        sb.append(visibility.toString()).append(":<br>");
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
        }
    }
    
    @NotNull
    private static String c(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getCanonicalNamePrefix"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String s2 = null;
        Label_0152: {
            try {
                if (!(ocSymbol instanceof OCSymbolWithQualifiedName) || ocSymbol.getKind() == OCSymbolKind.PARAMETER) {
                    break Label_0152;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final String canonicalPrefix = OCDocUtil.getCanonicalPrefix((OCSymbolWithQualifiedName<?>)ocSymbol);
            String s = null;
            Label_0113: {
                try {
                    if (canonicalPrefix.length() > 0) {
                        final String string;
                        s = (string = canonicalPrefix + "::");
                        break Label_0113;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                String string;
                s = (string = "");
                try {
                    if (string == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getCanonicalNamePrefix"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return s;
            try {
                s2 = "";
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getCanonicalNamePrefix"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
        }
        return s2;
    }
    
    @NotNull
    private static String b(@NotNull final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "symbol", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "effectiveTypeString"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCFile containingOCFile = ocSymbol.getContainingOCFile();
        final OCType effectiveType = ocSymbol.getEffectiveType();
        OCType ocType = effectiveType.accept((OCTypeVisitor<OCType>)new OCTypeParameterResolveVisitor((PsiFile)containingOCFile));
        Label_0097: {
            try {
                if (!(ocType instanceof OCUnknownType)) {
                    if (!(ocType instanceof OCMagicType)) {
                        break Label_0097;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            ocType = effectiveType;
        }
        if (ocSymbol instanceof OCSymbolWithQualifiedName) {
            final List<OCSymbolWithQualifiedName> parents = OCDocUtil.getParents((OCSymbolWithQualifiedName<?>)ocSymbol);
            String replaceAnonymous;
            try {
                replaceAnonymous = OCDocUtil.replaceAnonymous(OCDocUtil.getCanonicalName(ocType, parents, containingOCFile));
                if (replaceAnonymous == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "effectiveTypeString"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            return replaceAnonymous;
        }
        String replaceAnonymous2;
        try {
            replaceAnonymous2 = OCDocUtil.replaceAnonymous(escapeHTML(ocType.getName((PsiElement)containingOCFile)));
            if (replaceAnonymous2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "effectiveTypeString"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return replaceAnonymous2;
    }
    
    @Nullable
    private String c(@NotNull final OCProperty ocProperty) {
        try {
            if (ocProperty == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "propertyIVarDoc"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiElement a = a(ocProperty);
        try {
            if (a != null) {
                return this.generateDocInnerHtml(a, null, true);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    private static PsiElement a(@NotNull final OCProperty ocProperty) {
        try {
            if (ocProperty == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getPropertyIVar"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCPropertySymbol b = b(ocProperty);
        OCSymbol<PsiElement> associatedIvar = null;
        if (b != null) {
            associatedIvar = b.getAssociatedIvar();
        }
        try {
            if (associatedIvar == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return associatedIvar.locateDefinition();
    }
    
    @Nullable
    private static OCPropertySymbol b(@NotNull final OCProperty ocProperty) {
        try {
            if (ocProperty == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "p", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getPropertySymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final OCDeclaration declaration = ocProperty.getDeclaration();
        try {
            if (declaration == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final List<OCDeclarator> declarators = declaration.getDeclarators();
        try {
            if (declarators.size() == 0) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final OCSymbol symbol = declarators.get(0).getSymbol();
        try {
            if (symbol instanceof OCPropertySymbol) {
                return (OCPropertySymbol)symbol;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return null;
    }
    
    @NotNull
    private static String a(@NotNull final PsiFile psiFile) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "declaredInHint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String declaredInHint;
        try {
            declaredInHint = declaredInHint(psiFile.getName());
            if (declaredInHint == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "declaredInHint"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return declaredInHint;
    }
    
    @NotNull
    public static String declaredInHint(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "fileName", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "declaredInHint"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String string;
        try {
            string = "<b>Declared In:</b> " + s + "<br><br>";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "declaredInHint"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return string;
    }
    
    @Nullable
    private static PsiElement c(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "resolveIfRequired"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        if (psiElement instanceof OCReferenceElement) {
            final OCSymbol resolveToSymbol = ((OCReferenceElement)psiElement).resolveToSymbol();
            if (resolveToSymbol instanceof OCInstanceVariableSymbol) {
                final OCPropertySymbol associatedProperty = ((OCInstanceVariableSymbol)resolveToSymbol).getAssociatedProperty();
                try {
                    if (associatedProperty != null) {
                        return associatedProperty.locateDefinition();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            else if (resolveToSymbol instanceof OCMethodSymbol) {
                final OCPropertySymbol generatedFromProperty = ((OCMethodSymbol)resolveToSymbol).getGeneratedFromProperty();
                try {
                    if (generatedFromProperty != null) {
                        return generatedFromProperty.locateDefinition();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
        }
        return psiElement;
    }
    
    @Nullable
    private static String a(@NotNull final OCTypeParameterDeclaration ocTypeParameterDeclaration) {
        try {
            if (ocTypeParameterDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "typeParameterHintDoc"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return ocTypeParameterDeclaration.getText();
    }
    
    @NotNull
    private static String a(@NotNull final OCClassDeclaration ocClassDeclaration) {
        try {
            if (ocClassDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "classDeclaration", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "classHintDoc"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String string = null;
        Label_0069: {
            StringBuilder sb;
            try {
                sb = new StringBuilder();
                if (ocClassDeclaration instanceof OCProtocol) {
                    final String s = "protocol ";
                    break Label_0069;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final String s = "class ";
            try {
                string = sb.append(s).append(ocClassDeclaration.getName()).toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "classHintDoc"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return string;
    }
    
    @NotNull
    private static String a(@NotNull final OCMethod ocMethod) {
        try {
            if (ocMethod == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "method", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "methodHintDoc"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder();
        final OCClassDeclaration ocClassDeclaration = (OCClassDeclaration)PsiTreeUtil.getContextOfType((PsiElement)ocMethod, new Class[] { OCClassDeclaration.class });
        try {
            if (ocClassDeclaration != null) {
                sb.append(a(ocClassDeclaration)).append("<br>");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        StringBuilder sb2 = null;
        char c = '\0';
        Label_0115: {
            try {
                sb2 = sb;
                if (ocMethod.isInstanceMethod()) {
                    c = '-';
                    break Label_0115;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            c = '+';
        }
        sb2.append(c).append(" ");
        a(sb, ocMethod.getRawReturnType(), ocMethod.getContainingFile(), true);
        for (final OCMethodSelectorPart ocMethodSelectorPart : ocMethod.getParameters()) {
            sb.append("<b>");
            sb.append(ocMethodSelectorPart.getSelectorPart());
            sb.append("</b>");
            final OCType rawType = ocMethodSelectorPart.getRawType();
            try {
                if (rawType != OCUnknownType.INSTANCE) {
                    a(sb, rawType, ocMethod.getContainingFile(), true);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            final String parameterName = ocMethodSelectorPart.getParameterName();
            try {
                if (parameterName != null) {
                    sb.append(parameterName);
                }
            }
            catch (IllegalArgumentException ex5) {
                throw b(ex5);
            }
            sb.append(" ");
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "methodHintDoc"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return string;
    }
    
    private static void a(@NotNull final StringBuilder sb, @NotNull final OCType ocType, @NotNull final PsiFile psiFile, final boolean b) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "appendType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "appendType"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "appendType"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final String name = ocType.accept((OCTypeVisitor<OCType>)new OCTypeParameterResolveVisitor(psiFile)).getName();
        boolean b3 = false;
        Label_0182: {
            Label_0173: {
                try {
                    if (!b) {
                        break Label_0173;
                    }
                    final String s = name;
                    final String s2 = "(";
                    final boolean b2 = s.startsWith(s2);
                    if (!b2) {
                        break Label_0173;
                    }
                    break Label_0173;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    final String s = name;
                    final String s2 = "(";
                    final boolean b2 = s.startsWith(s2);
                    if (!b2) {
                        b3 = true;
                        break Label_0182;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            b3 = false;
        }
        final boolean b4 = b3;
        try {
            if (b4) {
                sb.append("(");
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            sb.append(escapeHTML(name));
            if (b4) {
                sb.append(")");
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
    }
    
    @Nullable
    public String generateDocInnerHtml(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "generateDocInnerHtml"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder();
        try {
            if (this.myExternalProvider != null) {
                this.myExternalProvider.addExternalDoc(psiElement, psiElement2, sb);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        boolean b2 = false;
        Label_0097: {
            try {
                if (sb.length() > 0) {
                    b2 = true;
                    break Label_0097;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            b2 = false;
        }
        final boolean b3 = b2;
        Label_0124: {
            Label_0118: {
                try {
                    if (!(psiElement instanceof PsiFile)) {
                        break Label_0124;
                    }
                    final boolean b4 = b3;
                    if (!b4) {
                        break Label_0118;
                    }
                    break Label_0124;
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
                try {
                    final boolean b4 = b3;
                    if (!b4) {
                        return null;
                    }
                }
                catch (IllegalArgumentException ex5) {
                    throw b(ex5);
                }
            }
            try {
                if (psiElement instanceof OCDefineDirective) {
                    this.a(psiElement2, sb, (OCDefineDirective)psiElement);
                }
            }
            catch (IllegalArgumentException ex6) {
                throw b(ex6);
            }
        }
        Label_0183: {
            Label_0168: {
                try {
                    if (b) {
                        break Label_0183;
                    }
                    final StringBuilder sb2 = sb;
                    final int n = sb2.length();
                    if (n == 0) {
                        break Label_0168;
                    }
                    break Label_0183;
                }
                catch (IllegalArgumentException ex7) {
                    throw b(ex7);
                }
                try {
                    final StringBuilder sb2 = sb;
                    final int n = sb2.length();
                    if (n == 0) {
                        this.a(psiElement, psiElement2, sb);
                    }
                }
                catch (IllegalArgumentException ex8) {
                    throw b(ex8);
                }
            }
            try {
                if (!b3) {
                    this.a(psiElement, sb);
                }
            }
            catch (IllegalArgumentException ex9) {
                throw b(ex9);
            }
        }
        Label_0237: {
            Label_0221: {
                try {
                    if (b3) {
                        break Label_0237;
                    }
                    final PsiElement psiElement3 = psiElement;
                    final boolean b5 = psiElement3 instanceof PsiNamedElement;
                    if (b5) {
                        break Label_0221;
                    }
                    break Label_0237;
                }
                catch (IllegalArgumentException ex10) {
                    throw b(ex10);
                }
                try {
                    final PsiElement psiElement3 = psiElement;
                    final boolean b5 = psiElement3 instanceof PsiNamedElement;
                    if (b5) {
                        a(sb, (PsiNamedElement)psiElement);
                    }
                }
                catch (IllegalArgumentException ex11) {
                    throw b(ex11);
                }
            }
            try {
                if (sb.length() == 0) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex12) {
                throw b(ex12);
            }
        }
        return sb.toString();
    }
    
    private void a(@NotNull final PsiElement psiElement, @Nullable final PsiElement psiElement2, @NotNull final StringBuilder sb) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addQuickDoc"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addQuickDoc"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final String quickDocContent = this.quickDocContent(psiElement, psiElement2, true);
        try {
            if (quickDocContent != null) {
                a(sb);
                sb.append("<code>").append(quickDocContent).append("</code>");
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
    }
    
    private static void a(@NotNull final StringBuilder p0, @NotNull final PsiNamedElement p1) {
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
        //    18: ldc             "answer"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "addManDoc"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "namedElement"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "addManDoc"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokeinterface com/intellij/psi/PsiNamedElement.getContainingFile:()Lcom/intellij/psi/PsiFile;
        //    94: astore_2       
        //    95: aload_2        
        //    96: ifnull          112
        //    99: aload_2        
        //   100: invokeinterface com/intellij/psi/PsiFile.getVirtualFile:()Lcom/intellij/openapi/vfs/VirtualFile;
        //   105: goto            113
        //   108: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   111: athrow         
        //   112: aconst_null    
        //   113: astore_3       
        //   114: aload_3        
        //   115: ifnull          137
        //   118: aload_3        
        //   119: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   122: ldc             "/usr/include/"
        //   124: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   127: ifne            142
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   136: athrow         
        //   137: return         
        //   138: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   141: athrow         
        //   142: aload_1        
        //   143: invokeinterface com/intellij/psi/PsiNamedElement.getName:()Ljava/lang/String;
        //   148: astore          4
        //   150: aload           4
        //   152: ifnonnull       160
        //   155: return         
        //   156: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   159: athrow         
        //   160: aload           4
        //   162: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/String;)Ljava/lang/String;
        //   165: astore          5
        //   167: aload           5
        //   169: ifnull          229
        //   172: aload           5
        //   174: invokevirtual   java/lang/String.length:()I
        //   177: ifle            229
        //   180: goto            187
        //   183: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   186: athrow         
        //   187: aload_0        
        //   188: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.a:(Ljava/lang/StringBuilder;)V
        //   191: aload_0        
        //   192: invokevirtual   java/lang/StringBuilder.length:()I
        //   195: ifle            219
        //   198: goto            205
        //   201: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   204: athrow         
        //   205: aload_0        
        //   206: ldc             "<br>"
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: pop            
        //   212: goto            219
        //   215: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   218: athrow         
        //   219: aload_0        
        //   220: aload           5
        //   222: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.c:(Ljava/lang/String;)Ljava/lang/String;
        //   225: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   228: pop            
        //   229: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  95     108    108    112    Ljava/lang/IllegalArgumentException;
        //  114    130    133    137    Ljava/lang/IllegalArgumentException;
        //  118    138    138    142    Ljava/lang/IllegalArgumentException;
        //  150    156    156    160    Ljava/lang/IllegalArgumentException;
        //  167    180    183    187    Ljava/lang/IllegalArgumentException;
        //  172    198    201    205    Ljava/lang/IllegalArgumentException;
        //  187    212    215    219    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0187:
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
    
    private void a(@Nullable final PsiElement psiElement, @NotNull final StringBuilder sb, @NotNull final OCDefineDirective ocDefineDirective) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addMacroDoc"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (ocDefineDirective == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "define", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addMacroDoc"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        a(sb);
        if (sb.length() == 0) {
            final PsiFile containingFile = ocDefineDirective.getContainingFile();
            try {
                if (containingFile != null) {
                    sb.append(a(containingFile));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        sb.append("<b>Definition:</b><br><br><tt><pre>");
        sb.append(escapeHTML(this.a(ocDefineDirective, OCDefineDirective.class).getTextWithMacros().replaceAll("\\s+\\\\", "")));
        sb.append("</pre></tt><br>");
        final OCMacroCall ocMacroCall = (OCMacroCall)PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCMacroCall.class });
        try {
            if (ocMacroCall != null) {
                sb.append("<b>Replacement:</b><br><br> <tt><pre>");
                sb.append(escapeHTML(ocMacroCall.getReplacementText()));
                sb.append("</pre></tt>");
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
    }
    
    @NotNull
    protected static String escapeHTML(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "escapeHTML"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String escapeXml;
        try {
            escapeXml = StringUtil.escapeXml(s);
            if (escapeXml == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "escapeHTML"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return escapeXml;
    }
    
    private static void a(@NotNull final StringBuilder sb) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addBreakIfRequired"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb.length() > 0) {
                sb.append("<br>");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    @Nullable
    private static String b(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "getManPage"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        final GeneralCommandLine generalCommandLine = new GeneralCommandLine();
        generalCommandLine.setExePath("man");
        generalCommandLine.addParameter("-S");
        generalCommandLine.addParameter("2:3");
        generalCommandLine.addParameter(s);
        generalCommandLine.setCharset(CharsetToolkit.getDefaultSystemCharset());
        try {
            final ProcessOutput runProcess = new CapturingProcessHandler(generalCommandLine).runProcess(1000);
            if (runProcess.isTimeout()) {
                return null;
            }
            return runProcess.getStdout().trim();
        }
        catch (ExecutionException ex2) {
            return null;
        }
    }
    
    @NotNull
    private static String c(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "man", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "man2html"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("<pre>");
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            int char2 = 0;
            Label_0110: {
                try {
                    if (i + 1 < s.length()) {
                        char2 = s.charAt(i + 1);
                        break Label_0110;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                char2 = 0;
            }
            final int n3 = char2;
            boolean b = false;
            Label_0142: {
                Label_0133: {
                    try {
                        if (char1 != '_') {
                            break Label_0133;
                        }
                        final int n4 = n3;
                        final int n5 = 8;
                        if (n4 == n5) {
                            break Label_0133;
                        }
                        break Label_0133;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final int n4 = n3;
                        final int n5 = 8;
                        if (n4 == n5) {
                            b = true;
                            break Label_0142;
                        }
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
                b = false;
            }
            final boolean b2 = b;
            boolean b3 = false;
            Label_0174: {
                Label_0165: {
                    try {
                        if (char1 == '_') {
                            break Label_0165;
                        }
                        final int n6 = n3;
                        final int n7 = 8;
                        if (n6 == n7) {
                            break Label_0165;
                        }
                        break Label_0165;
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    try {
                        final int n6 = n3;
                        final int n7 = 8;
                        if (n6 == n7) {
                            b3 = true;
                            break Label_0174;
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw b(ex6);
                    }
                }
                b3 = false;
            }
            final boolean b4 = b3;
            Label_0306: {
                Label_0281: {
                    Label_0256: {
                        Label_0243: {
                            Label_0226: {
                                Label_0201: {
                                    try {
                                        if (b4 || n == 0) {
                                            break Label_0201;
                                        }
                                    }
                                    catch (IllegalArgumentException ex7) {
                                        throw b(ex7);
                                    }
                                    n = 0;
                                    sb.append("</b>");
                                    try {
                                        if (b2 || n2 == 0) {
                                            break Label_0226;
                                        }
                                    }
                                    catch (IllegalArgumentException ex8) {
                                        throw b(ex8);
                                    }
                                }
                                n2 = 0;
                                sb.append("</u>");
                                try {
                                    if (b4) {
                                        break Label_0256;
                                    }
                                    final boolean b5 = b2;
                                    if (!b5) {
                                        break Label_0243;
                                    }
                                    break Label_0256;
                                }
                                catch (IllegalArgumentException ex9) {
                                    throw b(ex9);
                                }
                            }
                            try {
                                final boolean b5 = b2;
                                if (!b5) {
                                    a(sb, char1);
                                    continue;
                                }
                            }
                            catch (IllegalArgumentException ex10) {
                                throw b(ex10);
                            }
                        }
                        try {
                            if (!b4 || n != 0) {
                                break Label_0281;
                            }
                        }
                        catch (IllegalArgumentException ex11) {
                            throw b(ex11);
                        }
                    }
                    sb.append("<b>");
                    n = 1;
                    try {
                        if (!b2 || n2 != 0) {
                            break Label_0306;
                        }
                    }
                    catch (IllegalArgumentException ex12) {
                        throw b(ex12);
                    }
                }
                sb.append("<u>");
                n2 = 1;
            }
            a(sb, s.charAt(i + 2));
            i += 2;
        }
        String string;
        try {
            sb.append("</pre>");
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "man2html"));
            }
        }
        catch (IllegalArgumentException ex13) {
            throw b(ex13);
        }
        return string;
    }
    
    private static void a(@NotNull final StringBuilder sb, final char c) {
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "a", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "appendSymbol"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0150: {
            Label_0140: {
                Label_0130: {
                    Label_0120: {
                        try {
                            switch (c) {
                                case ' ': {
                                    sb.append("&nbsp;");
                                    return;
                                }
                                case '\t': {
                                    break;
                                }
                                case '<': {
                                    break Label_0120;
                                }
                                case '>': {
                                    break Label_0130;
                                }
                                case '\"': {
                                    break Label_0140;
                                }
                                default: {
                                    break Label_0150;
                                }
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                        return;
                    }
                    sb.append("&lt;");
                    return;
                }
                sb.append("&gt;");
                return;
            }
            sb.append("&quot;");
            return;
        }
        sb.append(c);
    }
    
    @NotNull
    public static String wrapDocInHtml(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "doc", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "wrapDocInHtml"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        String string;
        try {
            string = "<html><head><style type=\"text/css\">p { margin-bottom: 5px; }</style></head><body>" + s + "</body></html>";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "wrapDocInHtml"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return string;
    }
    
    private void a(@NotNull final PsiElement psiElement, @NotNull final StringBuilder sb) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addCommentDoc"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addCommentDoc"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        if (!this.b(psiElement, sb)) {
            for (final OCSymbolWithParent<PsiElement, ?> ocSymbolWithParent : OCDocUtil.getSuperSymbols(psiElement)) {
                final int length = sb.length();
                PsiElement psiElement2 = ocSymbolWithParent.locateDefinition();
                Label_0180: {
                    try {
                        if (psiElement2 == null) {
                            continue;
                        }
                        if (!(psiElement2 instanceof OCDeclarator)) {
                            break Label_0180;
                        }
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                    psiElement2 = psiElement2.getParent();
                    try {
                        if (this.b(psiElement2, sb)) {
                            sb.insert(length, "<br><br><b>Description copied from: </b>" + OCDocUtil.getLink(ocSymbolWithParent));
                            break;
                        }
                        continue;
                    }
                    catch (IllegalArgumentException ex4) {
                        throw b(ex4);
                    }
                }
            }
        }
    }
    
    private boolean b(@NotNull final PsiElement psiElement, @NotNull final StringBuilder sb) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addCommentDocForElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addCommentDocForElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final int length = sb.length();
        final DoxygenRender doxygenRender = new DoxygenRender(psiElement);
        Label_0130: {
            try {
                if (doxygenRender.hasDoxygenComments()) {
                    doxygenRender.render(sb);
                    break Label_0130;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            this.addDocForElement(psiElement, sb);
            try {
                if (sb.length() > length) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    protected void addDocForElement(@NotNull final PsiElement psiElement, @NotNull final StringBuilder sb) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addDocForElement"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (sb == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "answer", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "addDocForElement"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        final ArrayList<String> list = new ArrayList<String>();
        final ArrayList<PsiComment> list2 = new ArrayList<PsiComment>();
        for (final PsiComment psiComment : this.findCommentsFor(psiElement)) {
            final IElementType tokenType = psiComment.getTokenType();
            try {
                list2.add(psiComment);
                if (tokenType == OCTokenTypes.EOL_COMMENT) {
                    continue;
                }
                list.add(this.a(list2));
                list2.clear();
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        try {
            if (!list2.isEmpty()) {
                list.add(this.a(list2));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final String join = StringUtil.join((Collection)list, "\n");
        try {
            if (!StringUtil.isEmptyOrSpaces(join)) {
                sb.append("<pre>").append(join).append("</pre>");
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
    }
    
    @NotNull
    private String a(@NotNull final List<PsiComment> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comments", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "processComments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        int n = 1073741823;
        final ArrayList<Object> list2 = new ArrayList<Object>();
        for (final PsiComment psiComment : list) {
            final List split = StringUtil.split(escapeHTML(this.stripCommentBegin(psiComment.getText())), "\n", true, false);
            Label_0135: {
                try {
                    if (CidrDocumentationProvider.$assertionsDisabled) {
                        break Label_0135;
                    }
                    final List list3 = split;
                    final int n2 = list3.size();
                    if (n2 <= 0) {
                        break Label_0135;
                    }
                    break Label_0135;
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    final List list3 = split;
                    final int n2 = list3.size();
                    if (n2 <= 0) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
            }
            final String s2 = split.get(0);
            try {
                if (!s2.isEmpty()) {
                    split.set(0, "  " + s2);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            for (final String s3 : split) {
                String s4 = null;
                boolean b = false;
                Label_0259: {
                    try {
                        s4 = s3;
                        if (psiComment.getTokenType() != OCTokenTypes.EOL_COMMENT) {
                            b = true;
                            break Label_0259;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw b(ex5);
                    }
                    b = false;
                }
                final int a = a(s4, b);
                if (a < n) {
                    n = a;
                }
            }
            list2.addAll(split);
        }
        final List<Object> list4 = list2.stream().map(s -> s.substring(Math.min(s.length(), n))).collect((Collector<? super Object, ?, List<Object>>)Collectors.toList());
        String join;
        try {
            join = StringUtil.join((Collection)list4, "\n");
            if (join == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "processComments"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        return join;
    }
    
    private static int a(@NotNull final String s, boolean b) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "line", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "indent"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (s.isEmpty()) {
                return 1073741823;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        int i;
        for (i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            try {
                if (Character.isWhitespace(char1)) {
                    continue;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                if (!b || char1 != '*') {
                    break;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            b = false;
        }
        try {
            if (i < s.length()) {
                return i;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return 1073741823;
    }
    
    @NotNull
    protected String stripCommentBegin(@NotNull String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "stripCommentBegin"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0075: {
            try {
                if (!s.startsWith("/*")) {
                    if (!s.startsWith("//")) {
                        break Label_0075;
                    }
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            s = s.substring(2);
        }
        s = StringUtil.trimEnd(s, "*/");
        s = StringUtil.trimTrailing(s, ' ');
        String s2;
        try {
            s2 = s;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "stripCommentBegin"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return s2;
    }
    
    @NotNull
    protected List<PsiComment> findCommentsFor(@Nullable final PsiElement psiElement) {
        List<PsiComment> list = this.b(psiElement);
        List<PsiComment> list2 = null;
        Label_0074: {
            try {
                if (list.size() != 0 || !(psiElement instanceof OCSymbolDeclarator)) {
                    break Label_0074;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            OCSymbol<PsiElement> ocSymbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            if (ocSymbol != null) {
                ocSymbol = ocSymbol.getAssociatedSymbol();
            }
            if (ocSymbol != null) {
                final PsiElement locateDefinition = ocSymbol.locateDefinition();
                if (locateDefinition != null) {
                    list = this.b(locateDefinition);
                }
            }
            try {
                list2 = list;
                if (list2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "findCommentsFor"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return list2;
    }
    
    protected boolean acceptDocComment(@NotNull final PsiComment psiComment) {
        try {
            if (psiComment == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "comment", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "acceptDocComment"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        Label_0064: {
            try {
                if (!DoxygenFacade.isDoxygenSupported()) {
                    break Label_0064;
                }
                final PsiComment psiComment2 = psiComment;
                final boolean b = DoxygenFacade.isDoxygenComment(psiComment2);
                if (!b) {
                    break Label_0064;
                }
                return false;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final PsiComment psiComment2 = psiComment;
                final boolean b = DoxygenFacade.isDoxygenComment(psiComment2);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @NotNull
    private List<PsiComment> b(@Nullable PsiElement psiElement) {
        if (psiElement instanceof OCDeclarator) {
            psiElement = psiElement.getParent();
        }
        if (psiElement instanceof OCStructLike) {
            psiElement = PsiTreeUtil.findFirstParent(psiElement, psiElement -> psiElement instanceof OCDeclaration);
        }
        Label_0084: {
            List<PsiComment> list = null;
            Label_0049: {
                try {
                    if (psiElement != null) {
                        break Label_0084;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0049;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "findOCCommentFor"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            return list;
        }
        if (psiElement.getParent() instanceof OCProperty) {
            psiElement = psiElement.getParent();
        }
        if (psiElement.getParent() instanceof OCDeclarationStatement) {
            psiElement = psiElement.getParent();
        }
        Label_0180: {
            List<PsiComment> list2 = null;
            Label_0145: {
                try {
                    if (psiElement.getContainingFile() != null) {
                        break Label_0180;
                    }
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        break Label_0145;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                try {
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "findOCCommentFor"));
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return list2;
        }
        final LinkedList<PsiComment> list3 = new LinkedList<PsiComment>();
        LinkedList<PsiComment> list4;
        try {
            this.findPrevComments(psiElement, list3);
            this.findInnerComments(psiElement, list3);
            this.findPostComments(psiElement, list3);
            list4 = list3;
            if (list4 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "findOCCommentFor"));
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        return list4;
    }
    
    protected void findPrevComments(@NotNull final PsiElement p0, @NotNull final List<PsiComment> p1) {
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
        //    18: ldc             "elt"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findPrevComments"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
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
        //    62: ldc             "comments"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "findPrevComments"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_1        
        //    89: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //    94: astore_3       
        //    95: aload_3        
        //    96: ifnull          253
        //    99: aload_3        
        //   100: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   103: ifne            243
        //   106: goto            113
        //   109: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   112: athrow         
        //   113: aload_3        
        //   114: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   117: ifeq            154
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   126: athrow         
        //   127: aload_3        
        //   128: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   133: invokestatic    com/intellij/openapi/util/text/StringUtil.countNewLines:(Ljava/lang/CharSequence;)I
        //   136: iconst_1       
        //   137: if_icmpgt       154
        //   140: goto            147
        //   143: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   146: athrow         
        //   147: goto            243
        //   150: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   153: athrow         
        //   154: aload_3        
        //   155: instanceof      Lcom/intellij/psi/PsiComment;
        //   158: ifeq            253
        //   161: aload_3        
        //   162: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   167: astore          4
        //   169: aload           4
        //   171: ifnull          209
        //   174: aload           4
        //   176: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   179: ifeq            240
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   188: athrow         
        //   189: aload           4
        //   191: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //   196: invokestatic    com/intellij/openapi/util/text/StringUtil.countNewLines:(Ljava/lang/CharSequence;)I
        //   199: ifle            240
        //   202: goto            209
        //   205: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   208: athrow         
        //   209: aload_3        
        //   210: checkcast       Lcom/intellij/psi/PsiComment;
        //   213: astore          5
        //   215: aload_0        
        //   216: aload           5
        //   218: invokevirtual   com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.acceptDocComment:(Lcom/intellij/psi/PsiComment;)Z
        //   221: ifeq            240
        //   224: aload_2        
        //   225: iconst_0       
        //   226: aload           5
        //   228: invokeinterface java/util/List.add:(ILjava/lang/Object;)V
        //   233: goto            243
        //   236: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   239: athrow         
        //   240: goto            253
        //   243: aload_3        
        //   244: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   249: astore_3       
        //   250: goto            95
        //   253: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Ljava/util/List<Lcom/intellij/psi/PsiComment;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     84     84     88     Ljava/lang/IllegalArgumentException;
        //  95     106    109    113    Ljava/lang/IllegalArgumentException;
        //  99     120    123    127    Ljava/lang/IllegalArgumentException;
        //  113    140    143    147    Ljava/lang/IllegalArgumentException;
        //  127    150    150    154    Ljava/lang/IllegalArgumentException;
        //  169    182    185    189    Ljava/lang/IllegalArgumentException;
        //  174    202    205    209    Ljava/lang/IllegalArgumentException;
        //  215    236    236    240    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0113:
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
    
    protected void findInnerComments(@NotNull final PsiElement psiElement, final List<PsiComment> list) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "elt", "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider", "findInnerComments"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        for (final PsiComment psiComment : PsiTreeUtil.getChildrenOfTypeAsList(psiElement, (Class)PsiComment.class)) {
            try {
                if (this.acceptDocComment(psiComment)) {
                    list.add(psiComment);
                    continue;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            break;
        }
    }
    
    protected void findPostComments(@NotNull final PsiElement p0, final List<PsiComment> p1) {
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
        //    18: ldc             "elt"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "findPostComments"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    50: astore_3       
        //    51: aload_3        
        //    52: ifnull          134
        //    55: aload_3        
        //    56: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    59: ifeq            97
        //    62: goto            69
        //    65: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    68: athrow         
        //    69: aload_3        
        //    70: invokeinterface com/intellij/psi/PsiElement.getText:()Ljava/lang/String;
        //    75: ldc             "\n"
        //    77: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    80: ifne            97
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    89: athrow         
        //    90: goto            124
        //    93: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    96: athrow         
        //    97: aload_3        
        //    98: instanceof      Lcom/intellij/psi/impl/source/tree/LeafElement;
        //   101: ifeq            134
        //   104: aload_3        
        //   105: checkcast       Lcom/intellij/psi/impl/source/tree/LeafElement;
        //   108: invokevirtual   com/intellij/psi/impl/source/tree/LeafElement.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   111: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.SEMICOLON:Lcom/jetbrains/cidr/lang/parser/OCPunctuatorElementType;
        //   114: if_acmpne       134
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   123: athrow         
        //   124: aload_3        
        //   125: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   130: astore_3       
        //   131: goto            51
        //   134: aload_3        
        //   135: instanceof      Lcom/intellij/psi/PsiComment;
        //   138: ifeq            172
        //   141: aload_3        
        //   142: checkcast       Lcom/intellij/psi/PsiComment;
        //   145: astore          4
        //   147: aload_0        
        //   148: aload           4
        //   150: invokevirtual   com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.acceptDocComment:(Lcom/intellij/psi/PsiComment;)Z
        //   153: ifeq            172
        //   156: aload_2        
        //   157: aload           4
        //   159: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   164: pop            
        //   165: goto            172
        //   168: invokestatic    com/jetbrains/cidr/lang/documentation/CidrDocumentationProvider.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   171: athrow         
        //   172: return         
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Ljava/util/List<Lcom/intellij/psi/PsiComment;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  51     62     65     69     Ljava/lang/IllegalArgumentException;
        //  55     83     86     90     Ljava/lang/IllegalArgumentException;
        //  69     93     93     97     Ljava/lang/IllegalArgumentException;
        //  97     117    120    124    Ljava/lang/IllegalArgumentException;
        //  147    165    168    172    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
                if (!CidrDocumentationProvider.class.desiredAssertionStatus()) {
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
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    public interface ExternalProvider
    {
        void addExternalDoc(@NotNull final PsiElement p0, @Nullable final PsiElement p1, @NotNull final StringBuilder p2);
    }
}
