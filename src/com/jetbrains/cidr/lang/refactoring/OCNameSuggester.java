// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import java.util.Set;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.types.OCIntType;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.openapi.project.Project;
import com.intellij.util.ArrayUtil;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import org.jetbrains.annotations.NonNls;
import com.jetbrains.cidr.lang.util.OCNamesValidator;
import com.intellij.psi.codeStyle.NameUtil;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import java.util.List;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.ArrayList;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCImplementationSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.objc.OCInterfaceSymbol;
import gnu.trove.THashSet;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCInstanceVariablesList;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCProperty;
import com.intellij.openapi.util.text.StringUtil;
import java.util.LinkedHashSet;
import com.jetbrains.cidr.lang.psi.OCForeachStatement;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import java.util.HashSet;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.openapi.util.Ref;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import java.util.Collection;
import com.jetbrains.cidr.lang.psi.OCDeclarator;

public class OCNameSuggester
{
    public static final String BOOLEAN_SHORT_NAME = "b";
    public static final String INT_SHORT_NAME = "i";
    public static final String DOUBLE_SHORT_NAME = "d";
    public static final String STRING_SHORT_NAME = "string";
    public static final String AUTO_SHORT_NAME = "item";
    public static final String POINTER_SHORT_NAME = "ptr";
    public static final String OBJECT_SHORT_NAME = "o";
    public static final String FUNCTION_SHORT_NAME = "fun";
    public static final String UDL_SHORT_NAME = "_op";
    public static final String VOID_SHORT_NAME = "v";
    public static final String BOOLEAN_LONG_NAME = "boolean";
    public static final String INT_LONG_NAME = "int";
    public static final String DOUBLE_LONG_NAME = "double";
    public static final String OBJECT_LONG_NAME = "object";
    public static final String FUNCTION_LONG_NAME = "function";
    public static final String VOID_LONG_NAME = "void";
    private static String[] GARBAGE_SUFFIXES;
    private static String[] SHORT_NAMES;
    private static final String EMPTY_PREFIX = "";
    
    public static Collection<String> suggestForDeclaration(final OCDeclarator ocDeclarator, final boolean b, @NotNull final Collection<String> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "predefinedNames", "com/jetbrains/cidr/lang/refactoring/OCNameSuggester", "suggestForDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final HashSet hashSet = ContainerUtil.newHashSet((Iterable)collection);
        final HashSet hashSet2 = ContainerUtil.newHashSet();
        final Ref ref = new Ref();
        final Collection<String> a = a(ocDeclarator, (Ref<OCSymbolKind>)ref, hashSet, hashSet2);
        final HashSet hashSet3 = ContainerUtil.newHashSet((Iterable)a);
        if (b) {
            hashSet2.removeAll(hashSet);
            hashSet3.retainAll(hashSet);
            final HashSet hashSet4 = ContainerUtil.newHashSet((Iterable)a);
            hashSet4.removeAll(hashSet);
            hashSet3.addAll(a((OCSymbolKind)ref.get(), hashSet4, (PsiElement)ocDeclarator, hashSet2));
        }
        return (Collection<String>)hashSet3;
    }
    
    private static Collection<String> a(@NotNull final OCDeclarator ocDeclarator, final Ref<OCSymbolKind> ref, final Collection<String> collection, final Collection<String> collection2) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "subj", "com/jetbrains/cidr/lang/refactoring/OCNameSuggester", "doSuggestForDeclaration"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Label_0080: {
            try {
                if (!(ocDeclarator.getParent() instanceof OCDeclaration)) {
                    break Label_0080;
                }
                final OCDeclarator ocDeclarator2 = ocDeclarator;
                final PsiElement psiElement = ocDeclarator2.getParent();
                final OCDeclaration ocDeclaration = (OCDeclaration)psiElement;
                final boolean b = ocDeclaration.isTypedef();
                if (b) {
                    break Label_0080;
                }
                break Label_0080;
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            try {
                final OCDeclarator ocDeclarator2 = ocDeclarator;
                final PsiElement psiElement = ocDeclarator2.getParent();
                final OCDeclaration ocDeclaration = (OCDeclaration)psiElement;
                final boolean b = ocDeclaration.isTypedef();
                if (b) {
                    return (Collection<String>)Collections.emptyList();
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        final OCExpression initializer = ocDeclarator.getInitializer();
        try {
            if (initializer != null) {
                return a(initializer);
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        PsiElement psiElement2 = ocDeclarator.getContext();
        if (psiElement2 != null) {
            psiElement2 = psiElement2.getContext();
            if (psiElement2 instanceof OCDeclarationStatement) {
                psiElement2 = psiElement2.getContext();
            }
        }
        if (psiElement2 instanceof OCForeachStatement) {
            final OCExpression collectionExpression = ((OCForeachStatement)psiElement2).getCollectionExpression();
            final LinkedHashSet<String> set = new LinkedHashSet<String>();
            for (final String s : b(collectionExpression)) {
                final String unpluralize = StringUtil.unpluralize(s);
                LinkedHashSet<String> set2 = null;
                String s2 = null;
                Label_0238: {
                    try {
                        set2 = set;
                        if (unpluralize != null) {
                            s2 = unpluralize;
                            break Label_0238;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    s2 = s;
                }
                set2.add(s2);
            }
            set.addAll((Collection<?>)a(ocDeclarator.getType(), "", (PsiElement)collectionExpression));
            return set;
        }
        if (psiElement2 instanceof OCProperty) {
            final PsiElement parent = psiElement2.getParent();
            if (parent instanceof OCSymbolDeclarator) {
                final OCClassSymbol symbol = ((OCSymbolDeclarator<OCClassSymbol>)parent).getSymbol();
                try {
                    if (symbol instanceof OCClassSymbol) {
                        ref.set((Object)OCSymbolKind.PROPERTY);
                        return a(ocDeclarator, symbol, collection, collection2);
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
        }
        else if (psiElement2 instanceof OCInstanceVariablesList) {
            final PsiElement parent2 = psiElement2.getParent();
            if (parent2 instanceof OCSymbolDeclarator) {
                final OCClassSymbol symbol2 = ((OCSymbolDeclarator<OCClassSymbol>)parent2).getSymbol();
                try {
                    if (symbol2 instanceof OCClassSymbol) {
                        ref.set((Object)OCSymbolKind.INSTANCE_VARIABLE);
                        return a(ocDeclarator, symbol2, collection2);
                    }
                }
                catch (IllegalArgumentException ex7) {
                    throw a(ex7);
                }
            }
        }
        final OCFunctionSymbol symbol3 = ocDeclarator.getSymbol();
        Label_0443: {
            try {
                if (!(symbol3 instanceof OCFunctionSymbol)) {
                    return a(ocDeclarator.getType(), "", psiElement2);
                }
                final OCFunctionSymbol ocFunctionSymbol = symbol3;
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final boolean b2 = ocFunctionSymbol2.isUDL();
                if (b2) {
                    break Label_0443;
                }
                return a(ocDeclarator.getType(), "", psiElement2);
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            try {
                final OCFunctionSymbol ocFunctionSymbol = symbol3;
                final OCFunctionSymbol ocFunctionSymbol2 = ocFunctionSymbol;
                final boolean b2 = ocFunctionSymbol2.isUDL();
                if (b2) {
                    return a("_op", "_op", "");
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
        return a(ocDeclarator.getType(), "", psiElement2);
    }
    
    private static Collection<String> a(final OCDeclarator ocDeclarator, final OCClassSymbol ocClassSymbol, final Collection<String> collection) {
        final THashSet set = new THashSet();
        final OCInterfaceSymbol mainInterface = ocClassSymbol.getMainInterface();
        String categoryName = null;
        Label_0038: {
            try {
                if (ocClassSymbol instanceof OCInterfaceSymbol) {
                    categoryName = ocClassSymbol.getCategoryName();
                    break Label_0038;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            categoryName = "";
        }
        final String s = categoryName;
        OCClassSymbol ocClassSymbol2 = null;
        Label_0055: {
            try {
                if (mainInterface == null) {
                    ocClassSymbol2 = ocClassSymbol;
                    break Label_0055;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ocClassSymbol2 = mainInterface;
        }
        final OCClassSymbol ocClassSymbol3 = ocClassSymbol2;
        ocClassSymbol3.processCategories((Processor<? super OCClassSymbol>)(ocClassSymbol2 -> {
            Label_0028: {
                try {
                    if (StringUtil.compare(ocClassSymbol2.getCategoryName(), s, false) == 0) {
                        break Label_0028;
                    }
                    final OCClassSymbol ocClassSymbol3 = ocClassSymbol2;
                    final OCClassSymbol ocClassSymbol4 = ocClassSymbol3;
                    if (ocClassSymbol3 == ocClassSymbol4) {
                        break Label_0028;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClassSymbol ocClassSymbol3 = ocClassSymbol2;
                    final OCClassSymbol ocClassSymbol4 = ocClassSymbol3;
                    if (ocClassSymbol3 == ocClassSymbol4) {
                        ocClassSymbol2.processMembers(OCPropertySymbol.class, (com.intellij.util.Processor<? super OCPropertySymbol>)processor);
                        ocClassSymbol2.processMembers(OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCInstanceVariableSymbol>)processor2);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return true;
        }), true, null);
        final Iterator<String> iterator = a(ocDeclarator.getType(), "", (PsiElement)ocDeclarator).iterator();
        while (iterator.hasNext()) {
            ((Set<String>)set).add(getNonCollidingName(iterator.next(), OCSymbolKind.PROPERTY, true, ocDeclarator.getProject()));
        }
        return (Collection<String>)set;
    }
    
    private static Collection<String> a(@NotNull final OCDeclarator ocDeclarator, @NotNull final OCClassSymbol ocClassSymbol, final Collection<String> collection, final Collection<String> collection2) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/refactoring/OCNameSuggester", "suggestForProperty"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (ocClassSymbol == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "interfaceSymbol", "com/jetbrains/cidr/lang/refactoring/OCNameSuggester", "suggestForProperty"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final THashSet set = new THashSet();
        final Processor processor = ocInstanceVariableSymbol -> {
            try {
                if (ocDeclarator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/refactoring/OCNameSuggester", "lambda$suggestForProperty$2"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0073: {
                try {
                    if (OCResolveUtil.isDisabledSymbol(ocInstanceVariableSymbol, ocDeclarator.getContainingFile())) {
                        return true;
                    }
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                    final OCPropertySymbol ocPropertySymbol = ocInstanceVariableSymbol2.getAssociatedProperty();
                    if (ocPropertySymbol == null) {
                        break Label_0073;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final OCInstanceVariableSymbol ocInstanceVariableSymbol2 = ocInstanceVariableSymbol;
                    final OCPropertySymbol ocPropertySymbol = ocInstanceVariableSymbol2.getAssociatedProperty();
                    if (ocPropertySymbol != null) {
                        return true;
                    }
                    if (!ocDeclarator.getType().isCompatible(ocInstanceVariableSymbol.getType(), (PsiElement)ocDeclarator)) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final String nonCollidingName = getNonCollidingName(ocInstanceVariableSymbol, true);
            ((Set<String>)set).add(nonCollidingName);
            collection.add(nonCollidingName);
            return true;
        };
        ocClassSymbol.processCategories((Processor<? super OCClassSymbol>)(ocClassSymbol -> {
            final String categoryName = ocClassSymbol.getCategoryName();
            Label_0025: {
                try {
                    if (categoryName == null) {
                        break Label_0025;
                    }
                    final String s = categoryName;
                    final boolean b = s.isEmpty();
                    if (b) {
                        break Label_0025;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final String s = categoryName;
                    final boolean b = s.isEmpty();
                    if (b) {
                        ocClassSymbol.processMembers(OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCInstanceVariableSymbol>)processor);
                        ocClassSymbol.processMembers(OCPropertySymbol.class, (com.intellij.util.Processor<? super OCPropertySymbol>)processor2);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return true;
        }), true, null);
        final String categoryName = ocClassSymbol.getCategoryName();
        Label_0191: {
            try {
                if (categoryName != null) {
                    if (!categoryName.isEmpty()) {
                        break Label_0191;
                    }
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            final OCImplementationSymbol mainImplementation = ocClassSymbol.getMainImplementation();
            try {
                if (mainImplementation != null) {
                    mainImplementation.processMembers(OCInstanceVariableSymbol.class, (com.intellij.util.Processor<? super OCInstanceVariableSymbol>)processor);
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        ((Set)set).addAll(a(ocDeclarator.getType(), "", (PsiElement)ocDeclarator));
        return (Collection<String>)set;
    }
    
    private static Processor<OCSymbol> a(final Collection<String> collection, final OCElement ocElement) {
        return (Processor<OCSymbol>)(ocSymbol -> {
            final PsiElement locateDefinition = ocSymbol.locateDefinition();
            Label_0033: {
                try {
                    if (locateDefinition == null) {
                        return true;
                    }
                    final PsiElement psiElement = locateDefinition;
                    final int n = psiElement.getTextOffset();
                    final OCElement ocElement2 = ocElement;
                    final int n2 = ocElement2.getTextOffset();
                    if (n != n2) {
                        break Label_0033;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final PsiElement psiElement = locateDefinition;
                    final int n = psiElement.getTextOffset();
                    final OCElement ocElement2 = ocElement;
                    final int n2 = ocElement2.getTextOffset();
                    if (n != n2) {
                        collection.add(ocSymbol.getName());
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return true;
        });
    }
    
    public static Collection<String> suggestForExpression(@Nullable final OCSymbolKind ocSymbolKind, final OCExpression ocExpression) {
        final ArrayList<String> list = new ArrayList<String>();
        ocExpression.accept((PsiElementVisitor)new OCRecursiveVisitor() {
            @Override
            public void visitReferenceElement(final OCReferenceElement ocReferenceElement) {
                if (ocReferenceElement.resolveToSymbol() != null) {
                    list.add(ocReferenceElement.getName());
                }
            }
        });
        return a(ocSymbolKind, a(ocExpression), (PsiElement)ocExpression, list);
    }
    
    private static Collection<String> a(final OCExpression ocExpression) {
        final LinkedHashSet<Object> set = (LinkedHashSet<Object>)new LinkedHashSet<String>();
        final OCExpression topmostParenthesized = OCParenthesesUtils.topmostParenthesized(ocExpression);
        set.addAll(a(topmostParenthesized, topmostParenthesized.getParent()));
        set.addAll(a(ocExpression.getType().getGuessedType(), "", (PsiElement)ocExpression));
        set.addAll(b(OCParenthesesUtils.diveIntoParentheses(ocExpression)));
        return (Collection<String>)set;
    }
    
    private static List<String> a(final OCExpression ocExpression, final PsiElement psiElement) {
        if (psiElement instanceof OCMessageArgument) {
            final OCSendMessageExpression ocSendMessageExpression = (OCSendMessageExpression)psiElement.getParent();
            final int index = ocSendMessageExpression.getArguments().indexOf(psiElement);
            if (index >= 0) {
                final OCMethodSymbol knownResponder = ocSendMessageExpression.getProbableResponders().getKnownResponder();
                if (knownResponder != null) {
                    final List<OCMethodSymbol.SelectorPartSymbol> selectors = knownResponder.getSelectors();
                    if (index < selectors.size()) {
                        final OCDeclaratorSymbol parameter = selectors.get(index).getParameter();
                        try {
                            if (parameter != null) {
                                return b(parameter.getName(), "");
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw a(ex);
                        }
                    }
                }
            }
        }
        else if (psiElement instanceof OCArgumentList) {
            final PsiElement parent = psiElement.getParent();
            if (parent instanceof OCCallExpression) {
                final int index2 = ((OCCallExpression)parent).getArguments().indexOf(ocExpression);
                if (index2 >= 0) {
                    final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(((OCCallExpression)parent).getFunctionReferenceExpression());
                    if (symbol instanceof OCFunctionSymbol) {
                        final List<OCDeclaratorSymbol> parameterSymbols = ((OCFunctionSymbol)symbol).getParameterSymbols();
                        try {
                            if (index2 < parameterSymbols.size()) {
                                return b(parameterSymbols.get(index2).getName(), "");
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw a(ex2);
                        }
                    }
                }
            }
        }
        return Collections.emptyList();
    }
    
    private static List<String> b(@Nullable final OCExpression ocExpression) {
        Label_0042: {
            Label_0026: {
                try {
                    if (!(ocExpression instanceof OCReferenceExpression)) {
                        break Label_0042;
                    }
                    final OCExpression ocExpression2 = ocExpression;
                    final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)ocExpression2;
                    final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression.getSelfSuperToken();
                    if (selfSuperToken == null) {
                        break Label_0026;
                    }
                    break Label_0042;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCExpression ocExpression2 = ocExpression;
                    final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)ocExpression2;
                    final OCElementTypes.SelfSuperToken selfSuperToken = ocReferenceExpression.getSelfSuperToken();
                    if (selfSuperToken == null) {
                        return b(ocExpression.getTextWithMacros(), "");
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            try {
                if (ocExpression instanceof OCQualifiedExpression) {
                    return b(((OCQualifiedExpression)ocExpression).getName(), "");
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        try {
            if (ocExpression instanceof OCSendMessageExpression) {
                return b(((OCSendMessageExpression)ocExpression).getMessageSelector(), "");
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (ocExpression instanceof OCCallExpression) {
                return b(((OCCallExpression)ocExpression).getFunctionReferenceExpression().getTextWithMacros(), "");
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        return Collections.emptyList();
    }
    
    public static Collection<String> suggestForType(final OCType ocType, @Nullable final PsiElement psiElement, final Collection<String> collection) {
        return a(null, a(ocType, "", psiElement), psiElement, collection);
    }
    
    public static Collection<String> suggestForType(final OCType ocType, @Nullable final PsiElement psiElement, final String s) {
        return a(null, a(ocType, s, psiElement), psiElement, (Collection<String>)Collections.emptyList());
    }
    
    public static Collection<String> suggestForType(final OCSymbolKind ocSymbolKind, final OCType ocType, @Nullable final PsiElement psiElement, final String s) {
        return a(ocSymbolKind, a(ocType, s, psiElement), psiElement, (Collection<String>)Collections.emptyList());
    }
    
    private static Collection<String> a(final OCType p0, final String p1, @Nullable final PsiElement p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       12
        //     4: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //     7: areturn        
        //     8: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    11: athrow         
        //    12: aload_2        
        //    13: ifnull          44
        //    16: aload_0        
        //    17: aload_2        
        //    18: invokestatic    com/jetbrains/cidr/lang/types/OCIntType.isBool:(Lcom/jetbrains/cidr/lang/types/OCType;Lcom/intellij/psi/PsiElement;)Z
        //    21: ifeq            44
        //    24: goto            31
        //    27: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    30: athrow         
        //    31: ldc             "b"
        //    33: ldc             "boolean"
        //    35: aload_1        
        //    36: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //    39: areturn        
        //    40: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    48: ifeq            64
        //    51: ldc             "i"
        //    53: ldc             "int"
        //    55: aload_1        
        //    56: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //    59: areturn        
        //    60: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: aload_0        
        //    65: instanceof      Lcom/jetbrains/cidr/lang/types/OCRealType;
        //    68: ifeq            84
        //    71: ldc             "d"
        //    73: ldc             "double"
        //    75: aload_1        
        //    76: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //    79: areturn        
        //    80: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    83: athrow         
        //    84: aload_0        
        //    85: invokevirtual   com/jetbrains/cidr/lang/types/OCType.isCString:()Z
        //    88: ifeq            102
        //    91: ldc             "string"
        //    93: aload_1        
        //    94: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.b:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/List;
        //    97: areturn        
        //    98: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   101: athrow         
        //   102: aload_0        
        //   103: instanceof      Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   106: ifeq            194
        //   109: aload_0        
        //   110: checkcast       Lcom/jetbrains/cidr/lang/types/OCPointerType;
        //   113: invokevirtual   com/jetbrains/cidr/lang/types/OCPointerType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   116: astore_3       
        //   117: aload_3        
        //   118: aload_1        
        //   119: invokevirtual   java/lang/String.isEmpty:()Z
        //   122: ifeq            153
        //   125: aload_3        
        //   126: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   129: ifne            153
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: aload_3        
        //   140: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   143: ifeq            161
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   152: athrow         
        //   153: aload_1        
        //   154: goto            163
        //   157: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   160: athrow         
        //   161: ldc             "p"
        //   163: aload_2        
        //   164: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/util/Collection;
        //   167: astore          4
        //   169: aload           4
        //   171: invokeinterface java/util/Collection.isEmpty:()Z
        //   176: ifeq            191
        //   179: ldc             "ptr"
        //   181: invokestatic    java/util/Collections.singleton:(Ljava/lang/Object;)Ljava/util/Set;
        //   184: goto            193
        //   187: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   190: athrow         
        //   191: aload           4
        //   193: areturn        
        //   194: aload_0        
        //   195: instanceof      Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   198: ifeq            237
        //   201: aload_0        
        //   202: checkcast       Lcom/jetbrains/cidr/lang/types/OCObjectType;
        //   205: invokevirtual   com/jetbrains/cidr/lang/types/OCObjectType.getClassSymbol:()Lcom/jetbrains/cidr/lang/symbols/objc/OCClassSymbol;
        //   208: astore_3       
        //   209: aload_3        
        //   210: ifnonnull       226
        //   213: ldc             "o"
        //   215: ldc             "object"
        //   217: aload_1        
        //   218: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //   221: areturn        
        //   222: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   225: athrow         
        //   226: aload_3        
        //   227: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCClassSymbol.getName:()Ljava/lang/String;
        //   232: aload_1        
        //   233: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //   236: areturn        
        //   237: aload_0        
        //   238: instanceof      Lcom/jetbrains/cidr/lang/types/OCFunctionType;
        //   241: ifeq            257
        //   244: ldc             "fun"
        //   246: ldc             "function"
        //   248: aload_1        
        //   249: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //   252: areturn        
        //   253: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   256: athrow         
        //   257: aload_0        
        //   258: instanceof      Lcom/jetbrains/cidr/lang/types/OCVoidType;
        //   261: ifeq            277
        //   264: ldc             "v"
        //   266: ldc             "void"
        //   268: aload_1        
        //   269: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //   272: areturn        
        //   273: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   276: athrow         
        //   277: aload_0        
        //   278: instanceof      Lcom/jetbrains/cidr/lang/types/OCAutoType;
        //   281: ifeq            297
        //   284: ldc             "item"
        //   286: ldc             "item"
        //   288: aload_1        
        //   289: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //   292: areturn        
        //   293: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   296: athrow         
        //   297: aload_0        
        //   298: instanceof      Lcom/jetbrains/cidr/lang/types/OCReferenceType;
        //   301: ifeq            317
        //   304: aload_0        
        //   305: invokevirtual   com/jetbrains/cidr/lang/types/OCType.getName:()Ljava/lang/String;
        //   308: aload_1        
        //   309: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //   312: areturn        
        //   313: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   316: athrow         
        //   317: aload_0        
        //   318: instanceof      Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   321: ifeq            343
        //   324: aload_0        
        //   325: checkcast       Lcom/jetbrains/cidr/lang/types/OCStructType;
        //   328: invokevirtual   com/jetbrains/cidr/lang/types/OCStructType.getSymbol:()Lcom/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol;
        //   331: invokevirtual   com/jetbrains/cidr/lang/symbols/cpp/OCStructSymbol.getName:()Ljava/lang/String;
        //   334: aload_1        
        //   335: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/String;Ljava/lang/String;)Ljava/util/Collection;
        //   338: areturn        
        //   339: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   342: athrow         
        //   343: aload_0        
        //   344: instanceof      Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   347: ifeq            367
        //   350: aload_0        
        //   351: checkcast       Lcom/jetbrains/cidr/lang/types/OCCppReferenceType;
        //   354: invokevirtual   com/jetbrains/cidr/lang/types/OCCppReferenceType.getRefType:()Lcom/jetbrains/cidr/lang/types/OCType;
        //   357: aload_1        
        //   358: aload_2        
        //   359: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/util/Collection;
        //   362: areturn        
        //   363: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   366: athrow         
        //   367: invokestatic    java/util/Collections.emptyList:()Ljava/util/List;
        //   370: areturn        
        //    Signature:
        //  (Lcom/jetbrains/cidr/lang/types/OCType;Ljava/lang/String;Lcom/intellij/psi/PsiElement;)Ljava/util/Collection<Ljava/lang/String;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      8      8      12     Ljava/lang/IllegalArgumentException;
        //  12     24     27     31     Ljava/lang/IllegalArgumentException;
        //  16     40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     60     60     64     Ljava/lang/IllegalArgumentException;
        //  64     80     80     84     Ljava/lang/IllegalArgumentException;
        //  84     98     98     102    Ljava/lang/IllegalArgumentException;
        //  117    132    135    139    Ljava/lang/IllegalArgumentException;
        //  125    146    149    153    Ljava/lang/IllegalArgumentException;
        //  139    157    157    161    Ljava/lang/IllegalArgumentException;
        //  169    187    187    191    Ljava/lang/IllegalArgumentException;
        //  209    222    222    226    Ljava/lang/IllegalArgumentException;
        //  237    253    253    257    Ljava/lang/IllegalArgumentException;
        //  257    273    273    277    Ljava/lang/IllegalArgumentException;
        //  277    293    293    297    Ljava/lang/IllegalArgumentException;
        //  297    313    313    317    Ljava/lang/IllegalArgumentException;
        //  317    339    339    343    Ljava/lang/IllegalArgumentException;
        //  343    363    363    367    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0139:
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
    
    private static Collection<String> a(String s, final String s2) {
        final int index = s.indexOf(60);
        if (index != -1) {
            s = s.substring(0, index);
        }
        try {
            if (s.equals("id")) {
                return a("o", "object", s2);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s.equals("BOOL")) {
                return a("b", "boolean", s2);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        s = StringUtil.trimStart(s, "NS");
        return b(StringUtil.trimStart(a(s), "const"), s2);
    }
    
    private static Collection<String> a(final String s, final String s2, final String s3) {
        try {
            if (!s3.isEmpty()) {
                final String string = s3 + StringUtil.capitalize(s2);
                return Collections.singleton(string);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String string = s;
        return Collections.singleton(string);
    }
    
    private static String a(final String s) {
        for (final String s2 : OCNameSuggester.GARBAGE_SUFFIXES) {
            try {
                if (StringUtil.endsWith((CharSequence)s, (CharSequence)s2)) {
                    return a(s.substring(0, s.length() - s2.length()));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return s;
    }
    
    private static List<String> b(final String s, final String s2) {
        return a(StringUtil.trimStart(s, "get"), false, s2, "");
    }
    
    private static List<String> a(final String s, final boolean b, final String s2, final String s3) {
        final List suggestionsByName = NameUtil.getSuggestionsByName(s, s2, s3, b, false, false);
        final ArrayList<String> list = new ArrayList<String>();
        final Iterator<String> iterator = suggestionsByName.iterator();
        while (iterator.hasNext()) {
            final String b2 = b(iterator.next());
            Label_0081: {
                try {
                    if (!OCNamesValidator.isIdentifier(b2)) {
                        continue;
                    }
                    final String s4 = b2;
                    final boolean b3 = OCNamesValidator.isKeyword(s4);
                    if (!b3) {
                        break Label_0081;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final String s4 = b2;
                    final boolean b3 = OCNamesValidator.isKeyword(s4);
                    if (b3) {
                        continue;
                    }
                    list.add(b2);
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        return list;
    }
    
    @NonNls
    private static String b(String trimEnd) {
        trimEnd = StringUtil.trimEnd(trimEnd, ":");
        try {
            if (!OCNamesValidator.isIdentifier(trimEnd)) {
                return StringUtil.fixVariableNameDerivedFromPropertyName(trimEnd);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return trimEnd;
    }
    
    public static String suggestUniqueName(final OCSymbol ocSymbol, final PsiElement psiElement) {
        return suggestUniqueName(ocSymbol.getKind(), ocSymbol.getName(), psiElement);
    }
    
    public static String suggestUniqueName(@Nullable final OCSymbolKind ocSymbolKind, final String s, final PsiElement psiElement) {
        return suggestUniqueName(ocSymbolKind, s, psiElement, (Collection<String>)Collections.emptyList());
    }
    
    public static String suggestUniqueName(@Nullable final OCSymbolKind ocSymbolKind, final String s, @Nullable final PsiElement psiElement, final Collection<String> collection) {
        try {
            if (psiElement != null) {
                final List<PsiElement> list = Collections.singletonList(psiElement);
                return a(ocSymbolKind, s, list, collection);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final List<PsiElement> list = Collections.emptyList();
        return a(ocSymbolKind, s, list, collection);
    }
    
    private static String a(@Nullable final OCSymbolKind ocSymbolKind, final String s, final List<PsiElement> list, final Collection<String> collection) {
        int n = 0;
        while (true) {
            String string = null;
            Label_0035: {
                try {
                    if (n > 0) {
                        string = s + n;
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                string = s;
            }
            final String s2 = string;
            boolean b = true;
            if (collection.contains(s2)) {
                b = false;
            }
            for (final PsiElement psiElement : list) {
                try {
                    if (psiElement == null || OCCodeInsightUtil.isUniqueInScope(ocSymbolKind, s2, psiElement, psiElement.getProject())) {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                Label_0114: {
                    break Label_0114;
                    continue;
                }
                b = false;
                break;
            }
            try {
                if (b) {
                    return s2;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            ++n;
        }
    }
    
    private static Collection<String> a(@Nullable final OCSymbolKind ocSymbolKind, final Collection<String> collection, @Nullable final PsiElement psiElement, final Collection<String> collection2) {
        final LinkedHashSet<Object> set = (LinkedHashSet<Object>)new LinkedHashSet<String>();
        final LinkedHashSet<String> set2 = new LinkedHashSet<String>();
        for (final String s : collection) {
            final String suggestUniqueName = suggestUniqueName(ocSymbolKind, s, psiElement, collection2);
            try {
                if (Comparing.equal(suggestUniqueName, s)) {
                    set.add(suggestUniqueName);
                    continue;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            set2.add(suggestUniqueName);
        }
        set.addAll(set2);
        return (Collection<String>)set;
    }
    
    public static String removeVariablePrefixes(String s) {
        if (s.startsWith("an")) {
            s = StringUtil.decapitalize(s.substring(2));
        }
        else {
            try {
                if (!s.startsWith("a")) {
                    if (!s.startsWith("_")) {
                        return s;
                    }
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            s = StringUtil.decapitalize(s.substring(1));
        }
        return s;
    }
    
    public static String removeSelectorPrefixes(String decapitalize) {
        if (decapitalize.startsWith("and")) {
            decapitalize = StringUtil.decapitalize(decapitalize.substring(3));
        }
        return decapitalize;
    }
    
    public static String suggestForParameter(final Collection<String> collection, final boolean b, final String s, final OCType ocType, @Nullable final OCMethodSymbol ocMethodSymbol, @Nullable final OCExpression ocExpression) {
        String s2 = null;
        Label_0067: {
            Label_0032: {
                try {
                    if (!b) {
                        if (!s.equals(":")) {
                            break Label_0032;
                        }
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                s2 = suggestForParameter(collection, ocType, ocExpression);
                break Label_0067;
            }
            if (s.endsWith(":")) {
                s2 = StringUtil.decapitalize(getLastSubword(s.substring(0, s.length() - 1)));
            }
            else {
                s2 = "param";
            }
        }
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        try {
            if (ocMethodSymbol != null) {
                ocMethodSymbol.processSameSymbols((com.intellij.util.Processor<OCSymbol<PsiElement>>)(ocSymbol -> {
                    final PsiElement locateDefinition = ocSymbol.locateDefinition();
                    try {
                        if (locateDefinition != null) {
                            list.add(locateDefinition);
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    return true;
                }));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a(OCSymbolKind.PARAMETER, s2, list, collection);
    }
    
    public static String suggestForParameter(final Collection<String> collection, final OCType ocType, @Nullable final OCExpression ocExpression) {
        final ArrayList<String> list = new ArrayList<String>(a(ocType, "", (PsiElement)ocExpression));
        list.addAll((Collection<?>)b(ocExpression));
        final Collection<String> a = a(null, list, null, collection);
        for (final String s : a) {
            try {
                if (!ArrayUtil.contains(s, OCNameSuggester.SHORT_NAMES)) {
                    return s;
                }
                continue;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        try {
            if (a.isEmpty()) {
                return "param";
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return a.iterator().next();
    }
    
    public static String getLastSubword(final String s) {
        final String[] nameToWords = NameUtil.nameToWords(s);
        String string = "";
        for (int i = nameToWords.length - 1; i >= 0; --i) {
            string = nameToWords[i] + string;
            try {
                if (StringUtil.isJavaIdentifier(string)) {
                    return string;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
        }
        return s;
    }
    
    public static String getSelectorNameWithoutParameter(String substring, @Nullable String removeVariablePrefixes) {
        try {
            if (removeVariablePrefixes == null) {
                return substring;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        removeVariablePrefixes = removeVariablePrefixes(removeVariablePrefixes);
        if (substring.endsWith(StringUtil.capitalize(removeVariablePrefixes))) {
            substring = substring.substring(0, substring.length() - removeVariablePrefixes.length());
        }
        return substring;
    }
    
    public static String getNonCollidingName(final OCSymbol ocSymbol) {
        return getNonCollidingName(ocSymbol, false);
    }
    
    public static String getNonCollidingName(final OCSymbol ocSymbol, final boolean b) {
        return getNonCollidingName(ocSymbol.getName(), ocSymbol.getKind(), b, ocSymbol.getProject());
    }
    
    public static String getNonCollidingName(final String s, final OCSymbolKind ocSymbolKind, final boolean b, final Project project) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(project).getCustomSettings((Class)OCCodeStyleSettings.class);
        String ivars_PREFIX = null;
        Label_0033: {
            try {
                if (ocCodeStyleSettings != null) {
                    ivars_PREFIX = ocCodeStyleSettings.IVARS_PREFIX;
                    break Label_0033;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            ivars_PREFIX = "_";
        }
        final String s2 = ivars_PREFIX;
        String ivars_SUFFIX = null;
        Label_0054: {
            try {
                if (ocCodeStyleSettings != null) {
                    ivars_SUFFIX = ocCodeStyleSettings.IVARS_SUFFIX;
                    break Label_0054;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            ivars_SUFFIX = "";
        }
        final String s3 = ivars_SUFFIX;
        if (ocSymbolKind == OCSymbolKind.INSTANCE_VARIABLE) {
            final String c = c(s, s2, s3);
            Label_0093: {
                try {
                    if (!c.equals(s)) {
                        return c;
                    }
                    final boolean b2 = b;
                    if (!b2) {
                        break Label_0093;
                    }
                    return c;
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
                try {
                    final boolean b2 = b;
                    if (!b2) {
                        return StringUtil.fixVariableNameDerivedFromPropertyName(s);
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
            }
            return c;
        }
        try {
            if (ocSymbolKind == OCSymbolKind.PROPERTY) {
                return b(s, s2, s3);
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        try {
            if (!b) {
                return StringUtil.fixVariableNameDerivedFromPropertyName(s);
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return s;
    }
    
    private static String b(final String s, final String s2, final String s3) {
        boolean b2 = false;
        Label_0039: {
            Label_0030: {
                try {
                    if (s2.isEmpty()) {
                        break Label_0030;
                    }
                    final String s4 = s2;
                    final String s5 = s2;
                    final int n = s5.length();
                    final int n2 = 1;
                    final int n3 = n - n2;
                    final char c = s4.charAt(n3);
                    final boolean b = Character.isLetter(c);
                    if (b) {
                        break Label_0030;
                    }
                    break Label_0030;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final String s4 = s2;
                    final String s5 = s2;
                    final int n = s5.length();
                    final int n2 = 1;
                    final int n3 = n - n2;
                    final char c = s4.charAt(n3);
                    final boolean b = Character.isLetter(c);
                    if (b) {
                        b2 = true;
                        break Label_0039;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            b2 = false;
        }
        final boolean b3 = b2;
        try {
            if (b3) {
                return s2 + StringUtil.capitalize(s) + s3;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return s2 + s + s3;
    }
    
    private static String c(final String p0, final String p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokevirtual   java/lang/String.isEmpty:()Z
        //     4: ifne            38
        //     7: aload_1        
        //     8: aload_1        
        //     9: invokevirtual   java/lang/String.length:()I
        //    12: iconst_1       
        //    13: isub           
        //    14: invokevirtual   java/lang/String.charAt:(I)C
        //    17: invokestatic    java/lang/Character.isLetter:(C)Z
        //    20: ifeq            38
        //    23: goto            30
        //    26: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    29: athrow         
        //    30: iconst_1       
        //    31: goto            39
        //    34: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: iconst_0       
        //    39: istore_3       
        //    40: aload_0        
        //    41: invokevirtual   java/lang/String.length:()I
        //    44: aload_1        
        //    45: invokevirtual   java/lang/String.length:()I
        //    48: if_icmple       199
        //    51: aload_1        
        //    52: invokevirtual   java/lang/String.length:()I
        //    55: aload_2        
        //    56: invokevirtual   java/lang/String.length:()I
        //    59: iadd           
        //    60: ifle            199
        //    63: goto            70
        //    66: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    69: athrow         
        //    70: iload_3        
        //    71: ifeq            103
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    80: athrow         
        //    81: aload_0        
        //    82: aload_1        
        //    83: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    86: ifeq            199
        //    89: goto            96
        //    92: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    95: athrow         
        //    96: goto            111
        //    99: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   102: athrow         
        //   103: aload_0        
        //   104: aload_1        
        //   105: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   108: ifeq            199
        //   111: aload_0        
        //   112: aload_1        
        //   113: invokevirtual   java/lang/String.length:()I
        //   116: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   119: astore          4
        //   121: iload_3        
        //   122: ifeq            132
        //   125: aload           4
        //   127: invokestatic    com/intellij/openapi/util/text/StringUtil.decapitalize:(Ljava/lang/String;)Ljava/lang/String;
        //   130: astore          4
        //   132: aload           4
        //   134: aload_2        
        //   135: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   138: ifeq            178
        //   141: aload           4
        //   143: invokevirtual   java/lang/String.length:()I
        //   146: aload_2        
        //   147: invokevirtual   java/lang/String.length:()I
        //   150: if_icmple       178
        //   153: goto            160
        //   156: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   159: athrow         
        //   160: aload           4
        //   162: iconst_0       
        //   163: aload           4
        //   165: invokevirtual   java/lang/String.length:()I
        //   168: aload_2        
        //   169: invokevirtual   java/lang/String.length:()I
        //   172: isub           
        //   173: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //   176: astore          4
        //   178: aload           4
        //   180: invokestatic    com/jetbrains/cidr/lang/util/OCNamesValidator.isKeyword:(Ljava/lang/String;)Z
        //   183: ifeq            196
        //   186: aload           4
        //   188: invokestatic    com/intellij/openapi/util/text/StringUtil.fixVariableNameDerivedFromPropertyName:(Ljava/lang/String;)Ljava/lang/String;
        //   191: areturn        
        //   192: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   195: athrow         
        //   196: aload           4
        //   198: areturn        
        //   199: aload_0        
        //   200: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      23     26     30     Ljava/lang/IllegalArgumentException;
        //  7      34     34     38     Ljava/lang/IllegalArgumentException;
        //  40     63     66     70     Ljava/lang/IllegalArgumentException;
        //  51     74     77     81     Ljava/lang/IllegalArgumentException;
        //  70     89     92     96     Ljava/lang/IllegalArgumentException;
        //  81     99     99     103    Ljava/lang/IllegalArgumentException;
        //  132    153    156    160    Ljava/lang/IllegalArgumentException;
        //  178    192    192    196    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0070:
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
    public static String getClang4ImplicitIvarName(final String s) {
        String string;
        try {
            string = "_" + s;
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/OCNameSuggester", "getClang4ImplicitIvarName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return string;
    }
    
    @Nullable
    public static String getClang4PropertyName(final String s) {
        try {
            if (s.startsWith("_")) {
                return s.substring(1);
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return null;
    }
    
    public static String getCppFieldNameWithoutPrefixAndSuffix(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocDeclaratorSymbol.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        String fields_PREFIX = null;
        Label_0033: {
            try {
                if (ocCodeStyleSettings != null) {
                    fields_PREFIX = ocCodeStyleSettings.FIELDS_PREFIX;
                    break Label_0033;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            fields_PREFIX = "";
        }
        final String s = fields_PREFIX;
        try {
            if (ocCodeStyleSettings != null) {
                final String fields_SUFFIX = ocCodeStyleSettings.FIELDS_SUFFIX;
                return c(ocDeclaratorSymbol.getName(), s, fields_SUFFIX);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final String fields_SUFFIX = "";
        return c(ocDeclaratorSymbol.getName(), s, fields_SUFFIX);
    }
    
    public static String getCppGetterName(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocDeclaratorSymbol.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        String getters_PREFIX = null;
        Label_0033: {
            try {
                if (ocCodeStyleSettings != null) {
                    getters_PREFIX = ocCodeStyleSettings.GETTERS_PREFIX;
                    break Label_0033;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            getters_PREFIX = "get";
        }
        String s = getters_PREFIX;
        String s2 = getCppFieldNameWithoutPrefixAndSuffix(ocDeclaratorSymbol);
        final String name = ocDeclaratorSymbol.getName();
        Label_0071: {
            try {
                if (!s2.equals(name) || !s.isEmpty()) {
                    break Label_0071;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            s = "get";
        }
        final boolean bool = OCIntType.isBool(ocDeclaratorSymbol.getResolvedType(), (PsiElement)ocDeclaratorSymbol.getContainingOCFile());
        Label_0143: {
            try {
                if (!s.equals("get") || !bool) {
                    break Label_0143;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            s = "is";
            try {
                if (s2.equals(name) || !OCElementUtil.startsWithWord(StringUtil.decapitalize(s2), s)) {
                    break Label_0143;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            s = "";
            s2 = StringUtil.decapitalize(s2);
            try {
                if (!s.equals("Get") || !bool) {
                    return b(s2, s, "");
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        s = "Is";
        try {
            if (s2.equals(name) || !OCElementUtil.startsWithWord(StringUtil.decapitalize(s2), StringUtil.decapitalize(s))) {
                return b(s2, s, "");
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        s = "";
        s2 = StringUtil.capitalize(s2);
        return b(s2, s, "");
    }
    
    public static String getCppSetterName(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(ocDeclaratorSymbol.getProject()).getCustomSettings((Class)OCCodeStyleSettings.class);
        String setters_PREFIX = null;
        Label_0033: {
            try {
                if (ocCodeStyleSettings != null) {
                    setters_PREFIX = ocCodeStyleSettings.SETTERS_PREFIX;
                    break Label_0033;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            setters_PREFIX = "set";
        }
        String s = setters_PREFIX;
        final String cppFieldNameWithoutPrefixAndSuffix = getCppFieldNameWithoutPrefixAndSuffix(ocDeclaratorSymbol);
        try {
            if (!cppFieldNameWithoutPrefixAndSuffix.equals(ocDeclaratorSymbol.getName()) || !s.isEmpty()) {
                return b(cppFieldNameWithoutPrefixAndSuffix, s, "");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        s = "set";
        return b(cppFieldNameWithoutPrefixAndSuffix, s, "");
    }
    
    public static boolean isObjCGetter(final String s) {
        try {
            if (!s.endsWith(":")) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public static boolean isObjCSetter(final String s) {
        try {
            if (s.indexOf(58) == s.length() - 1) {
                return true;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return false;
    }
    
    public static String getObjCSetterFromGetter(final String s) {
        return "set" + StringUtil.capitalize(s) + ":";
    }
    
    @Nullable
    public static String getObjCGetterFromSetter(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ldc             "set"
        //     3: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //     6: ifeq            57
        //     9: aload_0        
        //    10: invokevirtual   java/lang/String.length:()I
        //    13: iconst_4       
        //    14: if_icmple       57
        //    17: goto            24
        //    20: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    23: athrow         
        //    24: aload_0        
        //    25: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.isObjCSetter:(Ljava/lang/String;)Z
        //    28: ifeq            57
        //    31: goto            38
        //    34: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    37: athrow         
        //    38: aload_0        
        //    39: iconst_3       
        //    40: aload_0        
        //    41: invokevirtual   java/lang/String.length:()I
        //    44: iconst_1       
        //    45: isub           
        //    46: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    49: invokestatic    com/intellij/openapi/util/text/StringUtil.decapitalize:(Ljava/lang/String;)Ljava/lang/String;
        //    52: areturn        
        //    53: invokestatic    com/jetbrains/cidr/lang/refactoring/OCNameSuggester.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    56: athrow         
        //    57: aconst_null    
        //    58: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      17     20     24     Ljava/lang/IllegalArgumentException;
        //  9      31     34     38     Ljava/lang/IllegalArgumentException;
        //  24     53     53     57     Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0024:
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
        OCNameSuggester.GARBAGE_SUFFIXES = new String[] { "Impl", "IMPL", "Ref", "REF", "_t" };
        OCNameSuggester.SHORT_NAMES = new String[] { "b", "i", "d", "string", "ptr", "o", "fun", "_op", "v", "item" };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
