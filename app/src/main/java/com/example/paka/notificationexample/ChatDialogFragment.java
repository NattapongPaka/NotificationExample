package com.example.paka.notificationexample;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.example.paka.notificationexample.Activity.MyModel;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Noth on 21/6/2559.
 */
public class ChatDialogFragment extends DialogFragment implements View.OnClickListener {

    private static ChatDialogFragment chatDialogFragment;

    //private Pubnub pubnub;
    private String channel;
    //private Group group;
    private String group_id;
    private String title;
    //private MessageManager messageManager;

    private EditText edtSendMessageNotification_Box;
    private ImageButton btnSendMessage;
    private ImageButton btnPrevious;
    private ImageButton btnNext;
    //private TextView noti_msg;
    private ScrollView textAreaScroller;
    private TextView txtGroupTitle;
    private Button btnClose;
    private Button btnShow;
    private LinearLayout layoutContentMessage;
    private LinearLayout layoutContentSticker;
    private ViewPager viewPager;
    private FrameLayout frameContent;
    private SamplePager samplePager;

    private ArrayList<MyModel> myModelArrayList = new ArrayList<>();

    private String TAG = ChatDialogFragment.class.getSimpleName();


    public static ChatDialogFragment newInstance(String title, String group_id, String cal_id) {
        chatDialogFragment = new ChatDialogFragment();
        Bundle mBundle = new Bundle();
        mBundle.putString("TITLE", title);
        mBundle.putString("group_id", group_id);
        mBundle.putString("cal_id", cal_id);
        chatDialogFragment.setArguments(mBundle);
        return chatDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = (getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.notification_box,null);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        mBuilder.setView(view);
        initViewDialog(getView());
        return mBuilder.create();
    }

    private void initViewDialog(View v) {
        //Edit  Text
        edtSendMessageNotification_Box = (EditText) v.findViewById(R.id.edtSendMessageNotification_Box);
        //Button & Image Button
        btnSendMessage = (ImageButton) v.findViewById(R.id.btnSendMessage);
        btnPrevious = (ImageButton) v.findViewById(R.id.btnPrevious);
        btnNext = (ImageButton) v.findViewById(R.id.btnNext);
        btnClose = (Button) v.findViewById(R.id.btnClose);
        btnShow = (Button) v.findViewById(R.id.btnShow);
        //Text View
        //noti_msg = (TextView) v.findViewById(R.id.noti_msg);
        txtGroupTitle = (TextView) v.findViewById(R.id.txtGroupTitle);

        //Layout
        layoutContentMessage = (LinearLayout) v.findViewById(R.id.layoutContentMessage);
        layoutContentSticker = (LinearLayout) v.findViewById(R.id.layoutContentSticker);
        //View Pager
        viewPager = (ViewPager) v.findViewById(R.id.viewpagerContent);
        samplePager = new SamplePager(getActivity());
        viewPager.setAdapter(samplePager);
        viewPager.setCurrentItem(0,true);
        //Button Register Listener
        btnSendMessage.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle mBundle = getArguments();
        title = mBundle.getString("TITLE", "");
        group_id = mBundle.getString("group_id", "");
        //messageManager = new MessageManager(getActivity());
        //String cal_id = mBundle.getString("cal_id", "");
        //channel = ConfigSingleton.getInstantce().getCompany_id() + "-" + group_id;
        //group = Database.getInstantce().getGroupsDataSource().getGroupDataById(group_id);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnSendMessage) {
            Random r = new Random();
            String messageValue = "Test : "+ String.valueOf(r.nextInt(9999999));
            String lat = "";
            String lng = "";
            String chat_id = String.valueOf(r.nextInt(9999999));
            String message = getMessageBox();
            Log.i(TAG," "+messageValue+" "+chat_id+" "+message);
            mockupDataSamplePager(10);
            //messageManager.sendMessageToServer(messageValue,lat,lng,chat_id,group_id,title);
        }

        if(v.getId() == R.id.btnPrevious){
            int itemIndex = viewPager.getCurrentItem()-1;
            if(itemIndex >= 0) {
                viewPager.setCurrentItem(itemIndex);
            }
        }

        if(v.getId() == R.id.btnNext){
            int itemIndex = viewPager.getCurrentItem()+1;
            if(itemIndex < viewPager.getAdapter().getCount()) {
                viewPager.setCurrentItem(itemIndex);
            }
        }
    }

    public String getMessageBox(){
        if(!edtSendMessageNotification_Box.getText().toString().isEmpty() && edtSendMessageNotification_Box != null){
            return edtSendMessageNotification_Box.getText().toString();
        }else{
            return "Empty";
        }
    }

    public void mockupDataSamplePager(int loop){
        Random random = new Random();
        String test = "Often when launching a tabbed activity, there needs to be a way to select a particular tab to be displayed once the activity loads. For example, an activity has three tabs with one tab being a list of created posts. After a user creates a post on a separate activity, the user needs to be returned to the main activity with the \"new posts\" tab displayed. This can be done through the use of intent extras and the ViewPager#setCurrentItem method. First, when launching the tabbed activity, we need to pass in the selected tab as an extra:";
        for(int i=0;i<loop;i++) {
            int result = random.nextInt(999999);
            MyModel myModel = new MyModel();
            myModel.setGroupID(String.valueOf(result) + "_GroupID");
            if(i % 3 == 2) {
                myModel.setImagePath("Image");
            }
            myModel.setMessage(test + String.valueOf(result));
            myModel.setUserID(String.valueOf(result));
            myModelArrayList.add(myModel);
        }
        samplePager.notifyDataSetChanged();
    }

    private class SamplePager extends PagerAdapter{

        private LayoutInflater inflater;
        private Context context;

        public SamplePager(Context context){
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return myModelArrayList.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //Scroll View
            //ScrollView textAreaScroller = (ScrollView) container.findViewById(R.id.textAreaScroller);
            //Layout
            FrameLayout frameContent = new FrameLayout(container.getContext());
            MyModel myModel = myModelArrayList.get(position);
            if(myModel.getImagePath() == null) {
                TextView mTextView = new TextView(container.getContext());
                mTextView.setText(myModel.getMessage());
                mTextView.setTextColor(Color.parseColor("#0000FF"));
                mTextView.setSingleLine(false);
                mTextView.setMovementMethod(new ScrollingMovementMethod());
                frameContent.addView(mTextView);
            }else{
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.appicon);
                ImageView imageView = new ImageView(container.getContext());
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER);
                frameContent.addView(imageView);
            }
            container.addView(frameContent,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            return frameContent;
        }

//        @Override
//        public int getItemPosition(Object object) {
//            return super.getItemPosition(object);
//        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
