// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import java.util.Collection;
import com.google.protobuf.UnmodifiableLazyStringList;
import com.google.protobuf.Message;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.Descriptors;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ModulesLoaded_BroadcastOrBuilder
{
    private int bitField0_;
    private LazyStringList modules_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Broadcasts.access$4700();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Broadcasts.access$4800().ensureFieldAccessorsInitialized((Class)ModulesLoaded_Broadcast.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.modules_ = LazyStringArrayList.EMPTY;
        this.c();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.modules_ = LazyStringArrayList.EMPTY;
        this.c();
    }
    
    private void c() {
        if (ModulesLoaded_Broadcast.access$5200()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.modules_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Broadcasts.access$4700();
    }
    
    public ModulesLoaded_Broadcast getDefaultInstanceForType() {
        return ModulesLoaded_Broadcast.getDefaultInstance();
    }
    
    public ModulesLoaded_Broadcast build() {
        final ModulesLoaded_Broadcast buildPartial = this.buildPartial();
        try {
            if (!buildPartial.isInitialized()) {
                throw newUninitializedMessageException((Message)buildPartial);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return buildPartial;
    }
    
    public ModulesLoaded_Broadcast buildPartial() {
        final ModulesLoaded_Broadcast modulesLoaded_Broadcast = new ModulesLoaded_Broadcast((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        try {
            if ((this.bitField0_ & 0x1) == 0x1) {
                this.modules_ = (LazyStringList)new UnmodifiableLazyStringList(this.modules_);
                this.bitField0_ &= 0xFFFFFFFE;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        ModulesLoaded_Broadcast.access$5402(modulesLoaded_Broadcast, this.modules_);
        this.onBuilt();
        return modulesLoaded_Broadcast;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof ModulesLoaded_Broadcast) {
                return this.mergeFrom((ModulesLoaded_Broadcast)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ModulesLoaded_Broadcast modulesLoaded_Broadcast) {
        try {
            if (modulesLoaded_Broadcast == ModulesLoaded_Broadcast.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        Label_0092: {
            Label_0088: {
                Label_0044: {
                    try {
                        if (ModulesLoaded_Broadcast.access$5400(modulesLoaded_Broadcast).isEmpty()) {
                            break Label_0092;
                        }
                        final Builder builder = this;
                        final LazyStringList list = builder.modules_;
                        final boolean b = list.isEmpty();
                        if (b) {
                            break Label_0044;
                        }
                        break Label_0044;
                    }
                    catch (NullPointerException ex2) {
                        throw b(ex2);
                    }
                    try {
                        final Builder builder = this;
                        final LazyStringList list = builder.modules_;
                        final boolean b = list.isEmpty();
                        if (b) {
                            this.modules_ = ModulesLoaded_Broadcast.access$5400(modulesLoaded_Broadcast);
                            this.bitField0_ &= 0xFFFFFFFE;
                            break Label_0088;
                        }
                    }
                    catch (NullPointerException ex3) {
                        throw b(ex3);
                    }
                }
                this.b();
                this.modules_.addAll((Collection)ModulesLoaded_Broadcast.access$5400(modulesLoaded_Broadcast));
            }
            this.onChanged();
        }
        this.mergeUnknownFields(modulesLoaded_Broadcast.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ModulesLoaded_Broadcast modulesLoaded_Broadcast = null;
        try {
            modulesLoaded_Broadcast = (ModulesLoaded_Broadcast)ModulesLoaded_Broadcast.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            modulesLoaded_Broadcast = (ModulesLoaded_Broadcast)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (modulesLoaded_Broadcast != null) {
                    this.mergeFrom(modulesLoaded_Broadcast);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    private void b() {
        try {
            if ((this.bitField0_ & 0x1) != 0x1) {
                this.modules_ = (LazyStringList)new LazyStringArrayList(this.modules_);
                this.bitField0_ |= 0x1;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
    }
    
    public List<String> getModulesList() {
        return Collections.unmodifiableList((List<? extends String>)this.modules_);
    }
    
    public int getModulesCount() {
        return this.modules_.size();
    }
    
    public String getModules(final int n) {
        return (String)this.modules_.get(n);
    }
    
    public ByteString getModulesBytes(final int n) {
        return this.modules_.getByteString(n);
    }
    
    public Builder setModules(final int n, final String s) {
        try {
            if (s == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.b();
        this.modules_.set(n, (Object)s);
        this.onChanged();
        return this;
    }
    
    public Builder addModules(final String s) {
        try {
            if (s == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.b();
        this.modules_.add((Object)s);
        this.onChanged();
        return this;
    }
    
    public Builder addAllModules(final Iterable<String> iterable) {
        this.b();
        GeneratedMessage.Builder.addAll((Iterable)iterable, (Collection)this.modules_);
        this.onChanged();
        return this;
    }
    
    public Builder clearModules() {
        this.modules_ = LazyStringArrayList.EMPTY;
        this.bitField0_ &= 0xFFFFFFFE;
        this.onChanged();
        return this;
    }
    
    public Builder addModulesBytes(final ByteString byteString) {
        try {
            if (byteString == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.b();
        this.modules_.add(byteString);
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
