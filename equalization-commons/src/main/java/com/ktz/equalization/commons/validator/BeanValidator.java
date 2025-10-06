package com.ktz.equalization.commons.validator;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 曾泉明
 * @date 2021/1/3 下午6:41
 */
public class BeanValidator {

    private static Validator validator;

    public static void setValidator(Validator validator) {
        BeanValidator.validator = validator;
    }

    /**
     * 调用 JSR303 的 validate 验证方法，验证失败抛出 ConstraintViolationException 异常
     * @param validator Validator 对象
     * @param o 验证实体类对象
     * @param groups 验证组
     * @throws ConstraintViolationException 验证失败异常
     */
    private static void validateWithException(Validator validator, Object o, Class<?>... groups) throws ConstraintViolationException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(o, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    /**
     * 辅助方法，转换 ConstraintViolationException 中的 Set<ConstraintViolation> 为 List<Message>
     * @param e ConstraintViolationException 异常对象
     * @return 错误信息组成的 List 集合
     */
    private static List<String> extractMessage(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        return extractMessage(constraintViolations);
    }

    /**
     * 辅助方法，转换 Set<ConstraintViolation> 为 List<Message>
     * @param constraintViolations Set<ConstraintViolation> 对象
     * @return 错误信息组成的 List 集合
     */
    private static List<String> extractMessage(Set<? extends ConstraintViolation<?>> constraintViolations) {
        List<String> errorMessages = new ArrayList<String>();
        for (ConstraintViolation<?> violation : constraintViolations) {
            errorMessages.add(violation.getMessage());
        }
        return errorMessages;
    }

    /**
     * 服务端校验
     * @param o 验证的实体类对象
     * @param groups 验证组
     * @return 验证成功返回 null，反之返回错误信息
     */
    public static String validator(Object o, Class<?>... groups) {
        try {
            validateWithException(validator, o, groups);
        } catch (ConstraintViolationException e) {
            List<String> errorMessages = extractMessage(e);
            errorMessages.add(0, "数据验证失败");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < errorMessages.size(); i++) {
                String errorMessage = errorMessages.get(i);
                if (i != 0) {
                    sb.append(String.format("%s . %s", i, errorMessage)).append(i != (errorMessages.size() - 1) ? "<br/>" : "");
                }else {
                    sb.append(errorMessage).append("<br/>");
                }
            }
            return sb.toString();
        }
        return null;
    }
}
