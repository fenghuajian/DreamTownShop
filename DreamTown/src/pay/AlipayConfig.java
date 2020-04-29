package pay;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101300675825";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC51aZgoC3K994m1NtYXp3xCQ9U2dWMjseFSmDu9lVBRVjxcFm/ORvYQHAZc7vYQMC385lWwroBCzG7Vb6m1IzjSVXAsgx0P2QSk/xvlX4Ef0/ZCEWAPu3PDwRe2AFTUOy0m8Qn2jBFR/lNI6Do1UxKVZOwk6sBBjwPr2h/ZpBnynNirAL/PV2pcu5oCoLzB2rUwDjff3MAE6JP+rImmCiN4UDsZdjrKW30t+iS0uuk7mrg8eFeLgflpDHdTarR2aLUKUYIlQA4qZyZ/4Jrh8Y54VxCdIruqfvOocRqaEDM9sEJ02e1zQNrksOYnZjRTtCCL3+yVD7UjnhduOHyjMjPAgMBAAECggEBAKumrAq+ie3b7CgkHwNOSkdVHgM7PuSZEbSN0mGHyZIDszc4lr1py82BH8pyu4TxstF3QiEAtvy10Vb/yZ2h4Q0kNQBLtfM+hnoelWKgTAlta1zGJiRifBjw/xF4JilGK45b/uDsyf+EjsNw8HWAZ/qVd1j33VGR57J5TGW5YBNtOqst1fMopTDvCznB8924zBdveJ49R2oqO2ZJe3S6T59ty7HcaFaJH/RWeT0mcvdwARXyz3DoJvHghw2Mfq+vAS/BqJ5+DrkzFXVVoh5hIaEYTEfPyGkVNVe8xJpeOUJi6J6F29N+/K+I1Ceao3YcRpP5ViJGEMYVFA6/pRe1vfkCgYEA5JjSMw+8TOc7ryNf9FDxjEbZdNUJSWed1aikCpPcHxeieuC/w7Kf3zi8c0HBgO3JasBGK+GJzJdALYAGBXalvK1SocFlHmPL1B3vrMaNvrvT0cwXSIOom/fhM0Qtrl6ywXdgGZY+KvxAVt22Zp9Zabe90KcjCapfcZB/h/tf7CUCgYEA0ByJmvNyf9wB2gTzthYjT000VlwdX2t7gZIPEnR6MMrUwMtqKF/BNnpsUo954U3pR/pSrvij+oKOn8UStxznLHAhlNzl0/SImcYk9M69pe3q6Ij62tjd6r9pgQT+gE+whZcVVP5rsHkkx1aCLb0n57Jk9EK/SJeMpqCeRIpFlOMCgYEAnOnxz68LeFZ5d5z2rsQpKjsv5ayedU603walo8UfIhCdLW+88l5Ys2dEpHEMnK2cxrQ49sIYTuG74f+xImrxFXR79iDEgnnKXk42SQj6H2Ta33YSvWnygS1D3mb8XGcQmRCSXL3u6p7Ao2AJZqQXQiQoHWO8/1N9tHyVrgf1LikCgYEArpTiEuzbRsuHeh/hWwUjRelcWH4Z3exKxZ2Dq1WvE7Nz2vBJ8+iwK0V0DaZy9kDnzAwAxOuslABmKT2nv5ktjSfb5AeTfcWLuoRwiDXO/p2iDMSsvF5lfAJrPWK1JWq0iLz/ljeAm6Bk7Bx0RG6ERWhwLEC6Oul3gmkHnDPqv0UCgYAi2rcx68w2uy4Pay2VtgBtk5T4c2r+p3ipxYwcEYLc8sLNeX4KJD7kdgJK2xg3rBmUAjGUcRIxLfXJNUZlXq6Tk/6zAXl9fGfmdoLPtiZUIz6lOinKvjPl8Qe/decBol0ldYND32zkzRzZS489gnCPAeMpN11nWZzo29t6R/dKnQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjYrZgVCYn4aXfdHGre+iWhPzZ3WK8/xGtser5kZYSt38HnXX5QSVIx1/6ikWy6HXrQsWf5QN5NGV86bH2ZkTwLTX3Bo7pJ1iPsiK1I/Oh5vRufIkdYhi5JZYRzmkkr+F8kXWScGjH4V9mQl2p3tfNYvluYk5DnjPCCfAVDoC1U00qtImMYfBAlHwc+2rXd6lGS6SsTKo8OepR99Ennq+d7balihtIemPsIc/MV16twa0z+BwhvkvJ23CuOrboe6GI38DSiJtflmHxqeNzfoYjlElidDMAg9jf4Ft9qsNDN6xyhNUQ6RF+tp6IIY062G1VulobqAvJojKXlOze11JmQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8088//DreamTown/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8088/DreamTown/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

