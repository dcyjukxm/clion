// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.generate.handlers;

import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.settings.OCOption;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.project.Project;
import javax.swing.JComponent;
import java.util.List;
import com.jetbrains.cidr.lang.generate.OCMemberChooserObject;
import com.jetbrains.cidr.lang.generate.OCMemberChooser;

class OCClassActionHandlerBase$2 extends OCMemberChooser {
    @Nullable
    protected String getHelpId() {
        return OCClassActionHandlerBase.this.getHelpID();
    }
}