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
        webSettings.setDisplayZoomControls(false);
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
    }
    public String initData(){
        String htmlCode="<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>特色美食</title>\n" +
                "<style type=\"text/css\">\n" +
                "*{margin:0;padding:0;}\n" +
                ".top{height:40px;background:#39F}\n" +
                "ul{list-style:none;}\n" +
                "li{float:left;}\n" +
                "#menu{text-decoration:none;display:block;height:40px;line-height:30px;width:110px;background-color:#39F;margin-bottom:1px;text-align:center;}\n" +
                "#menu:hover{background-color:#FF9;color:#000}\n" +
                ".main{\n" +
                "\twidth:100%;\n" +
                "\theight:2500px;\n" +
                "\tbackground-image:url(../photo/background.jpg);\n" +
                "\tbackground-repeat:no-repeat;\n" +
                "\tbackground-size:cover;\n" +
                "\tbackground-attachment:fixed\n" +
                "\t}\n" +
                ".demo,.img,.mask,.border{\n" +
                "\twidth:350px;\n" +
                "\theight:350px;\n" +
                "\tborder-radius:50%;\n" +
                "\t}\n" +
                ".demo{\n" +
                "\tposition:relative;\n" +
                "\tdisplay:block;\n" +
                "\t}\n" +
                ".img{\n" +
                "\tbackground-size:cover;\n" +
                "\tbackground-position:50% 50%;\n" +
                "\t}\n" +
                ".mask,.border{\n" +
                "\tposition:absolute;\n" +
                "\tleft: 0;\n" +
                "\ttop: 0;\n" +
                "\t}\n" +
                ".mask{\n" +
                "\ttext-align:center;\n" +
                "\tcolor:rgba(255,255,255,0);\n" +
                "\ttransition:all .5s ease-in;\n" +
                "\t}\n" +
                ".info{\n" +
                "\tmargin-top:50%;\n" +
                "\ttransfrom:translateY(-50%);\n" +
                "    }\n" +
                ".border{\n" +
                "\tborder:10px solid #309;\n" +
                "\tborder-left-color:#FF0;\n" +
                "\tborder-top-color:#FF0;\n" +
                "\tbox-sizing:border-box;\n" +
                "\ttransition:all .5s ease-in;\n" +
                "\t}\n" +
                ".demo:hover .mask{\n" +
                "\tbackground-color: rgba(0,0,0,.5);\n" +
                "\tcolor:rgba(255,255,255,1);\n" +
                "\t}\n" +
                ".demo:hover .border{\n" +
                "\ttransform:rotate(180deg);\n" +
                "\t\n" +
                "\t}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>\n" +
                "<div class=\"top\">\n" +
                "<ul>\n" +
                "<li><a id=\"menu\" href=\"yulin.html\"><b>首&nbsp;&nbsp;页</b></a></li>\n" +
                "<li><a id=\"menu\" href=\"history.html\"><b>历史地理</b></a></li>\n" +
                "<li><a id=\"menu\" href=\"scenery.html\"><b>风景名胜</b></a></li>\n" +
                "<li><a id=\"menu\" href=\"food.html\"><b>特色美食</b></a></li>\n" +
                "<li><a id=\"menu\" href=\"more.html\"><b>了解更多</b></a></li>\n" +
                "<li><a id=\"menu\" href=\"questionnaires.html\"><b>调查问卷</b></a></li>\n" +
                "</ul>\n" +
                "</div>\n" +
                "<div>\n" +
                "<img src=\"../photo/s1.jpg\" width=\"100%\" />\n" +
                "</div>\n" +
                "<div class=\"main\">\n" +
                "<br>\n" +
                "<center>\n" +
                "<table width=\"1000\" style=\"font-family:'华文琥珀';font-size:24px;color:#000;\">\n" +
                "  <tr>\n" +
                "    <td style=\"width:250px;height:250px\"><center><img src=\"../photo/yulin-meishi-1.jpg\" width=\"240px\" height=\"200px\" style=\"border-radius:30%;overflow:hidden\" title=\"牛腩粉\" /></center></td>\n" +
                "    <td>牛腩粉是著名的传统风味食品。因以调制好的熟牛腩做佐料而得名。起于民间，解放前就已出名。至今已遍及两广。 将选好的牛腩、牛筋等用沸水“飞过”捞起过冷水。中火起镬，下料把牛腩炒至收水后，配以沙姜、甘松、草果等煮30-40分钟即可。将炖好的牛腩、米粉下碗，然后把牛腩汤、骨头汤、肉丸调味下碗即成。清香、可口，富含高蛋白。</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td style=\"width:250px;height:250px\"><center>\n" +
                "      <img src=\"../photo/yulin-meishi-2.jpg\" width=\"240px\" height=\"200px\" style=\"border-radius:50%;overflow:hidden\" title=\"玉林牛巴\"/>\n" +
                "    </center></td>\n" +
                "    <td>玉林牛巴，其颜色半透明，色似咖啡，油亮，香味浓郁，咸甜适口，鲜美爽口，韧而不坚。色泽暗亮，气味醇香，肉质细而耐嚼，入口生香，令人回味。肉质细而有嚼劲，吃后满口生香，堪称地方一绝。</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td style=\"width:250px;height:250px\"><center>\n" +
                "      <img src=\"../photo/yulin-meishi-3.jpg\" width=\"240px\" height=\"200px\" style=\"border-radius:50%;overflow:hidden\" title=\"陆川猪肉\" />\n" +
                "    </center></td>\n" +
                "    <td>　陆川猪是全国八大地方优良品种之一。陆川猪肉的营养非常全面，除了蛋白质、脂肪等主要营养成分外，还含有钙、磷、铁、硫、胺素、核黄素和尼克酸等。其肉皮薄、肉嫩、肥而不腻，可加工成陆川猪扣肉、香肠、无皮五花腊肉、烧乳猪等。炸猪排、白切猪脚、脆皮扣等在宾宴上不可缺少的菜</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "  \t<td style=\"width:250px;height:250px\"><center>\n" +
                "  \t  <img src=\"../photo/yulin-meishi-5.jpg\" width=\"240px\" height=\"200px\" style=\"border-radius:30%;overflow:hidden\" title=\"玉林茶泡\" />\n" +
                "  \t</center></td>\n" +
                "    <td>玉林茶泡是一种味道清甜的泡茶甜品，又是一种经精雕细錾、耐人观赏的工艺品。玉林茶泡在宋代已有。至清代，玉林的富户人家，在嫁女时用茶泡净遍亲戚朋友，俗称“新人茶”。在新郎到女家“迎亲”或“回门”时，女家须以大型精致的茶泡相馈赠。男家也须以同样规格的茶泡，馈赠女家的“送嫁娘”。逢春节，玉林民间常以茶泡招待宾客。</td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "  \t<td style=\"width:250px;height:250px\"><center><img src=\"../photo/yuntun.jpg\" width=\"240px\" height=\"200px\" style=\"border-radius:50%;overflow:hidden\" title=\"石南云吞\"/></center></td>\n" +
                "    <td>云吞的汤底很讲究，一般都是用猪筒骨，熬制一至两个小时，直至汤色浓白鲜美浓郁。煮熟后的云吞，它的白色面皮张开如云朵，若隐若现出可爱的粉红色。在乳白色的骨头汤里大只云吞好似船仔一样浮在汤面，肉馅随时要涨爆个皮。再撒上一把葱花，散发出幽幽的清香，在热气腾腾中细细品味。趁热入口，肉质结实，吃起来爽口弹牙！这时候再加点辣椒酱，那滋味实在是爽！</td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<a href=\"#\" class=\"demo\">\n" +
                "\t<div class=\"img\" style=\"background-image:url(../photo/wine.jpg);\">\n" +
                "    </div>\n" +
                "\t<div class=\"mask\">\n" +
                "    \t<div class=\"info\">\n" +
                "        \t<h2>Life is beautiful</h2>\n" +
                "        </div>\n" +
                "\t</div>\n" +
                "    <div class=\"border\"></div>\n" +
                "</a>\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<br />\n" +
                "<table width=\"1500\" style=\"font-family:'华文琥珀';font-size:24px;color:#000;\">\n" +
                "\t<tr>\n" +
                "    \t<td style=\"width:500px;height:250px\"><center><img src=\"../photo/yulin-meishi-4.jpg\" width=\"240px\" height=\"200px\"  style=\"border-radius:50%;overflow:hidden\" title=\"容县沙田柚\" /></center></td>\n" +
                "        <td style=\"width:500px;height:250px\"><center><img src=\"../photo/bobaiguiyan.jpg\" width=\"240px\" height=\"200px\" style=\"border-radius:50%;overflow:hidden\" title=\"博白桂圆\" /></center></td>\n" +
                "        <td style=\"width:500px;height:250px\"><center><img src=\"../photo/suanliao.jpg\" width=\"240px\" height=\"200px\" style=\"border-radius:50%;overflow:hidden\" title=\"城隍酸料\" /></center></td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<table width=\"1520\" style=\"font-family:'华文琥珀';font-size:24px;color:#000\">\n" +
                "\t<tr>\n" +
                "    \t<td style=\"width:506px;height:200px\">容县沙田柚，果大形美、味甜蜜、耐贮藏；果面金黄色，果肉虾肉色，汁饱脆嫩、蜜味清甜；10月下旬成熟，极耐贮藏，果实可贮藏150 - 180天，贮后风味尤佳，有水果珍品\"天然罐头\"之美称。容县沙田柚营养丰富，有消食、化痰、止咳、润肺、醒酒等功效。</td>\n" +
                "        <td style=\"width:506px;height:200px\">桂圆就是将新鲜龙眼去壳烘干后就成了龙眼干，俗称圆肉。广西称为“桂”，因此在这些特产名称前冠以“桂”字，龙眼干称为“桂圆”，圆肉称为“桂圆肉”。上等的桂圆色似琥珀，半透明而有光泽，松软而稍带弹性，含葡萄糖和多种维生素，营养丰富。</td>\n" +
                "        <td style=\"width:506px;height:200px\">城隍酸料，是我国南方的一种传统美食小吃，有去火、降压、开胃、美容等功效。腌制方法为整个原料腌制，选料考究，风味独特，酸甜脆口。</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "</center>\n" +
                "<center>\n" +
                "<font face=\"MS Serif, New York, serif\" size=\"7\" color=\"#CC0000\">觉得美食不够？</font>\n" +
                "<br />\n" +
                "<font face=\"MS Serif, New York, serif\" size=\"+6\" color=\"#CC0000\">想<a href=\"https://www.xiangha.com/xiaochi/yulinshi/\">了解更多</a>？</font>\n" +
                "</center>\n" +
                "</div>\n" +
                "</div>\n" +
                "<div style=\"background-color:#999;position:absolute;width:100%\">\n" +
                "<br />\n" +
                "<center>\n" +
                "<a>&copy;GUET&nbsp;luohuiduo&nbsp;&nbsp;本网页的版权归作者所有&nbsp;&nbsp;网页内容仅代表个人观点</a>\n" +
                "<br />\n" +
                "<br />\n" +
                "<a>如果你有好的资源和建议</a>\n" +
                "<br />\n" +
                "<br />\n" +
                "<a>请联系我们</a>\n" +
                "<br />\n" +
                "<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话&nbsp;&nbsp;123456789</a>\n" +
                "<br />\n" +
                "<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;QQ&nbsp;&nbsp;2345678</a>\n" +
                "<br />\n" +
                "<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;微信&nbsp;&nbsp;luo2435436122324</a>\n" +
                "</center>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";
        return htmlCode;
    }
}
