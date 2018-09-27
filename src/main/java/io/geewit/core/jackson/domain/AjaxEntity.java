package io.geewit.core.jackson.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 对返回前台的json的封装类
 @author gelif
 @since  2015-5-18
 */
@JsonSerialize
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@SuppressWarnings({"unused"})
public class AjaxEntity {
    protected Boolean success;
    protected Object data;
    protected String errors;

    protected AjaxEntity(Boolean success, String errors, Object data) {
        this.success = success;
        this.data = data;
        this.errors = errors;
    }

    public static AjaxEntity ok(Object data) {
        return new AjaxEntity(true, null, data);
    }

    public static AjaxEntity fail(String errors) {
        return new AjaxEntity(false, errors, null);
    }

    public Boolean getSuccess() {
        return success;
    }

    public AjaxEntity setSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public Object getData() {
        return data;
    }

    public AjaxEntity setData(Object data) {
        this.data = data;
        return this;
    }

    public String getErrors() {
        return errors;
    }

    public AjaxEntity setErrors(String errors) {
        this.errors = errors;
        return this;
    }
}
