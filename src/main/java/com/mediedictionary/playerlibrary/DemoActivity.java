package com.mediedictionary.playerlibrary;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class DemoActivity extends ListActivity implements OnItemClickListener {

	List<String> items;
	ArrayAdapter<String> adapter;

	@SuppressLint("SdCardPath")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_demo);

		items = new ArrayList<String>();
		items.add("http://img1.peiyinxiu.com/2014121211339c64b7fb09742e2c.mp4");
		items.add("rtmp://lm01.everyon.tv:1935/ptv/pld852");
		items.add("rtmp://183.129.244.168/weipai/s1");
		items.add("file:///sdcard/mix.mp4");
		items.add("file:///sdcard/DCIM/Camera/VID_20150128_155555.mp4");
		items.add("file:///sdcard/mix/1.mp4");
		items.add("http://img1.peiyinxiu.com/2015020312092f84a6085b34dc7c.mp4");
		items.add("rtmp://ctvideo.fc.llnwd.net/ctvideo/855crovd2");
		items.add("http://video.dispatch.tc.qq.com/11471065/y0013oyfi4q.mp4?vkey=38F12E2277BC6242F9D4E15C4C8FE2FD2F219FF039FEBC7717BB36BC0ADBEAC1C0F111B99CE73E795920188A8A4609EE29066BAB061B7E48743760D792BFBA780ABE1CC38491DCDDE7D41FF2FC74481A11B322CDED4D7C80&br=68776&platform=0&fmt=mp4&level=0&type=mp4");
		items.add("http://video.dispatch.tc.qq.com/52351758/c0019i651kz.mp4?vkey=1020E0E3CB794FDB7BF4229EC59660B4BDB9E9968499ECD975F28824D1531CA8E2E5B53257DD1FA2213A252727E3F2C6C3944EC5B4514AB9E7BDDA4696EA26AB4D8F8660DC6614A2DAECCD30AB5772E8BB7F8F3DC4320B8F&amp;br=62249&amp;platform=0&amp;fmt=mp4&amp;level=0&amp;type=mp4");
		items.add("http://video.dispatch.tc.qq.com/11471065/y0013oyfi4q.mp4?vkey=0C2FEA7BED3AF6E3298AB0EDDACB48EE6E9AF026FA2BB0096317B628D876ABCFE40131FF0DAA8A24108AA473E7B1B0C84899A98E11B4847866311D24F5A3039CD06BE24BB248904E6675CE3306DB9B4EFDF949D538CFAACD&br=68776&platform=0&fmt=mp4&level=0&type=mp4");
		//read data
		SharedPreferences mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
		((EditText) findViewById(R.id.et_url)).setText(mySharedPreferences.getString("url", ""));

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		setListAdapter(adapter);
		getListView().setOnItemClickListener(this);

		findViewById(R.id.btn_play).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				String url = ((EditText) findViewById(R.id.et_url)).getText().toString().trim();
				if (!TextUtils.isEmpty(url)) {
					startActivity(new Intent(DemoActivity.this, PlayerActivity.class).putExtra("url", url));
				}

				//save data
				SharedPreferences mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
				SharedPreferences.Editor editor = mySharedPreferences.edit();
				editor.putString("url", ((EditText) findViewById(R.id.et_url)).getText().toString().trim());
				editor.commit();

			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		startActivity(new Intent(this, PlayerActivity.class).putExtra("url", items.get(position)));
	}
}
