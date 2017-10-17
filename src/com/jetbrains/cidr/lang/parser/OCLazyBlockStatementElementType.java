// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.parser;

import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.lang.ForeignLeafType;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.intellij.psi.impl.source.DummyHolder;
import com.intellij.lang.PsiBuilder;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.Producer;
import com.jetbrains.cidr.lang.preprocessor.OCPreprocessingLexer;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.openapi.project.Project;
import com.intellij.lang.Language;
import com.intellij.lang.ASTNode;
import com.jetbrains.cidr.lang.psi.impl.OCLazyElementBase;
import com.jetbrains.cidr.lang.psi.impl.OCLazyBlockStatementImpl;
import org.jetbrains.annotations.Nullable;

public class OCLazyBlockStatementElementType extends OCReparseablePsiElementType
{
    @Nullable
    private static LazyParsingCallback ourGlobalLazyParsingCallback;
    
    public OCLazyBlockStatementElementType() {
        super("LAZY_BLOCK", OCLazyBlockStatementImpl.class);
    }
    
    public static void setGlobalLazyParsingCallback(@Nullable final LazyParsingCallback ourGlobalLazyParsingCallback) {
        OCLazyBlockStatementElementType.ourGlobalLazyParsingCallback = ourGlobalLazyParsingCallback;
    }
    
    public boolean isParsable(@Nullable final ASTNode astNode, final CharSequence charSequence, final Language language, final Project project) {
        final PsiFile psiFile = (astNode != null) ? astNode.getPsi().getContainingFile() : null;
        if (psiFile == null) {
            return false;
        }
        final int startOffset = astNode.getPsi().getTextRange().getStartOffset();
        final OCFile ocFile = (OCFile)psiFile;
        synchronized (OCParser.partialContextHolder(psiFile)) {
            final OCPreprocessingLexer ocPreprocessingLexer = new OCPreprocessingLexer(OCParser.updatePartialContext(astNode.getPsi(), OCParser.evaluatePartialContext(ocFile, startOffset).derive()), ocFile);
            ocPreprocessingLexer.start(charSequence);
            return isSafeBlock((Producer<IElementType>)(() -> {
                final IElementType tokenType = ocPreprocessingLexer.getTokenType();
                if (tokenType != null) {
                    ocPreprocessingLexer.advance();
                }
                return tokenType;
            }), OCTokenTypes.LBRACE, OCTokenTypes.RBRACE) && ocPreprocessingLexer.getTokenType() == null;
        }
    }
    
    public ASTNode doParse(final ASTNode astNode, final PsiBuilder psiBuilder) {
        final OCParsing ocParsing = new OCParsing(psiBuilder, astNode.getElementType());
        PsiElement context = astNode.getPsi().getContext();
        if (context instanceof DummyHolder) {
            final PsiElement context2 = context.getContext();
            context = ((context2 != null) ? context2.getContext() : null);
        }
        if (OCLazyBlockStatementElementType.ourGlobalLazyParsingCallback != null) {
            OCLazyBlockStatementElementType.ourGlobalLazyParsingCallback.onParsingLazyBlock(astNode, psiBuilder, ocParsing);
        }
        ocParsing.parseCompoundStatement(context instanceof OCMethod, false);
        return ocParsing.getTreeBuilt();
    }
    
    public static boolean isSafeBlock(final Producer<IElementType> producer, final IElementType elementType, final IElementType elementType2) {
        final IElementType elementType3 = (IElementType)producer.produce();
        if (elementType3 != elementType || elementType3 instanceof ForeignLeafType) {
            return false;
        }
        int i = 1;
        int n = 0;
        boolean b = true;
        while (i > 0) {
            final IElementType elementType4 = (IElementType)producer.produce();
            final IElementType unwrappedTokeType = OCElementUtil.getUnwrappedTokeType(elementType4);
            if (OCTokenTypes.BLOCK_UNSAFE_DIRECTIVES.contains(unwrappedTokeType)) {
                b = false;
            }
            if (unwrappedTokeType == elementType) {
                ++i;
            }
            else if (unwrappedTokeType == elementType2) {
                if (--i != 0 || !(elementType4 instanceof ForeignLeafType)) {
                    continue;
                }
                b = false;
            }
            else if (OCTokenTypes.IF_DIRECTIVES.contains(unwrappedTokeType)) {
                ++n;
            }
            else if (unwrappedTokeType == OCTokenTypes.ENDIF_DIRECTIVE) {
                if (--n >= 0) {
                    continue;
                }
                b = false;
            }
            else {
                if (unwrappedTokeType == null) {
                    b = false;
                    break;
                }
                continue;
            }
        }
        return b && n == 0;
    }
    
    static {
        OCLazyBlockStatementElementType.ourGlobalLazyParsingCallback = null;
    }
    
    public interface LazyParsingCallback
    {
        void onParsingLazyBlock(final ASTNode p0, final PsiBuilder p1, final OCParsing p2);
    }
}
