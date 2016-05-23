package com.test.tablelayout;



//import java.sql.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	private	final int FP = ViewGroup.LayoutParams.FILL_PARENT;
	private static final String[] m={"creat","ongoing","delay","closed"};
	//Button myButton;
	Button bu;
	Spinner spr;
	private EditText Date;
	private SharedPreferences pref;
    private ArrayAdapter<String> adapter;
    private TextView view ;
    private  EditText tabledit; 
    //private EditText time;
    //private Spinner spinner;
    int acc_bk=0;
	int acc_bkkk=0;
	String t_bk="2016-01-01";
	String t_bkstr="t_bk";
	String acc_bkstr="acc_bk";
	//int acc_bkkkk=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
		setContentView(R.layout.activity_main);
		Button myButton = (Button) findViewById(R.id.myButton);
		Button myButton1 =(Button) findViewById(R.id.myButton1);
		Button myButton2 =(Button) findViewById(R.id.myButton2);
		Button myButton3 =(Button) findViewById(R.id.myButton3);
		String t_bkk = load(t_bkstr,"");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        final String t_check=format.format(new Date());
		String acc_bkk = load(acc_bkstr,t_check);

		t_bk=t_bkk;
		//Toast.makeText(MainActivity.this, acc_bkk, Toast.LENGTH_LONG).show();
		//Toast.makeText(MainActivity.this, t_bkk, Toast.LENGTH_LONG).show();
		//acc_bkkk = Int.valueOf(acc_bkk).intValue();
		try {
		     acc_bkkk = Integer.valueOf(acc_bkk).intValue();
		} catch (NumberFormatException e) {
		    e.printStackTrace();
		}
		acc_bk=acc_bkkk;
		Date =(EditText)findViewById(R.id.Date);
		Date.setText(t_bkk);
		SharedPreferences pref1 = getSharedPreferences("data"+acc_bkstr+t_check,MODE_APPEND);
		//Toast.makeText(MainActivity.this, acc_bkkk+"", Toast.LENGTH_LONG).show();
		//acc_bkkk =atoi(acc_bkk);
		TableLayout tableLayout =(TableLayout)findViewById(R.id.TableLayout01);
		//tableLayout.setStretchAllColumns(true);
		//int acc_bk=0;
		
		pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		
		for( int roworg=0;roworg<acc_bkkk;roworg++)
		{
			TableRow tableRow = new TableRow (MainActivity.this); 
			tableRow.setBackgroundColor(Color.rgb(222, 222, 210));
			for (int colorg=0;colorg<4;colorg++)
			{   
				if (colorg==0)
				{ EditText tv0=new EditText(MainActivity.this);
				tv0.setBackgroundResource(R.drawable.shappee_old);
				tv0.setText(roworg+"");
				tv0.setTextSize(10);
				tableRow.addView(tv0);
				tv0.setId(colorg*100+roworg);
			}
				else {
					if (colorg==2) 
				{  
					spr = new Spinner(MainActivity.this);
					//将可选内容与ArrayAdapter连接起来
					//spr.setTag("");
				    adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,m);
			        
			        //设置下拉列表的风格
				    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			        //adapter.
			        //将adapter 添加到spinner中
				    spr.setAdapter(adapter);
				    
				    //添加事件Spinner事件监听  
			        //spr.setOnItemSelectedListener(new SpinnerSelectedListener());
			         
			        //设置默认值
			        spr.setVisibility(View.VISIBLE);
			        //spr.setTextSize(10);
			    	
					tableRow.addView(spr);
					spr.setId(colorg*100+roworg);
					//view = (TextView) findViewById(R.id.spinnerText);
				    //spinner = (Spinner) findViewById(R.id.Spinner01);
				
				   // tableRow.addView(bu);
					String row_col = pref1.getString(roworg+"_"+colorg,"");
					int row_col_1 = pref1.getInt(roworg+"_"+colorg+"Int",0);
					//Toast.makeText(MainActivity.this,roworg+"_"+colorg+"Int", Toast.LENGTH_LONG).show();
					spr.setSelection(row_col_1);
					//Toast.makeText(MainActivity.this,row_col_1+"", Toast.LENGTH_LONG).show();
					
				}
				else
				{
					//EditText tv = (EditText) findViewById(R.drawable.edittext1);
					EditText tv=new EditText(MainActivity.this);
					tv.setBackgroundResource(R.drawable.shappee_old);
					String row_col = pref1.getString(roworg+"_"+colorg,"");
					tv.setText(row_col);
					tv.setMaxWidth(10);
					tv.setMaxLines(2);
					tv.setTextSize(10);
					tv.setId(colorg*100+roworg);
					tableRow.addView(tv);
					
				}
			}
				
			}
		
			tableLayout.addView(tableRow,new TableLayout.LayoutParams(FP,WC));
			
		
		}
	
	
		myButton1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				String filename1=acc_bkstr+t_check;
				String filename2="data"+acc_bkstr+t_check;
				File f = new File("/data/data/com.test.tablelayout/files/"+filename1);  
				File ff = new File("/data/data/com.test.tablelayout/files/"+filename2);
               // File[] fl = f.listFiles();
                f.delete(); 
                ff.delete();
			Toast.makeText(MainActivity.this,"/data/data/com.test.tablelayout/files/"+acc_bkstr+t_check, Toast.LENGTH_LONG).show();
			Intent intent = new Intent("com.test.tablelayout.FORCE_OFFLINE");
			sendBroadcast(intent);
			}	
		});
	
		
		
		myButton2.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v) {
				SharedPreferences.Editor editor =getSharedPreferences("data"+acc_bkstr+t_check,MODE_APPEND).edit();
				for(int ii=0;ii<4;ii++){
				int i =0;
				if(ii != 2){for( i=0;i<acc_bk;i++)	{
				EditText sprEdit =(EditText) findViewById(ii*100+i);
				String col_2 = sprEdit.getText().toString();
				editor.putString(i+"_"+ii,col_2);
			
			//Toast.makeText(MainActivity.this,col_2, Toast.LENGTH_LONG).show();

			}	
			}
				else
			{	for( i=0;i<acc_bk;i++)	{
				Spinner sprEdit2 =(Spinner) findViewById(ii*100+i);
				String col_3 = sprEdit2.getSelectedItem().toString();
				int col_4 =sprEdit2.getLastVisiblePosition();
				editor.putString(i+"_"+ii,col_3);
				editor.putInt(i+"_"+ii+"Int",col_4);
				//Toast.makeText(MainActivity.this,col_3, Toast.LENGTH_LONG).show();

			}
			}
			}
				editor.commit();
				Toast.makeText(MainActivity.this,"save_finish", Toast.LENGTH_LONG).show();
			}
		});
		
		
		myButton3.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
			Intent intent = new Intent (MainActivity.this,historygetActivity.class);
			startActivity(intent);
			
			
			
			}
			
			});
			
		myButton.setOnClickListener(new View.OnClickListener(){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	        String t=format.format(new Date());
	      
	       
	        
			int acc = acc_bkkk;
			 
			public void onClick(View v) {
				 Date = (EditText) findViewById(R.id.Date);
				 Date.setText(t);
				  t_bk=t;
		//Toast.makeText(MainActivity.this, t_bk, Toast.LENGTH_LONG).show();

				TableLayout tableLayout =(TableLayout)findViewById(R.id.TableLayout01);
				//tableLayout.setStretchAllColumns(true);
				
				pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
			
				int row=0;
				int col=0;
				
				for( row=0;row<3;row++)
				{
					TableRow tableRow = new TableRow (MainActivity.this); 
					tableRow.setBackgroundColor(Color.rgb(222, 222, 210));
					for (col=0;col<4;col++)
					{   
						if (col==0)
						{ EditText tv0=new EditText(MainActivity.this);
						tv0.setBackgroundResource(R.drawable.shappee_old);
						tv0.setText(row+acc+"");
						tv0.setTextSize(10);
						tableRow.addView(tv0);
						tv0.setId(col*100+acc+row);
						//tv1.setId
						}
						else {
							if (col==2) 
						{  
							spr = new Spinner(MainActivity.this);
							//将可选内容与ArrayAdapter连接起来
							//spr.setTag("");
						    adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item,m);
					        
					        //设置下拉列表的风格
						    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					        
					        //将adapter 添加到spinner中
						    spr.setAdapter(adapter);
						   spr.setId(col*100+acc+row);
						    //添加事件Spinner事件监听  
					        //spr.setOnItemSelectedListener(new SpinnerSelectedListener());
					         
					        //设置默认值
					        spr.setVisibility(View.VISIBLE);
					        //spr.setTextSize(10);
							tableRow.addView(spr);
							
							//view = (TextView) findViewById(R.id.spinnerText);
						    //spinner = (Spinner) findViewById(R.id.Spinner01);
						
						   // tableRow.addView(bu);
							
						
						}
						else
						{
							//EditText tv = (EditText) findViewById(R.drawable.edittext1);
							EditText tv=new EditText(MainActivity.this);
							tv.setBackgroundResource(R.drawable.shappee_old);
							tv.setText("");
							tv.setMaxWidth(10);
							tv.setMaxLines(2);
							tv.setTextSize(10);
							tableRow.addView(tv);
							tv.setId(col*100+acc+row);
						}
					}
						//EditText tabledit=new EditText(MainActivity.this);
						
					
						//tabledit=(EditText) findViewById(R.id.edittext1);
						//String edittext1 = pref.getString("edittext1","");	
						//tabledit.setText(edittext1);
						//tableRow.addView(tabledit);
					}
				
					tableLayout.addView(tableRow,new TableLayout.LayoutParams(FP,WC));
					
				
				}
				
				acc = row+acc ;
				acc_bk = acc;
				
			}
				
				
			});
		}

class SpinnerSelectedListener implements OnItemSelectedListener{
		 
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
        view.setText("state："+m[arg2]);
      }
 
      public void onNothingSelected(AdapterView<?> arg0) {
       }
   }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onDestroy(){
		super.onDestroy();
		//int acc_bkk =acc_bk;
		save(acc_bk+"",acc_bkstr,t_bk);
		save(t_bk,t_bkstr,"");
		ActivityCollector.removeActivity(this);
	}
	
	public void save(String a,String b,String c) {
		FileOutputStream out = null;
		BufferedWriter writer =null;
		try{
			out= openFileOutput(b+c,Context.MODE_PRIVATE);
			writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write(a);
	   //Toast.makeText(MainActivity.this, a, Toast.LENGTH_LONG).show();
	
		}
		catch (IOException e) {
			e.printStackTrace();
			
		}
		finally{
			try{
				if(writer !=null){
					writer.close();
					
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public String load(String a,String b) {
		FileInputStream in = null;
		BufferedReader reader = null;
		StringBuilder content = new StringBuilder();
		try{
			in = openFileInput(a+b);
			reader = new BufferedReader (new InputStreamReader(in));
			String line ="";
			while ((line = reader.readLine()) != null){
				content.append(line);
			}
		
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			if(reader !=null) {
				try {
					
					reader.close();
					
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	return content.toString();
	}


	  public static void cleanCustomCache(File filePath,FilenameFilter file) {
	        
	        for (File item: filePath.listFiles(file)) {
                item.delete();
            }
	    }

	
	    
	}






