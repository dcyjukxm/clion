// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import java.util.HashMap;
import java.util.Map;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import com.intellij.patterns.ObjectPattern;
import com.jetbrains.cidr.lang.psi.OCDeclarationStatement;
import com.jetbrains.cidr.lang.psi.OCExpression;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.PsiElementPattern;
import com.intellij.psi.impl.source.tree.LeafElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCExpressionStatement;
import com.intellij.codeInsight.template.EverywhereContextType;
import com.jetbrains.cidr.lang.editor.colors.OCFileHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.psi.OCFile;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.codeInsight.template.TemplateContextType;

public abstract class OCCodeContextType extends TemplateContextType
{
    @NotNull
    protected final OCLanguageKind myNeedKind;
    
    protected OCCodeContextType(@NotNull final OCLanguageKind myNeedKind, @NotNull @NonNls final String s, @NotNull final String s2, @Nullable final Class<? extends TemplateContextType> clazz) {
        if (myNeedKind == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType", "<init>"));
        }
        if (s2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "presentableName", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType", "<init>"));
        }
        super(s, s2, (Class)clazz);
        this.myNeedKind = myNeedKind;
    }
    
    public boolean isInContext(@NotNull final PsiFile psiFile, final int n) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType", "isInContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCLanguageKind kind = null;
        Label_0068: {
            try {
                if (psiFile instanceof OCFile) {
                    kind = ((OCFile)psiFile).getKind();
                    break Label_0068;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            kind = null;
        }
        final OCLanguageKind ocLanguageKind = kind;
        try {
            if (ocLanguageKind == null || !ocLanguageKind.conforms(this.myNeedKind)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        final PsiElement element = psiFile.findElementAt(n);
        try {
            if (element instanceof PsiWhiteSpace) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        Label_0137: {
            try {
                if (element == null) {
                    return false;
                }
                final OCCodeContextType ocCodeContextType = this;
                final PsiElement psiElement = element;
                final boolean b = ocCodeContextType.isInContext(psiElement);
                if (b) {
                    break Label_0137;
                }
                return false;
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                final OCCodeContextType ocCodeContextType = this;
                final PsiElement psiElement = element;
                final boolean b = ocCodeContextType.isInContext(psiElement);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
        }
        return false;
    }
    
    protected boolean isInContext(@NotNull final PsiElement psiElement) {
        try {
            if (psiElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType", "isInContext"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return true;
    }
    
    @NotNull
    public SyntaxHighlighter createHighlighter() {
        OCFileHighlighter ocFileHighlighter;
        try {
            ocFileHighlighter = new OCFileHighlighter(this.myNeedKind, false, false, false);
            if (ocFileHighlighter == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType", "createHighlighter"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (SyntaxHighlighter)ocFileHighlighter;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class LanguageBase extends OCCodeContextType
    {
        public LanguageBase(@NotNull final OCLanguageKind ocLanguageKind) {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageBase", "<init>"));
            }
            super(ocLanguageKind, LanguageInfo.getContextName(ocLanguageKind), ocLanguageKind.getDisplayName(), (Class<? extends TemplateContextType>)EverywhereContextType.class);
        }
    }
    
    public static class LanguageObjC extends LanguageBase
    {
        public LanguageObjC() {
            super(OCLanguageKind.OBJ_C);
        }
    }
    
    public static class LanguageCPP extends LanguageBase
    {
        public LanguageCPP() {
            super(OCLanguageKind.CPP);
        }
    }
    
    public static class LanguageC extends LanguageBase
    {
        public LanguageC() {
            super(OCLanguageKind.C);
        }
    }
    
    public static class StatementBase extends OCCodeContextType
    {
        public StatementBase(@NotNull final OCLanguageKind ocLanguageKind) {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$StatementBase", "<init>"));
            }
            super(ocLanguageKind, "OC_STATEMENT" + LanguageInfo.getSuffix(ocLanguageKind), "Statement", LanguageInfo.getBaseContext(ocLanguageKind));
        }
        
        @Override
        protected boolean isInContext(@NotNull final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$StatementBase", "isInContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return a(psiElement);
        }
        
        private static boolean a(final PsiElement psiElement) {
            try {
                if (!a(psiElement)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (OCCompletionPatterns.AT_STATEMENT_LEVEL.accepts((Object)psiElement)) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            final OCExpressionStatement ocExpressionStatement = (OCExpressionStatement)PsiTreeUtil.getParentOfType(psiElement, (Class)OCExpressionStatement.class, true);
            try {
                if (ocExpressionStatement == null) {
                    return false;
                }
                final OCExpressionStatement ocExpressionStatement2 = ocExpressionStatement;
                final ASTNode astNode = ocExpressionStatement2.getNode();
                final LeafElement leafElement = TreeUtil.findFirstLeaf(astNode);
                final PsiElement psiElement2 = psiElement;
                final ASTNode astNode2 = psiElement2.getNode();
                if (leafElement == astNode2) {
                    return true;
                }
                return false;
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
            try {
                final OCExpressionStatement ocExpressionStatement2 = ocExpressionStatement;
                final ASTNode astNode = ocExpressionStatement2.getNode();
                final LeafElement leafElement = TreeUtil.findFirstLeaf(astNode);
                final PsiElement psiElement2 = psiElement;
                final ASTNode astNode2 = psiElement2.getNode();
                if (leafElement == astNode2) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            return false;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class StatementObjC extends StatementBase
    {
        public StatementObjC() {
            super(OCLanguageKind.OBJ_C);
        }
    }
    
    public static class StatementCPP extends StatementBase
    {
        public StatementCPP() {
            super(OCLanguageKind.CPP);
        }
    }
    
    public static class StatementC extends StatementBase
    {
        public StatementC() {
            super(OCLanguageKind.C);
        }
    }
    
    public static class ExpressionBase extends OCCodeContextType
    {
        public ExpressionBase(@NotNull final OCLanguageKind ocLanguageKind) {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$ExpressionBase", "<init>"));
            }
            super(ocLanguageKind, "OC_EXPRESSION" + LanguageInfo.getSuffix(ocLanguageKind), "Expression", LanguageInfo.getBaseContext(ocLanguageKind));
        }
        
        @Override
        protected boolean isInContext(@NotNull final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$ExpressionBase", "isInContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return a(psiElement);
        }
        
        private static boolean a(final PsiElement psiElement) {
            Label_0076: {
                Label_0051: {
                    try {
                        if (((PsiElementPattern.Capture)OCCompletionPatterns.REFERENCE_ELEMENT.andNot((ElementPattern)OCCompletionPatterns.AFTER_DOT)).accepts((Object)psiElement)) {
                            break Label_0051;
                        }
                        final PsiElementPattern.Capture<PsiElement> capture = OCCompletionPatterns.TYPE_IN_DECLARATION;
                        final PsiElementPattern.Capture<PsiElement> capture2 = OCCompletionPatterns.AFTER_DOT;
                        final ObjectPattern objectPattern = capture.andNot((ElementPattern)capture2);
                        final PsiElementPattern.Capture capture3 = (PsiElementPattern.Capture)objectPattern;
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = capture3.accepts((Object)psiElement2);
                        if (!b) {
                            return false;
                        }
                        break Label_0051;
                    }
                    catch (IllegalArgumentException ex) {
                        throw b(ex);
                    }
                    try {
                        final PsiElementPattern.Capture<PsiElement> capture = OCCompletionPatterns.TYPE_IN_DECLARATION;
                        final PsiElementPattern.Capture<PsiElement> capture2 = OCCompletionPatterns.AFTER_DOT;
                        final ObjectPattern objectPattern = capture.andNot((ElementPattern)capture2);
                        final PsiElementPattern.Capture capture3 = (PsiElementPattern.Capture)objectPattern;
                        final PsiElement psiElement2 = psiElement;
                        final boolean b = capture3.accepts((Object)psiElement2);
                        if (!b) {
                            return false;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    try {
                        if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCExpression.class) != null) {
                            break Label_0076;
                        }
                        final PsiElement psiElement3 = psiElement;
                        final Class<OCDeclarationStatement> clazz = OCDeclarationStatement.class;
                        final PsiElement psiElement4 = PsiTreeUtil.getParentOfType(psiElement3, (Class)clazz);
                        if (psiElement4 != null) {
                            break Label_0076;
                        }
                        return false;
                    }
                    catch (IllegalArgumentException ex3) {
                        throw b(ex3);
                    }
                }
                try {
                    final PsiElement psiElement3 = psiElement;
                    final Class<OCDeclarationStatement> clazz = OCDeclarationStatement.class;
                    final PsiElement psiElement4 = PsiTreeUtil.getParentOfType(psiElement3, (Class)clazz);
                    if (psiElement4 != null) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw b(ex4);
                }
            }
            return false;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class ExpressionObjC extends ExpressionBase
    {
        public ExpressionObjC() {
            super(OCLanguageKind.OBJ_C);
        }
    }
    
    public static class ExpressionCPP extends ExpressionBase
    {
        public ExpressionCPP() {
            super(OCLanguageKind.CPP);
        }
    }
    
    public static class ExpressionC extends ExpressionBase
    {
        public ExpressionC() {
            super(OCLanguageKind.C);
        }
    }
    
    public static class DeclarationBase extends OCCodeContextType
    {
        public DeclarationBase(@NotNull final OCLanguageKind ocLanguageKind) {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "needKind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$DeclarationBase", "<init>"));
            }
            super(ocLanguageKind, "OC_DECLARATION" + LanguageInfo.getSuffix(ocLanguageKind), "Declaration", LanguageInfo.getBaseContext(ocLanguageKind));
        }
        
        @Override
        protected boolean isInContext(@NotNull final PsiElement psiElement) {
            try {
                if (psiElement == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$DeclarationBase", "isInContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                if (PsiTreeUtil.getParentOfType(psiElement, (Class)OCBlockStatement.class) == null) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            return false;
        }
        
        private static IllegalArgumentException b(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    public static class DeclarationObjC extends DeclarationBase
    {
        public DeclarationObjC() {
            super(OCLanguageKind.OBJ_C);
        }
    }
    
    public static class DeclarationCPP extends DeclarationBase
    {
        public DeclarationCPP() {
            super(OCLanguageKind.CPP);
        }
    }
    
    public static class DeclarationC extends DeclarationBase
    {
        public DeclarationC() {
            super(OCLanguageKind.C);
        }
    }
    
    private static class LanguageInfo
    {
        private static final Map<OCLanguageKind, LanguageInfo> LANGUAGE_INFO;
        @NotNull
        @NonNls
        private final String languageContextName;
        @NotNull
        private final String languageSuffixName;
        @NotNull
        private final Class<? extends LanguageBase> baseContextType;
        
        private LanguageInfo(@NotNull @NonNls final String languageContextName, @NotNull final String languageSuffixName, @NotNull final Class<? extends LanguageBase> baseContextType) {
            if (languageContextName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "<init>"));
            }
            if (languageSuffixName == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "suffix", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "<init>"));
            }
            if (baseContextType == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "baseClass", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "<init>"));
            }
            this.languageContextName = languageContextName;
            this.languageSuffixName = languageSuffixName;
            this.baseContextType = baseContextType;
        }
        
        @NotNull
        @NonNls
        public static String getContextName(@NotNull final OCLanguageKind ocLanguageKind) {
            try {
                if (ocLanguageKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getContextName"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String languageContextName;
            try {
                languageContextName = LanguageInfo.LANGUAGE_INFO.get(ocLanguageKind).languageContextName;
                if (languageContextName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getContextName"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return languageContextName;
        }
        
        @NotNull
        public static String getSuffix(@NotNull final OCLanguageKind ocLanguageKind) {
            try {
                if (ocLanguageKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getSuffix"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String languageSuffixName;
            try {
                languageSuffixName = LanguageInfo.LANGUAGE_INFO.get(ocLanguageKind).languageSuffixName;
                if (languageSuffixName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getSuffix"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return languageSuffixName;
        }
        
        @NotNull
        public static Class<? extends LanguageBase> getBaseContext(@NotNull final OCLanguageKind ocLanguageKind) {
            try {
                if (ocLanguageKind == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getBaseContext"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Class<? extends LanguageBase> baseContextType;
            try {
                baseContextType = LanguageInfo.LANGUAGE_INFO.get(ocLanguageKind).baseContextType;
                if (baseContextType == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/editor/completion/OCCodeContextType$LanguageInfo", "getBaseContext"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return baseContextType;
        }
        
        static {
            (LANGUAGE_INFO = new HashMap<OCLanguageKind, LanguageInfo>(3)).put(OCLanguageKind.C, new LanguageInfo("c", "_C", LanguageC.class));
            LanguageInfo.LANGUAGE_INFO.put(OCLanguageKind.CPP, new LanguageInfo("cpp", "_CPP", LanguageCPP.class));
            LanguageInfo.LANGUAGE_INFO.put(OCLanguageKind.OBJ_C, new LanguageInfo("objc", "", LanguageObjC.class));
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
