package com.example.letsseatinmetro.Datahouse;

import com.example.letsseatinmetro.CardItem.MainCardItem;
import com.example.letsseatinmetro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 이승헌 on 2018-03-04.
 */

public class DataHouse {
    public static List<MainCardItem> mainCardItems = new ArrayList<MainCardItem>(){
        {
            add(new MainCardItem("1호선", R.drawable.line1, "소요산 - 인천/신창"));
            add(new MainCardItem("2호선", R.drawable.line2, "시청 - 시청"));
            add(new MainCardItem("3호선", R.drawable.line3, "대화 - 오금"));
            add(new MainCardItem("4호선", R.drawable.line4, "당고개 - 오이도"));
            add(new MainCardItem("5호선", R.drawable.line5, "방화 - 상일동/마천"));
            add(new MainCardItem("6호선", R.drawable.line6, "응암 - 봉화산"));
            add(new MainCardItem("7호선", R.drawable.line7, "장암 - 부평구청"));
            add(new MainCardItem("8호선", R.drawable.line8, "암사 - 모란"));
            add(new MainCardItem("9호선", R.drawable.line9, "개화 - 종합운동장"));
            add(new MainCardItem("경의·중앙선", R.drawable.linekyungei, "문산 - 서울역/지평"));
            add(new MainCardItem("분당선", R.drawable.lineboondang, "왕십리 - 수원"));
            add(new MainCardItem("신분당선", R.drawable.linenewboon, "강남 - 광교"));
            add(new MainCardItem("경춘선", R.drawable.linekyungchoon, "청량리 - 춘천"));
            add(new MainCardItem("공항철도", R.drawable.lineairport, "인천국제공항 - 서울역"));
        }
    };
}
