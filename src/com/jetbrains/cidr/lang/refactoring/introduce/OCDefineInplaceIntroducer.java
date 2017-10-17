// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import java.util.Arrays;
import com.intellij.psi.PsiComment;
import org.jetbrains.annotations.NotNull;
import java.util.Iterator;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.OCDeclaration;
import com.jetbrains.cidr.lang.psi.OCStatement;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.psi.PsiDocumentManager;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.jetbrains.cidr.lang.util.OCElementFactory;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.cidr.lang.psi.OCFile;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import java.util.List;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.jetbrains.cidr.lang.psi.OCElement;
import com.jetbrains.cidr.lang.psi.OCDefineDirective;

public class OCDefineInplaceIntroducer extends OCBaseInplaceIntroducer<OCDefineDirective, OCElement>
{
    private PsiFile myFileCopy;
    private TextRange myExpressionRange;
    private Class myExpressionClass;
    
    public OCDefineInplaceIntroducer(final Project project, final Editor editor, final OCElement ocElement, final List<OCElement> list, final String s) {
        super(project, editor, (PsiElement)ocElement, (List<PsiElement>)list, OCDefineDirective.class, (Class<PsiElement>)OCElement.class, s);
        this.myFileCopy = (PsiFile)ocElement.getContainingFile().copy();
        this.myExpressionRange = ocElement.getTextRange();
        this.myExpressionClass = ocElement.getClass();
    }
    
    @Override
    protected String[] suggestNames(final boolean b, @Nullable final OCDefineDirective ocDefineDirective) {
        try {
            if (this.myUsageName != null) {
                return new String[] { this.myUsageName };
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return new String[] { "DEF" };
    }
    
    @Override
    protected String checkExpression(final OCElement ocElement) {
        return null;
    }
    
    @Override
    public PsiElement evaluateAnchor() {
        final OCElement ocElement = (OCElement)PsiTreeUtil.getContextOfType(this.getCommonContext(), false, new Class[] { OCFile.class });
        try {
            if (ocElement != null) {
                return this.findAnchor((PsiElement)ocElement);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return null;
    }
    
    private void a(final String s, final boolean b) {
        final PsiElement anchor = this.getAnchor();
        final OCDefineDirective variable = OCChangeUtil.addBefore(this.getAnchorParent(anchor, b), (OCDefineDirective)OCElementFactory.topLevelDeclarationFromText(this.a(s), anchor), anchor);
        try {
            if (b) {
                ((OCBaseInplaceIntroducer<OCDefineDirective, E>)this).setVariable(variable);
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
    }
    
    @Override
    protected OCDefineDirective getVariable() {
        final OCDefineDirective ocDefineDirective = super.getVariable();
        Label_0028: {
            try {
                if (ocDefineDirective == null) {
                    return ocDefineDirective;
                }
                final OCDefineDirective ocDefineDirective2 = ocDefineDirective;
                final PsiElement psiElement = ocDefineDirective2.getNameIdentifier();
                if (psiElement == null) {
                    break Label_0028;
                }
                return ocDefineDirective;
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                final OCDefineDirective ocDefineDirective2 = ocDefineDirective;
                final PsiElement psiElement = ocDefineDirective2.getNameIdentifier();
                if (psiElement == null) {
                    return null;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
        }
        return ocDefineDirective;
    }
    
    @Override
    protected void introduceForPreview(final String s) {
        this.a(s, true);
    }
    
    @Override
    protected void introduceForReal(final String s) {
        this.a(s, false);
        final OCElement ocElement = ((OCBaseInplaceIntroducer<V, OCElement>)this).getMainExpression();
        final Document document = this.myEditor.getDocument();
        this.initOccurrencesMarkers();
        PsiDocumentManager.getInstance(this.myProject).doPostponedOperationsAndUnblockDocument(document);
        for (final RangeMarker rangeMarker : this.myOccurrenceMarkers) {
            StringBuilder append = null;
            String s2 = null;
            Label_0115: {
                Label_0104: {
                    try {
                        append = new StringBuilder().append(s);
                        if (ocElement instanceof OCStatement) {
                            break Label_0104;
                        }
                        final OCElement ocElement2 = ocElement;
                        final boolean b = ocElement2 instanceof OCDeclaration;
                        if (b) {
                            break Label_0104;
                        }
                        break Label_0104;
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                    try {
                        final OCElement ocElement2 = ocElement;
                        final boolean b = ocElement2 instanceof OCDeclaration;
                        if (b) {
                            s2 = ";";
                            break Label_0115;
                        }
                    }
                    catch (IllegalStateException ex2) {
                        throw a(ex2);
                    }
                }
                s2 = "";
            }
            document.replaceString(rangeMarker.getStartOffset(), rangeMarker.getEndOffset(), (CharSequence)append.append(s2).toString());
        }
        PsiDocumentManager.getInstance(this.myProject).commitDocument(document);
    }
    
    @NotNull
    @Override
    protected String getFeatureID() {
        String s;
        try {
            s = "refactoring.introduceDefine";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCDefineInplaceIntroducer", "getFeatureID"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        return s;
    }
    
    @NotNull
    private static String a(@NotNull final OCElement ocElement) {
        try {
            if (ocElement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expression", "com/jetbrains/cidr/lang/refactoring/introduce/OCDefineInplaceIntroducer", "getTextWithLineCommentsReplacedWithBlockComments"));
            }
        }
        catch (IllegalStateException ex) {
            throw a(ex);
        }
        String s = ocElement.getTextWithMacros().trim();
        int n = 0;
        for (final PsiComment psiComment : PsiTreeUtil.findChildrenOfType((PsiElement)ocElement, (Class)PsiComment.class)) {
            final int startOffsetInParent = psiComment.getStartOffsetInParent();
            final int textLength = psiComment.getTextLength();
            try {
                if (!psiComment.getText().startsWith("//") || startOffsetInParent + textLength >= s.length()) {
                    continue;
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            s = s.substring(0, startOffsetInParent + n) + "/*" + psiComment.getText().substring(2) + "*/" + s.substring(startOffsetInParent + textLength + n);
            n += 2;
        }
        String s2;
        try {
            s2 = s;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCDefineInplaceIntroducer", "getTextWithLineCommentsReplacedWithBlockComments"));
            }
        }
        catch (IllegalStateException ex3) {
            throw a(ex3);
        }
        return s2;
    }
    
    private String a(final String s) {
        final OCElement ocElement = ((OCBaseInplaceIntroducer<V, OCElement>)this).getMainExpression();
        final String[] split = a(((OCBaseInplaceIntroducer<V, OCElement>)this).getMainExpression()).split("\n");
        int n = 0;
        while (true) {
            Label_0063: {
                try {
                    if (n >= split.length - 1) {
                        break;
                    }
                    final String[] array = split;
                    final int n2 = n;
                    final String s2 = array[n2];
                    final String s3 = s2.trim();
                    final String s4 = "\\";
                    final boolean b = s3.endsWith(s4);
                    if (!b) {
                        break Label_0063;
                    }
                    break Label_0063;
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                try {
                    final String[] array = split;
                    final int n2 = n;
                    final String s2 = array[n2];
                    final String s3 = s2.trim();
                    final String s4 = "\\";
                    final boolean b = s3.endsWith(s4);
                    if (!b) {
                        final StringBuilder sb = new StringBuilder();
                        final String[] array2 = split;
                        final int n3 = n;
                        array2[n3] = sb.append(array2[n3]).append("\\").toString();
                    }
                }
                catch (IllegalStateException ex2) {
                    throw a(ex2);
                }
            }
            ++n;
        }
        String s5 = "#define " + s + " " + String.join("\n", Arrays.asList(split));
        Label_0161: {
            try {
                if (ocElement instanceof OCStatement) {
                    break Label_0161;
                }
                final OCElement ocElement2 = ocElement;
                final boolean b2 = ocElement2 instanceof OCDeclaration;
                if (b2) {
                    break Label_0161;
                }
                return s5 + "\n\n";
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            try {
                final OCElement ocElement2 = ocElement;
                final boolean b2 = ocElement2 instanceof OCDeclaration;
                if (!b2) {
                    return s5 + "\n\n";
                }
                if (!s5.endsWith(";")) {
                    return s5 + "\n\n";
                }
            }
            catch (IllegalStateException ex4) {
                throw a(ex4);
            }
        }
        s5 = s5.substring(0, s5.length() - 1);
        return s5 + "\n\n";
    }
    
    private static IllegalStateException a(final IllegalStateException ex) {
        return ex;
    }
}
