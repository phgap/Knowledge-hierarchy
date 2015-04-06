package com.example.cuijn.wechatgui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
//import java.lang.String;


public class MainActivity extends ActionBarActivity implements OnClickListener {

    private ArrayList fragments = new ArrayList();
    private MessageFragment messageFragment;
    private ContactsFragment contactsFragment;
    private NewsFragment newsFragment;
    private SettingFragment settingFragment;

    private ArrayList layouts = new ArrayList();

    private ArrayList images = new ArrayList();

    private ArrayList texts = new ArrayList();

    private FragmentManager fragmentMgr;
    private int preIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentMgr = getFragmentManager();
        initViews();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int tabIndex = 0;
        switch (v.getId()) {
            case R.id.message_layout:
                tabIndex = 0;
                break;
            case R.id.contacts_layout:
                tabIndex = 1;
                break;
            case R.id.news_layout:
                tabIndex = 2;
                break;
            case R.id.setting_layout:
                tabIndex = 3;
                break;
            default:
                break;
        }
        onTabClick(tabIndex);
    }

    private void initViews() {

        layouts.add(findViewById(R.id.message_layout));
        layouts.add(findViewById(R.id.contacts_layout));
        layouts.add(findViewById(R.id.news_layout));
        layouts.add(findViewById(R.id.setting_layout));

        images.add(findViewById(R.id.message_image));
        images.add(findViewById(R.id.contacts_image));
        images.add(findViewById(R.id.news_image));
        images.add(findViewById(R.id.setting_image));

        texts.add(findViewById(R.id.message_text));
        texts.add(findViewById(R.id.contacts_text));
        texts.add(findViewById(R.id.news_text));
        texts.add(findViewById(R.id.setting_text));

        for (int i = 0; i < 4; i++) {
            ((View) layouts.get(i)).setOnClickListener(this);
        }
    }

    private void onTabClick(int index) {
        setTabSelection(index);
        setContentFragment(index);
    }

    private void setContentFragment(int index) {
        FragmentTransaction transaction = fragmentMgr.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (messageFragment == null) {
                    messageFragment = new MessageFragment();
                    transaction.add(R.id.content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case 1:
                if (contactsFragment == null) {
                    contactsFragment = new ContactsFragment();
                    transaction.add(R.id.content, contactsFragment);
                } else {
                    transaction.show(contactsFragment);
                }
                break;
            case 2:
                if (newsFragment == null) {
                    newsFragment = new NewsFragment();
                    transaction.add(R.id.content, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                break;
            case 3:
                if (settingFragment == null) {
                    settingFragment = new SettingFragment();
                    transaction.add(R.id.content, settingFragment);
                } else {
                    transaction.show(settingFragment);
                }
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (contactsFragment != null) {
            transaction.hide(contactsFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    private void setTabSelection(int index) {
        int[] imgRes = {R.drawable.message_selected, R.drawable.contacts_selected, R.drawable.news_selected, R.drawable.setting_selected};
        if (preIndex != -1) {
            clearSelection(preIndex);
        }

        ImageView curImage = (ImageView) images.get(index);
        TextView curText = (TextView) texts.get(index);
        curImage.setImageResource(imgRes[index]);
        curText.setTextColor(Color.WHITE);
        preIndex = index;
    }

    private void clearSelection(int index) {
        int[] imgRes = {R.drawable.message_unselected, R.drawable.contacts_unselected, R.drawable.news_unselected, R.drawable.setting_unselected};
        ImageView curImage = (ImageView) images.get(index);
        TextView curText = (TextView) texts.get(index);
        curImage.setImageResource(imgRes[index]);
        curText.setTextColor(Color.parseColor("#82858b"));
    }
}
