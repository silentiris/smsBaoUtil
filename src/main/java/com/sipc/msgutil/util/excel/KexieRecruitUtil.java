package com.sipc.msgutil.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.sipc.msgutil.entity.po.KexieRecruit;
import com.sipc.msgutil.service.MsgService;
import com.sipc.msgutil.util.SendMsgUtil;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.xml.transform.sax.SAXResult;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Slf4j
public class KexieRecruitUtil implements ReadListener<KexieRecruit> {
    private final String username;
    private final String password;
    private static final Map<String, String> msgMap = new HashMap<>();

    private final MsgService msgService;

    public KexieRecruitUtil(MsgService msgService, String username, String password) {
        this.msgService = msgService;
        this.username = username;
        this.password = password;
    }

    @Override
    public void invoke(KexieRecruit kexieRecruit, AnalysisContext analysisContext) {
        log.info("name: " + kexieRecruit.getName() + " interviewTime: " + kexieRecruit.getInterviewTime() + " interviewPlace: " + kexieRecruit.getInterviewPlace());

        msgMap.put(kexieRecruit.getPhone(), SendMsgUtil.getSendMsg(kexieRecruit));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！"+msgMap);
        log.info("map size: "+msgMap.size());
        System.out.println(msgService.sendDiffMsg(username, password, msgMap));
    }
}
