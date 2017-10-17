// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard.generators;

import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import org.apache.commons.codec.Charsets;
import java.io.IOException;
import com.intellij.openapi.util.io.FileUtil;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.ui.CMakeSettingsPanel;
import javax.swing.JComponent;
import com.intellij.platform.GeneratorPeerImpl;
import javax.swing.JPanel;
import com.intellij.platform.ProjectGeneratorPeer;
import com.jetbrains.cidr.cpp.CPPLog;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.project.Project;
import com.intellij.facet.ui.ValidationResult;
import org.jetbrains.annotations.NotNull;
import icons.CLionIcons;
import javax.swing.Icon;
import org.jetbrains.annotations.Nullable;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.settings.CMakeProjectSettings;
import com.intellij.platform.DirectoryProjectGeneratorBase;

public abstract class CMakeProjectGenerator extends DirectoryProjectGeneratorBase<CMakeProjectSettings>
{
    public static final String SHARED_LIB_TYPE = "shared";
    public static final String STATIC_LIB_TYPE = "static";
    public static final String OTHER_PROJECT_GROUP_NAME = "Other";
    public static final String EMPTY_LANGUAGE_VERSION_LINE_FOR_CMAKE = "";
    private static final CMakeProjectSettings C_MAKE_DEFAULT_PROJECT_SETTINGS;
    @Nullable
    protected String myLanguageVersion;
    @Nullable
    private String myLibraryType;
    
    public CMakeProjectGenerator() {
        this.myLibraryType = "static";
    }
    
    @Nullable
    @Override
    public Icon getLogo() {
        return CLionIcons.CMake;
    }
    
    @NotNull
    @Override
    public ValidationResult validate(@NotNull final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "baseDirPath"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "validate"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_1        
        //    45: invokestatic    com/intellij/openapi/util/text/StringUtil.isEmptyOrSpaces:(Ljava/lang/String;)Z
        //    48: ifeq            106
        //    51: new             Lcom/intellij/facet/ui/ValidationResult;
        //    54: dup            
        //    55: ldc             "Enter project location"
        //    57: invokespecial   com/intellij/facet/ui/ValidationResult.<init>:(Ljava/lang/String;)V
        //    60: dup            
        //    61: ifnonnull       105
        //    64: goto            71
        //    67: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    70: athrow         
        //    71: new             Ljava/lang/IllegalStateException;
        //    74: dup            
        //    75: ldc             "@NotNull method %s.%s must not return null"
        //    77: ldc             2
        //    79: anewarray       Ljava/lang/Object;
        //    82: dup            
        //    83: ldc             0
        //    85: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //    87: aastore        
        //    88: dup            
        //    89: ldc             1
        //    91: ldc             "validate"
        //    93: aastore        
        //    94: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    97: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   100: athrow         
        //   101: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   104: athrow         
        //   105: areturn        
        //   106: new             Ljava/io/File;
        //   109: dup            
        //   110: aload_1        
        //   111: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   114: astore_2       
        //   115: aload_2        
        //   116: invokevirtual   java/io/File.isAbsolute:()Z
        //   119: ifne            177
        //   122: new             Lcom/intellij/facet/ui/ValidationResult;
        //   125: dup            
        //   126: ldc             "Project location path should be absolute"
        //   128: invokespecial   com/intellij/facet/ui/ValidationResult.<init>:(Ljava/lang/String;)V
        //   131: dup            
        //   132: ifnonnull       176
        //   135: goto            142
        //   138: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   141: athrow         
        //   142: new             Ljava/lang/IllegalStateException;
        //   145: dup            
        //   146: ldc             "@NotNull method %s.%s must not return null"
        //   148: ldc             2
        //   150: anewarray       Ljava/lang/Object;
        //   153: dup            
        //   154: ldc             0
        //   156: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //   158: aastore        
        //   159: dup            
        //   160: ldc             1
        //   162: ldc             "validate"
        //   164: aastore        
        //   165: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   168: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   171: athrow         
        //   172: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   175: athrow         
        //   176: areturn        
        //   177: aload_2        
        //   178: invokevirtual   java/io/File.exists:()Z
        //   181: ifeq            264
        //   184: aload_2        
        //   185: invokevirtual   java/io/File.canWrite:()Z
        //   188: ifne            264
        //   191: goto            198
        //   194: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   197: athrow         
        //   198: new             Lcom/intellij/facet/ui/ValidationResult;
        //   201: dup            
        //   202: ldc             "Directory '%s' is not writable.\nPlease choose another directory."
        //   204: iconst_1       
        //   205: anewarray       Ljava/lang/Object;
        //   208: dup            
        //   209: iconst_0       
        //   210: aload_1        
        //   211: aastore        
        //   212: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   215: invokespecial   com/intellij/facet/ui/ValidationResult.<init>:(Ljava/lang/String;)V
        //   218: dup            
        //   219: ifnonnull       263
        //   222: goto            229
        //   225: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   228: athrow         
        //   229: new             Ljava/lang/IllegalStateException;
        //   232: dup            
        //   233: ldc             "@NotNull method %s.%s must not return null"
        //   235: ldc             2
        //   237: anewarray       Ljava/lang/Object;
        //   240: dup            
        //   241: ldc             0
        //   243: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //   245: aastore        
        //   246: dup            
        //   247: ldc             1
        //   249: ldc             "validate"
        //   251: aastore        
        //   252: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   255: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   258: athrow         
        //   259: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   262: athrow         
        //   263: areturn        
        //   264: getstatic       com/intellij/facet/ui/ValidationResult.OK:Lcom/intellij/facet/ui/ValidationResult;
        //   267: dup            
        //   268: ifnonnull       305
        //   271: new             Ljava/lang/IllegalStateException;
        //   274: dup            
        //   275: ldc             "@NotNull method %s.%s must not return null"
        //   277: ldc             2
        //   279: anewarray       Ljava/lang/Object;
        //   282: dup            
        //   283: ldc             0
        //   285: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //   287: aastore        
        //   288: dup            
        //   289: ldc             1
        //   291: ldc             "validate"
        //   293: aastore        
        //   294: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   297: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   300: athrow         
        //   301: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   304: athrow         
        //   305: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  0      40     40     44     Ljava/lang/IllegalArgumentException;
        //  44     64     67     71     Ljava/lang/IllegalArgumentException;
        //  51     101    101    105    Ljava/lang/IllegalArgumentException;
        //  115    135    138    142    Ljava/lang/IllegalArgumentException;
        //  122    172    172    176    Ljava/lang/IllegalArgumentException;
        //  177    191    194    198    Ljava/lang/IllegalArgumentException;
        //  184    222    225    229    Ljava/lang/IllegalArgumentException;
        //  198    259    259    263    Ljava/lang/IllegalArgumentException;
        //  264    301    301    305    Ljava/lang/IllegalArgumentException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0198:
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
    
    @Override
    public void generateProject(@NotNull final Project p0, @NotNull final VirtualFile p1, @NotNull final CMakeProjectSettings p2, @NotNull final Module p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_1        
        //     1: ifnonnull       44
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    10: ldc             3
        //    12: anewarray       Ljava/lang/Object;
        //    15: dup            
        //    16: ldc             0
        //    18: ldc             "project"
        //    20: aastore        
        //    21: dup            
        //    22: ldc             1
        //    24: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //    26: aastore        
        //    27: dup            
        //    28: ldc             2
        //    30: ldc             "generateProject"
        //    32: aastore        
        //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    39: athrow         
        //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    43: athrow         
        //    44: aload_2        
        //    45: ifnonnull       88
        //    48: new             Ljava/lang/IllegalArgumentException;
        //    51: dup            
        //    52: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    54: ldc             3
        //    56: anewarray       Ljava/lang/Object;
        //    59: dup            
        //    60: ldc             0
        //    62: ldc             "baseDir"
        //    64: aastore        
        //    65: dup            
        //    66: ldc             1
        //    68: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //    70: aastore        
        //    71: dup            
        //    72: ldc             2
        //    74: ldc             "generateProject"
        //    76: aastore        
        //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    83: athrow         
        //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //    87: athrow         
        //    88: aload_3        
        //    89: ifnonnull       132
        //    92: new             Ljava/lang/IllegalArgumentException;
        //    95: dup            
        //    96: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //    98: ldc             3
        //   100: anewarray       Ljava/lang/Object;
        //   103: dup            
        //   104: ldc             0
        //   106: ldc             "settings"
        //   108: aastore        
        //   109: dup            
        //   110: ldc             1
        //   112: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //   114: aastore        
        //   115: dup            
        //   116: ldc             2
        //   118: ldc             "generateProject"
        //   120: aastore        
        //   121: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   124: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   131: athrow         
        //   132: aload           4
        //   134: ifnonnull       177
        //   137: new             Ljava/lang/IllegalArgumentException;
        //   140: dup            
        //   141: ldc             "Argument for @NotNull parameter '%s' of %s.%s must not be null"
        //   143: ldc             3
        //   145: anewarray       Ljava/lang/Object;
        //   148: dup            
        //   149: ldc             0
        //   151: ldc             "module"
        //   153: aastore        
        //   154: dup            
        //   155: ldc             1
        //   157: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator"
        //   159: aastore        
        //   160: dup            
        //   161: ldc             2
        //   163: ldc             "generateProject"
        //   165: aastore        
        //   166: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   169: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   172: athrow         
        //   173: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   176: athrow         
        //   177: invokestatic    com/intellij/featureStatistics/FeatureUsageTracker.getInstance:()Lcom/intellij/featureStatistics/FeatureUsageTracker;
        //   180: ldc             "codeassists.new.cmake.project"
        //   182: invokevirtual   com/intellij/featureStatistics/FeatureUsageTracker.triggerFeatureUsed:(Ljava/lang/String;)V
        //   185: new             Ljava/lang/StringBuilder;
        //   188: dup            
        //   189: invokespecial   java/lang/StringBuilder.<init>:()V
        //   192: ldc             "new.cmake.project."
        //   194: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   197: aload_0        
        //   198: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.getName:()Ljava/lang/String;
        //   201: invokestatic    com/intellij/internal/statistic/beans/ConvertUsagesUtil.ensureProperKey:(Ljava/lang/String;)Ljava/lang/String;
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   210: invokestatic    com/intellij/internal/statistic/UsageTrigger.trigger:(Ljava/lang/String;)V
        //   213: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   216: aload_0        
        //   217: aload_1        
        //   218: aload_2        
        //   219: invokedynamic   compute:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator;Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;)Lcom/intellij/openapi/util/ThrowableComputable;
        //   224: invokeinterface com/intellij/openapi/application/Application.runWriteAction:(Lcom/intellij/openapi/util/ThrowableComputable;)Ljava/lang/Object;
        //   229: checkcast       Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder;
        //   232: astore          5
        //   234: goto            247
        //   237: astore          6
        //   239: aload_0        
        //   240: aload_1        
        //   241: aload           6
        //   243: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.handleErrorDuringGeneration:(Lcom/intellij/openapi/project/Project;Ljava/lang/Exception;)V
        //   246: return         
        //   247: aload_1        
        //   248: aload           5
        //   250: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder.access$000:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   253: aload_0        
        //   254: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.formatSourceFilesAsCpp:()Z
        //   257: aload           5
        //   259: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder.access$100:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder;)[Lcom/intellij/openapi/vfs/VirtualFile;
        //   262: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/CLionProjectWizardUtils.reformatProjectFiles:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;Z[Lcom/intellij/openapi/vfs/VirtualFile;)V
        //   265: aload_1        
        //   266: invokestatic    com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.getInstance:(Lcom/intellij/openapi/project/Project;)Lcom/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace;
        //   269: new             Ljava/io/File;
        //   272: dup            
        //   273: aload_2        
        //   274: invokevirtual   com/intellij/openapi/vfs/VirtualFile.getPath:()Ljava/lang/String;
        //   277: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   280: invokevirtual   com/jetbrains/cidr/cpp/cmake/workspace/CMakeWorkspace.scheduleReloadNewProjectDir:(Ljava/io/File;)V
        //   283: invokestatic    com/intellij/openapi/application/ApplicationManager.getApplication:()Lcom/intellij/openapi/application/Application;
        //   286: invokeinterface com/intellij/openapi/application/Application.isHeadlessEnvironment:()Z
        //   291: ifne            337
        //   294: new             Lcom/intellij/openapi/fileEditor/OpenFileDescriptor;
        //   297: dup            
        //   298: aload_1        
        //   299: aload           5
        //   301: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder.access$000:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder;)Lcom/intellij/openapi/vfs/VirtualFile;
        //   304: invokespecial   com/intellij/openapi/fileEditor/OpenFileDescriptor.<init>:(Lcom/intellij/openapi/project/Project;Lcom/intellij/openapi/vfs/VirtualFile;)V
        //   307: iconst_0       
        //   308: invokevirtual   com/intellij/openapi/fileEditor/OpenFileDescriptor.navigate:(Z)V
        //   311: aload           5
        //   313: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder.access$100:(Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$CreatedFilesHolder;)[Lcom/intellij/openapi/vfs/VirtualFile;
        //   316: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   319: aload_1        
        //   320: invokedynamic   accept:(Lcom/intellij/openapi/project/Project;)Ljava/util/function/Consumer;
        //   325: invokeinterface java/util/List.forEach:(Ljava/util/function/Consumer;)V
        //   330: goto            337
        //   333: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.b:(Ljava/lang/Exception;)Ljava/lang/Exception;
        //   336: athrow         
        //   337: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  132    173    173    177    Ljava/io/IOException;
        //  88     128    128    132    Ljava/io/IOException;
        //  44     84     84     88     Ljava/io/IOException;
        //  0      40     40     44     Ljava/io/IOException;
        //  213    234    237    247    Ljava/io/IOException;
        //  247    330    333    337    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
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
    
    protected void handleErrorDuringGeneration(@NotNull final Project project, final Exception ex) {
        try {
            if (project == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "project", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "handleErrorDuringGeneration"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        Messages.showErrorDialog(project, "Cannot create a new project: " + ex.getMessage(), "New Project");
        CPPLog.LOG.error((Throwable)ex);
    }
    
    @NotNull
    @Override
    public ProjectGeneratorPeer<CMakeProjectSettings> createPeer() {
        JComponent settingsPanel = this.getSettingsPanel();
        if (settingsPanel == null) {
            settingsPanel = new JPanel();
        }
        CMakeProjectSettings cMakeProjectSettings = this.getCMakeProjectSettings();
        if (cMakeProjectSettings == null) {
            cMakeProjectSettings = CMakeProjectGenerator.C_MAKE_DEFAULT_PROJECT_SETTINGS;
        }
        GeneratorPeerImpl generatorPeerImpl;
        try {
            generatorPeerImpl = new GeneratorPeerImpl(cMakeProjectSettings, settingsPanel);
            if (generatorPeerImpl == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "createPeer"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return (ProjectGeneratorPeer<CMakeProjectSettings>)generatorPeerImpl;
    }
    
    @Nullable
    public CMakeProjectSettings getCMakeProjectSettings() {
        final CMakeProjectSettings projectSettings = this.createProjectSettings();
        Label_0038: {
            Label_0023: {
                try {
                    if (projectSettings == null) {
                        return projectSettings;
                    }
                    final CMakeProjectGenerator cMakeProjectGenerator = this;
                    final String s = cMakeProjectGenerator.myLanguageVersion;
                    if (s != null) {
                        break Label_0023;
                    }
                    break Label_0038;
                }
                catch (IllegalArgumentException ex) {
                    throw b(ex);
                }
                try {
                    final CMakeProjectGenerator cMakeProjectGenerator = this;
                    final String s = cMakeProjectGenerator.myLanguageVersion;
                    if (s != null) {
                        projectSettings.setLanguageVersion(this.myLanguageVersion);
                    }
                }
                catch (IllegalArgumentException ex2) {
                    throw b(ex2);
                }
            }
            try {
                if (this.myLibraryType != null) {
                    projectSettings.setLibraryType(this.myLibraryType);
                }
            }
            catch (IllegalArgumentException ex3) {
                throw b(ex3);
            }
        }
        return projectSettings;
    }
    
    public boolean addLibrarySettingsPanel() {
        return false;
    }
    
    public void setLanguageVersion(@NotNull final String myLanguageVersion) {
        try {
            if (myLanguageVersion == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "languageVersion", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "setLanguageVersion"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        this.myLanguageVersion = myLanguageVersion;
    }
    
    public void setLibraryType(@Nullable final String myLibraryType) {
        this.myLibraryType = myLibraryType;
    }
    
    public String[] getLanguageVersions() {
        return null;
    }
    
    @NotNull
    private static VirtualFile a(@NotNull final VirtualFile virtualFile) {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "convertToVirtualFileOrFail"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "convertToVirtualFileOrFail"));
            }
        }
        catch (IllegalArgumentException ex2) {
            throw b(ex2);
        }
        return virtualFile;
    }
    
    @Nullable
    public JComponent getSettingsPanel() {
        final CMakeSettingsPanel settingsPanel = this.createSettingsPanel();
        try {
            if (settingsPanel != null) {
                settingsPanel.initFields(this.myLanguageVersion, this.myLibraryType);
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return settingsPanel;
    }
    
    protected CMakeSettingsPanel createSettingsPanel() {
        return new CMakeSettingsPanel(this);
    }
    
    @Nullable
    public CMakeProjectSettings createProjectSettings() {
        return null;
    }
    
    private CreatedFilesHolder a(final String s, final VirtualFile virtualFile) throws IOException {
        final String sanitizeFileName = FileUtil.sanitizeFileName(s);
        return new CreatedFilesHolder(this.createCMakeFile(sanitizeFileName, virtualFile), this.createSourceFiles(sanitizeFileName, virtualFile));
    }
    
    @NotNull
    protected VirtualFile createCMakeFile(@NotNull final String s, @NotNull final VirtualFile virtualFile) throws IOException {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "name", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "createCMakeFile"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dir", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "createCMakeFile"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        VirtualFile projectFileWithContent;
        try {
            projectFileWithContent = this.createProjectFileWithContent(virtualFile, "CMakeLists.txt", this.getCMakeFileContent(s));
            if (projectFileWithContent == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "createCMakeFile"));
            }
        }
        catch (IOException ex3) {
            throw b(ex3);
        }
        return projectFileWithContent;
    }
    
    @NotNull
    public String getGroupName() {
        String s;
        try {
            s = "Other";
            if (s == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "getGroupName"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw b(ex);
        }
        return s;
    }
    
    @NotNull
    protected abstract String getCMakeFileContent(@NotNull final String p0);
    
    @NotNull
    protected abstract VirtualFile[] createSourceFiles(@NotNull final String p0, @NotNull final VirtualFile p1) throws IOException;
    
    protected boolean formatSourceFilesAsCpp() {
        return true;
    }
    
    @Nullable
    public String getLanguageVersion() {
        return this.myLanguageVersion;
    }
    
    @Nullable
    public String getLibraryType() {
        return this.myLibraryType;
    }
    
    @NotNull
    protected VirtualFile createProjectFileWithContent(@NotNull final VirtualFile virtualFile, final String s, final String s2) throws IOException {
        try {
            if (virtualFile == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectDir", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "createProjectFileWithContent"));
            }
        }
        catch (IOException ex) {
            throw b(ex);
        }
        final VirtualFile orCreateChildData = virtualFile.findOrCreateChildData((Object)this, s);
        VirtualFile virtualFile2;
        try {
            orCreateChildData.setBinaryContent(s2.getBytes(Charsets.UTF_8));
            virtualFile2 = orCreateChildData;
            if (virtualFile2 == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator", "createProjectFileWithContent"));
            }
        }
        catch (IOException ex2) {
            throw b(ex2);
        }
        return virtualFile2;
    }
    
    static {
        C_MAKE_DEFAULT_PROJECT_SETTINGS = new CMakeProjectSettings() {
            @NotNull
            @Override
            public String getLanguageVersionLineForCMake() {
                String s;
                try {
                    s = "";
                    if (s == null) {
                        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator$1", "getLanguageVersionLineForCMake"));
                    }
                }
                catch (IllegalStateException ex) {
                    throw a(ex);
                }
                return s;
            }
            
            private static IllegalStateException a(final IllegalStateException ex) {
                return ex;
            }
        };
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
    
    private static class CreatedFilesHolder
    {
        private final VirtualFile cMakeFile;
        private final VirtualFile[] sourceFiles;
        
        private CreatedFilesHolder(final VirtualFile cMakeFile, final VirtualFile[] sourceFiles) {
            this.cMakeFile = cMakeFile;
            this.sourceFiles = sourceFiles;
        }
    }
}
