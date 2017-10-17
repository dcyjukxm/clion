// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring;

import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCFunctionUsage;
import com.jetbrains.cidr.lang.refactoring.changeSignature.usages.OCMethodCallUsage;
import com.jetbrains.cidr.lang.dfa.OCUnreachableCodeFinder;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.refactoring.RefactoringBundle;
import com.jetbrains.cidr.lang.search.scopes.OCSearchScope;
import com.jetbrains.cidr.lang.search.OCSearchUtil;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import java.util.HashSet;
import com.intellij.usageView.UsageInfo;
import com.intellij.psi.SmartPsiElementPointer;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.SmartPointerManager;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.util.containers.ContainerUtil;
import com.jetbrains.cidr.lang.refactoring.move.OCCodeMoveValidator;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.daemon.OCGetSymbolVisitor;
import com.jetbrains.cidr.lang.psi.OCCallExpression;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.psi.OCReturnStatement;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.util.Iterator;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import com.jetbrains.cidr.lang.types.OCUnknownType;
import com.jetbrains.cidr.lang.util.OCExpectedTypeUtil;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.psi.OCUnaryExpression;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import com.intellij.psi.PsiReference;
import java.util.Collection;
import com.jetbrains.cidr.lang.types.OCCppReferenceType;
import com.jetbrains.cidr.lang.types.OCTypeUtils;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCParameterInfo;
import java.util.Map;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.Collections;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureActionHandler;
import com.jetbrains.cidr.lang.psi.OCLambdaExpression;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFunctionDefinition;
import com.jetbrains.cidr.lang.psi.OCMethod;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCBlockExpression;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCCallable;
import com.jetbrains.cidr.lang.types.OCType;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCCallableKind;
import com.intellij.openapi.actionSystem.DataContext;
import com.jetbrains.cidr.lang.refactoring.changeSignature.OCChangeSignatureHandler;
import com.jetbrains.cidr.lang.dfa.OCDataFlowAnalyzer;

public class OCExtractMethodProcessor
{
    private OCDataFlowAnalyzer analyzer;
    private OCChangeSignatureHandler handler;
    private DataContext dataContext;
    private OCCallableKind callableKind;
    private Project project;
    private TextRange selection;
    private PsiElement firstElement;
    private PsiElement[] elements;
    private OCType parentReturnType;
    private boolean nonVoidParentReturnType;
    private boolean hasExitNodes;
    private boolean hasStatementsAfter;
    private OCCallable parentCallable;
    private OCSymbol parentCallableSymbol;
    
    OCExtractMethodProcessor(final PsiElement[] elements, final Project project, final TextRange selection, final DataContext dataContext) {
        this.elements = elements;
        this.project = project;
        this.selection = selection;
        this.dataContext = dataContext;
    }
    
    public void invoke(final Editor editor) {
        FeatureUsageTracker.getInstance().triggerFeatureUsed("refactoring.extractMethod");
        if (this.elements.length == 0) {
            return;
        }
        this.firstElement = this.elements[0];
        if (!CommonRefactoringUtil.checkReadOnlyStatus(this.project, this.firstElement)) {
            return;
        }
        if (!this.a(editor)) {
            return;
        }
        if (!ApplicationManager.getApplication().isUnitTestMode()) {
            this.handler.setName("");
        }
        ApplicationManager.getApplication().runWriteAction(() -> this.a());
        final List list;
        final PsiElement[] elements;
        int length;
        int i;
        PsiElement psiElement;
        this.handler.getGeneratedInfo().runOnSuccess(() -> {
            this.handler.getNewCallables();
            if (list != null && !list.isEmpty()) {
                elements = this.elements;
                for (length = elements.length; i < length; ++i) {
                    psiElement = elements[i];
                    if (psiElement.isValid()) {
                        OCChangeUtil.deleteUnsafe(psiElement);
                    }
                }
                if (this.callableKind == OCCallableKind.BLOCK) {
                    this.a((OCBlockExpression)this.handler.getChangeInfo().getMethod());
                }
            }
            return;
        });
        this.handler.invoke();
    }
    
    private boolean a(final Editor editor) {
        this.parentCallable = (OCCallable)PsiTreeUtil.getParentOfType(this.firstElement, new Class[] { OCMethod.class, OCFunctionDefinition.class });
        if (this.parentCallable == null) {
            this.parentCallable = (OCCallable)PsiTreeUtil.getParentOfType(this.firstElement, new Class[] { OCBlockExpression.class, OCLambdaExpression.class });
        }
        if (this.parentCallable == null) {
            return false;
        }
        this.parentCallableSymbol = this.parentCallable.getSymbol();
        OCSymbol parent = null;
        boolean static1 = false;
        this.callableKind = (OCCallableKind)OCChangeSignatureActionHandler.CALLABLE_KIND.getData(this.dataContext);
        this.parentReturnType = null;
        if (this.callableKind == null) {
            this.callableKind = ((this.parentCallable instanceof OCMethod) ? OCCallableKind.METHOD : OCCallableKind.FUNCTION);
        }
        OCCallable methodFromSignature;
        if (this.parentCallableSymbol instanceof OCMethodSymbol && this.callableKind != OCCallableKind.BLOCK) {
            parent = ((OCMethodSymbol)this.parentCallableSymbol).getParent();
            methodFromSignature = OCElementFactory.methodFromSignature((((OCMethodSymbol)this.parentCallableSymbol).isStatic() ? "+" : "-") + "(void) f", this.parentCallable.getParent(), true, false);
        }
        else {
            String s = "";
            if (this.parentCallableSymbol instanceof OCSymbolWithQualifiedName) {
                final String nameWithParent = ((OCSymbolWithQualifiedName)this.parentCallableSymbol).getQualifiedName().getNameWithParent();
                final int lastIndex = nameWithParent.lastIndexOf("::");
                s = ((lastIndex > 0) ? nameWithParent.substring(0, lastIndex + 2) : "");
            }
            String s2 = "void " + s + "f()";
            if (this.b()) {
                s2 += " const";
            }
            if (this.parentCallableSymbol != null && this.parentCallableSymbol.getType().isVolatile()) {
                s2 += " volatile";
            }
            methodFromSignature = (OCCallable)OCElementFactory.topLevelDeclarationFromText(s2 + "{}", this.parentCallable.getParent());
            static1 = (this.parentCallableSymbol instanceof OCFunctionSymbol && ((OCFunctionSymbol)this.parentCallableSymbol).resolveIsStatic());
        }
        this.parentReturnType = this.parentCallable.getReturnType().resolve((PsiFile)this.parentCallable.getContainingOCFile());
        this.nonVoidParentReturnType = !this.parentReturnType.isVoid();
        this.handler = OCChangeSignatureActionHandler.getHandler(methodFromSignature, OCChangeUtil.getRealAnchorForInsertion(OCChangeUtil.getAppropriateParent(this.callableKind.getSymbolKind(), this.firstElement), this.firstElement));
        switch (this.callableKind) {
            case METHOD: {
                this.handler.setTitle("Extract Method");
                break;
            }
            case FUNCTION: {
                this.handler.setTitle("Extract Function");
                break;
            }
            case BLOCK: {
                this.handler.setTitle("Extract Block Parameter");
                break;
            }
            case LAMBDA: {
                return false;
            }
        }
        this.handler.setHelpId("refactoring.extractMethod");
        this.handler.setRefactorButtonText("Extract");
        this.handler.setCallableKind(this.callableKind);
        if (this.callableKind == OCCallableKind.BLOCK) {
            this.e();
        }
        PsiElement firstElement = this.firstElement;
        for (PsiElement psiElement = OCElementUtil.getPrevSiblingOrParentSibling(firstElement); psiElement instanceof OCMacroCall; psiElement = OCElementUtil.getPrevSiblingOrParentSibling(firstElement)) {
            firstElement = psiElement;
        }
        this.handler.setChangeUsages(false);
        this.handler.getGeneratedInfo().setMethodReference(PsiTreeUtil.prevLeaf(firstElement));
        this.handler.getGeneratedInfo().setMethodParent(parent, true, Collections.emptyList());
        this.handler.getGeneratedInfo().setStatic(static1);
        (this.analyzer = new OCDataFlowAnalyzer((PsiElement)this.parentCallable, this.selection)).buildControlFlowGraph();
        this.analyzer.analyze();
        final TextRange textRange = new TextRange(this.elements[this.elements.length - 1].getTextRange().getEndOffset(), this.parentCallable.getTextRange().getEndOffset());
        this.parentCallable.accept((PsiElementVisitor)new OCRecursiveVisitor(textRange) {
            @Override
            public void visitStatement(final OCStatement ocStatement) {
                super.visitStatement(ocStatement);
                if (textRange.contains(ocStatement.getTextRange())) {
                    OCExtractMethodProcessor.this.hasStatementsAfter = true;
                }
            }
        });
        final String d = this.d();
        if (d != null) {
            CommonRefactoringUtil.showErrorHint(this.project, editor, d, OCExtractMethodHandler.REFACTORING_NAME, (String)null);
            return false;
        }
        return true;
    }
    
    private OCParameterInfo a(final List<OCSymbol> list, final Map<OCSymbol, OCParameterInfo> map, final OCSymbol ocSymbol, boolean b, final boolean b2) {
        int n;
        String name;
        if (map.containsKey(ocSymbol)) {
            this.handler.removeParameter(ocSymbol.getName(), false);
            map.remove(ocSymbol);
            n = list.indexOf(ocSymbol);
            list.set(n, ocSymbol);
            name = ocSymbol.getName();
        }
        else {
            n = list.size();
            list.add(ocSymbol);
            name = ((n == 0) ? "" : ocSymbol.getName());
        }
        final OCType extractExpressionType = OCTypeUtils.getExtractExpressionType(ocSymbol.getType(), (PsiElement)this.parentCallable, b2, b);
        if (extractExpressionType instanceof OCCppReferenceType) {
            b = false;
        }
        final OCParameterInfo addParameter = this.handler.addParameter(name, ocSymbol.getName(), extractExpressionType, n, b);
        map.put(ocSymbol, addParameter);
        addParameter.setUsages(this.analyzer.getVariableUsages(ocSymbol));
        return addParameter;
    }
    
    private void a() {
        final ArrayList<PsiElement> methodStatements = new ArrayList<PsiElement>(Arrays.asList(this.elements));
        final StringBuilder sb = new StringBuilder();
        final ArrayList<OCSymbol> list = new ArrayList<OCSymbol>();
        final HashMap<Object, OCParameterInfo> hashMap = new HashMap<Object, OCParameterInfo>();
        final List<OCSymbol> escapedDeclarators = this.analyzer.getEscapedDeclarators();
        final OCReadWriteAccessDetector ocReadWriteAccessDetector = new OCReadWriteAccessDetector();
        boolean b = false;
        if (this.hasExitNodes && this.nonVoidParentReturnType) {
            sb.append("return ");
            this.a(this.parentReturnType, false, false);
            b = true;
        }
        for (final OCSymbol ocSymbol : this.analyzer.getInputVariables()) {
            boolean b2 = false;
            boolean b3 = false;
            final Iterator<PsiReference> iterator2 = this.analyzer.getVariableUsages(ocSymbol).iterator();
            while (iterator2.hasNext()) {
                final PsiElement element = iterator2.next().getElement();
                if (element instanceof OCReferenceElement && element.getParent() instanceof OCReferenceExpression) {
                    final PsiElement parent = OCParenthesesUtils.topmostParenthesized((OCExpression)element.getParent()).getParent();
                    if (parent instanceof OCUnaryExpression && ((OCUnaryExpression)parent).isGetAddress()) {
                        b2 = true;
                    }
                }
                if (ocReadWriteAccessDetector.getExpressionAccess(element) != ReadWriteAccessDetector.Access.Read || !ocReadWriteAccessDetector.canBeConstReference(element, true)) {
                    b3 = true;
                }
            }
            final OCType resolve = ocSymbol.getType().resolve((PsiFile)this.parentCallable.getContainingOCFile());
            this.a(list, (Map<OCSymbol, OCParameterInfo>)hashMap, ocSymbol, b2 || resolve instanceof OCStructType, b2 || b3);
        }
        if (this.firstElement instanceof OCExpression && !(this.firstElement.getParent() instanceof OCExpressionStatement)) {
            OCType ocType = OCExpectedTypeUtil.getExpressionType((OCExpression)this.firstElement, true);
            if (ocType.isPointerToID() || ocType.isUnknown()) {
                final OCType expectedType = OCExpectedTypeUtil.getExpectedType((OCExpression)this.firstElement);
                if (expectedType != OCUnknownType.INSTANCE) {
                    ocType = expectedType.resolve(this.firstElement.getContainingFile());
                }
            }
            final OCType cloneWithAliasName = ocType.cloneWithAliasName(((OCExpression)this.firstElement).findBestTypeName(ocType));
            OCSymbol ocSymbol2;
            if (this.firstElement instanceof OCReferenceExpression) {
                ocSymbol2 = ((OCReferenceExpression)this.firstElement).resolveToSymbol();
            }
            else if (this.firstElement instanceof OCQualifiedExpression) {
                ocSymbol2 = ((OCQualifiedExpression)this.firstElement).resolveToSymbol();
            }
            else {
                ocSymbol2 = null;
            }
            this.a(cloneWithAliasName, ocSymbol2 != null && ocSymbol2.isGlobal(), new OCReadWriteAccessDetector().canBeConstReference(this.firstElement, true));
            b = true;
        }
        if (this.analyzer.getOutputVariables().size() > 0) {
            if (this.analyzer.getOutputVariables().size() == 1 && !(this.firstElement instanceof OCExpression) && (!this.hasExitNodes || !this.nonVoidParentReturnType) && !b) {
                this.a(methodStatements, sb, (Map<OCSymbol, OCParameterInfo>)hashMap, escapedDeclarators);
            }
            else {
                this.a(methodStatements, list, (Map<OCSymbol, OCParameterInfo>)hashMap, escapedDeclarators);
            }
        }
        this.a(methodStatements, (Set<OCSymbol>)hashMap.keySet());
        sb.append("f(");
        int n = 1;
        for (final OCSymbol ocSymbol3 : list) {
            final OCParameterInfo ocParameterInfo = hashMap.get(ocSymbol3);
            sb.append((n != 0) ? "" : ",");
            if (ocParameterInfo.isReferenceMode()) {
                sb.append('&');
            }
            sb.append(ocSymbol3.getName());
            n = 0;
        }
        sb.append(')').append((this.firstElement instanceof OCExpression) ? "" : ";");
        this.handler.getGeneratedInfo().setMethodStatements(methodStatements);
        this.handler.getGeneratedInfo().setCallString(sb.toString());
        final ArrayList<PsiElement> beforeCallStatements = new ArrayList<PsiElement>();
        final ArrayList<PsiElement> afterCallStatements = new ArrayList<PsiElement>();
        this.a(escapedDeclarators, beforeCallStatements);
        if (this.hasExitNodes && !this.nonVoidParentReturnType && this.hasStatementsAfter) {
            afterCallStatements.add(OCElementFactory.statementFromText("return;", (PsiElement)this.firstElement.getContainingFile()));
        }
        this.handler.getGeneratedInfo().setAfterCallStatements(afterCallStatements);
        this.handler.getGeneratedInfo().setBeforeCallStatements(beforeCallStatements);
    }
    
    private void a(OCType to, final boolean b, final boolean b2) {
        final OCFile containingOCFile = this.parentCallable.getContainingOCFile();
        if (b && OCTypeUtils.isPassableByReference(to.resolve((PsiFile)containingOCFile), false, (PsiElement)this.parentCallable)) {
            to = OCCppReferenceType.to(b2 ? to.cloneWithConstModifier(this.parentCallableSymbol.getProject()) : to);
        }
        this.handler.setReturnType(to);
        final Collection<String> suggestForType = OCNameSuggester.suggestForType(to, this.firstElement, "get");
        if (!suggestForType.isEmpty()) {
            this.handler.setName(suggestForType.iterator().next());
        }
    }
    
    private void a(final List<OCSymbol> list, final List<PsiElement> list2) {
        for (final OCSymbol ocSymbol : list) {
            list2.add((PsiElement)OCElementFactory.declarationStatement(ocSymbol.getName(), ocSymbol.getType(), null, (PsiElement)this.parentCallable));
        }
    }
    
    private void a(final List<PsiElement> list, final StringBuilder sb, final Map<OCSymbol, OCParameterInfo> map, final List<OCSymbol> list2) {
        final OCSymbol ocSymbol = this.analyzer.getOutputVariables().get(0);
        final OCType decayType = OCTypeUtils.decayType(ocSymbol.getType().resolve(this.firstElement.getContainingFile()), this.project);
        this.a(decayType, this.analyzer.getInputVariables().contains(ocSymbol), false);
        if (this.selection.contains(ocSymbol.getOffset())) {
            sb.append(OCElementFactory.declarationText(ocSymbol.getName(), decayType, (PsiElement)this.parentCallable));
            list2.remove(ocSymbol);
        }
        else {
            sb.append(ocSymbol.getName());
        }
        sb.append('=');
        final OCReturnStatement ocReturnStatement = (OCReturnStatement)OCElementFactory.statementFromText("return " + ocSymbol.getName() + ";", (PsiElement)this.firstElement.getContainingFile());
        list.add((PsiElement)ocReturnStatement);
        final OCParameterInfo ocParameterInfo = map.get(ocSymbol);
        if (ocParameterInfo != null) {
            final OCReferenceElement referenceElement = ((OCReferenceExpression)ocReturnStatement.getExpression()).getReferenceElement();
            if (referenceElement != null) {
                ocParameterInfo.getUsages().add(referenceElement.getReference());
            }
        }
    }
    
    private void a(final List<PsiElement> list, final List<OCSymbol> list2, final Map<OCSymbol, OCParameterInfo> map, final List<OCSymbol> list3) {
        int n = 0;
        for (final OCSymbol<PsiElement> ocSymbol : this.analyzer.getOutputVariables()) {
            final PsiElement locateDefinition = ocSymbol.locateDefinition();
            final OCParameterInfo a = this.a(list2, map, ocSymbol, true, true);
            if (locateDefinition instanceof OCDeclarator && list3.contains(ocSymbol)) {
                final OCStatement a2 = this.a((OCDeclarator)locateDefinition, a);
                if (a2 == null) {
                    continue;
                }
                list.add(n++, (PsiElement)a2);
            }
        }
    }
    
    private void a(final List<PsiElement> list, final Set<OCSymbol> set) {
        for (final OCSymbol ocSymbol : this.analyzer.getWrittenVariables()) {
            if (!this.selection.contains(ocSymbol.getOffset()) && !set.contains(ocSymbol)) {
                list.add(0, (PsiElement)OCElementFactory.declarationStatement(ocSymbol.getName(), ocSymbol.getType(), null, (PsiElement)this.parentCallable));
            }
        }
    }
    
    @Nullable
    private OCStatement a(final OCDeclarator ocDeclarator, final OCParameterInfo ocParameterInfo) {
        this.handler.getGeneratedInfo().runOnSuccess(() -> OCChangeUtil.delete((PsiElement)ocDeclarator), 0);
        if (ocDeclarator.getInitializer() != null && PsiTreeUtil.getParentOfType((PsiElement)ocDeclarator, (Class)OCStatement.class) != null) {
            final OCStatement statementFromText = OCElementFactory.statementFromText(ocDeclarator.getName() + "= 0;", (PsiElement)this.firstElement.getContainingFile());
            final OCAssignmentExpression ocAssignmentExpression;
            final OCReferenceElement ocReferenceElement;
            ApplicationManager.getApplication().runWriteAction(() -> {
                ocAssignmentExpression = (OCAssignmentExpression)((OCExpressionStatement)statementFromText).getExpression();
                OCChangeUtil.replaceHandlingMacros((PsiElement)ocAssignmentExpression.getSourceExpression(), (PsiElement)ocDeclarator.getInitializer());
                if (ocParameterInfo != null) {
                    ((OCReferenceExpression)ocAssignmentExpression.getReceiverExpression()).getReferenceElement();
                    if (ocReferenceElement != null) {
                        ocParameterInfo.getUsages().add(ocReferenceElement.getReference());
                    }
                }
                return;
            });
            return statementFromText;
        }
        return null;
    }
    
    private boolean b() {
        if (!(this.parentCallableSymbol instanceof OCFunctionSymbol) || ((OCFunctionSymbol)this.parentCallableSymbol).resolveIsStatic()) {
            return false;
        }
        final OCSymbolWithQualifiedName<OCElement> resolvedOwner = ((OCFunctionSymbol)this.parentCallableSymbol).getResolvedOwner();
        if (!(resolvedOwner instanceof OCStructSymbol)) {
            return false;
        }
        final Ref ref = new Ref((Object)true);
        final PsiElement[] elements = this.elements;
        for (int length = elements.length, i = 0; i < length; ++i) {
            elements[i].accept((PsiElementVisitor)new OCRecursiveVisitor() {
                @Override
                public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
                    super.visitQualifiedExpression(ocQualifiedExpression);
                    this.a(ocQualifiedExpression);
                }
                
                @Override
                public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
                    super.visitReferenceExpression(ocReferenceExpression);
                    this.a(ocReferenceExpression);
                }
                
                @Override
                public void visitCallExpression(final OCCallExpression ocCallExpression) {
                    super.visitCallExpression(ocCallExpression);
                    final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(ocCallExpression.getFunctionReferenceExpression());
                    if (symbol instanceof OCFunctionSymbol && OCCodeInsightUtil.isMemberAccess((OCSymbolWithQualifiedName)symbol, (OCStructSymbol)resolvedOwner, ocCallExpression.getFunctionReferenceExpression())) {
                        final OCFunctionSymbol ocFunctionSymbol = (OCFunctionSymbol)symbol;
                        if (!ocFunctionSymbol.isConst() && !ocFunctionSymbol.isCppConstructor() && !ocFunctionSymbol.resolveIsStatic() && !ocFunctionSymbol.isCppNonMemberOperator(new OCResolveContext((PsiElement)ocCallExpression))) {
                            ref.set((Object)false);
                        }
                    }
                }
                
                private void a(final OCExpression ocExpression) {
                    final OCSymbol symbol = OCGetSymbolVisitor.getSymbol(ocExpression);
                    if (new OCReadWriteAccessDetector().getExpressionAccess((PsiElement)ocExpression) != ReadWriteAccessDetector.Access.Read && symbol instanceof OCDeclaratorSymbol && OCCodeInsightUtil.isNonStaticFieldAccess((OCDeclaratorSymbol)symbol, (OCStructSymbol)resolvedOwner, ocExpression)) {
                        ref.set((Object)false);
                    }
                }
            });
        }
        return (boolean)ref.get();
    }
    
    private void e() {
        this.handler.setNameVisible(true);
        final OCCodeMoveValidator ocCodeMoveValidator = new OCCodeMoveValidator((PsiElement)null);
        final PsiElement[] elements = this.elements;
        for (int length = elements.length, i = 0; i < length; ++i) {
            elements[i].accept((PsiElementVisitor)ocCodeMoveValidator);
        }
        if (this.parentCallableSymbol instanceof OCMethodSymbol && !((OCMethodSymbol)this.parentCallableSymbol).isStatic() && ocCodeMoveValidator.isOutOfScope() && ContainerUtil.exists((Iterable)this.c(), usageInfo -> {
            final PsiElement element = usageInfo.getElement();
            if (!(usageInfo instanceof OCMethodCallUsage) || !(element instanceof OCSendMessageExpression)) {
                return false;
            }
            final OCExpression receiverExpression = ((OCSendMessageExpression)element).getReceiverExpression();
            return !(receiverExpression instanceof OCReferenceExpression) || ((OCReferenceExpression)receiverExpression).getSelfSuperToken() == null;
        })) {
            this.handler.addSelfParameter(((OCSymbolWithParent<T, OCClassSymbol>)this.parentCallableSymbol).getParent().getName());
        }
    }
    
    private void a(final OCBlockExpression ocBlockExpression) {
        final SmartPsiElementPointer smartPsiElementPointer = SmartPointerManager.getInstance(ocBlockExpression.getProject()).createSmartPsiElementPointer((PsiElement)ocBlockExpression);
        final OCChangeSignatureHandler handler = OCChangeSignatureActionHandler.getHandler(this.parentCallable, (PsiElement)this.handler.getMethodDescriptor().getMethod(), false, true, !ApplicationManager.getApplication().isUnitTestMode(), false);
        final String newName = this.handler.getChangeInfo().getNewName();
        handler.addParameter("withBlock", newName, ocBlockExpression.getResolvedType(), -1, false).setArgumentValue(ocBlockExpression.getTextWithMacros());
        handler.invokeSynchronously();
        this.handler.getChangeInfo().setNewMethod(handler.getChangeInfo().getMethod());
        final OCBlockExpression ocBlockExpression2 = (OCBlockExpression)smartPsiElementPointer.getElement();
        if (ocBlockExpression2 != null) {
            CodeStyleManager.getInstance(this.project).reformat((PsiElement)ocBlockExpression2);
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocBlockExpression2, (PsiElement)OCElementFactory.expressionFromText(newName, (PsiElement)ocBlockExpression)).getParent();
        }
    }
    
    private List<UsageInfo> c() {
        final HashSet<UsageInfo> set = new HashSet<UsageInfo>();
        OCSearchUtil.findAllMemberUsages((OCSymbolWithParent)this.parentCallableSymbol, set, true, true);
        return (List<UsageInfo>)ContainerUtil.filter((Collection)set, usageInfo -> {
            final PsiElement element = usageInfo.getElement();
            return usageInfo instanceof OCMethodCallUsage || (usageInfo instanceof OCFunctionUsage && element.getParent().getParent() instanceof OCCallExpression);
        });
    }
    
    @Nullable
    private String d() {
        if (!OCSearchScope.isInProjectSources(this.firstElement)) {
            return RefactoringBundle.message("error.out.of.project.element.default");
        }
        final String string = "Cannot extract a " + StringUtil.toLowerCase(this.callableKind.toString()) + ".\n";
        final OCDataFlowAnalyzer ocDataFlowAnalyzer = new OCDataFlowAnalyzer(this.elements, null);
        ocDataFlowAnalyzer.buildControlFlowGraph();
        final OCUnreachableCodeFinder unreachableCodeFinder = ocDataFlowAnalyzer.getUnreachableCodeFinder();
        this.hasExitNodes = unreachableCodeFinder.isExitNodeReached();
        if (this.analyzer.hasCrossSelectionJumps() || ocDataFlowAnalyzer.hasDanglingJumps() || (unreachableCodeFinder.isDeadEndReached() && this.hasExitNodes && this.hasStatementsAfter)) {
            return string + "There are multiple exit points in the selected code fragment";
        }
        if (this.callableKind == OCCallableKind.BLOCK) {
            if (this.parentCallable instanceof OCBlockExpression || this.parentCallable instanceof OCLambdaExpression) {
                return "Selected statements should be inside a function or method";
            }
            if (this.c().isEmpty()) {
                return ((this.parentCallableSymbol != null) ? this.parentCallableSymbol.getNameWithKindUppercase() : this.parentCallable.getKind().toString()) + " is never called";
            }
        }
        return null;
    }
}
