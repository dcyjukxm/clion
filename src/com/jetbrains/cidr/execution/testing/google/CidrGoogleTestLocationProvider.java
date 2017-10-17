// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.testing.google;

import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.intellij.util.Processor;
import com.jetbrains.cidr.lang.symbols.symtable.OCGlobalProjectSymbolsCache;
import org.jetbrains.annotations.Contract;
import com.intellij.util.CommonProcessors;
import com.jetbrains.cidr.lang.symbols.cpp.OCStructSymbol;
import com.intellij.psi.PsiElement;
import com.jetbrains.cidr.lang.symbols.OCSymbol;
import java.util.regex.Matcher;
import com.jetbrains.cidr.lang.psi.OCMacroCall;
import com.jetbrains.cidr.lang.psi.OCStruct;
import java.util.Collections;
import com.intellij.execution.Location;
import java.util.List;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import java.util.regex.Pattern;
import com.intellij.execution.testframework.sm.runner.SMTestLocator;

public class CidrGoogleTestLocationProvider implements SMTestLocator
{
    public static final String PROTOCOL = "gtest";
    public static final CidrGoogleTestLocationProvider INSTANCE;
    private static final Pattern METHOD_PATTERN;
    
    @NotNull
    @Override
    public List<Location> getLocation(@NotNull final String s, @NotNull final String s2, @NotNull final Project project, @NotNull final GlobalSearchScope globalSearchScope) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "protocol", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (s2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (globalSearchScope == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scope", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        Label_0235: {
            List<Location> list = null;
            Label_0200: {
                try {
                    if ("gtest".equals(s)) {
                        break Label_0235;
                    }
                    list = Collections.emptyList();
                    if (list == null) {
                        break Label_0200;
                    }
                    return list;
                }
                catch (IllegalArgumentException ex5) {
                    throw a(ex5);
                }
                try {
                    list = Collections.emptyList();
                    if (list == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
                    }
                }
                catch (IllegalArgumentException ex6) {
                    throw a(ex6);
                }
            }
            return list;
        }
        final Matcher matcher = CidrGoogleTestLocationProvider.METHOD_PATTERN.matcher(s2);
        if (!matcher.matches()) {
            List<Location> emptyList;
            try {
                emptyList = Collections.emptyList();
                if (emptyList == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
                }
            }
            catch (IllegalArgumentException ex7) {
                throw a(ex7);
            }
            return emptyList;
        }
        final String group = matcher.group(2);
        final String group2 = matcher.group(3);
        final String group3 = matcher.group(5);
        final String group4 = matcher.group(7);
        boolean b = false;
        Label_0304: {
            try {
                if (matcher.group(8) != null) {
                    b = true;
                    break Label_0304;
                }
            }
            catch (IllegalArgumentException ex8) {
                throw a(ex8);
            }
            b = false;
        }
        final boolean b2 = b;
        OCSymbol ocSymbol;
        if (group3 != null) {
            ocSymbol = CidrGoogleTestUtil.findGoogleTestSymbol(project, group2, group3);
        }
        else if (group != null) {
            ocSymbol = CidrGoogleTestUtil.findGoogleTestInstantiationSymbol(project, group2, group);
        }
        else {
            ocSymbol = a(project, group2);
        }
        Label_0404: {
            List<Location> list2 = null;
            Label_0369: {
                try {
                    if (ocSymbol != null) {
                        break Label_0404;
                    }
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        break Label_0369;
                    }
                    return list2;
                }
                catch (IllegalArgumentException ex9) {
                    throw a(ex9);
                }
                try {
                    list2 = Collections.emptyList();
                    if (list2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
                    }
                }
                catch (IllegalArgumentException ex10) {
                    throw a(ex10);
                }
            }
            return list2;
        }
        PsiElement locateDefinition = ocSymbol.locateDefinition();
        while (true) {
            Label_0436: {
                try {
                    if (locateDefinition instanceof OCStruct) {
                        break;
                    }
                    final PsiElement psiElement = locateDefinition;
                    final boolean b3 = psiElement instanceof OCMacroCall;
                    if (!b3) {
                        break Label_0436;
                    }
                    break;
                }
                catch (IllegalArgumentException ex11) {
                    throw a(ex11);
                }
                try {
                    final PsiElement psiElement = locateDefinition;
                    final boolean b3 = psiElement instanceof OCMacroCall;
                    if (b3) {
                        break;
                    }
                    if (locateDefinition == null) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex12) {
                    throw a(ex12);
                }
            }
            final PsiElement prevSibling = locateDefinition.getPrevSibling();
            PsiElement parent = null;
            Label_0478: {
                try {
                    if (prevSibling == null) {
                        parent = locateDefinition.getParent();
                        break Label_0478;
                    }
                }
                catch (IllegalArgumentException ex13) {
                    throw a(ex13);
                }
                parent = prevSibling;
            }
            locateDefinition = parent;
        }
        PsiElement psiElement2 = null;
        String s3 = null;
        String s4 = null;
        boolean b4 = false;
        Label_0562: {
            Label_0537: {
                List<Location> list3 = null;
                Label_0502: {
                    try {
                        if (locateDefinition != null) {
                            break Label_0537;
                        }
                        list3 = Collections.emptyList();
                        if (list3 == null) {
                            break Label_0502;
                        }
                        return list3;
                    }
                    catch (IllegalArgumentException ex14) {
                        throw a(ex14);
                    }
                    try {
                        list3 = Collections.emptyList();
                        if (list3 == null) {
                            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
                        }
                    }
                    catch (IllegalArgumentException ex15) {
                        throw a(ex15);
                    }
                }
                return list3;
                try {
                    psiElement2 = locateDefinition;
                    s3 = group4;
                    s4 = group;
                    if (group3 == null) {
                        b4 = true;
                        break Label_0562;
                    }
                }
                catch (IllegalArgumentException ex16) {
                    throw a(ex16);
                }
            }
            b4 = false;
        }
        final List<Location> singletonList = Collections.singletonList((Location)new CidrGoogleTestLocation(project, psiElement2, s3, s4, b4, b2));
        if (singletonList == null) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/execution/testing/google/CidrGoogleTestLocationProvider", "getLocation"));
        }
        return singletonList;
    }
    
    @Nullable
    private static OCStructSymbol a(final Project project, final String s) {
        final CommonProcessors.FindProcessor<OCSymbol> findProcessor = new CommonProcessors.FindProcessor<OCSymbol>() {
            @Contract("null -> false")
            protected boolean accept(final OCSymbol ocSymbol) {
                return ocSymbol instanceof OCStructSymbol && CidrGoogleTestUtil.isGoogleTestClass((OCStructSymbol)ocSymbol);
            }
        };
        try {
            OCGlobalProjectSymbolsCache.processTopLevelAndMemberSymbols(project, (Processor<OCSymbol>)findProcessor, s);
            if (findProcessor.isFound()) {
                return (OCStructSymbol)findProcessor.getFoundValue();
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Collection<OCStructSymbol> googleTestSymbolsForSuiteSorted = CidrGoogleTestUtil.findGoogleTestSymbolsForSuiteSorted(project, s);
        try {
            if (googleTestSymbolsForSuiteSorted.isEmpty()) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return googleTestSymbolsForSuiteSorted.iterator().next();
    }
    
    static {
        INSTANCE = new CidrGoogleTestLocationProvider();
        METHOD_PATTERN = Pattern.compile("((\\w+)/)?(\\w+)(\\.(\\w+))?(=(\\d+))?(\\?typed)?");
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
