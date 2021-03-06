package appdebugdemo.lyb.com.appdebugdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import appdebugdemo.lyb.com.appdebugdemo.R;
import appdebugdemo.lyb.com.appdebugdemo.model.DebugMessageModel;

public class DebugDetailActivity extends Activity {
	
	private TextView time;
	private TextView message;
	private TextView request;
	private TextView response;
	private TextView netSize;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.debug_message_detail);
		
		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 0);
		
		time = (TextView) findViewById(R.id.debug_detail_time);
		message = (TextView) findViewById(R.id.debug_detail_message);
		request = (TextView) findViewById(R.id.debug_detail_request);
		response = (TextView) findViewById(R.id.debug_detail_response);
		netSize = (TextView) findViewById(R.id.debug_detail_netSize);
		
		time.setText(DebugMessageModel.sendingmessageList.get(position).startTime);
		message.setText(DebugMessageModel.sendingmessageList.get(position).message);
		request.setText(DebugMessageModel.sendingmessageList.get(position).requset);
		response.setText(DebugMessageModel.sendingmessageList.get(position).response);
		netSize.setText(DebugMessageModel.sendingmessageList.get(position).netSize);
		
	}

}
