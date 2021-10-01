package com.zb.review.acts.data;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zb.review.R;
import com.zb.review.acts.BaseActivity;
import com.zb.review.data.sqlite.DatabaseHelper;
import com.zb.review.mvp.bean.sqlite.StudentBean;
import com.zb.review.utils.P;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SqliteActivity extends BaseActivity implements View.OnClickListener {

    private Button createBtn, addBtn, readBtn, deleteBtn;
    private TextView showTv;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        createBtn = findViewById(R.id.sqlite_create_button);
        addBtn = findViewById(R.id.sqlite_add_button);
        readBtn = findViewById(R.id.sqlite_read_button);
        deleteBtn = findViewById(R.id.sqlite_delete_button);
        showTv = findViewById(R.id.sqlite_show_text);

        showTv.setMovementMethod(ScrollingMovementMethod.getInstance());

        createBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        readBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

        helper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sqlite_create_button:
                doCreate();
                break;
            case R.id.sqlite_add_button:
                doAdd();
                doRead();
                break;
            case R.id.sqlite_read_button:
                doRead();
                break;
            case R.id.sqlite_delete_button:
                doDelete();
                doRead();
                break;
        }
    }

    /** 在调用获得数据库时，如果没有该数据库，会调用helper里的onCreate建立*/
    void doCreate() {
        if(null == db) {
            db = helper.getWritableDatabase();
        }
    }
    void doAdd() {
        long startTime = System.nanoTime();
        helper.saveStudents(createStudents(), db);
        long stopTime = System.nanoTime();
        P.p("SqliteActivity  add database data used time : " + (stopTime - startTime)/1000000 + " ms");
    }
    void doRead() {
        List<StudentBean> students = helper.getAllStudentsFromDatabase(db);
        if(null != students && students.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for(int i=0; i<students.size(); i++) {
                StudentBean sb = students.get(i);
                stringBuilder.append(sb.getName());
                if(sb.getName().length() <= 2)
                    stringBuilder.append("    ");
                stringBuilder.append("\t");
                stringBuilder.append(sb.getAge());
                stringBuilder.append("\t");
                stringBuilder.append(sb.getGender());
                stringBuilder.append("\t");
                stringBuilder.append(sb.getHeight());
                stringBuilder.append("\t");
                stringBuilder.append(sb.getWeight());
                stringBuilder.append("  \n");
            }
            String result = stringBuilder.toString();
            showTv.setText(result);
        } else {
            showTv.setText("");
        }
    }

    void doDelete() {
        if(null != db)
            helper.deleteStudents(db);
    }


    private List<StudentBean> createStudents() {
        List<StudentBean> data = new ArrayList<>();
        Random random = new Random();
        for(int i=0; i<10; i++) {
            String name = getRandomName();
            int age = random.nextInt(13) + 18;
            String gender = random.nextInt(2) > 0? StudentBean.GENDER_MALE : StudentBean.GENDER_FEMALE;
            float height = random.nextInt(600) / 10f + 140f;
            float weight = random.nextInt(500) / 10f  + 40f;
            StudentBean sb = new StudentBean(name, age, gender, height, weight);
            data.add(sb);
        }
        return data;
    }

    /** 在destory中关闭数据库 */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != db)
            db.close();
    }



    private static final int UNICODE_CHINESE_START = 0x4E00;
    private static final int UNICODE_CHINESE_END = 0x9FA5;

    public static String getRandomName() {
        Random random = new Random();
        int surnameIndex = random.nextInt(Surname.length);
        char surname = Surname[surnameIndex].charAt(0);

//        int tem1 = random.nextInt(UNICODE_CHINESE_END - UNICODE_CHINESE_START + 1)
//                + UNICODE_CHINESE_START;
//        char givenName1 = (char) tem1;
//        if(random.nextInt() > 0) {
//            int tem2 = random.nextInt(UNICODE_CHINESE_END - UNICODE_CHINESE_START + 1)
//                    + UNICODE_CHINESE_START;
//            char givenName2 = (char) tem2;
//            return "" + surname + givenName1 + givenName2;
//        }

        char givenName1 = MOST_USED_CHINESE_CHARATER.charAt(random.nextInt(MOST_USED_CHINESE_CHARATER.length()));
        if(random.nextBoolean()) {
            char givenName2 = MOST_USED_CHINESE_CHARATER.charAt(random.nextInt(MOST_USED_CHINESE_CHARATER.length()));
            return "" + surname + givenName1 + givenName2;
        }
        return "" + surname + givenName1;
    }







    private static final String[] Surname={"赵","钱","孙","李","周","吴","郑","王","冯","陈","褚","卫","蒋","沈","韩","杨","朱","秦","尤","许",
            "何","吕","施","张","孔","曹","严","华","金","魏","陶","姜","戚","谢","邹","喻","柏","水","窦","章","云","苏","潘","葛","奚","范","彭","郎",
            "鲁","韦","昌","马","苗","凤","花","方","俞","任","袁","柳","酆","鲍","史","唐","费","廉","岑","薛","雷","贺","倪","汤","滕","殷",
            "罗","毕","郝","邬","安","常","乐","于","时","傅","皮","卞","齐","康","伍","余","元","卜","顾","孟","平","黄","和",
            "穆","萧","尹","姚","邵","湛","汪","祁","毛","禹","狄","米","贝","明","臧","计","伏","成","戴","谈","宋","茅","庞","熊","纪","舒",
            "屈","项","祝","董","梁","杜","阮","蓝","闵","席","季","麻","强","贾","路","娄","危","江","童","颜","郭","梅","盛","林","刁","钟",
            "徐","邱","骆","高","夏","蔡","田","樊","胡","凌","霍","虞","万","支","柯","昝","管","卢","莫","经","房","裘","缪","干","解","应",
            "宗","丁","宣","贲","邓","郁","单","杭","洪","包","诸","左","石","崔","吉","钮","龚","程","嵇","邢","滑","裴","陆","荣","翁","荀",
            "羊","于","惠","甄","曲","家","封","芮","羿","储","靳","汲","邴","糜","松","井","段","富","巫","乌","焦","巴","弓","牧","隗","山",
            "谷","车","侯","宓","蓬","全","郗","班","仰","秋","仲","伊","宫","宁","仇","栾","暴","甘","钭","厉","戎","祖","武","符","刘","景",
            "詹","束","龙","叶","幸","司","韶","郜","黎","蓟","溥","印","宿","白","怀","蒲","邰","从","鄂","索","咸","籍","赖","卓","蔺","屠",
            "蒙","池","乔","阴","郁","胥","能","苍","双","闻","莘","党","翟","谭","贡","劳","逄","姬","申","扶","堵","冉","宰","郦","雍","却",
            "璩","桑","桂","濮","牛","寿","通","边","扈","燕","冀","浦","尚","农","温","别","庄","晏","柴","瞿","阎","充","慕","连","茹","习",
            "宦","艾","鱼","容","向","古","易","慎","戈","廖","庾","终","暨","居","衡","步","都","耿","满","弘","匡","国","文","寇","广","禄",
            "阙","东","欧","殳","沃","利","蔚","越","夔","隆","师","巩","厍","聂","晁","勾","敖","融","冷","訾","辛","阚","那","简","饶","空",
            "曾","毋","沙","乜","养","鞠","须","丰","巢","关","蒯","相","查","后","荆","红","游","郏","竺","权","逯","盖","益","桓","公","仉",
            "督","岳","帅","缑","亢","况","郈","有","琴","归","海","晋","楚","闫","法","汝","鄢","涂","钦","商","牟","佘","佴","伯","赏","墨",
            "哈","谯","篁","年","爱","阳","佟","言","福","南","火","铁","迟","漆","官","冼","真","展","繁","檀","祭","密","敬","揭","舜","楼",
            "疏","冒","浑","挚","胶","随","高","皋","原","种","练","弥","仓","眭","蹇","覃","阿","门","恽","来","綦","召","仪","风","介","巨",
            "木","京","狐","郇","虎","枚","抗","达","杞","苌","折","麦","庆","过","竹","端","鲜","皇","亓","老","是","秘","畅","邝","还","宾",
            "闾","辜","纵","侴","万俟","司马","上官","欧阳","夏侯","诸葛","闻人","东方","赫连","皇甫","羊舌","尉迟","公羊","澹台","公冶","宗正",
            "濮阳","淳于","单于","太叔","申屠","公孙","仲孙","轩辕","令狐","钟离","宇文","长孙","慕容","鲜于","闾丘","司徒","司空","兀官","司寇",
            "南门","呼延","子车","颛孙","端木","巫马","公西","漆雕","车正","壤驷","公良","拓跋","夹谷","宰父","谷梁","段干","百里","东郭","微生",
            "梁丘","左丘","东门","西门","南宫","第五","公仪","公乘","太史","仲长","叔孙","屈突","尔朱","东乡","相里","胡母","司城","张廖","雍门",
            "毋丘","贺兰","綦毋","屋庐","独孤","南郭","北宫","王孙"};

    /**常用简体汉字3000个*/
    private static final String MOST_USED_CHINESE_CHARATER = "的一是在了不和有大这主中人上为们地个用工时要动国产以我到他会作来分" +
            "生对于学下级就年阶义发成部民可出能方进同行面说种过命度革而多子后自社加小机也经力线本电高量长党得实家定深法表着水理化争现" +
            "所二起政三好十战无农使性前等反体合斗路图把结第里正新开论之物从当两些还天资事队批如应形想制心样干都向变关点育重其思与间内" +
            "去因件日利相由压员气业代全组数果期导平各基或月毛然问比展那它最及外没看治提五解系林者米群头意只明四道马认次文通但条较克又" +
            "公孔领军流入接席位情运器并飞原油放立题质指建区验活众很教决特此常石强极土少已根共直团统式转别造切九你取西持总料连任志观调" +
            "七么山程百报更见必真保热委手改管处己将修支识病象几先老光专什六型具示复安带每东增则完风回南广劳轮科北打积车计给节做务被整" +
            "联步类集号列温装即毫知轴研单色坚据速防史拉世设达尔场织历花受求传口断况采精金界品判参层止边清至万确究书术状厂须离再目海交" +
            "权且儿青才证低越际八试规斯近注办布门铁需走议县兵固除般引齿千胜细影济白格效置推空配刀叶率述今选养德话查差半敌始片施响收华" +
            "觉备名红续均药标记难存测士身紧液派准斤角降维板许破述技消底床田势端感往神便贺村构照容非搞亚磨族火段算适讲按值美态黄易彪服" +
            "早班麦削信排台声该击素张密害侯草何树肥继右属市严径螺检左页抗苏显苦英快称坏移约巴材省黑武培著河帝仅针怎植京助升王眼她抓含" +
            "苗副杂普谈围食射源例致酸旧却充足短划剂宣环落首尺波承粉践府鱼随考刻靠够满夫失包住促枝局菌杆周护岩师举曲春元超负砂封换太模" +
            "贫减阳扬江析亩木言球朝医校古呢稻宋听唯输滑站另卫字鼓刚写刘微略范供阿块某功套友限项余倒卷创律雨让骨远帮初皮播优占死毒圈伟" +
            "季训控激找叫云互跟裂粮粒母练塞钢顶策双留误础吸阻故寸盾晚丝女散焊功株亲院冷彻弹错散商视艺灭版烈零室轻血倍缺厘泵察绝富城冲" +
            "喷壤简否柱李望盘磁雄似困巩益洲脱投送奴侧润盖挥距触星松送获兴独官混纪依未突架宽冬章湿偏纹吃执阀矿寨责熟稳夺硬价努翻奇甲预" +
            "职评读背协损棉侵灰虽矛厚罗泥辟告卵箱掌氧恩爱停曾溶营终纲孟钱待尽俄缩沙退陈讨奋械载胞幼哪剥迫旋征槽倒握担仍呀鲜吧卡粗介钻" +
            "逐弱脚怕盐末阴丰编印蜂急拿扩伤飞露核缘游振操央伍域甚迅辉异序免纸夜乡久隶缸夹念兰映沟乙吗儒杀汽磷艰晶插埃燃欢铁补咱芽永瓦" +
            "倾阵碳演威附牙芽永瓦斜灌欧献顺猪洋腐请透司危括脉宜笑若尾束壮暴企菜穗楚汉愈绿拖牛份染既秋遍锻玉夏疗尖殖井费州访吹荣铜沿替" +
            "滚客召旱悟刺脑措贯藏敢令隙炉壳硫煤迎铸粘探临薄旬善福纵择礼愿伏残雷延烟句纯渐耕跑泽慢栽鲁赤繁境潮横掉锥希池败船假亮谓托伙" +
            "哲怀割摆贡呈劲财仪沉炼麻罪祖息车穿货销齐鼠抽画饲龙库守筑房歌寒喜哥洗蚀废纳腹乎录镜妇恶脂庄擦险赞钟摇典柄辩竹谷卖乱虚桥奥" +
            "伯赶垂途额壁网截野遗静谋弄挂课镇妄盛耐援扎虑键归符庆聚绕摩忙舞遇索顾胶羊湖钉仁音迹碎伸灯避泛亡答勇频皇柳哈揭甘诺概宪浓岛" +
            "袭谁洪谢炮浇斑讯懂灵蛋闭孩释乳巨徒私银伊景坦累匀霉杜乐勒隔弯绩招绍胡呼痛峰零柴簧午跳居尚丁秦稍追梁折耗碱殊岗挖氏刃剧堆赫" +
            "荷胸衡勤膜篇登驻案刊秧缓凸役剪川雪链渔啦脸户洛孢勃盟买杨宗焦赛旗滤硅炭股坐蒸凝竟陷枪黎救冒暗洞犯筒您宋弧爆谬涂味津臂障褐" +
            "陆啊健尊豆拔莫抵桑坡缝警挑污冰柬嘴啥饭塑寄赵喊垫康遵牧遭幅园腔订香肉弟屋敏恢忘衣孙龄岭骗休借丹渡耳刨虎笔稀昆浪萨茶滴浅拥" +
            "穴覆伦娘吨浸袖珠雌妈紫戏塔锤震岁貌洁剖牢锋疑霸闪埔猛诉刷狠忽灾闹乔唐漏闻沈熔氯荒茎男凡抢像浆旁玻亦忠唱蒙予纷捕锁尤乘乌智" +
            "淡允叛畜俘摸锈扫毕璃宝芯爷鉴秘净蒋钙肩腾枯抛轨堂拌爸循诱祝励肯酒绳穷塘燥泡袋朗喂铝软渠颗惯贸粪综墙趋彼届墨碍启逆卸航雾冠" +
            "丙街莱贝辐肠付吉渗瑞惊顿挤秒悬姆烂森糖圣凹陶词迟蚕亿矩脊歼羽掩汗碰谱童庭蓬贴岸店怪馆挡肢胆君乏傅凌恰吴鸡盆氮铃荡汇狂偶辽" +
            "宴珊描监涉伏弃仔坯症睛窝跃串瑚饱巢辑迷诗肃谊胎宾顽钠辛阔牲估禁屑秀催炸搬坑暂埋墓腰隆堡迈慌钾魏踏旺蜜兼扭肺兄撒矮拆叉贮抬" +
            "痕彩冻丛漆详拨瓜奔腿暖脾棒湾旅潜摄朱纤览融拍愚添抱蓄稿翅蛾锐栓签牌瞧疏舍糊驱泉毁伪锯卢函掘扰淬册棱爬豪螟标授朋俗骂仓脏昌" +
            "邦欺博伐衰寻杠蜗尿幕絮蘖辨孵垄粹填丘歪鬼挺帅斥摘父狗罢炎疆肝酶恨曼蹲币返颠剩港颜酵梯楼绪淮邻御杰恒弗溉淀苯跨肿抑诸凉胚舒" +
            "胀氢搭醒逃曰竞疾韩尘寿孤督涡甜拒梅乔锡睡昂烯拧扑郊患购蝗锅蔑赖瓶租怒巧膏涌狭醇惕档燕泰胁盘竭违丽氨框舌膨骤蓝幸诚吓秩扶芬" +
            "咬牵忍椎愤迁仇滩仿绘辈拚喝驳畦番扑葡款敲邀郭妥隐s轰籽忆旦犹庸崇庙秆闸厉臣窗纺掠涝涨递葬阅堵扁钳棚鳞伴珍敦椭沃欲鼻宇甫锌皆" +
            "铲砖贼渣济筛斋梦贪哇萄铺桃蟹挝糙颈雅晒韦耻沸雇储畏霍菲徐榜囊腺茨陕抹屈宿硝昨蔬郝铬茧窄哨辆耀仲薯僚浙饰朴恐腊兽蜡惠犁嘛售" +
            "鳍敬坝烘颂叔卧纠络玩栏剑苹闯丢柏牺奎嚷宫肾笼郑叙奶芒霞朽妹茬码掀阁卑铰铵弦肤拟署淋梨迪俩撑呵申穆杯姑劝崩劣贺棕裁吐嫩凭曹" +
            "摧疫鸟镍眉梁禾臭冈陵歧幻丧迭脆怨董镀酷罐逻橡浩撤驶享锦俺佛兔姿铅堤址溃胺皱晨胃氟灿漫泄枢戴孕扣沼逼肌碗巡吊盗蚜钩汤梢挨翼" +
            "疯鞭扇冶烦悉蔓泼桌柯罩啮勾舰晋扳谴侯倡诊鸣桂奖贾朵霜萌滞蛹阐偿译稼捞棵戈诬撮洒萧奸饮涅衬镗纱瘤葛饼凶饵沾馏钼鞋姓汞枣溜疼" +
            "凑醛颌肖篡邓撞搅铡卜歇妨挽审凯轧垒箭炕浑龟账趣俭泪泊乃捉窑驾汁凿饿帽湘郎欣慎芳肪蔽绵畅盲缚焕惜仰衍廷玄泻蒲捣妙帕蛇锰棘溪" +
            "匪绒潘疲纬鸭坎盒拼荫兆熊悲捧锄奉陪玛微钨籍蚊漂糟嘉狼桶拾唉默皂吕馈酯邪孝睛屠畸峡祥蒂拜蝉艾叹淑烤骄篮伞尝吏吞雹勘萎闲佳耙" +
            "剿鳃砍冯毅骑酚咳煮披佩杏偷摊肚昔韧唇喘吵荆刑拦镁蹄瓷澳塌饥垮滋钝醋捍诡哩宏瞬缔婆扛捷刹猿葱亏阮帆纂喀邵丑郁茂糠俞泳夸砚抖" +
            "渴聪拱泌藻靶褶扯藤悄逊岘姜砾舆瘦咸焰榴涛垦媳圃胳肆仑叠攀莲债汪棍飘闷蛄蔗贷俊傲哺蝼颁蠢鲤噪膀氛洼栅凤溢炊浦橄陡胰仙柔咂呆" +
            "姐哭懈兹赋岳楔蜕嵌僵晰挠熙婚缠鬃佣吾辞抚暑遮嚣赴钒嫁磺膛辣谨鄙桩惨杉秸蝇鞘匆娟晃涕萍钛眇趁邮蛮廉熏侦浴俯圭颇赢掏帜枚酮瓣" +
            "宙谣踩奏竖鞍曝耶茄谐躺榄臼哎抄铆晓虱矢艇坞鞅履恳弥搜肛逸喉苔茁欠叭扔琴芦俱砌拢礁茫筹辱靳枕惩醉挣婶拣嫂荚膊铂昏滨誓夕扮昼" +
            "艘遥戒逢苍匈慈愁唤蕾帐掺丈瘟顷裕誉祸坛彭橘匹傍淤烷绞豫庞咒芝荀弓罚捏嗨楞仕嘻沫崖瞅帘榨墒捐恕螨汛诞赏琼贩鸿铭嘱隘驰娃瞎遣" +
            "跌挪耘悦钴魂裸薛鲢躲鳙悠碘沥嘿灶饶酬艳堪淹怠砷吁涤慰缴窜羔趟脖锭兜魔梗炒纽奈硼鼎惑栗谎袁滥亨浊埂垅匝轲遂乒踪俘怔陨噬惧颖" +
            "茅摔粳垃圾疮厅鄂讥隧睁痰镶哀劈峻尸拐拳眠蔡腋哑契翁肋砧捆哟菊笨垛谦畴膝铍猜殷咽巾赌骚挫钦乓痹嘲渍杭蕉妻壶仆耸蛙廊蛛翠鹰喻" +
            "扼蕴寇腥瞪籼咕猫况鹏钮搏溅胫萝臀鲫羞罕殿忌亭盅菇旨吻厌宰氰屏桐颚佐栖蒜卓殉搁煌橙窖眨墩躁沛翘蜘酰矫钵哗梭毂嗓禽壕凳筐耦漠" +
            "屁恭钡驴姚怖滔煽虾哼匠禄稚蚁窃咐茵坊裤勿熬狱熄荐镰柑屯醚耿髓戊腕愉蕨眶煎盈慨晕盼勉虏釉皿瘀昭蝈嗽讽秃谚畔疽冕宵窍峪槐癌敷" +
            "岂侮携脓卿丸柜碾咀烃怜蝽傻椰逮猎崎淆寺恼胖颊氩盯赠甩坪淘谭莎雏棺躯熹蚧懒踢爵衷仟陋撕缆晾狡庇蓖酿拓簇蚌阎雀鹿卤荸荠搂琢猾" +
            "苷祛崭硕苞逞炫厄焚铀舵耽爽稠跗邱盏廖韵豹钓奠溴枫犬猖驯侨灼翟擂嘀汹磅嚼狮爹鹅贤颅煞萤烙蛀裹骡痢巷寡碧猴栋嗯柿篷吱厩鳄蕊甸" +
            "澄闺荧黔嫌瑟玲撇敞葫硷乞蛭阜矾瞒聊琅傀儡啃澜绥豌删龚衔敛厢堕潭舶翔赔夷稗啉僻堰恋萘扒瞄韶笋蚴媒榆廊衅吼锹睦颤刽啬慧碑彝瘠" +
            "祭侣赚蝶郡叨岔坟疤螬悔譬乖巍疡禹魁掷棋憎阱坠碲卟哄彬绑腑押揉枷菱蹈汰渎愧珩贬衫宅蛴夯吭烫灸竿酱倦镦寮戳睾拴孜迄秤笛羟蜱樟" +
            "鲍蠕芍诫慕虹厦弊翰锣沪逝诈劫锂咧凋毡蓟椅毯斧绸矣";
}
