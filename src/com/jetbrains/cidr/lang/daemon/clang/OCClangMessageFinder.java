// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.lang.daemon.clang;

import gnu.trove.TIntIterator;
import gnu.trove.TIntHashSet;
import org.jetbrains.annotations.Nullable;
import java.util.Iterator;
import com.jetbrains.cidr.lang.symbols.symtable.OCNamesInternary;
import java.util.regex.Pattern;
import com.jetbrains.cidr.lang.daemon.OCAnnotatorHelper;
import java.io.IOException;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.lang.daemon.OCAnnotator;
import com.jetbrains.cidr.lang.inspections.OCInspection;
import com.jetbrains.cidr.lang.inspections.OCInspectionToolProvider;
import com.jetbrains.cidr.lang.inspections.OCInspections;
import java.util.HashSet;
import com.intellij.util.containers.hash.HashMap;
import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.ServiceManager;
import java.util.Set;
import com.intellij.util.containers.IntArrayList;
import java.util.Map;
import java.util.List;
import com.intellij.openapi.diagnostic.Logger;

public class OCClangMessageFinder
{
    private static final Logger LOG;
    protected static final String MESSAGES_FILE = "all-messages.txt";
    protected static final String SUPPORTED_IDS_FILE = "supported-ids.txt";
    private static final String[] myHardcodedMessages;
    private final List<OCClangMessageDescriptor> myDescriptors;
    private final Map<String, OCClangMessageDescriptor> myDescriptorsMap;
    private final Map<String, IntArrayList> myWordsIndex;
    private final Set<String> mySupportedIDs;
    private final int myClangVersion;
    
    @NotNull
    public static OCClangMessageFinder getInstance() {
        OCClangMessageFinder ocClangMessageFinder;
        try {
            ocClangMessageFinder = (OCClangMessageFinder)ServiceManager.getService((Class)OCClangMessageFinder.class);
            if (ocClangMessageFinder == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getInstance"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return ocClangMessageFinder;
    }
    
    @NotNull
    public String getMethodNotImplemented() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 50) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "warn_undef_method_impl");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "warn_incomplete_impl");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getMethodNotImplemented"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getUsageOfArcObjectInStruct() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 50) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_arc_objc_object_in_tag");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "err_arc_objc_object_in_struct");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getUsageOfArcObjectInStruct"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getMissingCase() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 70) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "warn_missing_case");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "warn_missing_cases");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getMissingCase"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getConditionShouldBeScalar() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 70) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_typecheck_cond_expect_scalar");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "err_typecheck_cond_expect_scalar_or_vector");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getConditionShouldBeScalar"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getInvalidIntSuffix() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 73) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_invalid_suffix_constant");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "err_invalid_suffix_integer_constant");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getInvalidIntSuffix"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getInvalidFloatSuffix() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 73) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_invalid_suffix_constant");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "err_invalid_suffix_float_constant");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getInvalidFloatSuffix"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getReturnStackAddr() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 73) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "warn_ret_stack_addr_ref");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "warn_ret_stack_addr");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getReturnStackAddr"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getReturnTempAddr() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 73) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "warn_ret_local_temp_addr_ref");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "warn_ret_local_temp_addr");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getReturnTempAddr"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getObjcThrowExpectsObject() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_objc_throw_expects_object");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_objc_throw_expects_object");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getObjcThrowExpectsObject"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getRethrowUsedOutsideCatch() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_rethrow_used_outside_catch");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_rethrow_used_outside_catch");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getRethrowUsedOutsideCatch"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getSynthesizeCategoryDecl() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_synthesize_category_decl");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_synthesize_category_decl");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getSynthesizeCategoryDecl"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getCategoryProperty() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_category_property");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_category_property");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getCategoryProperty"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getIvarInSuperclassUse() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_ivar_in_superclass_use");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_ivar_in_superclass_use");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getIvarInSuperclassUse"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getDuplicateIvarUse() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_duplicate_ivar_use");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_duplicate_ivar_use");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getDuplicateIvarUse"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getPropertyImplemented() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_property_implemented");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_property_implemented");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getPropertyImplemented"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getWeakProperty() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_weak_property");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_weak_property");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getWeakProperty"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    @NotNull
    public String getIvarUseInClassMethod() {
        String s = null;
        Label_0035: {
            Label_0024: {
                try {
                    if (this.myClangVersion >= 81) {
                        break Label_0024;
                    }
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        break Label_0024;
                    }
                    break Label_0024;
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
                try {
                    final OCClangMessageFinder ocClangMessageFinder = this;
                    final int n = ocClangMessageFinder.myClangVersion;
                    final int n2 = -1;
                    if (n == n2) {
                        final String s2;
                        s = (s2 = "err_ivar_use_in_class_method");
                        break Label_0035;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            String s2;
            s = (s2 = "error_ivar_use_in_class_method");
            try {
                if (s2 == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getIvarUseInClassMethod"));
                }
            }
            catch (IllegalArgumentException ex3) {
                throw a(ex3);
            }
        }
        return s;
    }
    
    public OCClangMessageFinder() {
        this.myDescriptors = new ArrayList<OCClangMessageDescriptor>();
        this.myDescriptorsMap = (Map<String, OCClangMessageDescriptor>)new HashMap();
        this.myWordsIndex = (Map<String, IntArrayList>)new HashMap();
        final HashSet<String> set = new HashSet<String>();
        int i = 0;
        try {
            for (final Class<?> clazz : OCInspections.class.getDeclaredClasses()) {
                try {
                    if (OCInspectionToolProvider.getInstance().isClangCompilerInspection(clazz)) {
                        set.add(((OCInspection)clazz.newInstance()).getDisplayName());
                    }
                }
                catch (IllegalArgumentException ex) {
                    throw a(ex);
                }
            }
            int clangVersion = -1;
            final OCAnnotatorHelper[] annotatorHelpers = OCAnnotator.getAnnotatorHelpers();
            for (int length2 = annotatorHelpers.length, k = 0; k < length2; ++k) {
                clangVersion = annotatorHelpers[k].getClangVersion();
                try {
                    if (clangVersion != -1) {
                        break;
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw a(ex2);
                }
            }
            this.myClangVersion = clangVersion;
            String s = "apple81/";
            if (this.myClangVersion == 60) {
                s = "apple60/";
            }
            else if (this.myClangVersion == 61) {
                s = "apple61/";
            }
            else if (this.myClangVersion == 70) {
                s = "apple70/";
            }
            else if (this.myClangVersion == 73) {
                s = "apple73/";
            }
            else if (this.myClangVersion == 80) {
                s = "apple80/";
            }
            else if (this.myClangVersion == 81) {
                s = "apple81/";
            }
            else {
                try {
                    if (this.myClangVersion != -1) {
                        OCClangMessageFinder.LOG.warn("Unhandled clang version: " + this.myClangVersion);
                    }
                }
                catch (IllegalArgumentException ex3) {
                    throw a(ex3);
                }
            }
            final String[] splitByLines = StringUtil.splitByLines(FileUtil.loadTextAndClose(this.getClass().getResource(s + "all-messages.txt").openStream()));
            while (i < splitByLines.length) {
                ProgressManager.checkCanceled();
                final String s2 = splitByLines[i++];
                final String s3 = splitByLines[i++];
                OCClangMessageDescriptor.OCClangMessageKind ocClangMessageKind;
                try {
                    ocClangMessageKind = OCClangMessageDescriptor.OCClangMessageKind.valueOf(s3);
                }
                catch (IllegalArgumentException ex12) {
                    ocClangMessageKind = OCClangMessageDescriptor.OCClangMessageKind.Warning;
                    OCClangMessageFinder.LOG.error("Unknown message kind: " + s3);
                }
                this.a(s2, ocClangMessageKind, splitByLines[i++], splitByLines[i++], splitByLines[i++].toLowerCase(), set);
            }
            Logger log = null;
            boolean b = false;
            Label_0498: {
                try {
                    log = OCClangMessageFinder.LOG;
                    if (i == splitByLines.length) {
                        b = true;
                        break Label_0498;
                    }
                }
                catch (IllegalArgumentException ex4) {
                    throw a(ex4);
                }
                b = false;
            }
            log.assertTrue(b, (Object)"Not all lines have been read");
            final String loadTextAndClose = FileUtil.loadTextAndClose(this.getClass().getResource(s + "supported-ids.txt").openStream());
            this.mySupportedIDs = new HashSet<String>();
            for (final String s4 : StringUtil.splitByLines(loadTextAndClose)) {
                Label_0679: {
                    try {
                        if (s4.startsWith("//") || s4.isEmpty()) {
                            break Label_0679;
                        }
                    }
                    catch (IllegalArgumentException ex5) {
                        throw a(ex5);
                    }
                    final String trim = s4.trim();
                    try {
                        this.mySupportedIDs.add(trim);
                        if (!this.myDescriptorsMap.containsKey(trim)) {
                            OCClangMessageFinder.LOG.error("--------------------\n\nCan't find Clang message \"" + trim + "\"\n", (Throwable)null);
                        }
                    }
                    catch (IllegalArgumentException ex6) {
                        throw a(ex6);
                    }
                }
            }
            int n = 0;
            for (final String s5 : OCClangMessageFinder.myHardcodedMessages) {
                ProgressManager.checkCanceled();
                final String string = "__hardcoded_" + n++;
                this.a(string, OCClangMessageDescriptor.OCClangMessageKind.Error, "Empty Category", "Empty Group", s5.toLowerCase(), set);
                this.mySupportedIDs.add(string);
            }
        }
        catch (ProcessCanceledException ex7) {
            throw ex7;
        }
        catch (RuntimeException ex8) {
            OCClangMessageFinder.LOG.error("Error reading messages, line " + i, (Throwable)ex8);
            throw ex8;
        }
        catch (IOException ex9) {
            OCClangMessageFinder.LOG.error("Can't read messages file", (Throwable)ex9);
            throw new RuntimeException(ex9);
        }
        catch (InstantiationException ex10) {
            OCClangMessageFinder.LOG.error((Throwable)ex10);
            throw new RuntimeException(ex10);
        }
        catch (IllegalAccessException ex11) {
            OCClangMessageFinder.LOG.error((Throwable)ex11);
            throw new RuntimeException(ex11);
        }
    }
    
    private void a(final String s, final OCClangMessageDescriptor.OCClangMessageKind ocClangMessageKind, final String s2, final String s3, final String s4, final Set<String> set) {
        try {
            if (s4.trim().equals(".*")) {
                return;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        try {
            if (!set.contains(s2)) {
                OCClangMessageFinder.LOG.error("--------------------\n\nUnknown message category: " + s2 + "\n", (Throwable)null);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        final Pattern compile = Pattern.compile(s4);
        for (final String s5 : StringUtil.getWordsIn(s4)) {
            IntArrayList list = this.myWordsIndex.get(s5);
            if (list == null) {
                list = new IntArrayList();
                this.myWordsIndex.put(s5, list);
            }
            list.add(this.myDescriptors.size());
        }
        final OCClangMessageDescriptor ocClangMessageDescriptor = new OCClangMessageDescriptor(s, ocClangMessageKind, OCNamesInternary.intern(s2), s3, compile, a(s4));
        this.myDescriptors.add(ocClangMessageDescriptor);
        this.myDescriptorsMap.put(ocClangMessageDescriptor.getID(), ocClangMessageDescriptor);
    }
    
    private static int a(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: new             Ljava/lang/StringBuilder;
        //     5: dup            
        //     6: invokespecial   java/lang/StringBuilder.<init>:()V
        //     9: astore_2       
        //    10: iconst_0       
        //    11: istore_3       
        //    12: iload_3        
        //    13: aload_0        
        //    14: invokevirtual   java/lang/String.length:()I
        //    17: if_icmpge       145
        //    20: aload_0        
        //    21: iload_3        
        //    22: invokevirtual   java/lang/String.charAt:(I)C
        //    25: istore          4
        //    27: iload           4
        //    29: bipush          40
        //    31: if_icmpne       74
        //    34: iload_3        
        //    35: ifle            64
        //    38: goto            45
        //    41: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    44: athrow         
        //    45: aload_0        
        //    46: iload_3        
        //    47: iconst_1       
        //    48: isub           
        //    49: invokevirtual   java/lang/String.charAt:(I)C
        //    52: bipush          92
        //    54: if_icmpeq       74
        //    57: goto            64
        //    60: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    63: athrow         
        //    64: iinc            1, 1
        //    67: goto            139
        //    70: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    73: athrow         
        //    74: iload           4
        //    76: bipush          41
        //    78: if_icmpne       121
        //    81: iload_3        
        //    82: ifle            111
        //    85: goto            92
        //    88: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //    91: athrow         
        //    92: aload_0        
        //    93: iload_3        
        //    94: iconst_1       
        //    95: isub           
        //    96: invokevirtual   java/lang/String.charAt:(I)C
        //    99: bipush          92
        //   101: if_icmpeq       121
        //   104: goto            111
        //   107: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   110: athrow         
        //   111: iinc            1, -1
        //   114: goto            139
        //   117: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   120: athrow         
        //   121: iload_1        
        //   122: ifne            139
        //   125: aload_2        
        //   126: iload           4
        //   128: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   131: pop            
        //   132: goto            139
        //   135: invokestatic    com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder.a:(Ljava/lang/IllegalArgumentException;)Ljava/lang/IllegalArgumentException;
        //   138: athrow         
        //   139: iinc            3, 1
        //   142: goto            12
        //   145: aload_2        
        //   146: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   149: invokestatic    com/intellij/openapi/util/text/StringUtil.getWordsIn:(Ljava/lang/String;)Ljava/util/List;
        //   152: invokeinterface java/util/List.size:()I
        //   157: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  27     38     41     45     Ljava/lang/IllegalArgumentException;
        //  34     57     60     64     Ljava/lang/IllegalArgumentException;
        //  45     70     70     74     Ljava/lang/IllegalArgumentException;
        //  74     85     88     92     Ljava/lang/IllegalArgumentException;
        //  81     104    107    111    Ljava/lang/IllegalArgumentException;
        //  92     117    117    121    Ljava/lang/IllegalArgumentException;
        //  121    132    135    139    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:141)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public boolean isMessageIDSupported(final String s) {
        return this.mySupportedIDs.contains(s);
    }
    
    public OCClangMessageDescriptor findMessageDescriptor(final String s) {
        return new Finder(s).a();
    }
    
    @Nullable
    public OCClangMessageDescriptor getSupportedMessageDescriptor(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "id", "com/jetbrains/cidr/lang/daemon/clang/OCClangMessageFinder", "getSupportedMessageDescriptor"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final OCClangMessageDescriptor ocClangMessageDescriptor = this.myDescriptorsMap.get(s);
        try {
            if (ocClangMessageDescriptor == null) {
                OCClangMessageFinder.LOG.error("Can't find clang message " + s);
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (!this.isMessageIDSupported(s)) {
                OCClangMessageFinder.LOG.error("Clang message " + s + " is not marked as supported");
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        return ocClangMessageDescriptor;
    }
    
    static {
        LOG = Logger.getInstance("#com.jetbrains.cidr.lang.daemon.clang.OCClangMessageFinder");
        myHardcodedMessages = new String[] { "expanded from macro '.*'", "arithmetic on pointer to .*, which is not a constant size in non-fragile ABI", "collection expression type '.*' is not a valid object" };
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    private class Finder
    {
        private String myString;
        private List<String> myWords;
        
        Finder(final String s) {
            this.myString = s.toLowerCase();
            this.myWords = (List<String>)StringUtil.getWordsIn(this.myString);
        }
        
        private OCClangMessageDescriptor a() {
            final TIntHashSet set = new TIntHashSet(OCClangMessageFinder.this.myDescriptors.size());
            for (int i = 0; i < OCClangMessageFinder.this.myDescriptors.size(); ++i) {
                set.add(i);
            }
            if (!this.myWords.isEmpty()) {
                final Integer a = this.a(0, set, 0);
                if (a != null) {
                    return (OCClangMessageDescriptor)OCClangMessageFinder.this.myDescriptors.get(a);
                }
            }
            else {
                OCClangMessageFinder.LOG.warn("No words found in the message \"" + this.myString + "\"");
            }
            return null;
        }
        
        @Nullable
        private Integer a(final int n, final TIntHashSet set, final int n2) {
            if (n < this.myWords.size()) {
                final TIntHashSet set2 = new TIntHashSet(set.toArray());
                final IntArrayList list = OCClangMessageFinder.this.myWordsIndex.get(this.myWords.get(n));
                if (list != null) {
                    set2.retainAll(list.toArray());
                }
                else {
                    set2.clear();
                }
                if (!set2.isEmpty()) {
                    final Integer a = this.a(n + 1, set2, n2 + 1);
                    if (a != null) {
                        return a;
                    }
                }
                final Integer a2 = this.a(n + 1, set, n2);
                if (a2 != null) {
                    return a2;
                }
            }
            else {
                final TIntIterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    if (((OCClangMessageDescriptor)OCClangMessageFinder.this.myDescriptors.get(iterator.next())).getWordsCnt() > n2) {
                        iterator.remove();
                    }
                }
                if (set.size() > 30) {
                    OCClangMessageFinder.LOG.error("Too many possible patterns for \"" + this.myString + "\"");
                    return null;
                }
                for (final int next : set) {
                    if (((OCClangMessageDescriptor)OCClangMessageFinder.this.myDescriptors.get(next)).getPattern().matcher(this.myString).matches()) {
                        return next;
                    }
                }
            }
            return null;
        }
    }
}
