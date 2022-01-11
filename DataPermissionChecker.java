package com.huawei.genexcloud.netwoekstructure.activity.data;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.huawei.genexcloud.netwoekstructure.bean.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限检查类
 */
public class DataPermissionChecker {

    private static final String CMCC = "移动";
    private static final String CUCC = "联通";
    private static final String CNTC = "电信";


    /**
     * 是否有对应运营商的全国权限
     * @param operator 需要匹配的运营商
     * @param users 用户权限列表
     * @return
     */
    public static boolean hasNationPermission(String operator, List<User> users) {
        return hasPermission("ALL", null, operator, users);
    }

    /**
     * 匹配用户是否有对应条件的权限
     * @param province 需要匹配的省份 不能为空
     * @param city 需要匹配的城市 可为空
     * @param operator 需要匹配的运营商 中英文名称皆可
     * @param users 用于匹配的用户权限列表
     * @return 用户是否拥有对应的权限
     */
    public static boolean hasPermission(@NonNull String province, String city,
                                        @NonNull String operator, List<User> users) {
        if(TextUtils.isEmpty(province) || TextUtils.isEmpty(operator)
                || null == users || 0 == users.size()) {
            return false;
        }
        for(User user:users) {
            // 如果用户是全国范围
            if("ALL".equals(user.getProvince())) {
                // 有全国权限，则只用判断运营商，不用判断地市
                if(containsOperator(operator, user.getOperator())) {
                    return true;
                }
            } else if(province.equals(user.getProvince())) { // 匹配到相应省份
                if("ALL".equals(user.getCity())) { // 该省份下有全部城市权限，去匹配运营商
                    if(containsOperator(operator, user.getOperator())) {
                        return true;
                    }
                } else if(!TextUtils.isEmpty(city) && city.equals(user.getCity())) {
                    // 省份下不是全部城市权限，去匹配城市
                    if(containsOperator(operator, user.getOperator())) {
                        return true;
                    }
                } else { // 未指定城市且不是省份下全部城市权限 或者 未匹配到城市 则用户没有相关权限
                    continue; // 继续检查权限
                }
            }
        }
        return false;
    }

    /**
     * 用户权限中是否包含指定运营商，该方法不单独使用
     * @param operator 指定运营商
     * @param userOperator 用户权限运营商
     * @return 用户权限中是否包含指定运营商
     */
    private static boolean containsOperator(String operator, String userOperator) {
        if(TextUtils.isEmpty(userOperator) || TextUtils.isEmpty(operator)) {
            return false;
        }
        String targetOperatorCNName = getOperatorCNName(operator);
        String userOperatorCNName = getOperatorCNName(userOperator);
        // 用户运营商权限为ALL 活着 与指定运营商相同
        return userOperatorCNName.equals(targetOperatorCNName) || "ALL".equals(userOperatorCNName);
    }

    /**
     * 获取运营商中文名
     * @param operator 运营商字母缩写 CMCC CUCC CNTC CUTC等
     * @return 对应的运营商中文名
     */
    private static String getOperatorCNName(String operator) {
        if(TextUtils.isEmpty(operator)) {
            return null;
        }
        if ("CMCC".equals(operator) || CMCC.equals(operator)) {
            return CMCC;
        } else if("CUCC".equals(operator) || "CUTC".equals(operator) || CUCC.equals(operator)) {
            return CUCC;
        } else if("CNTC".equals(operator) || "CNCC".equals(operator) || CNTC.equals(operator)) {
            return CNTC;
        } else if("ALL".equals(operator)) {
            return operator;
        } else {
            return null;
        }
    }

    /**
     * 如果用户没有全国的权限，该方法能获取用户所有用的权限所在的省
     * @param users 用户权限列表
     * @return 用户权限省份或者空
     */
    public static String getFirstProvince(List<User> users) {
        if(null == users || 0 == users.size()) {
            return null;
        }
        for(User user : users) {
            if(!TextUtils.isEmpty(user.getProvince()) && !"ALL".equals(user.getProvince())) {
                return user.getProvince();
            }
        }
        return null;
    }

    /**
     * 返回用户对应省份权限的运营商权限
     * @param users 用户权限列表
     * @param province 所在的省份
     * @return 空或者对应的运营商
     */
    public static List<String> getOperatorsByProvince(List<User> users, String province) {
        if(null == users || 0 == users.size() || TextUtils.isEmpty(province)) {
            return null;
        }
        List<String> operators = new ArrayList<>();
        for(User user : users) {
            if(province.equals(user.getProvince()) && !TextUtils.isEmpty(user.getOperator())) {
                operators.add(getOperatorCNName(user.getOperator()));
            }
        }
        return operators;
    }
}
