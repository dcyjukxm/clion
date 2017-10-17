// 
// Decompiled by Procyon v0.5.30
// 

package com.jetbrains.cidr.execution.debugger.backend.lldb.auto_generated;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.AbstractMessage;
import com.google.protobuf.ByteString;
import java.io.IOException;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.Message;
import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessage;

public static final class Builder extends GeneratedMessage.Builder<Builder> implements ThreadStopReasonInfoOrBuilder
{
    private int bitField0_;
    private ThreadStopReason stopReason_;
    private Object stopDescription_;
    private int signal_;
    private Object signalName_;
    private int codepointId_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Model.access$3300();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Model.access$3400().ensureFieldAccessorsInitialized((Class)ThreadStopReasonInfo.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
        this.stopDescription_ = "";
        this.signalName_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
        this.stopDescription_ = "";
        this.signalName_ = "";
        this.b();
    }
    
    private void b() {
        if (ThreadStopReasonInfo.access$3800()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
        this.bitField0_ &= 0xFFFFFFFE;
        this.stopDescription_ = "";
        this.bitField0_ &= 0xFFFFFFFD;
        this.signal_ = 0;
        this.bitField0_ &= 0xFFFFFFFB;
        this.signalName_ = "";
        this.bitField0_ &= 0xFFFFFFF7;
        this.codepointId_ = 0;
        this.bitField0_ &= 0xFFFFFFEF;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Model.access$3300();
    }
    
    public ThreadStopReasonInfo getDefaultInstanceForType() {
        return ThreadStopReasonInfo.getDefaultInstance();
    }
    
    public ThreadStopReasonInfo build() {
        final ThreadStopReasonInfo buildPartial = this.buildPartial();
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
    
    public ThreadStopReasonInfo buildPartial() {
        final ThreadStopReasonInfo threadStopReasonInfo = new ThreadStopReasonInfo((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        ThreadStopReasonInfo.access$4002(threadStopReasonInfo, this.stopReason_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        ThreadStopReasonInfo.access$4102(threadStopReasonInfo, this.stopDescription_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        ThreadStopReasonInfo.access$4202(threadStopReasonInfo, this.signal_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        ThreadStopReasonInfo.access$4302(threadStopReasonInfo, this.signalName_);
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x10;
        }
        ThreadStopReasonInfo.access$4402(threadStopReasonInfo, this.codepointId_);
        ThreadStopReasonInfo.access$4502(threadStopReasonInfo, n);
        this.onBuilt();
        return threadStopReasonInfo;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof ThreadStopReasonInfo) {
                return this.mergeFrom((ThreadStopReasonInfo)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final ThreadStopReasonInfo threadStopReasonInfo) {
        try {
            if (threadStopReasonInfo == ThreadStopReasonInfo.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (threadStopReasonInfo.hasStopReason()) {
                this.setStopReason(threadStopReasonInfo.getStopReason());
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (threadStopReasonInfo.hasStopDescription()) {
                this.bitField0_ |= 0x2;
                this.stopDescription_ = ThreadStopReasonInfo.access$4100(threadStopReasonInfo);
                this.onChanged();
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (threadStopReasonInfo.hasSignal()) {
                this.setSignal(threadStopReasonInfo.getSignal());
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (threadStopReasonInfo.hasSignalName()) {
                this.bitField0_ |= 0x8;
                this.signalName_ = ThreadStopReasonInfo.access$4300(threadStopReasonInfo);
                this.onChanged();
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        try {
            if (threadStopReasonInfo.hasCodepointId()) {
                this.setCodepointId(threadStopReasonInfo.getCodepointId());
            }
        }
        catch (NullPointerException ex6) {
            throw b(ex6);
        }
        this.mergeUnknownFields(threadStopReasonInfo.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        try {
            if (!this.hasStopReason()) {
                return false;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (!this.hasStopDescription()) {
                return false;
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        ThreadStopReasonInfo threadStopReasonInfo = null;
        try {
            threadStopReasonInfo = (ThreadStopReasonInfo)ThreadStopReasonInfo.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            threadStopReasonInfo = (ThreadStopReasonInfo)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (threadStopReasonInfo != null) {
                    this.mergeFrom(threadStopReasonInfo);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasStopReason() {
        try {
            if ((this.bitField0_ & 0x1) == 0x1) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public ThreadStopReason getStopReason() {
        return this.stopReason_;
    }
    
    public Builder setStopReason(final ThreadStopReason stopReason_) {
        try {
            if (stopReason_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.stopReason_ = stopReason_;
        this.onChanged();
        return this;
    }
    
    public Builder clearStopReason() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.stopReason_ = ThreadStopReason.ThreadStopReasonInvalid;
        this.onChanged();
        return this;
    }
    
    public boolean hasStopDescription() {
        try {
            if ((this.bitField0_ & 0x2) == 0x2) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getStopDescription() {
        final Object stopDescription_ = this.stopDescription_;
        if (!(stopDescription_ instanceof String)) {
            return (String)(this.stopDescription_ = ((ByteString)stopDescription_).toStringUtf8());
        }
        return (String)stopDescription_;
    }
    
    public ByteString getStopDescriptionBytes() {
        final Object stopDescription_ = this.stopDescription_;
        if (stopDescription_ instanceof String) {
            return (ByteString)(this.stopDescription_ = ByteString.copyFromUtf8((String)stopDescription_));
        }
        return (ByteString)stopDescription_;
    }
    
    public Builder setStopDescription(final String stopDescription_) {
        try {
            if (stopDescription_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.stopDescription_ = stopDescription_;
        this.onChanged();
        return this;
    }
    
    public Builder clearStopDescription() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.stopDescription_ = ThreadStopReasonInfo.getDefaultInstance().getStopDescription();
        this.onChanged();
        return this;
    }
    
    public Builder setStopDescriptionBytes(final ByteString stopDescription_) {
        try {
            if (stopDescription_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x2;
        this.stopDescription_ = stopDescription_;
        this.onChanged();
        return this;
    }
    
    public boolean hasSignal() {
        try {
            if ((this.bitField0_ & 0x4) == 0x4) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getSignal() {
        return this.signal_;
    }
    
    public Builder setSignal(final int signal_) {
        this.bitField0_ |= 0x4;
        this.signal_ = signal_;
        this.onChanged();
        return this;
    }
    
    public Builder clearSignal() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.signal_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasSignalName() {
        try {
            if ((this.bitField0_ & 0x8) == 0x8) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public String getSignalName() {
        final Object signalName_ = this.signalName_;
        if (!(signalName_ instanceof String)) {
            return (String)(this.signalName_ = ((ByteString)signalName_).toStringUtf8());
        }
        return (String)signalName_;
    }
    
    public ByteString getSignalNameBytes() {
        final Object signalName_ = this.signalName_;
        if (signalName_ instanceof String) {
            return (ByteString)(this.signalName_ = ByteString.copyFromUtf8((String)signalName_));
        }
        return (ByteString)signalName_;
    }
    
    public Builder setSignalName(final String signalName_) {
        try {
            if (signalName_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x8;
        this.signalName_ = signalName_;
        this.onChanged();
        return this;
    }
    
    public Builder clearSignalName() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.signalName_ = ThreadStopReasonInfo.getDefaultInstance().getSignalName();
        this.onChanged();
        return this;
    }
    
    public Builder setSignalNameBytes(final ByteString signalName_) {
        try {
            if (signalName_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x8;
        this.signalName_ = signalName_;
        this.onChanged();
        return this;
    }
    
    public boolean hasCodepointId() {
        try {
            if ((this.bitField0_ & 0x10) == 0x10) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getCodepointId() {
        return this.codepointId_;
    }
    
    public Builder setCodepointId(final int codepointId_) {
        this.bitField0_ |= 0x10;
        this.codepointId_ = codepointId_;
        this.onChanged();
        return this;
    }
    
    public Builder clearCodepointId() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.codepointId_ = 0;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
