// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.quickfixes;

import com.jetbrains.cidr.lang.symbols.cpp.OCFunctionSymbol;
import com.jetbrains.cidr.lang.resolve.OCResolveUtil;
import com.jetbrains.cidr.lang.types.OCArrayType;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCArgumentList;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.jetbrains.cidr.lang.psi.OCCompoundInitializer;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.jetbrains.cidr.lang.types.OCStructType;
import com.jetbrains.cidr.lang.symbols.OCResolveContext;
import com.jetbrains.cidr.lang.psi.OCAssignmentExpression;
import java.util.List;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCDeclarator;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.codeInsight.intention.HighPriorityAction;

public class OCRemoveDeclarationButInitializerIntentionAction extends OCRemoveDeclarationIntentionAction implements HighPriorityAction
{
    public OCRemoveDeclarationButInitializerIntentionAction(final OCSymbol ocSymbol) {
        super(ocSymbol);
    }
    
    public OCRemoveDeclarationButInitializerIntentionAction(final String s, final OCSymbol ocSymbol) {
        super(s, ocSymbol);
    }
    
    public OCRemoveDeclarationButInitializerIntentionAction(final String s, @Nullable final PsiElement psiElement) {
        super(s, psiElement);
    }
    
    @NotNull
    public String getText() {
        String string;
        try {
            string = super.getText() + " and leave initializer";
            if (string == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getText"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        return string;
    }
    
    @Override
    public boolean isAvailable() {
        final PsiElement validElementOrNull = this.getValidElementOrNull();
        try {
            if (validElementOrNull == null) {
                return false;
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        Label_0071: {
            Label_0050: {
                try {
                    if (this.mySymbol == null) {
                        break Label_0050;
                    }
                    final OCRemoveDeclarationButInitializerIntentionAction ocRemoveDeclarationButInitializerIntentionAction = this;
                    final OCSymbol ocSymbol = ocRemoveDeclarationButInitializerIntentionAction.mySymbol;
                    final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.LOCAL_VARIABLE;
                    if (ocSymbolKind != ocSymbolKind2) {
                        return false;
                    }
                    break Label_0050;
                }
                catch (IncorrectOperationException ex2) {
                    throw d(ex2);
                }
                try {
                    final OCRemoveDeclarationButInitializerIntentionAction ocRemoveDeclarationButInitializerIntentionAction = this;
                    final OCSymbol ocSymbol = ocRemoveDeclarationButInitializerIntentionAction.mySymbol;
                    final OCSymbolKind ocSymbolKind = ocSymbol.getKind();
                    final OCSymbolKind ocSymbolKind2 = OCSymbolKind.LOCAL_VARIABLE;
                    if (ocSymbolKind != ocSymbolKind2) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw d(ex3);
                }
                try {
                    if (!a(validElementOrNull)) {
                        return false;
                    }
                    final PsiElement psiElement = validElementOrNull;
                    final boolean b = c(psiElement);
                    if (b) {
                        break Label_0071;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex4) {
                    throw d(ex4);
                }
            }
            try {
                final PsiElement psiElement = validElementOrNull;
                final boolean b = c(psiElement);
                if (b) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw d(ex5);
            }
        }
        return false;
    }
    
    @Override
    public void invoke(@NotNull final Project project, @Nullable final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "invoke"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        PsiElement psiElement = null;
        Label_0083: {
            try {
                PsiDocumentManager.getInstance(project).commitAllDocuments();
                if (this.mySymbol != null) {
                    psiElement = this.mySymbol.locateDefinition();
                    break Label_0083;
                }
            }
            catch (IncorrectOperationException ex2) {
                throw d(ex2);
            }
            psiElement = this.myElementPtr.getElement();
        }
        final PsiElement psiElement2 = psiElement;
        Label_0118: {
            try {
                if (psiElement2 == null) {
                    return;
                }
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final PsiElement psiElement3 = psiElement2;
                final PsiFile psiFile2 = psiElement3.getContainingFile();
                final boolean b = fileModificationService.prepareFileForWrite(psiFile2);
                if (!b) {
                    return;
                }
                break Label_0118;
            }
            catch (IncorrectOperationException ex3) {
                throw d(ex3);
            }
            try {
                final FileModificationService fileModificationService = FileModificationService.getInstance();
                final PsiElement psiElement3 = psiElement2;
                final PsiFile psiFile2 = psiElement3.getContainingFile();
                final boolean b = fileModificationService.prepareFileForWrite(psiFile2);
                if (!b) {
                    return;
                }
            }
            catch (IncorrectOperationException ex4) {
                throw d(ex4);
            }
        }
        final OCStatement ocStatement = (OCStatement)PsiTreeUtil.getContextOfType(psiElement2, (Class)OCStatement.class, false);
        final PsiElement a = a(psiElement2, project);
        try {
            if (ocStatement == null) {
                return;
            }
        }
        catch (IncorrectOperationException ex5) {
            throw d(ex5);
        }
        Object o = null;
        Label_0244: {
            Label_0208: {
                Label_0193: {
                    try {
                        OCRemoveDeclarationIntentionAction.deleteUsages(psiElement2);
                        if (!(ocStatement instanceof OCDeclarationStatement)) {
                            break Label_0193;
                        }
                        final OCStatement ocStatement2 = ocStatement;
                        final OCDeclarationStatement ocDeclarationStatement = (OCDeclarationStatement)ocStatement2;
                        final OCDeclaration ocDeclaration = ocDeclarationStatement.getDeclaration();
                        final List<OCDeclarator> list = ocDeclaration.getDeclarators();
                        final int n = list.size();
                        final boolean b2 = true;
                        if (n == (b2 ? 1 : 0)) {
                            break Label_0193;
                        }
                        break Label_0208;
                    }
                    catch (IncorrectOperationException ex6) {
                        throw d(ex6);
                    }
                    try {
                        final OCStatement ocStatement2 = ocStatement;
                        final OCDeclarationStatement ocDeclarationStatement = (OCDeclarationStatement)ocStatement2;
                        final OCDeclaration ocDeclaration = ocDeclarationStatement.getDeclaration();
                        final List<OCDeclarator> list = ocDeclaration.getDeclarators();
                        final int n = list.size();
                        final boolean b2 = true;
                        if (n == (b2 ? 1 : 0)) {
                            OCChangeUtil.replaceHandlingMacros((PsiElement)ocStatement, a);
                            return;
                        }
                    }
                    catch (IncorrectOperationException ex7) {
                        throw d(ex7);
                    }
                }
                try {
                    ocStatement.getParent().addAfter(a, (PsiElement)ocStatement);
                    if (psiElement2 instanceof OCDeclarator) {
                        o = psiElement2;
                        break Label_0244;
                    }
                }
                catch (IncorrectOperationException ex8) {
                    throw d(ex8);
                }
            }
            o = ocStatement;
        }
        OCChangeUtil.delete((PsiElement)o);
    }
    
    @NotNull
    private static PsiElement a(@NotNull final PsiElement psiElement, @NotNull final Project project) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getInitializerStatement"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getInitializerStatement"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw d(ex2);
        }
        OCExpressionStatement ocExpressionStatement;
        if (psiElement instanceof OCAssignmentExpression) {
            ocExpressionStatement = b(psiElement);
            OCChangeUtil.replaceHandlingMacros((PsiElement)ocExpressionStatement.getExpression(), (PsiElement)((OCAssignmentExpression)psiElement).getSourceExpression());
        }
        else if (psiElement instanceof OCDeclarator) {
            final OCDeclarator ocDeclarator = (OCDeclarator)psiElement;
            final OCType resolve = ocDeclarator.getType().resolve(new OCResolveContext((PsiElement)ocDeclarator));
            OCExpressionStatement ocExpressionStatement2 = null;
            Label_0183: {
                try {
                    if (resolve instanceof OCStructType) {
                        ocExpressionStatement2 = b(ocDeclarator, project);
                        break Label_0183;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw d(ex3);
                }
                ocExpressionStatement2 = a(ocDeclarator);
            }
            ocExpressionStatement = ocExpressionStatement2;
        }
        else {
            ocExpressionStatement = b(psiElement);
        }
        Object o = null;
        Label_0227: {
            Label_0213: {
                try {
                    if (OCCodeInsightUtil.insideLoopHeader(psiElement)) {
                        break Label_0213;
                    }
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = OCCodeInsightUtil.insideConditionalHeader(psiElement2);
                    if (b) {
                        break Label_0213;
                    }
                    break Label_0213;
                }
                catch (IncorrectOperationException ex4) {
                    throw d(ex4);
                }
                try {
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = OCCodeInsightUtil.insideConditionalHeader(psiElement2);
                    if (b) {
                        final Object expression;
                        o = (expression = ocExpressionStatement.getExpression());
                        break Label_0227;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw d(ex5);
                }
            }
            Object expression;
            o = (expression = ocExpressionStatement);
            try {
                if (expression == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getInitializerStatement"));
                }
            }
            catch (IncorrectOperationException ex6) {
                throw d(ex6);
            }
        }
        return (PsiElement)o;
    }
    
    @NotNull
    private static OCExpressionStatement b(@NotNull final OCDeclarator ocDeclarator, @NotNull final Project project) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "buildStructTypeInitializer"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "buildStructTypeInitializer"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw d(ex2);
        }
        OCExpression ocExpression = ocDeclarator.getInitializer();
        if (ocExpression == null) {
            ocExpression = ocDeclarator.getInitializerList();
        }
        if (ocExpression instanceof OCCompoundInitializer) {
            final String string = a(ocDeclarator, project) + ocExpression.getText();
            OCExpressionStatement ocExpressionStatement;
            try {
                ocExpressionStatement = (OCExpressionStatement)OCElementFactory.statementFromText(string, (PsiElement)ocDeclarator);
                if (ocExpressionStatement == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "buildStructTypeInitializer"));
                }
            }
            catch (IncorrectOperationException ex3) {
                throw d(ex3);
            }
            return ocExpressionStatement;
        }
        if (ocExpression != null) {
            final OCExpressionStatement b = b((PsiElement)ocDeclarator);
            OCExpressionStatement ocExpressionStatement2;
            try {
                OCChangeUtil.replaceHandlingMacros((PsiElement)b.getExpression(), (PsiElement)ocExpression);
                ocExpressionStatement2 = b;
                if (ocExpressionStatement2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "buildStructTypeInitializer"));
                }
            }
            catch (IncorrectOperationException ex4) {
                throw d(ex4);
            }
            return ocExpressionStatement2;
        }
        final String a = a(ocDeclarator, project);
        final OCArgumentList argumentList = ocDeclarator.getArgumentList();
        String s = null;
        Label_0331: {
            try {
                if (argumentList == null) {
                    s = a + "()";
                    break Label_0331;
                }
            }
            catch (IncorrectOperationException ex5) {
                throw d(ex5);
            }
            s = "(" + a + argumentList.getText() + ")";
        }
        final String s2 = s;
        OCExpressionStatement ocExpressionStatement3;
        try {
            ocExpressionStatement3 = (OCExpressionStatement)OCElementFactory.statementFromText(s2, (PsiElement)ocDeclarator);
            if (ocExpressionStatement3 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "buildStructTypeInitializer"));
            }
        }
        catch (IncorrectOperationException ex6) {
            throw d(ex6);
        }
        return ocExpressionStatement3;
    }
    
    @NotNull
    private static OCExpressionStatement a(@NotNull final OCDeclarator ocDeclarator) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "buildBuiltinTypeInitializer"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        OCExpression ocExpression = ocDeclarator.getInitializers().get(0);
        if (ocExpression instanceof OCCompoundInitializer) {
            ocExpression = ((OCCompoundInitializer)ocExpression).getInitializerExpressions().get(0);
        }
        final OCExpressionStatement b = b((PsiElement)ocDeclarator);
        OCExpressionStatement ocExpressionStatement;
        try {
            OCChangeUtil.replaceHandlingMacros((PsiElement)b.getExpression(), (PsiElement)ocExpression);
            ocExpressionStatement = b;
            if (ocExpressionStatement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "buildBuiltinTypeInitializer"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw d(ex2);
        }
        return ocExpressionStatement;
    }
    
    @NotNull
    private static OCExpressionStatement b(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getDefaultInitializer"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        OCExpressionStatement ocExpressionStatement;
        try {
            ocExpressionStatement = (OCExpressionStatement)OCElementFactory.statementFromText("1", psiElement);
            if (ocExpressionStatement == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getDefaultInitializer"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw d(ex2);
        }
        return ocExpressionStatement;
    }
    
    private static boolean a(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "canLeaveInitializer"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        if (psiElement instanceof OCAssignmentExpression) {
            final OCExpression sourceExpression = ((OCAssignmentExpression)psiElement).getSourceExpression();
            Label_0081: {
                try {
                    if (sourceExpression == null) {
                        return false;
                    }
                    final OCExpression ocExpression = sourceExpression;
                    final boolean b = OCCodeInsightUtil.hasSideEffects(ocExpression);
                    if (b) {
                        break Label_0081;
                    }
                    return false;
                }
                catch (IncorrectOperationException ex2) {
                    throw d(ex2);
                }
                try {
                    final OCExpression ocExpression = sourceExpression;
                    final boolean b = OCCodeInsightUtil.hasSideEffects(ocExpression);
                    if (b) {
                        return true;
                    }
                }
                catch (IncorrectOperationException ex3) {
                    throw d(ex3);
                }
            }
            return false;
        }
        if (psiElement instanceof OCDeclarator) {
            final OCDeclarator ocDeclarator = (OCDeclarator)psiElement;
            final OCResolveContext ocResolveContext = new OCResolveContext((PsiElement)ocDeclarator);
            final OCType resolve = ocDeclarator.getType().resolve(ocResolveContext);
            Label_0150: {
                try {
                    if (resolve.isUnknown()) {
                        return false;
                    }
                    final OCType ocType = resolve;
                    final boolean b3 = ocType instanceof OCArrayType;
                    if (b3) {
                        return false;
                    }
                    break Label_0150;
                }
                catch (IncorrectOperationException ex4) {
                    throw d(ex4);
                }
                try {
                    final OCType ocType = resolve;
                    final boolean b3 = ocType instanceof OCArrayType;
                    if (b3) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex5) {
                    throw d(ex5);
                }
            }
            final List<OCExpression> initializers = ocDeclarator.getInitializers();
            Label_0230: {
                Label_0188: {
                    try {
                        if (!(resolve instanceof OCStructType)) {
                            break Label_0230;
                        }
                        final List<OCExpression> list = initializers;
                        final boolean b4 = list.isEmpty();
                        if (!b4) {
                            return true;
                        }
                        break Label_0188;
                    }
                    catch (IncorrectOperationException ex6) {
                        throw d(ex6);
                    }
                    try {
                        final List<OCExpression> list = initializers;
                        final boolean b4 = list.isEmpty();
                        if (!b4) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex7) {
                        throw d(ex7);
                    }
                }
                final OCFunctionSymbol resolveCtorCall = OCResolveUtil.resolveCtorCall(initializers, (OCStructType)resolve, ocResolveContext);
                Label_0220: {
                    try {
                        if (resolveCtorCall == null) {
                            return false;
                        }
                        final OCFunctionSymbol ocFunctionSymbol = resolveCtorCall;
                        final boolean b5 = ocFunctionSymbol.isDefault();
                        if (!b5) {
                            break Label_0220;
                        }
                        return false;
                    }
                    catch (IncorrectOperationException ex8) {
                        throw d(ex8);
                    }
                    try {
                        final OCFunctionSymbol ocFunctionSymbol = resolveCtorCall;
                        final boolean b5 = ocFunctionSymbol.isDefault();
                        if (!b5) {
                            return true;
                        }
                    }
                    catch (IncorrectOperationException ex9) {
                        throw d(ex9);
                    }
                }
                return false;
                try {
                    if (initializers.size() != 1) {
                        return false;
                    }
                }
                catch (IncorrectOperationException ex10) {
                    throw d(ex10);
                }
            }
            final OCExpression ocExpression2 = initializers.get(0);
            try {
                if (!OCCodeInsightUtil.hasSideEffects(ocExpression2)) {
                    return false;
                }
            }
            catch (IncorrectOperationException ex11) {
                throw d(ex11);
            }
            try {
                if (!(ocExpression2 instanceof OCCompoundInitializer)) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex12) {
                throw d(ex12);
            }
            final OCCompoundInitializer ocCompoundInitializer = (OCCompoundInitializer)ocExpression2;
            try {
                if (ocCompoundInitializer.getInitializerExpressions().size() == 1) {
                    return true;
                }
            }
            catch (IncorrectOperationException ex13) {
                throw d(ex13);
            }
            return false;
        }
        return false;
    }
    
    private static boolean c(@NotNull final PsiElement p0) {
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
        //    24: ldc             "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "canTransformElement"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    43: athrow         
        //    44: aload_0        
        //    45: instanceof      Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    48: ifeq            73
        //    51: aload_0        
        //    52: checkcast       Lcom/jetbrains/cidr/lang/psi/OCAssignmentExpression;
        //    55: astore_1       
        //    56: aload_1        
        //    57: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //    60: ifne            71
        //    63: iconst_1       
        //    64: goto            72
        //    67: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //    70: athrow         
        //    71: iconst_0       
        //    72: ireturn        
        //    73: aload_0        
        //    74: instanceof      Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    77: ifeq            212
        //    80: aload_0        
        //    81: checkcast       Lcom/jetbrains/cidr/lang/psi/OCDeclarator;
        //    84: astore_1       
        //    85: aload_1        
        //    86: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializerList:()Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //    91: astore_2       
        //    92: aload_2        
        //    93: ifnonnull       103
        //    96: aload_1        
        //    97: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getInitializer:()Lcom/jetbrains/cidr/lang/psi/OCExpression;
        //   102: astore_2       
        //   103: aload_2        
        //   104: ifnull          164
        //   107: aload_2        
        //   108: instanceof      Lcom/jetbrains/cidr/lang/psi/OCCompoundInitializer;
        //   111: ifeq            164
        //   114: goto            121
        //   117: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   120: athrow         
        //   121: aload_2        
        //   122: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   125: ifne            162
        //   128: goto            135
        //   131: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   134: athrow         
        //   135: aload_2        
        //   136: invokeinterface com/jetbrains/cidr/lang/psi/OCExpression.getFirstChild:()Lcom/intellij/psi/PsiElement;
        //   141: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   144: ifne            162
        //   147: goto            154
        //   150: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   153: athrow         
        //   154: iconst_1       
        //   155: goto            163
        //   158: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   161: athrow         
        //   162: iconst_0       
        //   163: ireturn        
        //   164: aload_1        
        //   165: invokeinterface com/jetbrains/cidr/lang/psi/OCDeclarator.getArgumentList:()Lcom/jetbrains/cidr/lang/psi/OCArgumentList;
        //   170: astore_3       
        //   171: aload_3        
        //   172: ifnull          195
        //   175: aload_3        
        //   176: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   179: ifeq            195
        //   182: goto            189
        //   185: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   188: athrow         
        //   189: iconst_0       
        //   190: ireturn        
        //   191: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   194: athrow         
        //   195: aload_1        
        //   196: invokestatic    com/jetbrains/cidr/lang/util/OCElementUtil.isPartOfMacroSubstitution:(Lcom/intellij/psi/PsiElement;)Z
        //   199: ifne            210
        //   202: iconst_1       
        //   203: goto            211
        //   206: invokestatic    com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction.d:(Lcom/intellij/util/IncorrectOperationException;)Lcom/intellij/util/IncorrectOperationException;
        //   209: athrow         
        //   210: iconst_0       
        //   211: ireturn        
        //   212: iconst_0       
        //   213: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                           
        //  -----  -----  -----  -----  -----------------------------------------------
        //  0      40     40     44     Lcom/intellij/util/IncorrectOperationException;
        //  56     67     67     71     Lcom/intellij/util/IncorrectOperationException;
        //  103    114    117    121    Lcom/intellij/util/IncorrectOperationException;
        //  107    128    131    135    Lcom/intellij/util/IncorrectOperationException;
        //  121    147    150    154    Lcom/intellij/util/IncorrectOperationException;
        //  135    158    158    162    Lcom/intellij/util/IncorrectOperationException;
        //  171    182    185    189    Lcom/intellij/util/IncorrectOperationException;
        //  175    191    191    195    Lcom/intellij/util/IncorrectOperationException;
        //  195    206    206    210    Lcom/intellij/util/IncorrectOperationException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0121:
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
    
    private static String a(@NotNull final OCDeclarator ocDeclarator, @NotNull final Project project) {
        try {
            if (ocDeclarator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "declarator", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getTypeName"));
            }
        }
        catch (IncorrectOperationException ex) {
            throw d(ex);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/quickfixes/OCRemoveDeclarationButInitializerIntentionAction", "getTypeName"));
            }
        }
        catch (IncorrectOperationException ex2) {
            throw d(ex2);
        }
        return ocDeclarator.getType().cloneWithoutCVQualifiers(project).getBestNameInContext((PsiElement)ocDeclarator);
    }
    
    private static IncorrectOperationException d(final IncorrectOperationException ex) {
        return ex;
    }
}
