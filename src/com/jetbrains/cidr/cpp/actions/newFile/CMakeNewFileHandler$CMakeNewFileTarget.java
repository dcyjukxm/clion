// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.actions.newFile;

import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.Nullable;
import com.intellij.psi.PsiFile;
import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import java.util.Iterator;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.cpp.cmake.psi.util.CMakeFileLocationUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.SmartPointerManager;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.cpp.cmake.psi.CMakeCommand;
import com.intellij.psi.SmartPsiElementPointer;

public static class CMakeNewFileTarget implements NewFileTarget
{
    @NotNull
    private final SmartPsiElementPointer<CMakeCommand> myPointer;
    @NotNull
    private final List<String> myTargetNames;
    @NotNull
    private final String myName;
    @NotNull
    private final String myAdditionalInfo;
    private final int mySelectionPriority;
    private static final Comparator<CMakeNewFileTarget> FILE_ORDER;
    private static final Comparator<CMakeNewFileTarget> NAME_ORDER;
    private static final Comparator<CMakeNewFileTarget> SELECTION_ORDER;
    private static final Comparator<CMakeNewFileTarget> VISUAL_ORDER;
    
    public CMakeNewFileTarget(@NotNull final CMakeCommand cMakeCommand, @NotNull final List<String> list, @NotNull final String myName, final int mySelectionPriority) {
        if (cMakeCommand == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "command", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetNames", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "<init>"));
        }
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "<init>"));
        }
        this.mySelectionPriority = mySelectionPriority;
        this.myTargetNames = new ArrayList<String>(list);
        this.myName = myName;
        this.myPointer = (SmartPsiElementPointer<CMakeCommand>)SmartPointerManager.getInstance(cMakeCommand.getProject()).createSmartPsiElementPointer((PsiElement)cMakeCommand);
        this.myAdditionalInfo = CMakeFileLocationUtil.getLocationInFile((PsiElement)cMakeCommand, true);
    }
    
    @Override
    public boolean addFiles(@NotNull final List<VirtualFile> list) {
        try {
            if (list == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "addedFiles", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "addFiles"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        this.myPointer.getVirtualFile().refresh(false, false);
        final CMakeCommand b = this.b();
        try {
            if (b == null) {
                return false;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        boolean b2 = false;
        final Iterator<VirtualFile> iterator = list.iterator();
        while (iterator.hasNext()) {
            b.appendArgument(this.calcRelativePath(iterator.next().getPath()));
            b2 = true;
        }
        return b2;
    }
    
    @NotNull
    public String calcRelativePath(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "filePath", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "calcRelativePath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final PsiFile containingFile = this.myPointer.getContainingFile();
        String path = null;
        Label_0078: {
            try {
                if (containingFile == null) {
                    path = null;
                    break Label_0078;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            path = containingFile.getVirtualFile().getParent().getPath();
        }
        final String s2 = path;
        String relativePath = null;
        Label_0110: {
            try {
                if (s2 == null) {
                    relativePath = null;
                    break Label_0110;
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
            relativePath = FileUtil.getRelativePath(new File(s2), new File(s));
        }
        final String s3 = relativePath;
        String canonicalPath = null;
        Label_0127: {
            try {
                if (s3 == null) {
                    final String s4 = s;
                    break Label_0127;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            final String s4 = s3;
            try {
                canonicalPath = FileUtil.toCanonicalPath(s4);
                if (canonicalPath == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "calcRelativePath"));
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
        }
        return canonicalPath;
    }
    
    public int getSelectionPriority() {
        return this.mySelectionPriority;
    }
    
    @NotNull
    public List<String> getTargetNames() {
        List<String> myTargetNames;
        try {
            myTargetNames = this.myTargetNames;
            if (myTargetNames == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getTargetNames"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTargetNames;
    }
    
    @NotNull
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @NotNull
    @Override
    public String getAdditionalInfo() {
        String myAdditionalInfo;
        try {
            myAdditionalInfo = this.myAdditionalInfo;
            if (myAdditionalInfo == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getAdditionalInfo"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myAdditionalInfo;
    }
    
    @Nullable
    private CMakeCommand b() {
        final CMakeCommand cMakeCommand = (CMakeCommand)this.myPointer.getElement();
        Label_0033: {
            try {
                if (cMakeCommand == null) {
                    return null;
                }
                final CMakeCommand cMakeCommand2 = cMakeCommand;
                final boolean b = cMakeCommand2.isValid();
                if (b) {
                    break Label_0033;
                }
                return null;
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            try {
                final CMakeCommand cMakeCommand2 = cMakeCommand;
                final boolean b = cMakeCommand2.isValid();
                if (b) {
                    return cMakeCommand;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.getName();
    }
    
    @NotNull
    public static Comparator<CMakeNewFileTarget> visualOrder() {
        Comparator<CMakeNewFileTarget> visual_ORDER;
        try {
            visual_ORDER = CMakeNewFileTarget.VISUAL_ORDER;
            if (visual_ORDER == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "visualOrder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return visual_ORDER;
    }
    
    @NotNull
    public static Comparator<CMakeNewFileTarget> nameOrder() {
        Comparator<CMakeNewFileTarget> name_ORDER;
        try {
            name_ORDER = CMakeNewFileTarget.NAME_ORDER;
            if (name_ORDER == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "nameOrder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return name_ORDER;
    }
    
    @NotNull
    public static Comparator<CMakeNewFileTarget> selectionOrder() {
        Comparator<CMakeNewFileTarget> selection_ORDER;
        try {
            selection_ORDER = CMakeNewFileTarget.SELECTION_ORDER;
            if (selection_ORDER == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "selectionOrder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return selection_ORDER;
    }
    
    @NotNull
    public static Comparator<CMakeNewFileTarget> fileOrder() {
        Comparator<CMakeNewFileTarget> file_ORDER;
        try {
            file_ORDER = CMakeNewFileTarget.FILE_ORDER;
            if (file_ORDER == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "fileOrder"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return file_ORDER;
    }
    
    @NotNull
    private String a() {
        String s = null;
        Label_0037: {
            try {
                if (this.myTargetNames.size() == 1) {
                    final String myName;
                    s = (myName = this.myTargetNames.get(0));
                    break Label_0037;
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String myName;
            s = (myName = this.myName);
            try {
                if (myName == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/actions/newFile/CMakeNewFileHandler$CMakeNewFileTarget", "getSingleTargetNameOrFullName"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
        }
        return s;
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
        try {
            if (o == null) {
                return false;
            }
            final CMakeNewFileTarget cMakeNewFileTarget = this;
            final Class<? extends CMakeNewFileTarget> clazz = cMakeNewFileTarget.getClass();
            final Object o2 = o;
            final Class<?> clazz2 = o2.getClass();
            if (clazz != clazz2) {
                return false;
            }
            return this.myPointer.equals(((CMakeNewFileTarget)o).myPointer);
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            final CMakeNewFileTarget cMakeNewFileTarget = this;
            final Class<? extends CMakeNewFileTarget> clazz = cMakeNewFileTarget.getClass();
            final Object o2 = o;
            final Class<?> clazz2 = o2.getClass();
            if (clazz != clazz2) {
                return false;
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return this.myPointer.equals(((CMakeNewFileTarget)o).myPointer);
    }
    
    @Override
    public int hashCode() {
        return this.myPointer.hashCode();
    }
    
    static {
        final CMakeCommand cMakeCommand;
        final Object o;
        final int n;
        final CMakeCommand cMakeCommand2;
        FILE_ORDER = ((cMakeNewFileTarget, cMakeNewFileTarget5) -> {
            cMakeNewFileTarget.b();
            cMakeNewFileTarget5.b();
            Label_0035_4: {
                Label_0025_4: {
                    try {
                        if (cMakeCommand == null) {
                            if (o == null) {
                                break Label_0025_4;
                            }
                            else {
                                return 1;
                            }
                        }
                        else {
                            break Label_0035_4;
                        }
                    }
                    catch (IllegalArgumentException ex) {
                        throw a(ex);
                    }
                    try {
                        if (o == null) {
                            return n;
                        }
                    }
                    catch (IllegalArgumentException ex2) {
                        throw a(ex2);
                    }
                }
                return n;
                try {
                    if (cMakeCommand2 == null) {
                        return -1;
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            return Comparing.compare(cMakeCommand.getTextOffset(), cMakeCommand2.getTextOffset());
        });
        NAME_ORDER = ((cMakeNewFileTarget2, cMakeNewFileTarget6) -> String.CASE_INSENSITIVE_ORDER.compare(cMakeNewFileTarget2.a(), cMakeNewFileTarget6.a()));
        SELECTION_ORDER = ((cMakeNewFileTarget3, cMakeNewFileTarget7) -> Comparing.compare(cMakeNewFileTarget3.getSelectionPriority(), cMakeNewFileTarget7.getSelectionPriority()));
        final int n2;
        final int n3;
        VISUAL_ORDER = ((cMakeNewFileTarget4, cMakeNewFileTarget8) -> {
            CMakeNewFileTarget.NAME_ORDER.compare(cMakeNewFileTarget4, cMakeNewFileTarget8);
            try {
                if (n2 != 0) {
                    return n3;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            CMakeNewFileTarget.FILE_ORDER.compare(cMakeNewFileTarget4, cMakeNewFileTarget8);
            return n3;
        });
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
}
