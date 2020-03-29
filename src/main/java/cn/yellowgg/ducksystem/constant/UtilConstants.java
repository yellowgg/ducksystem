package cn.yellowgg.ducksystem.constant;

/**
 * @Description: 常量
 * 通过静态内部类更好划分
 * @Author: yellowgg
 * @Date: Created in 2020/3/26 17:36
 */
public interface UtilConstants {

    /**
     * 数字常量
     */
    class Number {
        public static final int ZERO = 0;
        public static final int ONE = 1;
        public static final int THREE = 3;
    }

    /**
     * 日期格式常量
     */
    class DateFormatStr {
        public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";
        public static final String PURE_DATE_PATTERN = "yyyyMMdd";
        public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
        public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
        public static final String EN_DATETIME_PATTERN = "yyyy/MM/dd HH:mm:ss";
        public static final String EN_DATETIME_MINUTE_PATTERN = "yyyy/MM/dd HH:mm";
        public static final String EN_DATE_MONTH_PATTERN = "yyyy/MM";
        public static final String EN_DATE_PATTERN = "yyyy/MM/dd";
    }
}
