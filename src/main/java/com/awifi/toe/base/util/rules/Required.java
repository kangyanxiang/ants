package com.awifi.toe.base.util.rules;

import com.awifi.toe.base.util.MessageUtil;

public class Required extends Rule {

    public Required() {

    }
    
    /**
     * 校验
     * @return true/false
     */
    public boolean valid() {
        if (this.getValue() == null || this.getValue().equals("")) {
            this.setMessage(MessageUtil.getMessage("rule.required.error"));
            return false;
        } else {
            return true;
        }
    }
}
