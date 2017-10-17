// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import com.intellij.util.PlatformUtils;
import com.intellij.codeInsight.daemon.impl.UpdateHighlightersUtil;
import com.jetbrains.cidr.lang.symbols.symtable.OCSymbolTablesBuildingActivity;
import javax.swing.Icon;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.ui.InputValidator;
import com.intellij.openapi.util.TextRange;
import com.intellij.codeInsight.daemon.HighlightDisplayKey;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nls;
import com.intellij.util.IncorrectOperationException;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import java.util.ArrayList;
import com.jetbrains.cidr.lang.preprocessor.OCInclusionContext;
import com.jetbrains.cidr.lang.util.OCCodeInsightUtil;
import com.intellij.openapi.progress.ProgressIndicator;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.ASTNode;
import java.util.Collections;
import com.intellij.openapi.editor.Document;
import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import java.util.Collection;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.project.DumbAware;
import com.intellij.codeHighlighting.TextEditorHighlightingPass;

public class OCLargeFileHighlighter extends TextEditorHighlightingPass implements DumbAware
{
    private static final String LANGUAGES_STRING;
    private final PsiFile myFile;
    @NotNull
    private Collection<HighlightInfo> myHighlightInfos;
    
    public OCLargeFileHighlighter(final PsiFile myFile, final Document document) {
        super(myFile.getProject(), document);
        this.myHighlightInfos = (Collection<HighlightInfo>)Collections.emptyList();
        this.myFile = myFile;
    }
    
    @Nullable
    private static String a(@NotNull final ASTNode astNode) {
        try {
            if (astNode == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "node", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter", "findTooLongInlineHeader"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        for (final ASTNode astNode2 : astNode.getChildren((TokenSet)null)) {
            try {
                if (astNode2.getElementType() == OCTokenTypes.HEADER_TOO_LONG_INLINED_PATH_LITERAL) {
                    return astNode2.getText();
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            final String a = a(astNode2);
            try {
                if (a != null) {
                    return a;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return null;
    }
    
    @Override
    public void doCollectInformation(@NotNull final ProgressIndicator progressIndicator) {
        try {
            if (progressIndicator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "progress", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter", "doCollectInformation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myHighlightInfos = (Collection<HighlightInfo>)Collections.emptyList();
        final int textLength = this.myFile.getTextLength();
        String s;
        if (!OCCodeInsightUtil.isCodeInsightAvailable(this.myFile)) {
            s = "File length (" + textLength + " characters) exceeds configured limit for " + OCLargeFileHighlighter.LANGUAGES_STRING + " (" + OCCodeInsightUtil.getMaxFileLength() + " characters). Code insight features are not available.";
        }
        else {
            String s2 = a((ASTNode)this.myFile.getNode());
            if (s2 == null) {
                return;
            }
            final int index = s2.indexOf(58);
            String substring = "";
            if (index != -1) {
                substring = s2.substring(index + 1);
                s2 = s2.substring(0, index);
            }
            s = "File includes inline header (" + OCInclusionContext.extractPath(s2, false).getPath() + ") with length (" + substring + " characters) that exceeds configured limit for " + OCLargeFileHighlighter.LANGUAGES_STRING + " (" + OCCodeInsightUtil.getMaxFileLength() + " characters). Header is not parsed.";
        }
        final ArrayList<HighlightInfo> myHighlightInfos = new ArrayList<HighlightInfo>();
        final TextRange textRange = this.myFile.getTextRange();
        final HighlightInfo create = HighlightInfo.newHighlightInfo(HighlightInfoType.WARNING).range(textRange).fileLevelAnnotation().description(s).create();
        try {
            if (create == null) {
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        create.registerFix((IntentionAction)new IntentionAction() {
            @Nls
            @NotNull
            public String getText() {
                String s;
                try {
                    s = "Change the maximum file length";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter$1", "getText"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return s;
            }
            
            @Nls
            @NotNull
            public String getFamilyName() {
                String access$000;
                try {
                    access$000 = OCLargeFileHighlighter.LANGUAGES_STRING;
                    if (access$000 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter$1", "getFamilyName"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return access$000;
            }
            
            public boolean isAvailable(@NotNull final Project project, final Editor editor, final PsiFile psiFile) {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter$1", "isAvailable"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                return true;
            }
            
            public void invoke(@NotNull final Project project, final Editor editor, final PsiFile psiFile) throws IncorrectOperationException {
                try {
                    if (project == null) {
                        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter$1", "invoke"));
                    }
                }
                catch (IncorrectOperationException ex) {
                    throw a(ex);
                }
                OCLargeFileHighlighter.this.a();
            }
            
            public boolean startInWriteAction() {
                return false;
            }
            
            private static IncorrectOperationException a(final IncorrectOperationException ex) {
                return ex;
            }
        }, Collections.emptyList(), "Change the maximum file length", textRange, null);
        myHighlightInfos.add(create);
        this.myHighlightInfos = myHighlightInfos;
    }
    
    private void a() {
        final int maxFileLength = OCCodeInsightUtil.getMaxFileLength();
        final int int1 = StringUtil.parseInt(Messages.showInputDialog(this.myProject, "Enter the new maximum length for " + OCLargeFileHighlighter.LANGUAGES_STRING + " files in characters", "Maximum File Length", (Icon)null, String.valueOf(maxFileLength), (InputValidator)new InputValidator() {
            public boolean checkInput(final String s) {
                return StringUtil.parseInt(s, -1) > 0;
            }
            
            public boolean canClose(final String s) {
                return true;
            }
        }), maxFileLength);
        try {
            if (maxFileLength == int1) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        OCCodeInsightUtil.setMaxFileLength(int1);
        this.a((Collection<HighlightInfo>)Collections.emptyList());
        final OCSymbolTablesBuildingActivity instance = OCSymbolTablesBuildingActivity.getInstance(this.myProject);
        try {
            if (instance != null) {
                instance.rebuildSymbols(OCSymbolTablesBuildingActivity.Mode.FULL);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    @Override
    public void doApplyInformationToEditor() {
        this.a(this.myHighlightInfos);
    }
    
    @NotNull
    public Collection<HighlightInfo> getHighlightInfos() {
        Collection<HighlightInfo> myHighlightInfos;
        try {
            myHighlightInfos = this.myHighlightInfos;
            if (myHighlightInfos == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter", "getHighlightInfos"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myHighlightInfos;
    }
    
    private void a(@NotNull final Collection<HighlightInfo> collection) {
        try {
            if (collection == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "infos", "com/jetbrains/cidr/lang/daemon/OCLargeFileHighlighter", "applyToEditor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.myDocument != null) {
                UpdateHighlightersUtil.setHighlightersToEditor(this.myProject, this.myDocument, 0, this.myDocument.getTextLength(), collection, this.getColorsScheme(), this.getId());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
    }
    
    static {
        String languages_STRING = null;
        Label_0017: {
            try {
                if (PlatformUtils.isAppCode()) {
                    languages_STRING = "C/Objective-C/C++";
                    break Label_0017;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            languages_STRING = "C/C++";
        }
        LANGUAGES_STRING = languages_STRING;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
