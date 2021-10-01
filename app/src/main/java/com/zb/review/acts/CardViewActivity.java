package com.zb.review.acts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.os.Handler;

import com.zb.review.R;
import com.zb.review.adapter.CardAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CardViewActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private CardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        

        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
//        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter = new CardAdapter();
        recyclerView.setAdapter(adapter);

//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                ((StaggeredGridLayoutManager)recyclerView.getLayoutManager()).invalidateSpanAssignments();
//            }
//        });

        List<String> data = new ArrayList<>();
        for(int i=0; i<100; i++) {
            switch (i % 5) {
                case 0:
                    //500 x 528
                    data.add("https://img1.baidu.com/it/u=558332831,4064459262&fm=26&fmt=auto&gp=0.jpg");
                    break;
                case 1:
                    //500 x 500
                    data.add("https://img2.baidu.com/it/u=4048355283,3954849849&fm=26&fmt=auto&gp=0.jpg");
                    break;
                case 2:
                    //500 x 500
                    data.add("https://img2.baidu.com/it/u=23556030,3880179134&fm=26&fmt=auto&gp=0.jpg");
                    break;
                case 3:
                    //1280 * 800
                    data.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.v3wall.com" +
                            "%2Fwallpaper%2F1280_800%2F1301%2F1280_800_20130110065136424279.jpg&refer" +
                            "=http%3A%2F%2Fwww.v3wall.com&app=2002&size=f9999,10000&q=a80&n=0&g=" +
                            "0n&fmt=jpeg?sec=1630769189&t=7b827f141f416265cce61b3e3bde0008");
                    break;
                case 4:
                    //683 * 1024
                    data.add("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic31.nipic.com%2F20130731%2F7447430_114226342000_2.jpg&refer=http%3A%2F%2Fpic31.nipic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1630769189&t=73fc4eb04f53f143bd4789cf645064eb");
                    break;
            }
        }

        adapter.setData(data);

    }
}