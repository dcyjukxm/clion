// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.symbols.cpp;

import com.jetbrains.cidr.lang.symbols.OCSymbolKind;
import java.util.List;
import java.util.Collections;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.symbols.DeepEqual;
import com.intellij.openapi.vfs.VirtualFile;
import com.jetbrains.cidr.lang.symbols.OCSymbolImpl;

public class OCIncludeSymbol extends OCSymbolImpl
{
    private transient VirtualFile myTargetFile;
    private boolean myOnce;
    private boolean myNext;
    private IncludePath myIncludePath;
    private int myEndOffset;
    
    public OCIncludeSymbol() {
    }
    
    @Override
    public boolean deepEqualStep(@NotNull final DeepEqual.Comparator comparator, @NotNull final Object o, @NotNull final Object o2) {
        try {
            if (comparator == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "c", "com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (o == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "first", "com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        try {
            if (o2 == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "second", "com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol", "deepEqualStep"));
            }
        }
        catch (IllegalArgumentException ex3) {
            throw b(ex3);
        }
        try {
            if (!super.deepEqualStep(comparator, o, o2)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex4) {
            throw b(ex4);
        }
        final OCIncludeSymbol ocIncludeSymbol = (OCIncludeSymbol)o;
        final OCIncludeSymbol ocIncludeSymbol2 = (OCIncludeSymbol)o2;
        try {
            if (ocIncludeSymbol.myNext != ocIncludeSymbol2.myNext) {
                return false;
            }
        }
        catch (IllegalArgumentException ex5) {
            throw b(ex5);
        }
        try {
            if (ocIncludeSymbol.myOnce != ocIncludeSymbol2.myOnce) {
                return false;
            }
        }
        catch (IllegalArgumentException ex6) {
            throw b(ex6);
        }
        try {
            if (ocIncludeSymbol.myEndOffset != ocIncludeSymbol2.myEndOffset) {
                return false;
            }
        }
        catch (IllegalArgumentException ex7) {
            throw b(ex7);
        }
        try {
            if (!Comparing.equal((Object)ocIncludeSymbol.myIncludePath, (Object)ocIncludeSymbol2.myIncludePath)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex8) {
            throw b(ex8);
        }
        try {
            if (!Comparing.equal((Object)ocIncludeSymbol.myTargetFile, (Object)ocIncludeSymbol2.myTargetFile)) {
                return false;
            }
        }
        catch (IllegalArgumentException ex9) {
            throw b(ex9);
        }
        return true;
    }
    
    public OCIncludeSymbol(@Nullable final Project project, @Nullable final VirtualFile virtualFile, final int n, @Nullable final VirtualFile myTargetFile, @NotNull final IncludePath myIncludePath, final boolean myOnce, final boolean myNext, final int myEndOffset) {
        if (myIncludePath == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol", "<init>"));
        }
        super(project, virtualFile, n, myIncludePath.getPath(), Collections.emptyList());
        this.myIncludePath = myIncludePath;
        this.myTargetFile = myTargetFile;
        this.myOnce = myOnce;
        this.myNext = myNext;
        this.myEndOffset = myEndOffset;
    }
    
    @Nullable
    public VirtualFile getTargetFile() {
        return this.myTargetFile;
    }
    
    public void updateTargetFile(@Nullable final VirtualFile myTargetFile) {
        this.myTargetFile = myTargetFile;
    }
    
    public boolean isOnce() {
        return this.myOnce;
    }
    
    public boolean isNext() {
        return this.myNext;
    }
    
    public String getRelativePath() {
        return this.getName();
    }
    
    public String getLastPathComponent() {
        final String relativePath = this.getRelativePath();
        final int lastIndex = relativePath.lastIndexOf(47);
        try {
            if (lastIndex >= 0) {
                return relativePath.substring(lastIndex + 1);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return relativePath;
    }
    
    @NotNull
    @Override
    public OCSymbolKind getKind() {
        OCSymbolKind import1;
        try {
            import1 = OCSymbolKind.IMPORT;
            if (import1 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol", "getKind"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return import1;
    }
    
    @NotNull
    public IncludePath getIncludePath() {
        IncludePath myIncludePath;
        try {
            myIncludePath = this.myIncludePath;
            if (myIncludePath == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/symbols/cpp/OCIncludeSymbol", "getIncludePath"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return myIncludePath;
    }
    
    public int getEndOffset() {
        return this.myEndOffset;
    }
    
    private static IllegalArgumentException b(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class IncludePath
    {
        private String myPath;
        private boolean myAngleBrackets;
        public static final IncludePath EMPTY;
        
        public IncludePath() {
        }
        
        public IncludePath(final String myPath, final boolean myAngleBrackets) {
            this.myPath = myPath;
            this.myAngleBrackets = myAngleBrackets;
        }
        
        @Override
        public String toString() {
            return this.myAngleBrackets ? ("<" + this.myPath + ">") : ("\"" + this.myPath + "\"");
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof IncludePath)) {
                return false;
            }
            final IncludePath includePath = (IncludePath)o;
            if (this.myAngleBrackets != includePath.myAngleBrackets) {
                return false;
            }
            if (this.myPath != null) {
                if (this.myPath.equals(includePath.myPath)) {
                    return true;
                }
            }
            else if (includePath.myPath == null) {
                return true;
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return 31 * ((this.myPath != null) ? this.myPath.hashCode() : 0) + (this.myAngleBrackets ? 1 : 0);
        }
        
        public String getPath() {
            return this.myPath;
        }
        
        public boolean isAngleBrackets() {
            return this.myAngleBrackets;
        }
        
        static {
            EMPTY = new EmptyIncludePath();
        }
        
        private static class EmptyIncludePath extends IncludePath
        {
            private EmptyIncludePath() {
                super("", false);
            }
        }
    }
}
