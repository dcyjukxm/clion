// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.gdb;

import com.jetbrains.cidr.execution.debugger.backend.DebuggerDriver;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.InputStream;
import java.io.IOException;
import com.intellij.openapi.util.text.StringUtil;
import java.nio.charset.Charset;
import com.intellij.execution.util.ExecUtil;
import com.intellij.openapi.util.io.FileUtil;
import java.io.File;
import com.intellij.execution.ExecutionException;
import gnu.trove.TLongHashSet;
import org.jetbrains.annotations.NotNull;
import com.intellij.execution.configurations.GeneralCommandLine;

public class MacOSDebugSymbols
{
    public static TLongHashSet load(@NotNull final GeneralCommandLine generalCommandLine) throws IOException {
        try {
            if (generalCommandLine == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "cl", "com/jetbrains/cidr/execution/debugger/backend/gdb/MacOSDebugSymbols", "load"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            final File file = new File(generalCommandLine.getExePath());
            final File file2 = new File(file.getParentFile(), file.getName() + ".dSYM");
            Label_0287: {
                try {
                    if (file2.exists()) {
                        if (file.lastModified() <= file2.lastModified()) {
                            break Label_0287;
                        }
                    }
                }
                catch (ExecutionException ex2) {
                    throw b((Exception)ex2);
                }
                final File tempDirectory = FileUtil.createTempDirectory(file.getName(), (String)null);
                try {
                    final File file3 = new File(tempDirectory, file2.getName());
                    final GeneralCommandLine generalCommandLine2 = new GeneralCommandLine(new String[] { "/usr/bin/dsymutil" });
                    generalCommandLine2.addParameter(generalCommandLine.getExePath());
                    generalCommandLine2.addParameter("-o");
                    generalCommandLine2.addParameter(file3.getAbsolutePath());
                    final String notNullize = StringUtil.notNullize(ExecUtil.readFirstLine(generalCommandLine2.createProcess().getInputStream(), (Charset)null));
                    try {
                        if (notNullize.matches("^error.*")) {
                            throw new IOException("Cannot create dsym bundle for " + generalCommandLine.getExePath());
                        }
                    }
                    catch (ExecutionException ex3) {
                        throw b((Exception)ex3);
                    }
                    try {
                        if (file2.exists()) {
                            FileUtil.delete(file2);
                        }
                    }
                    catch (ExecutionException ex4) {
                        throw b((Exception)ex4);
                    }
                    FileUtil.rename(file3, file2);
                }
                finally {
                    FileUtil.delete(tempDirectory);
                }
            }
            final GeneralCommandLine generalCommandLine3 = new GeneralCommandLine(new String[] { "/usr/bin/otool" });
            generalCommandLine3.addParameter("-I");
            generalCommandLine3.addParameter(generalCommandLine.getExePath());
            return parseIndirectSymTable(generalCommandLine3.createProcess().getInputStream());
        }
        catch (ExecutionException ex5) {
            throw new IOException((Throwable)ex5);
        }
    }
    
    static TLongHashSet parseIndirectSymTable(final InputStream inputStream) throws ExecutionException {
        final Scanner scanner = new Scanner(inputStream);
        final TLongHashSet set = new TLongHashSet();
        try {
            while (true) {
                Label_0114: {
                    try {
                        if (!scanner.hasNext()) {
                            break;
                        }
                        if (scanner.findInLine("Indirect symbols for \\(__TEXT,__stubs\\)") == null) {
                            break Label_0114;
                        }
                    }
                    catch (NoSuchElementException ex) {
                        throw b(ex);
                    }
                    final int nextInt = scanner.nextInt();
                    scanner.nextLine();
                    a(scanner, "address");
                    a(scanner, "index");
                    scanner.nextLine();
                    int i = 0;
                    try {
                        while (i < nextInt) {
                            set.add(DebuggerDriver.parseAddress(scanner.next()).unsignedLongValue());
                            scanner.nextInt();
                            scanner.nextLine();
                            ++i;
                        }
                    }
                    catch (NoSuchElementException ex2) {
                        throw b(ex2);
                    }
                    continue;
                }
                scanner.nextLine();
            }
        }
        catch (NoSuchElementException ex3) {
            throw new ExecutionException("Cannot parse indirect symbol table: " + ex3);
        }
        return set;
    }
    
    private static void a(@NotNull final Scanner scanner, @NotNull final String s) throws ExecutionException {
        try {
            if (scanner == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "scanner", "com/jetbrains/cidr/execution/debugger/backend/gdb/MacOSDebugSymbols", "nextTitle"));
            }
        }
        catch (ExecutionException ex) {
            throw b((Exception)ex);
        }
        try {
            if (s == null) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "expected", "com/jetbrains/cidr/execution/debugger/backend/gdb/MacOSDebugSymbols", "nextTitle"));
            }
        }
        catch (ExecutionException ex2) {
            throw b((Exception)ex2);
        }
        final String next = scanner.next();
        try {
            if (!s.equals(next)) {
                throw new ExecutionException("Invalid section title:" + next);
            }
        }
        catch (ExecutionException ex3) {
            throw b((Exception)ex3);
        }
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
