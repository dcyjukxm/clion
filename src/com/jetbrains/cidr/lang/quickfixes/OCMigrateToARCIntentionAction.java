// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.psi.OCStatement;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.jetbrains.cidr.lang.psi.OCMethod;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.psi.OCReferenceExpression;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import java.util.Set;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.intellij.util.containers.HashSet;
import java.util.Iterator;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCAutoReleasePoolStatement;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiElementVisitor;
import com.jetbrains.cidr.lang.psi.visitors.OCRecursiveVisitor;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import java.util.ArrayList;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.workspace.compiler.OCCompilerFeatures;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.jetbrains.cidr.lang.psi.OCReferenceElement;
import com.jetbrains.cidr.lang.psi.OCSendMessageExpression;
import com.intellij.psi.PsiElement;

public class OCMigrateToARCIntentionAction extends OCPsiElementQuickFix<PsiElement>
{
    public OCMigrateToARCIntentionAction(final OCSendMessageExpression ocSendMessageExpression) {
        super((PsiElement)ocSendMessageExpression);
    }
    
    public OCMigrateToARCIntentionAction(final OCReferenceElement ocReferenceElement) {
        super((PsiElement)ocReferenceElement);
    }
    
    public OCMigrateToARCIntentionAction(final OCFile ocFile) {
        super((PsiElement)ocFile);
    }
    
    @NotNull
    public String getFamilyName() {
        String s;
        try {
            s = "Migrate to ARC";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction", "getFamilyName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return s;
    }
    
    @Override
    protected String getTextInternal() {
        final PsiElement element = this.myElementPtr.getElement();
        try {
            if (element instanceof OCFile) {
                return "Migrate '" + ((OCFile)element).getName() + "' to ARC";
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return "Migrate statement to ARC";
    }
    
    public boolean isAvailable(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction", "isAvailable"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        return OCCompilerFeatures.isArcEnabled(psiElement.getContainingFile());
    }
    
    public void invoke(final PsiFile psiFile, @NotNull final PsiElement psiElement) throws IncorrectOperationException {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCMigrateToARCIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw c(ex);
        }
        final ArrayList<OCSendMessageExpression> list = new ArrayList<OCSendMessageExpression>();
        final ArrayList<OCDeclarationStatement> list2 = new ArrayList<OCDeclarationStatement>();
        final ArrayList<List<PsiElement>> list3 = new ArrayList<List<PsiElement>>();
        Label_0197: {
            try {
                if (psiElement instanceof OCFile) {
                    psiElement.accept((PsiElementVisitor)new OCRecursiveVisitor() {
                        @Override
                        public void visitSendMessageExpression(final OCSendMessageExpression ocSendMessageExpression) {
                            super.visitSendMessageExpression(ocSendMessageExpression);
                            list.add(ocSendMessageExpression);
                        }
                        
                        @Override
                        public void visitDeclarationStatement(final OCDeclarationStatement ocDeclarationStatement) {
                            super.visitDeclarationStatement(ocDeclarationStatement);
                            if (ocDeclarationStatement.getDeclaration().getType().getSimpleName((PsiElement)ocDeclarationStatement).equals("NSAutoreleasePool")) {
                                list2.add(ocDeclarationStatement);
                            }
                        }
                    });
                    break Label_0197;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
            try {
                if (psiElement instanceof OCSendMessageExpression) {
                    list.add((OCSendMessageExpression)psiElement);
                    break Label_0197;
                }
            }
            catch (IncorrectOperationException ex3) {
                throw c(ex3);
            }
            if (psiElement instanceof OCReferenceElement) {
                final OCDeclarationStatement ocDeclarationStatement = (OCDeclarationStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCDeclarationStatement.class);
                Label_0180: {
                    try {
                        if (ocDeclarationStatement == null) {
                            break Label_0197;
                        }
                        final OCDeclarationStatement ocDeclarationStatement2 = ocDeclarationStatement;
                        final OCDeclaration ocDeclaration = ocDeclarationStatement2.getDeclaration();
                        final OCType ocType = ocDeclaration.getType();
                        final PsiElement psiElement2 = psiElement;
                        final String s = ocType.getSimpleName(psiElement2);
                        final String s2 = "NSAutoreleasePool";
                        final boolean b = s.equals(s2);
                        if (b) {
                            break Label_0180;
                        }
                        break Label_0197;
                    }
                    catch (IncorrectOperationException ex4) {
                        throw c(ex4);
                    }
                    try {
                        final OCDeclarationStatement ocDeclarationStatement2 = ocDeclarationStatement;
                        final OCDeclaration ocDeclaration = ocDeclarationStatement2.getDeclaration();
                        final OCType ocType = ocDeclaration.getType();
                        final PsiElement psiElement2 = psiElement;
                        final String s = ocType.getSimpleName(psiElement2);
                        final String s2 = "NSAutoreleasePool";
                        final boolean b = s.equals(s2);
                        if (b) {
                            list2.add(ocDeclarationStatement);
                        }
                    }
                    catch (IncorrectOperationException ex5) {
                        throw c(ex5);
                    }
                }
            }
        }
        a(list2, list3);
        a(list);
        b(list3);
    }
    
    private static void b(final List<List<PsiElement>> list) {
        for (final List<PsiElement> list2 : list) {
            final PsiElement psiElement = list2.get(0);
            final OCAutoReleasePoolStatement ocAutoReleasePoolStatement = (OCAutoReleasePoolStatement)OCElementFactory.statementFromText("@autoreleasepool{}", psiElement);
            for (final PsiElement psiElement2 : list2) {
                Label_0101: {
                    try {
                        if (!psiElement2.isValid()) {
                            continue;
                        }
                        final PsiElement psiElement3 = psiElement2;
                        final PsiElement psiElement4 = psiElement;
                        if (psiElement3 != psiElement4) {
                            break Label_0101;
                        }
                        continue;
                    }
                    catch (IncorrectOperationException ex) {
                        throw c(ex);
                    }
                    try {
                        final PsiElement psiElement3 = psiElement2;
                        final PsiElement psiElement4 = psiElement;
                        if (psiElement3 == psiElement4) {
                            continue;
                        }
                        OCChangeUtil.add((PsiElement)ocAutoReleasePoolStatement.getBody(), psiElement2);
                    }
                    catch (IncorrectOperationException ex2) {
                        throw c(ex2);
                    }
                }
            }
            for (final PsiElement psiElement5 : list2) {
                Label_0177: {
                    try {
                        if (!psiElement5.isValid()) {
                            continue;
                        }
                        final PsiElement psiElement6 = psiElement5;
                        final PsiElement psiElement7 = psiElement;
                        if (psiElement6 != psiElement7) {
                            break Label_0177;
                        }
                        continue;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw c(ex3);
                    }
                    try {
                        final PsiElement psiElement6 = psiElement5;
                        final PsiElement psiElement7 = psiElement;
                        if (psiElement6 == psiElement7) {
                            continue;
                        }
                        OCChangeUtil.delete(psiElement5);
                    }
                    catch (IncorrectOperationException ex4) {
                        throw c(ex4);
                    }
                }
            }
            OCChangeUtil.replaceHandlingMacros(psiElement, (PsiElement)ocAutoReleasePoolStatement);
        }
    }
    
    private static void a(final List<OCDeclarationStatement> list, final List<List<PsiElement>> list2) {
        for (final OCDeclarationStatement ocDeclarationStatement : list) {
            try {
                if (!ocDeclarationStatement.isValid()) {
                    continue;
                }
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            final HashSet set = new HashSet();
            final Iterator<OCDeclarator> iterator2 = ocDeclarationStatement.getDeclaration().getDeclarators().iterator();
            while (iterator2.hasNext()) {
                ((Set<String>)set).add(iterator2.next().getName());
            }
            PsiElement psiElement = ocDeclarationStatement.getNextSibling();
            PsiElement psiElement2 = null;
            while (true) {
                Label_0146: {
                    try {
                        if (psiElement == null) {
                            break;
                        }
                        if (!a((Set<String>)set, psiElement)) {
                            break Label_0146;
                        }
                    }
                    catch (IncorrectOperationException ex2) {
                        throw c(ex2);
                    }
                    psiElement2 = psiElement;
                }
                psiElement = psiElement.getNextSibling();
            }
            if (psiElement2 != null) {
                Object o = ocDeclarationStatement;
                final ArrayList<PsiElement> list3 = new ArrayList<PsiElement>();
                list2.add(list3);
                while (o != null) {
                    final PsiElement nextSibling = ((PsiElement)o).getNextSibling();
                    Label_0230: {
                        try {
                            if (a((Set<String>)set, (PsiElement)o)) {
                                OCChangeUtil.delete((PsiElement)o);
                                break Label_0230;
                            }
                        }
                        catch (IncorrectOperationException ex3) {
                            throw c(ex3);
                        }
                        list3.add((PsiElement)o);
                        try {
                            if (o == psiElement2) {
                                break;
                            }
                        }
                        catch (IncorrectOperationException ex4) {
                            throw c(ex4);
                        }
                    }
                    o = nextSibling;
                }
            }
        }
    }
    
    private static boolean a(final Set<String> set, final PsiElement psiElement) {
        if (psiElement instanceof OCExpressionStatement) {
            final OCExpression expression = ((OCExpressionStatement)psiElement).getExpression();
            if (expression instanceof OCSendMessageExpression) {
                final OCExpression receiverExpression = ((OCSendMessageExpression)expression).getReceiverExpression();
                try {
                    if (!(receiverExpression instanceof OCReferenceExpression)) {
                        return false;
                    }
                    final Set<String> set2 = set;
                    final OCExpression ocExpression = receiverExpression;
                    final String s = ocExpression.getText();
                    final boolean b = set2.contains(s);
                    if (b) {
                        return true;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex) {
                    throw c(ex);
                }
                try {
                    final Set<String> set2 = set;
                    final OCExpression ocExpression = receiverExpression;
                    final String s = ocExpression.getText();
                    final boolean b = set2.contains(s);
                    if (b) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex2) {
                    throw c(ex2);
                }
            }
        }
        return false;
    }
    
    private static void a(final List<OCSendMessageExpression> list) {
        final ArrayList<OCMethod> list2 = new ArrayList<OCMethod>();
        for (final OCSendMessageExpression ocSendMessageExpression : list) {
            try {
                if (!ocSendMessageExpression.isValid()) {
                    continue;
                }
            }
            catch (IncorrectOperationException ex) {
                throw c(ex);
            }
            final List<OCMethodSymbol> allResponders = ocSendMessageExpression.getProbableResponders().getAllResponders();
            try {
                if (allResponders.isEmpty()) {
                    continue;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw c(ex2);
            }
            final OCMethodSymbol ocMethodSymbol = allResponders.get(0);
            if ("dealloc".equals(ocMethodSymbol.getName())) {
                final OCMethod ocMethod = (OCMethod)PsiTreeUtil.getParentOfType((PsiElement)ocSendMessageExpression, (Class)OCMethod.class);
                Label_0144: {
                    try {
                        if (ocMethod == null) {
                            break Label_0144;
                        }
                        final OCMethod ocMethod2 = ocMethod;
                        final String s = ocMethod2.getSelector();
                        final String s2 = "dealloc";
                        final boolean b = s.equals(s2);
                        if (b) {
                            break Label_0144;
                        }
                        break Label_0144;
                    }
                    catch (IncorrectOperationException ex3) {
                        throw c(ex3);
                    }
                    try {
                        final OCMethod ocMethod2 = ocMethod;
                        final String s = ocMethod2.getSelector();
                        final String s2 = "dealloc";
                        final boolean b = s.equals(s2);
                        if (b) {
                            list2.add(ocMethod);
                        }
                    }
                    catch (IncorrectOperationException ex4) {
                        throw c(ex4);
                    }
                }
            }
            else {
                try {
                    if (!ocMethodSymbol.isForbiddenByARC((PsiElement)ocSendMessageExpression)) {
                        continue;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw c(ex5);
                }
            }
            final OCExpression receiverExpression = ocSendMessageExpression.getReceiverExpression();
            try {
                if (receiverExpression == null) {
                    continue;
                }
            }
            catch (IncorrectOperationException ex6) {
                throw c(ex6);
            }
            Label_0228: {
                try {
                    if (!(receiverExpression instanceof OCReferenceExpression)) {
                        break Label_0228;
                    }
                    final OCSendMessageExpression ocSendMessageExpression2 = ocSendMessageExpression;
                    final PsiElement psiElement = ocSendMessageExpression2.getParent();
                    final boolean b2 = psiElement instanceof OCExpressionStatement;
                    if (b2) {
                        break Label_0228;
                    }
                    break Label_0228;
                }
                catch (IncorrectOperationException ex7) {
                    throw c(ex7);
                }
                try {
                    final OCSendMessageExpression ocSendMessageExpression2 = ocSendMessageExpression;
                    final PsiElement psiElement = ocSendMessageExpression2.getParent();
                    final boolean b2 = psiElement instanceof OCExpressionStatement;
                    if (b2) {
                        OCChangeUtil.delete(ocSendMessageExpression.getParent());
                        continue;
                    }
                }
                catch (IncorrectOperationException ex8) {
                    throw c(ex8);
                }
            }
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocSendMessageExpression, (PsiElement)receiverExpression);
        }
        for (final OCMethod ocMethod3 : list2) {
            final OCBlockStatement body = ocMethod3.getBody();
            Label_0315: {
                try {
                    if (body == null) {
                        continue;
                    }
                    final OCBlockStatement ocBlockStatement = body;
                    final List<OCStatement> list3 = ocBlockStatement.getStatements();
                    final boolean b3 = list3.isEmpty();
                    if (b3) {
                        break Label_0315;
                    }
                    continue;
                }
                catch (IncorrectOperationException ex9) {
                    throw c(ex9);
                }
                try {
                    final OCBlockStatement ocBlockStatement = body;
                    final List<OCStatement> list3 = ocBlockStatement.getStatements();
                    final boolean b3 = list3.isEmpty();
                    if (!b3) {
                        continue;
                    }
                    OCChangeUtil.delete((PsiElement)ocMethod3);
                }
                catch (IncorrectOperationException ex10) {
                    throw c(ex10);
                }
            }
        }
    }
    
    private static IncorrectOperationException c(final IncorrectOperationException ex) {
        return ex;
    }
}
