package com.sipc.msgutil.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.sipc.msgutil.entity.po.KexieRecruit;
import com.sipc.msgutil.service.MsgService;
import com.sipc.msgutil.util.SendMsgUtil;
import lombok.extern.slf4j.Slf4j;

import javax.xml.transform.sax.SAXResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class KexieRecruitUtil implements ReadListener<KexieRecruit> {
    private static Map<String, String> msgMap = new HashMap<>();

    private MsgService msgService;

    public KexieRecruitUtil(MsgService msgService) {
        this.msgService = msgService;
    }

    @Override
    public void invoke(KexieRecruit kexieRecruit, AnalysisContext analysisContext) {
        log.info("name: " + kexieRecruit.getName() + " interviewTime: " + kexieRecruit.getInterviewTime() + " interviewPlace: " + kexieRecruit.getInterviewPlace());

        msgMap.put(kexieRecruit.getPhone(), SendMsgUtil.getSendMsg(kexieRecruit));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！"+msgMap);
        System.out.println(msgMap.size());
        System.out.println(msgService.sendDiffMsg("suzuran", "9a70eeeefdb14695b67a6a5dea8b127b", msgMap));
    }
}
