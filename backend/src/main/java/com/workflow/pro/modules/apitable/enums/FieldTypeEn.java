package com.workflow.pro.modules.apitable.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FieldTypeEn {
    NOT_SUPPORT(0, "NotSupport"),

    TEXT(1, "Text"),

    NUMBER(2, "Number"),

    SINGLE_SELECT(3, "SingleSelect"),

    MULTI_SELECT(4, "MultiSelect"),

    DATETIME(5, "DateTime"),

    ATTACHMENT(6, "Attachment"),

    LINK(7, "Link"),

    URL(8, "URL"),

    EMAIL(9, "Email"),

    PHONE(10, "Phone"),

    CHECKBOX(11, "Checkbox"),

    RATING(12, "Rating"),

    MEMBER(13, "Member"),

    LOOKUP(14, "LookUp"),

    ROLLUP(15, "RollUp"),

    FORMULA(16, "Formula"),

    CURRENCY(17, "Currency"),

    PERCENT(18, "Percent"),

    SINGLE_TEXT(19, "SingleText"),

    AUTO_NUMBER(20, "AutoNumber"),

    CREATED_TIME(21, "CreatedTime"),
    CREATED_TIME1(21, "CreatedTime"),

    LAST_MODIFIED_TIME(22, "LastModifiedTime"),

    CREATED_BY(23, "CreatedBy"),

    LAST_MODIFIED_BY(24, "LastModifiedBy"),

    OPTION_STATUS(26, "OptionStatus"),
    DISCUSS(27, "discuss"),
    InterviewStatus(30, "interview_status"),
    InviteStatus(31, "invite_status"),
    Interviewer(32, "interviewer");


    private final int type;

    private final String pattern;

    public static String getPatternNu(int type) {
        for (FieldTypeEn format : FieldTypeEn.values()) {
            if (format.getType() == type) {
                return format.getPattern();
            }
        }
        return NOT_SUPPORT.getPattern();
    }

    public static int getPatternStr(String pattern) {
        for (FieldTypeEn format : FieldTypeEn.values()) {
            if (Objects.equals(format.getPattern(), pattern)) {
                return format.getType();
            }
        }
        return NOT_SUPPORT.getType();
    }
}
