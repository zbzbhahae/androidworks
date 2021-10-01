package com.zb.jetpack.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.zb.jetpack.R
import com.zb.jetpack.adapter.StarAdapter
import com.zb.jetpack.databinding.ActivityRecyclerDataBindingBinding
import com.zb.jetpack.mvvm.bean.Star
import com.zb.jetpack.utils.P

class RecyclerDataBindingactivity : BaseActivity() {

    lateinit var binding: ActivityRecyclerDataBindingBinding
    lateinit var data: ArrayList<Star>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_data_binding)

        binding.recyclerView.adapter = StarAdapter()

        initData()
        (binding.recyclerView.adapter as StarAdapter).setData(data)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        P.p("  ${(binding.recyclerView.adapter as StarAdapter).itemCount}")


    }

    private fun initData() {
        data = ArrayList()
        data.add(Star("中文名1", "EnglishName1", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn10108%2F30%2Fw1080h1350%2F20190602%2F2670-hxvzhtf4583798.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782574&t=fa0cda4f41bdbb8c5ef958680ee43808"))
        data.add(Star("中文名2", "EnglishName2", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fq_70%2Cc_zoom%2Cw_640%2Fimages%2F20190416%2Fd7bde5362c48486ca34c35ccf73b761c.jpeg&refer=http%3A%2F%2F5b0988e595225.cdn.sohucs.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782590&t=95d2ca5faef49848c2da600066ad05a6"))
        data.add(Star("中文名3", "EnglishName3", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fs6.sinaimg.cn%2Fbmiddle%2F6dc6765dtda4d57454095%26690&refer=http%3A%2F%2Fs6.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782618&t=553f64e83e552ae9fb8326cb4902e5df"))
        data.add(Star("中文名4", "EnglishName4", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.365che.net%2Fwximg.php%3Furl%3Dhttp%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2020-03-27%2F5e7dc0c4d5a86.jpg&refer=http%3A%2F%2Fwww.365che.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782643&t=440132188c6c5139408b95efec72e422"))
        data.add(Star("中文名5", "EnglishName5", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn06%2F202%2Fw800h1002%2F20181113%2F3db4-hnstwwr3240360.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782666&t=110285217ec9b203357b9e3cfc860ef6"))
        data.add(Star("中文名6", "EnglishName6", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fgame.people.com.cn%2FNMediaFile%2F2014%2F0909%2FMAIN201409091025000445944124029.jpg&refer=http%3A%2F%2Fgame.people.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782685&t=2a429fd656ab589beb628053de8d9c33"))
        data.add(Star("中文名7", "EnglishName7", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F11972469726%2F641.jpg&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782685&t=1bd803578e6087cd617f53e2efb14cd9"))
        data.add(Star("中文名8", "EnglishName8", "https://pics7.baidu.com/feed/9345d688d43f879493e4d33db3c584f21ad53aea.jpeg?token=c81e4648daf3f54079c88b045e8bcda8"))
        data.add(Star("中文名9", "EnglishName9", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F600%2Fw600h800%2F20181213%2FEwes-hqackac0358699.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782729&t=8b7c69f980c658a9d5cb49316d74a8ea"))
        data.add(Star("中文名10", "EnglishName10", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201408%2F29%2F20140829173229_zaeva.thumb.700_0.jpeg&refer=http%3A%2F%2Fcdn.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782825&t=b54a7d0f01d1222268ac4314deca3f72"))
        data.add(Star("中文名11", "EnglishName11", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.365che.net%2Fwximg.php%3Furl%3Dhttp%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2019-03-06%2F5c7f8ea8ab4b4.jpg&refer=http%3A%2F%2Fwww.365che.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782906&t=eb553d234568d6c7b995e9e77080c2a9"))
        data.add(Star("中文名12", "EnglishName12", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201608%2F11%2F20160811111751_tJPmA.thumb.700_0.jpeg&refer=http%3A%2F%2Fb-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782906&t=ce2b7e24b56aa0b6e21d5c7dbb3fe4f1"))
        data.add(Star("中文名13", "EnglishName13", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2F1d935b3f6e2d272562f66c1f3a5580d4d89d0da381a3f-NkiEeZ_fw658&refer=http%3A%2F%2Fhbimg.b0.upaiyun.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782906&t=0fe97d05c5a100c5b2042b5dadfbf12f"))
        data.add(Star("中文名14", "EnglishName14", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F13700799236%2F641&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782974&t=1d0822230c2175c593928adde89d65e7"))
        data.add(Star("中文名15", "EnglishName15", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg9.51tietu.net%2Fpic%2F2019-091314%2Firau2yfiwhcirau2yfiwhc.jpg&refer=http%3A%2F%2Fimg9.51tietu.net&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629782996&t=288fee9e3f0f0b6d560648ee7df10cfd"))
        data.add(Star("中文名16", "EnglishName16", "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fn.sinaimg.cn%2Fsinakd20200707ac%2F535%2Fw600h735%2F20200707%2F19f0-iwasyeh7917737.jpg&refer=http%3A%2F%2Fn.sinaimg.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1629783036&t=52f800f84cf8432cad2fb35a517f104f"))
    }
}