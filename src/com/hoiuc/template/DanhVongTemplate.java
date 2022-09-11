package com.hoiuc.template;

import com.hoiuc.io.Util;

public class DanhVongTemplate {
    public static int[] idDaDanhVong = new int[]{695, 696, 697, 698, 699, 700, 701, 702, 703, 704};
    public static int[] typeNV = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static String[] nameNV = new String[]{
            "- Tiêu diệt %d/%d tinh anh không lệch quá 10 cấp độ",
            "- Tiêu diệt %d/%d thủ lĩnh không lệch quá 10 cấp độ"};

    public static int randomNVDV() {
        return Util.nextInt(DanhVongTemplate.typeNV.length);
    }

    public static int targetTask(int type) {
        return Util.nextInt(1, 2);
    }

    public static int randomDaDanhVong() {
        int percent = Util.nextInt(101);
        if (percent < 50) {
            return 695;
        } else if (percent >= 50 && percent < 75) {
            return 696;
        } else if (percent >= 75 && percent < 87) {
            return 697;
        } else if (percent >= 87 && percent < 93) {
            return 698;
        } else if (percent != 93 && percent != 94 && percent != 95) {
            if (percent != 96 && percent != 97) {
                if (percent == 98) {
                    return 701;
                } else if (percent == 99) {
                    return 702;
                } else {
                    return percent == 100 ? 703 : 695;
                }
            } else {
                return 700;
            }
        } else {
            return 699;
        }
    }
}
