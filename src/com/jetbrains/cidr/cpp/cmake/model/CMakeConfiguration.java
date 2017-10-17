// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import java.util.ArrayList;
import java.util.Set;
import java.util.Collection;
import com.intellij.openapi.diagnostic.Logger;
import com.jetbrains.cidr.cpp.CPPLog;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.Nullable;
import java.io.File;
import com.jetbrains.cidr.lang.OCLanguageKind;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import com.jetbrains.cidr.execution.CidrBuildConfiguration;
import java.io.Serializable;

public class CMakeConfiguration implements Serializable, CidrBuildConfiguration
{
    @NotNull
    private volatile CMakeTarget myTarget;
    private final int myId;
    @NotNull
    private final String myName;
    @NotNull
    private final Map<OCLanguageKind, CMakeConfigurationSettings> myPerLanguageSettings;
    @NotNull
    private final Map<File, CMakeFileSettings> mySources;
    @Nullable
    private final File myProductFile;
    @NotNull
    private final File myConfigurationGenerationDir;
    @NotNull
    private final File myConfigurationAndTargetGenerationDir;
    @NotNull
    private final File myBuildWorkingDir;
    @NotNull
    private final TargetType myTargetType;
    @NotNull
    private final List<String> myLinkerFlags;
    
    public CMakeConfiguration(final int myId, @NotNull final String myName, @NotNull final File myConfigurationGenerationDir, @NotNull final File myConfigurationAndTargetGenerationDir, @NotNull final Map<OCLanguageKind, CMakeConfigurationSettings> map, @NotNull final List<String> list, @NotNull final Map<File, CMakeFileSettings> map2, @Nullable final File myProductFile, @NotNull final File myBuildWorkingDir, @NotNull final TargetType myTargetType) {
        if (myName == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        if (myConfigurationGenerationDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationGenerationDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        if (myConfigurationAndTargetGenerationDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "configurationAndTargetGenerationDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        if (map == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "perLanguageSettings", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        if (list == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "linkerFlags", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        if (map2 == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "sources", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        if (myBuildWorkingDir == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "buildWorkingDir", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        if (myTargetType == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "targetType", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "<init>"));
        }
        this.myName = myName;
        this.myId = myId;
        this.myConfigurationGenerationDir = myConfigurationGenerationDir;
        this.myConfigurationAndTargetGenerationDir = myConfigurationAndTargetGenerationDir;
        this.myPerLanguageSettings = Collections.unmodifiableMap((Map<? extends OCLanguageKind, ? extends CMakeConfigurationSettings>)map);
        this.myLinkerFlags = Collections.unmodifiableList((List<? extends String>)list);
        this.mySources = Collections.unmodifiableMap((Map<? extends File, ? extends CMakeFileSettings>)map2);
        this.myProductFile = myProductFile;
        this.myBuildWorkingDir = myBuildWorkingDir;
        this.myTargetType = myTargetType;
    }
    
    void initTarget(@NotNull final CMakeTarget myTarget) {
        try {
            if (myTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "initTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        Logger log = null;
        boolean b = false;
        Label_0063: {
            try {
                log = CPPLog.LOG;
                if (this.myTarget == null) {
                    b = true;
                    break Label_0063;
                }
            }
            catch (IllegalArgumentException ex2) {
                throw a(ex2);
            }
            b = false;
        }
        log.assertTrue(b, (Object)"myTarget already initialized");
        this.myTarget = myTarget;
    }
    
    public int getId() {
        return this.myId;
    }
    
    @NotNull
    @Override
    public String getName() {
        String myName;
        try {
            myName = this.myName;
            if (myName == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myName;
    }
    
    @NotNull
    public File getConfigurationGenerationDir() {
        File myConfigurationGenerationDir;
        try {
            myConfigurationGenerationDir = this.myConfigurationGenerationDir;
            if (myConfigurationGenerationDir == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getConfigurationGenerationDir"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConfigurationGenerationDir;
    }
    
    @NotNull
    File getConfigurationAndTargetGenerationDir() {
        File myConfigurationAndTargetGenerationDir;
        try {
            myConfigurationAndTargetGenerationDir = this.myConfigurationAndTargetGenerationDir;
            if (myConfigurationAndTargetGenerationDir == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getConfigurationAndTargetGenerationDir"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myConfigurationAndTargetGenerationDir;
    }
    
    @Nullable
    public File getProductFile() {
        return this.myProductFile;
    }
    
    @NotNull
    public Collection<OCLanguageKind> getSupportedLanguages() {
        Set<OCLanguageKind> keySet;
        try {
            keySet = this.myPerLanguageSettings.keySet();
            if (keySet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getSupportedLanguages"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return keySet;
    }
    
    @NotNull
    Map<OCLanguageKind, CMakeConfigurationSettings> getPerLanguageSettingsMap() {
        Map<OCLanguageKind, CMakeConfigurationSettings> myPerLanguageSettings;
        try {
            myPerLanguageSettings = this.myPerLanguageSettings;
            if (myPerLanguageSettings == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getPerLanguageSettingsMap"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myPerLanguageSettings;
    }
    
    @Nullable
    public CMakeConfigurationSettings getSettings(@Nullable final OCLanguageKind ocLanguageKind) {
        try {
            if (ocLanguageKind == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.myPerLanguageSettings.get(ocLanguageKind);
    }
    
    @NotNull
    public List<String> getLinkerFlags() {
        List<String> myLinkerFlags;
        try {
            myLinkerFlags = this.myLinkerFlags;
            if (myLinkerFlags == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getLinkerFlags"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myLinkerFlags;
    }
    
    @NotNull
    public Collection<File> getSources() {
        Set<File> keySet;
        try {
            keySet = this.mySources.keySet();
            if (keySet == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getSources"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return keySet;
    }
    
    @NotNull
    Map<File, CMakeFileSettings> getSourcesMap() {
        Map<File, CMakeFileSettings> mySources;
        try {
            mySources = this.mySources;
            if (mySources == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getSourcesMap"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return mySources;
    }
    
    @Nullable
    public CMakeFileSettings getFileSettings(@Nullable final File file) {
        try {
            if (file == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return this.mySources.get(file);
    }
    
    @NotNull
    public List<String> getCombinedCompilerFlags(@NotNull final OCLanguageKind ocLanguageKind, @Nullable final File file) {
        try {
            if (ocLanguageKind == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageKind", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getCombinedCompilerFlags"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final CMakeConfigurationSettings settings = this.getSettings(ocLanguageKind);
        final CMakeFileSettings fileSettings = this.getFileSettings(file);
        final ArrayList<Object> list = (ArrayList<Object>)new ArrayList<String>();
        try {
            if (settings != null) {
                list.addAll(settings.getDefines());
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        try {
            if (fileSettings != null) {
                list.addAll(fileSettings.getDefines());
            }
        }
        catch (IllegalArgumentException ex3) {
            throw a(ex3);
        }
        try {
            if (settings != null) {
                list.addAll(settings.getFlags());
            }
        }
        catch (IllegalArgumentException ex4) {
            throw a(ex4);
        }
        try {
            if (fileSettings != null) {
                list.addAll(fileSettings.getFlags());
            }
        }
        catch (IllegalArgumentException ex5) {
            throw a(ex5);
        }
        List<Object> unmodifiableList;
        try {
            unmodifiableList = (List<Object>)Collections.unmodifiableList((List<? extends String>)list);
            if (unmodifiableList == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getCombinedCompilerFlags"));
            }
        }
        catch (IllegalArgumentException ex6) {
            throw a(ex6);
        }
        return (List<String>)unmodifiableList;
    }
    
    @Override
    public String toString() {
        return this.myId + "-" + this.myName + " [" + this.mySources.size() + " sources]";
    }
    
    @NotNull
    public CMakeTarget getTarget() {
        CMakeTarget myTarget;
        try {
            myTarget = this.myTarget;
            if (myTarget == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getTarget"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTarget;
    }
    
    @NotNull
    public File getBuildWorkingDir() {
        File myBuildWorkingDir;
        try {
            myBuildWorkingDir = this.myBuildWorkingDir;
            if (myBuildWorkingDir == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getBuildWorkingDir"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myBuildWorkingDir;
    }
    
    @NotNull
    public TargetType getTargetType() {
        TargetType myTargetType;
        try {
            myTargetType = this.myTargetType;
            if (myTargetType == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration", "getTargetType"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return myTargetType;
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public enum TargetType
    {
        EXECUTABLE, 
        STATIC_LIBRARY, 
        SHARED_LIBRARY, 
        UTILITY;
        
        @NotNull
        public static TargetType fromString(@Nullable final String p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     0: aload_0        
            //     1: iconst_0       
            //     2: invokestatic    com/intellij/openapi/util/text/StringUtil.parseInt:(Ljava/lang/String;I)I
            //     5: istore_1       
            //     6: iload_1        
            //     7: tableswitch {
            //                0: 36
            //                1: 36
            //                2: 85
            //                3: 127
            //          default: 169
            //        }
            //    36: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.EXECUTABLE:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
            //    39: dup            
            //    40: ifnonnull       84
            //    43: goto            50
            //    46: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    49: athrow         
            //    50: new             Ljava/lang/IllegalStateException;
            //    53: dup            
            //    54: ldc             "@NotNull method %s.%s must not return null"
            //    56: ldc             2
            //    58: anewarray       Ljava/lang/Object;
            //    61: dup            
            //    62: ldc             0
            //    64: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
            //    66: aastore        
            //    67: dup            
            //    68: ldc             1
            //    70: ldc             "fromString"
            //    72: aastore        
            //    73: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    76: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //    79: athrow         
            //    80: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    83: athrow         
            //    84: areturn        
            //    85: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.STATIC_LIBRARY:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
            //    88: dup            
            //    89: ifnonnull       126
            //    92: new             Ljava/lang/IllegalStateException;
            //    95: dup            
            //    96: ldc             "@NotNull method %s.%s must not return null"
            //    98: ldc             2
            //   100: anewarray       Ljava/lang/Object;
            //   103: dup            
            //   104: ldc             0
            //   106: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
            //   108: aastore        
            //   109: dup            
            //   110: ldc             1
            //   112: ldc             "fromString"
            //   114: aastore        
            //   115: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   118: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   121: athrow         
            //   122: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   125: athrow         
            //   126: areturn        
            //   127: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.SHARED_LIBRARY:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
            //   130: dup            
            //   131: ifnonnull       168
            //   134: new             Ljava/lang/IllegalStateException;
            //   137: dup            
            //   138: ldc             "@NotNull method %s.%s must not return null"
            //   140: ldc             2
            //   142: anewarray       Ljava/lang/Object;
            //   145: dup            
            //   146: ldc             0
            //   148: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
            //   150: aastore        
            //   151: dup            
            //   152: ldc             1
            //   154: ldc             "fromString"
            //   156: aastore        
            //   157: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   160: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   163: athrow         
            //   164: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   167: athrow         
            //   168: areturn        
            //   169: getstatic       com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.UTILITY:Lcom/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType;
            //   172: dup            
            //   173: ifnonnull       210
            //   176: new             Ljava/lang/IllegalStateException;
            //   179: dup            
            //   180: ldc             "@NotNull method %s.%s must not return null"
            //   182: ldc             2
            //   184: anewarray       Ljava/lang/Object;
            //   187: dup            
            //   188: ldc             0
            //   190: ldc             "com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType"
            //   192: aastore        
            //   193: dup            
            //   194: ldc             1
            //   196: ldc             "fromString"
            //   198: aastore        
            //   199: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //   202: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   205: athrow         
            //   206: invokestatic    com/jetbrains/cidr/cpp/cmake/model/CMakeConfiguration$TargetType.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //   209: athrow         
            //   210: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  6      43     46     50     Ljava/lang/IllegalStateException;
            //  36     80     80     84     Ljava/lang/IllegalStateException;
            //  85     122    122    126    Ljava/lang/IllegalStateException;
            //  127    164    164    168    Ljava/lang/IllegalStateException;
            //  169    206    206    210    Ljava/lang/IllegalStateException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:556)
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
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
