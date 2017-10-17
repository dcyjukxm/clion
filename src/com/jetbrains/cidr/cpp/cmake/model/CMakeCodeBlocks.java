// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import org.jetbrains.annotations.Nullable;
import java.io.IOException;
import com.intellij.openapi.util.JDOMUtil;
import org.jdom.JDOMException;
import java.io.File;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Collections;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import java.util.Map;

public class CMakeCodeBlocks
{
    @NotNull
    private final Map<String, String> myFilePathsWithFolders;
    @NotNull
    private final Map<String, String> myTargetTypes;
    
    private CMakeCodeBlocks(@NotNull final Element element) {
        if (element == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "root", "com/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks", "<init>"));
        }
        final Element child = element.getChild("Project");
        final HashMap hashMap = ContainerUtil.newHashMap();
        final HashMap hashMap2 = ContainerUtil.newHashMap();
        if (child != null) {
            for (final Element element2 : child.getChildren("Unit")) {
                final String attributeValue = element2.getAttributeValue("filename");
                if (attributeValue != null) {
                    final Element child2 = element2.getChild("Option");
                    String attributeValue2 = null;
                    Label_0142: {
                        try {
                            if (child2 == null) {
                                attributeValue2 = null;
                                break Label_0142;
                            }
                        }
                        catch (IllegalArgumentException ex) {
                            throw b(ex);
                        }
                        attributeValue2 = child2.getAttributeValue("virtualFolder");
                    }
                    hashMap.put(attributeValue, StringUtil.notNullize(attributeValue2));
                }
            }
            final Element child3 = child.getChild("Build");
            if (child3 != null) {
                for (final Element element3 : child3.getChildren("Target")) {
                    final String attributeValue3 = element3.getAttributeValue("title");
                    if (attributeValue3 != null) {
                        final Iterator iterator3 = element3.getChildren("Option").iterator();
                        while (iterator3.hasNext()) {
                            final String attributeValue4 = iterator3.next().getAttributeValue("type");
                            try {
                                if (attributeValue4 != null) {
                                    hashMap2.put(attributeValue3, attributeValue4);
                                    break;
                                }
                                continue;
                            }
                            catch (IllegalArgumentException ex2) {
                                throw b(ex2);
                            }
                        }
                    }
                }
            }
        }
        this.myFilePathsWithFolders = Collections.unmodifiableMap((Map<? extends String, ? extends String>)hashMap);
        this.myTargetTypes = Collections.unmodifiableMap((Map<? extends String, ? extends String>)hashMap2);
    }
    
    @Nullable
    public static CMakeCodeBlocks load(@NotNull final File file) throws JDOMException, IOException {
        try {
            if (file == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "codeBlocksFile", "com/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks", "load"));
            }
        }
        catch (JDOMException ex) {
            throw b((Exception)ex);
        }
        try {
            if (file.exists()) {
                return new CMakeCodeBlocks(JDOMUtil.load(file));
            }
        }
        catch (JDOMException ex2) {
            throw b((Exception)ex2);
        }
        return null;
    }
    
    @NotNull
    public Map<String, String> getFilePathsWithFolders() {
        Map<String, String> myFilePathsWithFolders;
        try {
            myFilePathsWithFolders = this.myFilePathsWithFolders;
            if (myFilePathsWithFolders == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks", "getFilePathsWithFolders"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myFilePathsWithFolders;
    }
    
    @NotNull
    public Map<String, String> getTargetTypes() {
        Map<String, String> myTargetTypes;
        try {
            myTargetTypes = this.myTargetTypes;
            if (myTargetTypes == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeCodeBlocks", "getTargetTypes"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myTargetTypes;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
