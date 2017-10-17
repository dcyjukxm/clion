// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.model;

import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.Trinity;
import java.util.List;
import com.intellij.openapi.util.io.FileUtil;
import gnu.trove.TObjectHashingStrategy;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import com.intellij.util.containers.ContainerUtil;
import java.io.File;
import com.jetbrains.cidr.cpp.cmake.CMakeException;
import org.jetbrains.annotations.NotNull;
import com.intellij.util.containers.MultiMap;

public class CMakeMakeFile
{
    private final MultiMap<String, Rule> myVariables;
    
    CMakeMakeFile(@NotNull final String s) throws CMakeException {
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "text", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile", "<init>"));
        }
        this.myVariables = (MultiMap<String, Rule>)new Parser().parse(s);
    }
    
    CMakeMakeFile(@NotNull final File file, @NotNull final String s) throws CMakeException {
        if (file == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "file", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile", "<init>"));
        }
        if (s == null) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "encoding", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile", "<init>"));
        }
        this.myVariables = (MultiMap<String, Rule>)new Parser().parse(file, s);
    }
    
    @NotNull
    public Iterable<Rule> getRules() {
        Iterable concat;
        try {
            concat = ContainerUtil.concat(new Iterable[] { this.myVariables.values() });
            if (concat == null) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile", "getRules"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Iterable<Rule>)concat;
    }
    
    @Nullable
    public Rule getRule(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile", "getRule"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Rule)ContainerUtil.getFirstItem((Collection)this.getRules(s));
    }
    
    @Nullable
    public Collection<Rule> getRules(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "key", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile", "getRules"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        return (Collection<Rule>)this.myVariables.get((Object)s);
    }
    
    @Nullable
    public String getRuleDependency(@NotNull final String s) {
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile", "getRuleDependency"));
            }
        }
        catch (IllegalArgumentException ex) {
            throw a(ex);
        }
        final Rule rule = this.getRule(s);
        try {
            if (rule == null) {
                return null;
            }
        }
        catch (IllegalArgumentException ex2) {
            throw a(ex2);
        }
        return rule.getDependencies();
    }
    
    private static IllegalArgumentException a(final IllegalArgumentException ex) {
        return ex;
    }
    
    public static class Rule
    {
        @NotNull
        private final String myTarget;
        @NotNull
        private final String myDependencies;
        @NotNull
        private final String myRecipe;
        
        public Rule(@NotNull final String myTarget, @NotNull final String s, @NotNull final String myRecipe) {
            if (myTarget == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "<init>"));
            }
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependencies", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "<init>"));
            }
            if (myRecipe == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "recipe", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "<init>"));
            }
            this.myTarget = myTarget;
            this.myDependencies = s.trim();
            this.myRecipe = myRecipe;
        }
        
        @NotNull
        public String getTarget() {
            String myTarget;
            try {
                myTarget = this.myTarget;
                if (myTarget == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "getTarget"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myTarget;
        }
        
        @NotNull
        public String getDependencies() {
            String myDependencies;
            try {
                myDependencies = this.myDependencies;
                if (myDependencies == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "getDependencies"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myDependencies;
        }
        
        @NotNull
        public String getRecipe() {
            String myRecipe;
            try {
                myRecipe = this.myRecipe;
                if (myRecipe == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Rule", "getRecipe"));
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            return myRecipe;
        }
        
        @Override
        public String toString() {
            StringBuilder append;
            try {
                append = new StringBuilder().append(this.myTarget).append(": ").append(this.myDependencies);
                if (this.myRecipe.isEmpty()) {
                    final String string = "";
                    return append.append(string).toString();
                }
            }
            catch (IllegalArgumentException ex) {
                throw a(ex);
            }
            final String string = "\n" + this.myRecipe;
            return append.append(string).toString();
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
                    final Rule rule = this;
                    final Class<? extends Rule> clazz = rule.getClass();
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
                    final Rule rule = this;
                    final Class<? extends Rule> clazz = rule.getClass();
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
            final Rule rule2 = (Rule)o;
            try {
                if (!this.myTarget.equals(rule2.myTarget)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex4) {
                throw a(ex4);
            }
            try {
                if (!this.myDependencies.equals(rule2.myDependencies)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex5) {
                throw a(ex5);
            }
            try {
                if (!this.myRecipe.equals(rule2.myRecipe)) {
                    return false;
                }
            }
            catch (IllegalArgumentException ex6) {
                throw a(ex6);
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            return 31 * (31 * this.myTarget.hashCode() + this.myDependencies.hashCode()) + this.myRecipe.hashCode();
        }
        
        private static IllegalArgumentException a(final IllegalArgumentException ex) {
            return ex;
        }
    }
    
    static class Parser extends CMakeSettingsFileParser<Rule>
    {
        private Parser() {
            super(": ");
        }
        
        @NotNull
        @Override
        protected TObjectHashingStrategy<String> getHashingStrategy() {
            TObjectHashingStrategy path_HASHING_STRATEGY;
            try {
                path_HASHING_STRATEGY = FileUtil.PATH_HASHING_STRATEGY;
                if (path_HASHING_STRATEGY == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "getHashingStrategy"));
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            return (TObjectHashingStrategy<String>)path_HASHING_STRATEGY;
        }
        
        @NotNull
        @Override
        protected Trinity<String, Rule, Integer> parseVariable(@NotNull final String s, @NotNull final String s2, final List<String> list) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                if (s2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependencies", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            final StringBuilder sb = new StringBuilder();
            int i = 0;
            int j = 0;
            while (i < list.size()) {
                final String s3 = list.get(i);
                Label_0153: {
                    try {
                        if (j != 0) {
                            break Label_0153;
                        }
                        final String s4 = s3;
                        final String s5 = "\t";
                        final boolean b = s4.startsWith(s5);
                        if (!b) {
                            break Label_0153;
                        }
                        break Label_0153;
                    }
                    catch (UnsupportedOperationException ex3) {
                        throw b(ex3);
                    }
                    try {
                        final String s4 = s3;
                        final String s5 = "\t";
                        final boolean b = s4.startsWith(s5);
                        if (!b) {
                            break;
                        }
                    }
                    catch (UnsupportedOperationException ex4) {
                        throw b(ex4);
                    }
                }
                int n = 0;
                do {
                    if (j == 0) {
                        n = s3.indexOf("@<<", n);
                        if (n != -1) {
                            j = 1;
                            n += 3;
                        }
                    }
                    if (j != 0) {
                        n = s3.indexOf("<<", n);
                        if (n == -1) {
                            break;
                        }
                        j = 0;
                        n += 2;
                    }
                } while (j != 0);
                sb.append(s3);
                ++i;
            }
            Trinity create;
            try {
                create = Trinity.create((Object)s, (Object)new Rule(s, s2, sb.toString()), (Object)i);
                if (create == null) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
                }
            }
            catch (UnsupportedOperationException ex5) {
                throw b(ex5);
            }
            return (Trinity<String, Rule, Integer>)create;
        }
        
        @NotNull
        @Override
        protected Pair<String, Rule> parseVariable(@NotNull final String s, @NotNull final String s2) {
            try {
                if (s == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "target", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
                }
            }
            catch (UnsupportedOperationException ex) {
                throw b(ex);
            }
            try {
                if (s2 == null) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "dependencies", "com/jetbrains/cidr/cpp/cmake/model/CMakeMakeFile$Parser", "parseVariable"));
                }
            }
            catch (UnsupportedOperationException ex2) {
                throw b(ex2);
            }
            throw new UnsupportedOperationException();
        }
        
        private static UnsupportedOperationException b(final UnsupportedOperationException ex) {
            return ex;
        }
    }
}
