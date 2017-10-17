// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.evaluation;

import com.intellij.openapi.util.text.StringUtil;
import java.util.regex.Matcher;
import org.jetbrains.annotations.NotNull;
import com.intellij.xdebugger.XDebuggerUtil;
import com.jetbrains.cidr.execution.debugger.backend.LLValue;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiWhiteSpace;
import com.intellij.util.DocumentUtil;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XSourcePosition;
import com.jetbrains.cidr.execution.debugger.CidrDebugProcess;
import java.util.regex.Pattern;

public abstract class CidrDebuggerTypesHelper
{
    private static final Pattern OPTIONAL_PATTERN;
    protected CidrDebugProcess myProcess;
    
    public CidrDebuggerTypesHelper(final CidrDebugProcess myProcess) {
        this.myProcess = myProcess;
    }
    
    protected static PsiElement getDefaultContextElement(final XSourcePosition xSourcePosition, final Project project) {
        try {
            if (xSourcePosition == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Document document = FileDocumentManager.getInstance().getDocument(xSourcePosition.getFile());
        try {
            if (document == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final PsiFile psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document);
        try {
            if (psiFile == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return a(xSourcePosition, document, psiFile);
    }
    
    @Nullable
    private static PsiElement a(final XSourcePosition xSourcePosition, final Document document, final PsiFile psiFile) {
        final int offset = xSourcePosition.getOffset();
        try {
            if (!DocumentUtil.isValidOffset(offset, document)) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final int lineEndOffset = document.getLineEndOffset(document.getLineNumber(offset));
        int i = offset;
        do {
            final PsiElement element = psiFile.findElementAt(i);
            if (element == null) {
                break;
            }
            Label_0087: {
                try {
                    if (element instanceof PsiWhiteSpace) {
                        break Label_0087;
                    }
                    final PsiElement psiElement = element;
                    final boolean b = psiElement instanceof PsiComment;
                    if (!b) {
                        return element;
                    }
                    break Label_0087;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final PsiElement psiElement = element;
                    final boolean b = psiElement instanceof PsiComment;
                    if (!b) {
                        return element;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            i = element.getTextRange().getEndOffset() + 1;
        } while (i < lineEndOffset);
        return psiFile.findElementAt(offset);
    }
    
    @Nullable
    public XSourcePosition computeSourcePosition(final XSourcePosition xSourcePosition, final LLValue llValue) {
        final PsiElement resolveToDeclaration = this.resolveToDeclaration(xSourcePosition, llValue);
        try {
            if (resolveToDeclaration == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return XDebuggerUtil.getInstance().createPositionByOffset(resolveToDeclaration.getContainingFile().getVirtualFile(), resolveToDeclaration.getTextOffset());
    }
    
    public boolean isCPPThisPsi(final CidrPhysicalValue cidrPhysicalValue) {
        return true;
    }
    
    public boolean isCStringType(final CidrPhysicalValue cidrPhysicalValue) {
        Label_0031: {
            try {
                if (cidrPhysicalValue.getType().equals("char *")) {
                    break Label_0031;
                }
                final CidrPhysicalValue cidrPhysicalValue2 = cidrPhysicalValue;
                final String s = cidrPhysicalValue2.getType();
                final String s2 = "char\\[.*\\]";
                final boolean b = s.matches(s2);
                if (b) {
                    break Label_0031;
                }
                return false;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CidrPhysicalValue cidrPhysicalValue2 = cidrPhysicalValue;
                final String s = cidrPhysicalValue2.getType();
                final String s2 = "char\\[.*\\]";
                final boolean b = s.matches(s2);
                if (b) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return false;
    }
    
    public abstract XSourcePosition computeSourcePosition(final CidrMemberValue p0);
    
    public PsiElement getContextElement(final XSourcePosition xSourcePosition) {
        return getDefaultContextElement(xSourcePosition, this.myProcess.getProject());
    }
    
    @Nullable
    public abstract XSourcePosition resolveProperty(@NotNull final CidrMemberValue p0, @Nullable final String p1);
    
    public abstract PsiElement resolveToDeclaration(final XSourcePosition p0, final LLValue p1);
    
    @Nullable
    public abstract Boolean isImplicitContextVariable(@NotNull final XSourcePosition p0, @NotNull final LLValue p1);
    
    @Nullable
    public static String unwrapSwiftOptionalType(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDebuggerTypesHelper", "unwrapSwiftOptionalType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Matcher matcher = CidrDebuggerTypesHelper.OPTIONAL_PATTERN.matcher(s);
        try {
            if (matcher.matches()) {
                return matcher.group(2);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return null;
    }
    
    @NotNull
    public static String unwrapSwiftOptionalTypeIfNecessary(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "typeName", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDebuggerTypesHelper", "unwrapSwiftOptionalTypeIfNecessary"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        String notNullize;
        try {
            notNullize = StringUtil.notNullize(unwrapSwiftOptionalType(s), s);
            if (notNullize == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/debugger/evaluation/CidrDebuggerTypesHelper", "unwrapSwiftOptionalTypeIfNecessary"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return notNullize;
    }
    
    static {
        OPTIONAL_PATTERN = Pattern.compile("Swift\\.(ImplicitlyUnwrapped)?Optional<(.*?)>");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
