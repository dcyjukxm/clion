// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.autoImport;

import com.jetbrains.cidr.lang.psi.OCIncludeDirective;
import java.util.EnumSet;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.util.Processor;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.lang.workspace.OCResolveRootAndConfiguration;
import com.intellij.openapi.extensions.ExtensionPointName;

public interface OCAutoImportHelper
{
    public static final ExtensionPointName<OCAutoImportHelper> EP_NAME = ExtensionPointName.create("cidr.lang.autoImportHelper");
    
    boolean supports(@NotNull final OCResolveRootAndConfiguration p0);
    
    boolean processPathSpecificationToInclude(@NotNull final Project p0, @Nullable final VirtualFile p1, @NotNull final VirtualFile p2, @NotNull final OCResolveRootAndConfiguration p3, @NotNull final Processor<ImportSpecification> p4);
    
    void addHeaderSearchPath(@NotNull final Project p0, @Nullable final VirtualFile p1, @NotNull final VirtualFile p2);
    
    @NotNull
    Iterable<IntentionAction> getAddHeaderSearchPathFixes(@NotNull final Project p0, @NotNull final VirtualFile p1, @NotNull final String p2);
    
    public static class ImportSpecification
    {
        @NotNull
        private final String myPath;
        @NotNull
        private final Kind myKind;
        @NotNull
        private final EnumSet<OCIncludeDirective.Delimiters> myPossibleDelimiters;
        static final /* synthetic */ boolean $assertionsDisabled;
        
        public ImportSpecification(@NotNull final String myPath, @NotNull final Kind myKind) {
            if (myPath == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "<init>"));
            }
            if (myKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "kind", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "<init>"));
            }
            this.myPath = myPath;
            this.myKind = myKind;
            final EnumSet<OCIncludeDirective.Delimiters> myPossibleDelimiters = (myKind == Kind.SYSTEM_HEADER_SEARCH_PATH) ? EnumSet.of(OCIncludeDirective.Delimiters.ANGLE_BRACKETS, OCIncludeDirective.Delimiters.QUOTES) : EnumSet.of(OCIncludeDirective.Delimiters.QUOTES);
            Label_0160: {
                try {
                    myPossibleDelimiters.add(OCIncludeDirective.Delimiters.NONE);
                    this.myPossibleDelimiters = myPossibleDelimiters;
                    if (ImportSpecification.$assertionsDisabled) {
                        return;
                    }
                    final ImportSpecification importSpecification = this;
                    final EnumSet<OCIncludeDirective.Delimiters> set = importSpecification.myPossibleDelimiters;
                    final ImportSpecification importSpecification2 = this;
                    final OCIncludeDirective.Delimiters delimiters = importSpecification2.getPreferredDelimiters();
                    final boolean b = set.contains(delimiters);
                    if (!b) {
                        break Label_0160;
                    }
                    return;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final ImportSpecification importSpecification = this;
                    final EnumSet<OCIncludeDirective.Delimiters> set = importSpecification.myPossibleDelimiters;
                    final ImportSpecification importSpecification2 = this;
                    final OCIncludeDirective.Delimiters delimiters = importSpecification2.getPreferredDelimiters();
                    final boolean b = set.contains(delimiters);
                    if (!b) {
                        throw new AssertionError();
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
        }
        
        @NotNull
        public String getImportText() {
            String importText;
            try {
                importText = this.getImportText(this.getPreferredDelimiters());
                if (importText == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "getImportText"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return importText;
        }
        
        @NotNull
        public String getImportText(@NotNull final OCIncludeDirective.Delimiters delimiters) {
            try {
                if (delimiters == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "delimiters", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "getImportText"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            String s = this.getImportPath();
            if (delimiters == OCIncludeDirective.Delimiters.NONE) {
                final int lastIndex = s.lastIndexOf(46);
                if (lastIndex > 0) {
                    s = s.substring(0, lastIndex);
                }
            }
            String string;
            try {
                string = delimiters.getBeforeText() + s + delimiters.getAfterText();
                if (string == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "getImportText"));
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            return string;
        }
        
        @NotNull
        public String getImportPath() {
            String myPath;
            try {
                myPath = this.myPath;
                if (myPath == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "getImportPath"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myPath;
        }
        
        @NotNull
        public Kind getKind() {
            Kind myKind;
            try {
                myKind = this.myKind;
                if (myKind == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "getKind"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myKind;
        }
        
        public boolean areDelimitersAllowed(final OCIncludeDirective.Delimiters delimiters) {
            return this.myPossibleDelimiters.contains(delimiters);
        }
        
        @NotNull
        public OCIncludeDirective.Delimiters getPreferredDelimiters() {
            OCIncludeDirective.Delimiters delimiters = null;
            Label_0023: {
                try {
                    if (this.myKind == Kind.SYSTEM_HEADER_SEARCH_PATH) {
                        final OCIncludeDirective.Delimiters delimiters2;
                        delimiters = (delimiters2 = OCIncludeDirective.Delimiters.ANGLE_BRACKETS);
                        break Label_0023;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                OCIncludeDirective.Delimiters delimiters2;
                delimiters = (delimiters2 = OCIncludeDirective.Delimiters.QUOTES);
                try {
                    if (delimiters2 == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/autoImport/OCAutoImportHelper$ImportSpecification", "getPreferredDelimiters"));
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            return delimiters;
        }
        
        static {
            boolean $assertionsDisabled2 = false;
            Label_0017: {
                try {
                    if (!OCAutoImportHelper.class.desiredAssertionStatus()) {
                        $assertionsDisabled2 = true;
                        break Label_0017;
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                $assertionsDisabled2 = false;
            }
            $assertionsDisabled = $assertionsDisabled2;
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
        
        public enum Kind
        {
            PROJECT_HEADER, 
            USER_HEADER_SEARCH_PATH, 
            SYSTEM_HEADER_SEARCH_PATH;
        }
    }
}
