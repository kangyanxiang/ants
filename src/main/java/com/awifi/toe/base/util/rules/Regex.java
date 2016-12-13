package com.awifi.toe.base.util.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.awifi.toe.base.util.MessageUtil;

public class Regex extends Rule {
    
    /** 正则表达式 */
    private String reg;
    /** 正则表达式 */
    private Pattern pattern;

    public Regex(String reg) {
        pattern = Pattern.compile(reg);
        this.reg = reg;
    }

    public Regex(Pattern pattern) {
        this.pattern = pattern;
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean valid() throws Exception {
        if (value != null && !value.equals("")) {
            Matcher matcher = this.pattern.matcher(value);
            if (!matcher.matches()) {
                message = MessageUtil.getMessage("rule.regex.error");
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }
}
