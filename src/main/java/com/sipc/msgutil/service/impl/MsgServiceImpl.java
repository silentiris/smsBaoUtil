package com.sipc.msgutil.service.impl;

import com.alibaba.excel.EasyExcel;
import com.sipc.msgutil.entity.po.KexieRecruit;
import com.sipc.msgutil.service.MsgService;
import com.sipc.msgutil.util.SendMsgUtil;
import com.sipc.msgutil.util.excel.KexieRecruitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MsgServiceImpl implements MsgService {

    @Override
    public List<String> sendMsg(String username, String password, List<String> numbers, String msg) {
        log.info("username: " + username + " password: " + password + " numbers: " + numbers + " msg: " + msg);
        List<String> numArgs = new ArrayList<>();
        List<String> result = new ArrayList<>();
        log.info("numbers.size(): " + numbers.size());
        for (int i = 0; i < numbers.size(); i++) {
            if (numArgs.size() == 90) { // 一次最多发99条
                result.add(SendMsgUtil.sendMsg(username, password, numArgs, msg));
                numArgs.clear();
            }
            numArgs.add(numbers.get(i));
            if (i == numbers.size() - 1  ) {
                result.add(SendMsgUtil.sendMsg(username, password, numArgs, msg));
            }
        }
        return result;
    }

    @Override
    public List<String> sendDiffMsg(String username, String password, Map<String, String> sendDiffMsg) {
        List<String> result = new ArrayList<>();
        for(Map.Entry<String,String> entry:sendDiffMsg.entrySet()){
            log.info("key: "+entry.getKey()+" value: "+entry.getValue());
            result.add(SendMsgUtil.sendMsg(username,password, new ArrayList<>() {{
                add(entry.getKey());
            }},entry.getValue()));
        }
        return result;
    }

    @Override
    public List<String> sendDiffMsgByExcel(String username, String password, MultipartFile multipartFile) {
        try {
            EasyExcel.read(multipartFile.getInputStream(),KexieRecruit.class,new KexieRecruitUtil(this,username,password)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
