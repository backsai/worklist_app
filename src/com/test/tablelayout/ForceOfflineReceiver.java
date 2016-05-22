package com.test.tablelayout;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.WindowManager;

public class ForceOfflineReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(final Context context, Intent intent) {
		// TODO Auto-generated method stub

		 
	AlertDialog.Builder dialogBuilder= new AlertDialog.Builder(context);
	dialogBuilder.setTitle("Note:");
	dialogBuilder.setMessage("Delet worklist is ongoing... ");
	dialogBuilder.setCancelable(false);
	dialogBuilder.setPositiveButton("OK",new DialogInterface.OnClickListener(){
		
		@Override
		
		public void onClick(DialogInterface dialog,int which){
			ActivityCollector.finishAll();
			Intent intent=new Intent(context,MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent);
			
		}
	});
	AlertDialog alertDialog = dialogBuilder.create();
	alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
	alertDialog.show();
	
	
	
	
	
	
	
	}

}
