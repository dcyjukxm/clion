// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.cpp.cmake.workspace;

import com.intellij.openapi.util.Pair;
import java.util.Map;
import java.io.File;
import java.util.List;

private static class IOLibraryRootsAndFiles
{
    List<File> systemHeaderRoots;
    List<File> userHeaderRoots;
    List<File> headerExcludeRoots;
    List<File> filesToIndex;
    Map<String, List<File>> config2pchFiles;
    Map<String, List<File>> fileAndLang2pchFiles;
    Map<String, Map<Pair<String, File>, File>> config2includeMap;
}
