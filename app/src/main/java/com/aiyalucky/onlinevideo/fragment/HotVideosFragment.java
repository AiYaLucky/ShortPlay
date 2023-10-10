package com.aiyalucky.onlinevideo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import com.aiyalucky.onlinevideo.R;
import com.aiyalucky.onlinevideo.adapter.ItemsAdapter;
import com.aiyalucky.onlinevideo.model.VideoModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: xu xiao wei
 * @Date: 2023/4/20 16:27
 * @Version 1.0
 * Created by IntelliJ IDEA.
 */
public class HotVideosFragment extends Fragment {

    private GridView rankVideo;
    private List<VideoModel> rankVideoModels = new ArrayList<>();

    public HotVideosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_video, container, false);
        rankVideo = view.findViewById(R.id.rank_grid);
        rankVideoModels = Data();
//        rankVideo.setAdapter(new ItemsAdapter(view.getContext(), rankVideoModels));

        return view;
    }

    /**
     * 临时数据
     *
     * @return
     */
    public List<VideoModel> Data() {
        List<VideoModel> items = new ArrayList<>();
        //漫威
        String imageUrl1 = "https://img2.doubanio.com/view/photo/s_ratio_poster/public/p2874034153.webp";
        String imageUrl2 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2873504366.webp";
        String imageUrl3 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2888100744.webp";
        String imageUrl4 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2874253805.webp";
        String imageUrl5 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2675102928.webp";
        //动漫
        String imageUrl6 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2557573348.webp";
        String imageUrl7 = "https://img2.doubanio.com/view/photo/s_ratio_poster/public/p456676352.webp";
        String imageUrl8 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2627847859.webp";
        String imageUrl9 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p1998028598.webp";
        String imageUrl10 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2890142218.webp";
        //科幻
        String imageUrl11 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2614988097.webp";
        String imageUrl12 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2209066019.webp";
        String imageUrl13 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p513344864.webp";
        String imageUrl14 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p988260245.webp";
        String imageUrl15 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p513303986.webp";
        //儿童
        String imageUrl16 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2554167519.webp";
        String imageUrl17 = "https://img1.doubanio.com/view/photo/m/public/p2354987418.webp";
        String imageUrl18 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p531278288.webp";
        String imageUrl19 = "https://img1.doubanio.com/view/photo/m/public/p2881435027.webp";
        String imageUrl20 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p1791964116.webp";
        //灾难
        String imageUrl21 = "https://img9.doubanio.com/view/photo/s_ratio_poster/public/p2889314814.webp";
        String imageUrl22 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2360940399.webp";
        String imageUrl23 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p2209602029.webp";
        String imageUrl24 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p505238859.webp";
        String imageUrl25 = "https://img1.doubanio.com/view/photo/s_ratio_poster/public/p545837150.webp";

        items.add(new VideoModel(imageUrl1, "奇异博士2：疯狂多元宇宙", "",""));
        items.add(new VideoModel(imageUrl2, "雷神4：爱与雷霆", "",""));
        items.add(new VideoModel(imageUrl3, "Black Panther: Wakanda Forever", "",""));
        items.add(new VideoModel(imageUrl4, "Black Adam", "",""));
        items.add(new VideoModel(imageUrl5, "毒液2", "",""));
        items.add(new VideoModel(imageUrl6, "千と千尋の神隠し", "",""));
        items.add(new VideoModel(imageUrl7, "魔女の宅急便", "",""));
        items.add(new VideoModel(imageUrl8, "崖の上のポニョ", "",""));
        items.add(new VideoModel(imageUrl9, "風立ちぬ", "",""));
        items.add(new VideoModel(imageUrl10, "天空の城ラピュタ", "",""));
        items.add(new VideoModel(imageUrl11, "星际穿越", "",""));
        items.add(new VideoModel(imageUrl12, "蝴蝶效应", "",""));
        items.add(new VideoModel(imageUrl13, "盗梦空间", "",""));
        items.add(new VideoModel(imageUrl14, "源代码", "",""));
        items.add(new VideoModel(imageUrl15, "这个男人来自地球", "",""));
        items.add(new VideoModel(imageUrl16, "你看起来好像很好吃", "",""));
        items.add(new VideoModel(imageUrl17, "怪兽电力公司", "",""));
        items.add(new VideoModel(imageUrl18, "跳出我天地", "",""));
        items.add(new VideoModel(imageUrl19, "三毛从军记", "",""));
        items.add(new VideoModel(imageUrl20, "月升王国", "",""));
        items.add(new VideoModel(imageUrl21, "泰坦尼克号", "",""));
        items.add(new VideoModel(imageUrl22, "釜山行", "",""));
        items.add(new VideoModel(imageUrl23, "后天", "",""));
        items.add(new VideoModel(imageUrl24, "2012", "",""));
        items.add(new VideoModel(imageUrl25, "唐山大地震", "",""));
        return items;
    }
}
