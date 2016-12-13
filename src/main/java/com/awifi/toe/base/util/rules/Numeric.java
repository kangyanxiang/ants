package com.awifi.toe.base.util.rules;

import org.apache.commons.lang.StringUtils;

import com.awifi.toe.base.util.MessageUtil;

public class Numeric extends Rule {

    public Numeric() {

    }

    @Override
    public boolean valid() throws Exception {
        if (this.getValue() == null || this.getValue().equals("")) {
            return true;
        } else {
            if (StringUtils.isNumeric(this.getValue())) {
                return true;
            } else {
                this.setMessage(MessageUtil.getMessage("rule.numeric.error"));
                return false;
            }
        }
    }

}
