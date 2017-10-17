// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.refactoring.introduce;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.intellij.psi.PsiNamedElement;
import com.intellij.codeInsight.highlighting.ReadWriteAccessDetector;
import com.jetbrains.cidr.lang.search.usages.OCReadWriteAccessDetector;
import java.util.Iterator;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.featureStatistics.FeatureUsageTracker;
import com.intellij.openapi.command.CommandProcessor;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.search.LocalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.impl.DocumentMarkupModel;
import java.awt.Color;
import com.intellij.ui.JBColor;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.TextAttributes;
import com.intellij.openapi.application.ApplicationManager;
import com.jetbrains.cidr.lang.util.OCParenthesesUtils;
import com.jetbrains.cidr.lang.psi.OCExpression;
import java.util.ArrayList;
import com.intellij.util.ArrayUtil;
import java.util.Collection;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.Pair;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.util.ui.JBUI;
import javax.swing.Box;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.AbstractButton;
import com.intellij.util.ui.DialogUtil;
import javax.swing.JPanel;
import com.intellij.util.Processor;
import com.intellij.ui.NonFocusableCheckBox;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.jetbrains.cidr.lang.psi.OCFile;
import java.awt.Component;
import com.intellij.openapi.fileTypes.FileType;
import com.jetbrains.cidr.lang.OCFileType;
import com.intellij.util.containers.ContainerUtil;
import java.lang.reflect.Array;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import java.util.List;
import javax.swing.JCheckBox;
import com.jetbrains.cidr.lang.types.OCType;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.refactoring.introduce.inplace.AbstractInplaceIntroducer;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public abstract class OCBaseInplaceIntroducer<V extends PsiNameIdentifierOwner, E extends PsiElement> extends AbstractInplaceIntroducer<V, E>
{
    private RangeMarker myVarMarker;
    protected PsiFile myVarFile;
    private Class<V> myVariableClass;
    private boolean myCreateFromUsageMode;
    protected OCSymbol myParentSymbol;
    protected String myUsageName;
    protected OCType myExprType;
    protected String myOldDocumentText;
    protected JCheckBox myPutToHeaderCb;
    protected List<MyPsiPointer> myOccurrencePtrs;
    protected MyPsiPointer myExprPtr;
    private MyPsiPointer myAnchorPtr;
    
    public OCBaseInplaceIntroducer(final Project project, final Editor editor, final E e, final List<E> list, final Class<V> myVariableClass, final Class<E> clazz, final String s) {
        super(project, editor, e, null, (PsiElement[])ContainerUtil.toArray((List)list, (Object[])Array.newInstance(clazz, list.size())), s, (FileType)OCFileType.INSTANCE);
        this.myVariableClass = myVariableClass;
        this.myExprPtr = new MyPsiPointer(e);
    }
    
    public void configurePanel() {
        try {
            if (!this.isPreviewDisabled()) {
                this.myWholePanel.add(this.getPreviewComponent());
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final PsiElement parent = this.myAnchorPtr.getElement().getParent();
        Label_0065: {
            try {
                if (!(parent instanceof OCFile)) {
                    return;
                }
                final PsiElement psiElement = parent;
                final OCFile ocFile = (OCFile)psiElement;
                final boolean b = ocFile.isHeader();
                if (!b) {
                    break Label_0065;
                }
                return;
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
            try {
                final PsiElement psiElement = parent;
                final OCFile ocFile = (OCFile)psiElement;
                final boolean b = ocFile.isHeader();
                if (b) {
                    return;
                }
                if (((OCFile)parent).getAssociatedFile() == null) {
                    return;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        final OCCodeStyleSettings ocCodeStyleSettings;
        this.myPutToHeaderCb = (JCheckBox)this.createCheckBox("Put to &header", ((OCCodeStyleSettings)CodeStyleSettingsManager.getSettings(this.myProject).getCustomSettings((Class)OCCodeStyleSettings.class)).INTRODUCE_GLOBALS_TO_HEADER, () -> ocCodeStyleSettings.INTRODUCE_GLOBALS_TO_HEADER = this.myPutToHeaderCb.isSelected());
    }
    
    protected NonFocusableCheckBox createCheckBox(final String s, final boolean b, @Nullable final Runnable runnable) {
        return createCheckBox(this.myProject, this.myTitle, this.myWholePanel, s, b, (Processor<Boolean>)(b -> {
            try {
                if (runnable != null) {
                    runnable.run();
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return true;
        }));
    }
    
    public static NonFocusableCheckBox createCheckBox(final Project project, final String s, final JPanel panel, final String s2, final boolean selected, @Nullable final Processor<Boolean> processor) {
        final NonFocusableCheckBox nonFocusableCheckBox = new NonFocusableCheckBox(s2);
        try {
            nonFocusableCheckBox.setSelected(selected);
            DialogUtil.registerMnemonic((AbstractButton)nonFocusableCheckBox, '&');
            addControl(panel, (JComponent)nonFocusableCheckBox);
            if (processor != null) {
                nonFocusableCheckBox.addActionListener((ActionListener)new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent actionEvent) {
                        new WriteCommandAction(project, s, s, new PsiFile[0]) {
                            protected void run(@NotNull final Result result) throws Throwable {
                                try {
                                    if (result == null) {
                                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer$1$1", "run"));
                                    }
                                }
                                catch (Throwable t) {
                                    throw a(t);
                                }
                                processor.process((Object)nonFocusableCheckBox.isSelected());
                            }
                            
                            private static Throwable a(final Throwable t) {
                                return t;
                            }
                        }.execute();
                    }
                });
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return nonFocusableCheckBox;
    }
    
    protected static void addControl(final JPanel panel, final JComponent component) {
        int componentCount = panel.getComponentCount();
        boolean b = false;
        int n = 0;
        boolean b2 = false;
        boolean b3 = false;
        double n2 = 0.0;
        double n3 = 0.0;
        int n4 = 0;
        boolean b4 = false;
        int n5 = 0;
        Label_0040: {
            try {
                b = false;
                n = componentCount++;
                b2 = true;
                b3 = true;
                n2 = 1.0;
                n3 = 0.0;
                n4 = 18;
                b4 = false;
                if (componentCount > 0) {
                    n5 = 0;
                    break Label_0040;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            n5 = 5;
        }
        panel.add(component, new GridBagConstraints((int)(b ? 1 : 0), n, (int)(b2 ? 1 : 0), (int)(b3 ? 1 : 0), n2, n3, n4, (int)(b4 ? 1 : 0), new Insets(n5, 5, 5, 5), 0, 0));
        panel.add(Box.createVerticalBox(), new GridBagConstraints(0, panel.getComponentCount(), 1, 1, 1.0, 1.0, 18, 1, (Insets)JBUI.emptyInsets(), 0, 0));
    }
    
    public void setCreateFromUsageMode(final boolean myCreateFromUsageMode) {
        this.myCreateFromUsageMode = myCreateFromUsageMode;
    }
    
    protected boolean isCreateFromUsageMode() {
        return this.myCreateFromUsageMode;
    }
    
    public void setParentSymbol(final OCSymbol myParentSymbol) {
        this.myParentSymbol = myParentSymbol;
    }
    
    public void setUsageName(final String myUsageName) {
        this.myUsageName = myUsageName;
    }
    
    @Override
    protected RangeMarker createMarker(final PsiElement psiElement) {
        return this.myEditor.getDocument().createRangeMarker(OCElementUtil.getRangeWithMacros(psiElement));
    }
    
    protected boolean changeUsages(final String s) {
        Label_0025: {
            try {
                if (!this.isCreateFromUsageMode()) {
                    break Label_0025;
                }
                final String s2 = s;
                final OCBaseInplaceIntroducer ocBaseInplaceIntroducer = this;
                final String s3 = ocBaseInplaceIntroducer.myUsageName;
                final boolean b = s2.equals(s3);
                if (!b) {
                    break Label_0025;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final String s2 = s;
                final OCBaseInplaceIntroducer ocBaseInplaceIntroducer = this;
                final String s3 = ocBaseInplaceIntroducer.myUsageName;
                final boolean b = s2.equals(s3);
                if (!b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return false;
    }
    
    public void setExprType(final OCType myExprType) {
        try {
            if (myExprType != null) {
                this.myExprType = myExprType;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    protected boolean isPreviewDisabled() {
        return false;
    }
    
    @Override
    protected void collectAdditionalElementsToRename(final List<Pair<PsiElement, TextRange>> list) {
        for (final PsiElement psiElement : this.getOccurrences()) {
            Label_0093: {
                try {
                    if (!psiElement.isValid()) {
                        break Label_0093;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                final OCMacroCall elementMacroCall = OCElementUtil.getElementMacroCall(psiElement);
                final TextRange rangeWithMacros = OCElementUtil.getRangeWithMacros(psiElement);
                Object o = null;
                Label_0071: {
                    try {
                        if (elementMacroCall != null) {
                            o = elementMacroCall;
                            break Label_0071;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw b(ex2);
                    }
                    o = psiElement;
                }
                list.add((Pair<PsiElement, TextRange>)Pair.create(o, (Object)new TextRange(0, rangeWithMacros.getLength())));
            }
        }
    }
    
    @Nullable
    @Override
    protected V getVariable() {
        try {
            if (this.myVarMarker == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (!this.myVarFile.isValid()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        PsiElement psiElement = this.myVarFile.getContainingFile().findElementAt(this.myVarMarker.getStartOffset());
        if (psiElement instanceof PsiWhiteSpace) {
            psiElement = PsiTreeUtil.skipSiblingsForward(psiElement, new Class[] { PsiWhiteSpace.class });
        }
        final PsiNameIdentifierOwner psiNameIdentifierOwner = (PsiNameIdentifierOwner)PsiTreeUtil.getParentOfType(psiElement, (Class)this.myVariableClass, false);
        try {
            if (psiNameIdentifierOwner != null) {
                return (V)psiNameIdentifierOwner;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        return (V)PsiTreeUtil.getParentOfType(PsiTreeUtil.skipSiblingsBackward(psiElement, new Class[] { PsiWhiteSpace.class }), (Class)this.myVariableClass, false);
    }
    
    protected void setVariable(final V v) {
        this.myVarFile = v.getContainingFile();
        this.myVarMarker = this.myEditor.getDocument().createRangeMarker(v.getTextRange());
    }
    
    public void setOccurrences(final Collection<E> collection) {
        this.myOccurrences = collection.toArray(ArrayUtil.ensureExactSize(collection.size(), (Object[])this.myOccurrences));
        this.myOccurrenceMarkers = null;
        this.myOccurrencePtrs = new ArrayList<MyPsiPointer>();
        final PsiElement[] occurrences = this.getOccurrences();
        for (int length = occurrences.length, i = 0; i < length; ++i) {
            this.myOccurrencePtrs.add(new MyPsiPointer(occurrences[i]));
        }
        this.initOccurrencesMarkers();
    }
    
    protected RangeMarker addOccurrence(final E e) {
        final RangeMarker rangeMarker = this.myEditor.getDocument().createRangeMarker(e.getTextRange());
        this.myOccurrences = (PsiElement[])ArrayUtil.append((Object[])this.myOccurrences, (Object)e);
        this.myOccurrenceMarkers.add(rangeMarker);
        this.myOccurrencePtrs.add(new MyPsiPointer(e));
        return rangeMarker;
    }
    
    protected E getMainExpression() {
        final PsiElement psiElement = this.myOccurrences[0];
        Label_0028: {
            try {
                if (!(psiElement instanceof OCExpression)) {
                    return (E)psiElement;
                }
                final PsiElement psiElement2 = psiElement;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement2);
                if (!b) {
                    break Label_0028;
                }
                return (E)psiElement;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final PsiElement psiElement2 = psiElement;
                final boolean b = OCElementUtil.isPartOfMacroSubstitution(psiElement2);
                if (!b) {
                    return (E)OCParenthesesUtils.diveIntoParentheses((OCExpression)psiElement);
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        return (E)psiElement;
    }
    
    protected PsiElement getAnchorParent(final PsiElement p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: invokeinterface com/intellij/psi/PsiElement.getParent:()Lcom/intellij/psi/PsiElement;
        //     6: astore_3       
        //     7: iload_2        
        //     8: ifne            108
        //    11: aload_0        
        //    12: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.myPutToHeaderCb:Ljavax/swing/JCheckBox;
        //    15: ifnull          108
        //    18: goto            25
        //    21: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    24: athrow         
        //    25: aload_0        
        //    26: getfield        com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.myPutToHeaderCb:Ljavax/swing/JCheckBox;
        //    29: invokevirtual   javax/swing/JCheckBox.isSelected:()Z
        //    32: ifeq            108
        //    35: goto            42
        //    38: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    41: athrow         
        //    42: aload_3        
        //    43: instanceof      Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    46: ifeq            108
        //    49: goto            56
        //    52: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    55: athrow         
        //    56: aload_3        
        //    57: checkcast       Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    60: astore          4
        //    62: aload           4
        //    64: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.isHeader:()Z
        //    69: ifne            81
        //    72: aload           4
        //    74: invokeinterface com/jetbrains/cidr/lang/psi/OCFile.getAssociatedFile:()Lcom/jetbrains/cidr/lang/psi/OCFile;
        //    79: astore          4
        //    81: aload           4
        //    83: ifnull          108
        //    86: aload           4
        //    88: invokestatic    com/intellij/refactoring/util/CommonRefactoringUtil.checkReadOnlyStatus:(Lcom/intellij/psi/PsiElement;)Z
        //    91: ifeq            108
        //    94: goto            101
        //    97: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   100: athrow         
        //   101: aload           4
        //   103: areturn        
        //   104: invokestatic    com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer.b:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   107: athrow         
        //   108: aload_3        
        //   109: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  7      18     21     25     Ljava/lang/IllegalArgumentException;
        //  11     35     38     42     Ljava/lang/IllegalArgumentException;
        //  25     49     52     56     Ljava/lang/IllegalArgumentException;
        //  81     94     97     101    Ljava/lang/IllegalArgumentException;
        //  86     104    104    108    Ljava/lang/IllegalArgumentException;
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
    
    @Nullable
    @Override
    protected JComponent getComponent() {
        return this.myWholePanel;
    }
    
    @Override
    protected void updateTitle(@Nullable final V v, final String s) {
        Label_0027: {
            try {
                if (v == null) {
                    return;
                }
                final Application application = ApplicationManager.getApplication();
                final boolean b = application.isUnitTestMode();
                if (b) {
                    return;
                }
                break Label_0027;
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            try {
                final Application application = ApplicationManager.getApplication();
                final boolean b = application.isUnitTestMode();
                if (b) {
                    return;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw b(ex2);
            }
        }
        final String name = v.getName();
        try {
            if (name == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        final PsiElement previewElement = this.getPreviewElement(v);
        final int startOffset = previewElement.getTextRange().getStartOffset();
        final TextRange markupRange = this.getMarkupRange(v);
        String s2 = this.getVariableTextWithReplacedName(v, s);
        String substring = null;
        if (markupRange != null) {
            final int n = markupRange.getStartOffset() - startOffset;
            final int n2 = markupRange.getEndOffset() - startOffset - previewElement.getTextLength() + s2.length();
            substring = s2.substring(n, n2);
            s2 = s2.substring(0, n) + "<var>" + s2.substring(n2);
        }
        final String trimTextForPreview = this.trimTextForPreview(s2);
        Label_0207: {
            try {
                if (markupRange != null) {
                    this.setPreviewText(trimTextForPreview.replaceAll("<var>", substring));
                    break Label_0207;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            this.setPreviewText(trimTextForPreview);
        }
        if (markupRange != null) {
            final TextAttributes textAttributes = new TextAttributes();
            textAttributes.setEffectType(EffectType.ROUNDED_BOX);
            textAttributes.setEffectColor((Color)JBColor.RED);
            final MarkupModel forDocument = DocumentMarkupModel.forDocument(this.getPreviewEditor().getDocument(), this.myProject, true);
            forDocument.removeAllHighlighters();
            final int index = trimTextForPreview.indexOf("<var>");
            forDocument.addRangeHighlighter(index, index + substring.length(), 0, textAttributes, HighlighterTargetArea.EXACT_RANGE);
        }
        this.revalidate();
    }
    
    protected String getVariableTextWithReplacedName(final V v, final String s) {
        final PsiElement previewElement = this.getPreviewElement(v);
        final String textWithMacros = OCElementUtil.getTextWithMacros(previewElement);
        final int index = textWithMacros.indexOf(v.getName(), v.getTextOffset() - previewElement.getTextRange().getStartOffset());
        try {
            if (index >= 0) {
                return textWithMacros.substring(0, index) + s + textWithMacros.substring(index + v.getName().length());
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return textWithMacros;
    }
    
    protected String trimTextForPreview(final String s) {
        final StringBuilder sb = new StringBuilder();
        for (final String s2 : StringUtil.splitByLines(s)) {
            try {
                if (sb.length() > 0) {
                    sb.append(' ');
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            sb.append(s2.trim());
        }
        return sb.toString();
    }
    
    @Override
    protected void updateTitle(@Nullable final V v) {
        try {
            if (v != null) {
                this.updateTitle(v, v.getName());
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Nullable
    protected TextRange getMarkupRange(final V v) {
        return null;
    }
    
    protected PsiElement getPreviewElement(final V v) {
        return (PsiElement)v;
    }
    
    @Override
    protected SearchScope getReferencesSearchScope(final VirtualFile virtualFile) {
        return (SearchScope)new LocalSearchScope((PsiElement)this.myElementToRename.getContainingFile());
    }
    
    @Override
    protected PsiElement checkLocalScope() {
        return (PsiElement)this.myElementToRename.getContainingFile();
    }
    
    @Override
    protected String getActionName() {
        return this.myTitle;
    }
    
    @Override
    protected V createFieldToStartTemplateOn(final boolean b, final String[] array) {
        final String s;
        Label_0039: {
            try {
                this.myOldDocumentText = this.myEditor.getDocument().getText();
                if (this.getInputName() != null) {
                    this.getInputName();
                    break Label_0039;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            s = array[0];
        }
        ApplicationManager.getApplication().runWriteAction(() -> this.introduceForPreview(s));
        this.d();
        return this.getVariable();
    }
    
    private static boolean b(PsiElement parent) {
        while (true) {
            try {
                if (parent == null || parent instanceof PsiFile) {
                    break;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            parent = parent.getParent();
        }
        try {
            if (parent == null) {
                return true;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return false;
    }
    
    private void d() {
        for (int i = 0; i < this.myOccurrences.length; ++i) {
            final RangeMarker rangeMarker = this.myOccurrenceMarkers.get(i);
            final PsiElement elementAtRange = OCCodeInsightUtil.findElementAtRange(this.myVarFile, new TextRange(rangeMarker.getStartOffset(), rangeMarker.getEndOffset()), this.myOccurrencePtrs.get(i).getElementClass(), true);
            Label_0096: {
                try {
                    if (elementAtRange == null) {
                        continue;
                    }
                    final OCBaseInplaceIntroducer ocBaseInplaceIntroducer = this;
                    final PsiElement[] array = ocBaseInplaceIntroducer.myOccurrences;
                    final int n = i;
                    final PsiElement psiElement = array[n];
                    final boolean b = b(psiElement);
                    if (b) {
                        break Label_0096;
                    }
                    continue;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCBaseInplaceIntroducer ocBaseInplaceIntroducer = this;
                    final PsiElement[] array = ocBaseInplaceIntroducer.myOccurrences;
                    final int n = i;
                    final PsiElement psiElement = array[n];
                    final boolean b = b(psiElement);
                    if (b) {
                        this.myOccurrences[i] = elementAtRange;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
    }
    
    @Override
    protected int getCaretOffset() {
        if (this.isPreviewDisabled()) {
            final PsiNameIdentifierOwner variable = this.getVariable();
            try {
                if (variable != null) {
                    return variable.getTextOffset();
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            return super.getCaretOffset();
        }
        return super.getCaretOffset();
    }
    
    @Override
    protected int restoreCaretOffset(int restoreCaretOffset) {
        restoreCaretOffset = super.restoreCaretOffset(restoreCaretOffset);
        try {
            if (restoreCaretOffset >= this.myEditor.getDocument().getTextLength()) {
                return 0;
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return restoreCaretOffset;
    }
    
    @Override
    protected void performIntroduce() {
        ApplicationManager.getApplication().runWriteAction(() -> {
            CommandProcessor.getInstance().setCurrentCommandName(this.myTitle);
            FeatureUsageTracker.getInstance().triggerFeatureUsed(this.getFeatureID());
            this.introduceForReal(this.getInputName());
        });
    }
    
    public void cancelIntroduce() {
        this.releaseResources();
    }
    
    @Override
    protected void restoreState(@NotNull final V v) {
        try {
            if (v == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "psiField", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer", "restoreState"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Document document;
        final PsiDocumentManager psiDocumentManager;
        final ArrayList<E> occurrences;
        final Iterator<MyPsiPointer> iterator;
        final E e;
        ApplicationManager.getApplication().runWriteAction(() -> {
            PsiDocumentManager.getInstance(this.myProject);
            this.myEditor.getDocument();
            document.setText((CharSequence)this.myOldDocumentText);
            psiDocumentManager.commitDocument(document);
            occurrences = new ArrayList<E>();
            this.myOccurrencePtrs.iterator();
            while (iterator.hasNext()) {
                iterator.next().getElement();
                try {
                    if (e != null) {
                        occurrences.add(e);
                    }
                    else {
                        continue;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            this.myExpr = (E)this.myExprPtr.getElement();
            this.myExprMarker = this.createMarker(this.myExpr);
            this.setOccurrences(occurrences);
        });
    }
    
    @Override
    public void finish(final boolean b) {
        try {
            if (!this.myFinished) {
                super.finish(b);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    @Override
    public E restoreExpression(final PsiFile psiFile, final V v, final RangeMarker rangeMarker, final String s) {
        try {
            assert false;
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return null;
    }
    
    @Nullable
    protected PsiElement findAnchor(final PsiElement psiElement) {
        PsiElement psiElement2 = null;
        Label_0117: {
            for (final PsiElement psiElement3 : psiElement.getChildren()) {
                for (final PsiElement psiElement4 : this.myOccurrences) {
                    Label_0095: {
                        try {
                            if (!psiElement4.isValid()) {
                                break Label_0095;
                            }
                            final PsiElement psiElement5 = psiElement4;
                            final TextRange textRange = OCElementUtil.getRangeWithMacros(psiElement5);
                            final int n = textRange.getStartOffset();
                            final PsiElement psiElement6 = psiElement3;
                            final TextRange textRange2 = psiElement6.getTextRange();
                            final int n2 = textRange2.getStartOffset();
                            if (n < n2) {
                                break Label_0095;
                            }
                            break Label_0095;
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        try {
                            final PsiElement psiElement5 = psiElement4;
                            final TextRange textRange = OCElementUtil.getRangeWithMacros(psiElement5);
                            final int n = textRange.getStartOffset();
                            final PsiElement psiElement6 = psiElement3;
                            final TextRange textRange2 = psiElement6.getTextRange();
                            final int n2 = textRange2.getStartOffset();
                            if (n < n2) {
                                break Label_0117;
                            }
                        }
                        catch (IllegalArgumentException ex2) {
                            throw b(ex2);
                        }
                    }
                }
                psiElement2 = psiElement3;
            }
            try {
                if (psiElement2 == null) {
                    return null;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        PsiElement psiElement7 = OCElementUtil.getPrevSiblingOrParentSibling(psiElement2);
        while (true) {
            try {
                if (!(psiElement2 instanceof OCMacroCall)) {
                    if (!(psiElement7 instanceof OCMacroCall)) {
                        break;
                    }
                }
            }
            catch (IllegalArgumentException ex4) {
                throw b(ex4);
            }
            psiElement2 = psiElement7;
            psiElement7 = OCElementUtil.getPrevSiblingOrParentSibling(psiElement2);
        }
        return psiElement2;
    }
    
    protected boolean hasNonConstUsages() {
        final OCReadWriteAccessDetector ocReadWriteAccessDetector = new OCReadWriteAccessDetector();
        for (final PsiElement psiElement : this.myOccurrences) {
            Label_0066: {
                try {
                    if (ocReadWriteAccessDetector.getExpressionAccess(psiElement) != ReadWriteAccessDetector.Access.Read) {
                        return true;
                    }
                    final OCReadWriteAccessDetector ocReadWriteAccessDetector2 = ocReadWriteAccessDetector;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = false;
                    final boolean b2 = ocReadWriteAccessDetector2.canBeConstReference(psiElement2, b);
                    if (!b2) {
                        return true;
                    }
                    break Label_0066;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final OCReadWriteAccessDetector ocReadWriteAccessDetector2 = ocReadWriteAccessDetector;
                    final PsiElement psiElement2 = psiElement;
                    final boolean b = false;
                    final boolean b2 = ocReadWriteAccessDetector2.canBeConstReference(psiElement2, b);
                    if (!b2) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
        }
        return false;
    }
    
    @Nullable
    protected abstract String checkExpression(final E p0);
    
    @Nullable
    public abstract PsiElement evaluateAnchor();
    
    protected abstract void introduceForPreview(final String p0);
    
    protected abstract void introduceForReal(final String p0);
    
    @NotNull
    protected abstract String getFeatureID();
    
    public void setAnchor(final PsiElement psiElement) {
        this.myAnchorPtr = new MyPsiPointer(psiElement);
    }
    
    @NotNull
    protected PsiElement getAnchor() {
        PsiElement psiElement = this.myAnchorPtr.getElement();
        if (psiElement == null) {
            psiElement = this.evaluateAnchor();
        }
        PsiElement psiElement2;
        try {
            psiElement2 = psiElement;
            if (psiElement2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer", "getAnchor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return psiElement2;
    }
    
    @Nullable
    protected PsiElement getCommonContext() {
        return PsiTreeUtil.findCommonContext((Collection)ContainerUtil.filter((Object[])this.myOccurrences, psiElement -> psiElement.isValid()));
    }
    
    @Override
    public boolean isReplaceAllOccurrences() {
        return true;
    }
    
    @Override
    public void setReplaceAllOccurrences(final boolean b) {
    }
    
    @Override
    protected void saveSettings(@NotNull final V v) {
        try {
            if (v == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "variable", "com/jetbrains/cidr/lang/refactoring/introduce/OCBaseInplaceIntroducer", "saveSettings"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
    }
    
    static {
        boolean $assertionsDisabled2 = false;
        Label_0017: {
            try {
                if (!OCBaseInplaceIntroducer.class.desiredAssertionStatus()) {
                    $assertionsDisabled2 = true;
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw b(ex);
            }
            $assertionsDisabled2 = false;
        }
        $assertionsDisabled = $assertionsDisabled2;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    protected static class MyPsiPointer
    {
        PsiFile file;
        TextRange range;
        Class<? extends PsiElement> clazz;
        
        MyPsiPointer(final PsiElement psiElement) {
            this.file = psiElement.getContainingFile();
            this.range = OCElementUtil.getRangeWithMacros(psiElement);
            this.clazz = psiElement.getClass();
        }
        
        Class<? extends PsiElement> getElementClass() {
            return this.clazz;
        }
        
        @Nullable
        PsiElement getElement() {
            return OCCodeInsightUtil.findElementAtRange(this.file, this.range, this.clazz, true);
        }
    }
}
