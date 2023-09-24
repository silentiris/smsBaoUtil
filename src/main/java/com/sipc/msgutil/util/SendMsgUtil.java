package com.sipc.msgutil.util;

import com.sipc.msgutil.entity.po.KexieRecruit;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.sipc.msgutil.util.Md5Util.getMd5;

@Slf4j
public class SendMsgUtil {
    public static String sendMsg(String username, String password, List<String> numbers, String msg) {
        String httpUrl = "http://api.smsbao.com/sms?";
        StringBuilder httpArg = new StringBuilder();
        httpArg.append(httpUrl);
        StringBuilder numArg = new StringBuilder();
        for (int i = 0; i < numbers.size(); i++) {
            numArg.append(numbers.get(i));
            if (i != numbers.size() - 1) {
                numArg.append(",");
            }
        }
        httpArg.append("u=").append(username).append("&");
        httpArg.append("p=").append(getMd5(password)).append("&");
        httpArg.append("m=").append(numArg).append("&");
        httpArg.append("c=").append(URLEncoder.encode(msg, StandardCharsets.UTF_8));
        log.info("httpArg: " + httpArg);
        return HttpUtils.get(httpArg.toString());
    }

    public static String getSendMsg(KexieRecruit kexieRecruit) {
        String sb = "【SIPC】亲爱的"+kexieRecruit.getName()+"，请于2023-09-22 19:00来到"+ kexieRecruit.getTestPlace()
                +"参与科协二面笔试，座位号："+kexieRecruit.getTestPos()
                +"，并于" +kexieRecruit.getInterviewTime()
                +"来到7-115 "
                +kexieRecruit.getInterviewPlace()
                +"参与科协二面面试，如因个人原因需要调整时间请联系SIPC-！";
        return sb;
    }
}
