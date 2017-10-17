// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.util;

import com.jetbrains.cidr.lang.quickfixes.OCChangeVisibilityIntentionAction;
import com.intellij.openapi.command.CommandProcessor;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.refactoring.move.ui.OCEscalateVisibilityDialog;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.psi.SmartPointerManager;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.jetbrains.cidr.lang.symbols.cpp.OCNamespaceSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCDeclaratorSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.openapi.util.Comparing;
import com.jetbrains.cidr.lang.OCLog;
import com.jetbrains.cidr.lang.psi.OCCppNamespaceQualifier;
import com.jetbrains.cidr.lang.symbols.OCQualifiedName;
import com.jetbrains.cidr.lang.psi.OCNamespaceQualifierOwner;
import com.jetbrains.cidr.lang.symbols.OCSymbolReferenceResolver;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCQualifiedExpression;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.jetbrains.cidr.lang.symbols.cpp.OCSymbolWithQualifiedName;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.symbols.OCVisibility;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.SmartPsiElementPointer;
import java.util.Map;
import com.intellij.psi.PsiElementVisitor;
import java.util.List;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCPolyVariantReference;
import com.jetbrains.cidr.lang.psi.OCReference;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveModifyableVisitor;
import java.util.Iterator;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.Collection;
import java.util.Collections;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.intellij.openapi.util.Key;

public class OCBindUtil
{
    private static final Key<OCSymbolWithParent> REF_TO_SYMBOL;
    private static final Key<PsiElement> REF_TO_DEFINITION;
    private static final Key<Object> KEY_ENCODED;
    private static final Key<Boolean> KEY_IS_REF_TO_PARENT;
    private static final Key<Boolean> KEY_BIND_QUALIFIER;
    
    public static void encodeContextInfo(final PsiElement psiElement, final boolean b) {
        encodeContextInfo(Collections.singletonList(psiElement), null, b);
    }
    
    public static void encodeContextInfo(final Collection<PsiElement> collection, @Nullable final OCSymbol ocSymbol, final boolean b) {
        final Iterator<PsiElement> iterator = collection.iterator();
        while (iterator.hasNext()) {
            a(iterator.next(), collection, ocSymbol, b);
        }
    }
    
    private static void a(final PsiElement psiElement, final Collection<PsiElement> collection, @Nullable final OCSymbol ocSymbol, final boolean b) {
        psiElement.accept((PsiElementVisitor)new OCRecursiveModifyableVisitor() {
            @Override
            public void visitElement(final PsiElement psiElement) {
                if (OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
                    return;
                }
                super.visitElement(psiElement);
                OCSymbol<PsiElement> ocSymbol = null;
                if (psiElement instanceof OCReferenceElement) {
                    ocSymbol = (OCSymbol<PsiElement>)((OCReferenceElement)psiElement).resolveToSymbol();
                }
                else {
                    final PsiReference reference = psiElement.getReference();
                    if (reference instanceof OCReference) {
                        ocSymbol = (OCSymbol<PsiElement>)((OCReference<OCSymbol<PsiElement>>)reference).resolveToSymbol();
                    }
                    else if (reference instanceof OCPolyVariantReference) {
                        final List<OCSymbol> resolveToSymbols = ((OCPolyVariantReference<OCSymbol>)reference).resolveToSymbols();
                        ocSymbol = (resolveToSymbols.isEmpty() ? null : resolveToSymbols.iterator().next());
                    }
                }
                if (ocSymbol != null) {
                    final PsiElement locateDefinition = ocSymbol.locateDefinition();
                    if (ocSymbol instanceof OCSymbolWithParent) {
                        boolean b = false;
                        if (locateDefinition != null) {
                            final Iterator<PsiElement> iterator = collection.iterator();
                            while (iterator.hasNext()) {
                                if (PsiTreeUtil.isContextAncestor((PsiElement)iterator.next(), locateDefinition, false)) {
                                    b = true;
                                    break;
                                }
                            }
                        }
                        if (b) {
                            if (ocSymbol != null) {
                                final PsiReference qualifierReference = OCBindUtil.getQualifierReference(psiElement);
                                if (qualifierReference instanceof OCReference && ocSymbol.isSameSymbol(((OCReference<OCSymbol>)qualifierReference).resolveToSymbol())) {
                                    OCBindUtil.encodeAsRefToParent(psiElement);
                                }
                                else if (qualifierReference instanceof OCPolyVariantReference) {
                                    final Iterator<OCSymbol> iterator2 = ((OCPolyVariantReference<OCSymbol>)qualifierReference).resolveToSymbols().iterator();
                                    while (iterator2.hasNext()) {
                                        if (ocSymbol.isSameSymbol(iterator2.next())) {
                                            OCBindUtil.encodeAsRefToParent(psiElement);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        else if (b || !ocSymbol.getKind().isLocal()) {
                            psiElement.putCopyableUserData(OCBindUtil.KEY_ENCODED, (Object)OCBindUtil.KEY_ENCODED);
                            psiElement.putCopyableUserData(OCBindUtil.REF_TO_SYMBOL, (Object)ocSymbol);
                            psiElement.putCopyableUserData(OCBindUtil.REF_TO_DEFINITION, (Object)locateDefinition);
                            psiElement.putCopyableUserData(OCBindUtil.KEY_IS_REF_TO_PARENT, (Object)false);
                            psiElement.putCopyableUserData(OCBindUtil.KEY_BIND_QUALIFIER, (Object)(((OCSymbolWithParent<?, ?>)ocSymbol).getParent() == ocSymbol));
                        }
                    }
                }
            }
        });
    }
    
    public static void encodeAsRefToParent(final PsiElement psiElement) {
        psiElement.putCopyableUserData((Key)OCBindUtil.KEY_ENCODED, (Object)OCBindUtil.KEY_ENCODED);
        psiElement.putCopyableUserData((Key)OCBindUtil.KEY_IS_REF_TO_PARENT, (Object)true);
        psiElement.putCopyableUserData((Key)OCBindUtil.KEY_BIND_QUALIFIER, (Object)false);
    }
    
    public static void decodeContextInfo(final Collection<PsiElement> collection) {
        final Iterator<PsiElement> iterator = collection.iterator();
        while (iterator.hasNext()) {
            decodeContextInfo(iterator.next(), null, null);
        }
    }
    
    public static void decodeContextInfo(final PsiElement psiElement) {
        decodeContextInfo(psiElement, null, null);
    }
    
    public static void decodeContextInfo(final PsiElement psiElement, @Nullable final OCSymbol ocSymbol, @Nullable final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map) {
        psiElement.accept((PsiElementVisitor)new OCRecursiveModifyableVisitor() {
            @Override
            public void visitElement(final PsiElement psiElement) {
                if (OCElementUtil.isPartOfMacroSubstitution(psiElement)) {
                    return;
                }
                super.visitElement(psiElement);
                if (psiElement.getCopyableUserData(OCBindUtil.KEY_ENCODED) != null) {
                    final OCSymbolWithParent ocSymbolWithParent = (OCSymbolWithParent)psiElement.getCopyableUserData(OCBindUtil.REF_TO_SYMBOL);
                    final PsiElement psiElement2 = (PsiElement)psiElement.getCopyableUserData(OCBindUtil.REF_TO_DEFINITION);
                    final Boolean b = (Boolean)psiElement.getCopyableUserData(OCBindUtil.KEY_IS_REF_TO_PARENT);
                    final Boolean b2 = (Boolean)psiElement.getCopyableUserData(OCBindUtil.KEY_BIND_QUALIFIER);
                    psiElement.putCopyableUserData(OCBindUtil.KEY_ENCODED, (Object)null);
                    psiElement.putCopyableUserData(OCBindUtil.KEY_IS_REF_TO_PARENT, (Object)null);
                    psiElement.putCopyableUserData(OCBindUtil.KEY_BIND_QUALIFIER, (Object)null);
                    psiElement.putCopyableUserData(OCBindUtil.REF_TO_SYMBOL, (Object)null);
                    psiElement.putCopyableUserData(OCBindUtil.REF_TO_DEFINITION, (Object)null);
                    assert b2 != null;
                    if (b == Boolean.TRUE) {
                        OCBindUtil.bindReferenceAndMakeVisible((psiElement instanceof OCDeclarator) ? ((OCDeclarator)psiElement).getNamespaceQualifier().getReference() : OCBindUtil.getQualifierReference(psiElement), ocSymbol, ocSymbol.locateDefinition(), map, b2);
                    }
                    else if (ocSymbolWithParent != null) {
                        OCBindUtil.bindReferenceAndMakeVisible(psiElement.getReference(), ocSymbolWithParent, psiElement2, map, b2);
                    }
                }
            }
        });
    }
    
    public static PsiElement insertRedundantQualifiers(final PsiElement psiElement, final boolean b) {
        final Ref create = Ref.create((Object)psiElement);
        psiElement.accept((PsiElementVisitor)new OCRecursiveModifyableVisitor() {
            @Override
            public void visitReferenceExpression(final OCReferenceExpression ocReferenceExpression) {
                if (OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocReferenceExpression)) {
                    return;
                }
                super.visitReferenceExpression(ocReferenceExpression);
                final OCSymbol resolveToSymbol = ocReferenceExpression.resolveToSymbol();
                Object o = null;
                if (resolveToSymbol instanceof OCInstanceVariableSymbol) {
                    o = OCElementFactory.expressionFromText("self->" + resolveToSymbol.getName(), (PsiElement)ocReferenceExpression);
                }
                else if (resolveToSymbol instanceof OCSymbolWithQualifiedName && ((OCFunctionSymbol)resolveToSymbol).getParent() instanceof OCStructSymbol && !((OCFunctionSymbol)resolveToSymbol).isFriendOrStatic() && resolveToSymbol.getKind() != OCSymbolKind.ENUM_CONST && (!(resolveToSymbol instanceof OCFunctionSymbol) || !((OCFunctionSymbol)resolveToSymbol).isCppConstructor())) {
                    o = OCElementFactory.expressionFromText((b ? "this." : "this->") + resolveToSymbol.getName(), (PsiElement)ocReferenceExpression);
                }
                if (o != null) {
                    if (ocReferenceExpression == psiElement) {
                        create.set(o);
                    }
                    else {
                        OCChangeUtil.replaceHandlingMacros((PsiElement)ocReferenceExpression, (PsiElement)o);
                    }
                }
            }
        });
        return (PsiElement)create.get();
    }
    
    public static List<PsiElement> insertRedundantQualifiers(final Collection<PsiElement> collection, final boolean b) {
        final ArrayList<PsiElement> list = new ArrayList<PsiElement>();
        for (final PsiElement psiElement : collection) {
            if (psiElement.isPhysical()) {
                list.add(insertRedundantQualifiers(psiElement, b));
            }
            else {
                list.add(psiElement);
            }
        }
        return list;
    }
    
    public static void removeRedundantQualifiers(final PsiElement psiElement) {
        psiElement.accept((PsiElementVisitor)new OCRecursiveModifyableVisitor() {
            @Override
            public void visitQualifiedExpression(final OCQualifiedExpression ocQualifiedExpression) {
                if (OCElementUtil.isPartOfMacroSubstitution((PsiElement)ocQualifiedExpression)) {
                    return;
                }
                super.visitQualifiedExpression(ocQualifiedExpression);
                final OCExpression qualifier = ocQualifiedExpression.getQualifier();
                final OCSymbol resolveToSymbol = ocQualifiedExpression.resolveToSymbol();
                final PsiElement copy = ocQualifiedExpression.copy();
                if (resolveToSymbol instanceof OCSymbolWithParent && qualifier instanceof OCReferenceExpression && ((OCReferenceExpression)qualifier).isSelfSuperOrThis()) {
                    final OCReferenceExpression ocReferenceExpression = (OCReferenceExpression)OCChangeUtil.replaceHandlingMacros((PsiElement)ocQualifiedExpression, (PsiElement)OCElementFactory.expressionFromText(resolveToSymbol.getName(), (PsiElement)ocQualifiedExpression));
                    if (!a(ocReferenceExpression.getReferenceElement().getReference(), resolveToSymbol, false)) {
                        OCChangeUtil.replaceHandlingMacros((PsiElement)ocReferenceExpression, copy);
                    }
                }
            }
        });
    }
    
    public static boolean setShortestPossibleName(final OCReferenceElement ocReferenceElement) {
        final OCSymbol resolveToSymbol = ocReferenceElement.resolveToSymbol();
        if (resolveToSymbol instanceof OCSymbolWithQualifiedName) {
            OCQualifiedName ocQualifiedName = OCSymbolReferenceResolver.getQualifiedName(ocReferenceElement);
            OCQualifiedName ocQualifiedName2;
            for (ocQualifiedName2 = ocQualifiedName.getQualifier(); ocQualifiedName2 != null && ocQualifiedName2 != OCQualifiedName.GLOBAL; ocQualifiedName2 = ocQualifiedName2.getQualifier()) {}
            if (ocQualifiedName2 == OCQualifiedName.GLOBAL) {
                ocQualifiedName = ocQualifiedName.dropSuperQualifier();
            }
            return setShortestPossibleName(ocQualifiedName, ocReferenceElement, (OCSymbolWithQualifiedName)resolveToSymbol);
        }
        return false;
    }
    
    public static boolean setShortestPossibleName(final OCQualifiedName ocQualifiedName, final OCNamespaceQualifierOwner ocNamespaceQualifierOwner, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        final OCQualifiedName qualifiedName = OCSymbolReferenceResolver.getQualifiedName(ocNamespaceQualifierOwner);
        if (a(ocQualifiedName, ocNamespaceQualifierOwner, ocSymbolWithQualifiedName)) {
            return true;
        }
        OCElementUtil.changeQualifiedName(ocNamespaceQualifierOwner, qualifiedName);
        return false;
    }
    
    private static boolean a(final OCQualifiedName ocQualifiedName, final OCNamespaceQualifierOwner ocNamespaceQualifierOwner, final OCSymbolWithQualifiedName ocSymbolWithQualifiedName) {
        if (ocQualifiedName.getQualifier() != null && a(ocQualifiedName.dropSuperQualifier(), ocNamespaceQualifierOwner, ocSymbolWithQualifiedName)) {
            return true;
        }
        OCElementUtil.changeQualifiedName(ocNamespaceQualifierOwner, ocQualifiedName);
        if (ocQualifiedName == OCQualifiedName.GLOBAL) {
            return true;
        }
        if (ocNamespaceQualifierOwner instanceof OCReferenceElement) {
            return ocSymbolWithQualifiedName.isSameSymbol(((OCReferenceElement)ocNamespaceQualifierOwner).resolveToSymbol());
        }
        return ocNamespaceQualifierOwner instanceof OCCppNamespaceQualifier && ((OCCppNamespaceQualifier)ocNamespaceQualifierOwner).resolveToSymbols().contains(ocSymbolWithQualifiedName);
    }
    
    @Nullable
    public static OCQualifiedName getShortestPossibleName(final OCQualifiedName ocQualifiedName, OCReferenceElement referenceElementFromText, final OCSymbol ocSymbol) {
        if (ocQualifiedName.getQualifier() != null) {
            final OCQualifiedName shortestPossibleName = getShortestPossibleName(ocQualifiedName.dropSuperQualifier(), referenceElementFromText, ocSymbol);
            if (shortestPossibleName != null) {
                return shortestPossibleName;
            }
        }
        referenceElementFromText = OCElementFactory.referenceElementFromText(ocQualifiedName.getCanonicalName(true), (PsiElement)referenceElementFromText, true);
        OCLog.LOG.assertTrue(referenceElementFromText != null);
        OCElementUtil.changeQualifiedName(referenceElementFromText, ocQualifiedName);
        if (Comparing.equal((Object)referenceElementFromText.resolveToSymbol(), (Object)ocSymbol)) {
            return ocQualifiedName;
        }
        return null;
    }
    
    @Nullable
    public static PsiReference getQualifierReference(final PsiElement psiElement) {
        Object o = null;
        if (psiElement instanceof OCReferenceElement) {
            o = ((OCReferenceElement)psiElement).getNamespaceQualifier();
        }
        else if (psiElement instanceof OCQualifiedExpression) {
            o = ((OCQualifiedExpression)psiElement).getQualifier();
        }
        else if (psiElement instanceof OCSendMessageExpression) {
            o = ((OCSendMessageExpression)psiElement).getReceiverExpression();
        }
        else if (psiElement instanceof OCCppNamespaceQualifier) {
            o = ((OCCppNamespaceQualifier)psiElement).getNamespaceQualifier();
        }
        if (o instanceof OCExpression) {
            o = OCParenthesesUtils.diveIntoParentheses((OCExpression)o);
        }
        if (o instanceof OCReferenceExpression) {
            final OCReferenceElement referenceElement = ((OCReferenceExpression)o).getReferenceElement();
            if (referenceElement != null && ((OCReferenceExpression)o).isSelfSuperOrThis()) {
                return referenceElement.getReference();
            }
            final OCSymbol ocSymbol = (referenceElement != null) ? referenceElement.resolveToSymbol() : null;
            return (ocSymbol != null && ocSymbol.getKind().isClass()) ? referenceElement.getReference() : null;
        }
        else {
            if (o instanceof OCCppNamespaceQualifier) {
                return ((PsiElement)o).getReference();
            }
            return null;
        }
    }
    
    private static boolean a(@Nullable final PsiReference psiReference, @Nullable final OCSymbol ocSymbol, final boolean b) {
        if (ocSymbol != null) {
            PsiElement psiElement;
            if (psiReference instanceof OCReference) {
                psiElement = ((OCReference)psiReference).bindToSymbol(ocSymbol);
            }
            else {
                if (!(psiReference instanceof OCPolyVariantReference)) {
                    return false;
                }
                psiElement = ((OCPolyVariantReference)psiReference).bindToSymbol(ocSymbol);
            }
            final PsiReference reference = psiElement.getReference();
            Object resolveToSymbols;
            if (reference instanceof OCReference) {
                final OCSymbol resolveToSymbol = ((OCReference<OCSymbol>)reference).resolveToSymbol();
                resolveToSymbols = ((resolveToSymbol != null) ? Collections.singletonList(resolveToSymbol) : Collections.emptyList());
            }
            else if (psiElement instanceof OCSendMessageExpression) {
                final OCMethodSymbol knownResponder = ((OCSendMessageExpression)psiElement).getProbableResponders().getKnownResponder();
                resolveToSymbols = ((knownResponder != null) ? Collections.singletonList(knownResponder) : Collections.emptyList());
            }
            else {
                if (!(reference instanceof OCPolyVariantReference)) {
                    return false;
                }
                resolveToSymbols = ((OCPolyVariantReference)reference).resolveToSymbols();
            }
            final boolean contains = ((List)resolveToSymbols).contains(ocSymbol);
            if ((b || !contains) && ((ocSymbol instanceof OCMethodSymbol && ((OCMethodSymbol)ocSymbol).isStatic()) || (ocSymbol instanceof OCDeclaratorSymbol && ((OCDeclaratorSymbol)ocSymbol).isFriendOrStatic()) || (ocSymbol instanceof OCFunctionSymbol && ((OCFunctionSymbol)ocSymbol).isFriendOrStatic()) || ocSymbol instanceof OCNamespaceSymbol)) {
                final PsiReference qualifierReference = getQualifierReference(psiElement);
                if (qualifierReference != null) {
                    return a(qualifierReference, ((OCFunctionSymbol)ocSymbol).getParent(), false);
                }
            }
            return contains;
        }
        return false;
    }
    
    public static void bindReferenceAndMakeVisible(@Nullable final PsiReference psiReference, @Nullable final OCSymbol ocSymbol, @Nullable final PsiElement psiElement, @Nullable final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map, final boolean b) {
        if (psiReference == null) {
            return;
        }
        final OCSymbol ocSymbol2 = (psiElement instanceof OCSymbolDeclarator) ? ((OCSymbolDeclarator)psiElement).getSymbol() : null;
        if (map != null && ocSymbol2 instanceof OCSymbolWithParent) {
            final OCVisibility visibility = OCVisibility.getVisibility(ocSymbol2, psiReference.getElement(), (psiReference.getElement() instanceof OCQualifiedExpression) ? ((OCQualifiedExpression)psiReference.getElement()).getQualifier().getResolvedType().getTerminalType() : null);
            if (visibility != OCVisibility.NULL && !OCVisibility.isVisible(ocSymbol2, visibility)) {
                final Pair<OCSymbol, OCVisibility> pair = map.get(psiElement);
                map.put(SmartPointerManager.getInstance(psiElement.getProject()).createSmartPsiElementPointer(psiElement), (Pair<OCSymbol, OCVisibility>)Pair.create((Object)ocSymbol2, (Object)((pair != null) ? OCVisibility.min((OCVisibility)pair.getSecond(), visibility) : visibility)));
            }
        }
        a(psiReference, ocSymbol, b);
    }
    
    public static boolean escalateVisibilities(final Project project, final Map<SmartPsiElementPointer, Pair<OCSymbol, OCVisibility>> map, final VirtualFile... array) {
        if (map != null && !map.isEmpty()) {
            final OCEscalateVisibilityDialog ocEscalateVisibilityDialog = new OCEscalateVisibilityDialog(project, map);
            if (!ApplicationManager.getApplication().isUnitTestMode()) {
                if (!ocEscalateVisibilityDialog.showAndGet()) {
                    return false;
                }
            }
            else {
                Disposer.dispose(ocEscalateVisibilityDialog.getDisposable());
            }
            new WriteCommandAction(project, new PsiFile[0]) {
                protected void run(@NotNull final Result result) throws Throwable {
                    try {
                        if (result == null) {
                            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/util/OCBindUtil$5", "run"));
                        }
                    }
                    catch (Throwable t) {
                        throw a(t);
                    }
                    CommandProcessor.getInstance().markCurrentCommandAsGlobal(project);
                    CommandProcessor.getInstance().setCurrentCommandName("Escalate visibility");
                    CommandProcessor.getInstance().addAffectedFiles(project, array);
                    for (final Pair<SmartPsiElementPointer, OCVisibility> pair : ocEscalateVisibilityDialog.getCheckedMembers()) {
                        final PsiElement element = ((SmartPsiElementPointer)pair.getFirst()).getElement();
                        OCSymbolWithParent symbol = null;
                        Label_0148: {
                            try {
                                if (element instanceof OCSymbolDeclarator) {
                                    symbol = ((OCSymbolDeclarator<OCSymbolWithParent>)element).getSymbol();
                                    break Label_0148;
                                }
                            }
                            catch (Throwable t2) {
                                throw a(t2);
                            }
                            symbol = null;
                        }
                        final OCSymbolWithParent ocSymbolWithParent = symbol;
                        try {
                            if (!(ocSymbolWithParent instanceof OCSymbolWithParent)) {
                                continue;
                            }
                            new OCChangeVisibilityIntentionAction(ocSymbolWithParent, (OCVisibility)pair.getSecond()).invoke();
                        }
                        catch (Throwable t3) {
                            throw a(t3);
                        }
                    }
                }
                
                private static Throwable a(final Throwable t) {
                    return t;
                }
            }.execute();
        }
        return true;
    }
    
    static {
        REF_TO_SYMBOL = Key.create("REF_TO_SYMBOL_KEY");
        REF_TO_DEFINITION = Key.create("REF_TO_DEFINITION_KEY");
        KEY_ENCODED = Key.create("KEY_ENCODED");
        KEY_IS_REF_TO_PARENT = Key.create("KEY_IS_REF_TO_PARENT");
        KEY_BIND_QUALIFIER = Key.create("KEY_BIND_QUALIFIER");
    }
}
