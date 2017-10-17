// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution;

import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExecutableData
{
    @Nullable
    public final BuildTargetData target;
    @Nullable
    public final String path;
    
    public ExecutableData(@NotNull final BuildTargetData target) {
        if (target == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/execution/ExecutableData", "<init>"));
        }
        this.target = target;
        this.path = null;
    }
    
    public ExecutableData(@NotNull final String path) {
        if (path == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/execution/ExecutableData", "<init>"));
        }
        this.target = null;
        this.path = path;
    }
    
    @Override
    public String toString() {
        try {
            if (this.path != null) {
                return "Path: " + this.path;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return "Target: " + this.target;
    }
    
    @Nullable
    public static ExecutableData loadExternal(@NotNull final Element element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/ExecutableData", "loadExternal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final String attributeValue = element.getAttributeValue("RUN_TARGET_PROJECT_NAME");
        final String attributeValue2 = element.getAttributeValue("RUN_TARGET_NAME");
        final String attributeValue3 = element.getAttributeValue("RUN_PATH");
        try {
            if (attributeValue3 != null) {
                return new ExecutableData(attributeValue3);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        Label_0097: {
            try {
                if (attributeValue == null) {
                    return null;
                }
                final String s = attributeValue2;
                if (s != null) {
                    break Label_0097;
                }
                return null;
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            try {
                final String s = attributeValue2;
                if (s != null) {
                    return new ExecutableData(new BuildTargetData(attributeValue, attributeValue2));
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
        }
        return null;
    }
    
    public void writeExternal(@NotNull final Element element) {
        try {
            if (element == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "element", "com/jetbrains/cidr/execution/ExecutableData", "writeExternal"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (this.target != null) {
                element.setAttribute("RUN_TARGET_PROJECT_NAME", this.target.projectName);
                element.setAttribute("RUN_TARGET_NAME", this.target.targetName);
                return;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (this.path != null) {
                element.setAttribute("RUN_PATH", this.path);
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
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
                final ExecutableData executableData = this;
                final Class<? extends ExecutableData> clazz = executableData.getClass();
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
                final ExecutableData executableData = this;
                final Class<? extends ExecutableData> clazz = executableData.getClass();
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
        final ExecutableData executableData2 = (ExecutableData)o;
        Label_0127: {
            Label_0120: {
                Label_0092: {
                    Label_0079: {
                        Label_0072: {
                            try {
                                if (this.path == null) {
                                    break Label_0079;
                                }
                                final ExecutableData executableData3 = this;
                                final String s = executableData3.path;
                                final ExecutableData executableData4 = executableData2;
                                final String s2 = executableData4.path;
                                final boolean b = s.equals(s2);
                                if (!b) {
                                    break Label_0072;
                                }
                                break Label_0092;
                            }
                            catch (IllegalArgumentException ex4) {
                                throw a(ex4);
                            }
                            try {
                                final ExecutableData executableData3 = this;
                                final String s = executableData3.path;
                                final ExecutableData executableData4 = executableData2;
                                final String s2 = executableData4.path;
                                final boolean b = s.equals(s2);
                                if (!b) {
                                    return false;
                                }
                                break Label_0092;
                            }
                            catch (IllegalArgumentException ex5) {
                                throw a(ex5);
                            }
                        }
                        try {
                            if (executableData2.path != null) {
                                return false;
                            }
                        }
                        catch (IllegalArgumentException ex6) {
                            throw a(ex6);
                        }
                    }
                    try {
                        if (this.target == null) {
                            break Label_0127;
                        }
                        final ExecutableData executableData5 = this;
                        final BuildTargetData buildTargetData = executableData5.target;
                        final ExecutableData executableData6 = executableData2;
                        final BuildTargetData buildTargetData2 = executableData6.target;
                        final boolean b2 = buildTargetData.equals(buildTargetData2);
                        if (!b2) {
                            break Label_0120;
                        }
                        return true;
                    }
                    catch (IllegalArgumentException ex7) {
                        throw a(ex7);
                    }
                }
                try {
                    final ExecutableData executableData5 = this;
                    final BuildTargetData buildTargetData = executableData5.target;
                    final ExecutableData executableData6 = executableData2;
                    final BuildTargetData buildTargetData2 = executableData6.target;
                    final boolean b2 = buildTargetData.equals(buildTargetData2);
                    if (!b2) {
                        return false;
                    }
                    return true;
                }
                catch (IllegalArgumentException ex8) {
                    throw a(ex8);
                }
            }
            try {
                if (executableData2.target != null) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex9) {
                throw a(ex9);
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        Label_0022: {
            try {
                if (this.target != null) {
                    hashCode = this.target.hashCode();
                    break Label_0022;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            hashCode = 0;
        }
        final int n = hashCode;
        int n2;
        try {
            n2 = 31 * n;
            if (this.path != null) {
                final int hashCode2 = this.path.hashCode();
                return n2 + hashCode2;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final int hashCode2 = 0;
        return n2 + hashCode2;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
