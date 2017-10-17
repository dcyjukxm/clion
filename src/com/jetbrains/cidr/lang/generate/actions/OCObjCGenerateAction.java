// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.actions;

import com.jetbrains.cidr.lang.OCActionUtil;
import com.jetbrains.cidr.lang.OCLanguageKind;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.codeInsight.actions.CodeInsightAction;

public abstract class OCObjCGenerateAction extends CodeInsightAction
{
    public void update(final AnActionEvent anActionEvent) {
        if (OCActionUtil.updateSmartAction(anActionEvent, OCLanguageKind.OBJ_C)) {
            super.update(anActionEvent);
        }
    }
}
