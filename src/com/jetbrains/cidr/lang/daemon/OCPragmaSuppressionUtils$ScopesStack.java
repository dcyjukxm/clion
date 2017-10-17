// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon;

import java.util.ArrayList;
import java.util.List;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.util.OCElementUtil;
import com.intellij.openapi.editor.Document;
import com.jetbrains.cidr.lang.psi.OCPragma;
import java.util.Stack;

private static class ScopesStack
{
    private Stack<Scope> myStack;
    private Scope myCurScope;
    private Scope myRootScope;
    
    private ScopesStack() {
        this.myStack = new Stack<Scope>();
        this.myRootScope = new Scope();
        this.myCurScope = this.myRootScope;
    }
    
    private void a(final OCPragma ocPragma, final Document document) {
        final Pair<OCPragma.Mode, String> pragma = ocPragma.parsePragma();
        if (pragma != null) {
            TextRange rangeWithMacros = OCElementUtil.getRangeWithMacros((PsiElement)ocPragma);
            if (document != null) {
                final int lineNumber = document.getLineNumber(rangeWithMacros.getEndOffset());
                if (lineNumber < document.getLineCount() - 1) {
                    rangeWithMacros = new TextRange(rangeWithMacros.getStartOffset(), document.getLineStartOffset(lineNumber + 1));
                }
            }
            switch ((OCPragma.Mode)pragma.first) {
                case PUSH: {
                    this.b(rangeWithMacros);
                    break;
                }
                case POP: {
                    this.a(rangeWithMacros);
                    break;
                }
                case IGNORE:
                case WARNING:
                case ERROR:
                case FATAL: {
                    this.myCurScope.settings.putValue(pragma.getSecond(), (Object)Pair.create((Object)rangeWithMacros, pragma.getFirst()));
                    this.myCurScope.ownSettingsCnt++;
                    break;
                }
            }
        }
    }
    
    private void b(final TextRange textRange) {
        final Scope myCurScope = new Scope();
        myCurScope.settings = (MultiMap<String, Pair<TextRange, OCPragma.Mode>>)new MultiMap();
        myCurScope.settings.putAllValues(this.myCurScope.settings);
        myCurScope.push = textRange;
        this.myStack.push(this.myCurScope);
        this.myCurScope.children.add(myCurScope);
        this.myCurScope = myCurScope;
    }
    
    private void a(final TextRange textRange) {
        if (!this.myStack.isEmpty()) {
            this.myCurScope.pop = textRange;
            this.myCurScope = this.myStack.pop();
        }
    }
}
