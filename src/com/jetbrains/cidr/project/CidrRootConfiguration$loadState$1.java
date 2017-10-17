// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.project;

import com.intellij.openapi.module.Module;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.vfs.pointers.VirtualFilePointerManager;
import gnu.trove.THashSet;
import kotlin.Unit;
import com.intellij.openapi.util.Computable;
import com.intellij.util.messages.Topic;
import gnu.trove.THashMap;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import org.jetbrains.annotations.Nullable;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Iterator;
import java.util.List;
import com.intellij.openapi.vfs.VfsUtil;
import kotlin.collections.CollectionsKt;
import java.util.Set;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import com.intellij.openapi.vfs.pointers.VirtualFilePointer;
import java.util.LinkedHashMap;
import java.util.Map;
import com.intellij.openapi.application.ApplicationManager;
import java.io.File;
import java.util.Collection;
import org.jetbrains.annotations.NotNull;
import kotlin.jvm.JvmField;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.components.State;
import com.intellij.openapi.vfs.pointers.VirtualFilePointerListener;
import org.jdom.Element;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.AbstractProjectComponent;
import kotlin.Metadata;

@Metadata(mv = { 1, 1, 7 }, bv = { 1, 0, 2 }, k = 3, d1 = { "\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002" }, d2 = { "<anonymous>", "", "run" })
static final class CidrRootConfiguration$loadState$1 implements Runnable {
    @Override
    public final void run() {
        this.this$0.a();
    }
}