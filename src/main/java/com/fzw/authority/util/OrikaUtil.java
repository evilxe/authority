package com.fzw.authority.util;

import static ma.glasnost.orika.metadata.TypeFactory.typeOf;
import static ma.glasnost.orika.metadata.TypeFactory.valueOf;
import static org.apache.commons.collections.MapUtils.isNotEmpty;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;


import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Type;

@Slf4j
@UtilityClass
public final class OrikaUtil {

    /** mapperFactory **/
    private final static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    static {
        mapperFactory.getConverterFactory().registerConverter(new UtilDateConverter());
        mapperFactory.getConverterFactory().registerConverter(new BigDecimalConverter());
    }

    /**
     * 获取实例
     * @return
     */
    public static MapperFactory getInstance() {
        return mapperFactory;
    }

    /**
     * copy List
     * @param source
     * @param target
     * @return
     */
    public static <T> List<T> mapList(Iterable<?> source, Class<T> targetClass) {
        return getMapperFacede(typeOf(source), valueOf(targetClass)).mapAsList(source, targetClass);
    }

    /**
     * copy
     * @param source
     * @param target
     * @return
     */
    public static <T> T map(Object source, Class<T> targetClass) {
        return getMapperFacede(typeOf(source), valueOf(targetClass)).map(source, targetClass);
    }

    /**
     * copy
     * @param source
     * @param target
     * @param fieldMap 特别映射
     * @return
     */
    public static <T> T map(Object source, Class<T> targetClass, Map<String, String> fieldMap) {
        return getMapperFacede(typeOf(source), valueOf(targetClass), fieldMap).map(source, targetClass);
    }

    /**
     * copy
     * @param source
     * @param target
     * @return
     */
    public static <T> T map(Object source, T target) {
        getMapperFacede(typeOf(source), typeOf(target)).map(source, target);
        return target;
    }

    /**
     * copy
     * @param source
     * @param target
     * @param fieldMap 特别映射
     * @return
     */
    public static <T> T map(Object source, T target, Map<String, String> fieldMap) {
        getMapperFacede(typeOf(source), typeOf(target), fieldMap).map(source, target);
        return target;
    }

    /**
     * 获得MapperFacede
     */
    private static <T> MapperFacade getMapperFacede(Type<?> sourceType, Type<?> targetType) {
        return mapperFactory.getMapperFacade();
    }

    /**
     * 处理特殊映射
     * @param sourceType
     * @param targetType
     * @param fieldMap 字段映射
     * @return
     */
    private static <S, T> MapperFacade getMapperFacede(Type<S> sourceType, Type<T> targetType,
                                                       Map<String, String> fieldMap) {

        // 特殊字段映射处理
        if (isNotEmpty(fieldMap)) {
            final ClassMapBuilder<S, T> classMapBuilder = mapperFactory.classMap(sourceType, targetType);
            for (final Entry<String, String> entry : fieldMap.entrySet()) {
                classMapBuilder.field(entry.getKey(), entry.getValue());
            }
            classMapBuilder.byDefault().register();
            return mapperFactory.getMapperFacade();
        } else {
            return getMapperFacede(sourceType, targetType);
        }
    }


    public static class UtilDateConverter extends BidirectionalConverter<Date, String> {

        public static final String         DEFAULT_DATEFORMAT       = "yyyy-MM-dd hh:mm:ss";

        public static final FastDateFormat DEFAULT_FAST_DATE_FORMAT = FastDateFormat.getInstance(DEFAULT_DATEFORMAT);

        /**
         * @param source
         * @param destinationType
         * @return
         */
        @Override
        public String convertTo(Date source, Type<String> destinationType) {
            return source != null ? DEFAULT_FAST_DATE_FORMAT.format(source) : null;
        }

        /**
         * @param source
         * @param destinationType
         * @return
         */
        @Override
        public Date convertFrom(String source, Type<Date> destinationType) {
            try {
                return DateUtils.parseDate(source, new String[] { DEFAULT_DATEFORMAT });
            } catch (final ParseException e) {
                log.error("[OrikaUtil] Exception ", e);
                throw new RuntimeException(e);
            }
        }

    }

    public static class BigDecimalConverter extends BidirectionalConverter<BigDecimal, String> {

        /**
         * @param source
         * @param destinationType
         * @return
         */
        @Override
        public String convertTo(BigDecimal source, Type<String> destinationType) {
            return source != null ? source.toString() : null;
        }

        /**
         * @param source
         * @param destinationType
         * @return
         */
        @Override
        public BigDecimal convertFrom(String source, Type<BigDecimal> destinationType) {
            return new BigDecimal(source);
        }

    }

}
