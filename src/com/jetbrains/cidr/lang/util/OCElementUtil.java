// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.util;

import com.jetbrains.cidr.lang.psi.impl.OCEmptyName;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.resolve.references.OCReferenceWithContext;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.resolve.references.OCResourceReference;
import com.jetbrains.cidr.lang.psi.OCSelectorExpression;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.jetbrains.cidr.lang.psi.OCCppNewExpression;
import com.jetbrains.cidr.lang.psi.OCUDLiteralExpression;
import com.jetbrains.cidr.lang.psi.OCConstructorFieldInitializer;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.resolve.references.OCOperatorReference;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.psi.OCSymbolNameOwner;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.SmartPointerManager;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCClassDeclaration;
import com.intellij.lang.ForeignLeafType;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.search.ProjectScope;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.intellij.psi.search.LocalSearchScope;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;
import com.intellij.psi.search.SearchScope;
import com.jetbrains.cidr.lang.resolve.references.OCResourceCompletionProvider;
import com.jetbrains.cidr.lang.resolve.references.OCResourceReferenceContributor;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.OCMacroCallArgument;
import com.intellij.psi.PsiReference;
import com.jetbrains.cidr.lang.psi.OCPragma;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.psi.OCMethodSelectorPart;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import java.util.Iterator;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.psi.OCConditionalExpression;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.psi.OCConstructorInitializationList;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.intellij.util.containers.ContainerUtil;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifiedNameOwner;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.parser.OCMacroRange;
import com.jetbrains.cidr.lang.preprocessor.OCMacroForeignLeafElement;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil;
import com.jetbrains.cidr.lang.psi.OCDirective;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiFile;
import java.math.BigInteger;
import com.jetbrains.cidr.lang.parser.OCKeywordElementType;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.types.OCMagicType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCLiteralExpression;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCTypeElement;
import com.jetbrains.cidr.lang.psi.OCFunctionDeclaration;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.jetbrains.cidr.lang.parser.OCPunctuatorElementType;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.parser.OCElementTypes;
import org.jetbrains.annotations.Contract;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.Condition;
import com.intellij.psi.tree.TokenSet;
import com.jetbrains.cidr.lang.types.OCIntType;

public class OCElementUtil
{
    private static final OCIntType[] NONE_DEC;
    private static final OCIntType[] NONE_HEX;
    private static final OCIntType[] U_DEC;
    private static final OCIntType[] U_HEX;
    private static final OCIntType[] L_DEC;
    private static final OCIntType[] L_HEX;
    private static final OCIntType[] UL_DEC;
    private static final OCIntType[] UL_HEX;
    private static final OCIntType[] LL_DEC;
    private static final OCIntType[] LL_HEX;
    private static final OCIntType[] ULL_DEC;
    private static final OCIntType[] ULL_HEX;
    private static final TokenSet GOOD_FOR_RENAME;
    private static final Condition<PsiElement> ELEMENT_NON_WHITESPACE_CONDITION;
    
    @Contract("null -> null;!null -> !null")
    public static IElementType getElementType(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return astNode.getElementType();
    }
    
    @Contract("null -> null;!null -> !null")
    public static IElementType getElementType(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return getElementType(psiElement.getNode());
    }
    
    @Contract("null -> null")
    public static IElementType getObjCKeywordElementType(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null || getElementType(astNode) != OCElementTypes.OBJC_KEYWORD) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final ASTNode[] children = astNode.getChildren(OCTokenTypes.KEYWORDS_WITH_DOGS);
        try {
            if (children.length == 1) {
                return children[0].getElementType();
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    public static ASTNode findObjCKeyword(@NotNull final ASTNode astNode, @NotNull final IElementType elementType) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/util/OCElementUtil", "findObjCKeyword"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (elementType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "type", "com/jetbrains/cidr/lang/util/OCElementUtil", "findObjCKeyword"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        for (final ASTNode astNode2 : astNode.getChildren((TokenSet)null)) {
            if (astNode2.getElementType() == OCElementTypes.OBJC_KEYWORD) {
                for (final ASTNode astNode3 : astNode2.getChildren((TokenSet)null)) {
                    try {
                        if (getElementType(astNode3) == elementType) {
                            return astNode3;
                        }
                    }
                    catch (NumberFormatException ex3) {
                        throw b(ex3);
                    }
                }
            }
        }
        return null;
    }
    
    @Contract("null, _ -> false")
    public static boolean isAlternativeCppPunctuator(@Nullable final IElementType elementType, @Nullable final CharSequence charSequence) {
        Label_0026: {
            try {
                if (!(elementType instanceof OCPunctuatorElementType)) {
                    return false;
                }
                final CharSequence charSequence2 = charSequence;
                final String s = charSequence2.toString();
                final OCPunctuatorElementType ocPunctuatorElementType = OCPunctuatorElementType.findByKeyword(s);
                if (ocPunctuatorElementType != null) {
                    break Label_0026;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final CharSequence charSequence2 = charSequence;
                final String s = charSequence2.toString();
                final OCPunctuatorElementType ocPunctuatorElementType = OCPunctuatorElementType.findByKeyword(s);
                if (ocPunctuatorElementType != null) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Contract("null, _ -> false")
    public static boolean isOneOf(@Nullable final ASTNode astNode, @NotNull final PsiElement... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "candidates", "com/jetbrains/cidr/lang/util/OCElementUtil", "isOneOf"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        for (final PsiElement psiElement : array) {
            Label_0103: {
                try {
                    if (psiElement == null) {
                        break Label_0103;
                    }
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement2 = psiElement;
                    final ASTNode astNode3 = psiElement2.getNode();
                    if (astNode2 == astNode3) {
                        return true;
                    }
                    break Label_0103;
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                try {
                    final ASTNode astNode2 = astNode;
                    final PsiElement psiElement2 = psiElement;
                    final ASTNode astNode3 = psiElement2.getNode();
                    if (astNode2 == astNode3) {
                        return true;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isMethodOrFunction(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final IElementType elementType = astNode.getElementType();
        Label_0038: {
            try {
                if (elementType == OCElementTypes.METHOD) {
                    break Label_0038;
                }
                final OCElementType ocElementType = (OCElementType)elementType;
                final OCElementType ocElementType2 = OCElementTypes.FUNCTION_DEFINITION;
                if (ocElementType == ocElementType2) {
                    break Label_0038;
                }
                return false;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final OCElementType ocElementType = (OCElementType)elementType;
                final OCElementType ocElementType2 = OCElementTypes.FUNCTION_DEFINITION;
                if (ocElementType == ocElementType2) {
                    return true;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isMethodOrFunctionBody(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        Label_0044: {
            try {
                if (!OCElementTypes.BLOCK_STATEMENTS.contains(astNode.getElementType())) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final ASTNode astNode3 = astNode2.getTreeParent();
                final boolean b = isMethodOrFunction(astNode3);
                if (b) {
                    break Label_0044;
                }
                return false;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final ASTNode astNode2 = astNode;
                final ASTNode astNode3 = astNode2.getTreeParent();
                final boolean b = isMethodOrFunction(astNode3);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean hasNoReturnType(@Nullable final OCFunctionDeclaration ocFunctionDeclaration) {
        try {
            if (ocFunctionDeclaration == null) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final OCTypeElement typeElement = ocFunctionDeclaration.getTypeElement();
        Label_0037: {
            try {
                if (typeElement == null) {
                    break Label_0037;
                }
                final OCTypeElement ocTypeElement = typeElement;
                final boolean b = ocTypeElement.isEmptyType();
                if (b) {
                    break Label_0037;
                }
                return false;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final OCTypeElement ocTypeElement = typeElement;
                final boolean b = ocTypeElement.isEmptyType();
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isOperatorDeclaration(@Nullable final OCDeclarator ocDeclarator) {
        try {
            if (ocDeclarator == null) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (ocDeclarator.getNode().findChildByType((IElementType)OCTokenTypes.OPERATOR_CPP_KEYWORD) != null) {
                return true;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isVariableDeclaration(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (astNode.getElementType() != OCElementTypes.DECLARATION) {
                return false;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final ASTNode childByType = astNode.findChildByType((IElementType)OCElementTypes.DECLARATOR);
        Label_0056: {
            try {
                if (childByType == null) {
                    return false;
                }
                final ASTNode astNode2 = astNode;
                final boolean b = isFunctionDeclaratorOrPredeclarator(astNode2);
                if (!b) {
                    break Label_0056;
                }
                return false;
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            try {
                final ASTNode astNode2 = astNode;
                final boolean b = isFunctionDeclaratorOrPredeclarator(astNode2);
                if (!b) {
                    return true;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isFunctionPredeclaration(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (astNode.getElementType() == OCElementTypes.FUNCTION_DECLARATION) {
                return true;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (astNode.getElementType() != OCElementTypes.DECLARATION) {
                return false;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        return isFunctionPredeclarator(astNode.findChildByType((IElementType)OCElementTypes.DECLARATOR));
    }
    
    @Contract("null -> false")
    public static boolean isFunctionPredeclarator(@Nullable final ASTNode astNode) {
        try {
            if (!isFunctionDeclaratorOrPredeclarator(astNode)) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final ASTNode treeParent = astNode.getTreeParent();
        Label_0043: {
            try {
                if (treeParent == null) {
                    break Label_0043;
                }
                final ASTNode astNode2 = treeParent;
                final IElementType elementType = astNode2.getElementType();
                final OCElementType ocElementType = OCElementTypes.FUNCTION_DEFINITION;
                if (elementType != ocElementType) {
                    break Label_0043;
                }
                return false;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final ASTNode astNode2 = treeParent;
                final IElementType elementType = astNode2.getElementType();
                final OCElementType ocElementType = OCElementTypes.FUNCTION_DEFINITION;
                if (elementType != ocElementType) {
                    return true;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isFunctionDeclaratorOrPredeclarator(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (astNode.getElementType() != OCElementTypes.DECLARATOR) {
                return false;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (astNode.findChildByType((IElementType)OCElementTypes.PARAMETER_LIST) != null) {
                return true;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isRefToken(@Nullable final IElementType elementType) {
        return OCTokenTypes.TYPE_MODIFIERS.contains(elementType);
    }
    
    @Contract("null -> null")
    public static IElementType getRefInDeclarator(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (astNode.getElementType() != OCElementTypes.DECLARATOR) {
                return null;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final IElementType elementType = getElementType(getFirstNonSpaceChild(astNode));
        try {
            if (isRefToken(elementType)) {
                return elementType;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @Contract("null -> null")
    public static ASTNode getFirstNonSpaceChild(@Nullable final ASTNode astNode) {
        try {
            if (astNode == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        ASTNode astNode2;
        for (astNode2 = astNode.getFirstChildNode(); astNode2 instanceof PsiWhiteSpace; astNode2 = astNode2.getTreeNext()) {}
        return astNode2;
    }
    
    @Contract("null -> false")
    public static boolean isStructTypeDeclaration(@Nullable final ASTNode astNode) {
        Label_0032: {
            try {
                if (astNode == null) {
                    return false;
                }
                final TokenSet set = OCElementTypes.STRUCTURE_TYPES;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set.contains(elementType);
                if (!b) {
                    return false;
                }
                break Label_0032;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final TokenSet set = OCElementTypes.STRUCTURE_TYPES;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set.contains(elementType);
                if (!b) {
                    return false;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                if (astNode.findChildByType((IElementType)OCTokenTypes.RBRACE) != null) {
                    return true;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @Contract("null -> null")
    public static String getStringLiteral(@Nullable PsiElement diveIntoParenthesesAndCasts) {
        diveIntoParenthesesAndCasts = OCParenthesesUtils.diveIntoParenthesesAndCasts(diveIntoParenthesesAndCasts);
        if (diveIntoParenthesesAndCasts instanceof OCLiteralExpression) {
            final OCLiteralExpression ocLiteralExpression = (OCLiteralExpression)diveIntoParenthesesAndCasts;
            try {
                if (ocLiteralExpression.isNSStringLiteral()) {
                    return ocLiteralExpression.getUnescapedLiteralText();
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
        }
        return null;
    }
    
    @Nullable
    public static OCElementTypes.SelfSuperToken getSelfSuperToken(final String s, final PsiElement psiElement, @NotNull final OCResolveContext ocResolveContext) {
        try {
            if (ocResolveContext == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "resolveContext", "com/jetbrains/cidr/lang/util/OCElementUtil", "getSelfSuperToken"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCElementTypes.SelfSuperToken selfSuperToken;
        if ("self".equals(s)) {
            selfSuperToken = OCElementTypes.SelfSuperToken.SELF;
        }
        else {
            if (!"super".equals(s)) {
                return null;
            }
            selfSuperToken = OCElementTypes.SelfSuperToken.SUPER;
        }
        try {
            if (PsiTreeUtil.getContextOfType(psiElement, (Class)OCMethod.class, false) == null) {
                return null;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCBlockExpression.class) != null) {
            final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
            OCResolveUtil.processLocalSymbols(s, psiElement, (Processor<OCSymbol>)findFirstProcessor, false);
            final OCSymbol ocSymbol = (OCSymbol)findFirstProcessor.getFoundValue();
            Label_0167: {
                try {
                    if (ocSymbol == null) {
                        return selfSuperToken;
                    }
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final OCType ocType = ocSymbol2.getType();
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final OCType ocType2 = ocType.resolve(ocResolveContext2);
                    final boolean b = ocType2 instanceof OCMagicType;
                    if (!b) {
                        break Label_0167;
                    }
                    return selfSuperToken;
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                try {
                    final OCSymbol ocSymbol2 = ocSymbol;
                    final OCType ocType = ocSymbol2.getType();
                    final OCResolveContext ocResolveContext2 = ocResolveContext;
                    final OCType ocType2 = ocType.resolve(ocResolveContext2);
                    final boolean b = ocType2 instanceof OCMagicType;
                    if (!b) {
                        return null;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
        }
        return selfSuperToken;
    }
    
    @Nullable
    public static Object getConstValue(final IElementType elementType, final String s, @Nullable final PsiElement psiElement, @Nullable final OCInclusionContext ocInclusionContext) {
        try {
            if (elementType == OCTokenTypes.FAKE_TRUE) {
                return 1;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (elementType == OCTokenTypes.FAKE_FALSE) {
                return 0;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (elementType == OCTokenTypes.CHARACTER_LITERAL) {
                return a(s, psiElement, ocInclusionContext);
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        Label_0077: {
            try {
                if (elementType == OCTokenTypes.STRING_LITERAL) {
                    return s;
                }
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCTokenTypes.AT;
                if (elementType2 == ocElementType) {
                    return s;
                }
                break Label_0077;
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
            try {
                final IElementType elementType2 = elementType;
                final OCElementType ocElementType = OCTokenTypes.AT;
                if (elementType2 == ocElementType) {
                    return s;
                }
            }
            catch (NumberFormatException ex5) {
                throw b(ex5);
            }
            try {
                if (elementType == OCTokenTypes.INTEGER_LITERAL) {
                    return parseInteger(s, psiElement, ocInclusionContext).second;
                }
            }
            catch (NumberFormatException ex6) {
                throw b(ex6);
            }
        }
        Label_0128: {
            try {
                if (elementType == OCTokenTypes.FLOAT_LITERAL) {
                    final String s2 = s;
                    final String s3 = "'";
                    final String s4 = "";
                    final String s5 = StringUtil.replace(s2, s3, s4);
                    final float n = Float.parseFloat(s5);
                    return n;
                }
                break Label_0128;
            }
            catch (OCElementUtil.1CancelException ex7) {
                throw b(ex7);
            }
            try {
                final String s2 = s;
                final String s3 = "'";
                final String s4 = "";
                final String s5 = StringUtil.replace(s2, s3, s4);
                final float n = Float.parseFloat(s5);
                return n;
            }
            catch (NumberFormatException ex12) {
                return null;
            }
            try {
                if (elementType == OCTokenTypes.TRUE_CPP_KEYWORD) {
                    return Boolean.TRUE;
                }
            }
            catch (NumberFormatException ex8) {
                throw b(ex8);
            }
        }
        try {
            if (elementType == OCTokenTypes.FALSE_CPP_KEYWORD) {
                return Boolean.FALSE;
            }
        }
        catch (NumberFormatException ex9) {
            throw b(ex9);
        }
        Label_0179: {
            try {
                if (elementType == OCTokenTypes.__NULL_KEYWORD) {
                    break Label_0179;
                }
                final IElementType elementType3 = elementType;
                final OCKeywordElementType ocKeywordElementType = OCTokenTypes.NULL_CPP_KEYWORD;
                if (elementType3 == ocKeywordElementType) {
                    break Label_0179;
                }
                return null;
            }
            catch (NumberFormatException ex10) {
                throw b(ex10);
            }
            try {
                final IElementType elementType3 = elementType;
                final OCKeywordElementType ocKeywordElementType = OCTokenTypes.NULL_CPP_KEYWORD;
                if (elementType3 == ocKeywordElementType) {
                    return 0;
                }
            }
            catch (NumberFormatException ex11) {
                throw b(ex11);
            }
        }
        return null;
    }
    
    @Nullable
    private static Number a(@Nullable final String s, @Nullable final PsiElement psiElement, @Nullable final OCInclusionContext ocInclusionContext) {
        PsiFile containingFile = null;
        Label_0018: {
            try {
                if (psiElement == null) {
                    containingFile = null;
                    break Label_0018;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            containingFile = psiElement.getContainingFile();
        }
        final PsiFile psiFile = containingFile;
        final OCCharLiteral charLiteral = OCStringLiteralUtil.parseCharLiteral(s);
        final OCIntType type = charLiteral.getType(OCCodeInsightUtil.isInPlainOldC(psiFile, ocInclusionContext));
        Number convert = null;
        final String contents = charLiteral.getContents(false);
        final int length = contents.length();
        if (length > 0) {
            long n = 0L;
            final int sizeInBytes = type.getSizeInBytes(psiFile, ocInclusionContext);
            for (int i = 0; i < length; ++i) {
                final char char1 = contents.charAt(i);
                long n2 = 0L;
                int n3 = 0;
                Label_0116: {
                    try {
                        n2 = n;
                        if ((char1 & '\uff00') != '\0') {
                            n3 = 16;
                            break Label_0116;
                        }
                    }
                    catch (NumberFormatException ex2) {
                        throw b(ex2);
                    }
                    n3 = 8;
                }
                n = (n2 << n3) + char1;
            }
            convert = OCNumber.convert(BigInteger.valueOf(n), sizeInBytes, type.isSigned());
        }
        return convert;
    }
    
    private static OCIntType[] a(final boolean p0, final boolean p1, final boolean p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iload_1        
        //     1: ifeq            40
        //     4: iload_3        
        //     5: ifeq            40
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    14: athrow         
        //    15: iload_0        
        //    16: ifeq            36
        //    19: goto            26
        //    22: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    25: athrow         
        //    26: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.ULL_HEX:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    29: goto            39
        //    32: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    35: athrow         
        //    36: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.ULL_DEC:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    39: areturn        
        //    40: iload_3        
        //    41: ifeq            69
        //    44: iload_0        
        //    45: ifeq            65
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    54: athrow         
        //    55: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.LL_HEX:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    58: goto            68
        //    61: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    64: athrow         
        //    65: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.LL_DEC:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    68: areturn        
        //    69: iload_1        
        //    70: ifeq            109
        //    73: iload_2        
        //    74: ifeq            109
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    83: athrow         
        //    84: iload_0        
        //    85: ifeq            105
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    94: athrow         
        //    95: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.UL_HEX:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //    98: goto            108
        //   101: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   104: athrow         
        //   105: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.UL_DEC:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   108: areturn        
        //   109: iload_2        
        //   110: ifeq            138
        //   113: iload_0        
        //   114: ifeq            134
        //   117: goto            124
        //   120: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   123: athrow         
        //   124: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.L_HEX:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   127: goto            137
        //   130: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   133: athrow         
        //   134: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.L_DEC:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   137: areturn        
        //   138: iload_1        
        //   139: ifeq            167
        //   142: iload_0        
        //   143: ifeq            163
        //   146: goto            153
        //   149: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   152: athrow         
        //   153: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.U_HEX:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   156: goto            166
        //   159: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   162: athrow         
        //   163: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.U_DEC:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   166: areturn        
        //   167: iload_0        
        //   168: ifeq            181
        //   171: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.NONE_HEX:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   174: goto            184
        //   177: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   180: athrow         
        //   181: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.NONE_DEC:[Lcom/jetbrains/cidr/lang/types/OCIntType;
        //   184: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      8      11     15     Ljava/lang/NumberFormatException;
        //  4      19     22     26     Ljava/lang/NumberFormatException;
        //  15     32     32     36     Ljava/lang/NumberFormatException;
        //  40     48     51     55     Ljava/lang/NumberFormatException;
        //  44     61     61     65     Ljava/lang/NumberFormatException;
        //  69     77     80     84     Ljava/lang/NumberFormatException;
        //  73     88     91     95     Ljava/lang/NumberFormatException;
        //  84     101    101    105    Ljava/lang/NumberFormatException;
        //  109    117    120    124    Ljava/lang/NumberFormatException;
        //  113    130    130    134    Ljava/lang/NumberFormatException;
        //  138    146    149    153    Ljava/lang/NumberFormatException;
        //  142    159    159    163    Ljava/lang/NumberFormatException;
        //  167    177    177    181    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    public static Pair<OCIntType, Number> parseInteger(@NotNull final String s, @Nullable final PsiElement psiElement, @Nullable final OCInclusionContext ocInclusionContext) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/lang/util/OCElementUtil", "parseInteger"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        OCIntType ulonglong = OCIntType.ULONGLONG;
        Object convert = null;
        try {
            final OCNumber.ParseInfo parse = OCNumber.ParseInfo.parse(s);
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            Label_0129: {
                Label_0111: {
                    Label_0093: {
                        Label_0076: {
                            try {
                                if (parse.radix != 10) {
                                    b = true;
                                    break Label_0076;
                                }
                            }
                            catch (NumberFormatException ex2) {
                                throw b(ex2);
                            }
                            b = false;
                            try {
                                if (parse.countU > 0) {
                                    b2 = true;
                                    break Label_0093;
                                }
                            }
                            catch (NumberFormatException ex3) {
                                throw b(ex3);
                            }
                        }
                        b2 = false;
                        try {
                            if (parse.countL == 1) {
                                b3 = true;
                                break Label_0111;
                            }
                        }
                        catch (NumberFormatException ex4) {
                            throw b(ex4);
                        }
                    }
                    b3 = false;
                    try {
                        if (parse.countL == 2) {
                            b4 = true;
                            break Label_0129;
                        }
                    }
                    catch (NumberFormatException ex5) {
                        throw b(ex5);
                    }
                }
                b4 = false;
            }
            final OCIntType[] a = a(b, b2, b3, b4);
            final BigInteger bigInteger = new BigInteger(parse.numbers, parse.radix);
            final int bitLength = bigInteger.bitLength();
            final OCIntType[] array = a;
            final int length = array.length;
            int i = 0;
            while (i < length) {
                final OCIntType ocIntType = array[i];
                final int bits = ocIntType.getBits(psiElement, ocInclusionContext);
                final int n = bits - bitLength;
                Label_0234: {
                    Label_0277: {
                        Label_0219: {
                            try {
                                if (n > 0) {
                                    break Label_0234;
                                }
                                final int n2 = n;
                                if (n2 == 0) {
                                    break Label_0219;
                                }
                                break Label_0277;
                            }
                            catch (NumberFormatException ex6) {
                                throw b(ex6);
                            }
                            try {
                                final int n2 = n;
                                if (n2 != 0) {
                                    break Label_0277;
                                }
                                if (ocIntType.isSigned()) {
                                    break Label_0277;
                                }
                            }
                            catch (NumberFormatException ex7) {
                                throw b(ex7);
                            }
                        }
                        break Label_0234;
                    }
                    ++i;
                    continue;
                }
                ulonglong = ocIntType;
                BigInteger negate = null;
                Label_0259: {
                    try {
                        if (parse.negative) {
                            negate = bigInteger.negate();
                            break Label_0259;
                        }
                    }
                    catch (NumberFormatException ex8) {
                        throw b(ex8);
                    }
                    negate = bigInteger;
                }
                convert = OCNumber.convert(negate, bits / 8, ocIntType.isSigned());
                break;
            }
        }
        catch (NumberFormatException ex10) {}
        Pair create;
        try {
            create = Pair.create((Object)ulonglong, convert);
            if (create == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "parseInteger"));
            }
        }
        catch (NumberFormatException ex9) {
            throw b(ex9);
        }
        return (Pair<OCIntType, Number>)create;
    }
    
    @Nullable
    public static OCType getType(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement instanceof OCTypeElement) {
                return ((OCTypeElement)psiElement).getType().resolve(psiElement.getContainingFile());
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement instanceof OCExpression) {
                return ((OCExpression)psiElement).getResolvedType();
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement == null || getElementType(psiElement) != OCElementTypes.TYPE_CODE_FRAGMENT) {
                return null;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        OCType type = null;
        for (final PsiElement psiElement2 : psiElement.getChildren()) {
            if (psiElement2 instanceof OCTypeElement) {
                type = getType(psiElement2);
            }
            else {
                try {
                    if (isElementSignificant(psiElement2)) {
                        return null;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
        }
        return type;
    }
    
    @Nullable
    public static OCMacroCall getElementMacroCall(final PsiElement psiElement) {
        PsiElement psiElement2 = getPrevSiblingOrParentSibling(psiElement);
        while (psiElement2 instanceof OCMacroCall) {
            final OCMacroCall ocMacroCall = (OCMacroCall)psiElement2;
            psiElement2 = psiElement2.getPrevSibling();
            try {
                if (ocMacroCall.getTextLength() > 0) {
                    return ocMacroCall;
                }
                continue;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
        }
        return null;
    }
    
    @Nullable
    public static PsiElement getPrevSiblingOrParentSibling(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getPrevSiblingOrParentSibling"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final PsiElement prevSibling = psiElement.getPrevSibling();
        try {
            if (prevSibling != null) {
                return prevSibling;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final PsiElement parent = psiElement.getParent();
        Label_0086: {
            try {
                if (parent == null) {
                    break Label_0086;
                }
                final PsiElement psiElement2 = parent;
                final boolean b = psiElement2 instanceof PsiFile;
                if (b) {
                    break Label_0086;
                }
                return getPrevSiblingOrParentSibling(parent);
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            try {
                final PsiElement psiElement2 = parent;
                final boolean b = psiElement2 instanceof PsiFile;
                if (b) {
                    return null;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
        }
        return getPrevSiblingOrParentSibling(parent);
    }
    
    @Nullable
    public static PsiElement getPrevLeaf(@Nullable PsiElement psiElement) {
        while (psiElement != null) {
            final PsiElement prevSiblingOrParentSibling = getPrevSiblingOrParentSibling(psiElement);
            try {
                if (prevSiblingOrParentSibling == null) {
                    return null;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            PsiElement psiElement2 = prevSiblingOrParentSibling;
            for (PsiElement psiElement3 = prevSiblingOrParentSibling.getLastChild(); psiElement3 != null; psiElement3 = psiElement3.getLastChild()) {
                psiElement2 = psiElement3;
            }
            try {
                if (psiElement2 instanceof LeafPsiElement) {
                    return psiElement2;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            psiElement = psiElement2;
        }
        return null;
    }
    
    @Nullable
    public static PsiElement getNextSiblingOrParentSibling(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getNextSiblingOrParentSibling"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final PsiElement nextSibling = psiElement.getNextSibling();
        try {
            if (nextSibling != null) {
                return nextSibling;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final PsiElement parent = psiElement.getParent();
        Label_0086: {
            try {
                if (parent == null) {
                    break Label_0086;
                }
                final PsiElement psiElement2 = parent;
                final boolean b = psiElement2 instanceof PsiFile;
                if (b) {
                    break Label_0086;
                }
                return getNextSiblingOrParentSibling(parent);
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            try {
                final PsiElement psiElement2 = parent;
                final boolean b = psiElement2 instanceof PsiFile;
                if (b) {
                    return null;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
        }
        return getNextSiblingOrParentSibling(parent);
    }
    
    @Nullable
    public static PsiElement getNextNonWhitespaceSibling(final PsiElement psiElement) {
        PsiElement psiElement2 = psiElement.getNextSibling();
        while (true) {
            try {
                if (psiElement2 == null || !isWhitespace(psiElement2)) {
                    break;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            psiElement2 = psiElement2.getNextSibling();
        }
        return psiElement2;
    }
    
    @Nullable
    public static PsiElement getPrevSignificantSibling(final PsiElement psiElement) {
        PsiElement psiElement2 = psiElement.getPrevSibling();
        while (true) {
            try {
                if (psiElement2 == null || isElementSignificant(psiElement2)) {
                    break;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            psiElement2 = psiElement2.getPrevSibling();
        }
        return psiElement2;
    }
    
    @SafeVarargs
    @Nullable
    public static PsiElement getParentOfType(@Nullable final PsiFile psiFile, @NotNull final TextRange textRange, final Class<? extends PsiElement>... array) {
        try {
            if (textRange == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "range", "com/jetbrains/cidr/lang/util/OCElementUtil", "getParentOfType"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                return null;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        PsiElement psiElement = psiFile.findElementAt(textRange.getStartOffset());
        while (true) {
            try {
                if (psiElement == null || getRangeWithMacros(psiElement).getEndOffset() >= textRange.getEndOffset()) {
                    break;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            psiElement = psiElement.getParent();
        }
        return PsiTreeUtil.getNonStrictParentOfType(psiElement, (Class[])array);
    }
    
    public static boolean isPartOfMacroSubstitution(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "isPartOfMacroSubstitution"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement.getTextLength() == 0) {
                return true;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    public static boolean insideDirective(@Nullable final PsiElement psiElement) {
        try {
            if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCDirective.class) != null) {
                return true;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return false;
    }
    
    private static void b(@NotNull final PsiElement psiElement, @NotNull final PsiElement psiElement2) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "from", "com/jetbrains/cidr/lang/util/OCElementUtil", "replaceAST"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "to", "com/jetbrains/cidr/lang/util/OCElementUtil", "replaceAST"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final ASTNode node = psiElement.getNode();
        final ASTNode node2 = psiElement2.getNode();
        final ASTNode treeParent = node.getTreeParent();
        CodeEditUtil.saveWhitespacesInfo(node);
        CodeEditUtil.saveWhitespacesInfo(node2);
        treeParent.replaceChild(node, node2);
    }
    
    public static void replaceWithIdentifier(@Nullable final PsiElement psiElement, @NotNull final String s, @NotNull final PsiElement psiElement2) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "newName", "com/jetbrains/cidr/lang/util/OCElementUtil", "replaceWithIdentifier"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/util/OCElementUtil", "replaceWithIdentifier"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement == null) {
                return;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        final IElementType elementType = getElementType(psiElement);
        try {
            if (!OCElementUtil.GOOD_FOR_RENAME.contains(elementType)) {
                return;
            }
        }
        catch (NumberFormatException ex4) {
            throw b(ex4);
        }
        try {
            if (s.equals(psiElement.getText())) {
                return;
            }
        }
        catch (NumberFormatException ex5) {
            throw b(ex5);
        }
        PsiElement psiElement3 = null;
        Label_0206: {
            Label_0182: {
                Label_0168: {
                    try {
                        if (!((OCFile)psiElement.getContainingFile()).isCpp()) {
                            break Label_0182;
                        }
                        final String s2 = s;
                        final String s3 = "this";
                        final boolean b = s2.equals(s3);
                        if (b) {
                            break Label_0168;
                        }
                        break Label_0182;
                    }
                    catch (NumberFormatException ex6) {
                        throw b(ex6);
                    }
                    try {
                        final String s2 = s;
                        final String s3 = "this";
                        final boolean b = s2.equals(s3);
                        if (b) {
                            psiElement3 = OCElementFactory.create(OCTokenTypes.THIS_CPP_KEYWORD, psiElement2);
                            break Label_0206;
                        }
                    }
                    catch (NumberFormatException ex7) {
                        throw b(ex7);
                    }
                }
                try {
                    if (elementType == OCTokenTypes.UDL_SUFFIX) {
                        psiElement3 = OCElementFactory.createUDLSuffux(s, psiElement2);
                        break Label_0206;
                    }
                }
                catch (NumberFormatException ex8) {
                    throw b(ex8);
                }
            }
            psiElement3 = OCElementFactory.createIdentifier(s, psiElement2);
        }
        final PsiElement psiElement4 = psiElement3;
        if (psiElement instanceof OCMacroForeignLeafElement) {
            final OCMacroRange rangeInMacroCall = getRangeInMacroCall(psiElement);
            Label_0241: {
                try {
                    if (rangeInMacroCall == null) {
                        break Label_0241;
                    }
                    final OCMacroRange ocMacroRange = rangeInMacroCall;
                    final boolean b2 = ocMacroRange.mapsToArguments();
                    if (b2) {
                        break Label_0241;
                    }
                    break Label_0241;
                }
                catch (NumberFormatException ex9) {
                    throw b(ex9);
                }
                try {
                    final OCMacroRange ocMacroRange = rangeInMacroCall;
                    final boolean b2 = ocMacroRange.mapsToArguments();
                    if (b2) {
                        b(rangeInMacroCall.getFirstElement(), psiElement4);
                    }
                }
                catch (NumberFormatException ex10) {
                    throw b(ex10);
                }
            }
            b(psiElement, (PsiElement)OCElementFactory.createMacroForeignIdentifier(s, (OCMacroForeignLeafElement)psiElement));
        }
        else {
            b(psiElement, psiElement4);
        }
    }
    
    public static void changeQualifiedName(final OCNamespaceQualifierOwner ocNamespaceQualifierOwner, final OCQualifiedName ocQualifiedName) {
        if (ocQualifiedName == OCQualifiedName.GLOBAL) {
            OCChangeUtil.clear((PsiElement)ocNamespaceQualifierOwner);
            final ASTNode node = ocNamespaceQualifierOwner.getParent().getNode();
            final ASTNode childByType = node.findChildByType((IElementType)OCTokenTypes.COLON2X);
            try {
                if (childByType != null) {
                    CodeEditUtil.removeChild(node, childByType);
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            return;
        }
        final OCCppNamespaceQualifier namespaceQualifier = ocNamespaceQualifierOwner.getNamespaceQualifier();
        final ASTNode node2 = ocNamespaceQualifierOwner.getNode();
        if (ocQualifiedName.getQualifier() != null) {
            final String string = ocQualifiedName.getQualifier().toString();
            Object namespaceQualifier2 = null;
            Label_0102: {
                try {
                    if (string.isEmpty()) {
                        namespaceQualifier2 = null;
                        break Label_0102;
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                namespaceQualifier2 = OCElementFactory.createNamespaceQualifier(string, (PsiElement)ocNamespaceQualifierOwner);
            }
            final Object o = namespaceQualifier2;
            Label_0213: {
                PsiElement psiElement = null;
                Label_0173: {
                    Label_0141: {
                        Label_0120: {
                            try {
                                if (namespaceQualifier == null) {
                                    break Label_0141;
                                }
                                final Object o2 = o;
                                if (o2 != null) {
                                    break Label_0120;
                                }
                                break Label_0120;
                            }
                            catch (NumberFormatException ex3) {
                                throw b(ex3);
                            }
                            try {
                                final Object o2 = o;
                                if (o2 != null) {
                                    OCChangeUtil.replaceHandlingMacros((PsiElement)namespaceQualifier, (PsiElement)o);
                                    break Label_0213;
                                }
                            }
                            catch (NumberFormatException ex4) {
                                throw b(ex4);
                            }
                        }
                        OCChangeUtil.delete((PsiElement)namespaceQualifier);
                        break Label_0213;
                        try {
                            if (ocNamespaceQualifierOwner instanceof OCNamespaceQualifiedNameOwner) {
                                psiElement = ((OCNamespaceQualifiedNameOwner)ocNamespaceQualifierOwner).getNameIdentifier();
                                break Label_0173;
                            }
                        }
                        catch (NumberFormatException ex5) {
                            throw b(ex5);
                        }
                    }
                    psiElement = ((PsiNameIdentifierOwner)ocNamespaceQualifierOwner).getNameIdentifier();
                }
                final PsiElement psiElement2 = psiElement;
                try {
                    if (o != null) {
                        ocNamespaceQualifierOwner.addBefore((PsiElement)o, psiElement2);
                    }
                }
                catch (NumberFormatException ex6) {
                    throw b(ex6);
                }
                CodeEditUtil.addChild(node2, OCElementFactory.createColon2x((PsiElement)ocNamespaceQualifierOwner), psiElement2.getNode());
            }
        }
        else {
            try {
                if (namespaceQualifier != null) {
                    OCChangeUtil.delete((PsiElement)namespaceQualifier);
                }
            }
            catch (NumberFormatException ex7) {
                throw b(ex7);
            }
            final ASTNode childByType2 = node2.findChildByType((IElementType)OCTokenTypes.COLON2X);
            try {
                if (childByType2 != null) {
                    CodeEditUtil.removeChild(node2, childByType2);
                }
            }
            catch (NumberFormatException ex8) {
                throw b(ex8);
            }
        }
        PsiElement psiElement3 = null;
        Label_0292: {
            try {
                if (ocNamespaceQualifierOwner instanceof OCNamespaceQualifiedNameOwner) {
                    psiElement3 = ((OCNamespaceQualifiedNameOwner)ocNamespaceQualifierOwner).getNameIdentifier();
                    break Label_0292;
                }
            }
            catch (NumberFormatException ex9) {
                throw b(ex9);
            }
            psiElement3 = ((PsiNameIdentifierOwner)ocNamespaceQualifierOwner).getNameIdentifier();
        }
        replaceWithIdentifier(psiElement3, ocQualifiedName.getName(), (PsiElement)ocNamespaceQualifierOwner);
    }
    
    public static void fillChildrenRecursive(final PsiElement psiElement, final List<PsiElement> list) {
        for (PsiElement psiElement2 = psiElement.getFirstChild(); psiElement2 != null; psiElement2 = psiElement2.getNextSibling()) {
            list.add(psiElement2);
            fillChildrenRecursive(psiElement2, list);
        }
    }
    
    public static List<PsiElement> getAllChildren(final PsiElement psiElement) {
        return (List<PsiElement>)ContainerUtil.mapNotNull((Object[])psiElement.getNode().getChildren((TokenSet)null), astNode -> astNode.getPsi());
    }
    
    @Nullable
    public static OCMacroRange getRangeInMacroCall(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          37
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    10: ifnull          37
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    19: athrow         
        //    20: aload_0        
        //    21: iconst_0       
        //    22: invokeinterface com/intellij/psi/PsiElement.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //    27: ifnull          57
        //    30: goto            37
        //    33: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    36: athrow         
        //    37: aload_0        
        //    38: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    41: ifne            57
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    50: athrow         
        //    51: aconst_null    
        //    52: areturn        
        //    53: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    56: athrow         
        //    57: aload_0        
        //    58: astore_2       
        //    59: aload_2        
        //    60: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    65: astore_3       
        //    66: aload_3        
        //    67: ifnull          96
        //    70: aload_3        
        //    71: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //    74: ifeq            96
        //    77: goto            84
        //    80: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    83: athrow         
        //    84: aload_3        
        //    85: astore_2       
        //    86: aload_3        
        //    87: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    92: astore_3       
        //    93: goto            66
        //    96: aload_2        
        //    97: invokestatic    com/intellij/psi/util/PsiTreeUtil.prevLeaf:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   100: astore_2       
        //   101: aload_2        
        //   102: iconst_1       
        //   103: anewarray       Ljava/lang/Class;
        //   106: dup            
        //   107: iconst_0       
        //   108: ldc             Lcom/jetbrains/cidr/lang/psi/OCMacroCall;.class
        //   110: aastore        
        //   111: invokestatic    com/intellij/psi/util/PsiTreeUtil.getContextOfType:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   114: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   117: astore_1       
        //   118: aload_1        
        //   119: ifnull          141
        //   122: aload_1        
        //   123: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   128: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //   131: ifne            141
        //   134: goto            168
        //   137: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   140: athrow         
        //   141: aload_2        
        //   142: ifnull          166
        //   145: aload_2        
        //   146: instanceof      Lcom/intellij/psi/impl/source/tree/LeafPsiElement;
        //   149: ifeq            96
        //   152: goto            159
        //   155: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   158: athrow         
        //   159: aload_2        
        //   160: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //   163: ifne            96
        //   166: aconst_null    
        //   167: areturn        
        //   168: aload_0        
        //   169: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //   172: ifeq            193
        //   175: aload_0        
        //   176: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   181: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   186: goto            199
        //   189: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   192: athrow         
        //   193: aload_0        
        //   194: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   199: istore          4
        //   201: aload_1        
        //   202: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   207: astore          5
        //   209: iload           4
        //   211: aload           5
        //   213: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   216: if_icmplt       236
        //   219: iload           4
        //   221: aload           5
        //   223: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   226: if_icmple       242
        //   229: goto            236
        //   232: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   235: athrow         
        //   236: aconst_null    
        //   237: areturn        
        //   238: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   241: athrow         
        //   242: aload_0        
        //   243: aload_1        
        //   244: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;Lcom/jetbrains/cidr/lang/psi/OCMacroCall;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   247: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      13     16     20     Ljava/lang/NumberFormatException;
        //  4      30     33     37     Ljava/lang/NumberFormatException;
        //  20     44     47     51     Ljava/lang/NumberFormatException;
        //  37     53     53     57     Ljava/lang/NumberFormatException;
        //  66     77     80     84     Ljava/lang/NumberFormatException;
        //  122    137    137    141    Ljava/lang/NumberFormatException;
        //  141    152    155    159    Ljava/lang/NumberFormatException;
        //  168    189    189    193    Ljava/lang/NumberFormatException;
        //  209    229    232    236    Ljava/lang/NumberFormatException;
        //  219    238    238    242    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0020:
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
    
    public static OCMacroRange getRangeInMacroCall(final PsiElement p0, final OCMacroCall p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getMacroReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnull          24
        //    11: aload_2        
        //    12: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceElement.getCanonicalText:()Ljava/lang/String;
        //    17: goto            25
        //    20: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    23: athrow         
        //    24: aconst_null    
        //    25: astore_3       
        //    26: aload_0        
        //    27: invokestatic    com/intellij/psi/util/PsiTreeUtil.firstChild:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    30: astore          4
        //    32: aload_0        
        //    33: invokestatic    com/intellij/psi/util/PsiTreeUtil.lastChild:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //    36: astore          5
        //    38: aload           4
        //    40: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    43: ifeq            58
        //    46: aload           4
        //    48: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    51: goto            59
        //    54: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    57: athrow         
        //    58: aconst_null    
        //    59: astore          6
        //    61: aload           5
        //    63: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    66: ifeq            81
        //    69: aload           5
        //    71: checkcast       Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    74: goto            82
        //    77: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    80: athrow         
        //    81: aconst_null    
        //    82: astore          7
        //    84: aload           6
        //    86: ifnull          101
        //    89: aload           6
        //    91: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement.getMacroArgumentIndex:()I
        //    94: goto            102
        //    97: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   100: athrow         
        //   101: iconst_m1      
        //   102: istore          8
        //   104: aload           7
        //   106: ifnull          308
        //   109: iload           8
        //   111: iconst_m1      
        //   112: if_icmpeq       308
        //   115: goto            122
        //   118: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   121: athrow         
        //   122: iload           8
        //   124: aload_1        
        //   125: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getArguments:()Ljava/util/List;
        //   130: invokeinterface java/util/List.size:()I
        //   135: if_icmpge       308
        //   138: goto            145
        //   141: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   144: athrow         
        //   145: aload           6
        //   147: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement.getMacroName:()Ljava/lang/String;
        //   150: aload_3        
        //   151: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   154: ifeq            308
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   163: athrow         
        //   164: aload           7
        //   166: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement.getMacroName:()Ljava/lang/String;
        //   169: aload_3        
        //   170: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   173: ifeq            308
        //   176: goto            183
        //   179: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   182: athrow         
        //   183: iload           8
        //   185: aload           7
        //   187: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement.getMacroArgumentIndex:()I
        //   190: if_icmpne       308
        //   193: goto            200
        //   196: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   199: athrow         
        //   200: aload_1        
        //   201: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getArguments:()Ljava/util/List;
        //   206: iload           8
        //   208: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   213: checkcast       Lcom/jetbrains/cidr/lang/psi/OCMacroCallArgument;
        //   216: astore          9
        //   218: aload_1        
        //   219: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   222: astore          10
        //   224: aload           10
        //   226: ifnull          267
        //   229: aload           10
        //   231: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getMacroCall:()Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   234: aload_1        
        //   235: if_acmpeq       267
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   244: athrow         
        //   245: aload           10
        //   247: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.mapsToArguments:()Z
        //   250: ifeq            267
        //   253: goto            260
        //   256: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   259: athrow         
        //   260: aload           10
        //   262: areturn        
        //   263: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   266: athrow         
        //   267: new             Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   270: dup            
        //   271: aload_1        
        //   272: aload           9
        //   274: aload           6
        //   276: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement.getRangeInMacroArgument:()Lcom/intellij/openapi/util/TextRange;
        //   279: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //   282: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCallArgument.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   287: aload           9
        //   289: aload           7
        //   291: invokevirtual   com/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement.getRangeInMacroArgument:()Lcom/intellij/openapi/util/TextRange;
        //   294: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   297: iconst_1       
        //   298: isub           
        //   299: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCallArgument.findElementAt:(I)Lcom/intellij/psi/PsiElement;
        //   304: invokespecial   com/jetbrains/cidr/lang/parser/OCMacroRange.<init>:(Lcom/jetbrains/cidr/lang/psi/OCMacroCall;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)V
        //   307: areturn        
        //   308: new             Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   311: dup            
        //   312: aload_1        
        //   313: invokespecial   com/jetbrains/cidr/lang/parser/OCMacroRange.<init>:(Lcom/jetbrains/cidr/lang/psi/OCMacroCall;)V
        //   316: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  7      20     20     24     Ljava/lang/NumberFormatException;
        //  38     54     54     58     Ljava/lang/NumberFormatException;
        //  61     77     77     81     Ljava/lang/NumberFormatException;
        //  84     97     97     101    Ljava/lang/NumberFormatException;
        //  104    115    118    122    Ljava/lang/NumberFormatException;
        //  109    138    141    145    Ljava/lang/NumberFormatException;
        //  122    157    160    164    Ljava/lang/NumberFormatException;
        //  145    176    179    183    Ljava/lang/NumberFormatException;
        //  164    193    196    200    Ljava/lang/NumberFormatException;
        //  224    238    241    245    Ljava/lang/NumberFormatException;
        //  229    253    256    260    Ljava/lang/NumberFormatException;
        //  245    263    263    267    Ljava/lang/NumberFormatException;
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
    
    public static void replaceDeclarationQualifiers(final PsiElement p0, final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnull          89
        //    11: aload_2        
        //    12: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    17: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //    22: astore_3       
        //    23: aload_2        
        //    24: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    29: astore          4
        //    31: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DECLARATION_SPECIFIERS_IN_TYPES:Lcom/intellij/psi/tree/TokenSet;
        //    34: aload_3        
        //    35: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    38: ifne            72
        //    41: aload_3        
        //    42: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPEDEF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    45: if_acmpeq       72
        //    48: goto            55
        //    51: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    54: athrow         
        //    55: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AUTO_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //    58: aload_3        
        //    59: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    62: ifeq            83
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    71: athrow         
        //    72: aload_2        
        //    73: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.delete:(Lcom/intellij/psi/PsiElement;)V
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    82: athrow         
        //    83: aload           4
        //    85: astore_2       
        //    86: goto            7
        //    89: aload_0        
        //    90: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //    95: iconst_1       
        //    96: anewarray       Ljava/lang/Class;
        //    99: dup            
        //   100: iconst_0       
        //   101: ldc             Lcom/intellij/psi/PsiWhiteSpace;.class
        //   103: aastore        
        //   104: invokestatic    com/intellij/psi/util/PsiTreeUtil.skipSiblingsForward:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   107: astore_3       
        //   108: aload_1        
        //   109: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   114: astore_2       
        //   115: aload_2        
        //   116: ifnull          394
        //   119: aload_2        
        //   120: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   125: astore          4
        //   127: aload_2        
        //   128: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   133: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   138: astore          5
        //   140: aload           5
        //   142: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.TYPEDEF_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   145: if_acmpne       223
        //   148: aload_0        
        //   149: aload_2        
        //   150: aload_0        
        //   151: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   156: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   161: pop            
        //   162: aload           4
        //   164: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   167: ifeq            202
        //   170: goto            177
        //   173: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   176: athrow         
        //   177: aload_0        
        //   178: aload           4
        //   180: aload_0        
        //   181: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   186: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   191: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   194: pop            
        //   195: goto            388
        //   198: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   201: athrow         
        //   202: aload_3        
        //   203: ifnull          388
        //   206: aload_0        
        //   207: aload_1        
        //   208: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.spaceFromText:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   211: aload_3        
        //   212: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   215: pop            
        //   216: goto            388
        //   219: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   222: athrow         
        //   223: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.DECLARATION_SPECIFIERS_IN_TYPES:Lcom/intellij/psi/tree/TokenSet;
        //   226: aload           5
        //   228: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   231: ifne            252
        //   234: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.AUTO_KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   237: aload           5
        //   239: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   242: ifeq            388
        //   245: goto            252
        //   248: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   251: athrow         
        //   252: aload_0        
        //   253: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   258: invokeinterface com/intellij/lang/ASTNode.getLastChildNode:()Lcom/intellij/lang/ASTNode;
        //   263: astore          6
        //   265: aload           6
        //   267: ifnull          307
        //   270: aload           6
        //   272: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   277: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   280: ifne            307
        //   283: goto            290
        //   286: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   289: athrow         
        //   290: aload_0        
        //   291: aload_1        
        //   292: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.spaceFromText:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   295: aconst_null    
        //   296: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   299: pop            
        //   300: goto            307
        //   303: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   306: athrow         
        //   307: aload_0        
        //   308: aload_2        
        //   309: aload_3        
        //   310: invokeinterface com/intellij/psi/PsiElement.addBefore:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   315: pop            
        //   316: aload           6
        //   318: ifnonnull       344
        //   321: aload_0        
        //   322: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   327: aload_1        
        //   328: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.spaceFromText:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   331: aload_0        
        //   332: iconst_0       
        //   333: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Lcom/intellij/psi/PsiElement;
        //   336: pop            
        //   337: goto            388
        //   340: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   343: athrow         
        //   344: aload           4
        //   346: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //   349: ifeq            367
        //   352: aload_0        
        //   353: aload           4
        //   355: aload_3        
        //   356: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   359: pop            
        //   360: goto            388
        //   363: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   366: athrow         
        //   367: aload_3        
        //   368: ifnull          388
        //   371: aload_0        
        //   372: aload_1        
        //   373: invokestatic    com/jetbrains/cidr/lang/util/OCElementFactory.spaceFromText:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   376: aload_3        
        //   377: invokestatic    com/jetbrains/cidr/lang/refactoring/util/OCChangeUtil.addHandlingMacros:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //   380: pop            
        //   381: goto            388
        //   384: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   387: athrow         
        //   388: aload           4
        //   390: astore_2       
        //   391: goto            115
        //   394: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  31     48     51     55     Ljava/lang/NumberFormatException;
        //  41     65     68     72     Ljava/lang/NumberFormatException;
        //  55     76     79     83     Ljava/lang/NumberFormatException;
        //  140    170    173    177    Ljava/lang/NumberFormatException;
        //  148    198    198    202    Ljava/lang/NumberFormatException;
        //  202    219    219    223    Ljava/lang/NumberFormatException;
        //  223    245    248    252    Ljava/lang/NumberFormatException;
        //  265    283    286    290    Ljava/lang/NumberFormatException;
        //  270    300    303    307    Ljava/lang/NumberFormatException;
        //  307    340    340    344    Ljava/lang/NumberFormatException;
        //  344    363    363    367    Ljava/lang/NumberFormatException;
        //  367    381    384    388    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
    
    public static void replaceComments(final PsiElement psiElement, final PsiElement psiElement2) {
        final PsiElement firstChild = psiElement2.getFirstChild();
        PsiElement psiElement3 = null;
        PsiElement nextSibling = firstChild;
        while (true) {
            try {
                if (nextSibling == null || !OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET.contains(getElementType(nextSibling))) {
                    break;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            psiElement3 = nextSibling;
            nextSibling = nextSibling.getNextSibling();
        }
        try {
            if (psiElement3 != null) {
                psiElement.addRangeBefore(firstChild, psiElement3, psiElement.getFirstChild());
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
    }
    
    public static void restoreFunction(final OCFunctionDeclaration ocFunctionDeclaration, final OCFunctionDeclaration ocFunctionDeclaration2) {
        OCChangeUtil.replaceHandlingMacros((PsiElement)ocFunctionDeclaration2.getTypeElement(), (PsiElement)ocFunctionDeclaration.getTypeElement());
        OCChangeUtil.replaceHandlingMacros((PsiElement)ocFunctionDeclaration2.getParameterList(), (PsiElement)ocFunctionDeclaration.getParameterList());
        replaceDeclarationQualifiers((PsiElement)ocFunctionDeclaration2, (PsiElement)ocFunctionDeclaration);
        replaceDeclarationQualifiers((PsiElement)ocFunctionDeclaration2.getTypeElement(), (PsiElement)ocFunctionDeclaration.getTypeElement());
        if (ocFunctionDeclaration instanceof OCFunctionDefinition) {
            final OCConstructorInitializationList constructorInitializationList = ((OCFunctionDefinition)ocFunctionDeclaration).getConstructorInitializationList();
            Label_0087: {
                try {
                    if (constructorInitializationList == null) {
                        return;
                    }
                    final OCFunctionDeclaration ocFunctionDeclaration3 = ocFunctionDeclaration2;
                    final boolean b = ocFunctionDeclaration3 instanceof OCFunctionDefinition;
                    if (b) {
                        break Label_0087;
                    }
                    return;
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                try {
                    final OCFunctionDeclaration ocFunctionDeclaration3 = ocFunctionDeclaration2;
                    final boolean b = ocFunctionDeclaration3 instanceof OCFunctionDefinition;
                    if (b) {
                        ((OCFunctionDefinition)ocFunctionDeclaration2).setConstructorInitializationList(constructorInitializationList);
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
            }
        }
    }
    
    @Nullable
    public static VirtualFile getFilePath(@Nullable final PsiFile psiFile) {
        try {
            if (psiFile != null) {
                return psiFile.getOriginalFile().getVirtualFile();
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return null;
    }
    
    public static boolean startsWithWord(final String s, final String s2) {
        Label_0032: {
            try {
                if (!s.startsWith(s2)) {
                    return false;
                }
                final String s3 = s;
                final int n = s3.length();
                final String s4 = s2;
                final int n2 = s4.length();
                if (n == n2) {
                    return true;
                }
                break Label_0032;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final String s3 = s;
                final int n = s3.length();
                final String s4 = s2;
                final int n2 = s4.length();
                if (n == n2) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        final char char1 = s.charAt(s2.length());
        try {
            if (Character.isUpperCase(char1)) {
                return true;
            }
            final char c = char1;
            final boolean b = Character.isLetterOrDigit(c);
            if (!b) {
                return true;
            }
            return false;
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        try {
            final char c = char1;
            final boolean b = Character.isLetterOrDigit(c);
            if (!b) {
                return true;
            }
        }
        catch (NumberFormatException ex4) {
            throw b(ex4);
        }
        return false;
    }
    
    public static boolean endsWithIgnoringFirstLetterCase(final String s, final String s2) {
        try {
            if (s2.isEmpty()) {
                return true;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (s.length() < s2.length()) {
                return false;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final char lowerCase = Character.toLowerCase(s2.charAt(0));
        final char lowerCase2 = Character.toLowerCase(s.charAt(s.length() - s2.length()));
        Label_0080: {
            try {
                if (lowerCase != lowerCase2) {
                    return false;
                }
                final String s3 = s;
                final String s4 = s2;
                final int n = 1;
                final String s5 = s4.substring(n);
                final boolean b = s3.endsWith(s5);
                if (b) {
                    break Label_0080;
                }
                return false;
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            try {
                final String s3 = s;
                final String s4 = s2;
                final int n = 1;
                final String s5 = s4.substring(n);
                final boolean b = s3.endsWith(s5);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
        }
        return false;
    }
    
    public static boolean isRetainMethod(final OCCallable ocCallable) {
        OCSymbol symbol = null;
        Label_0053: {
            Label_0035: {
                try {
                    if (!(ocCallable instanceof OCMethod)) {
                        break Label_0035;
                    }
                    final OCMethod ocMethod = (OCMethod)ocCallable;
                    final OCMethod ocMethod2 = ocMethod;
                    final String s = ocMethod2.getSelector();
                    final boolean b = isRetainSelector(s);
                    if (b) {
                        return true;
                    }
                    break Label_0035;
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                try {
                    final OCMethod ocMethod = (OCMethod)ocCallable;
                    final OCMethod ocMethod2 = ocMethod;
                    final String s = ocMethod2.getSelector();
                    final boolean b = isRetainSelector(s);
                    if (b) {
                        return true;
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                try {
                    if (ocCallable != null) {
                        symbol = ocCallable.getSymbol();
                        break Label_0053;
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
            }
            symbol = null;
        }
        final OCSymbol ocSymbol = symbol;
        Label_0076: {
            try {
                if (ocSymbol == null) {
                    return false;
                }
                final OCSymbol ocSymbol2 = ocSymbol;
                final String s2 = "ns_returns_retained";
                final boolean b2 = ocSymbol2.hasAttribute(s2);
                if (b2) {
                    break Label_0076;
                }
                return false;
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
            try {
                final OCSymbol ocSymbol2 = ocSymbol;
                final String s2 = "ns_returns_retained";
                final boolean b2 = ocSymbol2.hasAttribute(s2);
                if (b2) {
                    return true;
                }
            }
            catch (NumberFormatException ex5) {
                throw b(ex5);
            }
        }
        return false;
    }
    
    public static boolean isRetainSelector(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ldc             "retain"
        //     3: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //     6: ifne            73
        //     9: aload_0        
        //    10: ldc             "alloc"
        //    12: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    15: ifne            73
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    24: athrow         
        //    25: aload_0        
        //    26: ldc             "new"
        //    28: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    31: ifne            73
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    40: athrow         
        //    41: aload_0        
        //    42: ldc             "copy"
        //    44: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.startsWithWord:(Ljava/lang/String;Ljava/lang/String;)Z
        //    47: ifne            73
        //    50: goto            57
        //    53: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    56: athrow         
        //    57: aload_0        
        //    58: ldc             "Copy"
        //    60: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    63: ifeq            81
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    72: athrow         
        //    73: iconst_1       
        //    74: goto            82
        //    77: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    80: athrow         
        //    81: iconst_0       
        //    82: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      18     21     25     Ljava/lang/NumberFormatException;
        //  9      34     37     41     Ljava/lang/NumberFormatException;
        //  25     50     53     57     Ljava/lang/NumberFormatException;
        //  41     66     69     73     Ljava/lang/NumberFormatException;
        //  57     77     77     81     Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    public static boolean isReleaseSelector(final String s) {
        Label_0025: {
            try {
                if (s.endsWith("release")) {
                    break Label_0025;
                }
                final String s2 = s;
                final String s3 = "drain";
                final boolean b = s2.equals(s3);
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final String s2 = s;
                final String s3 = "drain";
                final boolean b = s2.equals(s3);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public static boolean isRetainCall(@Nullable final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCParenthesesUtils.diveIntoParenthesesAndCasts:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;
        //     4: astore_0       
        //     5: aload_0        
        //     6: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //     9: ifeq            61
        //    12: aload_0        
        //    13: checkcast       Lcom/jetbrains/cidr/lang/psi/OCConditionalExpression;
        //    16: astore_2       
        //    17: aload_2        
        //    18: iconst_1       
        //    19: invokeinterface com/jetbrains/cidr/lang/psi/OCConditionalExpression.getPositiveExpression:(Z)Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    24: iload_1        
        //    25: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRetainCall:(Lcom/intellij/psi/PsiElement;Z)Z
        //    28: ifne            51
        //    31: aload_2        
        //    32: invokeinterface com/jetbrains/cidr/lang/psi/OCConditionalExpression.getNegativeExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    37: iload_1        
        //    38: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRetainCall:(Lcom/intellij/psi/PsiElement;Z)Z
        //    41: ifeq            59
        //    44: goto            51
        //    47: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    50: athrow         
        //    51: iconst_1       
        //    52: goto            60
        //    55: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    58: athrow         
        //    59: iconst_0       
        //    60: ireturn        
        //    61: aload_0        
        //    62: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    65: ifne            74
        //    68: iconst_0       
        //    69: ireturn        
        //    70: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    73: athrow         
        //    74: aload_0        
        //    75: checkcast       Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //    78: astore_2       
        //    79: aload_2        
        //    80: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getMessageSelector:()Ljava/lang/String;
        //    85: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRetainSelector:(Ljava/lang/String;)Z
        //    88: ifeq            97
        //    91: iconst_1       
        //    92: ireturn        
        //    93: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    96: athrow         
        //    97: aload_2        
        //    98: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getProbableResponders:()Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders;
        //   103: invokevirtual   com/jetbrains/cidr/lang/psi/OCSendMessageExpression$ProbableResponders.getKnownResponder:()Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   106: astore_3       
        //   107: aload_3        
        //   108: ifnull          129
        //   111: aload_3        
        //   112: ldc             "ns_returns_retained"
        //   114: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.hasAttribute:(Ljava/lang/String;)Z
        //   119: ifne            174
        //   122: goto            129
        //   125: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   128: athrow         
        //   129: iload_1        
        //   130: ifeq            182
        //   133: goto            140
        //   136: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   139: athrow         
        //   140: aload_0        
        //   141: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isReleaseCall:(Lcom/intellij/psi/PsiElement;)Z
        //   144: ifne            182
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   153: athrow         
        //   154: aload_2        
        //   155: invokeinterface com/jetbrains/cidr/lang/psi/OCSendMessageExpression.getReceiverExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   160: iconst_1       
        //   161: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isRetainCall:(Lcom/intellij/psi/PsiElement;Z)Z
        //   164: ifeq            182
        //   167: goto            174
        //   170: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   173: athrow         
        //   174: iconst_1       
        //   175: goto            183
        //   178: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   181: athrow         
        //   182: iconst_0       
        //   183: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  17     44     47     51     Ljava/lang/NumberFormatException;
        //  31     55     55     59     Ljava/lang/NumberFormatException;
        //  61     70     70     74     Ljava/lang/NumberFormatException;
        //  79     93     93     97     Ljava/lang/NumberFormatException;
        //  107    122    125    129    Ljava/lang/NumberFormatException;
        //  111    133    136    140    Ljava/lang/NumberFormatException;
        //  129    147    150    154    Ljava/lang/NumberFormatException;
        //  140    167    170    174    Ljava/lang/NumberFormatException;
        //  154    178    178    182    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0129:
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
    
    public static boolean isReleaseCall(@Nullable PsiElement diveIntoParenthesesAndCasts) {
        diveIntoParenthesesAndCasts = OCParenthesesUtils.diveIntoParenthesesAndCasts(diveIntoParenthesesAndCasts);
        if (diveIntoParenthesesAndCasts instanceof OCConditionalExpression) {
            final OCConditionalExpression ocConditionalExpression = (OCConditionalExpression)diveIntoParenthesesAndCasts;
            Label_0049: {
                try {
                    if (isReleaseCall((PsiElement)ocConditionalExpression.getPositiveExpression(true))) {
                        break Label_0049;
                    }
                    final OCConditionalExpression ocConditionalExpression2 = ocConditionalExpression;
                    final OCExpression ocExpression = ocConditionalExpression2.getNegativeExpression();
                    final boolean b = isReleaseCall((PsiElement)ocExpression);
                    if (b) {
                        break Label_0049;
                    }
                    return false;
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                try {
                    final OCConditionalExpression ocConditionalExpression2 = ocConditionalExpression;
                    final OCExpression ocExpression = ocConditionalExpression2.getNegativeExpression();
                    final boolean b = isReleaseCall((PsiElement)ocExpression);
                    if (b) {
                        return true;
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
            }
            return false;
        }
        try {
            if (!(diveIntoParenthesesAndCasts instanceof OCSendMessageExpression)) {
                return false;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        return isReleaseSelector(((OCSendMessageExpression)diveIntoParenthesesAndCasts).getMessageSelector());
    }
    
    private static Condition<PsiElement> a(final boolean b) {
        return (Condition<PsiElement>)(p1 -> {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_1        
            //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDefineDirective;
            //     4: ifne            73
            //     7: aload_1        
            //     8: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDirective;
            //    11: ifne            73
            //    14: goto            21
            //    17: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    20: athrow         
            //    21: iload_0        
            //    22: ifne            46
            //    25: goto            32
            //    28: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    31: athrow         
            //    32: aload_1        
            //    33: instanceof      Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
            //    36: ifne            73
            //    39: goto            46
            //    42: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    45: athrow         
            //    46: getstatic       com/jetbrains/cidr/lang/util/OCElementUtil.ELEMENT_NON_WHITESPACE_CONDITION:Lcom/intellij/openapi/util/Condition;
            //    49: aload_1        
            //    50: invokeinterface com/intellij/openapi/util/Condition.value:(Ljava/lang/Object;)Z
            //    55: ifeq            73
            //    58: goto            65
            //    61: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    64: athrow         
            //    65: iconst_1       
            //    66: goto            74
            //    69: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
            //    72: athrow         
            //    73: iconst_0       
            //    74: ireturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  0      14     17     21     Ljava/lang/NumberFormatException;
            //  7      25     28     32     Ljava/lang/NumberFormatException;
            //  21     39     42     46     Ljava/lang/NumberFormatException;
            //  32     58     61     65     Ljava/lang/NumberFormatException;
            //  46     69     69     73     Ljava/lang/NumberFormatException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0021:
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
        });
    }
    
    public static boolean isElementSignificant(final PsiElement psiElement) {
        return a(false).value((Object)psiElement);
    }
    
    public static boolean isElementEmpty(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isElementEmpty"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/intellij/psi/impl/source/tree/LeafPsiElement;
        //    48: ifeq            91
        //    51: aload_0        
        //    52: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isElementSignificant:(Lcom/intellij/psi/PsiElement;)Z
        //    55: ifeq            81
        //    58: goto            65
        //    61: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    64: athrow         
        //    65: aload_0        
        //    66: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //    71: ifne            89
        //    74: goto            81
        //    77: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    80: athrow         
        //    81: iconst_1       
        //    82: goto            90
        //    85: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    88: athrow         
        //    89: iconst_0       
        //    90: ireturn        
        //    91: aload_0        
        //    92: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getAllChildren:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //    95: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   100: astore_1       
        //   101: aload_1        
        //   102: invokeinterface java/util/Iterator.hasNext:()Z
        //   107: ifeq            147
        //   110: aload_1        
        //   111: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   116: checkcast       Lcom/intellij/psi/PsiElement;
        //   119: astore_2       
        //   120: aload_2        
        //   121: ifnull          144
        //   124: aload_2        
        //   125: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isElementSignificant:(Lcom/intellij/psi/PsiElement;)Z
        //   128: ifeq            144
        //   131: goto            138
        //   134: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   137: athrow         
        //   138: iconst_0       
        //   139: ireturn        
        //   140: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   143: athrow         
        //   144: goto            101
        //   147: iconst_1       
        //   148: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     58     61     65     Ljava/lang/NumberFormatException;
        //  51     74     77     81     Ljava/lang/NumberFormatException;
        //  65     85     85     89     Ljava/lang/NumberFormatException;
        //  120    131    134    138    Ljava/lang/NumberFormatException;
        //  124    140    140    144    Ljava/lang/NumberFormatException;
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
    
    public static boolean isWhitespace(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "isWhitespace"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        Label_0080: {
            Label_0070: {
                try {
                    if (!(psiElement instanceof LeafPsiElement)) {
                        break Label_0080;
                    }
                    final Condition<PsiElement> condition = OCElementUtil.ELEMENT_NON_WHITESPACE_CONDITION;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = condition.value((Object)psiElement2);
                    if (!b) {
                        break Label_0070;
                    }
                    return false;
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                try {
                    final Condition<PsiElement> condition = OCElementUtil.ELEMENT_NON_WHITESPACE_CONDITION;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = condition.value((Object)psiElement2);
                    if (!b) {
                        return true;
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
            }
            return false;
        }
        for (final PsiElement psiElement3 : getAllChildren(psiElement)) {
            try {
                if (psiElement3 == null) {
                    continue;
                }
                final Condition<PsiElement> condition2 = OCElementUtil.ELEMENT_NON_WHITESPACE_CONDITION;
                final PsiElement psiElement4 = psiElement3;
                final boolean b3 = condition2.value((Object)psiElement4);
                if (b3) {
                    return false;
                }
                continue;
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
            try {
                final Condition<PsiElement> condition2 = OCElementUtil.ELEMENT_NON_WHITESPACE_CONDITION;
                final PsiElement psiElement4 = psiElement3;
                final boolean b3 = condition2.value((Object)psiElement4);
                if (b3) {
                    return false;
                }
                continue;
            }
            catch (NumberFormatException ex5) {
                throw b(ex5);
            }
        }
        return true;
    }
    
    public static boolean areElementsEquivalent(@NotNull final PsiElement psiElement, @NotNull final PsiElement psiElement2, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e1", "com/jetbrains/cidr/lang/util/OCElementUtil", "areElementsEquivalent"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "e2", "com/jetbrains/cidr/lang/util/OCElementUtil", "areElementsEquivalent"));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return areElementsEquivalent(psiElement, psiElement2, b, new OCResolveContext(psiElement));
    }
    
    public static boolean areElementsEquivalent(@NotNull final PsiElement p0, @NotNull final PsiElement p1, final boolean p2, @NotNull final OCResolveContext p3) {
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
        //    18: ldc             "e1"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "areElementsEquivalent"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
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
        //    62: ldc             "e2"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "areElementsEquivalent"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "context"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "areElementsEquivalent"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   131: athrow         
        //   132: iload_2        
        //   133: ifne            283
        //   136: aload_0        
        //   137: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   140: ifeq            283
        //   143: goto            150
        //   146: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   149: athrow         
        //   150: aload_1        
        //   151: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   154: ifne            170
        //   157: goto            164
        //   160: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   163: athrow         
        //   164: iconst_0       
        //   165: ireturn        
        //   166: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   169: athrow         
        //   170: aload_0        
        //   171: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   174: astore          4
        //   176: aload_1        
        //   177: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   180: astore          5
        //   182: aload           4
        //   184: ifnull          199
        //   187: aload           4
        //   189: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getMacroCall:()Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   192: goto            200
        //   195: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   198: athrow         
        //   199: aconst_null    
        //   200: astore          6
        //   202: aload           5
        //   204: ifnull          219
        //   207: aload           5
        //   209: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getMacroCall:()Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   212: goto            220
        //   215: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   218: athrow         
        //   219: aconst_null    
        //   220: astore          7
        //   222: aload           6
        //   224: ifnonnull       235
        //   227: iconst_1       
        //   228: goto            236
        //   231: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   234: athrow         
        //   235: iconst_0       
        //   236: aload           7
        //   238: ifnonnull       249
        //   241: iconst_1       
        //   242: goto            250
        //   245: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   248: athrow         
        //   249: iconst_0       
        //   250: ixor           
        //   251: ifne            277
        //   254: aload           6
        //   256: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   259: aload           7
        //   261: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextWithMacros:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   264: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   267: ifne            283
        //   270: goto            277
        //   273: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   276: athrow         
        //   277: iconst_0       
        //   278: ireturn        
        //   279: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   282: athrow         
        //   283: aload_0        
        //   284: instanceof      Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   287: ifeq            348
        //   290: aload_0        
        //   291: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   294: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.isEmpty:()Z
        //   299: ifeq            348
        //   302: goto            309
        //   305: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   308: athrow         
        //   309: aload_1        
        //   310: instanceof      Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   313: ifeq            348
        //   316: goto            323
        //   319: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   322: athrow         
        //   323: aload_1        
        //   324: checkcast       Lcom/jetbrains/cidr/lang/psi/OCElement;
        //   327: invokeinterface com/jetbrains/cidr/lang/psi/OCElement.isEmpty:()Z
        //   332: ifeq            348
        //   335: goto            342
        //   338: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   341: athrow         
        //   342: iconst_1       
        //   343: ireturn        
        //   344: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   347: athrow         
        //   348: aload_0        
        //   349: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   354: ifne            365
        //   357: iconst_1       
        //   358: goto            366
        //   361: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   364: athrow         
        //   365: iconst_0       
        //   366: aload_1        
        //   367: invokeinterface com/intellij/psi/PsiElement.getTextLength:()I
        //   372: ifne            383
        //   375: iconst_1       
        //   376: goto            384
        //   379: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   382: athrow         
        //   383: iconst_0       
        //   384: if_icmpeq       393
        //   387: iconst_0       
        //   388: ireturn        
        //   389: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   392: athrow         
        //   393: aload_0        
        //   394: aload_1        
        //   395: aload_3        
        //   396: invokedynamic   compare:(Lcom/jetbrains/cidr/lang/symbols/OCResolveContext;)Ljava/util/Comparator;
        //   401: invokedynamic   compare:()Ljava/util/Comparator;
        //   406: iload_2        
        //   407: ifne            418
        //   410: iconst_1       
        //   411: goto            419
        //   414: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   417: athrow         
        //   418: iconst_0       
        //   419: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Z)Lcom/intellij/openapi/util/Condition;
        //   422: iconst_0       
        //   423: invokestatic    com/intellij/codeInsight/PsiEquivalenceUtil.areElementsEquivalent:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Ljava/util/Comparator;Ljava/util/Comparator;Lcom/intellij/openapi/util/Condition;Z)Z
        //   426: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     84     84     88     Ljava/lang/NumberFormatException;
        //  88     128    128    132    Ljava/lang/NumberFormatException;
        //  132    143    146    150    Ljava/lang/NumberFormatException;
        //  136    157    160    164    Ljava/lang/NumberFormatException;
        //  150    166    166    170    Ljava/lang/NumberFormatException;
        //  182    195    195    199    Ljava/lang/NumberFormatException;
        //  202    215    215    219    Ljava/lang/NumberFormatException;
        //  222    231    231    235    Ljava/lang/NumberFormatException;
        //  236    245    245    249    Ljava/lang/NumberFormatException;
        //  250    270    273    277    Ljava/lang/NumberFormatException;
        //  254    279    279    283    Ljava/lang/NumberFormatException;
        //  283    302    305    309    Ljava/lang/NumberFormatException;
        //  290    316    319    323    Ljava/lang/NumberFormatException;
        //  309    335    338    342    Ljava/lang/NumberFormatException;
        //  323    344    344    348    Ljava/lang/NumberFormatException;
        //  348    361    361    365    Ljava/lang/NumberFormatException;
        //  366    379    379    383    Ljava/lang/NumberFormatException;
        //  384    389    389    393    Ljava/lang/NumberFormatException;
        //  393    414    414    418    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0150:
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
    public static Pair<PsiElement, PsiElement> getElementsDiff(@NotNull final PsiElement p0, @NotNull final PsiElement p1) {
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
        //    18: ldc             "element1"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getElementsDiff"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
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
        //    62: ldc             "element2"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "getElementsDiff"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    87: athrow         
        //    88: aload_0        
        //    89: aload_1        
        //    90: if_acmpne       99
        //    93: aconst_null    
        //    94: areturn        
        //    95: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    98: athrow         
        //    99: aload_0        
        //   100: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   105: astore_2       
        //   106: aload_1        
        //   107: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   112: astore_3       
        //   113: aload_0        
        //   114: aload_1        
        //   115: invokestatic    com/intellij/openapi/util/Pair.create:(Ljava/lang/Object;Ljava/lang/Object;)Lcom/intellij/openapi/util/Pair;
        //   118: astore          4
        //   120: aload_2        
        //   121: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   126: aload_3        
        //   127: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   132: if_acmpeq       142
        //   135: aload           4
        //   137: areturn        
        //   138: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   141: athrow         
        //   142: aload_0        
        //   143: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   146: ifne            163
        //   149: aload_1        
        //   150: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   153: ifeq            190
        //   156: goto            163
        //   159: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   162: athrow         
        //   163: aload_0        
        //   164: aload_1        
        //   165: iconst_0       
        //   166: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.areElementsEquivalent:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;Z)Z
        //   169: ifeq            187
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   178: athrow         
        //   179: aconst_null    
        //   180: goto            189
        //   183: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   186: athrow         
        //   187: aload           4
        //   189: areturn        
        //   190: aload_0        
        //   191: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getAllChildren:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   194: iconst_0       
        //   195: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Z)Lcom/intellij/openapi/util/Condition;
        //   198: invokestatic    com/intellij/util/containers/ContainerUtil.filter:(Ljava/util/Collection;Lcom/intellij/openapi/util/Condition;)Ljava/util/List;
        //   201: astore          5
        //   203: aload_1        
        //   204: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getAllChildren:(Lcom/intellij/psi/PsiElement;)Ljava/util/List;
        //   207: iconst_0       
        //   208: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Z)Lcom/intellij/openapi/util/Condition;
        //   211: invokestatic    com/intellij/util/containers/ContainerUtil.filter:(Ljava/util/Collection;Lcom/intellij/openapi/util/Condition;)Ljava/util/List;
        //   214: astore          6
        //   216: aload           5
        //   218: invokeinterface java/util/List.size:()I
        //   223: aload           6
        //   225: invokeinterface java/util/List.size:()I
        //   230: if_icmpeq       240
        //   233: aload           4
        //   235: areturn        
        //   236: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   239: athrow         
        //   240: aconst_null    
        //   241: astore          7
        //   243: iconst_0       
        //   244: istore          8
        //   246: iload           8
        //   248: aload           5
        //   250: invokeinterface java/util/List.size:()I
        //   255: if_icmpge       328
        //   258: aload           5
        //   260: iload           8
        //   262: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   267: checkcast       Lcom/intellij/psi/PsiElement;
        //   270: astore          9
        //   272: aload           6
        //   274: iload           8
        //   276: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   281: checkcast       Lcom/intellij/psi/PsiElement;
        //   284: astore          10
        //   286: aload           9
        //   288: aload           10
        //   290: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementsDiff:(Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/Pair;
        //   293: astore          11
        //   295: aload           11
        //   297: ifnull          322
        //   300: aload           7
        //   302: ifnonnull       319
        //   305: goto            312
        //   308: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   311: athrow         
        //   312: aload           11
        //   314: astore          7
        //   316: goto            322
        //   319: aload           4
        //   321: areturn        
        //   322: iinc            8, 1
        //   325: goto            246
        //   328: aload           5
        //   330: invokeinterface java/util/List.size:()I
        //   335: ifne            362
        //   338: aload_0        
        //   339: aload_1        
        //   340: invokeinterface com/intellij/psi/PsiElement.textMatches:(Lcom/intellij/psi/PsiElement;)Z
        //   345: ifne            362
        //   348: goto            355
        //   351: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   354: athrow         
        //   355: aload           4
        //   357: areturn        
        //   358: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   361: athrow         
        //   362: aload           7
        //   364: areturn        
        //    Signature:
        //  (Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/Pair<Lcom/intellij/psi/PsiElement;Lcom/intellij/psi/PsiElement;>;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     84     84     88     Ljava/lang/NumberFormatException;
        //  88     95     95     99     Ljava/lang/NumberFormatException;
        //  120    138    138    142    Ljava/lang/NumberFormatException;
        //  142    156    159    163    Ljava/lang/NumberFormatException;
        //  149    172    175    179    Ljava/lang/NumberFormatException;
        //  163    183    183    187    Ljava/lang/NumberFormatException;
        //  216    236    236    240    Ljava/lang/NumberFormatException;
        //  295    305    308    312    Ljava/lang/NumberFormatException;
        //  328    348    351    355    Ljava/lang/NumberFormatException;
        //  338    358    358    362    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0163:
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
    public static PsiElement getPreviousNonEmptyElement(final PsiElement psiElement) {
        ASTNode astNode = psiElement.getNode();
        PsiElement psi = astNode.getPsi();
        while (true) {
            try {
                if (psi == null || !isElementEmpty(psi)) {
                    break;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            astNode = astNode.getTreePrev();
            PsiElement psi2 = null;
            Label_0057: {
                try {
                    if (astNode != null) {
                        psi2 = astNode.getPsi();
                        break Label_0057;
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                psi2 = null;
            }
            psi = psi2;
        }
        return psi;
    }
    
    @Nullable
    public static PsiElement getNextNonEmptyElement(final PsiElement psiElement) {
        ASTNode astNode = psiElement.getNode();
        PsiElement psiElement2 = astNode.getPsi();
        while (true) {
            try {
                if (psiElement2 == null || !isElementEmpty(psiElement2)) {
                    break;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            astNode = astNode.getTreeNext();
            psiElement2 = astNode.getPsi();
        }
        return psiElement2;
    }
    
    @Nullable
    public static <T extends PsiElement> T getAdjacentParentOfType(@Nullable final PsiElement p0, @NotNull final Class<? extends T> p1) {
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
        //    18: ldc             "aClass"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getAdjacentParentOfType"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_0        
        //    45: ifnonnull       54
        //    48: aconst_null    
        //    49: areturn        
        //    50: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    53: athrow         
        //    54: aload_0        
        //    55: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    60: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    63: istore_2       
        //    64: aload_0        
        //    65: ifnull          164
        //    68: aload_1        
        //    69: aload_0        
        //    70: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //    73: ifeq            89
        //    76: goto            83
        //    79: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    82: athrow         
        //    83: aload_0        
        //    84: areturn        
        //    85: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    88: athrow         
        //    89: aload_0        
        //    90: instanceof      Lcom/intellij/psi/PsiFile;
        //    93: ifeq            102
        //    96: aconst_null    
        //    97: areturn        
        //    98: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   101: athrow         
        //   102: aload_0        
        //   103: invokeinterface com/intellij/psi/PsiElement.getPrevSibling:()Lcom/intellij/psi/PsiElement;
        //   108: astore_3       
        //   109: aload_3        
        //   110: ifnull          154
        //   113: aload_1        
        //   114: aload_3        
        //   115: invokevirtual   java/lang/Class.isInstance:(Ljava/lang/Object;)Z
        //   118: ifeq            154
        //   121: goto            128
        //   124: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   127: athrow         
        //   128: aload_3        
        //   129: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   134: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //   137: iload_2        
        //   138: if_icmpne       154
        //   141: goto            148
        //   144: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   147: athrow         
        //   148: aload_3        
        //   149: areturn        
        //   150: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   153: athrow         
        //   154: aload_0        
        //   155: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //   160: astore_0       
        //   161: goto            64
        //   164: aconst_null    
        //   165: areturn        
        //    Signature:
        //  <T:Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/PsiElement;Ljava/lang/Class<+TT;>;)TT; [from metadata: <T::Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/PsiElement;Ljava/lang/Class<+TT;>;)TT;]
        //  
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     50     50     54     Ljava/lang/NumberFormatException;
        //  64     76     79     83     Ljava/lang/NumberFormatException;
        //  68     85     85     89     Ljava/lang/NumberFormatException;
        //  89     98     98     102    Ljava/lang/NumberFormatException;
        //  109    121    124    128    Ljava/lang/NumberFormatException;
        //  113    141    144    148    Ljava/lang/NumberFormatException;
        //  128    150    150    154    Ljava/lang/NumberFormatException;
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
    
    @SafeVarargs
    @Nullable
    public static <T extends PsiElement> T getAdjacentParentOfType(@Nullable final PsiElement psiElement, @NotNull final Class<? extends T>... array) {
        try {
            if (array == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "classes", "com/jetbrains/cidr/lang/util/OCElementUtil", "getAdjacentParentOfType"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        final PsiElement nonStrictParentOfType = PsiTreeUtil.getNonStrictParentOfType(psiElement, (Class[])array);
        try {
            if (nonStrictParentOfType != null) {
                return (T)nonStrictParentOfType;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        return (T)PsiTreeUtil.getNonStrictParentOfType(PsiTreeUtil.prevLeaf(psiElement), (Class[])array);
    }
    
    public static TextRange getTrimmedRange(final PsiElement psiElement) {
        return getTrimmedRange(psiElement.getTextRange(), psiElement.getContainingFile());
    }
    
    public static TextRange getTrimmedRange(final TextRange textRange, final PsiFile psiFile) {
        final String substring = psiFile.getText().substring(textRange.getStartOffset(), textRange.getEndOffset());
        Label_0049: {
            try {
                if (substring.length() == 0) {
                    return textRange;
                }
                final String s = substring;
                final int n = s.length();
                final TextRange textRange2 = textRange;
                final int n2 = textRange2.getLength();
                if (n != n2) {
                    return textRange;
                }
                break Label_0049;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final String s = substring;
                final int n = s.length();
                final TextRange textRange2 = textRange;
                final int n2 = textRange2.getLength();
                if (n != n2) {
                    return textRange;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        int n3 = 0;
        int n4 = substring.length() - 1;
    Label_0092:
        while (true) {
            while (true) {
                Label_0082: {
                    try {
                        if (n3 > n4) {
                            break Label_0092;
                        }
                        final String s2 = substring;
                        final int n5 = n3;
                        final char c = s2.charAt(n5);
                        final char c2 = ' ';
                        if (c <= c2) {
                            break Label_0082;
                        }
                        break Label_0092;
                    }
                    catch (NumberFormatException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final String s2 = substring;
                        final int n5 = n3;
                        final char c = s2.charAt(n5);
                        final char c2 = ' ';
                        if (c <= c2) {
                            ++n3;
                            continue;
                        }
                    }
                    catch (NumberFormatException ex4) {
                        throw b(ex4);
                    }
                }
                break;
            }
            Label_0116: {
                try {
                    if (n3 > n4) {
                        return new TextRange(textRange.getStartOffset() + n3, textRange.getEndOffset() + n4 - substring.length() + 1);
                    }
                    final String s3 = substring;
                    final int n6 = n4;
                    final char c3 = s3.charAt(n6);
                    final char c4 = ' ';
                    if (c3 <= c4) {
                        break Label_0116;
                    }
                    return new TextRange(textRange.getStartOffset() + n3, textRange.getEndOffset() + n4 - substring.length() + 1);
                }
                catch (NumberFormatException ex5) {
                    throw b(ex5);
                }
                try {
                    final String s3 = substring;
                    final int n6 = n4;
                    final char c3 = s3.charAt(n6);
                    final char c4 = ' ';
                    if (c3 <= c4) {
                        --n4;
                        continue Label_0092;
                    }
                }
                catch (NumberFormatException ex6) {
                    throw b(ex6);
                }
            }
            break;
        }
        return new TextRange(textRange.getStartOffset() + n3, textRange.getEndOffset() + n4 - substring.length() + 1);
    }
    
    public static TextRange getRangeInParent(final ASTNode astNode) {
        return getRangeInParent(astNode.getPsi());
    }
    
    public static TextRange getRangeInParent(final PsiElement psiElement) {
        final TextRange textRange = psiElement.getTextRange();
        final int textOffset = psiElement.getParent().getTextOffset();
        try {
            if (textRange.isEmpty()) {
                return textRange;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return textRange.shiftRight(-textOffset);
    }
    
    private static boolean a(@Nullable final PsiElement psiElement, final PsiElement psiElement2) {
        try {
            if (!(psiElement instanceof OCMacroCall)) {
                return false;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final PsiElement firstExpansionLeaf = ((OCMacroCall)psiElement).getFirstExpansionLeaf();
        Label_0043: {
            try {
                if (firstExpansionLeaf == null) {
                    return false;
                }
                final PsiElement psiElement3 = psiElement2;
                final PsiElement psiElement4 = firstExpansionLeaf;
                final boolean b = false;
                final boolean b2 = PsiTreeUtil.isAncestor(psiElement3, psiElement4, b);
                if (b2) {
                    break Label_0043;
                }
                return false;
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            try {
                final PsiElement psiElement3 = psiElement2;
                final PsiElement psiElement4 = firstExpansionLeaf;
                final boolean b = false;
                final boolean b2 = PsiTreeUtil.isAncestor(psiElement3, psiElement4, b);
                if (b2) {
                    return true;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
        }
        return false;
    }
    
    @NotNull
    public static String getTextWithMacros(@Nullable final PsiElement psiElement) {
        Label_0052: {
            String s = null;
            Label_0017: {
                try {
                    if (psiElement != null) {
                        break Label_0052;
                    }
                    s = "";
                    if (s == null) {
                        break Label_0017;
                    }
                    return s;
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                try {
                    s = "";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTextWithMacros"));
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
            }
            return s;
        }
        final OCMacroRange rangeInMacroCall = getRangeInMacroCall(psiElement);
        if (rangeInMacroCall != null) {
            final TextRange argumentRange = rangeInMacroCall.getArgumentRange();
            final String textWithMacros = rangeInMacroCall.getMacroCall().getTextWithMacros();
            Label_0270: {
                String s2 = null;
                Label_0235: {
                    try {
                        if (argumentRange != null) {
                            break Label_0270;
                        }
                        s2 = textWithMacros;
                        if (s2 == null) {
                            break Label_0235;
                        }
                        return s2;
                    }
                    catch (NumberFormatException ex3) {
                        throw b(ex3);
                    }
                    try {
                        s2 = textWithMacros;
                        if (s2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTextWithMacros"));
                        }
                    }
                    catch (NumberFormatException ex4) {
                        throw b(ex4);
                    }
                }
                return s2;
            }
            final TextRange shiftRight = argumentRange.shiftRight(-rangeInMacroCall.getMacroCall().getTextRange().getStartOffset());
            String substring;
            try {
                substring = textWithMacros.substring(shiftRight.getStartOffset(), shiftRight.getEndOffset());
                if (substring == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTextWithMacros"));
                }
            }
            catch (NumberFormatException ex5) {
                throw b(ex5);
            }
            return substring;
        }
        PsiElement psiElement2 = getPrevSiblingOrParentSibling(psiElement);
        if (a(psiElement2, psiElement)) {
            final StringBuilder sb = new StringBuilder(psiElement.getText());
            while (a(psiElement2, psiElement)) {
                sb.insert(0, psiElement2.getText());
                psiElement2 = getPrevSiblingOrParentSibling(psiElement2);
            }
            String string;
            try {
                string = sb.toString();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTextWithMacros"));
                }
            }
            catch (NumberFormatException ex6) {
                throw b(ex6);
            }
            return string;
        }
        String text;
        try {
            text = psiElement.getText();
            if (text == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTextWithMacros"));
            }
        }
        catch (NumberFormatException ex7) {
            throw b(ex7);
        }
        return text;
    }
    
    public static String getTextFromLeaves(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTextFromLeaves"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        PsiElement psiElement2 = PsiTreeUtil.firstChild(psiElement);
        final PsiElement lastChild = PsiTreeUtil.lastChild(psiElement);
        final StringBuilder sb = new StringBuilder();
        PsiElement psiElement3;
        do {
            psiElement3 = psiElement2;
            psiElement2 = PsiTreeUtil.nextLeaf(psiElement3);
            sb.append(psiElement3.getText());
        } while (psiElement3 != lastChild && psiElement2 != null);
        return sb.toString();
    }
    
    @NotNull
    public static TextRange getRangeWithMacros(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //     4: astore_1       
        //     5: aload_1        
        //     6: ifnonnull       148
        //     9: aload_0        
        //    10: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //    13: astore_2       
        //    14: aload_0        
        //    15: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    20: astore_3       
        //    21: aload_2        
        //    22: ifnull          108
        //    25: aload_2        
        //    26: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getTextOffset:()I
        //    31: aload_3        
        //    32: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    35: if_icmpgt       108
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    44: athrow         
        //    45: new             Lcom/intellij/openapi/util/TextRange;
        //    48: dup            
        //    49: aload_2        
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getTextOffset:()I
        //    55: aload_3        
        //    56: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    59: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //    62: dup            
        //    63: ifnonnull       107
        //    66: goto            73
        //    69: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    72: athrow         
        //    73: new             Ljava/lang/IllegalStateException;
        //    76: dup            
        //    77: ldc             "@NotNull method %s.%s must not return null"
        //    79: ldc             2
        //    81: anewarray       Ljava/lang/Object;
        //    84: dup            
        //    85: ldc             0
        //    87: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    89: aastore        
        //    90: dup            
        //    91: ldc             1
        //    93: ldc             "getRangeWithMacros"
        //    95: aastore        
        //    96: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    99: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   102: athrow         
        //   103: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   106: athrow         
        //   107: areturn        
        //   108: aload_3        
        //   109: dup            
        //   110: ifnonnull       147
        //   113: new             Ljava/lang/IllegalStateException;
        //   116: dup            
        //   117: ldc             "@NotNull method %s.%s must not return null"
        //   119: ldc             2
        //   121: anewarray       Ljava/lang/Object;
        //   124: dup            
        //   125: ldc             0
        //   127: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //   129: aastore        
        //   130: dup            
        //   131: ldc             1
        //   133: ldc             "getRangeWithMacros"
        //   135: aastore        
        //   136: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   139: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   142: athrow         
        //   143: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   146: athrow         
        //   147: areturn        
        //   148: aload_1        
        //   149: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getMacroCall:()Lcom/jetbrains/cidr/lang/psi/OCMacroCall;
        //   152: invokeinterface com/jetbrains/cidr/lang/psi/OCMacroCall.getRangeWithMacros:()Lcom/intellij/openapi/util/TextRange;
        //   157: astore_2       
        //   158: aload_1        
        //   159: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getArgumentRange:()Lcom/intellij/openapi/util/TextRange;
        //   162: astore_3       
        //   163: aload_3        
        //   164: ifnull          214
        //   167: aload_3        
        //   168: dup            
        //   169: ifnonnull       213
        //   172: goto            179
        //   175: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   178: athrow         
        //   179: new             Ljava/lang/IllegalStateException;
        //   182: dup            
        //   183: ldc             "@NotNull method %s.%s must not return null"
        //   185: ldc             2
        //   187: anewarray       Ljava/lang/Object;
        //   190: dup            
        //   191: ldc             0
        //   193: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //   195: aastore        
        //   196: dup            
        //   197: ldc             1
        //   199: ldc             "getRangeWithMacros"
        //   201: aastore        
        //   202: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   205: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   208: athrow         
        //   209: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   212: athrow         
        //   213: areturn        
        //   214: aload_2        
        //   215: dup            
        //   216: ifnonnull       253
        //   219: new             Ljava/lang/IllegalStateException;
        //   222: dup            
        //   223: ldc             "@NotNull method %s.%s must not return null"
        //   225: ldc             2
        //   227: anewarray       Ljava/lang/Object;
        //   230: dup            
        //   231: ldc             0
        //   233: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //   235: aastore        
        //   236: dup            
        //   237: ldc             1
        //   239: ldc             "getRangeWithMacros"
        //   241: aastore        
        //   242: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   245: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   248: athrow         
        //   249: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   252: athrow         
        //   253: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  21     38     41     45     Ljava/lang/NumberFormatException;
        //  25     66     69     73     Ljava/lang/NumberFormatException;
        //  45     103    103    107    Ljava/lang/NumberFormatException;
        //  108    143    143    147    Ljava/lang/NumberFormatException;
        //  163    172    175    179    Ljava/lang/NumberFormatException;
        //  167    209    209    213    Ljava/lang/NumberFormatException;
        //  214    249    249    253    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
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
    
    public static boolean isEqualWithMacros(@Nullable final PsiElement p0, @Nullable final PsiElement p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          36
        //     4: aload_1        
        //     5: ifnull          36
        //     8: goto            15
        //    11: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    14: athrow         
        //    15: aload_0        
        //    16: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    19: aload_1        
        //    20: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    23: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    26: ifne            42
        //    29: goto            36
        //    32: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    35: athrow         
        //    36: iconst_0       
        //    37: ireturn        
        //    38: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    41: athrow         
        //    42: aload_0        
        //    43: aload_1        
        //    44: invokestatic    com/intellij/openapi/util/Comparing.equal:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //    47: ifeq            56
        //    50: iconst_1       
        //    51: ireturn        
        //    52: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    55: athrow         
        //    56: aload_0        
        //    57: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //    60: astore_2       
        //    61: aload_2        
        //    62: ifnull          97
        //    65: aload_2        
        //    66: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.mapsToArguments:()Z
        //    69: ifeq            97
        //    72: goto            79
        //    75: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    78: athrow         
        //    79: aload_2        
        //    80: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    83: aload_1        
        //    84: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    89: invokevirtual   com/intellij/openapi/util/TextRange.equals:(Ljava/lang/Object;)Z
        //    92: ireturn        
        //    93: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    96: athrow         
        //    97: aload_1        
        //    98: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //   101: astore_3       
        //   102: aload_3        
        //   103: ifnull          138
        //   106: aload_3        
        //   107: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.mapsToArguments:()Z
        //   110: ifeq            138
        //   113: goto            120
        //   116: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   119: athrow         
        //   120: aload_3        
        //   121: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   124: aload_0        
        //   125: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   130: invokevirtual   com/intellij/openapi/util/TextRange.equals:(Ljava/lang/Object;)Z
        //   133: ireturn        
        //   134: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   137: athrow         
        //   138: iconst_0       
        //   139: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      8      11     15     Ljava/lang/NumberFormatException;
        //  4      29     32     36     Ljava/lang/NumberFormatException;
        //  15     38     38     42     Ljava/lang/NumberFormatException;
        //  42     52     52     56     Ljava/lang/NumberFormatException;
        //  61     72     75     79     Ljava/lang/NumberFormatException;
        //  65     93     93     97     Ljava/lang/NumberFormatException;
        //  102    113    116    120    Ljava/lang/NumberFormatException;
        //  106    134    134    138    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0015:
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
    
    public static TextRange getTextRangeWithoutComments(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/intellij/psi/PsiElement.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //     6: astore_1       
        //     7: aload_1        
        //     8: instanceof      Lcom/intellij/psi/PsiComment;
        //    11: ifeq            86
        //    14: aload_1        
        //    15: instanceof      Lcom/intellij/psi/PsiComment;
        //    18: ifne            42
        //    21: goto            28
        //    24: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    27: athrow         
        //    28: aload_1        
        //    29: instanceof      Lcom/intellij/psi/PsiWhiteSpace;
        //    32: ifeq            52
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    41: athrow         
        //    42: aload_1        
        //    43: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //    48: astore_1       
        //    49: goto            14
        //    52: aload_1        
        //    53: ifnull          86
        //    56: new             Lcom/intellij/openapi/util/TextRange;
        //    59: dup            
        //    60: aload_1        
        //    61: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    66: invokevirtual   com/intellij/openapi/util/TextRange.getStartOffset:()I
        //    69: aload_0        
        //    70: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    75: invokevirtual   com/intellij/openapi/util/TextRange.getEndOffset:()I
        //    78: invokespecial   com/intellij/openapi/util/TextRange.<init>:(II)V
        //    81: areturn        
        //    82: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    85: athrow         
        //    86: aload_0        
        //    87: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    92: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  7      21     24     28     Ljava/lang/NumberFormatException;
        //  14     35     38     42     Ljava/lang/NumberFormatException;
        //  52     82     82     86     Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0014:
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
    
    public static boolean isVisibilityKeyword(@Nullable final ASTNode astNode) {
        Label_0021: {
            try {
                if (isOCVisibilityKeyword(astNode)) {
                    break Label_0021;
                }
                final ASTNode astNode2 = astNode;
                final boolean b = isCPPVisibilityKeyword(astNode2);
                if (b) {
                    break Label_0021;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final ASTNode astNode2 = astNode;
                final boolean b = isCPPVisibilityKeyword(astNode2);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Contract("null->false")
    public static boolean isOCVisibilityKeyword(@Nullable final ASTNode astNode) {
        if (astNode != null) {
            final IElementType elementType = astNode.getElementType();
            if (elementType == OCElementTypes.OBJC_KEYWORD) {
                final IElementType objCKeywordElementType = getObjCKeywordElementType(astNode);
                Label_0050: {
                    try {
                        if (OCTokenTypes.IVAR_VISIBILITY_KEYWORDS.contains(objCKeywordElementType)) {
                            break Label_0050;
                        }
                        final TokenSet set = OCTokenTypes.PROTOCOL_METHODS_KEYWORDS;
                        final IElementType elementType2 = objCKeywordElementType;
                        final boolean b = set.contains(elementType2);
                        if (b) {
                            break Label_0050;
                        }
                        return false;
                    }
                    catch (NumberFormatException ex) {
                        throw b(ex);
                    }
                    try {
                        final TokenSet set = OCTokenTypes.PROTOCOL_METHODS_KEYWORDS;
                        final IElementType elementType2 = objCKeywordElementType;
                        final boolean b = set.contains(elementType2);
                        if (b) {
                            return true;
                        }
                    }
                    catch (NumberFormatException ex2) {
                        throw b(ex2);
                    }
                }
                return false;
            }
            Label_0089: {
                try {
                    if (elementType != OCElementTypes.OBJC_ERROR_KEYWORD) {
                        return false;
                    }
                    final ASTNode astNode2 = astNode;
                    final ASTNode astNode3 = astNode2.getTreeParent();
                    final IElementType elementType3 = getElementType(astNode3);
                    final OCElementType ocElementType = OCElementTypes.INSTANCE_VARIABLES_LIST;
                    if (elementType3 == ocElementType) {
                        break Label_0089;
                    }
                    return false;
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                try {
                    final ASTNode astNode2 = astNode;
                    final ASTNode astNode3 = astNode2.getTreeParent();
                    final IElementType elementType3 = getElementType(astNode3);
                    final OCElementType ocElementType = OCElementTypes.INSTANCE_VARIABLES_LIST;
                    if (elementType3 == ocElementType) {
                        return true;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
            return false;
        }
        return false;
    }
    
    public static boolean isCPPVisibilityKeyword(@Nullable final ASTNode astNode) {
        Label_0026: {
            try {
                if (astNode == null) {
                    return false;
                }
                final TokenSet set = OCTokenTypes.CPP_VISIBILITY_KEYWORDS;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set.contains(elementType);
                if (b) {
                    break Label_0026;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final TokenSet set = OCTokenTypes.CPP_VISIBILITY_KEYWORDS;
                final ASTNode astNode2 = astNode;
                final IElementType elementType = astNode2.getElementType();
                final boolean b = set.contains(elementType);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    public static String getTypeTextWithModifiers(@NotNull final OCDeclaration ocDeclaration) {
        try {
            if (ocDeclaration == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declaration", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTypeTextWithModifiers"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final List<OCDeclarator> declarators = ocDeclaration.getDeclarators();
        try {
            if (declarators.size() == 1) {
                return getTypeTextWithModifiers(declarators.get(0));
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    public static String getTypeTextWithModifiers(final OCDeclarator p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclaration;
        //     9: astore_1       
        //    10: aload_1        
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclaration.getTypeElement:()Lcom/jetbrains/cidr/lang/psi/OCTypeElement;
        //    16: astore_2       
        //    17: aload_2        
        //    18: ifnull          93
        //    21: aload_1        
        //    22: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    25: ifne            60
        //    28: goto            35
        //    31: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    34: athrow         
        //    35: aload_0        
        //    36: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getTextWithMacros:()Ljava/lang/String;
        //    41: aload_0        
        //    42: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getName:()Ljava/lang/String;
        //    47: invokestatic    com/jetbrains/cidr/lang/util/OCCodeInsightUtil.isSimpleDeclaration:(Ljava/lang/String;Ljava/lang/String;)Z
        //    50: ifeq            93
        //    53: goto            60
        //    56: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    59: athrow         
        //    60: new             Ljava/lang/StringBuilder;
        //    63: dup            
        //    64: invokespecial   java/lang/StringBuilder.<init>:()V
        //    67: aload_2        
        //    68: invokeinterface com/jetbrains/cidr/lang/psi/OCTypeElement.getTextWithMacros:()Ljava/lang/String;
        //    73: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    76: aload_0        
        //    77: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getModifiersText:()Ljava/lang/String;
        //    82: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    85: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    88: areturn        
        //    89: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    92: athrow         
        //    93: aconst_null    
        //    94: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  17     28     31     35     Ljava/lang/NumberFormatException;
        //  21     53     56     60     Ljava/lang/NumberFormatException;
        //  35     89     89     93     Ljava/lang/NumberFormatException;
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
    
    @Nullable
    public static String getTypeTextWithModifiers(final OCDeclaratorSymbol ocDeclaratorSymbol) {
        final PsiNameIdentifierOwner psiNameIdentifierOwner = ocDeclaratorSymbol.locateDefinition();
        try {
            if (psiNameIdentifierOwner instanceof OCDeclarator) {
                return getTypeTextWithModifiers((OCDeclarator)psiNameIdentifierOwner);
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        if (psiNameIdentifierOwner instanceof OCMethodSelectorPart) {
            final OCTypeElement typeElement = ((OCMethodSelectorPart)psiNameIdentifierOwner).getTypeElement();
            try {
                if (typeElement != null) {
                    return typeElement.getTextWithMacros();
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            return null;
        }
        return null;
    }
    
    @Nullable
    public static String getReturnTypeTextWithModifiers(final OCFunctionSymbol ocFunctionSymbol) {
        final OCFunctionDeclaration locateFunctionDefinition = ocFunctionSymbol.locateFunctionDefinition();
        try {
            if (locateFunctionDefinition != null) {
                return getTypeTextWithModifiers(locateFunctionDefinition);
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    public static String getReturnTypeText(final OCMethodSymbol ocMethodSymbol) {
        final OCMethod locateDefinition = ((OCSymbol<OCMethod>)ocMethodSymbol).locateDefinition();
        OCTypeElement returnTypeElement = null;
        Label_0031: {
            try {
                if (locateDefinition instanceof OCMethod) {
                    returnTypeElement = locateDefinition.getReturnTypeElement();
                    break Label_0031;
                }
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            returnTypeElement = null;
        }
        final OCTypeElement ocTypeElement = returnTypeElement;
        try {
            if (ocTypeElement != null) {
                return ocTypeElement.getTextWithMacros();
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Contract("null -> null")
    public static OCPragma getPragmaAt(@Nullable final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement instanceof OCPragma) {
                return (OCPragma)psiElement;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement.getParent() instanceof OCPragma) {
                return (OCPragma)psiElement.getParent();
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @NotNull
    public static String getLeadingCommentsAndWhitespaces(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getLeadingCommentsAndWhitespaces"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        String string = "";
        final OCMacroRange rangeInMacroCall = getRangeInMacroCall(psiElement);
        OCMacroCall macroCall = null;
        Label_0068: {
            try {
                if (rangeInMacroCall != null) {
                    macroCall = rangeInMacroCall.getMacroCall();
                    break Label_0068;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            macroCall = null;
        }
        final OCMacroCall ocMacroCall = macroCall;
        PsiElement psiElement2 = PsiTreeUtil.prevLeaf(psiElement);
        while (true) {
            Label_0195: {
                Label_0147: {
                    Label_0096: {
                        try {
                            if (psiElement2 == null) {
                                break;
                            }
                            final PsiElement psiElement3 = psiElement2;
                            final boolean b2 = isElementEmpty(psiElement3);
                            if (b2) {
                                break Label_0096;
                            }
                            break;
                        }
                        catch (NumberFormatException ex3) {
                            throw b(ex3);
                        }
                        try {
                            final PsiElement psiElement3 = psiElement2;
                            final boolean b2 = isElementEmpty(psiElement3);
                            if (!b2) {
                                break;
                            }
                            if (!isPartOfMacroSubstitution(psiElement2)) {
                                break Label_0147;
                            }
                        }
                        catch (NumberFormatException ex4) {
                            throw b(ex4);
                        }
                    }
                    final OCMacroRange rangeInMacroCall2 = getRangeInMacroCall(psiElement2);
                    Label_0140: {
                        try {
                            if (rangeInMacroCall2 == null) {
                                break Label_0147;
                            }
                            final OCMacroRange ocMacroRange = rangeInMacroCall2;
                            final OCMacroCall ocMacroCall2 = ocMacroRange.getMacroCall();
                            final OCMacroCall ocMacroCall3 = ocMacroCall;
                            if (ocMacroCall2 == ocMacroCall3) {
                                break Label_0140;
                            }
                            break Label_0147;
                        }
                        catch (NumberFormatException ex5) {
                            throw b(ex5);
                        }
                        try {
                            final OCMacroRange ocMacroRange = rangeInMacroCall2;
                            final OCMacroCall ocMacroCall2 = ocMacroRange.getMacroCall();
                            final OCMacroCall ocMacroCall3 = ocMacroCall;
                            if (ocMacroCall2 == ocMacroCall3) {
                                break;
                            }
                        }
                        catch (NumberFormatException ex6) {
                            throw b(ex6);
                        }
                    }
                    try {
                        if (b) {
                            if (OCTokenTypes.WHITESPACES.contains(getElementType(psiElement2))) {
                                break Label_0195;
                            }
                        }
                    }
                    catch (NumberFormatException ex7) {
                        throw b(ex7);
                    }
                }
                string = getTextWithMacros(psiElement2) + string;
            }
            psiElement2 = PsiTreeUtil.prevLeaf(psiElement2);
        }
        String s;
        try {
            s = string;
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getLeadingCommentsAndWhitespaces"));
            }
        }
        catch (NumberFormatException ex8) {
            throw b(ex8);
        }
        return s;
    }
    
    @NotNull
    public static String getTrailingCommentsAndWhitespaces(@NotNull final PsiElement psiElement, final boolean b) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTrailingCommentsAndWhitespaces"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final StringBuilder sb = new StringBuilder();
        final OCMacroRange rangeInMacroCall = getRangeInMacroCall(psiElement);
        OCMacroCall macroCall = null;
        Label_0073: {
            try {
                if (rangeInMacroCall != null) {
                    macroCall = rangeInMacroCall.getMacroCall();
                    break Label_0073;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            macroCall = null;
        }
        final OCMacroCall ocMacroCall = macroCall;
        PsiElement psiElement2 = PsiTreeUtil.nextLeaf(psiElement);
        while (true) {
            Label_0177: {
                Label_0152: {
                    Label_0101: {
                        try {
                            if (psiElement2 == null) {
                                break;
                            }
                            final PsiElement psiElement3 = psiElement2;
                            final boolean b2 = isElementEmpty(psiElement3);
                            if (b2) {
                                break Label_0101;
                            }
                            break;
                        }
                        catch (NumberFormatException ex3) {
                            throw b(ex3);
                        }
                        try {
                            final PsiElement psiElement3 = psiElement2;
                            final boolean b2 = isElementEmpty(psiElement3);
                            if (!b2) {
                                break;
                            }
                            if (!isPartOfMacroSubstitution(psiElement2)) {
                                break Label_0152;
                            }
                        }
                        catch (NumberFormatException ex4) {
                            throw b(ex4);
                        }
                    }
                    final OCMacroRange rangeInMacroCall2 = getRangeInMacroCall(psiElement2);
                    Label_0145: {
                        try {
                            if (rangeInMacroCall2 == null) {
                                break Label_0152;
                            }
                            final OCMacroRange ocMacroRange = rangeInMacroCall2;
                            final OCMacroCall ocMacroCall2 = ocMacroRange.getMacroCall();
                            final OCMacroCall ocMacroCall3 = ocMacroCall;
                            if (ocMacroCall2 == ocMacroCall3) {
                                break Label_0145;
                            }
                            break Label_0152;
                        }
                        catch (NumberFormatException ex5) {
                            throw b(ex5);
                        }
                        try {
                            final OCMacroRange ocMacroRange = rangeInMacroCall2;
                            final OCMacroCall ocMacroCall2 = ocMacroRange.getMacroCall();
                            final OCMacroCall ocMacroCall3 = ocMacroCall;
                            if (ocMacroCall2 == ocMacroCall3) {
                                break;
                            }
                        }
                        catch (NumberFormatException ex6) {
                            throw b(ex6);
                        }
                    }
                    try {
                        if (!b) {
                            break Label_0177;
                        }
                        final TokenSet set = OCTokenTypes.WHITESPACES;
                        final PsiElement psiElement4 = psiElement2;
                        final IElementType elementType = getElementType(psiElement4);
                        final boolean b3 = set.contains(elementType);
                        if (!b3) {
                            break Label_0177;
                        }
                        break Label_0177;
                    }
                    catch (NumberFormatException ex7) {
                        throw b(ex7);
                    }
                }
                try {
                    final TokenSet set = OCTokenTypes.WHITESPACES;
                    final PsiElement psiElement4 = psiElement2;
                    final IElementType elementType = getElementType(psiElement4);
                    final boolean b3 = set.contains(elementType);
                    if (!b3) {
                        sb.append(getTextWithMacros(psiElement2));
                    }
                }
                catch (NumberFormatException ex8) {
                    throw b(ex8);
                }
            }
            psiElement2 = PsiTreeUtil.nextLeaf(psiElement2);
        }
        String string;
        try {
            string = sb.toString();
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getTrailingCommentsAndWhitespaces"));
            }
        }
        catch (NumberFormatException ex9) {
            throw b(ex9);
        }
        return string;
    }
    
    @Nullable
    public static PsiReference findReferenceInMacro(@Nullable final PsiElement psiElement) {
        Label_0021: {
            try {
                if (psiElement == null) {
                    break Label_0021;
                }
                final PsiElement psiElement2 = psiElement;
                final IElementType elementType = getElementType(psiElement2);
                final OCElementType ocElementType = OCTokenTypes.STRING_LITERAL;
                if (elementType != ocElementType) {
                    break Label_0021;
                }
                break Label_0021;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final IElementType elementType = getElementType(psiElement2);
                final OCElementType ocElementType = OCTokenTypes.STRING_LITERAL;
                if (elementType != ocElementType) {
                    return null;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        final OCMacroCallArgument ocMacroCallArgument = (OCMacroCallArgument)PsiTreeUtil.getParentOfType(psiElement, (Class)OCMacroCallArgument.class);
        try {
            if (ocMacroCallArgument == null) {
                return null;
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        final PsiElement parent = ocMacroCallArgument.getParent();
        if (parent instanceof OCMacroCall) {
            final Ref create = Ref.create((Object)null);
            try {
                OCResourceReferenceContributor.processReferenceProviders((Processor<OCResourceCompletionProvider>)(ocResourceCompletionProvider -> {
                    final OCResourceReference referenceByCall = ocResourceCompletionProvider.getReferenceByCall(parent, psiElement);
                    try {
                        if (referenceByCall != null) {
                            create.set((Object)referenceByCall);
                            return false;
                        }
                    }
                    catch (NumberFormatException ex) {
                        throw b(ex);
                    }
                    return true;
                }));
                if (!create.isNull()) {
                    return (PsiReference)create.get();
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
        }
        return null;
    }
    
    @NotNull
    public static SearchScope getUseScope(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getUseScope"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        Label_0250: {
            if (!(psiElement instanceof OCDefineDirective)) {
                final PsiElement contextOfType = PsiTreeUtil.getContextOfType(psiElement, new Class[] { OCCallable.class });
                Label_0086: {
                    try {
                        if (contextOfType == null) {
                            break Label_0250;
                        }
                        final PsiElement psiElement2 = contextOfType;
                        final PsiElement psiElement3 = psiElement;
                        final PsiElement psiElement4 = psiElement3.getContext();
                        if (psiElement2 == psiElement4) {
                            break Label_0086;
                        }
                        break Label_0086;
                    }
                    catch (NumberFormatException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final PsiElement psiElement2 = contextOfType;
                        final PsiElement psiElement3 = psiElement;
                        final PsiElement psiElement4 = psiElement3.getContext();
                        if (psiElement2 == psiElement4) {
                            if (!(psiElement instanceof OCMethodSelectorPart)) {
                                break Label_0250;
                            }
                        }
                    }
                    catch (NumberFormatException ex3) {
                        throw b(ex3);
                    }
                }
                final Pair<List<PsiElement>, OCSymbolKind> scopeAndKind = OCCodeInsightUtil.getScopeAndKind(psiElement);
                LocalSearchScope localSearchScope2 = null;
                Label_0203: {
                    LocalSearchScope localSearchScope = null;
                    Label_0168: {
                        try {
                            if (((List)scopeAndKind.getFirst()).isEmpty()) {
                                break Label_0203;
                            }
                            final Pair<List<PsiElement>, OCSymbolKind> pair = scopeAndKind;
                            final Object o = pair.getFirst();
                            final List list = (List)o;
                            final Pair<List<PsiElement>, OCSymbolKind> pair2 = scopeAndKind;
                            final Object o2 = pair2.getFirst();
                            final List list2 = (List)o2;
                            final int n = list2.size();
                            final PsiElement[] array = new PsiElement[n];
                            final PsiElement[] array2 = list.toArray(array);
                            final PsiElement[] array3 = array2;
                            localSearchScope = new LocalSearchScope(array3);
                            if (localSearchScope == null) {
                                break Label_0168;
                            }
                            return (SearchScope)localSearchScope;
                        }
                        catch (NumberFormatException ex4) {
                            throw b(ex4);
                        }
                        try {
                            final Pair<List<PsiElement>, OCSymbolKind> pair = scopeAndKind;
                            final Object o = pair.getFirst();
                            final List list = (List)o;
                            final Pair<List<PsiElement>, OCSymbolKind> pair2 = scopeAndKind;
                            final Object o2 = pair2.getFirst();
                            final List list2 = (List)o2;
                            final int n = list2.size();
                            final PsiElement[] array = new PsiElement[n];
                            final PsiElement[] array2 = list.toArray(array);
                            final PsiElement[] array3 = array2;
                            localSearchScope = new LocalSearchScope(array3);
                            if (localSearchScope == null) {
                                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getUseScope"));
                            }
                        }
                        catch (NumberFormatException ex5) {
                            throw b(ex5);
                        }
                    }
                    return (SearchScope)localSearchScope;
                    try {
                        localSearchScope2 = new LocalSearchScope(contextOfType);
                        if (localSearchScope2 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getUseScope"));
                        }
                    }
                    catch (NumberFormatException ex6) {
                        throw b(ex6);
                    }
                }
                return (SearchScope)localSearchScope2;
            }
        }
        final Project project = psiElement.getProject();
        GlobalSearchScope union = null;
        Label_0314: {
            GlobalSearchScope globalSearchScope = null;
            Label_0279: {
                try {
                    if (!OCSearchScope.isInProjectSources(psiElement)) {
                        break Label_0314;
                    }
                    final Project project2 = project;
                    globalSearchScope = OCSearchScope.getProjectSourcesScope(project2);
                    if (globalSearchScope == null) {
                        break Label_0279;
                    }
                    return (SearchScope)globalSearchScope;
                }
                catch (NumberFormatException ex7) {
                    throw b(ex7);
                }
                try {
                    final Project project2 = project;
                    globalSearchScope = OCSearchScope.getProjectSourcesScope(project2);
                    if (globalSearchScope == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getUseScope"));
                    }
                }
                catch (NumberFormatException ex8) {
                    throw b(ex8);
                }
            }
            return (SearchScope)globalSearchScope;
            try {
                union = OCSearchScope.getProjectSourcesScope(project).union((SearchScope)ProjectScope.getLibrariesScope(project));
                if (union == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/util/OCElementUtil", "getUseScope"));
                }
            }
            catch (NumberFormatException ex9) {
                throw b(ex9);
            }
        }
        return (SearchScope)union;
    }
    
    @NotNull
    public static String getIdentifierName(final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/impl/OCEmptyName;
        //     4: ifeq            55
        //     7: ldc             "id"
        //     9: dup            
        //    10: ifnonnull       54
        //    13: goto            20
        //    16: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    19: athrow         
        //    20: new             Ljava/lang/IllegalStateException;
        //    23: dup            
        //    24: ldc             "@NotNull method %s.%s must not return null"
        //    26: ldc             2
        //    28: anewarray       Ljava/lang/Object;
        //    31: dup            
        //    32: ldc             0
        //    34: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    36: aastore        
        //    37: dup            
        //    38: ldc             1
        //    40: ldc             "getIdentifierName"
        //    42: aastore        
        //    43: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    46: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    49: athrow         
        //    50: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    53: athrow         
        //    54: areturn        
        //    55: aload_0        
        //    56: ifnonnull       107
        //    59: ldc             "<unnamed>"
        //    61: dup            
        //    62: ifnonnull       106
        //    65: goto            72
        //    68: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    71: athrow         
        //    72: new             Ljava/lang/IllegalStateException;
        //    75: dup            
        //    76: ldc             "@NotNull method %s.%s must not return null"
        //    78: ldc             2
        //    80: anewarray       Ljava/lang/Object;
        //    83: dup            
        //    84: ldc             0
        //    86: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    88: aastore        
        //    89: dup            
        //    90: ldc             1
        //    92: ldc             "getIdentifierName"
        //    94: aastore        
        //    95: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    98: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   101: athrow         
        //   102: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   105: athrow         
        //   106: areturn        
        //   107: aload_0        
        //   108: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   113: astore_1       
        //   114: aload_1        
        //   115: ifnonnull       166
        //   118: ldc             "<unnamed>"
        //   120: dup            
        //   121: ifnonnull       165
        //   124: goto            131
        //   127: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   130: athrow         
        //   131: new             Ljava/lang/IllegalStateException;
        //   134: dup            
        //   135: ldc             "@NotNull method %s.%s must not return null"
        //   137: ldc             2
        //   139: anewarray       Ljava/lang/Object;
        //   142: dup            
        //   143: ldc             0
        //   145: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //   147: aastore        
        //   148: dup            
        //   149: ldc             1
        //   151: ldc             "getIdentifierName"
        //   153: aastore        
        //   154: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   157: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   160: athrow         
        //   161: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   164: athrow         
        //   165: areturn        
        //   166: new             Ljava/lang/StringBuilder;
        //   169: dup            
        //   170: invokespecial   java/lang/StringBuilder.<init>:()V
        //   173: astore_2       
        //   174: aload_1        
        //   175: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   180: instanceof      Lcom/intellij/lang/ForeignLeafType;
        //   183: ifeq            210
        //   186: aload_2        
        //   187: aload_1        
        //   188: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   193: checkcast       Lcom/intellij/lang/ForeignLeafType;
        //   196: invokevirtual   com/intellij/lang/ForeignLeafType.getValue:()Ljava/lang/String;
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   202: pop            
        //   203: goto            244
        //   206: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   209: athrow         
        //   210: aload_0        
        //   211: instanceof      Lcom/intellij/psi/impl/source/tree/ForeignLeafPsiElement;
        //   214: ifeq            233
        //   217: aload_2        
        //   218: aload_0        
        //   219: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextFromLeaves:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   222: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   225: pop            
        //   226: goto            244
        //   229: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   232: athrow         
        //   233: aload_2        
        //   234: aload_1        
        //   235: invokeinterface com/intellij/lang/ASTNode.getText:()Ljava/lang/String;
        //   240: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   243: pop            
        //   244: aload_0        
        //   245: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   248: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   251: if_acmpne       413
        //   254: aload_0        
        //   255: astore_3       
        //   256: iconst_1       
        //   257: istore          4
        //   259: aload_3        
        //   260: invokeinterface com/intellij/psi/PsiElement.getNextSibling:()Lcom/intellij/psi/PsiElement;
        //   265: dup            
        //   266: astore_3       
        //   267: ifnull          410
        //   270: aload_3        
        //   271: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //   276: invokeinterface com/intellij/lang/ASTNode.getElementType:()Lcom/intellij/psi/tree/IElementType;
        //   281: astore          5
        //   283: aload           5
        //   285: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.PARAMETER_LIST:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   288: if_acmpne       298
        //   291: goto            410
        //   294: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   297: athrow         
        //   298: aload_3        
        //   299: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isElementSignificant:(Lcom/intellij/psi/PsiElement;)Z
        //   302: ifeq            407
        //   305: iload           4
        //   307: ifeq            395
        //   310: goto            317
        //   313: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   316: athrow         
        //   317: aload_2        
        //   318: iconst_0       
        //   319: invokevirtual   java/lang/StringBuilder.setLength:(I)V
        //   322: aload_2        
        //   323: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.OPERATOR_CPP_KEYWORD:Lcom/jetbrains/cidr/lang/parser/OCKeywordElementType;
        //   326: invokevirtual   com/jetbrains/cidr/lang/parser/OCKeywordElementType.getName:()Ljava/lang/String;
        //   329: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   332: pop            
        //   333: aload           5
        //   335: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.IDENTIFIER:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   338: if_acmpeq       381
        //   341: goto            348
        //   344: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   347: athrow         
        //   348: aload           5
        //   350: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.TYPE_ELEMENT:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   353: if_acmpeq       381
        //   356: goto            363
        //   359: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   362: athrow         
        //   363: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.KEYWORDS:Lcom/intellij/psi/tree/TokenSet;
        //   366: aload           5
        //   368: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //   371: ifeq            395
        //   374: goto            381
        //   377: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   380: athrow         
        //   381: aload_2        
        //   382: bipush          32
        //   384: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   387: pop            
        //   388: goto            395
        //   391: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   394: athrow         
        //   395: aload_2        
        //   396: aload_3        
        //   397: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextFromLeaves:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   400: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   403: pop            
        //   404: iconst_0       
        //   405: istore          4
        //   407: goto            259
        //   410: goto            467
        //   413: aload_0        
        //   414: iconst_1       
        //   415: anewarray       Ljava/lang/Class;
        //   418: dup            
        //   419: iconst_0       
        //   420: ldc             Lcom/intellij/psi/PsiWhiteSpace;.class
        //   422: aastore        
        //   423: invokestatic    com/intellij/psi/util/PsiTreeUtil.skipSiblingsBackward:(Lcom/intellij/psi/PsiElement;[Ljava/lang/Class;)Lcom/intellij/psi/PsiElement;
        //   426: astore_3       
        //   427: aload_3        
        //   428: ifnull          467
        //   431: ldc             "~"
        //   433: aload_3        
        //   434: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextFromLeaves:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   437: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   440: ifeq            467
        //   443: goto            450
        //   446: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   449: athrow         
        //   450: aload_2        
        //   451: iconst_0       
        //   452: aload_3        
        //   453: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getTextFromLeaves:(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;
        //   456: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //   459: pop            
        //   460: goto            467
        //   463: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   466: athrow         
        //   467: aload_2        
        //   468: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   471: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   474: dup            
        //   475: ifnonnull       512
        //   478: new             Ljava/lang/IllegalStateException;
        //   481: dup            
        //   482: ldc             "@NotNull method %s.%s must not return null"
        //   484: ldc             2
        //   486: anewarray       Ljava/lang/Object;
        //   489: dup            
        //   490: ldc             0
        //   492: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //   494: aastore        
        //   495: dup            
        //   496: ldc             1
        //   498: ldc             "getIdentifierName"
        //   500: aastore        
        //   501: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   504: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   507: athrow         
        //   508: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   511: athrow         
        //   512: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      13     16     20     Ljava/lang/NumberFormatException;
        //  7      50     50     54     Ljava/lang/NumberFormatException;
        //  55     65     68     72     Ljava/lang/NumberFormatException;
        //  59     102    102    106    Ljava/lang/NumberFormatException;
        //  114    124    127    131    Ljava/lang/NumberFormatException;
        //  118    161    161    165    Ljava/lang/NumberFormatException;
        //  174    206    206    210    Ljava/lang/NumberFormatException;
        //  210    229    229    233    Ljava/lang/NumberFormatException;
        //  283    294    294    298    Ljava/lang/NumberFormatException;
        //  298    310    313    317    Ljava/lang/NumberFormatException;
        //  305    341    344    348    Ljava/lang/NumberFormatException;
        //  317    356    359    363    Ljava/lang/NumberFormatException;
        //  348    374    377    381    Ljava/lang/NumberFormatException;
        //  363    388    391    395    Ljava/lang/NumberFormatException;
        //  427    443    446    450    Ljava/lang/NumberFormatException;
        //  431    460    463    467    Ljava/lang/NumberFormatException;
        //  467    508    508    512    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0317:
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
    
    public static String getElementDebugName(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getElementDebugName"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final String simpleName = psiElement.getClass().getSimpleName();
        String substring = null;
        Label_0087: {
            try {
                if (simpleName.endsWith("Impl")) {
                    substring = simpleName.substring(0, simpleName.length() - "Impl".length());
                    break Label_0087;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            substring = simpleName;
        }
        final String s = substring;
        String s2 = null;
        Label_0135: {
            try {
                if (psiElement instanceof OCDeclarator) {
                    s2 = ((OCDeclarator)psiElement).getSymbolName();
                    break Label_0135;
                }
            }
            catch (NumberFormatException ex3) {
                throw b(ex3);
            }
            try {
                if (psiElement instanceof PsiNamedElement) {
                    s2 = ((PsiNamedElement)psiElement).getName();
                    break Label_0135;
                }
            }
            catch (NumberFormatException ex4) {
                throw b(ex4);
            }
            s2 = null;
        }
        final String s3 = s2;
        try {
            if (s3 != null) {
                return s + "(" + s3 + ")";
            }
        }
        catch (NumberFormatException ex5) {
            throw b(ex5);
        }
        return s;
    }
    
    public static IElementType getUnwrappedTokeType(@Nullable IElementType delegate) {
        while (delegate instanceof ForeignLeafType) {
            delegate = ((ForeignLeafType)delegate).getDelegate();
        }
        return delegate;
    }
    
    public static boolean containsDirectives(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/util/OCElementUtil", "containsDirectives"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        for (final ASTNode astNode2 : astNode.getChildren((TokenSet)null)) {
            Label_0104: {
                try {
                    if (OCTokenTypes.DIRECTIVES.contains(astNode2.getElementType())) {
                        return true;
                    }
                    final ASTNode astNode3 = astNode2;
                    final boolean b = containsDirectives(astNode3);
                    if (b) {
                        return true;
                    }
                    break Label_0104;
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                try {
                    final ASTNode astNode3 = astNode2;
                    final boolean b = containsDirectives(astNode3);
                    if (b) {
                        return true;
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
            }
        }
        return false;
    }
    
    public static boolean containsDirectives(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "containsDirectives"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        return containsDirectives(psiElement.getNode());
    }
    
    @Nullable
    public static OCClassDeclaration resolveClassDeclaration(@Nullable final OCSymbol ocSymbol) {
        try {
            if (ocSymbol == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        final PsiElement locateDefinition = ocSymbol.locateDefinition();
        try {
            if (locateDefinition instanceof OCClassDeclaration) {
                return (OCClassDeclaration)locateDefinition;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        return null;
    }
    
    @Nullable
    public static TextRange getUserVisibleRangeInDocument(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "getUserVisibleRangeInDocument"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: aload_0        
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeInMacroCall:(Lcom/intellij/psi/PsiElement;)Lcom/jetbrains/cidr/lang/parser/OCMacroRange;
        //    48: astore_2       
        //    49: aload_2        
        //    50: ifnonnull       141
        //    53: aload_0        
        //    54: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    59: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findFirstLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/psi/impl/source/tree/LeafElement;
        //    62: astore_3       
        //    63: aload_0        
        //    64: invokeinterface com/intellij/psi/PsiElement.getNode:()Lcom/intellij/lang/ASTNode;
        //    69: invokestatic    com/intellij/psi/impl/source/tree/TreeUtil.findLastLeaf:(Lcom/intellij/lang/ASTNode;)Lcom/intellij/lang/ASTNode;
        //    72: astore          4
        //    74: aload_3        
        //    75: ifnull          95
        //    78: aload_3        
        //    79: invokevirtual   com/intellij/psi/impl/source/tree/LeafElement.getPsi:()Lcom/intellij/psi/PsiElement;
        //    82: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //    85: ifne            127
        //    88: goto            95
        //    91: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    94: athrow         
        //    95: aload           4
        //    97: ifnull          133
        //   100: goto            107
        //   103: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   106: athrow         
        //   107: aload           4
        //   109: invokeinterface com/intellij/lang/ASTNode.getPsi:()Lcom/intellij/psi/PsiElement;
        //   114: instanceof      Lcom/jetbrains/cidr/lang/preprocessor/OCMacroForeignLeafElement;
        //   117: ifeq            133
        //   120: goto            127
        //   123: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   126: athrow         
        //   127: aconst_null    
        //   128: areturn        
        //   129: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   132: athrow         
        //   133: aload_0        
        //   134: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getRangeWithMacros:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/openapi/util/TextRange;
        //   137: astore_1       
        //   138: goto            158
        //   141: aload_2        
        //   142: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.mapsToArguments:()Z
        //   145: ifeq            156
        //   148: aload_2        
        //   149: invokevirtual   com/jetbrains/cidr/lang/parser/OCMacroRange.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   152: astore_1       
        //   153: goto            158
        //   156: aconst_null    
        //   157: astore_1       
        //   158: aload_1        
        //   159: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  74     88     91     95     Ljava/lang/NumberFormatException;
        //  78     100    103    107    Ljava/lang/NumberFormatException;
        //  95     120    123    127    Ljava/lang/NumberFormatException;
        //  107    129    129    133    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0095:
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
    
    public static boolean isEssentialNode(@NotNull final PsiElement p0) {
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
        //    18: ldc             "element"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/lang/util/OCElementUtil"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "isEssentialNode"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    43: athrow         
        //    44: getstatic       com/jetbrains/cidr/lang/parser/OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET:Lcom/intellij/psi/tree/TokenSet;
        //    47: aload_0        
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    51: invokevirtual   com/intellij/psi/tree/TokenSet.contains:(Lcom/intellij/psi/tree/IElementType;)Z
        //    54: ifne            98
        //    57: aload_0        
        //    58: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    63: invokevirtual   com/intellij/openapi/util/TextRange.isEmpty:()Z
        //    66: ifne            98
        //    69: goto            76
        //    72: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    75: athrow         
        //    76: aload_0        
        //    77: instanceof      Lcom/jetbrains/cidr/lang/psi/OCPragma;
        //    80: ifne            98
        //    83: goto            90
        //    86: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    89: athrow         
        //    90: iconst_1       
        //    91: goto            99
        //    94: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    97: athrow         
        //    98: iconst_0       
        //    99: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      40     40     44     Ljava/lang/NumberFormatException;
        //  44     69     72     76     Ljava/lang/NumberFormatException;
        //  57     83     86     90     Ljava/lang/NumberFormatException;
        //  76     94     94     98     Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0076:
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
    
    private static boolean a(@NotNull final OCDirective ocDirective) {
        try {
            if (ocDirective == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "directive", "com/jetbrains/cidr/lang/util/OCElementUtil", "isLocalGuardIfndef"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        if (getElementType(ocDirective.getHeaderToken()) == OCTokenTypes.IFNDEF_DIRECTIVE) {
            final PsiElement nextNonWhitespaceSibling = getNextNonWhitespaceSibling((PsiElement)ocDirective);
            try {
                if (!(nextNonWhitespaceSibling instanceof OCDefineDirective)) {
                    return false;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
            String name = null;
            int n = 1;
            for (final PsiElement psiElement : ocDirective.getChildren()) {
                if (psiElement instanceof OCReferenceElement) {
                    name = ((OCReferenceElement)psiElement).getName();
                }
                Label_0159: {
                    try {
                        if (n != 0) {
                            break Label_0159;
                        }
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = isEssentialNode(psiElement2);
                        if (b) {
                            return false;
                        }
                        break Label_0159;
                    }
                    catch (NumberFormatException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = isEssentialNode(psiElement2);
                        if (b) {
                            return false;
                        }
                    }
                    catch (NumberFormatException ex4) {
                        throw b(ex4);
                    }
                }
                n = 0;
            }
            try {
                if (name == null) {
                    return false;
                }
                final String s = name;
                final PsiElement psiElement3 = nextNonWhitespaceSibling;
                final OCDefineDirective ocDefineDirective = (OCDefineDirective)psiElement3;
                final String s2 = ocDefineDirective.getName();
                final boolean b2 = s.equals(s2);
                if (!b2) {
                    return false;
                }
                return true;
            }
            catch (NumberFormatException ex5) {
                throw b(ex5);
            }
            try {
                final String s = name;
                final PsiElement psiElement3 = nextNonWhitespaceSibling;
                final OCDefineDirective ocDefineDirective = (OCDefineDirective)psiElement3;
                final String s2 = ocDefineDirective.getName();
                final boolean b2 = s.equals(s2);
                if (!b2) {
                    return false;
                }
            }
            catch (NumberFormatException ex6) {
                throw b(ex6);
            }
            return true;
        }
        return false;
    }
    
    @Nullable
    public static OCDirective getGuardIfndef(@NotNull final PsiFile psiFile, final boolean b) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/util/OCElementUtil", "getGuardIfndef"));
            }
        }
        catch (OCElementUtil.1CancelException ex) {
            throw b(ex);
        }
        final OCDirective[] array = { null };
        try {
            final PsiElement[] array2 = { null };
            final PsiElement[] array3 = { null };
            Label_0111: {
                try {
                    psiFile.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                        int nestLevel = 0;
                        int directivesAtZeroNestLevel = 0;
                        
                        @Override
                        public void visitElement(@NotNull final PsiElement psiElement) {
                            try {
                                if (psiElement == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil$1", "visitElement"));
                                }
                            }
                            catch (1CancelException ex) {
                                throw b(ex);
                            }
                            Label_0084: {
                                Label_0072: {
                                    try {
                                        super.visitElement(psiElement);
                                        if (!OCElementUtil.isEssentialNode(psiElement)) {
                                            return;
                                        }
                                        final OCRecursiveVisitor ocRecursiveVisitor = this;
                                        final OCDirective[] array = array;
                                        final int n = 0;
                                        final OCDirective ocDirective = array[n];
                                        if (ocDirective == null) {
                                            break Label_0072;
                                        }
                                        break Label_0084;
                                    }
                                    catch (1CancelException ex2) {
                                        throw b(ex2);
                                    }
                                    try {
                                        final OCRecursiveVisitor ocRecursiveVisitor = this;
                                        final OCDirective[] array = array;
                                        final int n = 0;
                                        final OCDirective ocDirective = array[n];
                                        if (ocDirective == null) {
                                            throw new 1CancelException();
                                        }
                                    }
                                    catch (1CancelException ex3) {
                                        throw b(ex3);
                                    }
                                }
                                try {
                                    if (psiElement != psiFile) {
                                        array2[0] = psiElement;
                                    }
                                }
                                catch (1CancelException ex4) {
                                    throw b(ex4);
                                }
                            }
                        }
                        
                        @Override
                        public void visitPragma(@NotNull final OCPragma ocPragma) {
                            try {
                                if (ocPragma == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "pragma", "com/jetbrains/cidr/lang/util/OCElementUtil$1", "visitPragma"));
                                }
                            }
                            catch (1CancelException ex) {
                                throw b(ex);
                            }
                        }
                        
                        @Override
                        public void visitDirective(@NotNull final OCDirective ocDirective) {
                            try {
                                if (ocDirective == null) {
                                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "directive", "com/jetbrains/cidr/lang/util/OCElementUtil$1", "visitDirective"));
                                }
                            }
                            catch (1CancelException ex) {
                                throw b(ex);
                            }
                            final IElementType elementType = OCElementUtil.getElementType(ocDirective.getHeaderToken());
                            Label_0199: {
                                Label_0180: {
                                    Label_0148: {
                                        Label_0122: {
                                            Label_0091: {
                                                Label_0077: {
                                                    try {
                                                        if (array[0] != null) {
                                                            break Label_0091;
                                                        }
                                                        final OCDirective ocDirective2 = ocDirective;
                                                        final boolean b = a(ocDirective2);
                                                        if (b) {
                                                            break Label_0077;
                                                        }
                                                        break Label_0091;
                                                    }
                                                    catch (1CancelException ex2) {
                                                        throw b(ex2);
                                                    }
                                                    try {
                                                        final OCDirective ocDirective2 = ocDirective;
                                                        final boolean b = a(ocDirective2);
                                                        if (b) {
                                                            array[0] = ocDirective;
                                                        }
                                                    }
                                                    catch (1CancelException ex3) {
                                                        throw b(ex3);
                                                    }
                                                }
                                                try {
                                                    if (OCTokenTypes.ENDIF_DIRECTIVE != elementType) {
                                                        break Label_0148;
                                                    }
                                                    final OCRecursiveVisitor ocRecursiveVisitor = this;
                                                    final int n = ocRecursiveVisitor.nestLevel;
                                                    final int n2 = 1;
                                                    final int n3 = n - n2;
                                                    ocRecursiveVisitor.nestLevel = n3;
                                                    final OCRecursiveVisitor ocRecursiveVisitor2 = this;
                                                    final int n4 = ocRecursiveVisitor2.nestLevel;
                                                    if (n4 < 0) {
                                                        break Label_0122;
                                                    }
                                                    break Label_0122;
                                                }
                                                catch (1CancelException ex4) {
                                                    throw b(ex4);
                                                }
                                            }
                                            try {
                                                final OCRecursiveVisitor ocRecursiveVisitor = this;
                                                final int n = ocRecursiveVisitor.nestLevel;
                                                final int n2 = 1;
                                                final int n3 = n - n2;
                                                ocRecursiveVisitor.nestLevel = n3;
                                                final OCRecursiveVisitor ocRecursiveVisitor2 = this;
                                                final int n4 = ocRecursiveVisitor2.nestLevel;
                                                if (n4 < 0) {
                                                    array[0] = null;
                                                    throw new 1CancelException();
                                                }
                                            }
                                            catch (1CancelException ex5) {
                                                throw b(ex5);
                                            }
                                        }
                                        array3[0] = (PsiElement)ocDirective;
                                        try {
                                            if (this.nestLevel != 0) {
                                                break Label_0199;
                                            }
                                            final OCRecursiveVisitor ocRecursiveVisitor3 = this;
                                            ++ocRecursiveVisitor3.directivesAtZeroNestLevel;
                                            final OCRecursiveVisitor ocRecursiveVisitor4 = this;
                                            final int n5 = ocRecursiveVisitor4.directivesAtZeroNestLevel;
                                            final int n6 = 2;
                                            if (n5 > n6) {
                                                break Label_0180;
                                            }
                                            break Label_0199;
                                        }
                                        catch (1CancelException ex6) {
                                            throw b(ex6);
                                        }
                                    }
                                    try {
                                        final OCRecursiveVisitor ocRecursiveVisitor3 = this;
                                        ++ocRecursiveVisitor3.directivesAtZeroNestLevel;
                                        final OCRecursiveVisitor ocRecursiveVisitor4 = this;
                                        final int n5 = ocRecursiveVisitor4.directivesAtZeroNestLevel;
                                        final int n6 = 2;
                                        if (n5 > n6) {
                                            array[0] = null;
                                            throw new 1CancelException();
                                        }
                                    }
                                    catch (1CancelException ex7) {
                                        throw b(ex7);
                                    }
                                }
                                try {
                                    if (OCTokenTypes.IF_DIRECTIVES.contains(elementType)) {
                                        ++this.nestLevel;
                                    }
                                }
                                catch (1CancelException ex8) {
                                    throw b(ex8);
                                }
                            }
                            super.visitDirective(ocDirective);
                        }
                        
                        private static 1CancelException b(final 1CancelException ex) {
                            return ex;
                        }
                    });
                    if (b) {
                        return array[0];
                    }
                    final PsiElement[] array4 = array2;
                    final int n = 0;
                    final PsiElement psiElement = array4[n];
                    final PsiElement[] array5 = array3;
                    final int n2 = 0;
                    final PsiElement psiElement2 = array5[n2];
                    if (psiElement != psiElement2) {
                        break Label_0111;
                    }
                    return array[0];
                }
                catch (OCElementUtil.1CancelException ex2) {
                    throw b(ex2);
                }
                try {
                    final PsiElement[] array4 = array2;
                    final int n = 0;
                    final PsiElement psiElement = array4[n];
                    final PsiElement[] array5 = array3;
                    final int n2 = 0;
                    final PsiElement psiElement2 = array5[n2];
                    if (psiElement != psiElement2) {
                        array[0] = null;
                    }
                }
                catch (OCElementUtil.1CancelException ex3) {
                    throw b(ex3);
                }
            }
        }
        catch (OCElementUtil.1CancelException ex4) {}
        return array[0];
    }
    
    @Nullable
    public static <T extends PsiElement> SmartPsiElementPointer<T> createPsiElementPointer(@Nullable final T t) {
        try {
            if (t == null) {
                return null;
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        for (final PsiElement psiElement : t.getChildren()) {
            Label_0073: {
                try {
                    if (!psiElement.getTextRange().equals((Object)t.getTextRange())) {
                        break Label_0073;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final Class<? extends PsiElement> clazz = psiElement2.getClass();
                    final PsiElement psiElement3 = t;
                    final Class<? extends PsiElement> clazz2 = psiElement3.getClass();
                    final boolean b = clazz.equals(clazz2);
                    if (b) {
                        break Label_0073;
                    }
                    break Label_0073;
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final Class<? extends PsiElement> clazz = psiElement2.getClass();
                    final PsiElement psiElement3 = t;
                    final Class<? extends PsiElement> clazz2 = psiElement3.getClass();
                    final boolean b = clazz.equals(clazz2);
                    if (b) {
                        return (SmartPsiElementPointer<T>)createPsiElementPointer(psiElement);
                    }
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
            }
        }
        return (SmartPsiElementPointer<T>)SmartPointerManager.getInstance(t.getProject()).createSmartPsiElementPointer((PsiElement)t, t.getContainingFile());
    }
    
    @Nullable
    public static <T extends PsiElement> T getPsiElementByPointer(@Nullable final SmartPsiElementPointer<T> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          17
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/SmartPsiElementPointer.getElement:()Lcom/intellij/psi/PsiElement;
        //    10: goto            18
        //    13: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    16: athrow         
        //    17: aconst_null    
        //    18: astore_1       
        //    19: aload_1        
        //    20: ifnull          88
        //    23: aload_1        
        //    24: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //    29: astore_2       
        //    30: aload_2        
        //    31: ifnull          77
        //    34: aload_2        
        //    35: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    38: aload_1        
        //    39: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    42: if_acmpne       77
        //    45: goto            52
        //    48: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    51: athrow         
        //    52: aload_2        
        //    53: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    58: aload_1        
        //    59: invokeinterface com/intellij/psi/PsiElement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //    64: invokevirtual   com/intellij/openapi/util/TextRange.equals:(Ljava/lang/Object;)Z
        //    67: ifne            83
        //    70: goto            77
        //    73: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    76: athrow         
        //    77: aload_1        
        //    78: areturn        
        //    79: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    82: athrow         
        //    83: aload_2        
        //    84: astore_1       
        //    85: goto            19
        //    88: aconst_null    
        //    89: areturn        
        //    Signature:
        //  <T:Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/SmartPsiElementPointer<TT;>;)TT; [from metadata: <T::Lcom/intellij/psi/PsiElement;>(Lcom/intellij/psi/SmartPsiElementPointer<TT;>;)TT;]
        //  
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      13     13     17     Ljava/lang/NumberFormatException;
        //  30     45     48     52     Ljava/lang/NumberFormatException;
        //  34     70     73     77     Ljava/lang/NumberFormatException;
        //  52     79     79     83     Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0052:
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
    
    @Contract("null -> false")
    public static boolean isMissingSemicolonError(@Nullable final PsiElement psiElement) {
        Label_0025: {
            try {
                if (isErrorWithTextAtEnd(psiElement, "Missing ';'")) {
                    break Label_0025;
                }
                final PsiElement psiElement2 = psiElement;
                final String s = "Expecting ';'";
                final boolean b = isErrorWithTextAtEnd(psiElement2, s);
                if (b) {
                    break Label_0025;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final String s = "Expecting ';'";
                final boolean b = isErrorWithTextAtEnd(psiElement2, s);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Contract("null -> false")
    public static boolean isMissingRParError(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ldc             "Missing ')'"
        //     3: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //     6: ifne            41
        //     9: aload_0        
        //    10: ldc             "Expecting ')'"
        //    12: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //    15: ifne            41
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    24: athrow         
        //    25: aload_0        
        //    26: ldc             "',' or ')' expected"
        //    28: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //    31: ifeq            49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    48: athrow         
        //    49: iconst_0       
        //    50: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      18     21     25     Ljava/lang/NumberFormatException;
        //  9      34     37     41     Ljava/lang/NumberFormatException;
        //  25     45     45     49     Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    @Contract("null -> false")
    public static boolean isMissingCommaError(@Nullable final PsiElement psiElement) {
        return isErrorWithTextAtEnd(psiElement, "Expecting ','");
    }
    
    @Contract("null -> false")
    public static boolean isIncompleteExpressionError(@Nullable final PsiElement p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ldc             "Expression expected"
        //     3: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //     6: ifne            41
        //     9: aload_0        
        //    10: ldc             "Declaration or expression expected"
        //    12: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //    15: ifne            41
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    24: athrow         
        //    25: aload_0        
        //    26: ldc             "Unexpected end of file"
        //    28: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isErrorWithTextAtEnd:(Lcom/intellij/psi/PsiElement;Ljava/lang/String;)Z
        //    31: ifeq            49
        //    34: goto            41
        //    37: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    40: athrow         
        //    41: iconst_1       
        //    42: goto            50
        //    45: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    48: athrow         
        //    49: iconst_0       
        //    50: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  0      18     21     25     Ljava/lang/NumberFormatException;
        //  9      34     37     41     Ljava/lang/NumberFormatException;
        //  25     45     45     49     Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    
    @Contract("null, _ -> false")
    public static boolean isErrorWithTextAtEnd(@Nullable final PsiElement psiElement, final String s) {
        Label_0030: {
            try {
                if (!(psiElement instanceof PsiErrorElement)) {
                    return false;
                }
                final PsiElement psiElement2 = psiElement;
                final PsiErrorElement psiErrorElement = (PsiErrorElement)psiElement2;
                final String s2 = psiErrorElement.getErrorDescription();
                final String s3 = s;
                final boolean b = s2.endsWith(s3);
                if (b) {
                    break Label_0030;
                }
                return false;
            }
            catch (NumberFormatException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final PsiErrorElement psiErrorElement = (PsiErrorElement)psiElement2;
                final String s2 = psiErrorElement.getErrorDescription();
                final String s3 = s;
                final boolean b = s2.endsWith(s3);
                if (b) {
                    return true;
                }
            }
            catch (NumberFormatException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    @Nullable
    @Contract("null, _ -> null")
    public static PsiElement getNextSiblingOfAnyType(@Nullable final PsiElement psiElement, @NotNull final TokenSet set) {
        try {
            if (set == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "types", "com/jetbrains/cidr/lang/util/OCElementUtil", "getNextSiblingOfAnyType"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement == null) {
                return null;
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        PsiElement psiElement2 = psiElement.getNextSibling();
        while (true) {
            Label_0089: {
                try {
                    if (psiElement2 == null) {
                        break;
                    }
                    final TokenSet set2 = set;
                    final PsiElement psiElement3 = psiElement2;
                    final IElementType elementType = getElementType(psiElement3);
                    final boolean b = set2.contains(elementType);
                    if (b) {
                        return psiElement2;
                    }
                    break Label_0089;
                }
                catch (NumberFormatException ex3) {
                    throw b(ex3);
                }
                try {
                    final TokenSet set2 = set;
                    final PsiElement psiElement3 = psiElement2;
                    final IElementType elementType = getElementType(psiElement3);
                    final boolean b = set2.contains(elementType);
                    if (b) {
                        return psiElement2;
                    }
                }
                catch (NumberFormatException ex4) {
                    throw b(ex4);
                }
            }
            psiElement2 = psiElement2.getNextSibling();
        }
        return null;
    }
    
    @Nullable
    public static String getSymbolName(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/util/OCElementUtil", "getSymbolName"));
            }
        }
        catch (NumberFormatException ex) {
            throw b(ex);
        }
        try {
            if (psiElement instanceof OCSymbolNameOwner) {
                return ((OCSymbolNameOwner)psiElement).getSymbolName();
            }
        }
        catch (NumberFormatException ex2) {
            throw b(ex2);
        }
        try {
            if (psiElement instanceof PsiNamedElement) {
                return ((PsiNamedElement)psiElement).getName();
            }
        }
        catch (NumberFormatException ex3) {
            throw b(ex3);
        }
        return null;
    }
    
    @Nullable
    public static OCSymbol<?> getRawSymbolFromNamedElement(final PsiElement psiElement) {
        OCSymbol<?> ocSymbol = null;
        if (psiElement instanceof OCSymbolDeclarator) {
            ocSymbol = ((OCSymbolDeclarator)psiElement).getSymbol();
        }
        else {
            final PsiReference reference = psiElement.getReference();
            if (reference instanceof OCOperatorReference) {
                ocSymbol = (OCSymbol<?>)((OCOperatorReference)reference).resolveToSymbol();
            }
        }
        return ocSymbol;
    }
    
    @Nullable
    public static PsiElement findRenameTargetDefinition(@Nullable final PsiElement psiElement, final boolean b) {
        return substituteNamedElementToRename(PsiTreeUtil.getNonStrictParentOfType(psiElement, new Class[] { OCCallable.class, OCConstructorFieldInitializer.class, OCUDLiteralExpression.class, OCCppNewExpression.class, OCCallExpression.class, OCSendMessageExpression.class, OCSelectorExpression.class }), b);
    }
    
    @Nullable
    @Contract("null, _ -> null")
    public static PsiElement substituteNamedElementToRename(@Nullable final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //     4: ifeq            66
        //     7: aload_0        
        //     8: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallExpression;
        //    11: invokeinterface com/jetbrains/cidr/lang/psi/OCCallExpression.getFunctionReferenceExpression:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //    16: astore_2       
        //    17: aload_2        
        //    18: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    21: ifeq            42
        //    24: aload_2        
        //    25: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReferenceExpression;
        //    28: invokeinterface com/jetbrains/cidr/lang/psi/OCReferenceExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    33: iload_1        
        //    34: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Lcom/intellij/psi/PsiReference;Z)Lcom/intellij/psi/PsiElement;
        //    37: areturn        
        //    38: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    41: athrow         
        //    42: aload_2        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/psi/OCQualifiedExpression;
        //    46: ifeq            64
        //    49: aload_2        
        //    50: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getReference:()Lcom/intellij/psi/PsiReference;
        //    55: iload_1        
        //    56: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Lcom/intellij/psi/PsiReference;Z)Lcom/intellij/psi/PsiElement;
        //    59: areturn        
        //    60: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    63: athrow         
        //    64: aconst_null    
        //    65: areturn        
        //    66: aload_0        
        //    67: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //    70: ifeq            91
        //    73: aload_0        
        //    74: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCppNewExpression;
        //    77: invokeinterface com/jetbrains/cidr/lang/psi/OCCppNewExpression.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //    82: iconst_0       
        //    83: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Lcom/intellij/psi/PsiReference;Z)Lcom/intellij/psi/PsiElement;
        //    86: areturn        
        //    87: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    90: athrow         
        //    91: aload_0        
        //    92: instanceof      Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //    95: ifeq            116
        //    98: aload_0        
        //    99: checkcast       Lcom/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer;
        //   102: invokeinterface com/jetbrains/cidr/lang/psi/OCConstructorFieldInitializer.getReferenceElement:()Lcom/jetbrains/cidr/lang/psi/OCReferenceElement;
        //   107: iconst_0       
        //   108: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Lcom/intellij/psi/PsiReference;Z)Lcom/intellij/psi/PsiElement;
        //   111: areturn        
        //   112: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   115: athrow         
        //   116: aload_0        
        //   117: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSendMessageExpression;
        //   120: ifne            137
        //   123: aload_0        
        //   124: instanceof      Lcom/jetbrains/cidr/lang/psi/OCSelectorExpression;
        //   127: ifeq            199
        //   130: goto            137
        //   133: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   136: athrow         
        //   137: aload_0        
        //   138: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
        //   143: checkcast       Lcom/jetbrains/cidr/lang/psi/OCPolyVariantReference;
        //   146: astore_2       
        //   147: aload_2        
        //   148: ifnonnull       157
        //   151: aconst_null    
        //   152: areturn        
        //   153: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   156: athrow         
        //   157: aload_2        
        //   158: invokeinterface com/jetbrains/cidr/lang/psi/OCPolyVariantReference.resolveToSymbols:()Ljava/util/List;
        //   163: astore_3       
        //   164: aload_3        
        //   165: invokeinterface java/util/List.size:()I
        //   170: ifne            179
        //   173: aconst_null    
        //   174: areturn        
        //   175: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   178: athrow         
        //   179: aload_3        
        //   180: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   185: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   190: checkcast       Lcom/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol;
        //   193: invokeinterface com/jetbrains/cidr/lang/symbols/objc/OCMethodSymbol.locateDefinition:()Lcom/intellij/psi/PsiElement;
        //   198: areturn        
        //   199: aload_0        
        //   200: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   203: ifeq            285
        //   206: aload_0        
        //   207: checkcast       Lcom/jetbrains/cidr/lang/psi/OCCallable;
        //   210: invokeinterface com/jetbrains/cidr/lang/psi/OCCallable.getBody:()Lcom/jetbrains/cidr/lang/psi/OCBlockStatement;
        //   215: astore_2       
        //   216: aload_2        
        //   217: ifnull          245
        //   220: aload_2        
        //   221: invokeinterface com/jetbrains/cidr/lang/psi/OCBlockStatement.getTextRange:()Lcom/intellij/openapi/util/TextRange;
        //   226: aload_0        
        //   227: invokeinterface com/intellij/psi/PsiElement.getTextOffset:()I
        //   232: invokevirtual   com/intellij/openapi/util/TextRange.contains:(I)Z
        //   235: ifne            283
        //   238: goto            245
        //   241: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   244: athrow         
        //   245: iload_1        
        //   246: ifeq            281
        //   249: goto            256
        //   252: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   255: athrow         
        //   256: aload_0        
        //   257: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //   260: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_KR_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //   263: if_acmpne       281
        //   266: goto            273
        //   269: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   272: athrow         
        //   273: aconst_null    
        //   274: goto            282
        //   277: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   280: athrow         
        //   281: aload_0        
        //   282: areturn        
        //   283: aconst_null    
        //   284: areturn        
        //   285: aload_0        
        //   286: instanceof      Lcom/jetbrains/cidr/lang/psi/OCUDLiteralExpression;
        //   289: ifeq            307
        //   292: aload_0        
        //   293: invokeinterface com/intellij/psi/PsiElement.getReference:()Lcom/intellij/psi/PsiReference;
        //   298: iconst_0       
        //   299: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.a:(Lcom/intellij/psi/PsiReference;Z)Lcom/intellij/psi/PsiElement;
        //   302: areturn        
        //   303: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   306: athrow         
        //   307: aload_0        
        //   308: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  17     38     38     42     Ljava/lang/NumberFormatException;
        //  42     60     60     64     Ljava/lang/NumberFormatException;
        //  66     87     87     91     Ljava/lang/NumberFormatException;
        //  91     112    112    116    Ljava/lang/NumberFormatException;
        //  116    130    133    137    Ljava/lang/NumberFormatException;
        //  147    153    153    157    Ljava/lang/NumberFormatException;
        //  164    175    175    179    Ljava/lang/NumberFormatException;
        //  216    238    241    245    Ljava/lang/NumberFormatException;
        //  220    249    252    256    Ljava/lang/NumberFormatException;
        //  245    266    269    273    Ljava/lang/NumberFormatException;
        //  256    277    277    281    Ljava/lang/NumberFormatException;
        //  285    303    303    307    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0245:
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
    @Contract("null, _ -> null")
    private static PsiElement a(@Nullable final PsiReference p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnull          106
        //     4: aload_0        
        //     5: invokeinterface com/intellij/psi/PsiReference.resolve:()Lcom/intellij/psi/PsiElement;
        //    10: astore_2       
        //    11: aload_2        
        //    12: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    15: ifeq            72
        //    18: aload_2        
        //    19: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    22: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getExtendedContext:()Lcom/intellij/psi/PsiElement;
        //    27: astore_3       
        //    28: aload_3        
        //    29: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFunctionDeclaration;
        //    32: ifeq            69
        //    35: iload_1        
        //    36: ifeq            63
        //    39: goto            46
        //    42: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    45: athrow         
        //    46: aload_3        
        //    47: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.getElementType:(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;
        //    50: getstatic       com/jetbrains/cidr/lang/parser/OCElementTypes.FUNCTION_KR_DEFINITION:Lcom/jetbrains/cidr/lang/parser/OCElementType;
        //    53: if_acmpeq       69
        //    56: goto            63
        //    59: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    62: athrow         
        //    63: aload_3        
        //    64: areturn        
        //    65: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //    68: athrow         
        //    69: goto            106
        //    72: aload_0        
        //    73: instanceof      Lcom/jetbrains/cidr/lang/psi/OCReference;
        //    76: ifeq            106
        //    79: aload_0        
        //    80: checkcast       Lcom/jetbrains/cidr/lang/psi/OCReference;
        //    83: invokeinterface com/jetbrains/cidr/lang/psi/OCReference.resolveToSymbol:()Lcom/jetbrains/cidr/lang/symbols/OCSymbol;
        //    88: astore_3       
        //    89: aload_3        
        //    90: ifnull          106
        //    93: new             Lcom/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement;
        //    96: dup            
        //    97: aload_3        
        //    98: invokespecial   com/jetbrains/cidr/lang/symbols/OCSymbolHolderVirtualPsiElement.<init>:(Lcom/jetbrains/cidr/lang/symbols/OCSymbol;)V
        //   101: areturn        
        //   102: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.b:(Ljava/lang/RuntimeException;)Ljava/lang/RuntimeException;
        //   105: athrow         
        //   106: aconst_null    
        //   107: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  28     39     42     46     Ljava/lang/NumberFormatException;
        //  35     56     59     63     Ljava/lang/NumberFormatException;
        //  46     65     65     69     Ljava/lang/NumberFormatException;
        //  89     102    102    106    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0046:
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
        NONE_DEC = new OCIntType[] { OCIntType.INT, OCIntType.LONG, OCIntType.LONGLONG, OCIntType.INT128 };
        NONE_HEX = new OCIntType[] { OCIntType.INT, OCIntType.UINT, OCIntType.LONG, OCIntType.ULONG, OCIntType.LONGLONG, OCIntType.ULONGLONG, OCIntType.INT128, OCIntType.UINT128 };
        U_DEC = new OCIntType[] { OCIntType.UINT, OCIntType.ULONG, OCIntType.ULONGLONG, OCIntType.UINT128 };
        U_HEX = OCElementUtil.U_DEC;
        L_DEC = new OCIntType[] { OCIntType.LONG, OCIntType.LONGLONG, OCIntType.INT128 };
        L_HEX = new OCIntType[] { OCIntType.LONG, OCIntType.ULONG, OCIntType.LONGLONG, OCIntType.ULONGLONG, OCIntType.INT128, OCIntType.UINT128 };
        UL_DEC = new OCIntType[] { OCIntType.ULONG, OCIntType.ULONGLONG, OCIntType.INT128 };
        UL_HEX = OCElementUtil.UL_DEC;
        LL_DEC = new OCIntType[] { OCIntType.LONGLONG, OCIntType.INT128 };
        LL_HEX = new OCIntType[] { OCIntType.LONGLONG, OCIntType.ULONGLONG, OCIntType.INT128, OCIntType.UINT128 };
        ULL_DEC = new OCIntType[] { OCIntType.ULONGLONG, OCIntType.UINT128 };
        ULL_HEX = OCElementUtil.ULL_DEC;
        GOOD_FOR_RENAME = TokenSet.orSet(new TokenSet[] { OCTokenTypes.POSSIBLE_ID_NAMES, TokenSet.create(new IElementType[] { OCTokenTypes.THIS_CPP_KEYWORD }) });
        ELEMENT_NON_WHITESPACE_CONDITION = (psiElement -> {
            Label_0027: {
                try {
                    if (psiElement instanceof OCEmptyName) {
                        return false;
                    }
                    final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                    final PsiElement psiElement2 = psiElement;
                    final IElementType elementType = getElementType(psiElement2);
                    final boolean b = set.contains(elementType);
                    if (!b) {
                        break Label_0027;
                    }
                    return false;
                }
                catch (NumberFormatException ex) {
                    throw b(ex);
                }
                try {
                    final TokenSet set = OCTokenTypes.WHITE_SPACE_OR_COMMENT_BIT_SET;
                    final PsiElement psiElement2 = psiElement;
                    final IElementType elementType = getElementType(psiElement2);
                    final boolean b = set.contains(elementType);
                    if (!b) {
                        return true;
                    }
                }
                catch (NumberFormatException ex2) {
                    throw b(ex2);
                }
            }
            return false;
        });
    }
    
    private static RuntimeException b(final RuntimeException ex) {
        return ex;
    }
}
