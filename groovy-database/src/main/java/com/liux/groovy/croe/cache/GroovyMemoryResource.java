package com.liux.groovy.croe.cache;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.core.io.AbstractResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author :liuxin
 * @version :V1.0
 * @program : demo_groovy
 * @date :Create in 2022/7/29 17:30
 * @description :用来存储编译后的脚本文件
 */
public class GroovyMemoryResource extends AbstractResource {


    private static final String DESCRIPTION = "GroovyMemoryResource";

    /**
     * 脚本字节
     */
    private final byte[] source;

    public GroovyMemoryResource(String sourceString) {
        this.source = sourceString.getBytes();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return  new ByteArrayInputStream(source);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GroovyMemoryResource that = (GroovyMemoryResource) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(source, that.source).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(source).toHashCode();
    }
}
