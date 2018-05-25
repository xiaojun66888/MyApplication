package com.example.stepview;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.stepview.fragment.DrawCanvasFragment;
import com.example.stepview.fragment.HorizontalStepviewFragment;
import com.example.stepview.fragment.VerticalStepViewFrowardFragment;
import com.example.stepview.fragment.VerticalStepViewReverseFragment;
import com.example.stepview.fragment.VerticalStepViewSnapshotFragment;

/**
 * 日期：16/6/22 16:01
 * <p/>
 * 描述：
 */
public class MainActivity extends AppCompatActivity
{




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VerticalStepViewReverseFragment mVerticalStepViewFragment = new VerticalStepViewReverseFragment();
        getFragmentManager().beginTransaction().replace(R.id.container, mVerticalStepViewFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        VerticalStepViewReverseFragment mVerticalStepViewFragment;
        DrawCanvasFragment mDrawCanvasFragment;
        HorizontalStepviewFragment mHorizontalStepviewFragment;
        VerticalStepViewSnapshotFragment mVerticalStepViewSnapshotFragment;
        VerticalStepViewFrowardFragment mVerticalStepViewReverseFragment;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        int itemId = item.getItemId();
        switch(itemId)
        {
            case R.id.action_horizontal_stepview:
                mHorizontalStepviewFragment = new HorizontalStepviewFragment();
                fragmentTransaction.replace(R.id.container, mHorizontalStepviewFragment).commit();
                break;

            case R.id.action_drawcanvas:
                mDrawCanvasFragment = new DrawCanvasFragment();
                fragmentTransaction.replace(R.id.container, mDrawCanvasFragment).commit();
                break;
            case R.id.action_vertical_reverse:
                mVerticalStepViewFragment = new VerticalStepViewReverseFragment();
                fragmentTransaction.replace(R.id.container, mVerticalStepViewFragment).commit();
                break;

            case R.id.action_vertical_forward:
                mVerticalStepViewReverseFragment = new VerticalStepViewFrowardFragment();
                fragmentTransaction.replace(R.id.container, mVerticalStepViewReverseFragment).commit();
                break;

            case R.id.action_vertical_stepview_snapshot:
                mVerticalStepViewSnapshotFragment = new VerticalStepViewSnapshotFragment();
                fragmentTransaction.replace(R.id.container, mVerticalStepViewSnapshotFragment).commit();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}