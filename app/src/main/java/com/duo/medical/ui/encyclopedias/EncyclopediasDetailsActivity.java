package com.duo.medical.ui.encyclopedias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.duo.medical.R;

public class EncyclopediasDetailsActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encyclopedias_details);

        getSupportActionBar().hide();

        webView=findViewById(R.id.wv_news_details);
        WebSettings webSettings=webView.getSettings();

        webSettings.setTextZoom(300);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 得到 URL 可以传给应用中的某个 WebView 页面加载显示
                return true;
            }
        });
        webView.loadDataWithBaseURL(null,HtmlFormat.getNewContent(initData()),"text/html","UTF-8",null);
        webSettings.setDisplayZoomControls(true);
    }
    public String initData(){
        String htmlCode="<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">4月1日，在中国政府援助柬埔寨第二批新冠疫苗抵柬之际，王文天大使接受当地主流媒体巴戎电视台专访，就中国援柬疫苗和两国抗疫合作等回答提问，全文如下:<span class=\"bjh-br\"></span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_mediaWrap_213jB\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<div class=\"index-module_contentImg_JmmC0\">\n" +
                "\t\t<img src=\"https://pics2.baidu.com/feed/adaf2edda3cc7cd93529dd37a11c6c37b90e91fa.jpeg?token=5bf6a6b671e0011431dadb87ef5267f5\" width=\"640\" class=\"index-module_large_1mscr\" style=\"width:599px;\" />\n" +
                "\t</div>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">一、请问阁下如何看待柬政府的抗疫举措和疫苗接种进程？</span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">作为一个外交官我其实不应该对柬埔寨的内部事务评头论足，但是既然你问到了，我就谈几点个人感受：</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">首先，</span>我觉得在洪森首相的坚强领导下，柬埔寨的抗疫工作做得非常好，柬埔寨是国际社会公认的疫情防控综合成绩最好的国家之一。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">第二，</span>柬埔寨的防疫政策始终坚持“人民至上”的原则。为了减轻民众负担，政府出台了免费隔离、免费治疗、免费接种疫苗的政策，而且还为受疫情影响的家庭发放生活补助金，全力以赴救治确诊患者。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">第三,</span>柬埔寨十分重视抗疫国际合作。柬高度重视与世卫组织以及中国等友好国家的抗疫合作。疫情初期，柬埔寨向中国等友好国家捐赠了大量抗疫物资。洪森首相还批准无处停靠的外国邮轮停靠西哈努克港，体现了高尚的国际人道主义精神。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">虽然目前柬埔寨疫情出现了一些反弹，但总体可控，相信在洪森首相的领导下，柬埔寨一定能打赢这场疫情阻击战。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">柬埔寨的疫苗接种工作开展的也非常顺利。柬是东盟最早接种新冠疫苗的国家之一。为了有序开展接种工作，政府成立了疫苗采购委员会和疫苗接种委员会。一方面积极争取更多疫苗，另一方面按照优先顺序积极开展接种工作。我们相信在柬政府的有力协调和民众的积极配合下，一定能早日实现政府制定的接种目标。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">二、中方通过援助物资和派遣专家组等方式积极帮助柬方抗疫，并在近期最先向柬提供疫苗援助。请问中国政府尽心尽力帮助柬方的原因是什么？为什么最先向柬提供疫苗援助？</span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">答：</span>中国和柬埔寨是铁杆朋友和命运共同体，一向相互支持，相互帮助。新冠肺炎疫情发生以来，中柬两国人民同舟共济，守望相助，为抗疫国际合作树立了典范。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">在中国抗疫最困难的时候，西哈莫尼国王和莫尼列太后两位陛下慷慨解囊，洪森首相冒着大雪逆行访华，武汉收到的第一批外国援助抗疫物资就来自柬埔寨。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">柬发生疫情后，中方投桃报李，政府和军队分别向柬派出本地区和全球范围内第一支抗疫医疗专家组，并提供大量抗疫物资；中国疫苗有条件上市后，中方最先向柬埔寨提供疫苗援助。在我看来，这都是两国的“铁杆”情谊和命运共同体的应有之义。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">三、2020年2月5日洪森首相在中方遭受其他国家异样眼光之时赴华访问。是否由于那次访问使得中国政府和人民深受感动，成为中国现在帮助柬埔寨的重要原因之一？</span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">答：</span>洪森首相是中国出现疫情后第一位访华的外国政府首脑。当天，北京正下着雪，洪森首相走下飞机后，同前来接机的王毅国务委员兼外长紧紧拥抱，这个画面温暖了无数中国人的心。中国网友高度评价洪森首相此访，称他为“最帅逆行者”。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">洪森首相此访以实际行动表明柬人民对中方抗击疫情的坚定支持，同时也生动诠释了“患难见真情”这一中柬命运共同体的核心要义，必将载入中柬友好的史册。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_mediaWrap_213jB\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<div class=\"index-module_contentImg_JmmC0\">\n" +
                "\t\t<img src=\"https://pics6.baidu.com/feed/80cb39dbb6fd526638801a193f05da23d407363d.jpeg?token=0fda19ce197c18fe944331fe33d40497\" width=\"640\" class=\"index-module_large_1mscr\" style=\"width:599px;\" />\n" +
                "\t</div>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">四、中方援助疫苗得到广大柬埔寨人民的热烈欢迎。但疫苗也成为一些政治投机者的攻击目标，声称中国疫苗会危及生命，并质疑柬政府利用中国疫苗屠杀柬埔寨人民。大使阁下是否会担心这些谣言会影响柬埔寨民众接种中国疫苗的信心？您对此有何看法？</span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">答：</span>中国疫苗的安全性、有效性得到了广泛和充分的验证。目前，已有70多个国家相继授权使用中国疫苗。中国国内累计报告接种人数近1.2亿剂次，柬国内也有十几万人接种了中国疫苗，并未发现有严重不良反应案例。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">一些人在网上散播中国疫苗不安全的谣言，有些是为了通过耸人听闻的消息哗众取宠，博眼球；有的则是出于诋毁中国疫苗、干扰柬抗疫工作的政治目的。中国有句成语叫做“事实胜于雄辩”，中国疫苗是否安全有效，事实最终将证明给大家。关于柬埔寨政府利用中国疫苗杀害柬埔寨人民的质疑完全是胡说八道，不值一驳。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">五、阁下可能知道，洪森首相因为年龄原因宣布不接种国药疫苗。一些人曾表示，洪森首相不打中国疫苗而打阿斯利康疫苗会惹怒中国。您如何看待这一观点？</span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">答：</span>所谓洪森首相不打中国疫苗会惹怒中国的说法完全是无稽之谈。大家一定知道，洪森首相接种疫苗后，中国驻柬使馆在脸书上发帖专门表示祝贺。中国有句俗话叫做“不管白猫黑猫，能够抓住老鼠就是好猫”。因此，不论是中国疫苗还是其他疫苗，只要能帮助柬人民抵御新冠病毒侵袭，能为柬抗击新冠疫情发挥积极作用，都是可以接受的。我认为接种何种疫苗完全是个技术问题，不应该将其政治化。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">六、请问除了130万剂疫苗外，中方是否会向柬方提供其他抗疫帮助和疫苗无偿援助？</span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">答：</span>2月7日，中方援助的首批60万剂疫苗运抵金边；昨天，中方援助的第二批70万剂疫苗运抵金边，总共援助疫苗已有130万剂。我相信，未来中方将会根据柬方需要继续向柬提供疫苗援助。中方不仅将继续提供无偿援助疫苗，还将优先考虑安排柬方商采中国疫苗。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">此外，中方援建的西港核酸检测实验室即将在4月中旬竣工，相关设备已经抵柬，实验室的日检量将达到1000人次。与此同时中方将派出医疗专家来西港进行相关设备安装和人员培训。总之，中方将根据柬方的需要，继续及时提供力所能及的抗疫援助。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">七、请问阁下有什么话想跟柬埔寨人民和中国在柬公民说？</span></span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\"><span class=\"bjh-strong\" style=\"font-size:18px;font-weight:700;\">答：</span>我想告诉广大柬埔寨朋友，中国和柬埔寨是情同手足的好兄弟，肝胆相照的好朋友。只要柬埔寨的疫情一天不结束，中国对柬埔寨的抗疫援助就一天不会停止。在抗击新冠疫情的斗争中，中国人民将始终与柬埔寨人民站在一起。</span>\n" +
                "\t</p>\n" +
                "</div>\n" +
                "<div class=\"index-module_textWrap_3ygOc\" style=\"font-family:arial;background-color:#FFFFFF;\">\n" +
                "\t<p style=\"font-size:16px;color:#333333;text-align:justify;\">\n" +
                "\t\t<span class=\"bjh-p\">借这个机会，我愿告诉广大在柬的中国公民，疫情之下，党中央、祖国人民非常关心大家的身体健康。为了给海外中国公民接种疫苗，政府正在推进“春苗行动”。日前，驻柬使馆已按照柬方要求，对有意在柬接种国产疫苗的中国公民，包括港澳台同胞进行了摸底。这个名单已提交给了柬方。下一步，使馆将与柬方密切合作，争取让“春苗行动”早日在柬落地。同时也呼吁大家严格遵守柬各项防疫政策和规定，做一个文明守法有责任的中国人。</span>\n" +
                "\t</p>\n" +
                "</div>";
        return htmlCode;
    }
}
