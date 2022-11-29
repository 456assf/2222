package com.atguigu.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.atguigu.msmservice.service.MsmService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class MsmServiceImpl implements MsmService {

    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    //发送短信的方法
    @Override
    public boolean test(Map<String, Object> param, String phone) throws Exception {
        if(StringUtils.isEmpty(phone)) return false;

        com.aliyun.dysmsapi20170525.Client client =createClient("LTAI5t8nTTz27JpujHqgvN2G", "aVAdOdIA42ZmIJ4xX301WurhQI4Ld3");
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                .setSignName("阿里云短信测试")
                .setTemplateCode("SMS_154950909")
                .setPhoneNumbers(phone)
//                .setTemplateParam("{\"code\":\"1236\"}");
                .setTemplateParam(JSONObject.toJSONString(param));
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse response = client.sendSmsWithOptions(sendSmsRequest, runtime);

        } catch (TeaException error) {
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }

        return true;

//        DefaultProfile profile =
//                DefaultProfile.getProfile("default", "LTAI5t8nTTz27JpujHqgvN2G", "aVAdOdIA42ZmIJ4xX301WurhQI4Ld3");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        //设置相关固定的参数
//        CommonRequest request = new CommonRequest();
//        //request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//
//        //设置发送相关的参数
//        request.putQueryParameter("PhoneNumbers",phone); //手机号
//        request.putQueryParameter("SignName","阿里云短信测试"); //申请阿里云 签名名称
//        request.putQueryParameter("TemplateCode","SMS_154950909"); //申请阿里云 模板code
//        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param)); //验证码数据，转换json数据传递
//
//        try {
//            //最终发送
//            CommonResponse response = client.getCommonResponse(request);
//            boolean success = response.getHttpResponse().isSuccess();
//            return success;
//        }catch(Exception e) {
//            e.printStackTrace();
//            return false;
//        }

    }
}
