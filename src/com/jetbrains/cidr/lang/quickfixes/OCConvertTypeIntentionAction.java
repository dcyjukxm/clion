// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.types.OCReferenceType;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.types.OCObjectType;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCMessageArgument;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.refactoring.OCNameSuggester;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.types.OCPointerType;
import com.jetbrains.cidr.lang.types.OCTollFreeBridges;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.codeInsight.intention.HighPriorityAction;
import com.jetbrains.cidr.lang.psi.OCExpression;

public class OCConvertTypeIntentionAction extends OCPsiElementQuickFix<OCExpression> implements HighPriorityAction
{
    private OCType myType;
    static final /* synthetic */ boolean $assertionsDisabled;
    
    public OCConvertTypeIntentionAction(final OCExpression ocExpression, final OCType myType) {
        super((PsiElement)ocExpression);
        this.myType = myType;
        final OCType resolvedNSCounterpart = OCTollFreeBridges.getResolvedNSCounterpart(this.myType.getName(), ocExpression.getContainingFile());
        Label_0045: {
            try {
                if (resolvedNSCounterpart == null) {
                    return;
                }
                final OCType ocType = resolvedNSCounterpart;
                final boolean b = ocType instanceof OCPointerType;
                if (b) {
                    break Label_0045;
                }
                return;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCType ocType = resolvedNSCounterpart;
                final boolean b = ocType instanceof OCPointerType;
                if (b) {
                    this.myType = resolvedNSCounterpart;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
    }
    
    protected String getTextInternal() {
        final OCMethodSymbol a = this.a();
        Label_0022: {
            try {
                if (OCConvertTypeIntentionAction.$assertionsDisabled) {
                    return "Call " + a.getNameWithParent();
                }
                final OCMethodSymbol ocMethodSymbol = a;
                if (ocMethodSymbol == null) {
                    break Label_0022;
                }
                return "Call " + a.getNameWithParent();
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCMethodSymbol ocMethodSymbol = a;
                if (ocMethodSymbol == null) {
                    throw new AssertionError();
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return "Call " + a.getNameWithParent();
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Convert type";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction", "getFamilyName"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @Override
    protected boolean isAvailable(@NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction", "isAvailable"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        try {
            if (this.a() != null) {
                return true;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        return false;
    }
    
    @Override
    protected void invoke(final PsiFile psiFile, @NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction", "invoke"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCMethodSymbol a = this.a();
        try {
            if (a == null) {
                return;
            }
        }
        catch (IllegalStateException ex2) {
            throw a(ex2);
        }
        final String name = ((OCSymbolWithParent<T, OCClassSymbol>)a).getParent().getName();
        Label_0177: {
            try {
                if (!a.getName().endsWith(":") || a.getName().equals("getValue:")) {
                    break Label_0177;
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            final OCSendMessageExpression a2 = this.a((PsiElement)ocExpression, null, a.getName(), name, (PsiElement)ocExpression, ocExpression);
            Label_0148: {
                try {
                    if (a.isStatic()) {
                        return;
                    }
                    final OCSendMessageExpression ocSendMessageExpression = a2;
                    if (ocSendMessageExpression != null) {
                        break Label_0148;
                    }
                    return;
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                try {
                    final OCSendMessageExpression ocSendMessageExpression = a2;
                    if (ocSendMessageExpression != null) {
                        this.a((PsiElement)a2.getReceiverExpression(), null, "alloc", name, null, ocExpression);
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
            }
            return;
        }
        this.a((PsiElement)ocExpression, (PsiElement)ocExpression, a.getName(), name, null, ocExpression);
    }
    
    @Nullable
    private OCSendMessageExpression a(final PsiElement psiElement, @Nullable final PsiElement psiElement2, final String s, final String s2, @Nullable final PsiElement psiElement3, @NotNull final OCExpression ocExpression) {
        try {
            if (ocExpression == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/quickfixes/OCConvertTypeIntentionAction", "wrapWithMethodCall"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        String text = null;
        Label_0064: {
            try {
                if (psiElement2 != null) {
                    text = psiElement2.getText();
                    break Label_0064;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            text = s2;
        }
        final String s3 = text;
        if (s.equals("getValue:")) {
            final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCStatement.class);
            if (ocStatement != null) {
                final String suggestUniqueName = OCNameSuggester.suggestUniqueName(OCSymbolKind.LOCAL_VARIABLE, "value", psiElement);
                final OCDeclarationStatement declarationStatement = OCElementFactory.declarationStatement(suggestUniqueName, this.myType, null, psiElement);
                final OCExpressionStatement ocExpressionStatement = (OCExpressionStatement)OCElementFactory.statementFromText("[ " + s3 + " " + s + "&" + suggestUniqueName + " ]", psiElement);
                try {
                    if (psiElement2 != null) {
                        OCChangeUtil.replaceHandlingMacros((PsiElement)((OCSendMessageExpression)ocExpressionStatement.getExpression()).getReceiverExpression(), psiElement2);
                    }
                }
                catch (IllegalStateException ex3) {
                    throw a(ex3);
                }
                OCChangeUtil.addBefore(ocStatement.getParent(), declarationStatement, (PsiElement)ocStatement);
                OCChangeUtil.addBefore(ocStatement.getParent(), ocExpressionStatement, (PsiElement)ocStatement);
                OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)OCElementFactory.expressionFromText(suggestUniqueName, psiElement));
            }
            return null;
        }
        String s4 = "";
        if (psiElement3 != null) {
            s4 = psiElement3.getText();
            final PsiElement prevSibling = psiElement3.getPrevSibling();
            if (prevSibling instanceof OCMacroCall) {
                s4 = prevSibling.getText() + s4;
            }
        }
        OCSendMessageExpression ocSendMessageExpression;
        if (s.equals("value:withObjCType:")) {
            ocSendMessageExpression = (OCSendMessageExpression)OCElementFactory.expressionFromText("[ " + s3 + " value: " + s4 + " withObjCType: @encode(" + ocExpression.getResolvedType().getBestNameInContext(psiElement) + ")]", psiElement);
            try {
                if (psiElement2 != null) {
                    OCChangeUtil.replaceHandlingMacros((PsiElement)ocSendMessageExpression.getReceiverExpression(), psiElement2);
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
            final OCUnaryExpression ocUnaryExpression = (OCUnaryExpression)OCElementFactory.expressionFromText("&" + s4, psiElement);
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocUnaryExpression.getOperand(), psiElement3);
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocSendMessageExpression.getArguments().get(0).getArgumentExpression(), (PsiElement)ocUnaryExpression);
        }
        else {
            ocSendMessageExpression = (OCSendMessageExpression)OCElementFactory.expressionFromText("[ " + s3 + " " + s + s4 + " ] ", psiElement);
            try {
                if (psiElement2 != null) {
                    OCChangeUtil.replaceHandlingMacros((PsiElement)ocSendMessageExpression.getReceiverExpression(), psiElement2);
                }
            }
            catch (IllegalStateException ex5) {
                throw a(ex5);
            }
            try {
                if (psiElement3 != null) {
                    OCChangeUtil.replaceHandlingMacros((PsiElement)ocSendMessageExpression.getArguments().get(0).getArgumentExpression(), psiElement3);
                }
            }
            catch (IllegalStateException ex6) {
                throw a(ex6);
            }
        }
        return (OCSendMessageExpression)OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)ocSendMessageExpression);
    }
    
    @Nullable
    private OCMethodSymbol a() {
        final OCExpression ocExpression = (OCExpression)this.myElementPtr.getElement();
        try {
            if (ocExpression == null) {
                return null;
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        final OCType guessedType = ocExpression.getResolvedType().getGuessedType();
        Label_0354: {
            Label_0140: {
                Label_0062: {
                    try {
                        if (!this.myType.isPointerToObject()) {
                            break Label_0354;
                        }
                        final OCType ocType = guessedType;
                        final String s = ocType.getName();
                        final String s2 = "NS";
                        final boolean b = s.startsWith(s2);
                        if (b) {
                            break Label_0062;
                        }
                        break Label_0140;
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                    try {
                        final OCType ocType = guessedType;
                        final String s = ocType.getName();
                        final String s2 = "NS";
                        final boolean b = s.startsWith(s2);
                        if (!b) {
                            break Label_0140;
                        }
                        if (!this.myType.getName().equals("NSMutable" + guessedType.getName().substring(2))) {
                            break Label_0140;
                        }
                    }
                    catch (IllegalStateException ex3) {
                        throw a(ex3);
                    }
                }
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                ((OCObjectType)guessedType.getTerminalType()).processMembers("mutableCopy", OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)findFirstProcessor);
                return (OCMethodSymbol)findFirstProcessor.getFoundValue();
            }
            String s3;
            if (guessedType.isPointerToChar()) {
                s3 = "stringWith";
            }
            else if (guessedType instanceof OCStructType) {
                s3 = "valueWith";
            }
            else {
                s3 = "numberWith";
            }
            BoxingProcessor boxingProcessor = new BoxingProcessor(guessedType, s3);
            Label_0306: {
                try {
                    ((OCObjectType)this.myType.getTerminalType()).processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)boxingProcessor);
                    if (boxingProcessor.isFound() || !this.myType.isObjCRootType()) {
                        break Label_0306;
                    }
                }
                catch (IllegalStateException ex4) {
                    throw a(ex4);
                }
                final OCType resolvedFromText = OCReferenceType.resolvedFromText("NSNumber", ocExpression.getContainingFile());
                final OCType resolvedFromText2 = OCReferenceType.resolvedFromText("NSString", ocExpression.getContainingFile());
                try {
                    if (resolvedFromText instanceof OCObjectType) {
                        ((OCObjectType)resolvedFromText).processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)boxingProcessor);
                    }
                }
                catch (IllegalStateException ex5) {
                    throw a(ex5);
                }
                try {
                    if (resolvedFromText2 instanceof OCObjectType) {
                        ((OCObjectType)resolvedFromText2).processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)boxingProcessor);
                    }
                }
                catch (IllegalStateException ex6) {
                    throw a(ex6);
                }
            }
            if (!boxingProcessor.isFound()) {
                boxingProcessor = new BoxingProcessor(guessedType, "initWith");
                ((OCObjectType)this.myType.getTerminalType()).processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)boxingProcessor);
            }
            return (OCMethodSymbol)boxingProcessor.getFoundValue();
        }
        if (guessedType.isPointerToObject()) {
            final UnboxingProcessor unboxingProcessor = new UnboxingProcessor("Value");
            try {
                ((OCObjectType)guessedType.getTerminalType()).processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)unboxingProcessor);
                if (unboxingProcessor.isFound() || !"id".equals(guessedType.getName())) {
                    return (OCMethodSymbol)unboxingProcessor.getFoundValue();
                }
            }
            catch (IllegalStateException ex7) {
                throw a(ex7);
            }
            final OCType resolvedFromText3 = OCReferenceType.resolvedFromText("NSNumber", ocExpression.getContainingFile());
            try {
                if (resolvedFromText3 instanceof OCObjectType) {
                    ((OCObjectType)resolvedFromText3).processMembers(OCMethodSymbol.class, (com.intellij.util.Processor<? super OCMethodSymbol>)unboxingProcessor);
                }
            }
            catch (IllegalStateException ex8) {
                throw a(ex8);
            }
            return (OCMethodSymbol)unboxingProcessor.getFoundValue();
        }
        return null;
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCConvertTypeIntentionAction.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
    
    private class BoxingProcessor extends CommonProcessors.FindFirstProcessor<OCMethodSymbol>
    {
        private final OCType myExprType;
        private final String myMethodPrefix;
        
        public BoxingProcessor(final OCType myExprType, final String myMethodPrefix) {
            this.myExprType = myExprType;
            this.myMethodPrefix = myMethodPrefix;
        }
        
        public boolean process(final OCMethodSymbol ocMethodSymbol) {
            if ((ocMethodSymbol.getName().equals("valueWithPointer:") && this.myExprType instanceof OCPointerType && OCTollFreeBridges.getNSCounterpart(this.myExprType.getName()) == null) || (ocMethodSymbol.getName().equals("value:withObjCType:") && this.myExprType instanceof OCStructType && !OCTollFreeBridges.hasCFCounterpart(this.myExprType.getName()))) {
                return super.process((Object)ocMethodSymbol);
            }
            if (ocMethodSymbol.getName().startsWith(this.myMethodPrefix) && ocMethodSymbol.getSelectors().size() == 1 && !ocMethodSymbol.isDeprecated()) {
                final OCDeclaratorSymbol parameter = ocMethodSymbol.getSelectors().get(0).getParameter();
                if (parameter != null) {
                    final OCType resolvedType = parameter.getResolvedType();
                    if (resolvedType.equalsWithAliasName(this.myExprType, (PsiElement)OCConvertTypeIntentionAction.this.myElementPtr.getContainingFile()) || (resolvedType instanceof OCPointerType && Comparing.equal(resolvedType.getAliasName(), this.myExprType.getAliasName()) && ((OCPointerType)resolvedType).validateConstPointers(this.myExprType, OCConvertTypeIntentionAction.this.myElementPtr.getElement()).getState() == OCType.TypeCheckState.OK)) {
                        return super.process((Object)ocMethodSymbol);
                    }
                }
            }
            return true;
        }
    }
    
    private class UnboxingProcessor extends CommonProcessors.FindFirstProcessor<OCMethodSymbol>
    {
        private final String myMethodSuffix;
        
        public UnboxingProcessor(final String myMethodSuffix) {
            this.myMethodSuffix = myMethodSuffix;
        }
        
        public boolean process(final OCMethodSymbol ocMethodSymbol) {
            return ((!ocMethodSymbol.getName().equals("pointerValue") || !(OCConvertTypeIntentionAction.this.myType instanceof OCPointerType) || OCConvertTypeIntentionAction.this.myType.isPointerToObject() || OCTollFreeBridges.getNSCounterpart(OCConvertTypeIntentionAction.this.myType.getName()) != null) && (!ocMethodSymbol.getName().equals("getValue:") || !(OCConvertTypeIntentionAction.this.myType instanceof OCStructType) || OCTollFreeBridges.hasCFCounterpart(OCConvertTypeIntentionAction.this.myType.getName())) && (!ocMethodSymbol.getName().endsWith(this.myMethodSuffix) || !ocMethodSymbol.getReturnType().equalsWithAliasName(OCConvertTypeIntentionAction.this.myType, (PsiElement)OCConvertTypeIntentionAction.this.myElementPtr.getContainingFile()) || ocMethodSymbol.isStatic())) || super.process((Object)ocMethodSymbol);
        }
    }
}
