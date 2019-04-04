package org.wz.config.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;
import org.wz.config.annotation.Signature;
import org.wz.config.annotation.SignatureField;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

import static org.springframework.core.annotation.AnnotatedElementUtils.isAnnotated;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

/**
 * @project: userMaster
 * @package: org.wz.config.utils
 * @author: Administrator
 * @crDate: 2019/3/25 10:35
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class SignatureUtils {

    private static Logger logger = LogManager.getLogger(Signature.class.getName());

    private static final String DELIMETER = "^233^";

    private static final String NOT_FOUND = "not_found";

    /**
     * 生成所有注有 SignatureField属性 key=value的 拼接
     */
    public static String toSplice(Object object) {
        if (Objects.isNull(object)) {
            return org.apache.commons.lang.StringUtils.EMPTY;
        }
        if (isAnnotated(object.getClass(), Signature.class)) {
            Signature sg = findAnnotation(object.getClass(), Signature.class);
            switch (sg.sort()) {
                case Signature.ALPHA_SORT:
                    return alphaSignature(object);
//                case Signature.ORDER_SORT:
//                    return orderSignature(object);
                default:
                    return alphaSignature(object);
            }
        }
        return toString(object);
    }

    private static String alphaSignature(Object object) {
        StringBuilder result = new StringBuilder();
        Map<String, String> map = new TreeMap<>();
        for (Field field : getAllFields(object.getClass())) {
            if (field.isAnnotationPresent(SignatureField.class)) {
                field.setAccessible(true);
                try {
                    if (isAnnotated(field.getType(), Signature.class)) {
                        if (!Objects.isNull(field.get(object))) {
                            map.put(field.getName(), toSplice(field.get(object)));
                        }
                    } else {
                        SignatureField sgf = field.getAnnotation(SignatureField.class);
                        if (org.apache.commons.lang.StringUtils.isNotEmpty(sgf.customValue()) || !Objects.isNull(field.get(object))) {
                            map.put(org.apache.commons.lang.StringUtils.isNotBlank(sgf.customName()) ? sgf.customName() : field.getName()
                                    , StringUtils.isNotEmpty(sgf.customValue()) ? sgf.customValue() : toString(field.get(object)));
                        }
                    }
                } catch (Exception e) {
                   logger.error("签名拼接(alphaSignature)异常", e);
                }
            }
        }

        for (Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> entry = iterator.next();
            result.append(entry.getKey()).append("=").append(entry.getValue());
            if (iterator.hasNext()) {
                result.append(DELIMETER);
            }
        }
        return result.toString();
    }

    /**
     * 针对array, collection, simple property, map做处理
     */
    private static String toString(Object object) {
        Class<?> type = object.getClass();
        if (BeanUtils.isSimpleProperty(type)) {
            return object.toString();
        }
        if (type.isArray()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Array.getLength(object); ++i) {
                sb.append(toSplice(Array.get(object, i)));
            }
            return sb.toString();
        }
        if (ClassUtils.isAssignable(Collection.class, type)) {
            StringBuilder sb = new StringBuilder();
            for (Iterator<?> iterator = ((Collection<?>) object).iterator(); iterator.hasNext(); ) {
                sb.append(toSplice(iterator.next()));
                if (iterator.hasNext()) {
                    sb.append(DELIMETER);
                }
            }
            return sb.toString();
        }
        if (ClassUtils.isAssignable(Map.class, type)) {
            StringBuilder sb = new StringBuilder();
            for (Iterator<? extends Map.Entry<String, ?>> iterator = ((Map<String, ?>) object).entrySet().iterator(); iterator.hasNext(); ) {
                Map.Entry<String, ?> entry = iterator.next();
                if (Objects.isNull(entry.getValue())) {
                    continue;
                }
                sb.append(entry.getKey()).append("=").append(toSplice(entry.getValue()));
                if (iterator.hasNext()) {
                    sb.append(DELIMETER);
                }
            }
            return sb.toString();
        }
        return NOT_FOUND;
    }

    private static Field[] getAllFields(Class<?> clz){
        return clz.getDeclaredFields();
    }
}
