package com.sipc.msgutil;

import com.sipc.msgutil.service.MsgService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class MsgUtilApplicationTests {
@Autowired
private MsgService msgService;
    @Test
    void contextLoads() {
        Map<String,String> m = new HashMap<>();
        m.put("16600216791","【SIPC】亲爱的同学：感谢你能选择科技协会。请于9月17日13时12分在7-115参加考核，望准时参加。如有其他事宜请联系SIPC-赵沛文。");
        m.put("18108074809","【SIPC】亲爱的同学：感谢你能选择科技协会。请于9月17日13时24分在7-115参加考核，望准时参加。如有其他事宜请联系SIPC-赵沛文。");
        System.out.println(msgService.sendDiffMsg("suzuran", "9a70eeeefdb14695b67a6a5dea8b127b", m));
    }

}
