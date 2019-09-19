package com.example.tabviewapp.activity;

import android.os.Bundle;
/*
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
*/
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.tabviewapp.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
/*
import br.com.aluno.materialtabapp.R;
*/
import com.example.tabviewapp.R;
import com.example.tabviewapp.fragments.EightFragment;
import com.example.tabviewapp.fragments.FiveFragment;
import com.example.tabviewapp.fragments.FourFragment;
import com.example.tabviewapp.fragments.NineFragment;
import com.example.tabviewapp.fragments.OneFragment;
import com.example.tabviewapp.fragments.SevenFragment;
import com.example.tabviewapp.fragments.SixFragment;
import com.example.tabviewapp.fragments.TenFragment;
import com.example.tabviewapp.fragments.ThreeFragment;
import com.example.tabviewapp.fragments.TwoFragment;

public class ScrollableTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable_tabs);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "UM");
        adapter.addFrag(new TwoFragment(), "DOIS");
        adapter.addFrag(new ThreeFragment(), "TRES");
        adapter.addFrag(new FourFragment(), "QUATRO");
        adapter.addFrag(new FiveFragment(), "CINCO");
        adapter.addFrag(new SixFragment(), "SEIS");
        adapter.addFrag(new SevenFragment(), "SETE");
        adapter.addFrag(new EightFragment(), "OITO");
        adapter.addFrag(new NineFragment(), "NOVE");
        adapter.addFrag(new TenFragment(), "DEZ");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
