package com.awifi.toe.base.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.awifi.toe.base.util.rules.Rule;

public class ValidateUtil {

    /** */
    private Map<String, Rule[]> ruleMap = null;
    /** */
    private Map<String, String> nameMap = null;
    /** */
    private Map<String, String> valueMap = null;

    public ValidateUtil() {
        ruleMap = new HashMap<String, Rule[]>();
        nameMap = new HashMap<String, String>();
        valueMap = new HashMap<String, String>();
    }

    /**
     * 校验
     * @param key key
     * @param value value
     * @param name name
     * @return 结果
     * @throws Exception 异常
     */
    public Map<String, Object> validate(String key, String value, String name) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Rule[] rules = ruleMap.get(key);
        if (rules != null && rules.length > 0) {
            for (Rule rule : rules) {
                rule.setValue(value);
                if (!rule.valid()) {
                    resultMap.put("result", false);
                    resultMap.put("message", name + rule.getMessage());
                    break;
                }
            }
        }
        return resultMap;
    }

    /**
     * 初始化
     */
    public void init() {
        ruleMap = new HashMap<String, Rule[]>();
    }

    /**
     * 
     * @param key key
     * @param value value
     * @param name name
     * @param ruleArr ruleArr
     */
    public void add(String key, String value, String name, Rule[] ruleArr) {
        nameMap.put(key, name);
        valueMap.put(key, value);
        ruleMap.put(key, ruleArr);
    }

    /**
     * 
     * @return 结果
     * @throws Exception 异常
     */
    public Map<String, Object> validateAll() throws Exception {
        Map<String, Object> resultMap = null;
        for (Entry<String, Rule[]> entry : ruleMap.entrySet()) {
            String key = entry.getKey();
            resultMap = validate(key);
            boolean result = (Boolean) resultMap.get("result");
            if (!result) {
                resultMap.put("result", result);
                break;
            }
        }
        return resultMap;
    }

    /**
     * 
     * @return 结果
     * @throws Exception 异常
     */
    public String validateString() throws Exception {
        String message = null;
        Map<String, Object> resultMap = null;
        for (Entry<String, Rule[]> entry : ruleMap.entrySet()) {
            String key = entry.getKey();
            resultMap = validate(key);
            boolean result = (Boolean) resultMap.get("result");
            if (!result) {
                message = (String) resultMap.get("message");
                break;
            }
        }
        this.clear();
        return message;
    }

    /**
     * 
     * @param key key
     * @return  结果
     * @throws Exception 异常
     */
    public Map<String, Object> validate(String key) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("result", true);
        Rule[] rules = ruleMap.get(key);
        if (rules != null && rules.length > 0) {
            for (Rule rule : rules) {
                rule.setValue(valueMap.get(key));
                if (!rule.valid()) {
                    resultMap.put("result", false);
                    resultMap.put("message", nameMap.get(key) + rule.getMessage());
                    break;
                }
            }
        }
        return resultMap;
    }

    /**
     * 释放资源
     */
    public void clear() {
        // ruleMap.clear();
        // nameMap.clear();
        // valueMap.clear();
        ruleMap = null;
        nameMap = null;
        valueMap = null;
    }
}
