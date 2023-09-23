package com.sipc.msgutil.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MsgService {
    List<String> sendMsg(String username, String password, List<String> numbers, String msg);
    List<String> sendDiffMsg(String username, String password, Map<String,String> sendDiffMsg);
    List<String> sendDiffMsgByExcel(String username, String password, MultipartFile multipartFile);
}
