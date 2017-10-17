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

public static final class Builder extends GeneratedMessage.Builder<Builder> implements AddBreakpoint_ReqOrBuilder
{
    private int bitField0_;
    private Object file_;
    private int line_;
    private Object condition_;
    private Object symbolPattern_;
    private Object moduleName_;
    private int threadId_;
    private boolean regexp_;
    
    public static final Descriptors.Descriptor getDescriptor() {
        return Protocol.access$19500();
    }
    
    protected GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable() {
        return Protocol.access$19600().ensureFieldAccessorsInitialized((Class)AddBreakpoint_Req.class, (Class)Builder.class);
    }
    
    private Builder() {
        this.file_ = "";
        this.condition_ = "";
        this.symbolPattern_ = "";
        this.moduleName_ = "";
        this.b();
    }
    
    private Builder(final GeneratedMessage.BuilderParent builderParent) {
        super(builderParent);
        this.file_ = "";
        this.condition_ = "";
        this.symbolPattern_ = "";
        this.moduleName_ = "";
        this.b();
    }
    
    private void b() {
        if (AddBreakpoint_Req.access$20000()) {}
    }
    
    private static Builder a() {
        return new Builder();
    }
    
    public Builder clear() {
        super.clear();
        this.file_ = "";
        this.bitField0_ &= 0xFFFFFFFE;
        this.line_ = 0;
        this.bitField0_ &= 0xFFFFFFFD;
        this.condition_ = "";
        this.bitField0_ &= 0xFFFFFFFB;
        this.symbolPattern_ = "";
        this.bitField0_ &= 0xFFFFFFF7;
        this.moduleName_ = "";
        this.bitField0_ &= 0xFFFFFFEF;
        this.threadId_ = 0;
        this.bitField0_ &= 0xFFFFFFDF;
        this.regexp_ = false;
        this.bitField0_ &= 0xFFFFFFBF;
        return this;
    }
    
    public Builder clone() {
        return a().mergeFrom(this.buildPartial());
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
        return Protocol.access$19500();
    }
    
    public AddBreakpoint_Req getDefaultInstanceForType() {
        return AddBreakpoint_Req.getDefaultInstance();
    }
    
    public AddBreakpoint_Req build() {
        final AddBreakpoint_Req buildPartial = this.buildPartial();
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
    
    public AddBreakpoint_Req buildPartial() {
        final AddBreakpoint_Req addBreakpoint_Req = new AddBreakpoint_Req((GeneratedMessage.Builder)this);
        final int bitField0_ = this.bitField0_;
        int n = 0;
        if ((bitField0_ & 0x1) == 0x1) {
            n |= 0x1;
        }
        AddBreakpoint_Req.access$20202(addBreakpoint_Req, this.file_);
        if ((bitField0_ & 0x2) == 0x2) {
            n |= 0x2;
        }
        AddBreakpoint_Req.access$20302(addBreakpoint_Req, this.line_);
        if ((bitField0_ & 0x4) == 0x4) {
            n |= 0x4;
        }
        AddBreakpoint_Req.access$20402(addBreakpoint_Req, this.condition_);
        if ((bitField0_ & 0x8) == 0x8) {
            n |= 0x8;
        }
        AddBreakpoint_Req.access$20502(addBreakpoint_Req, this.symbolPattern_);
        if ((bitField0_ & 0x10) == 0x10) {
            n |= 0x10;
        }
        AddBreakpoint_Req.access$20602(addBreakpoint_Req, this.moduleName_);
        if ((bitField0_ & 0x20) == 0x20) {
            n |= 0x20;
        }
        AddBreakpoint_Req.access$20702(addBreakpoint_Req, this.threadId_);
        if ((bitField0_ & 0x40) == 0x40) {
            n |= 0x40;
        }
        AddBreakpoint_Req.access$20802(addBreakpoint_Req, this.regexp_);
        AddBreakpoint_Req.access$20902(addBreakpoint_Req, n);
        this.onBuilt();
        return addBreakpoint_Req;
    }
    
    public Builder mergeFrom(final Message message) {
        try {
            if (message instanceof AddBreakpoint_Req) {
                return this.mergeFrom((AddBreakpoint_Req)message);
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        super.mergeFrom(message);
        return this;
    }
    
    public Builder mergeFrom(final AddBreakpoint_Req addBreakpoint_Req) {
        try {
            if (addBreakpoint_Req == AddBreakpoint_Req.getDefaultInstance()) {
                return this;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        try {
            if (addBreakpoint_Req.hasFile()) {
                this.bitField0_ |= 0x1;
                this.file_ = AddBreakpoint_Req.access$20200(addBreakpoint_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex2) {
            throw b(ex2);
        }
        try {
            if (addBreakpoint_Req.hasLine()) {
                this.setLine(addBreakpoint_Req.getLine());
            }
        }
        catch (NullPointerException ex3) {
            throw b(ex3);
        }
        try {
            if (addBreakpoint_Req.hasCondition()) {
                this.bitField0_ |= 0x4;
                this.condition_ = AddBreakpoint_Req.access$20400(addBreakpoint_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex4) {
            throw b(ex4);
        }
        try {
            if (addBreakpoint_Req.hasSymbolPattern()) {
                this.bitField0_ |= 0x8;
                this.symbolPattern_ = AddBreakpoint_Req.access$20500(addBreakpoint_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex5) {
            throw b(ex5);
        }
        try {
            if (addBreakpoint_Req.hasModuleName()) {
                this.bitField0_ |= 0x10;
                this.moduleName_ = AddBreakpoint_Req.access$20600(addBreakpoint_Req);
                this.onChanged();
            }
        }
        catch (NullPointerException ex6) {
            throw b(ex6);
        }
        try {
            if (addBreakpoint_Req.hasThreadId()) {
                this.setThreadId(addBreakpoint_Req.getThreadId());
            }
        }
        catch (NullPointerException ex7) {
            throw b(ex7);
        }
        try {
            if (addBreakpoint_Req.hasRegexp()) {
                this.setRegexp(addBreakpoint_Req.getRegexp());
            }
        }
        catch (NullPointerException ex8) {
            throw b(ex8);
        }
        this.mergeUnknownFields(addBreakpoint_Req.getUnknownFields());
        return this;
    }
    
    public final boolean isInitialized() {
        return true;
    }
    
    public Builder mergeFrom(final CodedInputStream codedInputStream, final ExtensionRegistryLite extensionRegistryLite) throws IOException {
        AddBreakpoint_Req addBreakpoint_Req = null;
        try {
            addBreakpoint_Req = (AddBreakpoint_Req)AddBreakpoint_Req.PARSER.parsePartialFrom(codedInputStream, extensionRegistryLite);
        }
        catch (InvalidProtocolBufferException ex) {
            addBreakpoint_Req = (AddBreakpoint_Req)ex.getUnfinishedMessage();
            throw ex;
        }
        finally {
            try {
                if (addBreakpoint_Req != null) {
                    this.mergeFrom(addBreakpoint_Req);
                }
            }
            catch (InvalidProtocolBufferException ex2) {
                throw b((Exception)ex2);
            }
        }
        return this;
    }
    
    public boolean hasFile() {
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
    
    public String getFile() {
        final Object file_ = this.file_;
        if (!(file_ instanceof String)) {
            return (String)(this.file_ = ((ByteString)file_).toStringUtf8());
        }
        return (String)file_;
    }
    
    public ByteString getFileBytes() {
        final Object file_ = this.file_;
        if (file_ instanceof String) {
            return (ByteString)(this.file_ = ByteString.copyFromUtf8((String)file_));
        }
        return (ByteString)file_;
    }
    
    public Builder setFile(final String file_) {
        try {
            if (file_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.file_ = file_;
        this.onChanged();
        return this;
    }
    
    public Builder clearFile() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.file_ = AddBreakpoint_Req.getDefaultInstance().getFile();
        this.onChanged();
        return this;
    }
    
    public Builder setFileBytes(final ByteString file_) {
        try {
            if (file_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x1;
        this.file_ = file_;
        this.onChanged();
        return this;
    }
    
    public boolean hasLine() {
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
    
    public int getLine() {
        return this.line_;
    }
    
    public Builder setLine(final int line_) {
        this.bitField0_ |= 0x2;
        this.line_ = line_;
        this.onChanged();
        return this;
    }
    
    public Builder clearLine() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.line_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasCondition() {
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
    
    public String getCondition() {
        final Object condition_ = this.condition_;
        if (!(condition_ instanceof String)) {
            return (String)(this.condition_ = ((ByteString)condition_).toStringUtf8());
        }
        return (String)condition_;
    }
    
    public ByteString getConditionBytes() {
        final Object condition_ = this.condition_;
        if (condition_ instanceof String) {
            return (ByteString)(this.condition_ = ByteString.copyFromUtf8((String)condition_));
        }
        return (ByteString)condition_;
    }
    
    public Builder setCondition(final String condition_) {
        try {
            if (condition_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.condition_ = condition_;
        this.onChanged();
        return this;
    }
    
    public Builder clearCondition() {
        this.bitField0_ &= 0xFFFFFFFB;
        this.condition_ = AddBreakpoint_Req.getDefaultInstance().getCondition();
        this.onChanged();
        return this;
    }
    
    public Builder setConditionBytes(final ByteString condition_) {
        try {
            if (condition_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x4;
        this.condition_ = condition_;
        this.onChanged();
        return this;
    }
    
    public boolean hasSymbolPattern() {
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
    
    public String getSymbolPattern() {
        final Object symbolPattern_ = this.symbolPattern_;
        if (!(symbolPattern_ instanceof String)) {
            return (String)(this.symbolPattern_ = ((ByteString)symbolPattern_).toStringUtf8());
        }
        return (String)symbolPattern_;
    }
    
    public ByteString getSymbolPatternBytes() {
        final Object symbolPattern_ = this.symbolPattern_;
        if (symbolPattern_ instanceof String) {
            return (ByteString)(this.symbolPattern_ = ByteString.copyFromUtf8((String)symbolPattern_));
        }
        return (ByteString)symbolPattern_;
    }
    
    public Builder setSymbolPattern(final String symbolPattern_) {
        try {
            if (symbolPattern_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x8;
        this.symbolPattern_ = symbolPattern_;
        this.onChanged();
        return this;
    }
    
    public Builder clearSymbolPattern() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.symbolPattern_ = AddBreakpoint_Req.getDefaultInstance().getSymbolPattern();
        this.onChanged();
        return this;
    }
    
    public Builder setSymbolPatternBytes(final ByteString symbolPattern_) {
        try {
            if (symbolPattern_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x8;
        this.symbolPattern_ = symbolPattern_;
        this.onChanged();
        return this;
    }
    
    public boolean hasModuleName() {
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
    
    public String getModuleName() {
        final Object moduleName_ = this.moduleName_;
        if (!(moduleName_ instanceof String)) {
            return (String)(this.moduleName_ = ((ByteString)moduleName_).toStringUtf8());
        }
        return (String)moduleName_;
    }
    
    public ByteString getModuleNameBytes() {
        final Object moduleName_ = this.moduleName_;
        if (moduleName_ instanceof String) {
            return (ByteString)(this.moduleName_ = ByteString.copyFromUtf8((String)moduleName_));
        }
        return (ByteString)moduleName_;
    }
    
    public Builder setModuleName(final String moduleName_) {
        try {
            if (moduleName_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.moduleName_ = moduleName_;
        this.onChanged();
        return this;
    }
    
    public Builder clearModuleName() {
        this.bitField0_ &= 0xFFFFFFEF;
        this.moduleName_ = AddBreakpoint_Req.getDefaultInstance().getModuleName();
        this.onChanged();
        return this;
    }
    
    public Builder setModuleNameBytes(final ByteString moduleName_) {
        try {
            if (moduleName_ == null) {
                throw new NullPointerException();
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        this.bitField0_ |= 0x10;
        this.moduleName_ = moduleName_;
        this.onChanged();
        return this;
    }
    
    public boolean hasThreadId() {
        try {
            if ((this.bitField0_ & 0x20) == 0x20) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public int getThreadId() {
        return this.threadId_;
    }
    
    public Builder setThreadId(final int threadId_) {
        this.bitField0_ |= 0x20;
        this.threadId_ = threadId_;
        this.onChanged();
        return this;
    }
    
    public Builder clearThreadId() {
        this.bitField0_ &= 0xFFFFFFDF;
        this.threadId_ = 0;
        this.onChanged();
        return this;
    }
    
    public boolean hasRegexp() {
        try {
            if ((this.bitField0_ & 0x40) == 0x40) {
                return true;
            }
        }
        catch (NullPointerException ex) {
            throw b(ex);
        }
        return false;
    }
    
    public boolean getRegexp() {
        return this.regexp_;
    }
    
    public Builder setRegexp(final boolean regexp_) {
        this.bitField0_ |= 0x40;
        this.regexp_ = regexp_;
        this.onChanged();
        return this;
    }
    
    public Builder clearRegexp() {
        this.bitField0_ &= 0xFFFFFFBF;
        this.regexp_ = false;
        this.onChanged();
        return this;
    }
    
    private static Exception b(final Exception ex) {
        return ex;
    }
}
