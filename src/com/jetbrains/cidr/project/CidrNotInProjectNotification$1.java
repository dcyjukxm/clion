// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.ui.EditorNotifications;
import com.intellij.openapi.roots.ModuleRootEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootListener;

class CidrNotInProjectNotification$1 implements ModuleRootListener {
    final /* synthetic */ Project val$project;
    
    public void rootsChanged(final ModuleRootEvent moduleRootEvent) {
        EditorNotifications.getInstance(this.val$project).updateAllNotifications();
    }
}