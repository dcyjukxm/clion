// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr;

import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.objc.OCMemberSymbol;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import com.intellij.openapi.util.Ref;
import com.jetbrains.cidr.lang.symbols.objc.OCClassSymbol;
import com.intellij.openapi.project.Project;

public class ContextFreeResolveUtil
{
    @Nullable
    public static OCClassSymbol resolveToClass(final String s, final Project project) {
        final Ref ref = new Ref();
        OCGlobalProjectSymbolsCache.processTopLevelSymbols(project, (Processor<OCSymbol>)(ocSymbol -> {
            if (ocSymbol instanceof OCClassSymbol && ocSymbol.getName().equals(s) && !ocSymbol.isPredeclaration() && ((OCClassSymbol)ocSymbol).getCategoryName() == null) {
                ref.set((Object)ocSymbol);
                return false;
            }
            return true;
        }), s);
        return (OCClassSymbol)ref.get();
    }
    
    @Nullable
    public static OCMemberSymbol resolveToProperty(final String s, final String s2, final Project project) {
        if (s == null) {
            return null;
        }
        return resolveToProperty(s2, resolveToClass(s, project));
    }
    
    public static OCMemberSymbol resolveToProperty(final String s, OCClassSymbol resolveToClass) {
        final CommonProcessors.FindFirstProcessor findFirstProcessor = new CommonProcessors.FindFirstProcessor();
        while (resolveToClass != null) {
            resolveToClass.processMembers(s, (Processor<OCMemberSymbol>)findFirstProcessor);
            if (findFirstProcessor.isFound()) {
                return (OCMemberSymbol)findFirstProcessor.getFoundValue();
            }
            resolveToClass = resolveToClass(resolveToClass.getSuperClassName(), resolveToClass.getProject());
        }
        return null;
    }
}
