// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang;

import com.intellij.openapi.editor.EditorModificationUtil;
import com.jetbrains.cidr.lang.symbols.objc.OCInstanceVariableSymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCPropertySymbol;
import com.jetbrains.cidr.lang.symbols.objc.OCMethodSymbol;
import com.intellij.openapi.editor.Editor;
import java.util.regex.Matcher;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.openapi.util.Condition;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import java.util.regex.Pattern;
import com.intellij.openapi.project.Project;
import com.jetbrains.cidr.lang.symbols.OCSymbolWithParent;
import com.jetbrains.cidr.lang.psi.OCSymbolDeclarator;
import com.intellij.psi.PsiElement;
import com.intellij.ide.actions.QualifiedNameProvider;

public class OCQualifiedNameProvider implements QualifiedNameProvider
{
    @Override
    public PsiElement adjustElementToCopy(final PsiElement psiElement) {
        return null;
    }
    
    @Override
    public String getQualifiedName(final PsiElement psiElement) {
        if (psiElement instanceof OCSymbolDeclarator) {
            final OCSymbolWithParent symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            if (symbol instanceof OCSymbolWithParent) {
                return symbol.getNameWithParent();
            }
            if (symbol != null) {
                return symbol.getName();
            }
        }
        return null;
    }
    
    @Override
    public PsiElement qualifiedNameToElement(final String s, final Project project) {
        Matcher matcher = Pattern.compile(".\\[([^ ]*) ([^ ]*)\\]").matcher(s);
        if (!matcher.matches()) {
            matcher = Pattern.compile("([^.]*).([^.]*)]").matcher(s);
        }
        if (matcher.matches()) {
            final OCSymbol nearestTopLevelSymbol = OCGlobalProjectSymbolsCache.findNearestTopLevelSymbol(project, matcher.group(1), null, null);
            if (nearestTopLevelSymbol instanceof OCClassSymbol) {
                final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
                ((OCClassSymbol)nearestTopLevelSymbol).processMembersInAllCategories(matcher.group(2), (Class<? extends OCMemberSymbol>)OCMemberSymbol.class, (com.intellij.util.Processor<? super OCMemberSymbol>)findFirstProcessor, false);
                return findFirstProcessor.isFound() ? ((OCMemberSymbol)findFirstProcessor.getFoundValue()).locateDefinition() : null;
            }
        }
        final OCSymbol nearestTopLevelSymbol2 = OCGlobalProjectSymbolsCache.findNearestTopLevelSymbol(project, s, null, null);
        return (nearestTopLevelSymbol2 != null) ? nearestTopLevelSymbol2.locateDefinition() : null;
    }
    
    @Override
    public void insertQualifiedName(final String s, final PsiElement psiElement, final Editor editor, final Project project) {
        String s2 = s;
        if (psiElement instanceof OCSymbolDeclarator) {
            final OCInstanceVariableSymbol symbol = ((OCSymbolDeclarator)psiElement).getSymbol();
            if (symbol instanceof OCMethodSymbol) {
                s2 = "@selector(" + symbol.getName() + ")";
            }
            else if (symbol instanceof OCPropertySymbol) {
                s2 = ((OCPropertySymbol)symbol).getParent() + "." + symbol.getName();
            }
            else if (symbol instanceof OCInstanceVariableSymbol) {
                s2 = symbol.getParent() + "->" + symbol.getName();
            }
        }
        EditorModificationUtil.insertStringAtCaret(editor, s2);
    }
}
