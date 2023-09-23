package com.sipc.msgutil.controller;

import com.sipc.msgutil.service.MsgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class MsgController {
    private MsgService msgService;
    @Autowired
    public MsgController(MsgService msgService) {
        this.msgService = msgService;
    }
    /**
     * @param username 用户名
     * @param password 密码（不需md5）
     * @param numbers  手机号列表
     * @param msg      短信内容（有模板可以直接传模板内容）
     */
    @PostMapping("/sendSameMsg")
    public List<String> sendMsg(String username, String password, @RequestParam List<String> numbers, String msg) {
        return msgService.sendMsg(username, password, numbers, msg);
    }

    /**
     *
     * @param username
     * @param password
     * @param args
     * @return {@code List<String>}
     */
    @PostMapping("/sendDiffMsg")
    public List<String> sendDiffMsg(String username, String password, @RequestParam Map<String,String> args) {
        return msgService.sendDiffMsg(username, password,args);
    }

    /**
     * @param username
     * @param password
     * @param multipartFile
     * @return {@code List<String>}
     */
    @PostMapping("/sendDiffMsgByExcel")
    public List<String> sendDiffMsgByExcel(String username, String password,@RequestParam("file") MultipartFile multipartFile) {
        return msgService.sendDiffMsgByExcel(username, password,multipartFile);
    }
}
