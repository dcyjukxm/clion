// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.editor.liveTemplates;

import java.util.Collection;
import com.intellij.util.ArrayUtil;
import com.intellij.util.PlatformUtils;
import java.util.ArrayList;
import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider;

public class OCDefaultLiveTemplatesProvider implements DefaultLiveTemplatesProvider
{
    @Override
    public String[] getDefaultLiveTemplateFiles() {
        final ArrayList<String> list = new ArrayList<String>(2);
        list.add("/liveTemplates/CPP");
        if (PlatformUtils.isAppCode()) {
            list.add("/liveTemplates/ObjectiveC");
        }
        else {
            list.add("/liveTemplates/CMake");
        }
        return ArrayUtil.toStringArray((Collection)list);
    }
    
    @Override
    public String[] getHiddenLiveTemplateFiles() {
        return null;
    }
}
