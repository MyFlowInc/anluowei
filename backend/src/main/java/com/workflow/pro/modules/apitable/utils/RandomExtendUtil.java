/*
 * APITable <https://github.com/apitable/apitable>
 * Copyright (C) 2022 APITable Ltd. <https://apitable.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.workflow.pro.modules.apitable.utils;

import java.util.Base64;

import cn.hutool.core.util.RandomUtil;

/**
 * random automatic generation extension tool class
 *
 * @author Benson Cheung
 */
public class RandomExtendUtil extends RandomUtil {

    public static final String BASE_CHAR = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public static final String BASE_CHAR_NUMBER = BASE_CHAR + BASE_NUMBER;

    /**
     * Get a random string (only numbers and uppercase and lowercase characters)
     *
     * @param length the length of the string
     * @return random string
     */
    public static String randomString(int length) {
        return randomString(BASE_CHAR_NUMBER, length);
    }

    /**
     * get a random string (only numbers and lowercase characters)
     *
     * @param length the length of the string
     * @return random string
     */
    public static String randomStringLowerCase(int length) {
        return randomString(RandomUtil.BASE_CHAR_NUMBER, length);
    }

    public static String encode(String str) {
        byte[] encodedBytes = Base64.getEncoder().encode(str.getBytes());
        return new String(encodedBytes);
    }

    // 使用 Base64 解密字符串
    public static String decode(String str) {
        byte[] decodedBytes = Base64.getDecoder().decode(str.getBytes());
        return new String(decodedBytes);
    }
}
