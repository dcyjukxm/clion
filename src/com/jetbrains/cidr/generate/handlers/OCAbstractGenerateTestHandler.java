// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.generate.handlers;

import com.intellij.codeInsight.template.TemplateEditingListener;
import com.intellij.openapi.editor.RangeMarker;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Segment;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.codeInsight.template.TemplateEditingAdapter;
import com.intellij.refactoring.util.CommonRefactoringUtil;
import com.intellij.ide.util.EditorHelper;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Editor;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.jetbrains.cidr.lang.psi.OCBlockStatement;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiElement;
import com.intellij.ide.fileTemplates.FileTemplate;
import java.util.Properties;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import java.io.IOException;
import java.util.regex.Matcher;
import com.intellij.codeInsight.template.Expression;
import com.intellij.codeInsight.template.impl.ConstantNode;
import java.util.HashSet;
import java.util.regex.Pattern;
import com.intellij.codeInsight.template.TemplateManager;
import com.intellij.codeInsight.template.Template;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.fileTemplates.FileTemplateDescriptor;
import java.util.Collection;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.lang.generate.handlers.OCClassActionHandlerBase;
import com.jetbrains.cidr.lang.generate.actions.OCActionContext;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.symbols.symtable.OCMembersContainer;

public abstract class OCAbstractGenerateTestHandler<P extends OCMembersContainer, M extends OCSymbolWithParent<?, ?>, C extends OCActionContext<P, M>> extends OCClassActionHandlerBase<P, M, C>
{
    private static final Logger LOG;
    private final String myActionTitle;
    private final String myTemplateName;
    
    protected OCAbstractGenerateTestHandler(final String myActionTitle, final String myTemplateName) {
        this.myActionTitle = myActionTitle;
        this.myTemplateName = myTemplateName;
    }
    
    @Override
    protected String getActionTitle() {
        return this.myActionTitle;
    }
    
    @Override
    protected String getMembersChooserTitle() {
        return "";
    }
    
    @Override
    protected boolean enableChooseDialog(final Collection<M> collection) {
        return false;
    }
    
    @Override
    protected boolean allowEmptySelection(final C c) {
        return true;
    }
    
    @NotNull
    private Template a(@NotNull final FileTemplateDescriptor fileTemplateDescriptor, final Project project) {
        try {
            if (fileTemplateDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateDesc", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "createTestMethodTemplate"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final Template template = TemplateManager.getInstance(project).createTemplate("", "");
        final String templateText = this.getTemplateText(fileTemplateDescriptor, project);
        int end = 0;
        final Matcher matcher = Pattern.compile("\\$\\{(\\w+)}").matcher(templateText);
        final HashSet<String> set = new HashSet<String>();
        while (matcher.find()) {
            final String group = matcher.group(1);
            Label_0203: {
                try {
                    template.addTextSegment(templateText.substring(end, matcher.start()));
                    if (group.equals("END")) {
                        template.addEndVariable();
                        break Label_0203;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
                try {
                    if (set.contains(group)) {
                        template.addVariableSegment(group);
                        break Label_0203;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw b(ex3);
                }
                final ConstantNode constantNode = new ConstantNode(group);
                template.addVariable(group, constantNode, constantNode, true);
                set.add(group);
            }
            end = matcher.end();
        }
        Template template2;
        try {
            template.addTextSegment(templateText.substring(end, templateText.length()));
            template.setToIndent(true);
            template.setToReformat(true);
            template.setToShortenLongNames(true);
            template2 = template;
            if (template2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "createTestMethodTemplate"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        return template2;
    }
    
    @NotNull
    protected String getTemplateText(@NotNull final FileTemplateDescriptor fileTemplateDescriptor, final Project project) {
        try {
            if (fileTemplateDescriptor == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "templateDesc", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "getTemplateText"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        final FileTemplate codeTemplate = FileTemplateManager.getInstance(project).getCodeTemplate(fileTemplateDescriptor.getFileName());
        String s;
        try {
            s = codeTemplate.getText(new Properties());
        }
        catch (IOException ex2) {
            OCAbstractGenerateTestHandler.LOG.warn((Throwable)ex2);
            s = codeTemplate.getText();
        }
        String s2;
        try {
            s2 = s;
            if (s2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "getTemplateText"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        return s2;
    }
    
    protected abstract int getInsertPos(@NotNull final PsiElement p0, final int p1, @Nullable final PsiElement p2, @NotNull final List<M> p3, @NotNull final C p4);
    
    protected boolean shouldSelectResult(@NotNull final OCBlockStatement ocBlockStatement) {
        try {
            if (ocBlockStatement == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "body", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "shouldSelectResult"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return false;
    }
    
    @Nullable
    protected PsiElement getElementToModify(@NotNull final C c) {
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "context", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "getElementToModify"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        final List<? extends OCSymbol> symbolsToModify = c.getSymbolsToModify();
        PsiElement locateDefinition = null;
        if (symbolsToModify.size() != 0) {
            locateDefinition = symbolsToModify.get(0).locateDefinition();
        }
        try {
            if (locateDefinition == null) {
                OCAbstractGenerateTestHandler.LOG.warn("Can't locate element to modify");
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return locateDefinition;
    }
    
    @Override
    protected void performAction(@NotNull final Project project, @Nullable final Editor editor, @NotNull final PsiFile psiFile, @NotNull final C c, @NotNull final List<M> list) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (c == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "actionContext", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "chosenCandidates", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "performAction"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final PsiElement elementToModify = this.getElementToModify(c);
        try {
            if (elementToModify == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        final PsiFile containingFile = elementToModify.getContainingFile();
        new WriteCommandAction(project, this.getActionTitle(), new PsiFile[] { containingFile }) {
            protected void run(@NotNull final Result result) throws Throwable {
                try {
                    if (result == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "result", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler$1", "run"));
                    }
                }
                catch (Throwable t) {
                    throw a(t);
                }
                int offset = 0;
                Label_0073: {
                    try {
                        if (editor != null) {
                            offset = editor.getCaretModel().getOffset();
                            break Label_0073;
                        }
                    }
                    catch (Throwable t2) {
                        throw a(t2);
                    }
                    offset = -1;
                }
                final int n = offset;
                PsiElement psiElement = null;
                if (editor != null) {
                    psiElement = psiFile.findElementAt(n);
                    if (psiElement == null) {
                        psiElement = psiFile.getLastChild();
                    }
                }
                try {
                    if (!FileModificationService.getInstance().prepareFileForWrite(containingFile)) {
                        return;
                    }
                }
                catch (Throwable t3) {
                    throw a(t3);
                }
                if (elementToModify.isValid()) {
                    final VirtualFile virtualFile = containingFile.getVirtualFile();
                    try {
                        if (virtualFile != null) {
                            CommandProcessor.getInstance().addAffectedFiles(project, new VirtualFile[] { virtualFile });
                        }
                    }
                    catch (Throwable t4) {
                        throw a(t4);
                    }
                    final Editor openInEditor = EditorHelper.openInEditor((PsiElement)containingFile);
                    final int insertPos = OCAbstractGenerateTestHandler.this.getInsertPos(elementToModify, n, psiElement, list, c);
                    Label_0230: {
                        try {
                            if (openInEditor == null) {
                                break Label_0230;
                            }
                            final int n2 = insertPos;
                            final int n3 = -1;
                            if (n2 == n3) {
                                break Label_0230;
                            }
                            break Label_0230;
                        }
                        catch (Throwable t5) {
                            throw a(t5);
                        }
                        try {
                            final int n2 = insertPos;
                            final int n3 = -1;
                            if (n2 == n3) {
                                CommonRefactoringUtil.showErrorHint(project, openInEditor, "Action is invalid for the current caret position", OCAbstractGenerateTestHandler.this.getActionTitle(), (String)null);
                                return;
                            }
                        }
                        catch (Throwable t6) {
                            throw a(t6);
                        }
                    }
                    openInEditor.getCaretModel().moveToOffset(insertPos);
                    TemplateManager.getInstance(project).startTemplate(openInEditor, OCAbstractGenerateTestHandler.this.a(new FileTemplateDescriptor(OCAbstractGenerateTestHandler.this.myTemplateName), project), new TemplateEditingAdapter() {
                        @Override
                        public void templateFinished(final Template template, final boolean b) {
                            PsiElement psiElement = containingFile.findElementAt(insertPos);
                            if (psiElement instanceof PsiWhiteSpace) {
                                psiElement = psiElement.getNextSibling();
                            }
                            if (psiElement != null) {
                                final RangeMarker rangeMarker = openInEditor.getDocument().createRangeMarker(psiElement.getTextRange());
                                OCAbstractGenerateTestHandler.this.onTemplateFinished(containingFile, rangeMarker);
                                OCCodeInsightUtil.showCallableInEditorAndSelectBody(containingFile, (Segment)rangeMarker, (Condition<OCBlockStatement>)OCAbstractGenerateTestHandler.this::shouldSelectResult);
                            }
                        }
                    });
                }
            }
            
            private static Throwable a(final Throwable t) {
                return t;
            }
        }.execute();
    }
    
    protected void onTemplateFinished(@NotNull final PsiFile psiFile, @NotNull final RangeMarker rangeMarker) {
        try {
            if (psiFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "onTemplateFinished"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (rangeMarker == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "rangeMarker", "com/jetbrains/cidr/generate/handlers/OCAbstractGenerateTestHandler", "onTemplateFinished"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
    }
    
    static {
        LOG = Logger.getInstance(OCAbstractGenerateTestHandler.class.getName());
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
