// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.projectWizard;

import com.intellij.openapi.actionSystem.DefaultActionGroup;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Collection;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.CMakeProjectGenerator;
import com.intellij.util.containers.MultiMap;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase;
import com.jetbrains.cidr.cpp.cmake.projectWizard.generators.CMakeCPPProjectGenerator;
import com.intellij.platform.DirectoryProjectGenerator;
import org.jetbrains.annotations.NotNull;
import com.intellij.ide.util.projectWizard.actions.ProjectSpecificAction;
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep;

public class CLionCMakeNewProjectStep extends AbstractNewProjectStep
{
    protected CLionCMakeNewProjectStep() {
        super(new Customization());
        this.getTemplatePresentation().setText("New Project");
        this.getTemplatePresentation().setDescription("Create a new CMake Project");
    }
    
    @Override
    protected void addProjectSpecificAction(@NotNull final ProjectSpecificAction projectSpecificAction) {
        try {
            if (projectSpecificAction == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectSpecificAction", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep", "addProjectSpecificAction"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Customization extends AbstractNewProjectStep.Customization
    {
        @NotNull
        public AbstractCallback createCallback() {
            AbstractCallback abstractCallback;
            try {
                abstractCallback = new AbstractCallback();
                if (abstractCallback == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization", "createCallback"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return abstractCallback;
        }
        
        @NotNull
        @Override
        protected DirectoryProjectGenerator createEmptyProjectGenerator() {
            CMakeCPPProjectGenerator cMakeCPPProjectGenerator;
            try {
                cMakeCPPProjectGenerator = new CMakeCPPProjectGenerator();
                if (cMakeCPPProjectGenerator == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization", "createEmptyProjectGenerator"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            return cMakeCPPProjectGenerator;
        }
        
        @NotNull
        @Override
        protected ProjectSettingsStepBase createProjectSpecificSettingsStep(@NotNull final DirectoryProjectGenerator directoryProjectGenerator, @NotNull final AbstractCallback abstractCallback) {
            try {
                if (directoryProjectGenerator == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "projectGenerator", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization", "createProjectSpecificSettingsStep"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            try {
                if (abstractCallback == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization", "createProjectSpecificSettingsStep"));
                }
            }
            catch (IllegalStateException ex2) {
                throw a(ex2);
            }
            CMakeProjectSpecificSettingsStep cMakeProjectSpecificSettingsStep;
            try {
                cMakeProjectSpecificSettingsStep = new CMakeProjectSpecificSettingsStep(directoryProjectGenerator, abstractCallback);
                if (cMakeProjectSpecificSettingsStep == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization", "createProjectSpecificSettingsStep"));
                }
            }
            catch (IllegalStateException ex3) {
                throw a(ex3);
            }
            return cMakeProjectSpecificSettingsStep;
        }
        
        @Override
        public AnAction[] getActions(@NotNull final DirectoryProjectGenerator[] p0, @NotNull final AbstractCallback p1) {
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
            //    18: ldc             "generators"
            //    20: aastore        
            //    21: dup            
            //    22: ldc             1
            //    24: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization"
            //    26: aastore        
            //    27: dup            
            //    28: ldc             2
            //    30: ldc             "getActions"
            //    32: aastore        
            //    33: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    36: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    39: athrow         
            //    40: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
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
            //    62: ldc             "callback"
            //    64: aastore        
            //    65: dup            
            //    66: ldc             1
            //    68: ldc             "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization"
            //    70: aastore        
            //    71: dup            
            //    72: ldc             2
            //    74: ldc             "getActions"
            //    76: aastore        
            //    77: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
            //    80: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
            //    83: athrow         
            //    84: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization.a:(Ljava/lang/IllegalStateException;)Ljava/lang/IllegalStateException;
            //    87: athrow         
            //    88: invokestatic    com/intellij/util/containers/ContainerUtil.newArrayList:()Ljava/util/ArrayList;
            //    91: astore_3       
            //    92: new             Lcom/intellij/util/containers/MultiMap;
            //    95: dup            
            //    96: invokespecial   com/intellij/util/containers/MultiMap.<init>:()V
            //    99: astore          4
            //   101: aload_1        
            //   102: astore          5
            //   104: aload           5
            //   106: arraylength    
            //   107: istore          6
            //   109: iconst_0       
            //   110: istore          7
            //   112: iload           7
            //   114: iload           6
            //   116: if_icmpge       163
            //   119: aload           5
            //   121: iload           7
            //   123: aaload         
            //   124: astore          8
            //   126: ldc             "Other"
            //   128: astore          9
            //   130: aload           8
            //   132: instanceof      Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator;
            //   135: ifeq            148
            //   138: aload           8
            //   140: checkcast       Lcom/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator;
            //   143: invokevirtual   com/jetbrains/cidr/cpp/cmake/projectWizard/generators/CMakeProjectGenerator.getGroupName:()Ljava/lang/String;
            //   146: astore          9
            //   148: aload           4
            //   150: aload           9
            //   152: aload           8
            //   154: invokevirtual   com/intellij/util/containers/MultiMap.putValue:(Ljava/lang/Object;Ljava/lang/Object;)V
            //   157: iinc            7, 1
            //   160: goto            112
            //   163: new             Ljava/util/ArrayList;
            //   166: dup            
            //   167: aload           4
            //   169: invokevirtual   com/intellij/util/containers/MultiMap.keySet:()Ljava/util/Set;
            //   172: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
            //   175: astore          5
            //   177: aload           5
            //   179: invokedynamic   compare:()Ljava/util/Comparator;
            //   184: invokestatic    java/util/Collections.sort:(Ljava/util/List;Ljava/util/Comparator;)V
            //   187: aload           4
            //   189: invokestatic    com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization.a:(Lcom/intellij/util/containers/MultiMap;)V
            //   192: aload           5
            //   194: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //   199: astore          6
            //   201: aload           6
            //   203: invokeinterface java/util/Iterator.hasNext:()Z
            //   208: ifeq            241
            //   211: aload           6
            //   213: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //   218: checkcast       Ljava/lang/String;
            //   221: astore          7
            //   223: aload_0        
            //   224: aload           7
            //   226: aload_3        
            //   227: aload_2        
            //   228: aload           4
            //   230: aload           7
            //   232: invokevirtual   com/intellij/util/containers/MultiMap.get:(Ljava/lang/Object;)Ljava/util/Collection;
            //   235: invokespecial   com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization.a:(Ljava/lang/String;Ljava/util/List;Lcom/intellij/ide/util/projectWizard/AbstractNewProjectStep$AbstractCallback;Ljava/util/Collection;)V
            //   238: goto            201
            //   241: aload_3        
            //   242: aload_3        
            //   243: invokeinterface java/util/List.size:()I
            //   248: anewarray       Lcom/intellij/openapi/actionSystem/AnAction;
            //   251: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
            //   256: checkcast       [Lcom/intellij/openapi/actionSystem/AnAction;
            //   259: areturn        
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  0      40     40     44     Ljava/lang/IllegalStateException;
            //  44     84     84     88     Ljava/lang/IllegalStateException;
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.decompiler.languages.java.ast.NameVariables.generateNameForVariable(NameVariables.java:264)
            //     at com.strobel.decompiler.languages.java.ast.NameVariables.assignNamesToVariables(NameVariables.java:198)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:276)
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
        
        private static void a(final MultiMap<String, DirectoryProjectGenerator> multiMap) {
            final Collection remove = multiMap.remove((Object)"Other");
            if (remove != null) {
                for (final DirectoryProjectGenerator directoryProjectGenerator : remove) {
                    try {
                        if (!(directoryProjectGenerator instanceof CMakeProjectGenerator)) {
                            continue;
                        }
                        multiMap.putValue((Object)"Other", (Object)directoryProjectGenerator);
                    }
                    catch (IllegalStateException ex) {
                        throw a(ex);
                    }
                }
            }
        }
        
        private void a(final String s, final List<AnAction> list, @NotNull final AbstractCallback abstractCallback, final Collection<DirectoryProjectGenerator> collection) {
            try {
                if (abstractCallback == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "callback", "com/jetbrains/cidr/cpp/cmake/projectWizard/CLionCMakeNewProjectStep$Customization", "appendActionsFromAllGenerators"));
                }
            }
            catch (IllegalStateException ex) {
                throw a(ex);
            }
            final ArrayList<Object> list2 = new ArrayList<Object>();
            final Iterator<DirectoryProjectGenerator> iterator = collection.iterator();
            while (iterator.hasNext()) {
                Collections.addAll(list2, this.getActions(iterator.next(), abstractCallback));
            }
            list.add((AnAction)new DefaultActionGroup(s, (List)list2));
        }
        
        private static IllegalStateException a(final IllegalStateException ex) {
            return ex;
        }
    }
}
