package com.awifi.toe.base.util.rules;

import com.awifi.toe.base.util.MessageUtil;

public class Length extends Rule {
    
    /** 最小长度 */
    private int minLength;
    /** 最大长度 */
    private int maxLength;

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public Length(int maxLength) {
        this.minLength = 0;
        this.maxLength = maxLength;
    }

    public Length(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public boolean valid() throws Exception {
        if (this.getValue() != null && !this.getValue().equals("")) {
            if (this.getValue().getBytes(charset).length < minLength
                    || this.getValue().getBytes(charset).length > maxLength) {
                message = MessageUtil.getMessage("rule.length.error");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
