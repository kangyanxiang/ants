package com.awifi.toe.base.util.rules;

public class BooleanValue extends Rule {

    @Override
    public boolean valid() throws Exception {
        if (this.getValue() == null) {
            return true;
        }
        boolean pass = true;
        if (this.getValue() != null && (this.getValue().equalsIgnoreCase(Boolean.TRUE.toString()) || this.getValue().equalsIgnoreCase(Boolean.FALSE.toString()))) {
            //pass 已默认为true
        } else {
            pass = false;
        }
        return pass;

    }
}
