// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.completion;

import org.jetbrains.annotations.NotNull;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.jetbrains.cidr.lang.parser.OCElementType;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.jetbrains.cidr.lang.parser.OCTokenTypes;
import com.jetbrains.cidr.lang.settings.OCCodeStyleSettings;
import com.intellij.lang.Language;
import com.jetbrains.cidr.lang.OCLanguage;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.Document;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.jetbrains.cidr.lang.refactoring.util.OCChangeUtil;
import com.intellij.codeInsight.folding.CodeFoldingManager;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.completion.InsertHandler;

public class TemplateInsertHandler implements InsertHandler<LookupElement>
{
    private final String myInsertion;
    
    private TemplateInsertHandler(final String myInsertion) {
        this.myInsertion = myInsertion;
    }
    
    public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
        final Document document = insertionContext.getDocument();
        final Editor editor = insertionContext.getEditor();
        String s = a(insertionContext, this.myInsertion);
        final char c = (insertionContext.getTailOffset() < document.getTextLength()) ? document.getCharsSequence().charAt(insertionContext.getTailOffset()) : '\0';
        if (s.endsWith(" ") && insertionContext.getCompletionChar() != ' ' && (c == ')' || c == ' ' || c == '\t')) {
            s = s.trim();
        }
        int tailOffset = insertionContext.getStartOffset() + s.length();
        final String s2 = "<caret>";
        final int index = s.indexOf(s2);
        if (index != -1) {
            s = s.substring(0, index) + s.substring(index + s2.length(), s.length());
            tailOffset = insertionContext.getStartOffset() + index;
        }
        else if (!Character.isWhitespace(c) && !Character.isUnicodeIdentifierPart(c) && s.endsWith(Character.toString(c))) {
            s = s.substring(0, s.length() - 1);
        }
        document.replaceString(insertionContext.getStartOffset(), insertionContext.getTailOffset(), (CharSequence)s);
        insertionContext.setTailOffset(tailOffset);
        CodeFoldingManager.getInstance(insertionContext.getProject()).updateFoldRegions(editor);
        final String text = document.getText();
        final int index2 = text.indexOf("<#", insertionContext.getStartOffset());
        final int index3 = text.indexOf("#>", index2);
        final SelectionModel selectionModel = editor.getSelectionModel();
        if (index2 >= 0 && index3 >= 0 && index2 < insertionContext.getTailOffset()) {
            selectionModel.setSelection(index2, index3 + 2);
        }
        if (!s.endsWith(" ")) {
            OCChangeUtil.reformatTextIfNotInjected(insertionContext.getFile(), insertionContext.getStartOffset(), insertionContext.getStartOffset() + s.length());
        }
        if (index2 >= 0 && index3 >= 0 && selectionModel.hasSelection()) {
            editor.getCaretModel().moveToOffset(selectionModel.getSelectionStart());
        }
        else {
            final int tailOffset2 = insertionContext.getTailOffset();
            editor.getCaretModel().moveToOffset(tailOffset2);
            if (tailOffset2 > 0 && editor.getDocument().getCharsSequence().charAt(tailOffset2 - 1) == '\n') {
                CodeStyleManager.getInstance(insertionContext.getProject()).adjustLineIndent(editor.getDocument(), tailOffset2);
            }
        }
        if (insertionContext.getCompletionChar() == ' ') {
            insertionContext.setAddCompletionChar(false);
        }
    }
    
    private static String a(final InsertionContext insertionContext, String s) {
        final CodeStyleSettings settings = CodeStyleSettingsManager.getSettings(insertionContext.getProject());
        final CommonCodeStyleSettings commonSettings = settings.getCommonSettings((Language)OCLanguage.getInstance());
        final OCCodeStyleSettings ocCodeStyleSettings = (OCCodeStyleSettings)settings.getCustomSettings((Class)OCCodeStyleSettings.class);
        s = a(s, OCTokenTypes.FOR_KEYWORD, commonSettings.SPACE_BEFORE_FOR_PARENTHESES);
        s = a(s, OCTokenTypes.IF_KEYWORD, commonSettings.SPACE_BEFORE_IF_PARENTHESES);
        s = a(s, OCTokenTypes.WHILE_KEYWORD, commonSettings.SPACE_BEFORE_WHILE_PARENTHESES);
        s = a(s, OCTokenTypes.SWITCH_KEYWORD, commonSettings.SPACE_BEFORE_SWITCH_PARENTHESES);
        s = a(s, OCTokenTypes.CATCH_KEYWORD, commonSettings.SPACE_BEFORE_CATCH_PARENTHESES);
        s = a(s, OCTokenTypes.FINALLY_KEYWORD, commonSettings.SPACE_BEFORE_FINALLY_KEYWORD);
        s = a(s, OCTokenTypes.SYNCHRONIZED_KEYWORD, commonSettings.SPACE_BEFORE_SYNCHRONIZED_PARENTHESES);
        s = a(s, OCTokenTypes.PROPERTY_KEYWORD, ocCodeStyleSettings.SPACE_BEFORE_PROPERTY_ATTRIBUTES_PARENTHESES);
        return s;
    }
    
    private static String a(final String s, final OCElementType ocElementType, final boolean b) {
        if (s.equals(ocElementType.getName())) {
            return b ? (ocElementType.getName() + " ") : ocElementType.getName();
        }
        return s;
    }
    
    public static LookupElementBuilder lookup(final String s) {
        return lookup(s.replaceAll("\\n|(<caret>)|(<#(.*)#>)", "").trim(), s);
    }
    
    public static LookupElementBuilder lookup(final String s, final String s2) {
        return LookupElementBuilder.create((Object)new TemplateObject(s), s).withInsertHandler((InsertHandler)new TemplateInsertHandler(s2));
    }
    
    public static class TemplateObject
    {
        @NotNull
        private String myData;
        
        public TemplateObject(@NotNull final String myData) {
            if (myData == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "data", "com/jetbrains/cidr/lang/editor/completion/TemplateInsertHandler$TemplateObject", "<init>"));
            }
            this.myData = myData;
        }
        
        @Override
        public String toString() {
            return this.myData;
        }
        
        @Override
        public boolean equals(final Object o) {
            try {
                if (this == o) {
                    return true;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            Label_0039: {
                try {
                    if (o == null) {
                        return false;
                    }
                    final TemplateObject templateObject = this;
                    final Class<? extends TemplateObject> clazz = templateObject.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                    break Label_0039;
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
                try {
                    final TemplateObject templateObject = this;
                    final Class<? extends TemplateObject> clazz = templateObject.getClass();
                    final Object o2 = o;
                    final Class<?> clazz2 = o2.getClass();
                    if (clazz != clazz2) {
                        return false;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final TemplateObject templateObject2 = (TemplateObject)o;
            try {
                if (!this.myData.equals(templateObject2.myData)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return this.myData.hashCode();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
}
